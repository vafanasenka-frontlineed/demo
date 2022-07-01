package com.allot.domain.asm.frontend.model;

public enum DevicePlatform {

    ANDROID("Android"),
    IOS("iOS"),
    MACOS("MacOS"),
    WINDOWS("Windows"),
    OTHER("Other"),
    ;

    private String name;

    DevicePlatform(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
