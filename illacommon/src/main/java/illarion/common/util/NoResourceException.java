/*
 * This file is part of the Illarion project.
 *
 * Copyright © 2015 - Illarion e.V.
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
package illarion.common.util;

/**
 * No resource exception is a runtime exception that is thrown in case the the
 * table loader fails at loading a resource.
 *
 * @author Nop
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public final class NoResourceException extends RuntimeException {
    /**
     * The serialization unique ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor that creates this exception without any message.
     */
    public NoResourceException() {
    }

    /**
     * Create this message and give a readable cause for that message.
     *
     * @param message the exception message
     */
    public NoResourceException(String message) {
        super(message);
    }

    /**
     * Create this exception with a readable reason and a text why it turned up.
     *
     * @param message the exception message
     * @param cause the exception this newly created exception was caused by
     */
    public NoResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create this exception without a readable reason but with a exception that
     * caused this exception to raise up.
     *
     * @param cause the exception this newly created exception was caused by
     */
    public NoResourceException(Throwable cause) {
        super(cause);
    }
}
