package si.zbe.grains.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import si.zbe.grains.Main;
import si.zbe.grains.utils.ItemManager;
import si.zbe.grains.utils.LanguageManager;
import si.zbe.grains.utils.Messages;

public class EnderChestCommand implements CommandExecutor {
    private final Main plugin;

    public EnderChestCommand(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("grain.enderchest")) {
            p.sendMessage(ChatColor.RED + Messages.no_permission);
            return true;
        }

        if (args.length > 0) {
            p.sendMessage(ChatColor.RED + Messages.invalid_input);
            return true;
        }
        if (p.getInventory().getItemInMainHand().getType() == Material.ENDER_CHEST) {
            p.getInventory().setItemInMainHand(ItemManager.enderchest);
        } else {
            p.sendMessage(ChatColor.RED + LanguageManager.get("enderchest.wrong-item"));
        }

        return true;
    }
}
