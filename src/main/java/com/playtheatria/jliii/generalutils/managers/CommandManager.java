package com.playtheatria.jliii.generalutils.managers;

import com.playtheatria.jliii.generalutils.GeneralUtils;
import com.playtheatria.jliii.generalutils.commands.AdminCommands;
import com.playtheatria.jliii.generalutils.commands.KitCommands;
import com.playtheatria.jliii.generalutils.utils.CustomLogger;
import com.playtheatria.jliii.generalutils.utils.PlayerMessenger;

import java.util.Objects;

public class CommandManager {

    private final GeneralUtils plugin;
    private final CustomLogger customLogger;
    private PlayerMessenger playerMessenger;

    public CommandManager(GeneralUtils plugin, CustomLogger customLogger, PlayerMessenger playerMessenger) {
        this.plugin = plugin;
        this.customLogger = customLogger;
        this.playerMessenger = playerMessenger;
        registerCommands();
    }

    private void registerCommands() {
        Objects.requireNonNull(plugin.getServer().getPluginCommand("gu")).setExecutor(new AdminCommands(plugin, playerMessenger));
        Objects.requireNonNull(plugin.getServer().getPluginCommand("tkit")).setExecutor(new KitCommands(customLogger));
        customLogger.sendLog("Registered commands.");
    }

}
