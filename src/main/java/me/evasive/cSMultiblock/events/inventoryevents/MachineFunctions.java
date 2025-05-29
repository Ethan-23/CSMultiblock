package me.evasive.cSMultiblock.events.inventoryevents;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.CSMultiblock;
import me.evasive.cSMultiblock.data.MultiblockInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.evasive.cSMultiblock.util.MachineItemStacks.fuelSpace;

/**
 * Functions for machine GUI
 */
public class MachineFunctions {

    public List<Integer> emptyFuelSlots(Inventory inventory){
        List<Integer> emptyFuelSlots = new ArrayList<>();
        for(int i = 0; i<9; i++){
            if(!Objects.requireNonNull(inventory.getItem(i)).getType().equals(Material.COAL)){
                emptyFuelSlots.add(i);
            }
        }
        return emptyFuelSlots;
    }

    public boolean hasEnoughFuel(Inventory inventory, int amount){
        if(amount <= 0)
            return true;
        for(int i = 0; i<9; i++){
            ItemStack itemStack = inventory.getItem(i);
            if(itemStack == null || !itemStack.getType().equals(Material.COAL))
                continue;
            amount -= itemStack.getAmount();
        }
        return amount <= 0;
    }

    public void takeFuel(Inventory inventory, int amount){
        for (int i = 0; i < 9; i++) {
            if (amount <= 0) break;

            ItemStack item = inventory.getItem(i);
            if (item == null || !item.getType().equals(Material.COAL)) continue;

            int stackAmount = item.getAmount();

            if (stackAmount <= amount) {
                amount -= stackAmount;
                inventory.setItem(i, fuelSpace); // remove entire stack
            } else {
                item.setAmount(stackAmount - amount); // reduce stack
                inventory.setItem(i, item);
                amount = 0;
            }
        }
    }

    public void cancelForgeTask(Vector vector){
        MultiblockInfo multiblockInfo = CSMultiblock.getMultiblockMap().getMultiblockInfo(vector);
        multiblockInfo.setForgeSelectedRecipe(null);
        multiblockInfo.setProcessingRecipe(null);
        multiblockInfo.setProgress(0);
        multiblockInfo.setProcessingItemStack(null);
    }

}
