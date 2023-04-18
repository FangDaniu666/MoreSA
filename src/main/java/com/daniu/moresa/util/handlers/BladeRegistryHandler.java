package com.daniu.moresa.util.handlers;

import com.daniu.moresa.blade.BigAxeBlade;
import com.daniu.moresa.blade.CheeseBlade;
import com.daniu.moresa.blade.XiaoBlade;
import mods.flammpfeil.slashblade.SlashBlade;
public class BladeRegistryHandler {
    public static void onBladeRegistry() {
        //Blade
        SlashBlade.InitEventBus.register(new CheeseBlade());
        SlashBlade.InitEventBus.register(new BigAxeBlade());
        SlashBlade.InitEventBus.register(new XiaoBlade());
        //SlashBlade.InitEventBus.register(new MieShengBlade());
    }
}
