package ua.mei.spwn.config;

import com.terraformersmc.modmenu.api.*;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.minecraft.client.gui.screen.*;
import net.minecraft.text.*;
import net.minecraft.util.*;

public class ModMenuIntegration implements ModMenuApi {
    public static Screen createConfigScreen(Screen parent) {
        SPWorldsNavConfig config = SPWorldsNavConfig.getConfig();

        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("SPWorlds Nav"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("config.spwn.category.main"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.translatable("config.spwn.group.visual"))
                                .option(Option.<AlignEnum>createBuilder()
                                        .name(Text.translatable("config.spwn.visual.align.name"))
                                        .description(OptionDescription.createBuilder().text(Text.literal("Для применения изменений нужно перезайти в Minecraft").formatted(Formatting.RED)).build())
                                        .binding(config.align, () -> config.align, value -> config.align = value)
                                        .controller(enumOption -> EnumControllerBuilder.create(enumOption).enumClass(AlignEnum.class).formatValue(value -> Text.literal(value.name())))
                                        .build()
                                )
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("config.spwn.visual.show_in_overworld.name"))
                                        .binding(config.showInOverworld, () -> config.showInOverworld, value -> config.showInOverworld = value)
                                        .controller(booleanOption -> BooleanControllerBuilder.create(booleanOption).formatValue(value -> value ? Text.translatable("config.spwn.label.yes") : Text.translatable("config.spwn.label.no")).coloured(true))
                                        .build()
                                )
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("config.spwn.visual.show_spawn.name"))
                                        .binding(config.showSpawn, () -> config.showSpawn, value -> config.showSpawn = value)
                                        .controller(booleanOption -> BooleanControllerBuilder.create(booleanOption).formatValue(value -> value ? Text.translatable("config.spwn.label.yes") : Text.translatable("config.spwn.label.no")).coloured(true))
                                        .build()
                                )
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.translatable("config.spwn.visual.show_in_end.name"))
                                        .binding(config.showInEnd, () -> config.showInEnd, value -> config.showInEnd = value)
                                        .controller(booleanOption -> BooleanControllerBuilder.create(booleanOption).formatValue(value -> value ? Text.translatable("config.spwn.label.yes") : Text.translatable("config.spwn.label.no")).coloured(true))
                                        .build()
                                )
                                .build()
                        ).build())
                .save(SPWorldsNavConfig.CONFIG_CLASS_HANDLER::save)
                .build()
                .generateScreen(parent);
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ModMenuIntegration::createConfigScreen;
    }
}
