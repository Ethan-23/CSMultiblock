package org.evasive.me.cSMultiblock.events.blockevents;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.data.MultiblockInfo;
import org.evasive.me.cSMultiblock.events.eventFunctions.MultiblockFunctions;
import org.evasive.me.cSMultiblock.events.eventFunctions.PacketFunctions;
import org.evasive.me.cSMultiblock.events.eventFunctions.TextDisplayFunctions;
import org.evasive.me.cSMultiblock.keys.NamespaceKeys;
import org.evasive.me.cSMultiblock.multiblocks.MultiblockType;

public class MultiblockCorePlace implements Listener {

    MultiblockFunctions multiblockFunctions = new MultiblockFunctions();

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
