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
 public class Inbox_Tests extends InboxMethods {

	 public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	// public static String vibeRecipient_text = "kevin.mcevoy@branch-1-9.vibeoffice.com";
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
     readProp prop  = new readProp();

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


	// Verify if the count shown for unread mails in the QuickLinks pane is correct.

	@Test(priority=403)
	public void verifyQuickLinks() throws Exception{
		logInfo("inside verifyQuickLinks() Test");
		System.out.println("inside verifyQuickLinks() Test");
		
		 wm.setWidgetStatus("Quick Links Button View",true);
		 wm.removeAllWidgetInHomepage();
		 wm.dragAndDropWidget("Quick Links Button View");
		
		//logIn(adminUser_text, adminPwd_text);
		int quickLinksInboxCnt = getInboxCntInQuickLinksWidget();
		System.out.println("quickLinksInboxCnt = " +quickLinksInboxCnt);
		
		int totalInboxMails = selectVibeMails("All");
		System.out.println("totalInboxMails = " +totalInboxMails);
	
		if(quickLinksInboxCnt==totalInboxMails){
			System.out.println("Inbox count in Quick Links matches with the count of emails in Inbox page.");
			// Assert.assertTrue(totalInboxMails==quickLinksInboxCnt, "Inbox count in Quick Links matches with the count of emails in Inbox page.");
		} else {
			System.out.println("Inbox count in Quick Links does not match with the count of emails in Inbox page.");
			Assert.assertEquals(totalInboxMails,quickLinksInboxCnt, "Inbox count in Quick Links does not match with the count of emails in Inbox page.");
		}
		//logOut();
	}

	
	//  Mark an email as starred and verify if displayed in starred emails section. 
	
	@Test(priority=404)
	public void markEmailAsStarred() throws Exception{
		logInfo("inside markEmailAsStarred() Test");
		
			profile.navigateMyProfileAccount("Notifications");
        String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
        System.out.println(selfmailId);
        
		//System.out.println("vibeRecipient_text = " +vibeRecipient_text);
		composeVibeEmail(selfmailId,txtStarVibeEmailSubject);  
		boolean isMailPresent = false;
		isMailPresent = selectMailsWithSubject(txtStarVibeEmailSubject);
		if(isMailPresent==true){
			markEmailStarred(txtStarVibeEmailSubject);
			logInfo(txtStarVibeEmailSubject + " mail match found in Inbox.");
			selectInboxFolder("Starred");
			boolean isMailStarred = selectMailsWithSubject(txtStarVibeEmailSubject);
			 if(isMailStarred==true){
				 logInfo(txtStarVibeEmailSubject + " email found in Starred folder.");
				 Assert.assertTrue(isMailStarred, txtStarVibeEmailSubject + " email found in Starred folder.");
			 } else {
				 logInfo(txtStarVibeEmailSubject  + " email not found in Starred folder.");
			 }
		} else {
			logInfo(txtStarVibeEmailSubject + " mail match not found in Inbox.");
			Assert.assertTrue(isMailPresent, txtStarVibeEmailSubject + " mail match not found in Inbox.");
		}
	}
	
	
	@Test(priority=405) //, dependsOnMethods={"markEmailAsStarred"})
	public void unmarkStarredEmail() throws Exception{
		logInfo("inside unmarkStarredEmail() Test");
		boolean isMailPresent = false;
			performMoreActionsForSelectedEmails("Mark As Unstarred");
			
			isMailPresent = selectMailsWithSubject(txtStarVibeEmailSubject);
			if(isMailPresent==true){
				logInfo(txtStarVibeEmailSubject + " unstarred email should not be present in starred folder.");
				Assert.assertFalse(isMailPresent, txtStarVibeEmailSubject + " unstarred email should not be present in starred folder.");
			} else {
				selectInboxFolder("Inbox");
				boolean isMailUnstarred = selectMailsWithSubject(txtStarVibeEmailSubject);
				 if(isMailUnstarred==true){
					 logInfo(txtStarVibeEmailSubject + " unstarred email found in Inbox folder.");
					 deleteFilteredVibeMails();
					 
				 } else {
					 logInfo(txtStarVibeEmailSubject + " unstarred email not found in Inbox folder.");
					 Assert.assertTrue(isMailUnstarred, txtStarVibeEmailSubject + " unstarred email not found in Inbox folder.");
				 }
		}
	}
	 	
	//Mark an email as important and verify if displayed in important emails section.

	
	@Test(priority=406)
	public void markEmailAsImportant() throws Exception{
		logInfo("inside markEmailAsImportant() Test");

		profile.navigateMyProfileAccount("Notifications");
    String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
    System.out.println(selfmailId);
		composeVibeEmail(selfmailId,txtImpVibeEmailSubject);  
		boolean isMailPresent = false;
		isMailPresent = selectMailsWithSubject(txtImpVibeEmailSubject);
		if(isMailPresent==true){
			markEmailsImportant(txtImpVibeEmailSubject);
			logInfo(txtImpVibeEmailSubject + " mail match found in Inbox.");
			selectInboxFolder("Important");
			boolean isMailImportant = false;
			isMailImportant = selectMailsWithSubject(txtImpVibeEmailSubject);
			 if(isMailImportant==true){
				 logInfo(txtImpVibeEmailSubject + " email found in Important folder.");
			} else {
				 logInfo(txtImpVibeEmailSubject + " email not found in Important folder.");
				 Assert.assertTrue(isMailImportant, txtImpVibeEmailSubject + " email found in Important folder.");
			 }
		} else {
			logInfo(txtImpVibeEmailSubject + " mail match not found in Inbox.");
			Assert.assertTrue(isMailPresent, txtImpVibeEmailSubject + " mail match not found in Inbox.");
		}
	}
	
	
	@Test(priority=407)   //, dependsOnMethods={"markEmailAsImportant"})
	public void unmarkImportantEmails() throws Exception{
		logInfo("inside unmarkImportantEmails() Test");
		boolean isMailPresent = false;
		   // go2Inbox();
		   // selectInboxFolder("Important");
			performMoreActionsForSelectedEmails("Mark As Unimportant");
			
			isMailPresent = selectMailsWithSubject(txtImpVibeEmailSubject);
			if(isMailPresent==true){
				logInfo(txtImpVibeEmailSubject + " Unimportant email should not be present in Important folder.");
				Assert.assertFalse(isMailPresent, txtImpVibeEmailSubject + " Unimportant email should not be present in Important folder.");
			} else {
				selectInboxFolder("Inbox");
				boolean isMailUnimportant = selectMailsWithSubject(txtImpVibeEmailSubject);
				 if(isMailUnimportant==true){
					 logInfo(txtImpVibeEmailSubject + " Unimportant email found in Inbox folder.");
					 deleteFilteredVibeMails();
				 } else {
					 logInfo(txtImpVibeEmailSubject + " Unimportant email not found in Inbox folder.");
					 Assert.assertTrue(isMailUnimportant, txtImpVibeEmailSubject + " Unimportant email not found in Inbox folder.");
				 }
		}
	}
	 	

	//Add Label to emails and verify if label is associated to the email.
		
	@Test(priority=408)
	public void setEmailLabel() throws Exception{
		logInfo("inside setEmailLabel() Test");
		// tb.logIn(adminUser_text, adminPwd_text);
		profile.navigateMyProfileAccount("Notifications");
	    String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
	    System.out.println(selfmailId);
		composeVibeEmail(selfmailId,txtVibeEmailSubject);  
		go2Inbox();
		createLabel(labelName_text);
		selectVibeMailsWithSubject(txtVibeEmailSubject);
		applyLabel(labelName_text);
		boolean isLabelFound = verifyLabeledEmail(txtVibeEmailSubject);
		if(isLabelFound){
			deleteFilteredVibeMails();
			deleteLabel(labelName_text);
			Thread.sleep(5000);
		}
		// tb.logOut();
	}
			
			
	@Test(priority=409)
	public void sendEmail2VibeUserwithResourceAttachment() throws Exception{
		logInfo("inside sendEmail2VibeUserwithResourceAttachment() Test");
		System.out.println("inside sendEmail2VibeUserwithResourceAttachment() Test");
		
	//	SmokeRL_Tests rlt = new SmokeRL_Tests();
	//	String vibeRecipient2_text = "ramesh.buridi@myyevo.com";
	//	logIn(adminUser_text, adminPwd_text);
	//	rlt.createResource();						// comment when running with RL and Inbox scripts together.
	//	rl.back2Office();
	//	go2Inbox();
	//	composeVibeEmailwithRescAttachment(vibeRecipient2_text,parentCategory_text,newResourceTitle_text);
		
		go2Inbox();  // String recipients, String subject, String categoryName, String resourceName)
		composeVibeEmailwithRescAttachment(vibeRecipient2_text, "resource attached", "PCAT001","T2");
		logOut();
	//	loginVibe(adminUser1_text, adminPwd1_text);
		logIn(adminUser1_text, adminPwd1_text);
		selectVibeMailsWithSubject(subject_Mail_text);
		deleteFilteredVibeMails();
	//	logOut();
	}
	
	
	@Test(priority=410)
	public void sendEmail2otherVibeUser() throws Exception{
		logInfo("inside sendEmail2otherVibeUser() Test");
		boolean isMailPresent = false;
		composeVibeEmail(vibeRecipient2_text,txtVibeEmailSubject);
	//	logOut();
	//	logIn(adminUser1_text, adminPwd1_text);
		loginAsUser(distributorUser1_text);
		setNotifications2User(vibeRecipient2_text);
		isMailPresent = selectVibeMailsWithSubject(txtVibeEmailSubject);
		if(isMailPresent){
			logInfo(txtVibeEmailSubject + " email present in Inbox");
			deleteFilteredVibeMails();
			go2Homepage();
			logOut();
			go2Homepage();
		} else {
			logInfo(txtVibeEmailSubject + " email not present in Inbox");
			logOut();
			go2Homepage();
			Assert.assertTrue(isMailPresent, txtVibeEmailSubject + " email not present in Inbox");
		}
		// logIn(adminUser_text, adminPwd_text);
	}
     
	
	@Test(priority=411)
	public void show10EmailsPerpage() throws Exception{
		logInfo("inside show10EmailsPerpage() Test");
		
		go2Inbox();
		int actEmailsCnt = 0;
		actEmailsCnt = countEmailsInInboxPage("10 emails per page","All");
		System.out.println("Total emails = " +actEmailsCnt);
		//Assert.assertEquals(actEmailsCnt, 10);
		
	}
	
	@Test(priority=412)
	public void show25EmailsPerpage() throws Exception{
		logInfo("inside show25EmailsPerpage() Test");
		
		go2Inbox();
		int actEmailsCnt = 0;
		actEmailsCnt = countEmailsInInboxPage("25 emails per page","All");
		System.out.println("Total emails = " +actEmailsCnt);
		//Assert.assertEquals(actEmailsCnt, 25);
		
	}
	
		
	@Test(priority=413)
	public void show50EmailsPerpage() throws Exception{
		logInfo("inside show50EmailsPerpage() Test");
		
		go2Inbox();
		//setMailsPerPage("50 emails per page");
		int actEmailsCnt = 0;
		actEmailsCnt = countEmailsInInboxPage("50 emails per page","All");
		System.out.println("Total emails = " +actEmailsCnt);
		//Assert.assertEquals(actEmailsCnt, 50);
		
	}
	
	
	@Test(priority=414)
	public void show100EmailsPerpage() throws Exception{
		logInfo("inside show100EmailsPerpage() Test");
		
		go2Inbox();
		// setMailsPerPage("100 emails per page");
		int actEmailsCnt = 0;
		actEmailsCnt = countEmailsInInboxPage("100 emails per page","All");
		System.out.println("Total emails = " +actEmailsCnt);
		//Assert.assertEquals(actEmailsCnt, 100);
	}
	
	
	@Test(priority=415)
	public void searchInboxMail() throws Exception{
		logInfo("inside searchInboxMail() Test");
		
		composeVibeEmail(vibeRecipient_text,txtVibeEmailSubject); 
	    setMailsPerPage("100 emails per page");
	    searchEmail(txtVibeEmailSubject);
	    boolean isMailPresent = false;
	    isMailPresent = selectMailsWithSubject(txtVibeEmailSubject);
	    if(isMailPresent){
	    	logInfo(txtVibeEmailSubject + " email with subject found in the search");
	    	deleteFilteredVibeMails();
	    } else {
	    	logInfo(txtVibeEmailSubject + " email with subject not found in the search");
	    	Assert.assertTrue(isMailPresent, txtVibeEmailSubject + " email with subject not found in the search");
	    }
	}
	
	
	@Test(priority=416)
	public void markEmailAsRead() throws Exception{
		logInfo("inside markEmailAsRead() Test");
		
		composeVibeEmail(vibeRecipient_text,txtReadVibeEmailSubject);  
	
		boolean isMailPresent = false;
		isMailPresent = selectMailsWithSubject(txtReadVibeEmailSubject);
		if(isMailPresent==true){
			logInfo(txtReadVibeEmailSubject + " mail match found.");
			viewEmailsWithSubject(txtReadVibeEmailSubject);				// view email
			boolean isMailRead = false;
			go2Inbox();
			 isMailRead = verifyEmailsCheckedForFilter(txtReadVibeEmailSubject,"Read");
			 System.out.println("is mail read = " +isMailRead);
			 if(isMailRead==false){
				 logInfo(txtReadVibeEmailSubject + " email not marked as Read.");
				 Assert.assertTrue(isMailRead, txtReadVibeEmailSubject + " email not marked as Read.");
			 } else {
				 logInfo(txtReadVibeEmailSubject + " email marked as Read.");
			 }
		} else {
			logInfo(txtReadVibeEmailSubject + " mail match not found.");
			Assert.assertTrue(isMailPresent, txtReadVibeEmailSubject + " mail match not found.");
		}
	}
	
	
	
	@Test(priority=417) //  , dependsOnMethods={"markEmailAsRead"}) 
	public void markEmailAsUnread() throws Exception{
		logInfo("inside markEmailAsUnread() Test");
		boolean isMailFound = false;
		
		go2Inbox();	
		isMailFound = selectMailsWithSubject(txtReadVibeEmailSubject);
				
		if(isMailFound==false){
				 logInfo(txtReadVibeEmailSubject + " email not found in Inbox.");
				 Assert.assertTrue(isMailFound, txtReadVibeEmailSubject + " email not found in Inbox.");
			 } else {
				 performMoreActionsForSelectedEmails("Mark As Unread");
				 logInfo(txtReadVibeEmailSubject + " email marked as Unread.");
				 boolean isMailUnread = verifyEmailsCheckedForFilter(txtReadVibeEmailSubject,"Unread");
				 if(isMailUnread==false){
					 logInfo(txtReadVibeEmailSubject + " email could not be found in Unread emails.");
					 Assert.assertTrue(isMailUnread, txtReadVibeEmailSubject + " email could not be found in Unread emails.");
				 } else {
					 logInfo(txtReadVibeEmailSubject + " email found in Unread emails.");
					 go2Inbox();
					 selectMailsWithSubject(txtReadVibeEmailSubject);
					 deleteFilteredVibeMails();
				 }
		 }
	} 
	
	
	@Test(priority=418)
	public void verifyEmailInSentbox() throws Exception{
		logInfo("inside verifyEmailInSentbox() Test");
		boolean isMailSent = false;
		//String txtVibeEmailSubject = "Vibe_Mail_656";
		composeVibeEmail(vibeRecipient_text,txtVibeEmailSubject);  
		go2Inbox();
		selectInboxFolder("Sent");
		isMailSent = selectMailsWithSubject(txtVibeEmailSubject);
		if(isMailSent==false){
				 logInfo(txtVibeEmailSubject + " email not visible in Sent folder.");
				 Assert.assertTrue(isMailSent, txtVibeEmailSubject + " email not visible in Sent folder.");
			 } else {
				 logInfo(txtVibeEmailSubject + " email is visible in Sent folder.");
				 deleteFilteredVibeMails();
		 }
	}
	
	
	
	@Test(priority=419)
	public void deleteEmail2Trash() throws Exception{
		logInfo("inside deleteEmail2Trash() Test");
		
		composeVibeEmail(vibeRecipient_text,txtTrashVibeEmailSubject);  
		
		boolean isMailFound = selectMailsWithSubject(txtTrashVibeEmailSubject);
		if(isMailFound==false){
				 logInfo(txtTrashVibeEmailSubject + " email not visible in Inbox.");
				 Assert.assertTrue(isMailFound, txtTrashVibeEmailSubject + " email not visible in in Inbox.");
			 } else {
				 logInfo(txtTrashVibeEmailSubject + " email is visible in Inbox.");
				 clickOnElement("xpath",btnDeleteInboxMail);
				 selectInboxFolder("Trash");
				 boolean isMailFoundInTrash = selectMailsWithSubject(txtTrashVibeEmailSubject);;
				 if(isMailFoundInTrash==false){
					 logInfo(txtTrashVibeEmailSubject + " email not visible in Trash folder.");
					 Assert.assertTrue(isMailFoundInTrash, txtTrashVibeEmailSubject + " email not visible in Trash folder.");
				 } else {
					 logInfo(txtTrashVibeEmailSubject + " email is visible in Trash folder.");
			 }
		 }
	}
	
	
	@Test(priority=420)  // , dependsOnMethods={"deleteEmail2Trash"})
	public void undeleteEmailFromTrash() throws Exception{
		logInfo("inside undeleteEmailFromTrash() Test");
		
		// go2Inbox();
		// selectInboxFolder("Trash");
		
		clickOnElement("xpath",btnDeleteInboxMail);
		// ConfirmationMessage("Messages restored successfully.");
		selectInboxFolder("Inbox");
		boolean isMailFound = selectMailsWithSubject(txtTrashVibeEmailSubject);
		if(isMailFound==false){
				 logInfo(txtTrashVibeEmailSubject + " undelete emails could not be restored.");
				 Assert.assertTrue(isMailFound, txtTrashVibeEmailSubject + " undelete emails could not be restored.");
			 } else {
				 logInfo(txtTrashVibeEmailSubject + " undelete emails restored successfully.");
				 deleteFilteredVibeMails();
			 }
	}
	
	
	@Test(priority=421)
	public void deleteEmailForEver() throws Exception{
		logInfo("inside deleteEmailForEver() Test");
		
		composeVibeEmail(vibeRecipient_text,txtTrashVibeEmailSubject);  
		boolean isMailFound = selectMailsWithSubject(txtTrashVibeEmailSubject);
		if(isMailFound==true){
			 clickOnElement("xpath",btnDeleteInboxMail);
			 selectInboxFolder("Trash");
			boolean isMailFoundInTrash = selectMailsWithSubject(txtTrashVibeEmailSubject);
			if(isMailFoundInTrash==true){
				clickOnElement("xpath",btnDeleteForEver);
				logInfo("clicked on Delete For Ever button");
				Thread.sleep(5000);
				boolean isMailDeletedInTrash = selectMailsWithSubject(txtTrashVibeEmailSubject);
				if(isMailDeletedInTrash==true){
						 logInfo(txtTrashVibeEmailSubject + " deleted emails still seen in Trash folder.");
						 Assert.assertFalse(isMailDeletedInTrash, txtTrashVibeEmailSubject + " deleted emails still seen in Trash folder.");
					 } 
				
				selectInboxFolder("Inbox");
				boolean isMailDeletedInInbox = selectMailsWithSubject(txtTrashVibeEmailSubject);
				if(isMailDeletedInInbox==true){
						 logInfo(txtTrashVibeEmailSubject + " deleted emails still seen in Inbox folder.");
						 Assert.assertFalse(isMailFound, txtTrashVibeEmailSubject + " deleted emails still seen in Inbox folder.");
					 } 
				
			} else {
				Assert.assertTrue(isMailFoundInTrash, txtTrashVibeEmailSubject + " deleted email not found in Trash folder.");
			}
		
		} else {
			Assert.assertTrue(isMailFound, txtTrashVibeEmailSubject + " email not found in Inbox folder.");
		}
	}
	
	
	@Test(priority=422)
	public void createDuplicateLabels() throws Exception{
		logInfo("inside createDuplicateLabels() Test");
		go2Inbox();
		createLabel(labelName_text);
		createLabel(labelName_text);
		deleteLabel(labelName_text);
	}
	
	

	@Test(priority=423)
	public void reply2Users() throws Exception{
		logInfo("inside reply2Users() Test");
		boolean isMailPresent = false;
		composeVibeEmail(vibeRecipient2_text,txtVibeEmailSubject);
	//	logOut();
	//	logIn(adminUser1_text, adminPwd1_text);
		loginAsUser(distributorUser1_text);
		setNotifications2User(vibeRecipient2_text);
		isMailPresent = selectVibeMailsWithSubject(txtVibeEmailSubject);
		if(isMailPresent){
			logInfo(txtVibeEmailSubject + " email present in Inbox");
			viewEmailsWithSubject(txtVibeEmailSubject);
			submitReply(txtVibeEmailSubject + " replied");
			go2Homepage();
			logOut();
			go2Homepage();
			boolean isReplyMailPresent = selectVibeMailsWithSubject(txtVibeEmailSubject + " replied");
			if(isMailPresent){
				logInfo(txtVibeEmailSubject + " replied" + " present in Inbox");
				deleteFilteredVibeMails(); 
			} else {
				logInfo(txtVibeEmailSubject + " replied" + " not present in Inbox");
				Assert.assertTrue(isReplyMailPresent, txtVibeEmailSubject + " replied" + " not present in Inbox");
			}
		} else {
			logInfo(txtVibeEmailSubject + " email not present in Inbox");
			logOut();
			go2Homepage();
			Assert.assertTrue(isMailPresent, txtVibeEmailSubject + " email not present in Inbox");
		}
		// logIn(adminUser_text, adminPwd_text);
	}
	
	
	
	@Test(priority=424)
	public void sendEmailWithNoData() throws Exception{
		logInfo("inside sendEmailWithNoData() Test");
		
		String errMsg = composeEmailWithNoData();
		System.out.println("errMsg = " +errMsg);

		// confirmEventDeleteModal();

		String expMsg = "Please add * recipient to your message.";
		Assert.assertEquals(errMsg, expMsg);
	}
	
	
	@Test(priority=425)
	public void sendEmailAsAttachment() throws Exception{
		logInfo("inside sendEmailAsAttachment() Test");
			
		 txtVibeEmailSubject = txtVibeEmailSubject + " file attachment";  // uncomment later
	
		boolean isMailPresent = false;
		composeEmailWithAttachment(vibeRecipient_text ,txtVibeEmailSubject);
		isMailPresent = selectVibeMailsWithSubject(txtVibeEmailSubject);
		if(isMailPresent==false){
			 logInfo(txtVibeEmailSubject + " email with attachment not found in Inbox.");
			 Assert.assertTrue(isMailPresent, txtVibeEmailSubject + " email with attachment not found in Inbox.");
		 } else {
			 logInfo(txtVibeEmailSubject + " email found in Inbox.");
			 selectInboxFolder("Attachments");
			 boolean isAttachmentFound = verifyAttachmentIsPresent(vibeRecipient_text);
			 if(isAttachmentFound==false){
				 logInfo(vibeRecipient_text + " email is not present in Attachment folder.");
				 Assert.assertTrue(isAttachmentFound, vibeRecipient_text + " email is not present in Attachment folder.");
			 } else {
				 logInfo(vibeRecipient_text + " email is present in Attachment folder.");
				 
				 boolean isFileDownloaded =  downloadAttachment(vibeRecipient_text);
				 if(isFileDownloaded==false){
					 logInfo("file could not be downloaded successfully in c:\\vibe\\downloads folder.");
					 Assert.assertTrue(isFileDownloaded, "file could not be downloaded successfully in c:\\downloads folder.");
				 }
					 
			 }
			 
			 
			 // deleteFilteredVibeMails(); 
		 }
	}
	
	
	@Test(priority=426)
	public void unsubscribeUsers() throws Exception{
		logInfo("inside unsubscribeUsers() Test");
		boolean isMailPresent = false;
	//	logOut();
	//	logIn(adminUser1_text, adminPwd1_text);
		
		loginAsUser(distributorUser1_text);
		setNotifications2User(vibeRecipient2_text);
		composeVibeEmail(vibeRecipient_text,txtVibeEmailSubject);
		go2Homepage();
		logOut();
		go2Homepage();
		isMailPresent = selectVibeMailsWithSubject(txtVibeEmailSubject);
		if(isMailPresent){
			logInfo(txtVibeEmailSubject + " email present in Inbox");
			viewEmailsWithSubject(txtVibeEmailSubject);
				unsubscribeEmail(vibeRecipient_text, "Optout", vibeRecipient2_text);
				go2Inbox();
				loginAsUser(distributorUser1_text);
				composeVibeEmail(vibeRecipient_text,txtVibeEmailSubject + " opt-out");
				go2Homepage();
				logOut();
				go2Homepage();
				boolean isMailPresentAgain = selectVibeMailsWithSubject(txtVibeEmailSubject + " opt-out");
				System.out.println("opt-out settings =" +isMailPresentAgain);
				if(isMailPresentAgain){
					logInfo(txtVibeEmailSubject + " opt-out" + " email from unsubscribed users should not be received.");
					Assert.assertFalse(isMailPresentAgain, txtVibeEmailSubject + " opt-out" + " email from unsubscribed users should not be received.");
					
				} else {
					logInfo(txtVibeEmailSubject + " opt-out" + " email not present in Inbox");
					 deleteFilteredVibeMails(); 
				}
				
			}
	}
	
	
	

	@Test(priority=427)
	public void forwardEmail2Users() throws Exception{
		logInfo("inside forwardEmail2Users() Test");
		boolean isMailPresent = false;
	  
		loginAsUser(distributorUser1_text);
		setNotifications2User(vibeRecipient2_text);
		composeVibeEmail(vibeRecipient_text,txtVibeEmailSubject);
		go2Homepage();
		logOut();
		go2Homepage();
		isMailPresent = selectVibeMailsWithSubject(txtVibeEmailSubject);
		if(isMailPresent){
			logInfo(txtVibeEmailSubject + " email present in Inbox");
			viewEmailsWithSubject(txtVibeEmailSubject);
			forwardEmail(txtVibeEmailSubject + " forwarded", vibeRecipient3_text);
			go2Homepage();
			loginAsUser(distributorUser2_text);
			boolean isForwardMailPresent = selectVibeMailsWithSubject(txtVibeEmailSubject + " forwarded");
			if(isForwardMailPresent){
				logInfo(txtVibeEmailSubject + " forwarded" + " present in Inbox");
				deleteFilteredVibeMails(); 
			} else {
				logInfo(txtVibeEmailSubject + " forwarded" + " not present in Inbox");
				Assert.assertTrue(isForwardMailPresent, txtVibeEmailSubject+ " forwarded" + " not present in Inbox");
			}
		} else {
			logInfo(txtVibeEmailSubject + " email not present in Inbox");
			logOut();
			go2Homepage();
			// Assert.assertTrue(isMailPresent, txtVibeEmailSubject + " email not present in Inbox");
		}
		// logIn(adminUser_text, adminPwd_text);
	}
	
	
	
	
	@Test(priority=415)
 	public void searchInboxMailForInvalidData() throws Exception{
 		logInfo("inside searchInboxMailForInvalidData() Test");
 		String txtVibeEmailInvalidSubject = "abcd@gmail.com";
 		go2Inbox();
 	    searchEmail(txtVibeEmailInvalidSubject);
 	    String noEmailMsg = getTextPresentOnElement("xpath",inboxNoEmails);
	    Assert.assertEquals(noEmailMsg, "You Don't Have Any Messages");
 	   
 	}
	
	
     

 	@Test(priority=425)
 	public void emailWithSingleAttachment() throws Exception{
 		logInfo("inside emailWithSingleAttachment() Test");
 		String vibeRecipient_text = "kevin.mcevoy@branch-1-11.vibeoffice.com";
 		
 		/*	
 		 txtVibeEmailSubject = txtVibeEmailSubject + " single file attachment";  // uncomment later
 		
 		
 		boolean isMailPresent = false;
 		composeEmailWithAttachment(vibeRecipient_text ,txtVibeEmailSubject);
 		isMailPresent = selectVibeMailsWithSubject(txtVibeEmailSubject);
 		if(isMailPresent==false){
 			 logInfo(txtVibeEmailSubject + " email with attachment not found in Inbox.");
 			 Assert.assertTrue(isMailPresent, txtVibeEmailSubject + " email with attachment not found in Inbox.");
 		 } else {
 			 logInfo(txtVibeEmailSubject + " email found in Inbox.");
 			 selectInboxFolder("Attachments");
 			 boolean isAttachmentFound = verifyAttachmentIsPresent(vibeRecipient_text);
 			 if(isAttachmentFound==false){
 				 logInfo(vibeRecipient_text + " email is not present in Attachment folder.");
 				 Assert.assertTrue(isAttachmentFound, vibeRecipient_text + " email is not present in Attachment folder.");
 			 } else {
 				 logInfo(vibeRecipient_text + " email is present in Attachment folder.");
 				 */
 				
 		  		 go2Inbox();
 				 selectInboxFolder("Attachments");
 				 boolean isFileDownloaded =  downloadAttachment(vibeRecipient_text);
 				 if(isFileDownloaded==false){
 					 logInfo("file could not be downloaded successfully in c:\\vibe\\downloads folder.");
 					 Assert.assertTrue(isFileDownloaded, "file could not be downloaded successfully in c:\\downloads folder.");
 				 }
 					 
 		//	 }
 			 
 				// deleteFilteredVibeMails(); 
 			 
 		// }
 	}
 	

 	@Test(priority=426)
 	public void createSignature() throws Exception{
 		logInfo("inside createSignature method");
 		addSignature(SignatureName);
 		boolean isSignatureFound = 	makeSignatureasDefault(SignatureName);
 		if(isSignatureFound==false){
				 logInfo("Unable to add signature");
				 Assert.assertTrue(isSignatureFound, "Unable to add signature");
			 }
	
 	}

 	@Test(priority=427)
 	public void verifyCreatedSignatureInNewEmail() throws Exception{
 		logInfo("inside createSignature method");
 		boolean isDeafultSingatureFound = 	verifyAddedSignatureInNewEmail(composeBodySignatureText);
 		back2Office();
 		//deleteDefaultSingature(SignatureName);
 		if(isDeafultSingatureFound==false){
			 logInfo("Unable to see  signature in the ck editor");
			 Assert.assertTrue(isDeafultSingatureFound, "Unable to see signature");
		 }

	
 	}
 	
 	
 	

 	@Test(priority=428)
 	public void logoutUserFromInbox() throws Exception{
 		logInfo("Inside logoutUserFromInbox method");
 		userLogout();
 		
 	}
 	
	
}
