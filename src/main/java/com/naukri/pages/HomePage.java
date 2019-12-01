package com.naukri.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naukri.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//span[contains(text(),' Search Jobs ')]")
	WebElement Searchbar;

	public HomePage() throws Exception {
		PageFactory.initElements(driver, this);
	}

	public void searchpage() {

		Searchbar.click();
	}

}
