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
package illarion.common.graphics;

/**
 * This interfaces defines the file constructs that are load by the texture loader implementations.
 *
 * @param <T> the type of the texture stored in this texture atlas
 * @author Martin Karing &gt;nitram@illarion.org&lt;
 */
public interface TextureAtlas<T> {
    /**
     * Check if this texture atlas contains the specified image.
     *
     * @param texture the name of the texture to look for
     * @return {@code true} in case the atlas contains the texture
     */
    boolean containsTexture(String texture);

    /**
     * Get the texture assigned to a specified name.
     *
     * @param texture the name of the texture to look for
     * @return the texture or {@code null} in case the texture was not found in this object
     */
    T getTexture(String texture);
}
