package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.uuidid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableLicenses;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObsProperties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableProjects;

import java.util.UUID;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableUuidProjects extends AbstractTableProjects<UUID> {

    private static final long serialVersionUID = -1873692390;

    /**
     * The reference instance of <code>public.PROJECTS</code>
     */
    public static final TableUuidProjects PROJECTS = new TableUuidProjects();

    /**
     * The column <code>public.PROJECTS.ID</code>.
     */
    public final TableField<Record, UUID> colId = createField(DSL.name("ID"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.UUID)), this, "");

    /**
     * Create a <code>public.PROJECTS</code> table reference
     */
    public TableUuidProjects() {
        super();
    }

    /**
     * Create an aliased <code>public.PROJECTS</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableUuidProjects(Name alias) {
        this(alias, PROJECTS);
    }

    private TableUuidProjects(Name alias, TableUuidProjects aliased) {
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
    public TableUuidProjects as(String alias) {
        return new TableUuidProjects(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableUuidProjects as(Name alias) {
        return new TableUuidProjects(alias, this);
    }

}
