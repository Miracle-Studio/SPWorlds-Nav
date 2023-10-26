package ua.mei.spwn.client.ui;

import io.wispforest.owo.ui.container.*;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import net.minecraft.world.*;
import ua.mei.spwn.config.*;

public class NavigationHud extends FlowLayout {
    private Positioning getAlign() {
        if (SPWorldsNavConfig.getConfig().align == AlignEnum.LEFT) {
            return Positioning.relative(1, 2);
        } else if (SPWorldsNavConfig.getConfig().align == AlignEnum.CENTER) {
            return Positioning.relative(50, 2);
        } else {
            return Positioning.relative(99, 2);
        }
    }

    public NavigationHud() {
        super(Sizing.content(), Sizing.content(), Algorithm.VERTICAL);
        this.surface(SPWorldsNavComponents.BEDROCK_PANEL);

        this.children.add(new DynamicLabel().margins(Insets.of(2, 2, 6, 6)));

        this.zIndex(-10000);

        this.updateLayout();
    }

    @Override
    protected void updateLayout() {
        super.updateLayout();

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            if (player.clientWorld.getRegistryKey() == World.OVERWORLD && !SPWorldsNavConfig.getConfig().showInOverworld) {
                this.positioning(Positioning.relative(50, -5));
            } else if (player.clientWorld.getRegistryKey() == World.END && !SPWorldsNavConfig.getConfig().showInEnd) {
                this.positioning(Positioning.relative(50, -5));
            } else if (MinecraftClient.getInstance().getCurrentServerEntry() == null) {
                this.positioning(Positioning.relative(50, -5));
            } else if (!MinecraftClient.getInstance().getCurrentServerEntry().address.equals("sp.spworlds.ru") && !MinecraftClient.getInstance().getCurrentServerEntry().address.equals("spm.spworlds.ru")) {
                this.positioning(Positioning.relative(50, -5));
            } else {
                this.positioning(getAlign());
            }
        }
    }
}
