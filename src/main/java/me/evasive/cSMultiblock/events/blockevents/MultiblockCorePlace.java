package me.evasive.cSMultiblock.events.blockevents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.CSMultiblock;
import me.evasive.cSMultiblock.data.MultiblockInfo;
import me.evasive.cSMultiblock.events.eventFunctions.MultiblockFunctions;
import me.evasive.cSMultiblock.events.eventFunctions.PacketFunctions;
import me.evasive.cSMultiblock.events.eventFunctions.TextDisplayFunctions;
import me.evasive.cSMultiblock.keys.NamespaceKeys;
import me.evasive.cSMultiblock.multiblocks.MultiblockType;

/**
 * Handles place events with the multiblock core.
 */
public class MultiblockCorePlace implements Listener {

    MultiblockFunctions multiblockFunctions = new MultiblockFunctions();

    /**
     * Handles core place event.
     *
     * <p>Triggers when the player places a multiblock core</p>
     *
     * @param e The interact event triggered by a player placing a block.
     */
    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent e){
        ItemStack placedBlock = e.getItemInHand();
        if(!placedCore(placedBlock)) return;

        Vector vector = e.getBlockPlaced().getLocation().toVector();

        CSMultiblock.getMultiblockMap().addMultiblock(vector, new MultiblockInfo(getCoreType(placedBlock), new TextDisplayFunctions().spawnIncompleteText(vector, e.getBlockPlaced().getWorld()),e.getBlockPlaced().getWorld()));

        if(new PacketFunctions().allBlockMatch(vector, e.getBlock().getWorld()))
            multiblockFunctions.createMultiblock(vector, e.getPlayer());
    }

    private MultiblockType getCoreType(ItemStack placedBlock) {

        String itemID = placedBlock.getItemMeta().getPersistentDataContainer().get(NamespaceKeys.itemID, PersistentDataType.STRING);
        itemID = itemID.replace("_CORE", "");
        return MultiblockType.valueOf(itemID);

    }

    public boolean placedCore(ItemStack placedBlock){
        return placedBlock.hasItemMeta() && placedBlock.getItemMeta().getPersistentDataContainer().has(NamespaceKeys.coreKey);
    }


}
