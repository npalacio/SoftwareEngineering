package unitTests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases {
  private static WebDriver driver = null;
  private static String baseUrl = "http://ec2npalacio.ddns.net:8080/";
  private boolean acceptNextAlert = true;
  private static StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass
  public static void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver", ".\\webdrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void AcreateNewUser() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(3000);
    driver.findElement(By.linkText("New? Create an account")).click();
	Thread.sleep(3000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
	Thread.sleep(1000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(3000);
    driver.findElement(By.xpath("//input[@value='Submit']")).click();
	Thread.sleep(10000);
	String text = "Welcome,";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }
  
  @Test
  public void BnewUserAddBook() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(5000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(2000);
    driver.findElement(By.linkText("Add Book")).click();
	Thread.sleep(2000);
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("My New Book");
	Thread.sleep(2000);
    driver.findElement(By.name("author")).clear();
    driver.findElement(By.name("author")).sendKeys("James Madison");
	Thread.sleep(2000);
    driver.findElement(By.name("publisher")).clear();
    driver.findElement(By.name("publisher")).sendKeys("USA Publishing");
	Thread.sleep(2000);
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("1776");
	Thread.sleep(2000);
    driver.findElement(By.name("isbn")).clear();
    driver.findElement(By.name("isbn")).sendKeys("8374293847298");
	Thread.sleep(2000);
    driver.findElement(By.name("price")).clear();
    driver.findElement(By.name("price")).sendKeys("19.99");
	Thread.sleep(2000);
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
	Thread.sleep(4000);
	String text = "Book added!";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }
  
  @Test
  public void CnewUserViewMyBooks() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(3000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
	Thread.sleep(2000);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(4000);
    driver.findElement(By.linkText("MyBooks")).click();
	Thread.sleep(4000);
	String text = "List of My Books For Sale or Trade";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }

  @Test
  public void DnewUserMakeTrade() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(5000);
    driver.findElement(By.id("trade5")).click();
	Thread.sleep(6000);
    driver.findElement(By.name("send")).click();
	Thread.sleep(6000);
	String text = "Trades you have proposed:";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }
  
  @Test
  public void EshannonNotificationsAcceptJsteinTrade() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("shannon");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("surbs");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(2000);
    driver.findElement(By.linkText("Notifications")).click();
	Thread.sleep(6000);
    driver.findElement(By.id("accept5")).click();
	Thread.sleep(6000);
	String text = "Messages";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }
  
  @Test
  public void FjsteinNotificationsOkShannonMsg() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("jstein");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("stein");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(4000);
    driver.findElement(By.linkText("Notifications")).click();
	Thread.sleep(6000);
    driver.findElement(By.id("ok16")).click();
	Thread.sleep(6000);
	String text = "Messages";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }
  
  @Test
  public void GshannonNotificationsDeclineNewUserTrade() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("shannon");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("surbs");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(2000);
    driver.findElement(By.linkText("Notifications")).click();
	Thread.sleep(6000);
    driver.findElement(By.id("decline6")).click();
	Thread.sleep(6000);
	String text = "Messages";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }
  
  @Test
  public void HnewUserNotificationsOkShannonMsg() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(4000);
    driver.findElement(By.linkText("Notifications")).click();
	Thread.sleep(6000);
    driver.findElement(By.id("ok17")).click();
	Thread.sleep(6000);
	String text = "Messages";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }
  
  @Test
  public void InewUserHomePurchase() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(5000);
    driver.findElement(By.id("purchase5")).click();
	Thread.sleep(6000);
	String text = "Messages";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }
  
  @Test
  public void JshannonNotificationsOkNewUserMsg() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("shannon");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("surbs");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(4000);
    driver.findElement(By.linkText("Notifications")).click();
	Thread.sleep(6000);
    driver.findElement(By.id("ok19")).click();
	Thread.sleep(6000);
	String text = "Messages";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }
  
  @Test
  public void KnewUserNotificationsOkPurchaseMsg() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(4000);
    driver.findElement(By.linkText("Notifications")).click();
	Thread.sleep(6000);
    driver.findElement(By.id("ok18")).click();
	Thread.sleep(6000);
	String text = "Messages";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }
  
  @Test
  public void LnewUserAddBookUnavailable() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(2000);
    driver.findElement(By.linkText("Add Book")).click();
	Thread.sleep(3000);
    driver.findElement(By.name("title")).clear();
    driver.findElement(By.name("title")).sendKeys("Cooking for Dummies");
	Thread.sleep(2000);
    driver.findElement(By.name("author")).clear();
    driver.findElement(By.name("author")).sendKeys("Guy Fieri");
	Thread.sleep(2000);
    driver.findElement(By.name("publisher")).clear();
    driver.findElement(By.name("publisher")).sendKeys("Food Network");
	Thread.sleep(2000);
    driver.findElement(By.name("year")).clear();
    driver.findElement(By.name("year")).sendKeys("2016");
	Thread.sleep(2000);
    driver.findElement(By.name("isbn")).clear();
    driver.findElement(By.name("isbn")).sendKeys("8374591274645");
	Thread.sleep(2000);
    driver.findElement(By.name("price")).clear();
    driver.findElement(By.name("price")).sendKeys("59.99");
	Thread.sleep(2000);
    driver.findElement(By.name("isAvailable")).click();
	Thread.sleep(3000);
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
	Thread.sleep(4000);
	String text = "Book added!";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }

  @Test
  public void MnewUserMyBooksHomeUnavailableBookNotThere() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(2000);
    driver.findElement(By.linkText("MyBooks")).click();
	Thread.sleep(6000);
    driver.findElement(By.linkText("Home")).click();
	Thread.sleep(6000);
	String text = "Cooking for Dummies";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text found!", !bodyText.contains(text));
  }

  @Test
  public void NnewUserContactUsMessage() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("newUser");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("abc123");
	Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(2000);
    driver.findElement(By.linkText("ContactUs")).click();
	Thread.sleep(2000);
    driver.findElement(By.name("message")).clear();
    driver.findElement(By.name("message")).sendKeys("This place is cool!");
	Thread.sleep(4000);
    driver.findElement(By.cssSelector("input.btn.btn-default")).click();
	Thread.sleep(5000);
	String text = "Message successfully sent! Thank you for your feedback, Harambe would be proud!";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text found!", bodyText.contains(text));
  }
  
  @Test
  public void OadminNotificationsOkMsg() throws Exception {
    driver.get(baseUrl + "/BookExchange/Login");
	Thread.sleep(2000);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("administrator");
	Thread.sleep(3000);
    driver.findElement(By.xpath("//input[@value='Login']")).click();
	Thread.sleep(2000);
    driver.findElement(By.linkText("Notifications")).click();
	Thread.sleep(6000);
    driver.findElement(By.id("ok20")).click();
	Thread.sleep(6000);
	String text = "Messages";
	String bodyText = driver.findElement(By.tagName("body")).getText();
	Assert.assertTrue("Text not found!", bodyText.contains(text));
  }

  @AfterClass
  public static void tearDown() throws Exception {
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
