package wiiu.mavity.who;

import net.fabricmc.api.ModInitializer;

import org.slf4j.*;

import wiiu.mavity.who.entity.WhoEntities;

public class Who implements ModInitializer {

	public static final String NAME = "Who";

	public static final String MOD_ID = "who";

    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	@Override
	public void onInitialize() {

		Who.LOGGER.info("Who has started up!");
		WhoEntities.registerWhoEntities();
	}
}