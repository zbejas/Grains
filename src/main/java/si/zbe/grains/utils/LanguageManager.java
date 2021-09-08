package si.zbe.grains.utils;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import si.zbe.grains.Main;

import java.io.File;

public class LanguageManager {

    private static File langYml = new File(Main.plugin.getDataFolder() + "/lang.yml");
    private static FileConfiguration langConfig = YamlConfiguration.loadConfiguration(langYml);

    public static String get(String key) {
        String str = langConfig.getString(key);
        if (str == null) str = "No key found in lang.yml (" + key + ").";
        return str;
    }

    public static void reloadLanguage() {
        langYml = new File(Main.plugin.getDataFolder() + "/lang.yml");
        langConfig = YamlConfiguration.loadConfiguration(langYml);
    }

    public static void setupLanguages() {
        if (!langYml.exists()) {
            langYml.getParentFile().mkdirs();
            Main.plugin.saveResource("lang.yml", false);

            langYml = new File(Main.plugin.getDataFolder() + "/lang.yml");
            langConfig = YamlConfiguration.loadConfiguration(langYml);
        } else {
            //langConfig.options().copyDefaults(true);
            //Main.plugin.saveResource("lang.yml", false);
        }
    }
}
