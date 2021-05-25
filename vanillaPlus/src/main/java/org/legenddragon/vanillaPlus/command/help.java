package org.legenddragon.vanillaPlus.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.vanillaPlus.vanillaPlus;

import static org.legenddragon.vanillaPlus.vanillaPlus.*;

public class help implements CommandExecutor {

    public vanillaPlus plugin;

    public help(vanillaPlus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        Player p = (Player) sender;

        // Sending help message
        p.sendMessage(Serverprefix + "§7Befehl: §6/tpa {to/accept/denied} {player}");
        p.sendMessage(Serverprefix + "§7Befehl: §6/inv {player}");
        p.sendMessage(Serverprefix + "§7Befehl: §6/pos {save/delete/name} {name}");
        p.sendMessage(Serverprefix + "§7Befehl: §6/spawn");
        p.sendMessage(Serverprefix + "§7Befehl: §6/stats {player}");
        p.sendMessage(Serverprefix + "§7Befehl: §6/tpa {accept/denied/to} {player}");
        p.sendMessage(Serverprefix + "§7Befehl: §6/ec {player}");
        p.sendMessage(Serverprefix + "§7Befehl: §6/lock {message/on/off} {reason}");
        p.sendMessage(Serverprefix + "§7Befehl: §6/tempban {player} {reason} {time} {m/h}");
        p.sendMessage(Serverprefix + "§7Befehl: §6/tempunban {player}");
        p.sendMessage(Serverprefix + "§7Befehl: §6/commands {command} {on/off}");

        return false;
    }
}