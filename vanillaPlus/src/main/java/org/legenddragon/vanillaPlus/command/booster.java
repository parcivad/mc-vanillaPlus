package org.legenddragon.vanillaPlus.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.vanillaPlus.vanillaPlus;

import static org.legenddragon.vanillaPlus.vanillaPlus.*;

public class booster implements CommandExecutor {

    public vanillaPlus plugin;
    public CheckActive checkActive;

    public booster(vanillaPlus plugin) {
        this.plugin = plugin;
        checkActive = new CheckActive( plugin );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        // Check if command active
        if ( !checkActive.check("booster") ) { sender.sendMessage( Serverprefix + "§7Dieser Befehl ist §l§cnicht §r§7aktiv!"); return true; }

        Player p = (Player) sender;

        if ( !p.hasPermission("server.setting")) { return true; }

        if ( args.length == 0 ) {
            Location pos = p.getLocation();
            plugin.ServerConfig.get().set("Server.Booster-Height", pos);
            plugin.ServerConfig.save();
            p.sendMessage(plugin.Serverprefix + "§atask finished");
        } else {
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/settings {booster}");
        }

        return false;
    }
}
