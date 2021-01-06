package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;

public class lock implements CommandExecutor {

    public craftattack plugin;

    public lock(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

        boolean lockmode = plugin.ServerConfig.get().getBoolean("Server.LockMode");
        Player p = (Player) sender;

        if (p.hasPermission("server.settings")) {


            if (args.length == 1) {

                if (args[0].equalsIgnoreCase("on")) {

                    p.sendMessage(plugin.Serverprefix + "§7Der Server befindet sich nun im §c§lLOCKMODE!");
                    lockmode = true;

                    String message = (String) plugin.ServerConfig.get().get("Server.message");

                    for (Player all : Bukkit.getOnlinePlayers() ) {
                        all.kickPlayer("§4✖§r §6§lCraftattack Netzwerk§r §7ist §cgelockt§r§7! §4✖§r  §7// §c§l" + message);
                    }

                }
                if (args[0].equalsIgnoreCase("off")) {

                    p.sendMessage(plugin.Serverprefix + "§7Der Server befindet sich nun nicht mehr im §a§lLOCKMODE!");
                    lockmode = false;
                }

                plugin.ServerConfig.get().set("Server.LockMode", lockmode);

                plugin.ServerConfig.save();

            } else if ( args.length == 2) {

                if (args[0].equalsIgnoreCase("message")) {

                    String message = args[1];

                    plugin.ServerConfig.get().set("Server.message", message);
                    plugin.ServerConfig.save();
                    p.sendMessage(plugin.Serverprefix + "§7Server LockMessage wurde auf: §r§c§l" + message + " §r§6gesetzt!");

                } else {

                    p.sendMessage(plugin.Serverprefix + "§7Der Command lautet: §6§l/lock [on/off/message] [optinaler Grund]");

                }

            } else {

                p.sendMessage(plugin.Serverprefix + "§7Der Command lautet: §6§l/lock [on/off]");

            }

        } else {

            p.sendMessage(plugin.Serverprefix + "§4Dazu hast du keine Berechtigung!");

        }
        return false;
    }
}
