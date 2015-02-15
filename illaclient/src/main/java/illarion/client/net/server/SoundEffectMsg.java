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
package illarion.client.net.server;

import illarion.client.net.CommandList;
import illarion.client.net.annotations.ReplyMessage;
import illarion.client.resources.SoundFactory;
import illarion.client.util.UpdateTask;
import illarion.client.world.World;
import illarion.common.net.NetCommReader;
import illarion.common.types.Location;
import org.illarion.engine.GameContainer;
import org.illarion.engine.assets.SoundsManager;
import org.illarion.engine.sound.Sound;
import org.illarion.engine.sound.Sounds;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;

/**
 * Server message: Sound effect
 *
 * @author Martin Karing &lt;nitram@illarion.org&gt;
 * @author Nop
 */
@ReplyMessage(replyId = CommandList.MSG_SOUND_FX)
public final class SoundEffectMsg implements UpdateTask, ServerReply {
    /**
     * ID of the effect that shall be shown.
     */
    private int effectId;

    /**
     * The location the effect occurs on.
     */
    @Nullable
    private Location location;

    @Override
    public void decode(@Nonnull NetCommReader reader) throws IOException {
        location = new Location(reader);
        effectId = reader.readUShort();
    }

    @Nonnull
    @Override
    public ServerReplyResult execute() {
        if (location == null) {
            throw new NotDecodedException();
        }

        World.getUpdateTaskManager().addTask(this);
        return ServerReplyResult.Success;
    }

    @Override
    public void onUpdateGame(@Nonnull GameContainer container, int delta) {
        if (location == null) {
            throw new NotDecodedException(); // this can't happen.
        }

        Location plyLoc = World.getPlayer().getLocation();
        SoundsManager manager = container.getEngine().getAssets().getSoundsManager();
        Sound sound = SoundFactory.getInstance().getSound(effectId, manager);
        if (sound == null) {
            return;
        }
        Sounds sounds = container.getEngine().getSounds();

        int dX = location.getScX() - plyLoc.getScX();
        int dY = location.getScY() - plyLoc.getScY();
        int dZ = location.getScZ() - plyLoc.getScZ();
        sounds.playSound(sound, sounds.getSoundVolume(), dX, dY, dZ);
    }

    @Nonnull
    @Override
    @Contract(pure = true)
    public String toString() {
        return Utilities.toString(SoundEffectMsg.class, location, "Sound: " + effectId);
    }
}
