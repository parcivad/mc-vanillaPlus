package org.legenddragon.craftattack.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.legenddragon.craftattack.craftattack;


public class onJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage("§7§l>>§r§a " + p.getName() + " §r§7ist §aon.");

    }

}
