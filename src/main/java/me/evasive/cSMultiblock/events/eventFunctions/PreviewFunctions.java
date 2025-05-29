package me.evasive.cSMultiblock.events.eventFunctions;

import com.github.retrooper.packetevents.protocol.world.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.CSMultiblock;
import me.evasive.cSMultiblock.preview.PreviewPackets;

import java.util.Map;

import static me.evasive.cSMultiblock.util.PacketOffset.packetOffset;

/**
 * Functions for multiblock Preview
 */
public class PreviewFunctions {

    public static void removePreviewPacket(Player player){
        for (int entityID : CSMultiblock.getPreviewPacketMap().getPacketList(player)){
            new PreviewPackets().sendDestroyBlockDisplayPacket(player, entityID);
            CSMultiblock.getPreviewIds().releaseAnimationId(entityID);
        }
        CSMultiblock.getPreviewPacketMap().removePlayer(player.getUniqueId());
    }

    public static void sendPreviewPackets(Player player, Vector coreVector, Map<Vector, Material> materialMap) {

        for (Map.Entry<Vector, Material> entry : materialMap.entrySet()) {
            Vector vector = packetOffset(entry.getKey().clone().add(coreVector));
            Material expectedMaterial = entry.getValue();
            Location location = new Location(vector.getX(), vector.getY(), vector.getZ(), 0, 0);
            int id = new PreviewPackets().sendBlockDisplayPacket(player, location, expectedMaterial);
            compareMaterials(player, vector, expectedMaterial, id);
        }

    }

    public static void compareMaterials(Player player, Vector vector, Material expected, int entityID){
        Material placed = player.getWorld().getBlockAt(new org.bukkit.Location(player.getWorld(), vector.getX(), vector.getY(), vector.getZ())).getType();
        if(placed.equals(Material.AIR))
            return;
        if(!placed.equals(expected))
            new PreviewPackets().sendIncorrectBlock(player, entityID);
        else
            new PreviewPackets().sendCompleteBlock(player,entityID);
    }


}
