/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.misc;

import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;

public class AntiPacketKick extends Module {
    public AntiPacketKick() {
        super(Category.Misc, "anti-packet-kick", "Attempts to prevent you from being disconnected by large packets.");
    }
}
