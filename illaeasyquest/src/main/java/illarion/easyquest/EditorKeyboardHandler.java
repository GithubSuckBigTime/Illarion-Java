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
package illarion.easyquest;

import com.mxgraph.swing.handler.mxKeyboardHandler;
import com.mxgraph.swing.mxGraphComponent;

import javax.swing.*;

public class EditorKeyboardHandler extends mxKeyboardHandler {
    public EditorKeyboardHandler(mxGraphComponent graphComponent) {
        super(graphComponent);
    }

    protected InputMap getInputMap(int condition) {
        InputMap map = super.getInputMap(condition);

        if (condition == JComponent.WHEN_FOCUSED && map != null) {
            map.put(KeyStroke.getKeyStroke("control S"), "save");
            map.put(KeyStroke.getKeyStroke("control N"), "new");
            map.put(KeyStroke.getKeyStroke("control O"), "open");
            map.put(KeyStroke.getKeyStroke("control Z"), "undo");
            map.put(KeyStroke.getKeyStroke("control Y"), "redo");
        }

        return map;
    }

    protected ActionMap createActionMap() {
        ActionMap map = super.createActionMap();

        map.put("save", new EditorActions.SaveAction());
        map.put("new", new EditorActions.NewAction());
        map.put("open", new EditorActions.OpenAction());
        map.put("undo", new EditorActions.UndoAction());
        map.put("redo", new EditorActions.RedoAction());

        return map;
    }
}