package net.nuviramc.nuviraMCGeyser.util;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.ArrayList;
import java.util.List;

public class SimpleFormBuilder {

    private static SimpleFormBuilder instance;

    public static SimpleFormBuilder getInstance() {
        if (instance == null) {
            instance = new SimpleFormBuilder();
        }
        return instance;
    }

    public void openServerList(Player viewer) {
        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

        SimpleForm.Builder form = SimpleForm.builder()
                .title("Serverliste - NuviraMC")
                .content("Es sind derzeit " + onlinePlayers.size() + " Spieler online!");

        for (Player player : onlinePlayers) {
            form.button(player.getName(), FormImage.Type.URL,
                    ConfigLoader.getSkinApiUrl() + player.getName());
        }

        form.validResultHandler(response -> {
            int clickedIndex = response.clickedButtonId();
            if (clickedIndex >= 0 && clickedIndex < onlinePlayers.size()) {
                Player target = onlinePlayers.get(clickedIndex);
                openPlayerStats(viewer, target);
            }
        });

        FloodgateApi.getInstance().sendForm(viewer.getUniqueId(), form.build());
    }

    public void openPlayerStats(Player viewer, Player target) {
        SimpleForm.Builder form = SimpleForm.builder()
                .title("Statistiken - " + target.getName())
                .content(buildStatsContent(target))
                .content("§7Color Codes?")
                .content("§7Color Codes?")
                .button("Zurück");

        form.validResultHandler(response -> {
            if (response.clickedButtonId() == 0) {
                openServerList(viewer);
            }
        });

        FloodgateApi.getInstance().sendForm(viewer.getUniqueId(), form.build());
    }

    private String buildStatsContent(Player target) {
        return "Rang: " + resolvePlaceholder(target, "rank") + "\n"
                + "Clan: " + resolvePlaceholder(target, "clan") + "\n"
                + "Ping: " + target.getPing() + "ms\n";
    }

    private String resolvePlaceholder(Player target, String key) {
        String value = PlaceholderAPI.setPlaceholders(target, ConfigLoader.getPlaceholder(key));
        return value != null ? value : "Placeholder nicht verfügbar.";
    }
}