/*
 * This file is part of the Illarion project.
 *
 * Copyright © 2014 - Illarion e.V.
 *
 * Illarion is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Illarion is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
package org.illarion.engine.input;

/**
 * This enumerator defines the possible values for the forwarding of input events.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public enum ForwardingTarget {
    /**
     * This forwarding target effects all input events.
     */
    All,

    /**
     * This forwarding target effects only mouse events.
     */
    Mouse,

    /**
     * This forwarding target effects only keyboard events.
     */
    Keyboard
}
