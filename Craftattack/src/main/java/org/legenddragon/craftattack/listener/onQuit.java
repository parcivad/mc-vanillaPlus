package org.legenddragon.craftattack.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.legenddragon.craftattack.craftattack;

public class onQuit implements Listener {

    public craftattack plugin;

    public onQuit(craftattack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage("§7§l<<§r§c " + p.getName() + " §r§7ist §coff.");
    }
}
