/*
 * Copyright (C) 2016 Fraunhofer Institut IOSB, Fraunhoferstr. 1, D 76131
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
package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.imp;

import de.fraunhofer.iosb.ilt.frostserver.model.core.Entity;
import de.fraunhofer.iosb.ilt.frostserver.model.core.IdLong;
import de.fraunhofer.iosb.ilt.frostserver.persistence.IdManager;
import de.fraunhofer.iosb.ilt.frostserver.persistence.IdManagerLong;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.IdGenerationHandler;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.PostgresPersistenceManager;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.PropertyResolver;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.TableCollection;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongActuators;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongDatastreams;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongFeatures;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongHistLocations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongLicenses;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongLocations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongLocationsHistLocations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongMultiDatastreams;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongMultiDatastreamsObsProperties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongObsProperties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongObservationGroups;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongObservationRelations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongObservations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongObservationsObservationGroups;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongParties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongProjects;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongSensors;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongTaskingCapabilities;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongTasks;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongThings;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongThingsLocations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidObservationsObservationGroups;
import de.fraunhofer.iosb.ilt.frostserver.settings.CoreSettings;

/**
 *
 * @author jab
 * @author scf
 */
public class PostgresPersistenceManagerLong extends PostgresPersistenceManager<Long> {

    private static final String LIQUIBASE_CHANGELOG_FILENAME = "liquibase/tables.xml";

    private static final IdManagerLong ID_MANAGER = new IdManagerLong();
    private static EntityFactories<Long> entityFactories;
    private static PropertyResolver<Long> propertyResolver;

    @Override
    public IdManager getIdManager() {
        return ID_MANAGER;
    }

    @Override
    public void init(CoreSettings settings) {
        super.init(settings);
        IdGenerationHandler.setIdGenerationMode(settings.getPersistenceSettings().getIdGenerationMode());
        if (entityFactories == null) {
            init(new TableCollection<Long>()
                    .setTableActuators(TableLongActuators.ACTUATORS)
                    .setTableDatastreams(TableLongDatastreams.DATASTREAMS)
                    .setTableFeatures(TableLongFeatures.FEATURES)
                    .setTableHistLocations(TableLongHistLocations.HIST_LOCATIONS)
                    .setTableLocations(TableLongLocations.LOCATIONS)
                    .setTableLocationsHistLocations(TableLongLocationsHistLocations.LOCATIONS_HIST_LOCATIONS)
                    .setTableMultiDatastreams(TableLongMultiDatastreams.MULTI_DATASTREAMS)
                    .setTableMultiDatastreamsObsProperties(TableLongMultiDatastreamsObsProperties.MULTI_DATASTREAMS_OBS_PROPERTIES)
                    .setTableObservations(TableLongObservations.OBSERVATIONS)
                    .setTableObservationGroups(TableLongObservationGroups.OBSERVATION_GROUPS)
                    .setTableObservationsObservationGroups(TableLongObservationsObservationGroups.OBSERVATIONS_OBSERVATION_GROUPS)
                    .setTableObservationRelations(TableLongObservationRelations.OBSERVATION_RELATIONS)
                    .setTableObsProperties(TableLongObsProperties.OBS_PROPERTIES)
                    .setTableLicenses(TableLongLicenses.LICENSES)
                    .setTableSensors(TableLongSensors.SENSORS)
                    .setTableParties(TableLongParties.PARTIES)
                    .setTableProjects(TableLongProjects.PROJECTS)
                    .setTableTasks(TableLongTasks.TASKS)
                    .setTableTaskingCapabilities(TableLongTaskingCapabilities.TASKINGCAPABILITIES)
                    .setTableThings(TableLongThings.THINGS)
                    .setTableThingsLocations(TableLongThingsLocations.THINGS_LOCATIONS)
                    .init());
        }
    }

    private static synchronized void init(TableCollection<Long> tableCollection) {
        if (entityFactories == null) {
            entityFactories = new EntityFactories(ID_MANAGER, tableCollection);
            propertyResolver = new PropertyResolver<>(tableCollection, IdLong.PERSISTENCE_TYPE_INTEGER);
        }
    }

    @Override
    public PropertyResolver<Long> getPropertyResolver() {
        return propertyResolver;
    }

    @Override
    public String getLiquibaseChangelogFilename() {
        return LIQUIBASE_CHANGELOG_FILENAME;
    }

    @Override
    public EntityFactories<Long> getEntityFactories() {
        return entityFactories;
    }

    @Override
    public IdGenerationHandler createIdGenerationHanlder(Entity e) {
        return new IdGenerationHandlerLong(e);
    }

}
