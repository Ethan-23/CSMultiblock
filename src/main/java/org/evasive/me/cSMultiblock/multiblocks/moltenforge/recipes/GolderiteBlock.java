package org.evasive.me.cSMultiblock.multiblocks.moltenforge.recipes;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cSMultiblock.customItems.ItemMaker;
import org.evasive.me.cSMultiblock.multiblocks.RecipeBuilder;

import java.util.Map;

public class GolderiteBlock implements RecipeBuilder {
    @Override
    public Map<ItemStack, Integer> recipe() {
        return Map.of(new ItemStack(Material.NETHERITE_INGOT), 3, new ItemStack(Material.GOLD_INGOT), 6);
    }

    @Override
    public ItemStack output() {
        return ItemMaker.golderiteBlock;
    }

    @Override
    public int time() {
        return 600;
    }
}
