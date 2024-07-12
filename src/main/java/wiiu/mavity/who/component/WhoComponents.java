package wiiu.mavity.who.component;

import dev.onyxstudios.cca.api.v3.component.*;
import dev.onyxstudios.cca.api.v3.world.*;

import net.minecraft.util.Identifier;

import org.jetbrains.annotations.NotNull;

public final class WhoComponents implements WorldComponentInitializer {

    public static final ComponentKey<WorldTardisId> TARDIS_IDS =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("who:tardisIds"), WorldTardisId.class);

    @Override
    public void registerWorldComponentFactories(@NotNull WorldComponentFactoryRegistry registry) {
        registry.register(TARDIS_IDS, world -> new WorldTardisId(0, world));
    }
}