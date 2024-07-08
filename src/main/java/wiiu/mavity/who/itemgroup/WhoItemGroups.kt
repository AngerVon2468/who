package wiiu.mavity.who.itemgroup

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import wiiu.mavity.who.Who
import wiiu.mavity.who.item.WhoItems

object WhoItemGroups {

    val ALL: ItemGroup = Registry.register(
        Registries.ITEM_GROUP, Identifier(Who.MOD_ID, "dungeon_utils"),
        FabricItemGroup.builder().displayName(Text.translatable("tab.dungeon_utils.dungeon_utils"))
            .icon { ItemStack(WhoItems.TARDIS) }.entries { displayContext, entries ->
                entries.add(WhoItems.TARDIS)
                entries.add(WhoItems.SONIC)
                entries.add(WhoItems.DALEK_GUNSTICK)
            }.build()
    )

    @JvmStatic
    fun registerWhoItemGroups() {
        Who.LOGGER.info(Who.NAME + " has registered its itemgroups.")
    }
}