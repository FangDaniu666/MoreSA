package mods.flammpfeil.slashblade.entity;

import mods.flammpfeil.slashblade.ability.AirTrick;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Furia on 2016/05/09.
 */
public class EntitySummonedSwordAirTrickMarker extends EntitySummonedSwordBase {

    public EntitySummonedSwordAirTrickMarker(World par1World) {
        super(par1World);

        setInterval(0);
    }

    public EntitySummonedSwordAirTrickMarker(World par1World, EntityLivingBase entityLiving, float AttackLevel) {
        super(par1World, entityLiving, AttackLevel);
    }

    public EntitySummonedSwordAirTrickMarker(World par1World, EntityLivingBase entityLiving, float AttackLevel, float roll) {
        super(par1World, entityLiving, AttackLevel, roll);

        {
            Vec3d eyeDir = thrower.getLookVec();

            this.setLocationAndAngles(
                    thrower.posX + eyeDir.x * 2,
                    thrower.posY + eyeDir.y * 2 + thrower.getEyeHeight(),
                    thrower.posZ + eyeDir.z * 2,
                    thrower.rotationYaw,
                    thrower.rotationPitch);

            this.setDriveVector(1.75f,true);
        }
    }

    @Override
    protected boolean onImpact(RayTraceResult mop) {
        boolean result = false;

        if (mop.entityHit != null){
            Entity target = mop.entityHit;

            if(getTargetEntityId() != 0 && target.getEntityId() != getTargetEntityId())
                return result;

            if(mop.hitInfo.equals(EntitySelectorAttackable.getInstance())){
                attackEntity(target);
                result = true;
            }
        }else{
            if(!world.getCollisionBoxes(this,this.getEntityBoundingBox()).isEmpty())
            {
                if(this.getThrower() != null && this.getThrower() instanceof EntityPlayer)
                    ((EntityPlayer)this.getThrower()).onCriticalHit(this);
                this.setDead();
                result = true;
            }
        }

        return result;
    }

    @Override
    public void mountEntity(Entity par1Entity) {
        super.mountEntity(par1Entity);

        if(!this.world.isRemote){
            if(this.getThrower() instanceof EntityPlayerMP)
                AirTrick.doAirTrick((EntityPlayerMP)this.getThrower());
        }
    }

    @Override
    public boolean doTargeting() {
        //boolean result = super.doTargeting();

        int targetid = this.getTargetEntityId();

        if(targetid != 0 && getInterval() < this.ticksExisted ){
            Entity target = world.getEntityByID(targetid);

            if(target != null){

                if(Float.isNaN(iniPitch) && thrower != null){
                    iniYaw = thrower.rotationYaw;
                    iniPitch = thrower.rotationPitch;
                }

                faceEntity(this,target, 180f, 180.0f);

                float factor = 1.0f;
                float distance = target.getDistance(this);
                if(distance < 5)
                    factor = distance / 5;

                //double vec = 1.1 * (new Vec3d(this.motionX,this.motionY,this.motionZ)).lengthVector();

                setDriveVector((float)(1.0f + 3.0f * factor), false);
            }
        }

        return true;
    }
}
