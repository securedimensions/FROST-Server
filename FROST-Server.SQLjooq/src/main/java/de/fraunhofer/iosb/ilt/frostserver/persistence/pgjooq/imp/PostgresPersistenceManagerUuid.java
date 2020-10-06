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
import de.fraunhofer.iosb.ilt.frostserver.persistence.IdManager;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.IdGenerationHandler;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.PostgresPersistenceManager;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.PropertyResolver;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.factories.EntityFactories;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.TableCollection;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongHistLocations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid.TableLongProjects;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidActuators;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidDatastreams;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidFeatures;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidHistLocations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidLicenses;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidLocations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidLocationsHistLocations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidMultiDatastreams;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidMultiDatastreamsObsProperties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidObsProperties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidObservationRelations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidObservations;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidObservationsObservationGroups;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidSensors;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidParties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidProjects;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidTaskingCapabilities;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidTasks;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidThings;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid.TableUuidThingsLocations;
import de.fraunhofer.iosb.ilt.frostserver.settings.CoreSettings;
import java.util.UUID;

/**
 *
 * @author jab
 * @author scf
 */
public class PostgresPersistenceManagerUuid extends PostgresPersistenceManager<UUID> {

    private static final String LIQUIBASE_CHANGELOG_FILENAME = "liquibase/tablesUuid.xml";

    private static final IdManagerUuid ID_MANAGER = new IdManagerUuid();
    private static EntityFactories<UUID> entityFactories;
    private static PropertyResolver<UUID> propertyResolver;

    @Override
    public IdManager getIdManager() {
        return ID_MANAGER;
    }

    @Override
    public void init(CoreSettings settings) {
        super.init(settings);
        IdGenerationHandler.setIdGenerationMode(settings.getPersistenceSettings().getIdGenerationMode());
        if (entityFactories == null) {
            init(new TableCollection<UUID>()
                    .setTableActuators(TableUuidActuators.ACTUATORS)
                    .setTableDatastreams(TableUuidDatastreams.DATASTREAMS)
                    .setTableFeatures(TableUuidFeatures.FEATURES)
                    .setTableHistLocations(TableUuidHistLocations.HIST_LOCATIONS)
                    .setTableHistLocations(TableUuidHistLocations.HIST_LOCATIONS)
                    .setTableLocations(TableUuidLocations.LOCATIONS)
                    .setTableLocationsHistLocations(TableUuidLocationsHistLocations.LOCATIONS_HIST_LOCATIONS)
                    .setTableObservationsObservationGroups(TableUuidObservationsObservationGroups.OBSERVATIONS_OBSERVATION_GROUPS)
                    .setTableMultiDatastreams(TableUuidMultiDatastreams.MULTI_DATASTREAMS)
                    .setTableMultiDatastreamsObsProperties(TableUuidMultiDatastreamsObsProperties.MULTI_DATASTREAMS_OBS_PROPERTIES)
                    .setTableObservations(TableUuidObservations.OBSERVATIONS)
                    .setTableObservationRelations(TableUuidObservationRelations.OBSERVATION_RELATIONS)
                    .setTableObsProperties(TableUuidObsProperties.OBS_PROPERTIES)
                    .setTableLicenses(TableUuidLicenses.LICENSES)
                    .setTableParties(TableUuidParties.PARTIES)
                    .setTableProjects(TableUuidProjects.PROJECTS)
                    .setTableSensors(TableUuidSensors.SENSORS)
                    .setTableTasks(TableUuidTasks.TASKS)
                    .setTableTaskingCapabilities(TableUuidTaskingCapabilities.TASKINGCAPABILITIES)
                    .setTableThings(TableUuidThings.THINGS)
                    .setTableThingsLocations(TableUuidThingsLocations.THINGS_LOCATIONS)
                    .init());
        }
    }

    private static synchronized void init(TableCollection<UUID> tableCollection) {
        if (entityFactories == null) {
            entityFactories = new EntityFactories(ID_MANAGER, tableCollection);
            propertyResolver = new PropertyResolver<>(tableCollection, UuidId.PERSISTENCE_TYPE_BYTEARRAY);
        }
    }

    @Override
    public PropertyResolver<UUID> getPropertyResolver() {
        return propertyResolver;
    }

    @Override
    public String getLiquibaseChangelogFilename() {
        return LIQUIBASE_CHANGELOG_FILENAME;
    }

    @Override
    public EntityFactories<UUID> getEntityFactories() {
        return entityFactories;
    }

    @Override
    public IdGenerationHandler createIdGenerationHanlder(Entity e) {
        return new IdGenerationHandlerUuid(e);
    }

}
