/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.render.hud.modules;

import minegame159.meteorclient.mixin.MinecraftClientAccessor;
import minegame159.meteorclient.modules.render.hud.HUD;

public class FpsHud extends DoubleTextHudModule {
    public FpsHud(HUD hud) {
        super(hud, "fps", "Displays your FPS.", "FPS: ");
    }

    @Override
    protected String getRight() {
        return Integer.toString(((MinecraftClientAccessor) mc).getFps());
    }
}
