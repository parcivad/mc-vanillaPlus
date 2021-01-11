package org.legenddragon.craftattack.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.legenddragon.craftattack.craftattack;

import static org.legenddragon.craftattack.craftattack.Serverprefix;

public class onElytraToggle implements Listener {

    public craftattack plugin;

    public onElytraToggle(craftattack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onElytraToggle(EntityToggleGlideEvent e) {
        Player p = (Player) e.getEntity();

        ItemStack Elytra = new ItemStack(Material.ELYTRA);
        ItemMeta ElytraMeta = Elytra.getItemMeta();
        ElytraMeta.setDisplayName(p.getUniqueId().toString());
        Elytra.setAmount(1);
        Elytra.setItemMeta(ElytraMeta);

        if ( !e.isGliding() ) {
            if ( p.getInventory().getChestplate() == null ) {
                return;
            } else if ( p.getInventory().getChestplate().getItemMeta().getDisplayName().equals(Elytra.getItemMeta().getDisplayName()) ) {
                p.getInventory().setChestplate(null);
                p.sendMessage(Serverprefix + "§7Die Flug §dElytra §7wurde dir §centzogen!");
            }
        }
    }
}
