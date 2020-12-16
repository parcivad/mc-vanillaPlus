package org.legenddragon.craftattack;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.legenddragon.craftattack.config.CustomConfiguration;
import org.legenddragon.craftattack.listener.*;

public class craftattack extends JavaPlugin {

    public CustomConfiguration Config;

    public void onEnable() {
        // Reload and save Config files
        reloadConfig();
        getConfig().options().copyDefaults();
        saveConfig();
        saveDefaultConfig();

        Config = new CustomConfiguration("Config", this );
        Config.reload();
        Config.save();

        // Register Events
        Bukkit.getPluginManager().registerEvents(new onJoin( this), this);
        Bukkit.getPluginManager().registerEvents(new onQuit( this), this);
    }

    public void onDisable() {
        Config.save();
    }
}
