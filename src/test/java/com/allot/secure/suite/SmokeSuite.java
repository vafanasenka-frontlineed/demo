package com.allot.secure.suite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.*;
import org.junit.runner.RunWith;


@SuiteDisplayName("Allot Secure Managment smoke suite")
@RunWith(JUnitPlatform.class)
@SelectPackages("com.allot.secure")
public class SmokeSuite {
}
