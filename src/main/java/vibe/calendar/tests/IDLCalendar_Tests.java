package vibe.calendar.tests;

import org.openqa.selenium.By.ByCssSelector;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(3)
public class IDLCalendar_Tests extends IDLCalendarMethods {
	
	MyProfileMethods profile = new MyProfileMethods();
	ShoppingMethods shop= new ShoppingMethods();
	InboxMethods inbox = new InboxMethods();
	CommunityMethods comm = new CommunityMethods();
	BusinessContactsMethods con = new BusinessContactsMethods();
	readProp prop = new readProp();
	
	@Test(priority=301)
	public void createNewCalendarEvent() throws Exception{
		System.out.println("inside createNewCalendarEvent() Test");
		logInfo("inside createNewCalendarEvent() Test");
		userLogout();
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
		boolean isMatchFound = searchCalendarEvent(newEventName_text);
		if(isMatchFound==false){
			Assert.assertTrue(isMatchFound, invalidEventName + " calendar event match not found.");
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
		
		/*boolean isMatchFound = searchCalendarEvent(newEventName_updtext);
		if(isMatchFound==true){
			logInfo(newEventName_updtext + " could not be deleted.");
			Assert.assertTrue(isMatchFound, newEventName_updtext + " could not be deleted.");
		}*/
	}

	@Test(priority=305)
	public void createRepeatCalendarEvent() throws Exception{
		System.out.println("inside createRepeatCalendarEvent() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEventName_text, repeatEventStartdate_text, repeatEventEnddate_text);
		go2CalendarPage();
		verifyEventisDisplayed(repeatEventName_text);
	}

		
	@Test(priority=306)
	public void deleteRepeatCalendarEvent() throws Exception{
		try{
			System.out.println("inside deleteRepeatCalendarEvent() Test");
			go2CalendarPage();
			deleteCalendarEvent("Repeat Event",repeatEventName_text);
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}

	}	


	@Test(priority=307)
	public void createCorpCalendarEvent() throws Exception{
		try{
			System.out.println("inside corpCalendarEvent() Test");
			go2CorporateCalendarPage();
			addCorpCalendarEvent(corpEventName_text, corpEventStartdate_text, corpEventEnddate_text);
			go2CorporateCalendarPage();
			verifyEventisDisplayed(corpEventName_text);
			back2Office();
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		}
		catch(Exception ex){
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		}
		
	}
	

	@Test(priority=308)
	public void deleteCorpCalendarEvent() throws Exception{
		System.out.println("inside deleteCorpCalendarEvent() Test");
		go2CalendarPage();
		deleteCalendarEvent("Quick Event",corpEventName_text);
		boolean isMatchFound = searchCalendarEvent(corpEventName_text);
		if(isMatchFound==true){
			logInfo(corpEventName_text + " could not be deleted.");
			Assert.assertTrue(isMatchFound, corpEventName_text + " could not be deleted.");
		}
	}	

	@Test(priority=309)
	public void verifyEventInAllViews() throws Exception{
		System.out.println("inside verifyEventInAllViews() Test");	
		go2CalendarPage();
		addQuickCalendarEvent(quickEventName_text,quickEventStartdate_text,quickEventEnddate_text);
		verifyCalenderEventInAllViews(quickEventName_text);
	}
	
	
	
	@Test (priority=310)
	public void shareEventinFB() throws Exception{		
		try{
			go2CalendarPage();	
			addQuickCalendarEvent(EventMeeting5,quickEventStartdate_text,quickEventEnddate_text);
			selectMoreOptioninListView(EventMeeting5, "Share");
			shop.selectFacebookIcon();		
			shop.shareInFaceBook();			
			profile.close();
			profile.login2FBVerifyPostedDetails();  
			profile.getPostsFromFB(EventMeeting5) ;  	
			logIn(adminUser_text,adminPwd_text);
		}catch(Exception ex){
			logIn(adminUser_text, adminPwd_text);  
		}

	}
	
	@Test (priority=311)
	public void shareEventByEmail() throws Exception{
		
//		String selfmailId  = getEmail(adminUser_text, appUrl);
		
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(ByCssSelector.cssSelector(mailId)).getText();
		System.out.println(selfmailId);
		go2CalendarPage();		
		addQuickCalendarEvent(EventMeeting5,quickEventStartdate_text,quickEventEnddate_text);
		selectMoreOptioninListView(EventMeeting5, "Share");
		shop.selectEmailIcon();			   
		comm.shareByEmail(selfmailId,eventsubText );		
		profile.close();
		inbox.go2Inbox();
		inbox.selectVibeMailsWithSubject(eventsubText);
		inbox.deleteFilteredVibeMails();
		
	}
	
	@Test(priority=312)
	public void verifyNewInvitees() throws Exception{
		logInfo("inside verifyNewInvitees() Test.");	
		System.out.println("inside verifyNewInvitees() Test.");
		go2CalendarPage();
		createEventWithMoreOptions(EventMeeting3, newEventStartdate_text, newEventEnddate_text);	
		verifyNewInvitees("New Invitees", EventMeeting3);
	}
	
	
	@Test(priority=313)
	public void advancedSearchByOptions() throws Exception{
		logInfo("inside advancedSearchByOptions() Test.");
		System.out.println("inside advancedSearchByOptions() Test.");
		boolean isDisplayed = false;
		go2CalendarPage();
		createAnniversaryEvent(anniversaryEvent, newEventStartdate_text, newEventEnddate_text);
		go2CalendarPage();
		verifyAdvancedSearchWithOptions(true,false,false);
		Thread.sleep(3000);
		isDisplayed = verifyEventisDisplayed(anniversaryEvent);
		if(!isDisplayed){
			Assert.assertTrue(isDisplayed, "Unable to do advanced search based on event type : Anniversary");
		}
		
		verifyAdvancedSearchWithOptions(false,true,false);
		Thread.sleep(3000);
		isDisplayed = verifyEventisDisplayed(anniversaryEvent);
		if(!isDisplayed){
			Assert.assertTrue(isDisplayed, "Unable to do advanced search based on country : United States");
		}
		
		verifyAdvancedSearchWithOptions(false,false,true);
//		Thread.sleep(3000);
		isDisplayed = verifyEventisDisplayed(anniversaryEvent);
		if(!isDisplayed){
			Assert.assertTrue(isDisplayed, "Unable to do advanced search based on state : Utah");
		}
		
		verifyAdvancedSearchWithOptions(true,true,true);
//		Thread.sleep(3000);
		isDisplayed = verifyEventisDisplayed(anniversaryEvent);
		if(!isDisplayed){
			Assert.assertTrue(isDisplayed, "Unable to do advanced search based on event type, country and state all together.");
		}
	}
	
	@Test(priority=314)
	public void verifyUpcomingEvents() throws Exception{
		try{
			logInfo("inside verifyUpcomingEvents() Test.");
			System.out.println("inside verifyUpcomingEvents() Test.");
			go2CalendarPage();
			addQuickCalendarEvent(upcomingEvent,getDate(1, "MM/dd/yyyy"),getDate(2, "MM/dd/yyyy"));
			go2CalendarPage();
			boolean isVerified = checkUpcomingEventsCount(upcomingEvent);
			if(!isVerified){
				Assert.assertTrue(isVerified,"Unable to verify the upcoming events count.");
			}
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}
		
	}

}

