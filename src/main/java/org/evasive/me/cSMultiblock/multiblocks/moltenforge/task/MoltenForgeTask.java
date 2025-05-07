package org.evasive.me.cSMultiblock.multiblocks.moltenforge.task;

import org.bukkit.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.data.MultiblockInfo;
import org.evasive.me.cSMultiblock.events.inventoryevents.MachineFunctions;
import org.evasive.me.cSMultiblock.multiblocks.RecipeBuilder;
import org.evasive.me.cSMultiblock.multiblocks.moltenforge.recipes.MoltenForgeRecipes;

import java.util.List;
import java.util.Map;

import static org.evasive.me.cSMultiblock.util.MachineItemStacks.*;
import static org.evasive.me.cSMultiblock.util.Sounds.playMoltenForgeStart;

public class MoltenForgeTask {

    TaskItemFunctions taskItemFunctions = new TaskItemFunctions();

    public void activateForgeTick(Vector vector){
        MultiblockInfo multiblockInfo = CSMultiblock.getMultiblockMap().getMultiblockInfo(vector);
        Inventory inventory = multiblockInfo.getInventory();

        if(inventory == null)
            return;

        if(multiblockInfo.getForgeSelectedRecipe() == null){
            inventory.setItem(31, idle);
            return;
        }


        if(!new MachineFunctions().hasEnoughFuel(inventory, 1)){
            taskItemFunctions.updateProgressIcon(inventory, outOfFuel);
            return;
        }

        if(multiblockInfo.getProcessingItemStack() == null){

            multiblockInfo.setProcessingRecipe(multiblockInfo.getForgeSelectedRecipe());
            RecipeBuilder recipeItem = multiblockInfo.getProcessingRecipe().getRecipeBuilder();
//            updateCookingItemDisplay(inventory, multiblockInfo.getProcessingRecipe().name());
            if(!isOutputOpen(multiblockInfo, inventory)) {
                return;
            }

            if(!scanForInputs(multiblockInfo, recipeItem, inventory)){
                return;
            }


            takeInputs(multiblockInfo, multiblockInfo.getProcessingRecipe(), inventory, vector);
            playMoltenForgeStart(multiblockInfo.getWorld(), vector);

        }else{

            addItemProgress(multiblockInfo, multiblockInfo.getProcessingRecipe(), inventory);

        }
    }

    public void addItemProgress(MultiblockInfo multiblockInfo, MoltenForgeRecipes recipeItem, Inventory inventory){
        multiblockInfo.setProgress(multiblockInfo.getProgress() + 1);

        taskItemFunctions.updateProgressDisplay(multiblockInfo.getProgress(), multiblockInfo.getInventory(), recipeItem);

        new MachineFunctions().takeFuel(inventory, 1);

        if(multiblockInfo.getProgress() >= recipeItem.getRecipeBuilder().time()){
            finishCraft(multiblockInfo, inventory, recipeItem.getRecipeBuilder());
        }
    }



    private boolean isOutputOpen(MultiblockInfo multiblockInfo, Inventory inventory) {
        for(int i : multiblockInfo.getMultiblockType().getMultiblockBuilder().outputSlots()){
            ItemStack itemStack = inventory.getItem(i);
            if(itemStack == null || itemStack.getType().equals(Material.AIR) || itemStack.getType().equals(outputSpace.getType()))
                return true;
        }
        return false;
    }

    public boolean scanForInputs(MultiblockInfo multiblockInfo, RecipeBuilder recipeItem, Inventory inventory){

        for (Map.Entry<ItemStack, Integer> entry : recipeItem.recipe().entrySet()) {
            ItemStack item = entry.getKey();
            int totalNeeded = entry.getValue();

            if(countMaterials(inventory, item, multiblockInfo.getMultiblockType().getMultiblockBuilder().inputSlots()) < totalNeeded)
                return false;

        }
        return true;
    }

    public int countMaterials(Inventory inventory, ItemStack item, List<Integer> slots){
        int amount = 0;
        for(int i : slots){
            if(inventory.getItem(i) == null)
                continue;
            if(!inventory.getItem(i).isSimilar(item))
                continue;
            amount += inventory.getItem(i).getAmount();
        }
        return amount;
    }

    public void takeInputs(MultiblockInfo multiblockInfo, MoltenForgeRecipes moltenForgeRecipes, Inventory inventory, Vector vector){
        for (Map.Entry<ItemStack, Integer> entry : moltenForgeRecipes.getRecipeBuilder().recipe().entrySet()) {
            ItemStack item = entry.getKey();
            int totalNeeded = entry.getValue();

            takeMaterials(multiblockInfo.getMultiblockType().getMultiblockBuilder().inputSlots(), inventory, item, totalNeeded);
        }

        multiblockInfo.setProcessingItemStack(moltenForgeRecipes.getRecipeBuilder().output());
        addItemProgress(multiblockInfo, moltenForgeRecipes, inventory);
    }

    public void takeMaterials(List<Integer> slots, Inventory inventory, ItemStack item, int totalNeeded){
        int collected = 0;
        for (int i : slots) {
            if(collected == totalNeeded)
                break;
            if(inventory.getItem(i) == null || !inventory.getItem(i).isSimilar(item))
                continue;
            ItemStack temp = inventory.getItem(i);
            if(temp.getAmount() > totalNeeded){
                temp.setAmount(temp.getAmount() - totalNeeded);
                inventory.setItem(i, temp);
                collected = totalNeeded;
            }else {
                collected += temp.getAmount();
                inventory.setItem(i, null);
            }
        }
    }

    public void finishCraft(MultiblockInfo multiblockInfo, Inventory inventory, RecipeBuilder recipeItem){
        //Check for an open slot to place item in
        for(int i : multiblockInfo.getMultiblockType().getMultiblockBuilder().outputSlots()){
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType() == Material.AIR || item.getType().equals(outputSpace.getType())) {
                multiblockInfo.getInventory().setItem(i, recipeItem.output());
                break;
            }else if(item.isSimilar(recipeItem.output()) && item.getAmount() + recipeItem.output().getAmount() <= item.getMaxStackSize()){
                item.setAmount(item.getAmount() + recipeItem.output().getAmount());
                multiblockInfo.getInventory().setItem(i, item);
                break;
            }
        }
        multiblockInfo.setProgress(0);
        multiblockInfo.setProcessingItemStack(null);
        multiblockInfo.setProcessingRecipe(null);
        taskItemFunctions.updateProgressIcon(inventory, idle);
    }



}
