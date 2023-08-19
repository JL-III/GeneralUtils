package com.playtheatria.jliii.generalutils.enums;

public enum ToolStatus {
    ON("ON"),
    OFF("OFF"),
    EMPTY("EMPTY");

    private final String string;
    ToolStatus(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
