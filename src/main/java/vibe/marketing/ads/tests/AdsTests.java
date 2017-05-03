package vibe.marketing.ads.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLService;
import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.billing.tests.BillingMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.shopping.tests.ShoppingMethods;
import vibe.users.tests.MonatUsersMethods;
import vibe.widgets.tests.WidgetsMethods;

@Priority(17)
public class AdsTests extends AdsMethods{
	
	ShoppingMethods sm = new ShoppingMethods();
	BillingMethods bm = new BillingMethods();

	WidgetsMethods wm= new WidgetsMethods();
	readProp prop = new readProp();
	
	
	// Creates ad with subscription, lang And verifies in office ads widget and then finally deletes
	@Test(priority = 1701)
	public void ads_CreateNewAds() throws Exception{
		logInfo("Entered into ads_CreateNewAds() Test");		
		String[] subscripList = null;
		 if ((driver().getCurrentUrl().contains("monat"))){
			 subscripList = subscripListOfMonat;
		 	}else if((driver().getCurrentUrl().contains("tupperware"))){
		 		subscripList = subProMonthlyTupper;
		 	}else{
		 		subscripList = subscripListOfVibe;
		 	}
		
		
		adsCreation(urlAds1, ranker5, language1, st1,subscripList);	  // change subscription based on admins		
		
		if ((driver().getCurrentUrl().contains("master"))||
		   (driver().getCurrentUrl().contains("release"))||
		   (driver().getCurrentUrl().contains("tupperware"))){
			selectPublishImmd();
				}		
		selectOfficeVisibility();
	 	selectShopVisibility();
	 	selectAddBanner();				
		back2Office();
		/*loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));*/		
		//wm.deleteAllWidgets();		
		CompanyAdswidgets();		
	    verifyAdfromWidget(urlAds1);
	    //AdNotToBePresentInWidget(urlAds1);
	   // userLogout();	
	    back2Office();
	    navigateConfigureAds();
	    deleteAd(urlAds1); 	   
	 }
	
	
	// Creates ad with subscription, lang And verifies in office ads widget and then finally deletes
		@Test(priority = 1702)
		public void ads_validateFutureAds() throws Exception{
			logInfo("Entered into ads_CreateNewAds() Test");				
			String[] subscripList = null;
			 if ((driver().getCurrentUrl().contains("monat"))){
				 subscripList = subscripListOfMonat;
			 	}else if((driver().getCurrentUrl().contains("tupperware"))){
			 		subscripList = subProMonthlyTupper;
			 	}else{
			 		subscripList = subscripListOfVibe;
			 	}
			
			adsCreation(urlAds6, ranker5, language1, st1,subscripList);	  // change sub scription based on admins		
			enterPublishDate(5);
			selectOfficeVisibility();
		 	selectShopVisibility();
		 	selectAddBanner();				
			back2Office();
		    navigateConfigureAds();		  
		    boolean ispresent = validateAdsUrl(urlAds6);
		    if (ispresent==true){		    	
		    	validateField();
		    	
		    }else {
		    	Assert.assertTrue(ispresent, urlAds6+ " -url is not present" );
		    }
		}
	
	@Test(priority = 1750)		
	public void Ads_DeleteAllAds() throws Exception{
		logInfo("*** Delete all Ads***"); 
	   navigateConfigureAds(); 		  
	   deleteAllAds();
	  
	}
	
	@Test(priority = 1703)
	public void ads_Sorting() throws Exception{		
		 navigateConfigureAds();
		 getAllMarketCountries();
		 getAllSubcriptionPlans();
		 getAllAvailablity();
		 getAllLanguages();
		 getAllRankers();
		 getAllShopCategory();
		 getAllStatus();	
		 sortSelectMarket("US");
		 sortSelectSubcriptionPlans("Vibe Pro - Monthly");
		 sortSelectAvaibleIn("Office");
		 sortSelectShopCategory("VIBE");
		 sortSelectLanguages("English");
		 sortSelectRanker("Manager");
		 sortSelectStatus("Draft");
		 backToOffice();		
	}
	
	@Test(priority = 1704)
	public void ads_VerifyAdsInCompanyAdsWidget() throws Exception{
		
		/* logOut();
		  
		  login.masDist3();
		   CompanyAdswidgets()
		     .adsWidgetClicked()
		  
		  
		  ;
		
		*/
		
	}
	
	@Test(priority = 1705)
	public void ads_SortAdsfromAdsWidget() throws Exception{
	   CompanyAdswidgets();
	   editSettingOnAdswidgets();
	   /*   logOut();     */
	}
	
	@Test(priority = 1706)
	public void ads_AdsvalidationOfMonthlySubption() throws Exception{
		logInfo("Create Ads with Office Visibility and verify at Sorting at Ads main page.\n"
				+ "Login with different distibutors of Monthly and Yearly subscrptions. \n"
				+ "Ads should display for monthly subscriber , And not to display for Yearly subscriber");	
		
		//String urlAds = "http://yevo.vibeofficestage.com/29";
		
	      adsCreation(urlAds2, ranker3, language1, st1,subProMonthly);		 
	      selectOfficeVisibility();		 	
	 	  selectAddBanner();
	 	  confirmationMessage("Banner created successfully.");
	 	  //nm.modifyUsersSubscription(VibeProMonthlyUser, txtSubPlanProMonthly);	 
	 	  nm.loginAsSubUser(VibeProMonthlyUser);	 	
	      CompanyAdswidgets();				
		  verifyAdfromWidget(urlAds2);	
		  clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);	
		  //nm.modifyUsersSubscription(vibeProYearlyUser, subProYearly);	 
		  nm.loginAsSubUser(vibeProYearlyUser);		  
		  CompanyAdswidgets();			
	      AdNotToBePresentInWidget(urlAds2);
	      clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);	
	}
	
	@Test(priority = 1707)
	public void ads_AdsvalidationforAllSubscriptions() throws Exception{
		 logInfo("Create Ads with Office Visibility and verify at Sorting at Ads main page.\n"
				+ "Login with different distibutors of Monthly and Yearly subscrptions. \n"
				+ "Ads should display for monthly as well as Yearly subscribers");
		 adsCreation(urlAds3, ranker3, language1, st1,subProMonthly);
		 SubscriptionPlanSlection(subProYear);
		 defineRank(ranker4);				 
		 selectOfficeVisibility();		 	
		 selectAddBanner();
		 confirmationMessage("Banner created successfully.");
		 backToOffice();		     
		 navigateConfigureAds();
		 sortSelectRanker(ranker3);
	     sortSelectLanguages(language1);
	     sortSelectSubcriptionPlans(txtSubPlanProMonthly);
	     sortSelectAvaibleIn(avail1);		  
	     validateAdsbyItsUrl(urlAds3);
	     nm.loginAsSubUser(VibeProMonthlyUser);	 	
	      CompanyAdswidgets();				
		  verifyAdfromWidget(urlAds3);	
		  clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);	
		  nm.loginAsSubUser(vibeProYearlyUser);		  
		  CompanyAdswidgets();			
		  verifyAdfromWidget(urlAds3);	
	      clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);	
	}
	
	@Test(priority = 1708)
	public void ads_ShopAdsvalidation() throws Exception{		
		logOut();		  
		logIn(adminUser_text,adminPwd_text);	
		CompanyAdswidgets();
		editSettingOnAdswidgets();
	}
	
	
	//****************************AdsValidationOfSubsTest tests*****************************************//
	
	
	@Test (priority=1709)
	public void ads_AdsValidationOfYearlySubscription() throws Exception{
		  logInfo("Create Ads with Office Visibility and verify at Sorting at Ads main page.\n"
				+ "Login with different distributors of Monthly and Yearly subscriptions. \n"
				+ "Ads should display for Yearly subscriber , And not to display for monthly subscriber");
		//String urlAds5 = "http://master.vibeoffice.com/110";
		  
		  adsCreation(urlAds5, ranker4, language1, st1,subProYear);		 
		  selectOfficeVisibility();		 	
		  selectAddBanner();
		  confirmationMessage("Banner created successfully.");
		  backToOffice();
		  nm.loginAsSubUser(VibeProMonthlyUser);	 	
	      CompanyAdswidgets();				
	      AdNotToBePresentInWidget(urlAds5);	
		  clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);	
		  nm.loginAsSubUser(vibeProYearlyUser);		  
		  CompanyAdswidgets();			
		  verifyAdfromWidget(urlAds5);	
	      clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);	   	
	}
	
	
	@Test (priority=1710)
	public void ads_ValidationStatus() throws Exception{
		logInfo("Validate the status in New Ad creation page ");		 
		navigateConfigureAds();
	    selectAddNewAd();		
		valStatus();
		back2Office();
	}
	
	@Test (priority=1711)
	public void ads_ValidationUploadingImage() throws Exception{
		logInfo("Validate the mandatory fields in New Ad creation page ");
		navigateConfigureAds();
		selectAddNewAd();	
		selectAddBanner();
		confirmationMessage("Please select at least one market.");
		marketLanguages(language1);
		selectAddBanner();
		alertMessage();
		uploadImage();
		selectAddBanner();
		confirmationMessage("Banner created successfully.");	   	
		back2Office();	 
	}
	
	
	@Test (priority=1712)
	public void ads_ValidateAdsBasedOnMarket() throws Exception{
		  logInfo("Create Ad based on Market Country and verify Ad valid country user and non valid user also.");
		 // String  urlAds2 = "http://master.vibeoffice.com/3";
		  
		  adsCreation(urlAds6, ranker3, language1, st1,subProMonthly);		 
	      selectOfficeVisibility();		 	
	 	  selectAddBanner();
	 	  confirmationMessage("Banner created successfully.");		  
		  nm.loginAsSubUser(VibeProMonthlyUser);	 	

	      CompanyAdswidgets();				
		  verifyAdfromWidget(urlAds6);	
		  clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(5000);
		  back2Office();
		  modifyMarketLangForExistingAd(urlAds6, language2);
		  back2Office();
		  nm.loginAsSubUser(VibeProMonthlyUser);
		  AdNotToBePresentInWidget(urlAds6);
		  clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);	
		  back2Office();	  
		
	}
	
	
		@Test (priority=1713)
		public void ads_ValidateAdsAlert() throws Exception{		
		navigateConfigureAds();
		selectAddNewAd();	
		validateAlertMessages();
		}
	
	
	//****************************************ShopAdsValidationTest****************************************//
		
		@Test (priority=1714)
		public void ads_setNoShopBannerAndNoAdsInShopping() throws Exception{
			 logInfo("Login with Admin and Turn off Shop Banner. \n"
					+ "Verify the Ads in shopping modules which should not have any  \n"
					+ "Expected message is 'Successfully!! No Ad is present'");
			 navigateConfigureAds();
			 selectNoShopBanner();
			 confirmationMessage("Shop Banner settings successfully updated.");
			 back2Office();
			 sm.navigatePlaceOrder();		  			
			 AdNotToBePresentInWidget(urlAds5);
			 Thread.sleep(3000);
			 clickOnLink("cssSelector", logoutHere);
			  Thread.sleep(2000);	
			 
			 nm.loginAsSubUser(VibeProMonthlyUser);	 	
			  sm.navigatePlaceOrder();		   			
			  AdNotToBePresentInWidget(urlAds2);
			  Thread.sleep(3000);
			  clickOnLink("cssSelector", logoutHere);
			  Thread.sleep(2000);	
			 
			 
		}
		
		
		@Test (priority=1715)
		public void ads_setYesShopBannerAndDisplayAdsInShopping() throws Exception{
			 logInfo("Login with Admin and Turn on Shop Banner. \n"
					+ "Verify the Ads in shopping modules which should have Ads");	
			 	 
			 navigateConfigureAds();
			 updateShopAndSubscriptionForShopping ();
			 selectYesShopBanner();		         
			 back2Office();		 
			 nm.loginAsSubUser(vibeProYearlyUser);		  
			  sm.navigatePlaceOrder();		 		
			  verifyAdfromWidgetAtShop(urlAds5);	
		      clickOnLink("cssSelector", logoutHere);
			  Thread.sleep(2000);	
			 
		}
	
	@Test (priority=1716)
	public void ads_ShopAdsvalidationOfYearlySubscription() throws Exception{
		  logInfo("Create Ads with Shop Visibility and Login with different distibutors of Monthly and Yearly subscrptions. \n"
				+ "Ads should display for Yearly subscriber , And not to display for monthly subscriber");
		  //String urlAds5 = "http://master.vibeoffice.com/114";
		  navigateConfigureAds();
		  editExistingAds(urlAds5);		  
		  selectShopVisibility();
		  selectAddBanner();
		  confirmationMessage("Banner updated successfully.");			  
		  nm.loginAsSubUser(vibeProYearlyUser);		  

		  sm.navigatePlaceOrder();	
		  verifyAdfromWidgetAtShop(urlAds5);	
	      clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);	 
		  nm.loginAsSubUser(VibeProMonthlyUser);	 	
		  sm.navigatePlaceOrder();
		  selectProductsLink();				
	      AdNotToBePresentInWidget(urlAds5);	
	      Thread.sleep(3000);
		  clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);	
			
		 
	}
	
	@Test (priority=1717)
	public void ads_ShopAdsvalidationOfMonthlySubscription() throws Exception{
		logInfo("Create Ads with Shop Visibility and Login with different distibutors of Monthly and Yearly subscrptions. \n"
				+ "Ads should display for Monthly subscriber at shopping page, And not to display for yearly subscriber");
			
		  navigateConfigureAds();
		  editExistingAds(urlAds2);		  
		  selectShopVisibility();
		  selectAddBanner();
		  confirmationMessage("Banner updated successfully.");	
		  back2Office();
		  nm.loginAsSubUser(VibeProMonthlyUser);	 	
		  sm.navigatePlaceOrder();		   		
		  verifyAdfromWidgetAtShop(urlAds2);
		  clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);	
		  nm.loginAsSubUser(vibeProYearlyUser);		  
		  sm.navigatePlaceOrder();		
		  AdNotToBePresentInWidget(urlAds2);	
		  Thread.sleep(3000);
		  clickOnLink("cssSelector", logoutHere);
		  Thread.sleep(2000);		
	}
	
	
	
}
