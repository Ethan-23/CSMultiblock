package org.evasive.me.cSMultiblock.multiblocks.moltenforge.inventories;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.evasive.me.cSMultiblock.multiblocks.MultiblockType;

import static org.evasive.me.cSMultiblock.util.MachineItemStacks.*;

public class MoltenForgeInventoryBuilder {



    public Inventory buildInventory(){

        Inventory moltenForgeInventory = Bukkit.createInventory(null, 54, MultiblockType.MOLTEN_FORGE.getMultiblockBuilder().name());

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

        moltenForgeInventory.setItem(22, selectedRecipe);
        moltenForgeInventory.setItem(31, idle);
        moltenForgeInventory.setItem(40, abort);

        return moltenForgeInventory;
    }

}
