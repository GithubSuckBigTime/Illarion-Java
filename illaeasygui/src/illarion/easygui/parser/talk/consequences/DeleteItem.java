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
import illarion.easygui.data.Items;
import illarion.easygui.parsed.talk.AdvancedNumber;
import illarion.easygui.parsed.talk.TalkConsequence;
import illarion.easygui.parsed.talk.consequences.ConsequenceDeleteItem;
import illarion.easygui.parser.talk.AdvNumber;
import illarion.easygui.parser.talk.ConsequenceParser;

/**
 * This is the delete item consequence. Its able to parse a delete item
 * consequence out of the consequence collection string.
 * 
 * @author Martin Karing
 * @since 1.00
 * @version 1.02
 */
public final class DeleteItem extends ConsequenceParser {
    /**
     * A empty string used for some replace operations.
     */
    @SuppressWarnings("nls")
    private static final String EMPTY_STRING = "".intern();

    /**
     * This pattern is used to find the strings in the condition and to remove
     * them properly.
     */
    @SuppressWarnings("nls")
    private static final Pattern STRING_FIND =
        Pattern
            .compile("\\s*deleteitem\\s*\\(\\s*(\\d{1,4})\\s*[,]\\s*"
                + AdvNumber.ADV_NUMBER_REGEXP
                + "\\s*([,]\\s*(\\d+))?\\)\\s*,\\s*", Pattern.CASE_INSENSITIVE);

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
            final int itemId = Integer.parseInt(stringMatcher.group(1));
            final AdvancedNumber targetValue =
                AdvNumber.getNumber(stringMatcher.group(2));

            final String dataString = stringMatcher.group(4);
            long dataValue = -1L;
            if (dataString != null) {
                dataValue = Long.parseLong(dataString);
            }

            setLine(stringMatcher.replaceFirst(EMPTY_STRING));

            if (targetValue == null) {
                reportError(String.format(Lang.getMsg(getClass(), "number"),
                    stringMatcher.group(3), stringMatcher.group(0)));
                return extract();
            }

            Items item = null;
            for (final Items it : Items.values()) {
                if (it.getItemId() == itemId) {
                    item = it;
                    break;
                }
            }

            if (item == null) {
                reportError(String.format(Lang.getMsg(getClass(), "item"),
                    stringMatcher.group(2), stringMatcher.group(0)));
                return extract();
            }

            final ConsequenceDeleteItem deleteItemCons =
                ConsequenceDeleteItem.getInstance();

            if (dataValue > -1L) {
                deleteItemCons.setData(item, targetValue, dataValue);
            } else {
                deleteItemCons.setData(item, targetValue);
            }
            return deleteItemCons;
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
