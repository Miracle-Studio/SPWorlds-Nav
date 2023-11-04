package ua.mei.spwn.client;

import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.*;
import net.fabricmc.fabric.api.client.keybinding.v1.*;
import net.minecraft.client.*;
import net.minecraft.client.option.*;
import net.minecraft.client.util.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import org.lwjgl.glfw.*;
import org.slf4j.*;
import ua.mei.spwn.client.api.types.*;
import ua.mei.spwn.client.hud.*;
import ua.mei.spwn.client.ui.*;
import ua.mei.spwn.config.*;
import ua.mei.spwn.util.*;

import java.util.concurrent.*;
import java.util.function.*;

public class SPWorldsNavClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("spwn");
    public static City selectedCity;
    public static final AsyncTasksService asyncTasksService = new AsyncTasksService(Executors.newCachedThreadPool());
    private static KeyBinding citiesGuiKeyBinding;

    @Override
    public void onInitializeClient() {
        SPWorldsNavConfig.load();
        Hud.add(new Identifier("spwn", "navigationHud"), NavigationHud::new);

        citiesGuiKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.spwn.open_navigator_screen", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, "SPWorlds Nav"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            try {
                asyncTasksService.updateTasks();
            } catch (Exception e) {
                e.printStackTrace();
            }

            asyncTasksService.removeDone();
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (citiesGuiKeyBinding.wasPressed()) {
                if (MinecraftClient.getInstance().getCurrentServerEntry() != null) {
                    if (MinecraftClient.getInstance().getCurrentServerEntry().address.equals("sp.spworlds.ru")) {
                        MinecraftClient.getInstance().setScreen(new NavigatorScreen("sp"));
                    } else if (MinecraftClient.getInstance().getCurrentServerEntry().address.equals("spm.spworlds.ru")) {
                        MinecraftClient.getInstance().setScreen(new NavigatorScreen("spm"));
                    } else {
                        MessageScreen.openMessage(Text.translatable("gui.spwn.title.error"), Text.translatable("gui.spwn.description.join_server"));
                    }
                } else {
                    MessageScreen.openMessage(Text.translatable("gui.spwn.title.error"), Text.translatable("gui.spwn.description.join_server"));
                }
            }
        });
    }
}
