package vibe.adminlinks.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.TestBase;

public class adminMethods extends TestBase {

	public void go2Adminpage() throws Exception{
		verifyLinkPresent("Go To Admin");
		clickOnLink("linkText", "Go To Admin");
		Thread.sleep(3000);
	}
	
	public void readHeader(String HeaderName) throws Exception{
		//verifyLinkPresent(HeaderName);
		WebElement lnk = driver().findElement(By.linkText(HeaderName));
		if(lnk.isDisplayed()){

		WebElement adminSidePane = driver().findElement(By.cssSelector(adminNavigationbar));
		List<WebElement> allHeadings = adminSidePane.findElements(By.className("accordion-heading"));
		int count = allHeadings.size();
		for(int i=1;i<=count-1;i++){
			String headingName = allHeadings.get(i).getText().trim();
		//	verifyLinkPresent(headingName);
			if(headingName.equalsIgnoreCase(HeaderName)){
				System.out.println("Header =" +headingName);
				clickOnLink("linkText",headingName);
				Thread.sleep(1000);
				break;
			}
		}
	  }	else {
		  Assert.assertTrue(lnk.isDisplayed(), "Link " + HeaderName + " missing in the pane.");
	  }
	}
	
	
	public void readSubHeaders(String HeaderName) throws IOException{
		WebElement lnk = driver().findElement(By.linkText(HeaderName));
		if(lnk.isDisplayed()){
			
		}
		WebElement x = driver().findElement(By.xpath(adminInnerPane));
		List allLinks = x.findElements(By.tagName("li"));
		System.out.println("Total Sublinks in " + HeaderName + " = " +allLinks.size());
		logInfo("Total Sublinks in " + HeaderName + " = " +allLinks.size());
		int innerLinksCount = allLinks.size();
		if(innerLinksCount >0) {
			
			String before = "//div[@class='accordion-group']/div[@class='accordion-body collapse in']/div[@class='accordion-inner']/ul/li[";
			String after = "]/a/span[@class='text']";
		
			String beforeLink = "//div[@class='accordion-group']/div[@class='accordion-body collapse in']/div[@class='accordion-inner']/ul/li[";
			String afterLink = "]/a";
			
			for(int i=1;i<=allLinks.size();i++){
				WebElement e = driver().findElement(By.xpath(before+i+after));
				String linkName = e.getText().trim();
				System.out.println("Link Name =" +linkName);
				
				WebElement z = driver().findElement(By.xpath(beforeLink+i+afterLink));
				String href = z.getAttribute("href");
				URL url = new URL(href);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				String response = "";
				
				try{
					connection.connect();
					response = connection.getResponseMessage().trim();
					connection.disconnect();
					System.out.println("Response for " +linkName +" =" +response);
					logInfo("Response for " +linkName + " = " +response);
					if(!response.equalsIgnoreCase("OK")){
						logInfo("Link " + HeaderName + "-->" + linkName + " has Invalid Response \'" + response + "\'");
						Assert.assertTrue(!response.equalsIgnoreCase("OK"), "Link " + HeaderName + "-->" + linkName + " has Invalid Response \'" + response + "\'");
					}
					
				} catch(Exception ex){
					System.out.println(ex.getMessage());
				
				}
	  		 }
		}
	  }
	
	

	
	}
