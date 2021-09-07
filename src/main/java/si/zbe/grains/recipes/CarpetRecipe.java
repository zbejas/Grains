package si.zbe.grains.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import si.zbe.grains.Main;

import java.util.ArrayList;

public class CarpetRecipe {
    public ShapedRecipe getCarpetRecipe(Material outputCarpet, Material inputDye, String key) {
        ShapedRecipe r = new ShapedRecipe(new NamespacedKey(Main.plugin, key), new ItemStack(outputCarpet, 8));
        ArrayList <Material> carpets = new ArrayList <Material>();
        carpets.add(Material.BLACK_CARPET);
        carpets.add(Material.BLUE_CARPET);
        carpets.add(Material.BROWN_CARPET);
        carpets.add(Material.CYAN_CARPET);
        carpets.add(Material.GRAY_CARPET);
        carpets.add(Material.GREEN_CARPET);
        carpets.add(Material.YELLOW_CARPET);
        carpets.add(Material.LIGHT_BLUE_CARPET);
        carpets.add(Material.LIGHT_GRAY_CARPET);
        carpets.add(Material.LIME_CARPET);
        carpets.add(Material.MAGENTA_CARPET);
        carpets.add(Material.ORANGE_CARPET);
        carpets.add(Material.PINK_CARPET);
        carpets.add(Material.PURPLE_CARPET);
        carpets.add(Material.RED_CARPET);
        carpets.add(Material.WHITE_CARPET);


        r.shape("ccc",
                "cdc",
                "ccc");

        r.setIngredient('c', new RecipeChoice.MaterialChoice(carpets));
        r.setIngredient('d', inputDye);

        return r;
    }

    public ArrayList <ShapedRecipe> getRecipes() {
        ArrayList <ShapedRecipe> recipes = new ArrayList <ShapedRecipe>();

        recipes.add(getCarpetRecipe(Material.BLACK_CARPET, Material.BLACK_DYE, "blackCarpet"));
        recipes.add(getCarpetRecipe(Material.BLUE_CARPET, Material.BLUE_DYE, "blueCarpet"));
        recipes.add(getCarpetRecipe(Material.BROWN_CARPET, Material.BROWN_DYE, "brownCarpet"));
        recipes.add(getCarpetRecipe(Material.CYAN_CARPET, Material.CYAN_DYE, "cyanCarpet"));
        recipes.add(getCarpetRecipe(Material.GRAY_CARPET, Material.GRAY_DYE, "grayCarpet"));
        recipes.add(getCarpetRecipe(Material.GREEN_CARPET, Material.GREEN_DYE, "greenCarpet"));
        recipes.add(getCarpetRecipe(Material.YELLOW_CARPET, Material.YELLOW_DYE, "yellowCarpet"));
        recipes.add(getCarpetRecipe(Material.LIGHT_BLUE_CARPET, Material.LIGHT_BLUE_DYE, "lightBlueCarpet"));
        recipes.add(getCarpetRecipe(Material.LIGHT_GRAY_CARPET, Material.LIGHT_GRAY_DYE, "lightGrayCarpet"));
        recipes.add(getCarpetRecipe(Material.LIME_CARPET, Material.LIME_DYE, "limeCarpet"));
        recipes.add(getCarpetRecipe(Material.MAGENTA_CARPET, Material.MAGENTA_DYE, "magentaCarpet"));
        recipes.add(getCarpetRecipe(Material.ORANGE_CARPET, Material.ORANGE_DYE, "orangeCarpet"));
        recipes.add(getCarpetRecipe(Material.PINK_CARPET, Material.PINK_DYE, "pinkCarpet"));
        recipes.add(getCarpetRecipe(Material.PURPLE_CARPET, Material.PURPLE_DYE, "purpleCarpet"));
        recipes.add(getCarpetRecipe(Material.RED_CARPET, Material.RED_DYE, "redCarpet"));
        recipes.add(getCarpetRecipe(Material.WHITE_CARPET, Material.WHITE_DYE, "whiteCarpet"));

        return recipes;
    }
}