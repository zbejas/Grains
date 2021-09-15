package si.zbe.grains;

import org.bstats.bukkit.Metrics;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import si.zbe.grains.commands.EnderChest;
import si.zbe.grains.commands.Grains;
import si.zbe.grains.commands.Workbench;
import si.zbe.grains.events.*;
import si.zbe.grains.recipes.*;
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
        LanguageManager.init();
        checkPluginDependencies();
        ItemManager.init();
        registerEvents();
        registerCommands();
        registerRecipes();
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
        getServer().getPluginManager().registerEvents(new CraftingLimiter(), this);
        getServer().getPluginManager().registerEvents(new JoinNotification(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
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

        if (getConfig().getBoolean("recipes.workbench")) {
            WorkbenchRecipe wb = new WorkbenchRecipe();
            plugin.getServer().addRecipe(wb.getRecipe());
            if (debug) getLogger().info("Workbench recipe has been loaded.");
        }

        if (getConfig().getBoolean("recipes.redstone")) {
            RedstoneRecipe rr = new RedstoneRecipe();
            plugin.getServer().addRecipe(rr.getRepeaterRecipe());
            plugin.getServer().addRecipe(rr.getHopperRecipe());
            if (debug) getLogger().info("Redstone recipe has been loaded.");
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
            plugin.getServer().addRecipe(minecart.getBoomRecipe());
            if (debug) getLogger().info("Minecart recipe has been loaded.");
        }
    }

    private void checkPluginDependencies() {
        if(getServer().getPluginManager().getPlugin("ProtocolLib") == null) {
            getLogger().info("ProtocolLib is not installed. Please install ProtocolLib for Grains to work as intended.");
        }
    }
}
