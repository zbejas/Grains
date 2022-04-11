package si.zbe.grains.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import si.zbe.grains.Main;
import si.zbe.grains.utils.ItemCollections;

import java.util.ArrayList;

public class RedstoneRecipe {
    public ShapedRecipe getRepeaterRecipe() {
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(Main.plugin, "grepeater"), new ItemStack(Material.REPEATER, 1));

        r.shape("r r","srs","www");
        r.setIngredient('w', new RecipeChoice.MaterialChoice(Material.STONE));
        r.setIngredient('r', new RecipeChoice.MaterialChoice(Material.REDSTONE));
        r.setIngredient('s', new RecipeChoice.MaterialChoice(Material.STICK));

        return r;
    }
    public ShapedRecipe getHopperRecipe() {
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(Main.plugin, "ghopper"), new ItemStack(Material.HOPPER, 1));
        ArrayList<Material> logs = ItemCollections.logs;

        r.shape("ili","ili"," i ");
        r.setIngredient('i', new RecipeChoice.MaterialChoice(Material.IRON_INGOT));
        r.setIngredient('l', new RecipeChoice.MaterialChoice(logs));

        return r;
    }

}
