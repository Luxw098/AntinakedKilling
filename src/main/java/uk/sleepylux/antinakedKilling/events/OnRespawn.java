package uk.sleepylux.antinakedKilling.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import uk.sleepylux.antinakedKilling.AntiNakedKilling;
import uk.sleepylux.antinakedKilling.registries.NakedRegistry;
import uk.sleepylux.antinakedKilling.utility.BannedItems;
import uk.sleepylux.antinakedKilling.utility.ConfigMessages;
import uk.sleepylux.antinakedKilling.utility.MessageUtils;

public class OnRespawn implements Listener {
    public AntiNakedKilling plugin;
    public OnRespawn(AntiNakedKilling plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        try {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                FileConfiguration config = plugin.getConfig();
                Player player = event.getPlayer();
                PlayerInventory inv = player.getInventory();

                boolean protect = true;
                for (ItemStack item : inv.getStorageContents())
                    if (item != null && BannedItems.BannedItemsContain(item.getType()))
                        protect = false;

                if (protect)
                    NakedRegistry.addNaked(plugin, player);

                ConfigMessages messages = new ConfigMessages(config, player, null);

                boolean finalProtect = protect;
                MessageUtils.sendMessage(player, (finalProtect)
                        ? ChatColor.GREEN + messages.translateFromKey("protectedJoinMessage")
                        : ChatColor.RED + messages.translateFromKey("vulnerableJoinMessage")
                );
            }, 5L);

        } catch (Exception ex) {
            plugin.getLogger().severe("Exception during `onRespawn` Event" + ex.getMessage());
        };
    }
}
