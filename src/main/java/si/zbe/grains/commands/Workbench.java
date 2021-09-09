package si.zbe.grains.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import si.zbe.grains.Main;
import si.zbe.grains.utils.ItemManager;
import si.zbe.grains.utils.LanguageManager;
import si.zbe.grains.utils.Messages;

public class Workbench implements CommandExecutor {
    @SuppressWarnings("unused")
    private final Main plugin;

    public Workbench(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("grains.workbench")) {
            p.sendMessage(ChatColor.RED + Messages.no_permission);
            return true;
        }

        if (args.length > 0) {
            p.sendMessage(ChatColor.RED + Messages.invalid_input);
            return true;
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.CRAFTING_TABLE) {
            p.getInventory().setItemInMainHand(ItemManager.workbench);
        } else {
            p.sendMessage(ChatColor.RED + LanguageManager.get("workbench.wrong-item"));
        }

        return true;
    }
}
