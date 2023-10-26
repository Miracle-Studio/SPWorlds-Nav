package ua.mei.spwn.client;

import io.wispforest.owo.ui.hud.*;
import net.fabricmc.api.*;
import net.minecraft.client.item.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import ua.mei.spwn.client.ui.*;
import ua.mei.spwn.config.*;

public class SPWorldsNavClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SPWorldsNavConfig.CONFIG_INSTANCE.load();
        Hud.add(new Identifier("spwn", "navigationHud"), NavigationHud::new);
    }
}
