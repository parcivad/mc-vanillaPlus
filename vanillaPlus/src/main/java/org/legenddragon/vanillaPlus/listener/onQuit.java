package org.legenddragon.vanillaPlus.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.legenddragon.vanillaPlus.vanillaPlus;

public class onQuit implements Listener {

    public vanillaPlus plugin;

    public onQuit(vanillaPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage("§7§l<<§r§c " + p.getName() + " §r§7ist §coff.");
    }
}
