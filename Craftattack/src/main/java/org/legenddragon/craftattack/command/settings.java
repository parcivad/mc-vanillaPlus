package org.legenddragon.craftattack.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;

public class settings implements CommandExecutor {

    public craftattack plugin;

    public settings(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( !p.hasPermission("server.setting")) { return true; }

        if ( args[0].equals("booster")) {
            Location pos = p.getLocation();
            plugin.ServerConfig.get().set("Server.Booster-Height", pos);
            plugin.ServerConfig.save();
            p.sendMessage(plugin.Serverprefix + "§atask finished");
        } else {
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/settings {booster/jumpoffmiddle/jumpoffradius}");
        }

        return false;
    }
}
