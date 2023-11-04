package ua.mei.spwn.config;

import com.google.gson.*;
import dev.isxander.yacl3.config.v2.api.*;
import dev.isxander.yacl3.config.v2.api.serializer.*;
import net.fabricmc.loader.api.*;
import net.minecraft.util.*;

public class SPWorldsNavConfig {
    public static ConfigClassHandler<SPWorldsNavConfig> CONFIG_CLASS_HANDLER = ConfigClassHandler.createBuilder(SPWorldsNavConfig.class)
            .id(new Identifier("spwn", "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("spwn-config.json"))
                    .setJson5(true)
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .build()
            ).build();

    @SerialEntry
    public AlignEnum align = AlignEnum.CENTER;

    @SerialEntry
    public boolean showInOverworld = false;

    @SerialEntry
    public boolean showSpawn = false;

    @SerialEntry
    public boolean showInEnd = false;

    public static SPWorldsNavConfig getConfig() {
        return CONFIG_CLASS_HANDLER.instance();
    }

    public static void load() {
        CONFIG_CLASS_HANDLER.load();
    }
}
