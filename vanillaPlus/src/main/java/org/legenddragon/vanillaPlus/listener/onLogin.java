package org.legenddragon.vanillaPlus.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.legenddragon.vanillaPlus.command.CheckActive;
import org.legenddragon.vanillaPlus.vanillaPlus;

public class onLogin implements Listener {

    public vanillaPlus plugin;
    public CheckActive checkActive;

    public onLogin(vanillaPlus plugin) {
        this.plugin = plugin;
        checkActive = new CheckActive( plugin );
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player p = (Player) e.getPlayer();

        String message = (String) plugin.ServerConfig.get().get("Server.message");
        boolean lockmode = plugin.ServerConfig.get().getBoolean("Server.LockMode");


        if ( !p.isWhitelisted() ) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§6Teilnehmer §a§l//§r §7Melde dich bei dem Owner um zugang auf den Server zu bekommen");
        }

        if (lockmode) {
            if ( !p.hasPermission("server.settings")) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§4✖§r §6§lNetzwerk§r §7ist §cgelockt§r§7! §4✖§r  §7// §c§l" + message);
            }
        } else {

            // Joins the Player for the first Time
            if ( !plugin.PlayerConfig.get().isSet("User." + p.getUniqueId())) {
                // Check if the User path is set
                if ( !plugin.PlayerConfig.get().isSet("User")) { plugin.PlayerConfig.get().createSection("User"); }

                // Creating a User
                plugin.PlayerConfig.get().createSection("User." + p.getUniqueId());
                plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".DisplayName", p.getDisplayName() );
                plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", null);
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§6Spieler erstellt! §a§l//§r §7Dir wurde ein Spieler erstellt du kannst nochmal versuchen zu joinen!");


            // Is the Player banned?
            } else if ( plugin.PlayerConfig.get().isSet("User." + p.getUniqueId() + ".ban") ) {

                // If ban Message is set. In Case of not, the Kick Message has no reason!
                if ( plugin.PlayerConfig.get().isSet("User." + p.getUniqueId() + ".banMessage")) {

                    // Ban Message
                    String banMessage = plugin.PlayerConfig.get().getString("User." + p.getUniqueId() + ".banMessage");

                    // If the player is not been permenatly banned
                    if ( plugin.PlayerConfig.get().isSet("User." + p.getUniqueId() + ".banTime") ) {
                        long unixTime = System.currentTimeMillis() / 1000L;
                        long minute = unixTime - plugin.PlayerConfig.get().getLong("User." + p.getUniqueId() + ".bannedTime");
                        long banTime = plugin.PlayerConfig.get().getLong("User." + p.getUniqueId() + ".banTime");

                        // Minutes and Hours till the player is unbanned
                        long secondUnban = (banTime - minute) % 60;
                        long minuteUnban = (banTime - minute) / 60 % 60;
                        long hourUnban = (banTime - minute) / 60 / 60;
                        long dayUnban =  hourUnban / 24 ;


                        if ( minuteUnban <= 0 && hourUnban <= 0 ) {
                            e.allow();

                            // reseting that the player is banned in config
                            plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".ban", null);
                            plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".banMessage", null);
                            // reseting the player banned time, when the player got temporal banned
                            if ( plugin.PlayerConfig.get().isSet("User." + p.getUniqueId() + ".banTime")) {
                                plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".banTime", null);
                                plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".bannedTime", null);
                            }
                        } else {
                            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§6§lServer Netzwerk \n§r§7Du wurdest gebannt!\n§4§lReason: " + banMessage + "\n\n§7Entbannt in: \n§a"+ dayUnban + " Tage " + hourUnban + " Stunden " + minuteUnban + " Minuten " + secondUnban + " Sekunden" );
                        }


                    } else {
                        e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§6§lServer Netzwerk \n§r§7Du wurdest gebannt!\n§4§lReason: " + banMessage);
                    }

                } else {
                    e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§6§lServer Netzwerk \n§r§7Du wurdest gebannt!");
                }

            }
        }

        plugin.PlayerConfig.save();
    }
}
