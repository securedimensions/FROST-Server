/*
 * Copyright (C) 2016 Fraunhofer Institut IOSB, Fraunhoferstr. 1, D 76131
 * Karlsruhe, Germany.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.fraunhofer.iosb.ilt.frostserver.parser.query;

/**
 * A Comparison.
 */
public class ASTFunction extends SimpleNode {

    private String name;

    /**
     * Constructor.
     *
     * @param id the id
     */
    public ASTFunction(int id) {
        super(id);
    }

    /**
     * Set the name.
     *
     * @param n the name
     */
    public void setName(String n) {
        name = n;
        if (name.endsWith("(")) {
            name = name.substring(0, name.length() - 1);
        }
    }

    @Override
    public String toString() {
        return "Function: " + name;
    }

    @Override
    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public String getName() {
        return name;
    }

}
