package org.evasive.me.cSMultiblock.customItems.multiblockcores;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cSMultiblock.customItems.ItemMaker;

import static org.evasive.me.cSMultiblock.util.ComponentUtils.legacyComponent;

public class CropShredderCore implements MultiblockCoreBuilder{
    @Override
    public Component getName() {
        return legacyComponent("&7Crop Shredder");
    }

    @Override
    public String itemID() {
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
