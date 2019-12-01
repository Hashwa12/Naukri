package com.naukri.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.naukri.base.TestBase;
import com.naukri.pages.DataCollectionsPage;
import com.naukri.pages.HomePage;
import com.naukri.utils.DataProviders;

public class TechDetailsTest extends TestBase{


	DataCollectionsPage naukri_datacollection_page;
	HomePage homepage;

	public TechDetailsTest() throws Exception {
		super();
	}

	@BeforeMethod
	public void setup() throws Exception {
		initialization();
		naukri_datacollection_page = new DataCollectionsPage();
		homepage = new HomePage();
		homepage.searchpage();
	}

	@DataProvider
	public Object[][] getdata() throws Exception {
		Object[][] data = DataProviders.getexceldata();
		return data;
	}

	@Test(dataProvider = "getdata" )
	public void searchtest(String Technologies, String Location) throws Exception {
		closepopups();
		naukri_datacollection_page.Technologywise_company_details(Technologies, Location);
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
