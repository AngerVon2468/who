package wiiu.mavity.who.itemgroup

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup

import net.minecraft.item.*
import net.minecraft.registry.*
import net.minecraft.text.Text
import net.minecraft.util.Identifier

import wiiu.mavity.who.Who
import wiiu.mavity.who.block.WhoBlocks
import wiiu.mavity.who.item.WhoItems

object WhoItemGroups {

    @Suppress("unused")
    val ALL: ItemGroup = Registry.register(
        Registries.ITEM_GROUP, Identifier(Who.MOD_ID, "who"),
        FabricItemGroup.builder().displayName(Text.translatable("tab.who.who"))
            .icon { ItemStack(WhoItems.TARDIS) }.entries { _, entries ->
                entries.add(WhoItems.TARDIS)
                entries.add(WhoItems.SONIC)
                entries.add(WhoItems.DALEK_GUNSTICK)
                entries.add(WhoItems.SHORT_TIME_ROTOR)
                entries.add(WhoBlocks.TIME_ROTOR)
                entries.add(WhoBlocks.FLIGHT_LEVER)
                entries.add(WhoBlocks.ORANGE_LIGHT)
                entries.add(WhoBlocks.TARDIS_STAND)
                entries.add(WhoBlocks.TARDIS_CATWALK)
                entries.add(WhoBlocks.TARDIS_CATWALK_PLATED)
                entries.add(WhoBlocks.TARDIS_CATWALK_RAILS)
                entries.add(WhoBlocks.TARDIS_CATWALK_RAILS_CORNER)
                entries.add(WhoBlocks.TARDIS_CATWALK_STEPS)
                entries.add(WhoBlocks.TARDIS_CATWALK_STEPS_CORNER)
            }.build()
    )

    @JvmStatic
    fun registerWhoItemGroups() {
        Who.LOGGER.info(Who.NAME + " has registered its itemgroups.")
    }
}