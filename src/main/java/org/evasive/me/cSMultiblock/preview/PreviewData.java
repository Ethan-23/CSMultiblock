package org.evasive.me.cSMultiblock.preview;

import org.bukkit.Material;

public class PreviewData {
    Material material;
    int id;

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
