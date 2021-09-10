package si.zbe.grains.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import si.zbe.grains.Main;

import java.util.ArrayList;

public class MinecartRecipe {
    public ShapedRecipe getHopperRecipe() {
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(Main.plugin, "minecart_hopper"), new ItemStack(Material.HOPPER_MINECART, 1));
        r.shape("   ","wsw","www");
        r.setIngredient('w', new RecipeChoice.MaterialChoice(Material.IRON_INGOT));
        r.setIngredient('s', new RecipeChoice.MaterialChoice(Material.HOPPER));

        return r;
    }
    public ShapedRecipe getChestRecipe() {
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(Main.plugin, "minecart_chest"), new ItemStack(Material.CHEST_MINECART, 1));
        r.shape("   ","wsw","www");
        r.setIngredient('w', new RecipeChoice.MaterialChoice(Material.IRON_INGOT));
        r.setIngredient('s', new RecipeChoice.MaterialChoice(Material.CHEST));

        return r;
    }
    public ShapedRecipe getFurnaceRecipe() {
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(Main.plugin, "minecart_furnace"), new ItemStack(Material.FURNACE_MINECART, 1));
        r.shape("   ","wsw","www");
        r.setIngredient('w', new RecipeChoice.MaterialChoice(Material.IRON_INGOT));
        r.setIngredient('s', new RecipeChoice.MaterialChoice(Material.FURNACE));

        return r;
    }

    public ShapedRecipe getBoomRecipe() {
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(Main.plugin, "minecart_tnt"), new ItemStack(Material.TNT_MINECART, 1));
        r.shape("   ","wsw","www");
        r.setIngredient('w', new RecipeChoice.MaterialChoice(Material.IRON_INGOT));
        r.setIngredient('s', new RecipeChoice.MaterialChoice(Material.TNT));

        return r;
    }
}
