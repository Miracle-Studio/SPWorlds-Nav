package ua.mei.spwn.config;

import com.google.gson.*;
import dev.isxander.yacl3.config.v2.api.*;
import dev.isxander.yacl3.config.v2.api.autogen.*;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.serializer.*;
import net.fabricmc.loader.api.*;
import net.minecraft.client.gui.screen.*;
import net.minecraft.util.*;

import java.awt.Color;

public class SPWorldsNavConfig {
    public static ConfigClassHandler<SPWorldsNavConfig> HANDLER = ConfigClassHandler.createBuilder(SPWorldsNavConfig.class)
            .id(new Identifier("spwn", "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("spwn-config.json"))
                    .setJson5(true)
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .build()
            ).build();

    // Главное
    @AutoGen(category = "main")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean showInOverworld = false;

    @AutoGen(category = "main")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean showInNether = true;

    @AutoGen(category = "main")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean showInEnd = false;

    @AutoGen(category = "main")
    @Boolean(formatter = Boolean.Formatter.YES_NO, colored = true)
    @SerialEntry
    public boolean showSpawn = false;

    // Визуал -> Отображение
    @AutoGen(category = "visual", group = "navbar")
    @IntSlider(min = 0, max = 100, step = 1)
    @CustomFormat(IntPercentFormatter.class)
    @SerialEntry
    public int navbarX = 50;

    @AutoGen(category = "visual", group = "navbar")
    @IntSlider(min = 0, max = 100, step = 1)
    @CustomFormat(IntPercentFormatter.class)
    @SerialEntry
    public int navbarY = 1;

    @AutoGen(category = "visual", group = "navbar")
    @ColorField(allowAlpha = true)
    @SerialEntry
    public Color navbarColor = new Color(0x80000000, true);

    // Визуал -> Красная ветка
    @AutoGen(category = "visual", group = "red")
    @ColorField
    @SerialEntry
    public Color redColor = new Color(0xFF5555);

    @AutoGen(category = "visual", group = "red")
    @StringField
    @SerialEntry
    public String redName = "Красная";

    @AutoGen(category = "visual", group = "red")
    @StringField
    @SerialEntry
    public String redSeparator = "|";

    // Визуал -> Зеленая ветка
    @AutoGen(category = "visual", group = "green")
    @ColorField
    @SerialEntry
    public Color greenColor = new Color(0x00AA00);

    @AutoGen(category = "visual", group = "green")
    @StringField
    @SerialEntry
    public String greenName = "Зеленая";

    @AutoGen(category = "visual", group = "green")
    @StringField
    @SerialEntry
    public String greenSeparator = "|";

    // Визуал -> Желтая ветка
    @AutoGen(category = "visual", group = "yellow")
    @ColorField
    @SerialEntry
    public Color yellowColor = new Color(0xFFAA00);

    @AutoGen(category = "visual", group = "yellow")
    @StringField
    @SerialEntry
    public String yellowName = "Желтая";

    @AutoGen(category = "visual", group = "yellow")
    @StringField
    @SerialEntry
    public String yellowSeparator = "|";

    // Визуал -> Синяя ветка
    @AutoGen(category = "visual", group = "blue")
    @ColorField
    @SerialEntry
    public Color blueColor = new Color(0x5555FF);

    @AutoGen(category = "visual", group = "blue")
    @StringField
    @SerialEntry
    public String blueName = "Синяя";

    @AutoGen(category = "visual", group = "blue")
    @StringField
    @SerialEntry
    public String blueSeparator = "|";

    // Визуал -> Спавн
    @AutoGen(category = "visual", group = "spawn")
    @ColorField
    @SerialEntry
    public Color spawnColor = new Color(0xAAAAAA);

    @AutoGen(category = "visual", group = "spawn")
    @StringField
    @SerialEntry
    public String spawnName = "Спавн";

    public static SPWorldsNavConfig config() {
        return HANDLER.instance();
    }

    public static void load() {
        HANDLER.load();
    }

    public static Screen generateScreen(Screen parent) {
        return HANDLER.generateGui().generateScreen(parent);
    }
}
