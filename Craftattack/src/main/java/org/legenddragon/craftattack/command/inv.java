package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.legenddragon.craftattack.craftattack;

import static org.legenddragon.craftattack.craftattack.*;

public class inv implements CommandExecutor {

    public craftattack plugin;

    public inv(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        Player p = (Player) sender;

        // Command should look like: /inv Player
        if ( args.length == 1 ) {
            // Has the player permission
            if ( p.hasPermission("invsee")) {
                // Catch if the player is offline
                try {
                    Player p2 = Bukkit.getPlayer(args[0]);
                    Inventory p2inv = p2.getInventory();
                    p.openInventory(p2inv);
                } catch (Exception ex) {
                    p.sendMessage(Serverprefix + "§cDieser Spieler ist nicht erreichbar!");
                }

            } else {
                p.sendMessage(Serverprefix + "§4Du hast dafür keine Berechtigung!");
            }
        } else {
            p.sendMessage(Serverprefix + "§7Befehl: §6/inv {player}");
        }
        return false;
    }
}
