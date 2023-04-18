package com.daniu.moresa.blade;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CheeseBlade {

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        String name = "flammpfeil.slashblade.named.cheese";
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(350));
        ItemSlashBlade.AttackAmplifier.set(tag, Float.valueOf(3.0F));
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
        ItemSlashBlade.BaseAttackModifier.set(tag, Float.valueOf(8.0F));
        ItemSlashBlade.KillCount.set(tag, 1500);
        ItemSlashBlade.ProudSoul.set(tag, 1500);
        ItemSlashBlade.SpecialAttackType.set(tag, 11);
        ItemSlashBlade.TextureName.set(tag, "named/cheese/texture");
        ItemSlashBlade.ModelName.set(tag, "named/cheese/model");
        ItemSlashBlade.StandbyRenderType.set(tag, 3);
        SlashBlade.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add(name);
        tag.setString("RepairOreDicMaterial", "ingotIron");
        ItemSlashBlade.SummonedSwordColor.set(tag, 16776960);
        customblade.addEnchantment(Enchantments.POWER, 5);
        customblade.addEnchantment(Enchantments.PUNCH, 2);
        customblade.addEnchantment(Enchantments.THORNS, 1);
        customblade.addEnchantment(Enchantments.LOOTING, 6);
        customblade.addEnchantment(Enchantments.FEATHER_FALLING, 4);

        ItemStack cheese = SlashBlade.findItemStack("flammpfeil.slashblade", name, 1);
        ItemStack custombladeRequired = new ItemStack(SlashBlade.bladeWhiteSheath);
        NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeRequired);
        ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(15));
        ItemStack itemSphereBladeSoul2 = SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.SphereBladeSoulStr, 1);
        /*SlashBlade.addRecipe(name, new RecipeAwakeBlade(cheese, custombladeRequired, new Object[]{"121", "232", "121",
                '1', itemSphereBladeSoul2, '2', Blocks.GOLD_BLOCK, '3', Items.NETHER_STAR}));*/
        SlashBlade.addRecipe(name,
                new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"cheese"),
                        cheese,
                        custombladeRequired,
                        "121", "232", "121",
                        '1', itemSphereBladeSoul2, '2', Blocks.GOLD_BLOCK, '3', Items.NETHER_STAR));
    }
}
