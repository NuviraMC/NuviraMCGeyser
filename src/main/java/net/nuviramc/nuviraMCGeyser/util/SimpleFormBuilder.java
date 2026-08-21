package net.nuviramc.nuviraMCGeyser.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.ArrayList;
import java.util.List;

public class SimpleFormBuilder {

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
                .button("Zurück");

        form.validResultHandler(response -> {
            if (response.clickedButtonId() == 0) {
                openServerList(viewer);
            }
        });

        FloodgateApi.getInstance().sendForm(viewer.getUniqueId(), form.build());
    }

    private String buildStatsContent(Player target) {
        return "Level: " + target.getLevel() + "\n"
                + "Health: " + target.getHealth() + "\n"
                + "Ping: " + target.getPing() + "ms";
    }
}