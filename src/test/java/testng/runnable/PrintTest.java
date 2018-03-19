package testng.runnable;

import org.testng.annotations.Test;

/**
 * @author bsun
 */
public class PrintTest {
    @Test
    public void testCase1() {
        System.out.println("In print test case 1.");
    }
    @Test
    public void testCase2() {
        System.out.println("In print test case 2.");
    }
}
