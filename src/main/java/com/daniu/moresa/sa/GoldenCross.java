package com.daniu.moresa.sa;
import mods.flammpfeil.slashblade.entity.EntitySakuraEndManager;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
public class GoldenCross extends SpecialAttackBase {
    public String toString() {
        return "GoldenCross";
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.world;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);

        if(!world.isRemote){

            final int cost = -20;
            if(!ItemSlashBlade.ProudSoul.tryAdd(tag,cost,false)){
                ItemSlashBlade.damageItem(stack, 10, player);
            }

            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();

            EntitySakuraEndManager entityDA = new EntitySakuraEndManager(world, player);
            if (entityDA != null) {
                world.spawnEntity(entityDA);
            }
        }

        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashEdge);
    }

}
