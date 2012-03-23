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
package illarion.easygui.parsed.talk.consequences;

import java.io.IOException;
import java.io.Writer;

import javolution.context.ObjectFactory;

import illarion.easygui.data.CalculationOperators;
import illarion.easygui.data.Towns;
import illarion.easygui.parsed.talk.AdvancedNumber;
import illarion.easygui.parsed.talk.TalkConsequence;

/**
 * This class is used to store all required values for the attribute
 * consequence.
 * 
 * @author Martin Karing
 * @since 1.00
 * @version 1.02
 */
public final class ConsequenceRankpoints implements TalkConsequence {
    /**
     * The factory class that creates and buffers ConsequenceRankpoints objects
     * for later reuse.
     * 
     * @author Martin Karing
     * @since 1.02
     * @version 1.02
     */
    private static final class ConsequenceRankpointsFactory extends
        ObjectFactory<ConsequenceRankpoints> {
        /**
         * Public constructor to the parent class is able to create a instance
         * properly.
         */
        public ConsequenceRankpointsFactory() {
            // nothing to do
        }

        /**
         * Create a new instance of this class.
         */
        @Override
        protected ConsequenceRankpoints create() {
            return new ConsequenceRankpoints();
        }
    }

    /**
     * The easyGUI code needed for this consequence.
     */
    @SuppressWarnings("nls")
    private static final String EASY_CODE = "rankpoints(%1$s) %2$s %3$s";

    /**
     * The factory used to create and reuse objects of this class.
     */
    private static final ConsequenceRankpointsFactory FACTORY =
        new ConsequenceRankpointsFactory();

    /**
     * The LUA code needed to be included for a quest status consequence.
     */
    @SuppressWarnings("nls")
    private static final String LUA_CODE =
        "talkEntry:addConsequence(%1$s.rankpoints(\"%2$s\", \"%3$s\", %4$s));"
            + illarion.easygui.writer.LuaWriter.NL;

    /**
     * The LUA module needed for this consequence to work.
     */
    @SuppressWarnings("nls")
    private static final String LUA_MODULE = BASE_LUA_MODULE + "rankpoints";

    /**
     * The operator used to alter the rankpoints.
     */
    private CalculationOperators operator;
    /**
     * The town thats rankpoints are altered.
     */
    private Towns town;

    /**
     * The value the rankpoints are altered with.
     */
    private AdvancedNumber value;

    /**
     * Constructor that limits the access to this class, in order to have only
     * the factory creating instances.
     */
    ConsequenceRankpoints() {
        // nothing to do
    }

    /**
     * Get a newly created or a old and reused instance of this class.
     * 
     * @return the instance of this class that is now ready to be used
     */
    public static ConsequenceRankpoints getInstance() {
        return FACTORY.object();
    }

    /**
     * Get the module that is needed for this consequence to work.
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
        town = null;
        if (value != null) {
            value.recycle();
            value = null;
        }
    }

    /**
     * Set the data required for this rankpoint consequence.
     * 
     * @param newTown the town thats rankpoints are altered
     * @param op the operator the rankpoints are altered by
     * @param newValue the value the rankpoints are altered with
     */
    public void setData(final Towns newTown, final CalculationOperators op,
        final AdvancedNumber newValue) {
        town = newTown;
        operator = op;
        value = newValue;
    }

    /**
     * Write this rankpoint consequence into its easyGUI shape.
     */
    @Override
    public void writeEasyNpc(final Writer target) throws IOException {
        target.write(String.format(EASY_CODE, town.name(),
            operator.getLuaOp(), value.getEasyNPC()));
    }

    /**
     * Write the LUA code of this consequence.
     */
    @Override
    public void writeLua(final Writer target) throws IOException {
        target.write(String.format(LUA_CODE, LUA_MODULE, town.name(),
            operator.getLuaOp(), value.getLua()));
    }
}
