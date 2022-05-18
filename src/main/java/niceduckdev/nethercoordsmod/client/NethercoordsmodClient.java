package niceduckdev.nethercoordsmod.client;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import niceduckdev.nethercoordsmod.commands.GetCoordsCommand;

@Environment(EnvType.CLIENT)
public class NethercoordsmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient()
    {
        registerCommands(ClientCommandManager.DISPATCHER);
    }

    public static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        GetCoordsCommand.register(dispatcher);
    }
}
