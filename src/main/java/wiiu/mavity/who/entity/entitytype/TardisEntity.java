package wiiu.mavity.who.entity.entitytype;

import net.minecraft.entity.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class TardisEntity extends Entity {

    public TardisEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public boolean canHit() {
        return !this.isRemoved();
    }

    @Override
    public boolean isCollidable() {
        return true;
    }
}