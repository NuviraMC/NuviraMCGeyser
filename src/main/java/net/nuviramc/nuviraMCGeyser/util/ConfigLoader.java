package net.nuviramc.nuviraMCGeyser.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class ConfigLoader {
    private static FileConfiguration config;

    public static void load() {
        File configFile = new File(Bukkit.getPluginManager().getPlugin("NuviraMCGeyser").getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static String getSkinApiUrl() {
        return config.getString("skinapi.url");
    }
}