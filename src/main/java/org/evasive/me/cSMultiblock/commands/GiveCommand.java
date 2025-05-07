package org.evasive.me.cSMultiblock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.evasive.me.cSMultiblock.CSMultiblock;
import org.evasive.me.cSMultiblock.customItems.ItemList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class GiveCommand implements CommandExecutor {

    public GiveCommand(){
        CSMultiblock.getCore().getCommand("admingive").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        List<String> itemList = Arrays.stream(ItemList.values()).map(Enum::name).toList();
        if(!(commandSender instanceof Player player)) return true;
        else if(emptyCommand(player, strings, itemList)) return true;
        else if(giveItem(player, strings, itemList))return true;
        return true;
    }

    public boolean emptyCommand(Player player, @NotNull String @NotNull [] strings, List<String> itemList){
        if (strings.length > 0) return false;
        player.sendMessage(String.format("%s", itemList));
        return true;
    }

    public boolean giveItem(Player player, @NotNull String @NotNull [] strings, List<String> itemList){
        if(strings.length != 1) return false;
        String itemName = strings[0].toUpperCase();
        if(!itemList.contains(itemName)){
            player.sendMessage("Item Not Found");
            return false;
        }
        player.getInventory().addItem(ItemList.valueOf(itemName).getItemBuilder().getItem());
        return true;
    }
}
