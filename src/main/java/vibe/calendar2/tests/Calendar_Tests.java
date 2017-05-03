package vibe.calendar2.tests;

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
public class Calendar_Tests extends CalendarMethods {
	
	MyProfileMethods profile = new MyProfileMethods();
	ShoppingMethods shop= new ShoppingMethods();
	InboxMethods inbox = new InboxMethods();
	CommunityMethods comm = new CommunityMethods();
	BusinessContactsMethods con = new BusinessContactsMethods();
	readProp prop = new readProp();
	
	@Test(priority=301)
	public void createNewCalendarEvent() throws Exception{
		logInfo("inside createNewCalendarEvent() Test");
		logInfo("inside createNewCalendarEvent() Test");
		go2CalendarPage();
		addNewCalendarEvent(newEventName_text, newEventStartdate_text, newEventEnddate_text, calendarInvitee_text);
		go2CalendarPage();
		verifyEventisDisplayed(newEventName_text);
	}
	
	
	@Test(priority=302)
	public void searchCalendarEvent() throws Exception{
		logInfo("inside searchCalendarEvent() Test");	
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(newEventName_text);
		if(isMatchFound==false){
			Assert.assertTrue(isMatchFound, newEventName_text + " calendar event match not found.");
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
		logInfo("inside deleteCalendarEvent() Test");
		go2CalendarPage();
		deleteCalendarEvent(newEventName_updtext);
		boolean isMatchFound = searchCalendarEvent(newEventName_updtext);
		if(isMatchFound==true){
			logInfo(newEventName_updtext + " calendar event could not be deleted.");
			Assert.assertFalse(isMatchFound, newEventName_updtext + " calendar event could not be deleted.");
		} else {
			logInfo(newEventName_updtext + " calendar event deleted.");	
		}
		
	}
	
	
	@Test(priority=305)
	public void createAllDayCalendarEvent() throws Exception{
		logInfo("inside AlldayEvent() Test");
		go2CalendarPage();
		addAlldayCalendarEvent(allDayEventName_text, allDayEventStartdate_text, allDayEventEnddate_text, calendarInvitee_text);
		go2CalendarPage();
		verifyEventisDisplayed(allDayEventName_text);
	}
	

	@Test(priority=306)
	public void searchAllDayCalendarEvent() throws Exception{
		logInfo("inside searchAllDayCalendarEvent() Test");
		go2CalendarPage();
		searchCalendarEvent(allDayEventName_text);
		boolean isMatchFound = searchCalendarEvent(allDayEventName_text);
		if(isMatchFound==false){
			logInfo(allDayEventName_text + " Allday calendar event match not found.");
			Assert.assertTrue(isMatchFound, allDayEventName_text + " Allday calendar event match not found.");
		} else {
			logInfo(allDayEventName_text + " Allday calendar event match found.");
			
		}
	}
	
	


	@Test(priority=307)
	public void updateAllDayCalendarEvent() throws Exception{
		logInfo("inside updateAllDayCalendarEvent() Test");
		go2CalendarPage();	
		boolean isFound = searchCalendarEvent(allDayEventName_text);
		if(isFound==true){
			updateCalendarEvent("All Day", allDayEventName_text);
			go2CalendarPage();
			verifyEventisDisplayed(allDayEventName_updtext);
		}
	}
	

	@Test(priority=308)
	public void deleteAllDayCalendarEvent() throws Exception{
		logInfo("inside deleteAllDayCalendarEvent() Test");
		go2CalendarPage();
		deleteCalendarEvent(allDayEventName_updtext);
		boolean isMatchFound = searchCalendarEvent(allDayEventName_updtext);
		if(isMatchFound==true){
			logInfo(allDayEventName_updtext + " could not be deleted.");
			Assert.assertFalse(isMatchFound, allDayEventName_updtext + " could not be deleted.");
		}
	}	


	@Test(priority=309)
	public void createRepeatCalendarEvent() throws Exception{
		logInfo("inside createRepeatCalendarEvent() Test");
		go2CalendarPage();
		addRepeatCalendarEvent(repeatEventName_text, repeatEventStartdate_text, repeatEventEnddate_text, calendarInvitee_text);
		go2CalendarPage();
		verifyEventisDisplayed(repeatEventName_text);
	}

	@Test(priority=310)
	public void searchRepeatCalendarEvent() throws Exception{
		logInfo("inside searchRepeatCalendarEvent() Test");
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(repeatEventName_text);
		if(isMatchFound==false){
			logInfo(repeatEventName_text + " Repeat calendar event match not found.");
			Assert.assertTrue(isMatchFound, repeatEventName_text + " Repeat calendar event match not found.");
		} else {
			logInfo(repeatEventName_text + " Repeat calendar event match found.");
			
		}
	}
	
 
	@Test(priority=311)
	public void updateRepeatCalendarEvent() throws Exception{
		logInfo("inside updateRepeatCalendarEvent() Test");
		go2CalendarPage();
		boolean isFound = searchCalendarEvent(repeatEventName_text);
		if(isFound==true){
			updateCalendarEvent("Repeat Event", repeatEventName_text);
			go2CalendarPage();
			verifyEventisDisplayed(repeatEventName_updtext);
		}
	}
		
	@Test(priority=312)
	public void deleteRepeatCalendarEvent() throws Exception{
		try{
			logInfo("inside deleteRepeatCalendarEvent() Test");
			go2CalendarPage();
			deleteCalendarEvent(repeatEventName_updtext); 
			boolean isMatchFound = searchCalendarEvent(repeatEventName_updtext);
			if(isMatchFound==true){
				logInfo(repeatEventName_updtext + " could not be deleted.");
				Assert.assertFalse(isMatchFound, repeatEventName_updtext + " could not be deleted.");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
		
	@Test(priority=313)
	public void createQuickCalendarEvent() throws Exception{
		logInfo("inside quickCalendarEvent() Test");
		go2CalendarPage();
		addQuickCalendarEvent(quickEventName_text,quickEventStartdate_text,quickEventEnddate_text);
		go2CalendarPage();
		verifyEventisDisplayed(quickEventName_text);
		
	}
	
	@Test(priority=314)
	public void searchQuickCalendarEvent() throws Exception{
		logInfo("inside searchQuickCalendarEvent() Test");
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(quickEventName_text);
		if(isMatchFound==false){
			logInfo(quickEventName_text + " Quick calendar event match not found.");
			Assert.assertTrue(isMatchFound, quickEventName_text + " Quick calendar event match not found.");
		} else {
			logInfo(quickEventName_text + " Quick calendar event match found.");
			
		}
	}

	
	@Test(priority=315)
	public void updateQuickCalendarEvent() throws Exception{
		logInfo("inside updateQuickCalendarEvent() Test");
		go2CalendarPage();
		boolean isFound = searchCalendarEvent(quickEventName_text);
		if(isFound==true){
			updateCalendarEvent("Quick Event", quickEventName_text);
			go2CalendarPage();
			verifyEventisDisplayed(quickEventName_updtext);
		}
	}

	@Test(priority=316)
	public void deleteQuickCalendarEvent() throws Exception{
		logInfo("inside deleteQuickCalendarEvent() Test");
		go2CalendarPage();
		deleteCalendarEvent(quickEventName_updtext);
		boolean isMatchFound = searchCalendarEvent(quickEventName_updtext);
		if(isMatchFound==true){
			logInfo(quickEventName_updtext + " could not be deleted.");
			Assert.assertFalse(isMatchFound, quickEventName_updtext + " could not be deleted.");
		}
	}	


	@Test(priority=317)
	public void createCorpCalendarEvent() throws Exception{
			logInfo("inside corpCalendarEvent() Test");
			go2CorporateCalendarPage();
			addCorpCalendarEvent(corpEventName_text, corpEventStartdate_text, corpEventEnddate_text,calendarInvitee_text);
			go2CorporateCalendarPage();
			verifyEventisDisplayed(corpEventName_text);
			back2Office();
	}
	
	@Test(priority=318)
	public void searchCorpCalendarEvent() throws Exception{
		logInfo("inside searchCorpCalendarEvent() Test");
		go2CorporateCalendarPage();
		verifyEventisDisplayed(corpEventName_text);
		back2Office();
	}


	@Test(priority=319)
	public void updateCorpCalendarEvent() throws Exception{
		logInfo("inside updateCorpCalendarEvent() Test");
		go2CorporateCalendarPage();
		boolean isFound = verifyEventisDisplayed(corpEventName_text);
		if(isFound==true){
		   updateCalendarEvent("Corporate Calendar Event", corpEventName_text);
		   go2CorporateCalendarPage();
		   verifyEventisDisplayed(corpEventName_updtext);
		   back2Office();
		}
	}

	
	@Test(priority=320)
	public void deleteCorpCalendarEvent() throws Exception{
		try{
			logInfo("inside deleteCorpCalendarEvent() Test");
			go2CalendarPage();
			deleteCalendarEvent(corpEventName_updtext); //corpEventName_updtext
			boolean isMatchFound = searchCalendarEvent(corpEventName_updtext);
			if(isMatchFound==true){
				logInfo(corpEventName_updtext + " could not be deleted.");
				Assert.assertFalse(isMatchFound, corpEventName_updtext + " could not be deleted.");
			}
			
		}
		catch(Exception ex){
			logInfo("Unable to delete corporate event.");
		}
		
	}	

	
	@Test(priority=321)
	public void createNewEventwithPastDate() throws Exception{
		logInfo("inside createNewEventwithPastDate() Test");
		String newEventStartPastdate_text = getDate( 2, "MM/dd/yyyy");
		String newEventEnddate_text = getDate(0, "MM/dd/yyyy");
		go2CalendarPage();
		addQuickCalendarEvent(pastEventName_text, newEventStartPastdate_text, newEventEnddate_text);
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(pastEventName_text);
		logInfo("isMatchFound =" +isMatchFound);
		if(isMatchFound==false){
			logInfo(pastEventName_text + " calendar event not created for the past dates.");
			Assert.assertTrue(isMatchFound, pastEventName_text + " calendar event not created for the past dates.");
		}
	}


	@Test(priority=322)
	public void createNewEventwithFutureDate() throws Exception{
		logInfo("inside createNewEventwithFutureDate() Test");
		String newEventFutureStartdate_text = getDate(0, "MM/dd/yyyy");
		String newEventFutureEnddate_text = getDate(1, "MM/dd/yyyy");
		go2CalendarPage();
		addQuickCalendarEvent(futEventName_text, newEventFutureStartdate_text, newEventFutureEnddate_text);
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(futEventName_text);
		logInfo("isMatchFound =" +futEventName_text);
		if(isMatchFound==false){
			logInfo(futEventName_text + " calendar event not created for the past date events..");
			Assert.assertTrue(isMatchFound, futEventName_text + " calendar event not created for the past date events.");
		}
	}
	
	@Test(priority=323)
	public void verifyEventInAllViews() throws Exception{
		try{
			logInfo("inside verifyEventInAllViews() Test");	
			//userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			go2CalendarPage();
			addQuickCalendarEvent(quickEventName_text,quickEventStartdate_text,quickEventEnddate_text);
			verifyCalenderEventInAllViews(quickEventName_text);
			//adminLogin();
			userLogout();
		}
		catch(Exception ex){
		//	adminLogin();
			userLogout();
		}
		
	}
	
	//TC375
	@Test(priority=324)
	public void verifyPreviousCalendarEvents() throws Exception{
		String newEventStartPastdate_text = getDateByMonth( 1, "MM/dd/yyyy");
		String newEventEnddate_text = getDate(0, "MM/dd/yyyy");
		addQuickCalendarEvent(pastEvent, newEventStartPastdate_text, newEventEnddate_text);		
		Assert.assertTrue(verifyPreviousEventDisplayed(pastEvent));
	}
	
	//TC376
	@Test(priority=325)
	public void verifyFutureCalendarEvents() throws Exception{
		String newEventStartPastdate_text = getDateByMonth(1, "MM/dd/yyyy");
		String newEventEnddate_text = getDateByMonth(2, "MM/dd/yyyy");
		addQuickCalendarEvent(futureEvent, newEventStartPastdate_text, newEventEnddate_text);
		Assert.assertTrue(verifyFutureEventDisplayed(futureEvent));
	}
	
	@Test(priority=326)
	public void inviteGroups() throws Exception{
		logInfo("TC411   Access contacts from a group.");	
		go2CalendarPage();
		addNewCalendarEvent(EventMeeting, newEventStartdate_text, newEventEnddate_text,"");	
		logInfo(EventMeeting);
		go2CalendarPage();
		boolean isFound = searchCalendarEvent(EventMeeting);
		if(isFound==true){
			editCalendarEvent (EventMeeting);
			verifyInvitedGuests("Invited", EventMeeting);			
		}		
	}
	
	
	@Test (priority= 327)
	public void verifyContactList() throws Exception{	
		go2CalendarPage();
		addQuickCalendarEvent(EventMeeting2,quickEventStartdate_text,quickEventEnddate_text);
		logInfo(EventMeeting2);
		go2CalendarPage();
		boolean isFound = searchCalendarEvent(EventMeeting2);
		if(isFound==true){
			editCalendarEvent (EventMeeting2);
			invitation(EventMeeting2);
		}
	}
	
	@Test (priority=328)
	public void verifyComposeBody() throws Exception{
		logInfo("TC414   Compose the content of the email invitation.");	 
		logInfo(EventMeeting2);
		go2CalendarPage();
		boolean isFound = searchCalendarEvent(EventMeeting2);
		if(isFound==true){
			editCalendarEvent (EventMeeting2);
			composeBody(EventMeeting2, claBodyText);
		}
	}
	
	@Test (priority=329)
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
	
	@Test (priority=330)
	public void shareEventinLinkedIn () throws Exception{
		go2CalendarPage();		
		selectMoreOptioninListView(EventMeeting, "Share");
		shop.selectLinkedInIcon();
		shop.shareInLinkedIn();
		profile.close();
	    profile.login2LinkedIn();
	    profile.verifyPostInLinkedAccount(EventMeeting);
	    //     lc.login();
	    logIn(adminUser_text,adminPwd_text);  
		
	}
	
	
	@Test (priority=331)
	public void shareEventByEmail() throws Exception{
		
//		String selfmailId  = getEmail(adminUser_text, appUrl);
		
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(ByCssSelector.cssSelector(mailId)).getText();
		logInfo(selfmailId);
		
		go2CalendarPage();		
		selectMoreOptioninListView(EventMeeting, "Share");
		shop.selectEmailIcon();			   
		comm.shareByEmail(selfmailId,eventsubText );		
		profile.close();
		inbox.go2Inbox();
		inbox.selectVibeMailsWithSubject(eventsubText);
		inbox.deleteFilteredVibeMails();
		
	}
	
    //TC404 & TC405
	@Test(priority=332)
	public void verifyRepeatSettings() throws Exception{
		go2CalendarPage();
		Assert.assertTrue(verifyRepeatCalendarSettings(repeatEventName_text2, repeatEventStartdate_text, repeatEventEnddate_text));
	}
	
	//TC419
	@Test(priority=333)
	public void verifyCalendarListOptions() throws Exception{
		go2CalendarPage();
		Assert.assertTrue(verifyEventsListOptions(repeatEventName_text2));
	}
		
	
	
	//@Test(priority=340)
	public void deleteListCalendarEvent() throws Exception{	
		go2CalendarPage();
		addQuickCalendarEvent(EventMeeting2,quickEventStartdate_text,quickEventEnddate_text);
		go2CalendarPage();
		calenderToolBar("List");
		deleteListEvent(EventMeeting2);
		go2CalendarPage();		
		deleteCalendarEvent(EventMeeting2);
//		Thread.sleep(10000);
		boolean isMatchFound = searchCalendarEvent(EventMeeting2);
		if(isMatchFound==true){
			logInfo(quickEventName_updtext + " could not be deleted.");
			Assert.assertTrue(isMatchFound, EventMeeting2 + " could not be deleted.");
		}
	}

	//@Test(priority=341)
	public void verifyDeletedListEvent() throws Exception{	
		driver().navigate().refresh();
		go2CalendarPage();		
		calenderToolBar("List");
		verifyListEventNotToPresent(EventMeeting2);
	}	

	
	@Test(priority=342)
	public void verifyNewInvitees() throws Exception{
		try{
			logInfo("inside verifyNewInvitees() Test.");	
			logInfo("inside verifyNewInvitees() Test.");
			go2CalendarPage();
			createEventWithMoreOptions(EventMeeting3, newEventStartdate_text, newEventEnddate_text);	
			verifyNewInvitees("New Invitees", EventMeeting3);
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}
		
	}
	
	@Test(priority=343)
	public void verifyNewInviteesSectionEdit() throws Exception{
		logInfo("inside verifyNewInviteesSectionEdit() Test.");	
		logInfo("inside verifyNewInviteesSectionEdit() Test.");
		go2CalendarPage();
		boolean isVerified = verifyNewInviteeSectionEditEvent(EventMeeting3);
		if(isVerified){
			Assert.assertFalse(isVerified, "Shouldn't see the New Invitees sections during Event edit.");
		}
		
	}
	
	@Test(priority=344)
	public void verifyEventLinkFromEmail() throws Exception{
		logInfo("inside verifyEventLinkFromEmail() Test.");	
		logInfo("inside verifyEventLinkFromEmail() Test.");
		go2CalendarPage();
		createEventWithMoreOptions(EventMeeting4, newEventStartdate_text, newEventEnddate_text);	
		verifyEventLink(EventMeeting4);
	}
	
	@Test(priority=345)
	public void verifyDismissedCorporateEvent() throws Exception, Exception{
		logInfo("inside verifyDismissedCorporateEvent() Test");
		logInfo("inside verifyDismissedCorporateEvent() Test");
		go2CorporateCalendarPage();
		addCorpCalendarEvent(corpEventName_text, corpEventStartdate_text, corpEventEnddate_text,calendarInvitee_text);
		back2Office();
		logOut();
		logIn(masDistlogin, masDistPswd);
		go2CalendarPage();
		boolean isDismissed = dismissCorporateEvent(corpEventName_text);
		if(isDismissed){
			Assert.assertFalse(isDismissed, "Unable to dismiss corporate event.");
		}
	}
	
	@Test(priority=346)
	public void verifyStatesOnAdvancedSearch() throws Exception{
		logInfo("inside verifyStatesOnAdvancedSearch() Test.");
		logInfo("inside verifyStatesOnAdvancedSearch() Test.");
		go2CalendarPage();
		boolean isDisplayed = verifyStatesPresent();
		if(!isDisplayed){
			Assert.assertTrue(isDisplayed, "Unable to select States.");
		}
	}
	
	@Test(priority=347)
	public void advancedSearchByOptions() throws Exception{
		logInfo("inside advancedSearchByOptions() Test.");
		logInfo("inside advancedSearchByOptions() Test.");
		boolean isDisplayed = false;
		go2CalendarPage();
		createAnniversaryEvent(anniversaryEvent, newEventStartdate_text, newEventEnddate_text);
		go2CalendarPage();
		verifyAdvancedSearchWithOptions(true,false,false);
//		Thread.sleep(3000);
		isDisplayed = verifyEventisDisplayed(anniversaryEvent);
		if(!isDisplayed){
			Assert.assertTrue(isDisplayed, "Unable to do advanced search based on event type : Anniversary");
		}
		
		verifyAdvancedSearchWithOptions(false,true,false);
//		Thread.sleep(3000);
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
	
	@Test(priority=348)
	public void verifyBackFromEventDetails() throws Exception{
		logInfo("inside verifyBackFromEventDetails() Test.");
		logInfo("inside verifyBackFromEventDetails() Test.");
		go2CalendarPage();
		selectCalendarEvent(anniversaryEvent);
		boolean isVerified = verifyBackFromEventDetailsPage(anniversaryEvent);
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to go to the events page when clicking on back button from event details page.");
		}

	}
	
	@Test(priority=349)
	public void verifyListViewEvent() throws Exception{
		logInfo("inside verifyListViewEvent() Test.");
		logInfo("inside verifyListViewEvent() Test.");
		go2CalendarPage();
		waitOnElement("xpath",btnCalendarListView);
		clickOnElement("xpath",btnCalendarListView);
		boolean isVerified = verifyListViewEvent(anniversaryEvent);
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to find the event in the ListView. "+anniversaryEvent);
		}
	}
	
	@Test(priority=350)
	public void verifyCorporateDefaultOptions() throws Exception, Exception{
		logInfo("inside verifyCorporateDefaultOptions() Test.");
		logInfo("inside verifyCorporateDefaultOptions() Test.");
		go2CorporateCalendarPage();
		boolean isVerified = checkCorporateDefaultOptions(corpEventName_text, corpEventStartdate_text, corpEventEnddate_text);
		if(!isVerified){
			Assert.assertTrue(isVerified, "Unable to check the Ranks,Subscriptions & Markets by default, when creating a Corp Calendar Event.");
		}
	}
	
	@Test(priority=351)
	public void verifyUpcomingEvents() throws Exception{
		logInfo("inside verifyUpcomingEvents() Test.");
		logInfo("inside verifyUpcomingEvents() Test.");
		go2CalendarPage();
		addQuickCalendarEvent(upcomingEvent,getDate(1, "MM/dd/yyyy"),getDate(2, "MM/dd/yyyy"));
		go2CalendarPage();
		boolean isVerified = checkUpcomingEventsCount(upcomingEvent);
		if(!isVerified){
			Assert.assertTrue(isVerified,"Unable to verify the upcoming events count.");
		}
	}
	
	@Test(priority=352)
	public void validateCalendarEvent() throws Exception{
		logInfo("inside validateCalendarEvent() Test.");
		logInfo("inside validateCalendarEvent() Test.");
		go2CalendarPage();
		boolean isValidated = validateQuickCalendarEvent(quickEventName_text);
		if(!isValidated){
			Assert.assertTrue(isValidated, "Unable to validate the blank event name during quick event creation.");
		}
	}
	
	@Test(priority=353)
	public void deleteEventFromMoreOptions() throws Exception{
		logInfo("inside deleteEventFromMoreOptions() Test.");
		logInfo("inside deleteEventFromMoreOptions() Test.");
		go2CalendarPage();
		createEventWithMoreOptions(EventMeeting4, newEventStartdate_text, newEventEnddate_text);	
//		deleteEventFromEditPage();

	}
	
	@Test(priority=354)
	public void verifyBackFromMoreOptions() throws Exception{
		logInfo("inside verifyBackFromMoreOptions() Test.");
		logInfo("inside verifyBackFromMoreOptions() Test.");
		go2CalendarPage();
		createEventWithMoreOptions(EventMeeting3, newEventStartdate_text, newEventEnddate_text);	
		boolean isValidated = verifyBackFromEditEvent();
		if(!isValidated){
			Assert.assertTrue(isValidated, "Unable to validate the back button from edit event page.");
		}
	}
	
	@Test(priority=355)
	public void verifyGuestInvitationMail() throws Exception{
		logInfo("inside verifyGuestInvitationMail() Test.");
		logInfo("inside verifyGuestInvitationMail() Test.");
		String contactName_text = contactFirstName_text + " " + contactLastName_text;
		String contactEmail_text = getEmail(adminUser_text, appUrl);
		String[] contactNames = {contactName_text};
		con.go2ContactsPage();
		boolean isContactFound = con.verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactFound==true){
			logInfo("match found..deleting the contact.");
			con.selectMultipleContacts(contactNames,"Delete Selected");
			confirmAlert();
		}
					
		con.addBusinessContacts(contactFirstName_text,contactLastName_text,contactEmail_text);
		boolean isContactAdded= con.verifyBusinessContact(contactFirstName_text,contactLastName_text);
		if(isContactAdded==false){
			logInfo("business contact could not be added.");
			Assert.assertTrue(isContactAdded, "business contact could not be added.");
		}
		else{
			go2CalendarPage();
			boolean isVerified = verifyGuestInvMail(EventMeeting,newEventStartdate_text, newEventEnddate_text);
			if(!isVerified){
				Assert.assertTrue(isVerified, "Unable to verify the guest invitation mail from Guest Inbox.");
			}
		}
		
	}
	
	@Test(priority=356)
	public void validateSearchCalendarEvent() throws Exception{
		logInfo("inside validateSearchCalendarEvent() Test");		
		logInfo("inside validateSearchCalendarEvent() Test.");
		go2CalendarPage();
		boolean isMatchFound = searchCalendarEvent(invalidEventName);
		if(isMatchFound==true){
			logInfo(invalidEventName + " calendar event match not found.");
			Assert.assertTrue(isMatchFound, invalidEventName + " calendar event match found with invalid data.");
		} 
	}
	
	@Test(priority=357)
	public void sortEventsByType() throws Exception{
		logInfo("inside sortEventsByType() Test");		
		logInfo("inside sortEventsByType() Test.");
		go2CalendarPage();
		createEventWithMoreOptions(EventMeeting, newEventStartdate_text, newEventEnddate_text);	
				
		boolean isMatchFound = sortEventsByEventType("Show Anniversary", EventMeeting);
		if(isMatchFound==false){
			logInfo(EventMeeting + " calendar event match not found.");
			Assert.assertTrue(isMatchFound, EventMeeting + " calendar event match not found according tot he event type : Show Anniversary");
		} 
	}
	
	@Test(priority=358)
	public void SearchCalendarEventListView() throws Exception{
		logInfo("inside SearchCalendarEventListView() Test");		
		logInfo("inside SearchCalendarEventListView() Test.");
		go2CalendarPage();
		searchCalendarEventListView(EventMeeting);
		boolean isMatchFound = verifyListViewEvent(EventMeeting);
		if(isMatchFound==false){
			logInfo(newEventName_text + " calendar event match not found.");
			Assert.assertTrue(isMatchFound, newEventName_text + " calendar event match not found.");
		} 
	}
	
	@Test(priority=359)
	public void validateSearchCalendarEventListView() throws Exception{
		logInfo("inside validateSearchCalendarEventListView() Test");		
		logInfo("inside validateSearchCalendarEventListView() Test.");
		go2CalendarPage();
		searchCalendarEventListView(invalidEventName);
		boolean isMatchFound = verifyListViewEvent(invalidEventName);
		if(isMatchFound==true){
			logInfo(invalidEventName + " invalid calendar event match found.");
			Assert.assertFalse(isMatchFound, invalidEventName + " invalid calendar event match found.");
		} 
	}
	
	@Test(priority=360)
	public void verifyEventByFilterTypes() throws Exception{
		logInfo("inside verifyEventByFilterTypes() Test");		
		logInfo("inside verifyEventByFilterTypes() Test.");
		go2CalendarPage();
		boolean isVerified = verifyEventByFilterTypesListView(EventMeeting);
		if(isVerified==false){
			Assert.assertTrue(isVerified, "Unable to find the event by event filter types.");
		} 
	}
	
	@Test(priority=361)
 	public void corpVeriyEventInAllViews() throws Exception{
 		go2CorporateCalendarPage();		
 		verifyCalenderEventInAllViews(corpEventName_updtext);	
 	}
	 	
	 	
 	@Test(priority=362)
 	public void corpVerifyInviteGroups() throws Exception{	
	 	go2CorporateCalendarPage();	
	 	addNewCalendarEvent(corpEventMeeting, newEventStartdate_text, newEventEnddate_text,"");	
	 	logInfo(corpEventMeeting);
	 	go2CorporateCalendarPage();	
	 	boolean isFound = searchCalendarEvent(corpEventMeeting);
	 	if(isFound==true){
	 		editCalendarEvent (corpEventMeeting);
	 		verifyInvitedGuestsInAdmin("Invited", corpEventMeeting);			
	 	}	
 	
 	}
 	
 	@Test (priority=363)
 	public void corpShareEventByEmail() throws Exception{
 		//String corpEventMeeting = "Corporate434";
 		//String selfmailId  = "kevin.mcevoy@branch 1 10.vibeoffice.com";
 		
 		profile.navigateMyProfileAccount("Notifications");
 		String selfmailId = driver().findElement(ByCssSelector.cssSelector(mailId)).getText();
 		logInfo(selfmailId);		
 		go2CorporateCalendarPage();	
 		selectMoreOptioninListView(corpEventMeeting, "Share");
 		shop.selectEmailIcon();			   
 		comm.shareByEmail(selfmailId,corpEventsubText );		
 		profile.close();
 		inbox.go2Inbox();
 		inbox.selectVibeMailsWithSubject(corpEventsubText);
 		inbox.deleteFilteredVibeMails();		
 	}
 	
 	
 	@Test (priority=364)
 	public void corpShareEventInAllSocialNetworks() throws Exception{		
 		go2CorporateCalendarPage();	
 		selectMoreOptioninListView(corpEventMeeting, "Share");
 		shop.selectLinkedInIcon();
 		shop.shareInLinkedIn();
 		shop.selectFacebookIcon();		
 		shop.shareInFaceBook();
 		shop.selectTwitterInIcon();    	
     	shop.shareInTwitter();
     	shop.selectPWPInIcon(); 
     	confirmationMessage("Shared.");    		
 		profile.close();		
 	}
 	
 	
 	@Test(priority=365)
 	public void corpVerifyRepeatSettings() throws Exception{
 		go2CorporateCalendarPage();	
 		Assert.assertTrue(verifyRepeatCalendarSettings(repeatEventName_text2, repeatEventStartdate_text, repeatEventEnddate_text));
 	}
 	
 	@Test(priority=366)
 	public void corpVerifyCalendarListOptions() throws Exception{		
 		go2CorporateCalendarPage();	
 		Assert.assertTrue(verifyEventsListOptions(repeatEventName_text2));
 	}
 	
 	@Test(priority=367)
 	public void corpAddEventFromListView() throws Exception, Exception {
	 	go2CorporateCalendarPage();		
	 	calenderToolBar("List");
	 	addNewCalendarEventFromListView(corpListEvent, newEventStartdate_text, newEventEnddate_text,EventType3_text);
	 	go2CorporateCalendarPage();
	 	verifyEventisDisplayed(corpListEvent);	
 	
 	}
 		 	
 	@Test(priority=368)
 	public void corpVerifyEventTypeBySorting() throws Exception, Exception {
 		//String corpListEventProduct = "Corporate Product706";
	 	go2CorporateCalendarPage();	
	 	calenderToolBar("List");
	 	addNewCalendarEventFromListView(corpListEventProduct, newEventStartdate_text, newEventEnddate_text, EventType2_text);
	 	logInfo(corpListEventProduct);
	 	go2CorporateCalendarPage();
	 	calenderToolBar("List");
	 	eventTypeSelection("Show Products");	
	 	verifyListEvent(corpListEventProduct);
	 	go2CorporateCalendarPage();
	 	calenderToolBar("List");
	 	eventTypeSelection("Show Presentation");
	 	verifyListEventNotToPresent(corpListEventProduct);
    }

	
}
