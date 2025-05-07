package org.evasive.me.cSMultiblock.customItems.multiblockcores;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.evasive.me.cSMultiblock.customItems.ItemBuilder;
import org.evasive.me.cSMultiblock.keys.NamespaceKeys;

public interface MultiblockCoreBuilder extends ItemBuilder {

    @Override
    default ItemStack buildItem(){
        ItemStack item = new ItemStack(getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.displayName(getName());
        meta.lore(getLore());
        if(isGlowing()){
            meta.addEnchant(Enchantment.LOYALTY, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }else{
            meta.removeEnchant(Enchantment.LOYALTY);
        }
        meta.getPersistentDataContainer().set(NamespaceKeys.itemID, PersistentDataType.STRING, itemID());
        meta.getPersistentDataContainer().set(NamespaceKeys.coreKey, PersistentDataType.BOOLEAN, true);
        item.setItemMeta(meta);
        return item;
    }

}
