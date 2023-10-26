package ua.mei.spwn.client.ui;

import com.mojang.blaze3d.systems.*;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.util.*;
import net.minecraft.util.*;

public interface SPWorldsNavComponents {
    Surface BEDROCK_PANEL = (context, component) -> {
        RenderSystem.setShaderColor(1f, 1f, 1f, 0.5f);
        RenderSystem.enableBlend();
        NinePatchTexture.draw(new Identifier("spwn", "panel/bedrock"), context, component);
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    };
}
