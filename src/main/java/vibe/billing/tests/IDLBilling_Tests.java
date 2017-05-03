package vibe.billing.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.users.tests.UsersMethods;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@Priority(38)
public class IDLBilling_Tests extends IDLBillingMethods {
	
	UsersMethods um = new UsersMethods();
	readProp prop = new readProp();
	
	@Test(priority=3800)
	public void enableBillingUsers() throws Exception{
		logInfo("Inside enableBillingUsers() test..");
		System.out.println("Inside enableBillingUsers() test..");
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
				
		//go to admin & update subscriptions
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"liteUser"));
		updateSubscriptionDetails(IDLSubPlanLite,billingDate);
		go2BillingSubscriptionAdmin(prop.getLocatorForEnvironment(appUrl,"proUser"));
		updateSubscriptionDetails(IDLSubPlanPro,billingDate);
		setAllowDowngrade();
		back2Office();

	}
	
	//TC3718
	@Test(priority=3801)
	public void upgradeFromLite2Pro() throws Exception{
		try{
			String billingDate = getDateByMonth(1, "MM/dd/yyyy");
			logInfo("Inside upgradeFromLite2Pro() test..");
			System.out.println("Inside upgradeFromLite2Pro() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN3"),prop.getLocatorForEnvironment(appUrl,"newsLN3"),prop.getLocatorForEnvironment(appUrl,"newsCon3"));
			navigate2Subscription();
			boolean isSubscriptionUpgraded = upgradeSubscription(IDLSubPlanLite,IDLSubPlanPro,billingDate);
			if(isSubscriptionUpgraded==false){
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite to pro.");
			}
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}
		
	}
	
	//TC3734
	@Test(priority=3802)
	public void downgradeFromPro2Lite() throws Exception{
		try{
			String billingDate = getDateByMonth(1, "MM/dd/yyyy");
			logInfo("Inside downgradeFromPro2Lite() test..");
			System.out.println("Inside downgradeFromPro2Lite() test..");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN4"),prop.getLocatorForEnvironment(appUrl,"newsLN4"),prop.getLocatorForEnvironment(appUrl,"newsCon4"));
			navigate2Subscription();
			boolean isSubscriptionDowngraded = downgradeSubscription(IDLSubPlanPro,IDLSubPlanLite,billingDate);
			if(isSubscriptionDowngraded==false){
				Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro to lite.");
			}
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}
		
	}
		
	
}
