package ua.mei.spwn.client.hud;

import io.wispforest.owo.ui.component.*;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import net.minecraft.text.*;
import net.minecraft.world.*;
import ua.mei.spwn.client.*;
import ua.mei.spwn.client.api.types.*;
import ua.mei.spwn.util.*;
import ua.mei.spwn.util.Thread;

public class NavigatorLabel extends LabelComponent {
    public NavigatorLabel() {
        super(Text.empty());
        this.shadow(true);
    }

    @Override
    public void update(float delta, int mouseX, int mouseY) {
        super.update(delta, mouseX, mouseY);

        City city = SPWorldsNavClient.selectedCity;

        if (city != null) {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;

            if (player != null) {
                if (player.clientWorld.getRegistryKey() == World.NETHER) {
                    double x = player.getX();
                    double z = player.getZ();

                    Thread thread = SPMath.thread(city.x(), city.z());

                    if (thread == Thread.RED) {

                    } else if (thread == Thread.GREEN) {

                    } else if (thread == Thread.YELLOW) {

                    } else {

                    }
                } else {
                    this.text = Text.literal("Чтобы навигатор заработал зайдите в ад");
                }
            }
        }

        this.notifyParentIfMounted();
    }

    @Override
    public void draw(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta) {
        if (SPWorldsNavClient.selectedCity != null) {
            super.draw(context, mouseX, mouseY, partialTicks, delta);
        }
    }
}
