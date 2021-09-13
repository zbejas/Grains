package si.zbe.grains.utils;


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import si.zbe.grains.Main;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LanguageManager {

    private static File langYml; //= new File(Main.plugin.getDataFolder() + "/lang.yml");
    private static FileConfiguration langConfig; // = YamlConfiguration.loadConfiguration(langYml);

    public static void init() {
        langYml = new File(Main.plugin.getDataFolder() + "/lang.yml");

        if (!langYml.exists()) {
            Main.plugin.saveResource("lang.yml", false);
        }

        langConfig = YamlConfiguration.loadConfiguration(langYml);

        saveDefaultLanguage();
    }

    public static void saveLanguage() {
        if (langYml == null)
            langYml = new File(Main.plugin.getDataFolder(), "lang.yml");

        try {
            langConfig.save(langYml);
        } catch (IOException e) {
            Main.plugin.getLogger().severe("Error copying over lang.yml.");
        }
    }

    public static void saveDefaultLanguage() {
        if (langYml == null)
            langYml = new File(Main.plugin.getDataFolder(), "lang.yml");

        if (!langYml.exists()) {
            Main.plugin.saveResource("lang.yml", false);
        }
    }

    public static String get(String key) {
        String str = langConfig.getString(key);
        if (str == null) str = "No key found in lang.yml (" + key + ").";
        return str;
    }

    public static void reloadLanguage() {
        //langYml = new File(Main.plugin.getDataFolder() + "/lang.yml");
        langConfig = YamlConfiguration.loadConfiguration(langYml);
    }
}
