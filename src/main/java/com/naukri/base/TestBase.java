package com.naukri.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class TestBase {
	

	public static WebDriver driver;
	public static Properties prop;
	public static File file;
	public static OutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\aathi\\eclipse-workspace\\NaukriCompanies\\src\\main\\java\\com\\naukri\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void initialization() {
		String BrowserName = prop.getProperty("browser");
		
		if (BrowserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\softwares\\hashwa\\Drivers\\chromedriver.exe");
			driver= new ChromeDriver();
		}
		driver.manage().window().maximize();
driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	
	public void closepopups() {
		String parentwindow = driver.getWindowHandle();
		
		Set<String> totalwindows = driver.getWindowHandles();
		Iterator<String> it = totalwindows.iterator();
		
		while (it.hasNext()) {
			String childwindow = it.next();
			if (!parentwindow.equalsIgnoreCase(childwindow)) {
				// closing child window
				driver.switchTo().window(childwindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentwindow);
	}

}
