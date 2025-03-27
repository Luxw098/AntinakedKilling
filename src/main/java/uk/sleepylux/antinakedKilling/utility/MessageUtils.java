package uk.sleepylux.antinakedKilling.utility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtils {
    public static void sendMessage(Player player, String message) {
        player.sendMessage(ChatColor.BOLD + "" + ChatColor.GRAY + "["
                + ChatColor.GOLD + "AntinakedKilling"
                + ChatColor.GRAY + "] " + ChatColor.RESET + ChatColor.GRAY + message);
    }
}
