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

public class BigAxeBlade {

    @SubscribeEvent
    public void init(LoadEvent.InitEvent event) {
        String name = "flammpfeil.slashblade.named.big_axe";
        ItemStack customblade = new ItemStack(SlashBlade.bladeNamed, 1, 0);
        NBTTagCompound tag = new NBTTagCompound();
        customblade.setTagCompound(tag);
        ItemSlashBladeNamed.CurrentItemName.set(tag, name);
        ItemSlashBladeNamed.CustomMaxDamage.set(tag, Integer.valueOf(-1));
        ItemSlashBlade.AttackAmplifier.set(tag, Float.valueOf(0.0F));
        ItemSlashBladeNamed.IsDefaultBewitched.set(tag, Boolean.valueOf(true));
        ItemSlashBlade.BaseAttackModifier.set(tag, Float.valueOf(50.0F));
        ItemSlashBlade.KillCount.set(tag, 3000);
        ItemSlashBlade.ProudSoul.set(tag, 3000);
        ItemSlashBlade.SpecialAttackType.set(tag, 250);
        ItemSlashBlade.IsNoScabbard.set(tag, true);
        ItemSlashBlade.TextureName.set(tag, "named/big_axe/texture");
        ItemSlashBlade.ModelName.set(tag, "named/big_axe/model");
        ItemSlashBlade.StandbyRenderType.set(tag, 2);
        SlashBlade.registerCustomItemStack(name, customblade);
        ItemSlashBladeNamed.NamedBlades.add(name);
        tag.setString("RepairOreDicMaterial", "ingotIron");
        ItemSlashBlade.SummonedSwordColor.set(tag, 9922895);
		customblade.addEnchantment(Enchantments.SHARPNESS, 36);
		customblade.addEnchantment(Enchantments.BLAST_PROTECTION, 3);
		customblade.addEnchantment(Enchantments.PUNCH, 5);
		customblade.addEnchantment(Enchantments.POWER, 8);

        ItemStack custombladeRequired = new ItemStack(SlashBlade.bladeWhiteSheath);
        NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(custombladeRequired);
        ItemSlashBlade.RepairCount.set(reqTag, Integer.valueOf(15));
        ItemStack xiao = SlashBlade.findItemStack("flammpfeil.slashblade", name, 1);
		SlashBlade.findItemStack("flammpfeil.slashblade", "sphere_bladesoul", 1);
        ItemStack itemSphereBladeSoul2 = SlashBlade.findItemStack(SlashBlade.modid, SlashBlade.SphereBladeSoulStr, 1);
        /*SlashBlade.addRecipe(name, new RecipeAwakeBlade(xiao, custombladeRequired, new Object[]{"121", "232", "121",
                '1', itemSphereBladeSoul2, '2', Blocks.anvil, '3', Items.stone_axe}));*/
		SlashBlade.addRecipe(name,
				new RecipeAwakeBlade(new ResourceLocation(SlashBlade.modid,"xiao"),
						xiao,
						custombladeRequired,
						"121", "232", "121",
						'1', itemSphereBladeSoul2, '2', Blocks.ANVIL, '3', Items.STONE_AXE));
    }
}
