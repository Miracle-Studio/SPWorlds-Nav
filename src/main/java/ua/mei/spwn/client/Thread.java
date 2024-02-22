package ua.mei.spwn.client;

import net.minecraft.text.*;
import ua.mei.spwn.config.*;

import java.awt.*;

public enum Thread {
    RED,
    GREEN,
    YELLOW,
    BLUE,

    SPAWN;

    public Color getColor() {
        SPWorldsNavConfig config = SPWorldsNavConfig.config();

        return switch (this) {
            case RED -> config.redColor;
            case GREEN -> config.greenColor;
            case YELLOW -> config.yellowColor;
            case BLUE -> config.blueColor;

            case SPAWN -> config.spawnColor;
        };
    }

    public String getDisplayName() {
        SPWorldsNavConfig config = SPWorldsNavConfig.config();

        return switch (this) {
            case RED -> config.redName;
            case GREEN -> config.greenName;
            case YELLOW -> config.yellowName;
            case BLUE -> config.blueName;

            case SPAWN -> config.spawnName;
        };
    }

    public Text getText(double x, double z) {
        SPWorldsNavConfig config = SPWorldsNavConfig.config();

        return switch (this) {
            case RED -> Text.literal(String.format("%s %s %s", config.redName, config.redSeparator, Math.round(Math.abs(z)))).styled(style -> style.withColor(config.redColor.getRGB()));
            case GREEN -> Text.literal(String.format("%s %s %s", config.greenName, config.greenSeparator, Math.round(Math.abs(x)))).styled(style -> style.withColor(config.greenColor.getRGB()));
            case YELLOW -> Text.literal(String.format("%s %s %s", config.yellowName, config.yellowSeparator, Math.round(Math.abs(z)))).styled(style -> style.withColor(config.yellowColor.getRGB()));
            case BLUE -> Text.literal(String.format("%s %s %s", config.blueName, config.blueSeparator, Math.round(Math.abs(x)))).styled(style -> style.withColor(config.blueColor.getRGB()));

            case SPAWN -> Text.literal(config.spawnName).styled(style -> style.withColor(config.spawnColor.getRGB()));
        };
    }
}
