/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.player;

import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;

public class NoBreakDelay extends Module {
    public NoBreakDelay() {
        super(Category.Player, "no-break-delay", "Completely removes the delay between breaking blocks.");
    }
}