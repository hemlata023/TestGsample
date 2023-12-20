package testScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import commonUtils.Utility;

public class ExtentReportTest {
	WebDriver driver;

	ExtentReports extentReports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	@BeforeTest
	public void setupExtent() {
		extentReports = new ExtentReports();
		spark = new ExtentSparkReporter("test-output/SparkReport.html")
				.viewConfigurer()
				.viewOrder()
				.as(new ViewName[] {
					ViewName.DASHBOARD,
					ViewName.TEST,
					ViewName.AUTHOR,
					ViewName.DEVICE,
					ViewName.LOG
				}).apply();
		extentReports.attachReporter(spark);
	}
	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}
  @Test
  public void cypressSearchTest() {
	  extentTest = extentReports.createTest("Cypress Search Test");
	  	driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
  }
  @Test
  public void seleniumSearchTest() {
	  extentTest = extentReports.createTest("Selenium Search Test");
	  	driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
  }
  @Test
  public void cucumberSearchTest() {
	  extentTest = extentReports.createTest("Selenium Search Test");
	  	driver.get("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Cucumber Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Cucumber Tutorial - Google Search");
  }
  
  @AfterMethod
  public void teardown(ITestResult result) {
	  extentTest.assignAuthor("AutomationTester1")
	  .assignCategory("Regression")
	  .assignDevice(System.getProperty("os.name"))
	  .assignDevice(System.getProperty("os.version"));
	  if(ITestResult.FAILURE == result.getStatus()) {
		  extentTest.log(Status.FAIL, result.getThrowable().getMessage());
		  String strPath = Utility.getScreenshotPath(driver);
		  extentTest.fail(
				  MediaEntityBuilder.createScreenCaptureFromPath(strPath).build());
	  }
  }
  @AfterTest
  public void finishExtent() {
	  extentReports.flush();
  }
}
