package me.evasive.cSMultiblock.customItems.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.evasive.cSMultiblock.customItems.ItemBuilder;
import me.evasive.cSMultiblock.customItems.ItemMaker;

import static me.evasive.cSMultiblock.util.ComponentUtils.legacyComponent;

/**
 * Represents the Steel custom item
 */
public class Steel implements ItemBuilder {
    @Override
    public Component getName() {
        return legacyComponent("&7Steel");
    }

    @Override
    public String getItemID() {
        return "STEEL";
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_BLOCK;
    }

    @Override
    public boolean isGlowing() {
        return true;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.steel;
    }
}
