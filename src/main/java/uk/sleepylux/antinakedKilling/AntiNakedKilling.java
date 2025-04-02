package uk.sleepylux.antinakedKilling;

import java.util.Objects;

import org.bukkit.plugin.java.JavaPlugin;

import uk.sleepylux.antinakedKilling.commands.Reload;
import uk.sleepylux.antinakedKilling.events.OnDamage;
import uk.sleepylux.antinakedKilling.events.OnPickup;
import uk.sleepylux.antinakedKilling.events.OnRespawn;
import uk.sleepylux.antinakedKilling.utility.BannedItems;


public final class AntiNakedKilling extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        
        BannedItems.setup(this);

        getServer().getPluginManager().registerEvents(new OnRespawn(this), this);
        getServer().getPluginManager().registerEvents(new OnPickup(this), this);
        getServer().getPluginManager().registerEvents(new OnDamage(this), this);

        Objects.requireNonNull(getCommand("ankreload")).setExecutor(new Reload(this));
    }
}
