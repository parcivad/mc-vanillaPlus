package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class infoTools {

    public Inventory defaultInv() {

        // Create Inv
        Inventory inv = Bukkit.createInventory(null, 54, "§6§lUser Informations");

        // Item Stacks
        ItemStack grayglass = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta grayglassMeta = grayglass.getItemMeta();
        grayglassMeta.setDisplayName(null);
        grayglass.setItemMeta(grayglassMeta);

        // Setting default glass
        for (int i = 0; i < 9; i++) {
            inv.setItem( i, grayglass );
        }

        inv.setItem( 17, grayglass );
        inv.setItem( 18, grayglass );
        inv.setItem( 26, grayglass );
        inv.setItem( 27, grayglass );
        inv.setItem( 35, grayglass );
        inv.setItem( 36, grayglass );
        inv.setItem( 45, grayglass );

        // Setting Options

        // WORKING ON THIS CLASS


        return inv;
    }
}
