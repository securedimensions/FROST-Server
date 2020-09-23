package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableMultiDatastreamsLicenses;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableMultiDatastreamsObsProperties;
import java.util.UUID;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableUuidMultiDatastreamsLicenses extends AbstractTableMultiDatastreamsLicenses<UUID> {

    private static final long serialVersionUID = 344714892;

    /**
     * The reference instance of
     * <code>public.MULTI_DATASTREAMS_LICENSES</code>
     */
    public static final TableUuidMultiDatastreamsLicenses MULTI_DATASTREAMS_LICENSES = new TableUuidMultiDatastreamsLicenses();

    /**
     * The column
     * <code>public.MULTI_DATASTREAMS_LICENSES.MULTI_DATASTREAM_ID</code>.
     */
    public final TableField<Record, UUID> colMultiDatastreamId = createField(DSL.name("MULTI_DATASTREAM_ID"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column
     * <code>public.MULTI_DATASTREAMS_LICENSES.LICENSE_ID</code>.
     */
    public final TableField<Record, UUID> colLicenseId = createField(DSL.name("LICENSE_ID"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * Create a <code>public.MULTI_DATASTREAMS_LICENSES</code> table
     * reference
     */
    public TableUuidMultiDatastreamsLicenses() {
        super();
    }

    /**
     * Create an aliased <code>public.MULTI_DATASTREAMS_LICENSES</code>
     * table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableUuidMultiDatastreamsLicenses(Name alias) {
        this(alias, MULTI_DATASTREAMS_LICENSES);
    }

    private TableUuidMultiDatastreamsLicenses(Name alias, TableUuidMultiDatastreamsLicenses aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, UUID> getMultiDatastreamId() {
        return colMultiDatastreamId;
    }

    @Override
    public TableField<Record, UUID> getLicenseId() {
        return colLicenseId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidMultiDatastreamsLicenses as(String alias) {
        return new TableUuidMultiDatastreamsLicenses(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidMultiDatastreamsLicenses as(Name alias) {
        return new TableUuidMultiDatastreamsLicenses(alias, this);
    }

}
