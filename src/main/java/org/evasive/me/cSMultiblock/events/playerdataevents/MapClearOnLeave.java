package org.evasive.me.cSMultiblock.events.playerdataevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.evasive.me.cSMultiblock.CSMultiblock;

public class MapClearOnLeave implements Listener {

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent e){
        Player player = e.getPlayer();
        CSMultiblock.getPreviewPacketMap().removePlayer(player.getUniqueId());
        CSMultiblock.getInventoryVectorMap().removePlayerVector(player.getUniqueId());
        CSMultiblock.getPreviewCooldownMap().removePlayer(player.getUniqueId());
    }

}
