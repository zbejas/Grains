package si.zbe.grains.utils;


import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

/*
  ?  ItemManager needs to be loaded before events, commands and recipes.
 */

public class ItemManager {
    public static ItemStack workbench;
    public static ItemStack enderchest;

    public static void init() {
        ItemCollections.init();
        createWorkbench();
        createEnderChest();
    }

    // TODO: add glow to all items
    private static ItemStack addGlow(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    private static void createWorkbench() {
        ItemStack item = addGlow(new ItemStack(Material.CRAFTING_TABLE, 1));
        ItemMeta itemmeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + LanguageManager.get("workbench.lore"));
        itemmeta.setDisplayName(ChatColor.GOLD + LanguageManager.get("workbench.name"));
        itemmeta.setLore(lore);
        item.setItemMeta(itemmeta);
        workbench = item;
    }
    private static void createEnderChest() {
        ItemStack item = addGlow(new ItemStack(Material.ENDER_CHEST, 1));
        ItemMeta itemmeta = item.getItemMeta();
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + LanguageManager.get("enderchest.lore"));
        itemmeta.setDisplayName(ChatColor.GOLD + LanguageManager.get("enderchest.name"));
        itemmeta.setLore(lore);
        item.setItemMeta(itemmeta);
        enderchest = item;
    }
}
