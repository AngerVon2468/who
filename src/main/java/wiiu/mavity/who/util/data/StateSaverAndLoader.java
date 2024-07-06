package wiiu.mavity.who.util.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.*;

import org.jetbrains.annotations.NotNull;

import wiiu.mavity.who.Who;

public class StateSaverAndLoader extends PersistentState {

    public Integer totalDirtBlocksBroken = 0;

    @Override
    public NbtCompound writeNbt(@NotNull NbtCompound nbt) {
        nbt.putInt("totalDirtBlocksBroken", totalDirtBlocksBroken);
        return nbt;
    }

    public static StateSaverAndLoader createFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        StateSaverAndLoader state = new StateSaverAndLoader();
        state.totalDirtBlocksBroken = tag.getInt("totalDirtBlocksBroken");
        return state;
    }

    /*
    private static Type<StateSaverAndLoader> TYPE = new Type<>(
            StateSaverAndLoader::new, // If there's no 'StateSaverAndLoader' yet create one
            StateSaverAndLoader::createFromNbt, // If there is a 'StateSaverAndLoader' NBT, parse it with 'createFromNbt'
            null // Supposed to be an 'DataFixTypes' enum, but we can just pass null
    );
    */

    /*
    public static StateSaverAndLoader getServerState(MinecraftServer server) {
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();
        StateSaverAndLoader state = persistentStateManager.getOrCreate(TYPE, Who.MOD_ID);
        state.markDirty();

        return state;
    }
    */
}