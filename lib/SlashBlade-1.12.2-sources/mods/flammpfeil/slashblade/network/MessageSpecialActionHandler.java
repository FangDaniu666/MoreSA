package mods.flammpfeil.slashblade.network;

import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityCaliburManager;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.event.ScheduleEntitySpawner;
import mods.flammpfeil.slashblade.util.ReflectionAccessHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.ability.AirTrick;
import mods.flammpfeil.slashblade.ability.UntouchableTime;
import mods.flammpfeil.slashblade.specialattack.ISuperSpecialAttack;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Furia on 15/05/15.
 */
public class MessageSpecialActionHandler implements IMessageHandler<MessageSpecialAction,IMessage> {

    @Override
    public IMessage onMessage(MessageSpecialAction message, MessageContext ctx) {
        if(ctx.getServerHandler() == null) return null;
        EntityPlayerMP entityPlayer = ctx.getServerHandler().player;
        if(entityPlayer == null) return null;

        entityPlayer.getServerWorld().addScheduledTask(() -> {
            ItemStack stack = entityPlayer.getHeldItem(EnumHand.MAIN_HAND);
            if(stack.isEmpty()) return ;
            if(!(stack.getItem() instanceof ItemSlashBlade)) return ;

            switch(message.mode){
                case 5:{
                    //calibur

                    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);

                    ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Calibur);

                    entityPlayer.fallDistance = 0;

                    double playerDist = 2.5;
                    ReflectionAccessHelper.setVelocity(entityPlayer,
                            -Math.sin(Math.toRadians(entityPlayer.rotationYaw)) * playerDist,
                            entityPlayer.motionY,
                            Math.cos(Math.toRadians(entityPlayer.rotationYaw)) * playerDist);

                    UntouchableTime.setUntouchableTime(entityPlayer, 6, false);

                    entityPlayer.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 1.0F, 0.2F);

                    ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();
                    blade.doSwingItem(stack, entityPlayer);

                    if (!entityPlayer.world.isRemote) {
                        EntityCaliburManager mgr = new EntityCaliburManager(entityPlayer.world, entityPlayer, false);
                        if (mgr != null) {
                            mgr.setLifeTime(14);

                            ScheduleEntitySpawner.getInstance().offer(mgr);
                        }
                    }

                    break;
                }
                case 4:{
                    //rising star

                    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);

                    ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.RisingStar);

                    ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();
                    blade.doSwingItem(stack, entityPlayer);

                    if (!entityPlayer.world.isRemote) {
                        AxisAlignedBB bb = entityPlayer.getEntityBoundingBox();
                        bb = bb.grow(4, 0, 4);

                        bb = bb.offset(entityPlayer.motionX, entityPlayer.motionY, entityPlayer.motionZ);

                        List<Entity> list = entityPlayer.world.getEntitiesInAABBexcluding(entityPlayer, bb, EntitySelectorAttackable.getInstance());

                        StylishRankManager.setNextAttackType(entityPlayer, StylishRankManager.AttackTypes.RapidSlash);

                        for (Entity curEntity : list) {
                            curEntity.hurtResistantTime = 0;
                            if (entityPlayer instanceof EntityPlayer) {
                                ItemSlashBlade itemBlade = (ItemSlashBlade) stack.getItem();
                                itemBlade.attackTargetEntity(stack, curEntity, (EntityPlayer) entityPlayer, true);
                                //((ItemSlashBlade) stack.getItem()).hitEntity(stack, (EntityLivingBase) curEntity, (EntityLivingBase) entityPlayer);
                            } else {
                                DamageSource ds = new EntityDamageSource("mob", entityPlayer);
                                curEntity.attackEntityFrom(ds, 10);
                                if (!stack.isEmpty() && curEntity instanceof EntityLivingBase)
                                    ((ItemSlashBlade) stack.getItem()).hitEntity(stack, (EntityLivingBase) curEntity, (EntityLivingBase) entityPlayer);
                            }
                        }

                    }

                    break;
                }
                case 3:
                {
                    ItemSlashBlade itemBlade = (ItemSlashBlade)stack.getItem();
                    SpecialAttackBase base = itemBlade.getSpecialAttack(stack);
                    if(base instanceof ISuperSpecialAttack){
                        ((ISuperSpecialAttack) base).doSuperSpecialAttack(stack, entityPlayer);
                    }else if(ItemSlashBlade.defaultSA instanceof ISuperSpecialAttack){
                        ((ISuperSpecialAttack) ItemSlashBlade.defaultSA).doSuperSpecialAttack(stack, entityPlayer);
                    }

                    break;
                }
                case 2:
                {
                    UntouchableTime.setUntouchableTime(entityPlayer,3,true);
                    break;
                }
                default:
                {
                    AirTrick.SummonOrDo(entityPlayer);
                }
                break;
            }
        });

        return null;
    }
}
