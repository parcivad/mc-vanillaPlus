package org.legenddragon.vanillaPlus.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.vanillaPlus.vanillaPlus;

import static org.legenddragon.vanillaPlus.vanillaPlus.*;

public class pos implements CommandExecutor {

    public vanillaPlus plugin;
    public CheckActive checkActive;

    public pos(vanillaPlus plugin) {
        this.plugin = plugin;
        checkActive = new CheckActive( plugin );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        // Check if command active
        if ( !checkActive.check("pos") ) { sender.sendMessage( Serverprefix + "§7Dieser Befehl ist §l§cnicht §r§7aktiv!"); return true; }

        Player p = (Player) sender;

        // Command should look like: /pos save {name}
        if ( args.length == 2 && args[0].equals("save") ) {

            Location pos = p.getLocation();
            String posName = args[1].toString();

            // Catch home overwrite
            if ( posName.equals("home") ) { return true; }

            // Check. no double name
            if ( plugin.PlayerConfig.get().isSet("User." + p.getUniqueId() + "." + posName) ) { p.sendMessage(Serverprefix + "§cEs existiert bereits eine Position mit diesem Namen!"); return true; }

            // Save it in PlayerConfig
            plugin.PlayerConfig.get().set("User." + p.getUniqueId() + "." + posName, pos);
            plugin.PlayerConfig.save();

            // Sending Player a Message
            p.sendMessage(Serverprefix + "§7Die Position §9" + posName + "§7 wurde §aabgespeichert!");

        } else if ( args.length == 2 && args[0].equals("delete") ) {

            String posName = args[1].toString();

            // Catch home overwrite
            if ( posName.equals("home") ) { return true; }

            // Is there any Location with the same name
            if ( !plugin.PlayerConfig.get().isSet("User." + p.getUniqueId() + "." + posName) ) { p.sendMessage(Serverprefix + "§cEs existiert keine Position mit diesem Namen!"); return true; }

            // Save it in PlayerConfig
            plugin.PlayerConfig.get().set("User." + p.getUniqueId() + "." + posName, null);
            plugin.PlayerConfig.save();

            // Sending Player a Message
            p.sendMessage(Serverprefix + "§7Die Position §9" + posName + "§7 wurde §cgelöscht!");

        } else if ( args.length == 1 ) {

            String posName = args[0].toString();

            // Is there any Location the name
            if ( plugin.PlayerConfig.get().isSet("User." + p.getUniqueId() + "." + posName) ) {
                Location pos = plugin.PlayerConfig.get().getLocation("User." + p.getUniqueId() + "." + posName);
                p.sendMessage(Serverprefix + "§7Die Position §9" + posName + "§7 wurde aufgerufen §7[§9§l" + pos.getBlockX() + "§7, §9§l" + pos.getBlockY() + "§7, §9§l" + pos.getBlockZ() + "§7]");
            } else {
                p.sendMessage(Serverprefix + "§cDiese Location existiert unter deinem Account nicht!");
            }

        } else {
            p.sendMessage(Serverprefix + "§7Befehl:§6 /pos {save/delete/name} {name}");
        }
        return false;
    }
}
