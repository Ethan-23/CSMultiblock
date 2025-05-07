package org.evasive.me.cSMultiblock.events.eventFunctions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.preview.PreviewData;
import org.evasive.me.cSMultiblock.preview.PreviewPackets;

import java.util.Map;
import java.util.UUID;

import static org.evasive.me.cSMultiblock.events.eventFunctions.PreviewFunctions.removePreviewPacket;
import static org.evasive.me.cSMultiblock.events.eventFunctions.PreviewFunctions.sendPreviewPackets;
import static org.evasive.me.cSMultiblock.util.PacketOffset.packetOffset;

public class PacketFunctions {

    public void updateAllPlayersPackets(Vector editedVector, boolean correctBlockType, Material blockType){
        for(Map.Entry<UUID, Map<Vector, PreviewData>> entry: CSMultiblock.getPreviewPacketMap().getPreviewMap().entrySet()) {
            UUID uuid = entry.getKey();
            Map<Vector, PreviewData> vectorMap = entry.getValue();
            Player foundPlayer = Bukkit.getPlayer(uuid);
            if (foundPlayer == null)
                continue;
            if (!vectorMap.containsKey(packetOffset(editedVector)))
                continue;
            if (blockType == Material.AIR){
                sendNothingPacket(foundPlayer, editedVector);
            }else if (correctBlockType) {
                sendCorrectPacket(foundPlayer, editedVector);
            }else {
                sendIncorrectPacket(foundPlayer, editedVector);
            }
        }
    }


    private void sendIncorrectPacket(Player player, Vector placedVector){
        PreviewData previewData = CSMultiblock.getPreviewPacketMap().getPreviewData(player.getUniqueId(), packetOffset(placedVector));
        new PreviewPackets().sendIncorrectBlock(player, previewData.getId());
    }

    private void sendCorrectPacket(Player player, Vector placedVector){
        PreviewData previewData = CSMultiblock.getPreviewPacketMap().getPreviewData(player.getUniqueId(), packetOffset(placedVector));
        new PreviewPackets().sendCompleteBlock(player, previewData.getId());
    }

    private void sendNothingPacket(Player player, Vector brokenVector){
        PreviewData previewData = CSMultiblock.getPreviewPacketMap().getPreviewData(player.getUniqueId(), packetOffset(brokenVector));
        new PreviewPackets().sendNothingBlock(player, previewData.getId());
    }

    public void packetToggle(Player player, Vector vector) {
        if (!CSMultiblock.getPreviewCooldownMap().canActivate(player.getUniqueId()))
            return;
        if (!CSMultiblock.getPreviewPacketMap().hasPlayer(player.getUniqueId())) {
            sendPreviewPackets(player, vector, getMaterialMap(vector));
        } else if (!CSMultiblock.getPreviewPacketMap().containsVector(player.getUniqueId(), packetOffset(vector))) {
            removePreviewPacket(player);
            sendPreviewPackets(player, vector, getMaterialMap(vector));
        } else {
            removePreviewPacket(player);
        }
    }

    public Map<Vector, Material> getMaterialMap(Vector vector){
        return CSMultiblock.getMultiblockMap().getMultiblockInfo(vector).getMultiblockType().getMultiblockBuilder().materialMap();
    }

    public boolean allBlockMatch(Vector coreVector, World world){
        Map<Vector, Material> materialMap = CSMultiblock.getMultiblockMap().getMultiblockInfoMap().get(coreVector).getMultiblockType().getMultiblockBuilder().materialMap();
        for (Map.Entry<Vector, Material> entry : materialMap.entrySet()) {
            Vector offsetVector = entry.getKey();
            Material blockMaterial = entry.getValue();
            Vector location = coreVector.clone().add(offsetVector);
            if(!world.getBlockAt(new Location(world, location.getX(), location.getY(), location.getZ())).getType().equals(blockMaterial))
                return false;
        }
        return true;
    }



}
