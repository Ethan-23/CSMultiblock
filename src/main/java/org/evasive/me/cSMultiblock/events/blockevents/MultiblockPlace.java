package org.evasive.me.cSMultiblock.events.blockevents;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.events.eventFunctions.MultiblockFunctions;
import org.evasive.me.cSMultiblock.events.eventFunctions.PacketFunctions;

public class MultiblockPlace implements Listener {

    MultiblockFunctions multiblockFunctions = new MultiblockFunctions();
    PacketFunctions packetFunctions = new PacketFunctions();

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent e){
        Block block = e.getBlockPlaced();
        Vector placedVector = block.getLocation().toVector();
        Vector coreVector = CSMultiblock.getMultiblockMap().getConnectedCore(placedVector);

        if(coreVector == null) return;

        Player player = e.getPlayer();

        if(packetFunctions.allBlockMatch(coreVector, block.getWorld())) {
            multiblockFunctions.createMultiblock(coreVector, player);
            return;
        }

        if(!CSMultiblock.getPreviewPacketMap().hasPlayer(player.getUniqueId()))
            return;

        Vector offsetVector = placedVector.clone().subtract(coreVector);

        boolean correctBlockType = CSMultiblock.getMultiblockMap().isMaterialMatching(coreVector, offsetVector, block.getType());

        packetFunctions.updateAllPlayersPackets(placedVector, correctBlockType, block.getType());

    }



}
