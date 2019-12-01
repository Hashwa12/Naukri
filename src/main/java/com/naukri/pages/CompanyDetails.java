package com.naukri.pages;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naukri.base.TestBase;

public class CompanyDetails extends TestBase{

	@FindBy(xpath = "//span[contains(text(),' Search Jobs ')]")
	WebElement Searchbar;

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

	public CompanyDetails() throws Exception {
		PageFactory.initElements(driver, this);

	}

	public void company_details(String tech, String locat) throws Exception {
		Searchbar.click();
		Inputsearch.sendKeys(tech);
		location.sendKeys(locat);
		Actions action = new Actions(driver);
		action.click(experience).build().perform();
		action.click(exp_years).build().perform();
		searchbutton.click();
		// close popups
		Set<String> browserpopups = driver.getWindowHandles();
		Iterator<String> it = browserpopups.iterator();
		String parentwindowid = it.next();
		String childwindowid = it.next();
		String childwindowid1 = it.next();
		driver.switchTo().window(childwindowid);
		driver.close();
		driver.switchTo().window(parentwindowid);
		driver.switchTo().window(childwindowid1);
		driver.close();
		driver.switchTo().window(parentwindowid);

		// Total companies
		List<WebElement> totalcompanies = driver.findElements(By.xpath("//span[@class='org']"));
		int count = totalcompanies.size();
		Set<String> companydata = new HashSet<String>();
		for (int i = 0; i < count; i++) {
			companydata.add(totalcompanies.get(i).getText());
		}
		Iterator<String> it2 = companydata.iterator();
		while (it2.hasNext()) {
			System.out.println(it2.next());
		}
	}

}
