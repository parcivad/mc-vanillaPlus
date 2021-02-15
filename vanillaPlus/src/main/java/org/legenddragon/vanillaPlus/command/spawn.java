package org.legenddragon.vanillaPlus.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.vanillaPlus.vanillaPlus;

import static org.legenddragon.vanillaPlus.vanillaPlus.*;

public class spawn implements CommandExecutor {

    public vanillaPlus plugin;
    public CheckActive checkActive;

    public spawn(vanillaPlus plugin) {
        this.plugin = plugin;
        checkActive = new CheckActive( plugin );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        // Check if command active
        if ( !checkActive.check("spawn") ) { sender.sendMessage( Serverprefix + "§7Dieser Befehl ist §l§cnicht §r§7aktiv!"); return true; }

        if ( args.length == 0 ) {
            Player p = (Player) sender;

            p.teleport(Bukkit.getWorld("world").getSpawnLocation());
            p.sendMessage(plugin.Serverprefix + "§7Du wurdest zum §6Spawn §7teleportiert!");
        }

        return false;
    }
}
