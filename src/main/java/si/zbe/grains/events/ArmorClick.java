package si.zbe.grains.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import si.zbe.grains.utils.ItemCollections;

public class ArmorClick implements Listener {
    // ! This event is called when a player clicks on a helmet, chestplate, leggings or boots
    // ! stored in the ItemCollections.
    @EventHandler
    public void onArmorClick(PlayerInteractEvent e) {
        if (!e.getPlayer().hasPermission("grain.armor"))
            return;

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player p = e.getPlayer();
            ItemStack hand = p.getInventory().getItemInMainHand();
            hand.setItemMeta(hand.getItemMeta());

            if (isHelmet(hand.getType())) {
                if (p.getInventory().getHelmet() != null) {

                    if (e.getAction() == Action.RIGHT_CLICK_BLOCK && hand.getType() == Material.CARVED_PUMPKIN) {
                        return;
                    }

                    ItemStack helmet = p.getInventory().getHelmet();
                    helmet.setItemMeta(helmet.getItemMeta());

                    p.getInventory().setHelmet(hand);
                    p.getInventory().setItemInMainHand(helmet);
                    e.setCancelled(true);
                }
            } else if (isChestplate(hand.getType())) {
                if (p.getInventory().getChestplate() != null) {
                    ItemStack chestplate = p.getInventory().getChestplate();
                    chestplate.setItemMeta(chestplate.getItemMeta());

                    p.getInventory().setChestplate(hand);
                    p.getInventory().setItemInMainHand(chestplate);
                    e.setCancelled(true);
                }
            } else if (isLeggings(hand.getType())) {
                if (p.getInventory().getLeggings() != null) {
                    ItemStack leggings = p.getInventory().getLeggings();
                    leggings.setItemMeta(leggings.getItemMeta());

                    p.getInventory().setLeggings(hand);
                    p.getInventory().setItemInMainHand(leggings);
                    e.setCancelled(true);
                }
            } else if (isBoots(hand.getType())) {
                if (p.getInventory().getBoots() != null) {
                    ItemStack boots = p.getInventory().getBoots();
                    boots.setItemMeta(boots.getItemMeta());

                    p.getInventory().setBoots(hand);
                    p.getInventory().setItemInMainHand(boots);
                    e.setCancelled(true);
                }
            }
        }
    }

    boolean isHelmet(Material m) {
        return ItemCollections.helmets.contains(m);
    }

    boolean isChestplate(Material m) {
        return ItemCollections.chestplates.contains(m);
    }

    boolean isLeggings(Material m) {
        return ItemCollections.leggings.contains(m);
    }

    boolean isBoots(Material m) {
        return ItemCollections.boots.contains(m);
    }
}
