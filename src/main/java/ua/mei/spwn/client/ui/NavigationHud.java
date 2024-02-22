package ua.mei.spwn.client.ui;

import io.wispforest.owo.ui.container.*;
import io.wispforest.owo.ui.core.*;
import ua.mei.spwn.client.*;

public class NavigationHud extends FlowLayout {
    public NavigationHud() {
        super(Sizing.content(), Sizing.content(), Algorithm.VERTICAL);

        this.surface(SPWorldsNavComponents.BEDROCK_PANEL);
        this.positioning(SPMath.getPositioning());

        this.children.add(new DynamicLabel().margins(Insets.of(2, 2, 6, 6)));
    }

    @Override
    protected void updateLayout() {
        super.updateLayout();

        this.positioning(SPMath.getPositioning());
    }

    @Override
    public void draw(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta) {
        if (SPMath.showHud()) {
            super.draw(context, mouseX, mouseY, partialTicks, delta);
        }
    }
}
