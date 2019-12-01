package com.naukri.pages;

import java.io.File;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naukri.base.TestBase;
import com.naukri.utils.DataProviders;
import com.naukri.utils.ExcelWrite;

public class DataCollectionsPage extends TestBase{

	ExcelWrite excelwrite;
	public static File file;
	public static OutputStream fos;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;

	// pagefactory or object repository
	@FindBy(name = "qp")
	WebElement Inputsearch;

	@FindBy(name = "ql")
	WebElement location;

	@FindBy(xpath = "(//input[@class='sdTxt w85'])[1]")
	WebElement experience;

	@FindBy(xpath = "(//li[@id='a3'])[1]")
	WebElement exp_years;

	@FindBy(id = "qsbFormBtn")
	WebElement searchbutton;

	@FindBy(xpath = "//button[contains(text(),'Next')]")
	WebElement Nextbutton;

	@FindBy(xpath = "//a[@id='jdUrl']")
	List<WebElement> totaljobs;

	public DataCollectionsPage() throws Exception {
		PageFactory.initElements(driver, this);
	}

	public void Technologywise_company_details(String tech, String loc) throws Exception {

		// search actions
		Inputsearch.sendKeys(tech);
		location.sendKeys(loc);
		Actions action = new Actions(driver);
		action.click(experience).build().perform();
		action.click(exp_years).build().perform();
		searchbutton.click();

		// intialize excelwrite
		excelwrite = new ExcelWrite(tech);

		while (Nextbutton.isDisplayed()) {
			for (int i = 0; i < 3; i++) {//*********************************************************
				// getting all the required details from front page
				String jobtitle = driver.findElement(By.xpath("(//a[@id='jdUrl'])[" + (i + 1) + "]")).getText();
				String companyname = driver.findElement(By.xpath("(//span[@class='org'])[" + (i + 1) + "]")).getText();
				String requiredexperience = driver.findElement(By.xpath("(//span[@class='exp'])[" + (i + 1) + "]"))
						.getText();
				String location = driver.findElement(By.xpath("(//span[@class='loc'])[" + (i + 1) + "]")).getText();
				String posteddate = driver.findElement(By.xpath("(//span[@class='date'])[" + (i + 1) + "]")).getText();
				// click on individual company walkins
				driver.findElement(By.xpath("(//a[@id='jdUrl'])[" + (i + 1) + "]")).click();
				// switching to child window
				Set<String> secondwindow = driver.getWindowHandles();
				Iterator<String> it1 = secondwindow.iterator();
				String parentwindow = it1.next();
				String childwindow = it1.next();
				driver.switchTo().window(childwindow);
				// clicking on contact details
				String contactinfo = "";
				try {
					driver.findElement(By.id("viewCont_trg")).click();
					contactinfo = driver.findElement(By.id("viewContact")).getText();
					driver.close();
					driver.switchTo().window(parentwindow);
				} catch (Exception e) {
					contactinfo = "Not available";
					driver.close();
					driver.switchTo().window(parentwindow);
				}
				excelwrite.excelwriteutil(jobtitle, companyname, requiredexperience, location, posteddate, contactinfo);
			}
			Nextbutton.click();
		}
		excelwrite.write();
	}
}
