package me.evasive.cSMultiblock.events.blockevents;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.CSMultiblock;
import me.evasive.cSMultiblock.events.eventFunctions.MultiblockFunctions;
import me.evasive.cSMultiblock.events.eventFunctions.PacketFunctions;

/**
 * Handles interact events with the multiblock components.
 */
public class MultiblockPlace implements Listener {

    MultiblockFunctions multiblockFunctions = new MultiblockFunctions();
    PacketFunctions packetFunctions = new PacketFunctions();

    /**
     * Handles multilblock component place event.
     *
     * <p>Triggers when the player places a multiblock component in a valid multiblock location</p>
     *
     * @param e The interact event triggered by a player placing a block.
     */
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
