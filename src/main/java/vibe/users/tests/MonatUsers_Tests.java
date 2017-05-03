package vibe.users.tests;

import org.openqa.selenium.By.ByXPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.readProp;

import vibe.billing.tests.BillingMethods;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.mywebsite.tests.MyWebSite_Methods;

@Priority(5)
public class MonatUsers_Tests extends MonatUsersMethods {
	 BusinessContactsMethods bc = new BusinessContactsMethods();
	 BillingMethods bm = new BillingMethods();
	 MyWebSite_Methods wm = new MyWebSite_Methods();
	 readProp prop = new readProp();
	 
	 @Test(priority = 501)
	 public void createAdminUsers() throws Exception, Exception{
		    logInfo("inside createAdminUsers() Test");
		    System.out.println("inside createAdminUsers() Test");		
			go2Users();
			addAdminUser(userName_text,email);  
			go2AdminUsers();
		 	searchAdminUser(userName_text);
		 	boolean isUserFound = verifyAdminUserPresent(userName_text);		 	
//		 	addNotes(userNotes_text);
		 	if(isUserFound==false){
		 		Assert.assertTrue(isUserFound, userName_text + " failed to create admin user.");
		 	} 
		 	
		 }
	 
	 @Test(priority = 502)
	 public void editUsers() throws Exception, Exception{
		logInfo("inside editUsers() Test");
	    System.out.println("inside editUsers() Test");
	    go2AdminUsers();
	 	searchAdminUser(userName_text);
	 	boolean isUserFound = verifyAdminUserPresent(userName_text);
	 	editAdminUser(userName_text);
	 	changeUserName(txtFirstName,txtLastName);
	 	if(isUserFound==false){
	 		Assert.assertTrue(isUserFound, userName_text + " user is not found.");
	 	}
	 }

	 
	 @Test(priority = 503)
	 public void manageRoles() throws Exception, Exception{
		 logInfo("inside manageRoles() Test");
		 System.out.println("inside manageRoles() Test");
		 go2AdminUsers();
		 searchAdminUser(userName_text);
		 boolean isUserFound = verifyAdminUserPresent(userName_text);
		 if(isUserFound){
			 editAdminUser(userName_text);
			 addRoles2User(arrSecurityGrps);
			 verifyUserRole(arrSecurityGrps);
		 }
	 }
	 
	 @Test(priority = 504)
	 public void verifySortOrderCols() throws Exception, Exception{
		 String userName_text = "newuser1";
		 logInfo("inside verifySortOrderCols() Test");
		 System.out.println("inside verifySortOrderCols() Test");
		 boolean isSortOrderVerified = false;
		 boolean isUserFound = false;
		 go2AdminUsers();
		 searchAdminUser(userName_text);
		 isUserFound = verifyAdminUserPresent(userName_text);
		 if(isUserFound){
			 isSortOrderVerified = verifySortOrderCols(txtFirstName,txtLastName,userName_text,"",userEmail_text,"Active");
		 }
		 if(isSortOrderVerified == false){
			 Assert.assertTrue(isSortOrderVerified,"Unable to sort the users in descending order "+userName_text);
		 }
		 go2AdminUsers();
		 searchAdminUser(userName_text1);
		 isUserFound = verifyAdminUserPresent(userName_text1);
		 if(isUserFound){
			 isSortOrderVerified = verifySortOrderCols(txtFirstName,txtLastName,userName_text1,"",email,"Active");
		 }
		 if(isSortOrderVerified == false){
			 Assert.assertTrue(isSortOrderVerified,"Unable to sort the users in ascending order "+userName_text1);
		 }
	 }
	 
	 
	 @Test(priority = 505)
	 public void loggedInAsUser() throws Exception, Exception{
		 String enrollUser = "automation.enroll2";
		 logInfo("inside loggedInAsUser() Test");
		 System.out.println("inside loggedInAsUser() Test");
		 boolean isVerified = false;
		 go2AdminUsers();
		 searchAdminUser(enrollUser);
		 if(verifyUserPresent(enrollUser)){
			 isVerified = verifyLoggedInUser(enrollUser);
			 if(isVerified == false){
			 	Assert.assertTrue(isVerified, "Unable to logged in as user from admin.");
			 } 
		 }
		
	 }
	 
	 @Test(priority = 506)
	 public void verifyUserDetailsPage() throws Exception, Exception{
		 logInfo("inside verifyUserDetailsPage() Test");
		 System.out.println("inside verifyUserDetailsPage() Test");
		 boolean isVerified = false;
		 go2Users();
		 searchUser(userName_text);
		 if(verifyUserPresent(userName_text)){
			 isVerified = verifyUserDetailsPage(userName_text);
			 if(isVerified == false){
				 Assert.assertTrue(isVerified, "Unable to verify the user details.");
			 } 
		 }
		
	 }
	 
	 @Test(priority = 507)
	 public void searchUsersAdvancedSearch() throws Exception, Exception{
		 logInfo("inside searchUsersAdvancedSearch() Test");		
		 boolean isVerified = false;
		 go2Users();
		 isVerified = verifyUserDetailsByAdvancedSearch();
		 if(isVerified == false){
			 Assert.assertTrue(isVerified, "Unable to verify the user details using advanced search option.");
		 } 
		
	 }
	 
	 @Test(priority = 508)
	 public void verifyValidationsForNewAdminUser() throws Exception, Exception{
		 logInfo("inside verifyValidationsForNewAdminUser() Test");
		 System.out.println("inside verifyValidationsForNewAdminUser() Test");
		 boolean isVerified = false;
		 go2Users();
		 isVerified = verifyValidationsForAdminUser();
		 if(isVerified == false){
			 Assert.assertTrue(isVerified, "Unable to verify the validations while creating a new admin user.");
		 } 
		
	 }
	 
	 @Test(priority = 509)
	 public void ResetPassword() throws Exception, Exception{
		 logInfo("inside ResetPassword() Test");
		 String userName_text = "testUser529";
		
		 boolean isVerified = false;
		 go2AdminUsers();
		 searchAdminUser(userName_text);
		 boolean isUserFound = verifyAdminUserPresent(userName_text);
		 if(isUserFound){
			 editAdminUser(userName_text);
			 isVerified = verifyResetPassword(userName_text);
			 if(isVerified == false){
				 Assert.assertTrue(isVerified, "Unable to reset the password for the user.");
			 } 
		 }
	 }
	 
	 @Test(priority = 510)
	 public void validateBlankResetPasswordFields() throws Exception, Exception{
		 logInfo("inside validateBlankResetPasswordFields() Test");
		 System.out.println("inside validateBlankResetPasswordFields() Test");
		 boolean isVerified = false;
   		 logOut();
		 logIn(adminUser_text, adminPwd_text);
		 go2PasswordSettings();
		 changePasswordSettings("None");
		 implicityWaits(5);
		 go2Users();
		 searchUser(userName_text);
		 boolean isUserFound = verifyUserPresent(userName_text);
		 if(isUserFound){
			 editUser(userName_text);
			 isVerified = validateBlankResetPasswordFields(userName_text);
			 if(isVerified == false){
				 Assert.assertTrue(isVerified, "Unable to validate the blank reset password fields, after re-setting password for the user.");
			 } 
		 }
	 }
	 
	 @Test(priority = 511)
	 public void validateResetPasswordFields() throws Exception, Exception{
				 
		 logInfo("inside validateResetPasswordFields() Test");
		 System.out.println("inside validateResetPasswordFields() Test");
		 boolean isVerified = false;
		 
		 logOut();
		 logIn(adminUser_text, adminPwd_text);
		 go2PasswordSettings();
		 changePasswordSettings("Minimum length 7. Include at least one number.");
		 Thread.sleep(5000);
		 confirmOK();
		 go2Users();
		 searchUser(userName_text);
		 boolean isUserFound = verifyUserPresent(userName_text);
		 if(isUserFound){
			 editUser(userName_text);
			 isVerified = validateResetPasswordFields(userName_text);
			 if(isVerified == false){
				 Assert.assertTrue(isVerified, "Unable to validate the reset password fields, after re-setting password for the user.");
			 } 
		 }
	 }
	 
	 @Test(priority = 512)
	 public void DenyOldPasswords() throws Exception, Exception{
			 
		 logInfo("inside DenyOldPasswords() Test");
		 System.out.println("inside DenyOldPasswords() Test");
		 boolean isVerified = false;

		 logIn(adminUser_text, adminPwd_text);
		 go2PasswordSettings();
		 implicityWaits(3);
		 changeDenyPasswordSettings("Password Archiving Count","Deny Old Passwords");
		 confirmOK();
		 go2Users();
		 searchUser(userName_text);
		 boolean isUserFound = verifyUserPresent(userName_text);
		 if(isUserFound){
			 editUser(userName_text);
			 isVerified = validateDenyOldPasswords(userName_text);
			 if(isVerified == false){
				 Assert.assertTrue(isVerified, "Unable to validate the deny old password fields, after re-setting password for the user.");
			 } 
		 }
	 }

	@Test(priority = 513)
	public void changeSubscriptionPlanFromLite2Pro() throws Exception, Exception{
		//take lite-monthly user as enrollUser
		String enrollUser = "automation.enroll2";
		logInfo("inside changeSubscriptionPlanFromLite2Pro Test");
		System.out.println("inside changeSubscriptionPlanFromLite2Pro Test");
		bm.go2BillingSubscriptionAdmin(enrollUser);
		String drpSubscriptionPlanName = "//*[@id='pyr_core_subscription_subscription_plan_id']";
		String before_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("before_change =" +before_change);
		bm.changeSubscriptionPlanInAdmin(txtSubPlanProMonthly);
		String after_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("after_change =" +after_change);
		boolean isNotesMatchFound = bm.verifySubscriptionNotesPresent("Subscription plan changed from "+before_change+" to "+after_change);
		Assert.assertTrue(isNotesMatchFound, "Subscription plan changed from "+before_change+" to "+after_change + " match not found.");
	}
	 
	@Test(priority = 514)
	public void changeSubscriptionPlanFromPro2Lite() throws Exception, Exception{
				
		logInfo("inside changeSubscriptionPlanFromPro2Lite Test");
		System.out.println("inside changeSubscriptionPlanFromPro2Lite Test");
		bm.go2BillingSubscriptionAdmin(enrollUser);
		String drpSubscriptionPlanName = "//*[@id='pyr_core_subscription_subscription_plan_id']";
		String before_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("before_change =" +before_change);
		bm.changeSubscriptionPlanInAdmin(txtSubPlanLiteMonthly);
		String after_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("after_change =" +after_change);
		boolean isNotesMatchFound = bm.verifySubscriptionNotesPresent("Subscription plan changed from "+before_change+" to "+after_change);
		Assert.assertTrue(isNotesMatchFound, "Subscription plan changed from "+before_change+" to "+after_change + " match not found.");
	}
	 
	@Test(priority = 515)
	public void AccountSectionFieldsAndWebsites() throws Exception, Exception{
		logInfo("inside verifyAccountSectionFieldsAndWebsites Test");
		System.out.println("inside verifyAccountSectionFieldsAndWebsites Test");
		back2Office();
		logOut();
		logIn(userName_text,adminPwd_text);

		String websiteTemplate_text = prop.getLocatorForEnvironment(appUrl,"websiteTemplate_text");
		wm.addMyWebsite(websiteTemplate_text,websiteName_text);

		if(wm.verifyWebsiteIsPresent(websiteName_text)){
			logOut();
			logIn(adminUser_text,adminPwd_text);
			go2Users();
			searchUser(userName_text);
			boolean isUserFound = verifyUserPresent(userName_text);
			if(isUserFound){
				editUser(userName_text);
				boolean isFieldsVerified = verifyAccountSectionFieldsAndWebsites(websiteName_text);
				if(!isFieldsVerified){
					Assert.assertTrue(isFieldsVerified, "Unable to verify account section fields and websites.");
				}
			}
		}
		
	}
	 
	@Test(priority = 516)
	
	public void updateUserDetails() throws Exception, Exception{
				
		logInfo("inside updateUserDetails Test");
		System.out.println("inside updateUserDetails Test");
//		back2Office(); uncomment this during smoke
		logOut();
		logIn(userName_text,adminPwd_text);
		boolean isWebsiteUpdated = updateWebSiteName(websiteName_text);
		if(isWebsiteUpdated){
			logOut();
			logIn(adminUser_text,adminPwd_text);
			go2Users();
			searchUser(userName_text);
			boolean isUserFound = verifyUserPresent(userName_text);
			if(isUserFound){
				editUser(userName_text);
				boolean isVerified = verifyUpdateUserDetails(userName_text,websiteName_text);
				if(!isVerified){
					Assert.assertTrue(isVerified, "Unable to update the user details.");
				}
			}
		}
		else{
			isWebsiteUpdated = false;
			Assert.assertTrue(isWebsiteUpdated, "Unable to update the website.");
		}
		Thread.sleep(3000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
		
	}
	
	
	/*public void updateUserDetails() throws Exception, Exception{
		logInfo("inside updateUserDetails Test");
		System.out.println("inside updateUserDetails Test");
		back2Office();
		logOut();
		logIn(userName_text,adminPwd_text);
		boolean isWebsiteUpdated = updateWebSiteName(websiteName_text);
		if(isWebsiteUpdated){
			logOut();
			logIn(adminUser_text,adminPwd_text);
			go2Users();
			searchUser(userName_text);
			boolean isUserFound = verifyUserPresent(userName_text);
			if(isUserFound){
				editUser(userName_text);
				boolean isVerified = verifyUpdateUserDetails(userName_text,websiteName_text);
				if(!isVerified){
					Assert.assertTrue(isVerified, "Unable to update the user details.");
				}
			}
		}
		else{
			isWebsiteUpdated = false;
			Assert.assertTrue(isWebsiteUpdated, "Unable to update the website.");
		}
		Thread.sleep(3000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
		
	}*/
	 
	@Test(priority = 517)
	public void validateEmail() throws Exception, Exception{
		logInfo("inside validateEmail() Test");
		System.out.println("inside validateEmail() Test");
		waitOnElement("xpath",lnkBack2Admin);
		clickOnElement("xpath",lnkBack2Admin);
		go2Users();
		searchUser(userName_text);
		editUser(userName_text);
		boolean isValidated = verifyValidateEmail();
		if(!isValidated){
			Assert.assertTrue(isValidated, "Unable to validate the blank email when updating user.");
		}
	}
	
	@Test(priority = 518)
	public void verifyUserStats() throws Exception, Exception{
		logInfo("inside verifyUserStats() Test");
		System.out.println("inside verifyUserStats() Test");
		go2Users();
		searchUser(userName_text);
		editUser(userName_text);
		boolean isVerified = verifyUserStatistics();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to validate the user statistics.");
		}
	}
	
	
	

	@Test(priority = 519)
	 public void logInAsNewAdminUser() throws Exception, Exception{
		 logInfo("inside logInAsNewAdminUser() Test");
		 System.out.println("inside logInAsNewAdminUser() Test");
		 boolean isVerified = false;
		 back2Office();
		 logOut();
		 logIn(userName_text,userPassword_text);
		 confirmationMessage("Signed in successfully.");
		 implicityWaits(3);
		 bc.go2ContactsPage();
		 String pageTitle = driver().findElement(ByXPath.xpath("//*[@id='home-link']")).getText().trim();
		 if(!pageTitle.contains("Contacts")){
			 Assert.assertTrue(false,"Unable to access the contacts module with this new admin user " + userName_text);
		 }
	 }
}
