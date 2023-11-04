package ua.mei.spwn.client.hud;

import io.wispforest.owo.ui.container.*;
import io.wispforest.owo.ui.core.*;
import ua.mei.spwn.util.*;

import java.util.*;

public class NavigationHud extends FlowLayout {
    public NavigationHud() {
        super(Sizing.content(), Sizing.content(), Algorithm.VERTICAL);

        this.children.add(Containers.verticalFlow(Sizing.content(), Sizing.content())
                .child(Containers.horizontalFlow(Sizing.content(), Sizing.content())
                        .child(new ThreadLabel().margins(Insets.of(2, 2, 6, 6)))
                        .surface(SPWorldsSurface.BEDROCK_PANEL)
                )
                .child(new NavigatorLayout())
                .gap(4)
                .horizontalAlignment(SPMath.getAlignment())
        );

        this.positioning(SPMath.getPositioning());

        this.zIndex(-10000);
    }

    @Override
    protected void drawChildren(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta, List<? extends Component> children) {
        if (SPMath.showHud()) {
            super.drawChildren(context, mouseX, mouseY, partialTicks, delta, children);
        }
    }
}
