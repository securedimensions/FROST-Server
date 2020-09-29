package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableDatastreams;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableLongDatastreams extends AbstractTableDatastreams<Long> {

    private static final long serialVersionUID = -1460005950;

    /**
     * The reference instance of <code>public.DATASTREAMS</code>
     */
    public static final TableLongDatastreams DATASTREAMS = new TableLongDatastreams();

    /**
     * The column <code>public.DATASTREAMS.ID</code>.
     */
    public final TableField<Record, Long> colId = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"DATASTREAMS_ID_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.DATASTREAMS.SENSOR_ID</code>.
     */
    public final TableField<Record, Long> colSensorId = createField(DSL.name("SENSOR_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.DATASTREAMS.PARTY_ID</code>.
     */
    public final TableField<Record, Long> colPartyId = createField(DSL.name("PARTY_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.DATASTREAMS.OBS_PROPERTY_ID</code>.
     */
    public final TableField<Record, Long> colObsPropertyId = createField(DSL.name("OBS_PROPERTY_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.DATASTREAMS.LICENSE_ID</code>.
     */
    public final TableField<Record, Long> colLicenseId = createField(DSL.name("LICENSE_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.DATASTREAMS.PROJECT_ID</code>.
     */
    public final TableField<Record, Long> colProjectId = createField(DSL.name("PROJECT_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.DATASTREAMS.THING_ID</code>.
     */
    public final TableField<Record, Long> colThingId = createField(DSL.name("THING_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>public.DATASTREAMS</code> table reference
     */
    public TableLongDatastreams() {
        super();
    }

    /**
     * Create an aliased <code>public.DATASTREAMS</code> table reference
     *
     * @param alias The name to use for the alias.
     */
    public TableLongDatastreams(Name alias) {
        this(alias, DATASTREAMS);
    }

    private TableLongDatastreams(Name alias, TableLongDatastreams aliased) {
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

    @Override
    public TableField<Record, Long> getObsPropertyId() {
        return colObsPropertyId;
    }

    @Override
    public TableField<Record, Long> getLicenseId() {
        return colLicenseId;
    }

    @Override
    public TableField<Record, Long> getSensorId() {
        return colSensorId;
    }

    @Override
    public TableField<Record, Long> getPartyId() {
        return colPartyId;
    }

    @Override
    public TableField<Record, Long> getProjectId() {
        return colProjectId;
    }

    @Override
    public TableField<Record, Long> getThingId() {
        return colThingId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongDatastreams as(String alias) {
        return new TableLongDatastreams(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongDatastreams as(Name alias) {
        return new TableLongDatastreams(alias, this);
    }

}
