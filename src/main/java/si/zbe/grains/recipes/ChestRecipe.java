package si.zbe.grains.recipes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import si.zbe.grains.Main;
import si.zbe.grains.utils.ItemCollections;

public class ChestRecipe {
    public ShapedRecipe getRecipe() {
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(Main.plugin, "chest"), new ItemStack(Material.CHEST, 4));
        ArrayList<Material> logs = ItemCollections.logs;

        r.shape("www","w w","www");
        r.setIngredient('w', new RecipeChoice.MaterialChoice(logs));

        return r;
    }
}
