package com.allot.cs.suite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;


@SuiteDisplayName("ClearSee smoke suite")
@RunWith(JUnitPlatform.class)
@SelectPackages({"com.allot.cs.common","com.allot.cs.password"})
public class ClearSeeWithResetPasswordSuite {
}
