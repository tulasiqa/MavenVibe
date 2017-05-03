package vibe.accountsettings.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.TestBase;

public class AccountSettingsMethods extends TestBase {
	
	
	public void go2AccountSettingspage() throws Exception{
		logInfo("inside go2AccountSettingspage() method");
		driver().navigate().to(appUrl + "pyr_core/account#account-info");
		Thread.sleep(3000);
	}	
	
	
	
	public void readHeader(String HeaderName) throws InterruptedException, IOException, Exception {
		logInfo("inside readHeader() method");
		
		WebElement adminSidePane = driver().findElement(By.cssSelector("div.panel-column.col-md-2 > ul.panel-nav"));   // adminNavigationbar
		List<WebElement> allLinks = adminSidePane.findElements(By.tagName("li"));
		// System.out.println("Total Links =" +allLinks.size());
		String actHeader ;
		boolean isHeaderFound = false;
		WebElement elnk = null;
		
		String before_lnk = "//div[@class='panel panel-columnar panel-widget']/div[@class='panel-column col-md-2']/ul/li[";
		String last_lnk = "]/a";
		
				
		for(int i=1;i<=allLinks.size();i++){
			if(i==5){
				elnk = driver().findElement(By.xpath(before_lnk+i+"]/span/a"));
			} else {
				elnk = driver().findElement(By.xpath(before_lnk+i+last_lnk));
			}
			
			 actHeader = elnk.getText().trim();
			// actHeader = allLinks.get(i).getText();
			if(HeaderName.equalsIgnoreCase(actHeader)){
				elnk.click();
				System.out.println("clicked on " +actHeader); 
				String href = elnk.getAttribute("href");
				System.out.println("href = " +href); 
								
				//	try{
						URL url = new URL(href);
						HttpURLConnection connection = (HttpURLConnection) url.openConnection();
						String response = "";
						
						connection.connect();
						response = connection.getResponseMessage().trim();
						connection.disconnect();
						System.out.println("Response for " +href +" =" +response);
						logInfo("Response for " +href + " = " +response);
						if(!response.equalsIgnoreCase("OK")){
							logInfo("Link " + HeaderName + "-->" + elnk + " has Invalid Response \'" + response + "\'");
							Assert.assertTrue(!response.equalsIgnoreCase("OK"), "Link " + HeaderName + "-->" + elnk + " has Invalid Response \'" + response + "\'");
						}
						/*} catch(Exception e){
							e.getMessage();
						}*/
				
					break;
			}
			
			// break;
		}
	
		/*if(isHeaderFound==false){
			logInfo(HeaderName + " link did not found.");
		}*/
	}

	
}
