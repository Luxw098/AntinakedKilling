package uk.sleepylux.antinakedKilling.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import uk.sleepylux.antinakedKilling.AntiNakedKilling;
import uk.sleepylux.antinakedKilling.registries.NakedRegistry;
import uk.sleepylux.antinakedKilling.utility.BannedItems;
import uk.sleepylux.antinakedKilling.utility.ConfigMessages;
import uk.sleepylux.antinakedKilling.utility.MessageUtils;

public class OnPickup implements Listener {
    public AntiNakedKilling plugin;
    public OnPickup(AntiNakedKilling plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        try {
            ItemStack item = event.getItem().getItemStack();
            if (NakedRegistry.getNaked(player.getUniqueId()) && BannedItems.BannedItemsContain(item.getType())) {
                NakedRegistry.removeNaked(plugin, player.getUniqueId(), "Pickup Banned Item.");

                ConfigMessages messages = new ConfigMessages(plugin.getConfig(), player, item);
                MessageUtils.sendMessage(player, ChatColor.RED + messages.translateFromKey("vulnerablePickupMessage"));
            }
        } catch (Exception ex) {
            plugin.getLogger().severe("Exception during `onPickup` Event" + ex.getMessage());
        }
    }
}
