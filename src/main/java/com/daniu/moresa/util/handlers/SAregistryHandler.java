package com.daniu.moresa.util.handlers;

import com.daniu.moresa.sa.GoldenCross;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;

public class SAregistryHandler {
    public static void onSAregistry() {
        //SA
        ItemSlashBlade.specialAttacks.put(11, new GoldenCross());
    }
}
