package si.zbe.grains.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import si.zbe.grains.Main;
import si.zbe.grains.utils.LanguageManager;

public class InventoryFull implements Listener {
    // ! This event is called when a player breaks a block and his inventory is full.
    @EventHandler
    public void onPickUp(BlockBreakEvent e) {
        if (!Main.plugin.getConfig().getBoolean("events.full-inventory")) {
            return;
        }

        Player p = e.getPlayer();

        if (p.getInventory().firstEmpty() == -1) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + LanguageManager.get("events.full-inventory")));
            //p.sendTitle(ChatColor.RED + LanguageManager.get("events.full-inventory.title"),ChatColor.DARK_RED + LanguageManager.get("events.full-inventory.subtitle"),1,20,1);
        }
    }
}
