package net.nuviramc.nuviraMCGeyser.cmd;

import net.nuviramc.nuviraMCGeyser.NuviraMCGeyser;
import net.nuviramc.nuviraMCGeyser.util.ConfigLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CMDReload implements TabExecutor {

    private static final List<String> RELOAD_WORDS = List.of("reload", "refresh", "rl", "rf");
    private static final String permission = "nuviramcgeyser.reload";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (!sender.hasPermission(permission)) {
            sender.sendMessage("§cKeine Berechtigung.");
            return true;
        }

        if (args.length == 0 || !RELOAD_WORDS.contains(args[0].toLowerCase())) {
            sender.sendMessage("§cNutzung: /nvgeyser reload");
            return true;
        }

        boolean isPlayer = sender instanceof Player;

        if (isPlayer) {
            sender.sendMessage("§7Gonna try to reload the config... gimme a moment, this is going to be a tuff one...");
        }

        try {
            ConfigLoader.reload();
            sender.sendMessage("§aConfig neu geladen!");
        } catch (Exception e) {
            sender.sendMessage("§cFehler beim Neuladen der Config: " + e.getMessage());
            NuviraMCGeyser.getInstance().getLogger().log(Level.WARNING, "Config-Reload fehlgeschlagen", e);
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length == 1 && sender.hasPermission(permission)) {
            List<String> matches = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], RELOAD_WORDS, matches);
            return matches;
        }
        return List.of();
    }
}