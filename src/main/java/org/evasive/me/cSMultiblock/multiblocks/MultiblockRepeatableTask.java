package org.evasive.me.cSMultiblock.multiblocks;

import org.bukkit.Bukkit;
import org.bukkit.util.Vector;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.data.MultiblockInfo;
import org.evasive.me.cSMultiblock.multiblocks.moltenforge.task.MoltenForgeTask;

import java.util.Map;

public class MultiblockRepeatableTask {

    public void start(){
        Bukkit.getScheduler().runTaskTimer(CSMultiblock.getCore(), () -> {

            //Loads all multiblocks to run every second
            for(Map.Entry<Vector, MultiblockInfo> entry : CSMultiblock.getMultiblockMap().getMultiblockInfoMap().entrySet()){
                Vector coreVector = entry.getKey();
                MultiblockInfo multiblockInfo = entry.getValue();

                if(multiblockInfo.getMultiblockType().equals(MultiblockType.MOLTEN_FORGE))
                    new MoltenForgeTask().activateForgeTick(coreVector);
            }

        }, 0L, 20L).getTaskId();
    }

}
