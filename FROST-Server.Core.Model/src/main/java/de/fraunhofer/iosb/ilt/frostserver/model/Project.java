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
import de.fraunhofer.iosb.ilt.frostserver.model.ext.TimeInstant;
import de.fraunhofer.iosb.ilt.frostserver.model.ext.TimeInterval;
import de.fraunhofer.iosb.ilt.frostserver.property.EntityProperty;
import java.util.Objects;

/**
 *
 * @author jab, scf
 */
public class Project extends NamedDsHoldingEntity<Project> {

	private String url;
	private String classification;
	private String termsOfUse;
	private String privacyPolicy;
	private TimeInstant created;
	private TimeInterval runtime;

    private boolean setUrl;
    private boolean setClassification;
    private boolean setTermsOfUse;
    private boolean setPrivacyPolicy;
    private boolean setCreated;
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
        setClassification = set;
        setTermsOfUse = set;
        setPrivacyPolicy = set;
        setCreated = set;
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
        if (!Objects.equals(classification, comparedTo.getClassification())) {
            setClassification = true;
            message.addEpField(EntityProperty.CLASSIFICATION);
        } else {
        	setClassification = false;
        }
        if (!Objects.equals(termsOfUse, comparedTo.getTermsOfUse())) {
            setTermsOfUse = true;
            message.addEpField(EntityProperty.TERMSOFUSE);
        } else {
        	setTermsOfUse = false;
        }
        if (!Objects.equals(privacyPolicy, comparedTo.getPrivacyPolicy())) {
            setPrivacyPolicy = true;
            message.addEpField(EntityProperty.PRIVACYPOLICY);
        } else {
            setPrivacyPolicy = false;
        }
        if (!Objects.equals(created, comparedTo.getCreated())) {
            setCreated = true;
            message.addEpField(EntityProperty.CREATED);
        } else {
            setCreated = false;
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

    
    public String getClassification() {
        return classification;
    }

    public Project setClassification(String classification) {
        this.classification = classification;
        setClassification = classification != null;
        return this;
    }

    public boolean isSetClassification() {
        return setClassification;
    }


    public String getPrivacyPolicy() {
        return privacyPolicy;
    }

    public Project setPrivacyPolicy(String privacyPolicy) {
        this.privacyPolicy = privacyPolicy;
        setPrivacyPolicy = privacyPolicy != null;
        return this;
    }

    public boolean isSetPrivacyPolicy() {
        return setPrivacyPolicy;
    }


    public String getTermsOfUse() {
        return termsOfUse;
    }

    public Project setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
        setTermsOfUse = termsOfUse != null;
        return this;
    }

    public boolean isSetTermsOfUse() {
        return setTermsOfUse;
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

    
    public TimeInstant getCreated() {
        return created;
    }

    public Project setCreated(TimeInstant created) {
        this.created = created;
        setCreated = created != null;
        return this;
    }

    public boolean isSetCreated() {
        return setCreated;
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
        		&& Objects.equals(classification, other.classification)
        		&& Objects.equals(termsOfUse, other.termsOfUse)
        		&& Objects.equals(privacyPolicy, other.privacyPolicy)
        		&& Objects.equals(created, other.created)
                && Objects.equals(runtime, other.runtime);
    }

}
