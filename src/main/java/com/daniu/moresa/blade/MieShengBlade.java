package com.daniu.moresa.blade;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MieShengBlade {
    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        String name = "flammpfeil.slashblade.named.miesheng";
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(650));
        ItemSlashBlade.AttackAmplifier.set(tag, Float.valueOf(10.0F));
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
        ItemSlashBlade.BaseAttackModifier.set(tag, Float.valueOf(25.0F));
        ItemSlashBlade.KillCount.set(tag, Integer.valueOf(3500));
        ItemSlashBlade.ProudSoul.set(tag, Integer.valueOf(3500));
        ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(253));
        ItemSlashBlade.TextureName.set(tag, "named/miesheng/texture");
        ItemSlashBlade.ModelName.set(tag, "named/miesheng/model");
        ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
        SlashBlade.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add(name);
        tag.setString("RepairOreDicMaterial", "ingotIron");
        ItemSlashBlade.SummonedSwordColor.set(tag, Integer.valueOf(14381275));
        ItemSlashBlade.SummonedSwordColor.set(tag, Integer.valueOf(13467442));
        customblade.addEnchantment(Enchantments.POWER, 10);
        customblade.addEnchantment(Enchantments.THORNS, 4);
        customblade.addEnchantment(Enchantments.FIRE_PROTECTION, 2);
        customblade.addEnchantment(Enchantments.FLAME, 6);
        customblade.addEnchantment(Enchantments.FEATHER_FALLING, 5);

        ItemStack custombladeRequired = new ItemStack(SlashBlade.bladeWhiteSheath);
        NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeRequired);
        ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(15));
    }
}
