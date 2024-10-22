package wiiu.mavity.who;

import net.fabricmc.api.ModInitializer;

import org.slf4j.*;

import wiiu.mavity.who.entity.WhoEntities;
import wiiu.mavity.who.item.WhoItems;
import wiiu.mavity.who.block.*;
import wiiu.mavity.who.itemgroup.WhoItemGroups;
import wiiu.mavity.who.sound.WhoSounds;
import wiiu.mavity.who.util.WhoCommands;

public class Who implements ModInitializer {

	public static final String NAME = "Who?";

	public static final String MOD_ID = "who";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	@Override
	public void onInitialize() {

		Who.LOGGER.info(Who.NAME + " has started up!");

		// Registering classes.
		WhoEntities.registerWhoEntities();
		WhoItems.registerWhoItems();
		WhoItemGroups.registerWhoItemGroups();
		WhoBlocks.registerWhoBlocks();
		WhoBlockEntities.registerWhoBlockEntities();
		WhoSounds.registerWhoSounds();
		WhoCommands.getTardisIds();
	}
}