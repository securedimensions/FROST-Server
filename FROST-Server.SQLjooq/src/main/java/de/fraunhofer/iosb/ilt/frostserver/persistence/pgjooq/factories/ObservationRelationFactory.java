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
import de.fraunhofer.iosb.ilt.frostserver.model.FeatureOfInterest;
import de.fraunhofer.iosb.ilt.frostserver.model.MultiDatastream;
import de.fraunhofer.iosb.ilt.frostserver.model.Observation;
import de.fraunhofer.iosb.ilt.frostserver.model.ObservationGroup;
import de.fraunhofer.iosb.ilt.frostserver.model.ObservationRelation;
import de.fraunhofer.iosb.ilt.frostserver.model.core.Id;
import de.fraunhofer.iosb.ilt.frostserver.model.ext.TimeInstant;
import de.fraunhofer.iosb.ilt.frostserver.model.ext.TimeValue;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.DataSize;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.PostgresPersistenceManager;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.ResultType;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.Utils;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.Utils.getFieldOrNull;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories.CAN_NOT_BE_NULL;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories.CHANGED_MULTIPLE_ROWS;
import static de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories.LINKED_O_TO_OG;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableMultiDatastreamsObsProperties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationRelations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationsObservationGroups;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.TableCollection;
import de.fraunhofer.iosb.ilt.frostserver.property.EntityProperty;
import de.fraunhofer.iosb.ilt.frostserver.property.NavigationPropertyMain;
import de.fraunhofer.iosb.ilt.frostserver.property.Property;
import de.fraunhofer.iosb.ilt.frostserver.query.Query;
import de.fraunhofer.iosb.ilt.frostserver.util.exception.IncompleteEntityException;
import de.fraunhofer.iosb.ilt.frostserver.util.exception.NoSuchEntityException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
public class ObservationRelationFactory<J extends Comparable> implements EntityFactory<ObservationRelation, J> {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ObservationRelationFactory.class);

    private final EntityFactories<J> entityFactories;
    private final AbstractTableObservationRelations<J> table;
    private final TableCollection<J> tableCollection;

    public ObservationRelationFactory(EntityFactories<J> factories, AbstractTableObservationRelations<J> table) {
        this.entityFactories = factories;
        this.table = table;
        this.tableCollection = factories.tableCollection;
    }

    @Override
    public ObservationRelation create(Record tuple, Query query, DataSize dataSize) {
    	ObservationRelation entity = new ObservationRelation();
        Set<Property> select = query == null ? Collections.emptySet() : query.getSelect();

        J id = getFieldOrNull(tuple, table.getId());
        if (id != null) {
            entity.setId(entityFactories.idFromObject(id));
        }
        
        J oId = getFieldOrNull(tuple, table.getObservationId());
        if (oId != null) {
        	entity.setObservation(entityFactories.observationFromId(oId));
        }

        J ogId = getFieldOrNull(tuple, table.getObservationGroupId());
        if (ogId != null) {
            entity.setObservationGroup(entityFactories.observationGroupFromId(ogId));
        }

        entity.setName(getFieldOrNull(tuple, table.colName));
        entity.setDescription(getFieldOrNull(tuple, table.colDescription));
        entity.setType(getFieldOrNull(tuple, table.colType));

        return entity;
    }

    @Override
    public boolean insert(PostgresPersistenceManager<J> pm, ObservationRelation newObservationRelation) throws NoSuchEntityException, IncompleteEntityException {
        Observation o = newObservationRelation.getObservation();
        ObservationGroup og = newObservationRelation.getObservationGroup();
        String type = newObservationRelation.getType();
        String name = newObservationRelation.getName();
        String description = newObservationRelation.getDescription();

        Map<Field, Object> insert = new HashMap<>();
        
        if (o != null) {
            insert.put(table.getObservationId(), o.getId().getValue());
        }
        if (og != null) {
            insert.put(table.getObservationGroupId(), og.getId().getValue());
        }

        insert.put(table.colType, type);
        insert.put(table.colName, name);
        insert.put(table.colDescription, description);

        DSLContext dslContext = pm.getDslContext();
        Record1<J> result = dslContext.insertInto(table)
                .set(insert)
                .returningResult(table.getId())
                .fetchOne();
        J generatedId = result.component1();
        LOGGER.debug("Inserted ObservationRelation. Created id = {}.", generatedId);
        newObservationRelation.setId(entityFactories.idFromObject(generatedId));
        return true;
    }

    @Override
    public EntityChangedMessage update(PostgresPersistenceManager<J> pm, ObservationRelation newObservationRelation, J id) throws IncompleteEntityException {
        
        Map<Field, Object> update = new HashMap<>();
        EntityChangedMessage message = new EntityChangedMessage();

        if (newObservationRelation.isSetObservation()) {
            if (!entityFactories.entityExists(pm, newObservationRelation.getObservation())) {
                throw new IncompleteEntityException("Observation not found.");
            }
            update.put(table.getObservationId(), newObservationRelation.getObservation().getId().getValue());
            message.addField(NavigationPropertyMain.OBSERVATION);
        }
        
        if (newObservationRelation.isSetObservationGroup()) {
            if (!entityFactories.entityExists(pm, newObservationRelation.getObservationGroup())) {
                throw new IncompleteEntityException("ObservationGroup not found.");
            }
            update.put(table.getObservationGroupId(), newObservationRelation.getObservationGroup().getId().getValue());
            message.addField(NavigationPropertyMain.OBSERVATIONGROUP);
        }
        
        if (newObservationRelation.isSetType()) {
            if (newObservationRelation.getType() == null) {
                throw new IncompleteEntityException("name" + CAN_NOT_BE_NULL);
            }
            update.put(table.colType, newObservationRelation.getType());
            message.addField(EntityProperty.TYPE);
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
            LOGGER.error("Updating ObservationRelation {} caused {} rows to change!", id, count);
            throw new IllegalStateException(CHANGED_MULTIPLE_ROWS);
        }
        LOGGER.debug("Updated ObservationRelation {}", id);
                
        return message;
    }

    @Override
    public void delete(PostgresPersistenceManager<J> pm, J entityId) throws NoSuchEntityException {
        long count = pm.getDslContext()
                .delete(table)
                .where(table.getId().eq(entityId))
                .execute();
        if (count == 0) {
            throw new NoSuchEntityException("ObservationRelation " + entityId + " not found.");
        }
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.OBSERVATIONRELATION;
    }

    @Override
    public Field<J> getPrimaryKey() {
        return table.getId();
    }

}
