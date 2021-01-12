package org.legenddragon.craftattack;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.legenddragon.craftattack.command.*;
import org.legenddragon.craftattack.config.CustomConfiguration;
import org.legenddragon.craftattack.listener.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class craftattack extends JavaPlugin implements Listener {

    // Plugin Prefix
    public static final String Serverprefix = "§7[§6§lCA§r§7] §r";

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

        // Scoreboard for Prefix (teams)
        sb = Bukkit.getScoreboardManager().getNewScoreboard();

        // Team Register
        sb.registerNewTeam("00000Owner").setPrefix("§4Owner §7| §4");
        sb.getTeam("00000Owner").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00001Dev").setPrefix("§bDev §7| §f");
        sb.getTeam("00001Dev").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00003Building").setPrefix("§9Building §7| §f");
        sb.getTeam("00003Building").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00003Redstone").setPrefix("§cRedstone §7| §f");
        sb.getTeam("00003Redstone").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00003Neutral").setPrefix("§aNeutral §7| §f");
        sb.getTeam("00003Neutral").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00003Fighting").setPrefix("§cFighting §7| §f");
        sb.getTeam("00003Fighting").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00003Killer").setPrefix("§cKiller §7| §f");
        sb.getTeam("00003Killer").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00002Live").setPrefix("§cLIVE §7| §f");
        sb.getTeam("00002Live").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00002REC").setPrefix("§9REC §7| §f");
        sb.getTeam("00002REC").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00003Mining").setPrefix("§7Mining §7| §f");
        sb.getTeam("00003Mining").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00009Afk").setPrefix("§6Afk §7| ");
        sb.getTeam("00009Afk").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00008none").setPrefix("§7 - | §f");
        sb.getTeam("00008none").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        sb.registerNewTeam("00003Ehe").setPrefix("§bEhe §7| §f");
        sb.getTeam("00003Ehe").setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);

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
            Objects.requireNonNull(getCommand("status")).setExecutor(new status(this));
            Objects.requireNonNull(getCommand("help")).setExecutor(new help(this));
            Objects.requireNonNull(getCommand("settings")).setExecutor(new settings(this));
            Objects.requireNonNull(getCommand("seed")).setExecutor(new seed(this));
            Objects.requireNonNull(getCommand("tempban")).setExecutor(new ban(this));
            Objects.requireNonNull(getCommand("tempunban")).setExecutor(new unban(this));
            Objects.requireNonNull(getCommand("info")).setExecutor(new info(this));
        } catch(NullPointerException ex) {
            ex.printStackTrace();
            messageOps("§c§lPLUGIN ERROR: " + Arrays.toString(ex.getStackTrace()));
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
                case "Dev":
                    sb.getTeam("00001Dev").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00001Dev").getPrefix() + p.getName() + "§f");
                    break;
                case "Building":
                    sb.getTeam("00003Building").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00003Building").getPrefix() + p.getName() + "§f");
                    break;
                case "Redstone":
                    sb.getTeam("00003Redstone").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00003Redstone").getPrefix() + p.getName() + "§f");
                    break;
                case "Neutral":
                    sb.getTeam("00003Neutral").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00003Neutral").getPrefix() + p.getName() + "§f");
                    break;
                case "Live":
                    sb.getTeam("00002Live").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00002Live").getPrefix() + p.getName() + "§f");
                    break;
                case "REC":
                    sb.getTeam("00002REC").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00002REC").getPrefix() + p.getName() + "§f");
                    break;
                case "Mining":
                    sb.getTeam("00003Mining").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00003Mining").getPrefix() + p.getName() + "§f");
                    break;
                case "Fighting":
                    sb.getTeam("00003Fighting").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00003Fighting").getPrefix() + p.getName() + "§f");
                    break;
                case "Ehe":
                    sb.getTeam("00003Ehe").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00003Ehe").getPrefix() + p.getName() + "§f");
                    break;
                case "Killer":
                    sb.getTeam("00003Killer").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00003Killer").getPrefix() + p.getName() + "§f");
                    break;
                case "Afk":
                    sb.getTeam("00009Afk").addPlayer(p);
                    p.setDisplayName(sb.getTeam("00009Afk").getPrefix() + p.getName() + "§f");
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
}
