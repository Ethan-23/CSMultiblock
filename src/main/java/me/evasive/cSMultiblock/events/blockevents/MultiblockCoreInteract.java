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
 * Handles interact events with the multiblock core.
 */
public class MultiblockCoreInteract implements Listener {

    /**
     * Handles core interact event.
     *
     * <p>Triggers when the player right-clicks with their main hand while sneaking
     * on a block that is part of a multiblock structure.
     * If these conditions are met, toggles the packet display for the player at the block's location.</p>
     *
     * @param e The interact event triggered by a player interacting with a block.
     */
    @EventHandler(ignoreCancelled = true)
    public void rightClickEvent(PlayerInteractEvent e){
        if(!correctAction(e.getAction(), e.getHand(), e.getPlayer())) return;

        Vector vector = e.getClickedBlock().getLocation().toVector();
        if(!CSMultiblock.getMultiblockMap().hasMultiblock(vector)) return;

        new PacketFunctions().packetToggle(e.getPlayer(), vector);
    }

    public boolean correctAction(Action action, EquipmentSlot equipmentSlot, Player player){
        return action == action.RIGHT_CLICK_BLOCK && equipmentSlot == EquipmentSlot.HAND && player.isSneaking();
    }

}
