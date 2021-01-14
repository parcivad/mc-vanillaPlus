package org.legenddragon.craftattack.command;

import jdk.jfr.Timestamp;
import org.apache.commons.lang.ObjectUtils;
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
import java.util.ArrayList;

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
            try {
                Player p2 = Bukkit.getPlayer(args[0]);

                // Setting the ban into Config
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);

                // Sending Message to the Player and kick the banned player
                p.sendMessage(Serverprefix + "§6Player §6" + p.getName() + " §4§lbanned!");
                p.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!");
            } catch (NullPointerException npe) {
                OfflinePlayer p2 = Bukkit.getOfflinePlayer(args[0]);

                // Setting the ban into Config
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);

                // Sending Message to the Player and kick the banned player
                p.sendMessage(Serverprefix + "§6Player §6" + p.getName() + " §4§lbanned!");
                p.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!");
            }

        // Command should look like: /tempban Player Reason
        } else if ( args.length >= 2 && !( args[2].equals("m") || args[2].equals("h"))) {
            try {
                // Setting Var
                Player p2 = Bukkit.getPlayer( args[0] );

                // For Message
                ArrayList<String> message = new ArrayList<String>();
                // Setting the Message
                for (int i = 1; i < args.length; i++) {
                    message.add(args[i]);
                }
                // Message back to String. (right format)
                String reason = message.toString().replace("[", "").replace("]", "").replace(",", "");

                // Setting ban into Config with an reason
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banMessage", reason);

                // Sending Message to the Player and kick the banned player
                p.sendMessage(Serverprefix + "§7Player §6" + p2.getName() + " §4§lbanned!");
                p2.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!\n§4§lReason: " + reason);
            } catch (NullPointerException npe) {
                // Setting Var
                OfflinePlayer p2 = Bukkit.getOfflinePlayer( args[0] );

                // For Message
                ArrayList<String> message = new ArrayList<String>();
                // Setting the Message
                for (int i = 1; i < args.length; i++) {
                    message.add(args[i]);
                }
                // Message back to String. (right format)
                String reason = message.toString().replace("[", "").replace("]", "").replace(",", "");

                // Setting ban into Config with an reason
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banMessage", reason);

                // Sending Message to the Player and kick the banned player
                p.sendMessage(Serverprefix + "§7Player §6" + p2.getName() + " §4§lbanned!");
            }

        // Command should look like: /tempban Player Time m/h Reason
        } else if ( args.length >= 4 && ( args[2].equals("m") || args[2].equals("h"))) {
            try {
                // Setting Var
                Player p2 = Bukkit.getPlayer( args[0] );

                // For Message
                ArrayList<String> message = new ArrayList<String>();
                // Setting the Message
                for (int i = 3; i < args.length; i++) {
                    message.add(args[i]);
                }
                // Message back to String. (right format)
                String reason = message.toString().replace("[", "").replace("]", "").replace(",", "");

                // Checking time format
                if (args[2].equals("m")) {
                    // Try is for catching an String
                    try {
                        // Number into UnixTimeStamp (seconds9
                        Integer BanTimeUnixTime = Integer.parseInt(args[1]) * 60;
                        long unixTime = System.currentTimeMillis() / 1000L;

                        // Setting Config
                        plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banTime", BanTimeUnixTime);
                        plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".bannedTime", unixTime);

                    } catch (NumberFormatException nfe ) {
                        System.out.println( "NumberFormatExpection " + nfe.getMessage() );
                    }

                } else if ( args[2].equals("h")) {
                    // Try is for catching a string
                    try {
                        // Number into UnixTimeStamp (seconds)
                        Integer BanTimeUnixTime = Integer.parseInt(args[1]) * 60 * 60;
                        long unixTime = System.currentTimeMillis() / 1000L;

                        // Setting Config
                        plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banTime", BanTimeUnixTime);
                        plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".bannedTime", unixTime);
                    } catch (NumberFormatException nfe) {
                        System.out.println("NumberFormateExpection " + nfe.getMessage() );
                    }

                } else {
                    // Sending Message
                    p.sendMessage(Serverprefix + "§7Befehl: §6/ban {player} {(reason)} {time} {m/h} {reason}");
                    return true;
                }

                // Setting reason
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banMessage", reason);

                // Sending Message to the player and kick the banned player
                p.sendMessage(Serverprefix + "§7Player §6" + p2.getName() + " §4§lbanned!");
                p2.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!\n§4§lReason: " + reason);

                // save config
                plugin.PlayerConfig.save();
            } catch (NullPointerException npe) {
                // Setting Var
                OfflinePlayer p2 = Bukkit.getOfflinePlayer( args[0] );

                // For Message
                ArrayList<String> message = new ArrayList<String>();
                // Setting the Message
                for (int i = 3; i < args.length; i++) {
                    message.add(args[i]);
                }
                // Message back to String. (right format)
                String reason = message.toString().replace("[", "").replace("]", "").replace(",", "");

                // Checking time format
                if (args[2].equals("m")) {
                    // Try is for catching an String
                    try {
                        // Number into UnixTimeStamp (seconds9
                        Integer BanTimeUnixTime = Integer.parseInt(args[1]) * 60;
                        long unixTime = System.currentTimeMillis() / 1000L;

                        // Setting Config
                        plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banTime", BanTimeUnixTime);
                        plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".bannedTime", unixTime);

                    } catch (NumberFormatException nfe ) {
                        System.out.println( "NumberFormatExpection " + nfe.getMessage() );
                    }

                } else if ( args[2].equals("h")) {
                    // Try is for catching a string
                    try {
                        // Number into UnixTimeStamp (seconds)
                        Integer BanTimeUnixTime = Integer.parseInt(args[1]) * 60 * 60;
                        long unixTime = System.currentTimeMillis() / 1000L;

                        // Setting Config
                        plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banTime", BanTimeUnixTime);
                        plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".bannedTime", unixTime);
                    } catch (NumberFormatException nfe) {
                        System.out.println("NumberFormateExpection " + nfe.getMessage() );
                    }

                } else {
                    // Sending Message
                    p.sendMessage(Serverprefix + "§7Befehl: §6/ban {player} {(reason)} {time} {m/h} {reason}");
                    return true;
                }

                // Setting reason
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banMessage", reason);

                // Sending Message to the player and kick the banned player
                p.sendMessage(Serverprefix + "§7Player §6" + p2.getName() + " §4§lbanned!");

                // save config
                plugin.PlayerConfig.save();
            }
        } else {
            p.sendMessage(Serverprefix + "§7Befehl: §6/ban {player} {(reason)} {time} {m/h} {reason}");
        }

        // save config
        plugin.PlayerConfig.save();
        return false;
    }
}