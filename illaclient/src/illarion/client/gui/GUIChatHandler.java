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
package illarion.client.gui;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.ElementBuilder;
import de.lessvoid.nifty.controls.ScrollPanel;
import de.lessvoid.nifty.controls.TextField;
import de.lessvoid.nifty.controls.label.builder.LabelBuilder;
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.input.NiftyInputEvent;
import de.lessvoid.nifty.screen.KeyInputHandler;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import illarion.client.input.InputReceiver;
import illarion.client.net.CommandFactory;
import illarion.client.net.CommandList;
import illarion.client.net.client.SayCmd;
import illarion.client.net.server.events.BroadcastInformReceivedEvent;
import illarion.client.net.server.events.ScriptInformReceivedEvent;
import illarion.client.net.server.events.TextToInformReceivedEvent;
import illarion.client.util.Lang;
import illarion.client.world.events.CharTalkingEvent;
import javolution.text.TextBuilder;
import org.bushe.swing.event.EventBus;
import org.bushe.swing.event.EventSubscriber;
import org.bushe.swing.event.EventTopicSubscriber;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This class takes care to receive chat input from the GUI and sends it to the server. Also it receives chat from the
 * server and takes care for displaying it on the GUI.
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 */
public final class GUIChatHandler implements KeyInputHandler, EventTopicSubscriber<String>,
        EventSubscriber<CharTalkingEvent>, ScreenController, UpdatableHandler {
    /**
     * The log that is used to display the text.
     */
    private ScrollPanel chatLog;

    /**
     * The input field that holds the text that is yet to be send.
     */
    private TextField chatMsg;

    /**
     * The screen that displays the GUI.
     */
    private Screen screen;

    /**
     * The Queue of strings that yet need to be written to the GUI.
     */
    private final Queue<String> messageQueue;

    /**
     * The inform handler for the broadcast inform messages.
     */
    private final EventSubscriber<BroadcastInformReceivedEvent> bcInformEventHandler =
            new EventSubscriber<BroadcastInformReceivedEvent>() {
                @Override
                public void onEvent(final BroadcastInformReceivedEvent event) {
                    final TextBuilder textBuilder = TextBuilder.newInstance();
                    try {
                        textBuilder.append(Lang.getMsg("chat.broadcast"));
                        textBuilder.append(": ");
                        textBuilder.append(event.getMessage());

                        messageQueue.offer(textBuilder.toString());
                    } finally {
                        TextBuilder.recycle(textBuilder);
                    }
                }
            };

    /**
     * The inform handler for the text to inform messages.
     */
    private final EventSubscriber<TextToInformReceivedEvent> ttInformEventHandler =
            new EventSubscriber<TextToInformReceivedEvent>() {
                @Override
                public void onEvent(final TextToInformReceivedEvent event) {
                    final TextBuilder textBuilder = TextBuilder.newInstance();
                    try {
                        textBuilder.append(Lang.getMsg("chat.textto"));
                        textBuilder.append(": ");
                        textBuilder.append(event.getMessage());

                        messageQueue.offer(textBuilder.toString());
                    } finally {
                        TextBuilder.recycle(textBuilder);
                    }
                }
            };

    /**
     * The inform handler for the text to inform messages.
     */
    private final EventSubscriber<ScriptInformReceivedEvent> scriptInformEventHandler =
            new EventSubscriber<ScriptInformReceivedEvent>() {
                @Override
                public void onEvent(final ScriptInformReceivedEvent event) {
                    if (event.getInformPriority() == 0) {
                        return;
                    }

                    final TextBuilder textBuilder = TextBuilder.newInstance();
                    try {
                        textBuilder.append(Lang.getMsg("chat.scriptInform"));
                        textBuilder.append(": ");
                        textBuilder.append(event.getMessage());

                        messageQueue.offer(textBuilder.toString());
                    } finally {
                        TextBuilder.recycle(textBuilder);
                    }
                }
            };

    /**
     * The default constructor.
     */
    public GUIChatHandler() {
        messageQueue = new ConcurrentLinkedQueue<String>();
    }

    @Override
    public void bind(final Nifty nifty, final Screen screen) {
        this.screen = screen;

        chatMsg = screen.findNiftyControl("chatMsg", TextField.class);
        chatLog = screen.findNiftyControl("chatPanel", ScrollPanel.class);

        chatMsg.getElement().addInputHandler(this);
    }

    @Override
    public void onStartScreen() {
        EventBus.subscribe(CharTalkingEvent.class, this);
        EventBus.subscribe(InputReceiver.EB_TOPIC, this);
        EventBus.subscribe(BroadcastInformReceivedEvent.class, bcInformEventHandler);
        EventBus.subscribe(TextToInformReceivedEvent.class, ttInformEventHandler);
        EventBus.subscribe(ScriptInformReceivedEvent.class, scriptInformEventHandler);
    }

    @Override
    public void onEndScreen() {
        EventBus.unsubscribe(CharTalkingEvent.class, this);
        EventBus.unsubscribe(InputReceiver.EB_TOPIC, this);
        EventBus.unsubscribe(BroadcastInformReceivedEvent.class, bcInformEventHandler);
        EventBus.unsubscribe(TextToInformReceivedEvent.class, ttInformEventHandler);
        EventBus.unsubscribe(ScriptInformReceivedEvent.class, scriptInformEventHandler);
    }

    /**
     * Receive a Input event from the GUI and send a text in case this event applies.
     */
    @Override
    public boolean keyEvent(final NiftyInputEvent inputEvent) {
        if (inputEvent == NiftyInputEvent.SubmitText) {
            if (chatMsg.hasFocus()) {
                if (chatMsg.getDisplayedText().isEmpty()) {
                    screen.getFocusHandler().setKeyFocus(null);
                } else {
                    sendText(chatMsg.getDisplayedText());
                    chatMsg.setText("");
                }
            } else {
                chatMsg.setFocus();
            }

            return true;
        }
        return false;
    }

    /**
     * Send the text as talking text to the server.
     *
     * @param text the text to send
     */
    private static void sendText(final String text) {
        final SayCmd cmd = CommandFactory.getInstance().getCommand(CommandList.CMD_SAY, SayCmd.class);
        cmd.setText(text);
        cmd.send();
    }

    /**
     * Handle the events this handler subscribed to.
     *
     * @param topic the event topic
     * @param data  the data that was delivered along with this event
     */
    @Override
    public void onEvent(final String topic, final String data) {
        if (topic.equals(InputReceiver.EB_TOPIC)) {
            if (data.equals("SelectChat")) {
                chatMsg.setFocus();
            }
        }
    }

    @Override
    public void update(final int delta) {
        Element contentPane = null;

        while (true) {
            final String message = messageQueue.poll();
            if (message == null) {
                break;
            }

            if (contentPane == null) {
                contentPane = chatLog.getElement().findElementByName("chatLog");
            }

            final LabelBuilder label = new LabelBuilder();
            label.text(message);
            label.textHAlign(ElementBuilder.Align.Left);
            label.parameter("wrap", "true");
            label.width(label.percentage(100));
            label.build(contentPane.getNifty(), screen, contentPane);
        }
    }

    @Override
    public void onEvent(final CharTalkingEvent event) {
        messageQueue.offer(event.getLoggedText());
    }
}
