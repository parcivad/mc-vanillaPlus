package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.EnderChest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.legenddragon.craftattack.craftattack;


public class ec implements CommandExecutor {

    public craftattack plugin;

    public ec(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( args.length == 1 ) {
            if ( !p.hasPermission("enderchest.open")) { p.sendMessage(plugin.Serverprefix + "§4Du hast dafür keine Berechtigung");return true; }
            // You can open Player Ender Chest with a Command
            try {
                Player p2 = Bukkit.getPlayer(args[0]);

                Inventory enderChest = p2.getEnderChest();
                p.openInventory(enderChest);
            } catch (Exception ex) {
                p.sendMessage(plugin.Serverprefix + "§cSpieler konnte nicht gefunden werden!");
            }
        } else {
            if ( p.getStatistic(Statistic.ENDERCHEST_OPENED) >= 1 ) {
                Inventory enderChest = p.getEnderChest();
                p.openInventory(enderChest);
            } else {
                p.sendMessage(plugin.Serverprefix + "§7Es tut mir leid, du hast noch nie eine §5EnderChest §7benutzt!");
            }
        }
        return false;
    }
}
