package wiiu.mavity.who.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.block.blocktype.SonicModifierBlockEntity;

public class WhoBlockEntities {

    public static final BlockEntityType<SonicModifierBlockEntity> SONIC_MODIFIER_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(Who.MOD_ID, "sonic_modifier_entity"),
            FabricBlockEntityTypeBuilder.create(SonicModifierBlockEntity::new, WhoBlocks.SONIC_MODIFIER).build()
    );

    public static void registerWhoBlockEntities() {
        Who.LOGGER.info(Who.NAME + " has registered its block entities.");
    }
}