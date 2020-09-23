package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableLicenses;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObsProperties;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableLongLicenses extends AbstractTableLicenses<Long> {

    private static final long serialVersionUID = -1873692390;

    /**
     * The reference instance of <code>public.LICENSES</code>
     */
    public static final TableLongLicenses LICENSES = new TableLongLicenses();

    /**
     * The column <code>public.LICENSES.ID</code>.
     */
    public final TableField<Record, Long> colId = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"LICENSES\"'::regclass)", SQLDataType.BIGINT)), this, "");

    /**
     * Create a <code>public.LICENSES</code> table reference
     */
    public TableLongLicenses() {
        super();
    }

    /**
     * Create an aliased <code>public.LICENSES</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableLongLicenses(Name alias) {
        this(alias, LICENSES);
    }

    private TableLongLicenses(Name alias, TableLongLicenses aliased) {
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
    public TableLongLicenses as(String alias) {
        return new TableLongLicenses(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongLicenses as(Name alias) {
        return new TableLongLicenses(alias, this);
    }

}
