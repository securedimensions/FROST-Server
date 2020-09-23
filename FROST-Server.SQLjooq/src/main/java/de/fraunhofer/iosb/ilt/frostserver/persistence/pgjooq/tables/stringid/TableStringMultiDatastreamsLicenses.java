package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.stringid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableMultiDatastreamsLicenses;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableMultiDatastreamsObsProperties;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableStringMultiDatastreamsLicenses extends AbstractTableMultiDatastreamsLicenses<String> {

    private static final long serialVersionUID = 344714892;

    /**
     * The reference instance of
     * <code>public.MULTI_DATASTREAMS_LICENSES</code>
     */
    public static final TableStringMultiDatastreamsLicenses MULTI_DATASTREAMS_LICENSES = new TableStringMultiDatastreamsLicenses();

    /**
     * The column
     * <code>public.MULTI_DATASTREAMS_LICENSES.MULTI_DATASTREAM_ID</code>.
     */
    public final TableField<Record, String> colMultiDatastreamId = createField(DSL.name("MULTI_DATASTREAM_ID"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column
     * <code>public.MULTI_DATASTREAMS_LICENSES.LICENSE_ID</code>.
     */
    public final TableField<Record, String> colLicenseId = createField(DSL.name("LICENSE_ID"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * Create a <code>public.MULTI_DATASTREAMS_LICENSES</code> table
     * reference
     */
    public TableStringMultiDatastreamsLicenses() {
        super();
    }

    /**
     * Create an aliased <code>public.MULTI_DATASTREAMS_LICENSES</code>
     * table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableStringMultiDatastreamsLicenses(Name alias) {
        this(alias, MULTI_DATASTREAMS_LICENSES);
    }

    private TableStringMultiDatastreamsLicenses(Name alias, TableStringMultiDatastreamsLicenses aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, String> getMultiDatastreamId() {
        return colMultiDatastreamId;
    }

    @Override
    public TableField<Record, String> getLicenseId() {
        return colLicenseId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringMultiDatastreamsLicenses as(String alias) {
        return new TableStringMultiDatastreamsLicenses(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringMultiDatastreamsLicenses as(Name alias) {
        return new TableStringMultiDatastreamsLicenses(alias, this);
    }

}
