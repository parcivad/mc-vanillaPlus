package org.legenddragon.craftattack.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.parser.Yytoken;
import org.legenddragon.craftattack.craftattack;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.legenddragon.craftattack.craftattack.*;

public class info implements CommandExecutor {

    public craftattack plugin;

    public infoTools tools;

    public info(craftattack plugin) {
        this.plugin = plugin;
        tools = new infoTools();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
        // Cancel the command for console
        if ( !(sender instanceof Player) ) { System.out.println(ANSI_RED + " That Command is not optimized for console!" + ANSI_RESET); return true; }

        Player p = (Player) sender;

        // Command should look like: /info
        if ( args.length == 0 ) {

            // Creating Deafult Info Inv
            Inventory inv = tools.defaultInv();

            p.openInventory(inv);

        } else {
            // Sending Message
            p.sendMessage(Serverprefix + "§7Befehl: §6/info");

        }



        return false;
    }

}
