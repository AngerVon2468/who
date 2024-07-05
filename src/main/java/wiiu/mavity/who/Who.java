package wiiu.mavity.who;

import net.fabricmc.api.ModInitializer;

import org.slf4j.*;

public class Who implements ModInitializer {

	public static final String NAME = "Who";

	public static final String MOD_ID = "who";

    public static final Logger LOGGER = LoggerFactory.getLogger(Who.MOD_ID);

	@Override
	public void onInitialize() {

		Who.LOGGER.info("Hello Fabric world!");
	}
}