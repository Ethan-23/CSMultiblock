package me.evasive.cSMultiblock.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static me.evasive.cSMultiblock.util.ComponentUtils.legacyComponent;

/**
 * Specific itemstacks for Multiblocks
 */
public class MachineItemStacks {

    public static ItemStack emptySpace;
    public static ItemStack fuelSpace;
    public static ItemStack outputSpace;
    public static ItemStack progress;
    public static ItemStack idle;
    public static ItemStack outOfFuel;
    public static ItemStack selectedRecipe;
    public static ItemStack abort;

    public void init(){
        emptySpace = itemMaker(
                Material.GRAY_STAINED_GLASS_PANE,
                legacyComponent(""),
                List.of()
        );
        fuelSpace = itemMaker(
                Material.RED_STAINED_GLASS_PANE,
                legacyComponent("&cFuel Input"),
                List.of()
        );
        outputSpace = itemMaker(
                Material.BLUE_STAINED_GLASS_PANE,
                legacyComponent("&bEmpty Output"),
                List.of()
        );
        idle = itemMaker(
                Material.ORANGE_STAINED_GLASS_PANE,
                legacyComponent("&6&lIDLE"),
                List.of()
        );
        outOfFuel = itemMaker(
                Material.RED_STAINED_GLASS_PANE,
                legacyComponent("&c&lIDLE &r&6(NO FUEL)"),
                List.of()
        );
        progress = itemMaker(
                Material.YELLOW_STAINED_GLASS_PANE,
                legacyComponent("&e&lIN PROGRESS"),
                List.of()
        );
        selectedRecipe = itemMaker(
                Material.YELLOW_STAINED_GLASS_PANE,
                legacyComponent("&e&lSELECTED RECIPE"),
                List.of()
        );
        abort = itemMaker(
                Material.BARRIER,
                legacyComponent("&c&lABORT SMELT"),
                List.of());

    }

    public static ItemStack itemMaker(Material material, Component name, List<Component> lore){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.lore(lore);
        meta.displayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack setLore(ItemStack itemStack, List<Component> lore){
        ItemStack clone = itemStack.clone();
        ItemMeta meta = clone.getItemMeta();
        meta.lore(lore);
        clone.setItemMeta(meta);
        return clone;
    }
}
