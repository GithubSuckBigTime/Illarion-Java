/*
 * This file is part of the Illarion Common Library.
 *
 * Copyright © 2013 - Illarion e.V.
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
package illarion.common.config.entries;

import illarion.common.config.Config;

import javax.annotation.Nonnull;

/**
 * This is a configuration entry that is used to display a checkbox in the
 * configuration dialog. So a simple yes/no option.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public final class CheckEntry implements ConfigEntry {
    /**
     * The configuration that is controlled by this text entry.
     */
    private Config cfg;

    /**
     * The key in the configuration that is handled by this configuration.
     */
    private final String configEntry;

    /**
     * Create a new configuration entry that is handled by this entry.
     *
     * @param entry the configuration key that is handled by this text entry
     */
    public CheckEntry(final String entry) {
        configEntry = entry;
    }

    /**
     * Get the value set in the configuration for this check entry.
     *
     * @return the configuration stored for this check entry
     */
    public boolean getValue() {
        return cfg.getBoolean(configEntry);
    }

    /**
     * Set the configuration handled by this configuration entry.
     *
     * @param config the configuration that is supposed to be handled by this
     * configuration entry
     */
    @Override
    public void setConfig(@Nonnull final Config config) {
        cfg = config;
    }

    /**
     * Set the new value of the configuration entry that is controlled by this.
     *
     * @param newValue the new configuration value
     */
    public void setValue(final boolean newValue) {
        cfg.set(configEntry, newValue);
    }
}
