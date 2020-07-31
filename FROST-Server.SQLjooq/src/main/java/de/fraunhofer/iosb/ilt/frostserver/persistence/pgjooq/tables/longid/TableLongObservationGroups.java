package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationGroups;

import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableLongObservationGroups extends AbstractTableObservationGroups<Long> {

    private static final long serialVersionUID = -1457801967;

    /**
     * The reference instance of <code>public.OBSERVATION_GROUPS</code>
     */
    public static final TableLongObservationGroups OBSERVATION_GROUPS = new TableLongObservationGroups();

    /**
     * The column <code>public.OBSERVATION_GROUPS.ID</code>.
     */
    public final TableField<Record, Long> colId = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"OBSERVATION_GROUPS_ID_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");

    /**
     * Create a <code>public.OBSERVATION_GROUPS</code> table reference
     */
    public TableLongObservationGroups() {
        super();
    }

    /**
     * Create an aliased <code>public.OBSERVATION_GROUPS</code> table reference
     *
     * @param alias The name to use for the alias.
     */
    public TableLongObservationGroups(Name alias) {
        this(alias, OBSERVATION_GROUPS);
    }

    private TableLongObservationGroups(Name alias, TableLongObservationGroups aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, Long> getId() {
        return colId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongObservationGroups as(String alias) {
        return new TableLongObservationGroups(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongObservationGroups as(Name alias) {
        return new TableLongObservationGroups(alias, this);
    }

}
