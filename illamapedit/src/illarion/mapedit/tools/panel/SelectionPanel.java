/*
 * This file is part of the Illarion Mapeditor.
 *
 * Copyright © 2013 - Illarion e.V.
 *
 * The Illarion Mapeditor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Illarion Mapeditor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Illarion Mapeditor.  If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.mapedit.tools.panel;

import illarion.mapedit.Lang;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

/**
 * Panel for settings for the selection tool
 *
 * @author Fredrik K
 */
public class SelectionPanel  extends JPanel {
    @Nonnull
    protected JCheckBox delCheckBox;

    /**
     * Default constructor
     */
    public SelectionPanel() {
        setLayout(new BorderLayout());

        final JPanel northPanel = new JPanel(new GridLayout(0, 2));
        delCheckBox = new JCheckBox();
        northPanel.add(new JLabel(Lang.getMsg("tools.SelectionTool.Delete")));
        northPanel.add(delCheckBox);

        add(northPanel, BorderLayout.NORTH);
    }

    /**
     * Check if the Deselect checkbox is selected
     *
     * @return {@code true} if deselect is checked
     */
    public boolean isDeselectChecked() {
        return delCheckBox.isSelected();
    }
}
