package wiiu.mavity.who.resourcepack

import net.fabricmc.fabric.api.resource.*
import net.fabricmc.loader.api.FabricLoader

import net.minecraft.text.Text
import net.minecraft.util.Identifier

import wiiu.mavity.who.Who

/**
 * Loads resourcepacks from the folder ```resources/resourcepacks``` folder.
 *
 * Add new packs to the list by duplicating the ```packName```, ```identifier```. and ```success``` values, then changing the names and locations as needed.
 */
object ModResourcePack {

    @JvmStatic
    fun registerResourcePack() {
        val packName = "who_extras"
        val identifier = Identifier(Who.MOD_ID, packName)
        val container = FabricLoader.getInstance().getModContainer(Who.MOD_ID).orElseThrow {
            IllegalStateException("Mod container not found")
        }

        val success = ResourceManagerHelper.registerBuiltinResourcePack(
            identifier,
            container,
            Text.translatable("resourcepack.${Who.MOD_ID}.$packName"),
            ResourcePackActivationType.DEFAULT_ENABLED
        )

        if (success) {
            Who.LOGGER.info("Registered resource pack: $packName")
        } else {
            Who.LOGGER.error("Failed to register resource pack: $packName")
        }
    }
}