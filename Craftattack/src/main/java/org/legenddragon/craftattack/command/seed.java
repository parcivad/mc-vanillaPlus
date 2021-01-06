package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;

public class seed implements CommandExecutor {

    public craftattack plugin;

    public seed(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( !p.isOp() ) {
            p.sendMessage(plugin.Serverprefix + "§4Seed ist nicht zugänglich");
        } else {
            p.sendMessage(plugin.Serverprefix + "§aSeed: " + Bukkit.getWorld(p.getWorld().getName()).getSeed());
        }

        return false;
    }
}