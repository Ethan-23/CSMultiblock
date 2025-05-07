package org.evasive.me.cSMultiblock;

import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.evasive.me.cSMultiblock.commands.GiveCommand;
import org.evasive.me.cSMultiblock.config.SavingDataConfig;
import org.evasive.me.cSMultiblock.customItems.ItemMaker;
import org.evasive.me.cSMultiblock.data.InventoryVectorMap;
import org.evasive.me.cSMultiblock.data.MultiblockMap;
import org.evasive.me.cSMultiblock.events.blockevents.*;
import org.evasive.me.cSMultiblock.events.inventoryevents.ForgeSelectorGuiEvents;
import org.evasive.me.cSMultiblock.events.inventoryevents.MoltenForgeGuiEvents;
import org.evasive.me.cSMultiblock.events.playerdataevents.MapClearOnLeave;
import org.evasive.me.cSMultiblock.multiblocks.MultiblockRepeatableTask;
import org.evasive.me.cSMultiblock.preview.PreviewCooldownMap;
import org.evasive.me.cSMultiblock.preview.PreviewIds;
import org.evasive.me.cSMultiblock.preview.PreviewPacketMap;
import org.evasive.me.cSMultiblock.util.MachineItemStacks;

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
        new GiveCommand();
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

    public static CSMultiblock getCore(){
        return core;
    }
    public static MultiblockMap getMultiblockMap(){return multiblockMap;}
    public static PreviewIds getPreviewIds(){return previewIds;}
    public static PreviewPacketMap getPreviewPacketMap(){return previewPacketMap;}
    public static InventoryVectorMap getInventoryVectorMap(){return inventoryVectorMap;}
    public static PreviewCooldownMap getPreviewCooldownMap(){return previewCooldownMap;}

    private void loadItems() {
        new MachineItemStacks().init();
        new ItemMaker().initializeCustomItems();
    }

}
