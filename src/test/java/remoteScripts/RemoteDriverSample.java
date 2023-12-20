package remoteScripts;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class RemoteDriverSample {
  @Test
  public void sampleTest() throws MalformedURLException {
	  ChromeOptions options = new ChromeOptions();
//	  EdgeOptions options = new EdgeOptions();
//	  options.addArguments("headless");
	  options.setCapability(CapabilityType.PLATFORM_NAME,  Platform.WINDOWS);
//	 x
	  String strHub = " http://192.168.1.5:4444";
	  RemoteWebDriver driver = new RemoteWebDriver(new URL(strHub), options);
	  
	  driver.get("https://www.google.com/");
	  WebElement searchBox = driver.findElement(By.id("APjFqb"));
	  searchBox.sendKeys("Selenium Tutorial");
	  searchBox.submit();
	  System.out.println(driver.getTitle());
  }
}
