/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.render.hud.modules;

import minegame159.meteorclient.modules.Modules;
import minegame159.meteorclient.modules.player.NameProtect;
import minegame159.meteorclient.modules.render.hud.HUD;

public class WelcomeHud extends DoubleTextHudModule {
    public WelcomeHud(HUD hud) {
        super(hud, "welcome", "Displays a welcome message.", "Haha Imagine having this on your screen! ");
        rightColor = hud.welcomeColor.get();
    }

    @Override
    protected String getRight() {
        if (mc.player == null) return "UnknownPlayer!";
        return Modules.get().get(NameProtect.class).getName(mc.player.getGameProfile().getName()) + "!";
    }
}
