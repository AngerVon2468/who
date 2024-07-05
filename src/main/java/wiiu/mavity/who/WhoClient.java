package wiiu.mavity.who;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.*;

import wiiu.mavity.who.entity.WhoEntities;
import wiiu.mavity.who.entity.model.TardisEntityModel;
import wiiu.mavity.who.entity.renderer.TardisEntityRenderer;

public class WhoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        Who.LOGGER.info("WhoClient has started up!");
        WhoClient.registerEntityModelLayers();
        WhoClient.registerEntityRenderers();
    }

    public static void registerEntityModelLayers() {

        EntityModelLayerRegistry.registerModelLayer(TardisEntityModel.LAYER_LOCATION, TardisEntityModel::getTexturedModelData);
    }

    public static void registerEntityRenderers() {

        EntityRendererRegistry.register(WhoEntities.TARDIS, TardisEntityRenderer::new);
    }
}