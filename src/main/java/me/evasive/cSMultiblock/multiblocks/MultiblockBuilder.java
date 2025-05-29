package me.evasive.cSMultiblock.multiblocks;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;

/**
 * Interface for custom shaped multiblocks
 */
public interface MultiblockBuilder {

    Component name();
    int requiredLevel();
    Map<Vector, Material> materialMap();
    ItemStack coreItem();
    Inventory inventory(Vector vector);
    List<Integer> inputSlots();
    List<Integer> outputSlots();


}
