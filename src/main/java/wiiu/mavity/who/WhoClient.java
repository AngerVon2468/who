package wiiu.mavity.who;

import net.fabricmc.api.ClientModInitializer;

public class WhoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        Who.LOGGER.info("Hello Fabric world!");
    }
}