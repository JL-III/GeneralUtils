package com.playtheatria.jliii.generalutils.managers;

import com.playtheatria.jliii.generalutils.commands.AdminCommands;
import com.playtheatria.jliii.generalutils.utils.CustomLogger;
import com.playtheatria.jliii.generalutils.utils.PlayerMessenger;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class CommandManager {

    private final Plugin plugin;
    private final CustomLogger customLogger;
    private PlayerMessenger playerMessenger;

    public CommandManager(Plugin plugin, CustomLogger customLogger, PlayerMessenger playerMessenger) {
        this.plugin = plugin;
        this.customLogger = customLogger;
        this.playerMessenger = playerMessenger;
        registerCommands();
    }

    private void registerCommands() {
        Objects.requireNonNull(plugin.getServer().getPluginCommand("gu")).setExecutor(new AdminCommands(plugin, playerMessenger));
        customLogger.sendLog("Registered commands.");
    }

}
