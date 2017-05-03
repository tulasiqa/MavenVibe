package vibe.inbox.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import vibe.myprofile.tests.MyProfileMethods;
import vibe.resourcelibrary.tests.RLMethods;
import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.TestBase;
import common.readProp;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@Priority(4)
 public class MonatInbox_Tests extends MonatInboxMethods {

	// public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	 public static String vibeRecipient_text = "rajeswaran.srinivasan@monat-stage.vibeoffice.com";
//	 public static String vibeRecipient_text =  "kevin.mcevoy@branch-1-11.vibeoffice.com";
	// public static String vibeRecipient2_text =  getEmail(adminUser1_text,appUrl); 
	 public static String vibeRecipient2_text =  getEmail(distributorUser1_text,appUrl); 
	 public static String vibeRecipient3_text =  getEmail(distributorUser2_text,appUrl); 
     public static String recipientsTwo_text = vibeRecipient_text + "," + vibeRecipient2_text;
     public static String txtImpVibeEmailSubject = "Important_" + TestBase.random();
     public static String txtStarVibeEmailSubject = "Starred_" + TestBase.random();
     public static String txtReadVibeEmailSubject = "ReadUnRead_" + TestBase.random();
     public static String txtTrashVibeEmailSubject = "TrashUntrash_" + TestBase.random();
    
     WidgetsMethods wm = new WidgetsMethods();
     MyProfileMethods profile = new MyProfileMethods();
     readProp prop = new readProp();

     
     //mail from gmail to distributor and verifies in distributor account
     @Test(priority=401)

		public void sendEmailFromGmail() throws Exception{
 	
			logInfo("inside sendEmailFromGmail() Test");
			System.out.println("inside sendEmailFromGmail() Test");
			// setNotifications2User(vibeRecipient_text);
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			profile.navigateMyProfileAccount("Notifications");
			String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
			System.out.println(selfmailId);
			userLogout();
			logOut();
			loginGmail(gmailId_text, gmailPwd_text);
			composeGmail(selfmailId,composeSubject_text,composeDesc_text);
			signoutGmail();
			logIn(adminUser_text,adminPwd_text);
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			go2Inbox();
			boolean isMailFound = selectVibeMailsWithSubject(composeSubject_text);
			if(isMailFound==true){
				deleteFilteredVibeMails(); 
			} else {
				Assert.assertTrue(isMailFound, composeSubject_text + " email with subject not found in vibe Inbox.");
			}
			
		}	
	   
  	   
  	   
  	   @Test(priority=402)
  		public void sendEmail2GmailUsers() throws Exception{
  			logInfo("inside sendEmail2GmailUsers() Test");
  			System.out.println("inside sendEmail2GmailUsers() Test");
  			composeVibeEmail(recipientsGmail_text, txtGmailSubject);
  			userLogout();
  			logOut();
  			loginGmail(gmailId_text, gmailPwd_text);
  			boolean isMailFound = verifyInboxGmail(txtGmailSubject);
  			signoutGmail();
  			logIn(adminUser_text, adminPwd_text);
  			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));

  			if(isMailFound==false){
  				Assert.assertTrue(isMailFound, txtGmailSubject + " email with subject not found in Gmail.");
  			}
  		}
  	   
	//Add Label to emails and verify if label is associated to the email.
		
	@Test(priority=408)
	public void setEmailLabel() throws Exception{
		logInfo("inside setEmailLabel() Test");   
		go2Inbox();
		createLabel(labelName_text);	
		go2Inbox();
		deleteLabel(labelName_text);
		go2Inbox();
		labelNotToPresent(labelName_text);
		
	}
	
	@Test(priority=440)
	public void loginAsAdminFromInbox() throws Exception{
		
		adminLogin();
		
	}
	
	@Test(priority=441)  
	public void logOutAsUser() throws Exception{		
	//adminLogin();			
		userLogout();
	}
	
}
