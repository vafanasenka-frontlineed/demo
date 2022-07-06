package com.allot.demo;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;


@SuiteDisplayName("Demo suite")
@RunWith(JUnitPlatform.class)
@SelectPackages("com.allot.demo")
public class DemoSuite {
}
