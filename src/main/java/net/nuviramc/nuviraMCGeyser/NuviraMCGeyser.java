package net.nuviramc.nuviraMCGeyser;

import net.nuviramc.nuviraMCGeyser.cmd.CMDOpenserverlist;
import net.nuviramc.nuviraMCGeyser.util.ConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;

public final class NuviraMCGeyser extends JavaPlugin {

    private static NuviraMCGeyser instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        ConfigLoader.load();

        getCommand("onplay").setExecutor(new CMDOpenserverlist());
    }

    public static NuviraMCGeyser getInstance() {
        return instance;
    }
}