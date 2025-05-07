package org.evasive.me.cSMultiblock.events.blockevents;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.events.eventFunctions.MultiblockFunctions;
import org.evasive.me.cSMultiblock.events.eventFunctions.PacketFunctions;

import static org.evasive.me.cSMultiblock.util.PacketOffset.packetOffset;

public class MultiblockBreak implements Listener {

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

    public void handleMultiblockCoreBreak(Player player, Vector coreVector, BlockBreakEvent event){

        event.setDropItems(false);

        event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), CSMultiblock.getMultiblockMap().getMultiblockInfo(coreVector).getMultiblockType().getMultiblockBuilder().coreItem());

        new MultiblockFunctions().destroyCore(coreVector, player);
    }

}
