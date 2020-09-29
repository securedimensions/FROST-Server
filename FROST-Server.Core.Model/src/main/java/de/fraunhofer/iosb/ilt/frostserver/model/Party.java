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
public class Party extends NamedDsHoldingEntity<Party> {

    private String nickName;
    private String role;
    private String authId;

    private boolean setNickName;
    private boolean setRole;
    private boolean setAuthId;

    public Party() {
        this(null);
    }

    public Party(Id id) {
        super(id);
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.PARTY;
    }

    @Override
    public void setEntityPropertiesSet(boolean set, boolean entityPropertiesOnly) {
        super.setEntityPropertiesSet(set, entityPropertiesOnly);
        setSets(set);
    }

    private void setSets(boolean set) {
        setRole = set;
        setNickName = set;
        setAuthId = set;
    }

    @Override
    public void setEntityPropertiesSet(Party comparedTo, EntityChangedMessage message) {
        super.setEntityPropertiesSet(comparedTo, message);
        setSets(false);
        if (!Objects.equals(nickName, comparedTo.getNickName())) {
            setNickName = true;
            message.addEpField(EntityProperty.NICKNAME);
        }
        if (!Objects.equals(role, comparedTo.getRole())) {
            setRole = true;
            message.addEpField(EntityProperty.ROLE);
        }
        if (!Objects.equals(authId, comparedTo.getAuthId())) {
            setAuthId = true;
            message.addEpField(EntityProperty.AUTHID);
        }
    }

    public String getNickName() {
        return nickName;
    }

    public Party setNickName(String nickName) {
        this.nickName = nickName;
        setNickName = true;
        return this;
    }

    public boolean isSetNickName() {
        return setNickName;
    }

    public String getRole() {
        return role;
    }

    public Party setRole(String role) {
        this.role = role;
        setRole = true;
        return this;
    }

    public boolean isSetRole() {
        return setRole;
    }

    public String getAuthId() {
        return authId;
    }

    public Party setAuthId(String authId) {
        this.authId = authId;
        setAuthId = true;
        return this;
    }

    public boolean isSetAuthId() {
        return setAuthId;
    }


    @Override
    protected Party getThis() {
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nickName, role, authId);
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
        final Party other = (Party) obj;
        return super.equals(other)
                && Objects.equals(nickName, other.nickName)
                && Objects.equals(role, other.role)
                && Objects.equals(authId, other.authId);
    }

}
