package com.playtheatria.jliii.generalutils.enums;

import net.kyori.adventure.text.format.NamedTextColor;

public enum MessageStatus {

    VALID(NamedTextColor.GREEN),
    INVALID(NamedTextColor.DARK_RED);

    private final NamedTextColor color;

    MessageStatus(NamedTextColor color) {
        this.color = color;
    }

    public NamedTextColor getColor() {
        return color;
    }
}
