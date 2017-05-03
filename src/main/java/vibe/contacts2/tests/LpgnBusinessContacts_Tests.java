package vibe.contacts2.tests;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.calendar.tests.CalendarMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.resourcelibrary2.tests.RL2Methods;
import vibe.tasks.tests.TaskMethods;
import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.TestBase;

@Priority(2)
public class LpgnBusinessContacts_Tests extends LpgnBusinessContactsMethods {
	TestBase tb = new TestBase();
	InboxMethods in = new InboxMethods();
	CalendarMethods cal = new CalendarMethods();
	TaskMethods tk = new TaskMethods();
	WidgetsMethods widget = new WidgetsMethods();
	RL2Methods rl = new RL2Methods();
	MyProfileMethods profile = new MyProfileMethods();
	
	public static String vibeBusinessContacRecipient_text =  getEmail(adminUser_text,appUrl); 
	 public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	// public static String vibeRecipient_text = "kevin.mcevoy@branch-1-9.vibeoffice.com";
	
	public static String contactName_text = contactFirstName_text + " " + contactLastName_text;
	public static String contactName_text1 = contactFirstName_text1 + " " + contactLastName_text1;
	
	
	@Test(priority=201)
	public void addBusinessContact() throws Exception{
        logInfo("inside addBusinessContact() Test");
       // userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));
        loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
        String[] contactNames = {contactName_text};
        go2ContactsPage();
        boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
        if(isContactFound==true){
            System.out.println("match found..deleting the contact.");
            selectMultipleContacts(contactNames,"Delete Selected");
           // confirmEventDeleteModal(); //Gangadhar changed from robot to delete modal box Nov 16th
            confirmOK(); 
        }
                    
        addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);
        boolean isContactAdded= verifyBusinessContact(contactFirstName_text,contactLastName_text);
        if(isContactAdded==false){
            System.out.println("business contact could not be added.");
            Assert.assertTrue(isContactAdded, "business contact could not be added.");
        }
    }
	
	@Test(priority=202)
	public void searchBussinesContact() throws Exception{
		logInfo("inside searchBussinesContact Test");
		System.out.println("inside searchBussinesContact Test");
		go2ContactsPage();
		searchContact(contactFirstName_text,contactLastName_text);
		Thread.sleep(5000);
		boolean isContactFound= verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==false){
			System.out.println("business contact could not found.");
			Assert.assertTrue(isContactFound, "business contact not found.");
		}
	}
	
	
	
	@Test(priority=203)
	public void verifyAddingExistingContacts() throws Exception{
		logInfo("inside verifyAddingExistingContacts Test");
		String[] contactNames = {"test contact"};
									
		selectMultipleContacts(contactNames,"Delete Selected");
		//confirmEventDeleteModal();
		confirmOK();
		addBusinessContacts("test","contact","test.contact1@icentris.com");
		addBusinessContacts("test","contact","test.contact1@icentris.com");
			
		validateEmailErrMsg(); 
		
		selectMultipleContacts(contactNames,"Delete Selected");
		//confirmEventDeleteModal();
		confirmOK();
	}
	
	
	@Test(priority=204)
	public void verifyPreferredEmail() throws Exception{	
		logInfo("inside verifyPreferredEmail test");
		
		String[] contactNames = {"test contact"};
		go2ContactsPage();
		
		selectMultipleContacts(contactNames,"Delete Selected");
	//	confirmEventDeleteModal();
		confirmOK();
		
		addBusinessContacts("test","contact","test.contact1@icentris.com");
		selectBusinessContact("test","contact");		
		setPreferredEmail2Contact();
		
		sendMessage2PrefferedContacts("test","contact", "testing preffered mail messages");		
		//in.go2Inbox();
		//in.selectMailsWithSubject("testing preffered mail messages");
		//in.deleteFilteredVibeMails();	
	
	}

	
	@Test(priority=205)
	public void validateInvalidSearch() throws Exception{
		logInfo("inside validateInvalidSearch test");
		
		go2ContactsPage();
		contactSearch(invalidSearchText);
		go2ContactsPage();
		advancedContactSearch(invalidSearchText);
		go2ContactsPage();
		/*downLineSearch(invalidSearchText);
		go2Groups();
		groupSearch(invalidSearchText);	*/
	}
	
	
	
	
	@Test(priority=206)		
	public void addCallScripts2BusinessContact() throws Exception{
		logInfo("inside addCallScripts2BusinessContact() Test");
		System.out.println("inside addCallScripts2BusinessContact() Test");
		adminLogin();
		
		 //createCallScript(callScriptName_text);  
		 String[] markets = {"US (English)","RU (Russian)","CO (English)"};
		 createCallScript(callScriptName_text, "Active", markets, publishedOn_text, publishedEndDate_text);
		 
		boolean isCallscriptFound = verifyCallScriptFound(callScriptName_text);
		if(isCallscriptFound==true){
			// userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			selectCallscript4Contact(contactFirstName_text,contactLastName_text);
			submitCallScript(callScriptName_text,phoneScriptNotes_text);
			boolean isNotesFound = verifyNotesIsPresent(phoneScriptNotes_text);
			if(isNotesFound==false){
				System.out.println(phoneScriptNotes_text + " callscript match not found in the notes.");
				Assert.assertTrue(isNotesFound, phoneScriptNotes_text + " callscript match not found in the notes.");
			}
			adminLogin();
			deleteCallScript(callScriptName_text); 
			// userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		}
	}
	
	@Test(priority=207)
	public void verifyPendingCallScripts() throws Exception{
		logInfo("inside verifyPendingCallScripts() Test");
		
		String[] markets = {"US (English)","RU (Russian)","CO (English)"};
		String expErrMsg = "can't be blank"; 
	
		createCallScript(callScriptPendingName_text, "Pending", markets, publishedOn_text, publishedEndDate_text);
		boolean isCallscriptFound = verifyCallScriptFound(callScriptPendingName_text);
		if(isCallscriptFound==true){
			selectCallscript4Contact(contactFirstName_text,contactLastName_text);
			submitCallScript(callScriptPendingName_text,phoneScriptNotes_text);
			Thread.sleep(3000);
			waitOnElement("cssSelector",eleCallscriptCantBeBlank);
			WebElement e = driver().findElement(org.openqa.selenium.By.cssSelector(eleCallscriptCantBeBlank));
			String actErrMsg = e.getText().trim();
			System.out.println("actErrMsg ="+actErrMsg);
			Assert.assertEquals(actErrMsg, expErrMsg);
			
			deleteCallScript(callScriptPendingName_text); 
			
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
		
		 profile.navigateMyProfileAccount("Notifications");
	     String selfmailId = driver().findElement(org.openqa.selenium.By.cssSelector(mailId)).getText();
	     System.out.println(selfmailId);
	     sendMessage2Contacts(contactFirstName_text,contactLastName_text,selfmailId);
	     back2Office();
	
	     //sendMessage2Contacts(contactFirstName_text,contactLastName_text,vibeRecipient_text);
		//back2Office();
	
		in.go2Inbox();
		boolean isMailFound = in.selectVibeMailsWithSubject(inputSendMessageSubject_text);
		if(isMailFound==true){
			in.deleteFilteredVibeMails();
		} else {
			Assert.assertTrue(isMailFound, inputSendMessageSubject_text + " message not be found in Inbox.");
			logInfo(inputSendMessageSubject_text + " message not be found in Inbox.");
		}
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
		//confirmEventDeleteModal();
		confirmOK();
	}
		
	
	@Test(priority=211)
	public void createTask4BusinessContact() throws Exception{
		logInfo("inside createTask4BusinessContact() Test");
		System.out.println("inside createTask4BusinessContact() Test");
	
		createTaskFromContacts(contactTaskDesc_text);
			
		/*boolean isTaskFound = verifyContactHistory(contactTaskDesc_text);
		if(isTaskFound==false){
			logInfo(contactTaskDesc_text + " task not found in the contacts history.");
			Assert.assertTrue(isTaskFound, contactTaskDesc_text + " task not found in the contacts history.");
		}
		
		go2ContactsPage();
		takeActionOnContactEvents(contactName_text,contactTaskDesc_text,"Mark Complete");
		confirmationMessage("Task has been marked complete");*/
	}	

	
	
	//@Test(priority=212)
	public void validateEmailAttachmentAbove4MB() throws Exception{
	logInfo("inside validate EmailAttachmentAbove4MB test");
		logInfo("Try to upload more than 4 Mb size to validate the alert toaster message. ");
		
		go2ContactsPage();
		uploadMoreThan4MBfile(contactFirstName_text,contactLastName_text,vibeRecipient_text,"MoreThan4MbImg");		
		in.go2Inbox();
		in.selectMailsWithSubject(subjectContent2);
		in.deleteFilteredVibeMails();	
	}

	
	@Test(priority=213)
	public void addGroup() throws Exception{
		logInfo("inside addGroup Test");
		System.out.println("inside addGroup Test");
	
		addGroup(groupName_text);
		confirmationMessage("Group Created.");
		
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
	
	
	@Test(priority=214)
	public void verifyAddingExistingGroups() throws Exception{
		logInfo("inside verifyAddingExistingGroups test");
		
		addGroup(groupName_text);
		confirmationMessage("Group Created.");
		addGroup(groupName_text);
		confirmationMessage("[\"Category Name has already been taken\"]");
		deleteGroup(groupName_text);
		confirmationMessage("Group Deleted.");
	}
	
	/*@Test(priority=215)
	public void renameGroup() throws Exception{
		logInfo("inside renameGroup Test ");
		
		addGroup(groupName_text);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded==true){
			logInfo(groupName_text + " group created sucessfully.");
			selectGroupAndMoreOption(groupName_text+"(" , "Rename");
			
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
	
	
	
	@Test(priority=216)
	public void validatePrintLabelsWithAddress() throws Exception{	
		logInfo("inside validatePrintLabelsWithAddress test");
		String[] contactNames = {contactName_text};
		
		addGroup(groupName_text);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		}
			
		
		boolean isContactFound = verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==false){
			addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);
		}
		
		selectMultipleContacts(contactNames,"Add To Group");
		addToGroupsList(groupName_text);
		confirmationMessage("1 contacts added to " + groupName_text+".");
		selectGroupAndMoreOption(groupName_text+"(" , "Print Labels");
		handlePDFWindow();
		deleteGroup(groupName_text);
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
	
	

	/*@Test(priority=220)		// ,dependsOnMethods={"addContacts2Group"}
	public void importContactsFromYahoo() throws Exception{
		logInfo("inside importContactsFromYahoo test");
		go2ContactsPage();
		validateYahooImport(groupName_text, "Import Into Group",YahooMailId, YahooPWD);
		// deleteGroup(groupName_text);
	}
	*/
	
	
	@Test(priority=221)		// ,dependsOnMethods={"addContacts2Group"}
	public void importContactsFromHotmail() throws Exception{
		logInfo("inside importContactsFromHotmail test");
		go2ContactsPage();
		validateHotmailImport(groupName_text, "Import Into Group",hotMailId_text, hotPwd_text);
		// deleteGroup(groupName_text);
	}
	

	@Test(priority=222)		// ,dependsOnMethods={"addContacts2Group"}
	public void importContactsFromGmail() throws Exception{
		logInfo("inside importContactsFromGmail test");
		// addGroup(groupName_text);
		go2ContactsPage();		
		validateGmailImport(groupName_text, "Import Into Group", gmailId_text, gmailPwd_text);
		
	}
	
	@Test(priority=223)
	public void importHotmailContacts2Direct() throws Exception{
		logInfo("inside import importHotmailContacts2Direct Test");
		
		go2ContactsPage();
		go2Groups();
		directHotmail(hotMailId_text, hotPwd_text);
		verifyGroupNdDelete("hotmail");
		reverifyGroup("hotmail");	
	}
	

	@Test(priority=224)
   public void reply2EmailFromContactHistory() throws Exception{
		logInfo("inside reply2EmailFromContactHistory test");
		String[] contactNames = {contactName_text};
		
		// go2ContactsPage();
		selectMultipleContacts(contactNames,"Delete Selected");
		addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);	// comment later if reqd	
		selectBusinessContact(contactFirstName_text,contactLastName_text);
		validateInvalidAlertsforMailing("raviKumargmail.com", vibeRecipient_text);		
		go2ContactsPage();	
		sendMessage2Contacts(contactFirstName_text,contactLastName_text,vibeRecipient_text,subjectContent1 );		
		replyNForwardMail(vibeRecipient_text,subjectContent1 );
		in.go2Inbox();
		in.selectMailsWithSubject(subjectContent1);
		in.deleteFilteredVibeMails();	
		
	}
	

	@Test(priority = 225)
	public void validateNoMailIdForInvitee() throws Exception{	
		logInfo("inside validateNoMailIdForInvitee test");
		addBussinesscontactwithoutInfo(FNameText, LNameText);
		boolean isContactAdded= verifyBusinessContact(FNameText, LNameText);
		if(isContactAdded==false){
			System.out.println("business contact could not be added.");
			Assert.assertTrue(isContactAdded, "business contact could not be added.");
		}
		go2ContactsPage();			
		conatctList(FNameText, LNameText);
		selectContactOptions("Invite to Event");
		confirmationMessage("Contact doesn't have an Email");
		go2ContactsPage();			
		conatctList(FNameText, LNameText);
		conatctList(contactFirstName_text,contactLastName_text);
		selectContactOptions("Invite to Event");
		confirmationMessage("Some of your contacts don't have email addresses.");		
	}
	
	
	
	
	@Test(priority = 226)
	public void validations4BussinessContact() throws Exception{
		logInfo("inside validations4BussinessContact test");
		String FNameText = "Test "+TestBase.generateRandomNumberInRange(2, 900);
		addContactsAlerts(FNameText);		
		go2ContactsPage();
		selectBusinessContact(contactFirstName_text,contactLastName_text);
		taskAlerts();
		callScriptAlert();
		notesAlert();
		eventAlert();	
	}
	

	
	// Can remove below test method , once the importFromGmail issue is resolved.
	
	/*@Test(priority=227)	
	public void reaccessApp(){
		String wndBeforeWindow = driver().getWindowHandle();	
		driver().close();
		driver().switchTo().window(wndBeforeWindow);
	}*/
	
	
	@Test(priority=228)
	public void deletBusinessContact() throws Exception{
		logInfo("inside deletBusinessContact() Test");
		String[] contactNames = {contactName_text};
		go2ContactsPage();
		boolean isContactFound= verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==true){
			selectMultipleContacts(contactNames,"Delete Selected");
			//confirmEventDeleteModal();
			confirmOK();
			Thread.sleep(5000);
			userLogout();
		}
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
	public void attachResources2SendMessage() throws Exception{
		logInfo("inside attachResources2SendMessage() Test ");
		System.out.println("inside attachResources2SendMessage() Test");
		boolean isAssetAttached = false;
		rl.navigate2AdminRL();
		rl.addResourceCategory(parentCategory_text,"None");
		boolean isAdded = rl.verifyResourceCategoryPresent(parentCategory_text);
		if(isAdded==true){
			rl.addNewResource(newResourceTitle_text, parentCategory_text, newResourceStatus_text, true,true,"PDF");
			confirmationMessage("Resource is created");
		}
		
		isAssetAttached = attachResource2SendMessage(contactFirstName_text,contactLastName_text,vibeRecipient_text,newResourceTitle_text,"pdf");
		if(!isAssetAttached){
			Assert.assertTrue(isAssetAttached, "Unable to attach a resource to the send message.");
		}
		
	}
	
	@Test(priority=231)
	public void validateAppointment4Contact() throws Exception{
		 logInfo("inside setAppointmentWithContacts() method.");
		 System.out.println("inside setAppointmentWithContacts() method.");
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 validateAppointment();
	}
		
	
	@Test(priority=232)
	public void validatePrintLabelsWithoutAddress() throws Exception{	
		logInfo("inside validatePrintLabelsWithoutAddress test");
		System.out.println("inside validatePrintLabelsWithoutAddress test");
		String[] contactNames = {contactName_text1};
		
		addGroup(groupName_text);
		Thread.sleep(5000);
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		}
			
		
		/*boolean isContactFound = verifyBusinessContact(contactFirstName_text1,contactLastName_text1);
		if(isContactFound==false){*/
			addBusinessContactsWithoutAddress(contactFirstName_text1,contactLastName_text1,contactEmail_text1);
//		}
		
		selectMultipleContacts(contactNames,"Add To Group");
		addToGroupsList(groupName_text);
		confirmationMessage("1 contacts added to " + groupName_text+".");
		selectGroupAndMoreOption(groupName_text+"(" , "Print Labels");
		handlePDFWindow();
		deleteGroup(groupName_text);
	}	
	
	@Test(priority=233)
	public void validateBusinessContact() throws Exception{	
		logInfo("inside validateBusinessContact test");
		System.out.println("inside validateBusinessContact test");
		go2ContactsPage();
		selectBusinessContact(contactFirstName_text1, contactLastName_text1);
		boolean isValidated = validateContact();
		if(!isValidated){
			Assert.assertTrue(isValidated, "Unable to validate a contact.");
		}
	}
	
	@Test(priority=234)
	public void validateCheckboxForContact() throws Exception{	
		logInfo("inside validateCheckboxForContact test");
		System.out.println("inside validateCheckboxForContact test");
		go2ContactsPage();
		boolean isValidated = validateCheckbox4Contact();
		if(!isValidated){
			Assert.assertTrue(isValidated, "Unable to validate a contact checkbox without selecting a contact group.");
		}
	}
	
	@Test(priority=235)
	public void removeContactsFromGroup() throws Exception{
		boolean isContactsDeleted = false;		
		logInfo("inside removeContactsFromGroup() Test");
		System.out.println("inside removeContactsFromGroup() Test");
		
		boolean isGroupAdded = verifyGroupIsPresent(groupName_text);  
		System.out.println("isGroupAdded =" +isGroupAdded);
		
		if(isGroupAdded==false){
		  logInfo(groupName_text + " group could not be created.");
		  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
		} else {
			logInfo(groupName_text + " group found sucessfully.");
			isContactsDeleted = removeContactFromGroup(groupName_text);
			if(!isContactsDeleted){
				Assert.assertTrue(isContactsDeleted, "Unable to delete the contacts from a group "+ groupName_text);
			}
		}
	}
	
	
	@Test(priority=236)
	public void loginAsAdminFromContacts() throws Exception{
		logInfo("inside loginAsAdminFromContacts() Test");
		
		adminLogin();
		
		
	}
	
	
	
	
	
}
