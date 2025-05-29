package me.evasive.cSMultiblock.events.blockevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.CSMultiblock;
import me.evasive.cSMultiblock.events.eventFunctions.PacketFunctions;

/**
 * Handles interact events with multiblocks.
 */
public class MultiblockInteraction implements Listener {

    /**
     * Handles multiblock part interact event.
     *
     * <p>Triggers when the player right-clicks with their main hand while sneaking
     * on a block that is part of a multiblock structure.
     * If these conditions are met, toggles the packet display for the player at the multiblock core's location.</p>
     *
     * @param e The interact event triggered by a player interacting with a block.
     */
    @EventHandler
    public void onMultiblockInteraction(PlayerInteractEvent e){
        if(!rightClick(e.getAction(), e.getHand())) return;

        if(e.getClickedBlock() == null) return;

        Vector interactVector = e.getClickedBlock().getLocation().toVector();

        Vector coreVector = CSMultiblock.getMultiblockMap().getConnectedCore(interactVector);

        if(coreVector == null) return;

        if (!CSMultiblock.getMultiblockMap().isMultiblockComplete(coreVector)) {
            //If the block is not complete and player shift right clicks then display blocks
            if(!e.getPlayer().isSneaking())
                return;
            new PacketFunctions().packetToggle(e.getPlayer(), coreVector);
            return;
        }

        Player player = e.getPlayer();

        if(player.isSneaking())
            return;

        e.setCancelled(true);



        player.openInventory(CSMultiblock.getMultiblockMap().getMultiblockInfo(coreVector).getInventory());
        CSMultiblock.getInventoryVectorMap().addPlayerVector(player.getUniqueId(), coreVector);

    }

    public boolean rightClick(Action action, EquipmentSlot equipmentSlot){
        return action == action.RIGHT_CLICK_BLOCK && equipmentSlot == EquipmentSlot.HAND;
    }


}
