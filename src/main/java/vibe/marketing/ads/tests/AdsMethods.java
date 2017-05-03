package vibe.marketing.ads.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.sun.glass.events.KeyEvent;

import vibe.billing.tests.BillingMethods;
import vibe.chat.tests.ChatMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.tasks.tests.TaskMethods;
import vibe.users.tests.MonatUsersMethods;
import common.TestBase;

public class AdsMethods extends TestBase{

	TaskMethods tm = new TaskMethods();
	NewsMethods nm = new NewsMethods();
	MonatUsersMethods um = new MonatUsersMethods();
	BillingMethods bm = new BillingMethods();
	
	public void adsCreation(String url, String ranker, String lang,String status,String[] subscription) throws Exception{
	    navigateConfigureAds();
	   	selectAddNewAd();	
	   	enterUrl(url);	
	 	uploadEventImages();	 		 	
	 	selectTaxons();
	 	selectStatus(status);	 
	 	//defineAllRankers();
	 	//marketLanguages(lang);
	 	//SubscriptionPlanSlection(subscription);		
	}
	
	public void selectPublishImmd() throws Exception, Exception{
		waitOnElement("cssSelector", compPublishArticle);
	 	clickOnElement("cssSelector", compPublishArticle);
	}
	
	public void enterPublishDate(int FutueDate) throws Exception{
		String futDate = getDate(FutueDate, "MM/dd/yyyy");
		String ExpDate = getDate(FutueDate+5, "MM/dd/yyyy");
		inputTextClear("cssSelector", adsPubDate);
		inputText("cssSelector", adsPubDate, futDate);	
		inputTextClear("cssSelector", adsExpDate);
		inputText("cssSelector", adsExpDate, ExpDate);
	
	        }
	
	
	
	
	public void adsCreationWithAllOptions(String url, String status) throws Exception, InterruptedException, IOException, AWTException, Exception{
	    navigateConfigureAds();
	   	selectAddNewAd();	
	   	enterUrl(url);	
	 	uploadEventImages();	 		 	
	 	selectTaxons();
	 	selectStatus(status);
	 	clickOnElement("cssSelector", adRankDefine);
	 	WebElement allrank = driver().findElement(By.cssSelector(adsAllRank));
	 	if(!allrank.isSelected()){
	 		allrank.click();
	 	}
	 	
	 	clickOnElement("cssSelector", adMarketLang);
		WebElement all = driver().findElement(By.cssSelector("#market_all"));
			if(!all.isSelected()){
				all.click();
				Thread.sleep(2000);
								}
			
			clickOnElement("xpath", adSubPlan);			
			WebElement allPlan = driver().findElement(By.cssSelector("#subscription_all"));
			if(!allPlan.isSelected()){
				allPlan.click();
				Thread.sleep(2000);
				}	
		
			
	}
	
	public void alertMessage(){
		WebElement am = driver().findElement(By.cssSelector(alertmsgAds));
		System.out.println(am.getText());
		Assert.assertEquals(am.getText(), "can't be blank");	
	}

	public void navigateConfigureAds() throws Exception{
		logInfo("Navigate to Marketing >> Configure Ads ");
		try{	
			driver().navigate().to(appUrl+ "/pyr_core/banners");
			Thread.sleep(2000);
		}
		catch(Exception e){
			logger.error("Failed!! Not able to naviagte to Configure Ads Screen");	
		}		
	}
	
	public void selectAddNewAd() throws Exception, Exception{
		logInfo("Select 'Add new Ad' link.");
		try{			
			clickOnButton("cssSelector",addNewAdLnk);
			Thread.sleep(5000);
		}catch (WebDriverException we ){
			
			logger.error("Failed!! Not able to select Add New Ad link.");
		}	
	}
	
	public void uploadImage() throws Exception, IOException, AWTException, InterruptedException{
		logInfo("Upload a Photo from Local System .");
		try{
			Thread.sleep(6000);
			clickOnElement("cssSelector",adClickToUpload);
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Image.exe");
			Thread.sleep(5000);			
		}catch (WebDriverException we ){			
			logger.error("Failed!! Not able to upload Image File.");
		}
	}
	
	public void uploadEventImages() throws Exception, IOException, AWTException, InterruptedException{
		logInfo("Upload a Photo from Local System .");
		try{
			Thread.sleep(6000);	
			clickOnElement("cssSelector",adClickToUpload);	
			Thread.sleep(4000);
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Image.exe");
			Thread.sleep(3000);
		}catch (WebDriverException we ){
			
			logger.error("Failed!! Not able to upload Image File.");
		}
	}
	
	public void enterUrl(String url) throws Exception{
		logInfo("Enter url into URL.");
		try{
			inputTextClear("cssSelector",adURL);
			inputText("cssSelector",adURL, url );
		}
		catch (WebDriverException we ){
			logger.error("Failed!! Not able to enter Url.");
		}
	}
	
	
	public void selectOfficeVisibility() throws Exception, InterruptedException{
		logInfo("Select Check box of Office Visibility");
		try{
			WebElement OV = driver().findElement(By.cssSelector(adOfficeVisibilityChkBox));	
			if(!OV.isSelected()){
				OV.click();
				Thread.sleep(5000);
			}
		}
		catch (WebDriverException we ){			
			logger.error("Failed!! Not able to select check box Office visibility .");
		}	
	}
	
	public void selectShopVisibility() throws Exception, InterruptedException{
		logInfo("Select Check box of Shop Visibility");
		try{
			WebElement SV = driver().findElement(By.cssSelector(adShopVisibilityChkBox));	
			if(!SV.isSelected()){
				SV.click();
				Thread.sleep(5000);
			}
		}catch (WebDriverException we ){			
			logger.error("Failed!! Not able to select check box Shop visibility.");
		}
	}
	
	public void selectTaxons() throws Exception, InterruptedException{
		logInfo("Select check boxes of Taxons.");
		try{
			Thread.sleep(2000);
			clickOnElement("cssSelector", adTaxons);
			List <WebElement> tax = driver().findElements(By.cssSelector(adTaxonsDD));		
			for (WebElement taxon :tax){			
				taxon.click();			
			}
		}catch (WebDriverException we ){			
			logger.error("Failed!! Not able to select Taxon.");
		}
	}
	
	public void selectStatus(String status) throws Exception, InterruptedException{
		logInfo("Select the Status from dropdown");
		try{
			WebElement sts = driver().findElement(By.cssSelector(adStatusDropdown));
			Select sel = new Select(sts);
			sel.selectByVisibleText(status);
		}catch (WebDriverException we ){			
			logger.error("Failed!! Not able to select Status.");
		}		
	}
	
	public void defineAllRankers() throws Exception, InterruptedException{
		logInfo("Select check box of 'All Rank Definitions.'");
		try{
			Thread.sleep(2000);
			clickOnElement("cssSelector", adRankDefine);
			Thread.sleep(1000);				
			WebElement all = driver().findElement(By.cssSelector("#rank_all"));
			if(!all.isSelected()){
				all.click();
				Thread.sleep(2000);				
				}
		}catch (WebDriverException we ){			
			logger.error("Failed!! Not able to define Ranks.");
		}
	}
	
	
	public void defineRank(String ranker) throws Exception, InterruptedException{
		logInfo("Select check box of 'Rank Definitions.'");
		try{
			Thread.sleep(2000);
			clickOnElement("cssSelector", adRankDefine);
			Thread.sleep(1000);				
			WebElement all = driver().findElement(By.cssSelector("#rank_all"));
			if(all.isSelected()){
				all.click();
				Thread.sleep(2000);
				}	
			List <WebElement> status = driver().findElements(By.cssSelector(adRankDefineDD));		
			for (WebElement st : status){							
				if (st.getText().equals(ranker)){	
					if(!st.isSelected()){
					st.click();	
					}
					clickOnElement("cssSelector", adRankDefine);
					break;
									}	
			}
		}catch (WebDriverException we ){			
			logger.error("Failed!! Not able to define Ranks.");
		}
	}
	
	public void marketLanguages(String language) throws Exception, InterruptedException{
		logInfo("Select Market Languages.");
		try{
		clickOnElement("cssSelector", adMarketLang);
		Thread.sleep(1000);
		boolean islangPresent = false;
		List <WebElement> lang = driver().findElements(By.cssSelector(adMarketLangChkBox));	
		System.out.println("size is "+ lang.size());
		Thread.sleep(3000);
		for (WebElement langs : lang){
			islangPresent =true;
			WebElement all = driver().findElement(By.cssSelector("#market_all"));
			if(all.isSelected()){
				all.click();
				Thread.sleep(2000);
								}	
			if (langs.getText().equalsIgnoreCase(language)){
				if(!langs.isSelected()){
				langs.click();		
				clickOnElement("cssSelector", adMarketLang);
				break;
			}	}
		}if (islangPresent ==false){
			Assert.assertTrue(islangPresent, language + "- languague is not present.");
		}
		
		}catch (WebDriverException we ){			
			logger.error("Failed!! Not able to select Market Language.");
		}				
	}
	
	public void SubscriptionPlanSlection(String[] SubcriptionPlan) throws Exception{
		logInfo("Select Subscription Plans.");
		try{
			clickOnElement("xpath", adSubPlan);
			List <WebElement> sub = driver().findElements(By.cssSelector(adSubPlanChkBox));	
			for (WebElement subPlan : sub){
				WebElement all = driver().findElement(By.cssSelector("#subscription_all"));
				if(all.isSelected()){
					all.click();
					Thread.sleep(2000);
					}				
				if ((subPlan.getText()).equals(SubcriptionPlan)){	
					if(!subPlan.isSelected()){
						subPlan.click();
						Thread.sleep(5000);
						clickOnElement("xpath", adSubPlan);
						break;
						
					}
				}
			}
		}catch (WebDriverException we ){			
			logger.error("Failed!! Not able to select Subcription Plans.");
		}		
	}	
	
	
	public void selectAddBanner() throws Exception{
		logInfo("Select button of 'Add Banner'.");
		try{
			waitOnElement("cssSelector", adAddBannerUpdate);
			clickOnButton("cssSelector", adAddBannerUpdate);
			confirmationMessage("Banner created successfully.");
			waitOnElement("cssSelector",addNewAdLnk);
			
				
		}catch (WebDriverException we ){
			
			logger.error("Failed!! Not able to select Add Banner Button.");
		}				
	}
	
	
	public void selectBackToAds() throws Exception{
		logInfo("Select Back to Ads button.");
		try{
			
			JavascriptExecutor js = (JavascriptExecutor)driver();   
	    	 WebElement back = driver().findElement(By.linkText("Back To Ads"));
	    	 js.executeScript("arguments[0].scrollIntoView(true);", back); 			
			clickOnLink("linkText", "Back To Ads");
		/*	clickOnButton("cssSelector", adBackToAdsBtn);*/
		}catch (WebDriverException we ){
			
			logger.error("Failed!! Not able to select Back To Ads Button.");
		}		
	}
	
	public void getAllMarketCountries() throws Exception{
		logInfo("List of Market through out Global");
		try{
		clickOnButton("cssSelector", admarket);
		/*clickOnElement("cssSelector", admarket);
		verifyTextPresent("all");*/
		List <WebElement> market = driver().findElements(By.cssSelector(admarketDrpdwn));
		System.out.println("Get countires Market list is "+market.size());
		for (WebElement markets: market){			
			String cont = markets.getText().trim();
			
			switch (cont) {
			case "US":
				System.out.println(cont + " is match found.");
				break;
			case "CA":
				System.out.println(cont + " is match found.");
				break;
			case "MX":
				System.out.println(cont + " is match found.");
				break;
			case "CN":
				System.out.println(cont + " is match found.");
				break;
			case "DZ":
				System.out.println(cont + " is match found.");
				break;	
			case "VN":
				System.out.println(cont +" is match found.");
				break;
			case "AX":
				System.out.println(cont +" is match found.");
				break;	
			case "AL":
				System.out.println(cont + " is match found.");
				break;
			case "IN":
				System.out.println(cont + " is match found.");
				break;
			case "FR":
				System.out.println(cont + " is match found.");
				break;
			case "AS":
				System.out.println(cont + " is match found.");
				break;
			case "AD":
				System.out.println(cont + " is match found.");
				break;	
			case "AO":
				System.out.println(cont +" is match found.");
				break;
			case "AI":
				System.out.println(cont +" is match found.");
				break;
			case "BI":
				System.out.println(cont + " is match found.");
				break;
			case "IO":
				System.out.println(cont + " is match found.");
				break;
			case "PE":
				System.out.println(cont + " is match found.");
				break;
			case "ZW":
				System.out.println(cont + " is match found.");
				break;
			case "KH":
				System.out.println(cont + " is match found.");
				break;	
			case "DM":
				System.out.println(cont +" is match found.");
				break;
			case "EC":
				System.out.println(cont +" is match found.");
				break;	
			case "AU":
				System.out.println(cont + " is match found.");
				break;
			case "JP":
				System.out.println(cont + " is match found.");
				break;
			case "AW":
				System.out.println(cont + " is match found.");
				break;
			case "BS":
				System.out.println(cont + " is match found.");
				break;	
			case "AQ":
				System.out.println(cont +" is match found.");
				break;
			case "YE":
				System.out.println(cont +" is match found.");
				break;
			case "AF":
				System.out.println(cont + " is match found.");
				break;	
			case "All":
				System.out.println(cont + " is match found.");
				break;					
				
			default :
				logger.error("Country is "+cont);			
			}
			
			
			}}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Market countries");			
		}
	}
	
	
	public void getAllSubcriptionPlans() throws Exception{
		logInfo("Retrieve all List of Subscriptions.");
		try{
		clickOnButton("cssSelector", sortSub);
		List <WebElement> market = driver().findElements(By.cssSelector(adSubcriptionsDrpdwn));
		System.out.println("Get  all subscription "+market.size());
		for (WebElement markets: market){
			String sub = markets.getText().trim();
			switch (sub){
			case "Vibe Lite - Monthly":
				System.out.println(sub +" is match found.");
				break;
			case "Vibe Lite - Yearly":
				System.out.println(sub +" is match found.");
				break;
			case "Vibe Pro - Monthly":
				System.out.println(sub +" is match found.");
				break;
			case "Vibe Pro - Yearly":
				System.out.println(sub +" is match found.");
				break;	
			case "All":
				System.out.println(sub +" is match found.");
				break;		
			default:
				logger.error("subscription is "+sub);	
				}}}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Subscription");
		}
	}
	
	public void getAllAvailablity() throws Exception{
		logInfo("List of Available");
		try{
			clickOnButton("cssSelector", sortAvail);
			List <WebElement> market = driver().findElements(By.cssSelector(adAvailableDrpdwn));
			for (WebElement markets: market){
				String avail = markets.getText().trim();	
				switch (avail){	
				case "Office":
					System.out.println(avail +" is match found.");
					break;
				case "Shop":
					System.out.println(avail +" is match found.");
					break;	
				case "All":
					System.out.println(avail +" is match found.");
					break;		
				default:
					logger.error("Availablity  is "+avail);	
				
				}
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Availability");
		}
	}
	
	public void getAllShopCategory() throws Exception{
		logInfo("Retrieve all list of Shops Category");
		try{
			clickOnButton("cssSelector", sortShop);
			List <WebElement> market = driver().findElements(By.cssSelector(adShopCatDrpdwn));
			System.out.println("Get  all Shops Category"+market.size());
			for (WebElement markets: market){
				System.out.println(markets.getText());
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Subscription");
		}
	}
	
	public void getAllLanguages() throws Exception{
		logInfo("Retrieve all list of Languages");
		try{
			clickOnButton("cssSelector", sortLang);
			List <WebElement> market = driver().findElements(By.cssSelector(adlanguageDrpdwn));
			System.out.println("Get  all subscription"+market.size());
			for (WebElement markets: market){
				System.out.println(markets.getText());
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Subscription");
		}
	}
	
	public void getAllRankers() throws Exception{
		logInfo("Retrieve all list of Rankers");
		try{
			clickOnButton("cssSelector", sortRanks);
			List <WebElement> market = driver().findElements(By.cssSelector(adRankDrpdwn));
			System.out.println("Get  all subscription"+market.size());
			for (WebElement markets: market){
				System.out.println(markets.getText());
				}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Subscription");
		}
	}
	
	public void getAllStatus() throws Exception{
		logInfo("Retrieve all list of Status");
		try{
			clickOnButton("cssSelector", sortStatus);
			List <WebElement> market = driver().findElements(By.cssSelector(adStatusDrpdwn));
			for (WebElement markets: market){			
				String avail = markets.getText().trim();
			switch (avail){			
			case "All":
				System.out.println(avail +" is match found.");
				break;
			case "Published":
				System.out.println(avail +" is match found.");
				break;
			case "Draft":
				System.out.println(avail +" is match found.");
				break;	
			case "Archived":
				System.out.println(avail +" is match found.");
				break;		
			default:
				logger.error("Status is "+avail);	
			}
		}}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Subscription");
		}
	}
			
	public void sortSelectMarket(String country) throws Exception{
		logInfo("Sort the country uder Market for "+ country);
		try{
			clickOnElement("cssSelector", admarket);
			List <WebElement> market = driver().findElements(By.cssSelector(admarketDrpdwn));
			for (WebElement markets: market){
				if(markets.getText().equals(country)){
					markets.click();
					break;
				}
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select either Market or Market optionals.");
		}
	}
	
	public void selectLanguage(String Langauge) throws Exception{
		logInfo("Sorting the Language.");
		try{
		clickOnElement("cssSelector", adlanguage);
		List <WebElement> lang = driver().findElements(By.cssSelector(adlanguageDrpdwn));
		System.out.println("Get Language is "+lang.size());
		for (WebElement langs: lang){
			if(langs.getText().equals(Langauge)){
				langs.click();
				break;
			}			
		}
			
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select either langugae or langugae options.");
		}
	}
	
	public void selectSubcription(String subcription) throws Exception{
		logInfo("Sorting the Subscription with "+subcription);
		try{
			clickOnElement("cssSelector", adSubcriptions);
			List <WebElement> sub = driver().findElements(By.cssSelector(adSubcriptionsDrpdwn));
			System.out.println("Get Subscription list is "+sub.size());
			for (WebElement subs: sub){
				if(subs.getText().equals(subcription)){
					subs.click();
					break;
				}			
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select either Subcription or subcriptions options.");
		}
	}
	
	//Delete all Ads from Ads main Page.
	public void deleteAllAds() throws Exception, InterruptedException{
		logInfo("Entered into deleteAllAds() method.");
		Thread.sleep(3000);
		List <WebElement> del = driver().findElements(By.cssSelector(delAds));
		int dele = del.size();	
		System.out.println("Size is "+ dele);
		for (int i=del.size(); i>=1;i--){
			String ed1= "div#banners > div:nth-child(" ;
			String ed2=") > div > div.links > a.ic-icon-regular.ic-icon-delete";
			
			WebElement delete = driver().findElement(By.cssSelector(ed1+i+ed2));			
			delete.click();
			Thread.sleep(2000);
			deleteConfirm();
			confirmationMessage("Banner deleted successfully.");
			Thread.sleep(4000);
	
		}
	}
	
	// Edit Ads and validate URL , if matches url then deletes.
	public void deleteAd(String expectedUrl) throws Exception{
		logInfo("Entered deleteAd(String expectedUrl) method");			
		boolean isUrlPresent = false;
		List <WebElement> del = driver().findElements(By.cssSelector(delAds));
		int dele = del.size();			
		for (int i=1; i<=dele;i++){{
			String ed1= "div#banners > div:nth-child(" ;
			String ed2=") > div > div.links > a.ic-icon-regular.ic-icon-edit";
			String dele2=") > div > div.links > a.ic-icon-regular.ic-icon-delete";
			WebElement edits = driver().findElement(By.cssSelector(ed1+i+ed2));			
			edits.click();
			Thread.sleep(2000);				
			WebElement actUrl= driver().findElement(By.cssSelector(adURL));
			String actualUrl= actUrl.getAttribute("value");
			if (!actualUrl.equalsIgnoreCase(expectedUrl)){				
				selectBackToAds();
			}else{				
				isUrlPresent=true;	
				selectBackToAds();
				Thread.sleep(2000);
				WebElement delete = driver().findElement(By.cssSelector(ed1+i+dele2));	
				delete.click();
				Thread.sleep(2000);
				deleteConfirm();
				confirmationMessage("Banner deleted successfully.");
				Thread.sleep(4000);				
				break;
				}		
		} } if(isUrlPresent==false){		
			Assert.assertTrue(isUrlPresent,expectedUrl + " is not present" );			
		} }
	
	
	// Edit existing Ads and verify its URL , if matches -break;
		public boolean validateAdsUrl(String expectedUrl) throws Exception{
			logInfo("Entered deleteAd(String expectedUrl) method");			
			boolean isUrlPresent = false;
			List <WebElement> del = driver().findElements(By.cssSelector(delAds));
			int dele = del.size();			
			for (int i=1; i<=dele;i++){{
				String ed1= "div#banners > div:nth-child(" ;
				String ed2=") > div > div.links > a.ic-icon-regular.ic-icon-edit";
				String dele2=") > div > div.links > a.ic-icon-regular.ic-icon-delete";
				WebElement edits = driver().findElement(By.cssSelector(ed1+i+ed2));			
				edits.click();
				Thread.sleep(2000);				
				WebElement actUrl= driver().findElement(By.cssSelector(adURL));
				String actualUrl= actUrl.getAttribute("value");
				if (!actualUrl.equalsIgnoreCase(expectedUrl)){				
					selectBackToAds();
				}else{				
					isUrlPresent=true;								
					break;
					}		
			} } if(isUrlPresent==false){		
				Assert.assertTrue(isUrlPresent,expectedUrl + " is not present" );			
			}
			return isUrlPresent;
        }
		
		public void validateField() throws Exception, Exception{
			String text = "Publish this article immediately";
			waitOnElement("cssSelector", adsPubText);
			WebElement chBox = driver().findElement(By.cssSelector(adsPubText));
			System.out.println(chBox.getText());
			Assert.assertEquals(chBox.getText(), text);
			selectAddBanner();
			confirmationMessage("Banner updated successfully.");			
		}
	
	//Validate the created Ad by comparing URLs, Edit each & every Ad, and retrieve url for comparision.");	 
	public void validateAdsbyItsUrl (String expectedUrl) throws Exception{
		logInfo("Entered validateAdsbyItsUrl(String expectedUrl) method.");	    	
		List <WebElement> del = driver().findElements(By.cssSelector(delAds));
		int dele = del.size();	
		System.out.println("Size is "+ dele);
		for (int i=1; i<=del.size();i++){
			String ed1= "div#banners > div:nth-child(" ;
			String ed2=") > div > div.links > a.ic-icon-regular.ic-icon-edit";
			WebElement edits = driver().findElement(By.cssSelector(ed1+i+ed2));
			System.out.println(edits.getText());
			edits.click();
			Thread.sleep(2000);
			boolean isUrlPresent = false;
			WebElement actUrl= driver().findElement(By.cssSelector(adURL));
			String actualUrl= actUrl.getAttribute("value");
			if (actualUrl.equalsIgnoreCase(expectedUrl)){
				System.out.println(actualUrl+ " is exactly matched");
				isUrlPresent=true;				
				selectAddBanner();
				confirmationMessage("Banner updated successfully.");
				break;
		}if(isUrlPresent==false){
			selectBackToAds();			
		}
		
	 }
		
	}
		public void editExistingAds(String expectedUrl) throws Exception{
			logInfo("edit t Existing Ad by comapring URLs ");	    	
			List <WebElement> del = driver().findElements(By.cssSelector(delAds));
			int dele = del.size();	
			System.out.println("Size is "+ dele);
			for (int i=1; i<=del.size();i++){
				String ed1= "div#banners > div:nth-child(" ;
				String ed2=") > div > div.links > a.ic-icon-regular.ic-icon-edit";
				WebElement edits = driver().findElement(By.cssSelector(ed1+i+ed2));
				System.out.println(edits.getText());
				edits.click();
				Thread.sleep(2000);
				boolean isUrlPresent = false;
				WebElement actUrl= driver().findElement(By.cssSelector(adURL));
				String actualUrl= actUrl.getAttribute("value");
				if (actualUrl.equalsIgnoreCase(expectedUrl)){
					System.out.println(actualUrl+ " is exactly matched");
					isUrlPresent=true;								
					break;
			}if(isUrlPresent==false){
				selectBackToAds();
				
			}
			
			}
			
		
		
		
	}	

	public void updateShopAndSubscriptionForShopping () throws Exception{
		logInfo("Edit the Ad and Updation of Shop visibility Selection and Subscription of Yearly and Monthly.");
		driver().navigate().refresh();		
		List <WebElement> del = driver().findElements(By.cssSelector(delAds));
		int dele = del.size();		
		for (int i =1; i <=  (dele-14)/2; i++){	
			WebElement edit = driver().findElement(By.xpath(editAds1+i+editAds2));
			edit.click();
			Thread.sleep(5000);
			selectShopVisibility();
			selectStatus(st1);			
			SubscriptionPlanSlection(subs1);						
			selectAddBanner();			
			confirmationMessage("Banner updated successfully.");
			Thread.sleep(5000);
			break;	
		}		
	}
	

	public void sortSelectSubcriptionPlans(String subscription) throws Exception{
		logInfo("Selecting Subscription - "+subscription);
		try{
			clickOnButton("cssSelector", sortSub);
			List <WebElement> market = driver().findElements(By.cssSelector(adSubcriptionsDrpdwn));
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(subscription)){
					markets.click();
					break;
					
				}
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Subscription");
		}
	}
	
	public void sortSelectAvaibleIn(String availblity) throws Exception{
		logInfo("Selecting Availability In - "+availblity);
		try{
			clickOnButton("cssSelector", sortAvail);
			List <WebElement> market = driver().findElements(By.cssSelector(adAvailableDrpdwn));
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(availblity)){
					markets.click();
					break;
					
				}
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Available");
		}
	}
	
	public void sortSelectShopCategory(String shpCat) throws Exception{
		logInfo("Selecting Shop Category - "+shpCat);
		try{
			clickOnButton("cssSelector", sortShop);
			List <WebElement> market = driver().findElements(By.cssSelector(adShopCatDrpdwn));		
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(shpCat)){
					markets.click();
					break;
					
				}
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Shop Category");
		}
	}
	
	public void sortSelectLanguages(String lang) throws Exception{
		logInfo("Selecting Languages - "+lang);
		try{
			clickOnButton("cssSelector", sortLang);
			List <WebElement> market = driver().findElements(By.cssSelector(adlanguageDrpdwn));
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(lang)){
					markets.click();
					break;				
				}
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Subscription");
		}
	}
	
	public void sortSelectRanker(String rank) throws Exception{
		logInfo("Selecting Rankers - "+rank);
		try{
			clickOnButton("cssSelector", sortRanks);
			List <WebElement> market = driver().findElements(By.cssSelector(adRankDrpdwn));		
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(rank)){
					markets.click();
					break;				
				}
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Rankers");
		}
	}
	
	public void sortSelectStatus(String status) throws Exception{
		logInfo("Selecting Status - "+status);
		try{
			clickOnButton("cssSelector", sortStatus);
			List <WebElement> market = driver().findElements(By.cssSelector(adStatusDrpdwn));		
			for (WebElement markets: market){
				if(markets.getText().equalsIgnoreCase(status)){
					markets.click();
					break;
					
				}
				
			}
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Status");
		}
	}
	
	public void CompanyAdswidgets() throws Exception{
		logInfo("Verify Company Ads is present and Drag into Home Page.");
		tm.selectEditWidgetsFromHome();			
		dragCompanyAds();		
		clickOnElement("cssSelector",closeWidgetInHomeBranch);	
	}

	public void editSettingOnAdswidgets() throws Exception{
		logInfo("Select the Setting on the top of Ads widget.");	
		tm.selectEditWidgetsFromHome();	
		selectAdsWidgetSettings();
	}

	public void selectAdsWidgetSettings() throws Exception{	
		clickOnElement("xpath", adsWidgetSettings);
	}
	 
	public void deleteConfirm() throws Exception{
		try{
			clickOnElement("cssSelector", delInfo);
		}
		catch (Exception e) 
		{
			logger.error("Failed!! unable to delete Task from confirmation Message.");					
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
	}
		
	public void dragCompanyAds() throws Exception
	{
		logInfo("Drag Company Ads from Edit Widgets Link ");
		Thread.sleep(3000);
		List <WebElement> ne = driver().findElements(By.xpath(companyAdsWidgets));
		System.out.println(ne.size());
		for (WebElement ns : ne){			
			System.out.println(ns.getText());			
			if (ns.getText().contains("Ads")){				
				Thread.sleep(2000);
				WebElement from = driver().findElement(By.xpath(companyAdsWidgets));
				WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));
				tm.dragAndDropAction(from, to);
				Thread.sleep(5000);
				break;
			}		
		}
	}
	
	public void verifyAdfromWidget(String url) throws InterruptedException, Exception{
		logInfo("Verifies total ads present in widget and opens each Ads to compares with URL.");			
		boolean isURLPresent= false;
		List <WebElement> im = driver().findElements(By.cssSelector(imageDot));	
		System.out.println("size of dots "+im.size());
		if(im.size() ==0 ){
			System.err.println("TestCase Failed!! Due to No ads are present.");
			Assert.assertEquals(null, url);		
			}else if(im.size()!=1 ) {		
				for (int i=1 ; i <=im.size(); i++ ){
				Thread.sleep(4000);
				WebElement imzs = driver().findElement(By.cssSelector(imz1+i+imz2));
				imzs.click();
				Thread.sleep(1000);	
				WebElement img = driver().findElement(By.cssSelector(adswidgetInMaster));
				String actualUrl = img.getAttribute("href");				
				System.out.println("all Url are "+ actualUrl );
				Thread.sleep(3000);
				if (actualUrl.equalsIgnoreCase(url)){
					isURLPresent = true;
					System.out.println("Expected Url is "+ actualUrl );
					break;					
						}}
						if (isURLPresent==false){
						Assert.assertTrue(isURLPresent, url + " - url is not present");	
				}	
		 
		} else {
			boolean isURLpresent2 = false;
			WebElement img2 = driver().findElement(By.cssSelector(adswidgetInBranch));
			String actualUrl2 = img2.getAttribute("href");
			System.out.println("single  Url are "+ actualUrl2 );
			if (actualUrl2.equalsIgnoreCase(url)){
				isURLpresent2 = true;
				System.out.println("2nd  - Expected Url is "+ actualUrl2 );							
					} if (isURLpresent2==false){
						Assert.assertTrue(isURLpresent2, url + " 2nd - url is not present");
					}
				}		
   }
	
	
	public void singleAd( String url){
		
		boolean isURLpresent2 = false;
		WebElement img2 = driver().findElement(By.cssSelector(adswidgetInBranch));
		String actualUrl2 = img2.getAttribute("href");
		if (actualUrl2.equalsIgnoreCase(url)){
			isURLpresent2 = true;
			System.out.println("2nd  - Expected Url is "+ actualUrl2 );							
				}							
		if (isURLpresent2==false){
		Assert.assertTrue(isURLpresent2, url + " 2nd - url is not present");
		}
		
	}
	
	
	
	public void AdNotToBePresentInWidget(String url) throws InterruptedException, Exception{
		logInfo("Tries to verify Ads which should not be present in Ads Widget.");		
		boolean isURLPresent= true;
		List <WebElement> im = driver().findElements(By.cssSelector(imageDot));	
		System.out.println("size of dots "+im.size());
		if(im.size() ==0 ){
			System.out.println("TestCase passed!! Due to No ads are present.");				
			}else if(im.size()!=1 ) {		
				for (int i=1 ; i <=im.size(); i++ ){
				Thread.sleep(4000);
				WebElement imzs = driver().findElement(By.cssSelector(imz1+i+imz2));
				imzs.click();
				Thread.sleep(1000);	
				WebElement img = driver().findElement(By.cssSelector(adswidgetInBranch));
				String actualUrl = img.getAttribute("href");				
				System.out.println("all Url are "+ actualUrl );
				Thread.sleep(3000);
				if (actualUrl.equalsIgnoreCase(url)){
					isURLPresent = false;
					System.out.println("UnExpected Url is "+ actualUrl );
					Assert.assertTrue(isURLPresent, url + " - url is still present");	
					break;					
						}}
						if (isURLPresent==true){
							System.out.println(url+ " url is not present");
						
				}	
		 
		} else {
			boolean isURLpresent2 = true;
			WebElement img2 = driver().findElement(By.cssSelector(adswidgetInBranch));
			String actualUrl2 = img2.getAttribute("href");
			System.out.println("single  Url are "+ actualUrl2 );
			if (actualUrl2.equalsIgnoreCase(url)){
				isURLpresent2 = false;
				System.out.println("2nd  - Expected Url is "+ actualUrl2 );	
				Assert.assertTrue(isURLpresent2, url + " 2nd - url is present");
					} if (isURLpresent2==true){
						System.out.println("Url is not present");
					}
				}		
   }
	
	public void verifyAdfromWidgetAtShop(String url) throws InterruptedException, Exception{
		logInfo("Verifies total ads present in widget and opens each Ads to compares with URL.");			
		String imz1 = "//*[@id='carousel-ads']/ol /li[";
		String imz2 = "]";
		boolean isURLPresent= false;
		List <WebElement> im = driver().findElements(By.cssSelector(imageDot));	
		System.out.println("size of dots "+im.size());
		if(im.size() ==0 ){
			System.err.println("TestCase Failed!! Due to No ads are present.");
			Assert.assertEquals(null, url);		
			}else if(im.size()!=1 ) {		
				for (int i=1 ; i <=im.size(); i++ ){
				Thread.sleep(4000);
				WebElement imzs = driver().findElement(By.xpath(imz1+i+imz2));
				imzs.click();
				Thread.sleep(1000);	
				WebElement img = driver().findElement(By.cssSelector(adsWidgetinShop));
				String actualUrl = img.getAttribute("href");				
				System.out.println("all Url are "+ actualUrl );
				Thread.sleep(3000);
				if (actualUrl.equalsIgnoreCase(url)){
					isURLPresent = true;
					System.out.println("Expected Url is "+ actualUrl );
					break;					
						}}
						if (isURLPresent==false){
						Assert.assertTrue(isURLPresent, url + " - url is not present");	
				}	
		 
		} else {
			boolean isURLpresent2 = false;
			WebElement img2 = driver().findElement(By.cssSelector(adsWidgetinShop));
			String actualUrl2 = img2.getAttribute("href");
			System.out.println("single  Url are "+ actualUrl2 );
			if (actualUrl2.equalsIgnoreCase(url)){
				isURLpresent2 = true;
				System.out.println("2nd  - Expected Url is "+ actualUrl2 );							
					} if (isURLpresent2==false){
						Assert.assertTrue(isURLpresent2, url + " 2nd - url is not present");
					}
				}		
   }
	

	
	public void AdNotToBePresentInWidgetAtShop(String url) throws InterruptedException, Exception{
		logInfo("Tries to verify Ads which should not be present in Ads Widget.");		
		boolean isURLPresent= true;
		String imz1 = "//*[@id='carousel-ads']/ol /li[";
		String imz2 = "]";
		List <WebElement> im = driver().findElements(By.xpath(imageDots));	
		System.out.println("size of dots "+im.size());
		if(im.size() ==0 ){
			System.out.println("TestCase passed!! Due to No ads are present.");				
			}else if(im.size()!=1 ) {		
				for (int i=1 ; i <=im.size(); i++ ){				
				WebElement imzs = driver().findElement(By.xpath(imz1+i+imz2));
				imzs.click();
				WebElement img = driver().findElement(By.cssSelector(adsWidgetinShop));
				String actualUrl = img.getAttribute("href");
				System.out.println("all Url are "+ actualUrl );
				if (actualUrl.equalsIgnoreCase(url)){
					isURLPresent = false;
					System.out.println("UnExpected Url is "+ actualUrl );
					Assert.assertTrue(isURLPresent, url + " - url is still present");	
					break;					
						}}
						if (isURLPresent==true){
							System.out.println(url+ " url is not present");
						
				}	
		 
		} else {
			boolean isURLpresent2 = true;
			WebElement img2 = driver().findElement(By.cssSelector(adsWidgetinShop));
			String actualUrl2 = img2.getAttribute("href");
			System.out.println("single  Url are "+ actualUrl2 );
			if (actualUrl2.equalsIgnoreCase(url)){
				isURLpresent2 = false;
				System.out.println("2nd  - Expected Url is "+ actualUrl2 );	
				Assert.assertTrue(isURLpresent2, url + " 2nd - url is still present");
					} if (isURLpresent2==true){
						System.out.println("Url is not present");
					}
				}		
   }
	
	
	
	

	public void verifyAdAtShooping(String url) throws InterruptedException, Exception{
		logInfo("Verifies total ads present in Shopping and opens each Ads to compares with URL.");	
		List <WebElement> im = driver().findElements(By.cssSelector(imageDotAtShopping));
		System.out.println(im.size());	
		if(im.size() ==0 ){
			System.err.println("TestCase Failed!! Due to No ads are present.");
			Assert.assertEquals(null, url);
		}
		else if(im.size()!=1 ){	
			for (int i=1 ; i <=im.size(); i++ ){
				WebElement imzs = driver().findElement(By.cssSelector(imzShop1+i+imz2));
				imzs.click();
				Thread.sleep(3000);		
				Actions act = new Actions (driver()) ;
				if(driver().getCurrentUrl().contains("branch")){
					WebElement img = driver().findElement(By.cssSelector(adswidgetInBranch));
					act.contextClick(img).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(5000);
					}
				else{
					WebElement img = driver().findElement(By.cssSelector(adswidgetInMaster));
					act.contextClick(img).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(5000);
				}
				
				String winHandleBefore = driver().getWindowHandle();
				for(String winHandle : driver().getWindowHandles()){
					driver().switchTo().window(winHandle);
					String curUrl = driver().getCurrentUrl();		
					if (!curUrl.equalsIgnoreCase(url)){	
						Assert.assertNotEquals(curUrl, url);
						Thread.sleep(6000);			
						driver().switchTo().window(winHandleBefore);	
						Thread.sleep(6000);			
					}
					else{
						System.out.println("Succesfully !!Url matched found.");	
						Assert.assertEquals(curUrl, url);
						driver().switchTo().window(winHandleBefore);	
						break;
					}
				}
			}
			}else{
				Actions act = new Actions (driver()) ;
				if(driver().getCurrentUrl().contains("branch")){
					WebElement img = driver().findElement(By.cssSelector(adswidgetInBranch));
					act.contextClick(img).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(5000);
				}
				else {
					WebElement img = driver().findElement(By.cssSelector(adswidgetInMaster));
					act.contextClick(img).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(5000);
				}
				String winHandleBefore = driver().getWindowHandle();
				for(String winHandle : driver().getWindowHandles()){
				 driver().switchTo().window(winHandle);
				 String curUrl = driver().getCurrentUrl();		
				 if (!curUrl.equalsIgnoreCase(url)){		
						Assert.assertNotEquals(curUrl, url);
						Thread.sleep(6000);			
						driver().switchTo().window(winHandleBefore);	
						Thread.sleep(6000);	
				 	} else {
							System.out.println("Succesfully !!Url matched found.");
							Assert.assertEquals(curUrl, url);
							driver().switchTo().window(winHandleBefore);	
							break;
							}	
				 		}
				}
	}


	public void AdsToBePresentAtShopping() throws InterruptedException, Exception{
		logger.info("Validation Ads by selecting Yes for Turn on Shop Banner .\n"
				+ "Tries to verify Ads which should be avaiable in Shopping page.");	
		List <WebElement> im = driver().findElements(By.cssSelector(imageDotAtShopping));
		System.out.println("get size : "+im.size());
		try{
			if(im.size() ==0 ){
				try{
				Assert.assertEquals(im.size(), im.size() ==0);
				} catch(WebDriverException we){
					logger.error("Not able to capture the size ");
					
				}
	
			}
			else{	
				System.out.println("Succesfully !! Ads are avaiable in Shopping page");
			}
		}catch(Exception e){		
			System.err.println("Not able to capture the Ads size.");
		}
		
			}


	public void AdNotToBePresentInShopping(String url) throws InterruptedException, Exception{
		logInfo("Tries to verify Ads which should not be Present in Shopping.");	
		List <WebElement> im = driver().findElements(By.cssSelector(imageDotAtShopping));
		
		if(im.size() ==0 ){
			System.out.println("Successfully!! No Ad is present ");
			Assert.assertEquals(null, url);
			
		}else if(im.size()!=1 ) {	
		for (int i=1 ; i <=im.size(); i++ ){
			WebElement imzs = driver().findElement(By.cssSelector(imzShop1+i+imz2));
			imzs.click();
			Thread.sleep(3000);			
		Actions act = new Actions (driver()) ;
		if(driver().getCurrentUrl().contains("branch")){
			WebElement img = driver().findElement(By.cssSelector(adswidgetInBranch));
			act.contextClick(img).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(5000);
		}else {
			WebElement img = driver().findElement(By.cssSelector(adswidgetInMaster));
			act.contextClick(img).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(5000);
					}
		String winHandleBefore = driver().getWindowHandle();
		for(String winHandle : driver().getWindowHandles()){
		    driver().switchTo().window(winHandle);
		    String curUrl = driver().getCurrentUrl();		
			if (!curUrl.equalsIgnoreCase(url)){			
				Thread.sleep(6000);			
				System.out.println("Succesfully !! This ads is not present screen.\n");
				Assert.assertNotEquals(curUrl, url);	
				implicityWaits(6);			
				driver().switchTo().window(winHandleBefore);	
				Thread.sleep(6000);	
				break;
			} else {
				Thread.sleep(5000);
				System.err.println("TestCase Failed, due to still Ad is present, "+url);
				Assert.assertNotEquals(curUrl, url);						
				Thread.sleep(6000);	
				implicityWaits(10);
				driver().switchTo().window(winHandleBefore);
							  }
							}
		                 }
			} else {
				Actions act = new Actions (driver()) ;
				if(driver().getCurrentUrl().contains("branch")){
					WebElement img = driver().findElement(By.cssSelector(adswidgetInBranch));
					act.contextClick(img).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(5000);
				}else {
					WebElement img = driver().findElement(By.cssSelector(adswidgetInMaster));
					act.contextClick(img).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
					Thread.sleep(5000);
							}
				String winHandleBefore = driver().getWindowHandle();		
				for(String winHandle : driver().getWindowHandles()){
				    driver().switchTo().window(winHandle);			    
				    String curUrl = driver().getCurrentUrl();					   
					if (!curUrl.equals(url)){			
						Thread.sleep(6000);			
						System.out.println("Succesfully !! This ads is not present in screen.");
						Assert.assertNotEquals(curUrl, url);
						Thread.sleep(6000);					
						driver().switchTo().window(winHandleBefore);	
						Thread.sleep(6000);
							
						
					} else {
						System.err.println("TestCase Failed, due to still Ad is present, "+curUrl);
						Assert.assertNotEquals(curUrl, url);
						Thread.sleep(6000);
						implicityWaits(6);
						driver().switchTo().window(winHandleBefore);	
						break;
					
					       }	
					}
				
			}
       }



	public void NoAdsToBePresentAtShopping() throws InterruptedException, Exception{
		logInfo("Validation Ads by selecting Yes for Turn on Shop Banner. \n"
				+ "Tries to verify Ads which should not be avaiable in Shopping page.");	
		List <WebElement> im = driver().findElements(By.cssSelector(imageDotAtShopping));
		System.out.println("get size : "+im.size());
		try{
			if(im.size() ==0 ){
				
				System.out.println("Successfully!! No Ad is present ");
		
			}else {
				try{
					Assert.assertEquals(im.size(), im.size() ==0);
				} catch(WebDriverException we){
					logger.error("Not able to handle Ads ");
				}
				
			}
		} catch(Exception e){		
			System.err.println("Not able to capture the Ads size.");
		}		
	}

		public void navNextImg () throws InterruptedException{
			logInfo("Select Next symbol on photo image at ads widget. ");
			WebElement nextImage = driver().findElement(By.cssSelector(adsImgNext));
			
			if(nextImage.isEnabled()){
				nextImage.click();
				Thread.sleep(10000);
			}			
		}
		
		public void getCountofAdImages () throws InterruptedException{
			List <WebElement> im = driver().findElements(By.cssSelector("div#widget-ads > div > div > ol > li"));
			System.out.println(im.size());			
		}
		
	 	public void selectProductsLink () throws Exception, InterruptedException{
	 		clickOnLink("linkText", "Products");
	 		Thread.sleep(5000);
	 		/*clickOnElement("cssSelector", shopAdsCarousel);*/	 		
	 	}
	
	 	public void selectNoShopBanner() throws Exception{
			logInfo("Select No option from Turn on Shop Banner");
			try{		
				clickOnButton("cssSelector", sortBanner);	
				
				Robot rb = new Robot();
				rb.delay(5000);
				rb.keyPress(KeyEvent.VK_DOWN);
				rb.keyRelease(KeyEvent.VK_DOWN);
				rb.keyPress(KeyEvent.VK_DOWN);
				rb.keyRelease(KeyEvent.VK_DOWN);
				rb.keyPress(KeyEvent.VK_ENTER);;		
				rb.keyRelease(KeyEvent.VK_ENTER);		
			}
			catch (WebDriverException we ){	
				logger.error("Failed!! Not able to select Shop Banner");	
			}			
		}
 	 
 	public void selectYesShopBanner() throws Exception{
		logInfo("Select Yes option from Turn on Shop Banner");
		try{		
			clickOnButton("cssSelector", sortBanner);	
			
			Robot rb = new Robot();
			rb.delay(5000);
			rb.keyPress(KeyEvent.VK_UP);
			rb.keyRelease(KeyEvent.VK_UP);
			rb.keyPress(KeyEvent.VK_UP);
			rb.keyRelease(KeyEvent.VK_UP);
			rb.keyPress(KeyEvent.VK_ENTER);;		
			rb.keyRelease(KeyEvent.VK_ENTER);		
		}catch (WebDriverException we ){	
			logger.error("Failed!! Not able to select Shop Banner");	
		}
	   }
 	
 	
 	public void valStatus() throws Exception, InterruptedException{
 		logInfo("Validate all options of Status in Ads creations page" );
		
		try{
			WebElement sts = driver().findElement(By.cssSelector(adStatusDropdown));
			List <WebElement> st = driver().findElements(By.cssSelector(adStatusDd));
			System.out.println("status size is "+ st.size());
			for (WebElement st1 : st){
				Assert.assertTrue(isPublishedStatusPresent());
				Assert.assertTrue(isDraftStatusPresent());
				Assert.assertTrue(isArchivedStatusPresent());
				System.out.println("Successfully verifed all Status");
			}

		}catch (WebDriverException we ){			
			logger.error("Failed!! Not able to select Status.");
		}				
	}
 	
 	
 	public void backToOffice() throws Exception, InterruptedException{
		Thread.sleep(5000);
		clickOnLink("linkText", "back to office");		
	}
 	
 	public boolean isPublishedStatusPresent(){    	  
  		return getpublishText().matches("Published");
 	}		
 	
 	public boolean isDraftStatusPresent(){    	  
  		return getDraftText().matches("Draft");
 	}	
 	
	public boolean isArchivedStatusPresent(){    	  
  		return getArchivedText().matches("Archived");
	}	
	
	public String getpublishText (){			
		return driver().findElement(By.cssSelector(adStatusDdp)).getText();			
	}
	public String getDraftText (){			
		return driver().findElement(By.cssSelector(adStatusDdD)).getText();			
	}
	public String getArchivedText (){			
		return driver().findElement(By.cssSelector(adStatusDdA)).getText();
	}
	
	
	public void validateAlertMessages() throws Exception{
		String urlText = "Please enter valid URL (E.G: http://www.google.com)";
		selectAddBanner();
		WebElement upload = driver().findElement(By.cssSelector(imgAlert));
		System.out.println("aert message is "+upload.getText());		
		Assert.assertEquals(upload.getText(), imgAlertText);
		uploadEventImages();
		enterUrl(Eventtype1);	
		selectAddBanner();
		confirmationMessage(urlText);
		Thread.sleep(4000);		
		enterUrl(urlAds1);
		clickOnElement("cssSelector", adMarketLang);
		WebElement all = driver().findElement(By.cssSelector("#market_all"));
			if(all.isSelected()){
				all.click();				
				Thread.sleep(2000);
				clickOnElement("cssSelector", adMarketLang);
								}	
		selectAddBanner();
		confirmationMessage(adsMarkText);
		Thread.sleep(5000);
		marketLanguages(language1);
		WebElement OV = driver().findElement(By.cssSelector(adOfficeVisibilityChkBox));	
		if(OV.isSelected()){
			OV.click();
			Thread.sleep(5000);
							}
		selectAddBanner();	
		confirmationMessage("Please selet atleast one visibilty");
			
	}
	
	
	public void modifyMarketLangForExistingAd(String expectedUrl, String lang ) throws Exception{
		navigateConfigureAds();
		editExistingAds(expectedUrl);
		clickOnElement("cssSelector", adMarketLang);
		WebElement all = driver().findElement(By.cssSelector("#market_all"));			
		all.click();			
		Thread.sleep(2000);
		clickOnElement("cssSelector", adMarketLang);
		Thread.sleep(2000);
		marketLanguages(lang);
		selectAddBanner();
		confirmationMessage("Banner updated successfully.");	
		
		}	
	
	
	// Jayadev
	
	
		public void go2BannersPage(){
			logInfo("inside go2BannersPage() method.");
			driver().navigate().to(appUrl+"pyr_core/banners");
			
		}
		
		public void createNewAdd(String adUrl, String adStatus) throws Exception{
			logInfo("inside createNewAdd() method.");
			
			go2BannersPage();
			// waitOnElement("linkText","Add new Ad");
			clickOnLink("linkText","Add New Ad");
			
			Thread.sleep(3000);
			uploadEventImages();
			Thread.sleep(5000);
			
			inputTextClear("cssSelector","#pyr_core_banner_url");
			inputText("cssSelector","#pyr_core_banner_url", adUrl);
			
			WebElement eAvlInOffice = driver().findElement(By.cssSelector("#pyr_core_banner_office_visibility"));
			eAvlInOffice.click();
			
			
			WebElement eAvlInShop = driver().findElement(By.cssSelector("#pyr_core_banner_shop_visibility"));
			eAvlInShop.click();
			
			WebElement eTaxons = driver().findElement(By.xpath("//*[@id='new_pyr_core_banner']/div[3]/div[2]/div[3]/div[1]"));
			eTaxons.click();
			
			WebElement eRankDefs = driver().findElement(By.xpath("//*[@id='access_profile_settings']/div[1]/div[1]"));
			eRankDefs.click();
			
			WebElement eMarketLangs = driver().findElement(By.xpath("//*[@id='access_profile_settings']/div[2]/div[1]"));
			eMarketLangs.click();
			
			WebElement eSubscriptionPlan = driver().findElement(By.xpath("//*[@id='access_profile_settings']/div[3]/div[1]"));
			eSubscriptionPlan.click();
			
			clickOnElement("xpath","//input[@value='Add Banner']");
			
			Thread.sleep(5000);
		}
	
}