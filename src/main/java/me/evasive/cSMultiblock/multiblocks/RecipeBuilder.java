package me.evasive.cSMultiblock.multiblocks;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Interface for custom recipes
 */
public interface RecipeBuilder {
    Map<ItemStack, Integer> recipe();
    ItemStack output();
    int time();
}
