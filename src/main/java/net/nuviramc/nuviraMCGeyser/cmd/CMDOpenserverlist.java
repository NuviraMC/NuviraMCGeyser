package net.nuviramc.nuviraMCGeyser.cmd;

import net.nuviramc.nuviraMCGeyser.util.SimpleFormBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;
import org.jetbrains.annotations.NotNull;

public class CMDOpenserverlist implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        String PREFIX = "§b§lNUVIRAMC §8» §7";

        if (!(sender instanceof Player)) {
            sender.sendMessage("Bedrock form cannot be opened as a non Player.");
            return true;
        }

        Player player = (Player) sender;

        boolean isBedrockPlayer = FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId());

        if (!isBedrockPlayer) {
            sender.sendMessage(PREFIX + "§cFehler! §7Du kannst als Java-Spieler kein Bedrock GUI öffnen.");
            return true;
        }

        SimpleFormBuilder.getInstance().openServerList(player);

        return true;
    }
}
