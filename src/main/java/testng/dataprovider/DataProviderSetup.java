package testng.dataprovider;

import org.testng.annotations.DataProvider;

import java.io.IOException;

/**
 * @author bsun
 */
public abstract class DataProviderSetup {

    @DataProvider(name = "dataProviderTest")
    public Object[][] dataProviderForPrintData() throws IOException {
        return DataProviderHelper.dataProviderHelper(fileName());
    }

    public abstract String fileName();
}
