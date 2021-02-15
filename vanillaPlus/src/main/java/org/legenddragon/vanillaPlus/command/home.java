package org.legenddragon.vanillaPlus.command;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.vanillaPlus.vanillaPlus;

import static org.legenddragon.vanillaPlus.vanillaPlus.*;

public class home implements CommandExecutor {

    public vanillaPlus plugin;
    public CheckActive checkActive;

    public home(vanillaPlus plugin) {
        this.plugin = plugin;
        checkActive = new CheckActive( plugin );
    }

    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if (!(sender instanceof Player)) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        // Check if command active
        if (!checkActive.check("home")) { sender.sendMessage(Serverprefix + "§7Dieser Befehl ist §l§cnicht §r§7aktiv!"); return true; }

        // Player
        Player p = (Player) sender;

        // Command should look like: /home set
        if ( args.length == 1 && args[0].equals("set") ) {
            // Player Location
            Location loc = p.getLocation();

            plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".home", loc);
            plugin.PlayerConfig.save();
            // Send Feedback
            p.sendMessage( Serverprefix + "§7Dein §6Home §7wurde §aabgespeichert. §7[§9§l " + loc.getBlockX() + "§7, §9§l" + loc.getBlockY() + "§7, §9§l" + loc.getBlockZ() + "§7]");

        // Command should look like: /home remove
        } else if ( args.length == 1 && args[0].equals("remove") ) {

            plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".home", null);
            plugin.PlayerConfig.save();
            // Send Feedback
            p.sendMessage( Serverprefix + "§7Dein §6Home §7wurde §cgelöscht.");

        // Command should look like: /home
        } else if ( args.length == 0 ) {

            // Check last use
            if ( !checkTimer(p) && !p.hasPermission("home.noTimer")) { return false; } else { setTimer(p); }

            if ( plugin.PlayerConfig.get().isSet("User." + p.getUniqueId() + ".home")) {
                // Home Location
                Location loc = plugin.PlayerConfig.get().getLocation("User." + p.getUniqueId() + ".home");

                p.teleport(loc);
                p.sendMessage( Serverprefix + "§7Du wurdest zu deinem §6Home teleportiert.");
            } else {
                p.sendMessage( Serverprefix + "§7Du hast §ckein §6Home!");
                resetTimer(p);
            }
        } else {
            p.sendMessage( Serverprefix + "§7Befehl: §6/home {set/remove}");
        }
        return false;
    }

    private void setTimer(Player p) {
        // Timestamp
        long unixTime = System.currentTimeMillis() / 1000L;

        plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".homeTimeout", unixTime);
        plugin.PlayerConfig.save();
    }

    private void resetTimer(Player p) {
        plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".homeTimeout", null);
        plugin.PlayerConfig.save();
    }

    private boolean checkTimer(Player p) {
        // Timestamp
        long unixTime = System.currentTimeMillis() / 1000L;
        long difference = unixTime - plugin.PlayerConfig.get().getLong("User." + p.getUniqueId() + ".homeTimeout");
        long timetowait = 300 - difference;

        if ( difference >= 300 ) {
            return true;
        } else {
            if ( !p.hasPermission("home.noTimer") ) {
                p.sendMessage( Serverprefix + "§cDieser Befehl kann erst in " + timetowait + "sek. genutzt werden!");
            }
        }

        return false;
    }
}
