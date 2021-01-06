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
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Dev":
                    if ( !p.hasPermission("tablist.dev")) { p.sendMessage(plugin.Serverprefix + "§4Für diesen Prefix hast du keine Berechtigung"); return true;}
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Dev");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Building":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Building");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Redstone":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Redstone");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Neutral":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Neutral");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Live":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Live");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "REC":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "REC");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Mining":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Mining");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Afk":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Afk");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Fighting":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Fighting");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Ehe":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Ehe");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "Killer":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", "Killer");
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
                case "off":
                    plugin.PlayerConfig.get().set("User." + p.getUniqueId() + ".Status", null);
                    p.kickPlayer("§6Status wurde geändert! §c§l//§r §7Joine nochmal um deinen Aktuellen Status zu sehen!");
                    break;
            }

        } else {
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/status {Killer/Ehe/Building/Fighting/Redstone/Neutral/Live/REC/Mining/Afk/off}");
        }

        return false;
    }
}
