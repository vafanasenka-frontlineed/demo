package com.allot.cs.suite;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;


@SuiteDisplayName("ClearSee smoke suite")
@RunWith(JUnitPlatform.class)
@SelectPackages("com.allot.cs.common")
public class ClearSeeFixMeSuite {
    
}
