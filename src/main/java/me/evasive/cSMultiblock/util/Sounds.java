package me.evasive.cSMultiblock.util;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Specific sounds sent for machines
 */
public class Sounds {

    public static void playSoundAtVector(World world, Vector vector, Sound sound){
        Location location = new Location(world, vector.getX(), vector.getY(), vector.getZ());
        world.playSound(location, sound, 1f,1f);
    }

    public static void playMoltenForgeBuild(World world, Vector vector){
        playSoundAtVector(world, vector, Sound.BLOCK_ANVIL_PLACE);
    }

    public static void playMoltenForgeStart(World world, Vector vector){
        playSoundAtVector(world, vector, Sound.ITEM_FIRECHARGE_USE);
    }

    public static void playPickupItems(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1f,1f);
    }

}
