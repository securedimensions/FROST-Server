package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableMultiDatastreamsLicenses;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableMultiDatastreamsObsProperties;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableLongMultiDatastreamsLicenses extends AbstractTableMultiDatastreamsLicenses<Long> {

    private static final long serialVersionUID = 344714892;

    /**
     * The reference instance of
     * <code>public.MULTI_DATASTREAMS_LICENSES</code>
     */
    public static final TableLongMultiDatastreamsLicenses MULTI_DATASTREAMS_LICENSES = new TableLongMultiDatastreamsLicenses();

    /**
     * The column
     * <code>public.MULTI_DATASTREAMS_LICENSES.MULTI_DATASTREAM_ID</code>.
     */
    public final TableField<Record, Long> colMultiDatastreamId = createField(DSL.name("MULTI_DATASTREAM_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column
     * <code>public.MULTI_DATASTREAMS_OBS_PROPERTIES.LICENSEID</code>.
     */
    public final TableField<Record, Long> colLicenseId = createField(DSL.name("LICENSE_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>public.MULTI_DATASTREAMS_LICENSES</code> table
     * reference
     */
    public TableLongMultiDatastreamsLicenses() {
        super();
    }

    /**
     * Create an aliased <code>public.MULTI_DATASTREAMS_LICENSES</code>
     * table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableLongMultiDatastreamsLicenses(Name alias) {
        this(alias, MULTI_DATASTREAMS_LICENSES);
    }

    private TableLongMultiDatastreamsLicenses(Name alias, TableLongMultiDatastreamsLicenses aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, Long> getMultiDatastreamId() {
        return colMultiDatastreamId;
    }

    @Override
    public TableField<Record, Long> getLicenseId() {
        return colLicenseId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongMultiDatastreamsLicenses as(String alias) {
        return new TableLongMultiDatastreamsLicenses(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongMultiDatastreamsLicenses as(Name alias) {
        return new TableLongMultiDatastreamsLicenses(alias, this);
    }

}
