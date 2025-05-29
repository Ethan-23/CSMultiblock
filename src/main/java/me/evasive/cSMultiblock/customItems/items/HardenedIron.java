package me.evasive.cSMultiblock.customItems.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.evasive.cSMultiblock.customItems.ItemBuilder;
import me.evasive.cSMultiblock.customItems.ItemMaker;

import static me.evasive.cSMultiblock.util.ComponentUtils.legacyComponent;

/**
 * Represents the HardenedIron custom item
 */
public class HardenedIron implements ItemBuilder {
    @Override
    public Component getName() {
        return legacyComponent("&3Hardened Iron");
    }

    @Override
    public String getItemID() {
        return "HARDENED_IRON";
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_INGOT;
    }

    @Override
    public boolean isGlowing() {
        return true;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.hardenedIron;
    }
}
