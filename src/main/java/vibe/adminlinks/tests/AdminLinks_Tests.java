package vibe.adminlinks.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;

@Priority(21)
public class AdminLinks_Tests extends adminMethods  {
	
	@Test(priority=2101)
	public void verifyBrokenLinks() throws Exception{
		logInfo("inside verifyBrokenLinks() Test");
		go2Adminpage();
	
		readHeader("Distributor Manager");
		readSubHeaders("Distributor Manager");
		readHeader("Content");
		readSubHeaders("Content");
		readHeader("Setup");
		readSubHeaders("Setup");
	//	a.readHeader("Backend Settings");
	//	a.readSubHeaders("Backend Settings");
		
		readHeader("Marketing");
		readSubHeaders("Marketing");
		readHeader("Tools");
		readSubHeaders("Tools");
		readHeader("Security");
		readSubHeaders("Security");
		readHeader("Icentris Only");
		readSubHeaders("Icentris Only");
		readHeader("Shop");
		readSubHeaders("Shop");

	}
		
}
