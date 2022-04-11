package si.zbe.grains.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import si.zbe.grains.utils.ItemManager;

public class CraftingLimiter implements Listener {
    // ! This event prevents players without the permission from crafting our items
    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        Player p = (Player) e.getView().getPlayer();

        if (e.getInventory() == null) return;

        try {
            ItemStack result = e.getRecipe().getResult();

            if (result == null) return;

            if (!result.equals(ItemManager.workbench)) return;
        } catch (NullPointerException ex) {
            return;
        }


        if (!p.hasPermission("grains.workbench")) {
            //p.sendMessage(ChatColor.RED + Messages.no_permission_craft);
            e.getInventory().setResult(null);
            return;
        }
        p.updateInventory();
    }
}
