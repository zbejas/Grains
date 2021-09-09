package si.zbe.grains;

import org.bstats.bukkit.Metrics;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import si.zbe.grains.commands.EnderChest;
import si.zbe.grains.commands.Grains;
import si.zbe.grains.commands.Workbench;
import si.zbe.grains.events.*;
import si.zbe.grains.recipes.CarpetRecipe;
import si.zbe.grains.recipes.ChestRecipe;
import si.zbe.grains.recipes.MelonRecipe;
import si.zbe.grains.recipes.MinecartRecipe;
import si.zbe.grains.utils.ItemManager;
import si.zbe.grains.utils.LanguageManager;
import si.zbe.grains.utils.UpdateCheck;

public class Main extends JavaPlugin {

    public static Main plugin;
    public static boolean debug;

    @Override
    public void onEnable() {
        plugin = this;
        setConfig();
        debug  = getConfig().getBoolean("plugin.debug");
        LanguageManager.setupLanguages();
        checkPluginDependencies();
        registerEvents();
        registerCommands();
        registerRecipes();
        ItemManager.init();
        UpdateCheck.init();
        Metrics metrics = new Metrics(this, 12734);
        getLogger().info(LanguageManager.get("plugin.enabled"));
    }

    @Override
    public void onDisable() {
        getLogger().info(LanguageManager.get("plugin.disabled"));
    }

    private void setConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new CropHarvest(), this);
        getServer().getPluginManager().registerEvents(new ArmorClick(), this);
        getServer().getPluginManager().registerEvents(new LeashVillager(), this);
        getServer().getPluginManager().registerEvents(new WorkbenchClick(), this);
        getServer().getPluginManager().registerEvents(new EnderChestClick(), this);
        getServer().getPluginManager().registerEvents(new InventoryFull(), this);
    }

    private void registerCommands() {
        getCommand("gworkbench").setExecutor(new Workbench(this));
        getCommand("genderchest").setExecutor(new EnderChest(this));

        getCommand("grains").setExecutor(new Grains(this));
        getCommand("grains").setTabCompleter(new Grains(this));
    }

    private void registerRecipes() {

        if (getConfig().getBoolean("recipes.chest")) {
            ChestRecipe chest = new ChestRecipe();
            plugin.getServer().addRecipe(chest.getRecipe());
            if (debug) getLogger().info("Chest recipe has been loaded.");
        }

        if (getConfig().getBoolean("recipes.melon")) {
            MelonRecipe melon = new MelonRecipe();
            plugin.getServer().addRecipe(melon.getRecipe());
            if (debug) getLogger().info("Melon recipe has been loaded.");
        }

        if (getConfig().getBoolean("recipes.carpet")) {
            CarpetRecipe carpet = new CarpetRecipe();
            for (ShapedRecipe recipe : carpet.getRecipes()) {
                plugin.getServer().addRecipe(recipe);
            }
            if (debug) getLogger().info("Carpet recipe has been loaded.");
        }

        if (getConfig().getBoolean("recipes.minecart")) {
            MinecartRecipe minecart = new MinecartRecipe();
            plugin.getServer().addRecipe(minecart.getHopperRecipe());
            plugin.getServer().addRecipe(minecart.getChestRecipe());
            plugin.getServer().addRecipe(minecart.getFurnaceRecipe());
            if (debug) getLogger().info("Minecart recipe has been loaded.");
        }
    }

    private void checkPluginDependencies() {
        if(getServer().getPluginManager().getPlugin("ProtocolLib") == null) {
            getLogger().info("ProtocolLib is not installed. Please install ProtocolLib for Grains to work as intended.");
        }
    }
}
