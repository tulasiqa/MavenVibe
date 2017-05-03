package vibe.contacts.tests;

import java.util.List;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.testng.Assert;

import vibe.inbox.tests.InboxMethods;
import common.TestBase;

public class BusinessContactsMethods extends TestBase{
	Logger logger = Logger.getLogger(BusinessContactsMethods.class);
	
	 // Navigate to business contact page.
	public void navigateToContacts() throws Exception{
		clickOnLink("linkText", "Business");
		clickOnLink("linkText", "Contacts");	
	}
	
	public void searchContact(String contactName) throws Exception{	
		PropertyConfigurator.configure("Log4j.properties");	
		logger.info("Search the user '"+ contactName  +"' and Select contact.");
		
		inputTextClear("cssSelector", inputContactSearch);
		inputText("cssSelector",inputContactSearch,contactName);
		Thread.sleep(5000);
		clickOnElement("cssSelector", contactschkbox);
		Thread.sleep(5000);
		getText("cssSelector",contactsName, "Name is ");
		clickOnElement("cssSelector",contactsName);
		/*submitElement("cssSelector",inputContactSearch);*/
		Thread.sleep(5000);
	}
	
	public void selectCreateTask() throws Exception{	
		PropertyConfigurator.configure("Log4j.properties");	
		logger.info("Selecting 'Create Task' button.");		
		clickOnButton("cssSelector", createTask);
	}
	
	public void go2ContactsPage() throws Exception{
		logInfo("inside go2ContactsPage() method. ");
		driver().navigate().to(appUrl + "crm/contacts");
	}
	
	public void go2adminPage() throws Exception{
		logInfo("inside go2adminPage() method. ");
		driver().navigate().to(appUrl + "pyr_core/pyr_admin/dashboard");
	}

	public void go2CallScriptPage() throws Exception{
		logInfo("inside go2adminPage() method. ");
		driver().navigate().to(appUrl + "pyr_core/pyr_admin/dashboard");
		verifyLinkPresent("Marketing");
		clickOnLink("linkText", "Marketing");
		verifyLinkPresent("Call Scripts");
		clickOnLink("linkText", "Call Scripts");
	}
	
	
	public void addBusinessContacts(String fName, String lName, String email) throws Exception{
		logInfo("inside addBusinessContacts() method. ");
		System.out.println("inside addBusinessContacts() method. ");
		go2ContactsPage();
		verifyLinkPresent("Add Contact");
		clickOnLink("linkText", "Add Contact");
		Thread.sleep(5000);
		verifyElementPresent("cssSelector",inputContactFirstName);
		inputText("cssSelector",inputContactFirstName,fName);  // contactFirstName_text
		inputText("cssSelector",inputContactLastName,lName);    // contactLastName_text
		inputText("cssSelector",inputContactEmail,email);
		inputText("cssSelector",inputContactPhone,contactPhone_text);

		WebElement element = driver().findElement(By.cssSelector(drpdnContactPhoneType));
		Select select = new Select(element);
		List<WebElement> allItems =select.getOptions();
		int size = allItems.size();
		select.selectByIndex(size-1);
		
		selectFromDropdown("cssSelector",drpdnContactType,"byVisibleText",contactType_text);
		selectFromDropdown("cssSelector",drpdnContactInterestLevel,"byVisibleText",contactInterestLevel_text);
		inputText("cssSelector",inputContactAddr1,contactAddr1_text);
		inputText("cssSelector",inputContactAddr2,contactAddr2_text);
		inputText("cssSelector",inputContactCity,contactCity_text);
		
		inputText("cssSelector",inputContactPostalCode,contactPostalCode_text);
		selectFromDropdown("cssSelector",drpdnContactCntry,"byVisibleText",contactCntry_text);
		selectFromDropdown("cssSelector",drpdnContactState,"byVisibleText",contactState_text);
		inputText("cssSelector",inputContactGenInfo,contactGenInfo_text);
			
		logInfo("Entered all the fields in Business -> AddContact page.");
		
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(5000);
	}
	
	
	public boolean verifyBusinessContact(String fName, String lName) throws Exception{
		logInfo("inside verifyBusinessContact() method.");
		System.out.println("inside verifyBusinessContact() method.");
		go2ContactsPage();
		
		verifyElementPresent("xpath",eleMatchContact);
		List<WebElement> verifyContactsList = driver().findElements(org.openqa.selenium.By.xpath(eleMatchContact));
		
		Boolean isContactFound = false;
		String expVal = fName + " " + lName;
		int count = verifyContactsList.size();
		logInfo("total business contacts =" + count);
		for(int i=0;i<=count-1;i++){
			String actText = verifyContactsList.get(i).getText();
			if(actText.equalsIgnoreCase(expVal)){
				isContactFound = true;
				logInfo(expVal + " business contact match found");
				verifyContactsList.get(i).click();
				Assert.assertTrue(isContactFound, expVal + " business contact added sucessfully.");
				break;
			}
		}
		System.out.println(isContactFound);
		return isContactFound;
	}
	
	
	 public void deleteBusinessContact(String fName, String lName) throws Exception{
		  logInfo("inside deleteBusinessContact() method.");
		  System.out.println("inside deleteBusinessContact() method.");
		  go2ContactsPage();
		  
		  String expVal = fName + " " + lName;
		  List<WebElement> verifyContactsDeletedList = driver().findElements(org.openqa.selenium.By.xpath(eleMatchContact));
				
			boolean isContactFound = false;
			int count= verifyContactsDeletedList.size();
			for(int i=0;i<=count-1;i++){
				String actText = verifyContactsDeletedList.get(i).getText();
				if(actText.equalsIgnoreCase(expVal)){
					isContactFound = true;
					logInfo(expVal + " business contact match found.");
					verifyContactsDeletedList.get(i).click();
					System.out.println(expVal + " selected business contact");
					
					Thread.sleep(3000);
					waitOnElement("cssSelector",btnContactToggle);
					clickOnButton("cssSelector",btnContactToggle);
					verifyLinkPresent("Delete Contact");
					clickOnLink("linkText", "Delete Contact");
					System.out.println("clicked delete contact button");
					
					Thread.sleep(3000);
					(new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
					(new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);
					System.out.println("clicked confirm delete button");
					logInfo("clicked on confirm delete button.");
					// Thread.sleep(5000);
					break;
				}
			}
	 }
		  
	
	 public void createCallScript(String scriptName) throws Exception{
		 go2adminPage();
		 go2CallScriptPage();
		 
	//	 verifyLinkPresent("Add New Script");
		 clickOnLink("linkText","Add New Script");
		 
		 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");   //HH:mm:ss
		 Calendar cal = Calendar.getInstance();
		 String dd = dateFormat.format(cal.getTime());
		 publishedOn_text = dd.trim();
		 publishedEndDate_text = "10/10/2020 12:00 PM";
		 
		 System.out.println("Pulish start date: " +publishedOn_text);
		 System.out.println("Pulish end date: " +publishedEndDate_text);
		 
		 inputText("xpath",inputCallScriptName,scriptName);
		 selectFromDropdown("xpath",drpdnCallScriptStatus,"byVisibleText",callScriptStatus_text);
		 
		 clickOnElement("cssSelector",divScriptContent);
		 //composeTextOnCallScript();
		
		clickOnElement("xpath",divCallScriptMarketLang);
		WebElement chkAll = driver().findElement(By.xpath(inputMarketAll));
		if(!chkAll.isSelected()){
			chkAll.click();
		}
		 
		inputTextClear("xpath",inputPublishedOn);
		inputText("xpath",inputPublishedOn,publishedOn_text);
		inputTextClear("xpath",inputPublishedEndDate);
		inputText("xpath",inputPublishedEndDate,publishedEndDate_text);
		//saveAndProceed();
		clickOnButton("cssSelector",btnSubmitCallscript);
		waitOnElement("linkText","Back To Call Scripts");
		
	 }
	 
	 
	 public void addCallScript2Contacts(String fName, String lName, String scriptName) throws Exception{
		  logInfo("inside addCallScript2Contacts() method.");
		  System.out.println("inside addCallScript2Contacts() method.");
		  go2ContactsPage();
		  
		  String expVal = fName + " " + lName;
		  List<WebElement> verifyContactsList = driver().findElements(org.openqa.selenium.By.xpath(eleMatchContact));
			
			boolean isContactFound = false;
			int count= verifyContactsList.size();
			for(int i=0;i<=count-1;i++){
				String actText = verifyContactsList.get(i).getText();
				if(actText.equalsIgnoreCase(expVal)){
					isContactFound = true;
					logInfo(expVal + " business contact match found.");
					verifyContactsList.get(i).click();
					Thread.sleep(5000);
					// verifyElementPresent("cssSelector",btnContactToggle);		// remove later
					waitOnElement("cssSelector",btnContactToggle);
					clickOnButton("cssSelector",btnContactToggle);
					verifyLinkPresent("Make A Call");
					clickOnLink("linkText", "Make A Call");
					Thread.sleep(5000);
					// verifyElementPresent("cssSelector",drpdnPhoneScript);
					 waitOnElement("cssSelector",drpdnPhoneScript);
					 
					 selectFromDropdown("cssSelector",drpdnPhoneScript,"byVisibleText",scriptName);  // callScriptName_text
					 verifyElementPresent("cssSelector",textareaPhoneScriptNotes);
					 inputText("cssSelector",textareaPhoneScriptNotes,phoneScriptNotes_text);
					 selectRadioOrCheckbox("cssSelector",radioPhoneScriptInterest);
					 clickOnButton("cssSelector",btnPhoneScriptSave);
					break;
				}
			}
	 }
	 
	 
	 public void addNotes2Contacts(String fName, String lName, String notes) throws Exception{
		  logInfo("inside addNotes2Contacts() method.");
		  System.out.println("inside addNotes2Contacts() method.");
				
		  String expVal = fName + " " + lName;
		  List<WebElement> verifyContactsList = driver().findElements(org.openqa.selenium.By.xpath(eleMatchContact));
		 
				boolean isContactFound = false;
				int count= verifyContactsList.size();
				for(int i=0;i<=count-1;i++){
					String actText = verifyContactsList.get(i).getText();
					
					if(actText.equalsIgnoreCase(expVal)){
						isContactFound = true;
						logInfo(expVal + " business contact match found.");
						verifyContactsList.get(i).click();
						
						verifyLinkPresent("Notes");
						clickOnLink("linkText", "Notes");
						clickOnElement("linkText","Create Note");
						
						inputText("cssSelector",inputAddContactNotes, notes);	//addContactNotes_text
						clickOnButton("cssSelector",btnCreateContactNote);
						logInfo("clicked on create note button.");
						break;
					}
				}
				
			if(isContactFound==false){
				logInfo(fName + " " + lName + " business contact match not found.");
			}
		}

	
	 public boolean verifyNotesIsPresent(String notes) throws Exception{
		  logInfo("inside verifyNotesIsPresent() method.");
		  System.out.println("inside verifyNotesIsPresent() method.");
		  notes = notes.trim();
	/*	  waitOnElement("cssSelector",lnkDetails);
		  clickOnElement("cssSelector",lnkDetails);
		  waitOnElement("cssSelector",lnkNotes);
		  clickOnElement("cssSelector",lnkNotes);
		  waitOnElement("xpath",tblContactNotes); */
		  
		  clickOnElement("cssSelector",lnkNotes);
		  Thread.sleep(5000);
		  String tblContactNotes = "//*[@id='contact-notes-widget']/div[2]/div[2]/table/tbody";
		  WebElement notesTable = driver().findElement(By.xpath(tblContactNotes));
		  List allRows = notesTable.findElements(By.tagName("tr"));
		  int all_rows = allRows.size();
		  System.out.println("Total Rows = " +all_rows);
		  String before_text = "//*[@id='contact-notes-widget']/div[2]/table/tbody/tr[";
		  String after_text = "]/td[1]";
		  
		  boolean isMatchFound = false;
		  if(all_rows>0){
			  for(int i=1;i<=all_rows;i++){
				  WebElement x = driver().findElement(By.xpath(before_text+i+after_text));
				  String contactNotes = x.getText().trim();
				  waitOnElement("xpath",contactNotes);
				  System.out.println(contactNotes);
				  if(contactNotes.equalsIgnoreCase(notes)){
					  logInfo(notes + " contact notes match found.");
					  isMatchFound = true;
					  break;
				  }
			  }
		  }
	
		  if(isMatchFound==false){
			  logInfo(notes + " contact notes match not found in the Notes table.");
			  Assert.assertTrue(isMatchFound,notes + " contact notes match not found in the Notes table.");
		  }
		  return isMatchFound;
	 }
	 
	 
	 public void deleteContactNotes(String notes) throws Exception{
		  logInfo("inside deleteContactNotes() method.");
		  System.out.println("inside deleteContactNotes() method.");
		 		  
		  WebElement notesTable = driver().findElement(By.xpath(tblContactNotes));
		  List allRows = notesTable.findElements(By.tagName("tr"));
		  int all_rows = allRows.size();
		  
		  String before_text = "//*[@id='contact-notes-widget']/div[2]/table/tbody/tr[";
		  String after_text = "]/td[1]";
		  String before_delete = "//*[@id='contact-notes-widget']/div[2]/table/tbody/tr[";
		  String after_delete = "]/td[3]/a";
	
		  boolean isMatchFound = false;
		  
		  if(all_rows>0){
			  for(int i=1;i<=all_rows;i++){
				  WebElement x = driver().findElement(By.xpath(before_text+i+after_text));
				  String contactNotes = x.getText().trim();
				  if(contactNotes.equalsIgnoreCase(notes.trim())){
					  isMatchFound = true;
					  WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
					  delete.click();
					  Thread.sleep(2000);
					 // confirmEventDeleteModal();
					  confirmOK();
					  break;
				  }
			  }
		  }
		  
		  if(isMatchFound==false){
			  logInfo(notes + " contact notes not found to delete.");
			  Assert.assertTrue(isMatchFound, notes + " contact notes not found to delete.");
		  }
	 }
	 
	 
	 public void sendMessage2Contacts(String recepients) throws Exception{
		 logInfo("inside sendMessage2Contacts() method.");
		 System.out.println("inside sendMessage2Contacts() method.");
				 
		waitOnElement("cssSelector",contactSendMessage);
		clickOnElement("cssSelector",contactSendMessage);
		 
		 inputText("cssSelector",recipientsTo, recepients);
		 inputText("cssSelector",subject_Mail, inputSendMessageSubject_text);
		// Robot rb= new Robot();
		/* rb.delay(2000);
		 rb.keyPress(KeyEvent.VK_TAB);
		 rb.keyRelease(KeyEvent.VK_TAB); */
		// composeTextOnElement();
		 composeText("xpath",composeBody,composeBodyText);
		 //clickOnLink("cssSelector",btnSendMessage);
		 clickOnLink("linkText", "Send");
		 Thread.sleep(10000);
	 }
	 
	 
	 public void setAppointmentWithContacts(String eventName) throws Exception{
		 logInfo("inside setAppointmentWithContacts() method.");
		 System.out.println("inside setAppointmentWithContacts() method.");
				 
		 verifyElementPresent("cssSelector",contactSetAppointment);
		 clickOnElement("cssSelector",contactSetAppointment);
		 
		 inputTextClear("cssSelector",inputAppointmentName);
		 inputText("cssSelector",inputAppointmentName,appointmentName_text);
		 
		 inputTextClear("xpath",inputEventStartDate);
		 inputText("xpath",inputEventStartDate, inputAppointmentStartDate);
		 
		 inputTextClear("xpath",inputEventEndDate);
		 inputText("xpath",inputEventEndDate, inputAppointmentEndDate);
		
		 clickOnElement("xpath", btnEventSave);
		 Thread.sleep(10000);
	 }
	 
	 public void createTaskFromContacts(String taskName) throws Exception{
		 logInfo("inside createTaskFromContacts() method.");
		 System.out.println("inside createTaskFromContacts() method.");
				 
		 verifyElementPresent("cssSelector",contactCreateTask);
		 clickOnElement("cssSelector",contactCreateTask);
		
		 inputTextClear("xpath",taskDescriptionInput);
		 inputText("xpath",taskDescriptionInput,contactTaskDesc_text);
			 
		 inputTextClear("xpath",inputTaskDuedate);
		 inputText("xpath",inputTaskDuedate,taskDuedate_text);
		 
		 selectFromDropdown("xpath",drpdnTaskPriority,"byVisibleText",taskPriority_text);
		 clickOnButton("cssSelector",contactTaskSave);
		 logInfo("clicked on Add Task button in Contacts page.");
	 }
	 
	 
	 
	 public boolean verifyContactHistory(String history) throws Exception{
		 logInfo("inside verifyContactHistory() method.");
		 System.out.println("inside verifyContactHistory() method.");
		 
		 verifyLinkPresent("History");
		 clickOnLink("linkText","History");
		 logInfo("clicked in History link.");
			
		 implicityWaits(3);
		 boolean isHistoryMatchFound = false;
		
		 waitOnElement("xpath",tblHistory);
		 WebElement historyTable = driver().findElement(By.xpath(tblHistory));
		 List<WebElement> allHistory = historyTable.findElements(By.className("col-md-10"));
		 int countHistory = allHistory.size();
		 System.out.println("Count of History items " +countHistory );
		 for(int i=0;i<=countHistory-1;i++){
			 String historyName = allHistory.get(i).getText().trim();
			 logInfo("History Name = " +historyName);
			 if(historyName.endsWith(history)){
				 logInfo("History match found in history tab in contacts page = " +history);
				 isHistoryMatchFound = true;
				 break;
			 }
		 }
		 
		 if(isHistoryMatchFound==false){
			 logInfo(history + " match not found in history tab in contacts.");
		 }
		return isHistoryMatchFound;
	 }
	 
	/* 
	 public void deleteContact(String fName, String lName) throws Exception{
		 logInfo("inside deleteContact() method.");
		 System.out.println("inside deleteContact() method.");
		 
		 	waitOnElement("cssSelector",btnContactToggle);
			clickOnButton("cssSelector",btnContactToggle);
			verifyLinkPresent("Delete Contact");
			clickOnLink("linkText", "Delete Contact");
			System.out.println("clicked delete contact button");
			
			Thread.sleep(3000);
			(new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
			(new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);
			System.out.println("clicked confirm delete button");
			logInfo("clicked on confirm delete button.");
			 
	 }
*/	
	 
	 // Groups related methods.
	 
	 public void go2Groups(){
		 logInfo("inside go2Groups() method.");
		 System.out.println("inside go2Groups() method.");
		 driver().navigate().to(appUrl + "crm/contacts/groups");
	 }
	 
	 public void closeManageGroupsDialog() throws Exception, Exception{
		 logInfo("inside closeManageGroupsDialog() method.");
		 System.out.println("inside closeManageGroupsDialog() method.");
		 Thread.sleep(2000);		
		 clickOnElement("cssSelector",btnCloseGroup);
		}
	 
	 public void addGroup(String groupName) throws Exception, Exception{
		 logInfo("inside addGroup() method.");
		 System.out.println("inside addGroup() method.");
		 go2Groups();
		 waitOnElement("linkText", "Manage Groups");
		 clickOnLink("linkText", "Manage Groups");
		 
		 waitOnElement("xpath",inputGroupName);
		 inputText("xpath",inputGroupName,groupName);
		 
		 clickOnButton("cssSelector",btnCreateGroup);
		 closeManageGroupsDialog();
		
	 }
	 
	 
	 public boolean verifyGroupIsPresent(String grpName) throws Exception, Exception{
		 logInfo("inside verifyGroupIsPresent() method.");
		 System.out.println("inside verifyGroupIsPresent() method.");
		 go2Groups();
		 
		 waitOnElement("linkText", "Manage Groups");
		 clickOnLink("linkText", "Manage Groups");
		 boolean isMatchFound = false;
		 
		 WebElement tblGroup = driver().findElement(By.xpath(tblGroups));
		 List allGroups = tblGroup.findElements(By.tagName("tr"));
		 int total_groups = allGroups.size();
		 
		 String before_group = "//*[@id='listContactGroups']/tbody/tr[";
		 String after_group = "]/td[1]/a/span";
		 
		 String before_delete = "//*[@id='listContactGroups']/tbody/tr[";
		 String after_delete = "]/td[2]/a";
		 
		 for(int i=1;i<=total_groups;i++){
			 WebElement grp = driver().findElement(By.xpath(before_group+i+after_group));
			 String groupName = grp.getText().trim();
			 if(groupName.equalsIgnoreCase(grpName)){
				 isMatchFound = true;
				logInfo(grpName + " group match found in Manage Groups.");
				break;
			 }
		 }
		 
		 if(isMatchFound==false){
			 logInfo(grpName + " group match not found in Manage Groups.");
		 }
		 
		return isMatchFound;
	 }
	 
	 
	 public void deleteGroup(String grpName) throws Exception, Exception{
		 logInfo("inside deleteGroup() method.");
		 System.out.println("inside deleteGroup() method.");
		 
		 go2Groups();
		 
		 waitOnElement("linkText", "Manage Groups");
		 clickOnLink("linkText", "Manage Groups");
		 boolean isMatchFound = false;
		 
		 WebElement tblGroup = driver().findElement(By.xpath(tblGroups));
		 List allGroups = tblGroup.findElements(By.tagName("tr"));
		 int total_groups = allGroups.size();
		 
		 String before_group = "//*[@id='listContactGroups']/tbody/tr[";
		 String after_group = "]/td[1]/a/span";
		 
		 String before_delete = "//*[@id='listContactGroups']/tbody/tr[";
		 String after_delete = "]/td[2]/a";
		 
		 for(int i=1;i<=total_groups;i++){
			 WebElement grp = driver().findElement(By.xpath(before_group+i+after_group));
			 String groupName = grp.getText().trim();
			 if(groupName.equalsIgnoreCase(grpName)){
				 isMatchFound = true;
				 WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
				 delete.click();
				 Thread.sleep(2000);
				// confirmEventDeleteModal();
				 confirmOK();
				 break;
			 }
		 }
		 
		 if(isMatchFound==false){
			 logInfo(grpName + " group not found in Manage Groups.");
			 Assert.assertTrue(isMatchFound, grpName + " group not found in Manage Groups.");
		 }
	 }
	 
	 
	 public void addContact2Group(String grpName, String contact) throws Exception{
		 logInfo("inside addContact2Group() method.");
		 System.out.println("inside addContact2Group() method.");
		 
		 go2Groups();
		 
		 // Select Group
		 WebElement grpPanel = driver().findElement(By.xpath(panelGroups));
		 List allGroups = grpPanel.findElements(By.tagName("li"));
		 int all_groups = allGroups.size();
		 
		 String before_name = "//*[@class='content']/ul/li[";
		 String after_name = "]/div/div[2]/h4";
		 
		 boolean isGroupFound = false;
		 boolean isContactFound = false;
		 
		 for(int i=1;i<=all_groups;i++){
			 WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			 String groupName = x.getText().trim();
			 if(groupName.equalsIgnoreCase(grpName)){
				 isGroupFound = true;
				 x.click();
				 verifyLinkPresent("Add To Group");
				 clickOnLink("linkText","Add To Group");
				 WebElement contactsPanel = driver().findElement(By.xpath(panelContacts));
				 List allContacts = contactsPanel.findElements(By.className("contact"));
				 int all_contacts = allContacts.size();
				 
				 String before_contact = "//div[@class='add_group_contacts']/div[";
				 String after_contact = "]/div/div[3]/h4/span[2]";
				 
				 String before_input = "//div[@class='add_group_contacts']/div[";
				 String after_input = "]/div/div[1]/input";
				 
				 System.out.println("Total contacts = " +all_contacts);
				 if(all_contacts>0){
					 for(int j=1;j<=all_contacts;j++){
						WebElement y = driver().findElement(By.xpath(before_contact+j+after_contact));
						String contactName = y.getText().trim();
						if(contactName.equalsIgnoreCase(contact)){
							System.out.println(contact + " contact match found");
							isContactFound = true;
							WebElement input = driver().findElement(By.xpath(before_input+j+after_input));
							input.click();
							clickOnElement("xpath",btnSavenClose);
							break;
						}
					 }
				 }

				 break;
			 }
		 }
	 }

	
	 
}
