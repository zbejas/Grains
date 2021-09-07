package si.zbe.grains;

import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import si.zbe.grains.commands.GrainsCommand;
import si.zbe.grains.commands.WorkbenchCommand;
import si.zbe.grains.events.ArmorEvent;
import si.zbe.grains.events.CropHarvestEvent;
import si.zbe.grains.events.LeashEvent;
import si.zbe.grains.events.WorkbenchEvent;
import si.zbe.grains.recipes.CarpetRecipe;
import si.zbe.grains.recipes.ChestRecipe;
import si.zbe.grains.recipes.MelonRecipe;
import si.zbe.grains.utils.ItemManager;
import si.zbe.grains.utils.LanguageManager;

public class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
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
    }

    private void registerCommands() {
        getCommand("workbench").setExecutor(new WorkbenchCommand(this));

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
    }
}
