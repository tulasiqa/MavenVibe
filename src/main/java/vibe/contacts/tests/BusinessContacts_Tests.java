package vibe.contacts.tests;

import org.testng.Assert;
import org.testng.annotations.Test;




import vibe.calendar.tests.CalendarMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.tasks.tests.TaskMethods;
import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.TestBase;

@Priority(2)
public class BusinessContacts_Tests extends BusinessContactsMethods {
	
	InboxMethods inbox = new InboxMethods();
	CalendarMethods cal = new CalendarMethods();
	TaskMethods tm = new TaskMethods();
	
	public static String vibeBusinessContacRecipient_text =  getEmail(adminUser_text,appUrl); 
	
	@Test(priority=201)
	public void addBusinessContact() throws Exception{
		logInfo("inside addBusinessContact() Test");
		System.out.println("inside addBusinessContact() Test");
	
		boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==true){
			deleteBusinessContact(contactFirstName_text,contactLastName_text);
		} 
	
		addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);
		Thread.sleep(5000);
		boolean isContactCreated = verifyBusinessContact(contactFirstName_text,contactLastName_text);
		
		if(isContactCreated==false){
			logInfo(contactFirstName_text + " " + contactLastName_text + " contact could not be created.");
			Assert.assertTrue(isContactCreated, contactFirstName_text + " " + contactLastName_text + " contact could not be created.");
		}
	}

	
	@Test(priority=202, dependsOnMethods={"addBusinessContact"})
	public void addCallScripts2BusinessContact() throws Exception{
		logInfo("inside addCallScripts2BusinessContact() Test");
		System.out.println("inside addCallScripts2BusinessContact() Test");
		createCallScript(callScriptName_text);
		addCallScript2Contacts(contactFirstName_text,contactLastName_text,callScriptName_text);
	}
	
	
	@Test(priority=203, dependsOnMethods={"addBusinessContact"})
	public void addNotes2BusinessContact() throws Exception{
		logInfo("inside addNotes2BusinessContact() Test");
		System.out.println("inside addNotes2BusinessContact() Test");
		verifyBusinessContact(contactFirstName_text,contactLastName_text);
		addNotes2Contacts(contactFirstName_text,contactLastName_text,addContactNotes_text);
		verifyContactHistory(addContactNotes_text);
		boolean isNotesFound = verifyNotesIsPresent(addContactNotes_text);
		if(isNotesFound==true){
			deleteContactNotes(addContactNotes_text);
		}
	}  
	
	@Test(priority=204, dependsOnMethods={"addBusinessContact"})
	public void sendMessage2BusinessContact() throws Exception{
		logInfo("inside sendMessage2BusinessContact() Test");
		System.out.println("inside sendMessage2BusinessContact() Test");
		boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==true){
			System.out.println(vibeBusinessContacRecipient_text);			// remove later
			sendMessage2Contacts(vibeBusinessContacRecipient_text);
			verifyContactHistory(inputSendMessageSubject_text);
			boolean isEmailSubjectFound = inbox.selectVibeMailsWithSubject(inputSendMessageSubject_text);
			if(isEmailSubjectFound==false){
				Assert.assertTrue(isEmailSubjectFound, inputSendMessageSubject_text + " message not be send from contacts.");
			}
		}
	}
	
	

	@Test(priority=205, dependsOnMethods={"addBusinessContact"})
	public void setAppoinment2BusinessContact() throws Exception{
		logInfo("inside setAppoinment2BusinessContact() Test");
		System.out.println("inside setAppoinment2BusinessContact() Test");
		
		boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==true){
			setAppointmentWithContacts(appointmentName_text);
			cal.go2CalendarPage();
			boolean isEventDisplayed = false;
			isEventDisplayed = cal.verifyEventisDisplayed(appointmentName_text);
			if(isEventDisplayed==false){
				Assert.assertTrue(isEventDisplayed, appointmentName_text + " event could not be created through contacts.");
			}
		} 
	}
	

	/*@Test(priority=206, dependsOnMethods={"addBusinessContact"})
	public void createTask4BusinessContact() throws Exception{
		logInfo("inside createTask4BusinessContact() Test");
		System.out.println("inside createTask4BusinessContact() Test");
		WidgetsMethods widget = new WidgetsMethods();
		boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==true){
			createTaskFromContacts(contactTaskDesc_text);
			tm.navigate2BusinessTasks();
			tm.verifyandDragWidget("Tasks Incomplete");
			tm.collapseEditWidget();
			boolean isTaskFound = tm.verifyTaskInIncompleteWidget(contactTaskDesc_text);
			System.out.println(isTaskFound);
			if(isTaskFound==false){
				widget.removeWidgetInHomepage("Tasks Incomplete");
				Assert.assertTrue(isTaskFound, taskName + " task not found in the incomplete tasks widget." );
			}
		}
	}
	*/
	

	@Test(priority=207, dependsOnMethods={"addBusinessContact"})
	public void addContacts2Group() throws Exception{
		logInfo("inside manageGroups() Test");
		System.out.println("inside manageGroups() Test");
		String contactName_text = contactFirstName_text + " " + contactLastName_text;
		boolean isGroupFound = false;
		isGroupFound = verifyGroupIsPresent(groupName_text);   // groupName_text
		if(isGroupFound==true){
			deleteGroup(groupName_text);
			addGroup(groupName_text);
			addContact2Group(groupName_text,contactName_text);
			deleteGroup(groupName_text);
		}
		
		if(isGroupFound==false){
			addGroup(groupName_text);
			addContact2Group(groupName_text,contactName_text);
		}
	}
	
	@Test(priority=208, dependsOnMethods={"addBusinessContact"})
	public void deleteBusinessContact() throws Exception{
		logInfo("inside deleteBusinessContact() Test");
		System.out.println("inside deleteBusinessContact() Test");
		boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==true){
			deleteBusinessContact(contactFirstName_text,contactLastName_text);
			boolean isContactFound1 = verifyBusinessContact(contactFirstName_text,contactLastName_text);
			if(isContactFound1==true){
				logInfo(contactFirstName_text + " " +contactLastName_text + " contact could not be deleted.");
				Assert.assertFalse(isContactFound1, contactFirstName_text + " " +contactLastName_text + " contact could not be deleted.");
			} 
		} else {
			logInfo(contactFirstName_text + " " +contactLastName_text + " contact could not be found.");
		}
		
	}
	
	
}
