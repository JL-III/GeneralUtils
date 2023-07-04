package com.playtheatria.jliii.generalutils.listeners;

import com.playtheatria.jliii.generalutils.utils.CustomLogger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;

public class Portal implements Listener {

    private CustomLogger customLogger;

    public Portal(CustomLogger customLogger) {
        this.customLogger = customLogger;
    }

    @EventHandler
    public void onPortalCreate(PortalCreateEvent event) {
        HandlerList handlers = event.getHandlers();
        for (RegisteredListener registeredListener : handlers.getRegisteredListeners()) {
            Plugin plugin = registeredListener.getPlugin();
            customLogger.sendLog(plugin.getName());  // This will print the name of the plugin that registered the listener
        }
    }


}
