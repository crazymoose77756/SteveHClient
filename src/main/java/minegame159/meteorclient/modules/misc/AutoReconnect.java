/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.modules.misc;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.ConnectToServerEvent;
import minegame159.meteorclient.modules.Category;
import minegame159.meteorclient.modules.Module;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import net.minecraft.client.network.ServerInfo;

public class AutoReconnect extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();
    
    public final Setting<Double> time = sgGeneral.add(new DoubleSetting.Builder()
            .name("delay")
            .description("The amount of seconds to wait before reconnecting to the server.")
            .defaultValue(2.0)
            .min(0.0)
            .build()
    );

    public ServerInfo lastServerInfo;

    public AutoReconnect() {
        super(Category.Misc, "auto-reconnect", "Automatically reconnects when disconnected from a server.");
        MeteorClient.EVENT_BUS.subscribe(new StaticListener());
    }

    private class StaticListener {
        @EventHandler
        private void onConnectToServer(ConnectToServerEvent event) {
            lastServerInfo = mc.isInSingleplayer() ? null : mc.getCurrentServerEntry();
        }
    }
}
