/*
 * This file is part of the Illarion Nifty-GUI Controls.
 *
 * Copyright © 2012 - Illarion e.V.
 *
 * The Illarion Nifty-GUI Controls is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Illarion Nifty-GUI Controls is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Illarion Nifty-GUI Controls.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.illarion.nifty.controls;

import de.lessvoid.nifty.NiftyEvent;

/**
 * This event is fired in case the player closes the merchant dialog.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public final class DialogMerchantCloseEvent implements NiftyEvent<Void> {
    /**
     * The ID of the dialog.
     */
    private final int dialogId;

    /**
     * Create a new instance of this event and set the ID of the dialog that was closed.
     *
     * @param id the ID of the dialog
     */
    public DialogMerchantCloseEvent(final int id) {
        dialogId = id;
    }

    /**
     * Get the ID of the dialog that was closed when this event was fired.
     */
    public int getDialogId() {
        return dialogId;
    }
}
