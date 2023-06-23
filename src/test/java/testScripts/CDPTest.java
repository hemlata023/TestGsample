package testScripts;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v111.emulation.Emulation;
import org.openqa.selenium.devtools.v111.network.Network;
import org.openqa.selenium.devtools.v111.network.model.Headers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class CDPTest {
	ChromeDriver driver;
	DevTools devTools;
	@BeforeMethod
	public void setup() {

		driver = new ChromeDriver();
		devTools = driver.getDevTools();
//		driver.maybeGetDevTools().get();
		devTools.createSession();
	}
	
//	@Test
	public void deviceModeTest() {
		Map deviceMetrics = new HashMap() {{
			put("width", 500);
			put("height", 800);
			put("mobile", true);
			put("deviceScaleFactor", 50);
		}};
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
		driver.get("https://www.selenium.dev/");
	}
		
	@Test
	public void geoLocTest() {  
		devTools.send(Emulation.setGeolocationOverride(
				Optional.of(33.748997), 
				Optional.of(-84.387985),
				Optional.of(100)
				));
		driver.get("https://oldnavy.gap.com/stores");
	}
	
}
