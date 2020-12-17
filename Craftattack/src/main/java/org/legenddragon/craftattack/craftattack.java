package org.legenddragon.craftattack;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.legenddragon.craftattack.command.*;
import org.legenddragon.craftattack.config.CustomConfiguration;
import org.legenddragon.craftattack.listener.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class craftattack extends JavaPlugin {
    // Server Prefix
    public String Serverprefix = "§7[§6§lCA§r§7] §r";

    // Normal config for some saves
    public CustomConfiguration PlayerConfig;

    // Server Config to save settings and Server text
    public CustomConfiguration ServerConfig;

    // Hashmap for tpa Command
    public static HashMap<Player, Player> tpa = new HashMap<Player, Player>();

    public void onEnable() {
        // Reload and save Config files
        reloadConfig();
        getConfig().options().copyDefaults();
        saveConfig();
        saveDefaultConfig();

        PlayerConfig = new CustomConfiguration("PlayerConfig", this );
        PlayerConfig.reload();
        PlayerConfig.save();

        ServerConfig = new CustomConfiguration( "ServerConfig", this );
        ServerConfig.reload();
        ServerConfig.save();

        // Register Events
        Bukkit.getPluginManager().registerEvents(new onJoin( this), this);
        Bukkit.getPluginManager().registerEvents(new onQuit( this), this);
        Bukkit.getPluginManager().registerEvents(new onLogin(this), this);
        Bukkit.getPluginManager().registerEvents(new onServerListPing(this), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerMovement(this), this);
        Bukkit.getPluginManager().registerEvents(new onElytraToggle(this), this);
        Bukkit.getPluginManager().registerEvents(new onInventoryInteract(this),this);

        // try/catch to catch null
        try {
            Objects.requireNonNull(getCommand("tpa")).setExecutor(new tpa(this));
            Objects.requireNonNull(getCommand("lock")).setExecutor(new lock(this));
            Objects.requireNonNull(getCommand("pos")).setExecutor(new pos(this));
            Objects.requireNonNull(getCommand("ec")).setExecutor(new ec(this));
            Objects.requireNonNull(getCommand("inv")).setExecutor(new inv(this));
            Objects.requireNonNull(getCommand("spawn")).setExecutor(new spawn(this));
            Objects.requireNonNull(getCommand("stats")).setExecutor(new stats(this));
        } catch(NullPointerException ex) {
            ex.printStackTrace();
            messageOps("§c§lPLUGIN ERROR: " + Arrays.toString(ex.getStackTrace()));
        }
    }

    public void onDisable() {
        ServerConfig.save();
        PlayerConfig.save();
    }

    public void messageOps(String message) {
        Bukkit.getOperators().forEach(op -> {
            if(op.isOnline()) {
                ((Player) op).sendMessage(message);
            }
        });
    }
}
