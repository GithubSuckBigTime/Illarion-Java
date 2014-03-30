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
package org.illarion.engine.backend.slick;

import org.lwjgl.opengl.DisplayMode;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Comparator;

/**
 * This class can be used to sort the display modes for the display in the option menu.
 *
 * @author Stefano Bonicatti &lt;smjert@gmail.com&gt;
 */
final class DisplayModeSorter implements Comparator<DisplayMode>, Serializable {
    @Override
    public int compare(@Nonnull final DisplayMode a, @Nonnull final DisplayMode b) {
        //Width
        if (a.getWidth() != b.getWidth()) {
            return (a.getWidth() > b.getWidth()) ? 1 : -1;
        }
        //Height
        if (a.getHeight() != b.getHeight()) {
            return (a.getHeight() > b.getHeight()) ? 1 : -1;
        }
        //Bit depth
        if (a.getBitsPerPixel() != b.getBitsPerPixel()) {
            return (a.getBitsPerPixel() > b.getBitsPerPixel()) ? 1 : -1;
        }
        //Refresh rate
        if (a.getFrequency() != b.getFrequency()) {
            return (a.getFrequency() > b.getFrequency()) ? 1 : -1;
        }
        //All fields are equal
        return 0;
    }
}
