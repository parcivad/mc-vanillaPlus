package org.legenddragon.craftattack.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.legenddragon.craftattack.command.status;
import org.legenddragon.craftattack.craftattack;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class onJoin implements Listener {

    private craftattack plugin;

    public onJoin(craftattack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage("§7§l>>§r§a " + p.getName() + " §r§7ist §aon.");

    }

}
