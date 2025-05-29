package me.evasive.cSMultiblock.customItems;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import me.evasive.cSMultiblock.keys.NamespaceKeys;

import java.util.List;

/**
 * Interface for building customized ItemStack objects with specific properties.
 */
public interface ItemBuilder {

    Component getName();
    default List<Component> getLore() {
        return List.of();
    }
    String getItemID();
    Material getMaterial();
    boolean isGlowing();
    ItemStack getItem();

    /**
     * Builds and returns the ItemStack with meta applied.
     * This includes name, lore, glowing effect, and persistent data.
     *
     * @return The constructed ItemStack.
     */
    default ItemStack getItemBuilder(){
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
        meta.getPersistentDataContainer().set(NamespaceKeys.itemID, PersistentDataType.STRING, getItemID());
        item.setItemMeta(meta);
        return item;
    }
}
