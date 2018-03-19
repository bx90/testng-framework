package testng.dataprovider;

import org.testng.annotations.Test;

import java.util.Map;

/**
 * @author bsun
 */
public class TestCases extends DataProviderSetup{
    final static String fileName = "dataprovidertest/MappingTest.csv";
    @Test(dataProvider = "dataProviderTest")
    public void test(Map<String, Object> contents) {
        contents.values().stream().forEach(System.out::println);
    }

    @Override
    public String fileName() {
        return fileName;
    }
}
