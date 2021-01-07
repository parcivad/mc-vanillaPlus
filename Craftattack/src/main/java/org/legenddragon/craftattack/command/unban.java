package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.legenddragon.craftattack.craftattack;
import sun.security.x509.UniqueIdentity;

public class unban implements CommandExecutor {

    public craftattack plugin;

    public unban(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( !p.hasPermission("manage.unban")) { p.sendMessage(plugin.Serverprefix + "§4Du hast dafür keine Berechtigung"); return true;}

        if ( args.length == 1 ) {

            OfflinePlayer p2 = Bukkit.getOfflinePlayer(args[0]);

            if ( plugin.PlayerConfig.get().isSet("User." + p2.getUniqueId() + ".ban") ) {

                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".ban", null);
                plugin.PlayerConfig.get().set("User." + p2.getUniqueId() + ".unban", null);

                p.sendMessage(plugin.Serverprefix + "§7Player " + p2.getName() + " §aunbanned!");

            } else {
                p.sendMessage(plugin.Serverprefix + "§cPlayer is not banned ");
            }

        } else {
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/unban {player} ");
        }

        return false;
    }
}