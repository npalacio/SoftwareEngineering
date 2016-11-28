package unitTests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCase1.class, TestCase2.class, TestCase3.class, TestCase4.class,
		TestCase5.class, TestCase6.class, TestCase7.class, TestCase8.class, TestCase9.class, TestCase10.class, TestCase11.class, TestCase12.class, TestCase13.class, TestCase14.class, TestCase15.class })
public class AllTests {

	@BeforeClass
    public static void setUp() {
        System.out.println("setting up");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("tearing down");
    }
}
