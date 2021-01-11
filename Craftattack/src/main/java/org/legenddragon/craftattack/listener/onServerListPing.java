package org.legenddragon.craftattack.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.legenddragon.craftattack.craftattack;

public class onServerListPing implements Listener {

    public craftattack plugin;

    public onServerListPing(craftattack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerListPin(ServerListPingEvent e) {

        // Change Motd when server is locked
        if ( plugin.ServerConfig.get().getBoolean("Server.LockMode") ) {
            e.setMotd("              §7§l[§6CRAFTATTACK§7§l]§r  §7[§a1.16.4§7]           \n§4✖§c Der Server ist zur Zeit gesperrt!");
        } else {
            e.setMotd("              §7§l[§6CRAFTATTACK§7§l]§r  §7[§a1.16.4§7]           \n§7➯§l§aServer ist online §7| §a§lNEUE FEATURES");
        }

    }
}
