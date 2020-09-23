package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables;

import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public abstract class AbstractTableMultiDatastreamsLicenses<J extends Comparable> extends TableImpl<Record> implements StaTable<J> {

    private static final long serialVersionUID = 344714892;

    /**
     * The column <code>public.MULTI_DATASTREAMS_LICENSES.RANK</code>.
     */
    public final TableField<Record, Integer> colRank = createField(DSL.name("RANK"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>public.MULTI_DATASTREAMS_LICENSES</code> table
     * reference
     */
    protected AbstractTableMultiDatastreamsLicenses() {
        this(DSL.name("MULTI_DATASTREAMS_LICENSES"), null);
    }

    protected AbstractTableMultiDatastreamsLicenses(Name alias, AbstractTableMultiDatastreamsLicenses<J> aliased) {
        this(alias, aliased, null);
    }

    protected AbstractTableMultiDatastreamsLicenses(Name alias, AbstractTableMultiDatastreamsLicenses<J> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public abstract TableField<Record, J> getMultiDatastreamId();

    public abstract TableField<Record, J> getLicenseId();

    @Override
    public abstract AbstractTableMultiDatastreamsLicenses<J> as(Name as);

    @Override
    public abstract AbstractTableMultiDatastreamsLicenses<J> as(String alias);

}
