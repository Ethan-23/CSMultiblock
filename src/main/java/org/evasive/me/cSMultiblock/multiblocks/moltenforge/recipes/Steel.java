package org.evasive.me.cSMultiblock.multiblocks.moltenforge.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cSMultiblock.customItems.ItemMaker;
import org.evasive.me.cSMultiblock.customItems.items.HardenedIron;
import org.evasive.me.cSMultiblock.multiblocks.RecipeBuilder;

import java.util.Map;

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
