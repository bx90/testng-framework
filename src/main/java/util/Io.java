package util;

import org.testng.xml.Parser;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author bsun
 */
public class Io {
    /**
     * Get all xml files from the resource folder.
     * @param suiteFilesLocation
     * @return List of full paths of the xml files.
     */
    public static List<String> getSuiteFileNames(String suiteFilesLocation) {
        File[] files = new File(suiteFilesLocation).listFiles();
        List<String> fileLocations = new ArrayList<>();
        System.out.println(System.getProperty("user.dir"));
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
                fileLocations.add(suiteFilesLocation + file.getName());
            }
        }
        return fileLocations;
    }

    /**
     * Covert xml contents to XML Suite.
     *
     * @param fileNames
     * @return List of XMLSuite records.
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static List<XmlSuite> getSuites(List<String> fileNames) throws IOException, SAXException, ParserConfigurationException {
        List<XmlSuite> xmlSuites = new ArrayList<>();
        for(String s : fileNames) {
            xmlSuites.addAll((List<XmlSuite>)(new Parser(s).parse()));
        }
        return xmlSuites;
    }

    public static List<XmlClass> addClassesToXML(String[] classArray) {
        if (classArray == null || classArray.length == 0) {
            return null;
        }

        return Arrays.stream(classArray).peek(classPath -> System.out.println("Loading class path: " + classPath))
                     .map(XmlClass::new)
                     .collect(toList());
    }

    public static XmlSuite createTestSuiteXML(String suiteName) {
        XmlSuite suite = new XmlSuite();
        suite.setName(suiteName);
        return suite;
    }

    public static XmlTest setSuiteTestClass(XmlSuite suite, List<XmlClass> clazz) {
        XmlTest test = new XmlTest(suite);
        test.setXmlClasses(clazz);
        return test;
    }
}
