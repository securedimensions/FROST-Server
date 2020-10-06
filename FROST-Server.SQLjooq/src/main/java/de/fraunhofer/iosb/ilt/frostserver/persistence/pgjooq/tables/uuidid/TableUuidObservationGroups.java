package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationGroups;
import java.util.UUID;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableUuidObservationGroups extends AbstractTableObservationGroups<UUID> {

    private static final long serialVersionUID = -1104422281;

    /**
     * The reference instance of <code>public.OBSERVATIONGROUPS</code>
     */
    public static final TableUuidObservationGroups OBSERVATION_GROUPS = new TableUuidObservationGroups();

    /**
     * The column <code>public.OBSERVATIONGROUPS.ID</code>.
     */
    public final TableField<Record, UUID> colId = createField(DSL.name("ID"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.OBSERVATIONGROUPS.OBSERVATIONRELATION_ID</code>.
     */
    public final TableField<Record, UUID> colObservationRelationId = createField(DSL.name("OBSERVATION_RELATION_ID"), SQLDataType.UUID, this, "");

    /**
     * Create a <code>public.OBSERVATIONGROUPS</code> table reference
     */
    public TableUuidObservationGroups() {
        super();
    }

    /**
     * Create an aliased <code>public.OBSERVATIONGROUPS</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableUuidObservationGroups(Name alias) {
        this(alias, OBSERVATION_GROUPS);
    }

    private TableUuidObservationGroups(Name alias, TableUuidObservationGroups aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, UUID> getId() {
        return colId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidObservationGroups as(String alias) {
        return new TableUuidObservationGroups(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidObservationGroups as(Name alias) {
        return new TableUuidObservationGroups(alias, this);
    }

}
