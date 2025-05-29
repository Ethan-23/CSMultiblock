package me.evasive.cSMultiblock.multiblocks.moltenforge.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import me.evasive.cSMultiblock.customItems.ItemMaker;
import me.evasive.cSMultiblock.customItems.items.HardenedIron;
import me.evasive.cSMultiblock.multiblocks.RecipeBuilder;

import java.util.Map;

/**
 * Represents the Steel custom item recipe
 */
public class Steel implements RecipeBuilder {
    @Override
    public Map<ItemStack, Integer> recipe() {
        return Map.of(new HardenedIron().getItem(), 5, new ItemStack(Material.OBSIDIAN), 10);
    }

    @Override
    public ItemStack output() {
        return ItemMaker.steel;
    }

    @Override
    public int time() {
        return 180;
    }
}
