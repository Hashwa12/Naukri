package com.naukri.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.naukri.base.TestBase;
import com.naukri.pages.HomePage;


public class LoginTest extends TestBase{

	HomePage homepage;

	public LoginTest() throws Exception {
		super();
	}
	@BeforeMethod
	public void setup() throws Exception {
		initialization();
		homepage=new HomePage();
	}

	@Test
	public void searchtest() throws Exception {
		homepage.searchpage();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
