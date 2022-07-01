package com.allot.core.testdata;

import java.util.List;


public interface ITestDataProvider {

    <T extends TestDataObject> List<T> getDataList(TestDataSource dataSource);

    <T extends TestDataObject> T getData(TestDataSource dataSource, String name);

}
