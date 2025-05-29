package me.evasive.cSMultiblock.preview;

import org.bukkit.Material;

/**
 * Object for storing data of each preview material and packet id
 */
public class PreviewData {
    Material material;
    int id;

    /**
     * Stores data for a specific packet
     * @param material material for packet.
     * @param id entityID for packet
     */
    public PreviewData(Material material, int id) {
        this.material = material;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

}
