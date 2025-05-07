package org.evasive.me.cSMultiblock.events.blockevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.events.eventFunctions.PacketFunctions;

public class MultiblockCoreInteract implements Listener {

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
