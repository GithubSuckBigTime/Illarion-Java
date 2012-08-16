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
package illarion.easynpc.parser;

import illarion.easynpc.EasyNpcScript;
import illarion.easynpc.ParsedNpc;
import illarion.easynpc.docu.DocuEntry;
import org.fife.ui.rsyntaxtextarea.TokenMap;

/**
 * This interface is implement by all different kind of NPCs that can be load
 * into the parsed NPC. Each type requires the ability to parse the lines
 * assigned to it from the NPC script.
 *
 * @author Martin Karing
 * @since 1.00
 */
public interface NpcType extends DocuEntry {
    /**
     * Check if this NPC is able to parse a certain line of code.
     *
     * @param line the line that is supposed to be parsed
     * @return <code>true</code> in case the line can be parsed
     */
    boolean canParseLine(EasyNpcScript.Line line);

    /**
     * Parse the line and add it to the parsed NPC structure.
     *
     * @param line the line to parse
     * @param npc  the NPC the parsed line is supposed to be added to
     */
    void parseLine(EasyNpcScript.Line line, ParsedNpc npc);

    /**
     * Add the tokens the the map that need to be highlighted in the editor.
     *
     * @param map the token list
     */
    void enlistHighlightedWords(TokenMap map);
}
