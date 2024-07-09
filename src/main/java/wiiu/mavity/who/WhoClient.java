package wiiu.mavity.who;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.*;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.util.Identifier;

import wiiu.mavity.who.block.WhoBlockEntities;
import wiiu.mavity.who.block.blocktype.TimeRotorBlockEntityRenderer;
import wiiu.mavity.who.entity.WhoEntities;
import wiiu.mavity.who.entity.model.*;
import wiiu.mavity.who.entity.renderer.*;
import wiiu.mavity.who.item.WhoItems;

public class WhoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        Who.LOGGER.info("WhoClient has started up!");
        WhoClient.registerEntityModelLayers();
        WhoClient.registerEntityRenderers();
        WhoClient.registerCustomModelPredicateProviders();
    }

    public static void registerEntityModelLayers() {

        EntityModelLayerRegistry.registerModelLayer(TardisEntityModel.LAYER_LOCATION, TardisEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(DalekBeamEntityModel.LAYER_LOCATION, DalekBeamEntityModel::getTexturedModelData);
    }

    public static void registerEntityRenderers() {

        EntityRendererRegistry.register(WhoEntities.TARDIS, TardisEntityRenderer::new);
        EntityRendererRegistry.register(WhoEntities.DALEK_BEAM, DalekBeamEntityRenderer::new);
        BlockEntityRendererFactories.register(WhoBlockEntities.TIME_ROTOR_ENTITY, TimeRotorBlockEntityRenderer::new);
    }

    public static void registerCustomModelPredicateProviders() {

        ModelPredicateProviderRegistry.register(WhoItems.SONIC, new Identifier("green"), (stack, world, entity, seed) -> stack.getNbt() != null && stack.getNbt().contains("who.sonic.colour.green") ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(WhoItems.SONIC, new Identifier("purple"), (stack, world, entity, seed) -> stack.getNbt() != null && stack.getNbt().contains("who.sonic.colour.purple") ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(WhoItems.SONIC, new Identifier("orange"), (stack, world, entity, seed) -> stack.getNbt() != null && stack.getNbt().contains("who.sonic.colour.orange") ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(WhoItems.SONIC, new Identifier("blue_2"), (stack, world, entity, seed) -> stack.getNbt() != null && stack.getNbt().contains("who.sonic.blue_2") ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(WhoItems.SONIC, new Identifier("green_2"), (stack, world, entity, seed) -> stack.getNbt() != null && stack.getNbt().contains("who.sonic.green_2") ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(WhoItems.SONIC, new Identifier("purple_2"), (stack, world, entity, seed) -> stack.getNbt() != null && stack.getNbt().contains("who.sonic.purple_2") ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(WhoItems.SONIC, new Identifier("orange_2"), (stack, world, entity, seed) -> stack.getNbt() != null && stack.getNbt().contains("who.sonic.orange_2") ? 1.0f : 0.0f);
    }
}