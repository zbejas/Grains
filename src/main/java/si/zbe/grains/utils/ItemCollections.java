package si.zbe.grains.utils;

import org.bukkit.Material;

import java.util.ArrayList;

public class ItemCollections {
    // ! This class contains item collections
    public static ArrayList<Material> logs = new ArrayList<Material>();
    public static ArrayList<Material> leaves = new ArrayList<Material>();
    public static ArrayList<Material> helmets = new ArrayList<Material>();
    public static ArrayList<Material> chestplates = new ArrayList<Material>();
    public static ArrayList<Material> leggings = new ArrayList<Material>();
    public static ArrayList<Material> boots = new ArrayList<Material>();

    public static void init() {
        addLogs();
        addLeaves();
        addHelmets();
        addChestplates();
        addLeggings();
        addBoots();
    }

    private static void addLogs() {
        logs.add(Material.ACACIA_LOG);
        logs.add(Material.BIRCH_LOG);
        logs.add(Material.DARK_OAK_LOG);
        logs.add(Material.JUNGLE_LOG);
        logs.add(Material.OAK_LOG);
        logs.add(Material.SPRUCE_LOG);
        logs.add(Material.CRIMSON_STEM);
        logs.add(Material.WARPED_STEM);
        logs.add(Material.STRIPPED_ACACIA_LOG);
        logs.add(Material.STRIPPED_BIRCH_LOG);
        logs.add(Material.STRIPPED_DARK_OAK_LOG);
        logs.add(Material.STRIPPED_JUNGLE_LOG);
        logs.add(Material.STRIPPED_OAK_LOG);
        logs.add(Material.STRIPPED_SPRUCE_LOG);
        logs.add(Material.STRIPPED_CRIMSON_STEM);
        logs.add(Material.STRIPPED_WARPED_STEM);
    }

    private static void addLeaves() {
        leaves.add(Material.OAK_LEAVES);
        leaves.add(Material.SPRUCE_LEAVES);
        leaves.add(Material.BIRCH_LEAVES);
        leaves.add(Material.JUNGLE_LEAVES);
        leaves.add(Material.ACACIA_LEAVES);
        leaves.add(Material.DARK_OAK_LEAVES);
        leaves.add(Material.AZALEA_LEAVES);
        leaves.add(Material.FLOWERING_AZALEA_LEAVES);
    }

    private static void addHelmets() {
        helmets.add(Material.LEATHER_HELMET);
        helmets.add(Material.IRON_HELMET);
        helmets.add(Material.CHAINMAIL_HELMET);
        helmets.add(Material.GOLDEN_HELMET);
        helmets.add(Material.DIAMOND_HELMET);
        helmets.add(Material.NETHERITE_HELMET);
        helmets.add(Material.TURTLE_HELMET);
        helmets.add(Material.CARVED_PUMPKIN);
    }

    private static void addChestplates() {
        chestplates.add(Material.LEATHER_CHESTPLATE);
        chestplates.add(Material.IRON_CHESTPLATE);
        chestplates.add(Material.CHAINMAIL_CHESTPLATE);
        chestplates.add(Material.GOLDEN_CHESTPLATE);
        chestplates.add(Material.DIAMOND_CHESTPLATE);
        chestplates.add(Material.NETHERITE_CHESTPLATE);
    }

    private static void addLeggings() {
        leggings.add(Material.LEATHER_LEGGINGS);
        leggings.add(Material.IRON_LEGGINGS);
        leggings.add(Material.CHAINMAIL_LEGGINGS);
        leggings.add(Material.GOLDEN_LEGGINGS);
        leggings.add(Material.DIAMOND_LEGGINGS);
        leggings.add(Material.NETHERITE_LEGGINGS);
    }
    
    private static void addBoots() {
        boots.add(Material.LEATHER_BOOTS);
        boots.add(Material.IRON_BOOTS);
        boots.add(Material.CHAINMAIL_BOOTS);
        boots.add(Material.GOLDEN_BOOTS);
        boots.add(Material.DIAMOND_BOOTS);
        boots.add(Material.NETHERITE_BOOTS);
    }
}
