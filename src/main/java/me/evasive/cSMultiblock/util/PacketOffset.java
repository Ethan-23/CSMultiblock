package me.evasive.cSMultiblock.util;

import org.bukkit.util.Vector;


/**
 * Specific offset for packets being sent to keep centered
 */
public class PacketOffset {


    public static Vector packetOffset(Vector vector){
        return vector.clone().add(new Vector(0.5, 0.5, 0.5));
    }

}
