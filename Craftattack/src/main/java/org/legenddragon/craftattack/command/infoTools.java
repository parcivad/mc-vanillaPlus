package org.legenddragon.craftattack.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.legenddragon.craftattack.craftattack;

public class infoTools {

    public Inventory defaultInv() {

        // Create Inv
        Inventory inv = Bukkit.createInventory(null, 54, "§6§lUser Informations");

        // Item Stacks
        ItemStack grayglass = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta grayglassMeta = grayglass.getItemMeta();
        grayglassMeta.setDisplayName(" ");
        grayglass.setItemMeta(grayglassMeta);

        ItemStack goldglass = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta goldglassMeta = goldglass.getItemMeta();
        goldglassMeta.setDisplayName("§6§lÜbersicht");
        goldglass.setItemMeta(goldglassMeta);

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierMeta = barrier.getItemMeta();
        barrierMeta.setDisplayName("§c§lBACK");
        barrier.setItemMeta(barrierMeta);

        // Setting default glass
        for (int i = 0; i == 9; i++) {
            inv.setItem( i, grayglass );
        }
        inv.setItem(4, goldglass);
        inv.setItem( 17, grayglass );
        inv.setItem( 18, grayglass );
        inv.setItem( 26, grayglass );
        inv.setItem( 27, grayglass );
        inv.setItem( 35, grayglass );
        inv.setItem( 36, grayglass );
        inv.setItem( 45, grayglass );
        for (int i = 46; i == 53; i++) {
            inv.setItem(i, grayglass);
        }
        inv.setItem(49, barrier);

        // Setting Options

        return inv;
    }
}
