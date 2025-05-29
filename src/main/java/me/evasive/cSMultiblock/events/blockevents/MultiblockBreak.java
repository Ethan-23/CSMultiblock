package me.evasive.cSMultiblock.events.blockevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.CSMultiblock;
import me.evasive.cSMultiblock.events.eventFunctions.MultiblockFunctions;
import me.evasive.cSMultiblock.events.eventFunctions.PacketFunctions;

import static me.evasive.cSMultiblock.util.PacketOffset.packetOffset;

/**
 * Handles breaking events for multiblocks.
 */
public class MultiblockBreak implements Listener {

    /**
     * Handles block break events.
     *
     * <p>Checks if the broken block is part of a multiblock structure and
     * processes the break accordingly, including destroying multiblocks,
     * updating preview packets, and managing drops.</p>
     *
     * @param e The block break event triggered by a player breaking a block.
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        Vector breakVector = e.getBlock().getLocation().toVector();

        if(CSMultiblock.getMultiblockMap().hasMultiblock(breakVector)){
            handleMultiblockCoreBreak(player, breakVector, e);
            return;
        }

        Vector coreVector = CSMultiblock.getMultiblockMap().getConnectedCore(breakVector);

        if(coreVector == null) return;

        if(CSMultiblock.getMultiblockMap().isMultiblockComplete(coreVector)){
            new MultiblockFunctions().destroyMultiblock(coreVector, e.getBlock().getWorld(), player);
        }


        if(!CSMultiblock.getPreviewPacketMap().hasPlayer(player.getUniqueId()))
            return;

        if(!CSMultiblock.getPreviewPacketMap().containsVector(player.getUniqueId(), packetOffset(breakVector)))
            return;

        new PacketFunctions().updateAllPlayersPackets(breakVector, true, Material.AIR);
    }

    /**
     * Handles special logic when the core block of a multiblock is broken.
     *
     * <p>Cancels normal drops, drops the core item manually,
     * and triggers destruction of the multiblock core.</p>
     *
     * @param player The player who broke the core block.
     * @param coreVector The vector location of the core block.
     * @param event The original block break event.
     */
    public void handleMultiblockCoreBreak(Player player, Vector coreVector, BlockBreakEvent event){

        event.setDropItems(false);

        event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), CSMultiblock.getMultiblockMap().getMultiblockInfo(coreVector).getMultiblockType().getMultiblockBuilder().coreItem());

        new MultiblockFunctions().destroyCore(coreVector, player);
    }

}
