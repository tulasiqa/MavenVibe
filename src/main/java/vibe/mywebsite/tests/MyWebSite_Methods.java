package vibe.mywebsite.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.Assert;

import common.TestBase;
import common.readProp;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import vibe.mycommunity.tests.CommunityMethods;

public class MyWebSite_Methods extends TestBase {
	readProp prop = new readProp();
	
	public void go2MyWebsite(){
		logInfo("Inside go2MyWebsite() method.");
		String go2MyWebsite = appUrl + "pyr_pwp/sites";		
		driver().navigate().to(go2MyWebsite);
	}


	public void addMyWebsite(String templateName,String websiteName) throws Exception{
		logInfo("Inside addMyWebsite() method.");
		System.out.println("Inside addMyWebsite() method.");
		go2MyWebsite();
		boolean isTemplateFound = false;
		waitOnElement("xpath",panelAvailableTemplates);
		List<WebElement> templatestList = driver().findElements(By.xpath(availableTemp));
		int rows = templatestList.size();
		System.out.println(rows);
		for(int i=1;i<=rows;i++){
			  System.out.println("number of rows "+rows);
			  String beforePath = "//*[@id='available_templates']/div[";
			  String afterPath  = "]/div";
			  List<WebElement> colsList = driver().findElements(By.xpath(beforePath+i+afterPath));
			  int cols = colsList.size();
			  System.out.println("number of cols in each row "+cols);
			  for(int j=1; j<= cols; j++){
					String beforeCol = "//*[@id='available_templates']/div[";
					String 	afterCol = "]/div[";	
					String endCol = "]";
					WebElement  text = driver().findElement(By.xpath(beforeCol+i+afterCol+j+endCol));
					String expTempname = text.getText().trim();
					System.out.println(expTempname); 	
					if(expTempname.equalsIgnoreCase(templateName)){
					isTemplateFound = true;
					System.out.println("Template = " +templateName);
					String	 opt1 = "//*[@id='available_templates']/div[";
					String	 opt2 =  "]/div[";
					String	 opt3 = "]/div/div[3]/div/div";
					String opt4  = "/ul/li[2]";
					driver().findElement(By.xpath(opt1+i+opt2+j+opt3)).click();
					System.out.println("clicked...");
					/*waitOnElement("linkText","Add Website");
					clickOnLink("linkText","Add Website");*/
					driver().findElement(By.xpath(opt1+i+opt2+j+opt3+opt4)).click();
					Thread.sleep(5000);
					waitOnElement("xpath",inputWebsiteName);
					inputTextClear("xpath",inputWebsiteName);
					inputText("xpath",inputWebsiteName,websiteName_text);					
					clickOnButton("xpath",btnAddSite);
					confirmationMessage("Your site has been created.");
					Thread.sleep(5000);
				
				}
					break;
			}
	
		}	
		
		
		if(isTemplateFound==false){
			logInfo(templateName + " template not found in the webpage.");
			Assert.assertTrue(isTemplateFound, templateName + " template not found in the webpage.");
		}
	}
		
		
		
	public boolean verifyWebsiteIsPresent(String websiteURL) throws  Exception{
		logInfo("Inside verifyWebsiteIsPresent() method.");
		System.out.println("Inside verifyWebsiteIsPresent() method.");
		boolean isWebsiteFound = false;
		go2MyWebsite();
		waitOnElement("cssSelector","div#active_websites");
		List<WebElement> activeWeb = driver().findElements(By.xpath("//*[@id='active_websites']/div/div"));
		int rows = activeWeb.size();
		for(int i=2;i<=rows+1;i++){
			System.out.println("number of rows "+rows);
			String beforePath = "//*[@id='active_websites']/div[";
			String afterPath  = "]/div/div[3]/h4[2]";
			WebElement  text = driver().findElement(By.xpath(beforePath+i+afterPath));
			String website = text.getText().trim();
			System.out.println(website); 
			if(website.equalsIgnoreCase(websiteURL)){
				logInfo(websiteURL + " website match found in available websites.");
				System.out.println(" website match found.");
				isWebsiteFound = true;
				break;
		/*boolean isWebsiteFound = false;
		
		WebElement websitePanel = driver().findElement(By.cssSelector("div#active_websites"));
		List<WebElement> allIwebsites = websitePanel.findElements(By.cssSelector("div.col-md-6 > div.media.actionable.site"));
		int total_websites = allIwebsites.size();
		System.out.println("Total websites = " +total_websites);
		for(int i=2;i<=total_websites+1;i++){
			waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)");
			WebElement x = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)"));
			String website = x.getText().trim();
			System.out.println("i =" +i + ", site name = " + website);
			if(website.equalsIgnoreCase(websiteURL)){
				logInfo(websiteURL + " website match found in available websites.");
				System.out.println(" website match found.");
				isWebsiteFound = true;
				break;*/
			}
		}
		
		if(isWebsiteFound==false){
			logInfo(websiteURL + " website match not found in available websites.");
			// Assert.assertTrue(isWebsiteFound, websiteURL + " website not found in the webpage.");
		}
		return isWebsiteFound;
		
	}
	
	
	public void updateWebSiteContent(String websiteURL) throws Exception{
		logInfo("inside updateWebSiteContent() method.");
		
		waitOnElement("cssSelector","div#active_websites");
		WebElement websitePanel = driver().findElement(By.cssSelector("div#active_websites"));
		List<WebElement> allIwebsites = websitePanel.findElements(By.cssSelector("div.col-md-6 > div.media.actionable.site"));
		int total_websites = allIwebsites.size();
		System.out.println("Total websites = " +total_websites);
		for(int i=2;i<=total_websites+1;i++){
			waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)");
			WebElement x = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)"));
			String website = x.getText().trim();
			System.out.println("i =" +i + ", site name = " + website);
			if(website.equalsIgnoreCase(websiteURL)){
				logInfo(websiteURL + " website match found in available websites.");
				System.out.println(" website match found.");
				// waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i");
				WebElement btn = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i"));
				btn.click();
				Thread.sleep(5000);
				clickOnLink("linkText","Edit Website");
				enterOwnerContactDetails(websiteName_text, adminUser_text, txtWebOwnerPhone);
				break;
			}
		}
	}
	
	public void enterOwnerContactDetails(String siteName, String ownerName, String ownerPhone) throws Exception{
		logInfo("inside enterOwnerContactDetails() method.");
		
		waitOnElement("xpath", inputWebsiteName);
		inputTextClear("xpath", inputWebsiteName);
		inputText("xpath", inputWebsiteName, siteName);
		inputTextClear("cssSelector", inputWebOwnerName);
		inputText("cssSelector", inputWebOwnerName,ownerName );
		inputTextClear("cssSelector", inputWebOwnerPhone);
		inputText("cssSelector", inputWebOwnerPhone, ownerPhone );	
		composeText("xpath",composeBody,composeBodyText);		
		clickOnButton("cssSelector", btnWebsiteSave);
		confirmationMessage("Your website has been updated.");
		Thread.sleep(5000);
		clickOnElement("cssSelector", prop.getLocatorForEnvironment(appUrl,"btnCloseWebsite"));
		Thread.sleep(5000);
	}
	
	
	public void deleteWebsite(String websiteURL) throws Exception{
		logInfo("inside deleteWebsite() method.");
		
		waitOnElement("cssSelector","div#active_websites");
		WebElement websitePanel = driver().findElement(By.cssSelector("div#active_websites"));
		List<WebElement> allIwebsites = websitePanel.findElements(By.cssSelector("div.col-md-6 > div.media.actionable.site"));
		int total_websites = allIwebsites.size();
		System.out.println("Total websites = " +total_websites);
		for(int i=2;i<=total_websites+1;i++){
			waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)");
			WebElement x = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)"));
			String website = x.getText().trim();
			System.out.println("i =" +i + ", site name = " + website);
			if(website.equalsIgnoreCase(websiteURL)){
				logInfo(websiteURL + " website match found in available websites.");
				System.out.println(" website match found.");
				// waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i");
				WebElement btn = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i"));
				btn.click();
				Thread.sleep(3000);
				clickOnLink("linkText","Delete Website");
				Thread.sleep(2000);
				//confirmEventDeleteModal();
				confirmOK();
				break;
			}
		}
	}
		
		
	
	// Ravi
	
	public String getUrl(String websiteURL) throws Exception{
        logInfo("inside getUrl() method.");
        
        waitOnElement("cssSelector","div#active_websites");
        WebElement websitePanel = driver().findElement(By.cssSelector("div#active_websites"));
        List<WebElement> allIwebsites = websitePanel.findElements(By.cssSelector("div.col-md-6 > div.media.actionable.site"));
        int total_websites = allIwebsites.size();
        System.out.println("Total websites = " +total_websites);
        for(int i=2;i<=total_websites+1;i++){
            waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)");
            WebElement x = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child(" +i+ ") > div.media.actionable.site > div.media-body > h4:nth-child(2)"));
            String website = x.getText().trim();
            System.out.println("i =" +i + ", site name = " + website);
            if(website.equalsIgnoreCase(websiteURL)){
                logInfo(websiteURL + " website match found in available websites.");
                System.out.println(" website match found.");
                // waitOnElement("cssSelector","div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i");
                WebElement btn = driver().findElement(By.cssSelector("div#active_websites > div.col-md-6:nth-child("+i+") > div.media.actionable.site > div.more-options > div.btn-group > button > i"));
                btn.click();
                waitOnElement("linkText","Edit Website");
                clickOnLink("linkText","Edit Website");
                break;
                }
            }
        Thread.sleep(5000);
            WebElement urlSite = driver().findElement(By.cssSelector(webUrl));
            String webSiteUrl = urlSite.getText();
            System.out.println("url is "+ webSiteUrl);
            
            return webSiteUrl;
    
}


	
}