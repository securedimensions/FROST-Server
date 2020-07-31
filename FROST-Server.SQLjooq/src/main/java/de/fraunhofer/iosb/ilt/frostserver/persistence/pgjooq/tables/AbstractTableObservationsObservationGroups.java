package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

public abstract class AbstractTableObservationsObservationGroups<J extends Comparable> extends TableImpl<Record> implements StaTable<J> {

    private static final long serialVersionUID = -1022733888;

    /**
     * Create a <code>public.OBSERVATIONS_OBSERVATION_GROUPS</code> table reference
     */
    protected AbstractTableObservationsObservationGroups() {
        this(DSL.name("OBSERVATIONS_OBSERVATION_GROUPS"), null);
    }

    protected AbstractTableObservationsObservationGroups(Name alias, AbstractTableObservationsObservationGroups<J> aliased) {
        this(alias, aliased, null);
    }

    protected AbstractTableObservationsObservationGroups(Name alias, AbstractTableObservationsObservationGroups<J> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public abstract TableField<Record, J> getObservationId();

    public abstract TableField<Record, J> getObservationGroupId();

    @Override
    public abstract AbstractTableObservationsObservationGroups<J> as(Name as);

    @Override
    public abstract AbstractTableObservationsObservationGroups<J> as(String alias);

}
