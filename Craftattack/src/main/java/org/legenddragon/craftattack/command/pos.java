package org.legenddragon.craftattack.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;

public class pos implements CommandExecutor {

    public craftattack plugin;

    public pos(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( args.length == 2 && args[0].equals("save") ) {
            //position saves in PlayerConfig under the Player
            Location pos = p.getLocation();
            String posName = args[1].toString();

            // Is there any Location with the same name
            if ( plugin.PlayerConfig.get().isSet("User." + p.getUniqueId() + "." + posName) ) { p.sendMessage(plugin.Serverprefix + "§cEs existiert bereits eine Position mit diesem Namen!"); }

            // Save it in PlayerConfig
            plugin.PlayerConfig.get().set("User." + p.getUniqueId() + "." + posName, pos);
            plugin.PlayerConfig.save();

            p.sendMessage(plugin.Serverprefix + "§7Die Position §9" + posName + "§7 wurde §aabgespeichert!");
        } else if ( args.length == 1 ) {
            //searching for name in PlayerConfig under the Player and send coordinates in the Chat
            String posName = args[0].toString();

            // Is there any Location the name
            if ( plugin.PlayerConfig.get().isSet("User." + p.getUniqueId() + "." + posName) ) {
                Location pos = plugin.PlayerConfig.get().getLocation("User." + p.getUniqueId() + "." + posName);
                p.sendMessage(plugin.Serverprefix + "§7Die Position §9" + posName + "§7 wurde aufgerufen §7[§9§l" + pos.getBlockX() + "§7, §9§l" + pos.getBlockY() + "§7, §9§l" + pos.getBlockZ() + "§7]");
            } else {
                p.sendMessage(plugin.Serverprefix + "§cDiese Location existiert unter deinem Account nicht!");
            }
        } else {
            p.sendMessage(plugin.Serverprefix + "§7Befehl:§6 /pos {save/name} {name}");
        }
        return false;
    }
}
