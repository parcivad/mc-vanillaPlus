package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.block.EnderChest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.legenddragon.craftattack.craftattack;

import static org.legenddragon.craftattack.craftattack.*;


public class ec implements CommandExecutor {

    public craftattack plugin;

    public ec(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        Player p = (Player) sender;

        // Command should look like: /ec Player
        if ( args.length == 1 ) {
            // Check if the player has permission to open a ender chest from another player
            if ( !p.hasPermission("enderchest.open")) { p.sendMessage(Serverprefix + "§4Du hast dafür keine Berechtigung");return true; }

            // Check if the player is on the Server
            try {
                Player p2 = Bukkit.getPlayer(args[0]);

                Inventory enderChest = p2.getEnderChest();
                p.openInventory(enderChest);
            } catch (Exception ex) {
                p.sendMessage(Serverprefix + "§cSpieler konnte nicht gefunden werden!");
            }

        // Command should look like: /ec
        } else {
            // Check if the player ever opened a enderchest
            if ( p.getStatistic(Statistic.ENDERCHEST_OPENED) >= 1 ) {
                Inventory enderChest = p.getEnderChest();
                p.openInventory(enderChest);
            } else {
                p.sendMessage(Serverprefix + "§7Es tut mir leid, du hast noch nie eine §5EnderChest §7benutzt!");
            }
        }
        return false;
    }
}
