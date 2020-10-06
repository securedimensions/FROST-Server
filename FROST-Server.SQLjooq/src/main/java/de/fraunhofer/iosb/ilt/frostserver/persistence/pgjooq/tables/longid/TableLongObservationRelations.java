package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationRelations;

import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableLongObservationRelations extends AbstractTableObservationRelations<Long> {

    private static final long serialVersionUID = -1873692390;

    /**
     * The reference instance of <code>public.OBSERVATIONRELATIONS</code>
     */
    public static final TableLongObservationRelations OBSERVATION_RELATIONS = new TableLongObservationRelations();

    /**
     * The column <code>public.OBSERVATIONRELATIONS.ID</code>.
     */
    public final TableField<Record, Long> colId = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"OBSERVATION_RELATIONS\"'::regclass)", SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.OBSERVATIONRELATIONS.OBSERVATION_ID</code>.
     */
    public final TableField<Record, Long> colObservationId = createField(DSL.name("OBSERVATION_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.OBSERVATIONRELATIONS.OBSERVATIONGROUP_ID</code>.
     */
    public final TableField<Record, Long> colObservationGroupId = createField(DSL.name("OBSERVATION_GROUP_ID"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>public.OBSERVATIONRELATIONS</code> table reference
     */
    public TableLongObservationRelations() {
        super();
    }

    /**
     * Create an aliased <code>public.OBSERVATIONRELATIONS</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableLongObservationRelations(Name alias) {
        this(alias, OBSERVATION_RELATIONS);
    }

    private TableLongObservationRelations(Name alias, TableLongObservationRelations aliased) {
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
    public TableField<Record, Long> getObservationId() {
        return colObservationId;
    }

    @Override
    public TableField<Record, Long> getObservationGroupId() {
        return colObservationGroupId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongObservationRelations as(String alias) {
        return new TableLongObservationRelations(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongObservationRelations as(Name alias) {
        return new TableLongObservationRelations(alias, this);
    }

}
