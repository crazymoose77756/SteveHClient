/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.render;

import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;

public class UnfocusedCPU extends Module {
    public UnfocusedCPU() {
        super(Category.Render, "unfocused-cpu", "Will not render anything when your Minecraft window is not focused.");
    }
}