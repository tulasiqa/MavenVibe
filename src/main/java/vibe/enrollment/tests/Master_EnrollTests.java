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
public class Master_EnrollTests extends Master_EnrollMethods {
	Logger logger = Logger.getLogger(Master_EnrollTests.class);
	
	 String market = "United States";
	 String profileName = "Profile" + TestBase.generateRandomNumberInRange(1, 999); 
	 String[] enrollProfileConfigFields={"Subscription with promotion"}; // , "Work phone"
	 String enrollSponsor_text = "315351";
	// String enrollPDDayPhone_text = "4354322";
		 
	 // Enrollment @ Admin 
	
	
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
			selectProfileConfigFields(enrollProfileConfigFields);
			setProfileConfigurationDefaultFields();
			
		 } else {
		 logInfo(profileName + " profile could not be created in enrollment profile page.");
		 Assert.assertTrue(isProfileCreated, profileName + " profile could not be created in Enrollment profile page.");
	   }
    }
	 
	 
	 
	// Enrollment @ User
	 
		 @Test(priority =2303)
		 public void verifyEnrollmentProfileAtUser() throws Exception{
			 logInfo("inside verifyEnrollmentProfileAtUser Test");
			
			 go2BusinessEnrollmentPage();
			 boolean isMarketPresent = verifyEnrollMarketIsPresent(market);
			 if(isMarketPresent){
				 selectBusinessEnrollMarket(market);
				 submitEnrollmentPersonalDetails();
				 submitLoginDetails();
				 acceptTOC();
				 boolean isEnrollSuccessful = verifyEnrollmentSuccessfulAtUser();
				 if(isEnrollSuccessful==true){
					 logInfo("Enrollment is successful");
				 } else {
					 logInfo("Enrollment is not successful");
					 Assert.assertTrue(isEnrollSuccessful, "Enrollment is not successful");
				 }
			 }
	
		 }

		 
}