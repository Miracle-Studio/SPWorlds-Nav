package ua.mei.spwn.client.ui;

import io.wispforest.owo.ui.container.*;
import io.wispforest.owo.ui.core.*;
import ua.mei.spwn.client.*;

public class NavigationHud extends FlowLayout {
    public NavigationHud() {
        super(Sizing.content(), Sizing.content(), Algorithm.VERTICAL);
        this.surface(SPWorldsNavComponents.BEDROCK_PANEL);

        this.children.add(new DynamicLabel().margins(Insets.of(2, 2, 6, 6)));

        this.zIndex(-10000);

        if (SPMath.showHud()) {
            this.positioning(SPMath.getPositioning());
        } else {
            this.positioning(Positioning.relative(50, -10));
        }
    }

    @Override
    public void draw(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta) {
        if (SPMath.showHud()) {
            super.draw(context, mouseX, mouseY, partialTicks, delta);
        }
    }
}
