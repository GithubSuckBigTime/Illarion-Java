/*
 * This file is part of the Illarion easyGUI Editor.
 *
 * Copyright © 2011 - Illarion e.V.
 *
 * The Illarion easyGUI Editor is free software: you can redistribute i and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * The Illarion easyGUI Editor is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * the Illarion easyGUI Editor. If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.easygui.parsed;

import java.io.IOException;
import java.io.Writer;

import javolution.lang.Immutable;

import illarion.easygui.writer.EasyNpcWriter;
import illarion.easygui.writer.LuaWriter;
import illarion.easygui.writer.SQLBuilder;

/**
 * This class is used to store a empty line in the parsed NPC. This is needed to
 * ensure to keep the formatting of the NPC script.
 * 
 * @author Martin Karing
 * @since 1.00
 * @version 1.02
 */
public final class ParsedEmptyLine implements ParsedData, Immutable {
    /**
     * The singleton instance of this class. Since this class does not store any
     * data, the same instance can ge used every time.
     */
    private static final ParsedEmptyLine INSTANCE = new ParsedEmptyLine();

    /**
     * Private constructor to avoid any other instances being created.
     */
    private ParsedEmptyLine() {
        // nothing to do
    }

    /**
     * Get the instance of this class.
     * 
     * @return the instance of this class that can be used
     */
    public static ParsedEmptyLine getInstance() {
        return INSTANCE;
    }

    /**
     * Empty lines do not effect the query.
     */
    @Override
    public void buildSQL(final SQLBuilder builder) {
        // nothing to add to the query.
    }

    /**
     * Check the stages effected by this empty lines.
     */
    @Override
    public boolean effectsEasyNpcStage(final EasyNpcWriter.WritingStage stage) {
        return false;
    }

    /**
     * Get the stages effected by the free lines.
     * 
     * @return <code>false</code> always because no stages are effected
     */
    @Override
    public boolean effectsLuaWritingStage(final LuaWriter.WritingStage stage) {
        return false;
    }

    /**
     * Get the modules needed by the empty lines.
     * 
     * @return <code>null</code> always
     */
    @Override
    public String[] getRequiredModules() {
        return null;
    }

    /**
     * This class is not recycled. Calling this function does nothing.
     */
    @Override
    public void recycle() {
        // nothing to do
    }

    /**
     * This class does not store any data. So nothing is reset.
     */
    @Override
    public void reset() {
        // nothing to do
    }

    /**
     * Empty lines follow a strict predefined pattern. They are removed entirely
     * from the easyGUI script and so never written.
     */
    @Override
    public void writeEasyNpc(final Writer target,
        final EasyNpcWriter.WritingStage stage) throws IOException {
        // empty lines are never written to the easyGUI script
    }

    /**
     * Write the LUA code for those empty lines. Empty lines are discarded by
     * default.
     */
    @Override
    public void writeLua(final Writer target,
        final LuaWriter.WritingStage stage) throws IOException {
        // nothing to do
    }

}
