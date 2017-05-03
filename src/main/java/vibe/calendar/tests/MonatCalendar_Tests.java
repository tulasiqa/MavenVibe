package vibe.calendar.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(3)
public class MonatCalendar_Tests extends MonatCalendarMethods {
	
	MyProfileMethods profile = new MyProfileMethods();
	ShoppingMethods shop= new ShoppingMethods();
	InboxMethods inbox = new InboxMethods();
	CommunityMethods comm = new CommunityMethods();
	BusinessContactsMethods con = new BusinessContactsMethods();
	NewsMethods nm = new NewsMethods();
	readProp prop = new readProp();
	
	
	@Test(priority=301)
	public void createNewCalendarEvent() throws Exception{
		System.out.println("inside createNewCalendarEvent() Test");
		logInfo("inside createNewCalendarEvent() Test");
		//userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		go2CalendarPage();
		addNewCalendarEvent(newEventName_text, newEventStartdate_text, newEventEnddate_text);
		go2CalendarPage();
		verifyEventisDisplayed(newEventName_text);
	}
	
	
	@Test(priority=302)
	public void searchCalendarEvent() throws Exception{
		logInfo("inside searchCalendarEvent() Test");		
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(invalidEventName);
		if(isMatchFound==true){
			Assert.assertFalse(isMatchFound, invalidEventName + " calendar event match found.");
		} 
	}
	
	
	@Test(priority=303)
	public void updateCalendarEvent() throws Exception{
		logInfo("inside updateCalendarEvent() Test");
		go2CalendarPage();
		boolean isFound = searchCalendarEvent(newEventName_text);
		if(isFound==true){
			updateCalendarEvent("New Event", newEventName_text);
			go2CalendarPage();
			verifyEventisDisplayed(newEventName_updtext);
		}
	}
	

	@Test(priority=304)
	public void deleteCalendarEvent() throws Exception{
		System.out.println("inside deleteCalendarEvent() Test");
		go2CalendarPage();
		deleteCalendarEvent("New Event",newEventName_updtext);
		//Thread.sleep(10000);
		confirmationMessage("Your event has been deleted.");
		boolean isMatchFound = searchCalendarEvent(newEventName_updtext);
		if(isMatchFound==true){
			logInfo(newEventName_updtext + " could not be deleted.");
			Assert.assertTrue(isMatchFound, newEventName_updtext + " could not be deleted.");
		}
	}

	@Test(priority=305)
	public void createCorpCalendarEvent() throws Exception{
		try{
			System.out.println("inside corpCalendarEvent() Test");
			userLogout();
			go2CorporateCalendarPage();
			addCorpCalendarEvent(corpEventName_text, corpEventStartdate_text, corpEventEnddate_text);
			go2CorporateCalendarPage();
			verifyEventisDisplayed(corpEventName_text);
			//userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			go2CalendarPage();
			verifyEventisDisplayed(corpEventName_text);
		}
		catch(Exception ex){
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		}
		

	}
	
	@Test(priority=306)
	public void deleteCorpCalendarEvent() throws Exception{
		System.out.println("inside deleteCorpCalendarEvent() Test");
		go2CalendarPage();
		deleteCalendarEvent("Quick Event",corpEventName_updtext);
//		Thread.sleep(10000);
		boolean isMatchFound = searchCalendarEvent(corpEventName_updtext);
		if(isMatchFound==true){
			logInfo(corpEventName_updtext + " could not be deleted.");
			Assert.assertTrue(isMatchFound, corpEventName_updtext + " could not be deleted.");
		}
	}
	
	
	@Test(priority=307)
	public void verifyEventInAllViews() throws Exception{		
		System.out.println("inside verifyEventInAllViews() Test");	
		go2CalendarPage();
		addQuickCalendarEvent(quickEventName_text,quickEventStartdate_text,quickEventEnddate_text);
		go2CalendarPage();
		verifyCalenderEventInAllViews(quickEventName_text);
	}
	
	@Test(priority=308)
	public void verifyNewInvitees() throws Exception{
		logInfo("inside verifyNewInvitees() Test.");	
		System.out.println("inside verifyNewInvitees() Test.");
		go2CalendarPage();
		createEventWithMoreOptions(EventMeeting3, newEventStartdate_text, newEventEnddate_text);	
		verifyNewInvitees("New Invitees", EventMeeting3);
	}
	
	 
	@Test(priority=309)
	public void advancedSearchByOptions() throws Exception{
		logInfo("inside advancedSearchByOptions() Test.");
		System.out.println("inside advancedSearchByOptions() Test.");
		boolean isDisplayed = false;
		go2CalendarPage();
		createAnniversaryEvent(anniversaryEvent, newEventStartdate_text, newEventEnddate_text);
		go2CalendarPage();
		verifyAdvancedSearchWithOptions(true,false,false);
		isDisplayed = verifyEventisDisplayed(anniversaryEvent);
		if(!isDisplayed){
			Assert.assertTrue(isDisplayed, "Unable to do advanced search based on event type : Anniversary");
		}
		
		verifyAdvancedSearchWithOptions(false,true,false);
		isDisplayed = verifyEventisDisplayed(anniversaryEvent);
		if(!isDisplayed){
			Assert.assertTrue(isDisplayed, "Unable to do advanced search based on country : United States");
		}
		
		verifyAdvancedSearchWithOptions(false,false,true);
		isDisplayed = verifyEventisDisplayed(anniversaryEvent);
		if(!isDisplayed){
			Assert.assertTrue(isDisplayed, "Unable to do advanced search based on state : Utah");
		}
		
		verifyAdvancedSearchWithOptions(true,true,true);
		isDisplayed = verifyEventisDisplayed(anniversaryEvent);
		if(!isDisplayed){
			Assert.assertTrue(isDisplayed, "Unable to do advanced search based on event type, country and state all together.");
		}
	}
	
	 	
	@Test(priority=348)  
	public void logOutAsUser() throws Exception{		
		userLogout();			
	}
	
	@Test(priority=349)  
	public void loginAsAdminFromCalender() throws Exception{		
		adminLogin();		
	}
		
	
   }
