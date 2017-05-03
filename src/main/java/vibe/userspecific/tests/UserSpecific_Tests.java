
package vibe.userspecific.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import vibe.calendar.tests.Calendar_Tests;
import vibe.contacts.tests.BusinessContacts_Tests;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.reports.tests.ReportsMethods;
import vibe.reports.tests.ReportsTest;
import vibe.resourcelibrary.tests.RLMethods;
import vibe.resourcelibrary.tests.RL_Tests;
import vibe.setup.tests.Setup_SubscriptionPlan_Methods;
import vibe.shopping.tests.ShoppingMethods;
import common.Priority;
import common.TestBase;

@Priority(37)
public class UserSpecific_Tests extends TestBase{

	CommunityMethods cm = new CommunityMethods();
	Calendar_Tests ct = new Calendar_Tests();
	ShoppingMethods sm = new ShoppingMethods();
	BusinessContacts_Tests bc = new BusinessContacts_Tests();
	RL_Tests rl = new RL_Tests();
	Setup_SubscriptionPlan_Methods sp = new Setup_SubscriptionPlan_Methods();
	ReportsMethods rm = new ReportsMethods();
	
	@Test(priority= 3701)
	public void verifyCorpEvents4Distributor() throws Exception{
		logInfo("inside verifyCorpEvents4Distributor() Test.");
		logIn(adminUser_text,adminPwd_text);
		ct.createCorpCalendarEvent();
		logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);		// login as distributor
		driver().navigate().to(appUrl + "resource_library");
		driver().navigate().to(appUrl + "calendar");
		// cm.go2CalendarPage();
		// driver().navigate().refresh();
		
		boolean isEventPresent = ct.verifyEventisDisplayed(corpEventName_text);
		if(isEventPresent==false){
			Assert.assertTrue(isEventPresent, corpEventName_text + " event match not found.");
			logInfo(corpEventName_text + " event match not found.");
		}
	}

	
	@Test(priority= 3702)
	public void verifyEvents4Distributor() throws Exception{
		logInfo("inside verifyEvents4Distributor() Test...");
		// logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);
		ct.createNewCalendarEvent();
		logOut();
		logIn(distributorUser2_text,distributorUser2Pwd_text);
		driver().navigate().to(appUrl + "resource_library");
		driver().navigate().to(appUrl + "calendar");
	
		boolean isEventPresent = ct.verifyEventisDisplayed(newEventName_text);
		if(isEventPresent==true){
			Assert.assertFalse(isEventPresent, newEventName_text + " calendar event should not be visible created by other user.");
			logInfo(newEventName_text + " calendar event should not be visible created by other user.");
		}
		
	}

	
	
	@Test(priority= 3703)
	public void verifyPrivateBlogPost4Distributor() throws Exception{
		logInfo("inside verifyPrivateBlogPost4Distributor() Test.");
		logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);
		cm.addBlogPost(addPrivatePostTitle_text,"PRIVATE",false);
		boolean isPostFound = cm.verifyPostIsPresent(addPrivatePostTitle_text);
		if(isPostFound==true){
			Assert.assertFalse(isPostFound, "private blog post is visible in community page for distributor1.");
			logInfo("private blog post is visible in community page for distributor1.");
		}
		boolean isMatchFound = cm.verifyPostInMyProfilePage("Posted to your blog: \"" + addPrivatePostTitle_text + "\"");
		if(isMatchFound==false){
			Assert.assertTrue(isMatchFound, addPrivatePostTitle_text + " private blog post not found in Myprofile page for distributor1.");
			logInfo(addPrivatePostTitle_text + " private blog post not found in Myprofile page for distributor1.");
		}
		
		logOut();
		
		logIn(distributorUser2_text,distributorUser2Pwd_text);	
		System.out.println("logged in as distributor2");
	//	cm.navigate2CommunityPage();
		boolean isPostFound1 = cm.verifyPostIsPresent(addPrivatePostTitle_text);
		if(isPostFound1==true){
			Assert.assertFalse(isPostFound1, " private blog post is visible in community page for distributor2.");
			logInfo("private blog post is visible in community page for distributor2.");
		}
		boolean isMatchFound1 = cm.verifyPostInMyProfilePage(addPrivatePostTitle_text);
		if(isMatchFound1==true){
			Assert.assertFalse(isMatchFound1, addPrivatePostTitle_text + " private post match found in MyProfile page for distributor2.");
			logInfo(addPrivatePostTitle_text + " private blog post match found in MyProfile page for distributor2.");
		}
	}
	

	
	@Test(priority= 3704)
	public void verifyPublicBlogPost4Distributor() throws Exception{
		logInfo("inside verifyPublicBlogPost4Distributor() Test.");
		logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);	
		cm.addBlogPost(addPublicPostTitle_text,"PUBLIC",true);
		boolean isPostFound = cm.verifyPostIsPresent(addPublicPostTitle_text);
		System.out.println("isPostFound=" +isPostFound);
		if(isPostFound==false){
			Assert.assertTrue(isPostFound, "public blog post is not visible in community page for distributor1.");
			logInfo("public blog post is not visible in community page for distributor1.");
		}
		boolean isMatchFound = cm.verifyPostInMyProfilePage("Posted to your blog: \"" + addPublicPostTitle_text + "\"");
		System.out.println("isMatchFound=" +isMatchFound);
		if(isMatchFound==false){
			Assert.assertTrue(isMatchFound, addPublicPostTitle_text + " public blog post not found in Myprofile page for distributor1.");
			logInfo(addPublicPostTitle_text + " public blog post not found in Myprofile page for distributor1.");
		}
		
		logOut();
		
		logIn(distributorUser2_text,distributorUser2Pwd_text);	
		System.out.println("logged in as distributor2");
		boolean isPostFound1 = cm.verifyPostIsPresent(addPublicPostTitle_text);
		System.out.println("isPostFound1=" +isPostFound1);
		if(isPostFound1==false){
			Assert.assertTrue(isPostFound1, " public blog post is not visible in community page for other users.");
			logInfo("public blog post is not visible in community page for other users.");
		}
		boolean isMatchFound1 = cm.verifyPostInMyProfilePage(addPublicPostTitle_text);
		System.out.println("isMatchFound1=" +isMatchFound1);
		if(isMatchFound1==true){
			Assert.assertFalse(isMatchFound1, addPublicPostTitle_text + " blog posted by other users should not be displayed in MyProfile page for other users.");
			logInfo(addPublicPostTitle_text + " public blog posted by other users should not be displayed in MyProfile page for other users.");
		}
	
	}

	@Test(priority= 3705)
	public void verifyCallScripts4Distributor() throws Exception{
		logInfo("inside verifyCallScripts4Distributor() Test.");
		logOut();
		logIn(adminUser_text,adminPwd_text);
		bc.createCallScript(callScriptName_text);
		back2Office();
		logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);	
		bc.addBusinessContact();
		bc.addCallScript2Contacts(contactFirstName_text,contactLastName_text,callScriptName_text);
		back2Office();
	}
	

	@Test(priority= 3706)
	public void verifyRL4Distributor() throws Exception{
		logInfo("inside verifyRL4Distributor() Test.");
		// logOut();
		logIn(adminUser_text,adminPwd_text);
		rl.createResource();
		back2Office();
		logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);	
		rl.navigate2BusinessRL();
		rl.verifyResourceInBusinessRL(newResourceTitle_text);
		
	}

	
	@Test(priority= 3707)
	public void verifySubscriptionPlan4Distributor() throws Exception{
		logInfo("inside verifySubscriptionPlan4Distributor() Test.");
		logOut();
		logIn(adminUser_text,adminPwd_text);
		sp.go2SubscriptionPlan();
		sp.createSubscriptionPlan(subscriptionName_text);
		sp.editSubscriptionMarkets(subscriptionName_text);
		back2Office();
		logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);	
		sp.go2UsersSubscriptionPage();
		sp.verifyAvailableSubscriptions(subscriptionName_text);
	}
		
 	

	@Test(priority= 3708)
	public void verifyReports4Distributor() throws Exception{
		logInfo("inside verifyReports4Distributor() Test.");
		logOut();
		logIn(adminUser_text,adminPwd_text);
		rm.navigate2AdminReport();
		rm.createNewReport(reportName_text);
		back2Office();
		logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);	
		rm.navigate2Report();
		rm.verifyReportPresent(reportName_text);
	}


	@Test(priority= 3709)
	public void verifyPrivatePhoto4Distributor() throws Exception{
		logInfo("inside verifyPrivatePhoto4Distributor() Test.");
		logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);	
		cm.addCommunityPhoto(addPrivatePhotoTitle_text,"PRIVATE");
		boolean isPhotoFound = cm.verifyPostIsPresent(addPrivatePhotoTitle_text);
		if(isPhotoFound==true){
			Assert.assertFalse(isPhotoFound, " private photo should not be visible in community page.");
			logInfo(" private photo should not be visible in community page.");
		}
		boolean isMatchFound = cm.verifyPostInMyProfilePage("Posted A Photo: \"" + addPrivatePhotoTitle_text + "\"");
		if(isMatchFound==false){
			Assert.assertTrue(isMatchFound, addPrivatePhotoTitle_text + " private photo not found in Myprofile page for distributor1.");
			logInfo(addPrivatePhotoTitle_text + " private photo not found in Myprofile page for distributor1.");
		}
		
		logOut();
		
		logIn(distributorUser2_text,distributorUser2Pwd_text);	
		System.out.println("logged in as distributor2");
	//	cm.navigate2CommunityPage();
		boolean isPhotoFound1 = cm.verifyPostIsPresent(addPrivatePhotoTitle_text);
		if(isPhotoFound1==true){
			Assert.assertFalse(isPhotoFound1, " private photo is visible in community page for distributor2.");
			logInfo("private photo is visible in community page for distributor2.");
		}
		boolean isMatchFound1 = cm.verifyPostInMyProfilePage(addPrivatePhotoTitle_text);
		if(isMatchFound1==true){
			Assert.assertFalse(isMatchFound1, addPrivatePhotoTitle_text + " private photo match found in MyProfile page for distributor2.");
			logInfo(addPrivatePhotoTitle_text + " private photo match found in MyProfile page for distributor2.");
		}
		
	}
	
	
	
	@Test(priority= 3710)
	public void verifyPrivateVideo4Distributor() throws Exception{
		logInfo("inside verifyPrivateVideo4Distributor() Test.");
		logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);	
		cm.addCommunityVideo(addPrivateVideoTitle_text,"PRIVATE");
		boolean isVideoFound = cm.verifyPostIsPresent(addPrivateVideoTitle_text);
		if(isVideoFound==true){
			Assert.assertFalse(isVideoFound, " private video should not be visible in community page.");
			logInfo(" private video should not be visible in community page.");
		}
//		boolean isMatchFound = cm.verifyPostInMyProfilePage("Posted A Video: \"" + addPrivateVideoTitle_text + "\"");
		boolean isMatchFound = cm.verifyPostInMyProfilePage("addPrivateVideoTitle_text");

		if(isMatchFound==false){
//			Assert.assertTrue(isMatchFound, addPrivateVideoTitle_text + " private video not found in Myprofile page for distributor1.");
			logInfo(addPrivateVideoTitle_text + " private video not found in Myprofile page for distributor1.");
		}
		
		logOut();
		
		logIn(distributorUser2_text,distributorUser2Pwd_text);	
		System.out.println("logged in as distributor2");
	//	cm.navigate2CommunityPage();
		boolean isVideoFound1 = cm.verifyPostIsPresent(addPrivateVideoTitle_text);
		if(isVideoFound1==true){
			Assert.assertFalse(isVideoFound1, " private video is visible in community page for distributor2.");
			logInfo("private video is visible in community page for distributor2.");
		}
		boolean isMatchFound1 = cm.verifyPostInMyProfilePage(addPrivateVideoTitle_text);
		if(isMatchFound1==true){
			Assert.assertFalse(isMatchFound1, addPrivateVideoTitle_text + " private video match found in MyProfile page for distributor2.");
			logInfo(addPrivateVideoTitle_text + " private video match found in MyProfile page for distributor2.");
		}
		
		logOut();
	}


	
	@Test(priority= 3711)
	public void verifyProducts4Distributor() throws Exception{
		logInfo("inside verifyProducts4Distributor() Test.");
		//	logOut();
/*		tb.adminLogIn();
		sm.navigate2AdminShop();
		sm.addNewProduct(shopProductName_text);
		tb.back2Office();
		logOut();		*/
		logIn(distributorUser1_text,distributorUser1Pwd_text);	
		sm.navigate2ShopPage();
	//	sm.verifyShopProductIsPresent(shopProductName_text);
		sm.verifyShopProductIsPresent("Prod_159");
		
	}
	
	
}
