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

import de.fraunhofer.iosb.ilt.frostserver.model.core.AbstractEntity;
import de.fraunhofer.iosb.ilt.frostserver.model.core.EntitySet;
import de.fraunhofer.iosb.ilt.frostserver.model.core.EntitySetImpl;
import de.fraunhofer.iosb.ilt.frostserver.model.core.Id;
import de.fraunhofer.iosb.ilt.frostserver.model.core.NamedEntity;
import de.fraunhofer.iosb.ilt.frostserver.model.ext.TimeInstant;
import de.fraunhofer.iosb.ilt.frostserver.model.ext.TimeInterval;
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
public class ObservationGroup extends NamedEntity<ObservationGroup> {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ObservationGroup.class);
    private TimeInstant created;
    private TimeInterval runtime;
    
    private EntitySet<Observation> observations;

    private boolean setCreated;
    private boolean setRuntime;
    
    public ObservationGroup() {
        this(null);
    }

    public ObservationGroup(Id id) {
        super(id);
        this.observations = new EntitySetImpl<>(EntityType.OBSERVATION);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.OBSERVATIONGROUP;
    }

    @Override
    public void complete(PathElementEntitySet containingSet) throws IncompleteEntityException {
        EntityType type = containingSet.getEntityType();
        if (type != getEntityType()) {
            throw new IllegalStateException("Set of type " + type + " can not contain a " + getEntityType());
        }
        if (getObservations().isEmpty()) {
            throw new IncompleteEntityException(getEntityType() + " must have at least one Observation.");
        }
        super.complete();
    }

    @Override
    public void setEntityPropertiesSet(boolean set, boolean entityPropertiesOnly) {
        super.setEntityPropertiesSet(set, entityPropertiesOnly);
        setSets(set, entityPropertiesOnly);
    }

    private void setSets(boolean set, boolean entityPropertiesOnly) {
        setCreated = set;
    }

    @Override
    public void setEntityPropertiesSet(ObservationGroup comparedTo, EntityChangedMessage message) {
        super.setEntityPropertiesSet(comparedTo, message);
        setSets(false, false);
        if (!Objects.equals(created, comparedTo.getCreated())) {
            setCreated = true;
            message.addEpField(EntityProperty.CREATED);
        }
        if (!Objects.equals(runtime, comparedTo.getRuntime())) {
            setRuntime = true;
            message.addEpField(EntityProperty.RUNTIME);
        }
        
    }

    public TimeInstant getCreated() {
        return created;
    }

    public ObservationGroup setCreated(TimeInstant created) {
        this.created = created;
        setCreated = created != null;
        return this;
    }

    public boolean isSetCreated() {
        return setCreated;
    }

    public TimeInterval getRuntime() {
        return runtime;
    }

    public ObservationGroup setRuntime(TimeInterval runtime) {
        this.runtime = runtime;
        setRuntime = runtime != null;
        return this;
    }

    public boolean isSetRuntime() {
        return setRuntime;
    }


    public EntitySet<Observation> getObservations() {
        return observations;
    }

    public ObservationGroup setObservations(EntitySet<Observation> observations) {
        this.observations = observations;
        return this;
    }

    @Override
    protected ObservationGroup getThis() {
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, observations);
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
        final ObservationGroup other = (ObservationGroup) obj;
        return super.equals(other)
                && Objects.equals(this.created, other.created)
                && Objects.equals(this.observations, other.observations);
    }

}
