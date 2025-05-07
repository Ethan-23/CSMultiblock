package org.evasive.me.cSMultiblock.events.inventoryevents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.multiblocks.MultiblockType;
import org.evasive.me.cSMultiblock.multiblocks.moltenforge.inventories.MoltenForgeRecipeSelectorInventory;

import java.util.List;

import static org.evasive.me.cSMultiblock.util.MachineItemStacks.*;

public class MoltenForgeGuiEvents implements Listener {

    @EventHandler
    public void onInventoryClicked(InventoryClickEvent e){


        if(!e.getView().title().equals(MultiblockType.MOLTEN_FORGE.getMultiblockBuilder().name()))
            return;

        if(e.getClickedInventory() == null) return;


        if(e.getClickedInventory().equals(e.getView().getBottomInventory())){
            handlePlayerInventoryClick(e);
        }

        if(e.getClickedInventory() != e.getView().getTopInventory()){
            return;
        }


        List<Integer> inputSlots = List.of(19, 20, 28, 29, 37, 38);
        if(inputSlots.contains(e.getSlot())){
            return;
        }

        List<Integer> outputSlots = List.of(24, 25, 33, 34, 42, 43);
        if(outputSlots.contains(e.getSlot())){
            handleOutputSlots(e);
            return;
        }

        List<Integer> fuelSlots = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8);
        if(fuelSlots.contains(e.getSlot())){
            handleFuelSlots(e);
            return;
        }

        Player player = (Player) e.getWhoClicked();

        if(e.getSlot() == 22)
            handleRecipeSelectorSlot(player);

        if(e.getSlot() == 40)
            handleAbortSlot(e);

        if(e.getClickedInventory().equals(e.getView().getTopInventory()))
            e.setCancelled(true);

    }

    private void handleAbortSlot(InventoryClickEvent e) {
        e.setCancelled(true);
        Player player = (Player) e.getWhoClicked();
        e.getInventory().setItem(22, selectedRecipe);
        new MachineFunctions().cancelForgeTask(CSMultiblock.getInventoryVectorMap().getVector(player.getUniqueId()));
    }

    private void handlePlayerInventoryClick(InventoryClickEvent e) {
        List<Integer> fuelSlots = new MachineFunctions().emptyFuelSlots(e.getView().getTopInventory());
        if(e.getCurrentItem() != null && e.getCurrentItem().getType().equals(Material.COAL) && !fuelSlots.isEmpty()){
            e.setCancelled(true);
            ItemStack fuelStack = e.getCurrentItem();
            Player player = (Player) e.getWhoClicked();
            player.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
            e.getView().getTopInventory().setItem(fuelSlots.getFirst(), fuelStack);
        }
    }

    public void handleOutputSlots(InventoryClickEvent e){

        ItemStack itemDown = e.getCurrentItem();
        ItemStack itemCursor = e.getCursor();

        if(itemDown == null || itemDown.isSimilar(outputSpace)){
            e.setCancelled(true);
            return;
        }

        if(itemCursor.getType().equals(Material.AIR)) {
            Bukkit.getScheduler().runTask(CSMultiblock.getCore(), () -> e.getClickedInventory().setItem(e.getSlot(), outputSpace));
            return;
        }else if(e.getAction().name().contains("PLACE") && itemDown.isSimilar(itemCursor)){
            int maxStackSize = itemDown.getMaxStackSize();
            int totalAmount = itemCursor.getAmount() + itemDown.getAmount();

            if (totalAmount <= maxStackSize) {
                itemCursor.setAmount(totalAmount);
                e.setCurrentItem(outputSpace.clone());
            } else {
                itemCursor.setAmount(maxStackSize);
                itemDown.setAmount(totalAmount - maxStackSize);
            }
        }

        e.setCancelled(true);
    }

    public void handleFuelSlots(InventoryClickEvent e){
        e.setCancelled(true);

        if(e.getCurrentItem() == null || !e.getCurrentItem().getType().equals(Material.COAL))
            return;

        ItemStack fuelStack = e.getCurrentItem();
        e.getClickedInventory().setItem(e.getSlot(), fuelSpace);
        Player player = (Player) e.getWhoClicked();
        player.getInventory().addItem(fuelStack);
    }

    public void handleRecipeSelectorSlot(Player player){
        new MoltenForgeRecipeSelectorInventory().openRecipeSelectInventory(player);
    }

}
