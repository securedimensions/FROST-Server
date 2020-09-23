package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables;

import de.fraunhofer.iosb.ilt.frostserver.model.EntityType;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.relations.RelationManyToManyOrdered;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.relations.RelationOneToMany;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public abstract class AbstractTableLicenses<J extends Comparable> extends StaTableAbstract<J> {

    private static final long serialVersionUID = -1873692390;

    /**
     * The column <code>public.LICENSES.NAME</code>.
     */
    public final TableField<Record, String> colName = createField(DSL.name("NAME"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.LICENSES.DEFINITION</code>.
     */
    public final TableField<Record, String> colDefinition = createField(DSL.name("DEFINITION"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.LICENSES.DESCRIPTION</code>.
     */
    public final TableField<Record, String> colDescription = createField(DSL.name("DESCRIPTION"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.LICENSES.PROPERTIES</code>.
     */
    public final TableField<Record, String> colProperties = createField(DSL.name("PROPERTIES"), SQLDataType.CLOB, this, "");

    /**
     * Create a <code>public.LICENSES</code> table reference
     */
    protected AbstractTableLicenses() {
        this(DSL.name("LICENSES"), null);
    }

    protected AbstractTableLicenses(Name alias, AbstractTableLicenses<J> aliased) {
        this(alias, aliased, null);
    }

    protected AbstractTableLicenses(Name alias, AbstractTableLicenses<J> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    @Override
    public void initRelations() {
        final TableCollection<J> tables = getTables();
        registerRelation(
                new RelationOneToMany<>(this, tables.getTableDatastreams(), EntityType.DATASTREAM, true)
                        .setSourceFieldAccessor(AbstractTableLicenses::getId)
                        .setTargetFieldAccessor(AbstractTableDatastreams::getLicenseId)
        );

        registerRelation(
                new RelationManyToManyOrdered<J, AbstractTableLicenses<J>, AbstractTableMultiDatastreamsLicenses<J>, Integer, AbstractTableMultiDatastreams<J>>(
                        this, tables.getTableMultiDatastreamsLicenses(), tables.getTableMultiDatastreams(),
                        EntityType.MULTIDATASTREAM)
                        .setSourceFieldAcc(AbstractTableLicenses::getId)
                        .setSourceLinkFieldAcc(AbstractTableMultiDatastreamsLicenses::getLicenseId)
                        .setTargetLinkFieldAcc(AbstractTableMultiDatastreamsLicenses::getMultiDatastreamId)
                        .setTargetFieldAcc(AbstractTableMultiDatastreams::getId)
                        .setOrderFieldAcc((AbstractTableMultiDatastreamsLicenses<J> table) -> table.colRank)
        );
    }

    @Override
    public abstract TableField<Record, J> getId();

    @Override
    public abstract AbstractTableLicenses<J> as(Name as);

    @Override
    public abstract AbstractTableLicenses<J> as(String alias);

}
