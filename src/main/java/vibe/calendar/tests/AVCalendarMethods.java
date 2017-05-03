package vibe.calendar.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import common.TestBase;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.inbox.tests.InboxMethods;

public class AVCalendarMethods extends TestBase {
	
	InboxMethods inbox = new InboxMethods();
	BusinessContactsMethods con = new BusinessContactsMethods();
	
	String contactName_text = "Test" + " " + "Contact";
	
	
	// Create a new calendar event.
	public void addNewCalendarEvent(String eventName, String startDate, String endDate) throws Exception{
		logInfo("inside addNewCalendarEvent() method...");
		verifyElementPresent("xpath",btnNewEvent);
		clickOnLink("xpath",btnNewEvent);
		Thread.sleep(3000);
		verifyElementPresent("xpath",inputEventName);
		inputText("xpath",inputEventName,eventName);		
		inputTextClear("xpath",inputEventStartDate);
		inputText("xpath",inputEventStartDate,startDate);
		inputTextClear("xpath",inputEventEndDate);
		inputText("xpath",inputEventEndDate,endDate);
		clickOnElement("xpath", btnMoreOptions);
		/*verifyElementPresent("xpath",dpdnEventType);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
		inputText("xpath",inputEventLoc, eventLoc_text);
		inputText("xpath",inputEventAddr1, eventAddr1_text);
		inputText("xpath",inputEventCity, eventCity_text);
		inputText("xpath",inputEventPincode, eventPincode_text);		
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", "India");
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", eventCtry_text);
		verifyElementPresent("xpath",drpdnEventState);
		selectFromDropdown("xpath", drpdnEventState, "byVisibleText", eventState_text);		
		verifyElementPresent("xpath",btnEventSave);
		clickOnButton("xpath",btnEventSave);*/
	}
	
	public void addNewCalendarEventFromListView(String eventName, String startDate, String endDate, String typeOfEvent ) throws Exception{
		logInfo("inside addNewCalendarEvent() method...");
		verifyElementPresent("xpath",btnAddEventFromList);
		clickOnLink("xpath",btnAddEventFromList);
		verifyElementPresent("xpath",inputEventName);
		inputText("xpath",inputEventName,eventName);		
		inputTextClear("xpath",inputEventStartDate);
		inputText("xpath",inputEventStartDate,startDate);
		inputTextClear("xpath",inputEventEndDate);
		inputText("xpath",inputEventEndDate,endDate);
		clickOnElement("xpath", btnMoreOptions);
		verifyElementPresent("xpath",dpdnEventType);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", typeOfEvent);
		inputText("xpath",inputEventLoc, eventLoc_text);
		inputText("xpath",inputEventAddr1, eventAddr1_text);
		inputText("xpath",inputEventCity, eventCity_text);				
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", "India");
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", eventCtry_text);		
		selectFromDropdown("xpath", drpdnEventState, "byVisibleText", eventState_text);	
		inputText("xpath",inputEventPincode, eventPincode_text);
		Thread.sleep(6000);
		clickOnElement("cssSelector","div > input#submit-form.btn.btn-primary");
	}
		
		
	
	public void addAlldayCalendarEvent(String eventName, String startDate, String endDate) throws Exception{
		logInfo("inside addAlldayCalendarEvent() method...");
		verifyElementPresent("xpath",btnNewEvent);
		clickOnLink("xpath",btnNewEvent);
		//driver().navigate().to(appUrl + "crm/events/new");
		verifyElementPresent("xpath",inputEventName);
		inputText("xpath",inputEventName,eventName);		
		inputTextClear("xpath",inputEventStartDate);
		inputText("xpath",inputEventStartDate,startDate);
		inputTextClear("xpath",inputEventEndDate);
		inputText("xpath",inputEventEndDate,endDate);
		
		clickOnElement("xpath", btnMoreOptions);
		Thread.sleep(5000);
		
		verifyElementPresent("xpath", chkAlldayEvent);
		selectRadioOrCheckbox("xpath", chkAlldayEvent);
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", allDayEventType_text);
		
		inputText("xpath",inputEventLoc, allDayEventLoc_text);
		inputText("xpath",inputEventAddr1, allDayEventAddr_text);
		inputText("xpath",inputEventCity, allDayEventCity_text);
		inputText("xpath",inputEventPincode, allDayEventPincode_text);
		
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", "India");
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", allDayEventCtry_text);
		verifyElementPresent("xpath",drpdnEventState);
		selectFromDropdown("xpath", drpdnEventState, "byVisibleText", allDayEventState_text);
	
		verifyElementPresent("xpath",btnEventSave);
		clickOnButton("xpath",btnEventSave);
	}

	
	public void addRepeatCalendarEvent(String eventName, String startDate, String endDate) throws Exception{
		logInfo("inside addRepeatCalendarEvent() method...");
		logInfo("inside addRepeatCalendarEvent() method...");
		verifyElementPresent("xpath",btnNewEvent);
		clickOnLink("xpath",btnNewEvent);
		//driver().navigate().to(appUrl + "crm/events/new");
		verifyElementPresent("xpath",inputEventName);
		inputText("xpath",inputEventName,eventName);		
		inputTextClear("xpath",inputEventStartDate);
		inputText("xpath",inputEventStartDate,startDate);
		inputTextClear("xpath",inputEventEndDate);
		inputText("xpath",inputEventEndDate,endDate);
		
		clickOnElement("xpath", btnMoreOptions);
		Thread.sleep(5000);
		
		verifyElementPresent("xpath", chkRepeatEvent);
		selectRadioOrCheckbox("xpath", chkRepeatEvent);
		optionsRepeatEventDialog("Daily","End After"); // calling dialog
		
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", repeatEventType_text);
		
		inputText("xpath",inputEventLoc, repeatEventLoc_text);
		inputText("xpath",inputEventAddr1, repeatEventAddr_text);
		inputText("xpath",inputEventCity, repeatEventCity_text);
		inputText("xpath",inputEventPincode, repeatEventPincode_text);
		
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", "India");
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", repeatEventCtry_text);
		verifyElementPresent("xpath",drpdnEventState);
		selectFromDropdown("xpath", drpdnEventState, "byVisibleText", repeatEventState_text);
	
		verifyElementPresent("xpath",btnEventSave);
		clickOnButton("xpath",btnEventSave);
	}

	
	public void optionsRepeatEventDialog(String eventRecurencePeriod , String eventRecurenceEnd) throws Exception{
		logInfo("inside optionsRepeatEventDialog() method...");
		switch(eventRecurencePeriod){
			case "Daily":
				verifyElementPresent("xpath", radioRepEventDaily);
				selectRadioOrCheckbox("xpath",radioRepEventDaily);
				inputTextClear("xpath",inputRepeatEventEvery);
				inputText("xpath",inputRepeatEventEvery,repeatEventEvery_text);
				break;
			case "Day Of The Week":
				selectRadioOrCheckbox("xpath",radioRepEventDayOfWeek);
				inputTextClear("xpath",inputRepeatEventEvery);
				inputText("xpath",inputRepeatEventEvery,repeatEventEvery_text);
				break;
			case "Weekly":
				selectRadioOrCheckbox("xpath",radioRepEventWeekly);
				inputTextClear("xpath",inputRepeatEventEvery);
				inputText("xpath",inputRepeatEventEvery,repeatEventEvery_text);
				break;
			case "Monthly":
				selectRadioOrCheckbox("xpath",radioRepEventMonthly);
				inputTextClear("xpath",inputRepeatEventEvery);
				inputText("xpath",inputRepeatEventEvery,repeatEventEvery_text);
				break;
			case "Yearly":
				selectRadioOrCheckbox("xpath",radioRepEventYearly);
				inputTextClear("xpath",inputRepeatEventEvery);
				inputText("xpath",inputRepeatEventEvery,repeatEventEvery_text);
				break;
		}
		
		switch(eventRecurenceEnd){
			case "No End Date":
				selectRadioOrCheckbox("xpath",radioRepEventNoEndDate);
				break;
			case "End After":
				selectRadioOrCheckbox("xpath",radioRepEventEndAfter);
				inputTextClear("xpath",inputEventOccurance);
				inputText("xpath",inputEventOccurance,eventOccurance_text);
				break;
			case "On Date":
				selectRadioOrCheckbox("xpath",radioRepEventOnDate);
				inputTextClear("xpath",inputRepEventDate);
				inputText("xpath",inputRepEventDate,repEventOnDate_text);
				break;
		 }
		verifyElementPresent("xpath",btnRepEventSave);
		clickOnButton("xpath",btnRepEventSave);
	}
	
	
	public void addQuickCalendarEvent(String eventName, String startDate, String endDate) throws Exception{
		logInfo("inside addQuickCalendarEvent() method...");
		go2CalendarPage();
		Thread.sleep(3000);
		clickOnElement("xpath",btnNewEvent);
		
		verifyElementPresent("xpath",inputEventName);
		inputText("xpath",inputEventName, eventName);
		
		inputTextClear("xpath",inputEventStartDate);
		inputText("xpath",inputEventStartDate, startDate);
		
		inputTextClear("xpath",inputEventEndDate);
		inputText("xpath",inputEventEndDate,endDate);
		
		verifyElementPresent("xpath",btnEventSave);
		clickOnButton("xpath",btnEventSave);
	}
	
	public boolean validateQuickCalendarEvent(String eventName) throws Exception{
		logInfo("inside validateQuickCalendarEvent() method...");
		System.out.println("inside validateQuickCalendarEvent() method...");
		boolean isValidated = false;
		
		go2CalendarPage();
		Thread.sleep(3000);
		clickOnElement("xpath",btnNewEvent);
		
		waitOnElement("xpath",inputEventName);
		inputTextClear("xpath",inputEventName);
		
		verifyElementPresent("xpath",btnEventSave);
		clickOnButton("xpath",btnEventSave);
		
		confirmationMessage("Please enter a title for your event.");
		
		clickOnElement("linkText","More Options");
		waitOnElement("xpath","//*[@id='event_form']/div[2]/div[1]/div/span");
		WebElement e = driver().findElement(By.xpath("//*[@id='event_form']/div[2]/div[1]/div/span"));
		if(e.getText().trim().contains("can't be blank")){
			isValidated = true;
		}
		
		if(isValidated){
			inputText("xpath",inputEventName,eventName);
			String startDate = getDate(0, "MM/dd/yyyy");
			String endDate = getDate(-2, "MM/dd/yyyy");
			
			inputTextClear("xpath",inputEventStartDate);
			inputText("xpath",inputEventStartDate, startDate);
			
			inputTextClear("xpath",inputEventEndDate);
			inputText("xpath",inputEventEndDate,endDate);
			
			verifyElementPresent("xpath",btnEventSave);
			clickOnButton("xpath",btnEventSave);
			
			confirmationMessage("Event end date cannot be before event start date.");
			isValidated = true;
		}
		
		if(isValidated){
			go2CalendarPage();
			Thread.sleep(3000);
			clickOnElement("xpath",btnNewEvent);
			
			inputText("xpath",inputEventName,eventName);
			String startTime = "08:00 AM";
			String endTime = "07:00 AM";
			inputTextClear("xpath",eventStartTime);
			inputText("xpath",eventStartTime,startTime);
			inputTextClear("xpath",eventEndTime);
			inputText("xpath",eventEndTime,endTime);
			verifyElementPresent("xpath",btnEventSave);
			clickOnButton("xpath",btnEventSave);
			
			confirmationMessage("Event start time must be before event end time");
			isValidated = true;
		}
		
		return isValidated;
	}
	public void go2CalendarPage() throws Exception {
		logInfo("inside go2CalendarPage() method...");
		driver().navigate().to(appUrl + "crm/calendar");
		Thread.sleep(3000);
	}
	
	
	public void go2CorporateCalendarPage() throws Exception, Exception{
		System.out.println("inside go2CorporateCalendarPage() method");
		// driver().navigate().to(appUrl + "calendar?admin=true#");
		driver().navigate().to(appUrl + "pyr_core/pyr_admin/dashboard");
		verifyLinkPresent("Marketing");
		clickOnLink("linkText","Marketing");
		verifyLinkPresent("Corporate Calendar");
		clickOnLink("linkText","Corporate Calendar");
		Thread.sleep(5000);
	}
	
	
	public void addCorpCalendarEvent(String eventName, String startDate, String endDate) throws Exception{
		System.out.println("inside addCorpCalendarEvent() method");
		logInfo("inside addCorpCalendarEvent() method...");
		
		verifyElementPresent("xpath",btnNewEvent);
		clickOnLink("xpath",btnNewEvent);
	
		verifyElementPresent("xpath",inputEventName);
		inputText("xpath",inputEventName,eventName);		
		inputTextClear("xpath",inputEventStartDate);
		inputText("xpath",inputEventStartDate,startDate);
		inputTextClear("xpath",inputEventEndDate);
		inputText("xpath",inputEventEndDate,endDate);
		
		clickOnElement("xpath", btnMoreOptions);
		Thread.sleep(5000);
		///
		selectFromDropdown("xpath", dpdnEventType, "byVisibleText", corpEventType_text);
		
		clickOnElement("xpath",eRankDefinitions);
		Thread.sleep(2000);
		WebElement rank = driver().findElement(By.xpath(chkRankAll));
		if(!rank.isSelected()) {
			selectRadioOrCheckbox("xpath",chkRankAll);
		}
		
		clickOnElement("xpath",eMarketLangs);
		Thread.sleep(2000);
		WebElement markets = driver().findElement(By.xpath(chkMarketsAll));
		if(!markets.isSelected()) {
			selectRadioOrCheckbox("xpath",chkMarketsAll);
		}
		
		
		clickOnElement("xpath",eSubscriptionplan);
		Thread.sleep(2000);
		WebElement subscription = driver().findElement(By.xpath(chkSubscriptionAll));
		if(!subscription.isSelected()) {
			selectRadioOrCheckbox("xpath",chkSubscriptionAll);
		}
		
		
		inputText("xpath",inputEventLoc, corpEventLoc_text);
		inputText("xpath",inputEventAddr1, corpEventAddr_text);
		inputText("xpath",inputEventCity, corpEventCity_text);
		inputText("xpath",inputEventPincode,corpEventPincode_text);
		
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", "India");
		selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", corpEventCtry_text);
		verifyElementPresent("xpath",drpdnEventState);
		selectFromDropdown("xpath", drpdnEventState, "byVisibleText", corpEventState_text);
		
		verifyElementPresent("xpath",btnEventSave);
		clickOnButton("xpath",btnEventSave);
		///
		waitForPageToLoad();
		//	Thread.sleep(5000);
		//	verifyElementPresent("xpath",eleMatchEvent);
		//	Thread.sleep(5000);
	}
	
	
	// Verify if event is displayed in the calendar dashboard.
	public boolean verifyEventisDisplayed(String eventName) throws Exception {
		logInfo("inside verifyEventisDisplayed() method...");
		boolean isMatchFound=false;
		try{
			verifyElementPresent("xpath",eleMatchEvent);
			waitOnElement("xpath",eleMatchEvent);
		
			List<WebElement> allElements = driver().findElements(By.xpath(eleMatchEvent));
			for(WebElement x: allElements){
				String event = x.getText().trim();
				if(event.equalsIgnoreCase(eventName)){		
					logInfo(eventName + " match found found in Month view.");
					isMatchFound = true;
					Assert.assertTrue(isMatchFound, eventName + " match found in Month view.");
					break;
				 }
			}
			if(isMatchFound==false){
				logInfo(eventName + " match not found.");
			}
			
		}catch(Exception e){
			e.getMessage();
		}
			return isMatchFound;
	}
	
	 // Search for an event in the calendar
	 public boolean searchCalendarEvent(String eventName) throws Exception {
		System.out.println("inside searchCalendarEvent() method");
		logInfo("inside searchCalendarEvent() method.");	
		boolean isSearchMatchFound=false;
		
		waitOnElement("cssSelector",calSerach);
		clickOnElement("cssSelector",calSerach);
		waitOnElement("xpath",inputEventQuery);
		inputTextClear("xpath",inputEventQuery);
		inputText("xpath",inputEventQuery,eventName);		
		waitOnElement("xpath",eleMatchEvent);
		Thread.sleep(5000);
		List<WebElement> allElements = driver().findElements(By.xpath(eleMatchEvent));
			for(WebElement x: allElements){
				String event = x.getText().trim();
				if(event.equalsIgnoreCase(eventName)){		
					logInfo(eventName + " event search match found.");
					isSearchMatchFound = true;
					break;			
				}
			}

		return isSearchMatchFound;	
	 }
	 
	 public void searchCalendarEventListView(String eventName) throws Exception {
			System.out.println("inside searchCalendarEvent() method");
			logInfo("inside searchCalendarEvent() method.");
			Thread.sleep(5000);
			waitOnElement("xpath",btnCalendarListView);
			clickOnElement("xpath",btnCalendarListView);
			waitOnElement("xpath","inputSearchListView");			
		
			inputTextClear("xpath",inputSearchListView);
			inputText("xpath",inputSearchListView,eventName);
			Thread.sleep(3000);
			
		 }


	 public void updateCalendarEvent(String eventType, String eventName) throws Exception {
		logInfo("inside updateCalendarEvent() method.");
		System.out.println("inside updateCalendarEvent() method.");
		Thread.sleep(6000);
		boolean isMatchFound=false;
		waitOnElement("xpath",eleMatchEvent);
		List<WebElement> allElements = driver().findElements(By.xpath(eleMatchEvent));
		System.out.println(allElements.size());
			for(WebElement x: allElements){
				String event = x.getText();
				System.out.println(event);
				if(event.equalsIgnoreCase(eventName)){		
					isMatchFound = true;
					logInfo(eventName + " match found.");
					x.click();
					
					// waitOnElement("xpath",btnEditEvent);
					Thread.sleep(5000);
					waitOnElement("xpath",btnEditEvent);
					clickOnElement("xpath",btnEditEvent);
					Thread.sleep(5000);
					switch(eventType){
					case "New Event": 
						Thread.sleep(5000);
						verifyElementPresent("xpath",inputEventName);
						inputTextClear("xpath",inputEventName);
						inputText("xpath",inputEventName,newEventName_updtext);		
						verifyElementPresent("xpath",btnEventSave);
						clickOnButton("xpath",btnEventSave);
						break;
					case "All Day": 
						verifyElementPresent("xpath",inputEventName);
						inputTextClear("xpath",inputEventName);
						inputText("xpath",inputEventName,allDayEventName_updtext);		
						verifyElementPresent("xpath",btnEventSave);
						clickOnButton("xpath",btnEventSave);
						break;
					case "Repeat Event": 
						// Thread.sleep(6000);
						verifyElementPresent("xpath",inputEventName);
						inputTextClear("xpath",inputEventName);
						inputText("xpath",inputEventName,repeatEventName_updtext);		
						verifyElementPresent("xpath",btnEventSave);
						clickOnButton("xpath",btnEventSave);
						implicityWaits(5);
						break;
					case "Quick Event": 
						// Thread.sleep(6000);
						verifyElementPresent("xpath",inputEventName);
						inputTextClear("xpath",inputEventName);
						inputText("xpath",inputEventName,quickEventName_updtext);		
						verifyElementPresent("xpath",btnEventSave);
						clickOnButton("xpath",btnEventSave);
						break;
					case "Corporate Calendar Event": 
						System.out.println("inside corp cal event");
						// Thread.sleep(6000);
						verifyElementPresent("xpath",inputEventName);
						inputTextClear("xpath",inputEventName);
						inputText("xpath",inputEventName,corpEventName_updtext);		
						verifyElementPresent("xpath",btnEventSave);
						clickOnButton("xpath",btnEventSave);
						break;
						
					default :
						logInfo("Invalid Event Type entered in updateCalendarEvent() method.");
						break;
					}break;
				}  
			}
		
		if(isMatchFound==false){
			logInfo(eventName + " match not found to update.");
			Assert.assertTrue(isMatchFound, eventName + " event match not found to update.");
		}
			
	}
 
	 
	public void deleteCalendarEvent(String eventType, String eventName) throws Exception, Exception{
		System.out.println("inside deleteCalendarEvent() method.");
		logInfo("inside deleteCalendarEvent() method.");
		waitOnElement("cssSelector",tblCalendar);
		List<WebElement> allElements = driver().findElements(By.xpath(eleMatchEvent));
			for(WebElement element: allElements){
				if(element.getText().equalsIgnoreCase(eventName)){
					logInfo(eventName + " - event match found ...");
					element.click();
					switch(eventType){
					case "New Event": 
						try{
							verifyElementPresent("xpath",btnDeleteEvent);
							Thread.sleep(2000);
							clickOnButton("xpath",btnDeleteEvent);
						//	Thread.sleep(5000);
							Alert alert = driver().switchTo().alert();
							alert.accept();
						} catch (Exception e){
							logInfo(e.getMessage());
						}
							/*(new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
							(new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);*/
							logInfo("clicked on delete button of calendar event");
							break;
							
					case "Allday Event": 
						try{
							verifyElementPresent("xpath",btnDeleteEvent);
							clickOnButton("xpath",btnDeleteEvent);
							Alert alert = driver().switchTo().alert();
							alert.accept();
							/*Thread.sleep(5000);
							(new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
							(new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);*/
							logInfo("clicked on delete button of calendar event");
							break;
						} catch (Exception e){
							logInfo(e.getSuppressed());
						}
					
					case "Repeat Event":
						try{
							verifyElementPresent("xpath",btnDeleteAllInstances);
							clickOnButton("xpath",btnDeleteAllInstances);
							Alert alert = driver().switchTo().alert();
							alert.accept();
							/*Thread.sleep(5000);
							(new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
							(new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);*/
							logInfo("clicked on delete button of calendar event");
							break;
							} catch (Exception e){
								logInfo(e.getSuppressed());
							}
					case "Quick Event": 
						try{
							verifyElementPresent("xpath",btnDeleteEvent);
							clickOnButton("xpath",btnDeleteEvent);
							Alert alert = driver().switchTo().alert();
							alert.accept();
							/*Thread.sleep(5000);
							(new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
							Thread.sleep(1000);
							(new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);*/
							logInfo("clicked on delete button of calendar event");
							break;
							} catch (Exception e){
								logInfo(e.getSuppressed());
							}
					 case "Corporate Calendar Event": 
							try{
								verifyElementPresent("xpath",btnDeleteEvent);
								clickOnButton("xpath",btnDeleteEvent);
								Alert alert = driver().switchTo().alert();
								alert.accept();
								/*Thread.sleep(5000);
								(new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
								(new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);*/
								logInfo("clicked on delete button of calendar event");
								break;
							} catch (Exception e){
								logInfo(e.getSuppressed());
							}
						default :
								logInfo("Invalid Event Type entered in updateCalendarEvent() method.");
								break;
							}
						}
					}
		   }	
		
	 
	 public void addNewEventInvitation(String eventName, String startDate, String endDate) throws Exception{
			logInfo("inside addNewEventInvitation() method...");
			waitOnElement("cssSelector",tblCalendar);
			verifyElementPresent("xpath",btnNewEvent);
			clickOnLink("xpath",btnNewEvent);
			
			verifyElementPresent("xpath",inputEventName);
			inputText("xpath",inputEventName,eventName);		
			inputTextClear("xpath",inputEventStartDate);
			inputText("xpath",inputEventStartDate,startDate);
			inputTextClear("xpath",inputEventEndDate);
			inputText("xpath",inputEventEndDate,endDate);
			
			clickOnElement("xpath", btnMoreOptions);
			Thread.sleep(5000);
			
			selectFromDropdown("xpath", dpdnEventType, "byVisibleText", newEventType_text);
			
			inputText("xpath",inputEventLoc, eventLoc_text);
			inputText("xpath",inputEventAddr1, eventAddr1_text);
			inputText("xpath",inputEventCity, eventCity_text);
			inputText("xpath",inputEventPincode, eventPincode_text);
			
			selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", "India");
			selectFromDropdown("xpath", drpdnEventCtry, "byVisibleText", eventCtry_text);
			verifyElementPresent("xpath",drpdnEventState);
			selectFromDropdown("xpath", drpdnEventState, "byVisibleText", eventState_text);
			
			verifyElementPresent("xpath",btnInviteGuests);
			clickOnButton("xpath",btnInviteGuests);
			
			selectContact2Event(inviteGuestFName_text+" "+inviteGuestLName_text);
			Thread.sleep(3000);
			verifyElementPresent("xpath",inputCalendarSubject);
			inputText("xpath",inputCalendarSubject,inviteGuestEventSubject_text);
			
			verifyElementPresent("xpath",btnEventSave);
			clickOnButton("xpath",btnEventSave);
			Thread.sleep(5000);
		}
	 
	 

public void selectContact2Event(String guestName) throws Exception{
		 logInfo("inside selectContact2Event() method.");
		
		 verifyLinkPresent("All Contacts");
	//	 clickOnLink("linkText","All Contacts");
		 
		 verifyElementPresent("xpath",inputSearchContact);
		 inputText("xpath",inputSearchContact,guestName);
		 
		 //////////Thread.sleep(5000);
		 waitOnElement("xpath",panelInviteGuests);
		 WebElement contactsPane = driver().findElement(By.xpath(panelInviteGuests));
		 List allContacts = contactsPane.findElements(By.xpath("//div[@data-toggle='tooltip']"));
		 int count = allContacts.size();
		 System.out.println("total contacts =" +count );
		 
		 String beforeName = "//*[@id='search-list']/div[";
		 String afterName = "]/div[2]//h4"; 
		 		 
		 String beforeInput = "//*[@id='search-list']/div[";
		 String afterInput = "]/div[3]/input[@type='checkbox']";
		/* 
		 String beforeInput = "//div[@class='content']/div[";
		 String afterInput = "]/div[3]/input[@type='checkbox']";
		 */
		 boolean isContactFound = false;
		 if(count>0){
			 for(int i=count/2;i>0;i--){
				 
				 waitOnElement("xpath",beforeName+i+afterName);
				 WebElement heading = driver().findElement(By.xpath(beforeName+i+afterName));
				 String headingName = heading.getText().trim();
				 System.out.println("Heading =" +headingName);
				 System.out.println("Heading =" +guestName.trim());
				 if(headingName.equalsIgnoreCase(guestName)){
					 System.out.println("match found " +guestName);
					 isContactFound=true;
					 waitOnElement("xpath",beforeInput+i+afterInput);
					 WebElement input = driver().findElement(By.xpath(beforeInput+i+afterInput));
					 input.click();
					/* WebElement input1 = driver().findElement(By.xpath(beforeInput+2+afterInput));
					 input1.click();
					 WebElement input2 = driver().findElement(By.xpath(beforeInput+3+afterInput));
					 input2.click();
					 */
					 //////////Thread.sleep(3000);
				
				 }
			 }
			 verifyElementPresent("cssSelector",btnSaveGuests);
			 clickOnButton("cssSelector",btnSaveGuests);
			 logInfo("clicked on Save button of Invite Guests pane.");
			// break;
		 }
		 if(isContactFound==false){
			 logInfo("contact could not be found = " +guestName);
			 Assert.assertTrue(isContactFound, "contact could not be found = " +guestName);
		 }
	 }
	


	 
	 public void verifyCalenderEventInAllViews(String eventName) throws Exception{
		 logInfo("inside verifyCalenderEventInAllViews() method.");
		 Thread.sleep(5000);
		 waitOnElement("xpath",calendarViewPane);
		 WebElement viewpane = driver().findElement(By.xpath(calendarViewPane));
		 List<WebElement> allButtons =  viewpane.findElements(By.tagName("button"));
		 for(WebElement x : allButtons){
			 String buttonName = x.getText().trim();
			 x.click();
			 logInfo("clicked on " + buttonName +  " view.");
			 System.out.println("clicked on " + buttonName +  " view.");
						
			switch(buttonName){
			case "Day":
				logInfo("inside Day view...");
				Thread.sleep(5000);
				if(buttonName.equalsIgnoreCase("Day")){
					 verifyElementPresent("xpath", calendarDayViewPane);					
					 List<WebElement> dayEvent = driver().findElements(By.cssSelector("div.fc-title"));
					 System.out.println("dayEnets"+ dayEvent.size());				
					 boolean isDayEventMatch = false;
					 for (WebElement events : dayEvent ){
						 System.out.println(events.getText());
						 String dayeventName = events.getText().trim();					 
						 if(dayeventName.equalsIgnoreCase(eventName)){
							isDayEventMatch = true;
							logInfo(eventName + " calendar event match found in day calendar view.");
							break;
						 }	
					  }
					 if(isDayEventMatch==false){
						 logInfo(eventName + " calendar event match not found in day calendar view.");
						 Assert.assertTrue(isDayEventMatch, eventName + " calendar event match not found in day calendar view.");
					 }
				   }	 
			
			/*case "Week":
				logInfo("inside Week view...");
				Thread.sleep(5000);
				if(buttonName.equalsIgnoreCase("Week")){
					 WebElement table = driver().findElement(By.xpath(calendarWeekViewPane));
					 boolean isWeekEventMatch = false;
					 List alltds = table.findElements(By.tagName("td"));
					 int totalDays = alltds.size();
					 logInfo("Total days = " +totalDays);
					 for(int i=2;i<=totalDays;i++){
						 String a_before = "//*[@id='calendar']/div[2]/div/table/tbody/tr/td/div[2]/div/div[4]/table/tbody/tr/td[";
						 String a_mid = "]/div";
						 WebElement aElement = driver().findElement(By.xpath(a_before+i+a_mid));
						 List<WebElement> allLinks = aElement.findElements(By.tagName("a"));
						 for(WebElement l: allLinks){
							 String linkName = l.getText().trim();
							  if(linkName.contains(eventName)){
								 logInfo(eventName + " event name matching in the week view");
								 isWeekEventMatch = true;
								 break;
							 }
						 }
				      }
					 if(isWeekEventMatch=false){
						 logInfo(eventName + " event name not matching in the week view");
						 Assert.assertTrue(isWeekEventMatch,eventName + " event name not matching in the week view");
					 }
				   }*/			
			
			  case "Month":
				  Thread.sleep(5000);
				logInfo("inside Month view...");
				if(buttonName.equalsIgnoreCase("Month")){
					verifyEventisDisplayed(eventName);
				}
		    case "List":
		    	logInfo("inside List view...");
		    	
				if(buttonName.equalsIgnoreCase("List")){
					Thread.sleep(5000);
					 waitOnElement("xpath",calendarViewTable);
					 WebElement table = driver().findElement(By.xpath(calendarViewTable));
					 List allRows = table.findElements(By.tagName("tr"));
					 int rows_count = allRows.size();
					 logInfo("Total events =" +rows_count);
					 boolean isListMatchFound = false;
					 String before = "//*[@id='main-content']/div/div/div/div[1]/div[2]/table/tbody/tr[";				
					 String after = "]/td[1]";
					 for(int i=2;i<=rows_count;i++){
						 WebElement e = driver().findElement(By.xpath(before+i+after));
						 String data = e.getText().trim();
						 if(data.contains(eventName)){
							 isListMatchFound = true;
							 logInfo(eventName + " event match found in List view");
							 break;
						 } 
					 }
					 
					 if(isListMatchFound=false){
						 Assert.assertTrue(isListMatchFound,eventName + " event name not matching in the week view");
					 }
					 
					 clickOnButton("cssSelector", btnBack2Calender);
					 Thread.sleep(6000);					
				}	
			}	 
		 }	
	 }	 
	public boolean verifyPreviousEventDisplayed(String eventName) throws Exception{
		logInfo("Inside verifyPreviousEventDisplayed() method."); 
		boolean isEventFound = false;
		Thread.sleep(5000);
		driver().findElement(By.xpath(btnPrevMonth)).click(); 
		Thread.sleep(3000);
		isEventFound = verifyEventisDisplayed(eventName);
		return isEventFound;
	}
	
	public boolean verifyFutureEventDisplayed(String eventName) throws Exception{
		logInfo("Inside verifyFutureEventDisplayed() method."); 
		boolean isEventFound = false;
		Thread.sleep(5000);
		driver().findElement(By.xpath(btnNextMonth)).click();
		Thread.sleep(4000);
		isEventFound = verifyEventisDisplayed(eventName);
		return isEventFound;
	}
	
	public boolean verifyRepeatCalendarSettings(String eventName, String startDate, String endDate) throws Exception{
		logInfo("Inside verifyRepeatCalendarSettings() method."); 
		boolean isRepeatSettingsFound = false;
		verifyElementPresent("xpath",btnNewEvent);
		clickOnLink("xpath",btnNewEvent);

		verifyElementPresent("xpath",inputEventName);
		inputText("xpath",inputEventName,eventName);		
		inputTextClear("xpath",inputEventStartDate);
		inputText("xpath",inputEventStartDate,startDate);
		inputTextClear("xpath",inputEventEndDate);
		inputText("xpath",inputEventEndDate,endDate);
		
		clickOnElement("xpath", btnMoreOptions);
		Thread.sleep(5000);
		
		verifyElementPresent("xpath", chkRepeatEvent);
		selectRadioOrCheckbox("xpath", chkRepeatEvent);
		optionsRepeatEventDialog("Daily","End After");			
		
		waitOnElement("linkText","Edit");
		clickOnElement("linkText","Edit");
		WebElement repeatDaily = driver().findElement(By.xpath(chkRepeatDaily));
		WebElement endsAfter = driver().findElement(By.xpath(chkEndsAfter));
		if(repeatDaily.isSelected() && endsAfter.isSelected()){
			isRepeatSettingsFound = true;
		}
		verifyElementPresent("xpath",btnRepEventSave);
		clickOnButton("xpath",btnRepEventSave);
		return isRepeatSettingsFound;
		
	}
	
	public boolean verifyEventsListOptions(String eventName) throws InterruptedException{
		logInfo("Inside verifyEventsListOptions() method."); 
		boolean isOptionsFound = false;
		driver().findElement(By.xpath(btnList)).click();
		verifyElementPresent("xpath", eventsList);
		List<WebElement> events = driver().findElements(By.xpath(eventsList));
		
		String before = "//tr[contains(@id,'event')][";
		String after = "]/td[1]/a";
		
		String optionBefore = "//tr[contains(@id,'event')][";
		String optionAfter = "]/td[3]/div/div/div/button";
		
		String eventOptionBefore = "//tr[contains(@id,'event')][";
		String eventOptionAfter = "]/td[3]/div/div/div/ul/li[";
		String txtOption = "]/a";
		
		for(int i=1;i<=events.size();i++){
			WebElement event = driver().findElement(By.xpath(before+i+after));
			if(event.getText().equalsIgnoreCase(eventName)){
				WebElement eventOptions = driver().findElement(By.xpath(optionBefore+i+optionAfter));
				eventOptions.click();
				String shareEvent = driver().findElement(By.xpath(eventOptionBefore+i+eventOptionAfter+1+txtOption)).getText();
				String editEvent = driver().findElement(By.xpath(eventOptionBefore+i+eventOptionAfter+2+txtOption)).getText();
				String deleteEvent = driver().findElement(By.xpath(eventOptionBefore+i+eventOptionAfter+3+txtOption)).getText();
				if(shareEvent.equalsIgnoreCase(txtShare) && editEvent.equalsIgnoreCase(txtEdit) && deleteEvent.equalsIgnoreCase(txtDeleteEvent)){
					isOptionsFound = true;
				}
			}
		}
		return isOptionsFound;
	}     
  
	 
	 public void back2Calender() throws Exception{
		 verifyElementPresent("linkText","Back To Calendar");
		 clickOnLink("linktext","Back To Calendar");
		 
	 }
	 
	 public void editCalendarEvent(String eventName) throws Exception {
			logInfo("Edit the calender event ");		
			boolean isMatchFound=false;
			waitOnElement("xpath",eleMatchEvent);
			List<WebElement> allElements = driver().findElements(By.xpath(eleMatchEvent));
				for(WebElement x: allElements){
					String event = x.getText().trim();
					if(event.equalsIgnoreCase(eventName)){		
						logInfo(eventName + " match found.");
						x.click();
						isMatchFound = true;
						Thread.sleep(2000);					
						waitOnElement("xpath",btnEditEvent);
						clickOnElement("xpath",btnEditEvent);
						Thread.sleep(5000);
						break;						
					}
				}
				if (isMatchFound==false){					
					Assert.assertTrue(isMatchFound, eventName + " is not available in Calender.");
				}				
	 		}
	 
	 public void getInvitedcount(String guestType){
		 List <WebElement> gst  = driver().findElements(By.cssSelector(guest));
		 System.out.println(gst.size());
		 for(int i=1; i <= gst.size(); i++){
			 WebElement guests =  driver().findElement(By.cssSelector(guest1+i+guest2));
			 String g = guests.getText().trim();
			 if (g.equalsIgnoreCase(guestType)){				 
				WebElement count = driver().findElement(By.cssSelector(guest1+i+inviteguest2));
				count.getText();
				System.out.println(count.getText());
				break;
			 }			 
		 }		 
	 }
	 
	 
 	public void verifyInvitedGuests(String guestType, String eventName) throws Exception{
 	 logInfo("Get count of Guests and Invite the guest by selecting conatct mail Ids, verify invited conatcts.");
	 List <WebElement> gst  = driver().findElements(By.cssSelector(guest));		 
	 for(int i=1; i <= gst.size(); i++){
		 WebElement guests =  driver().findElement(By.cssSelector(guest1+i+guest2));
		 String g = guests.getText().trim();
		 if (g.equalsIgnoreCase(guestType)){				 
			WebElement count = driver().findElement(By.cssSelector(guest1+i+inviteguest2));
			String b4Invite = count.getText();				
			int b4i = Integer.parseInt(b4Invite);
			System.out.println("before count is "+b4i);
			clickOnButton("cssSelector",inviteBtn );
			waitOnElement("cssSelector",titleInvite );
			clickOnLink("linkText", "All Contacts");	 
			List <WebElement> mails = driver().findElements(By.cssSelector(emailList));
			System.out.println(mails.size());	 
			if (mails.size()==0){		 
				 System.err.println("No contacts are available to invite");
			}
			else if(mails.size()==15){ 
			 	for (int j =1; j<=mails.size()-10; j++){
			 		WebElement mm = driver().findElement(By.cssSelector(emailList1+j+emailList2));		 		
			 		System.out.println(mm.getText());
			 		mm.click();
			 		Thread.sleep(2000);	
			 	}	
			}
			else{
	 			for(int j =1; j<=mails.size()-22; j++){
			 		WebElement mm = driver().findElement(By.cssSelector(emailList1+j+emailList2));		 		
			 		System.out.println("original "+mm.getText());
			 		mm.click();
			 		Thread.sleep(2000);
			 	}
		 	}
	  		
			System.out.println("Retrive count of Selected mail Ids");				  		
	  		List <WebElement> selectedMail = driver().findElements(By.cssSelector(conList));
	  		int noMailsId = selectedMail.size();
	  		System.out.println("selected mail Id count is "+noMailsId);
	  		int aftInviatation = b4i+noMailsId;
	  		String afterInvited = Integer.toString(aftInviatation);	
	  		Thread.sleep(2000);
	  		getText("cssSelector", saveInv, "Text is");
	  		clickOnButton("cssSelector", saveInv);
	  		Thread.sleep(5000);		  				  		
	  		clickOnButton("xpath",btnEventSave);				  		
	  		//confirmationMessage(eventUpdate);
	  		Thread.sleep(5000);
	  		go2CalendarPage();	  	
	  		editCalendarEvent (eventName);	
	  		Thread.sleep(4000);				  		
	  		WebElement Invitedcount = driver().findElement(By.cssSelector(invCount));				  		
	  		Assert.assertEquals(Invitedcount.getText(), afterInvited);			  		
	  		break;
 		}			 
	  }
   }
	 

	 public void invitation(String eventName) throws Exception{	
	     logInfo("Verify the invited contact");
		 clickOnButton("cssSelector",inviteBtn );
		 waitOnElement("cssSelector",titleInvite );
		 clickOnLink("linkText", "All Contacts");	 
		 List <WebElement> mails = driver().findElements(By.cssSelector(emailList));
		 System.out.println(mails.size());	 
		 if (mails.size()==0){		 
			 System.err.println("No contacts are available to invite");
	 	 }
		 else{
 			for(int i =1; i<=mails.size(); i++){
		 		WebElement mm = driver().findElement(By.cssSelector(emailList1+i+emailList2));		 		
		 		mm.click();
		 		Thread.sleep(2000);
		 		List <WebElement> selectedMail = driver().findElements(By.cssSelector(conList));			  		
		  		for (WebElement Selectedemails : selectedMail){	
		  			String selectedContact = Selectedemails.getText();	
		  			System.out.println("selectedContact"+selectedContact);
		  			clickOnButton("cssSelector", saveInv);
			  		Thread.sleep(5000);				  		
			  		waitOnElement("xpath",btnEventSave);
			  		inputText("cssSelector", claBody, claBodyText);
			  		clickOnButton("xpath",btnEventSave);				  		
			  		confirmationMessage(eventUpdate);
	     
	   //  String selectedContact  = "Rani Kidambi";
			  		go2CalendarPage();				  		
			  		editCalendarEvent (eventName);	
			  		Thread.sleep(4000);	
			  		WebElement Invitedcount = driver().findElement(By.cssSelector(invCount));	
			  		Invitedcount.click();				  		
			  		Thread.sleep(2000);				  		
			  		 boolean isInivtedContact = false;
					   List <WebElement > li =  driver().findElements(By.cssSelector(invitees));
					   for (WebElement list : li ){
						   System.out.println(list.getText()+ "names ");
						   if (list.getText().equalsIgnoreCase(selectedContact));
								isInivtedContact =true;								
								Assert.assertEquals(list.getText(), selectedContact);
								inputTextClear("cssSelector", inputComposeEmailSubject);
								inputText("cssSelector", inputComposeEmailSubject,QuickEventDescription_text  );
								clickOnButton("cssSelector", send_Cal);
								//confirmationMessage("Message sent to: "+selectedContact);
								break;
						   }
						   if (isInivtedContact==false){
							   Assert.assertTrue(isInivtedContact,selectedContact + " - contact is not available." );
							  
						   }  		
						   break;			  			
		  				}	
		 				break;
		 		}
 				}
 	   }
		 
	   public void composeBody(String Event, String bodyTexts) throws Exception{
		   List <WebElement> gst  = driver().findElements(By.cssSelector(guest));		 
		   for(int i=1; i <= gst.size(); i++){
				 WebElement guests =  driver().findElement(By.cssSelector(guest1+i+guest2));
				 String g = guests.getText().trim();
				 if (g.equalsIgnoreCase("Invited")){				 
					WebElement counts = driver().findElement(By.cssSelector(guest1+i+inviteguest2));
					String invCount = counts.getText();
					int invitedCount = Integer.parseInt(invCount);
					
					if (invitedCount!=0){
						inputText("cssSelector", bodyText, bodyTexts);
						Thread.sleep(4000);						
						clickOnButton("xpath",btnEventSave);
						//confirmationMessage(eventUpdate);
						go2CalendarPage();
						searchCalendarEvent(Event);
						editCalendarEvent (Event);
						Thread.sleep(4000);						
						WebElement bodyTxt = driver().findElement(By.cssSelector(bodyText));
						Assert.assertEquals(bodyTxt.getText().trim(),bodyTexts);
						break;						
					}else{
						System.err.println("No Invitees are available , so not able to compose body.");
								}	
				 			}
				 }
	   		}
	   
	   
	   public void calenderToolBar(String toolItem) throws Exception{
		   logInfo("Select required tab in the Calender Tool bar");
		   
		   List <WebElement> tb = driver().findElements(By.cssSelector(caltoolBar));		   
		   for (WebElement toolbar : tb){
			   String tools = toolbar.getText();			
			   if (tools.trim().equalsIgnoreCase(toolItem)){
				   toolbar.click();
				   Thread.sleep(4000);
				   break;
			   }			   
		   }
	   }
	   
	   public void deleteListEvent(String eventname) throws Exception{
		   List <WebElement> itemss = driver().findElements(By.cssSelector(items));
		   System.out.println(itemss.size());
		   boolean isEventPresent = false;
		   
		   for (WebElement event :itemss){
			   String events = event.getText().trim();
			   if (events.equalsIgnoreCase(eventname)){	
				   isEventPresent= true;
				   event.click();
				   Thread.sleep(2000);
				   clickOnButton("cssSelector",deleteCal);
				   confirmOK();			   
				   break;				   
			   }			
		   } if (isEventPresent==false){
			   Assert.assertTrue(isEventPresent,eventname + " is not available in the list view." );
		   }
		   
		   
	   }
	   
	   
	   public boolean  verifyListEvent(String eventname) throws Exception{
		   Thread.sleep(5000);
		   waitOnElement("cssSelector",items);
		   List <WebElement> itemss = driver().findElements(By.cssSelector(items));
		   System.out.println(itemss.size());
		   boolean isEventPresent = false;
		   
		   for (WebElement event :itemss){
			   String events = event.getText().trim();
			   System.out.println(events);
		      if (events.equalsIgnoreCase(eventname)){	
				   isEventPresent= true;				   			  		   
				   break;				   
			   }			
		   } 
		   if (isEventPresent==false){
			   Assert.assertTrue(isEventPresent,eventname + " is not available in the list view." );
		   }
		return isEventPresent;
	   }   
	   
	   public boolean  verifyListEventNotToPresent(String eventname) throws Exception{
		   List <WebElement> itemss = driver().findElements(By.cssSelector(items));
		   System.out.println(itemss.size());
		   boolean isEventPresent = true;
		   
		   for (WebElement event :itemss){
			   String events = event.getText().trim();
			   System.out.println(events);
		      if (events.equalsIgnoreCase(eventname)){	
				   isEventPresent= false;
				   Assert.assertTrue(isEventPresent,eventname + " is available in the list view." );				  		   
				   break;				   
			   }			
		   } 
		   if (isEventPresent==true){
			   System.out.println("Succeed , Event is present  - "+eventname );
		   }
		return isEventPresent;
	   }   
		  
	public void selectMoreOptioninListView(String eventName, String actionType) throws Exception{
	   //calenderToolBar("List");
	  Thread.sleep(5000);
	  waitOnElement("xpath","//*[@id='calendar']/div[1]/div[2]/button[4]");
	  clickOnElement("xpath","//*[@id='calendar']/div[1]/div[2]/button[4]");
	   boolean isPresent = verifyListEvent(eventName);
	   if (isPresent= true){		   
		   selectEventActionsUnderListView(eventName, actionType);
		   
	   }else{		   
		   Assert.assertTrue(isPresent, eventName + " event is not present");
	   }
	   
	
	   
	   /*inputTextClear("cssSelector", filtCal);
	   inputText("cssSelector", filtCal, eventName);
	   submitElement("cssSelector", filtCal);*/   
	}
	
	
	public void selectEventActionsUnderListView(String eventname, String actionType) throws Exception{
		logInfo("In List View, verifies the event then select option like Share, edit , delete");
		String eventB4r ="tr:nth-child(";
		String eventAft = ") > td:nth-child(1) > a";
		String actionAft = ") > td:nth-child(3) > div > div.more-options > div";
		String moreOptAft =" > ul > li";
		
		List <WebElement> itemss = driver().findElements(By.cssSelector(items));
		   System.out.println(itemss.size());
		   boolean isEventPresent = false;
		   for (int i=2 ; i<=itemss.size()*2; i++)  {
			   WebElement event = driver().findElement(By.cssSelector(eventB4r+i+eventAft));
			   String events = event.getText().trim();
			   System.out.println(events);
		      if (events.equalsIgnoreCase(eventname)){	
				   isEventPresent= true;	
				   WebElement moreOp = driver().findElement(By.cssSelector(eventB4r+i+actionAft));
				   moreOp.click();
				   boolean isoptionsPresent = false;
				   List <WebElement> options = driver().findElements(By.cssSelector(eventB4r+i+actionAft+moreOptAft));				   
				   for (WebElement op : options){					   
					   if (op.getText().trim().equalsIgnoreCase(actionType)){
						   isoptionsPresent= true;
						   op.click();
						   Thread.sleep(3000);
						   break;
					   }
					   
				   } if (isoptionsPresent ==false){
					   Assert.assertTrue(isoptionsPresent ,actionType +" options is present in Action Type Options." );
				   }				   
				   break;				   
			   }i++;			
		   } 
		   if (isEventPresent==false){
			   Assert.assertTrue(isEventPresent,eventname + " is not available in the list view." );
		   }
		
	   }  	

	//neelima
	
	public void createEventWithMoreOptions(String eventName, String startDate, String endDate) throws Exception{
		logInfo("inside verifyNewInviteeCount4Event() method...");
		System.out.println("inside verifyNewInviteeCount4Event() method...");
		Thread.sleep(5000);
		clickOnElement("xpath",btnNewEvent);
		
		verifyElementPresent("xpath",inputEventName);
		inputText("xpath",inputEventName, eventName);
		inputTextClear("xpath",inputEventStartDate);
		inputText("xpath",inputEventStartDate, startDate);
		
		inputTextClear("xpath",inputEventEndDate);
		inputText("xpath",inputEventEndDate,endDate);
		
		verifyElementPresent("linkText","More Options");
		clickOnElement("linkText","More Options");
		Thread.sleep(5000);
		
	}

	
	public void verifyInvitedGuestsInAdmin(String guestType, String eventName) throws Exception{
	 	 logInfo("Admin- Get count of Guests and Invite the guest by selecting conatct mail Ids, verify invited conatcts.");
		 List <WebElement> gst  = driver().findElements(By.cssSelector(guest));		 
		 for(int i=1; i <= gst.size(); i++){
			 WebElement guests =  driver().findElement(By.cssSelector(guest1+i+guest2));
			 String g = guests.getText().trim();
			 if (g.equalsIgnoreCase(guestType)){				 
				WebElement count = driver().findElement(By.cssSelector(guest1+i+inviteguest2));
				String b4Invite = count.getText();				
				int b4i = Integer.parseInt(b4Invite);
				System.out.println("before count is "+b4i);
				clickOnButton("cssSelector",inviteBtn );
				waitOnElement("cssSelector",titleInvite );
				clickOnLink("linkText", "All Contacts");	 
				List <WebElement> mails = driver().findElements(By.cssSelector(emailList));
				System.out.println(mails.size());	 
				if (mails.size()==0){		 
					 System.err.println("No contacts are available to invite");
				}
				else if(mails.size()==15){ 
				 	for (int j =1; j<=mails.size()-10; j++){
				 		WebElement mm = driver().findElement(By.cssSelector(emailList1+j+emailList2));		 		
				 		System.out.println(mm.getText());
				 		mm.click();
				 		Thread.sleep(2000);	
				 	}	
				}
				else{
		 			for(int j =1; j<=mails.size()-22; j++){
				 		WebElement mm = driver().findElement(By.cssSelector(emailList1+j+emailList2));		 		
				 		System.out.println("original "+mm.getText());
				 		mm.click();
				 		Thread.sleep(2000);
				 	}
			 	}
		  		
				System.out.println("Retrive count of Selected mail Ids");				  		
		  		List <WebElement> selectedMail = driver().findElements(By.cssSelector(conList));
		  		int noMailsId = selectedMail.size();
		  		System.out.println("selected mail Id count is "+noMailsId);
		  		int aftInviatation = b4i+noMailsId;
		  		String afterInvited = Integer.toString(aftInviatation);	
		  		Thread.sleep(2000);
		  		getText("cssSelector", saveInv, "Text is");
		  		clickOnButton("cssSelector", saveInv);
		  		Thread.sleep(5000);		  				  		
		  		clickOnButton("xpath",btnEventSave);				  		
		  		//confirmationMessage(eventUpdate);
		  		Thread.sleep(5000);
		  		go2CorporateCalendarPage();	  	
		  		editCalendarEvent (eventName);	
		  		Thread.sleep(4000);				  		
		  		WebElement Invitedcount = driver().findElement(By.cssSelector(invCount));				  		
		  		Assert.assertEquals(Invitedcount.getText(), afterInvited);			  		
		  		break;
	 		}			 
		  }
	   }
	
	//Try to select Event Type which would be beside AddEvent in List View page.
	public void eventTypeSelection (String typeOfEvent) throws Exception{
		logInfo("Entered into 'eventTypeSelection' method");
		boolean isTypePresent = false;
		clickOnElement("cssSelector", eventTypeDots);
		Thread.sleep(2000);
		List <WebElement> et = driver().findElements(By.cssSelector(eventType));
		System.out.println(et.size() + " size is");
		for (WebElement ets : et){
			System.out.println("ets are "+ ets.getText());
			if (ets.getText().equalsIgnoreCase(typeOfEvent)){
				isTypePresent= true;
				ets.click();
				Thread.sleep(5000);
				break;
			}			
		}if(isTypePresent == false){
			Assert.assertTrue(isTypePresent,typeOfEvent +" is not present in options." );
		}	
	}
	
	
	

		
		
	
	public void verifyNewInvitees(String guestType, String eventName) throws Exception{
	 	logInfo("inside verifyNewInvitees() method.");
	 	System.out.println("inside verifyNewInvitees() method.");
	 	
	 	waitOnElement("xpath",btnInviteGuests);
		clickOnElement("xpath", btnInviteGuests);
		
		waitOnElement("cssSelector",titleInvite );
		//clickOnLink("linkText","Previously Invited");
		clickOnLink("linkText", "All Contacts");	 
		waitOnElement("cssSelector",emailList);
		List <WebElement> mails = driver().findElements(By.cssSelector(emailList));
		System.out.println(mails.size());	
		
		if (mails.size()==0){		 
			 System.err.println("No contacts are available to invite");
		}
		else{
			for(int j=1;j<=mails.size();j++){
				WebElement e = driver().findElement(By.xpath("//*[@id='contact-lists']/div[2]/div/div[1]/div[3]/input"));
				e.click();
				Thread.sleep(2000);
				break;
			}
		}
		
		/*else if(mails.size()==15){ 
		 	for (int j =1; j<=mails.size()-10; j++){
		 		WebElement mm = driver().findElement(By.cssSelector(emailList1+j+emailList2));		 		
		 		System.out.println(mm.getText());
		 		mm.click();
		 		Thread.sleep(2000);	
		 	}	
		}
		else{
 			for(int j =1; j<=mails.size()-22; j++){
		 		WebElement mm = driver().findElement(By.cssSelector(emailList1+j+emailList2));		 		
		 		System.out.println("original "+mm.getText());
		 		mm.click();
		 		Thread.sleep(2000);
		 	}
	 	}*/
  		
		System.out.println("Retrive count of Selected mail Ids");				  		
  		List <WebElement> selectedMail = driver().findElements(By.cssSelector(conList));
  		int inviteeList = selectedMail.size();
  		System.out.println("selected mail Id count is "+inviteeList);
  		int aftInviatation = inviteeList;
  		String afterInvited = Integer.toString(aftInviatation);	
  		Thread.sleep(2000);
  		clickOnButton("cssSelector", saveInv);
  		Thread.sleep(5000);		  				  		
  					  		
  		waitOnElement("cssSelector","#new-invitees");
  		String newInvitees = driver().findElement(By.cssSelector("#new-invitees")).getText().trim();
  		Assert.assertEquals(afterInvited, newInvitees);	
  		
  		clickOnButton("cssSelector",inviteBtn);
		waitOnElement("cssSelector",titleInvite);
		
		List <WebElement> selectedContacts = driver().findElements(By.cssSelector(conList));
		for(int i=1;i<=selectedContacts.size();i++){
			WebElement e = driver().findElement(By.cssSelector("#invite-contacts > div > div > div > ul > li:nth-child("+i+")"));
			e.click();
		}
		int after_contacts_deletion = driver().findElements(By.cssSelector(conList)).size();
		String afterDeletion = Integer.toString(after_contacts_deletion);
		clickOnButton("cssSelector", saveInv);
  		Thread.sleep(5000);	
  		String newInvitees1 = driver().findElement(By.cssSelector("#new-invitees")).getText().trim();
  		Assert.assertEquals(afterDeletion, newInvitees1);	

	   }
	
	
	public void verifyEventLink(String eventName) throws Exception{
		logInfo("inside verifyEventLink() method.");
		System.out.println("inside verifyEventLink() method.");
		waitOnElement("xpath",btnInviteGuests);
		clickOnElement("xpath",btnInviteGuests);
		waitOnElement("cssSelector",titleInvite );
		clickOnLink("linkText", "All Contacts");	 
		List <WebElement> mails = driver().findElements(By.cssSelector(emailList));
		System.out.println(mails.size());	 
		if (mails.size()==0){		 
			 System.err.println("No contacts are available to invite");
		}
		else if(mails.size()==15){ 
		 	for (int j =1; j<=mails.size()-10; j++){
		 		WebElement mm = driver().findElement(By.cssSelector(emailList1+j+emailList2));		 		
		 		System.out.println(mm.getText());
		 		mm.click();
		 		Thread.sleep(2000);	
		 	}	
		}
		else{
 			for(int j =1; j<=mails.size()-22; j++){
		 		WebElement mm = driver().findElement(By.cssSelector(emailList1+j+emailList2));		 		
		 		System.out.println("original "+mm.getText());
		 		mm.click();
		 		Thread.sleep(2000);
		 	}
	 	}

  		clickOnButton("cssSelector", saveInv);
  		Thread.sleep(5000);		  				  		
  		waitOnElement("xpath", btnSave);			  		
  		clickOnElement("xpath", btnSave);
  		go2CalendarPage();
//  		viewCalendarEvent(eventName); even if we select recipient, it is saying plz select atleast one recipient.
	}
	
	 public void viewCalendarEvent(String eventName) throws Exception {
			logInfo("View the calender event ");		
			boolean isMatchFound=false;
			waitOnElement("xpath",eleMatchEvent);
			List<WebElement> allElements = driver().findElements(By.xpath(eleMatchEvent));
				for(WebElement x: allElements){
					String event = x.getText().trim();
					if(event.equalsIgnoreCase(eventName)){		
						logInfo(eventName + " match found.");
						x.click();
						isMatchFound = true;
						Thread.sleep(2000);					
						waitOnElement("linkText","View");
						clickOnLink("linkText","View");
						Thread.sleep(5000);
						break;						
					}
				}
				if (isMatchFound==false){					
					Assert.assertTrue(isMatchFound, eventName + " is not available in Calender.");
				}	
				
				waitOnElement("linkText","Invite");
				clickOnElement("linkText","Invite");
				Thread.sleep(3000);
				waitOnElement("xpath",recipTo);
				String recipient = getEmail(adminUser_text, appUrl);
				inputText("xpath", recipTo, recipient);
				Thread.sleep(10000);
				waitOnElement("xpath", btnSendInvite);
				clickOnElement("xpath", btnSendInvite);
				Thread.sleep(5000);
				
				inbox.go2Inbox();
				inbox.selectVibeMailsWithSubject(eventName);
				waitOnElement("linkText","Click Here To View The Event");
				clickOnElement("linkText","Click Here To View The Event");
				if(!driver().getCurrentUrl().contains("events")){
					Assert.assertTrue(false, "Unable to go to the event details page.");
				}
				else{
					
				}
	 		}
	
	
	 public void selectCalendarEvent(String eventName) throws Exception {
			logInfo("inside selectCalendarEvent() method.");		
			boolean isMatchFound=false;
			waitOnElement("xpath",eleMatchEvent);
			List<WebElement> allElements = driver().findElements(By.xpath(eleMatchEvent));
				for(WebElement x: allElements){
					String event = x.getText().trim();
					if(event.equalsIgnoreCase(eventName)){		
						logInfo(eventName + " match found.");
						x.click();
						isMatchFound = true;
						break;						
					}
				}
				if (isMatchFound==false){					
					Assert.assertTrue(isMatchFound, eventName + " is not available in Calender.");
				}
	 }
	 
	 public boolean dismissCorporateEvent(String eventName) throws Exception, Exception{
		 logInfo("inside dismissCorporateEvent() method.");
		 boolean isDismissed = false;
		 selectCalendarEvent(eventName);
		 waitOnElement("linkText","Dismiss Event");
		 clickOnLink("linkText","Dismiss Event");
		 //Need to click on "ok" button on confirmation of delete event.
		 Thread.sleep(5000);
		 go2CalendarPage();
		 isDismissed = verifyEventisDisplayed(eventName);
		 return isDismissed;
	 }
	
	public boolean verifyStatesPresent() throws Exception, Exception{
		logInfo("inside verifyStatesPresent() method.");
		System.out.println("inside verifyStatesPresent() method.");
		boolean isDisplayed = false;
		waitOnElement("xpath",btnSearch);
		clickOnElement("xpath",btnSearch);
		waitOnElement("linkText","Advanced Search");
		clickOnElement("linkText", "Advanced Search");
		waitOnElement("xpath","//*[@id='pyr_crm_event_search_form_event_type_id']");
		selectFromDropdown("xpath", "//*[@id='pyr_crm_event_search_form_event_type_id']", "byVisibleText", "Meeting");
		selectFromDropdown("xpath", "//*[@id='pyr_crm_event_search_form_country']", "byVisibleText", "United States");
		try{
			selectFromDropdown("xpath", "//*[@id='pyr_crm_event_search_form_state']", "byVisibleText", "Utah");
			String state = getCurrentSelectionFromDropdown("xpath","//*[@id='pyr_crm_event_search_form_state']");
			if(state.trim().contains("Utah")){
				isDisplayed = true;
			}
		}
		catch(Exception ex){
			isDisplayed = false;
			Assert.assertTrue(isDisplayed, "Unable to select States.");
		}
		return isDisplayed;
	}
	
	public void verifyAdvancedSearchWithOptions(boolean eventType, boolean country, boolean state) throws Exception, Exception{
		logInfo("inside verifyAdvancedSearchWithOptions() method.");	
		waitOnElement("xpath",btnSearch);
		clickOnElement("xpath",btnSearch);
		waitOnElement("linkText","Advanced Search");
		clickOnElement("linkText", "Advanced Search");
		if(eventType && !country && !state){
			waitOnElement("xpath","//*[@id='pyr_crm_event_search_form_event_type_id']");
			selectFromDropdown("xpath", "//*[@id='pyr_crm_event_search_form_event_type_id']", "byVisibleText", "Anniversary");
		}
		if(!eventType && country && !state){
			selectFromDropdown("xpath", "//*[@id='pyr_crm_event_search_form_country']", "byVisibleText", "United States");
		}
		if(!eventType && !country && state){
			selectFromDropdown("xpath", "//*[@id='pyr_crm_event_search_form_state']", "byVisibleText", "Utah");
		}
		if(eventType && country && state){
			waitOnElement("xpath","//*[@id='pyr_crm_event_search_form_event_type_id']");
			selectFromDropdown("xpath", "//*[@id='pyr_crm_event_search_form_event_type_id']", "byVisibleText", "Anniversary");
			selectFromDropdown("xpath", "//*[@id='pyr_crm_event_search_form_country']", "byVisibleText", "United States");
			selectFromDropdown("xpath", "//*[@id='pyr_crm_event_search_form_state']", "byVisibleText", "Utah");
		}
		clickOnElement("xpath", "//*[@id='event_search']");
	}
	
	public void createAnniversaryEvent(String eventName, String startDate, String endDate) throws Exception{
		logInfo("inside createAnniversaryEvent() method...");
		System.out.println("inside createAnniversaryEvent() method...");
		Thread.sleep(5000);
		clickOnElement("xpath",btnNewEvent);
		
		verifyElementPresent("xpath",inputEventName);
		inputText("xpath",inputEventName, eventName);
		
		inputTextClear("xpath",inputEventStartDate);
		inputText("xpath",inputEventStartDate, startDate);
		
		inputTextClear("xpath",inputEventEndDate);
		inputText("xpath",inputEventEndDate,endDate);
		
		verifyElementPresent("linkText","More Options");
		clickOnElement("linkText","More Options");
		
		waitOnElement("xpath","//*[@id='pyr_crm_event_event_type_id']");
		selectFromDropdown("xpath", "//*[@id='pyr_crm_event_event_type_id']", "byVisibleText", "Anniversary");
		
		selectFromDropdown("xpath", "//*[@id='pyr_crm_event_state']", "byVisibleText", "Utah");
		selectFromDropdown("xpath", "//*[@id='pyr_crm_event_country']", "byVisibleText", "United States");
		clickOnElement("xpath", "//*[@id='submit-form']");
		Thread.sleep(5000);
	}
	
	public boolean verifyBackFromEventDetailsPage(String eventName) throws Exception, Exception{
		logInfo("inside verifyBackFromEventDetailsPage() method.");
		System.out.println("inside verifyBackFromEventDetailsPage() method.");
		boolean isVerified = false;
		waitOnElement("linkText","Edit");
		clickOnElement("linkText","Edit");
		waitOnElement("xpath",btnBackEvent);
		clickOnElement("xpath", btnBackEvent);
		Thread.sleep(5000);
		isVerified = verifyEventisDisplayed(anniversaryEvent);
		return isVerified;
	}
	
	
	public boolean verifyListViewEvent(String eventName) throws Exception, Exception{
		logInfo("inside verifyListViewEvent() method.");
		System.out.println("inside verifyListViewEvent() method.");
		boolean isEventPresent = false;
		
		String eventB4r ="//*[contains(@id,'event')][";
		String eventAft = "]/td[1]/a";
				
		List <WebElement> itemss = driver().findElements(By.cssSelector(items));
		System.out.println(itemss.size());
	    for (int i=1 ; i<=itemss.size(); i++)  {
		   WebElement event = driver().findElement(By.xpath(eventB4r+i+eventAft));
		   String events = event.getText().trim();
		   System.out.println(events);
	       if (events.equalsIgnoreCase(eventName)){	
	    	   isEventPresent= true;
			/*   event.click();*/
			  
	       }
	    }

		return isEventPresent;
	}
	
	public boolean checkCorporateDefaultOptions(String eventName, String startDate, String endDate) throws Exception{
		System.out.println("inside checkCorporateDefaultOptions() method");
		logInfo("inside checkCorporateDefaultOptions() method...");
		boolean isVerified = true;
		
		verifyElementPresent("xpath",btnNewEvent);
		clickOnLink("xpath",btnNewEvent);
	
		verifyElementPresent("xpath",inputEventName);
		inputText("xpath",inputEventName,eventName);		
		inputTextClear("xpath",inputEventStartDate);
		inputText("xpath",inputEventStartDate,startDate);
		inputTextClear("xpath",inputEventEndDate);
		inputText("xpath",inputEventEndDate,endDate);
		
		clickOnElement("xpath", btnMoreOptions);
		Thread.sleep(5000);

		clickOnElement("xpath",eRankDefinitions);
		Thread.sleep(2000);
		WebElement rank = driver().findElement(By.xpath(chkRankAll));
		
		clickOnElement("xpath",eMarketLangs);
		Thread.sleep(2000);
		WebElement markets = driver().findElement(By.xpath(chkMarketsAll));
		
		clickOnElement("xpath",eSubscriptionplan);
		Thread.sleep(2000);
		WebElement subscription = driver().findElement(By.xpath(chkSubscriptionAll));
		
		if(!rank.isSelected() || !markets.isSelected() || !subscription.isSelected()) {
			isVerified = false;
		}
		
		return isVerified;
	}
	
	public boolean checkUpcomingEventsCount(String eventName) throws Exception, Exception{
		System.out.println("inside checkCorporateDefaultOptions() method");
		logInfo("inside checkCorporateDefaultOptions() method...");
		boolean isVerified = false;
		
		waitOnElement("xpath",btnCalendarListView);
		clickOnElement("xpath",btnCalendarListView);
		
		waitOnElement("xpath",txtUpcomingEvents);
		WebElement upcomingEvents = driver().findElement(By.xpath(txtUpcomingEvents));
		if(upcomingEvents.getText().trim().contains("1 upcoming event(s) in the next day")){
			isVerified = true;
			upcomingEvents.click();
		}
		
		if(isVerified){
			waitOnElement("xpath","//*[contains(@id,'event')]/td[1]");
			List events = driver().findElements(By.xpath("//*[contains(@id,'event')]/td[1]"));
			String before = "//*[contains(@id,'event')][";
			String after = "]/td[1]/a";
			
			for(int i=1;i<=events.size();i++){
				WebElement e = driver().findElement(By.xpath(before+i+after));
				if(e.getText().trim().contains(eventName)){
					isVerified = true;
				}
			}
		}
		
		return isVerified;
	}
	
	public void deleteEventFromEditPage() throws Exception, Exception{
		System.out.println("inside deleteEventFromEditPage() method");
		logInfo("inside deleteEventFromEditPage() method...");
		waitOnElement("linkText","Delete");
		clickOnElement("linkText","Delete");
		confirmOK();
		confirmationMessage("Your event has been deleted.");
	}
	
	public boolean verifyBackFromEditEvent() throws Exception, Exception{
		System.out.println("inside verifyBackFromEditEvent() method");
		logInfo("inside verifyBackFromEditEvent() method...");
		boolean isValidated = false;
		Thread.sleep(3000);
		waitOnElement("xpath",inputEventTitle);
		inputTextClear("xpath",inputEventTitle);
		inputText("xpath",inputEventTitle,"new event");
		clickOnElement("xpath",btnBackEditEvent);
		waitOnElement("xpath",unsavedChanges);
		WebElement e = driver().findElement(By.xpath(unsavedChanges));
		
		if(e.getText().trim().contains("You have unsaved changes")){
			isValidated = true;
		}
		if(isValidated){
			Thread.sleep(3000);
			waitOnElement("linkText","Proceed");
			clickOnElement("linkText","Proceed");
			Thread.sleep(3000);
			WebElement el = driver().findElement(By.xpath(calendarTitle));
			if(el.getText().trim().contains("Calendar")){
				isValidated = true;
			}
			else{
				isValidated = false;
			}
		}
		return isValidated;	
	}
	
	public boolean verifyNewInviteeSectionEditEvent(String eventName) throws Exception, Exception{
		System.out.println("inside verifyNewInviteeSectionEditEvent() method");
		logInfo("inside verifyNewInviteeSectionEditEvent() method...");
		boolean isVerified = false;
		
		selectCalendarEvent(eventName);
		waitOnElement("linkText","Edit");
		clickOnElement("linkText","Edit");
		
		waitOnElement("xpath","//*[@id='attendees']/div[1]/div[2]/div/div[2]/div/p");
		List<WebElement> el = driver().findElements(By.xpath("//*[@id='attendees']/div[1]/div[2]/div/div[2]/div/p"));
		for(WebElement x: el){
			String inviteesSection = x.getText().trim();
			if(inviteesSection.equalsIgnoreCase("New Invitees")){	
				isVerified = true;
			}
		}
		return isVerified;	
	}
	
	public boolean verifyGuestInvMail(String eventName,String startDate,String endDate) throws Exception{
		System.out.println("inside verifyGuestInvMail() method");
		logInfo("inside verifyGuestInvMail() method...");
		boolean isVerified = false;
		addNewEventInvitation(eventName,startDate,endDate);
		Thread.sleep(5000);
		inbox.go2Inbox();
		isVerified = inbox.selectVibeMailsWithSubject(inviteGuestEventSubject_text);
		return isVerified;	
	}
	
	public boolean sortEventsByEventType(String eventType,String eventName) throws Exception, Exception{
		System.out.println("inside sortEventsByEventType() method");
		logInfo("inside sortEventsByEventType() method...");
		boolean isVerified = false;
		waitOnElement("xpath","//*[@id='pyr_crm_event_event_type_id']");
		selectFromDropdown("xpath", "//*[@id='pyr_crm_event_event_type_id']", "byVisibleText", "Anniversary");
		clickOnElement("xpath", "//*[@id='submit-form']");
		Thread.sleep(5000);
		waitOnElement("xpath",eventTypes);
		clickOnElement("xpath",eventTypes);
		clickOnElement("linkText",eventType);
		Thread.sleep(3000);
		isVerified = verifyListViewEvent(eventName);
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to find the event in the ListView. "+eventName);
		}
		return isVerified;	
	}
	
	public boolean verifyEventByFilterTypesListView(String eventName) throws Exception, Exception{
		System.out.println("inside verifyEventByFilterTypesListView() method");
		logInfo("inside verifyEventByFilterTypesListView() method...");
		boolean isVerified = false;
		String[] options = {"Show 1 Day","Show 1 Week","Show 1 Month"};
		waitOnElement("xpath",btnCalendarListView);
		clickOnElement("xpath",btnCalendarListView);
		waitOnElement("xpath",drpdwnEventFilters);
		clickOnElement("xpath",drpdwnEventFilters);
		
		List<WebElement> el = driver().findElements(By.xpath("//*[@id='filter_by_type_container']/ul/li"));
		String before = "//*[@id='filter_by_type_container']/ul/li[";
		String after = "]/a";
		int j=0;
		
		for(int i=1;i<=el.size();i++){
			WebElement e = driver().findElement(By.xpath(before+i+after));
			if(e.getText().trim().contains(options[j])){
				Thread.sleep(5000);
				isVerified = verifyListViewEvent(eventName);
				j++;
			}
		}
		if(isVerified){
			try{
				WebElement prevList = driver().findElement(By.xpath(btnPrevEventsList));
				WebElement nextList = driver().findElement(By.xpath(btnNextEventsList));
				if(prevList.isDisplayed() && nextList.isDisplayed()){
					isVerified = true;
				}
				if(isVerified){
					nextList.click();
					Thread.sleep(3000);
					isVerified = verifyListViewEvent(eventName);
					if(isVerified){
						Assert.assertFalse(isVerified, "Today's event is displayed on clicking Next button from List View.");
					}
					else{
						isVerified = true;
					}
				}
				if(isVerified){
					WebElement prevList1 = driver().findElement(By.xpath(btnPrevEventsList));
					prevList1.click();
					Thread.sleep(3000);
					isVerified = verifyListViewEvent(eventName);
					if(!isVerified){
						Assert.assertTrue(isVerified, "Today's event is not displayed on clicking Prev button from Next button in the List View.");
					}
				}
			}
			catch(Exception ex){
				logInfo("Event prev & next buttons are not found.");
				isVerified = false;
			}
			
			
		}
		return isVerified;	
	}
	   //Ganga
	   public void add3StaticContacts() throws Exception{
		   	logInfo("iside add3StaticContacts()method" );
		   	String[] contactNames = {contactName_text,contactName_text,contactName_text};
		   	
		   	con.go2ContactsPage();
		   	boolean isContactfound = con.verifyBusinessContact("Test","Contact");
		  	con.selectMultipleContacts(contactNames, "Delete Selected");
		  	
			//con.confirmEventDeleteModal();
		  	 confirmOK();
			con.addBusinessContacts("Test","Contact","icentris.vibe001@gmail.com"); //adding 3 contacts with static emails
			con.addBusinessContacts("Test","Contact","icentris.vibe002@gmail.com");
			con.addBusinessContacts("Test","Contact","icentris.vibe003@gmail.com");
	 		
		   }
	
	      //Ganga
			public void openMailInGmail(String subject){ 
				logInfo("inside openMailInGmail() method ...");
				WebElement gmailBody = driver().findElement(By.xpath(tblGmailBody));
				List allRows = gmailBody.findElements(By.tagName("tr"));
				int count = allRows.size();
					
				String before = "//table/tbody/tr[";
				String after = "]/td[6]/div/div/div/span[1]";
					
				String beforeChk = "//table/tbody/tr[";
				String afterChk = "]/td[2]/div[@role='checkbox']";
					
				boolean isMatchFound = false;
				for(int i=1;i<=count;i++){
					WebElement x = driver().findElement(By.xpath(before+i+after));
					String gmail_summary = x.getText().trim();
					System.out.println("Gmail subjects = " +gmail_summary);
					if(gmail_summary.equalsIgnoreCase(subject.trim())){
						isMatchFound=true;
						x.click();
				logInfo(subject + " email match found @row " +i);
				Assert.assertTrue(isMatchFound, subject + " email match found @row " +i);
					}
				}
					
				if(isMatchFound==false){
			logInfo(subject + " no email match found in Gmail.");
			// Assert.assertTrue(isMatchFound, subject + " no email match found");
		}
	}
				
			


}
	
		    
	