package testScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class LoginPageTest {
	WebDriver driver;
	Properties prop;
	@BeforeMethod
	public void setup() {
		prop = new Properties();
		String path = System.getProperty("user.dir") 
				+ "//src//test//resources//configFiles//config.properties";
		FileInputStream fin;
		 try {
			fin = new FileInputStream(path);
			prop.load(fin);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String strBrowser = prop.getProperty("browser");
		if(strBrowser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(strBrowser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
  @Test(dataProvider = "loginData")
  public void validLogin(String strUser, String strPwd) {
	  driver.get(prop.getProperty("url"));
	  driver.findElement(By.cssSelector("input#username")).sendKeys(strUser);
	  driver.findElement(
				By.xpath("//input[@type='password' or @id='password']")).sendKeys(strPwd);
	  driver.findElement(By.cssSelector(".radius")).click();
	  boolean isValid = driver.findElement(By.cssSelector("div.flash.success")).isDisplayed();
	  Assert.assertTrue(isValid);
  }
  //"tomsmith", "SuperSecretPassword!"
  
//  @DataProvider(name="loginData")
//  public Object[][] getData() throws CsvValidationException, IOException{
//	  String path = System.getProperty("user.dir") +
//			  "//src//test//resources//testData//loginData.csv";
//
//	  CSVReader reader = new CSVReader(new FileReader(path));
//	  String str[];
//	  ArrayList<Object> dataList = new ArrayList<Object>();
//	  while((str = reader.readNext()) != null) {
//		  Object[] record = {str[0], str[1]};
//		  dataList.add(record);
//		  }
//	  reader.close();
//	  return dataList.toArray(new Object[dataList.size()][]);
//  }
//  
  @DataProvider(name="loginData")
  public String[][] getData() throws IOException, ParseException {  
	  String path = System.getProperty("user.dir") +
			  "//src//test//resources//testData//loginData.json";
	  FileReader reader = new FileReader(path);
	  JSONParser parser = new JSONParser();
	  Object obj = parser.parse(reader);
	  JSONObject jsonObj = (JSONObject) obj;
	  JSONArray userArray = (JSONArray)jsonObj.get("userLogins");
	  String arr[][] = new String[userArray.size()][];
	  for(int i = 0; i < userArray.size(); i++) {
		 JSONObject user=  (JSONObject)userArray.get(i);
		 String strUser = (String)user.get("username");
		 String strPwd = (String)user.get("password");
		 String record[] = {strUser, strPwd};
		 arr[i] = record;
	  }
	  return arr;
  }
  
  @AfterMethod
  public void teardown() {
	  driver.close();
  }
}
