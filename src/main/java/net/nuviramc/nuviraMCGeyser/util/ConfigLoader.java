package net.nuviramc.nuviraMCGeyser.util;

import net.nuviramc.nuviraMCGeyser.NuviraMCGeyser;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigLoader {
    private static FileConfiguration config;

    public static void load() {
        NuviraMCGeyser plugin = NuviraMCGeyser.getInstance();
        File configFile = new File(plugin.getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static String getSkinApiUrl() {
        return config.getString("skinapi.url");
    }

    public static String getPlaceholder(String placeholder) {
        return config.getString("placeholders." + placeholder.toLowerCase());
    }

    public static void reload() {
        load();
    }
}