package vibe.contacts2.tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.calendar.tests.CalendarMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.resourcelibrary2.tests.RL2Methods;
import vibe.tasks.tests.TaskMethods;
import vibe.users.tests.UsersMethods;
import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.TestBase;

@Priority(2)
public class MonatBusinessContacts_Tests extends MonatBusinessContactsMethods {
	TestBase tb = new TestBase();
	InboxMethods in = new InboxMethods();
	CalendarMethods cal = new CalendarMethods();
	TaskMethods tk = new TaskMethods();
	WidgetsMethods widget = new WidgetsMethods();
	RL2Methods rl = new RL2Methods();
	NewsMethods nm = new NewsMethods();
	
	
	
	public static String vibeBusinessContacRecipient_text =  getEmail(adminUser_text,appUrl); 
	//public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	public static String vibeRecipient_text = "icentris.vibe001@gmail.com";
	
	public static String contactName_text = contactFirstName_text + " " + contactLastName_text;
	public static String contactName_text1 = contactFirstName_text1 + " " + contactLastName_text1;
	
	
	@Test(priority=201)
	public void addBusinessContact() throws Exception{
		logInfo("inside addBusinessContact() Test");
		String[] contactNames = {contactName_text};
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
		go2ContactsPage();
		boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==true){
			System.out.println("match found..deleting the contact.");
			selectMultipleContacts(contactNames,"Delete Selected");			
			confirmOK();
			}
					
		addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);
		boolean isContactAdded= verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactAdded==false){
			System.out.println("business contact could not be added.");
			Assert.assertTrue(isContactAdded, "business contact could not be added.");
		}
	}

	
	@Test(priority=203)
	public void searchBussinesContact() throws Exception{
		logInfo("inside searchBussinesContact Test");
		System.out.println("inside searchBussinesContact Test");
	//	nm.loginAsUser(repId);
		go2ContactsPage();
		searchContact(contactFirstName_text,contactLastName_text);
	//	Thread.sleep(5000);
		boolean isContactFound= verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==false){
			System.out.println("business contact could not found.");
			Assert.assertTrue(isContactFound, "business contact not found.");
		}
	}
	
	
		
	
	@Test(priority=206)		
	public void addCallScripts2BusinessContact() throws Exception{
		logInfo("inside addCallScripts2BusinessContact() Test");
		System.out.println("inside addCallScripts2BusinessContact() Test");
		userLogout();
		 String[] markets = {"US (English)","RU (Russian)","CO (English)"};
		 createCallScript(callScriptName_text, "Active", markets, publishedOn_text, publishedEndDate_text);
		 
		boolean isCallscriptFound = verifyCallScriptFound(callScriptName_text);
		if(isCallscriptFound==true){
			logInfo(callScriptName_text + " callscript found");
			}
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		selectCallscript4Contact(contactFirstName_text,contactLastName_text);
		submitCallScript(callScriptName_text,phoneScriptNotes_text);
		boolean isNotesFound = verifyNotesIsPresent(phoneScriptNotes_text);
		if(isNotesFound==false){
			System.out.println(phoneScriptNotes_text + " callscript match not found in the notes.");
			Assert.assertTrue(isNotesFound, phoneScriptNotes_text + " callscript match not found in the notes.");
			userLogout();
			deleteCallScript(callScriptName_text);
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		}
	}
	
	
	@Test(priority=208)
	public void addNotes2BusinessContact() throws Exception{
		logInfo("inside addNotes2BusinessContact() Test");
		System.out.println("inside addNotes2BusinessContact() Test");
	
		addNotes2Contacts(contactFirstName_text,contactLastName_text,addContactNotes_text);
		boolean isNotesFound = verifyNotesIsPresent(addContactNotes_text);
		System.out.println("isNotesFound =" +isNotesFound);
		
		if(isNotesFound==false){
			logInfo(isNotesFound + " notes could not be found.");
			Assert.assertTrue(isNotesFound, isNotesFound + " notes could not be found.");
		} else {
			manageContactNotes(addContactNotes_text,"Delete");
		}
	}  
	

	
	@Test(priority=209)
	public void sendMesg2BusinessContact() throws Exception{
		logInfo("inside sendMesg2BusinessContact() Test");
		System.out.println("inside sendMesg2BusinessContact() Test");
		
	//	userLogin(prop.getLocatorForEnvironment(appUrl,"IDLUser"),prop.getLocatorForEnvironment(appUrl,"IDLPwd"));
		sendMessage2Contacts(contactFirstName_text,contactLastName_text,gmailId_text2);
		userLogout();
		logOut();	
		in.loginGmail(gmailId_text2, gmailPwd_text1);		
		boolean isMailFound = in.verifyInboxGmail(inputSendMessageSubject_text);
		if(isMailFound==true){
			logInfo(isMailFound + "message found in Inbox.");
			System.out.println(isMailFound + "message found in Inbox.");
			in.signoutGmail();
			Thread.sleep(5000);
			logIn(adminUser_text,adminPwd_text);
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
					
		} else {
			Assert.assertTrue(isMailFound, vibeRecipient_text + " message not be found in Inbox.");
			logInfo(vibeRecipient_text + " message not be found in Inbox.");
			Thread.sleep(5000);
			logIn(adminUser_text,adminPwd_text);
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));}
	}
	
	
	
	@Test(priority=210)
	public void setAppoinment2BusinessContact() throws Exception{
		logInfo("inside setAppoinment2BusinessContact() Test");
		System.out.println("inside setAppoinment2BusinessContact() Test");
		
		setAppointmentWithContacts(appointmentName_text);
		cal.go2CalendarPage();
		boolean isEventDisplayed = false;
		isEventDisplayed = cal.verifyEventisDisplayed(appointmentName_text);
		if(isEventDisplayed==false){
			logInfo(appointmentName_text + " event could not be created through contacts.");
			Assert.assertTrue(isEventDisplayed, appointmentName_text + " event could not be created through contacts.");
		} else {
			logInfo(appointmentName_text + " event created through contacts.");
		}
		
		go2ContactsPage();
		takeActionOnContactEvents(contactName_text,appointmentName_text, "Delete");
		confirmOK();
	}
		
	
	@Test(priority=211)
	public void createTask4BusinessContact() throws Exception{
		logInfo("inside createTask4BusinessContact() Test");
		System.out.println("inside createTask4BusinessContact() Test");
	
		createTaskFromContacts(contactTaskDesc_text);
			
		boolean isTaskFound = verifyContactHistory(contactTaskDesc_text);
		if(isTaskFound==false){
			logInfo(contactTaskDesc_text + " task not found in the contacts history.");
			Assert.assertTrue(isTaskFound, contactTaskDesc_text + " task not found in the contacts history.");
		}
		
		go2ContactsPage();
		takeActionOnContactEvents(contactName_text,contactTaskDesc_text,"Mark Complete");
		confirmationMessage("Task has been marked complete");
	}	

	
	
	
	
	@Test(priority=213)
	public void addGroup() throws Exception{
		logInfo("inside addGroup Test");
		System.out.println("inside addGroup Test");
	
		addGroup(groupName_text);	
				
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		System.out.println("isGroupAdded =" +isGroupAdded);
		
		if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		} else {
			logInfo(groupName_text + " group created sucessfully.");
			deleteGroup(groupName_text);
			confirmationMessage("Group Deleted.");
		}
		
	}
	
	
	/*
	@Test(priority=215)
	public void renameGroup() throws Exception{
		logInfo("inside renameGroup Test ");
		
		addGroup(groupName_text);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded==true){
			logInfo(groupName_text + " group created sucessfully.");
			selectGroupAndMoreOption(groupName_text+"(" , "Rename");
			confirmationMessage("Group updated.");
			boolean isGroupUpdated= verifyGroupIsPresent(groupName_text+"Updated");  
			if(isGroupUpdated==true){
				logInfo(groupName_text+"Updated" + " group updated sucessfully.");
				deleteGroup(groupName_text+"Updated");
				
				confirmationMessage("Group Deleted.");
			} else {
				Assert.assertTrue(isGroupUpdated, groupName_text+"Updated" + " group not updated.");
			}
			
		}
	}*/
	
	

	@Test(priority=215)
	public void renameGroup() throws Exception{
		logInfo("inside renameGroup Test ");
		
		addGroup(groupName_text);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded==true){
			logInfo(groupName_text + " group created sucessfully.");
			renameGroup(groupName_text, groupName_text);
			confirmationMessage("Group updated.");
			boolean isGroupUpdated= verifyGroupIsPresent(groupName_text+"Updated");  
			if(isGroupUpdated==true){
				logInfo(groupName_text+"Updated" + " group updated sucessfully.");
				deleteGroup(groupName_text+"Updated");
				
				confirmationMessage("Group Deleted.");
			} else {
				Assert.assertTrue(isGroupUpdated, groupName_text+"Updated" + " group not updated.");
			}
			
		}
	}
	

	
	@Test(priority=217)
	public void addContacts2Group() throws Exception{
		logInfo("inside addContacts2Group Test");
		System.out.println("inside addContacts2Group Test");
	
		addGroup(groupName_text);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		System.out.println("isGroupAdded =" +isGroupAdded);
		
		if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		} else {
			logInfo(groupName_text + " group created sucessfully.");
			addContact2Group(groupName_text + " (0)",contactName_text);
		}
	}
	
	@Test(priority=218)					// ,dependsOnMethods={"addContacts2Group"}
	public void exportGroups2CSV() throws Exception{
		logInfo("inside exportGroups2CSV Test ");
		
		selectGroupAndMoreOption(groupName_text,"Export Csv");
		boolean isFileExists = false;
		String filepath = projectPath+"\\downloads\\";
		if(verifyFileExistsOnDisk(filepath+"export-2016-08-02.csv")){
			logInfo(filepath+"export-2016-08-02.csv" + " file exists.");
		} else {
			logInfo(filepath+"export-2016-08-02.csv" + " file does not exists.");
		}
	}
	
	@Test(priority=219)		// ,dependsOnMethods={"addContacts2Group"}
	public void exportGroups2Excel() throws Exception{
		logInfo("inside exportGroups2Excel Test ");
		selectGroupAndMoreOption(groupName_text,"Export Excel");
		boolean isFileExists = false;
		String filepath = projectPath+"\\downloads\\";
		if(verifyFileExistsOnDisk(filepath+"export.xls")){
			logInfo(filepath+"export.xls" + " file exists.");
		} else {
			logInfo(filepath+"export.xls" + " file does not exists.");
		}
	}
	

	
	@Test(priority=221)		// ,dependsOnMethods={"addContacts2Group"}
	public void importContactsFromHotmail() throws Exception{
		logInfo("inside importContactsFromHotmail test");
		String groupName_text = "Newgrp";
		go2ContactsPage();
		validateHotmailImport(groupName_text, "Import Into Group",hotMailId_text, hotPwd_text);
		// deleteGroup(groupName_text);
	}
	

	@Test(priority=222)		// ,dependsOnMethods={"addContacts2Group"}
	public void importContactsFromGmail() throws Exception{
		logInfo("inside importContactsFromGmail test");
	//	String groupName_text = "TestGroup_740";
		// addGroup(groupName_text);
		go2ContactsPage();		
		validateGmailImport(groupName_text, "Import Into Group", gmailId_text, gmailPwd_text);
		
	}
	
	


	@Test(priority=229)
	public void deleteGroup() throws Exception{
		logInfo("inside deleteGroup Test ");
		boolean isGroupFound = false;
		
		deleteGroup(groupName_text);
		confirmationMessage("Group Deleted.");
		isGroupFound = verifyGroupIsPresent(groupName_text);
		System.out.println("isGroupFound =" +isGroupFound);
		
		if(isGroupFound==true){
			Assert.assertFalse(isGroupFound, groupName_text + " group could not be deleted.");
			
		}
	}
	
	@Test(priority=230)  
	public void logOutAsUser() throws Exception{		
	//adminLogin();			
		userLogout();
	}
	
	
	
	
	
}
