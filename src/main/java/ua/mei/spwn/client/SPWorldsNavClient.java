package ua.mei.spwn.client;

import io.wispforest.owo.ui.hud.*;
import net.fabricmc.api.*;
import net.minecraft.util.*;
import ua.mei.spwn.client.ui.*;
import ua.mei.spwn.config.*;

public class SPWorldsNavClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SPWorldsNavConfig.load();
        Hud.add(new Identifier("spwn", "navigation_hud"), NavigationHud::new);
    }
}
