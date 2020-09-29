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
import de.fraunhofer.iosb.ilt.frostserver.model.core.NamedDsHoldingEntity;
import de.fraunhofer.iosb.ilt.frostserver.model.ext.TimeInterval;
import de.fraunhofer.iosb.ilt.frostserver.property.EntityProperty;
import java.util.Objects;

/**
 *
 * @author jab, scf
 */
public class Project extends NamedDsHoldingEntity<Project> {

    private String url;
    private TimeInterval runtime;

    private boolean setUrl;
    private boolean setRuntime;

    public Project() {
    }

    public Project(Id id) {
        super(id);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.PROJECT;
    }

    @Override
    public void setEntityPropertiesSet(boolean set, boolean entityPropertiesOnly) {
        super.setEntityPropertiesSet(set, entityPropertiesOnly);
        setUrl = set;
        setRuntime = set;
    }

    @Override
    public void setEntityPropertiesSet(Project comparedTo, EntityChangedMessage message) {
        super.setEntityPropertiesSet(comparedTo, message);
        if (!Objects.equals(url, comparedTo.getUrl())) {
            setUrl = true;
            message.addEpField(EntityProperty.URL);
        } else {
            setUrl = false;
        }
        if (!Objects.equals(runtime, comparedTo.getRuntime())) {
            setRuntime = true;
            message.addEpField(EntityProperty.RUNTIME);
        } else {
            setRuntime = false;
        }
    }

    public String getUrl() {
        return url;
    }

    public Project setUrl(String url) {
        this.url = url;
        setUrl = url != null;
        return this;
    }

    public boolean isSetUrl() {
        return setUrl;
    }

    public TimeInterval getRuntime() {
        return runtime;
    }

    public Project setRuntime(TimeInterval runtime) {
        this.runtime = runtime;
        setRuntime = runtime != null;
        return this;
    }

    public boolean isSetRuntime() {
        return setRuntime;
    }

    @Override
    protected Project getThis() {
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), url, runtime);
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
        final Project other = (Project) obj;
        return super.equals(other)
                && Objects.equals(url, other.url)
                && Objects.equals(runtime, other.runtime);
    }

}
