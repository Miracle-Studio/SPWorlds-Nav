package ua.mei.spwn.client.ui;

import io.wispforest.owo.ui.component.*;
import net.minecraft.client.*;
import net.minecraft.client.network.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import ua.mei.spwn.config.*;

public class DynamicLabel extends LabelComponent {
    public DynamicLabel() {
        super(Text.empty());
        this.shadow(true);
    }

    private Text calculateThread() {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            double calcX = player.getX();
            double calcZ = player.getZ();
            double x = player.getX();
            double z = player.getZ();

            if (player.clientWorld.getRegistryKey() != World.NETHER) {
                x /= 8;
                z /= 8;

                if (SPWorldsNavConfig.getConfig().showSpawn) {
                    if (player.clientWorld.getRegistryKey() == World.OVERWORLD) {
                        if (Math.abs(calcX) <= 500 && Math.abs(calcZ) <= 500) {
                            return Text.translatable("hud.spwn.spawn").formatted(Formatting.GRAY);
                        }
                    }
                }
            }

            if (calcZ > 0 && calcZ > Math.abs(calcX)) {
                return Text.translatable("hud.spwn.yellow").append(String.valueOf(Math.abs(Math.round(z)))).formatted(Formatting.GOLD);
            } else if (calcZ < 0 && Math.abs(calcZ) > Math.abs(calcX)) {
                return Text.translatable("hud.spwn.red").append(String.valueOf(Math.abs(Math.round(z)))).formatted(Formatting.RED);
            } else if (calcX > 0 && calcX > Math.abs(calcZ)) {
                return Text.translatable("hud.spwn.green").append(String.valueOf(Math.abs(Math.round(x)))).formatted(Formatting.DARK_GREEN);
            } else if (calcX < 0 && Math.abs(calcX) > Math.abs(calcZ)) {
                return Text.translatable("hud.spwn.blue").append(String.valueOf(Math.abs(Math.round(x)))).formatted(Formatting.DARK_AQUA);
            }
        }
        return null;
    }

    @Override
    public void update(float delta, int mouseX, int mouseY) {
        super.update(delta, mouseX, mouseY);

        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            Text result = calculateThread();
            if (result != null) {
                this.text = result;
            }
        }

        this.notifyParentIfMounted();
    }
}
