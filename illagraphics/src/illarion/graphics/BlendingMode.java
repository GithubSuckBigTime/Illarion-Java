/*
 * This file is part of the Illarion Graphics Engine.
 *
 * Copyright © 2011 - Illarion e.V.
 *
 * The Illarion Graphics Engine is free software: you can redistribute i and/or
 * modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * The Illarion Graphics Engine is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * the Illarion Graphics Interface. If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.graphics;

/**
 * This enumerator defines the different supported color blending modes the
 * render area is able to operate.
 * 
 * @author Martin Karing
 * @since 2.00
 * @version 2.00
 */
public enum BlendingMode {
    /**
     * Blend the colors that overlay each other.
     */
    BLEND,

    /**
     * Multiply the colors that overlay each other.
     */
    MULTIPLY;
}
