package uk.sleepylux.antinakedKilling.registries;

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
    private final static CopyOnWriteArrayList<UUID> nakedPlayers = new CopyOnWriteArrayList<>();
    private final static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    public static void shutdown() {
        executor.shutdown();
    }

    public static void addNaked(AntiNakedKilling plugin, Player player) {
        UUID playerUUID = player.getUniqueId();
        plugin.getLogger().info("Added " + plugin.getName() + " as a naked player");
        nakedPlayers.add(playerUUID);
        executor.schedule(() -> {
            if (nakedPlayers.contains(playerUUID)) {
                nakedPlayers.remove(playerUUID);
                MessageUtils.sendMessage(player, ChatColor.RED + plugin.getConfig().getString("vulnerableTimeoutMessage"));
            }
        }, 30, TimeUnit.MINUTES);
    }

    public static void removeNaked(AntiNakedKilling plugin, UUID playerUUID, String reason) {
        plugin.getLogger().info("Removed " + plugin.getName() + " as a naked player: " + reason);
        nakedPlayers.remove(playerUUID);
    }


    public static boolean getNaked(UUID playerUUID) {
        return nakedPlayers.contains(playerUUID);
    }
}
