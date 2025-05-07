package org.evasive.me.cSMultiblock.multiblocks.moltenforge.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cSMultiblock.customItems.ItemMaker;
import org.evasive.me.cSMultiblock.multiblocks.RecipeBuilder;

import java.util.Map;

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
