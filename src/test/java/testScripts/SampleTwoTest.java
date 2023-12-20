package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleTwoTest {
	WebDriver driver;
	@Parameters("browser")
	@Test
	  public void cypressSearchTest(String strBrowser) {
		if(strBrowser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(strBrowser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		   
			driver.navigate().to("https://www.google.com/");
			WebElement srcBox = driver.findElement(By.id("APjFqb"));
			srcBox.sendKeys("Cypress Tutorial");
			srcBox.submit();
			Assert.assertEquals(driver.getTitle(), "Cypress Tutorial - Google Search");
			driver.close();
	  }
}
