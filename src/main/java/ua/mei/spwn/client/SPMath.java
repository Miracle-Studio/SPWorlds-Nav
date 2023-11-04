package ua.mei.spwn.client;

import io.wispforest.owo.ui.core.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import net.minecraft.world.*;
import ua.mei.spwn.config.*;

public class SPMath {
    public static Thread thread(double x, double z, boolean spawn) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (spawn && player != null && SPWorldsNavConfig.getConfig().showSpawn) {
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
        } else if (x <= 0 && Math.abs(x) >= Math.abs(z)) {
            Server server = server();

            if (server == Server.SPm || server == Server.PoopLand) {
                return Thread.CYAN;
            } else {
                return Thread.BLUE;
            }
        }

        return null;
    }

    public static Thread thread(double x, double z) {
        return thread(x, z, false);
    }

    public static Server server() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            ServerInfo server = MinecraftClient.getInstance().getCurrentServerEntry();

            if (server != null) {
                switch (server.address) {
                    case "sp.spworlds.ru" -> {
                        return Server.SP;
                    }
                    case "spm.spworlds.ru" -> {
                        return Server.SPm;
                    }
                    case "pl.spworlds.ru" -> {
                        return Server.PoopLand;
                    }
                }
            }
        }

        return Server.OTHER;
    }

    public static boolean showHud() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        SPWorldsNavConfig config = SPWorldsNavConfig.getConfig();

        if (server() != Server.OTHER) {
            if (player != null) {
                if (player.clientWorld.getRegistryKey() == World.OVERWORLD) {
                    return config.showInOverworld;
                } else if (player.clientWorld.getRegistryKey() == World.END) {
                    return config.showInEnd;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    public static Positioning getPositioning() {
        SPWorldsNavConfig config = SPWorldsNavConfig.getConfig();

        if (config.align == AlignEnum.LEFT) {
            return Positioning.relative(1, 1);
        } else if (config.align == AlignEnum.CENTER) {
            return Positioning.relative(50, 1);
        } else {
            return Positioning.relative(99, 1);
        }
    }
}
