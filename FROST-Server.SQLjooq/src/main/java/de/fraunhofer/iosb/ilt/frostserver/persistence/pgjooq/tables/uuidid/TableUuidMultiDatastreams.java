package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableMultiDatastreams;
import java.util.UUID;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableUuidMultiDatastreams extends AbstractTableMultiDatastreams<UUID> {

    private static final long serialVersionUID = 560943996;

    /**
     * The reference instance of <code>public.MULTI_DATASTREAMS</code>
     */
    public static final TableUuidMultiDatastreams MULTI_DATASTREAMS = new TableUuidMultiDatastreams();

    /**
     * The column <code>public.MULTI_DATASTREAMS.ID</code>.
     */
    public final TableField<Record, UUID> colId = createField(DSL.name("ID"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.MULTI_DATASTREAMS.SENSOR_ID</code>.
     */
    public final TableField<Record, UUID> colSensorId = createField(DSL.name("SENSOR_ID"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.MULTI_DATASTREAMS.PARTY_ID</code>.
     */
    public final TableField<Record, UUID> colPartyId = createField(DSL.name("PARTY_ID"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.MULTI_DATASTREAMS.THING_ID</code>.
     */
    public final TableField<Record, UUID> colThingId = createField(DSL.name("THING_ID"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.MULTI_DATASTREAMS.PROJECT_ID</code>.
     */
    public final TableField<Record, UUID> colProjectId = createField(DSL.name("PROJECT_ID"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * Create a <code>public.MULTI_DATASTREAMS</code> table reference
     */
    public TableUuidMultiDatastreams() {
        super();
    }

    /**
     * Create an aliased <code>public.MULTI_DATASTREAMS</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableUuidMultiDatastreams(Name alias) {
        this(alias, MULTI_DATASTREAMS);
    }

    private TableUuidMultiDatastreams(Name alias, TableUuidMultiDatastreams aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, UUID> getId() {
        return colId;
    }

    @Override
    public TableField<Record, UUID> getSensorId() {
        return colSensorId;
    }

    @Override
    public TableField<Record, UUID> getPartyId() {
        return colPartyId;
    }

    @Override
    public TableField<Record, UUID> getThingId() {
        return colThingId;
    }

    @Override
    public TableField<Record, UUID> getProjectId() {
        return colProjectId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidMultiDatastreams as(String alias) {
        return new TableUuidMultiDatastreams(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidMultiDatastreams as(Name alias) {
        return new TableUuidMultiDatastreams(alias, this);
    }

}
