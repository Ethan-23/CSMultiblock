package me.evasive.cSMultiblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.evasive.cSMultiblock.customItems.ItemList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Handles the /admingive command which allows players to receive custom items from the plugin.
 * <p>
 * If no arguments are provided, the command lists all available items.
 * If a valid item name is provided, the player receives that item in their inventory.
 * Only players can execute this command.
 * </p>
 */
public class GiveCommand implements CommandExecutor {

    /** Cached list of valid item names from the ItemList enum. */
    private final List<String> itemList = Arrays.stream(ItemList.values()).map(Enum::name).toList();

    /**
     * Executes the /admingive command.
     *
     * @param commandSender the sender of the command (must be a Player)
     * @param command the command object
     * @param s the command label
     * @param strings command arguments
     * @return true if the command was handled, false to show usage
     */
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(commandSender instanceof Player player))
            return true;

        if(strings.length == 0){
            player.sendMessage(String.format("%s", itemList));
            return true;
        }

        if(strings.length != 1)
            return true;

        giveItem(player, strings[0]);

        return true;
    }

    /**
     * Gives the specified item to the player if it exists in the item list.
     * Sends an error message to the player if the item is not found.
     *
     * @param player the player to receive the item
     * @param itemName the item name the player input into the command
     */
    private void giveItem(Player player, String itemName){

        itemName = itemName.toUpperCase();

        if(!itemList.contains(itemName)){
            player.sendMessage("Item Not Found");
            return;
        }

        player.getInventory().addItem(ItemList.valueOf(itemName).getItemBuilder().getItem());
    }
}
