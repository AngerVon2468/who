package wiiu.mavity.who.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import org.jetbrains.annotations.NotNull;

public class WorldTardisId implements ITardisComponent, AutoSyncedComponent {

    public int tardisIds;

    public final World world;

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public void setTardisIds(int value) {
        this.tardisIds = value;
        WhoComponents.TARDIS_IDS.sync(this.world);
    }

    @Override
    public int getTardisIds() {
        return this.tardisIds;
    }

    @Override
    public void readFromNbt(@NotNull NbtCompound nbt) {
        this.tardisIds = nbt.getInt("tardisIds");
    }

    @Override
    public void writeToNbt(@NotNull NbtCompound nbt) {
        nbt.putInt("tardisIds", this.tardisIds);
    }

    public WorldTardisId(int tardisIds, World world) {

        this.tardisIds = tardisIds;
        this.world = world;
    }
}