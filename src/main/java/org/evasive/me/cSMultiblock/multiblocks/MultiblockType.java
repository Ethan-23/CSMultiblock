package org.evasive.me.cSMultiblock.multiblocks;

import org.evasive.me.cSMultiblock.multiblocks.cropshredder.CropShredder;
import org.evasive.me.cSMultiblock.multiblocks.moltenforge.MoltenForge;

public enum MultiblockType {
    MOLTEN_FORGE(new MoltenForge()),
    CROP_SHREDDER(new CropShredder());


    private final MultiblockBuilder multiblockBuilder;

    MultiblockType(MultiblockBuilder itemBuilder){this.multiblockBuilder = itemBuilder;}

    public MultiblockBuilder getMultiblockBuilder(){
        return multiblockBuilder;
    }
}
