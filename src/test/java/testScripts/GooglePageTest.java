package testScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import commonUtils.Utility;

public class GooglePageTest {
	WebDriver driver;
	
	ExtentReports reports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	
	@BeforeTest
	public void setupExtent() {
		reports = new ExtentReports();
//		spark = new ExtentSparkReporter("target/Spark.html");
		spark = new ExtentSparkReporter("target/Spark.html")
				.viewConfigurer()
				.viewOrder()
				.as(new ViewName[] {
					ViewName.DASHBOARD,
					ViewName.TEST,
					ViewName.AUTHOR,
					ViewName.DEVICE,
					ViewName.LOG
				}).apply();
		reports.attachReporter(spark);
	}

	@Parameters("browser")
	@BeforeMethod
	public void setup(String  strBrowser) {
		if(strBrowser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(strBrowser.equalsIgnoreCase("edge")){
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
  @Test(retryAnalyzer = MyRetryAnalyzer.class)
  public void javaSearchTest() {
	  extentTest = reports.createTest("Java Search Test.....");
	 	driver.navigate().to("https://www.google.com/");
	 	Assert.assertEquals(driver.getTitle(), "Google");
		WebElement srcBox = driver.findElement(By.id("APjFqb"));
		srcBox.sendKeys("Java Tutorial");
		srcBox.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
	}
  
   
  @Test
  public void seleniumSearchTest() {
	  extentTest = reports.createTest("Selenium Search Test.....");
		driver.navigate().to("https://www.google.com/");
		WebElement srcBox = driver.findElement(By.id("APjFqb"));
		srcBox.sendKeys("Selenium Tutorial");
		srcBox.submit();
		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google Search");
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
	  driver.close();
  }
  
  @AfterTest
  public void finishExtent() {
	  reports.flush();
  }
}

// TestSuite -> Tests -> TestClasses -> TestMethods