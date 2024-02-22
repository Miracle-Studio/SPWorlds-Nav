package ua.mei.spwn.client;

import io.wispforest.owo.ui.core.*;
import net.kyori.adventure.text.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.*;
import net.kyori.adventure.text.format.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import net.minecraft.registry.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import ua.mei.spwn.config.*;

public class SPMath {
    public static Thread thread(double x, double z, boolean spawn) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        SPWorldsNavConfig config = SPWorldsNavConfig.config();

        if (player != null && config.showSpawn && spawn) {
            if (player.clientWorld.getRegistryKey() == World.OVERWORLD) {
                if (Math.abs(x) <= 500 && Math.abs(z) <= 500) {
                    return Thread.SPAWN;
                }
            }
        }

        if (z <= 0 && Math.abs(z) >= Math.abs(x)) {
            return Thread.RED;
        } else if (x >= 0 && x >= Math.abs(z)) {
            return Thread.GREEN;
        } else if (z >= 0 && z >= Math.abs(x)) {
            return Thread.YELLOW;
        } else {
            return Thread.BLUE;
        }
    }

    public static String turnText(Thread thread, int x, int z) {
        return switch (thread) {
            case RED -> String.format("Через %s поворот %s", Math.abs(z), x <= 0 ? "← налево" : "→ направо");
            case GREEN -> String.format("Через %s поворот %s", Math.abs(x), z <= 0 ? "← налево" : "→ направо");
            case YELLOW -> String.format("Через %s поворот %s", Math.abs(z), x >= 0 ? "← налево" : "→ направо");
            case BLUE -> String.format("Через %s поворот %s", Math.abs(x), z >= 0 ? "← налево" : "→ направо");
            case SPAWN -> "";
        };
    }

    public static TextComponent posText(int x, int y, int z) {
        Thread thread = thread(x, z, false);
        int textColor = thread.getColor().getRGB();
        int netherX = Math.round(x / 8f);
        int netherZ = Math.round(z / 8f);

        return Component.text()
                .content(String.format("[%s, %s, %s]", x, y, z))
                .color(TextColor.color(textColor))
                .hoverEvent(HoverEvent.showText(
                        Component.text()
                                .content(String.format("%s ветка\n", thread.getDisplayName()))
                                .color(TextColor.color(textColor))
                                .append(Component.text(turnText(thread, netherX, netherZ) + "\n\n").color(TextColor.color(textColor)))
                                .append(Component.text((String.format("☀ %s, %s, %s\n", x, y, z))).color(TextColor.color(Formatting.GREEN.getColorValue())))
                                .append(Component.text((String.format("\uD83D\uDD25 %s, %s, %s", netherX, y, netherZ))).color(TextColor.color(Formatting.RED.getColorValue())))
                ))
                .build();
    }

    public static boolean showHud() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        SPWorldsNavConfig config = SPWorldsNavConfig.config();

        if (Server.getServer() != null) {
            if (player != null) {
                RegistryKey<World> world = player.clientWorld.getRegistryKey();

                if (world == World.OVERWORLD) {
                    return config.showInOverworld;
                } else if (world == World.NETHER) {
                    return config.showInNether;
                } else if (world == World.END) {
                    return config.showInEnd;
                }
            }
        }

        return false;
    }

    public static Positioning getPositioning() {
        SPWorldsNavConfig config = SPWorldsNavConfig.config();
        return Positioning.relative(config.navbarX, config.navbarY);
    }
}
