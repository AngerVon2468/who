package wiiu.mavity.who.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings

import net.minecraft.block.Block
import net.minecraft.item.*
import net.minecraft.registry.*
import net.minecraft.util.Identifier

import wiiu.mavity.who.Who
import wiiu.mavity.who.block.blocktype.SonicModifierBlock

object WhoBlocks {

    private fun registerBlock(name: String, block: Block): Block? {
        registerBlockItem(name, block)
        return Registry.register(Registries.BLOCK, Identifier(Who.MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String, block: Block): Item {
        return Registry.register(Registries.ITEM, Identifier(Who.MOD_ID, name),
            BlockItem(block, FabricItemSettings().maxCount(64))
        )
    }

    @JvmField
    val SONIC_MODIFIER = registerBlock("sonic_modifier",
        SonicModifierBlock(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmStatic
    fun registerWhoBlocks() {
        Who.LOGGER.info(Who.NAME + " has registered its blocks.")
    }
}