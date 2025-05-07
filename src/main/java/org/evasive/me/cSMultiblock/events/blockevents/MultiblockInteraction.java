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

public class MultiblockInteraction implements Listener {

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
