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

import de.fraunhofer.iosb.ilt.frostserver.model.EntityChangedMessage;
import de.fraunhofer.iosb.ilt.frostserver.model.EntityType;
import de.fraunhofer.iosb.ilt.frostserver.model.Observation;
import de.fraunhofer.iosb.ilt.frostserver.model.ObservationGroup;
import de.fraunhofer.iosb.ilt.frostserver.model.core.EntitySet;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.DataSize;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.PostgresPersistenceManager;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.Utils;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.Utils.getFieldOrNull;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories.CAN_NOT_BE_NULL;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories.CHANGED_MULTIPLE_ROWS;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories.LINKED_O_TO_OG;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationGroups;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationsObservationGroups;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.TableCollection;
import de.fraunhofer.iosb.ilt.frostserver.property.EntityProperty;
import de.fraunhofer.iosb.ilt.frostserver.query.Query;
import static de.fraunhofer.iosb.ilt.frostserver.util.Constants.UTC;
import de.fraunhofer.iosb.ilt.frostserver.util.exception.IncompleteEntityException;
import de.fraunhofer.iosb.ilt.frostserver.util.exception.NoSuchEntityException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hylke van der Schaaf
 * @param <J> The type of the ID fields.
 */
public class ObservationGroupFactory<J extends Comparable> implements EntityFactory<ObservationGroup, J> {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ObservationGroupFactory.class);
    private final EntityFactories<J> entityFactories;
    private final AbstractTableObservationGroups<J> table;
    private final TableCollection<J> tableCollection;

    public ObservationGroupFactory(EntityFactories<J> factories, AbstractTableObservationGroups<J> table) {
        this.entityFactories = factories;
        this.table = table;
        this.tableCollection = factories.tableCollection;
    }

    @Override
    public ObservationGroup create(Record tuple, Query query, DataSize dataSize) {
    	ObservationGroup entity = new ObservationGroup();
        J id = getFieldOrNull(tuple, table.getId());
        if (id != null) {
            entity.setId(entityFactories.idFromObject(id));
        }
        entity.setName(getFieldOrNull(tuple, table.colName));
        entity.setDescription(getFieldOrNull(tuple, table.colDescription));
        entity.setCreated(Utils.instantFromTime(getFieldOrNull(tuple, table.colCreated)));
        
        /* This needs to go into the insert section !
        OffsetDateTime pTimeStart = getFieldOrNull(tuple, table.colRuntimeStart);
        OffsetDateTime pTimeEnd = getFieldOrNull(tuple, table.colRuntimeEnd);
        if (pTimeStart != null && pTimeEnd != null) {
            entity.setRuntime(Utils.intervalFromTimes(pTimeStart, pTimeEnd));
        }
        */
        return entity;
    }

    @Override
    public boolean insert(PostgresPersistenceManager<J> pm, ObservationGroup og) throws NoSuchEntityException, IncompleteEntityException {
    	OffsetDateTime newTime = OffsetDateTime.ofInstant(Instant.ofEpochMilli(og.getCreated().getDateTime().getMillis()), UTC);

    	DSLContext dslContext = pm.getDslContext();

        Map<Field, Object> insert = new HashMap<>();
        insert.put(table.colName, og.getName());
        insert.put(table.colDescription, og.getDescription());
        insert.put(table.colCreated, newTime);
        
        entityFactories.insertUserDefinedId(pm, insert, table.getId(), og);

        Record1<J> result = dslContext.insertInto(table)
                .set(insert)
                .returningResult(table.getId())
                .fetchOne();
        J generatedId = result.component1();
        LOGGER.debug("Inserted ObservationGroup. Created id = {}.", generatedId);
        og.setId(entityFactories.idFromObject(generatedId));

        EntitySet<Observation> observations = og.getObservations();
        for (Observation o : observations) {
            entityFactories.entityExistsOrCreate(pm, o);
            J oId = (J) o.getId().getValue();
            AbstractTableObservationsObservationGroups<J> qlog = tableCollection.getTableObservationsObservationGroups();
            dslContext.insertInto(qlog)
                    .set(qlog.getObservationGroupId(), generatedId)
                    .set(qlog.getObservationId(), oId)
                    .execute();
            LOGGER.debug(LINKED_O_TO_OG, oId, generatedId);
        }

        return true;
    }

    @Override
    public EntityChangedMessage update(PostgresPersistenceManager<J> pm, ObservationGroup og, J id) throws IncompleteEntityException {
        Map<Field, Object> update = new HashMap<>();

        EntityChangedMessage message = new EntityChangedMessage();

        if (og.isSetDescription()) {
            if (og.getDescription() == null) {
                throw new IncompleteEntityException(EntityProperty.DESCRIPTION.jsonName + CAN_NOT_BE_NULL);
            }
            update.put(table.colDescription, og.getDescription());
            message.addField(EntityProperty.DESCRIPTION);
        }
        if (og.isSetName()) {
            if (og.getName() == null) {
                throw new IncompleteEntityException("name" + CAN_NOT_BE_NULL);
            }
            update.put(table.colName, og.getName());
            message.addField(EntityProperty.NAME);
        }
        
        DSLContext dslContext = pm.getDslContext();
        long count = 0;
        if (!update.isEmpty()) {
            count = dslContext.update(table)
                    .set(update)
                    .where(table.getId().equal(id))
                    .execute();
        }
        if (count > 1) {
            LOGGER.error("Updating Observation {} caused {} rows to change!", id, count);
            throw new IllegalStateException(CHANGED_MULTIPLE_ROWS);
        }
        LOGGER.debug("Updated Observation {}", id);

        // Link existing locations to the ObservationGroup.
        for (Observation o : og.getObservations()) {
            if (!entityFactories.entityExists(pm, o)) {
                throw new IllegalArgumentException("Unknown Observation or Observation with no id.");
            }
            J lId = (J) o.getId().getValue();

            AbstractTableObservationsObservationGroups<J> qlog = tableCollection.getTableObservationsObservationGroups();
            dslContext.insertInto(qlog)
                    .set(qlog.getObservationGroupId(), id)
                    .set(qlog.getObservationId(), lId)
                    .execute();
            LOGGER.debug(LINKED_O_TO_OG, lId, id);
        }
        return message;
    }

    @Override
    public void delete(PostgresPersistenceManager<J> pm, J entityId) throws NoSuchEntityException {
        long count = pm.getDslContext()
                .delete(table)
                .where(table.getId().eq(entityId))
                .execute();
        if (count == 0) {
            throw new NoSuchEntityException("ObservationGroup " + entityId + " not found.");
        }
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.OBSERVATIONGROUP;
    }

    @Override
    public Field<J> getPrimaryKey() {
        return table.getId();
    }

}
