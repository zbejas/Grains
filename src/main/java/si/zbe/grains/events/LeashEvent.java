package si.zbe.grains.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import si.zbe.grains.Main;

public class LeashEvent implements Listener {
    @EventHandler
    public void onVillagerLeash(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() != EntityType.VILLAGER)
            return;
        Player p = e.getPlayer();

        Villager v = (Villager) e.getRightClicked();
        final ItemStack itemInHand = p.getInventory().getItemInMainHand();

        if (itemInHand.getType() != Material.LEAD)
            return;

        if (!p.hasPermission("grains.leash")) {
            return;
        }

        e.setCancelled(true);

        if (!v.isLeashed()) {
            Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    itemInHand.setAmount(itemInHand.getAmount() - 1);
                    v.setLeashHolder(p);
                }
            }, 1L);
        }
    }
}