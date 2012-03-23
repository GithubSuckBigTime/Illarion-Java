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
import illarion.easygui.data.PlayerLanguage;
import illarion.easygui.parsed.talk.TalkCondition;
import illarion.easygui.parsed.talk.conditions.ConditionLanguage;
import illarion.easygui.parser.talk.ConditionParser;

/**
 * This is a language condition. Its able to parse a language value out of the
 * NPC condition line.
 * 
 * @author Martin Karing
 * @since 1.00
 * @version 1.02
 */
public final class Language extends ConditionParser {
    /**
     * A empty string used for some replace operations.
     */
    @SuppressWarnings("nls")
    private static final String EMPTY_STRING = "".intern();

    /**
     * This pattern is used to find the language operation in the condition
     * properly.
     */
    @SuppressWarnings("nls")
    private static final Pattern LANGUAGE_FIND = Pattern.compile(
        "\\s*((german)|(english))\\s*,\\s*", Pattern.CASE_INSENSITIVE);

    /**
     * Extract a condition from the working string.
     */
    @Override
    @SuppressWarnings("nls")
    public TalkCondition extract() {
        if (getNewLine() == null) {
            throw new IllegalStateException("Can't extract if no state set.");
        }

        final Matcher stringMatcher = LANGUAGE_FIND.matcher(getNewLine());
        if (stringMatcher.find()) {
            final String language = stringMatcher.group(1).toLowerCase();

            setLine(stringMatcher.replaceFirst(EMPTY_STRING));

            PlayerLanguage lang = null;
            for (final PlayerLanguage testLang : PlayerLanguage.values()) {
                if (testLang.name().equalsIgnoreCase(language)) {
                    lang = testLang;
                    break;
                }
            }

            if (lang == null) {
                reportError(String.format(Lang.getMsg(getClass(), "lang"),
                    language, stringMatcher.group(0)));
                return extract();
            }

            final ConditionLanguage langCon = ConditionLanguage.getInstance();
            langCon.setData(lang);
            return langCon;
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
