package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.legenddragon.craftattack.craftattack;

public class stats implements CommandExecutor {

    public craftattack plugin;

    public stats(craftattack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        Player p = (Player) sender;

        if ( args.length == 0 ) {
            // Calling Stats
            Integer pKills = p.getStatistic(Statistic.PLAYER_KILLS);
            Integer pDeaths = p.getStatistic(Statistic.DEATHS);
            Integer pDamageTaken = p.getStatistic(Statistic.DAMAGE_TAKEN) / 10 % 50 ;
            Integer pJumps = p.getStatistic(Statistic.JUMP);
            Integer pDiamands = p.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND);
            if ( pDiamands == null ) { pDiamands = 0; }

            p.sendMessage(plugin.Serverprefix + "§7-----------< §6§lSTATS §r§7>-----------");
            p.sendMessage(plugin.Serverprefix + "§7- §6Player:§a " + p.getDisplayName());
            p.sendMessage(plugin.Serverprefix + "§7- §6Player Kills:§a " + pKills + "§c⚔");
            p.sendMessage(plugin.Serverprefix + "§7- §6Deaths:§c " + pDeaths + "☠");
            p.sendMessage(plugin.Serverprefix + "§7- §6Last Damage:§a " + pDamageTaken + "§c❤");
            p.sendMessage(plugin.Serverprefix + "§7- §6Jumps:§a " + pJumps);
            p.sendMessage(plugin.Serverprefix + "§7- §6Diamonds:§a " + pDiamands + "♦");
            p.sendMessage(plugin.Serverprefix + "§7-----------< §6§lSTATS §r§7>-----------");

        } else if ( args.length == 1 ) {

            try {
                Player p2 = Bukkit.getPlayer(args[0]);

                // Calling Stats
                Integer pKills = p2.getStatistic(Statistic.PLAYER_KILLS);
                Integer pDeaths = p2.getStatistic(Statistic.DEATHS);
                Integer pDamageTaken = p2.getStatistic(Statistic.DAMAGE_TAKEN) / 10 % 50 ;
                Integer pJumps = p2.getStatistic(Statistic.JUMP);
                Integer pDiamands = p2.getStatistic(Statistic.PICKUP, Material.DIAMOND);
                if ( pDiamands == null ) { pDiamands = 0; }

                p.sendMessage(plugin.Serverprefix + "§7-----------< §6§lSTATS §r§7>-----------");
                p.sendMessage(plugin.Serverprefix + "§7- §6Player:§a " + p2.getDisplayName());
                p.sendMessage(plugin.Serverprefix + "§7- §6Player Kills:§a " + pKills + "§c⚔");
                p.sendMessage(plugin.Serverprefix + "§7- §6Deaths:§c " + pDeaths + "☠");
                p.sendMessage(plugin.Serverprefix + "§7- §6Damage taken:§a " + pDamageTaken + "§c❤");
                p.sendMessage(plugin.Serverprefix + "§7- §6Jumps:§a " + pJumps);
                p.sendMessage(plugin.Serverprefix + "§7- §6Diamonds:§a " + pDiamands + "♦");
                p.sendMessage(plugin.Serverprefix + "§7-----------< §6§lSTATS §r§7>-----------");
            } catch (Exception ex) {
                p.sendMessage(plugin.Serverprefix + "§cDer Spieler ist nicht erreichbar!");
            }

        } else {
            p.sendMessage(plugin.Serverprefix + "§7Befehl: §6/stats {player}");
        }


        return false;
    }
}
