package vibe.widgets.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.tasks.tests.TaskMethods;
import vibe.marketing.ads.tests.AdsMethods;
import vibe.marketing.ads.tests.AdsTests;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.marketing.companyNews.tests.NewsTests;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.mycommunity.tests.Community_Tests;
import vibe.myprofile.tests.GoalTest;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.myprofile.tests.MyProfileTests;
import vibe.reports.tests.AvonReportsMethods;
import vibe.tasks.tests.TaskMethods;
import vibe.calendar.tests.CalendarMethods;
import vibe.calendar.tests.Calendar_Tests;
import vibe.contacts2.tests.BusinessContactsMethods;

@Priority(43)
public class AvonWidgets_Tests extends WidgetsMethods {
	
	WidgetsMethods wm = new WidgetsMethods();
	TestBase tb = new TestBase();
	NewsTests nt = new NewsTests();
//	NewsMethods nm = new NewsMethods();
	// TaskMethods tm = new TaskMethods();
	CommunityMethods cm = new CommunityMethods();
	CalendarMethods cal = new CalendarMethods();
	MyProfileMethods pm = new MyProfileMethods();
	BusinessContactsMethods bm = new BusinessContactsMethods();
	AdsMethods ads = new AdsMethods();
	Community_Tests ct = new Community_Tests();
	GoalTest gt = new GoalTest();
	Calendar_Tests cat = new Calendar_Tests();
	MyProfileTests pt = new MyProfileTests();
	AvonReportsMethods arm = new AvonReportsMethods();
	readProp prop = new readProp();
	
	
	@Test(priority=4301)
	 public void avon_ValidateRepresentativeDetailsWidget() throws Exception{
		 logInfo("Entered into avon_ValidateAllTablesWithTitles() test");	
		 loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		// remove all widgets in home page.
		 deleteAllWidgets();		
		 arm.dragAndDropWidget("Progress Report");			 
		 boolean isWidgetPresentInWidgetPane = verifyWidgetPresentInAvon("Progress Report");
	     if (isWidgetPresentInWidgetPane==true){	    	
	    	 arm.verifyItemsInWidget();
	    	 arm.profileSections("Business Dashboard");
	    	 arm.verifyBusinessDashBoard();	
	   	 
	         } 
	     }
	     
	     @Test(priority=4302)
		 public void avon_ValidateBusinessDashboardWidget() throws Exception{
			 logInfo("Entered into avon_ValidateAllTablesWithTitles() test");			
			 go2HomePage();
			// remove all widgets in home page.
			 deleteAllWidgets();			
			 arm.dragAndDropWidget("Business Dashboard");
			 boolean isWidgetPresentInWidgetPane = verifyWidgetPresentInAvon("Business Dashboard");
		     if (isWidgetPresentInWidgetPane==true){	 
			 arm.verifyBusinessDashBoard();	
		     }		 
	     }
	     
	     
	     @Test(priority=4303)	     
	     public void avon_ValidateActionAlertsWidget() throws  Exception {
	            logInfo("Inside verifyActionAlertsWidget() method.");	           
	            go2HomePage();
				// remove all widgets in home page.
				 deleteAllWidgets();			
				 arm.dragAndDropWidget("Action Alerts");
				 boolean isWidgetPresentInWidgetPane = verifyWidgetPresentInAvon("Action Alerts");
			     if (isWidgetPresentInWidgetPane==true){	
			    	 arm.verifylinksOfActionAlerts();
	            
			     }
	     }
		 
	     @Test(priority=4304)	     
	     public void avon_ValidateSearchContactsWidget() throws  Exception {
	            logInfo("Inside avon_ValidateSearchContactsWidget() method.");
	           // nm.loginAsUser(repID);	
	            go2HomePage();
				// remove all widgets in home page.
				 deleteAllWidgets();			
				 arm.dragAndDropWidget("Search Contacts");
				 boolean isWidgetPresentInWidgetPane = verifyWidgetPresentInAvon("Search Contacts");
			     if (isWidgetPresentInWidgetPane==true){	
			    	 arm.searchContactFromWidget("Test Contact");
	            
			     }
	     } 	
	     
	     
	     @Test(priority=4305)
	 	public void avon_ValidateQuickLinksWidget() throws Exception {
	 		logInfo("Inside quickLinksContacts() method.");	 		
	 		 
	 		go2HomePage();		
			deleteAllWidgets();			
			arm.dragAndDropWidget("Relationship Manager Quick Links");	
	 		//verifyContactsLinkInQuickLinks();
	 		verifyCalendarLinkInQuickLinks();
	 		verifyTasksLinkInQuickLinks();
	 		
	 		
	 	}
	
		
	     
	     @Test(priority=4305)
	 	public void avon_ValidateBusinessAlertsWidget() throws Exception {
	 		logInfo("Inside quickLinksContacts() method.");	 		
	 		 
	 		go2HomePage();		
			deleteAllWidgets();			
			arm.dragAndDropWidget("Business Alerts");	
	 	
	 		
	 		
	 	}
	
			
		
}
