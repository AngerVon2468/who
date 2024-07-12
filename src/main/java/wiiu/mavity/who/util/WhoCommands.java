package wiiu.mavity.who.util;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

import wiiu.mavity.who.component.WhoComponents;

public class WhoCommands {

    public static void getTardisIds() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("get_tardis_ids")
                .executes(context -> {

                    int value = WhoComponents.TARDIS_IDS.get(context.getSource().getWorld()).getTardisIds();
                    String message = "Tardis ids: " + value;
                    context.getSource().sendMessage(Text.literal(message));
                    return 1;

                })
        ));
    }
}