package org.legenddragon.vanillaPlus.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.vanillaPlus.vanillaPlus;

import static org.legenddragon.vanillaPlus.vanillaPlus.*;

public class commands implements CommandExecutor {

    public vanillaPlus plugin;

    public commands(vanillaPlus plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        Player p = (Player) sender;

        // Check player permission
        if ( p.hasPermission("manage.commands")) {

            // Command should look like: /commands {command} {on/off}
            if ( args.length == 2 ) {
                String command = args[0];
                Boolean status = false;

                if ( args[1].equals("on") ) { status = true; }

                if ( plugin.ServerConfig.get().isSet("Server.commands." + command) ) {

                    plugin.ServerConfig.get().set("Server.commands." + command, status);
                    plugin.ServerConfig.save();

                    p.sendMessage( Serverprefix + "§7Der Befehlstatus wurde §agesetzt");
                } else {
                    p.sendMessage( Serverprefix + "§7Diesen Befehl gibt es §cnicht.");
                }
            } else {
                p.sendMessage( Serverprefix + "§7Befehl: §6/commands {command} {on/off}");
            }
        } else {
            p.sendMessage( Serverprefix + "§4Dafür hast du keine Berechtigung.");
        }



        return false;
    }
}
