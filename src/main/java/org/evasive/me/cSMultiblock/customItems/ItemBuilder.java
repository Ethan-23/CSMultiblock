package org.evasive.me.cSMultiblock.customItems;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.evasive.me.cSMultiblock.keys.NamespaceKeys;

import java.util.List;

public interface ItemBuilder {

    Component getName();
    default List<Component> getLore() {
        return List.of();
    }
    String itemID();
    Material getMaterial();
    boolean isGlowing();
    ItemStack getItem();

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
        item.setItemMeta(meta);
        return item;
    }
}
