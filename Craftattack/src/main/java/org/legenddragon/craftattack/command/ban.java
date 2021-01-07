package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;

public class ban implements CommandExecutor {

    public craftattack plugin;

    public ban(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( !p.hasPermission("manage.ban")) { p.sendMessage(plugin.Serverprefix + "§4Du hast dafür keine Berechtigung"); return true;}

        if ( args.length == 1 ) {
            Player p2 = Bukkit.getPlayer( args[0] );

            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);

            p.sendMessage(plugin.Serverprefix + "§6Player " + p.getName() + " §abanned!");
            p.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!");

        } else if ( args.length == 2) {
            Player p2 = Bukkit.getPlayer( args[0] );
            String reason = args[1];

            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", true);
            plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".banMessage", reason);

            p.sendMessage(plugin.Serverprefix + "§7Player " + p2.getName() + " §abanned!");
            p2.kickPlayer("§6§lServer Netzwerk \n§r§7Du wurdest gebannt!\n§4§lReason: " + reason);

        } else {
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/ban {player} {reason}");
        }

        return false;
    }
}