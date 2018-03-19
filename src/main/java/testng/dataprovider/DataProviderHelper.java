package testng.dataprovider;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author bsun
 */
public class DataProviderHelper {
    private static final String DELIMITER = ",";

    /**
     * Convert csv content.
     *
     * @param filePath
     * @return List of data records in csv file.
     * @throws IOException
     */
    private static final List<Object[]> populateObject(String filePath) throws IOException {
        System.out.println("DataProvider-FilesPath: " + filePath);
        if(filePath == null){
            System.err.println("Input FilePath " +filePath + " is Invalid. Please set the correct path.");
            return null;
        }
        List<Object[]> contents = new LinkedList<>();
        // System.out.println("classloader : " + classLoader.getResource(filePath));
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        /* Removed with Java 8
        URL path = classLoader.getResource(filePath);
        if(path == null || !path.toString().startsWith("jar:")){
            filePath = "resources/"+ filePath;
         }
        System.out.println("Executed File Path : " +filePath);
        */
        if(classLoader.getResource(filePath) != null) {
            File file = new File(classLoader.getResource(filePath).getFile());
            try (Scanner fileScanner = new Scanner(file)) {
                String line;
                if ((fileScanner.hasNext()) && ((line = fileScanner.next()) != null)) {
                    System.out.println("Parsing Header Record : " + line);
                    String[] header = line.split(DELIMITER);

                    while (fileScanner.hasNextLine() && ((line = fileScanner.nextLine()) != null)) {
                        System.out.println("Parsing Data Record : " + line);
                        String[] record = line.split(DELIMITER);
                        Map<String, Object> recordMap = new HashMap<>();
                        if (header.length != record.length) {
                            System.err.println("Record size doesn't Match . Skipping input data record " + line);
                        } else {
                            for (int i = 0; i < header.length; i++) {
                                recordMap.put(header[i], record[i]);
                            }
                            contents.add(new Object[]{recordMap});
                        }
                    }
                }
                fileScanner.close();
            }
            catch (Exception e) {
                System.err.println("Data Provider error : cannot read file "+ filePath);
                System.err.println("Data Provider error : "+ e.getMessage());
                return contents;
            }
        }else{
            System.err.println("Data Provider error : cannot read file "+filePath);
            return contents;
        }
        return contents;
    }

    /**
     * DataProvider function that
     *
     * @param filePath
     * @return 2D Array for TestNG.
     * @throws IOException
     */
    public static final Object[][] dataProviderHelper(String filePath) throws IOException {
        List<Object[]> contents = DataProviderHelper.populateObject(filePath);
        return contents.toArray(new Object[contents.size()][]);
    }

}
