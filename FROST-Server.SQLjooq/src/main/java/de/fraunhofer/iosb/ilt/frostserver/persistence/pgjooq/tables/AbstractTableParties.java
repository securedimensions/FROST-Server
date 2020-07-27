package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables;

import de.fraunhofer.iosb.ilt.frostserver.model.EntityType;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.relations.RelationOneToMany;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public abstract class AbstractTableParties<J extends Comparable> extends StaTableAbstract<J> {

    private static final long serialVersionUID = 1850108682;

    /**
     * The column <code>public.PARTIES.DESCRIPTION</code>.
     */
    public final TableField<Record, String> colDescription = createField(DSL.name("DESCRIPTION"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.PARTIES.ENCODING_TYPE</code>.
     */
    public final TableField<Record, String> colEncodingType = createField(DSL.name("ENCODING_TYPE"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.PARTIES.METADATA</code>.
     */
    public final TableField<Record, String> colMetadata = createField(DSL.name("METADATA"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.PARTIES.NAME</code>.
     */
    public final TableField<Record, String> colName = createField(DSL.name("NAME"), SQLDataType.CLOB.defaultValue(DSL.field("'no name'::text", SQLDataType.CLOB)), this, "");

    /**
     * The column <code>public.PARTIES.PROPERTIES</code>.
     */
    public final TableField<Record, String> colProperties = createField(DSL.name("PROPERTIES"), SQLDataType.CLOB, this, "");

    /**
     * Create a <code>public.PARTIES</code> table reference
     */
    protected AbstractTableParties() {
        this(DSL.name("PARTIES"), null);
    }

    protected AbstractTableParties(Name alias, AbstractTableParties<J> aliased) {
        this(alias, aliased, null);
    }

    protected AbstractTableParties(Name alias, AbstractTableParties<J> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    @Override
    public void initRelations() {
        final TableCollection<J> tables = getTables();
        registerRelation(
                new RelationOneToMany<>(this, tables.getTableDatastreams(), EntityType.DATASTREAM, true)
                        .setSourceFieldAccessor(AbstractTableParties::getId)
                        .setTargetFieldAccessor(AbstractTableDatastreams::getPartyId)
        );

        registerRelation(
                new RelationOneToMany<>(this, tables.getTableMultiDatastreams(), EntityType.MULTIDATASTREAM, true)
                        .setSourceFieldAccessor(AbstractTableParties::getId)
                        .setTargetFieldAccessor(AbstractTableMultiDatastreams::getPartyId)
        );
    }

    @Override
    public abstract TableField<Record, J> getId();

    @Override
    public abstract AbstractTableParties<J> as(Name as);

    @Override
    public abstract AbstractTableParties<J> as(String alias);

}
