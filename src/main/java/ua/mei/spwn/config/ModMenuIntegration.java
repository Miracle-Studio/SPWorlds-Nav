package ua.mei.spwn.config;

import com.terraformersmc.modmenu.api.*;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return SPWorldsNavConfig::generateScreen;
    }
}
