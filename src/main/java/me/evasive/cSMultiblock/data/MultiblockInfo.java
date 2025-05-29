package me.evasive.cSMultiblock.data;

import org.bukkit.World;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import me.evasive.cSMultiblock.multiblocks.MultiblockType;
import me.evasive.cSMultiblock.multiblocks.moltenforge.recipes.MoltenForgeRecipes;

/**
 * Represents the state and data of a multiblock structure, including
 * its type, associated inventory, crafting progress, selected recipes,
 * and in-world display information.
 */
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

    /**
     * Constructs a MultiblockInfo with the specified parameters for a fully initialized multiblock.
     *
     * @param multiblockType The type of multiblock structure
     * @param world The world where the multiblock exists
     * @param forgeSelectedRecipe The recipe selected by the user
     * @param processingRecipe The recipe currently being processed
     * @param processingItemStack The item being processed
     * @param inventory The inventory tied to the multiblock
     * @param progress The current progress of processing (0-100)
     * @param completeBuild Whether the multiblock is fully constructed
     */
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

    /**
     * Constructs a MultiblockInfo with the specified parameters for a fully initialized multiblock.
     *
     * @param multiblockType The type of multiblock structure
     * @param world The world where the multiblock exists
     * @param textDisplay The text displayed tied to the multiblock
     */
    public MultiblockInfo(MultiblockType multiblockType, TextDisplay textDisplay, World world) {
        this(multiblockType, world, null, null, null, null, 0, false);
        this.textDisplay = textDisplay;
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
