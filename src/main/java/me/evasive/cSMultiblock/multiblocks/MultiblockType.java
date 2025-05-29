package me.evasive.cSMultiblock.multiblocks;

import me.evasive.cSMultiblock.multiblocks.cropshredder.CropShredder;
import me.evasive.cSMultiblock.multiblocks.moltenforge.MoltenForge;

public enum MultiblockType {
    MOLTEN_FORGE(new MoltenForge()),
    CROP_SHREDDER(new CropShredder());


    private final MultiblockBuilder multiblockBuilder;

    MultiblockType(MultiblockBuilder itemBuilder){this.multiblockBuilder = itemBuilder;}

    public MultiblockBuilder getMultiblockBuilder(){
        return multiblockBuilder;
    }
}
