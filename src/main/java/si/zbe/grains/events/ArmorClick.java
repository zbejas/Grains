package si.zbe.grains.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorClick implements Listener {
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
        return m == Material.LEATHER_HELMET || m == Material.IRON_HELMET || m == Material.CHAINMAIL_HELMET || m == Material.GOLDEN_HELMET || m == Material.DIAMOND_HELMET || m == Material.NETHERITE_HELMET || m == Material.TURTLE_HELMET || m == Material.CARVED_PUMPKIN;
    }

    boolean isChestplate(Material m) {
        return m == Material.LEATHER_CHESTPLATE || m == Material.IRON_CHESTPLATE || m == Material.CHAINMAIL_CHESTPLATE || m == Material.GOLDEN_CHESTPLATE || m == Material.DIAMOND_CHESTPLATE || m == Material.NETHERITE_CHESTPLATE;
    }

    boolean isLeggings(Material m) {
        return m == Material.LEATHER_LEGGINGS || m == Material.IRON_LEGGINGS || m == Material.CHAINMAIL_LEGGINGS || m == Material.GOLDEN_LEGGINGS || m == Material.DIAMOND_LEGGINGS || m == Material.NETHERITE_LEGGINGS;
    }

    boolean isBoots(Material m) {
        return m == Material.LEATHER_BOOTS || m == Material.IRON_BOOTS || m == Material.CHAINMAIL_BOOTS || m == Material.GOLDEN_BOOTS || m == Material.DIAMOND_BOOTS || m == Material.NETHERITE_BOOTS;
    }
}
