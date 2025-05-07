package org.evasive.me.cSMultiblock.multiblocks.moltenforge;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.customItems.ItemMaker;
import org.evasive.me.cSMultiblock.multiblocks.MultiblockBuilder;

import java.util.List;
import java.util.Map;

import static org.evasive.me.cSMultiblock.util.ComponentUtils.legacyComponent;

public class MoltenForge implements MultiblockBuilder {
    @Override
    public Component name() {
        return legacyComponent("&8Molten Forge");
    }

    @Override
    public int requiredLevel() {
        return 0;
    }

    @Override
    public Map<Vector, Material> materialMap() {
        return Map.ofEntries(
                //Bottom Layer
                Map.entry(new Vector(-1, 0, -1), Material.NETHER_BRICKS), Map.entry(new Vector(0, 0, -1), Material.NETHER_BRICKS), Map.entry(new Vector(1, 0, -1), Material.NETHER_BRICKS),
                Map.entry(new Vector(-1, 0, 0), Material.NETHER_BRICKS), Map.entry(new Vector(0, 0, 0), coreItem().getType()), Map.entry(new Vector(1, 0, 0), Material.NETHER_BRICKS),
                Map.entry(new Vector(-1, 0, 1), Material.NETHER_BRICKS), Map.entry(new Vector(0, 0, 1), Material.NETHER_BRICKS), Map.entry(new Vector(1, 0, 1), Material.NETHER_BRICKS),

                //Middle Layer
                Map.entry(new Vector(-1, 1, -1), Material.NETHER_BRICKS), Map.entry(new Vector(0, 1, -1), Material.CHISELED_NETHER_BRICKS), Map.entry(new Vector(1, 1, -1), Material.NETHER_BRICKS),
                Map.entry(new Vector(-1, 1, 0), Material.NETHER_BRICK_WALL), Map.entry(new Vector(0, 1, 0), Material.GLOWSTONE), Map.entry(new Vector(1, 1, 0), Material.NETHER_BRICK_WALL),
                Map.entry(new Vector(-1, 1, 1), Material.NETHER_BRICKS), Map.entry(new Vector(0, 1, 1), Material.NETHER_BRICK_WALL), Map.entry(new Vector(1, 1, 1), Material.NETHER_BRICKS),

                //Top Layer
                Map.entry(new Vector(-1, 2, -1), Material.NETHER_BRICKS), Map.entry(new Vector(0, 2, -1), Material.NETHER_BRICKS), Map.entry(new Vector(1, 2, -1), Material.NETHER_BRICKS),
                Map.entry(new Vector(-1, 2, 0), Material.NETHER_BRICKS), Map.entry(new Vector(1, 2, 0), Material.NETHER_BRICKS),
                Map.entry(new Vector(-1, 2, 1), Material.NETHER_BRICKS), Map.entry(new Vector(0, 2, 1), Material.NETHER_BRICKS), Map.entry(new Vector(1, 2, 1), Material.NETHER_BRICKS)
        );
    }

    @Override
    public ItemStack coreItem() {
        return ItemMaker.moltenForgeCore;
    }

    @Override
    public Inventory inventory(Vector vector) {
        return CSMultiblock.getMultiblockMap().getMultiblockInfo(vector).getInventory();
    }

    @Override
    public List<Integer> inputSlots() {
        return List.of(19,20,28,29,37,38);
    }

    @Override
    public List<Integer> outputSlots() {
        return List.of(24,25,33,34,42,43);
    }
}
