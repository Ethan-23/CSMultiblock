package me.evasive.cSMultiblock.customItems.multiblockcores;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import me.evasive.cSMultiblock.customItems.ItemBuilder;
import me.evasive.cSMultiblock.keys.NamespaceKeys;

public interface MultiblockCoreBuilder extends ItemBuilder {

    /**
     * Builds the multiblock core item using standard item formatting from {@link ItemBuilder},
     * and adds a persistent data byte to mark it as a multiblock core.
     *
     * @return the customized multiblock core {@link ItemStack}
     */
    @Override
    default ItemStack getItemBuilder(){
        ItemStack item = ItemBuilder.super.getItemBuilder();
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(NamespaceKeys.coreKey, PersistentDataType.BYTE, (byte) 1);
        item.setItemMeta(meta);
        return item;
    }

}
