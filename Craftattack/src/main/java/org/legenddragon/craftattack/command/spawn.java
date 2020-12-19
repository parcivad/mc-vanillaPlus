package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;

public class spawn implements CommandExecutor {

    public craftattack plugin;

    public spawn(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {

        if ( args.length == 0 ) {
            Player p = (Player) sender;

            p.teleport(Bukkit.getWorld("world").getSpawnLocation());
            p.sendMessage(plugin.Serverprefix + "§7Du wurdest zum §6Spawn §7teleportiert!");
        }

        return false;
    }
}
