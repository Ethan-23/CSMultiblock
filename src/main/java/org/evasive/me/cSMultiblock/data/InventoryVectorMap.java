package org.evasive.me.cSMultiblock.data;

import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryVectorMap {

    Map<UUID, Vector> playerOpenedInventoryMap = new HashMap<>();

    public void addPlayerVector(UUID uuid, Vector vector){
        playerOpenedInventoryMap.put(uuid, vector);
    }

    public void removePlayerVector(UUID uuid){
        playerOpenedInventoryMap.remove(uuid);
    }

    public Vector getVector(UUID uuid){
        return playerOpenedInventoryMap.get(uuid);
    }

    public Map<UUID, Vector> getPlayerOpenedInventoryMap(){
        return Collections.unmodifiableMap(playerOpenedInventoryMap);
    }

}
