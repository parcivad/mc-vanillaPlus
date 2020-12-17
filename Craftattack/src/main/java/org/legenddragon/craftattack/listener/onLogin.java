package org.legenddragon.craftattack.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.legenddragon.craftattack.craftattack;

public class onLogin implements Listener {

    public craftattack plugin;

    public onLogin(craftattack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player p = (Player) e.getPlayer();

        String message = (String) plugin.ServerConfig.get().get("Server.message");
        boolean lockmode = plugin.ServerConfig.get().getBoolean("Server.LockMode");

        if (lockmode) {
            if ( !p.hasPermission("server.settings")) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§4✖§r §6§lCraftattack Netzwerk§r §7ist §cgelockt§r§7! §4✖§r  §7// §c§l" + message);
            }
        } else {
            if ( !plugin.PlayerConfig.get().isSet("User." + p.getUniqueId())) {
                // Check if the User path is set
                if ( !plugin.PlayerConfig.get().isSet("User")) { plugin.PlayerConfig.get().createSection("User"); }

                // Creating a User
                plugin.PlayerConfig.get().createSection("User." + p.getUniqueId());
                plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".DisplayName", p.getDisplayName() );
                plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Address", p.getAddress() );
                plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", null);
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§6Spieler erstellt! §a§l//§r §7Dir wurde ein Spieler erstellt du kannst nochmal versuchen zu joinen!");
            }
        }

        plugin.PlayerConfig.save();
    }
}
