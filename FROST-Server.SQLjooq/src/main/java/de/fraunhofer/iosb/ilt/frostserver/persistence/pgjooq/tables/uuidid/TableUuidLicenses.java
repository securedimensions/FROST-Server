package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableLicenses;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObsProperties;
import java.util.UUID;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableUuidLicenses extends AbstractTableLicenses<UUID> {

    private static final long serialVersionUID = -1873692390;

    /**
     * The reference instance of <code>public.LICENSES</code>
     */
    public static final TableUuidLicenses LICENSES = new TableUuidLicenses();

    /**
     * The column <code>public.LICENSES.ID</code>.
     */
    public final TableField<Record, UUID> colId = createField(DSL.name("ID"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.UUID)), this, "");

    /**
     * Create a <code>public.LICENSES</code> table reference
     */
    public TableUuidLicenses() {
        super();
    }

    /**
     * Create an aliased <code>public.LICENSES</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableUuidLicenses(Name alias) {
        this(alias, LICENSES);
    }

    private TableUuidLicenses(Name alias, TableUuidLicenses aliased) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidLicenses as(String alias) {
        return new TableUuidLicenses(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidLicenses as(Name alias) {
        return new TableUuidLicenses(alias, this);
    }

}
