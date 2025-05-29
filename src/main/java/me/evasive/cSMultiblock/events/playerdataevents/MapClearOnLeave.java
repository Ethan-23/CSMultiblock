package me.evasive.cSMultiblock.events.playerdataevents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import me.evasive.cSMultiblock.CSMultiblock;

/**
 * Handles Clearing useless player data on leave
 */
public class MapClearOnLeave implements Listener {

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent e){
        Player player = e.getPlayer();
        CSMultiblock.getPreviewPacketMap().removePlayer(player.getUniqueId());
        CSMultiblock.getInventoryVectorMap().removePlayerVector(player.getUniqueId());
        CSMultiblock.getPreviewCooldownMap().removePlayer(player.getUniqueId());
    }

}
