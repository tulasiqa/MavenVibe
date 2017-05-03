package vibe.contacts2.tests;

import java.util.List;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.testng.Assert;

import vibe.calendar2.tests.CalendarMethods;
import vibe.ecards.tests.EcardMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.tasks.tests.TaskMethods;
import common.TestBase;
import common.readProp;

public class BusinessContactsMethods extends TestBase {
	Logger logger = Logger.getLogger(BusinessContactsMethods.class);
	 public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	 
	 readProp prop = new readProp();
	 CommunityMethods cm = new CommunityMethods();
	 TaskMethods tm = new TaskMethods();
	 EcardMethods em = new EcardMethods();
	 CalendarMethods cal = new CalendarMethods();
	
	public void go2ContactsPage() throws Exception{
		logInfo("inside go2ContactsPage() method. ");
		driver().navigate().to(appUrl + "crm/contacts/manager");
		Thread.sleep(3000);
		String lnkContacts = prop.getLocatorForEnvironment(appUrl,"lnkRecentContacts");
		lnkContacts = lnkContacts+" (";
		clickOnLink("partialLinkText",lnkContacts);
		Thread.sleep(5000);
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
		
		// waitOnElement("linkText","Add A Contact");
		// clickOnLink("linkText", "Add A Contact");
		waitOnElement("cssSelector","#add-a-contact>a");
		clickOnLink("cssSelector", "#add-a-contact>a");
		
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
	
	public boolean verifyBusinessContact(String fName, String lName) throws Exception{
        logInfo("inside verifyBusinessContact() method.");
         System.out.println("inside verifyBusinessContact() method.");
         go2ContactsPage();
         
         // waitOnElement("xpath","//*[@id='list-results']/div[3]/div/form");
        // //Thread.sleep(5000);
         
         
           String contactsPanel = prop.getLocatorForEnvironment(appUrl,"contactsPanel");
           System.out.println("contact panel =" +contactsPanel);
           System.out.println("appUrl =" +appUrl);
         
       //  String contactsPanel =  "//*[@id='list-results']/div[3]/div/form";   // for idlife stage2 = "//*[@id='list-results']/div[2]/div[1]"; 
         WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
         
         waitOnElement("xpath",contactsPanel);
         
         String before_name = contactsPanel + "/div[";   // "//*[@id='list-results']/div[3]/div/form/div[";  // "//*[@id='all-contacts-list']/div[1]/form/div[";   //
         String after_name = "]/div[@class='media-body']/div/div[2]/h4/div/a/span[2]";
         
         String expVal = fName + " " + lName;
         List<WebElement> verifyContactsList = econtactsPanel.findElements(By.cssSelector(".contact-list-item.media"));
               
               boolean isContactFound = false;
               int count= verifyContactsList.size();
               System.out.println("Total Business Contacts =" +count);
               logInfo("Total Business Contacts =" +count);
               for(int i=1;i<=count;i++){
                   WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
                   String actText = e.getText().trim();
                   
                   System.out.println("actText =" +actText);
                   
                   if(actText.equalsIgnoreCase(expVal)){
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
		 System.out.println("inside selectBusinessContact() method");
		 
		  
		  String contactsPanel = "//*[@id='all-contacts-list']/div[1]/form";
		  waitOnElement("xpath",contactsPanel);
		  WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
		  
		  String expVal = fName + " " + lName;
		  List<WebElement> verifyContactsList = econtactsPanel.findElements(By.cssSelector(".contact-list-item")); // .media
				
				boolean isContactFound = false;
				int count= verifyContactsList.size();
				System.out.println("contacts list count =" +count);
				
			/*	String before_name = "//*[@id='all-contacts-list']/div[1]/form/div[";
				String after_name = "]/div[2]/div/div[2]/h4/a/span[2]";*/
				
				
				String before_name = "//*[@id='all-contacts-list']/div[1]/form/div[";
				String after_name = "]/div[@class='media-body']/div/div[2]/h4/div/a/span[2]";
				
				String before_link = "//*[@id='all-contacts-list']/div[1]/form/div[";
				String after_link = "]/div[@class='media-body']/div/div[2]/h4/div/a";    // "]/div[2]/div/div[2]/h4/a";
				
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
						
		  Thread.sleep(5000);
		  (new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
		  (new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);
		  System.out.println("clicked confirm delete button");
		  logInfo("clicked on confirm delete button.");
		  // Thread.sleep(5000);
		  confirmationMessage("Contact Deleted.");
		  
		  String contactsPanel = "//*[@id='all-contacts-list']/div[1]/form";
		  waitOnElement("xpath",contactsPanel);
	  }
		  
	
	public void createCallScript(String scriptName) throws Exception{
		 go2adminPage();
		 go2CallScriptPage();
	
		 clickOnLink("linkText","Add New Script");
		 
		 verifyElementPresent("xpath",inputCallScriptName);
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
		 publishedOn_text = "01/01/2016 12:00 PM";
		 publishedEndDate_text = "10/10/2020 12:00 PM";
		 System.out.println("Pulish start date: " +publishedOn_text);
		 System.out.println("Pulish end date: " +publishedEndDate_text);
		 inputTextClear("xpath",inputPublishedOn);
		 inputText("xpath",inputPublishedOn,publishedOn_text);
		 inputTextClear("xpath",inputPublishedEndDate);
		 inputText("xpath",inputPublishedEndDate,publishedEndDate_text);
		// saveAndProceed();
		 clickOnButton("cssSelector",btnSubmitCallscript);
		// waitOnElement("linkText","Back To Call Scripts");
		 Thread.sleep(5000);
		 waitOnElement("linkText","back to office");
		 clickOnLink("linkText","back to office");
		 Thread.sleep(5000);
	 }
	 
	
	public void composeText2CallScript()throws IOException, AWTException {
		logInfo("inside the composeTextOnCallScript method.");
		Robot rb= new Robot();
		// rb.delay(3000);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		//rb.delay(5000);
		rb.keyPress(KeyEvent.VK_C);
		rb.keyRelease(KeyEvent.VK_C);
		rb.keyPress(KeyEvent.VK_A);
		rb.keyRelease(KeyEvent.VK_A);
		rb.keyPress(KeyEvent.VK_L);
		rb.keyRelease(KeyEvent.VK_L);
		rb.keyPress(KeyEvent.VK_L);
		rb.keyRelease(KeyEvent.VK_L);
		rb.keyPress(KeyEvent.VK_SPACE);
		rb.keyRelease(KeyEvent.VK_SPACE);
		rb.keyPress(KeyEvent.VK_S);
		rb.keyRelease(KeyEvent.VK_S);
		rb.keyPress(KeyEvent.VK_C);
		rb.keyRelease(KeyEvent.VK_C);
		rb.keyPress(KeyEvent.VK_R);
		rb.keyRelease(KeyEvent.VK_R);
		rb.keyPress(KeyEvent.VK_I);
		rb.keyRelease(KeyEvent.VK_I);
		rb.keyPress(KeyEvent.VK_P);
		rb.keyRelease(KeyEvent.VK_P);
		rb.keyPress(KeyEvent.VK_T);
		rb.keyRelease(KeyEvent.VK_T);
		rb.keyPress(KeyEvent.VK_S);
		rb.keyRelease(KeyEvent.VK_S);
		rb.delay(2000);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.delay(5000);
	}
	 
	 public void createCallScript(String scriptName, String status, String[] markets, String startDate, String endDate) throws Exception{
		 go2adminPage();
		 go2CallScriptPage();
	
		 clickOnLink("linkText","Add New Script");
		 
		 verifyElementPresent("xpath",inputCallScriptName);
		 inputText("xpath",inputCallScriptName,scriptName);
		 selectFromDropdown("xpath",drpdnCallScriptStatus,"byVisibleText",status);
		 
		 Thread.sleep(10000);
		 
		 switch(status){
		 case "Active":
		//	 composeTextOnCallScript();
			 composeText("xpath",composeBody,composeBodyText);
		 case "Pending":
			 composeText2CallScript();
		 }
		 
		 
		 WebElement chkAll = driver().findElement(By.xpath(inputMarketAll));
		 if(chkAll.isSelected()){
			chkAll.click();
		 }
		 
		 WebElement eMarketPanel = driver().findElement(By.cssSelector(".form-group.check_boxes.required.pyr_crm_call_script_market_languages"));
		 List selectMarkets = eMarketPanel.findElements(By.tagName("span"));
		 int total_markets = selectMarkets.size();
		 System.out.println("total_markets =" +total_markets);
		 
		 int selected_markets = markets.length;
		 System.out.println("selected_markets =" +selected_markets);
		 
		 String before_name = "//*[@id='market_language_checkbox']/div/span[";
		 String after_name = "]/label";
		 
		 String before_input = "//*[@id='market_language_checkbox']/div/span[";
		 String after_input = "]/label/input";
		 
		 if(selected_markets > 0){
			 for(int i=1;i<=total_markets;i++){
				 for(int j=0;j<=selected_markets-1;j++){
					 WebElement emarketName = driver().findElement(By.xpath(before_name+i+after_name));
					 String actName = emarketName.getText().trim();
					 if(actName.equalsIgnoreCase(markets[j])){
						 WebElement input = driver().findElement(By.xpath(before_input+i+after_input));
						 input.click();
						 break;
						 
					 }
				 }
			 }
		 }
		 
		 Robot rb= new Robot();
		 clickOnElement("xpath",inputPublishedOn);
		 inputTextClear("xpath",inputPublishedOn);
		 inputText("xpath",inputPublishedOn,startDate);
		 rb.keyPress(KeyEvent.VK_TAB);
		 rb.keyRelease(KeyEvent.VK_TAB);
		 
		 clickOnElement("xpath",inputPublishedEndDate);
		 inputTextClear("xpath",inputPublishedEndDate);
		 inputText("xpath",inputPublishedEndDate,endDate);
		/* rb.keyPress(KeyEvent.VK_TAB);
		 rb.keyRelease(KeyEvent.VK_TAB);*/
		 clickOnButton("cssSelector",btnSubmitCallscript);
		// saveAndProceed();
		 // confirmationMessage("Call Script is created");
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
				 Thread.sleep(5000);
				 break;
			 }
		 }
	 }
	 
	 public boolean verifyCallScriptFound(String scriptName) throws Exception{
		 logInfo("inside verifyCallScriptFound() method.");
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
				 break;
			 }
		 }
		return isCallScriptFound;
	 }

	 
	 public void selectCallscript4Contact(String fName, String lName) throws Exception{
		  logInfo("inside selectCallscript4Contact() method.");
		  System.out.println("inside selectCallscript4Contact() method.");
		  go2ContactsPage();
		  selectBusinessContact(contactFirstName_text,contactLastName_text);
		  selectContactAction("Start Call Script");
		  /*Thread.sleep(5000);
		  waitOnElement("xpath",lnkStartCallScript);
		  clickOnElement("xpath",lnkStartCallScript);*/
						
		  logInfo("clicked start call script link");
		 
	   }
	 
	 public void submitCallScript(String scriptName, String scriptNotes) throws Exception {
		 logInfo("inside submitCallScript() method.");
		 System.out.println("inside submitCallScript() method.");
		 Thread.sleep(4000);
		 waitOnElement("cssSelector",drpdnPhoneScript);
		 selectFromDropdown("cssSelector",drpdnPhoneScript,"byVisibleText",scriptName);  
		 inputText("cssSelector",textareaPhoneScriptNotes,phoneScriptNotes_text);
		 selectRadioOrCheckbox("cssSelector",radioPhoneScriptInterest);
		 clickOnButton("cssSelector",inputSubmitCallscript);
		 System.out.println("clicked save call script button");
	 }
	 
	 public void addNotes2Contacts(String fName, String lName, String notes) throws Exception{
		  logInfo("inside addNotes2Contacts() method.");
		  System.out.println("inside addNotes2Contacts() method.");
				
		  go2ContactsPage();
		  selectBusinessContact(contactFirstName_text,contactLastName_text);
		 
		  Thread.sleep(5000);
		  /*waitOnElement("xpath",lnkNotes);
		  clickOnElement("xpath",lnkNotes);*/
		  selectContactAction("Notes");
		
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
		  
		  /*waitOnElement("xpath",lnkNotes);
		  clickOnElement("xpath",lnkNotes);*/
		  Thread.sleep(5000);

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
		
		 selectContactAction("Send Message");
		 waitOnElement("cssSelector",recipientsTo);
		 clickOnElement("cssSelector",recipientsTo);
		 inputText("cssSelector",recipientsTo, recepients);
		 inputText("cssSelector",subject_Mail, inputSendMessageSubject_text);
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new Message to Contacts");
		// Robot rb= new Robot();
		/* rb.delay(2000);
		 rb.keyPress(KeyEvent.VK_TAB);
		 rb.keyRelease(KeyEvent.VK_TAB); */
		// composeTextOnElement();
		 //clickOnLink("cssSelector",btnSendMessage);
		 clickOnLink("linkText", "Send");
		 Thread.sleep(10000);
	 }
	 
	 
	 public void setAppointmentWithContacts(String eventName) throws Exception{
		 logInfo("inside setAppointmentWithContacts() method.");
		 System.out.println("inside setAppointmentWithContacts() method.");
		 
		 try{
			 //remove it after tupperware updates to new calendar
			 if ((driver().getCurrentUrl().contains("tupperware")) ||
			 (driver().getCurrentUrl().contains("monat"))){
				 waitOnElement("cssSelector",inputAppointmentName);
				 //Thread.sleep(5000);
				 inputTextClear("cssSelector",inputAppointmentName);
				 inputText("cssSelector",inputAppointmentName,appointmentName_text);
				 inputText("xpath",inputEventLoc,eventLoc_text);
				 inputTextClear("xpath",inputEventStartDate);
				 inputText("xpath",inputEventStartDate, inputAppointmentStartDate);
				 inputTextClear("xpath",inputEventEndDate);
				 inputText("xpath",inputEventEndDate, inputAppointmentEndDate);
				 clickOnElement("xpath", btnEventSave);			
					 
			 }
	    	     else{
	    
			 waitOnElement("xpath",inputEventName);
				inputTextClear("xpath",inputEventName);
				inputText("xpath",inputEventName,eventName);
				selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
				selectFromDropdown("xpath", dpdnCalendarType, "byVisibleText", calendarType_text);
				inputTextClear("xpath",inputEventStartDate);
				inputText("xpath",inputEventStartDate,newEventStartdate_text);
				inputTextClear("xpath",inputEventEndDate);
				inputText("xpath",inputEventEndDate, newEventEnddate_text);
				
				clickOnElement("xpath", newEventMoreotions);
				inputText("xpath",inputEventDescription,calendarEventDescription_text);
				inputText("xpath",inputEventLoc, calendarEventLocation_text);
				inputText("xpath",inputEventAddr1, calendarEventAddr1_text);
				// clickOnElement("xpath",calendarEventImg);
				clickOnElement("xpath","//*[@id='submit-form']");
	    	     }
		 } catch(Exception ex){
				logInfo("Unable to create an event.");
				Assert.assertTrue(false, eventName + " Unable to create an event");
			}
		 
		 
		// cal.addNewCalendarEvent(eventName, newEventStartdate_text, newEventEnddate_text, calendarInvitee_text);
		 
		 /*
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
		 Thread.sleep(10000);*/
	 }
	 
	 

	  public void createTaskFromContacts(String taskName) throws Exception{
		 logInfo("inside createTaskFromContacts() method.");
		 System.out.println("inside createTaskFromContacts() method.");
		 
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 selectContactAction("Create Task");
		 waitOnElement("xpath",taskDescriptionInput);
		 Thread.sleep(5000);
		 inputTextClear("xpath",taskDescriptionInput);
		 inputText("xpath",taskDescriptionInput,contactTaskDesc_text);
		
		 inputTextClear("xpath",inputTaskDuedate);
		 inputText("xpath",inputTaskDuedate,taskDuedate_text);
		 
		 selectFromDropdown("xpath",drpdnTaskPriority,"byVisibleText",taskPriority_text);
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new task");
//		 composeTextOnElement();
		 clickOnButton("xpath",inputAddTask);
		 logInfo("clicked on Add Task button in Contacts page.");
		 
	 }
	 
	 public boolean verifyContactHistory(String expHistory) throws Exception{
		 logInfo("inside verifyContactHistory() method.");
		 System.out.println("inside verifyContactHistory() method.");
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 selectContactAction("View History");
		 Thread.sleep(5000);
	//	 waitOnElement("xpath",tblHistory2);
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
		Thread.sleep(5000);
		 waitOnElement("linkText", "Manage Groups");
		 clickOnLink("linkText", "Manage Groups");
		 
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
		 
		 waitOnElement("linkText", "Add New Group");
		 clickOnLink("linkText", "Add New Group");
		 
		 waitOnElement("xpath",inputGroupName);
		 inputText("xpath",inputGroupName,groupName);
		 
		 clickOnLink("linkText","Create");
		 Thread.sleep(5000);
		 
		 
	 }
	 
	 
	 // This method checks the group on the left side panel in Manage Groups page.
	 
	 public boolean verifyGroupIsPresent(String grpName) throws Exception, Exception{
		 logInfo("inside verifyGroupIsPresent() method.");
		 System.out.println("inside verifyGroupIsPresent() method.");
		 go2Groups();
		
		 boolean isMatchFound = false;
		 Thread.sleep(5000);
		 waitOnElement("xpath",tblGroups2);
		 WebElement tblGroup = driver().findElement(By.xpath(tblGroups2));
		 List allGroups = tblGroup.findElements(By.cssSelector(".group-list-item.list-result"));
		 int total_groups = allGroups.size();
		 System.out.println("Total groups = " +total_groups);
		 
		 
		 String before_group = "//*[@id='list-results']/div[@id='groups-list']/div[";
		 String after_group = "]/a";
		
		 
		 for(int i=1;i<=total_groups;i++){
			 WebElement grp = driver().findElement(By.xpath(before_group+i+after_group));
			 String groupName = grp.getText().trim();
			 System.out.println("Group Name = " +groupName);
			 if(groupName.contains(grpName)){
				 isMatchFound = true;
				logInfo(grpName + " group found in Manage Groups.");
				break;
			 }
			 
		 }
		 
		 if(isMatchFound==false){
			 logInfo(grpName + " group not found in Manage Groups.");
		 }
		 
		return isMatchFound;
	 }
	 
	/* public boolean verifyGroupIsPresent(String expGrpName) throws Exception, Exception{
		 logInfo("inside verifyGroupIsPresent() method.");
		 System.out.println("inside verifyGroupIsPresent() method.");
		 go2Groups();
		
		 boolean isMatchFound = false;
				 
		 WebElement tblGroup = driver().findElement(By.xpath("//*[@id='group-manager-list']"));
		 List<WebElement> allGroups = tblGroup.findElements(By.className("group-list-item-form "));  //group-list-item.clearfix
		 System.out.println("Total Groups =" + allGroups.size());
		 
		 System.out.println("expectedGrp = " + expGrpName);
		 
		 for(WebElement x: allGroups){
			 String actGrp = x.getText().trim();
			 System.out.println("actGrp = " + actGrp);
			 if(actGrp.contains(expGrpName)){
				 System.out.println(actGrp + " ********** match found in Manage Groups page.");
				 logInfo(actGrp + " match found in Manage Groups page.");
				 isMatchFound=true;
				 break;
			 }
		 } 
		 
		if(isMatchFound==false){
			 logInfo(expGrpName + " group not found in Manage Groups.");
			 System.out.println(expGrpName + " group not found in Manage Groups.");
			 // Assert.assertTrue(isMatchFound, grpName + " group not found in Manage Groups.");
		 }	 
		return isMatchFound;
	 }*/
	 
	
	 
	 
	 public void addContact2Group(String grpName, String contact) throws Exception, Exception{
		 logInfo("inside addContact2Group() method.");
		 System.out.println("inside addContact2Group() method.");
		 
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		 
		 /*Thread.sleep(5000);
		 waitOnElement("xpath",lnkAdd2Group);
		 clickOnElement("xpath",lnkAdd2Group);
		 */
		 
		// selectContactAction("Add To Group");
		 selectContactAction(prop.getLocatorForEnvironment(appUrl,"addtoGrp"));
		 selectGroup2Add(groupName_text);
		
	 }
	 
	 public void selectGroup2Add(String grpName) throws Exception, Exception{
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
			 WebElement addGrp = driver().findElement(By.xpath(before_grpAdd+i+after_grpAdd));
			 String actGrpName = eName.getText().trim();
			 System.out.println("actGrpName =" + actGrpName);
			 if(actGrpName.contains(grpName)){
				 // eName.click();
				 addGrp.click();
				 isGrpFound = true;
				 System.out.println(grpName + " group match found to add.");
				 WebElement add = driver().findElement(By.xpath(before_grpAdd+i+after_grpAdd));
				 add.click();
				 break;
			 }
		 }
	 }
	 
	 public void selectGroupAndMoreOption(String groupName, String moreOption) throws Exception{
		 logInfo("Select particular Group under Manage Group and click 'More' to import");
		 WebElement more;
		 
		 go2Groups();
		 boolean isGroupFound = false;
		
		 Thread.sleep(2000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		 System.out.println("Manage Size is "+ gr.size());
		 
		 String beforeGrpName ="div#group-manager-list > div:nth-child(" ;
		 String afterGrpName =") > div.col-md-9 > div > a";
		 
		 String beforeGrpRename = "div#group-manager-list > div:nth-child(" ;
		 String afterGrpRename = ") > div.view-tools.col-md-3 > a:nth-child(1)";
		 
		 String beforeGrpDelete = "div#group-manager-list > div:nth-child(" ;
		 String afterGrpDelete = ") > div.view-tools.col-md-3 > a:nth-child(2)";
		 
		 String beforeGrpMore ="div#group-manager-list > div:nth-child(" ;
		 String afterGrpMore =") > div.view-tools.col-md-3 > a:nth-child(3)";
		 
		 String beforeGrp_Import2Groups ="div#group-manager-list > div:nth-child(" ;
		 String afterGrp_Import2Groups =") > div.view-tools.col-md-3 > ul > li:nth-child(1) > a";
		 
		 String beforeGrp_PrintLbls ="div#group-manager-list > div:nth-child(" ;
		 String afterGrp_PrintLbls =") > div.view-tools.col-md-3 > ul > li:nth-child(2) > a";
		
		 String beforeGrp_exportCSV ="div#group-manager-list > div:nth-child(" ;
		 String afterGrp_exportCSV =") > div.view-tools.col-md-3 > ul > li:nth-child(3) > a";
		
		 String beforeGrp_exportExcel ="div#group-manager-list > div:nth-child(" ;
		 String afterGrp_exportExcel =") > div.view-tools.col-md-3 > ul > li:nth-child(4) > a";
		
		 
		 for (int i=2; i<=gr.size(); i+=2){
			 System.out.println("i =" +i);
			 
			 WebElement gpName = driver().findElement(By.cssSelector(beforeGrpName+i+afterGrpName));	
			 String actGrp = gpName.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(groupName)){
				 isGroupFound = true;
				 
				 	switch(moreOption){
					/*case "Rename" :
						WebElement rename = driver().findElement(By.cssSelector(beforeGrpRename+i+afterGrpRename));	
						rename.click();
						Thread.sleep(3000);
						
						System.out.println("groupName =" +groupName);
						String arr1[] = groupName.split("\\(");
						groupName = arr1[0];
												
						String renameGroup_text = groupName+"Updated";
						System.out.println("renameGroup_text ="+renameGroup_text);
						
						// String beforeSave ="div#group-manager-list > div:nth-child(" ;
						// String afterSave =") > div.col-md-12 > form.simple_form.edit-group-name-form > div.pull-right > input.btn.btn-primary";
						 
						 String beforeSave ="div#group-manager-list > div:nth-child(" ;
						 String afterSave =") > div.col-md-12 > form.simple_form.edit-group-name-form > div.btn-group > input.btn.btn-primary";
						
						 
						 clickOnElement("xpath","//input[@value='"+groupName+"']");
						 inputTextClear("xpath","//input[@value='"+groupName+"']");
						 inputText("xpath","//input[@value='"+groupName+"']",renameGroup_text);
						 
						 int k=i-1;
						 clickOnElement("cssSelector",beforeSave+k+afterSave);
						// confirmationMessage("Group updated");
						 
						break;
					case "Delete" :
						WebElement delete = driver().findElement(By.cssSelector(beforeGrpDelete+i+afterGrpDelete));	
						delete.click();
						break;*/
					case "Import Into Group" :
						more = driver().findElement(By.cssSelector(beforeGrpMore+i+afterGrpMore));	
						more.click();
						WebElement import2grp = driver().findElement(By.cssSelector(beforeGrp_Import2Groups+i+afterGrp_Import2Groups));	
						import2grp.click();
					    break;
					case "Print Labels" :
						more = driver().findElement(By.cssSelector(beforeGrpMore+i+afterGrpMore));	
						more.click();
						WebElement printlbl = driver().findElement(By.cssSelector(beforeGrp_PrintLbls+i+afterGrp_PrintLbls));	
						printlbl.click();
					    break;    
					case "Export Csv" :
						more = driver().findElement(By.cssSelector(beforeGrpMore+i+afterGrpMore));	
						more.click();
						WebElement exportcsv = driver().findElement(By.cssSelector(beforeGrp_exportCSV+i+afterGrp_exportCSV));	
						exportcsv.click();
					    break;  
					case "Export Excel" :
						more = driver().findElement(By.cssSelector(beforeGrpMore+i+afterGrpMore));	
						more.click();
						WebElement exportexcel = driver().findElement(By.cssSelector(beforeGrp_exportExcel+i+afterGrp_exportExcel));	
						exportexcel.click();
					    break;   
				 	}
			 }	
			 
		 }	 if (isGroupFound==false){
			Assert.assertTrue(isGroupFound, groupName + " is not found in the lis of Manage Groups."); 
			 
		 }
	 }

	 
	 
	/* 
	 public void deleteGroup(String groupName) throws Exception{
		 logInfo("inside deleteGroup() method");
		 
		go2Groups();
		
		 boolean isGroupFound = false;
		
		 Thread.sleep(7000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		 System.out.println("Manage Size is "+ gr.size());
		 String beforeGpLi ="div#group-manager-list > div:nth-child(" ;
		 String afterGpLi =") > div:nth-child(1)";
		 String afterDelete = ") > div:nth-child(2) > a:nth-child(2)";
		 
		// for (int i=1; i<=gr.size(); i++){
		 for (int i=2; i<=gr.size(); i=i+2){
			 WebElement gpList = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLi));	
			 String actGrp = gpList.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(groupName)){
				 isGroupFound = true;
				 WebElement grpDelete =  driver().findElement(By.cssSelector(beforeGpLi+i+afterDelete));
				 grpDelete.click();	
				 Thread.sleep(2000);
				// confirmEventDeleteModal();
				 confirmOK();
				 break;
			 }
		 }	 
		 
		 if (isGroupFound==false){
			Assert.assertTrue(isGroupFound, groupName + " not found in Manage Groups."); 
		 }
	 }*/
	 
	 
	 public void deleteGroup(String groupName) throws Exception{
		 logInfo("inside deleteGroup() method");
		 
		 go2Groups();
		
		 boolean isGroupFound = false;
		
		 //Thread.sleep(2000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		 System.out.println("Manage Size is "+ gr.size());
		 String beforeGpLi ="div#group-manager-list > div:nth-child(" ;
		 String afterGpLi =") > div:nth-child(1) > div > a";
		 String afterDelete = ") > div:nth-child(2) > a:nth-child(2)";
		 
		 for (int i=2; i<=gr.size(); i=i+2){
			 WebElement gpList = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLi));	
			 String actGrp = gpList.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(groupName)){
				 isGroupFound = true;
				 WebElement grpDelete =  driver().findElement(By.cssSelector(beforeGpLi+i+afterDelete));
				 grpDelete.click();	
				 confirmOK();
				 break;
			 }
		 }	 
		 
		 if (isGroupFound==false){
			Assert.assertTrue(isGroupFound, groupName + " not found in Manage Groups."); 
		 }
	 }
	 
	 
	 
	/* public void renameGroup(String groupName) throws Exception{
		 logInfo("inside renameGroup() method");
		 System.out.println("inside renameGroup() method");
		 String renameGroup_text = groupName+"Updated";
		
		 clickOnElement("xpath","//input[@value='"+groupName+"']");
		 inputTextClear("xpath","//input[@value='"+groupName+"']");
		 inputText("xpath","//input[@value='"+groupName+"']",renameGroup_text);
		 clickOnElement("xpath","//input[@value='Save']");
		 
	 }*/
	 
	 public void renameGroup(String groupName, String newGroupName) throws Exception{
		 logInfo("inside renameGroup() method");
		 System.out.println("inside renameGroup() method");
		 String renameGroup_text = groupName+"Updated";
		 
		 go2Groups();
			
		 boolean isGroupFound = false;
		
		 //Thread.sleep(2000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		 System.out.println("Manage Size is "+ gr.size());
		 String beforeGpLi ="div#group-manager-list > div:nth-child(" ;
		 String afterGpLi =") > div:nth-child(1) > div > a";
		 String afterRename = ") > div:nth-child(2) > a:nth-child(1)";
		 String afterEdit = ") > div:nth-child(1) > form > div.btn-group > input";
		 
		 for (int i=2; i<=gr.size(); i=i+2){
			 WebElement gpList = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLi));	
			 String actGrp = gpList.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(groupName)){
				 isGroupFound = true;
				 WebElement grpRename =  driver().findElement(By.cssSelector(beforeGpLi+i+afterRename));
				 grpRename.click();	
				 int j = i+1;
				 System.out.println("i = " +i +" j= " +j);
				
				// waitOnElement("cssSelector",beforeGpLi+j+afterEdit);
				// waitOnElement("xpath","//input[@value='"+groupName+"']");
				 clickOnElement("xpath","//input[@value='"+groupName+"']");
				 inputTextClear("xpath","//input[@value='"+groupName+"']");
				 inputText("xpath","//input[@value='"+groupName+"']",renameGroup_text);
				 
				 clickOnElement("cssSelector",beforeGpLi+j+afterEdit);
				 break;
			 }
		 }	 
		 
		 if (isGroupFound==false){
			Assert.assertTrue(isGroupFound, groupName + " not found in Manage Groups."); 
		 }
		
	 }
	 
	 public boolean verifyFileExistsOnDisk(String filepath){
		 logInfo("inside verifyFileExistsOnDisk() method");
		 
		 File file = new File(filepath);
		
		return file.exists();
	 }
	 
	 
	public void searchContactsInGroup(String contactName, String grpName) throws Exception, Exception{
		logInfo("inside searchContactsInGroup() method");
		
		go2Groups();
		
		boolean isGroupFound = false;
			
		 Thread.sleep(2000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		
		 System.out.println("Manage Size is "+ gr.size());
		 String beforeGpLi ="div#group-manager-list > div:nth-child(" ;
		 String afterGpLi =") > div:nth-child(1)";  // remove > div > a 
		 String afterRename = ") > div:nth-child(2) > a:nth-child(1)";
		 String afterDelete = ") > div:nth-child(2) > a:nth-child(2)";
		 String afterMore = ") > div:nth-child(2) > a:nth-child(3)";
		 
		 for (int i=1; i<=gr.size(); i++){
			 WebElement eleGroup = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLi));	
			 String actGrp = eleGroup.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(grpName)){
				 logInfo(actGrp + " group match found in the groups table.");
				 isGroupFound = true;
				 String afterGpLia =") > div:nth-child(1) > div > a";
				 WebElement lnk = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLia));
				 lnk.click();
				 break;
			 }
		 }
	}
	
	
	// verify if task or appointment is displayed in the contacts home page.
	
	
	public void takeActionOnContactEvents(String contactName, String actionName, String actionType) throws Exception, Exception{
		logInfo("inside takeActionOnContactEvents() method");
		boolean isContactMatchFound = false;
		
		 String after_actionType = prop.getLocatorForEnvironment(appUrl,"after_actionType");
		 System.out.println("after_actionType =" +after_actionType);
		
		 String after_contactName = prop.getLocatorForEnvironment(appUrl,"after_contactName");
		 System.out.println("after_contactName =" +after_contactName);
		
		 String after_actionName = prop.getLocatorForEnvironment(appUrl,"after_actionName");
		 System.out.println("after_actionName =" +after_actionName);
		 
		waitOnElement("xpath","//div[@id='actionable-list-items']");
		WebElement eActiontbl = driver().findElement(By.xpath("//div[@id='actionable-list-items']"));
		List allActions = eActiontbl.findElements(By.cssSelector(".row.list-item"));
		int all_actions = allActions.size();
		
		String before_contactName = "//div[@id='actionable-list-items']/div[";
		String before_actionName = "//div[@id='actionable-list-items']/div[";
		String before_actionType = "//div[@id='actionable-list-items']/div[";
			
		if(all_actions>0){
			for(int i=1;i<=all_actions;i++){
				//WebElement econtactName = driver().findElement(By.xpath(before_contactName+i+after_contactName));
				//String actContactName = econtactName.getText().trim();
				
				WebElement eactionName = driver().findElement(By.xpath(before_actionName+i+after_actionName));
				String actActionName = eactionName.getText().trim();
				
				if(actActionName.equalsIgnoreCase(actionName)){
					isContactMatchFound = true;
					logInfo(contactName + " match found in contact home page");
					selectFromDropdown("xpath",before_actionType+i+after_actionType,"byVisibleText",actionType);
					//Thread.sleep(2000);
					logInfo("performed action " +actionType + " for the contact " + contactName);
					break;
				}
			}
		}
		
		if(isContactMatchFound==false){
			logInfo(contactName + " match not found in contact home page");
		}
	}
	
	
	
	// Select contact from contacts list
	
	
	public void selectMultipleContacts(String[] contactNames, String actionName) throws Exception, Exception{
        logInfo("inside selectMultipleContacts() methods");
        
         go2ContactsPage();
        
        waitOnElement("xpath","//*[@id='all-contacts-list']/div[@id='list-body']/form");
        WebElement econtactsList = driver().findElement(By.xpath("//*[@id='all-contacts-list']/div[@id='list-body']/form"));
        List allContacts = econtactsList.findElements(By.cssSelector(".contact-list-item.media"));    // .contact-list-item.list-result.media    
        int all_contacts = allContacts.size();
        
        int contacts2select = contactNames.length;
        System.out.println("Total contacts to be selected  =" +contacts2select);
        System.out.println("Total contacts Found  =" +all_contacts);
        
        boolean isContactMatchFound = false;
        
        String before_name = "//*[@id='all-contacts-list']/div[@id='list-body']/form/div[";
        String after_name = "]/div[@class='media-body']/div[@class='media']/div[@class='media-body']/h4/div[1]/a/span[2]";
        
        String before_input = "//*[@id='all-contacts-list']/div[@id='list-body']/form/div[";
        String after_input = "]/div[@class='media-left']/input";
        
        
        if(all_contacts<=0){
            WebElement eNoContacts = driver().findElement(By.xpath("//*[@id='all-contacts-list']/div[@id='list-body']/form/div[@class='alert alert-danger']"));
            System.out.println("is NoContactsFound =" +eNoContacts.isDisplayed());
        }
        
        if(all_contacts>0){
                for(int i=1;i<=all_contacts;i++){
                    for(int j=0;j<contacts2select;j++){
                        System.out.println("inside for loop");
                    WebElement eName = driver().findElement(By.xpath(before_name+i+after_name));
                    String actContact = eName.getText().trim();
                    if(actContact.equalsIgnoreCase(contactNames[j])){
                    // if(actContact.contains(contactNames[j])){
                        System.out.println("contact matched...");
                        isContactMatchFound = true;
                        WebElement eInput = driver().findElement(By.xpath(before_input+i+after_input));
                        eInput.click();
                        
                        // toggle action
                        
                        WebElement etogglebtn = driver().findElement(By.xpath("//*[@id='contacts-bulk-action-dropdown']/button"));
                        etogglebtn.click();
                        waitOnElement("xpath","//*[@id='contacts-bulk-action-dropdown']/ul");
                        WebElement togglePanel = driver().findElement(By.xpath("//*[@id='contacts-bulk-action-dropdown']/ul"));
                        List allItems = togglePanel.findElements(By.tagName("li"));
                        for(int k=1; k<=allItems.size();k++){
                            String before_liName = "//*[@id='contacts-bulk-action-dropdown']/ul/li[";
                            String after_liName = "]/a";
                            WebElement eliName = driver().findElement(By.xpath(before_liName+k+after_liName));
                            String actActionName = eliName.getText().trim();
                            if(actActionName.equalsIgnoreCase(actionName)){
                                eliName.click();
                                break;
                            }
                        }
                        
                       // break;
                    }
    
                        
                    
                    }
                    
                }
                    
                    
                    
             }

        }
	
	
 // ****************************************************************************************************************************************************
 
 // ******************** RAVI's METHODS ****************************************************************************************************************
 
	
	
	public void addBussinesscontactwithoutInfo(String fName, String lName) throws Exception{
		logInfo("inside contactwithNoInfomation() method. ");			
		go2ContactsPage();			
		waitOnElement("linkText","Add A Contact");
		clickOnLink("linkText", "Add A Contact");
		Thread.sleep(5000);
		waitOnElement("cssSelector",inputContactFirstName);
		inputText("cssSelector",inputContactFirstName,fName);  // contactFirstName_text
		inputText("cssSelector",inputContactLastName,lName);    // contactLastName_text				
		logInfo("Entered all the fields in Business -> AddContact page.");		
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(5000);
	}
	
	
	 public void verifyGroupNdDelete(String groupName) throws Exception{
		 logInfo("inside verifyGroupNdDelete method");
	
		 go2Groups();
		 clickOnLink("linkText", "Manage Groups");
		 boolean isGroupFound = false;
		
		 Thread.sleep(2000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		 System.out.println("Manage Size is "+ gr.size());
		 String beforeGpLi ="div#group-manager-list > div:nth-child(" ;
		 String afterGpLi =") > div:nth-child(1)";
		 String afterdel = ") > div:nth-child(2) > a:nth-child(2)";
		 
		 for (int i=1; i<=gr.size(); i++){
			 WebElement gpList = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLi));	
			 String actGrp = gpList.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(groupName)){
				 isGroupFound = true;
				 WebElement grpMoreSelect =  driver().findElement(By.cssSelector(beforeGpLi+i+afterdel));
				 grpMoreSelect.click();	
				 confirmOK();
				/* confirmEventDeleteModal();*/
				 confirmationMessage("Group Deleted.");
				 break;
			 }			 
		 }	 if (isGroupFound==false){
			Assert.assertTrue(isGroupFound, groupName + " is not found in the list of Manage Groups."); 
			 
		 }
	 }
	 
	 public void reverifyGroup(String groupName) throws Exception{
		 logInfo("inside reverifyGroup method");	
		 clickOnLink("linkText", "Manage Groups");
		 boolean isGroupFound = false;		
		 Thread.sleep(2000);
		 List <WebElement> gr = driver().findElements(By.cssSelector(groupList));
		 System.out.println("Manage Size is "+ gr.size());
		 String beforeGpLi ="div#group-manager-list > div:nth-child(" ;
		 String afterGpLi =") > div:nth-child(1)";		 
		 for (int i=1; i<=gr.size(); i++){
			 WebElement gpList = driver().findElement(By.cssSelector(beforeGpLi+i+afterGpLi));	
			 String actGrp = gpList.getText().trim();
			 System.out.println("Group names "+ actGrp);
			 if (actGrp.contains(groupName)){
				 isGroupFound = false;
				 Assert.assertEquals(isGroupFound, groupName + "  - Groupp is found");
				
				 
			 }			 
		 }	 if (isGroupFound==true){
			 System.out.println("Group is not found");
			 
			 
		 }
	 }
	
	
	 public void addToGroupsList(String grpName) throws Exception{
		 logInfo("addToGroupsList(String grpName method  ");
		boolean isGrpPresent = false; 
		List <WebElement> grp =  driver().findElements(By.cssSelector(grpList));
		System.out.println("Size of groups "+ grp.size());
		Thread.sleep(2000);
		for (int i=1; i<=grp.size();i++){
			WebElement grps = driver().findElement(By.cssSelector(grpList1+i+grpList2));
			System.out.println("name are "+grps.getText().trim());
			if (grps.getText().trim().equalsIgnoreCase(grpName)){
				isGrpPresent= true;
				WebElement cb = driver().findElement(By.cssSelector(grpList1+i+grpCheckbox2)) ;
				cb.click();
				clickOnButton("cssSelector", addGrpBtn);				
				break;				
			}			
		}if (isGrpPresent==false){
			Assert.assertTrue(isGrpPresent, grpName + " is not found" );
		}
	 }
	 
	 
	 
	 public void sendMessage2PrefferedContacts(String fName, String lName, String subject) throws Exception{
		 logInfo("inside sendMessage2PrefferedContacts() method.");
		 System.out.println("inside sendMessage2PrefferedContacts() method.");
		 
		 go2ContactsPage();
		 selectBusinessContact(fName,lName);
		
		 waitOnElement("xpath",lnkSendMesg);
		 Thread.sleep(5000);
		 clickOnElement("xpath",lnkSendMesg);
		 
		 inputText("cssSelector",subject_Mail,subject );
		 Robot rb= new Robot();
		
	//	 composeTextOnElement();
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new message to the contact");
		 clickOnLink("linkText", "Send");
		 Thread.sleep(10000);
	 }
	
	 public void sendMessage2Contacts(String fName, String lName, String recepients,String subject) throws Exception{
		 logInfo("inside sendMessage2Contacts() method.");
		 System.out.println("inside sendMessage2Contacts() method.");
		 
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		/*
		 waitOnElement("xpath",lnkSendMesg);
		 Thread.sleep(5000);
		 clickOnElement("xpath",lnkSendMesg);
		 */
		 selectContactAction("Send Message");
		 waitOnElement("cssSelector",recipientsTo);
		 clickOnElement("cssSelector",recipientsTo);
		 inputText("cssSelector",recipientsTo, recepients);
		 inputText("cssSelector",subject_Mail,subject );
		// Robot rb= new Robot();
		/* rb.delay(2000);
		 rb.keyPress(KeyEvent.VK_TAB);
		 rb.keyRelease(KeyEvent.VK_TAB); */
		// composeTextOnElement();
		 //clickOnLink("cssSelector",btnSendMessage);
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","sending message to a contact");
		 clickOnLink("linkText", "Send");
		 Thread.sleep(10000);
	 }
	
	 public void selectContactActions(String contactAction) throws InterruptedException{
		 logInfo("Inside selectContactActions method");		 
		 String B4rAct = "//*[@id='contact-actions']/div[";
		 String A4rAct = "]/a/span[2]";
		 boolean isActionFound = false;
		 List<WebElement> act = driver().findElements(By.xpath(allActions));
		 System.out.println(act.size());
		 Thread.sleep(2000);
		 for (int i = 1; i<=act.size(); i++){
		     WebElement actions = driver().findElement(By.xpath(B4rAct+i+A4rAct));			 
			 String conatActions = actions.getText();
			 System.out.println(conatActions);
			 if (conatActions.contains(contactAction)){
				 isActionFound = true;
				 actions.click();
				 break;				 
			 }				 
		 }if (isActionFound){
			 Assert.assertTrue(isActionFound,contactAction + " item is not found in the Conatct Actions" );
		 }
		 
	 }

	 public void selectSocialNetwork(String socialNetwork){
		 logInfo("Inside selectSocialNetwork method");
		 
		 List <WebElement> soc = driver().findElements(By.cssSelector(socialList));
		 System.out.println(soc.size());
		 for(WebElement social :soc){
			 System.out.println(social.getText());
			 
			 if (social.getText().equalsIgnoreCase(socialNetwork)){
				 social.click();
				 break;				 
			 }			 
		 }		 
	 }
	
	 
	 public void gmailContacts(String gmail, String passwd) throws Exception{
		 logInfo ("Inside gmailContacts method");
		 logInfo("Enter Gmail credentials and Import all contacts");
			String wndBeforeWindow = driver().getWindowHandle();	
			for(String w : driver().getWindowHandles()){
					if(!w.equalsIgnoreCase(wndBeforeWindow)){
			driver().switchTo().window(w);
			Thread.sleep(3000);
				inputTextClear("cssSelector",inputGmail);
				inputText("cssSelector",inputGmail, gmail);
				clickOnButton("cssSelector", btnGmailNext);
				WebElement staysigned = driver().findElement(By.xpath(chkStaySignedIn));
				String isChecked = staysigned.getAttribute("checked").trim();
				if(isChecked.equalsIgnoreCase("checked")){
					staysigned.click();
				}
				
				inputText("cssSelector",inputGmailPasswd, passwd);
				clickOnButton("cssSelector", btnSignIn);
				Thread.sleep(6000);
				clickOnButton("xpath", "//*[@id='submit_approve_access']");
				Thread.sleep(4000);				
				WebElement gText = driver().findElement(By.cssSelector(gmailText));
				String actImportMsg = gText.getText().trim();
				String expImportMsg = "Close this window and see the import status in bell alerts.";
				String errImportMsg ="There was a problem importing contacts, please try again later or contact Customer Service.";
				System.out.println("actImportMsg = " + actImportMsg);
				
				driver().close();
				driver().switchTo().window(wndBeforeWindow);
				/*if(actImportMsg!=expImportMsg){
					Assert.assertFalse(actImportMsg!=expImportMsg, "Import Unsuccessful");
				}*/
				
			break;
			}
					
			}	 
	 }	
	 
	 
	 
	 public void YahooContacts(String yahoo, String passwd) throws Exception{
		 logInfo("Inside YahooContacts method");
		 logInfo("Enter Yahoo credentials and Import all contacts");
			String wndBeforeWindow = driver().getWindowHandle();	
			for(String w : driver().getWindowHandles()){
					if(!w.equalsIgnoreCase(wndBeforeWindow)){
			driver().switchTo().window(w);
			Thread.sleep(3000);
			
			// check if email and pwd are visible. else switch to main window
			WebElement e = driver().findElement(By.xpath(yaEmail));
			if(!e.isDisplayed()){
				driver().switchTo().defaultContent();
				logInfo("switching back to main window." + yaEmail + " element not found.");
				Assert.assertTrue(e.isDisplayed());
			}
				inputTextClear("xpath",yaEmail);
				inputText("xpath",yaEmail, yahoo);
				clickOnButton("xpath", yaNext);		
				Thread.sleep(3000);			
				
				inputText("xpath",yaPwd, passwd);
				clickOnButton("xpath", yaNext);
				Thread.sleep(3000);
				clickOnButton("cssSelector", yahooAgree);					
				Thread.sleep(6000);
				driver().close();			
				driver().switchTo().window(wndBeforeWindow);			
			break;
			}
					
		}	 
	 }	
	 
	 
	 
	 public void hotmailContacts(String hotmail, String passwd) throws Exception{
		 logInfo("Inside hotmailContacts method");		 
		 logInfo("Enter hotmail credentials and Import all contacts");
		 
			String wndBeforeWindow = driver().getWindowHandle();	
			for(String w : driver().getWindowHandles()){
					if(!w.equalsIgnoreCase(wndBeforeWindow)){
			driver().switchTo().window(w);
			Thread.sleep(3000);
				inputTextClear("cssSelector",hotEmail);
				inputText("cssSelector",hotEmail, hotmail);		
				
				inputText("cssSelector",hotPwd, passwd);
				clickOnElement("cssSelector", hotPwd);
				Thread.sleep(3000);
				clickOnButton("cssSelector", hotSign);
				String hotExpText= "Close this window and see the import status in bell alerts.";
				WebElement alertMsg = driver().findElement(By.cssSelector(hotActText));
				Assert.assertEquals(alertMsg.getText(), hotExpText);
				Thread.sleep(6000);
				driver().close();			
				driver().switchTo().window(wndBeforeWindow);						
			break;
			}
					
		}	 
	 }	
	 	 

	
	 public void eventValidate() throws Exception{
	 logInfo("inside eventValidate() Method");
	 Thread.sleep(2000);
	 inputTextClear("cssSelector", eventName);
	 Thread.sleep(2000);
	 clickOnButton("cssSelector", finalSave);
	 confirmationMessage("Please enter Title name"); 
	 
 }
 
 
   public void validateGmailImport(String groupName, String moreOption, String gmail, String Pwd) throws Exception{
	   logInfo("inside validateGmailImport Method");
	   String wndBeforeWindow = driver().getWindowHandle();
	   WebElement ac = driver().findElement(By.cssSelector(alertCount));			
	   	String al = ac.getText();
		int alertsCount = Integer.parseInt(al);
		int increasedAlerts= alertsCount+1;	
		String increasedAlertsCount = Integer.toString(increasedAlerts);
		System.out.println("Normal count is "+ alertsCount);		   
	   	selectGroupAndMoreOption(groupName , "Import Into Group");
		selectSocialNetwork("Import Gmail");
		gmailContacts(gmail, Pwd);				
		WebElement aftcount = driver().findElement(By.cssSelector(alertCount));	
		String afterCount = aftcount.getText();		
		System.out.println(afterCount + " and  "+ increasedAlertsCount);  		
		Assert.assertNotEquals(afterCount, increasedAlertsCount);		
		driver().switchTo().window(wndBeforeWindow);
		
	   
   }
   
  
   
   public void validateYahooImport(String groupName, String moreOption, String yahoo, String Pwd) throws Exception{
	   logInfo("inside validateYahooImport Method");
	   
	   	WebElement ac = driver().findElement(By.cssSelector(alertCount));				
		String al = ac.getText();
		int alertsCount = Integer.parseInt(al);
		int increasedAlerts= alertsCount+1;	
		String expectedIncreasedAlertsCount = Integer.toString(increasedAlerts);
		System.out.println("Normal count is "+ alertsCount);	
		
		selectGroupAndMoreOption(groupName , "Import Into Group");
		selectSocialNetwork("Import Yahoo");
		YahooContacts(yahoo, Pwd);
		go2ContactsPage();	
		WebElement aftcount = driver().findElement(By.cssSelector(alertCount));
		String afterCount = aftcount.getText();			
		System.out.println(afterCount + " and  "+ expectedIncreasedAlertsCount);  		
		Assert.assertEquals(afterCount,expectedIncreasedAlertsCount);		
   }

   public void validateHotmailImport(String groupName, String moreOption, String hotmail, String Pwd) throws Exception{
	   logInfo("inside validateHotmailImport Method");
	   	WebElement ac = driver().findElement(By.cssSelector(alertCount));			
	    String al = ac.getText();
		int alertsCount = Integer.parseInt(al);
		int increasedAlerts= alertsCount+1;	
		String increasedAlertsCount = Integer.toString(increasedAlerts);
		System.out.println("Normal count is "+ alertsCount);	
		selectGroupAndMoreOption(groupName , "Import Into Group");
		selectSocialNetwork("Import Hotmail");
		hotmailContacts(hotmail, Pwd);
		go2ContactsPage();	
		WebElement aftcount = driver().findElement(By.cssSelector(alertCount));	
		String afterCount = aftcount.getText();			
		System.out.println(afterCount + " and  "+ increasedAlertsCount);  		
		// Assert.assertEquals(afterCount, increasedAlertsCount);	
   
   
   }
   
   
   public void directHotmail(String hotmail, String Pwd) throws Exception{
	   logInfo("inside directHotmail Method");
	   clickOnLink("linkText", "Manage Groups");
	   clickOnLink("linkText", "Import");
	   selectSocialNetwork("Import Hotmail");
		hotmailContacts(hotmail, Pwd);
		go2ContactsPage();	   
   }
 
 
 public void historyItems(String historyItems) throws Exception{		 
	 logInfo("inside historyItems Method");
	 boolean isHistoryItem = false;
	 Thread.sleep(4000);
	 String beforHist = "//*[@id='history-items']/div[";
	 String afterHist = "]/div[2]";
	 List<WebElement> act = driver().findElements(By.xpath(histItems));
	 System.out.println(act.size());		 
	 for (int i = 1; i<=act.size(); i++){
	     WebElement items = driver().findElement(By.xpath(beforHist+i+afterHist));			 
		 String conatActions = items.getText();
		 System.out.println(conatActions);
		 if (conatActions.contains(historyItems)){
			 isHistoryItem= true;
			 items.click();
			 break;				 
		 			}	 
		 }if (isHistoryItem){
			 Assert.assertTrue(isHistoryItem,historyItems + " is not found in the History item" );
		 }
	 
 }
 
 public void validateInvalidAlertsforMailing(String recepients1, String recepients2) throws Exception	{	 
	 logInfo("inside validateInvalidAlertsforMailing Method");
	 
	 String alertMsgText ="Invalid email addresses, please correct: ";	
	 String alertMsgOfNoMailText = "Please add a recipient to your message.";
	 selectContactActions("Send Message");		 
	 waitOnElement("cssSelector",recipientsTo);
	 clickOnElement("cssSelector",recipientsTo);
	 inputText("cssSelector",recipientsTo, recepients1);
	 inputText("cssSelector",subject_Mail, subjectContent1);
	 clickOnLink("linkText", "Send");
	 Thread.sleep(3000);		 
	 WebElement alertMsg = driver().findElement(By.cssSelector(aletMsg));
	 System.out.println(" alert msg is "+alertMsg.getText());
	 Assert.assertEquals(alertMsg.getText(), alertMsgText+recepients1);
	 clickOnButton("cssSelector", btnPhoneScriptSave);		 
	 waitOnElement("cssSelector",recipientsTo);
	 /*clickOnElement("cssSelector",recipientsTo);*/
	 inputTextClear("cssSelector",recipientsTo);
	 inputTextClear("cssSelector",recipientsTo);
	 WebElement recp = driver().findElement(By.cssSelector(recipientsTo));
	 recp.sendKeys(Keys.BACK_SPACE);
	 recp.sendKeys(Keys.BACK_SPACE);
	 recp.sendKeys(Keys.BACK_SPACE);
	 recp.sendKeys(Keys.BACK_SPACE);
	 recp.sendKeys("\u0008");
	 recp.sendKeys("\u0008");
	 clickOnLink("linkText", "Send");
	 Thread.sleep(3000);
	 WebElement alertMsg2 = driver().findElement(By.cssSelector(aletMsg));
	 System.out.println(" alert msg is "+alertMsg2.getText());
	 Assert.assertEquals(alertMsg2.getText(), alertMsgOfNoMailText);
	 clickOnButton("cssSelector", btnPhoneScriptSave);		 
	 waitOnElement("cssSelector",recipientsTo);
	 
	 inputText("cssSelector",recipientsTo, recepients2);
	 inputTextClear("cssSelector",subject_Mail);
	 inputText("cssSelector",subject_Mail, subjectContent1);
	 clickOnLink("linkText", "Send");
	 Thread.sleep(3000);
	 confirmationMessage("Your message has been sent to "+recepients2 ); 
	 
 }
 
 public void replyNForwardMail(String recepients2, String subject) throws Exception{
	 logInfo("inside replyNForwardMail Method");
	
	String Attach1 = "Attach To Message" ;
	String Attach2 = "Insert Into Message Editor" ;    
	selectContactActions("View History");
	clickOnLink("linkText", subject);
	clickOnLink("linkText", "Reply");
	waitOnElement("cssSelector",recipientsTo);
	clickOnLink("linkText", "Send");
	Thread.sleep(3000);	 
	// confirmationMessage("Your message has been sent to "+recepients2 ); 
	/*clickOnLink("linkText", subject);
	clickOnLink("linkText", "Forward");
	Thread.sleep(4000);		
	inputText("cssSelector",recipientsTo, recepients2);
	inputTextClear("cssSelector",subject_Mail);
	inputText("cssSelector",subject_Mail, subject);		
	clickOnButton("cssSelector", attach);
	Thread.sleep(4000);	    	
	clickOnElement("xpath", browseInBr);
	Thread.sleep(5000);
	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\ProfAudio.exe");
	Thread.sleep(5000);
	cm.uploadFooter(Attach1);  	
	Thread.sleep(3000);
	clickOnLink("linkText", "Send");
	Thread.sleep(3000);	    	
	confirmationMessage("Your message has been sent to "+recepients2 ); */
	
 }
 
 
 
 public void uploadMoreThan4MBfile(String fName, String lName, String recepients, String fileName) throws Exception{
	 logInfo("inside uploadMoreThan4MBfile method.");			
		 
		 
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		
		 waitOnElement("xpath",lnkSendMesg);
		 Thread.sleep(5000);	
		 clickOnElement("xpath",lnkSendMesg);			 
		 waitOnElement("cssSelector",recipientsTo);
		 clickOnElement("cssSelector",recipientsTo);
		 inputText("cssSelector",recipientsTo, recepients);
		 inputText("cssSelector",subject_Mail, subjectContent2);
		 clickOnButton("cssSelector", attach);
	    	Thread.sleep(4000);	    	
	    	clickOnElement("xpath", browseInBr);
	    	Thread.sleep(5000);
	    	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\"+fileName+".exe");
			Thread.sleep(5000);
			cm.uploadFooter("Attach To Message"); 
			clickOnLink("linkText", "Send");
			Thread.sleep(10000);
			confirmationMessage(fileName+ ".jpg could not be saved. Max file size is 4 MB");
	 
 }
 
 public void validateEmailErrMsg() throws Exception, Exception{
	 logInfo("inside validateEmailErrMsg() Method");
	 
	 	waitOnElement("cssSelector","div.alert.alert-danger > ul > li");
		String actErrMsg = getTextPresentOnElement("cssSelector","div.alert.alert-danger > ul > li");
		System.out.println("actErrMsg =" +actErrMsg);
		Assert.assertEquals(actErrMsg, "Email has already been taken");		
	 
	/* WebElement alert = driver().findElement(By.cssSelector(mailAlert));
	 String msg = "Email has already been taken";
	 Assert.assertEquals(alert.getText(), msg);*/
	 
 }
 
 public void contactSearch(String searchText) throws Exception{		
	 logInfo("inside conatctSearch() method");
	 	String expResult = "No Contacts Found";		 		 
		Thread.sleep(2000);
		inputTextClear("xpath", contSearch);
		inputText("xpath", contSearch, searchText);
		Thread.sleep(2000);
		clickOnElement("xpath", contSearch);	
		waitOnElement("cssSelector","form#contacts_selected > div.alert.alert-danger");
		WebElement searResult =  driver().findElement(By.cssSelector("form#contacts_selected > div.alert.alert-danger"));
		Assert.assertEquals(searResult.getText(), expResult);
 }
 
 
 public void advancedContactSearch(String searchText) throws Exception{		
	 logInfo("inside advancedContactSearch() method");
	 	String expResult = "No Contacts Found";		 		 
	 	clickOnLink("linkText","Advanced Search");
		Thread.sleep(3000);
		inputTextClear("cssSelector", contFName);
		inputText("cssSelector",contFName,searchText);
		clickOnButton("cssSelector", advSerch);
		Thread.sleep(2000);
		WebElement searResult2 =  driver().findElement(By.cssSelector("form#contacts_selected > div.alert.alert-danger"));   // searchReslt
		Assert.assertEquals(searResult2.getText(), expResult);	
 }
 
 public void downLineSearch(String searchText) throws Exception{	
	 logInfo("inside downLineSearch(String searchText) Method");
	 	String expResult = "No Contacts Found";
	 	clickOnLink("linkText","Downline (");		 
		Thread.sleep(2000);
		inputTextClear("xpath", contSearch);
		inputText("xpath", contSearch, searchText);
		Thread.sleep(2000);
		clickOnElement("xpath", contSearch);			
		WebElement searResult =  driver().findElement(By.cssSelector("form#contacts_selected > div.alert.alert-danger"));
		Assert.assertEquals(searResult.getText(), expResult);
			 
 }
 
 public void groupSearch(String searchText) throws Exception{		
	 logInfo("inside groupSearch() method");
	 
	String expResult =  "No groups found.";
	inputTextClear("xpath", grpSearch);
	inputText("xpath", grpSearch, searchText);
	Thread.sleep(2000);
	clickOnElement("xpath", grpSearch);			
	WebElement searResult =  driver().findElement(By.cssSelector("form#contacts_selected > div.alert.alert-danger"));
	Assert.assertEquals(searResult.getText(), expResult); 
	Thread.sleep(2000);
 
 }
 
 
 public void conatctList(String FName, String Lname) throws Exception{
	 logInfo("inside conatctList(String FName, String Lname)  Method");
	 
	 
	 Thread.sleep(3000);
	 boolean ispresent = false;
	 List <WebElement> all = driver().findElements(By.xpath(allContacts));
	 System.out.println(" All Size is "+all.size());
	 for (int i = 1; i<=all.size(); i++){
		 String b4r1 = "//*[starts-with(@id,'3')]";
		 String b4r2= "]/div[";
		 String b4Con = "//div/form/div[";
		 String a4Con = "]/div[2]/div/div[2]/h4/div/a/span[2]";  //"]/div[2]/div"; ///div/a/span[2]
		 WebElement lis = driver().findElement(By.xpath(b4Con+i+a4Con));
		 System.out.println("conatcts are "+lis.getText());
		 if (lis.getText().trim().equalsIgnoreCase(FName+" "+Lname)){
			 ispresent =true;
			 String a4CheckBox ="]/div[1]/input";
			 WebElement check = driver().findElement(By.xpath(b4Con+i+a4CheckBox)); 
			 check.click();
			 break;				 
		 }			 
	 }if (ispresent==false){
		 Assert.assertTrue(ispresent,FName+" "+Lname +" contact is not found" );
	 }	 
 }
 
 public boolean verifyContactPresent(String FName, String Lname) throws Exception{
	 logInfo("inside conatctList(String FName, String Lname)  Method");
	 
	 
	 Thread.sleep(3000);
	 boolean ispresent = false;
	 List <WebElement> all = driver().findElements(By.xpath(allContacts));
	 System.out.println(" All Size is "+all.size());
	 for (int i = 1; i<=all.size(); i++){			
		 String b4Con = "//div/form/div[";
		 String a4Con = "]/div[2]/div/div[2]/h4/div/a/span[2]";  //"]/div[2]/div"; ///div/a/span[2]
		 WebElement lis = driver().findElement(By.xpath(b4Con+i+a4Con));
		 System.out.println("conatcts are "+lis.getText());
		 if (lis.getText().trim().equalsIgnoreCase(FName+" "+Lname)){
			 ispresent =true;	
			 logInfo(lis.getText().trim() + " business contact match found.");
			 break;				 
		 }			 
	 }if (ispresent==false){
		 Assert.assertTrue(ispresent,FName+" "+Lname +" contact is not found" );
	 }
	return ispresent;	 
 }
 
 
 public void selectContactOptions(String toggleType){
	 logInfo("inside selectContactOptions(String toggleType) Method");
	 
	 clickOnElement("cssSelector", toggle);
	 boolean istogglePresent = false;
	 
	 List <WebElement> lit = driver().findElements(By.cssSelector("ul#contact-dropdown-actions > li >a"));
	 System.out.println(lit.size()+ " size is ");
	 for (WebElement list :lit){
		 System.out.println("List is "+ list.getText());
		 if (list.getText().trim().equalsIgnoreCase(toggleType)){
			 istogglePresent = true;
			 list.click();
			 break;	 
		 }			 
		 
	 }if (istogglePresent==false){
		 Assert.assertEquals(istogglePresent, toggleType+ " is not found in the toggle list");
		 
	 }
	 
 }
 
 
 
 public void handlePDFWindow() throws Exception{			
		logInfo("verify details in pdf page");
		String expText = "No contacts with addresses found.";  
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
			if(!w.equalsIgnoreCase(wndBeforeWindow)){
			driver().switchTo().window(w);
			System.out.println(driver().getTitle());
			WebElement text = driver().findElement(By.cssSelector("div#pageContainer1.page > div.textLayer > div"));
			if(text.getText().trim().equalsIgnoreCase(expText)){
				Assert.assertTrue(true, "Able to get addresses in the pdf window for print labels.");
			}
//			Assert.assertEquals(text.getText(), expText);		
		Thread.sleep(3000);
		driver().close();	
		Thread.sleep(8000);
		driver().switchTo().window(wndBeforeWindow);
		break;
		}
			
				}
	
    } 
 
 
 
 public void taskAlerts() throws Exception{
	 logInfo("inside taskAlerts() Method");
	 
	 String msgText = "can't be blank";
	 String msgText2 = "Task Title can't be blank";
	 
	 selectContactActions("Create Task");	
	 clickOnButton("xpath",inputAddTask);
	 WebElement alertMsg = driver().findElement(By.cssSelector(alrtMsg));
	 Assert.assertEquals(alertMsg.getText(), msgText);
	 WebElement alertMsg2 = driver().findElement(By.cssSelector(alrtMsg2));
	 Assert.assertEquals(alertMsg2.getText(), msgText2);
	 Thread.sleep(2000);
 }
 
 	public void callScriptAlert() throws Exception{
 		logInfo("inside callScriptAlert Method");
	 
	 String msgText = "can't be blank";	 
	 selectContactActions("Start Call Script");	
	 Thread.sleep(2000);
	 clickOnButton("cssSelector",inputSubmitCallscript);
	 Thread.sleep(2000);
	 WebElement alertMsg = driver().findElement(By.cssSelector(alrtMsg));
	 Assert.assertEquals(alertMsg.getText(), msgText);		 
	 Thread.sleep(2000);
 }
 
 public void notesAlert() throws Exception{
	 logInfo("inside notesAlert Method");
		 
		 String msgText = "can't be blank";	 
		 selectContactActions("Notes");	
		 Thread.sleep(2000);
		 waitOnElement("linkText","New Note");	
		 clickOnLink("linkText","New Note");
		 clickOnButton("cssSelector",btnCreateContactNote);
		 Thread.sleep(2000);
		 WebElement alertMsg = driver().findElement(By.cssSelector(alrtMsg));
		 Assert.assertEquals(alertMsg.getText(), msgText);		 
		 Thread.sleep(2000);	 			 
	 }
 
 public void eventAlert() throws Exception{
	 logInfo("inside eventAlert() Method");
	 String alrtMsg =  "Please enter Title name";
	 String alrtMsgforStartDate = "Please enter Start date";
	 selectContactActions("Create Event");
	 Thread.sleep(2000);
	 inputTextClear("cssSelector",inputAppointmentName);
	 clickOnElement("xpath", btnEventSave);	 
	 confirmationMessage(alrtMsg);		 
 }
 
 
 	public void setPreferredEmail2Contact() throws Exception{	
	 logInfo("inside setPreferredEmail2Contact method");
	 
	 String vibeRecipient_text =   getEmail(adminUser_text,appUrl);
	 String mail1 = vibeRecipient_text;  // "vibe.icentris@gmail.com";
	 String mail2 = "test.contact2@icentris.com";
	 
	 Thread.sleep(10000);
	 waitOnElement("linkText", "Edit");
	 clickOnLink("linkText", "Edit");
	 
	 Thread.sleep(5000);
	 clickOnElement("xpath",lnkAddMoreEmails);
	
	 String input1 = "//*[@class='simple_form view-container']/div[3]/div/p/div[1]/div/div/div/input";
	  inputText("xpath",input1, mail1);
	  
	  String btnPrefferedEmail = "//*[@class='simple_form view-container']/div[3]/div/p/div[1]/div/div/div/span[1]/i";
	  clickOnElement("xpath",btnPrefferedEmail);
	 
	  clickOnElement("xpath",lnkAddMoreEmails);
	  
	  String input2 = "//*[@class='simple_form view-container']/div[3]/div/p/div[2]/div/div/div/input";
	  inputText("xpath",input2, mail2);
	  
	  clickOnButton("cssSelector",btnUpdateContact);
	  confirmationMessage("Contact is updated");
	 
 	}
 
 
 /*public void addMultiEmails(String email) throws Exception{	
	 logInfo("inside addMultiEmails Method");
	 
	 String mal1 = "//div[3]/div/p/div[1]/div/div/div/input";
	 String mal2 = "//div[3]/div/p/div[2]/div/div/div/input";
	 String mal3 = "//div[3]/div/p/div[3]/div/div/div/input";
	 String prefer = "//div[3]/div/p/div[3]/div/div/div/span[1]/i";
	 String star = "div[3]/div[2]/div[1]/div[1]/div[3]/span";
	 
	 Thread.sleep(5000);
	 clickOnLink("linkText", "Edit");
	 Thread.sleep(4000);
	 clickOnLink ("linkText", "Add More Email");
	 Thread.sleep(2000);
	 clickOnLink ("linkText", "Add More Email");
	 Thread.sleep(2000);
	 clickOnLink ("linkText", "Add More Email");	
		 
	 inputText("xpath", mal1, recipientsOne_text);
	 Thread.sleep(2000);
	 inputText("xpath", mal2, recipientsGmail_text);
	 inputText("xpath", mal3, email);		
	 clickOnElement ("xpath", prefer);
	 
	 clickOnButton("cssSelector",btnUpdateContact);
	 Thread.sleep(5000);
	 WebElement mailer = driver().findElement(By.cssSelector("div > div > span#preferred-email"));
	 System.out.println(mailer.getText());
	 WebElement mailer1 = driver().findElement(By.cssSelector("div#contact-details-panel > #contact-details > div > div > a"));
	 System.out.println("Mailer1"+mailer1.getText() );
	 WebElement mailer2 = driver().findElement(By.xpath("//*[@id='contact-details']/div[1]/div[1]/div/a"));
	 System.out.println("Mailer2"+mailer2.getText() );
	
 }
*/ 
	 
 
 	public void addContactsAlerts(String FirstName) throws Exception{
		logInfo("inside addContactsAlerts() method. ");
		System.out.println("inside addContactsAlerts() method. ");
		String ExpMsg= "Name and email cannot be blank.";		// Please add the name to your contact. Please add an email to your contact.
		go2ContactsPage();			
		waitOnElement("linkText","Add A Contact");
		clickOnLink("linkText", "Add A Contact");
		Thread.sleep(5000);
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(2000);
		WebElement actMsg = driver().findElement(By.cssSelector(alrtMsg2));
		String actualMsg = actMsg.getText();
		Assert.assertEquals(actualMsg, ExpMsg);			
		inputText("cssSelector",inputContactLastName,lName);
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(2000);
		WebElement actMsg2 = driver().findElement(By.cssSelector(alrtMsg2));
		String actualMsg2 = actMsg2.getText();
		Assert.assertEquals(actualMsg2, ExpMsg);
		inputText("cssSelector",inputContactFirstName,FirstName); 
		inputTextClear("cssSelector",inputContactLastName);  
		clickOnButton("cssSelector",btnUpdateContact);
		confirmationMessage("Contact is created");
		Thread.sleep(2000);
		clickOnLink("linkText", "Edit");
		Thread.sleep(2000);
		inputTextClear("cssSelector",inputContactFirstName);
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(2000);
		WebElement actMsg3 = driver().findElement(By.cssSelector(alrtMsg2));
		String actualMsg3 = actMsg3.getText();
		Assert.assertEquals(actualMsg3, ExpMsg);
		Thread.sleep(2000);
		inputText("cssSelector",inputContactFirstName,FirstName);
		clickOnButton("cssSelector",btnUpdateContact);			
		confirmationMessage("Contact is updated");
	
	}
 
 	public boolean attachResource2SendMessage(String fName, String lName, String recepients,String resName,String resAsset) throws Exception{
		 logInfo("inside attachResource2SendMessage() method.");
		 System.out.println("inside attachResource2SendMessage() method.");
		 boolean isAssetAttached = false;
		 
		 go2ContactsPage();
		 selectBusinessContact(contactFirstName_text,contactLastName_text);
		
		 waitOnElement("xpath",lnkSendMesg);
		 Thread.sleep(5000);
		 clickOnElement("xpath",lnkSendMesg);
		 
		 waitOnElement("cssSelector",recipientsTo);
		 clickOnElement("cssSelector",recipientsTo);
		 inputText("cssSelector",recipientsTo, recepients);
		 inputText("cssSelector",subject_Mail, inputSendMessageSubject_text);
		 composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","attaching a resoruce and sending a message");
		// Robot rb= new Robot();
		/* rb.delay(2000);
		 rb.keyPress(KeyEvent.VK_TAB);
		 rb.keyRelease(KeyEvent.VK_TAB); */
		// composeTextOnElement();
		 clickOnLink("linkText", "Attach Resource File");
		 Thread.sleep(5000);
		 
		 List<WebElement> el = driver().findElements(By.xpath(lstResources));
		 String before_plus = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[2]/div[3]/div[";
		 String after_plus = "]/div/div[1]/a";
		 
		 String before_check = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[2]/div[3]/div[";
		 String after_check = "]/div/div[2]/div/div/div[2]/input";
		 
		 String before_res = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[2]/div[3]/div[";
		 String after_res = "]/div/div[1]";
		 
		 for(int i=1;i<=el.size();i++){
			 WebElement res = driver().findElement(By.xpath(before_res+i+after_res));
			 if(res.getText().trim().contains(resName)){
				 waitOnElement("xpath",before_plus+i+after_plus);
				 driver().findElement(By.xpath(before_plus+i+after_plus)).click();
				 Thread.sleep(3000);
				 waitOnElement("xpath",before_check+i+after_check);
				 clickOnElement("xpath",before_check+i+after_check);
				 Thread.sleep(3000);
				 clickOnElement("xpath",btnAttachAsset);
				 break;
			 }
		 }
		 Thread.sleep(5000);
		 waitOnElement("xpath",lstAsset);
		 List<WebElement> res = driver().findElements(By.xpath(lstAsset));
		 String before = "//*[@id='new_pyr_crm_message']/div[@class='attachment'][";
		 String after = "]/div[1]";
		 
		 for(int i=1;i<=res.size();i++){
			 WebElement assetType = driver().findElement(By.xpath(before+i+after));
			 if(assetType.getText().trim().contains(resAsset)){
				 isAssetAttached = true;
				 Thread.sleep(3000);
				 clickOnLink("linkText","Send");
				 Thread.sleep(5000);
				 break;
			 }
		 }
		 
		 return isAssetAttached;
	 }
 	
 	public boolean removeContactFromGroup(String grpName) throws Exception, Exception{
		 logInfo("inside removeContactFromGroup() method.");
		 System.out.println("inside removeContactFromGroup() method.");
		 boolean isDeleted = false;
		 go2ContactsPage();
		 clickOnLink("linkText", "Manage Groups");
		
		 waitOnElement("xpath","//div[@id='groups-list']/div");
		 List allGrps = driver().findElements(By.xpath("//div[@id='groups-list']/div"));
		 System.out.println("Total Groups = " +allGrps.size());
		 
		 String before_grpName = "//div[@id='groups-list']/div[";
		 String after_grpName = "]/a";
		 
		 String chkGroup = "//*[@id='list-nav']/div/div[1]/input";
				 
		 boolean isGrpFound = false;
		 System.out.println("expGrpName =" + grpName.trim());
		 
		 for(int i=1;i<=allGrps.size();i++){
			 waitOnElement("xpath",before_grpName+i+after_grpName);
			 WebElement eName = driver().findElement(By.xpath(before_grpName+i+after_grpName));
			 String actGrpName = eName.getText().trim();
			 System.out.println("actGrpName =" + actGrpName);
			 if(actGrpName.contains(grpName)){
				 eName.click();
				 isGrpFound = true;
				 System.out.println(grpName + " group match found.");
				 implicityWaits(3);
				 waitOnElement("xpath",chkGroup);
				 clickOnElement("xpath", chkGroup);
				 waitOnElement("xpath","//*[@id='contacts-bulk-action-dropdown']/button");
				 clickOnElement("xpath", "//*[@id='contacts-bulk-action-dropdown']/button");
				 waitOnElement("xpath","//*[@id='remove-from-group']");
				 clickOnElement("xpath", "//*[@id='remove-from-group']");
				 waitOnElement("xpath","html/body/div[4]/div/div/div[2]/button[2]");
				 clickOnElement("xpath", "html/body/div[4]/div/div/div[2]/button[2]");
				 
				 implicityWaits(5);
				 waitOnElement("xpath","//*[@id='contacts_selected']/div[1]");
				 WebElement e = driver().findElement(By.xpath("//*[@id='contacts_selected']/div[1]"));
				 if(e.getText().trim().contains("No Contacts Found")){
					 isDeleted = true;
				 }
				 break;
			 }
		 }
		 return isDeleted;
		
	 }

 	public void validateAppointment() throws Exception, Exception{
 		logInfo("inside validateAppointment() method.");
 		System.out.println("inside validateAppointment() method");
 		 		
 		waitOnElement("xpath",lnkCreateEvent);
 		implicityWaits(5);
 		clickOnElement("xpath",lnkCreateEvent);
 		Thread.sleep(5000);
 		waitOnElement("cssSelector",inputAppointmentName);
 		inputTextClear("cssSelector", inputAppointmentName);
 		clickOnLink("linkText", "More Options");
 		confirmationMessage("Please enter a title for your event.");
 	}
 	
 	public void addBusinessContactsWithoutAddress(String fName, String lName, String email) throws Exception{
		logInfo("inside addBusinessContactsWithoutAddress() method. ");
		System.out.println("inside addBusinessContactsWithoutAddress() method. ");
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
		select.selectByIndex(size-1);
		logInfo("Entered all the fields in Business -> AddContact page.");
		clickOnButton("cssSelector",btnUpdateContact);
		Thread.sleep(5000);
	}
 	
 	public boolean validateContact() throws Exception, Exception{
 		logInfo("inside validateContact() method.");
 		System.out.println("inside validateContact() method.");
 		boolean isValidated = false;
 		implicityWaits(3);
 		waitOnElement("linkText","Edit");
 		clickOnElement("linkText", "Edit");
 		waitOnElement("cssSelector","#pyr_crm_contact_first_name");
 		inputTextClear("cssSelector","#pyr_crm_contact_first_name");
 		clickOnButton("cssSelector",btnUpdateContact);
 		//validation msg should display here, bug has been logged for this.
 		return isValidated;
 	}
 	
 	public boolean validateCheckbox4Contact() throws Exception, Exception{
 		logInfo("inside validateCheckbox4Contact() method.");
 		System.out.println("inside validateCheckbox4Contact() method.");
 		boolean isValidated = false;
 
 		String contactsPanel = "//*[@id='all-contacts-list']/div[1]/form";
 		waitOnElement("xpath",contactsPanel);
 		WebElement econtactsPanel = driver().findElement(By.xpath(contactsPanel));
	  
 		String expVal = contactFirstName_text + " " + contactLastName_text;
 		List<WebElement> verifyContactsList = econtactsPanel.findElements(By.cssSelector(".contact-list-item")); // .media
		
		boolean isContactFound = false;
		int count= verifyContactsList.size();
		System.out.println("contacts list count =" +count);
		
		String before_name = "//*[@id='all-contacts-list']/div[1]/form/div[";
		String after_name = "]/div[@class='media-body']/div/div[2]/h4/div/a/span[2]";
		
		String before_check = "//*[@id='all-contacts-list']/div[1]/form/div[";
		String after_check = "]/div[@class='media-left']/input";   
		
		for(int i=1;i<=count;i++){
			WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
			String name = e.getText().trim();
	
			if(name.equalsIgnoreCase(expVal)){
				isContactFound = true;
				logInfo(name + " business contact match found.");
				WebElement cb = driver().findElement(By.xpath(before_check+i+after_check));
				cb.click();
				System.out.println(name + " selected business contact");
				break;
			}
		}
		
		clickOnElement("xpath","//*[@id='contacts-bulk-action-dropdown']/button");
		clickOnElement("xpath", "//*[@id='group']");
		waitOnElement("xpath","//*[@id='add_to_group']");
		clickOnElement("xpath","//*[@id='add_to_group']");
		confirmationMessage("Please select at least one group.");
		
		for(int i=1;i<=count;i++){
			WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
			String name = e.getText().trim();
	
			if(name.equalsIgnoreCase(expVal)){
				isContactFound = true;
				logInfo(name + " business contact match found.");
				WebElement cb = driver().findElement(By.xpath(before_check+i+after_check));
				if(cb.isSelected()){
					isValidated = true;
					break;
				}
				
			}
		}
 		return isValidated;
 	}
 	

	 public void sendecard2Contacts() throws Exception{
		  logInfo("inside sendecard2Contacts() method.");
		  System.out.println("inside sendecard2Contacts() method.");
		  go2ContactsPage();
		  selectBusinessContact(contactFirstName_text,contactLastName_text);
		  selectContactAction("Send Ecard");
		}
	
	 
	
	
	public void selectContactAction(String actionName) throws Exception, Exception 
	{
		logInfo("inside contactAction() method");
		boolean islinkAvailable = false;
		List<WebElement> links = driver().findElements(By.xpath("//*[@id='contact-actions']/div"));
		int linksSize = links.size();
		System.out.println(linksSize);
		String beforePath = "//*[@id='contact-actions']/div[";
		String afterPath = "]/a/span[2]";
		for(int i=1;i<=linksSize; i++){
			
		WebElement x =	driver().findElement(org.openqa.selenium.By.xpath(beforePath+i+afterPath));
		waitOnElement("xpath",beforePath+i+afterPath);
		String title = x.getText().trim();
		System.out.println("title is " +title);
		if(title.equalsIgnoreCase(actionName)){
			islinkAvailable= true;
			logInfo(actionName+ "action  found in contact details list");
			x.click();
			break;

		}
		}if (islinkAvailable==false){
			logInfo(actionName+ "action not found in contact details list");
			Assert.assertTrue(islinkAvailable,actionName + " is not found in contact details list");
			
		}
	}
 
}
