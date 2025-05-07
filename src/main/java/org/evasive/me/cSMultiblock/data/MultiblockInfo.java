package org.evasive.me.cSMultiblock.data;

import org.bukkit.World;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cSMultiblock.multiblocks.MultiblockType;
import org.evasive.me.cSMultiblock.multiblocks.moltenforge.recipes.MoltenForgeRecipes;

public class MultiblockInfo {
    private MultiblockType multiblockType;
    private Inventory inventory;
    private MoltenForgeRecipes forgeSelectedRecipe;
    private MoltenForgeRecipes processingRecipe;
    private ItemStack processingItemStack;
    private int progress;
    private TextDisplay textDisplay;
    private World world;
    private boolean completeBuild;

    public MultiblockInfo(MultiblockType multiblockType, TextDisplay textDisplay, World world) {
        this.multiblockType = multiblockType;
        this.processingItemStack = null;
        this.inventory = null;
        this.forgeSelectedRecipe = null;
        this.processingRecipe = null;
        this.progress = 0;
        this.textDisplay = textDisplay;
        this.completeBuild = false;
        this.world = world;
    }

    public MultiblockInfo(MultiblockType multiblockType, World world, MoltenForgeRecipes forgeSelectedRecipe, MoltenForgeRecipes processingRecipe, ItemStack processingItemStack, Inventory inventory, int progress, boolean completeBuild) {
        this.multiblockType = multiblockType;
        this.world = world;
        this.forgeSelectedRecipe = forgeSelectedRecipe;
        this.processingRecipe = processingRecipe;
        this.progress = progress;
        this.processingItemStack = processingItemStack;
        this.inventory = inventory;
        this.textDisplay = null;
        this.completeBuild = completeBuild;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public MoltenForgeRecipes getProcessingRecipe() {
        return processingRecipe;
    }

    public void setProcessingRecipe(MoltenForgeRecipes processingRecipe) {
        this.processingRecipe = processingRecipe;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public MoltenForgeRecipes getForgeSelectedRecipe() {
        return forgeSelectedRecipe;
    }

    public void setForgeSelectedRecipe(MoltenForgeRecipes forgeSelectedRecipe) {
        this.forgeSelectedRecipe = forgeSelectedRecipe;
    }

    public void setProcessingItemStack(ItemStack processingItemStack) {
        this.processingItemStack = processingItemStack;
    }

    public ItemStack getProcessingItemStack() {
        return processingItemStack;
    }

    public TextDisplay getTextDisplay() {
        return textDisplay;
    }

    public void setTextDisplay(TextDisplay textDisplay) {
        this.textDisplay = textDisplay;
    }

    public MultiblockType getMultiblockType() {
        return multiblockType;
    }

    public boolean isCompleteBuild() {
        return completeBuild;
    }

    public void setCompleteBuild(boolean completeBuild) {
        this.completeBuild = completeBuild;
    }

    public void setMultiblockType(MultiblockType multiblockType) {
        this.multiblockType = multiblockType;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
