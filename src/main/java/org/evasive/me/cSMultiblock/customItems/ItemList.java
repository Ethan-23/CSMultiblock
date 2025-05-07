package org.evasive.me.cSMultiblock.customItems;

import org.evasive.me.cSMultiblock.customItems.items.GolderiteBlock;
import org.evasive.me.cSMultiblock.customItems.items.Steel;
import org.evasive.me.cSMultiblock.customItems.multiblockcores.CropShredderCore;
import org.evasive.me.cSMultiblock.customItems.multiblockcores.MoltenForgeCore;
import org.evasive.me.cSMultiblock.customItems.items.HardenedIron;

public enum ItemList {
    MOLTEN_FORGE_CORE(new MoltenForgeCore()),
    CROP_SHREDDER_CORE(new CropShredderCore()),
    HARDENED_IRON(new HardenedIron()),
    STEEL(new Steel()),
    GOLDERITE_BLOCK(new GolderiteBlock());

    private final ItemBuilder itemBuilder;

    ItemList(ItemBuilder itemBuilder){this.itemBuilder = itemBuilder;}

    public ItemBuilder getItemBuilder(){
        return itemBuilder;
    }
}
