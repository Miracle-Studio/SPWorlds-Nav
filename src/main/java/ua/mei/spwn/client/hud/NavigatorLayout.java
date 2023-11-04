package ua.mei.spwn.client.hud;

import io.wispforest.owo.ui.container.*;
import io.wispforest.owo.ui.core.*;
import ua.mei.spwn.client.*;

public class NavigatorLayout extends FlowLayout {
    protected NavigatorLayout() {
        super(Sizing.content(), Sizing.content(), Algorithm.VERTICAL);

        this.children.add(Containers.horizontalFlow(Sizing.content(), Sizing.content())
                .child(new NavigatorLabel().margins(Insets.of(2, 2, 6, 6)))
                .surface(SPWorldsSurface.BEDROCK_PANEL)
        );
    }

    @Override
    public void draw(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta) {
        if (SPWorldsNavClient.selectedCity != null) {
            super.draw(context, mouseX, mouseY, partialTicks, delta);
        }
    }
}
