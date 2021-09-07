package si.zbe.grains.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import si.zbe.grains.Main;

public class MelonRecipe {
    public static ShapelessRecipe getRecipe() {
        ShapelessRecipe r = new ShapelessRecipe(new NamespacedKey(Main.plugin, "melonSlices"), new ItemStack(Material.MELON_SLICE, 9));
        r.addIngredient(1, Material.MELON);

        return r;
    }
}
