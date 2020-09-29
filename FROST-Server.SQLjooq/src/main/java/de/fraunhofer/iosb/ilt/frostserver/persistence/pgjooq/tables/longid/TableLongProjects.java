package de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.longid;

import de.fraunhofer.iosb.ilt.frostserver.persistence.pgjooq.tables.AbstractTableProjects;

import org.jooq.Name;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class TableLongProjects extends AbstractTableProjects<Long> {

    private static final long serialVersionUID = -1873692390;

    /**
     * The reference instance of <code>public.PROJECTS</code>
     */
    public static final TableLongProjects PROJECTS = new TableLongProjects();

    /**
     * The column <code>public.PROJECTS.ID</code>.
     */
    public final TableField<Record, Long> colId = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"PROJECTS_ID_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");

    /**
     * Create a <code>public.PROJECTS</code> table reference
     */
    public TableLongProjects() {
        super();
    }

    /**
     * Create an aliased <code>public.PROJECTS</code> table reference
     *
     * @param alias The alias to use in queries.
     */
    public TableLongProjects(Name alias) {
        this(alias, PROJECTS);
    }

    private TableLongProjects(Name alias, TableLongProjects aliased) {
        super(alias, aliased);
    }

    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    @Override
    public TableField<Record, Long> getId() {
        return colId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongProjects as(String alias) {
        return new TableLongProjects(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableLongProjects as(Name alias) {
        return new TableLongProjects(alias, this);
    }

}
