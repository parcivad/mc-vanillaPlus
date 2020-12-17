package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.legenddragon.craftattack.craftattack;

public class inv implements CommandExecutor {

    public craftattack plugin;

    public inv(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( args.length == 1 ) {
            if ( p.hasPermission("invsee")) {
                try {
                    Player p2 = Bukkit.getPlayer(args[0]);
                    Inventory p2inv = p2.getInventory();
                    p.openInventory(p2inv);
                } catch (Exception ex) {
                    p.sendMessage(plugin.Serverprefix + "§cDieser Spieler ist nicht erreichbar!");
                }
            } else {
                p.sendMessage(plugin.Serverprefix + "§4Du hast dafür keine Berechtigung!");
            }
        } else {
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/inv {player}");
        }
        return false;
    }
}
