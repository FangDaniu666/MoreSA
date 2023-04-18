package com.daniu.moresa.blade;

import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class XiaoBlade {
  @SubscribeEvent
  public void init(LoadEvent.InitEvent event) {
    String name = "flammpfeil.slashblade.named.xiao";
    ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
    NBTTagCompound tag = new NBTTagCompound();
    customblade.setTagCompound(tag);
    ItemSlashBladeNamed.CurrentItemName.set(tag, name);
    ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(450));
    ItemSlashBlade.AttackAmplifier.set(tag, Float.valueOf(5.0F));
    ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
    ItemSlashBlade.BaseAttackModifier.set(tag, Float.valueOf(12.0F));
    ItemSlashBlade.KillCount.set(tag, Integer.valueOf(2500));
    ItemSlashBlade.ProudSoul.set(tag, Integer.valueOf(2500));
    ItemSlashBlade.SpecialAttackType.set(tag, Integer.valueOf(252));
    ItemSlashBlade.TextureName.set(tag, "named/xiao/texture");
    ItemSlashBlade.ModelName.set(tag, "named/xiao/model");
    ItemSlashBlade.StandbyRenderType.set(tag, Integer.valueOf(2));
    SlashBlade.registerCustomItemStack(name, customblade);
    ItemSlashBladeNamed.NamedBlades.add(name);
    tag.setString("RepairOreDicMaterial", "ingotIron");
    ItemSlashBlade.SummonedSwordColor.set(tag, Integer.valueOf(65280));
    customblade.addEnchantment(Enchantments.POWER, 8);
    customblade.addEnchantment(Enchantments.THORNS, 2);
    customblade.addEnchantment(Enchantments.FEATHER_FALLING, 2);

    ItemStack custombladeRequired = new ItemStack(SlashBlade.bladeWhiteSheath);
    NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeRequired);
    ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(15));
    ItemStack xiao = SlashBlade.findItemStack("flammpfeil.slashblade", name, 1);
    ItemStack itemSphereBladeSoul2 = SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
    /*SlashBlade.addRecipe(name, new RecipeAwakeBlade(xiao, custombladeRequired, new Object[] {"121", "232", "121", Character.valueOf('1'),
    itemSphereBladeSoul2, Character.valueOf('2'), Blocks.DRAGON_EGG, Character.valueOf('3'), Blocks.DIAMOND_BLOCK }));*/
    SlashBlade.addRecipe(name,
            new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"xiao"),
                    xiao,
                    custombladeRequired,
                    "121", "232", "121",
                    '1', itemSphereBladeSoul2, '2', Blocks.DRAGON_EGG, '3', Blocks.DIAMOND_BLOCK));
  }
}
