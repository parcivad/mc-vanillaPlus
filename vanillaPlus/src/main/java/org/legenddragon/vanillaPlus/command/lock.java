package org.legenddragon.vanillaPlus.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.vanillaPlus.vanillaPlus;

import java.util.ArrayList;

import static org.legenddragon.vanillaPlus.vanillaPlus.*;

public class lock implements CommandExecutor {

    public vanillaPlus plugin;
    public CheckActive checkActive;

    public lock(vanillaPlus plugin) {
        this.plugin = plugin;
        checkActive = new CheckActive( plugin );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Check if command active
        if ( !checkActive.check("lock") ) { sender.sendMessage( Serverprefix + "§7Dieser Befehl ist §l§cnicht §r§7aktiv!"); return true; }

        // Use optimized command for console
        if ( !(sender instanceof Player) ) {
            // Setting Lock Message (FROM CONSOLE)
            if (args.length == 0) {return true;}

            plugin.ServerConfig.get().set("Server.message", "Server Locked from Console");



            boolean lockmode = plugin.ServerConfig.get().getBoolean("Server.LockMode");
            if (args[0].equalsIgnoreCase("on")) {

                System.out.println("Der Server ist nun gelockt!");
                lockmode = true;

                String message = (String) plugin.ServerConfig.get().get("Server.message");

                for (Player all : Bukkit.getOnlinePlayers() ) {
                    all.kickPlayer("§4✖§r §6§Netzwerk§r §7ist §cgelockt§r§7! §4✖§r  §7// §c§l" + message);
                }

            }
            // Lockmode off
            if (args[0].equalsIgnoreCase("off")) {

                System.out.println("Der Server ist nun entgelockt!");
                lockmode = false;
            }

            // Set/Save Config
            plugin.ServerConfig.get().set("Server.LockMode", lockmode);
            plugin.ServerConfig.save();

            return true;
        }

        // Command for Player
        boolean lockmode = plugin.ServerConfig.get().getBoolean("Server.LockMode");
        Player p = (Player) sender;

        // Player must have permission
        if (p.hasPermission("server.settings")) {

            // Command should look like: /lock on/off
            if (args.length == 1) {

                // Lock the Server
                if (args[0].equalsIgnoreCase("on")) {

                    p.sendMessage(Serverprefix + "§7Der Server befindet sich nun im §c§lLOCKMODE!");
                    lockmode = true;

                    String message = (String) plugin.ServerConfig.get().get("Server.message");

                    for (Player all : Bukkit.getOnlinePlayers() ) {
                        all.kickPlayer("§4✖§r §6§lCraftattack Netzwerk§r §7ist §cgelockt§r§7! §4✖§r  §7// §c§l" + message);
                    }

                }

                // Unlock the Server
                if (args[0].equalsIgnoreCase("off")) {

                    p.sendMessage(Serverprefix + "§7Der Server befindet sich nun nicht mehr im §a§lLOCKMODE!");
                    lockmode = false;
                }

                // set/save the config
                plugin.ServerConfig.get().set("Server.LockMode", lockmode);
                plugin.ServerConfig.save();

            // Command should look like: /lock message {message}
            } else if ( args.length >= 2) {

                if (args[0].equalsIgnoreCase("message")) {

                    // For Message
                    ArrayList<String> message = new ArrayList<String>();
                    // Setting the Message
                    for (int i = 1; i < args.length; i++) {
                        message.add(args[i]);
                    }
                    // Message back to String. (right format)
                    String messageToSet = message.toString().replace("[", "").replace("]", "").replace(",", "");

                    plugin.ServerConfig.get().set("Server.message", messageToSet);
                    plugin.ServerConfig.save();
                    p.sendMessage(Serverprefix + "§7Server LockMessage wurde auf: §r§c§l" + messageToSet + " §r§6gesetzt!");


                } else {
                    p.sendMessage(Serverprefix + "§7Der Command lautet: §6§l/lock [on/off/message] [optinaler Grund]");
                }
            } else {
                p.sendMessage(Serverprefix + "§7Der Command lautet: §6§l/lock [on/off]");
            }
        } else {
            p.sendMessage(Serverprefix + "§4Dazu hast du keine Berechtigung!");
        }
        return false;
    }
}
