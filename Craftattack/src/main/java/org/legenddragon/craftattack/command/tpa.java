package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;


public class tpa implements CommandExecutor {

    public craftattack plugin;

    public tpa(craftattack plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( args.length == 2 && args[0].equals("to")) {
            if ( p == Bukkit.getPlayer(args[1])) { p.sendMessage(plugin.Serverprefix + "§cDu kannst dich nicht zu dir selbst teleportieren!"); return true; }
            // Is the player on the server
            try {
                // Getting player to send
                Player p2 = Bukkit.getPlayer(args[1]);

                // Set it in a HashMap
                craftattack.tpa.put(p2, p);

                // Send Message
                p2.sendMessage(plugin.Serverprefix + "§7Der Spieler §6" + p.getDisplayName() + "§r§7 möchte sich zu dir §cteleportieren§7! Entscheide: §a§l/tpa accept§r§7/§l§cdenied§r§7 ein.");
                p.sendMessage(plugin.Serverprefix + "§7Die Anfrage wurde an §6" + p2.getDisplayName() + "§7 geschickt!");

            } catch (Exception ex) {
                p.sendMessage(plugin.Serverprefix + "§cDer Spieler ist nicht erreichbar.");
            }

        } else if ( args.length == 1 && args[0].equals("accept")) {
            // Is the Player online check
            try {
                // Getting Player asked
                Player p2 = craftattack.tpa.get(p);

                p2.teleport(p.getLocation());

                p2.sendMessage(plugin.Serverprefix + "§7Teleport Anfrage §aangenommen!");
            } catch (Exception ex) {
                p.sendMessage(plugin.Serverprefix + "§cDer Spieler ist nicht mehr erreichbar.");
            }
            // Clearing HashMap
            craftattack.tpa.clear();
        } else if ( args.length == 1 && args[0].equals("denied")) {
            // Is the Player online check
            try {
                // Getting Player asked
                Player p2 = craftattack.tpa.get(p);

                p2.sendMessage(plugin.Serverprefix + "§7Teleport Anfrage §cabgelehnt!");

            } catch (Exception ex) {
                p.sendMessage(plugin.Serverprefix + "§cDer Spieler ist nicht mehr erreichbar.");
            }
            // Clearing HashMap
            craftattack.tpa.clear();
        } else {
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/tpa {to/accept/denied} {player}");
        }

        return false;
    }
}
