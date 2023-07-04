package com.playtheatria.jliii.generalutils;

import com.playtheatria.jliii.generalutils.managers.CommandManager;
import com.playtheatria.jliii.generalutils.utils.CustomLogger;
import com.playtheatria.jliii.generalutils.utils.PlayerMessenger;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class GeneralUtils extends JavaPlugin {

    private CustomLogger customLogger;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        PlayerMessenger playerMessenger = new PlayerMessenger();
        customLogger = new CustomLogger(getName(), NamedTextColor.GREEN, NamedTextColor.YELLOW);
//        Bukkit.getPluginManager().registerEvents(new Portal(customLogger), this);
        new CommandManager(this, customLogger, playerMessenger);
//        new ItemCreator(this, customLogger);
    }

    public CustomLogger getCustomLogger() {
        return customLogger;
    }

}
