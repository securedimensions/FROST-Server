package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables;

import de.fraunhofer.iosb.ilt.frostserver.model.EntityType;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.relations.RelationOneToMany;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public abstract class AbstractTableObservationRelations<J extends Comparable> extends StaTableAbstract<J> {

    private static final long serialVersionUID = -1104422281;

    /**
     * The column <code>public.OBSERVATIONRELATIONS.TYPE</code>.
     */
    public final TableField<Record, String> colType = createField(DSL.name("TYPE"), SQLDataType.CLOB, this, "");

    /**
     * Create a <code>public.OBSERVATIONRELATIONS</code> table reference
     */
    protected AbstractTableObservationRelations() {
        this(DSL.name("OBSERVATION_RELATIONS"), null);
    }

    protected AbstractTableObservationRelations(Name alias, AbstractTableObservationRelations<J> aliased) {
        this(alias, aliased, null);
    }

    protected AbstractTableObservationRelations(Name alias, AbstractTableObservationRelations<J> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    @Override
    public void initRelations() {
        final TableCollection<J> tables = getTables();
        registerRelation(
                new RelationOneToMany<>(this, tables.getTableObservations(), EntityType.OBSERVATION)
                        .setSourceFieldAccessor(AbstractTableObservationRelations::getObservationId)
                        .setTargetFieldAccessor(AbstractTableObservations::getId)
        );

        registerRelation(
                new RelationOneToMany<>(this, tables.getTableObservationGroups(), EntityType.OBSERVATIONGROUP)
                        .setSourceFieldAccessor(AbstractTableObservationRelations::getObservationGroupId)
                        .setTargetFieldAccessor(AbstractTableObservationGroups::getId)
        );

    }

    @Override
    public abstract TableField<Record, J> getId();

    public abstract TableField<Record, J> getObservationId();

    public abstract TableField<Record, J> getObservationGroupId();

    @Override
    public abstract AbstractTableObservationRelations<J> as(Name as);

    @Override
    public abstract AbstractTableObservationRelations<J> as(String alias);

}
