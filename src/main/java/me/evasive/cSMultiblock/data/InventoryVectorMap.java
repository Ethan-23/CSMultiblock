package me.evasive.cSMultiblock.data;

import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Stores a mapping between player UUIDs and their corresponding Vector
 * location or position of multi-block inventory being opened.
 */
public final class InventoryVectorMap {

    private final Map<UUID, Vector> playerOpenedInventoryMap = new HashMap<>();

    public void addPlayerVector(UUID uuid, Vector vector){
        playerOpenedInventoryMap.put(uuid, vector);
    }

    public void removePlayerVector(UUID uuid){
        playerOpenedInventoryMap.remove(uuid);
    }

    public Vector getVector(UUID uuid){
        return playerOpenedInventoryMap.get(uuid);
    }

    /**
     * @return An unmodifiable Map of UUIDs to Vectors.
     */
    public Map<UUID, Vector> getPlayerOpenedInventoryMap(){
        return Collections.unmodifiableMap(playerOpenedInventoryMap);
    }

}
