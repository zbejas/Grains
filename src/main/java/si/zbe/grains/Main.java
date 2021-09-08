package si.zbe.grains;

import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import si.zbe.grains.commands.EnderChestCommand;
import si.zbe.grains.commands.GrainsCommand;
import si.zbe.grains.commands.WorkbenchCommand;
import si.zbe.grains.events.*;
import si.zbe.grains.recipes.CarpetRecipe;
import si.zbe.grains.recipes.ChestRecipe;
import si.zbe.grains.recipes.MelonRecipe;
import si.zbe.grains.recipes.MinecartRecipe;
import si.zbe.grains.utils.ItemManager;
import si.zbe.grains.utils.LanguageManager;

public class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        checkPluginDependencies();
        LanguageManager.setupLanguages();
        setConfig();
        registerEvents();
        registerCommands();
        registerRecipes();
        ItemManager.init();
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
        getServer().getPluginManager().registerEvents(new CropHarvestEvent(), this);
        getServer().getPluginManager().registerEvents(new ArmorEvent(), this);
        getServer().getPluginManager().registerEvents(new LeashEvent(), this);
        getServer().getPluginManager().registerEvents(new WorkbenchEvent(), this);
        getServer().getPluginManager().registerEvents(new EnderChestEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryFullEvent(), this);
    }

    private void registerCommands() {
        getCommand("gworkbench").setExecutor(new WorkbenchCommand(this));
        getCommand("genderchest").setExecutor(new EnderChestCommand(this));

        getCommand("grains").setExecutor(new GrainsCommand(this));
        getCommand("grains").setTabCompleter(new GrainsCommand(this));
    }

    private void registerRecipes() {
        boolean debug = getConfig().getBoolean("plugin.debug");

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
