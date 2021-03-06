/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.mixin;

import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Inject(method = "apply", at = @At("HEAD"))
    private void onApply(List<String> list, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        list.add("Steve Hamish!");
        list.add("Star SteveHClient on GitHub!");
        list.add("Based utility mod.");
        list.add("based");
        list.add("1.17 out now!");
        list.add("The Barb King");
        list.add("OK.");
        list.add("Admin");
        list.add("Snake");
        list.add("monkey");
        list.add("Minceraft");
        list.add("www.google.com");
        list.add("azob moment");
        list.add("xli moment");
        list.add("squid god");
        list.add("pork");
    }
}
