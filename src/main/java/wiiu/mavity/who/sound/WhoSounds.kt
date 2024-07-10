package wiiu.mavity.who.sound

import net.minecraft.registry.*
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

import wiiu.mavity.who.Who

object WhoSounds {

    @JvmField val TARDIS_DEMAT = register("tardis_demat")

    @JvmField val TARDIS_REMAT = register("tardis_remat")

    @JvmStatic
    fun register(id: String): SoundEvent {
        val sound = SoundEvent.of(Identifier(Who.MOD_ID, id))
        return Registry.register(Registries.SOUND_EVENT, Identifier(Who.MOD_ID, id), sound)
    }

    @JvmStatic
    fun registerWhoSounds() {
        Who.LOGGER.info(Who.NAME + " has registered its items.")
    }
}