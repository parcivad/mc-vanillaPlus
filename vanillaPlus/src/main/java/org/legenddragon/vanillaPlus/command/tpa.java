package org.legenddragon.vanillaPlus.command;

import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.vanillaPlus.vanillaPlus;


import static org.legenddragon.vanillaPlus.vanillaPlus.*;


public class tpa implements CommandExecutor {

    public vanillaPlus plugin;
    public CheckActive checkActive;

    public tpa(vanillaPlus plugin) {
        this.plugin = plugin;
        checkActive = new CheckActive( plugin );
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        // Check if command active
        if ( !checkActive.check("tpa") ) { sender.sendMessage( Serverprefix + "§7Dieser Befehl ist §l§cnicht §r§7aktiv!"); return true; }


        Player p = (Player) sender;

        if ( args.length == 1 && Bukkit.getPlayer(args[0]) instanceof Player) {
            if ( p == Bukkit.getPlayer(args[0])) { p.sendMessage(Serverprefix + "§cDu kannst dich nicht zu dir selbst teleportieren!"); return true; }
            // Is the player on the server
            try {
                // Getting player to send
                Player p2 = Bukkit.getPlayer(args[0]);

                // Set it in a HashMap
                vanillaPlus.tpa.put(p2, p);

                // Text Components ---------------------------------------- MESSAGE FOR PLAYER 1
                TextComponent defaultMessagePlayer1 = new TextComponent(Serverprefix + "§7Die Anfrage wurde an §6" + p2.getDisplayName() + "§7 geschickt!");

                TextComponent revoke = new TextComponent(" §7[§cREVOKE§7]");
                revoke.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa revoke"));
                revoke.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§cTPA zurücknehmen")));

                TextComponent confirmation = new TextComponent();
                confirmation.addExtra(defaultMessagePlayer1);
                confirmation.addExtra(revoke);


                // Text Components ---------------------------------------- MESSAGE FOR PLAYER 2
                TextComponent defaultMessagePlayer2 = new TextComponent(Serverprefix + "§7Der Spieler §6" + p.getDisplayName() + "§r§7 möchte sich zu dir §cteleportieren§7! Entscheide: §a§l/tpa ");

                TextComponent accept = new TextComponent("§a§laccept§r§7/");
                accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa accept"));
                accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§a§lTPA akzeptieren")));

                TextComponent denied = new TextComponent("§c§ldenied");
                denied.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa denied"));
                denied.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§c§lTPA ablehnen")));

                TextComponent acceptanddenied = new TextComponent();
                acceptanddenied.addExtra(defaultMessagePlayer2);
                acceptanddenied.addExtra(accept);
                acceptanddenied.addExtra(denied);


                // Send Message
                p2.spigot().sendMessage(acceptanddenied);
                p.spigot().sendMessage(confirmation);

            } catch (Exception ex) {
                p.sendMessage(Serverprefix + "§cDer Spieler ist nicht erreichbar.");
            }

        } else if ( args.length == 1 && args[0].equals("accept")) {
            // Is the Player online check
            try {
                // Getting Player asked
                Player p2 = vanillaPlus.tpa.get(p);

                p2.teleport(p.getLocation());

                p2.sendMessage(Serverprefix + "§7Teleport Anfrage §aangenommen!");
            } catch (Exception ex) {
                p.sendMessage(Serverprefix + "§cDer Spieler ist nicht mehr erreichbar.");
            }
            // Clearing HashMap
            vanillaPlus.tpa.clear();
        } else if ( args.length == 1 && args[0].equals("denied")) {
            // Is the Player online check
            try {
                // Getting Player asked
                Player p2 = vanillaPlus.tpa.get(p);

                p2.sendMessage(Serverprefix + "§7Teleport Anfrage §cabgelehnt!");

            } catch (Exception ex) {
                p.sendMessage(Serverprefix + "§cDer Spieler ist nicht mehr erreichbar.");
            }
            // Clearing HashMap
            vanillaPlus.tpa.clear();
        } else if ( args.length == 1 && args[0].equals("revoke")) {
            // Is the Player online check
            try {
                vanillaPlus.tpa.clear();

                p.sendMessage(Serverprefix + "§7Teleport Anfrage §czurückgenommen!");

            } catch (Exception ex) {
                p.sendMessage(Serverprefix + "§cDer Spieler ist nicht mehr erreichbar.");
            }
            // Clearing HashMap
            vanillaPlus.tpa.clear();
        } else {
            p.sendMessage(Serverprefix + "§7Befehl: §6/tpa {player/revoke/accept/denied} {player}");
        }

        return false;
    }
}
