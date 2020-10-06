package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.stringid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObservationRelations;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableStringObservationRelations extends AbstractTableObservationRelations<String> {

    private static final long serialVersionUID = -1104422281;

    /**
     * The reference instance of <code>public.OBSERVATIONRELATIONS</code>
     */
    public static final TableStringObservationRelations OBSERVATION_RELATIONS = new TableStringObservationRelations();

    /**
     * The column <code>public.OBSERVATIONRELATIONS.ID</code>.
     */
    public final TableField<Record, String> colId = createField(DSL.name("ID"), SQLDataType.VARCHAR.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>public.OBSERVATIONRELATIONS.OBSERVATION_ID</code>.
     */
    public final TableField<Record, String> colObservationId = createField(DSL.name("OBSERVATION_ID"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.OBSERVATIONRELATIONS.OBSERVATIONGROUP_ID</code>.
     */
    public final TableField<Record, String> colObservationGroupId = createField(DSL.name("OBSERVATION_GROUP_ID"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * Create a <code>public.OBSERVATIONS</code> table reference
     */
    public TableStringObservationRelations() {
        super();
    }

    /**
     * Create an aliased <code>public.OBSERVATIONS</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableStringObservationRelations(Name alias) {
        this(alias, OBSERVATION_RELATIONS);
    }

    private TableStringObservationRelations(Name alias, TableStringObservationRelations aliased) {
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

    @Override
    public TableField<Record, String> getObservationId() {
        return colObservationId;
    }

    @Override
    public TableField<Record, String> getObservationGroupId() {
        return colObservationGroupId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringObservationRelations as(String alias) {
        return new TableStringObservationRelations(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringObservationRelations as(Name alias) {
        return new TableStringObservationRelations(alias, this);
    }

}
