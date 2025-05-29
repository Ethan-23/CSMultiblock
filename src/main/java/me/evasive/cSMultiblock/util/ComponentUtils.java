package me.evasive.cSMultiblock.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * Simplifies components for naming colored items
 */
public class ComponentUtils {

    public static Component legacyComponent(String input) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(input).decoration(TextDecoration.ITALIC, false);
    }

    public static String formatKey(String input) {
        String[] parts = input.toLowerCase().split("_");

        for (int i = 0; i < parts.length; i++) {
            if (!parts[i].isEmpty()) {
                parts[i] = parts[i].substring(0, 1).toUpperCase() + parts[i].substring(1);
            }
        }

        return String.join(" ", parts);
    }

}
