package org.evasive.me.cSMultiblock.customItems.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cSMultiblock.customItems.ItemBuilder;
import org.evasive.me.cSMultiblock.customItems.ItemMaker;

import static org.evasive.me.cSMultiblock.util.ComponentUtils.legacyComponent;

public class HardenedIron implements ItemBuilder {
    @Override
    public Component getName() {
        return legacyComponent("&3Hardened Iron");
    }

    @Override
    public String itemID() {
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
