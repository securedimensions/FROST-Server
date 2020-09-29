package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.stringid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableLicenses;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableObsProperties;
import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableProjects;

import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableStringProjects extends AbstractTableProjects<String> {

    private static final long serialVersionUID = -1873692390;

    /**
     * The reference instance of <code>public.PROJECTS</code>
     */
    public static final TableStringProjects PROJECTS = new TableStringProjects();

    /**
     * The column <code>public.PROJECTS.ID</code>.
     */
    public final TableField<Record, String> colId = createField(DSL.name("ID"), SQLDataType.VARCHAR.nullable(false).defaultValue(DSL.field("uuid_generate_v1mc()", SQLDataType.VARCHAR)), this, "");

    /**
     * Create a <code>public.PROJECTS</code> table reference
     */
    public TableStringProjects() {
        super();
    }

    /**
     * Create an aliased <code>public.PROJECTS</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableStringProjects(Name alias) {
        this(alias, PROJECTS);
    }

    private TableStringProjects(Name alias, TableStringProjects aliased) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringProjects as(String alias) {
        return new TableStringProjects(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableStringProjects as(Name alias) {
        return new TableStringProjects(alias, this);
    }

}
