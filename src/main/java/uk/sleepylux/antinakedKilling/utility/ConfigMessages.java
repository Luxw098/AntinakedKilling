package uk.sleepylux.antinakedKilling.utility;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.Nullable;

public class ConfigMessages {
    private final FileConfiguration config;
    private final @Nullable ItemStack item;
    private final @Nullable Player player;
    public ConfigMessages(FileConfiguration config, @Nullable Player player, @Nullable ItemStack item) {
        this.config = config;
        this.player = player;
        this.item = item;
    }

    public String translateFromKey(String key) {
        String result = config.getString(key);
        if (result == null) return "Key " + key + " Not Found, Please report this";

        result = result.replace("&0", ChatColor.BLACK + "");
        result = result.replace("&1", ChatColor.BLUE + "");
        result = result.replace("&2", ChatColor.GREEN + "");
        result = result.replace("&3", ChatColor.AQUA + "");
        result = result.replace("&4", ChatColor.RED + "");
        result = result.replace("&5", ChatColor.LIGHT_PURPLE + "");
        result = result.replace("&6", ChatColor.GOLD + "");
        result = result.replace("&7", ChatColor.GRAY + "");
        result = result.replace("&8", ChatColor.DARK_GRAY + "");

        if (player != null)
            result = result.replace("{player}", player.getDisplayName());
        if (item != null) {
            String[] keySplit = item.getTranslationKey().split("\\.");
            result = result.replace("{item}", keySplit[keySplit.length-1]);
        }
        result = result.replace("{timeout}", String.valueOf(config.getInt("timeout")));

        return result;
    }
}
