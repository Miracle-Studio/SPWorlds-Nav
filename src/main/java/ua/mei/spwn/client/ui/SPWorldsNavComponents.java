package ua.mei.spwn.client.ui;

import com.mojang.blaze3d.systems.*;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.util.*;
import net.minecraft.util.*;
import ua.mei.spwn.config.*;

public interface SPWorldsNavComponents {
    Surface BEDROCK_PANEL = (context, component) -> {
        SPWorldsNavConfig config = SPWorldsNavConfig.config();

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(config.navbarColor.getRed() / 255f, config.navbarColor.getGreen() / 255f, config.navbarColor.getBlue() / 255f, config.navbarColor.getAlpha() / 255f);
        NinePatchTexture.draw(new Identifier("spwn", "panel/bedrock"), context, component);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.disableBlend();
    };
}
