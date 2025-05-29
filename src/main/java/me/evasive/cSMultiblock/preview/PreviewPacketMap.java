package me.evasive.cSMultiblock.preview;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.*;

/**
 * Preview packets sent to specific player
 */
public class PreviewPacketMap {

    Map<UUID, Map<Vector, PreviewData>> previewMap = new HashMap<>();

    public void addPlayer(UUID uuid){
        previewMap.put(uuid, new HashMap<>());
    }

    public boolean hasPlayer(UUID uuid){
        return previewMap.containsKey(uuid);
    }

    public boolean containsVector(UUID uuid, Vector vector){
        if(!previewMap.containsKey(uuid))
            return false;
        return previewMap.get(uuid).containsKey(vector);
    }

    public void setPreviewData(UUID uuid, Vector vector, PreviewData previewData){
        if(!previewMap.containsKey(uuid))
            addPlayer(uuid);
        Map<Vector, PreviewData> tempMap = previewMap.get(uuid);
        tempMap.put(vector, previewData);
        previewMap.put(uuid, tempMap);
    }

    public PreviewData getPreviewData(UUID uuid, Vector vector){
        return previewMap.get(uuid).get(vector);
    }

    public void removePlayer(UUID uuid){
        previewMap.remove(uuid);
    }

    public List<Integer> getPacketList(Player player){
        List<Integer> activePackets = new ArrayList<>();
        for (Map.Entry<Vector, PreviewData> entry : previewMap.get(player.getUniqueId()).entrySet()) {
            activePackets.add(entry.getValue().id);
        }
        return activePackets;
    }

    public Map<UUID, Map<Vector, PreviewData>> getPreviewMap(){
        return Collections.unmodifiableMap(previewMap);
    }

}
