package com.allot.domain.asm.frontend.page.isp.account;

import com.allot.domain.asm.frontend.page.Tab;


public enum AccountTabs {

    USERS(UsersTab.class, 0),
    DEVICES(DeviceTab.class, 2),
    ;

    public Class<? extends Tab> clazz;
    private int index;

    AccountTabs(Class<? extends Tab> clazz, int index) {
        this.clazz = clazz;
        this.index = index;
    }

    public Class<? extends Tab> getClazz() {
        return clazz;
    }

    public int getIndex() {
        return index;
    }

}
