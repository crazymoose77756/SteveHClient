/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.mixin;

import io.netty.channel.ChannelHandlerContext;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.ConnectToServerEvent;
import minegame159.meteorclient.modules.Modules;
import minegame159.meteorclient.modules.misc.AntiPacketKick;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.net.InetAddress;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void onHandlePacket(Packet<T> packet, PacketListener listener, CallbackInfo info) {
        PacketEvent.Receive event = MeteorClient.EVENT_BUS.post(PacketEvent.Receive.get(packet));

        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "connect", at = @At("HEAD"))
    private static void onConnect(InetAddress address, int port, boolean shouldUseNativeTransport, CallbackInfoReturnable<ClientConnection> info) {
        MeteorClient.EVENT_BUS.post(ConnectToServerEvent.get());
    }

    @Inject(method = "exceptionCaught", at = @At("HEAD"), cancellable = true)
    private void exceptionCaught(ChannelHandlerContext context, Throwable throwable, CallbackInfo ci) {
        if (throwable instanceof IOException && Modules.get().isActive(AntiPacketKick.class)) ci.cancel();
    }
}