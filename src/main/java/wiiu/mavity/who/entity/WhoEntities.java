package wiiu.mavity.who.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.entity.entitytype.TardisEntity;

public class WhoEntities {

    public static final EntityType<TardisEntity> TARDIS = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Who.MOD_ID, "tardis"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, TardisEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f))
                    .fireImmune()
                    .build()
    );

    public static void registerWhoEntities() {
        Who.LOGGER.info(Who.NAME + " has registered its entities.");
    }
}