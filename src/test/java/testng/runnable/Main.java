package testng.runnable;

import util.Io;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bsun
 */
public class Main {
    private static final String SUITE_FILE_PATH = "./src/test/resources/suite/";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        System.out.println("Current Directory -- " + System.getProperty("user.dir"));

        List<XmlSuite> suites = new ArrayList<>();

        // By default, if there isn't any class paths provided while
        // running the jar file, it will run the 'end-end-demo.xml'
        if (args == null || args.length == 0) {
            List<String> suiteFiles = Io.getSuiteFileNames(SUITE_FILE_PATH);
            suites = Io.getSuites(suiteFiles);
        } else {
            XmlSuite suite = Io.createTestSuiteXML("Run test cases.");
            List<XmlClass> classes = Io.addClassesToXML(args);
            Io.setSuiteTestClass(suite, classes);
            suites.add(suite);
        }

        TestNG testng = new TestNG();
        testng.setXmlSuites(suites);
        testng.run();
    }
}
