package si.zbe.grains.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Cocoa;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import si.zbe.grains.Main;

import java.lang.reflect.InvocationTargetException;

public class CropHarvest implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Block block = e.getClickedBlock();

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!isCrop(block.getType()))
                return;

            harvestCrop(block.getType(), e);
        }
    }

    public boolean isCrop(Material m) {
        return m == Material.WHEAT || m == Material.POTATOES || m == Material.CARROTS || m == Material.BEETROOTS
                || m == Material.NETHER_WART || m == Material.COCOA;
    }

    public Material getSeed(Material m) {
        if (m == Material.WHEAT)
            return Material.WHEAT_SEEDS;
        else if (m == Material.POTATOES)
            return Material.POTATO;
        else if (m == Material.CARROTS)
            return Material.CARROT;
        else if (m == Material.BEETROOTS)
            return Material.BEETROOT_SEEDS;
        else if (m == Material.NETHER_WART)
            return Material.NETHER_WART;
        else if (m == Material.COCOA)
            return Material.COCOA_BEANS;
        else
            return Material.AIR;
    }

    void sendServerPacket(ProtocolManager pm, Player p, PacketContainer pc) {
        try {
            pm.sendServerPacket(p, pc);
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }

    }

    public void harvestCrop(Material m, PlayerInteractEvent e) {
        Block block = e.getClickedBlock();

        if (isCrop(m)) {
            if (!e.getPlayer().hasPermission("grains.crops")) {
                return;
            }

            if (!(block.getBlockData() instanceof Ageable)) {
                return;
            }

            Ageable crop = (Ageable) block.getBlockData();

            if (crop.getAge() != crop.getMaximumAge()) return;


            boolean SeedInDrop = false;
            for (ItemStack is : block.getDrops())
                if (is.getType() == getSeed(m)) {
                    SeedInDrop = true;
                }

            // http://wiki.vg/Protocol#Animation
            ProtocolManager pm = ProtocolLibrary.getProtocolManager();

            PacketContainer handAnimation = pm.createPacket(PacketType.Play.Server.ANIMATION, false);
            handAnimation.getEntityModifier(e.getPlayer().getWorld()).write(0, e.getPlayer());
            handAnimation.getIntegers().write(1, 0);

            if (SeedInDrop) {
                block.getDrops().remove(new ItemStack(getSeed(m), 1));
            } else if (e.getPlayer().getInventory().containsAtLeast(new ItemStack(getSeed(m)), 1)) {
                e.getPlayer().getInventory().remove(new ItemStack(getSeed(m), 1));
            }

            sendServerPacket(pm, e.getPlayer(), handAnimation);

            //TODO: Replace with something that doesnt ignore protection
            //Main.plugin.getServer().getPluginManager().callEvent(new BlockBreakEvent(block, e.getPlayer()));

            // Cocoa needs directions
            if (block.getType() == Material.COCOA) {
                BlockFace blockFace = ((Directional) block.getBlockData()).getFacing();
                Cocoa cocoa = (Cocoa) block.getBlockData();
                Cocoa smallCocoa = (Cocoa) cocoa.clone();
                smallCocoa.setAge(0);
                smallCocoa.setFacing(cocoa.getFacing());
                block.breakNaturally();
                block.setBlockData(smallCocoa);

                return;
            }

            block.breakNaturally();

            block.setType(m);


        }
    }
}
