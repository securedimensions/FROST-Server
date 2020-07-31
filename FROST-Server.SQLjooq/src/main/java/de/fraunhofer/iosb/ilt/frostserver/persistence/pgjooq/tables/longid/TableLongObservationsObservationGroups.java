package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationsObservationGroups;

import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableLongObservationsObservationGroups extends AbstractTableObservationsObservationGroups<Long> {

    private static final long serialVersionUID = -1022733888;

    /**
     * The reference instance of <code>public.OBSERVATIONS_OBSERVATION_GROUPS</code>
     */
    public static final TableLongObservationsObservationGroups OBSERVATIONS_OBSERVATION_GROUPS = new TableLongObservationsObservationGroups();

    /**
     * The column <code>public.OBSERVATIONS_OBSERVATION_GROUPS.OBSERVATION_ID</code>.
     */
    public final TableField<Record, Long> colObservationId = createField(DSL.name("OBSERVATION_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.OBSERVATIONS_OBSERVATION_GROUPS.OBSERVATION_GROUP_ID</code>.
     */
    public final TableField<Record, Long> colObservationGroupId = createField(DSL.name("OBSERVATION_GROUP_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>public.OBSERVATIONS_OBSERVATION_GROUPS</code> table reference
     */
    public TableLongObservationsObservationGroups() {
        super();
    }

    /**
     * Create an aliased <code>public.OBSERVATIONS_OBSERVATION_GROUPS</code> table
     * reference
     *
     * @param alias The alias to use in queries.
     */
    public TableLongObservationsObservationGroups(Name alias) {
        this(alias, OBSERVATIONS_OBSERVATION_GROUPS);
    }

    private TableLongObservationsObservationGroups(Name alias, TableLongObservationsObservationGroups aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, Long> getObservationId() {
        return colObservationId;
    }

    @Override
    public TableField<Record, Long> getObservationGroupId() {
        return colObservationGroupId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongObservationsObservationGroups as(String alias) {
        return new TableLongObservationsObservationGroups(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongObservationsObservationGroups as(Name alias) {
        return new TableLongObservationsObservationGroups(alias, this);
    }

}
