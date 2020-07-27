package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.stringid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableParties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableSensors;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableStringParties extends AbstractTableParties<String> {

    private static final long serialVersionUID = 1850108682;

    /**
     * The reference instance of <code>public.PARTIES</code>
     */
    public static final TableStringParties PARTIES = new TableStringParties();

    /**
     * The column <code>public.PARTIES.ID</code>.
     */
    public final TableField<Record, String> colId = createField(DSL.name("ID"), SQLDataType.VARCHAR.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.VARCHAR)), this, "");

    /**
     * Create a <code>public.PARTIES</code> table reference
     */
    public TableStringParties() {
        super();
    }

    /**
     * Create an aliased <code>public.PARTIES</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableStringParties(Name alias) {
        this(alias, PARTIES);
    }

    private TableStringParties(Name alias, TableStringParties aliased) {
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
    public TableStringParties as(String alias) {
        return new TableStringParties(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringParties as(Name alias) {
        return new TableStringParties(alias, this);
    }

}
