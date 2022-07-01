package com.allot.secure.suite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;


@SuiteDisplayName("ISP General View and General Management › Password Policy suite")
@RunWith(JUnitPlatform.class)
@SelectPackages("com.allot.secure.e2e.password.policy")
public class PasswordPolicySuite {
}
