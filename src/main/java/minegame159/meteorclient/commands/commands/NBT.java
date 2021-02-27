/*
 *hello
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.commands.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import minegame159.meteorclient.commands.Command;
import minegame159.meteorclient.commands.arguments.CompoundNbtTagArgumentType;
import minegame159.meteorclient.utils.player.ChatUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.NbtPathArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.text.BaseText;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class NBT extends Command {
    public NBT() {
        super("nbt", "Modifies NBT data for an item, example: .nbt add {display:{Name:'{\"text\":\"$cRed Name\"}'}}");
    }

    @Override
    public void build(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(literal("add").then(argument("nbt_data", CompoundNbtTagArgumentType.nbtTag()).executes(s -> {
            ItemStack stack = mc.player.inventory.getMainHandStack();
            if (validBasic(stack)) {
                CompoundTag tag = s.getArgument("nbt_data", CompoundTag.class);
                stack.getTag().copyFrom(tag);
                setStack(stack);
            }
            return SINGLE_SUCCESS;
        })));
        builder.then(literal("set").then(argument("nbt_data", CompoundNbtTagArgumentType.nbtTag()).executes(s -> {
            ItemStack stack = mc.player.inventory.getMainHandStack();
            if (validBasic(stack)) {
                CompoundTag tag = s.getArgument("nbt_data", CompoundTag.class);
                stack.setTag(tag);
                setStack(stack);
            }
            return SINGLE_SUCCESS;
        })));
        builder.then(literal("remove").then(argument("nbt_path", NbtPathArgumentType.nbtPath()).executes(s -> {
            ItemStack stack = mc.player.inventory.getMainHandStack();
            if (validBasic(stack)) {
                NbtPathArgumentType.NbtPath path = s.getArgument("nbt_path", NbtPathArgumentType.NbtPath.class);
                path.remove(stack.getTag());
            }
            return SINGLE_SUCCESS;
        })));
        builder.then(literal("get").executes(s -> {
            ItemStack stack = mc.player.inventory.getMainHandStack();
            if (stack == null) {
                ChatUtils.prefixError("NBT","You must hold an item in your main hand.");
            } else {
                CompoundTag tag = stack.getTag();
                String nbt = tag == null ? "none" : tag.asString();

                BaseText copyButton = new LiteralText("NBT");
                copyButton.setStyle(copyButton.getStyle()
                        .withFormatting(Formatting.UNDERLINE)
                        .withClickEvent(new ClickEvent(
                                ClickEvent.Action.RUN_COMMAND,
                                this.toString("copy")
                        ))
                        .withHoverEvent(new HoverEvent(
                                HoverEvent.Action.SHOW_TEXT,
                                new LiteralText("Copy the NBT data to your clipboard.")
                        )));

                BaseText text = new LiteralText("");
                text.append(copyButton);
                text.append(new LiteralText(": " + nbt));

                ChatUtils.info("NBT", text);
            }
            return SINGLE_SUCCESS;
        }));
        builder.then(literal("copy").executes(s -> {
            ItemStack stack = mc.player.inventory.getMainHandStack();
            if (stack == null) {
                ChatUtils.prefixError("NBT","You must hold an item in your main hand.");
            } else {
                CompoundTag tag = stack.getTag();
                if (tag == null)
                    ChatUtils.prefixError("NBT","No NBT data on this item.");
                else {
                    mc.keyboard.setClipboard(tag.toString());
                    BaseText nbt = new LiteralText("NBT");
                    nbt.setStyle(nbt.getStyle()
                            .withFormatting(Formatting.UNDERLINE)
                            .withHoverEvent(new HoverEvent(
                                    HoverEvent.Action.SHOW_TEXT,
                                    new LiteralText(tag.toString())
                            )));

                    BaseText text = new LiteralText("");
                    text.append(nbt);
                    text.append(new LiteralText(" data copied!"));

                    ChatUtils.info("NBT", text);
                }
            }
            return SINGLE_SUCCESS;
        }));
    }

    private void setStack(ItemStack stack) {
        mc.player.networkHandler.sendPacket(new CreativeInventoryActionC2SPacket(36 + mc.player.inventory.selectedSlot, stack));
    }

    private boolean validBasic(ItemStack stack) {
        if (!mc.player.abilities.creativeMode) {
            ChatUtils.prefixError("NBT","Creative mode only.");
            return false;
        }

        if (stack == null) {
            ChatUtils.prefixError("NBT","You must hold an item in your main hand.");
            return false;
        }
        return true;
    }
}
