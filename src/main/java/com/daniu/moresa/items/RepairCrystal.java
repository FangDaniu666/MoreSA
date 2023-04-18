package com.daniu.moresa.items;

import com.daniu.moresa.MoreSA;
import com.daniu.moresa.init.ModItems;
import com.daniu.moresa.util.IHasModel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class RepairCrystal extends Item implements IHasModel {
    public int cooldown = 0;

    public RepairCrystal(String registryName, CreativeTabs tab) {
        setCreativeTab(tab);
        setRegistryName(registryName);
        //setUnlocalizedName(registryName);
        setTranslationKey(registryName);
        setMaxStackSize(1);
        ModItems.ITEMS.add(this);
    }

    private static boolean repair(EntityPlayer player) {
        NonNullList<ItemStack> list = player.inventory.mainInventory;

        for (ItemStack stack : list) {
            if (!stack.isEmpty() && stack.getItem().isRepairable()) {
                if (stack.isItemDamaged()) {
                    stack.setItemDamage(stack.getItemDamage() - 1);
                }
            }
        }
        return true;
    }

    @Override
    public void registerModels() {
        MoreSA.proxy.registerItemRender(this, 0, "inventory");
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add(I18n.format("tooltip.repairgem.expanded_tooltip"));
        } else {
            tooltip.add(TextFormatting.ITALIC + I18n.format("tooltip.repairgem.hold_shift"));
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean isSelected) {
        if (world.isRemote) {
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if (--cooldown <= 0) {
                    repair(player);
                    cooldown = 30;
                }
            }
        }
    }

/*    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote) {
            if (player.experienceLevel >= 10) {
                ItemStack stack = player.getHeldItem(hand);
                player.experienceLevel =player.experienceLevel-10;
                stack.addEnchantment(null,0);
//                stack.isItemEnchanted();
//                stack.hasEffect();
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }*/

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        return super.onEntityItemUpdate(entityItem);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
