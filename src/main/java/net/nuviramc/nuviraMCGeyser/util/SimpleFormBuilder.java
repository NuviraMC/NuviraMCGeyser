package net.nuviramc.nuviraMCGeyser.util;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.geysermc.cumulus.form.SimpleForm;
import org.geysermc.cumulus.util.FormImage;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.ArrayList;
import java.util.List;

public class SimpleFormBuilder {

    private static SimpleFormBuilder instance;
    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder()
            .character('&')
            .hexColors()
            .build();

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
                .button("Zurück");

        form.validResultHandler(response -> {
            if (response.clickedButtonId() == 0) {
                openServerList(viewer);
            }
        });

        FloodgateApi.getInstance().sendForm(viewer.getUniqueId(), form.build());
    }

    private String buildStatsContent(Player target) {
        return "§7Rang§8: §r" + resolvePlaceholder(target, "rank") + "\n"
                + "§7Clan§8: §b" + resolvePlaceholder(target, "clan") + "\n"
                + "§7Geld§8: §a$" + resolvePlaceholder(target, "balance") + "\n"
                + "§7Spielzeit§8: §b" + resolvePlaceholder(target, "playtime") + "\n"
                + "§7Tode§8: §b" + resolvePlaceholder(target, "deaths") + "\n"
                + "§7Kills§8: §b" + resolvePlaceholder(target, "kills") + "\n"
                + "§7Afk§8: §b" + resolvePlaceholder(target, "afk") + "\n\n";
    }

    private Component resolvePlaceholder(Player target, String key) {
        String raw = ConfigLoader.getPlaceholder(key);
        if (raw == null) {
            return Component.text("Placeholder nicht verfügbar.");
        }
        String value = PlaceholderAPI.setPlaceholders(target, raw);
        return translateColor(value);
    }

    private Component translateColor(String key) {
        return LEGACY_SERIALIZER.deserialize(key);
    }
}