package me.evasive.cSMultiblock.customItems;

import org.bukkit.inventory.ItemStack;

/**
 * Utility class responsible for creating and holding instances of custom items.
 * Call {@link #initialize()} once to prepare all custom item stacks.
 */
public final class ItemMaker {

    public static ItemStack moltenForgeCore;
    public static ItemStack cropShredderCore;
    public static ItemStack hardenedIron;
    public static ItemStack steel;
    public static ItemStack golderiteBlock;


    private ItemMaker() {
        //prevents instantiation
    }

    /**
     * Initializes all custom item stacks.
     * Should be called once during plugin startup.
     */
    public static void initialize(){
        moltenForgeCore = ItemList.MOLTEN_FORGE_CORE.getItemBuilder().getItemBuilder();
        cropShredderCore = ItemList.CROP_SHREDDER_CORE.getItemBuilder().getItemBuilder();
        hardenedIron = ItemList.HARDENED_IRON.getItemBuilder().getItemBuilder();
        steel = ItemList.STEEL.getItemBuilder().getItemBuilder();
        golderiteBlock = ItemList.GOLDERITE_BLOCK.getItemBuilder().getItemBuilder();
    }

}
