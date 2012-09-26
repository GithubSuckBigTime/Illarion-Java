/*
 * This file is part of the Illarion Client.
 *
 * Copyright © 2012 - Illarion e.V.
 *
 * The Illarion Client is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Illarion Client is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with the Illarion Client.  If not, see <http://www.gnu.org/licenses/>.
 */
package illarion.client.gui.controller;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import illarion.client.gui.*;
import org.newdawn.slick.GameContainer;

import java.util.ArrayList;
import java.util.Collection;

public final class GameScreenController implements ScreenController {

    private Nifty parentNifty;
    private Screen parentScreen;

    private final Collection<ScreenController> childControllers;
    private final Collection<UpdatableHandler> childUpdateControllers;

    private final NumberSelectPopupHandler numberPopupHandler;
    private final TooltipHandler tooltipHandler;

    public GameScreenController() {
        numberPopupHandler = new NumberSelectPopupHandler();
        tooltipHandler = new TooltipHandler();

        childControllers = new ArrayList<ScreenController>();
        childUpdateControllers = new ArrayList<UpdatableHandler>();

        addHandler(numberPopupHandler);
        addHandler(tooltipHandler);
        addHandler(new GUIChatHandler());
        addHandler(new GUIInventoryHandler(numberPopupHandler, tooltipHandler));
        addHandler(new CharListHandler());
        addHandler(new DialogHandler(numberPopupHandler));
        addHandler(new ContainerHandler(numberPopupHandler, tooltipHandler));
        addHandler(new CloseGameHandler());

        addHandler(new GameMapHandler(numberPopupHandler, tooltipHandler));

        addHandler(new InformHandler());
    }

    private void addHandler(final ScreenController handler) {
        childControllers.add(handler);
        if (handler instanceof UpdatableHandler) {
            childUpdateControllers.add((UpdatableHandler) handler);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void bind(final Nifty nifty, final Screen screen) {
        parentNifty = nifty;
        parentScreen = screen;

        for (final ScreenController childController : childControllers) {
            childController.bind(nifty, screen);
        }
    }

    @Override
    public void onStartScreen() {
        for (final ScreenController childController : childControllers) {
            childController.onStartScreen();
        }
    }

    /**
     * This function is called once inside the game loop with the delta value of the current update loop. Inside this
     * functions changes to the actual representation of the GUI should be done.
     *
     * @param container the container that displays the game
     * @param delta     the time since the last update call
     */
    public void onUpdateGame(final GameContainer container, final int delta) {
        for (final UpdatableHandler childController : childUpdateControllers) {
            childController.update(container, delta);
        }
    }

    @Override
    public void onEndScreen() {
        for (final ScreenController childController : childControllers) {
            childController.onEndScreen();
        }
    }
}
