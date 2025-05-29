package me.evasive.cSMultiblock.customItems.multiblockcores;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.evasive.cSMultiblock.customItems.ItemMaker;

import static me.evasive.cSMultiblock.util.ComponentUtils.legacyComponent;

/**
 * Represents the MoltenForgeCore custom block
 */
public class MoltenForgeCore implements MultiblockCoreBuilder {
    @Override
    public Component getName() {
        return legacyComponent("&6Molten Forge Core");
    }

    @Override
    public String getItemID() {
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
