package org.legenddragon.craftattack.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.legenddragon.craftattack.craftattack;

public class onPlayerDeath implements Listener {

    public craftattack plugin;

    public onPlayerDeath(craftattack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity();
        Player killer = p.getKiller();
        Location pos = p.getLocation();

        if ( killer instanceof Player) {
            e.setDeathMessage("§c☠ " + p.getName() + " §6killed by §c" + killer.getName());
            p.sendMessage(plugin.Serverprefix + "§7Todes Kooridnaten: [§9§l" + pos.getBlockX() + "§7, §9§l" + pos.getBlockY() + "§7, §9§l" + pos.getBlockZ() + "§7]");
        } else {
            e.setDeathMessage("§c☠ " + p.getName());
            p.sendMessage(plugin.Serverprefix + "§7Todes Kooridnaten: [§9§l" + pos.getBlockX() + "§7, §9§l" + pos.getBlockY() + "§7, §9§l" + pos.getBlockZ() + "§7]");
        }

        // Elytra Item Stack
        ItemStack Elytra = new ItemStack(Material.ELYTRA);
        ItemMeta ElytraMeta = Elytra.getItemMeta();
        ElytraMeta.setDisplayName(p.getUniqueId().toString());
        Elytra.setAmount(1);
        Elytra.setItemMeta(ElytraMeta);

        if ( p.getInventory().getChestplate() == null ) {
            return;
        } else if ( p.getInventory().getChestplate().getItemMeta().getDisplayName().equals(Elytra.getItemMeta().getDisplayName()) ) {
            e.getDrops().clear();
            e.setKeepInventory(true);
        }
    }
}
