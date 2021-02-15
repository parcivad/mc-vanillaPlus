package org.legenddragon.vanillaPlus;

import com.sun.tools.javac.comp.Check;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.legenddragon.vanillaPlus.command.*;
import org.legenddragon.vanillaPlus.config.CustomConfiguration;
import org.legenddragon.vanillaPlus.listener.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class vanillaPlus extends JavaPlugin implements Listener {

    // Plugin Prefix
    public static final String Serverprefix = "§7[§6§lV+§r§7] §r";

    // Normal config for some saves
    public CustomConfiguration PlayerConfig;

    // Server Config to save settings and Server text
    public CustomConfiguration ServerConfig;

    // Hashmap for tpa Command
    public static HashMap<Player, Player> tpa = new HashMap<Player, Player>();

    // ANSI Colors for Console
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";


    Scoreboard sb;
    public void onEnable() {

        /*
        // Scoreboard for Prefix (teams)
        sb = Bukkit.getScoreboardManager().getNewScoreboard();

        // Team Register
        sb.registerNewTeam("00000Owner").setPrefix("§4Owner §7| §4");
        sb.getTeam("00000Owner").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
         */

        // Reload and save Config files
        reloadConfig();
        getConfig().options().copyDefaults();
        saveConfig();

        // Player Config for Player Data like positions
        PlayerConfig = new CustomConfiguration("PlayerConfig", this );
        PlayerConfig.reload();
        PlayerConfig.save();

        // Server Config for general settings
        ServerConfig = new CustomConfiguration( "ServerConfig", this );
        ServerConfig.reload();
        ServerConfig.save();

        // Register Events
        Bukkit.getPluginManager().registerEvents(new onJoin(), this);
        Bukkit.getPluginManager().registerEvents(new onQuit( this), this);
        Bukkit.getPluginManager().registerEvents(new onLogin(this), this);
        Bukkit.getPluginManager().registerEvents(new onServerListPing(this), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerMovement(this), this);
        Bukkit.getPluginManager().registerEvents(new onElytraToggle(this), this);
        Bukkit.getPluginManager().registerEvents(new onInventoryInteract(this),this);
        Bukkit.getPluginManager().registerEvents(new onPlayerDeath(this),this);
        Bukkit.getPluginManager().registerEvents(this, this);

        // try/catch to catch null
        try {
            Objects.requireNonNull(getCommand("tpa")).setExecutor(new tpa(this));
            Objects.requireNonNull(getCommand("lock")).setExecutor(new lock(this));
            Objects.requireNonNull(getCommand("pos")).setExecutor(new pos(this));
            Objects.requireNonNull(getCommand("ec")).setExecutor(new ec(this));
            Objects.requireNonNull(getCommand("inv")).setExecutor(new inv(this));
            Objects.requireNonNull(getCommand("spawn")).setExecutor(new spawn(this));
            Objects.requireNonNull(getCommand("stats")).setExecutor(new stats(this));
            Objects.requireNonNull(getCommand("help")).setExecutor(new help(this));
            Objects.requireNonNull(getCommand("booster")).setExecutor(new booster(this));
            Objects.requireNonNull(getCommand("seed")).setExecutor(new seed(this));
            Objects.requireNonNull(getCommand("tempban")).setExecutor(new ban(this));
            Objects.requireNonNull(getCommand("tempunban")).setExecutor(new unban(this));
            Objects.requireNonNull(getCommand("commands")).setExecutor(new commands(this));
            Objects.requireNonNull(getCommand("home")).setExecutor(new home(this));
        } catch(NullPointerException exception) {
            exception.printStackTrace();
            messageOps("§c§lPLUGIN ERROR: " + Arrays.toString(exception.getStackTrace()));
        }
    }

    // Saves Config
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
    /*
    // Setting the prefix/team in tablist
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if ( this.PlayerConfig.get().get("User." + p.getUniqueId() + ".Status") != null ) {
            String team = this.PlayerConfig.get().get("User." + p.getUniqueId() + ".Status").toString();
            switch (team) {
                case "Owner":
                    sb.getTeam("00000Owner").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00000Owner").getPrefix() + p.getName()+ "§f");
                    break;
            }

        } else {
            sb.getTeam("00008none").addPlayer(p);
            p.setDisplayName(sb.getTeam("00008none").getPrefix() + p.getName() + "§f");
        }

        for (Player all : Bukkit.getOnlinePlayers() ) {
            all.setScoreboard(sb);
        }
    }
    */
}
