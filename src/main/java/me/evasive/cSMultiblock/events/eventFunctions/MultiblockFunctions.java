package me.evasive.cSMultiblock.events.eventFunctions;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.CSMultiblock;
import me.evasive.cSMultiblock.data.MultiblockInfo;
import me.evasive.cSMultiblock.multiblocks.MultiblockType;
import me.evasive.cSMultiblock.multiblocks.moltenforge.inventories.MoltenForgeInventoryBuilder;
import me.evasive.cSMultiblock.preview.PreviewData;

import java.util.*;

import static me.evasive.cSMultiblock.util.MachineItemStacks.fuelSpace;
import static me.evasive.cSMultiblock.util.MachineItemStacks.outputSpace;
import static me.evasive.cSMultiblock.util.PacketOffset.packetOffset;
import static me.evasive.cSMultiblock.util.Sounds.playMoltenForgeBuild;
import static me.evasive.cSMultiblock.util.Sounds.playPickupItems;

/**
 * Functions for multiblock events
 */
public class MultiblockFunctions {

    TextDisplayFunctions textDisplayFunctions = new TextDisplayFunctions();

    public void createMultiblock(Vector coreVector, Player player){

        textDisplayFunctions.despawnIncompleteText(coreVector);

        removeAllPreviewPlayers(coreVector);

        MultiblockType multiblockType = CSMultiblock.getMultiblockMap().getMultiblockInfo(coreVector).getMultiblockType();

        Inventory storedInventory = multiblockType.equals(MultiblockType.MOLTEN_FORGE) ? new MoltenForgeInventoryBuilder().buildInventory() : null;

        CSMultiblock.getMultiblockMap().setMultiblockComplete(coreVector, true);
        CSMultiblock.getMultiblockMap().getMultiblockInfo(coreVector).setInventory(storedInventory);
        playMoltenForgeBuild(player.getWorld(), coreVector);
    }

    public void destroyMultiblock(Vector coreVector, World world, Player player){

        removeAllInventoryViewers(coreVector);

        CSMultiblock.getMultiblockMap().setMultiblockComplete(coreVector, false);
        TextDisplay textDisplay = textDisplayFunctions.spawnIncompleteText(coreVector, world);

        CSMultiblock.getMultiblockMap().getMultiblockInfo(coreVector).setTextDisplay(textDisplay);
        giveAllItems(coreVector, player);
    }

    public void destroyCore(Vector coreVector, Player player){

        removeAllInventoryViewers(coreVector);

        removeAllPreviewPlayers(coreVector);

        if(CSMultiblock.getMultiblockMap().getMultiblockInfo(coreVector).getTextDisplay() != null)
            CSMultiblock.getMultiblockMap().getMultiblockInfo(coreVector).getTextDisplay().remove();

        if(CSMultiblock.getMultiblockMap().isMultiblockComplete(coreVector))
            giveAllItems(coreVector, player);

        CSMultiblock.getMultiblockMap().removeMultiblock(coreVector);

    }

    /**
     * Removes preview packets from all players who have them active
     *
     * <p>Loops through maps of preview players and removes the players who have a specific vector active</p>
     *
     * @param coreVector Vector of the core block.
     */
    private void removeAllPreviewPlayers(Vector coreVector){

        List<Player> players = new ArrayList<>();
        for(Map.Entry<UUID, Map<Vector, PreviewData>> entry: CSMultiblock.getPreviewPacketMap().getPreviewMap().entrySet()){
            UUID uuid = entry.getKey();
            Map<Vector, PreviewData> vectorMap = entry.getValue();
            Player foundPlayer = Bukkit.getPlayer(uuid);
            if(foundPlayer == null)
                continue;
            if(!vectorMap.containsKey(packetOffset(coreVector)))
                continue;
            players.add(foundPlayer);
        }
        for (Player player : players){
            PreviewFunctions.removePreviewPacket(player);
            CSMultiblock.getPreviewPacketMap().removePlayer(player.getUniqueId());
        }

    }

    /**
     * Returns all items inside the gui to the player
     *
     * <p>When called will loop through all slots holding items of the multi block
     * and place them in the players inventory</p>
     *
     * @param coreVector Vector of the core block.
     * @param player Player who destroyed the block.
     */
    private void giveAllItems(Vector coreVector, Player player){
        MultiblockInfo multiblockInfo = CSMultiblock.getMultiblockMap().getMultiblockInfo(coreVector);
        List<Integer> input = multiblockInfo.getMultiblockType().getMultiblockBuilder().inputSlots();
        List<Integer> output = multiblockInfo.getMultiblockType().getMultiblockBuilder().outputSlots();
        List<Integer> allSlots = new ArrayList<>();
        allSlots.addAll(input);
        allSlots.addAll(output);
        allSlots.addAll(List.of(0,1,2,3,4,5,6,7,8));
        for(int i : allSlots){
            ItemStack item = multiblockInfo.getInventory().getItem(i);
            if(item == null)
                continue;
            if(item.isSimilar(fuelSpace) || item.isSimilar(outputSpace))
                continue;
            HashMap<Integer, ItemStack> leftovers = player.getInventory().addItem(item);
            if (!leftovers.isEmpty()) {
                for (ItemStack leftover : leftovers.values()) {
                    player.getWorld().dropItemNaturally(player.getLocation(), leftover);
                }
            }
        }
        playPickupItems(player);
    }

    /**
     * Removes all players who are currently viewing the GUI
     *
     * @param vector Vector of the core block.
     */
    private void removeAllInventoryViewers(Vector vector) {
        for(Map.Entry<UUID, Vector> playerInventoryLocation : CSMultiblock.getInventoryVectorMap().getPlayerOpenedInventoryMap().entrySet()){
            UUID uuid = playerInventoryLocation.getKey();
            Player player = Bukkit.getPlayer(uuid);
            if(player == null)
                continue;
            Vector inventoryVector = playerInventoryLocation.getValue();
            if(inventoryVector != vector)
                continue;
            player.closeInventory();
        }
    }

}
