package org.legenddragon.craftattack.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;

public class help implements CommandExecutor {

    public craftattack plugin;

    public help(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( args.length == 0 ) {
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/ec {player}");
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/inv {player}");
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/pos {save/delete/name} {name}");
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/spawn");
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/stats {player}");
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/status {mehr info: /status}");
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/tpa {accept/denied/to} {player}");
        }

        return false;
    }
}
