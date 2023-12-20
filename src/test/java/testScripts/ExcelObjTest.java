package testScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExcelObjTest {
	
	WebDriver driver;
	@BeforeMethod
	public void setup() {
	
		driver = new ChromeDriver();
		
	}
  @Test
  public void validLogin() throws IOException {
	  driver.get("http://the-internet.herokuapp.com/login");
	  driver.findElement(By.cssSelector(readObjPath("username"))).sendKeys("tomsmith");
	  driver.findElement(
				By.xpath(readObjPath("password"))).sendKeys("SuperSecretPassword!");
	  driver.findElement(By.cssSelector(readObjPath("loginBtn"))).click();
	  boolean isValid = driver.findElement(By.cssSelector(readObjPath("successMsg"))).isDisplayed();
	  Assert.assertTrue(isValid);
  }
  
  public String readObjPath(String objName) throws IOException {
	  String objPath = "";
	  String path = System.getProperty("user.dir") +
			  "//src//test//resources//testData//excelData.xlsx";
	  FileInputStream fin = new FileInputStream(path);
	  //.xlsx
	  XSSFWorkbook workbook = new XSSFWorkbook(fin);
	  //.xls --   HSSFxxxx
	
	  XSSFSheet sheet =  workbook.getSheet("loginPage");
	  int numRows = sheet.getLastRowNum();
	  for(int i =1; i <= numRows; i++) {
		  XSSFRow row= sheet.getRow(i);
		  
	  if(row.getCell(0).getStringCellValue().equalsIgnoreCase(objName)) {
			  objPath = row.getCell(1).getStringCellValue();
			  System.out.println(objPath);
		  }
	  }
	  return objPath;
	  
  }
  
}
