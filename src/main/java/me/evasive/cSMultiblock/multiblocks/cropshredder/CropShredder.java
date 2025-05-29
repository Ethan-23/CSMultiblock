package me.evasive.cSMultiblock.multiblocks.cropshredder;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.customItems.ItemMaker;
import me.evasive.cSMultiblock.multiblocks.MultiblockBuilder;

import java.util.List;
import java.util.Map;

import static me.evasive.cSMultiblock.util.ComponentUtils.legacyComponent;

public class CropShredder implements MultiblockBuilder {
    @Override
    public Component name() {
        return legacyComponent("Crop Shredder");
    }

    @Override
    public int requiredLevel() {
        return 1;
    }

    @Override
    public Map<Vector, Material> materialMap() {
        return Map.ofEntries(
                //Bottom Layer
                Map.entry(new Vector(-2, 0, -1), Material.SMOOTH_STONE),Map.entry(new Vector(-1, 0, -1), Material.SMOOTH_STONE),Map.entry(new Vector(0, 0, -1), Material.SMOOTH_STONE),Map.entry(new Vector(1, 0, -1), Material.SMOOTH_STONE),Map.entry(new Vector(2, 0, -1), Material.SMOOTH_STONE),
                Map.entry(new Vector(-2, 0, 0), Material.SMOOTH_STONE), Map.entry(new Vector(-1, 0, 0), Material.STONECUTTER),Map.entry(new Vector(0, 0, 0), coreItem().getType()), Map.entry(new Vector(1, 0, 0), Material.STONECUTTER),Map.entry(new Vector(2, 0, 0), Material.SMOOTH_STONE),

                //Middle Layer
                Map.entry(new Vector(-2, 1, -1), Material.SMOOTH_STONE),Map.entry(new Vector(-1, 1, -1), Material.SMOOTH_STONE),Map.entry(new Vector(0, 1, -1), Material.SMOOTH_STONE),Map.entry(new Vector(1, 1, -1), Material.SMOOTH_STONE),Map.entry(new Vector(2, 1, -1), Material.SMOOTH_STONE),
                Map.entry(new Vector(-2, 1, 0), Material.SMOOTH_STONE), Map.entry(new Vector(-1, 1, 0), Material.SMOOTH_STONE_SLAB),Map.entry(new Vector(0, 1, 0), Material.SMOOTH_STONE_SLAB), Map.entry(new Vector(1, 1, 0), Material.SMOOTH_STONE_SLAB),Map.entry(new Vector(2, 1, 0), Material.SMOOTH_STONE)
        );
    }

    @Override
    public ItemStack coreItem() {
        return ItemMaker.cropShredderCore;
    }

    @Override
    public Inventory inventory(Vector vector) {
        return null;
    }

    @Override
    public List<Integer> inputSlots() {
        return List.of();
    }

    @Override
    public List<Integer> outputSlots() {
        return List.of();
    }
}
