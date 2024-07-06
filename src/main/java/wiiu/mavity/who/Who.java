package wiiu.mavity.who;

import net.fabricmc.api.ModInitializer;

import org.slf4j.*;

import wiiu.mavity.who.entity.WhoEntities;
import wiiu.mavity.who.item.WhoItems;

// TODO: Davros chair && || Dalek cosplay of some sort
public class Who implements ModInitializer {

	public static final String NAME = "Who";

	public static final String MOD_ID = "who";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	@Override
	public void onInitialize() {

		Who.LOGGER.info("Who has started up!");

		// Registering classes.
		WhoEntities.registerWhoEntities();
		WhoItems.registerWhoItems();
		/*
		FabricDefaultAttributeRegistry.register(WhoEntities.TARDIS, TardisEntity.createLivingAttributes());
		*/
	}
}