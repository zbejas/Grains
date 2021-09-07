package si.zbe.grains.utils;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemManager {
    public static ItemStack workbench;

    public static void init() {
        createWorkbench();
    }

    private static void createWorkbench() {
        ItemStack item = new ItemStack(Material.CRAFTING_TABLE, 1);
        ItemMeta itemmeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + LanguageManager.get("workbench.lore"));
        itemmeta.setDisplayName(ChatColor.GOLD + LanguageManager.get("workbench.name"));
        itemmeta.setLore(lore);
        item.setItemMeta(itemmeta);
        workbench = item;
    }
}
