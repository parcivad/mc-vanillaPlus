package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;

import static org.legenddragon.craftattack.craftattack.ANSI_RED;
import static org.legenddragon.craftattack.craftattack.ANSI_RESET;

public class spawn implements CommandExecutor {

    public craftattack plugin;

    public spawn(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }


        if ( args.length == 0 ) {
            Player p = (Player) sender;

            p.teleport(Bukkit.getWorld("world").getSpawnLocation());
            p.sendMessage(plugin.Serverprefix + "§7Du wurdest zum §6Spawn §7teleportiert!");
        }

        return false;
    }
}
