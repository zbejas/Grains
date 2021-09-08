package si.zbe.grains.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import si.zbe.grains.Main;
import si.zbe.grains.utils.LanguageManager;
import si.zbe.grains.utils.Messages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grains implements CommandExecutor, TabCompleter {

    private final Main plugin;

    public Grains(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("grains.admin")) {
            sender.sendMessage(ChatColor.RED + Messages.no_permission);
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.GREEN + LanguageManager.get("plugin.grains.info"));
            sender.sendMessage(ChatColor.GREEN + LanguageManager.get("plugin.grains.reload"));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            Main.plugin.reloadConfig();
            LanguageManager.reloadLanguage();
            sender.sendMessage(ChatColor.GREEN + LanguageManager.get("plugin.grains.reload-success"));
            return true;
        }

        if (args[0].equalsIgnoreCase("version")) {
            sender.sendMessage(ChatColor.GREEN + "Grains version: " + ChatColor.GOLD + Main.plugin.getDescription().getVersion());
            return true;
        }

        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 1) {
            ArrayList<String> commands = new ArrayList<>();
            commands.add("reload");
            commands.add("version");

            Collections.sort(commands);
            return commands;
        }

        return null;
    }
}
