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
package illarion.easygui.parsed.talk.conditions;

import java.io.IOException;
import java.io.Writer;

import javolution.context.ObjectFactory;

import illarion.easygui.data.CompareOperators;
import illarion.easygui.parsed.talk.TalkCondition;

/**
 * This class is used to store all required values for the number condition.
 * 
 * @author Martin Karing
 * @since 1.00
 * @version 1.02
 */
public final class ConditionNumber implements TalkCondition {
    /**
     * The factory class that creates and buffers ConditionNumber objects for
     * later reuse.
     * 
     * @author Martin Karing
     * @since 1.02
     * @version 1.02
     */
    private static final class ConditionNumberFactory extends
        ObjectFactory<ConditionNumber> {
        /**
         * Public constructor to the parent class is able to create a instance
         * properly.
         */
        public ConditionNumberFactory() {
            // nothing to do
        }

        /**
         * Create a new instance of this class.
         */
        @Override
        protected ConditionNumber create() {
            return new ConditionNumber();
        }
    }

    /**
     * The code needed for this condition in the easyGUI script.
     */
    @SuppressWarnings("nls")
    private static final String EASY_CODE = "%%NUMBER %1$s %2$s";

    /**
     * The factory used to create and reuse objects of this class.
     */
    private static final ConditionNumberFactory FACTORY =
        new ConditionNumberFactory();

    /**
     * The LUA code needed for this consequence to work.
     */
    @SuppressWarnings("nls")
    private static final String LUA_CODE =
        "talkEntry:addCondition(%1$s.number(\"%2$s\", %3$s));"
            + illarion.easygui.writer.LuaWriter.NL;

    /**
     * The LUA module required for this condition to work.
     */
    @SuppressWarnings("nls")
    private static final String LUA_MODULE = BASE_LUA_MODULE + "number";

    /**
     * The operator that is used to compare the said number.
     */
    private CompareOperators operator;
    /**
     * The value the said number is compared against.
     */
    private int value;

    /**
     * Constructor that limits the access to this class, in order to have only
     * the factory creating instances.
     */
    ConditionNumber() {
        // nothing to do
    }

    /**
     * Get a newly created or a old and reused instance of this class.
     * 
     * @return the instance of this class that is now ready to be used
     */
    public static ConditionNumber getInstance() {
        return FACTORY.object();
    }

    /**
     * Get the LUA module needed for this condition.
     */
    @Override
    public String getLuaModule() {
        return LUA_MODULE;
    }

    /**
     * Recycle the object so it can be used again later.
     */
    @Override
    public void recycle() {
        reset();
        FACTORY.recycle(this);
    }

    /**
     * Reset the state of this instance to its ready to be used later.
     */
    @Override
    public void reset() {
        operator = null;
    }

    /**
     * Set the data needed for this number condition.
     * 
     * @param op the operator used to compare the said number
     * @param newValue the value the said number is compared against
     */
    public void setData(final CompareOperators op, final int newValue) {
        operator = op;
        value = newValue;
    }

    /**
     * Write this said number condition to its easyGUI shape.
     */
    @Override
    public void writeEasyNpc(final Writer target) throws IOException {
        target.write(String.format(EASY_CODE, operator.getLuaComp(),
            Integer.toString(value)));
    }

    /**
     * Write the LUA code needed for this number condition.
     */
    @Override
    public void writeLua(final Writer target) throws IOException {
        target.write(String.format(LUA_CODE, LUA_MODULE,
            operator.getLuaComp(), Integer.toString(value)));
    }
}
