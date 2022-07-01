package com.allot.core.testdata;

import com.allot.domain.asm.frontend.model.*;


public enum TestDataSource {

    USER(User.class, "asm/data/users.json"),
    USER_ACCOUNT(UserAccount.class, "asm/data/accounts.json"),
    USER_DEVICE(UserDevice.class, "asm/data/devices.json"),
    CATEGORY(AccountCategory.class, "asm/data/categories.json"),
    DEFAULT_CATEGORY(AccountCategory.class, "asm/data/default/categories.json"),
    PROFILE(AccountProfile.class, "asm/data/profiles.json"),
    DEFAULT_PROFILE(AccountProfile.class, "asm/data/default/profiles.json"),
    ;

    Class<? extends TestDataObject> testDataObjectClass;
    String sourceFilePath;

    TestDataSource(Class<? extends TestDataObject> testDataObjectClass, String sourceFilePath) {
        this.testDataObjectClass = testDataObjectClass;
        this.sourceFilePath = sourceFilePath;
    }

    public Class<?> getTestDataObjectClass() {
        return testDataObjectClass;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }
}
