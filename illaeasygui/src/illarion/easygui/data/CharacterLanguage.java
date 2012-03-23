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
package illarion.easygui.data;

/**
 * This enumerator contains the possible values for the language the character
 * is speaking.
 * 
 * @author Martin Karing
 * @since 1.00
 */
public enum CharacterLanguage {
    ancient(10), common(0), dwarf(2), elf(3), halfling(5), human(1),
    lizard(4), orc(5);

    /**
     * The ID of the language.
     */
    private final int id;

    /**
     * Constructor for the constants. It takes the Id of the language to store
     * it in each constant.
     * 
     * @param langId the language ID fitting this constant
     */
    private CharacterLanguage(final int langId) {
        id = langId;
    }

    /**
     * Get the language ID fitting of this constant.
     * 
     * @return the language ID
     */
    public int getLangId() {
        return id;
    }
}
