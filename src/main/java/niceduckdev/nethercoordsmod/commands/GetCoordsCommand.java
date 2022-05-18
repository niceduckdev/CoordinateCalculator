package niceduckdev.nethercoordsmod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.world.World;

import java.awt.*;

import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal;

public class GetCoordsCommand
{
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        dispatcher.register(literal("coordinates")
                .then(literal("convert").executes(context -> run(context.getSource()))));
    }

    private static int run(FabricClientCommandSource source) throws CommandSyntaxException
    {
        double x = source.getPlayer().getPos().x;
        double y = source.getPlayer().getPos().y;
        double z = source.getPlayer().getPos().z;

        if (source.getPlayer().world.getRegistryKey() == World.NETHER)
        {
            double overWorldX = x * 8;
            double overWorldY = y;
            double overWorldZ = z * 8;

            overWorldX = Math.round(overWorldX * 100.0) / 100.0;
            overWorldY = Math.round(overWorldY * 100.0) / 100.0;
            overWorldZ = Math.round(overWorldZ * 100.0) / 100.0;

            source.sendFeedback(new LiteralText("§bOverworld coordinates:"));
            source.sendFeedback(new LiteralText("§bx: " + overWorldX + " y: " + overWorldY + " z: " + overWorldZ));

            return 1;
        }
        else if (source.getPlayer().world.getRegistryKey() == World.OVERWORLD)
        {
            double netherX = x / 8;
            double netherY = y;
            double netherZ = z / 8;

            netherX = Math.round(netherX * 100.0) / 100.0;
            netherY = Math.round(netherY * 100.0) / 100.0;
            netherZ = Math.round(netherZ * 100.0) / 100.0;

            source.sendFeedback(new LiteralText("§4Nether coordinates:"));
            source.sendFeedback(new LiteralText("§4x: " + netherX + " y: " + netherY + " z: " + netherZ));

            return 1;
        }
        else
        {
            source.sendFeedback(new LiteralText("§dCannot get coordinates in the end."));

            return 0;
        }
    }
}
