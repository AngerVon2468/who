package wiiu.mavity.who.entity.entitytype;

import net.minecraft.entity.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TimeRotorEntity extends Entity {

    public TimeRotorEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();

        Vec3d pos = this.getBlockPos().toCenterPos();
        this.updatePosition(pos.x, this.getY(), pos.z);
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