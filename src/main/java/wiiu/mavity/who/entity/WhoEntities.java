package wiiu.mavity.who.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.*;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import wiiu.mavity.who.Who;
import wiiu.mavity.who.entity.entitytype.*;

public class WhoEntities {

    public static final EntityType<TardisEntity> TARDIS = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Who.MOD_ID, "tardis"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, TardisEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f,3.2f))
                    .fireImmune()
                    .build()
    );

    public static final EntityType<DalekBeamEntity> DALEK_BEAM = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Who.MOD_ID, "dalek_beam"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, DalekBeamEntity::new)
                    .dimensions(EntityDimensions.fixed(0.45f, 0.2f))
                    .fireImmune()
                    .build()
    );

    public static final EntityType<TimeRotorEntity> TIME_ROTOR = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(Who.MOD_ID, "time_rotor"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, TimeRotorEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 0.8f))
                    .fireImmune()
                    .build()
    );

    public static void registerWhoEntities() {
        Who.LOGGER.info(Who.NAME + " has registered its entities.");
    }
}