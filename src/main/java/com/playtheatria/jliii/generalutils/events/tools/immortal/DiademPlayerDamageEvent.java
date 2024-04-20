package com.playtheatria.jliii.generalutils.events.tools.immortal;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class DiademPlayerDamageEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    private final Player player;

    private final double damage;

    public DiademPlayerDamageEvent(Player player, double damage) {
        this.player = player;
        this.damage = damage;
    }

    public Player getPlayer() {
        return player;
    }

    public double getDamage() {
        return damage;
    }
}
