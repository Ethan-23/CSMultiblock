package org.evasive.me.cSMultiblock.multiblocks;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface RecipeBuilder {
    Map<ItemStack, Integer> recipe();
    ItemStack output();
    int time();
}
