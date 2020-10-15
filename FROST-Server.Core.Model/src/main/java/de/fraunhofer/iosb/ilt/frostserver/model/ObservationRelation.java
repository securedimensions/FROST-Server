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
package de.fraunhofer.iosb.ilt.frostserver.model;

import de.fraunhofer.iosb.ilt.frostserver.model.core.Id;
import de.fraunhofer.iosb.ilt.frostserver.model.core.NamedEntity;
import de.fraunhofer.iosb.ilt.frostserver.path.PathElement;
import de.fraunhofer.iosb.ilt.frostserver.path.PathElementEntity;
import de.fraunhofer.iosb.ilt.frostserver.path.PathElementEntitySet;
import de.fraunhofer.iosb.ilt.frostserver.property.EntityProperty;
import de.fraunhofer.iosb.ilt.frostserver.property.NavigationPropertyMain;
import de.fraunhofer.iosb.ilt.frostserver.util.exception.IncompleteEntityException;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jab, scf
 */
public class ObservationRelation extends NamedEntity<ObservationRelation> {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ObservationRelation.class);
    private String type;
    private Observation observation;
    private ObservationGroup observationGroup;
    
    private boolean setType;
    private boolean setObservation;
    private boolean setObservationGroup;
    
    public ObservationRelation() {
        this(null);
    }

    public ObservationRelation(Id id) {
        super(id);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.OBSERVATIONRELATION;
    }

    @Override
    public void complete(PathElementEntitySet containingSet) throws IncompleteEntityException {
        PathElement parent = containingSet.getParent();
        if (parent instanceof PathElementEntity) {
            PathElementEntity parentEntity = (PathElementEntity) parent;
            Id parentId = parentEntity.getId();
            if (parentId != null) {
                switch (parentEntity.getEntityType()) {
                case OBSERVATION:
                    setObservation(new Observation(parentId));
                    LOGGER.debug("Set observationId to {}.", parentId);
                    break;

                case OBSERVATIONGROUP:
                    setObservationGroup(new ObservationGroup(parentId));
                    LOGGER.debug("Set observationGroupId to {}.", parentId);
                    break;

                    default:
                        LOGGER.error("Incorrect 'parent' entity type for {}: {}", getEntityType(), parentEntity.getEntityType());
                        break;
                }
            }
        }

        super.complete(containingSet);
    }

    @Override
    public void complete(boolean entityPropertiesOnly) throws IncompleteEntityException {
        if (!entityPropertiesOnly) {
           if (observation == null || observationGroup == null) {
                throw new IllegalStateException("ObservationRelation must have both an Observation and an ObservationGroup.");
            }
        }

        super.complete(entityPropertiesOnly);
    }

    @Override
    public void setEntityPropertiesSet(boolean set, boolean entityPropertiesOnly) {
        super.setEntityPropertiesSet(set, entityPropertiesOnly);
        setSets(set, entityPropertiesOnly);
    }

    private void setSets(boolean set, boolean entityPropertiesOnly) {
        setType = set;
        if (!entityPropertiesOnly) {
            setObservation = set;
            setObservationGroup = set;
        }
    }

    @Override
    public void setEntityPropertiesSet(ObservationRelation comparedTo, EntityChangedMessage message) {
        super.setEntityPropertiesSet(comparedTo, message);
        setSets(false, false);
        if (!Objects.equals(observation, comparedTo.getObservation())) {
            setObservation = true;
            message.addNpField(NavigationPropertyMain.OBSERVATION);
        }
        if (!Objects.equals(observationGroup, comparedTo.getObservationGroup())) {
            setObservationGroup = true;
            message.addNpField(NavigationPropertyMain.OBSERVATIONGROUP);
        }
        if (!Objects.equals(type, comparedTo.getType())) {
            setType = true;
            message.addEpField(EntityProperty.TYPE);
        }
    }

    public String getType() {
        return type;
    }

    public ObservationRelation setType(String type) {
        this.type = type;
        setType = type != null;
        return this;
    }

    public boolean isSetType() {
        return setType;
    }


    public Observation getObservation() {
        return observation;
    }

    public ObservationRelation setObservation(Observation observation) {
        this.observation = observation;
        setObservation = observation != null;
        return this;
    }

    public boolean isSetObservation() {
        return setObservation;
    }

    
    public ObservationGroup getObservationGroup() {
        return observationGroup;
    }

    public ObservationRelation setObservationGroup(ObservationGroup observationGroup) {
        this.observationGroup = observationGroup;
        setObservationGroup = observationGroup != null;
        return this;
    }

    public boolean isSetObservationGroup() {
        return setObservationGroup;
    }


    @Override
    protected ObservationRelation getThis() {
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                type,
                observation,
                observationGroup
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObservationRelation other = (ObservationRelation) obj;
        return super.equals(other)
                && Objects.equals(type, other.type)
                && Objects.equals(observation, other.observation)
                && Objects.equals(observationGroup, other.observationGroup);
    }

}
