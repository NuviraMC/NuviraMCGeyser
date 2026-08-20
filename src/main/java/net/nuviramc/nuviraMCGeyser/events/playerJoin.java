package net.nuviramc.nuviraMCGeyser.events;

import net.nuviramc.nuviraMCGeyser.NuviraMCGeyser;
import net.nuviramc.nuviraMCGeyser.util.ConfigLoader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.HttpURLConnection;
import java.net.URL;

public class playerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskAsynchronously(NuviraMCGeyser.getInstance(), () -> {
            try {
                URL url = new URL(ConfigLoader.getSkinApiUrl() + player.getName());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.getResponseCode(); // Request auslösen, Antwort juckt
                conn.disconnect();
            } catch (Exception ignored) {}
        });
    }

}
