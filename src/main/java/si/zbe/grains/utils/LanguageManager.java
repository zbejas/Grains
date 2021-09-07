package si.zbe.grains.utils;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import si.zbe.grains.Main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class LanguageManager {

    HashMap<String, String> translationMap;

    private static File langYml = new File(Main.plugin.getDataFolder() + "/lang.yml");
    private static FileConfiguration langConfig = YamlConfiguration.loadConfiguration(langYml);

    public static String get(String key) {
        return langConfig.getString(key);
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
            langConfig.options().copyDefaults(true);
            try {
                langConfig.save(langYml);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
