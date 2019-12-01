package com.naukri.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.naukri.base.TestBase;
import com.naukri.utils.DataProviders;
import com.naukri.pages.DataCollectionsPage;
import com.naukri.pages.CompanyDetails;

public class CompanyDetailsTest extends TestBase{
	DataCollectionsPage naukri_datacollection_page;
	CompanyDetails company_details;

	public CompanyDetailsTest() throws Exception {
		super();
	}
	
	@BeforeMethod
	public void setup() throws Exception {
		initialization();
		naukri_datacollection_page=new DataCollectionsPage();
		company_details=new CompanyDetails();
	}

	@DataProvider
	public Object[][] getdata() throws Exception {
		Object[][] data=DataProviders.getexceldata();
		return data;
	}
	
	@Test(dataProvider="getdata")
	public void cdetails(String Technologies,String Location) throws Exception {
		company_details.company_details(Technologies,Location);
	}
	
	@AfterMethod
	public void teardown() {
	driver.quit();
	}


}
