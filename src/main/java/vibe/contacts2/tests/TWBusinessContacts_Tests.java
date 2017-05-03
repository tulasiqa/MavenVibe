package vibe.contacts2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import vibe.calendar.tests.CalendarMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.tasks.tests.TaskMethods;
import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.TestBase;


@Priority(2)
public class TWBusinessContacts_Tests extends TWBusinessContactsMethods {
	
	TestBase tb = new TestBase();
	InboxMethods in = new InboxMethods();
	CalendarMethods cal = new CalendarMethods();
	TaskMethods tk = new TaskMethods();
	WidgetsMethods widget = new WidgetsMethods();
	
	public static String vibeBusinessContacRecipient_text =  getEmail(adminUser_text,appUrl); 
	public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	
	
	@Test(priority=2)
	public void addBusinessContact() throws Exception{
		logInfo("inside addBusinessContact() Test");
		System.out.println("inside addBusinessContact() Test");
	
		go2ContactsPage();
		boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
		System.out.println("isContactFound =" +isContactFound);
		
		if(isContactFound==true){
			System.out.println("match found..deleting the contact.");
			deleteBusinessContact(contactFirstName_text,contactLastName_text);
		}
					
		addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);
		boolean isContactAdded= verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactAdded==false){
			System.out.println("business contact could not be added.");
			Assert.assertTrue(isContactAdded, "business contact could not be added.");
		}
	}
	
	@Test(priority=3)
	public void searchBussinesContact() throws Exception{
		logInfo("inside searchBussinesContact Test");
		System.out.println("inside searchBussinesContact Test");
		String contactName_text = contactFirstName_text + " " + contactLastName_text;
	
		go2ContactsPage();
		searchContact(contactFirstName_text,contactLastName_text);
		Thread.sleep(5000);
		boolean isContactFound= verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==false){
			System.out.println("business contact could not found.");
			Assert.assertTrue(isContactFound, "business contact not found.");
		}
	}
	
	@Test(priority=4)		
	public void addCallScripts2BusinessContact() throws Exception{
		logInfo("inside addCallScripts2BusinessContact() Test");
		System.out.println("inside addCallScripts2BusinessContact() Test");
		createCallScript(callScriptName_text);  
		
		boolean isCallscriptFound = verifyCallScriptFound(callScriptName_text);
		if(isCallscriptFound==true){
			addCallScript2Contacts(contactFirstName_text,contactLastName_text,callScriptName_text);
			boolean isNotesFound = verifyNotesIsPresent(phoneScriptNotes_text);
			if(isNotesFound==false){
				System.out.println(phoneScriptNotes_text + " callscript match not found in the notes.");
				Assert.assertTrue(isNotesFound, phoneScriptNotes_text + " callscript match not found in the notes.");
			}
			
			deleteCallScript(callScriptName_text);
		}
	}
	
	
	@Test(priority=5)
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
	

	
	@Test(priority=6)
	public void sendMesg2BusinessContact() throws Exception{
		logInfo("inside sendMesg2BusinessContact() Test");
		System.out.println("inside sendMesg2BusinessContact() Test");
	
		sendMessage2Contacts(contactFirstName_text,contactLastName_text,vibeRecipient_text);
		back2Office();
	
		in.go2Inbox();
		boolean isMailFound = in.selectVibeMailsWithSubject(inputSendMessageSubject_text);
		if(isMailFound==true){
			in.deleteFilteredVibeMails();
		} else {
			Assert.assertTrue(isMailFound, inputSendMessageSubject_text + " message not be found in Inbox.");
			logInfo(inputSendMessageSubject_text + " message not be found in Inbox.");
		}
	}
	
	
	
	@Test(priority=7)
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
		}
	}
		
	
	@Test(priority=8)
	public void createTask4BusinessContact() throws Exception{
		logInfo("inside createTask4BusinessContact() Test");
		System.out.println("inside createTask4BusinessContact() Test");
	
		createTaskFromContacts(contactTaskDesc_text);
			
		boolean isTaskFound = verifyContactHistory(contactTaskDesc_text);
		if(isTaskFound==false){
			logInfo(contactTaskDesc_text + " task not found in the contacts history.");
			Assert.assertTrue(isTaskFound, contactTaskDesc_text + " task not found in the contacts history.");
		}
	}	

	
	@Test(priority=9)
	public void addContacts2Group() throws Exception{
		logInfo("inside addContacts2Group Test");
		System.out.println("inside addContacts2Group Test");
		
		String contactName_text = contactFirstName_text + " " + contactLastName_text;
		
		addGroup(groupName_text);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		}
		
		addContact2Group(groupName_text,contactName_text);
		/*boolean isGroupFound = false;
		isGroupFound = verifyGroupIsPresent(groupName_text);
		System.out.println("isGroupFound =" +isGroupFound);
		if(isGroupFound==true){
			deleteGroup(groupName_text);
		}*/
	}
	

}
