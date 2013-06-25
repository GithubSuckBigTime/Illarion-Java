/*
 * This file is part of the Illarion easyNPC Editor.
 *
 * Copyright © 2012 - Illarion e.V.
 *
 * The Illarion easyNPC Editor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Illarion easyNPC Editor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Illarion easyNPC Editor.  If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.easynpc.parser.talk;

import illarion.easynpc.parsed.talk.TalkConsequence;

/**
 * This interface it used by all parsers that aim to parse consequences out of a
 * talking line.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public abstract class ConsequenceParser extends Parser {
    /**
     * Extract the consequence from a line.
     *
     * @return the consequence filled with the data of this line
     */
    public abstract TalkConsequence extract();
}
