package vibe.setting.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.profile.account.ProfileAccountMethods;
import vibe.users.tests.MonatUsersMethods;

@Priority(39)
public class AVSettings_Tests extends AVSettingsMethods {
	
	CommunityMethods cm = new CommunityMethods();
	ProfileAccountMethods pa = new ProfileAccountMethods();
	MonatUsersMethods um = new MonatUsersMethods();
	NewsMethods nm = new NewsMethods();
	readProp prop = new readProp();
	@Test(priority=3901)
	public void enableAccountSideNavs() throws Exception, Exception{
		logInfo("inside enableAccountSideNavs() Test ");
		System.out.println("inside enableAccountSideNavs() Test");
		go2SpecialSettings();
		enableAllAccountSideNavs();
		
	}
	
	@Test(priority=3902)
	public void verifyAccountInformation() throws Exception{
		logInfo("inside verifyAccountInformation() Test");
		System.out.println("inside verifyAccountInformation() Test");
		back2Office();
		Thread.sleep(5000);
		logOut();
		logIn(nonadminUser_text,nonadminPwd_text);
		go2Accounts();
		boolean isVerified = verifyAccntInfo();
		if(isVerified){
			updateAccountInfo("first name","last name","testuser1@icentris.com","Salt Lake City");
		}
		else{
			Assert.assertTrue(isVerified, "Unable to verify the account information.");
		}

	}
	
	@Test(priority=3903)
	public void ChangeCommunityProfilePhoto() throws Exception{
		logInfo("inside ChangeCommunityProfilePhoto() Test ");
		System.out.println("inside ChangeCommunityProfilePhoto() Test");
		go2Accounts();
		selectAccountsSection("Community Profile");
		boolean isVerified = verifyCommunityProfileInfo();
		if(isVerified){
			isVerified = cm.addProfilePhoto();
			if(!isVerified){
				Assert.assertTrue(isVerified, "Unable to change the profile photo on the Community Profile section.");
			}
		}
		else{
			Assert.assertTrue(isVerified, "Unable to verify the Community Profile information.");
		}
	}
	
	@Test(priority=3904)
	public void ChangeInvalidProfilePhoto() throws Exception{
		logInfo("inside ChangeInvalidProfilePhoto() Test ");
		System.out.println("inside ChangeInvalidProfilePhoto() Test");
		go2Accounts();
		selectAccountsSection("Community Profile");
		boolean isVerified = cm.validateProfilePhoto();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to validate the profile photo with invalid file on the Community Profile section.");
		}
		
	}
	
	@Test(priority=3905)
	public void UpdateCommunityProfileInfo() throws Exception{
		logInfo("inside UpdateCommunityProfileInfo() Test ");
		System.out.println("inside UpdateCommunityProfileInfo() Test");
		Thread.sleep(5000);
		logOut();
		logIn(nonadminUser_text,nonadminPwd_text);
		go2Accounts();
		selectAccountsSection("Community Profile");
		pa.updateCommProfileInfo();
		boolean isVerified = verifyCommProfileInfoInAboutSection(communityFName_text,profileMyStory_text);
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to verify the updated Community Profile info, under About section in community profile page.");
		}
		else{
			logOut();
			logIn(nonadminUser1_text,nonadminPwd1_text);
			isVerified = verifyPrivateInfoInAboutSection(nonadminUser_text);
			if(!isVerified){
				Assert.assertTrue(isVerified, "Unable to verify the private Community Profile info, under About section in community profile page.");
			}
		}
		
	}
	
	@Test(priority=3906)
	public void resetCommunityProfileInfo() throws Exception{
		logInfo("inside resetCommunityProfileInfo() Test ");
		System.out.println("inside resetCommunityProfileInfo() Test");
		Thread.sleep(5000);
		logOut();
		logIn(nonadminUser_text,nonadminPwd_text);
		go2Accounts();
		selectAccountsSection("Community Profile");
		boolean isVerified = verifyResetCommunityInfo();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to reset the community profile information.");
		}
	}
	
	@Test(priority=3907)
	public void UpdateUserInfoByAdmin() throws Exception{
		logInfo("inside UpdateUserInfoByAdmin() Test ");
		System.out.println("inside UpdateUserInfoByAdmin() Test");
		logOut();
		logIn(adminUser_text,adminPwd_text);
		um.go2Users();
		um.searchUser(nonadminUser1_text + " ");
		um.editUser(nonadminUser1_text);
		um.changeUserName(txtFirstName,txtLastName);
		boolean isVerified = verifyUpdatedUserInfo(txtFirstName,txtLastName);
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to verify the updated user info by admin, on the user side.");
		}
	}
	
	@Test(priority=3908)
	public void changeUserPasswordAndValidate() throws Exception{
		
		logInfo("inside changeUserPasswordAndValidate() Test ");
		System.out.println("inside changeUserPasswordAndValidate() Test");
		Thread.sleep(5000);
		logOut();
		logIn(nonadminUser_text,nonadminPwd_text);
		go2Accounts();
		boolean isVerified = verifyChangeUserPassword();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to change / validate password fields under Security section.");
		}
	}
	
	@Test(priority=3909)
	public void updateEmailNotificationSettings() throws Exception{
		logInfo("inside updateEmailNotificationSettings() Test ");
		System.out.println("inside updateEmailNotificationSettings() Test");
		go2Accounts();
		verifyEmailNotificationSettings();
	}
	
	@Test(priority=3910)
	public void updateCellNotificationSettings() throws Exception{
		logInfo("inside updateCellNotificationSettings() Test ");
		System.out.println("inside updateCellNotificationSettings() Test");
		go2Accounts();
		verifyCellNotificationSettings();
	}
	
	@Test(priority=3911)
	public void updateLanguageSettings() throws Exception, Exception{
		logInfo("inside changeUserPassword() Test ");
		System.out.println("inside changeUserPassword() Test");
		//nm.loginAsUser(repId);
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		go2Accounts();
		verifyChangeLanguageSettings();
		
	}
	
	@Test(priority=3912)
	public void pwdRecoveryEmailFromNotifications() throws Exception, Exception{
		logInfo("inside pwdRecoveryEmailFromNotifications() Test ");
		System.out.println("inside pwdRecoveryEmailFromNotifications() Test");
		go2Accounts();
		verifyPasswordRecoveryEmail();
		
	}
	
	@Test(priority=3913)
	public void setEmailNotifications() throws Exception, Exception{
		logInfo("inside setEmailNotifications() Test ");
		System.out.println("inside setEmailNotifications() Test");
		go2Accounts();
		verifyEmailNotifications();
		
	}
	
	@Test(priority=3914)
	public void verifyCommissionPaymentOptions() throws Exception, Exception{
		logInfo("inside verifyCommissionPaymentOptions() Test ");
		System.out.println("inside verifyCommissionPaymentOptions() Test");
		Thread.sleep(5000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2PaymentSettings();
		enableConfigurePaymentOptions();
		back2Office();
		logOut();
		logIn(nonadminUser_text,nonadminPwd_text);
		go2Accounts();
		boolean isVerified = verifyCommPaymentOptions();
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to verify all the options under Commission Payment.");
		}
		
	}
	
	@Test(priority=3915)
	public void selectMarketsForAllPaymentOptions() throws Exception, Exception{
		logInfo("inside selectMarketsForAllPaymentOptions() Test ");
		System.out.println("inside selectMarketsForAllPaymentOptions() Test");
		Thread.sleep(5000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2PaymentSettings();
		selectMarketsForPaymentOptions();
				
	}
	
	
	
	
}
