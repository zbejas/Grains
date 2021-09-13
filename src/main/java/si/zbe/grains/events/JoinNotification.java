package si.zbe.grains.events;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import si.zbe.grains.Main;
import si.zbe.grains.utils.LanguageManager;
import si.zbe.grains.utils.UpdateCheck;

import java.text.MessageFormat;

public class JoinNotification implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (UpdateCheck.version == Main.plugin.getDescription().getVersion()) {
            return;
        }

        if (!Main.plugin.getConfig().getBoolean("plugin.on-join-notification")) {
            return;
        }

        if (!p.hasPermission("grains.admin")) {
            return;
        }

        if (UpdateCheck.version == Main.plugin.getDescription().getVersion()) {
            return;
        }

        p.sendMessage(ChatColor.GOLD + MessageFormat.format(LanguageManager.get("plugin.update"), UpdateCheck.version));
        p.sendMessage(ChatColor.GREEN + "Grains version: " + ChatColor.GOLD + Main.plugin.getDescription().getVersion());
        p.sendMessage(ChatColor.GREEN + "Latest: " + ChatColor.GOLD + "https://github.com/pablo3x6/Grains/releases/latest");
        //p.sendMessage(ChatColor.GREEN + "Latest version: " + ChatColor.GOLD + UpdateCheck.version);
    }
}
