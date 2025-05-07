package org.evasive.me.cSMultiblock.events.inventoryevents;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.multiblocks.RecipeBuilder;
import org.evasive.me.cSMultiblock.multiblocks.moltenforge.recipes.MoltenForgeRecipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.evasive.me.cSMultiblock.keys.NamespaceKeys.itemID;
import static org.evasive.me.cSMultiblock.util.ComponentUtils.formatKey;
import static org.evasive.me.cSMultiblock.util.ComponentUtils.legacyComponent;
import static org.evasive.me.cSMultiblock.util.MachineItemStacks.selectedRecipe;

public class ForgeSelectorGuiEvents implements Listener {

    @EventHandler
    public void selectForgeRecipe(InventoryClickEvent e){
        if(!e.getView().title().equals(legacyComponent("&8Forge Recipe Selector")))
            return;
        e.setCancelled(true);

        if(e.getClickedInventory() == null || !e.getClickedInventory().equals(e.getView().getTopInventory()))
            return;

        if(e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR))
            return;

        Player player = (Player) e.getWhoClicked();
        Vector vector = CSMultiblock.getInventoryVectorMap().getVector(player.getUniqueId());

        selectRecipe(vector, e.getSlot());

        setActiveRecipeItem(vector, e.getSlot());

        player.openInventory(CSMultiblock.getMultiblockMap().getMultiblockInfo(vector).getInventory());
    }

    public void selectRecipe(Vector vector, int slot){
        CSMultiblock.getMultiblockMap().getMultiblockInfo(vector).setForgeSelectedRecipe(MoltenForgeRecipes.values()[slot]);
    }

    public void setActiveRecipeItem(Vector vector, int slot){
        ItemStack temp = MoltenForgeRecipes.values()[slot].getRecipeBuilder().output().clone();
        ItemMeta tempMeta = selectedRecipe.getItemMeta();

        tempMeta.lore(getSelectedRecipeLore(slot));

        temp.setItemMeta(tempMeta);

        CSMultiblock.getMultiblockMap().getMultiblockInfo(vector).getInventory().setItem(22, temp);

    }

    public List<Component> getSelectedRecipeLore(int slot){

        List<Component> lore = new ArrayList<>();
        RecipeBuilder recipeBuilder = MoltenForgeRecipes.values()[slot].getRecipeBuilder();

        lore.add(legacyComponent("&a" + formatKey(MoltenForgeRecipes.values()[slot].name())));

        for(Map.Entry<ItemStack, Integer> entry : recipeBuilder.recipe().entrySet()){
            String itemName = entry.getKey().hasItemMeta() && entry.getKey().getPersistentDataContainer().has(itemID) ? entry.getKey().getItemMeta().getPersistentDataContainer().get(itemID, PersistentDataType.STRING) : entry.getKey().getType().name();
            lore.add(legacyComponent(String.format("&7 - %d %s", entry.getValue(), formatKey(itemName))));
        }

        return lore;
    }

}
