package org.legenddragon.vanillaPlus.command;

import lombok.Builder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.vanillaPlus.vanillaPlus;

import static org.legenddragon.vanillaPlus.vanillaPlus.Serverprefix;

public class unban implements CommandExecutor {

    public vanillaPlus plugin;
    public CheckActive checkActive;

    public unban(vanillaPlus plugin) {
        this.plugin = plugin;
        checkActive = new CheckActive( plugin );
    }

    @Override @Builder
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

        // Check if command active
        if ( !checkActive.check("bansystem") ) { sender.sendMessage( Serverprefix + "§7Dieser Befehl ist §l§cnicht §r§7aktiv!"); return true; }

        // Command for Console
        if ( !(sender instanceof Player) ) {
            if (args.length == 0) {return true;}

            OfflinePlayer p2 = Bukkit.getOfflinePlayer(args[0]);

            if ( plugin.PlayerConfig.get().isSet("User." + p2.getUniqueId() + ".ban") ) {

                // reseting that the player is banned in config
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", null);
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banMessage", null);
                // reseting the player banned time, when the player got temporal banned
                if ( plugin.PlayerConfig.get().isSet("User." + p2.getUniqueId() + ".banTime")) {
                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banTime", null);
                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".bannedTime", null);
                }

                // Sending Message
                System.out.println("Player " + p2.getName() + " unbanned!");

            } else {
                // Sending Message that the player is not in Config banned
                System.out.println("Player is not banned ");
            }
            return true;
        }

        // Command for a player
        Player p = (Player) sender;

        // Player need unban Permission
        if ( !p.hasPermission("manage.unban")) { p.sendMessage(plugin.Serverprefix + "§4Du hast dafür keine Berechtigung"); return true;}

        if ( args.length == 1 ) {

            // A banned Player must be offline
            OfflinePlayer p2 = Bukkit.getOfflinePlayer(args[0]);

            if ( plugin.PlayerConfig.get().isSet("User." + p2.getUniqueId() + ".ban") ) {

                // reseting that the player is banned in config
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", null);
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".unban", null);
                // reseting the player banned time, when the player got temporal banned
                if ( plugin.PlayerConfig.get().isSet("User." + p2.getUniqueId() + ".banTime")) {
                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banTime", null);
                    plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".bannedTime", null);
                }

                // Sending Message
                p.sendMessage(plugin.Serverprefix + "§7Player §6" + p2.getName() + " §a§lunbanned!");

            } else {
                // Sending Message that the player is not in Config banned
                p.sendMessage(plugin.Serverprefix + "§cPlayer is not banned ");
            }
        } else {
            // Sending Message how the Command works
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/tempunban {player} ");
        }

        return false;
    }
}