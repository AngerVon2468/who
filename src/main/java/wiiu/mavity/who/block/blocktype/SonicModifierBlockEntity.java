package wiiu.mavity.who.block.blocktype;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.*;

import wiiu.mavity.who.block.WhoBlockEntities;

public class SonicModifierBlockEntity extends BlockEntity {

    private int number = 0;

    public SonicModifierBlockEntity(BlockPos pos, BlockState state) {
        super(WhoBlockEntities.SONIC_MODIFIER_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(@NotNull NbtCompound nbt) {
        nbt.putInt("who.sonic.value", this.number);
    }

    @Override
    public void readNbt(@NotNull NbtCompound nbt) {
        this.number = nbt.getInt("who.sonic.value");
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, SonicModifierBlockEntity blockEntity) {
    }
}