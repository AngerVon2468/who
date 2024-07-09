package wiiu.mavity.who.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.block.blocktype.TimeRotorBlockEntity;

public class WhoBlockEntities {

    public static final BlockEntityType<TimeRotorBlockEntity> TIME_ROTOR_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(Who.MOD_ID, "time_rotor_entity"),
            FabricBlockEntityTypeBuilder.create(TimeRotorBlockEntity::new, WhoBlocks.TIME_ROTOR).build()
    );

    public static void registerWhoBlockEntities() {
        Who.LOGGER.info(Who.NAME + " has registered its block entities.");
    }
}