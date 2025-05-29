package me.evasive.cSMultiblock.customItems.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.evasive.cSMultiblock.customItems.ItemBuilder;
import me.evasive.cSMultiblock.customItems.ItemMaker;

import static me.evasive.cSMultiblock.util.ComponentUtils.legacyComponent;

/**
 * Represents the GolderiteBlock custom item
 */
public class GolderiteBlock implements ItemBuilder {
    @Override
    public Component getName() {
        return legacyComponent("&6Golderite Block");
    }

    @Override
    public String getItemID() {
        return "GOLDERITE_BLOCK";
    }

    @Override
    public Material getMaterial() {
        return Material.GILDED_BLACKSTONE;
    }

    @Override
    public boolean isGlowing() {
        return true;
    }

    @Override
    public ItemStack getItem() {
        return ItemMaker.golderiteBlock;
    }
}
