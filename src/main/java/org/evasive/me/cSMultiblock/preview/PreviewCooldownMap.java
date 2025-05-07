package org.evasive.me.cSMultiblock.preview;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PreviewCooldownMap {

    Map<UUID, Long> previewCooldownMap = new HashMap<>();

    public void addCooldown(UUID uuid){
        long currentTime = System.currentTimeMillis();
        previewCooldownMap.put(uuid, currentTime);
    }

    public boolean canActivate(UUID uuid){
        if(previewCooldownMap.containsKey(uuid)){
            long currentTime = System.currentTimeMillis();
            long lastUsed = previewCooldownMap.get(uuid);

            if((currentTime - lastUsed) < 500)
                return false;
        }
        addCooldown(uuid);
        return true;
    }

    public void removePlayer(UUID uuid){
        previewCooldownMap.remove(uuid);
    }

}
