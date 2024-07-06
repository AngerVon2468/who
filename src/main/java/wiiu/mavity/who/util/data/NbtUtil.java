package wiiu.mavity.who.util.data;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import org.jetbrains.annotations.NotNull;

public class NbtUtil {

    public static void setNbt(@NotNull ItemStack stack, String key, int value) {

        NbtCompound nbt = new NbtCompound();
        nbt.putInt(key, value);
        stack.setNbt(nbt);

    }

    public static void writeNbt(@NotNull ItemStack stack, String key, int value) {

        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putInt(key, value);
        stack.writeNbt(nbt);

    }
}