package vibe.ecards.tests;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.contacts.tests.BusinessContactsMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.reports.tests.ReportsMethods;
import vibe.resourcelibrary2.tests.RL2Methods;
import vibe.shopping.tests.ShoppingMethods;
import vibe.users.tests.UsersMethods;



@Priority(40)
public class AvonEcardTest extends EcardMethods{

   
 	String parentEcard ="Blank Letterhead";
    String ecardtempName ="Embedded video";
    String tagText = "test123";   
	 //"cardsGroup"+TestBase.generateRandomString();
	  
    String groupMail = "TestGroup_702";
     String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
     
     
     InboxMethods inbox = new InboxMethods();
     ReportsMethods report = new ReportsMethods();
     NewsMethods nm = new NewsMethods();
     RL2Methods rl2 = new RL2Methods();
     MyProfileMethods profile = new MyProfileMethods();
     CommunityMethods cm = new CommunityMethods();
     readProp prop = new readProp();
     BusinessContactsMethods bcm = new BusinessContactsMethods();

 	
  // Create parentCat, two ecards,  update one ecard , delete 1 card and verify deleted card.
     @Test(priority=4001)
   	public void createCardCategory() throws Exception{    	 
    	 logInfo("Entered into createCardCategory() test");    	 
    	 System.out.println(parentEcard);
    	 System.out.println(childEcard);
    	
    	 nav2AdminEcard();
    	 addEcardCategory(parentEcard, "None");
    	 nav2AdminEcard();
    	 addEcardCategory(childEcard, parentEcard);         
    	 validateCategories(parentEcard, childEcard);    	
      }
       
     @Test(priority=4002)
   	public void createNewCard() throws Exception{
     	 logInfo("Entered into createNewCard() test");    	 
     	 
     	 nav2AdminEcard();
     	 createECard(ecardtempName,parentEcard, childEcard);
   	     }	
     
 
    
     // navigate to office and verify the created Cat and Ecard
	@Test(priority=4003)
	public void verifyCardInOffice() throws Exception{	
		logInfo("Entered into verifyCardInOffice() test");		
		nav2Ecard();
		verifyCategoryName(parentEcard);
		selectcatNdEcard(parentEcard, childEcard);		
	}
     
     
     
 
    
	@Test(priority=4002)
	public void verifyexistingEcardcategory() throws Exception{	
		logInfo("Entered into verifyexistingEcardcategory() test");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		nav2Ecard();
		verifyCategoryName(parentEcard);		
		selectcatNdEcard(parentEcard, ecardtempName);
		
	}
	
	@Test(priority=4002)
	public void createNdVerifySavedEcard() throws Exception{	
		logInfo("Entered into createNdVerifySavedEcard() test");
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		saveCardAs(saveAstempName);
		verifyEcardInSavedEcards(saveAstempName);
	}
	
	@Test(priority=4003)
	public void sendEcardByGroupMail() throws Exception{
		logInfo("Entered into sendEcardByGroupMail() test");
		//bcm.addGroup(groupMail);
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		sendMailToGroup(groupMail);		
	}
	
	@Test(priority=4004)
	public void sendEcardByManuallyMail() throws Exception{		
		logInfo("Entered into sendEcardByManuallyMail() test");
		
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
		System.out.println(selfmailId);		
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		sendManualMail(selfmailId,ecardCatSubText2);			
	//	nm.logOutInAvon();
		}
	
	@Test(priority=4005)
	public void validateAlertOfEcardMails() throws Exception{
		logInfo("Entered into validateAlertOfEcardMails() test");		
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		validationMails(parentEcard);	
		
	}
	
	@Test(priority=4006)
	public void validateListndGridViews() throws Exception{
		
		nav2Ecard();
		selectcategory(parentEcard);
		gridNdLsitView();	
		
	}
	
	@Test(priority=4007)
	public void validateListndGridViewsInSavedCards() throws Exception{
	
		nav2Ecard();
		gridNdLsitViewInSavedCards(saveAstempName);
		
	}
	
	
	
	
	@Test(priority=4008)
	public void sendEcardBymultiManuallyMails() throws Exception{	
	
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		sendManualMailToManyRecepients(vibeRecipient_text,4);	
		
		
	}
	
	@Test(priority=4009)
	public void sendEcardToBothManuallyNdGroupRecepients() throws Exception{	
		
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);	
		enterDetails(ecardCatSubText);	
		sendMailToGroupNdManual(vibeRecipient_text,4, groupMail);
		
	}	
	
	@Test(priority=4010)
	public void verifyEmailSubjectOnLeftSide() throws Exception{	
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		validateSubject(ecardCatSubText2);	
		
		
	}
	
	@Test(priority=4011)
	public void verifySubjectBVA() throws Exception{	
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		validateSubjectLength();	
	}
	
	@Test(priority=4012)
	public void validateListndGridViewsEcardSizes() throws Exception{		
		nav2Ecard();
		selectcategory(parentEcard);
		gridNdLsitViewEcardSizes();	
		
	}
	
	@Test(priority=4013)
	public void validateEcardEmails() throws Exception{		
		nav2Ecard();
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);	
		validateMail(vibeRecipient_text);
		
		
	}
	
	@Test(priority=4014)
	public void validateFieldsOfEcard() throws Exception{		
		nav2Ecard();
		assertPageObjects(parentEcard, ecardtempName);	
		
	}
	
	
	@Test(priority=4015)
	public void validateEcardBySearch() throws Exception{
		nav2Ecard();
		searchEcard(ecardtempName);
		verifyEcard(ecardtempName);		
	}
	
	
	@Test(priority=4042)
	public void sendEcardFromReports() throws Exception{	
		
		nm.loginAsSubUser("1003");   // Called this method due to no downline users are available
		
		report.navigateReports();			
		report.reportstable("Downline Search") ;
		report.exportList("Send Ecard");
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		sendManualMail(vibeRecipient_text,ecardCatSubText2);	
		
	}
	
	//@Test(priority=4017)
	public void validateFilterOptions() throws Exception{		
		
		// to be implement
			
		
	}
	
	//@Test(priority=4018)
	public void sendEcardFromCalender() throws Exception{		
		
		// to be implement
			
		
	}
	

	@Test(priority=4019)
	public void shareEcardInsocialSites() throws Exception{	
		nav2Ecard();
		editEcardInListView(saveAstempName);
		shareInSocialSites();		
		rl2.selectFBIcon();
	    sm.shareInFaceBook();	    
	    rl2.selectTweetIcon();      
	    sm.shareInTwitter();    
	    rl2.closeModalWindow();			
	}
	
	@Test(priority=4020/*, dependsOnMethods = {"shareEcardInsocialSites"}*/)
	public void verifyEcardInFaceBook() throws Exception{		
		//String  saveAstempName = 	"Festival 253";
		profile.login2FBVerifyPostedDetails();  
		profile.getPostsFromFBOfFirstPart(saveAstempName) ; 
		
		
	}
	
	@Test(priority=4021, dependsOnMethods = {"shareEcardInsocialSites"})
	public void verifyEcardInTwitter() throws Exception{			
	 profile.login2Twitter() ;
   	 profile.verifyPostsInTwitter(saveAstempName);	
   	 logIn(adminUser_text,adminPwd_text);
		
	}
	
	@Test(priority=4022)
	public void relogin() throws Exception{
		
		logIn(adminUser_text,adminPwd_text);  
	}	
	
  
   
	
	@Test(priority=4023)
	public void verifyEcardInCommunity() throws Exception{
		
		nav2Ecard();
		editEcardInListView(saveAstempName);
		shareIncommunity(commEcardShare);
		cm.navigate2CommunityPage();		
		cm.isActivitypresentInMyRecentActivities(commEcardShare);	
		
	}
	
	//String saveAstempName = "Ecard 39";
	
	@Test(priority=4024)
	public void validateEcardInWebsite() throws Exception{
		nav2Ecard();
		editEcardInListView(saveAstempName);
		shareInWebsite();
		
		
	}
	
	@Test(priority=4025)
	public void validateEcardBasedOnKeywordSearch() throws Exception{
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		saveCardAs(saveAstempName);
		nav2Ecard();	
		searchEcard(ecardCatSubText);
		verifyEcard(saveAstempName);
	}
	
	@Test(priority = 4030)
	public void deleteSavedCard() throws Exception{
		//String saveAstempName = "123";
		nav2Ecard();		
		/*selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		saveCardAs(saveAstempName);*/
		deleteEcardInListView(saveAstempName);
		notToPresentSavedEcards(saveAstempName);
		
	}
	
	
	@Test(priority = 4031)
	public void ecardHistory() throws Exception{
		
		String history = "Ecard History";
		String past7 = "Past 7 Days";
		String pastMon = "Past Month";
		String past3Mon = "Past 3 Months";
		String past6Mon = "Past 6 Months";				

		nav2Ecard();		
		selectHistory(history);
		selectHistoryList(past7);		
		verfiyHistoryCard(ecardtempName);
		
		selectHistoryList(pastMon);		
		verfiyHistoryCard(ecardtempName);
		
		selectHistoryList(past3Mon);		
		verfiyHistoryCard(ecardtempName);
		
		selectHistoryList(past6Mon);		
		verfiyHistoryCard(ecardtempName);
		
		
		
	}
	
	
	@Test(priority=4032)  
	public void logOutAsUser() throws Exception{		
		userLogout();			
		
	}
	
	
	
	
	
	
	@Test(priority=4080)
   	public void deleteParentCategory() throws Exception{
     	 logInfo("Entered into deleteParentCategory() test");     	 
     	 nav2AdminEcard();
     	 deleteParentCategories(parentEcard);
     	 NotPresentParentCategories(parentEcard);
   		
   	}
	
	
	
	
	
	

	

}
