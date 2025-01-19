package com.playtheatria.jliii.generalutils.utils;

import com.playtheatria.jliii.generalutils.config.AbstractConfigManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * To construct a {@link CustomLogger} you must provide a {@link AbstractConfigManager} instance and 3 string HexColorCodes formatted as such {@code "#f5428a"}
 * @param <T> The JavaPlugin the logger belongs to.
 * @param <C> The ConfigManager instance for the JavaPlugin.
 */
public class CustomLogger<T extends JavaPlugin, C extends AbstractConfigManager<T>> {
    private final C configManager;
    private final String bracketsColor;
    private final String pluginNameColor;
    private final String messageColor;

    public CustomLogger(
            C configManager,
            String bracketsColor,
            String pluginNameColor,
            String messageColor
    ) {
        this.configManager = configManager;
        this.bracketsColor = validateHexColor(bracketsColor);
        this.pluginNameColor = validateHexColor(pluginNameColor);
        this.messageColor = validateHexColor(messageColor);
    }

    public void sendDebug(String message) {
        if (configManager.isDebug()) {
            sendFormattedLog(message);
        }
    }

    public void sendFormattedLog(String message) {
        sendFormattedMessage(message, Bukkit.getConsoleSender());
    }

    public void sendFormattedMessage(String message, CommandSender sender) {
        sender.sendMessage(formatMessage(configManager.getPluginName(), message));
    }

    public Component formatMessage(String label, Object value) {
        String template = "<color:%s>[<color:%s>%s<color:%s>]<color:%s> %s";
        String message = String.format(template, bracketsColor, pluginNameColor, label, bracketsColor, messageColor, value);

        return MiniMessage.miniMessage().deserialize(message);
    }

    private String validateHexColor(String color) {
        return color.matches("^#([A-Fa-f0-9]{6})$") ? color : "#FFFFFF";  // Fallback to white
    }
}
