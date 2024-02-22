package ua.mei.spwn.client.ui;

import io.wispforest.owo.ui.component.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import net.minecraft.text.*;
import net.minecraft.world.*;
import ua.mei.spwn.client.*;
import ua.mei.spwn.client.Thread;

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
            double x = player.getX();
            double z = player.getZ();

            Thread thread = SPMath.thread(x, z, true);

            if (player.clientWorld.getRegistryKey() != World.NETHER) {
                x /= 8;
                z /= 8;
            }

            if (thread != null) {
                this.text = thread.getText(x, z);
            }
        }

        this.notifyParentIfMounted();
    }
}
