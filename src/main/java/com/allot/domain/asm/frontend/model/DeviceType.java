package com.allot.domain.asm.frontend.model;

public enum DeviceType {

    PC("PC/Laptop"),
    TABLET("Tablet"),
    MOBILE_PHONE("Mobile phone"),
    PRINTER("Printer"),
    GAME_CONSOLE("Game Console"),
    OTHER("Other"),
    ;

    private String name;

    DeviceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
