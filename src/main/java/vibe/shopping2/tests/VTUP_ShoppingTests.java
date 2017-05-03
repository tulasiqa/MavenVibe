package vibe.shopping2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.LoginCredentials;
import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.shopping.tests.ShoppingMethods;
@Priority(114)
public class VTUP_ShoppingTests extends VTUP_ShoppingMethods{
	
	readProp prop = new readProp();
	
	
	
	@Test(priority=101)
	public void verifyPlaceOrders() throws Exception{
		logInfo("inside verifyPlaceOrders() test");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		validatePlaceOrders();
		
	
	}
	
	@Test(priority=102)
	public void verifyAddRecruits() throws Exception{
		logInfo("inside verifyAddRecruits() test");
		validateAddRecruitsPage();
		
	
	}
	

	@Test(priority=103)
	public void verifyPersonalSummary() throws Exception{
		logInfo("inside verifyPersonalSummary() test");
			validatePersonalSummaryPage();
		
	
	}
	
	@Test(priority=104)
	public void verifyCommissionsPage() throws Exception{
		logInfo("inside verifyCommissionsPage() test");
			validateCommissionsPage();
	
	}
	
	@Test(priority=105)
	public void verifyMyTeam() throws Exception{
		logInfo("inside verifyMyTeam() test");
			validateMyTeamPage(); 
	
	}
	
	@Test(priority=106)
	public void userLogoutFromShopping() throws Exception{
		logInfo("inside userLogoutFromShopping() test");
		userLogout();
		
	}
	
}
