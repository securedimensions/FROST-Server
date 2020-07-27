package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableParties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableSensors;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableLongParties extends AbstractTableParties<Long> {

    private static final long serialVersionUID = 4711;

    /**
     * The reference instance of <code>public.PARTIES</code>
     */
    public static final TableLongParties PARTIES = new TableLongParties();

    /**
     * The column <code>public.PARTIES.ID</code>.
     */
    public final TableField<Record, Long> colId = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"PARTIES_ID_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");

    /**
     * Create a <code>public.PARTIES</code> table reference
     */
    public TableLongParties() {
        super();
    }

    /**
     * Create an aliased <code>public.PARTIES</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableLongParties(Name alias) {
        this(alias, PARTIES);
    }

    private TableLongParties(Name alias, TableLongParties aliased) {
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
    public TableLongParties as(String alias) {
        return new TableLongParties(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongParties as(Name alias) {
        return new TableLongParties(alias, this);
    }

}
