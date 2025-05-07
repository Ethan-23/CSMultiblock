package org.evasive.me.cSMultiblock.customItems;

import org.bukkit.inventory.ItemStack;

public class ItemMaker {

    public static ItemStack moltenForgeCore;
    public static ItemStack cropShredderCore;
    public static ItemStack hardenedIron;
    public static ItemStack steel;
    public static ItemStack golderiteBlock;

    public void initializeCustomItems(){
        moltenForgeCore = ItemList.MOLTEN_FORGE_CORE.getItemBuilder().buildItem();
        cropShredderCore = ItemList.CROP_SHREDDER_CORE.getItemBuilder().buildItem();
        hardenedIron = ItemList.HARDENED_IRON.getItemBuilder().buildItem();
        steel = ItemList.STEEL.getItemBuilder().buildItem();
        golderiteBlock = ItemList.GOLDERITE_BLOCK.getItemBuilder().buildItem();
    }

}
