package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.stringid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableLicenses;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObsProperties;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableStringLicenses extends AbstractTableLicenses<String> {

    private static final long serialVersionUID = -1873692390;

    /**
     * The reference instance of <code>public.LICENSES</code>
     */
    public static final TableStringLicenses LICENSES = new TableStringLicenses();

    /**
     * The column <code>public.LICENSES.ID</code>.
     */
    public final TableField<Record, String> colId = createField(DSL.name("ID"), SQLDataType.VARCHAR.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.VARCHAR)), this, "");

    /**
     * Create a <code>public.OBS_PROPERTIES</code> table reference
     */
    public TableStringLicenses() {
        super();
    }

    /**
     * Create an aliased <code>public.OBS_PROPERTIES</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableStringLicenses(Name alias) {
        this(alias, LICENSES);
    }

    private TableStringLicenses(Name alias, TableStringLicenses aliased) {
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
    public TableStringLicenses as(String alias) {
        return new TableStringLicenses(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringLicenses as(Name alias) {
        return new TableStringLicenses(alias, this);
    }

}
