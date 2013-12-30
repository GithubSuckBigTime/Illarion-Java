/*
 * This file is part of the Illarion Client.
 *
 * Copyright © 2012 - Illarion e.V.
 *
 * The Illarion Client is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Illarion Client is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Illarion Client.  If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.client.input;

import org.illarion.engine.input.Button;

/**
 * This event is published when a click operation on the map was noted.
 *
 * @author Vilarion &lt;vilarion@illarion.org&gt;
 */
public final class ClickOnMapEvent extends AbstractMouseOnMapEvent {
    /**
     * Create and initialize such an event.
     *
     * @param key the mouse key that was clicked
     * @param x   the x coordinate of the click
     * @param y   the y coordinate of the click
     */
    public ClickOnMapEvent(final Button key, final int x, final int y) {
        super(key, x, y);
    }
}