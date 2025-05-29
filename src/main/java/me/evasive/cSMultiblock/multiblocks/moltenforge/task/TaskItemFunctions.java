package me.evasive.cSMultiblock.multiblocks.moltenforge.task;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.evasive.cSMultiblock.multiblocks.moltenforge.recipes.MoltenForgeRecipes;

import java.util.List;

import static me.evasive.cSMultiblock.util.ComponentUtils.formatKey;
import static me.evasive.cSMultiblock.util.ComponentUtils.legacyComponent;
import static me.evasive.cSMultiblock.util.MachineItemStacks.progress;
import static me.evasive.cSMultiblock.util.MachineItemStacks.setLore;

public class TaskItemFunctions {
    /**
     * Functions for the molten forge task
     */
    public void updateProgressIcon(Inventory inventory, ItemStack itemStack){
        inventory.setItem(31, itemStack);
    }

    public void updateProgressDisplay(int progressAmount, Inventory inventory, MoltenForgeRecipes moltenForgeRecipes){
        int time = moltenForgeRecipes.getRecipeBuilder().time();
        ItemStack progressDisplay = setLore(progress.clone(), List.of(
                legacyComponent(String.format("&l%s &r&e(&7%d:%02d&e)",getProgressString(progressAmount, moltenForgeRecipes), (time-progressAmount)/60, (time-progressAmount)%60)))
        );
        ItemMeta meta = progressDisplay.getItemMeta();
        meta.displayName(legacyComponent(String.format("&e&lIN PROGRESS &r&e(&7%s&e)", formatKey(moltenForgeRecipes.name()))));
        progressDisplay.setItemMeta(meta);
        updateProgressIcon(inventory, progressDisplay);
    }

    public String getProgressString(int progressAmount, MoltenForgeRecipes moltenForgeRecipes){
        int finishedProgress = moltenForgeRecipes.getRecipeBuilder().time();
        float totalProgress = (float) progressAmount / finishedProgress;
        int greenCount = Math.round(totalProgress * 25);
        String greenBars = "|".repeat(greenCount);
        String redBars = "|".repeat(25 - greenCount);
        return String.format("&a%s&c%s", greenBars, redBars);
    }

}
