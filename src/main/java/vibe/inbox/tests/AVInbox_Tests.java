package vibe.inbox.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import vibe.calendar.tests.AVCalendarMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.resourcelibrary.tests.RLMethods;
import vibe.setting.tests.AVSettingsMethods;
import vibe.users.tests.MonatUsersMethods;
import vibe.users.tests.UsersMethods;
import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.TestBase;
import common.readProp;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@Priority(4)
 public class AVInbox_Tests extends AVInboxMethods {

	// public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	 //public static String vibeRecipient_text = "kevin.mcevoy@branch-1-9.vibeoffice.com";
	 public static String vibeRecipient_text = "icentris.vibe001@gmail.com";
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
     readProp prop = new readProp();
 
 	
 	AVCalendarMethods cm = new AVCalendarMethods();
 	AVSettingsMethods sm = new AVSettingsMethods();
 	
 	
 	@Test(priority=401)
		public void sendEmail2GmailUsers() throws Exception{
			logInfo("inside sendEmail2GmailUsers() Test");
			System.out.println("inside sendEmail2GmailUsers() Test");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			composeVibeEmail(gmailId_text2, txtGmailSubject);
			userLogout();
			logOutInAvon();
			loginGmail(gmailId_text2, gmailPwd_text2);
			boolean isMailFound = verifyInboxGmail(txtGmailSubject);
			signoutGmail();
			logIn(adminUser_text, adminPwd_text);
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			if(isMailFound==false){				
				Assert.assertTrue(isMailFound, txtGmailSubject + " email with subject not found in Gmail.");
				
			}
		}
 	
 	@Test(priority=402)
 	 public void emailMaskedVerificationForDVM() throws Exception {
 	 		logInfo("inside emailMaskedVerificationForDVM() Test");
 	 		loginGmail(gmailId_text2, gmailPwd_text2);
 	 		openMailInGmail(txtGmailSubject);
 	 		gmailFromFieldDVM(inboxField);
 	 		signoutGmail();
 			
 			
 	 	}
 	
 	@Test(priority=403)
	 public void emailMaskedVerificationForDSM() throws Exception {
	 		logInfo("inside emailMaskedVerificationForDSM() Test");
	 		loginGmail(gmailId_text2, gmailPwd_text2);
	 		openMailInGmail(txtGmailSubject);
	 		gmailFromFieldDSM(inboxField);
	 		signoutGmail();
	 	}
 	
 	@Test(priority=404)
	 public void emailMaskedVerificationForREP() throws Exception {
	 		logInfo("inside emailMaskedVerificationForREP() Test");
	 		loginGmail(gmailId_text2, gmailPwd_text2);
	 		openMailInGmail(txtGmailSubject);
	 		gmailFromFieldREPID(inboxField);
	 		signoutGmail();
	 	}
 	
 	@Test(priority=405)	
	public void reLogin() throws Exception {
		logInfo("inside reLogin() method");
		userLogout();
		logOutInAvon();
	    logIn(adminUser_text,adminPwd_text);
	    loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		
	}
	
 	
 	
 
 	
 	
 	@Test(priority=406)
    public void replyEmailVerificationForRep() throws Exception{
	logInfo("inside replyEmailVerificationForRep() Test");	

	gmailReplyEmailVerificationForREP(txtGmailSubject);
	
	
}

 	@Test(priority=407)
    public void replyEmailVerificationForDVM() throws Exception{
	logInfo("inside replyEmailVerificationForDVM() Test");	

	gmailReplyEmailVerificationForDVM(txtGmailSubject);
	
	
}

 	@Test(priority=408)
    public void replyEmailVerificationForDSM() throws Exception{
	logInfo("inside replyEmailVerificationForDSM() Test");	

	gmailReplyEmailVerificationForDSM(txtGmailSubject);
	
	
}

 	
	@Test(priority=410)
	public void sendEmailAsAttachment() throws Exception{
		logInfo("inside sendEmailAsAttachment() Test");		
		
		String  txtVibeEmailSubject1 = txtVibeEmailSubject + " file attachment";  // uncomment later
		boolean isMailPresent = false;	   
		composeEmailWithAttachment(gmailId_text2 ,txtVibeEmailSubject1);	
		userLogout();
		//logOut();	
		logOutInAvon();
		loginGmail(gmailId_text2, gmailPwd_text2);
		isMailPresent = openMailInGmail(txtVibeEmailSubject1);  
		if(isMailPresent==true){
			 logInfo(txtVibeEmailSubject1 + " email with attachment  found in Inbox.");
			 Assert.assertTrue(isMailPresent, txtVibeEmailSubject1 + " email with attachment  found in Inbox.");
			 signoutGmail();
			}  else {
				 logInfo(txtVibeEmailSubject1 + " email is not present in Attachment folder.");
				 Assert.assertFalse(isMailPresent, txtVibeEmailSubject1 + " email with attachment not found in Inbox.");
						
			 }
		
		 }	
 	
 	@Test(priority=411)	

	public void reLoginAsUser() throws Exception {
		logInfo("inside reLogin() method");
	    logIn(adminUser_text,adminPwd_text);
	    loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		
	}
 	
 		
 	
	
	@Test(priority=420)  
	public void logOutAsUser() throws Exception{		
		userLogout();			
		
	}
	

	
 	
 	
 	
}
