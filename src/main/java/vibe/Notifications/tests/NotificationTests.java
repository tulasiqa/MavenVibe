package vibe.Notifications.tests;


import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.openqa.selenium.By;



import common.Priority;
import common.readProp;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.myprofile.tests.MyProfileMethods;



@Priority(12)
public class NotificationTests extends NotificationMethods{
	
	
	readProp prop = new readProp();
	MyProfileMethods profile = new MyProfileMethods();
	InboxMethods inbox = new InboxMethods();
	
	
	@Test(priority=1200)
	public void loginAsUserFromNotifications() throws Exception{

		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
	
	}
	
	@Test(priority=1250)
	public void loginAsAdminFromNotifications() throws Exception{
		
	 // adminLogin();	
		userLogout();
		
	}
	
	@Test(priority=1201)
	public void Notification_ValidateTitlesCounts() throws Exception{		
		 logInfo("Validate Panel Titles, Alert,Notification, All counts");	
		 loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		 navAlerts();
		 getPanelTitle(panelTitleText2);
		 navMailNotifications();
		 getPanelTitle(panelTitleText1);			
		 getListOfPriority();
		 selectPriority(prMed);
		 allMessageHeadersInPanel();
	
		
	}

	@Test(priority=1202)
    public void notification_HandleMailsAlerts() throws Exception{
		 logInfo("Alerts : Select Alerts Icon and expand it, Get All list of Tools and Priorties from Left Pane.");
		 navMailNotifications();
		 getCountOfAll();
		 getCountOfAlerts();
		 getCountOfMails();
		 getListOfNotifactionPanel();
		 getListOfPriority();	
		  
		  
		  /*.selectNotifactionPanel(toolsMsgRecipient)		 
		  .replyToMailFromPanel();	*/
		  
		  
		  ;
		}
	
	@Test(priority=1203)	
    public void notification_VerifyEmailAndReplyThroughTools() throws Exception{
		 logInfo("Access Notifications, and select 'Messsage' from Leftpanel. \n"
				+ "Based on message content, Select email and reply to mailer. ");
		//String selfmailId= "rameshburidi@master.vibeoffice.com";
		 profile.navigateMyProfileAccount("Notifications");
         String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
         System.out.println(selfmailId);
			
         inbox.composeVibeEmail(selfmailId, txtGmailSubject);
		 navMailNotifications();
		 selectNotifactionPanel(toolsMsgRecipient);
		 selectMsgOfChatUser(msgText2 + selfmailId);
		 replyToMail();
		
	}
	
	
	 @Test(priority=1204)
	
	public void notification_markAllSeen() throws Exception{		
		navMailNotifications();
		 selectNotifactionPanel("All");
		 verifyMarkAllSeen();
		 
		
	}
	
	@Test(priority=1205)	
    public void notification_VerifyEmailAndForwardThroughMessage() throws Exception{
		try{
			logInfo("Access Notifications, and select 'Messsage' from Leftpanel. \n"
					+ "Based on message content, Select email and reply to mailer. ");			
			 navMailNotifications();
			 selectNotifactionPanel(toolsMsgRecipient);	 
			 forwardMailFromPanel();	
			 
		}
		catch(Exception ex){
		ex.printStackTrace();
		}
		 
	}
	
	@Test(priority=1206)	
    public void Alerts_VerifyTasksDetails() throws Exception{
  		 logInfo("Retrieve Count of Alerts and Create the Task with tommorow's Date. \n"
				+ "Again retrieve increased count and verify the Tasks Notifcations with details");			
		 retrieveCountsWithTasks();
			
	}
	
	
	@Test(priority=1207)
	public void Notification_ValidatePage() throws Exception{
		logInfo("Entered into Notification_ValidatePage Test");				
		navMailNotifications();
		//getPanelTitle(panelTitleText2);
		getListOfNotifactionPanel();
		selectPriorityInAvon(prMed);
		allMessageHeadersInPanel();				
		
	}
	
	 //"Validate Panel Titles, Alert,Notification, All counts");
	@Test(priority=1240)
	public void avonNotification_ValidatePage() throws Exception{
		logInfo("Entered into avonNotification_ValidatePage Test");
		
		
		avon_navNotifications();
		getPanelTitle(panelTitleText2);
		getListOfNotifactionPanel();
		selectPriorityInAvon(prMed);
		//allMessageHeadersInPanel();		
		userLogout();	

		
	}
	

@Test(priority=1241)
	
	public void notification_marKAllSeen_forAvon() throws Exception{
		
	     navMailNotifications();
		 selectNotifactionPanel("All");
		 verifyMarkAllSeen();
		 
		
	}
	@Test(priority=1250)  
	public void logOutAsUserFromNotifications() throws Exception{		

		userLogout();
	}
	

	
}
