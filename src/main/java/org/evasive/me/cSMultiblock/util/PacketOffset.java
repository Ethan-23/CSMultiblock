package org.evasive.me.cSMultiblock.util;

import org.bukkit.util.Vector;

public class PacketOffset {


    public static Vector packetOffset(Vector vector){
        return vector.clone().add(new Vector(0.5, 0.5, 0.5));
    }

}
