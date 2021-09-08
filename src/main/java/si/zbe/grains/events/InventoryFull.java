package si.zbe.grains.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import si.zbe.grains.Main;
import si.zbe.grains.utils.LanguageManager;

public class InventoryFull implements Listener {
        @EventHandler
        public void onPickUp(BlockBreakEvent e) {
            if (!Main.plugin.getConfig().getBoolean("events.full-inventory")) {
                return;
            }

            Player p = (Player) e.getPlayer();

            if (p.getInventory().firstEmpty() == -1) {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + LanguageManager.get("events.full-inventory")));
                //p.sendTitle(ChatColor.RED + LanguageManager.get("events.full-inventory.title"),ChatColor.DARK_RED + LanguageManager.get("events.full-inventory.subtitle"),1,20,1);
            }
        }
    }
