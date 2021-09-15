package si.zbe.grains.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import si.zbe.grains.Main;
import si.zbe.grains.utils.LanguageManager;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerDeath implements Listener {
    private static HashMap<Player, Location> locationHashMap = new HashMap<Player, Location>();


    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();
        if (!p.hasPermission("grains.compass")) return;

        locationHashMap.put(p, p.getLocation());
        return;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("grains.compass")) return;

        ItemStack compass = new ItemStack(Material.COMPASS);

        Location loc = locationHashMap.get(p);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.AQUA + p.getName());
        lore.add(ChatColor.DARK_PURPLE + "X: " + loc.getBlockX());
        lore.add(ChatColor.DARK_PURPLE + "Y: " + loc.getBlockY());
        lore.add(ChatColor.DARK_PURPLE + "Z: " + loc.getBlockZ());
        lore.add(ChatColor.DARK_PURPLE + "World: " + loc.getWorld().getName());

        ItemMeta meta = compass.getItemMeta();
        meta.setLore(lore);
        compass.setItemMeta(meta);

        p.getInventory().addItem(compass);

        Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
            @Override
            public void run() {
                p.setCompassTarget(loc);
                locationHashMap.remove(p);
            }
        }, 1L);
    }

    @EventHandler
    public void onCompassClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission("grains.compass")) return;

        if (e.getAction() == Action.RIGHT_CLICK_AIR && p.getInventory().getItemInMainHand().getType() == Material.COMPASS) {

            if (p.getCompassTarget().equals(p.getWorld().getSpawnLocation())) return;

            Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    p.setCompassTarget(p.getWorld().getSpawnLocation());
                    p.sendMessage(ChatColor.GREEN + LanguageManager.get("compass.reset"));
                }
            }, 1L);
        }
    }
}
