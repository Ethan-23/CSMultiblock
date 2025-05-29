package me.evasive.cSMultiblock.data;

import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores a mapping between Vector and the MultiblockInfo at the world location
 */
public class MultiblockMap {

    Map<Vector, MultiblockInfo> multiblockInfoMap = new HashMap<>();

    public void addMultiblock(Vector vector, MultiblockInfo multiblockInfo){
        multiblockInfoMap.put(vector, multiblockInfo);
    }

    public boolean hasMultiblock(Vector vector){
        return multiblockInfoMap.containsKey(vector);
    }

    public void removeMultiblock(Vector vector){
        multiblockInfoMap.remove(vector);
    }

    public MultiblockInfo getMultiblockInfo(Vector vector){
        return multiblockInfoMap.get(vector);
    }

    public void setMultiblockComplete(Vector vector, boolean complete){
        multiblockInfoMap.get(vector).setCompleteBuild(complete);
    }

    public boolean isMultiblockComplete(Vector vector){
        return multiblockInfoMap.get(vector).isCompleteBuild();
    }

    /**
     * Loops map for a multiblock with this connected vector
     * @return Connected core to the multiblock structure, or null if none found
     */
    public Vector getConnectedCore(Vector placeVector){
        for (Map.Entry<Vector, MultiblockInfo> entry : multiblockInfoMap.entrySet()) {
            Vector coreVector = entry.getKey();
            MultiblockInfo multiblockInfo = entry.getValue();
            Vector vector = placeVector.clone().subtract(coreVector);
            if(multiblockInfo.getMultiblockType().getMultiblockBuilder().materialMap().containsKey(vector))
                return coreVector;
        }
        return null;
    }

    public boolean isMaterialMatching(Vector vector, Vector placementVector, Material material){
        return multiblockInfoMap.get(vector).getMultiblockType().getMultiblockBuilder().materialMap().get(placementVector).equals(material);
    }

    /**
     * @return An unmodifiable Map of Vector to MultiblockInfo.
     */
    public Map<Vector, MultiblockInfo> getMultiblockInfoMap(){
        return Collections.unmodifiableMap(multiblockInfoMap);
    }

}
