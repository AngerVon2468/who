package wiiu.mavity.who.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import org.jetbrains.annotations.NotNull;

public class NbtUtil {

    public static void writeCustomNbt(@NotNull ItemStack stack, String key, int value) {

        NbtCompound nbt = new NbtCompound();
        nbt.putInt(key, value);
        stack.setNbt(nbt);

    }
}