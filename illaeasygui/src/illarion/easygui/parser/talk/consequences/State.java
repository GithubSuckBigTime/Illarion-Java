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
package illarion.easygui.parser.talk.consequences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import illarion.easygui.Lang;
import illarion.easygui.data.CalculationOperators;
import illarion.easygui.parsed.talk.AdvancedNumber;
import illarion.easygui.parsed.talk.TalkConsequence;
import illarion.easygui.parsed.talk.consequences.ConsequenceState;
import illarion.easygui.parser.talk.AdvNumber;
import illarion.easygui.parser.talk.ConsequenceParser;

/**
 * This is the state consequence. Its able to parse a state consequence out of
 * the consequence collection string.
 * 
 * @author Martin Karing
 * @since 1.00
 * @version 1.02
 */
public final class State extends ConsequenceParser {
    /**
     * A empty string used for some replace operations.
     */
    @SuppressWarnings("nls")
    private static final String EMPTY_STRING = "".intern();

    /**
     * This pattern is used to find the state in the condition and to remove
     * them properly.
     */
    @SuppressWarnings("nls")
    private static final Pattern STRING_FIND = Pattern.compile(
        "\\s*state\\s*([=+\\-]{1,2})\\s*" + AdvNumber.ADV_NUMBER_REGEXP
            + "\\s*,\\s*", Pattern.CASE_INSENSITIVE);

    /**
     * Extract a condition from the working string.
     */
    @Override
    @SuppressWarnings("nls")
    public TalkConsequence extract() {
        if (getNewLine() == null) {
            throw new IllegalStateException("Can't extract if no state set.");
        }

        final Matcher stringMatcher = STRING_FIND.matcher(getNewLine());
        if (stringMatcher.find()) {
            final String operation = stringMatcher.group(1);
            final AdvancedNumber targetValue =
                AdvNumber.getNumber(stringMatcher.group(2));

            setLine(stringMatcher.replaceFirst(EMPTY_STRING));

            if (targetValue == null) {
                reportError(String.format(Lang.getMsg(getClass(), "number"),
                    stringMatcher.group(2), stringMatcher.group(0)));
                return extract();
            }

            CalculationOperators operator = null;
            for (final CalculationOperators op : CalculationOperators.values()) {
                if (op.getRegexpPattern().matcher(operation).matches()) {
                    operator = op;
                    break;
                }
            }

            if (operator == null) {
                reportError(String.format(Lang.getMsg(getClass(), "operator"),
                    operation, stringMatcher.group(0)));
                return extract();
            }

            final ConsequenceState stateCons = ConsequenceState.getInstance();
            stateCons.setData(operator, targetValue);
            return stateCons;
        }

        return null;
    }

    @Override
    public String getDescription() {
        return Lang.getMsg(getClass(), "Docu.description"); //$NON-NLS-1$
    }

    @Override
    public String getExample() {
        return Lang.getMsg(getClass(), "Docu.example"); //$NON-NLS-1$
    }

    @Override
    public String getSyntax() {
        return Lang.getMsg(getClass(), "Docu.syntax"); //$NON-NLS-1$
    }

    @Override
    public String getTitle() {
        return Lang.getMsg(getClass(), "Docu.title"); //$NON-NLS-1$
    }
}
