/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.events.game;

public class ResourcePacksReloadedEvent {
    private static final ResourcePacksReloadedEvent INSTANCE = new ResourcePacksReloadedEvent();

    public static ResourcePacksReloadedEvent get() {
        return INSTANCE;
    }
}
