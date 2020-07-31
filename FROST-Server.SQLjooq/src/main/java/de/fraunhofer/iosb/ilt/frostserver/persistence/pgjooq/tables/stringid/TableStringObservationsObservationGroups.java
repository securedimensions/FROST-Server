package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.stringid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationsObservationGroups;

import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableStringObservationsObservationGroups extends AbstractTableObservationsObservationGroups<String> {

    private static final long serialVersionUID = -1022733888;

    /**
     * The reference instance of <code>public.OBSERVATIONS_OBSERVATION_GROUPS</code>
     */
    public static final TableStringObservationsObservationGroups OBSERVATIONS_OBSERVATION_GROUPS = new TableStringObservationsObservationGroups();

    /**
     * The column <code>public.OBSERVATIONS_OBSERVATION_GROUPS.OBSERVATION_ID</code>.
     */
    public final TableField<Record, String> colObservationId = createField(DSL.name("OBSERVATION_ID"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.OBSERVATIONS_OBSERVATION_GROUPS.OBSERVATION_GROUP_ID</code>.
     */
    public final TableField<Record, String> colObservationGroupId = createField(DSL.name("OBSERVATION_GROUP_ID"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * Create a <code>public.OBSERVATIONS_OBSERVATION_GROUPS</code> table reference
     */
    public TableStringObservationsObservationGroups() {
        super();
    }

    /**
     * Create an aliased <code>public.OBSERVATIONS_OBSERVATION_GROUPS</code> table
     * reference
     *
     * @param alias The alias to use in queries.
     */
    public TableStringObservationsObservationGroups(Name alias) {
        this(alias, OBSERVATIONS_OBSERVATION_GROUPS);
    }

    private TableStringObservationsObservationGroups(Name alias, TableStringObservationsObservationGroups aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, String> getObservationId() {
        return colObservationId;
    }

    @Override
    public TableField<Record, String> getObservationGroupId() {
        return colObservationGroupId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringObservationsObservationGroups as(String alias) {
        return new TableStringObservationsObservationGroups(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringObservationsObservationGroups as(Name alias) {
        return new TableStringObservationsObservationGroups(alias, this);
    }

}
