/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.mixin;

import minegame159.meteorclient.modules.Modules;
import minegame159.meteorclient.modules.movement.NoSlow;
import net.minecraft.block.SlimeBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlimeBlock.class)
public class SlimeBlockMixin {
    @Inject(method = "onSteppedOn", at = @At("HEAD"), cancellable = true)
    private void onSteppedOn(World world, BlockPos pos, Entity entity, CallbackInfo info) {
        if (Modules.get().get(NoSlow.class).slimeBlock() && entity == MinecraftClient.getInstance().player) info.cancel();
    }
}
