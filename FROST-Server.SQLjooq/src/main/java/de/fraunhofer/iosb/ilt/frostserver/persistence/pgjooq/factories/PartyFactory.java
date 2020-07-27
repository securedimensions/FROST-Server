/*
 * Copyright (C) 2018 Fraunhofer Institut IOSB, Fraunhoferstr. 1, D 76131
 * Karlsruhe, Germany.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories;

import de.fraunhofer.iosb.ilt.frostserver.model.Datastream;
import de.fraunhofer.iosb.ilt.frostserver.model.EntityChangedMessage;
import de.fraunhofer.iosb.ilt.frostserver.model.EntityType;
import de.fraunhofer.iosb.ilt.frostserver.model.MultiDatastream;
import de.fraunhofer.iosb.ilt.frostserver.model.Party;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.DataSize;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.PostgresPersistenceManager;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.Utils;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.Utils.getFieldOrNull;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories.CAN_NOT_BE_NULL;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories.CHANGED_MULTIPLE_ROWS;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories.NO_ID_OR_NOT_FOUND;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableDatastreams;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableMultiDatastreams;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableParties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.TableCollection;
import de.fraunhofer.iosb.ilt.frostserver.property.EntityProperty;
import de.fraunhofer.iosb.ilt.frostserver.property.Property;
import de.fraunhofer.iosb.ilt.frostserver.query.Query;
import de.fraunhofer.iosb.ilt.frostserver.util.exception.IncompleteEntityException;
import de.fraunhofer.iosb.ilt.frostserver.util.exception.NoSuchEntityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hylke van der Schaaf
 *
 * @param <J> The type of the ID fields.
 */
public class PartyFactory<J extends Comparable> implements EntityFactory<Party, J> {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PartyFactory.class);

    private final EntityFactories<J> entityFactories;
    private final AbstractTableParties<J> table;
    private final TableCollection<J> tableCollection;

    public PartyFactory(EntityFactories<J> factories, AbstractTableParties<J> table) {
        this.entityFactories = factories;
        this.table = table;
        this.tableCollection = factories.tableCollection;
    }

    @Override
    public Party create(Record record, Query query, DataSize dataSize) {
        Set<Property> select = query == null ? Collections.emptySet() : query.getSelect();
        Party entity = new Party();
        entity.setName(getFieldOrNull(record, table.colName));
        entity.setDescription(getFieldOrNull(record, table.colDescription));
        entity.setEncodingType(getFieldOrNull(record, table.colEncodingType));
        J id = getFieldOrNull(record, table.getId());
        if (id != null) {
            entity.setId(entityFactories.idFromObject(id));
        }
        if (select.isEmpty() || select.contains(EntityProperty.METADATA)) {
            String metaDataString = getFieldOrNull(record, table.colMetadata);
            dataSize.increase(metaDataString == null ? 0 : metaDataString.length());
            entity.setMetadata(metaDataString);
        }
        if (select.isEmpty() || select.contains(EntityProperty.PROPERTIES)) {
            String props = getFieldOrNull(record, table.colProperties);
            entity.setProperties(Utils.jsonToObject(props, Map.class));
        }
        return entity;
    }

    @Override
    public boolean insert(PostgresPersistenceManager<J> pm, Party s) throws NoSuchEntityException, IncompleteEntityException {
        Map<Field, Object> insert = new HashMap<>();
        insert.put(table.colName, s.getName());
        insert.put(table.colDescription, s.getDescription());
        insert.put(table.colEncodingType, s.getEncodingType());
        // We currently assume it's a string.
        insert.put(table.colMetadata, s.getMetadata().toString());
        insert.put(table.colProperties, EntityFactories.objectToJson(s.getProperties()));

        entityFactories.insertUserDefinedId(pm, insert, table.getId(), s);

        DSLContext dslContext = pm.getDslContext();
        Record1<J> result = dslContext.insertInto(table)
                .set(insert)
                .returningResult(table.getId())
                .fetchOne();
        J generatedId = result.component1();
        LOGGER.debug("Inserted Party. Created id = {}.", generatedId);
        s.setId(entityFactories.idFromObject(generatedId));

        // Create new datastreams, if any.
        for (Datastream ds : s.getDatastreams()) {
            ds.setParty(new Party(s.getId()));
            ds.complete();
            pm.insert(ds);
        }

        // Create new multiDatastreams, if any.
        for (MultiDatastream mds : s.getMultiDatastreams()) {
            mds.setParty(new Party(s.getId()));
            mds.complete();
            pm.insert(mds);
        }

        return true;
    }

    @Override
    public EntityChangedMessage update(PostgresPersistenceManager<J> pm, Party s, J partyId) throws NoSuchEntityException, IncompleteEntityException {
        Map<Field, Object> update = new HashMap<>();
        EntityChangedMessage message = new EntityChangedMessage();

        if (s.isSetName()) {
            if (s.getName() == null) {
                throw new IncompleteEntityException("name" + CAN_NOT_BE_NULL);
            }
            update.put(table.colName, s.getName());
            message.addField(EntityProperty.NAME);
        }
        if (s.isSetDescription()) {
            if (s.getDescription() == null) {
                throw new IncompleteEntityException(EntityProperty.DESCRIPTION.jsonName + CAN_NOT_BE_NULL);
            }
            update.put(table.colDescription, s.getDescription());
            message.addField(EntityProperty.DESCRIPTION);
        }
        if (s.isSetEncodingType()) {
            if (s.getEncodingType() == null) {
                throw new IncompleteEntityException("encodingType" + CAN_NOT_BE_NULL);
            }
            update.put(table.colEncodingType, s.getEncodingType());
            message.addField(EntityProperty.ENCODINGTYPE);
        }
        if (s.isSetMetadata()) {
            if (s.getMetadata() == null) {
                throw new IncompleteEntityException("metadata" + CAN_NOT_BE_NULL);
            }
            // We currently assume it's a string.
            update.put(table.colMetadata, s.getMetadata().toString());
            message.addField(EntityProperty.METADATA);
        }
        if (s.isSetProperties()) {
            update.put(table.colProperties, EntityFactories.objectToJson(s.getProperties()));
            message.addField(EntityProperty.PROPERTIES);
        }

        DSLContext dslContext = pm.getDslContext();
        long count = 0;
        if (!update.isEmpty()) {
            count = dslContext.update(table)
                    .set(update)
                    .where(table.getId().equal(partyId))
                    .execute();
        }
        if (count > 1) {
            LOGGER.error("Updating Party {} caused {} rows to change!", partyId, count);
            throw new IllegalStateException(CHANGED_MULTIPLE_ROWS);
        }

        linkExistingDatastreams(s, pm, dslContext, partyId);

        linkExistingMultiDatastreams(s, pm, dslContext, partyId);

        LOGGER.debug("Updated Party {}", partyId);
        return message;
    }

    private void linkExistingMultiDatastreams(Party s, PostgresPersistenceManager<J> pm, DSLContext dslContext, J partyId) throws NoSuchEntityException {
        for (MultiDatastream mds : s.getMultiDatastreams()) {
            if (mds.getId() == null || !entityFactories.entityExists(pm, mds)) {
                throw new NoSuchEntityException("MultiDatastream" + NO_ID_OR_NOT_FOUND);
            }
            J mdsId = (J) mds.getId().getValue();
            AbstractTableMultiDatastreams<J> qmds = tableCollection.getTableMultiDatastreams();
            long mdsCount = dslContext.update(qmds)
                    .set(qmds.getPartyId(), partyId)
                    .where(qmds.getId().eq(mdsId))
                    .execute();
            if (mdsCount > 0) {
                LOGGER.debug("Assigned multiDatastream {} to party {}.", mdsId, partyId);
            }
        }
    }

    private void linkExistingDatastreams(Party s, PostgresPersistenceManager<J> pm, DSLContext dslContext, J partyId) throws NoSuchEntityException {
        for (Datastream ds : s.getDatastreams()) {
            if (ds.getId() == null || !entityFactories.entityExists(pm, ds)) {
                throw new NoSuchEntityException("Datastream" + NO_ID_OR_NOT_FOUND);
            }
            J dsId = (J) ds.getId().getValue();
            AbstractTableDatastreams<J> qds = tableCollection.getTableDatastreams();
            long dsCount = dslContext.update(qds)
                    .set(qds.getPartyId(), partyId)
                    .where(qds.getId().eq(dsId))
                    .execute();
            if (dsCount > 0) {
                LOGGER.debug("Assigned datastream {} to party {}.", dsId, partyId);
            }
        }
    }

    @Override
    public void delete(PostgresPersistenceManager<J> pm, J entityId) throws NoSuchEntityException {
        long count = pm.getDslContext()
                .delete(table)
                .where(table.getId().eq(entityId))
                .execute();
        if (count == 0) {
            throw new NoSuchEntityException("Party " + entityId + " not found.");
        }
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.PARTY;
    }

    @Override
    public Field<J> getPrimaryKey() {
        return table.getId();
    }

}