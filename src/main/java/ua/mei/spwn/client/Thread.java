package ua.mei.spwn.client;

import net.minecraft.text.*;
import net.minecraft.util.*;

public enum Thread {
    SPAWN,

    RED,
    GREEN,
    YELLOW,
    CYAN,

    BLUE;

    public Text getText(double x, double z) {
        return switch (this) {
            case SPAWN -> Text.translatable("hud.spwn.spawn").formatted(Formatting.GRAY);

            case RED -> Text.translatable("hud.spwn.red").append(" | " + Math.round(Math.abs(z))).formatted(Formatting.RED);
            case GREEN -> Text.translatable("hud.spwn.green").append(" | " + Math.round(Math.abs(x))).formatted(Formatting.DARK_GREEN);
            case YELLOW -> Text.translatable("hud.spwn.yellow").append(" | " + Math.round(Math.abs(z))).formatted(Formatting.GOLD);
            case CYAN -> Text.translatable("hud.spwn.cyan").append(" | " + Math.round(Math.abs(x))).formatted(Formatting.DARK_AQUA);

            case BLUE -> Text.translatable("hud.spwn.blue").append(" | " + Math.round(Math.abs(x))).formatted(Formatting.BLUE);
        };
    }

    public Text getText() {
        return switch (this) {
            case SPAWN -> Text.translatable("hud.spwn.spawn").formatted(Formatting.GRAY);

            case RED -> Text.translatable("hud.spwn.red").formatted(Formatting.RED);
            case GREEN -> Text.translatable("hud.spwn.green").formatted(Formatting.DARK_GREEN);
            case YELLOW -> Text.translatable("hud.spwn.yellow").formatted(Formatting.GOLD);
            case CYAN -> Text.translatable("hud.spwn.cyan").formatted(Formatting.DARK_AQUA);

            case BLUE -> Text.translatable("hud.spwn.blue").formatted(Formatting.BLUE);
        };
    }
}
