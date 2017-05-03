package vibe.contacts2.tests;

import common.TestBase;

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

public class TWBusinessContactsMethods extends TestBase {
	
	Logger logger = Logger.getLogger(BusinessContactsMethods.class);
	 public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	
	public void go2ContactsPage() throws Exception{
		logInfo("inside go2ContactsPage() method. ");
		driver().navigate().to(appUrl + "crm/contacts/manager");
		Thread.sleep(3000);
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
		
		waitOnElement("linkText","Add A Contact");
		clickOnLink("linkText", "Add A Contact");
		Thread.sleep(5000);
		waitOnElement("cssSelector",inputContactFirstName);
		inputText("cssSelector",inputContactFirstName,fName);  // contactFirstName_text
		inputText("cssSelector",inputContactLastName,lName);    // contactLastName_text
		inputText("cssSelector",inputContactEmail,email);
		inputText("cssSelector",inputContactPhone,contactPhone_text);

		WebElement element = driver().findElement(By.cssSelector(drpdnContactPhoneType));
		Select select = new Select(element);
		List<WebElement> allItems =select.getOptions();
		int size = allItems.size();
	//	System.out.println("count of items =" +size);
		select.selectByIndex(size-1);
		
		selectFromDropdown("cssSelector",drpdnContactType,"byVisibleText",contactType_text);
		selectFromDropdown("cssSelector",drpdnContactInterestLevel,"byVisibleText",contactInterestLevel_text);
		inputText("cssSelector",inputContactAddr1,contactAddr1_text);
		inputText("cssSelector",inputContactAddr2,contactAddr2_text);
		inputText("cssSelector",inputContactCity,contactCity_text);
		
		inputText("cssSelector",inputContactPostalCode,contactPostalCode_text);
		// selectFromDropdown("cssSelector",drpdnContactCntry,"byVisibleText","India");
		selectFromDropdown("cssSelector",drpdnContactCntry,"byVisibleText",contactCntry_text);
		selectFromDropdown("cssSelector",drpdnContactState,"byVisibleText",contactState_text);
		inputText("cssSelector",inputContactGenInfo,contactGenInfo_text);
			
		logInfo("Entered all the fields in Business -> AddContact page.");
		
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(5000);
	}
	
	
	/*public boolean verifyBusinessContact(String fName, String lName) throws Exception{
		 logInfo("inside verifyBusinessContact() method.");
		  System.out.println("inside verifyBusinessContact() method.");
		  go2ContactsPage();
		  
		  clickOnLink("partialLinkText","All Contacts(");
		 
		  String contactsPanel = "//*[@id='list-results']/div[3]/div";   // "//*[@id='list-results']/div[2]/div[1]"; 
		  waitOnElement("xpath",contactsPanel);
		  WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
		  
		  String expVal = fName + " " + lName;
		  List<WebElement> verifyContactsList = econtactsPanel.findElements(By.xpath(eleMatchContact));
				
				boolean isContactFound = false;
				int count= verifyContactsList.size();
				System.out.println("count =" +count);
				
				for(int i=0;i<=count-1;i++){
					String actText = verifyContactsList.get(i).getText();
					if(actText.equalsIgnoreCase(expVal)){
						isContactFound = true;
						logInfo(expVal + " business contact match found.");
						break;
					}
				}
			return isContactFound;
	}
	*/
	
		// methods for Tupperware-stage
	
		public boolean verifyBusinessContact(String fName, String lName) throws Exception{
		 logInfo("inside verifyBusinessContact() method.");
		  System.out.println("inside verifyBusinessContact() method.");
		  go2ContactsPage();
		  
		  // clickOnLink("partialLinkText","All Contacts (");
		  clickOnLink("partialLinkText","Contacts (");
		 
		  String contactsPanel = "//div[@id='index-action-panel']/div[2]/div[3]/div[@id='list-body']/form";   
		  waitOnElement("xpath",contactsPanel);
		  WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
		  
		  String before_name = "//div[@id='index-action-panel']/div[2]/div[3]/div[@id='list-body']/form/div[";   // "//*[@id='list-results']/div[3]/div/form/div[";
		  String after_name = "]/div[2]/div/div[2]/h4";
		  
		  String expVal = fName + " " + lName;
		  System.out.println("Expected Contact = " +expVal);
		  
		  List<WebElement> verifyContactsList = econtactsPanel.findElements(By.cssSelector(".contact-list-item.media"));
				
				boolean isContactFound = false;
				int count= verifyContactsList.size();
				System.out.println("Total Business Contacts =" +count);
				logInfo("Total Business Contacts =" +count);
				for(int i=1;i<=count;i++){
					WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
					String actText = e.getText().trim();
					
					System.out.println("actText =" +actText);
					
					if(actText.contains(expVal)){
						isContactFound = true;
						logInfo(expVal + " business contact match found.");
						// break;
					}
				}
				return isContactFound;
	}
	
	public void searchContact(String fName, String lName) throws Exception, Exception{
		logInfo("inside searchContact() method. ");
		System.out.println("inside searchContact() method. ");
		 String expVal = fName + " " + lName;
		 
		waitOnElement("cssSelector",inputContactSearch);
		inputText("cssSelector",inputContactSearch, expVal);
		
	}
	
	
	 public void selectBusinessContact(String fName, String lName) throws Exception{
		 logInfo("inside selectBusinessContact() method.");
		  
		  clickOnLink("partialLinkText","Contacts (");
			Thread.sleep(5000);
		  
		  String contactsPanel = "//div[@id='index-action-panel']/div[2]/div[3]/div[@id='list-body']/form";
		  waitOnElement("xpath",contactsPanel);
		  WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
		  
		  String expVal = fName + " " + lName;
		  List<WebElement> verifyContactsList = econtactsPanel.findElements(By.className("contact-list-item.media"));
				
				boolean isContactFound = false;
				int count= verifyContactsList.size();
				System.out.println("count =" +count);
				
			/*	String before_name = "//*[@id='all-contacts-list']/div[1]/form/div[";
				String after_name = "]/div[2]/div/div[2]/h4/a/span[2]";*/
				
				String before_name = "//div[@id='index-action-panel']/div[2]/div[3]/div[@id='list-body']/form/div[";
				String after_name = "]/div[2]/div/div[2]/h4";
				
				String before_link = "//div[@id='index-action-panel']/div[2]/div[3]/div[@id='list-body']/form/div[";
				String after_link = "]/div[2]/div/div[2]/h4/div[1]/a/span[2]";    // "]/div[2]/div/div[2]/h4/a";
				
				for(int i=1;i<=count;i++){
					WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
					String name = e.getText().trim();
					
					// System.out.println("Act Contact = " +name);
					// System.out.println("Exp Contact = " +expVal);
					if(name.equalsIgnoreCase(expVal)){
						isContactFound = true;
						logInfo(name + " business contact match found.");
						WebElement link = driver().findElement(By.xpath(before_link+i+after_link));
						link.click();
						System.out.println(name + " selected business contact");
						break;
					}
				}
	 }
	
	 public void deleteBusinessContact(String fName, String lName) throws Exception{
		  logInfo("inside deleteBusinessContact() method.");
		  System.out.println("inside deleteBusinessContact() method.");
		  go2ContactsPage();
	
		  selectBusinessContact(contactFirstName_text,contactLastName_text);
		  
		  waitOnElement("linkText","Delete");
		  clickOnLink("linkText", "Delete");
		  System.out.println("clicked delete contact button");
						
		  Thread.sleep(3000);
		  (new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
		  (new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);
		  System.out.println("clicked confirm delete button");
		  logInfo("clicked on confirm delete button.");
		  // Thread.sleep(5000);
		  String contactsPanel = "//*[@id='all-contacts-list']/div[1]/form";
		  waitOnElement("xpath",contactsPanel);
	  }
		  
	
	 public void createCallScript(String scriptName) throws Exception{
		 go2adminPage();
		 go2CallScriptPage();
	
		 clickOnLink("linkText","Add New Script");
		 waitOnElement("xpath",inputCallScriptName);
		 inputText("xpath",inputCallScriptName,scriptName);
		 selectFromDropdown("xpath",drpdnCallScriptStatus,"byVisibleText",callScriptStatus_text);
	//	 composeTextOnCallScript();
		 composeText("xpath",composeBody,composeBodyText);
		 clickOnElement("xpath",divCallScriptMarketLang);
		 WebElement chkAll = driver().findElement(By.xpath(inputMarketAll));
		 if(!chkAll.isSelected()){
			chkAll.click();
		 }
		 
		 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");   
		 Calendar cal = Calendar.getInstance();
		 String dd = dateFormat.format(cal.getTime());
		 publishedOn_text = dd.trim();
		 publishedEndDate_text = "10/10/2020 12:00 PM";
		 System.out.println("Pulish start date: " +publishedOn_text);
		 System.out.println("Pulish end date: " +publishedEndDate_text);
		 inputTextClear("xpath",inputPublishedOn);
		 inputText("xpath",inputPublishedOn,publishedOn_text);
		 inputTextClear("xpath",inputPublishedEndDate);
		 inputText("xpath",inputPublishedEndDate,publishedEndDate_text);
	// saveAndProceed();
		// waitOnElement("linkText","Back To Call Scripts");
		 clickOnButton("cssSelector",btnSubmitCallscript);
		 Thread.sleep(5000);
		 waitOnElement("linkText","back to office");
		 clickOnLink("linkText","back to office");
		 Thread.sleep(5000);
	 }
	 
	 public void deleteCallScript(String scriptName) throws Exception{
		 logInfo("inside deleteCallScript() method.");
		 go2adminPage();
		 go2CallScriptPage();
	
		 waitOnElement("xpath","//*[@id='call_scripts_list']/tbody");
		 WebElement ecallscripttbl = driver().findElement(By.xpath("//*[@id='call_scripts_list']/tbody"));
		 List allRows = ecallscripttbl.findElements(By.tagName("tr"));
		 
		 String before_name = "//*[@id='call_scripts_list']/tbody/tr[";
		 String after_name = "]/td[1]/a";
		 String before_delete = "//*[@id='call_scripts_list']/tbody/tr[";
		 String after_delete = "]/td[6]/a[2]";
		 		 
		 for(int i=1;i<=allRows.size();i++){
			 WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
			 String strCallscriptName = e.getText().trim();
			 if(scriptName.equalsIgnoreCase(strCallscriptName)){
				 WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
				 delete.click();
				// confirmEventDeleteModal();
				 confirmOK();
			 }
		 }
	 }
	 
	 public boolean verifyCallScriptFound(String scriptName) throws Exception{
		 logInfo("inside deleteCallScript() method.");
		 go2adminPage();
		 go2CallScriptPage();
	
		 waitOnElement("xpath","//*[@id='call_scripts_list']/tbody");
		 WebElement ecallscripttbl = driver().findElement(By.xpath("//*[@id='call_scripts_list']/tbody"));
		 List allRows = ecallscripttbl.findElements(By.tagName("tr"));
		 boolean isCallScriptFound = false;
		 
		 String before_name = "//*[@id='call_scripts_list']/tbody/tr[";
		 String after_name = "]/td[1]/a";
				 		 
		 for(int i=1;i<=allRows.size();i++){
			 WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
			 String strCallscriptName = e.getText().trim();
			 if(scriptName.equalsIgnoreCase(strCallscriptName)){
				 isCallScriptFound=true;
				 logInfo( scriptName + "call script match found in admin page");
			 }
		 }
		return isCallScriptFound;
	 }

	 
	 public void addCallScript2Contacts(String fName, String lName, String scriptName) throws Exception{
		  logInfo("inside addCallScript2Contacts() method.");
		  System.out.println("inside addCallScript2Contacts() method.");
		  go2ContactsPage();
		  selectBusinessContact(contactFirstName_text,contactLastName_text);
		  
		  Thread.sleep(5000);
		  waitOnElement("xpath",lnkStartCallScript);
		  clickOnElement("xpath",lnkStartCallScript);
						
		  logInfo("clicked start call script link");
		  submitCallScript();
	   }
	 
	 public void submitCallScript() throws Exception {
		 logInfo("inside submitCallScript() method.");
		 waitOnElement("cssSelector",drpdnPhoneScript);
		 selectFromDropdown("cssSelector",drpdnPhoneScript,"byVisibleText",callScriptName_text);  // callScriptName_text
		 inputText("cssSelector",textareaPhoneScriptNotes,phoneScriptNotes_text);
		 selectRadioOrCheckbox("cssSelector",radioPhoneScriptInterest);
		 clickOnButton("cssSelector",inputSubmitCallscript);
		 
	 }
	 
	 public void addNotes2Contacts(String fName, String lName, String notes) throws Exception{
		  logInfo("inside addNotes2Contacts() method.");
		  System.out.println("inside addNotes2Contacts() method.");
				
		  go2ContactsPage();
		  selectBusinessContact(contactFirstName_text,contactLastName_text);
		 
		  Thread.sleep(5000);
		  waitOnElement("xpath",lnkNotes);
		  clickOnElement("xpath",lnkNotes);
		
		  submitNotes();
		
		}

	
	 public void submitNotes() throws Exception {
		 logInfo("inside submitNotes() method.");
		 Thread.sleep(5000);
		 waitOnElement("linkText","New Note");	
		 clickOnLink("linkText","New Note");
		 waitOnElement("cssSelector",inputAddContactNotes);
		 inputTextClear("cssSelector",inputAddContactNotes);
		 inputText("cssSelector",inputAddContactNotes, addContactNotes_text);	
		 clickOnButton("cssSelector",btnCreateContactNote);
		 logInfo("clicked on create note button.");
		 
	 }
	 
	 
	 public boolean verifyNotesIsPresent(String notes) throws Exception{
		  logInfo("inside verifyNotesIsPresent() method.");
		  System.out.println("inside verifyNotesIsPresent() method.");
		  
		  waitOnElement("xpath",lnkNotes);
		  clickOnElement("xpath",lnkNotes);
		  
		  waitOnElement("xpath",tblContactNotes);
		  WebElement notesPane = driver().findElement(By.xpath(tblContactNotes));
		  List allRows = notesPane.findElements(By.className("row"));
		  int all_rows = allRows.size();
		  System.out.println("Total Rows = " +all_rows);
		  String before_text = "//*[@id='contact-details-panel']/div[3]/div[";
		  String after_text = "]/div[3]/div";
		  boolean isMatchFound = false;
		  if(all_rows>=0){
			  for(int i=1;i<=all_rows;i++){
				  WebElement x = driver().findElement(By.xpath(before_text+i+after_text));
				  String strNotes = x.getText().trim();
				  System.out.println(strNotes);
				  if(strNotes.equalsIgnoreCase(notes)){
					  logInfo(strNotes + " contact notes match found.");
					  isMatchFound = true;
					  break;
				  }
			  }
		  }
	
		  return isMatchFound;
	 }
	 
	 
	 public void manageContactNotes(String notes, String mode) throws Exception{
		  logInfo("inside deleteContactNotes() method.");
		  System.out.println("inside deleteContactNotes() method.");
		  		  
		  clickOnElement("cssSelector",lnkNotes);
		  
		  waitOnElement("xpath",tblContactNotes);
		  WebElement notesPane = driver().findElement(By.xpath(tblContactNotes));
		  List allRows = notesPane.findElements(By.className("row"));
		  int all_rows = allRows.size();
		  System.out.println("Total Rows = " +all_rows);
		  String before_text = "//*[@id='contact-details-panel']/div[3]/div[";
		  String after_text = "]/div[3]/div";
		  
		  String before_delete = "//*[@id='contact-details-panel']/div[3]/div[";
		  String after_delete = "]/div[2]/a[1]";
		  
		  String before_edit = "//*[@id='contact-details-panel']/div[3]/div[";
		  String after_edit = "]/div[2]/a[2]";
				  
		  boolean isMatchFound = false;
		  if(all_rows>=0){
			  for(int i=1;i<=all_rows;i++){
				  WebElement x = driver().findElement(By.xpath(before_text+i+after_text));
				  String contactNotes = x.getText().trim();
				  System.out.println(contactNotes);
				  if(contactNotes.equalsIgnoreCase(notes)){
					  logInfo(notes + " contact notes match found.");
					  isMatchFound = true;
					 
					  switch(mode){
						case "Delete":
					 	  WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
						  delete.click();
						//  confirmEventDeleteModal();
						  confirmOK();
						  break;
						case "Edit": 
					      WebElement edit = driver().findElement(By.xpath(before_edit+i+after_edit));
					      edit.click();
						  break;
					  }
					  break;
				  }
			  }
		  }
	 }
	 
	 
	 public void sendMessage2Contacts(String fName, String lName, String recepients) throws Exception{
		 logInfo("inside sendMessage2Contacts() method.");
		 System.out.println("inside sendMessage2Contacts() method.");
		 
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		
		 waitOnElement("xpath",lnkSendMesg);
		 Thread.sleep(5000);
		 clickOnElement("xpath",lnkSendMesg);
		 
		 waitOnElement("cssSelector",recipientsTo);
		 clickOnElement("cssSelector",recipientsTo);
		 inputText("cssSelector",recipientsTo, recepients);
		 inputText("cssSelector",subject_Mail, inputSendMessageSubject_text);
		// Robot rb= new Robot();
		/* rb.delay(2000);
		 rb.keyPress(KeyEvent.VK_TAB);
		 rb.keyRelease(KeyEvent.VK_TAB); */
		// composeTextOnElement();
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","Sending a message to contacts");
		 //clickOnLink("cssSelector",btnSendMessage);
		 clickOnLink("linkText", "Send");
		 Thread.sleep(10000);
	 }
	 
	 
	 public void setAppointmentWithContacts(String eventName) throws Exception{
		 logInfo("inside setAppointmentWithContacts() method.");
		 System.out.println("inside setAppointmentWithContacts() method.");
		 
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 
		 waitOnElement("xpath",lnkCreateEvent);
		 Thread.sleep(5000);
		 clickOnElement("xpath",lnkCreateEvent);
		 
		 
		 waitOnElement("cssSelector",inputAppointmentName);
		 Thread.sleep(5000);
		 inputTextClear("cssSelector",inputAppointmentName);
		 inputText("cssSelector",inputAppointmentName,appointmentName_text);
		 
		 inputText("xpath",inputEventLoc,eventLoc_text);
		 
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
		 
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 
		 waitOnElement("xpath",lnkCreateTask);
		 Thread.sleep(5000);
		 clickOnElement("xpath",lnkCreateTask);
		
		 waitOnElement("xpath",taskDescriptionInput);
		 Thread.sleep(5000);
		 inputTextClear("xpath",taskDescriptionInput);
		 inputText("xpath",taskDescriptionInput,contactTaskDesc_text);
		
		 inputTextClear("xpath",inputTaskDuedate);
		 inputText("xpath",inputTaskDuedate,taskDuedate_text);
		 
		 selectFromDropdown("xpath",drpdnTaskPriority,"byVisibleText",taskPriority_text);
		 
		// composeTextOnElement();
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","sending a message in while creating a task for a contact");
		 clickOnButton("xpath",inputAddTask);
		 logInfo("clicked on Add Task button in Contacts page.");
	 }
	 
	 
	 
	 public boolean verifyContactHistory(String expHistory) throws Exception{
		 logInfo("inside verifyContactHistory() method.");
		 System.out.println("inside verifyContactHistory() method.");
		 
		 Thread.sleep(5000);
		 waitOnElement("xpath",lnkViewHistory);
		 clickOnElement("xpath",lnkViewHistory);
		  
		 waitOnElement("xpath",tblHistory2);
		 WebElement historyTable = driver().findElement(By.xpath(tblHistory2));
		 
		 List<WebElement> allHistory = historyTable.findElements(By.className("row"));
		 int countHistory = allHistory.size();
		 System.out.println("count of History items " +countHistory );
		 
		 boolean isHistoryMatchFound = false;
		 
		 String before_tName = "//*[@id='contact-details-panel']/div[@id='history-items']/div[";
		 String after_tName =  "]/div[2]";
			
		 for(int i=1;i<=countHistory-1;i++){
			 WebElement e = driver().findElement(By.xpath(before_tName+i+after_tName));
			 String historyName = e.getText().trim();
			 
			 logInfo("History Name = " +historyName);
			 if(historyName.endsWith(expHistory)){
				 logInfo(expHistory + " history match found.");
				 isHistoryMatchFound = true;
				 break;
			 }
		 }
		 
		 if(isHistoryMatchFound==false){
			 logInfo(expHistory + " match not found in history tab in contacts.");
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
	 
	 public void go2Groups() throws Exception, Exception{
		 logInfo("inside go2Groups() method.");
		 System.out.println("inside go2Groups() method.");
		 driver().navigate().to(appUrl + "crm/contacts/manager");
		 
		 waitOnElement("partialLinkText","Groups (");
		 clickOnLink("partialLinkText","Groups (");
	 }
	 
	 public void closeManageGroupsDialog() throws Exception, Exception{
		 logInfo("inside closeManageGroupsDialog() method.");
		 System.out.println("inside closeManageGroupsDialog() method.");
		
		 clickOnElement("cssSelector",btnCloseGroup);
		}
	 
	 public void addGroup(String groupName) throws Exception, Exception{
		 logInfo("inside addGroup() method.");
		 System.out.println("inside addGroup() method.");
		 
		 go2Groups();
		 
		 waitOnElement("linkText", "Manage Groups");
		 clickOnLink("linkText", "Manage Groups");
		 
		 waitOnElement("linkText", "Add New Group");
		 clickOnLink("linkText", "Add New Group");
		 
		 waitOnElement("xpath",inputGroupName);
		 inputText("xpath",inputGroupName,groupName);
		 
		 clickOnLink("linkText","Create");
		 Thread.sleep(5000);
		 
	 }
	 
	 
	 // This method checks the group on the left side panel in Manage Groups page.
	 
	/* public boolean verifyGroupIsPresent(String grpName) throws Exception, Exception{
		 logInfo("inside verifyGroupIsPresent() method.");
		 System.out.println("inside verifyGroupIsPresent() method.");
		 go2Groups();
		
		 boolean isMatchFound = false;
		 waitOnElement("linkText","Manage Groups");
		 clickOnLink("linkText","Manage Groups");
		 
		 waitOnElement("xpath",tblGroups2);
		 WebElement tblGroup = driver().findElement(By.xpath(tblGroups2));
		 List allGroups = tblGroup.findElements(By.className("group-list-item"));
		 int total_groups = allGroups.size();
		 
		 String before_group = "//*[@id='list-results']/div[@id='groups-list']/div[";
		 String after_group = "]/div/div/a";
		 
		 
		 for(int i=1;i<=total_groups;i++){
			 WebElement grp = driver().findElement(By.xpath(before_group+i+after_group));
			 String groupName = grp.getText().trim();
			 System.out.println("Group Name = " +groupName);
			 if(groupName.equalsIgnoreCase(grpName)){
				 isMatchFound = true;
				logInfo(grpName + " group found in Manage Groups.");
				break;
			 }
			 
		 }
		 
		 if(isMatchFound==false){
			 logInfo(grpName + " group not found in Manage Groups.");
		 }
		 
		return isMatchFound;
	 }*/
	 
	 public boolean verifyGroupIsPresent(String grpName) throws Exception, Exception{
		 logInfo("inside verifyGroupIsPresent() method.");
		 System.out.println("inside verifyGroupIsPresent() method.");
		 go2Groups();
		
		 boolean isMatchFound = false;
		 waitOnElement("linkText","Manage Groups");
		 clickOnLink("linkText","Manage Groups");
		 
		 WebElement tblGroup = driver().findElement(By.xpath("//*[@id='group-manager-list']"));
		 List<WebElement> allGroups = tblGroup.findElements(By.className("group-list-item"));
		 
		 for(WebElement x: allGroups){
			 String actGrp = x.getText().trim();
			 System.out.println("actGrp = " + actGrp);
			 if(actGrp.contains(grpName)){
				 System.out.println(actGrp + " match found in Manage Groups page.");
				 isMatchFound=true;
				 break;
			 }
		 } 
		 
		if(isMatchFound==false){
			 logInfo(grpName + " group not found in Manage Groups.");
			 // Assert.assertTrue(isMatchFound, grpName + " group not found in Manage Groups.");
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
	 
	 
	 public void addContact2Group(String grpName, String contact) throws Exception, Exception{
		 logInfo("inside addContact2Group() method.");
		 System.out.println("inside addContact2Group() method.");
		 
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 
		 Thread.sleep(5000);
		 waitOnElement("xpath",lnkAdd2Group);
		 clickOnElement("xpath",lnkAdd2Group);
		 
		 selectGroup2Add(groupName_text);
		
	 }
	 
	 public void selectGroup2Add(String grpName) throws Exception{
		 logInfo("inside selectGroup2Add() method.");
		 System.out.println("inside selectGroup2Add() method.");
		 
		 waitOnElement("xpath","//*[@id='contact-details-panel']/div[@id='groups-list']");
		// Thread.sleep(10000);
		 WebElement grpPanel = driver().findElement(By.xpath("//*[@id='contact-details-panel']/div[@id='groups-list']"));
		  List allGrps = grpPanel.findElements(By.className("group-list-item"));
		 // List allGrps = grpPanel.findElements(By.cssSelector(".group-list-item"));
		 System.out.println("Total Groups = " +allGrps.size());
		 
		 String before_grpName = "//*[@id='contact-details-panel']/div[@id='groups-list']/div[";
		 String after_grpName = "]/div[1]/span[2]";
		 
		 String before_grpAdd = "//*[@id='contact-details-panel']/div[@id='groups-list']/div[";
		 String after_grpAdd = "]/div[2]/form/a[1]";
		 
		 boolean isGrpFound = false;
		 System.out.println("expGrpName =" + grpName.trim());
		 
		 for(int i=1;i<=allGrps.size();i++){
			 WebElement eName = driver().findElement(By.xpath(before_grpName+i+after_grpName));
			 String actGrpName = eName.getText().trim();
			 System.out.println("actGrpName =" + actGrpName);
			 if(actGrpName.contains(grpName)){
				 // eName.click();
				 isGrpFound = true;
				 System.out.println(grpName + " group match found to add.");
				 WebElement add = driver().findElement(By.xpath(before_grpAdd+i+after_grpAdd));
				 add.click();
				 break;
			 }
		 }
		 
	 }
	
	

}
