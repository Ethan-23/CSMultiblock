package me.evasive.cSMultiblock.events.eventFunctions;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.CSMultiblock;

import static me.evasive.cSMultiblock.util.ComponentUtils.legacyComponent;

/**
 * Functions for multiblock text display
 */
public class TextDisplayFunctions {

    public TextDisplay spawnIncompleteText(Vector vector, World world){
        TextDisplay textDisplay = (TextDisplay) world.spawnEntity(new Location(world, vector.getX() + 0.5, vector.getY() + 1.3f, vector.getZ() + 0.5), EntityType.TEXT_DISPLAY);
        textDisplay.text(legacyComponent("&c&lIncomplete Multiblock\n&7(Shift Right Click)"));
        textDisplay.setBillboard(Display.Billboard.CENTER);
        textDisplay.setSeeThrough(true);
        return textDisplay;
    }

    public void despawnIncompleteText(Vector vector){
        CSMultiblock.getMultiblockMap().getMultiblockInfo(vector).getTextDisplay().remove();
    }

}
