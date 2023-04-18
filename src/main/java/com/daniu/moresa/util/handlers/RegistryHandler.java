package com.daniu.moresa.util.handlers;

import com.daniu.moresa.init.ModItems;
import com.daniu.moresa.util.IHasModel;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        Item[] items = ModItems.ITEMS.toArray(new Item[0]);
        event.getRegistry().registerAll(items);
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        /*for (Item item : ModItems.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel) item).registerModels();
            }
        }*/
        ModItems.ITEMS.stream()
                .filter(item -> item instanceof IHasModel)
                .map(item -> (IHasModel) item)
                .forEach(IHasModel::registerModels);
    }
}
