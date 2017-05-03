package vibe.enrollment.tests;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import common.Priority;
import common.TestBase;


import org.testng.Assert;

import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.users.tests.UsersMethods;

// Enrollment for branch-1-10.vibeoffice.com environment.


@Priority(32)
public class Monat_EnrollTests extends Monat_EnrollMethods {
	Logger logger = Logger.getLogger(Monat_EnrollTests.class);
	
	 String market = "United States";
	    
	
	 String profileName = "Profile" + TestBase.generateRandomNumberInRange(1, 999); 
		 
	 // Enrollment @ Admin 
	
	/*
	 @Test(priority = 2301)
	 public void createEnrollmentProfile() throws Exception{
		 logInfo("inside createEnrollmentProfile Test");
		 
		 go2EnrollmentProfilePage();
	
		 addNewProfile(profileName, "icentris");
		 boolean isProfileCreated = verifyEnrollmentProfileIsPresent(profileName);
		 if(isProfileCreated){
			 logInfo(profileName + " profile found in enrollment profile page.");
			 editEnrollmentProfile(profileName);
			 addPage("Personal Details", "Page");
			 clickOnElement("xpath","//*[@class='simple_form edit_pyr_core_enrollment_profile']/div[4]/input");
			 editEnrollmentProfile(profileName);
			 addPage("Login Details", "Page");
			 clickOnElement("xpath","//*[@class='simple_form edit_pyr_core_enrollment_profile']/div[4]/input");
			 editEnrollmentProfile(profileName);
			 addReviewPage("Final Review", "Page");
			 clickOnElement("xpath","//*[@class='simple_form edit_pyr_core_enrollment_profile']/div[4]/input");
			 
		 } else {
			 logInfo(profileName + " profile could not be created in enrollment profile page.");
			 Assert.assertTrue(isProfileCreated, profileName + " profile could not be created in Enrollment profile page.");
		 }
	 }
	 
	 
	 
	 @Test(priority = 2302)
	 public void addFields2EnrollmentProfile() throws Exception{
		 logInfo("inside addFields2EnrollmentProfile Test");
		
		 go2EnrollmentProfilePage();
		 boolean isProfileCreated = verifyEnrollmentProfileIsPresent(profileName);
		 if(isProfileCreated){
			 logInfo(profileName + " profile found in enrollment profile page.");
			 editEnrollmentProfile(profileName);
			 
			 addFields2Page("Personal Details", "Select", enrollPersonalDetailsFields);
			 addRequiredFields2Page("Personal Details","Required", enrollPersonalDetailsRequiredFields);
			 addConfirmFields2Page("Personal Details","Confirm", enrollPersonalDetailsConfirmFields);
			 
			 addFields2Page("Login Details", "Select", enrollLoginDetailsFields);
			 addRequiredFields2Page("Login Details","Required", enrollLoginDetailsRequiredFields);
			 addConfirmFields2Page("Login Details","Confirm", enrollLoginDetailsConfirmFields);
			 
			 addFields2Page("Final Review", "Select", enrollFinalReviewFields);
			 addRequiredFields2Page("Final Review","Required", enrollFinalReviewRequiredFields);
			// addConfirmFields2Page("Final Review","Confirm", enrollFinalReviewConfirmFields);
	 
			// clickOnElement("xpath","//*[@class='simple_form edit_pyr_core_enrollment_profile']/div[4]/input");
			 
			selectPaymentOptions(enrollPaymentOptions);
			selectProfileConfigFields(idl_enrollProfileConfigFields);
			setProfileConfigurationDefaultFields();
			
		 } else {
		 logInfo(profileName + " profile could not be created in enrollment profile page.");
		 Assert.assertTrue(isProfileCreated, profileName + " profile could not be created in Enrollment profile page.");
	   }
    }
	 */
	 
	 
	 // Enrollment @ User
	 
	 @Test(priority =2303)
	 public void verifyEnrollmentProfileAtUser() throws Exception{
		 logInfo("inside verifyEnrollmentProfileAtUser Test");
		
		 go2BusinessEnrollmentPage();
		 boolean isMarketFound = verifyEnrollMarketIsPresent(market);
		   if(isMarketFound==true){
			 	selectBusinessEnrollMarket(market);
			 	
			 	selectProductPack();
			 	selectAddationalProducts();
			 	agreeTermsAndConditions();
			 	idlifeEnrollPersonalInformation();
			 	idlifeEnrollLoginInformation();
			 	idlifeEnrollShippingInformation();
			 	idlifeEnrollInitialOrderShippingInfo();
			 	idlifeSubmitOrder();
			 	verifyEnrollmentIsSuccessful();
		   }
		   
	 	}
	 




		/*	 	submitEnrollmentPersonalDetails();
			// 	submitCreditCardCDetails();
			//	submitCCAddressDetails();
			//	selectSubscriptionPlan("Vibe Pro - Monthly");
				submitLoginDetails();
				acceptTOC();
				
				waitOnElement("cssSelector","#main-content");
				WebElement eactMsg = driver().findElement(org.openqa.selenium.By.cssSelector("#main-content"));
				String actMsg = eactMsg.getText().trim();
				System.out.println("actEnrollMsg =" +actMsg);
				System.out.println("expEnrollMsg =" +expEnrollMsg);
				
				if(actMsg.contains(expEnrollMsg)){
					logInfo("Enrollment is successful");
				} else {
					logInfo("Enrollment is not successful");
				}
				
				 boolean isTextFound = validateTextPresentInPage("cssSelector","#main-content", expEnrollMsg);
				 if(isTextFound==true){
					 logInfo("Enrollment is successful");
				 } else {
					 logInfo("Enrollment is not successful");
					 Assert.assertTrue(isTextFound, "Enrollment is not successful");
				 }
		 } else {
			 logInfo(market + " market match not found.");
			 Assert.assertTrue(isMarketFound, market + " market match not found.");
		 }
		 
	 }
	 
	 
	
}


*/
			@Test(priority =2304)
			public void userEnrollment() throws Exception{	
				logInfo("inside userEnrollment test");
				go2BusinessEnrollmentPage();
				verifyEnrollMarketIsPresent(usMarket);
				selectBusinessEnrollMarket(usMarket);
				monatEnrollmentStep1();
				monatEnrollmentStep2();
				monatEnrollmentStep3();
				monatEnrollmentStep4();
				acceptTAC();
				monatSubmitOrder();
				verifyEnrollmentIsSuccessful();
				
				


}

}