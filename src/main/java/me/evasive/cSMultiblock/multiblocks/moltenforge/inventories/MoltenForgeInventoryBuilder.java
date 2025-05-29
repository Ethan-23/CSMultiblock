package me.evasive.cSMultiblock.multiblocks.moltenforge.inventories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import me.evasive.cSMultiblock.multiblocks.MultiblockType;

import static me.evasive.cSMultiblock.util.MachineItemStacks.*;

/**
 * Builder for molten forge inventory
 */
public class MoltenForgeInventoryBuilder {

    public static final int INVENTORY_SIZE = 54;
    public static final int SELECTED_RECIPE_SLOT = 22;
    public static final int PROGRESS_SLOT = 31;
    public static final int ABORT_SLOT = 40;

    public Inventory buildInventory(){

        Inventory moltenForgeInventory = Bukkit.createInventory(null, INVENTORY_SIZE, MultiblockType.MOLTEN_FORGE.getMultiblockBuilder().name());

        for (int i = 0; i < 9; i++) {
            moltenForgeInventory.setItem(i, fuelSpace);
        }

        for(int i : MultiblockType.MOLTEN_FORGE.getMultiblockBuilder().outputSlots()){
            moltenForgeInventory.setItem(i, outputSpace);
        }


        for (int i = 9; i < moltenForgeInventory.getSize(); i++) {
            if (!MultiblockType.MOLTEN_FORGE.getMultiblockBuilder().inputSlots().contains(i) && !MultiblockType.MOLTEN_FORGE.getMultiblockBuilder().outputSlots().contains(i)) {
                moltenForgeInventory.setItem(i, emptySpace);
            }
        }


        moltenForgeInventory.setItem(SELECTED_RECIPE_SLOT, selectedRecipe);

        moltenForgeInventory.setItem(PROGRESS_SLOT, idle);

        moltenForgeInventory.setItem(ABORT_SLOT, abort);

        return moltenForgeInventory;
    }

}
