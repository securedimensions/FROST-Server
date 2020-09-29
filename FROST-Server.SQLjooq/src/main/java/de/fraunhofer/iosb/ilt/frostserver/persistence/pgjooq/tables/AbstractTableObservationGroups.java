package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables;

import de.fraunhofer.iosb.ilt.frostserver.model.EntityType;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.relations.RelationManyToMany;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.relations.RelationOneToMany;
import java.time.OffsetDateTime;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public abstract class AbstractTableObservationGroups<J extends Comparable> extends StaTableAbstract<J> {

    private static final long serialVersionUID = -1457801967;

    /**
     * The column <code>public.OBSERVATION_GROUP.NAME</code>.
     */
    public final TableField<Record, String> colName = createField(DSL.name("NAME"), SQLDataType.CLOB.defaultValue(DSL.field("'no name'::text", SQLDataType.CLOB)), this, "");

    /**
     * The column <code>public.OBSERVATION_GROUP.DESCRIPTION</code>.
     */
    public final TableField<Record, String> colDescription = createField(DSL.name("DESCRIPTION"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.OBSERVATION_GROUP.CREATED</code>.
     */
    public final TableField<Record, OffsetDateTime> colCreated = createField(DSL.name("CREATED"), SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

    /**
     * The column <code>public.OBSERVATION_GROUP.RUNTIME_START</code>.
     */
    public final TableField<Record, OffsetDateTime> colRuntimeStart = createField(DSL.name("RUNTIME_START"), SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

    /**
     * The column <code>public.OBSERVATION_GROUP.RUNTIME_END</code>.
     */
    public final TableField<Record, OffsetDateTime> colRuntimeEnd = createField(DSL.name("RUNTIME_END"), SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

    /**
     * Create a <code>public.OBSERVATION_GROUPS</code> table reference
     */
    protected AbstractTableObservationGroups() {
        this(DSL.name("OBSERVATION_GROUPS"), null);
    }

    protected AbstractTableObservationGroups(Name alias, AbstractTableObservationGroups<J> aliased) {
        this(alias, aliased, null);
    }

    protected AbstractTableObservationGroups(Name alias, AbstractTableObservationGroups<J> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    @Override
    public void initRelations() {
        final TableCollection<J> tables = getTables();
        registerRelation(
                new RelationManyToMany<>(this, tables.getTableObservationsObservationGroups(), tables.getTableObservations(), EntityType.OBSERVATION)
                        .setSourceFieldAcc(AbstractTableObservationGroups::getId)
                        .setSourceLinkFieldAcc(AbstractTableObservationsObservationGroups::getObservationGroupId)
                        .setTargetLinkFieldAcc(AbstractTableObservationsObservationGroups::getObservationId)
                        .setTargetFieldAcc(AbstractTableObservations::getId)
        );
    }

    @Override
    public abstract TableField<Record, J> getId();

    @Override
    public abstract AbstractTableObservationGroups<J> as(Name as);

    @Override
    public abstract AbstractTableObservationGroups<J> as(String alias);

}
