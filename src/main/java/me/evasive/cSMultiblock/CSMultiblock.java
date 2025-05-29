package me.evasive.cSMultiblock;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.evasive.cSMultiblock.commands.GiveCommand;
import me.evasive.cSMultiblock.config.SavingDataConfig;
import me.evasive.cSMultiblock.customItems.ItemMaker;
import me.evasive.cSMultiblock.data.InventoryVectorMap;
import me.evasive.cSMultiblock.data.MultiblockMap;
import me.evasive.cSMultiblock.events.blockevents.*;
import me.evasive.cSMultiblock.events.inventoryevents.ForgeSelectorGuiEvents;
import me.evasive.cSMultiblock.events.inventoryevents.MoltenForgeGuiEvents;
import me.evasive.cSMultiblock.events.playerdataevents.MapClearOnLeave;
import me.evasive.cSMultiblock.multiblocks.MultiblockRepeatableTask;
import me.evasive.cSMultiblock.preview.PreviewCooldownMap;
import me.evasive.cSMultiblock.preview.PreviewIds;
import me.evasive.cSMultiblock.preview.PreviewPacketMap;
import me.evasive.cSMultiblock.util.MachineItemStacks;

import java.io.IOException;

public final class CSMultiblock extends JavaPlugin {

    private static CSMultiblock core;
    private static MultiblockMap multiblockMap;
    private static PreviewIds previewIds;
    private static PreviewPacketMap previewPacketMap;
    private static InventoryVectorMap inventoryVectorMap;
    private static PreviewCooldownMap previewCooldownMap;

    @Override
    public void onLoad(){
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage("Cosmic Multi Block Started");
        core = this;
        PacketEvents.getAPI().init();

        loadMaps();
        loadItems();
        loadCommands();
        loadEvents();

        new SavingDataConfig().loadDataFromFile();

        new MultiblockRepeatableTask().start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PacketEvents.getAPI().terminate();
        try {
            new SavingDataConfig().saveDataToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Bukkit.getConsoleSender().sendMessage("Cosmic Multi Block Stopped");
    }

    private void loadCommands(){
        getCommand("admingive").setExecutor(new GiveCommand());
    }

    private void loadEvents() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new MultiblockPlace(), this);
        pluginManager.registerEvents(new MultiblockCoreInteract(), this);
        pluginManager.registerEvents(new MultiblockCorePlace(), this);
        pluginManager.registerEvents(new MultiblockBreak(), this);
        pluginManager.registerEvents(new MultiblockInteraction(), this);
        pluginManager.registerEvents(new MoltenForgeGuiEvents(), this);
        pluginManager.registerEvents(new ForgeSelectorGuiEvents(), this);
        pluginManager.registerEvents(new MapClearOnLeave(), this);
    }

    private void loadMaps(){
        multiblockMap = new MultiblockMap();
        previewPacketMap = new PreviewPacketMap();
        inventoryVectorMap = new InventoryVectorMap();
        previewIds = new PreviewIds();
        previewCooldownMap = new PreviewCooldownMap();
    }

    public static CSMultiblock getCore(){return core;}
    public static MultiblockMap getMultiblockMap(){return multiblockMap;}
    public static PreviewIds getPreviewIds(){return previewIds;}
    public static PreviewPacketMap getPreviewPacketMap(){return previewPacketMap;}
    public static InventoryVectorMap getInventoryVectorMap(){return inventoryVectorMap;}
    public static PreviewCooldownMap getPreviewCooldownMap(){return previewCooldownMap;}

    private void loadItems() {
        new MachineItemStacks().init();
        ItemMaker.initialize();
    }

}
