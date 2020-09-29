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
import de.fraunhofer.iosb.ilt.frostserver.property.EntityProperty;
import java.util.Objects;

/**
 *
 * @author jab, scf
 */
public class License extends NamedDsHoldingEntity<License> {

    private String definition;
    private String logo;

    private boolean setDefinition;
    private boolean setLogo;

    public License() {
    }

    public License(Id id) {
        super(id);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.LICENSE;
    }

    @Override
    public void setEntityPropertiesSet(boolean set, boolean entityPropertiesOnly) {
        super.setEntityPropertiesSet(set, entityPropertiesOnly);
        setDefinition = set;
        setLogo = set;
    }

    @Override
    public void setEntityPropertiesSet(License comparedTo, EntityChangedMessage message) {
        super.setEntityPropertiesSet(comparedTo, message);
        if (!Objects.equals(definition, comparedTo.getDefinition())) {
            setDefinition = true;
            message.addEpField(EntityProperty.DEFINITION);
        } else {
            setDefinition = false;
        }
        if (!Objects.equals(logo, comparedTo.getLogo())) {
            setLogo = true;
            message.addEpField(EntityProperty.LOGO);
        } else {
            setLogo = false;
        }
    }

    public String getDefinition() {
        return definition;
    }

    public License setDefinition(String definition) {
        this.definition = definition;
        setDefinition = definition != null;
        return this;
    }

    public boolean isSetDefinition() {
        return setDefinition;
    }

    public String getLogo() {
        return logo;
    }

    public License setLogo(String logo) {
        this.logo = logo;
        setLogo = logo != null;
        return this;
    }

    public boolean isSetLogo() {
        return setLogo;
    }

    @Override
    protected License getThis() {
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), definition, logo);
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
        final License other = (License) obj;
        return super.equals(other)
                && Objects.equals(definition, other.definition)
                && Objects.equals(logo, other.logo);
    }

}
