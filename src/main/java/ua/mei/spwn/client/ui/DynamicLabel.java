package ua.mei.spwn.client.ui;

import io.wispforest.owo.ui.component.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import net.minecraft.text.*;
import net.minecraft.world.*;
import ua.mei.spwn.client.*;

public class DynamicLabel extends LabelComponent {
    public DynamicLabel() {
        super(Text.empty());
        this.shadow(true);
    }

    @Override
    public void update(float delta, int mouseX, int mouseY) {
        super.update(delta, mouseX, mouseY);

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            double playerX = player.getX();
            double playerZ = player.getZ();
            double x = player.getX();
            double z = player.getZ();

            if (player.clientWorld.getRegistryKey() != World.NETHER) {
                x /= 8;
                z /= 8;
            }

            Text text = SPMath.thread(playerX, playerZ).getText(x, z);

            if (text != null) {
                this.text = text;
            }
        }

        this.notifyParentIfMounted();
    }
}
