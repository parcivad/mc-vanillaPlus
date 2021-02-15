package org.legenddragon.vanillaPlus.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.legenddragon.vanillaPlus.vanillaPlus;

public class onServerListPing implements Listener {

    public vanillaPlus plugin;

    public onServerListPing(vanillaPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerListPin(ServerListPingEvent e) {

        if ( !plugin.ServerConfig.get().isSet("Server.Motd") ) {
            plugin.ServerConfig.get().set("Server.MotdLocked", "              §7§l[§6CRAFTATTACK§7§l]§r  §7[§a1.16.4§7]           \n§4✖§c Der Server ist zur Zeit gesperrt!");
            plugin.ServerConfig.get().set("Server.Motd", "              §7§l[§6CRAFTATTACK§7§l]§r  §7[§a1.16.4§7]           \n§7➯§l§aServer ist online §7| §a§lNEUE FEATURES");
            plugin.saveConfig();
        }

        // Getting Motd
        String motd = plugin.ServerConfig.get().getString("Server.Motd");
        String motdlocked = plugin.ServerConfig.get().getString( "Server.MotdLocked");

        // Change Motd when server is locked
        if ( plugin.ServerConfig.get().getBoolean("Server.LockMode") ) {
            e.setMotd(motdlocked);
        } else {
            e.setMotd(motd);
        }

    }
}
