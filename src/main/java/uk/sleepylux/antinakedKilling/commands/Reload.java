package uk.sleepylux.antinakedKilling.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uk.sleepylux.antinakedKilling.AntiNakedKilling;
import uk.sleepylux.antinakedKilling.utility.ConfigMessages;
import uk.sleepylux.antinakedKilling.utility.MessageUtils;

public class Reload implements CommandExecutor {
    public AntiNakedKilling plugin;
    public Reload(AntiNakedKilling plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;

        plugin.reloadConfig();
        ConfigMessages messages = new ConfigMessages(plugin.getConfig(), player, null);
        MessageUtils.sendMessage(player, ChatColor.GOLD + messages.translateFromKey("configReloadMessage"));

        return true;
    }
}
