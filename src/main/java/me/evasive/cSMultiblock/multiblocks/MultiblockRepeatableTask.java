package me.evasive.cSMultiblock.multiblocks;

import org.bukkit.Bukkit;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.CSMultiblock;
import me.evasive.cSMultiblock.data.MultiblockInfo;
import me.evasive.cSMultiblock.multiblocks.moltenforge.task.MoltenForgeTask;

import java.util.Map;

/**
 * Scheduler for multiblock tick
 */
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
