package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableParties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableSensors;
import java.util.UUID;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableUuidParties extends AbstractTableParties<UUID> {

    private static final long serialVersionUID = 1850108682;

    /**
     * The reference instance of <code>public.PARTIES</code>
     */
    public static final TableUuidParties PARTIES = new TableUuidParties();

    /**
     * The column <code>public.PARTIES.ID</code>.
     */
    public final TableField<Record, UUID> colId = createField(DSL.name("ID"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.UUID)), this, "");

    /**
     * Create a <code>public.PARTIES</code> table reference
     */
    public TableUuidParties() {
        super();
    }

    /**
     * Create an aliased <code>public.PARTIES</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableUuidParties(Name alias) {
        this(alias, PARTIES);
    }

    private TableUuidParties(Name alias, TableUuidParties aliased) {
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
    public TableUuidParties as(String alias) {
        return new TableUuidParties(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidParties as(Name alias) {
        return new TableUuidParties(alias, this);
    }

}
