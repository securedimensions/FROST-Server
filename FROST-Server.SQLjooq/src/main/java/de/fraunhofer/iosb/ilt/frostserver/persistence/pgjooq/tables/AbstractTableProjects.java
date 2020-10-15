package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables;

import de.fraunhofer.iosb.ilt.frostserver.model.EntityType;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.relations.RelationManyToManyOrdered;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.relations.RelationOneToMany;

import java.time.OffsetDateTime;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public abstract class AbstractTableProjects<J extends Comparable> extends StaTableAbstract<J> {

    private static final long serialVersionUID = -1873692390;

    /**
     * The column <code>public.PROJECTS.NAME</code>.
     */
    public final TableField<Record, String> colName = createField(DSL.name("NAME"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.PROJECTS.DEFINITION</code>.
     */
    public final TableField<Record, String> colDefinition = createField(DSL.name("DEFINITION"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.PROJECTS.DESCRIPTION</code>.
     */
    public final TableField<Record, String> colDescription = createField(DSL.name("DESCRIPTION"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.PROJECTS.LOGO</code>.
     */
    public final TableField<Record, String> colUrl = createField(DSL.name("URL"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.PROJECTS.CLASSIFICATION</code>.
     */
    public final TableField<Record, String> colClassification = createField(DSL.name("CLASSIFICATION"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.PROJECTS.TERMSOFUSE</code>.
     */
    public final TableField<Record, String> colTermsOfUse = createField(DSL.name("TERMSOFUSE"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.PROJECTS.PRIVACYPOLICY</code>.
     */
    public final TableField<Record, String> colPrivacyPolicy = createField(DSL.name("PRIVACYPOLICY"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.PROJECTS.CREATED</code>.
     */
    public final TableField<Record, OffsetDateTime> colCreated = createField(DSL.name("CREATED"), SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

    /**
     * The column <code>public.PROJECTS.RUNTIME_START</code>.
     */
    public final TableField<Record, OffsetDateTime> colRuntimeStart = createField(DSL.name("RUNTIME_START"), SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

    /**
     * The column <code>public.PROJECTS.RUNTIME_END</code>.
     */
    public final TableField<Record, OffsetDateTime> colRuntimeEnd = createField(DSL.name("RUNTIME_END"), SQLDataType.TIMESTAMPWITHTIMEZONE, this, "");

    /**
     * The column <code>public.PROJECTS.PROPERTIES</code>.
     */
    public final TableField<Record, String> colProperties = createField(DSL.name("PROPERTIES"), SQLDataType.CLOB, this, "");

    /**
     * Create a <code>public.PROJECTS</code> table reference
     */
    protected AbstractTableProjects() {
        this(DSL.name("PROJECTS"), null);
    }

    protected AbstractTableProjects(Name alias, AbstractTableProjects<J> aliased) {
        this(alias, aliased, null);
    }

    protected AbstractTableProjects(Name alias, AbstractTableProjects<J> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    @Override
    public void initRelations() {
        final TableCollection<J> tables = getTables();
        registerRelation(
                new RelationOneToMany<>(this, tables.getTableDatastreams(), EntityType.DATASTREAM, true)
                        .setSourceFieldAccessor(AbstractTableProjects::getId)
                        .setTargetFieldAccessor(AbstractTableDatastreams::getProjectId)
        );

        registerRelation(
                new RelationOneToMany<>(this, tables.getTableMultiDatastreams(), EntityType.MULTIDATASTREAM, true)
                        .setSourceFieldAccessor(AbstractTableProjects::getId)
                        .setTargetFieldAccessor(AbstractTableMultiDatastreams::getProjectId)
        );
    }

    @Override
    public abstract TableField<Record, J> getId();

    @Override
    public abstract AbstractTableProjects<J> as(Name as);

    @Override
    public abstract AbstractTableProjects<J> as(String alias);

}
