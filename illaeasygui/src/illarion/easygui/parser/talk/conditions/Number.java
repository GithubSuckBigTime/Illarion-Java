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
package illarion.easygui.parser.talk.conditions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import illarion.easygui.Lang;
import illarion.easygui.data.CompareOperators;
import illarion.easygui.parsed.talk.TalkCondition;
import illarion.easygui.parsed.talk.conditions.ConditionNumber;
import illarion.easygui.parser.talk.ConditionParser;

/**
 * This is a number condition. Its able to parse a number value out of the NPC
 * condition line.
 * 
 * @author Martin Karing
 * @since 1.00
 * @version 1.02
 */
public final class Number extends ConditionParser {
    /**
     * A empty string used for some replace operations.
     */
    @SuppressWarnings("nls")
    private static final String EMPTY_STRING = "".intern();

    /**
     * This pattern is used to find the number operation in the condition
     * properly.
     */
    @SuppressWarnings("nls")
    private static final Pattern NUMBER_FIND = Pattern.compile(
        "\\s*%NUMBER\\s*([=~!<>]{1,2})\\s*(\\d+)\\s*,\\s*",
        Pattern.CASE_INSENSITIVE);

    /**
     * Extract a condition from the working string.
     */
    @Override
    @SuppressWarnings("nls")
    public TalkCondition extract() {
        if (getNewLine() == null) {
            throw new IllegalStateException("Can't extract if no state set.");
        }

        final Matcher stringMatcher = NUMBER_FIND.matcher(getNewLine());
        if (stringMatcher.find()) {
            final String comperator = stringMatcher.group(1);
            final int targetValue = Integer.parseInt(stringMatcher.group(2));

            setLine(stringMatcher.replaceFirst(EMPTY_STRING));

            CompareOperators operator = null;
            for (final CompareOperators op : CompareOperators.values()) {
                if (op.getRegexpPattern().matcher(comperator).matches()) {
                    operator = op;
                    break;
                }
            }

            if (operator == null) {
                reportError(String.format(Lang.getMsg(getClass(), "operator"),
                    comperator, stringMatcher.group(0)));
                return extract();
            }

            final ConditionNumber numberCon = ConditionNumber.getInstance();
            numberCon.setData(operator, targetValue);
            return numberCon;
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
