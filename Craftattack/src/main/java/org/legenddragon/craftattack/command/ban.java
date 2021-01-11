package org.legenddragon.craftattack.command;

import jdk.jfr.Timestamp;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Warning;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;

import java.time.Instant;

import static org.legenddragon.craftattack.craftattack.*;

public class ban implements CommandExecutor {

    public craftattack plugin;

    public ban(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        Player p = (Player) sender;

        // Check if the player has ban permissions
        if ( !p.hasPermission("manage.ban")) { p.sendMessage(Serverprefix + "§4Du hast dafür keine Berechtigung"); return true;}

        // Command should look like: /tempban Player
        if ( args.length == 1 ) {
            Player p2 = Bukkit.getPlayer(args[0]);

            // Setting the ban into Config
            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);

            // Sending Message to the Player and kick the banned player
            p.sendMessage(Serverprefix + "§6Player §6" + p.getName() + " §4§lbanned!");
            p.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!");

        // Command should look like: /tempban Player Reason
        } else if ( args.length == 2 ) {
            // Setting Var
            Player p2 = Bukkit.getPlayer( args[0] );
            String reason = args[1];

            // Setting ban into Config with an reason
            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);
            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banMessage", reason);

            // Sending Message to the Player and kick the banned player
            p.sendMessage(Serverprefix + "§7Player §6" + p2.getName() + " §abanned!");
            p2.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!\n§4§lReason: " + reason);

        // Command should look like: /tempban Player Reason Time m/h
        } else if ( args.length == 4 ) {
            // Setting Var
            Player p2 = Bukkit.getPlayer( args[0] );
            String reason = args[1];

            // Checking time format
            if (args[3].equals("m")) {
                // Try is for catching an String
                try {
                    // Number into UnixTimeStamp (seconds9
                    Integer BanTimeUnixTime = Integer.parseInt(args[2]) * 60;
                    long unixTime = System.currentTimeMillis() / 1000L;

                    // Setting Config
                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banTime", BanTimeUnixTime);
                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".bannedTime", unixTime);

                } catch (NumberFormatException nfe ) {
                    System.out.println( "NumberFormatExpection " + nfe.getMessage() );
                }

            } else if ( args[3].equals("h")) {
                // Try is for catching a string
                try {
                    // Number into UnixTimeStamp (seconds)
                    Integer BanTimeUnixTime = Integer.parseInt(args[2]) * 60 * 60;
                    long unixTime = System.currentTimeMillis() / 1000L;

                    // Setting Config
                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banTime", BanTimeUnixTime);
                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".bannedTime", unixTime);
                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormateExpection " + nfe.getMessage() );
                }

            } else {
                // Sending Message
                p.sendMessage(Serverprefix + "§7Befehl: §6/ban {player} {reason} {time} {m/h}");
                return true;
            }

            // Setting reason
            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);
            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banMessage", reason);

            // Sending Message to the player and kick the banned player
            p.sendMessage(Serverprefix + "§7Player §6" + p2.getName() + " §abanned!");
            p2.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!\n§4§lReason: " + reason);

            // save config
            plugin.PlayerConfig.save();
        } else {
            p.sendMessage(Serverprefix + "§7Befehl: §6/ban {player} {reason} {time} {m/h}");
        }

        // save config
        plugin.PlayerConfig.save();
        return false;
    }
}