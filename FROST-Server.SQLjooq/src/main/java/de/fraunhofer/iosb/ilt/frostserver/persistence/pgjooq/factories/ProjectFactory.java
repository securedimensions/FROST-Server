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
import de.fraunhofer.iosb.ilt.frostserver.model.Project;
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
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableProjects;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.TableCollection;
import de.fraunhofer.iosb.ilt.frostserver.property.EntityProperty;
import de.fraunhofer.iosb.ilt.frostserver.property.Property;
import de.fraunhofer.iosb.ilt.frostserver.query.Query;
import de.fraunhofer.iosb.ilt.frostserver.util.exception.IncompleteEntityException;
import de.fraunhofer.iosb.ilt.frostserver.util.exception.NoSuchEntityException;

import java.time.OffsetDateTime;
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
public class ProjectFactory<J extends Comparable> implements EntityFactory<Project, J> {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectFactory.class);

    private final EntityFactories<J> entityFactories;
    private final AbstractTableProjects<J> table;
    private final TableCollection<J> tableCollection;

    public ProjectFactory(EntityFactories<J> factories, AbstractTableProjects<J> table) {
        this.entityFactories = factories;
        this.table = table;
        this.tableCollection = factories.tableCollection;
    }

    @Override
    public Project create(Record record, Query query, DataSize dataSize) {
        Set<Property> select = query == null ? Collections.emptySet() : query.getSelect();
        Project entity = new Project();
        entity.setName(getFieldOrNull(record, table.colName));
        entity.setDescription(getFieldOrNull(record, table.colDescription));
        entity.setUrl(getFieldOrNull(record, table.colUrl));
        entity.setClassification(getFieldOrNull(record, table.colClassification));
        entity.setTermsOfUse(getFieldOrNull(record, table.colTermsOfUse));
        entity.setPrivacyPolicy(getFieldOrNull(record, table.colPrivacyPolicy));
        if (select.isEmpty() || select.contains(EntityProperty.PROPERTIES)) {
            String props = getFieldOrNull(record, table.colProperties);
            entity.setProperties(Utils.jsonToObject(props, Map.class));
        }
        J id = getFieldOrNull(record, table.getId());
        if (id != null) {
            entity.setId(entityFactories.idFromObject(id));
        }
        entity.setCreated(Utils.instantFromTime(getFieldOrNull(record, table.colCreated)));
        
        OffsetDateTime pTimeStart = getFieldOrNull(record, table.colRuntimeStart);
        OffsetDateTime pTimeEnd = getFieldOrNull(record, table.colRuntimeEnd);
        if (pTimeStart != null && pTimeEnd != null) {
            entity.setRuntime(Utils.intervalFromTimes(pTimeStart, pTimeEnd));
        }
        return entity;
    }

    @Override
    public boolean insert(PostgresPersistenceManager<J> pm, Project p) throws NoSuchEntityException, IncompleteEntityException {
        Map<Field, Object> insert = new HashMap<>();
        insert.put(table.colName, p.getName());
        insert.put(table.colDescription, p.getDescription());
        insert.put(table.colUrl, p.getUrl());
        insert.put(table.colProperties, EntityFactories.objectToJson(p.getProperties()));

        entityFactories.insertUserDefinedId(pm, insert, table.getId(), p);

        DSLContext dslContext = pm.getDslContext();
        Record1<J> result = dslContext.insertInto(table)
                .set(insert)
                .returningResult(table.getId())
                .fetchOne();
        J generatedId = result.component1();
        LOGGER.debug("Inserted Project. Created id = {}.", generatedId);
        p.setId(entityFactories.idFromObject(generatedId));

        // Create new datastreams, if any.
        for (Datastream ds : p.getDatastreams()) {
            ds.setProject(new Project(p.getId()));
            ds.complete();
            pm.insert(ds);
        }

        // Create new multiDatastreams, if any.
        for (MultiDatastream mds : p.getMultiDatastreams()) {
            mds.setProject(new Project(p.getId()));
            mds.complete();
            pm.insert(mds);
        }

        return true;
    }

    @Override
    public EntityChangedMessage update(PostgresPersistenceManager<J> pm, Project p, J projectId) throws NoSuchEntityException, IncompleteEntityException {
        Map<Field, Object> update = new HashMap<>();
        EntityChangedMessage message = new EntityChangedMessage();

        if (p.isSetName()) {
            if (p.getName() == null) {
                throw new IncompleteEntityException("name" + CAN_NOT_BE_NULL);
            }
            update.put(table.colName, p.getName());
            message.addField(EntityProperty.NAME);
        }
        if (p.isSetDescription()) {
            if (p.getDescription() == null) {
                throw new IncompleteEntityException(EntityProperty.DESCRIPTION.jsonName + CAN_NOT_BE_NULL);
            }
            update.put(table.colDescription, p.getDescription());
            message.addField(EntityProperty.DESCRIPTION);
        }
        if (p.isSetUrl()) {
            if (p.getUrl() == null) {
                throw new IncompleteEntityException("url" + CAN_NOT_BE_NULL);
            }
            update.put(table.colUrl, p.getUrl());
            message.addField(EntityProperty.NICKNAME);
        }
        if (p.isSetProperties()) {
            update.put(table.colProperties, EntityFactories.objectToJson(p.getProperties()));
            message.addField(EntityProperty.PROPERTIES);
        }

        DSLContext dslContext = pm.getDslContext();
        long count = 0;
        if (!update.isEmpty()) {
            count = dslContext.update(table)
                    .set(update)
                    .where(table.getId().equal(projectId))
                    .execute();
        }
        if (count > 1) {
            LOGGER.error("Updating Project {} caused {} rows to change!", projectId, count);
            throw new IllegalStateException(CHANGED_MULTIPLE_ROWS);
        }

        linkExistingDatastreams(p, pm, dslContext, projectId);

        linkExistingMultiDatastreams(p, pm, dslContext, projectId);

        LOGGER.debug("Updated Project {}", projectId);
        return message;
    }

    private void linkExistingMultiDatastreams(Project p, PostgresPersistenceManager<J> pm, DSLContext dslContext, J projectId) throws NoSuchEntityException {
        for (MultiDatastream mds : p.getMultiDatastreams()) {
            if (mds.getId() == null || !entityFactories.entityExists(pm, mds)) {
                throw new NoSuchEntityException("MultiDatastream" + NO_ID_OR_NOT_FOUND);
            }
            J mdsId = (J) mds.getId().getValue();
            AbstractTableMultiDatastreams<J> qmds = tableCollection.getTableMultiDatastreams();
            long mdsCount = dslContext.update(qmds)
                    .set(qmds.getProjectId(), projectId)
                    .where(qmds.getId().eq(mdsId))
                    .execute();
            if (mdsCount > 0) {
                LOGGER.debug("Assigned multiDatastream {} to project {}.", mdsId, projectId);
            }
        }
    }

    private void linkExistingDatastreams(Project p, PostgresPersistenceManager<J> pm, DSLContext dslContext, J projectId) throws NoSuchEntityException {
        for (Datastream ds : p.getDatastreams()) {
            if (ds.getId() == null || !entityFactories.entityExists(pm, ds)) {
                throw new NoSuchEntityException("Datastream" + NO_ID_OR_NOT_FOUND);
            }
            J dsId = (J) ds.getId().getValue();
            AbstractTableDatastreams<J> qds = tableCollection.getTableDatastreams();
            long dsCount = dslContext.update(qds)
                    .set(qds.getProjectId(), projectId)
                    .where(qds.getId().eq(dsId))
                    .execute();
            if (dsCount > 0) {
                LOGGER.debug("Assigned datastream {} to project {}.", dsId, projectId);
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
            throw new NoSuchEntityException("Project " + entityId + " not found.");
        }
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.PROJECT;
    }

    @Override
    public Field<J> getPrimaryKey() {
        return table.getId();
    }

}
