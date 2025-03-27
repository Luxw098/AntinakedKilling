package uk.sleepylux.antinakedKilling.utility;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import uk.sleepylux.antinakedKilling.AntiNakedKilling;

public class BannedItems {
    public static ArrayList<Material> bannedItems = new ArrayList<>();

    public static void setup(AntiNakedKilling plugin) {
        bannedItems.addAll(
                plugin.getConfig().getStringList("BannedItems")
                        .stream().map(Material::getMaterial).toList()
        );
    }

    public static boolean BannedItemsContain(Material material) {
        return bannedItems.contains(material);
    }

    public static boolean BannedItemsInventory(PlayerInventory inv) {
        for (ItemStack item : inv.getStorageContents())
            if (item != null && BannedItemsContain(item.getType())) return true;
        for (ItemStack item : inv.getArmorContents())
            if (item != null && BannedItemsContain(item.getType())) return true;
        return false;
    }
}
