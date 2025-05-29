package me.evasive.cSMultiblock.multiblocks.moltenforge.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.evasive.cSMultiblock.customItems.ItemMaker;
import me.evasive.cSMultiblock.multiblocks.RecipeBuilder;

import java.util.Map;

/**
 * Represents the HardenedIron custom item recipe
 */
public class HardenedIron implements RecipeBuilder {
    @Override
    public Map<ItemStack, Integer> recipe() {
        return Map.of(new ItemStack(Material.OBSIDIAN), 1, new ItemStack(Material.IRON_INGOT), 10);
    }

    @Override
    public ItemStack output() {
        return ItemMaker.hardenedIron;
    }

    @Override
    public int time() {
        return 100;
    }
}
