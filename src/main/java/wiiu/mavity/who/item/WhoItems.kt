package wiiu.mavity.who.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings

import net.minecraft.item.Item
import net.minecraft.registry.*
import net.minecraft.util.Identifier

import wiiu.mavity.who.Who
import wiiu.mavity.who.item.itemtype.*

object WhoItems {

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(Who.MOD_ID, name), item)
    }

    @JvmField
    val TARDIS = registerItem("tardis", TardisItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val SONIC = registerItem("sonic", SonicItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmField
    val DALEK_GUNSTICK = registerItem("dalek_gunstick", DalekGunstickItem(FabricItemSettings().maxCount(1).maxDamage(500)))

    @JvmField
    val SHORT_TIME_ROTOR = registerItem("short_time_rotor", ShortTimeRotorItem(FabricItemSettings().maxCount(1).maxDamage(0)))

    @JvmStatic
    fun registerWhoItems() {
        Who.LOGGER.info(Who.NAME + " has registered its items.")
    }
}