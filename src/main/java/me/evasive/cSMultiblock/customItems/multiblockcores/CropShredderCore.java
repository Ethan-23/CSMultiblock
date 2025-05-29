package me.evasive.cSMultiblock.customItems.multiblockcores;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.evasive.cSMultiblock.customItems.ItemMaker;

import static me.evasive.cSMultiblock.util.ComponentUtils.legacyComponent;

/**
 * Represents the CropShredderCore custom block
 */
public class CropShredderCore implements MultiblockCoreBuilder{
    @Override
    public Component getName() {
        return legacyComponent("&7Crop Shredder");
    }

    @Override
    public String getItemID() {
        return "CROP_SHREDDER_CORE";
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
        return ItemMaker.cropShredderCore;
    }
}
