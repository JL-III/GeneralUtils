package com.playtheatria.jliii.generalutils;

import com.playtheatria.jliii.generalutils.items.ItemCreator;
import com.playtheatria.jliii.generalutils.managers.CommandManager;
import com.playtheatria.jliii.generalutils.utils.CustomLogger;
import com.playtheatria.jliii.generalutils.utils.PlayerMessenger;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Properties;

public final class GeneralUtils extends JavaPlugin {

    private CustomLogger customLogger;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        PlayerMessenger playerMessenger = new PlayerMessenger();
        customLogger = new CustomLogger(getName(), NamedTextColor.GREEN, NamedTextColor.YELLOW);
        new CommandManager(this, customLogger, playerMessenger);
//        new ItemCreator();
    }

    public CustomLogger getCustomLogger() {
        return customLogger;
    }

    public static String getVersion() {
        Properties properties = new Properties();
        try {
            properties.load(GeneralUtils.class.getResourceAsStream("/version.properties"));
        } catch (IOException e) {
            return ChatColor.RED + "No generalutils.properties found, was this set up?";
        }
        String dependencyVersion = properties.getProperty("generalutils.version");
        if (dependencyVersion != null) {
            return ChatColor.GREEN + "GeneralUtils version: " + dependencyVersion;
        } else {
            return ChatColor.RED + "No version information was found";
        }
    }
}
