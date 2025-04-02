package uk.sleepylux.antinakedKilling.registries;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import uk.sleepylux.antinakedKilling.AntiNakedKilling;
import uk.sleepylux.antinakedKilling.utility.MessageUtils;

public class NakedRegistry {
    private static ArrayList<UUID> nakedPlayers = new ArrayList<>();
    private static String getDisplayName(AntiNakedKilling plugin, UUID uuid) {
        Player player = plugin.getServer().getPlayer(uuid);
        return (player != null) ? player.getDisplayName() : "Player Not Found";
    }


    public static void addNaked(AntiNakedKilling plugin, Player player) {
        UUID playerUUID = player.getUniqueId();
        plugin.getLogger().info("Added " + getDisplayName(plugin, playerUUID) + " as a naked player");
        nakedPlayers.add(playerUUID);
    }

    public static void removeNaked(AntiNakedKilling plugin, UUID playerUUID, String reason) {
        plugin.getLogger().info("Removed " + getDisplayName(plugin, playerUUID) + " as a naked player: " + reason);
        nakedPlayers.remove(playerUUID);
    }

    public static boolean getNaked(UUID playerUUID) {
        return nakedPlayers.contains(playerUUID);
    }
}
