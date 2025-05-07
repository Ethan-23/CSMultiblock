package org.evasive.me.cSMultiblock.customItems.multiblockcores;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cSMultiblock.customItems.ItemMaker;

import static org.evasive.me.cSMultiblock.util.ComponentUtils.legacyComponent;

public class MoltenForgeCore implements MultiblockCoreBuilder {
    @Override
    public Component getName() {
        return legacyComponent("&6Molten Forge Core");
    }

    @Override
    public String itemID() {
        return "MOLTEN_FORGE_CORE";
    }

    @Override
    public Material getMaterial() {
        return Material.OBSIDIAN;
    }

    @Override
    public boolean isGlowing() {
        return true;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.moltenForgeCore;
    }
}
