package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.stringid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationGroups;

import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableStringObservationGroups extends AbstractTableObservationGroups<String> {

    private static final long serialVersionUID = -1457801967;

    /**
     * The reference instance of <code>public.OBSERVATION_GROUPS</code>
     */
    public static final TableStringObservationGroups OBSERVATION_GROUPS = new TableStringObservationGroups();

    /**
     * The column <code>public.OBSERVATION_GROUPS.ID</code>.
     */
    public final TableField<Record, String> colId = createField(DSL.name("ID"), SQLDataType.VARCHAR.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.VARCHAR)), this, "");

    /**
     * Create a <code>public.OBSERVATION_GROUPS</code> table reference
     */
    public TableStringObservationGroups() {
        super();
    }

    /**
     * Create an aliased <code>public.OBSERVATION_GROUPS</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableStringObservationGroups(Name alias) {
        this(alias, OBSERVATION_GROUPS);
    }

    private TableStringObservationGroups(Name alias, TableStringObservationGroups aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, String> getId() {
        return colId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringObservationGroups as(String alias) {
        return new TableStringObservationGroups(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringObservationGroups as(Name alias) {
        return new TableStringObservationGroups(alias, this);
    }

}
