package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationsObservationGroups;

import java.util.UUID;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableUuidObservationsObservationGroups extends AbstractTableObservationsObservationGroups<UUID> {

    private static final long serialVersionUID = -1022733888;

    /**
     * The reference instance of <code>public.OBSERVATIONS_OBSERVATION_GROUPS</code>
     */
    public static final TableUuidObservationsObservationGroups OBSERVATIONS_OBSERVATION_GROUPS = new TableUuidObservationsObservationGroups();

    /**
     * The column <code>public.OBSERVATIONS_OBSERVATION_GROUPS.OBSERVATION_ID</code>.
     */
    public final TableField<Record, UUID> colObservationId = createField(DSL.name("OBSERVATION_ID"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.OBSERVATIONS_OBSERVATION_GROUPS.OBSERVATION_GROUP_ID</code>.
     */
    public final TableField<Record, UUID> colObservationGroupId = createField(DSL.name("OBSERVATION_GROUP_ID"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * Create a <code>public.OBSERVATIONS_OBSERVATION_GROUPS</code> table reference
     */
    public TableUuidObservationsObservationGroups() {
        super();
    }

    /**
     * Create an aliased <code>public.OBSERVATIONS_OBSERVATION_GROUPS</code> table
     * reference
     *
     * @param alias The alias to use in queries.
     */
    public TableUuidObservationsObservationGroups(Name alias) {
        this(alias, OBSERVATIONS_OBSERVATION_GROUPS);
    }

    private TableUuidObservationsObservationGroups(Name alias, TableUuidObservationsObservationGroups aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, UUID> getObservationId() {
        return colObservationId;
    }

    @Override
    public TableField<Record, UUID> getObservationGroupId() {
        return colObservationGroupId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidObservationsObservationGroups as(String alias) {
        return new TableUuidObservationsObservationGroups(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidObservationsObservationGroups as(Name alias) {
        return new TableUuidObservationsObservationGroups(alias, this);
    }

}
