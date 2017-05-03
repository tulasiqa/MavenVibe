package vibe.accountsettings.tests;

import org.testng.annotations.Test;

public class AccountSettings_Tests extends AccountSettingsMethods {

	

	@Test(priority=350)
	public void verifyAdminBrokenLinks() throws Exception{
		logInfo("inside verifyAdminBrokenLinks() Test");
		AccountSettingsMethods acc = new AccountSettingsMethods();
		
		acc.go2AccountSettingspage();
		
		readHeader("Account Information");
		readHeader("Community Profile");
		readHeader("Linked Accounts");
		readHeader("Notifications");
		readHeader("Security");
		readHeader("Subscription");
		readHeader("Language Settings");
		readHeader("Commission Payment");
		readHeader("Waiting Room");
		readHeader("Placement Preference");
		readHeader("Exigo Primary Card");
		readHeader("Shop Existing Cc And Addresses");
	}
}
