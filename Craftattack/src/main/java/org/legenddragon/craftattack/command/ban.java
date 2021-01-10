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
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimise for console!" + ANSI_RESET); return true; }

        Player p = (Player) sender;

        if ( !p.hasPermission("manage.ban")) { p.sendMessage(Serverprefix + "§4Du hast dafür keine Berechtigung"); return true;}

        // /tempban {player} {reason} {time} {minute/hour}

        if ( args.length == 1 ) {
            Player p2 = Bukkit.getPlayer(args[0]);


            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);

            p.sendMessage(Serverprefix + "§6Player §6" + p.getName() + " §abanned!");
            p.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!");

        } else if ( args.length == 2 ) {
            Player p2 = Bukkit.getPlayer( args[0] );
            String reason = args[1];

            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);
            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banMessage", reason);

            p.sendMessage(Serverprefix + "§7Player §6" + p2.getName() + " §abanned!");
            p2.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!\n§4§lReason: " + reason);


        } else if ( args.length == 4 ) {
            // The Command should look like: /tempban musterman muster 10 m

            Player p2 = Bukkit.getPlayer( args[0] );
            String reason = args[1];

            if (args[3].equals("m")) {
                try {
                    Integer BanTimeUnixTime = Integer.parseInt(args[2]) * 60;
                    long unixTime = System.currentTimeMillis() / 1000L;

                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banTime", BanTimeUnixTime);
                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".bannedTime", unixTime);

                } catch (NumberFormatException nfe ) {
                    System.out.println( "NumberFormatExpection " + nfe.getMessage() );
                }
            } else if ( args[3].equals("h")) {
                try {
                    Integer BanTimeUnixTime = Integer.parseInt(args[2]) * 60 * 60;
                    long unixTime = System.currentTimeMillis() / 1000L;

                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banTime", BanTimeUnixTime);
                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".bannedTime", unixTime);

                } catch (NumberFormatException nfe) {
                    System.out.println("NumberFormateExpection " + nfe.getMessage() );
                }
            } else {
                p.sendMessage(Serverprefix + "§7Befehl: §6/ban {player} {reason} {time} {m/h}");
                return true;
            }

            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);
            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banMessage", reason);

            p.sendMessage(Serverprefix + "§7Player §6" + p2.getName() + " §abanned!");
            p2.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!\n§4§lReason: " + reason);

            plugin.PlayerConfig.save();
        } else {
            p.sendMessage(Serverprefix + "§7Befehl: §6/ban {player} {reason} {time} {m/h}");
        }

        plugin.PlayerConfig.save();
        return false;
    }
}