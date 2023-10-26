package ua.mei.spwn.config;

import dev.isxander.yacl3.config.*;
import net.fabricmc.loader.api.*;

public class SPWorldsNavConfig {
    public static ConfigInstance<SPWorldsNavConfig> CONFIG_INSTANCE = GsonConfigInstance.createBuilder(SPWorldsNavConfig.class)
            .setPath(FabricLoader.getInstance().getConfigDir().resolve("spwn-config.json"))
            .build();

    @ConfigEntry
    public AlignEnum align = AlignEnum.CENTER;

    @ConfigEntry
    public boolean showSpawn = false;

    @ConfigEntry
    public boolean showInOverworld = false;

    @ConfigEntry
    public boolean showInEnd = false;

    public static SPWorldsNavConfig getConfig() {
        return CONFIG_INSTANCE.getConfig();
    }
}
