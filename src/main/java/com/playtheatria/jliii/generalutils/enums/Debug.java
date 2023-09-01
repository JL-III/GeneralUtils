package com.playtheatria.jliii.generalutils.enums;

public enum Debug {

    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3);

    private final int level;

    Debug(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
