package vibe.mywebsite.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.Priority;
import common.readProp;


@Priority(11)

public class MyWebsite_Tests extends MyWebSite_Methods {
	
	readProp prop = new readProp();
	String[] appUrl1 = appUrl.split("/");
	String url1part2 = appUrl1[2];
	
	String[] urlpart = appUrl.split("//");		
	String urlpart0 = urlpart[0];
	String urlpart1 = urlpart[1];
	String  websiteURL_text = urlpart0 + "//" + websiteName_text + "." + url1part2;
	
	
	@Test(priority=135)
	public void addWebsite() throws Exception{
		logInfo("inside addWebsite() Test.");
		System.out.println("URL= "+ websiteURL_text);

		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),
				prop.getLocatorForEnvironment(appUrl,"newsLN1"),
				prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		
		String websiteTemplate_text = prop.getLocatorForEnvironment(appUrl,"websiteTemplate_text");
		
		addMyWebsite(websiteTemplate_text,websiteName_text);

		boolean isWebsiteFound = verifyWebsiteIsPresent(websiteURL_text);
		if(isWebsiteFound==false){
			logInfo(websiteURL_text + " website could not be created.");
			Assert.assertTrue(isWebsiteFound, websiteURL_text + " website could not be created.");
		}
	}

	@Test(priority=136)
	public void manageWebsite() throws Exception{
		logInfo("inside manageWebsite() method.");
		
		boolean isWebsiteFound = verifyWebsiteIsPresent(websiteURL_text);
		if(isWebsiteFound){
			logInfo("updating the website");
			updateWebSiteContent(websiteURL_text);
			go2MyWebsite();
			deleteWebsite(websiteURL_text);
			confirmationMessage("Site was successfully deleted.");
		}	else {
			logInfo(websiteURL_text + " website not found.");
			Assert.assertTrue(isWebsiteFound, websiteURL_text + " website not found.");
		}
	}
	
	@Test(priority = 137)
	public void userLogoutFromMyWebsite() throws Exception{
		
		userLogout();
		
	}
	
	
	
	
}