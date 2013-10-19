/*
 * This file is part of the common.
 *
 * Copyright © 2013 - Illarion e.V.
 *
 * The common is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The common is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the common.  If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.common.util;

import javax.annotation.Nonnull;

/**
 * This exception is thrown in case the crypto class runs into any problem.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
@SuppressWarnings("ALL")
public class CryptoException extends Exception {
    public CryptoException() {
        super();
    }

    public CryptoException(@Nonnull final String message) {
        super(message);
    }

    public CryptoException(@Nonnull final String message, @Nonnull final Throwable cause) {
        super(message, cause);
    }

    public CryptoException(@Nonnull final Throwable cause) {
        super(cause);
    }
}
