package si.zbe.grains.events;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import si.zbe.grains.utils.ItemManager;

public class EnderChestClick implements Listener {
    // ! This event is called when a player right-clicks on an ender chest
    // ! and opens it.
    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("grains.enderchest"))
            return;

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            final ItemStack itemInHand = p.getInventory().getItemInMainHand();
            final ItemStack itemInOffHand = p.getInventory().getItemInOffHand();

            if (itemInHand.getType() == Material.ENDER_CHEST) {

                if (!itemInHand.hasItemMeta())
                    return;

                if (!itemInHand.getItemMeta().equals(ItemManager.enderchest.getItemMeta()))
                    return;


                p.openInventory(p.getEnderChest());
                p.updateInventory();
                e.setCancelled(true);
            } else if (itemInOffHand.getType() == Material.ENDER_CHEST) {
                if (!itemInOffHand.hasItemMeta())
                    return;

                if (!itemInOffHand.getItemMeta().equals(ItemManager.enderchest.getItemMeta()))
                    return;

                p.openInventory(p.getEnderChest());
                p.updateInventory();
                e.setCancelled(true);
            }
        }
    }
}