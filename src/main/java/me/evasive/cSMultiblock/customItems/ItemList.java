package me.evasive.cSMultiblock.customItems;

import me.evasive.cSMultiblock.customItems.items.GolderiteBlock;
import me.evasive.cSMultiblock.customItems.items.Steel;
import me.evasive.cSMultiblock.customItems.multiblockcores.CropShredderCore;
import me.evasive.cSMultiblock.customItems.multiblockcores.MoltenForgeCore;
import me.evasive.cSMultiblock.customItems.items.HardenedIron;

/**
 * Enum representing all custom items available in the plugin.
 * Each constant holds an associated {@link ItemBuilder} instance
 * that defines the properties and behavior of the item.
 */
public enum ItemList {
    MOLTEN_FORGE_CORE(new MoltenForgeCore()),
    CROP_SHREDDER_CORE(new CropShredderCore()),
    HARDENED_IRON(new HardenedIron()),
    STEEL(new Steel()),
    GOLDERITE_BLOCK(new GolderiteBlock());

    private final ItemBuilder itemBuilder;

    /**
     * Constructs an enum constant with the given {@link ItemBuilder}.
     *
     * @param itemBuilder the item builder associated with this item
     */
    ItemList(ItemBuilder itemBuilder){
        this.itemBuilder = itemBuilder;
    }

    /**
     * Returns the {@link ItemBuilder} associated with this item.
     *
     * @return the item builder instance
     */
    public ItemBuilder getItemBuilder(){
        return itemBuilder;
    }
}
