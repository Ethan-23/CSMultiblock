package org.evasive.me.cSMultiblock.multiblocks.moltenforge.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.evasive.me.cSMultiblock.multiblocks.moltenforge.recipes.MoltenForgeRecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.evasive.me.cSMultiblock.keys.NamespaceKeys.itemID;
import static org.evasive.me.cSMultiblock.util.ComponentUtils.formatKey;
import static org.evasive.me.cSMultiblock.util.ComponentUtils.legacyComponent;

public class MoltenForgeRecipeSelectorInventory {

    public void openRecipeSelectInventory(Player player){

        Inventory forgeRecipeInventory = Bukkit.createInventory(null, 9, legacyComponent("&8Forge Recipe Selector"));

        displayRecipes(forgeRecipeInventory);

        player.openInventory(forgeRecipeInventory);
    }

    public void displayRecipes(Inventory inventory){
        for(int i = 0; i < MoltenForgeRecipes.values().length; i++){
            ItemStack recipeShowcase = MoltenForgeRecipes.values()[i].getRecipeBuilder().output().clone();
            ItemMeta showcaseMeta = recipeShowcase.getItemMeta();
            List<Component> lore = new ArrayList<>();
            int time = MoltenForgeRecipes.values()[i].getRecipeBuilder().time();
            String timeString = String.format("&7%d:%02d", (time)/60, (time)%60);
            lore.add(legacyComponent(String.format("&eSmelt Time: %s", timeString)));

            lore.add(legacyComponent("&eRecipe: "));


            for(Map.Entry<ItemStack, Integer> entry : MoltenForgeRecipes.values()[i].getRecipeBuilder().recipe().entrySet()){
                String itemName = entry.getKey().hasItemMeta() && entry.getKey().getPersistentDataContainer().has(itemID) ? entry.getKey().getItemMeta().getPersistentDataContainer().get(itemID, PersistentDataType.STRING) : entry.getKey().getType().name();
                lore.add(legacyComponent(String.format("&7 - %d %s", entry.getValue(), formatKey(itemName))));
            }

            showcaseMeta.lore(lore);
            recipeShowcase.setItemMeta(showcaseMeta);
            inventory.setItem(i, recipeShowcase);
        }
    }

}
