/*
 * This file is part of the Illarion Common Library.
 *
 * Copyright © 2012 - Illarion e.V.
 *
 * The Illarion Common Library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Illarion Common Library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Illarion Common Library.  If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.common.config;

/**
 * This listener is used to monitor changes all changes done to the
 * configuration.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
@Deprecated
public interface ConfigChangeListener {
    /**
     * This function is called for the listener in case the configuration
     * changed.
     *
     * @param cfg the configuration that was changed
     * @param key the key of the entry that was changed
     */
    void configChanged(Config cfg, String key);
}
