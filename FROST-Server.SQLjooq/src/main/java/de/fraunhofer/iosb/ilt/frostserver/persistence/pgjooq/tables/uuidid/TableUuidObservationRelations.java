package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationRelations;
import java.util.UUID;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableUuidObservationRelations extends AbstractTableObservationRelations<UUID> {

    private static final long serialVersionUID = -1104422281;

    /**
     * The reference instance of <code>public.OBSERVATIONRELATIONS</code>
     */
    public static final TableUuidObservationRelations OBSERVATION_RELATIONS = new TableUuidObservationRelations();

    /**
     * The column <code>public.OBSERVATIONRELATIONS.ID</code>.
     */
    public final TableField<Record, UUID> colId = createField(DSL.name("ID"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.OBSERVATIONRELATIONS.DATASTREAM_ID</code>.
     */
    public final TableField<Record, UUID> colObservationId = createField(DSL.name("OBSERVATION_ID"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.OBSERVATIONRELATIONS.FEATURE_ID</code>.
     */
    public final TableField<Record, UUID> colObservationGroupId = createField(DSL.name("OBSERVATION_GROUP_ID"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * Create a <code>public.OBSERVATIONS</code> table reference
     */
    public TableUuidObservationRelations() {
        super();
    }

    /**
     * Create an aliased <code>public.OBSERVATIONS</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableUuidObservationRelations(Name alias) {
        this(alias, OBSERVATION_RELATIONS);
    }

    private TableUuidObservationRelations(Name alias, TableUuidObservationRelations aliased) {
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
    public TableField<Record, UUID> getObservationId() {
        return colObservationId;
    }

    @Override
    public TableField<Record, UUID> getObservationGroupId() {
        return colObservationGroupId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidObservationRelations as(String alias) {
        return new TableUuidObservationRelations(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidObservationRelations as(Name alias) {
        return new TableUuidObservationRelations(alias, this);
    }

}
