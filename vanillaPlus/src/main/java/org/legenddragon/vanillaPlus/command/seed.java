package org.legenddragon.vanillaPlus.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.vanillaPlus.vanillaPlus;

import static org.legenddragon.vanillaPlus.vanillaPlus.*;

public class seed implements CommandExecutor {

    public vanillaPlus plugin;
    public CheckActive checkActive;

    public seed(vanillaPlus plugin) {
        this.plugin = plugin;
        checkActive = new CheckActive( plugin );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        // Check if command active
        if ( !checkActive.check("seed") ) { sender.sendMessage( Serverprefix + "§7Dieser Befehl ist §l§cnicht §r§7aktiv!"); return true; }

        Player p = (Player) sender;

        // If the Player is op...
        if ( !p.isOp() ) {
            p.sendMessage( Serverprefix + "§4Seed ist nicht zugänglich");
        } else {
            p.sendMessage( Serverprefix + "§aSeed: " + Bukkit.getWorld(p.getWorld().getName()).getSeed());
        }

        return false;
    }
}