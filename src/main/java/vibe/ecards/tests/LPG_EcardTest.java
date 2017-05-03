package vibe.ecards.tests;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.reports.tests.ReportsMethods;
import vibe.resourcelibrary2.tests.RL2Methods;
import vibe.shopping.tests.ShoppingMethods;



@Priority(40)
public class LPG_EcardTest extends LPGEcardMethods{

	 String parentEcard = "Happy Birthday";
     String ecardtempName = "Happy Birthday";
     String tagText = "test123";

     String groupMail = "gmail";
     public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	
     InboxMethods inbox = new InboxMethods();
     ReportsMethods report = new ReportsMethods();
     NewsMethods nm = new NewsMethods();
     RL2Methods rl2 = new RL2Methods();
     MyProfileMethods profile = new MyProfileMethods();
     CommunityMethods cm = new CommunityMethods();
     
     @Test(priority=4000)
   	public void createCardCategory() throws Exception{
    	 String childEcard2 = "Ecard Child2 "+TestBase.generateRandomString();
    	 String updateCategory = childEcard2 +" updated";
    	 nav2AdminEcard();    	 
    	 addEcardCategory(parentEcard, "None");
    	 nav2AdminEcard();
    	 addEcardCategory(childEcard, parentEcard);   
    	 nav2AdminEcard();
    	 addEcardCategory(childEcard2, parentEcard);   
    	 validateCategories(parentEcard, childEcard);
    	 boolean isCatPresent = validateCategories(parentEcard, childEcard2);
    	 if (isCatPresent=true){
    		 updateChildCategories(parentEcard, childEcard2, updateCategory);
    		 deleteChildCategories(parentEcard, updateCategory);
    		 NotPresentChildCategories(parentEcard, updateCategory);
    	       }    	 
    	 if (isCatPresent==false){
    		 Assert.assertTrue(isCatPresent, parentEcard+" or "+childEcard + " either or both not present." );
    	 }
      }
     
     
     @Test(priority=4000)
    	public void Avon_createCardCategory() throws Exception{
    	 
     	 nav2AdminEcard();    	 
     	 addEcardCategory(parentEcard, "None");
     	 nav2AdminEcard();
     	 addEcardCategory(childEcard, parentEcard);     	
     	 validateCategories(parentEcard, childEcard);
     	
       }
     
     
 	
     /* 	
      	String parentEcard = "Special Occasions";
         String ecardtempName =  "Happy Birthday";*/     
    // String childEcard =   "Ecard Child 113";
     
     
     
    @Test(priority=4000)
  	public void createNewCard() throws Exception{
    	 logInfo("Entered into createNewCard() test");    	 
    	 
    	 nav2AdminEcard();
    	 createECard(ecardtempName,parentEcard, childEcard);
  	             }	
     
     
     
     //@Test(priority=4000)
 	public void reloginAsAdmin() throws Exception{
 		
 		logIn(adminUser_text,adminPwd_text);  
 	}	

    
	
	
	@Test(priority=4001)
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
		
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		saveCardAs(saveAstempName);
		verifyEcardInSavedEcards(saveAstempName);
	}
	
	@Test(priority=4003)
	public void sendEcardByGroupMail() throws Exception{
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		sendMailToGroup(groupMail);		
	}
	
	@Test(priority=4004)
	public void sendEcardByManuallyMail() throws Exception{			
		
		
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
		System.out.println(selfmailId);		
		nav2Ecard();		
		selectcatNdEcard(parentEcard, ecardtempName);
		enterDetails(ecardCatSubText);
		sendManualMail(selfmailId);	
		inbox.go2Inbox();
		inbox.selectVibeMailsWithSubject(ecardCatSubText);
		inbox.deleteFilteredVibeMails();
		
		}
	
	@Test(priority=4005)
	public void validateAlertOfEcardMails() throws Exception{
		
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
		sendManualMail(vibeRecipient_text);	
		
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
		try{
			//String saveAstempName = "123";
			nav2Ecard();		
			/*selectcatNdEcard(parentEcard, ecardtempName);
			enterDetails(ecardCatSubText);
			saveCardAs(saveAstempName);*/
			deleteEcardInListView(saveAstempName);
			notToPresentSavedEcards(saveAstempName);
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}
		
		
	}
	
	
	@Test(priority=4080)
   	public void deleteParentCategory() throws Exception{
     	 logInfo("Entered into deleteParentCategory() test");     	 
     	 nav2AdminEcard();
     	 deleteParentCategories(parentEcard);
     	 NotPresentParentCategories(parentEcard);
   		
   	}
	
	
	
	
	
	

	

}
