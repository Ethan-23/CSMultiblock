package me.evasive.cSMultiblock.preview;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.entity.data.EntityData;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.protocol.world.Location;
import com.github.retrooper.packetevents.protocol.world.states.type.StateTypes;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.util.Vector3f;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerDestroyEntities;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityMetadata;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;
import me.evasive.cSMultiblock.CSMultiblock;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.UUID;

/**
 * Functions for packet sending and destroying
 */
public class PreviewPackets {

    //Sending Packets

    public int sendBlockDisplayPacket(Player player, Location location, Material material) {

        int entityID = CSMultiblock.getPreviewIds().getUniqueAnimationId();

        addToMap(player, location, material, entityID);

        blockDisplayPacket(player, entityID, location);
        appearAnimation(player, entityID, material);
        return entityID;
    }

    public void sendDestroyBlockDisplayPacket(Player player, int entityID){

        destroyBlockPacket(entityID);

        PacketEvents.getAPI().getPlayerManager().sendPacket(player, destroyBlockPacket(entityID));
    }

    private void blockDisplayPacket(Player player, int entityID, Location location){
        WrapperPlayServerSpawnEntity wrapperPlayServerSpawnEntity = new WrapperPlayServerSpawnEntity(
                entityID,
                UUID.randomUUID(),
                EntityTypes.BLOCK_DISPLAY,
                location,
                0f,
                0,
                new Vector3d(0, 0, 0)
        );

        PacketEvents.getAPI().getPlayerManager().sendPacket(player, wrapperPlayServerSpawnEntity);
    }

    public void sendIncorrectBlock(Player player, int entityID){
        sendEntityMetaDataPacket(player, entityID, getIncorrectData());
    }

    public void sendNothingBlock(Player player, int entityID){
        sendEntityMetaDataPacket(player, entityID, getMatchingData());
    }

    public void sendCompleteBlock(Player player, int entityID){
        sendEntityMetaDataPacket(player, entityID, getResetData());
    }

    public void sendEntityMetaDataPacket(Player player, int entityID, List<EntityData> entityDataList){
        WrapperPlayServerEntityMetadata wrapperPlayServerEntityMetadata = new WrapperPlayServerEntityMetadata(
                entityID,
                entityDataList
        );
        PacketEvents.getAPI().getPlayerManager().sendPacket(player, wrapperPlayServerEntityMetadata);
    }


    //Handle map data

    private void addToMap(Player player, Location location, Material material, int entityID) {
        Vector vector = new Vector(location.getX(), location.getY(), location.getZ());
        PreviewData previewData = new PreviewData(material, entityID);
        CSMultiblock.getPreviewPacketMap().setPreviewData(player.getUniqueId(), vector, previewData);
    }

    //Wrappers

    private WrapperPlayServerDestroyEntities destroyBlockPacket(int entityID){
        return new WrapperPlayServerDestroyEntities(
                entityID
        );
    }



    // Different Meta Data Presets

    public List<EntityData> getIncorrectData(){
        return List.of(
                new EntityData(0, EntityDataTypes.BYTE, (byte) 0x40),
                new EntityData(22, EntityDataTypes.INT, 11546150) //RED
        );
    }

    public List<EntityData> getMatchingData(){
        return List.of(
                new EntityData(0, EntityDataTypes.BYTE, (byte) 0x40),
                new EntityData(22, EntityDataTypes.INT, 16383998) //White
        );
    }

    public List<EntityData> getResetData(){
        return List.of(
                new EntityData(0, EntityDataTypes.BYTE, (byte) 0x00),
                new EntityData(22, EntityDataTypes.INT, -1)
        );
    }

    public List<EntityData> getDefaultData(Material material){
        return List.of(
                new EntityData(23, EntityDataTypes.BLOCK_STATE, StateTypes.getByName(material.getKey().toString()).createBlockState().getGlobalId()),
                new EntityData(12, EntityDataTypes.VECTOR3F, new Vector3f(0.01f, 0.01f, 0.01f))
        );
    }

    public List<EntityData> getGrowData(float size){
        return List.of(
                new EntityData(9, EntityDataTypes.INT, 1),
                new EntityData(12, EntityDataTypes.VECTOR3F, new Vector3f(size, size, size)),
                new EntityData(11, EntityDataTypes.VECTOR3F, new Vector3f(-size/2, -size/2, -size/2))
        );
    }

    //Animations

    public void appearAnimation(Player player, int entityID, Material material){
        sendEntityMetaDataPacket(player, entityID, getDefaultData(material));
        sendEntityMetaDataPacket(player, entityID, getMatchingData());
        for (int i = 1; i <= 25; i++) {
            final int tick = i;
            Bukkit.getScheduler().runTaskLater(CSMultiblock.getCore(), () -> {
                float scale = tick / 50f;
                sendEntityMetaDataPacket(player, entityID, getGrowData(scale));
            }, i);
        }

    }

}
