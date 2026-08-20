package net.nuviramc.nuviraMCGeyser;

import net.nuviramc.nuviraMCGeyser.cmd.CMDOpenserverlist;
import net.nuviramc.nuviraMCGeyser.util.ConfigLoader;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.N;

public final class NuviraMCGeyser extends JavaPlugin {

    private static NuviraMCGeyser instance;

    @Override
    public void onEnable() {
        ConfigLoader.load();

        getCommand("onplay").setExecutor(new CMDOpenserverlist());
    }

    public static NuviraMCGeyser getInstance() {
        if (instance == null) {
            instance = new NuviraMCGeyser();
        }

        return instance;
    }
}
