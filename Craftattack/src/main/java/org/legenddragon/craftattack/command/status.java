package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.legenddragon.craftattack.craftattack;

public class status implements CommandExecutor {

    public craftattack plugin;

    public status(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( args.length == 1 ) {
            switch (args[0]) {
                case "Owner":
                    if ( !p.hasPermission("tablist.owner")) { p.sendMessage(plugin.Serverprefix + "§4Für diesen Prefix hast du keine Berechtigung"); return true;}
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Owner");
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Dev":
                    if ( !p.hasPermission("tablist.dev")) { p.sendMessage(plugin.Serverprefix + "§4Für diesen Prefix hast du keine Berechtigung"); return true;}
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Dev");
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Building":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Building");
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Redstone":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Redstone");
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Neutral":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Neutral");
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Live":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Live");
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "REC":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "REC");
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Mining":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Mining");
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Afk":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Afk");
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Fighting":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Fighting");
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "off":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", null);
                    p.sendMessage(plugin.Serverprefix + "§7Dein Status wurde auf,§6 " + args[0] + ", §7geändert!");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
            }

        } else {
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/status {Owner/Dev/Building/Fighting/Redstone/Neutral/Live/REC/Mining/Afk/off}");
        }

        return false;
    }
}
