package me.evasive.cSMultiblock.config;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import me.evasive.cSMultiblock.CSMultiblock;
import me.evasive.cSMultiblock.data.MultiblockInfo;
import me.evasive.cSMultiblock.events.eventFunctions.TextDisplayFunctions;
import me.evasive.cSMultiblock.multiblocks.MultiblockType;
import me.evasive.cSMultiblock.multiblocks.moltenforge.inventories.MoltenForgeInventoryBuilder;
import me.evasive.cSMultiblock.multiblocks.moltenforge.recipes.MoltenForgeRecipes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SavingDataConfig {

    File file = new File(CSMultiblock.getCore().getDataFolder(), "multiblockSaveData.yml");

    public YamlConfiguration createSaveFile() throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public void saveDataToFile() throws IOException {
        YamlConfiguration config = createSaveFile();

        config.set("multiblocks", null);

        for (Map.Entry<Vector, MultiblockInfo> entry : CSMultiblock.getMultiblockMap().getMultiblockInfoMap().entrySet()) {
            Vector vec = entry.getKey();
            MultiblockInfo info = entry.getValue();

            String path = "multiblocks." + vec.getBlockX() + "_" + vec.getBlockY() + "_" + vec.getBlockZ();
            config.set(path + ".type", info.getMultiblockType().name());
            config.set(path + ".progress", info.getProgress());
            config.set(path + ".completeBuild", info.isCompleteBuild());
            config.set(path + ".incompleteItem", info.getProcessingItemStack());
            config.set(path + ".world", info.getWorld().getName());

            if(info.getInventory() != null)
                config.set(path + ".inventory", info.getInventory().getContents());
            if(info.getForgeSelectedRecipe() != null)
                config.set(path + ".selectedRecipe", info.getForgeSelectedRecipe().name());
            if(info.getProcessingRecipe() != null)
                config.set(path + ".processingRecipe", info.getForgeSelectedRecipe().name());

            if(!CSMultiblock.getMultiblockMap().isMultiblockComplete(vec))
                CSMultiblock.getMultiblockMap().getMultiblockInfo(vec).getTextDisplay().remove();
        }
        config.save(file);
    }

    public void loadDataFromFile(){
        if(!file.exists())
            return;
        File file = new File(CSMultiblock.getCore().getDataFolder(), "multiblockSaveData.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection section = config.getConfigurationSection("multiblocks");
        if (section != null) {
            //Looping through all saved multiblocks to grab data
            for (String key : section.getKeys(false)) {
                String[] parts = key.split("_");
                Vector vec = new Vector(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                MultiblockType type = MultiblockType.valueOf(config.getString("multiblocks." + key + ".type"));
                int progress = config.getInt("multiblocks." + key + ".progress");
                boolean completeBuild = config.getBoolean("multiblocks." + key + ".completeBuild");
                World world = Bukkit.getWorld(config.getString("multiblocks." + key + ".world"));

                //HAVE TO CHANGE TO A CENTRALIZED RECIPE LIST TO PULL FROM ONCE OTHER MULTIBLOCKS GET ADDED
                //Pulling Recipe Data
                MoltenForgeRecipes selectedRecipe = null;
                MoltenForgeRecipes processingRecipe = null;

                if(config.getString("multiblocks." + key + ".selectedRecipe") != null)
                    selectedRecipe = MoltenForgeRecipes.valueOf(config.getString("multiblocks." + key + ".selectedRecipe"));
                if(config.getString("multiblocks." + key + ".processingRecipe") != null)
                    processingRecipe = MoltenForgeRecipes.valueOf(config.getString("multiblocks." + key + ".processingRecipe"));

                ItemStack incompleteItem = config.getItemStack("multiblocks." + key + ".incompleteItem");

                //Pulling inventory
                ItemStack[] inventoryContents;
                Inventory inventory = new MoltenForgeInventoryBuilder().buildInventory();
                if(config.get("multiblocks." + key + ".inventory") != null){
                    List<ItemStack> itemList = (List<ItemStack>) config.get("multiblocks." + key + ".inventory");
                    inventoryContents = itemList.toArray(new ItemStack[0]);
                    inventory.setContents(inventoryContents);
                }

                MultiblockInfo info = new MultiblockInfo(type, world, selectedRecipe, processingRecipe, incompleteItem, inventory, progress, completeBuild);
                CSMultiblock.getMultiblockMap().addMultiblock(vec, info);

                // Recreate TextDisplay if needed
                if (!CSMultiblock.getMultiblockMap().isMultiblockComplete(vec)) {
                    TextDisplay textDisplay = new TextDisplayFunctions().spawnIncompleteText(vec, world);
                    CSMultiblock.getMultiblockMap().getMultiblockInfo(vec).setTextDisplay(textDisplay);
                }
            }

        }
    }

}
