package ua.mei.spwn.config;

import com.terraformersmc.modmenu.api.*;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.client.gui.screen.*;
import net.minecraft.text.*;

public class ModMenuIntegration implements ModMenuApi {
    public static Screen createConfigScreen(Screen parent) {
        SPWorldsNavConfig config = SPWorldsNavConfig.getConfig();

        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("SPWorlds Nav"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("config.spwn.category.main"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("config.spwn.group.visual"))
                                .option(Option.createBuilder(AlignEnum.class)
                                        .name(Text.translatable("config.spwn.visual.align.name"))
                                        .binding(config.align, () -> config.align, value -> config.align = value)
                                        .controller(enumOption -> EnumControllerBuilder.create(enumOption).enumClass(AlignEnum.class).valueFormatter(value -> Text.literal(value.name())))
                                        .build()
                                )
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.translatable("config.spwn.visual.show_in_overworld.name"))
                                        .binding(config.showInOverworld, () -> config.showInOverworld, value -> config.showInOverworld = value)
                                        .controller(booleanOption -> BooleanControllerBuilder.create(booleanOption).valueFormatter(value -> value ? Text.translatable("config.spwn.label.yes") : Text.translatable("config.spwn.label.no")).coloured(true))
                                        .build()
                                )
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.translatable("config.spwn.visual.show_spawn.name"))
                                        .binding(config.showSpawn, () -> config.showSpawn, value -> config.showSpawn = value)
                                        .controller(booleanOption -> BooleanControllerBuilder.create(booleanOption).valueFormatter(value -> value ? Text.translatable("config.spwn.label.yes") : Text.translatable("config.spwn.label.no")).coloured(true))
                                        .build()
                                )
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.translatable("config.spwn.visual.show_in_end.name"))
                                        .binding(config.showInEnd, () -> config.showInEnd, value -> config.showInEnd = value)
                                        .controller(booleanOption -> BooleanControllerBuilder.create(booleanOption).valueFormatter(value -> value ? Text.translatable("config.spwn.label.yes") : Text.translatable("config.spwn.label.no")).coloured(true))
                                        .build()
                                )
                                .build()
                        ).build())
                .save(SPWorldsNavConfig.CONFIG_INSTANCE::save)
                .build()
                .generateScreen(parent);
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ModMenuIntegration::createConfigScreen;
    }
}
