package net.nuviramc.nuviraMCGeyser;

import net.nuviramc.nuviraMCGeyser.cmd.CMDOpenserverlist;
import net.nuviramc.nuviraMCGeyser.cmd.CMDReload;
import net.nuviramc.nuviraMCGeyser.util.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class NuviraMCGeyser extends JavaPlugin {

    private static NuviraMCGeyser instance;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().addDefault("skinapi.url", "RANDOM_SKIN_API");

        getConfig().addDefault("placeholders.rank", "RANK_PLACEHOLDER");
        getConfig().addDefault("placeholders.clan", "CLAN_PLACEHOLDER");
        getConfig().addDefault("placeholders.balance", "BALANCE_PLACEHOLDER");
        getConfig().addDefault("placeholders.playtime", "PLAYTIME_PLACEHOLDER");
        getConfig().addDefault("placeholders.deaths", "DEATHS_PLACEHOLDER");
        getConfig().addDefault("placeholders.kills", "KILLS_PLACEHOLDER");
        getConfig().addDefault("placeholders.afk", "AFK_PLACEHOLDER");

        saveDefaultConfig();
        ConfigLoader.load();

        getCommand("onplay").setExecutor(new CMDOpenserverlist());
        getCommand("nvgeyser").setExecutor(new CMDReload());
    }

    public static NuviraMCGeyser getInstance() {
        return instance;
    }
}