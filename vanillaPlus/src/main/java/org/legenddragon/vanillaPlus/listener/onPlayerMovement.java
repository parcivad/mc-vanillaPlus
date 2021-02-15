package org.legenddragon.vanillaPlus.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.legenddragon.vanillaPlus.vanillaPlus;

public class onPlayerMovement implements Listener {

    public vanillaPlus plugin;

    public onPlayerMovement(vanillaPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent e) {

        if ( !plugin.ServerConfig.get().isSet("Server.Booster-Height") ) { return; }

        Player p = e.getPlayer();
        Location pos = e.getPlayer().getLocation();

        Location booster = plugin.ServerConfig.get().getLocation("Server.Booster-Height");

        Location ploc = p.getLocation();
        Location jumpoffmiddle = plugin.ServerConfig.get().getLocation("Server.JumpOffMiddle");
        Integer Radius = plugin.ServerConfig.get().getInt("Server.JumpOffRadius");

        if ( pos.getBlockX() == booster.getBlockX() && pos.getBlockZ() == booster.getBlockZ() && pos.getBlockY() < booster.getBlockY()) {

            p.addPotionEffect( new PotionEffect( PotionEffectType.LEVITATION, 5, 30, false, false, false));

        } else if ( pos.getBlockX() == booster.getBlockX() && pos.getBlockZ() == booster.getBlockZ() && pos.getBlockY() >= booster.getBlockY() ) {

            p.removePotionEffect( PotionEffectType.LEVITATION );
            GiveElytra(p);
        }

    }

    public void GiveElytra(Player p) {

        ItemStack ToGive = new ItemStack(Material.ELYTRA);
        ItemMeta ToGiveMeta = ToGive.getItemMeta();
        ToGiveMeta.setDisplayName(p.getUniqueId().toString());
        ToGive.setAmount(1);
        ToGive.setItemMeta(ToGiveMeta);

        if ( p.getInventory().getChestplate() != null ) { if (p.getInventory().getChestplate().getItemMeta().getDisplayName().equals(ToGive.getItemMeta().getDisplayName())) { return; } }
        if (p.getInventory().getChestplate() != null && p.getInventory().firstEmpty() == -1) { return; }

        if ( p.getInventory().getChestplate() == null ) {

            p.getInventory().setChestplate(ToGive);

        } else if ( p.getInventory().getChestplate() != null ){

            p.getInventory().addItem(p.getInventory().getChestplate());
            p.getInventory().setChestplate(ToGive);

        }

    }
}
