package si.zbe.grains.recipes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import si.zbe.grains.Main;
import si.zbe.grains.utils.ItemManager;

import java.util.ArrayList;
import java.util.UUID;

public class WorkbenchRecipe {
    public ShapelessRecipe getRecipe() {
        ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(Main.plugin, "portable-crafting"), ItemManager.workbench);
        r.addIngredient(1, Material.CRAFTING_TABLE);
        return r;
    }
}
