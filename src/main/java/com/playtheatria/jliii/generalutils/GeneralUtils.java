package com.playtheatria.jliii.generalutils;

import com.playtheatria.jliii.generalutils.managers.CommandManager;
import com.playtheatria.jliii.generalutils.utils.CustomLogger;
import com.playtheatria.jliii.generalutils.utils.PlayerMessenger;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class GeneralUtils extends JavaPlugin {

    @Override
    public void onEnable() {
        PlayerMessenger playerMessenger = new PlayerMessenger();
        CustomLogger customLogger = new CustomLogger(getName(), NamedTextColor.GREEN, NamedTextColor.YELLOW);
        new CommandManager(this, customLogger, playerMessenger);
    }

}
