package wiiu.mavity.who.entity.entitytype;

import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class TimeRotorEntity extends Entity {

    public TimeRotorEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void initDataTracker() {
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
    }
}