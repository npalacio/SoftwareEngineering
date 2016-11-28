package unitTests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCase13 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void newUserMyBooksHomeUnavailableBookNotThere13() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(5000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
	Thread.sleep(5000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(5000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(5000);
    driver.findElement(By.linkText("MyBooks")).click();
	Thread.sleep(8000);
    driver.findElement(By.linkText("Home")).click();
	Thread.sleep(10000);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
