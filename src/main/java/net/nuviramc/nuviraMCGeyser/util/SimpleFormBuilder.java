package net.nuviramc.nuviraMCGeyser.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.cumulus.util.FormImage;

import java.io.File;

public class SimpleFormBuilder {

    private static SimpleFormBuilder instance;

    public static SimpleFormBuilder getInstance() {
        if (instance == null) {
            instance = new SimpleFormBuilder();
        }
        return instance;
    }

    public SimpleForm initServerList() {
        SimpleForm.Builder form = SimpleForm.builder()
                .title("Serverliste - NuviraMC")
                .content("Es sind derzeit " + Bukkit.getServer().getOnlinePlayers().size() + " Spieler online!");

        for (Player player : Bukkit.getOnlinePlayers()) {
            form.button(player.getName(), FormImage.Type.URL,
                    ConfigLoader.getSkinApiUrl() + player.getName());
        }
        return form.build();
    }
}