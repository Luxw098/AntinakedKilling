package uk.sleepylux.antinakedKilling.events;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import uk.sleepylux.antinakedKilling.AntiNakedKilling;
import uk.sleepylux.antinakedKilling.registries.NakedRegistry;
import uk.sleepylux.antinakedKilling.utility.BannedItems;
import uk.sleepylux.antinakedKilling.utility.ConfigMessages;
import uk.sleepylux.antinakedKilling.utility.MessageUtils;


public class OnDamage implements Listener {
    public AntiNakedKilling plugin;
    public OnDamage(AntiNakedKilling plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player attacker)) return;
        if (!(event.getEntity() instanceof Player victim)) return;
        ConfigMessages messages = new ConfigMessages(plugin.getConfig(), victim, null);

        try {
            UUID attackerUUID = attacker.getUniqueId();
            if (NakedRegistry.getNaked(attackerUUID)) {
                NakedRegistry.removeNaked(plugin, attackerUUID, "Attacked Player.");
                MessageUtils.sendMessage(attacker, ChatColor.RED + messages.translateFromKey("vulnerableAttackMessage"));
            }
        } catch (Exception ex) {
            plugin.getLogger().severe("Exception during `onDamage/RemoveNaked` Event | " + ex.getMessage());
        }


        if (plugin.getConfig().getBoolean("protectAgainstPlayers") && !(event.getDamager() instanceof Player)
            && !plugin.getConfig().getBoolean("protectAgainstMobs")) return;
        try {
            UUID victimUUID = victim.getUniqueId();
            if (BannedItems.BannedItemsInventory(victim.getInventory()))
                NakedRegistry.removeNaked(plugin, victimUUID, "Redundancy Item Check.");

            if (NakedRegistry.getNaked(victimUUID)) {
                event.setCancelled(true);
                MessageUtils.sendMessage(attacker, ChatColor.RED + messages.translateFromKey("protectedAttackMessage"));
            }
        } catch (Exception ex) {
            plugin.getLogger().severe("Exception during `onDamage/Protection` Event | " + ex.getMessage());
        };
    }
}
