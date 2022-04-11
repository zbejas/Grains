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
    // ! This is the event that is called when a (admin) player joins the server.
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String gitLink = "https://github.com/pablo3x6/Grains/releases/latest";
        if (!Main.plugin.getConfig().getBoolean("plugin.on-join-notification")) {
            return;
        }

        if (!p.hasPermission("grains.admin")) {
            return;
        }

        // Get current version
        String currentVersion = Main.plugin.getDescription().getVersion();
        String latestVersion = UpdateCheck.version;
        String[] currentVersionSplit = currentVersion.split("\\.");
        String[] latestVersionSplit = latestVersion.split("\\.");


        int major = Integer.parseInt(latestVersionSplit[0]);
        int minor = Integer.parseInt(latestVersionSplit[1]);
        int patch = Integer.parseInt(latestVersionSplit[2]);

        // Parse current version
        int currentMajor = Integer.parseInt(currentVersionSplit[0]);
        int currentMinor = Integer.parseInt(currentVersionSplit[1]);
        int currentPatch = Integer.parseInt(currentVersionSplit[2]);

        // Compare versions
        if (major > currentMajor) {
            p.sendMessage(ChatColor.GOLD + MessageFormat.format(LanguageManager.get("plugin.update.major"), latestVersion));
            p.sendMessage(ChatColor.GREEN + "Grains version: " + ChatColor.GOLD + Main.plugin.getDescription().getVersion());
            p.sendMessage(ChatColor.GREEN + "Latest: " + ChatColor.GOLD + gitLink);
        } else if (minor > currentMinor) {
            p.sendMessage(ChatColor.GOLD + MessageFormat.format(LanguageManager.get("plugin.update.minor"), latestVersion));
            p.sendMessage(ChatColor.GREEN + "Grains version: " + ChatColor.GOLD + Main.plugin.getDescription().getVersion());
            p.sendMessage(ChatColor.GREEN + "Latest: " + ChatColor.GOLD + gitLink);
        } else if (patch > currentPatch) {
            p.sendMessage(ChatColor.GOLD + MessageFormat.format(LanguageManager.get("plugin.update.patch"), latestVersion));
            p.sendMessage(ChatColor.GREEN + "Grains version: " + ChatColor.GOLD + Main.plugin.getDescription().getVersion());
            p.sendMessage(ChatColor.GREEN + "Latest: " + ChatColor.GOLD + gitLink);
        } else {
            p.sendMessage(ChatColor.GREEN + MessageFormat.format(LanguageManager.get("plugin.update.none"), latestVersion));
            p.sendMessage(ChatColor.GREEN + "Grains version: " + ChatColor.GOLD + Main.plugin.getDescription().getVersion());
        }


        //p.sendMessage(ChatColor.GREEN + "Latest version: " + ChatColor.GOLD + UpdateCheck.version);
    }
}
