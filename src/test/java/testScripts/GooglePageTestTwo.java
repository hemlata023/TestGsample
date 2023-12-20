package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GooglePageTestTwo {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
//		driver.get("https://www.google.com/");
////		WebElement srcBox =  driver.findElement(By.id("APjFqb"));
////		WebElement srcBox =  driver.findElement(By.name("q"));
////		WebElement srcBox =  driver.findElement(By.className("gLFyf"));
//		WebElement srcBox =  driver.findElement(By.tagName("textarea"));
//		srcBox.sendKeys("Java Tutorial");
//		srcBox.sendKeys(Keys.ENTER);
		
		
		driver.get("http://the-internet.herokuapp.com/login");
//		driver.findElement(By.linkText("Elemental Selenium")).click();
		driver.findElement(By.partialLinkText("Elemental")).click();

	}

}
