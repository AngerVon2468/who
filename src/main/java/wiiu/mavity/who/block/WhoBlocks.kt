package wiiu.mavity.who.block

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings

import net.minecraft.block.Block
import net.minecraft.item.*
import net.minecraft.registry.*
import net.minecraft.util.Identifier

import wiiu.mavity.who.Who
import wiiu.mavity.who.block.blocktype.*

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
    val TIME_ROTOR = registerBlock("time_rotor",
        TimeRotorBlock(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val FLIGHT_LEVER = registerBlock("flight_lever",
        FlightLeverBlock(
            FabricBlockSettings.create().nonOpaque()
        )
    )

    @JvmField
    val ORANGE_LIGHT = registerBlock("orangelight",
        OrangeLightBlock(
            FabricBlockSettings.create().nonOpaque().luminance(7)
        )
    )

    @JvmStatic
    fun registerWhoBlocks() {
        Who.LOGGER.info(Who.NAME + " has registered its blocks.")
    }
}