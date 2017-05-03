package vibe.widgets.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
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
import vibe.tasks.tests.TaskMethods;
import vibe.calendar.tests.CalendarMethods;
import vibe.calendar.tests.Calendar_Tests;
import vibe.contacts2.tests.BusinessContactsMethods;

@Priority(7)
public class MonatWidgets_Tests extends WidgetsMethods {
	
	WidgetsMethods wm = new WidgetsMethods();
	TestBase tb = new TestBase();
	NewsTests nt = new NewsTests();
	NewsMethods nm = new NewsMethods();	
	CommunityMethods cm = new CommunityMethods();
	CalendarMethods cal = new CalendarMethods();
	MyProfileMethods pm = new MyProfileMethods();
	BusinessContactsMethods bm = new BusinessContactsMethods();
	AdsMethods ads = new AdsMethods();
	Community_Tests ct = new Community_Tests();
	GoalTest gt = new GoalTest();
	Calendar_Tests cat = new Calendar_Tests();
	MyProfileTests pt = new MyProfileTests();
	
	
	// ABOUT Widget
	
	@Test(priority=701)
	public void enableAboutWidget() throws Exception{
		logInfo("inside enableAboutWidget() Test");
		TaskMethods tm = new TaskMethods();
		
		setWidgetStatus("About",true);
			
		// verify enabled widgets are present in widgets pane.
		
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("About");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("About widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "About widget not found in the widget pane.");
		} 
		
						
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
		
		// drag and drop the widget on the home page.
		dragAndDropWidget("About");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("About");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("About");
		}
		
		if(isWidgetAdded2Homepage==true){
			logInfo("About widget added to home page.");
			 verifyWidgetForProblemsRendering("About");
			boolean isMatchFound = verifyAboutWidgetContent();
			if(!isMatchFound){
				Assert.assertTrue(isMatchFound, " Email match not found in the widget");
			}
		
			removeWidgetInHomepage("About");
		} else {
			logInfo("About widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "About widget could not be added to home page.");
		}
	}  
	
	
	
	
	
	
	// ACTION ITEMS Widget
	
	@Test(priority=703)
	public void enableActionItemsWidget() throws Exception{
		logInfo("inside enableActionItemsWidget() Test");
		TaskMethods tm = new TaskMethods();
		
		setWidgetStatus("Action Items",true);
			
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Action Items");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Action Items widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Action Items widget not found in the widget pane.");
		} 
		
						
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
		
		// drag and drop the widget on the home page.
		dragAndDropWidget("Action Items");
		
		/*boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Action Items");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Action Items");
		}*/
		
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Action Items");
		if(isWidgetAdded2Homepage==true){
			logInfo("Action Items widget added to home page.");
			verifyWidgetForProblemsRendering("Action Items");
			removeWidgetInHomepage("Action Items");
		} else {
			logInfo("Action Items widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Action Items widget could not be added to home page.");
		}
	}  
	
	
	
	@Test(priority=704)
	public void disableActionItemsWidget() throws Exception{
		logInfo("inside disableActionItemsWidget() Test");
		TaskMethods tm = new TaskMethods();
		setWidgetStatus("Action Items",false);
		go2HomePage();
		
		boolean isWidgPresent = verifyWidgetPresent("Action Items");
		if(isWidgPresent==true){
			logInfo("Action Items widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Action Items widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Action Items widget not found in the widget page, since the widget is disabled.");
		}
	}
	
	
	
	// Tabbed Information
	
	
	@Test(priority=705)
	public void enableTabbedInformationWidget() throws Exception{
		logInfo("inside enableTabbedInformationWidget() Test");
		
		
		setWidgetStatus("Tabbed Information",true);
		 nm.companyNewsCreation(title,excerpt, ranker5,languageList, subscripListOfMonat);
			
		// verify enabled widgets are present in widgets pane.
		go2HomePage();
		
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Tabbed Information");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Tabbed Information widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Tabbed Information widget not found in the widget pane.");
		} 
		
						
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
		
		// drag and drop the widget on the home page.
		dragAndDropWidget("Tabbed Information");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tabbed Information");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tabbed Information");
		}
		
		if(isWidgetAdded2Homepage==true){
			logInfo("Tabbed Information widget added to home page.");
			// verifyWidgetForProblemsRendering("Tabbed Information");
			 // String title = "news11";
			
			boolean isMatchFound = verifyNewsPresentInWidget(title);
			if(!isMatchFound){
				Assert.assertTrue(isMatchFound, " News match not found in the Tabbed Information widget");
			}
		
			removeWidgetInHomepage("Tabbed Information");
		} else {
			logInfo("Tabbed Information widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tabbed Information widget could not be added to home page.");
		}
	}  
	
	
	

	
	@Test(priority=706)
	public void disableTabbedInformationWidget() throws Exception{
		logInfo("inside disableTabbedInformationWidget() Test");
		TaskMethods tm = new TaskMethods();
		setWidgetStatus("Tabbed Information",false);
		go2HomePage();
		
		boolean isWidgPresent = verifyWidgetPresent("Tabbed Information");
		if(isWidgPresent==true){
			logInfo("Tabbed Information widget still present in the widget page when widget is disabled.");
			Assert.assertFalse(isWidgPresent, "Tabbed Information widget still present in the widget page when widget is disabled.");
		} else {
			logInfo("Tabbed Information widget not found in the widget page, since the widget is disabled.");
		}
	}
	
	
	
	
	
	// CALENDAR TYPES Widget

		@Test(priority=707)
		public void enableCalendarTypesWidget() throws Exception{
			logInfo("inside enableCalendarTypesWidget() Test");
			TaskMethods tm = new TaskMethods();
			
			setWidgetStatus("Calendar Types",true);
				
			// verify enabled widgets are present in widgets pane.
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Calendar Types");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Calendar Types widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Calendar Types widget not found in the widget pane.");
			} 
			
							
			// remove all widgets in home page.
			removeAllWidgetInHomepage();
			
			// drag and drop the widget on the home page.
			dragAndDropWidget("Calendar Types");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Calendar Types");
			while(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Calendar Types");
			}
			
			if(isWidgetAdded2Homepage==true){
				logInfo("Calendar Types widget added to home page.");
				// verifyWidgetForProblemsRendering("Calendar Types");
				verifyCalendarTypesInWidget();
				
				removeWidgetInHomepage("Calendar Types");
			} else {
				logInfo("Calendar Types widget could not be added to home page.");
				Assert.assertTrue(isWidgetAdded2Homepage, "Calendar Types widget could not be added to home page.");
			}
		}  
		
		
				// COMMUNITY STREAM Widget

		@Test(priority=709)
		public void enableCommunityStreamWidget() throws Exception{
			logInfo("inside enableCommunityStreamWidget() Test");
			TaskMethods tm = new TaskMethods();
					
			setWidgetStatus("Community Stream",true);
						
			// verify enabled widgets are present in widgets pane.
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Community Stream");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Community Stream widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Community Stream widget not found in the widget pane.");
			} 
					
									
			// remove all widgets in home page.
			removeAllWidgetInHomepage();
					
			// drag and drop the widget on the home page.
			dragAndDropWidget("Community Stream");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Community Stream");
			while(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Community Stream");
			}
					
			if(isWidgetAdded2Homepage==true){
				logInfo("Community Stream widget added to home page.");
				verifyWidgetForProblemsRendering("Community Stream");
				removeWidgetInHomepage("Community Stream");
			} else {
				logInfo("Community Stream widget could not be added to home page.");
				Assert.assertTrue(isWidgetAdded2Homepage, "Community Stream widget could not be added to home page.");
			}
		}  
				
				
				
				
		// COMPANY NEWS Widget

		@Test(priority=711)
		public void enableCompanyNewsWidget() throws Exception{
		logInfo("inside enableCompanyNewsWidget() Test");
		
									
		setWidgetStatus("Company News",true);
										
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Company News");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Company News widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Company News widget not found in the widget pane.");
		} 
									
													
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
									
		// drag and drop the widget on the home page.
		dragAndDropWidget("Company News");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Company News");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Company News");
		}
									
		if(isWidgetAdded2Homepage==true){
			logInfo("Company News widget added to home page.");
			verifyWidgetForProblemsRendering("Company News");
			removeWidgetInHomepage("Company News");
		} else {
			logInfo("Company News widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Company News widget could not be added to home page.");
		}
	}  
								
								
			
	// COMPANY SOCIAL NETWORKS Widget

		@Test(priority=713)
		public void enableCompanyNetworksWidget() throws Exception{
		logInfo("inside enableCompanyNetworksWidget() Test");
		TaskMethods tm = new TaskMethods();
									
		setWidgetStatus("Company Social Networks",true);
										
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Company Social Networks");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Company Social Networks widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Company Social Networks widget not found in the widget pane.");
		} 
									
													
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
									
		// drag and drop the widget on the home page.
		dragAndDropWidget("Company Social Networks");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Company Social Networks");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Company Social Networks");
		}
									
		if(isWidgetAdded2Homepage==true){
			logInfo("Company Social Networks widget added to home page.");
			verifyWidgetForProblemsRendering("Company Social Networks");
			removeWidgetInHomepage("Company Social Networks");
		} else {
			logInfo("Company Social Networks widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Company Social Networks widget could not be added to home page.");
		}
	}  
								
								
				// CONTACT GROUPS

		@Test(priority=715)
		public void enableContactGroupsWidget() throws Exception{
		logInfo("inside enableContactGroupsWidget() Test");
		TaskMethods tm = new TaskMethods();
											
		setWidgetStatus("Contact Groups",true);
												
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Contact Groups");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Contact Groups widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Contact Groups widget not found in the widget pane.");
		} 
											
															
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
											
		// drag and drop the widget on the home page.
		dragAndDropWidget("Contact Groups");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Contact Groups");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Contact Groups");
		}
											
		if(isWidgetAdded2Homepage==true){
			logInfo("Contact Groups widget added to home page.");
			verifyWidgetForProblemsRendering("Contact Groups");
			removeWidgetInHomepage("Contact Groups");
		} else {
			logInfo("Contact Groups widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Contact Groups widget could not be added to home page.");
		}
	}  
										
		
	
		
		// EVENTS Widgets

		@Test(priority=717)
		public void enableEventsWidget() throws Exception{
		logInfo("inside enableEventsWidget() Test");
		TaskMethods tm = new TaskMethods();
											
		setWidgetStatus("Events",true);
												
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Events");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Events widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Events widget not found in the widget pane.");
		} 
											
															
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
											
		// drag and drop the widget on the home page.
		dragAndDropWidget("Events");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Events");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Events");
		}
											
		if(isWidgetAdded2Homepage==true){
			logInfo("Events widget added to home page.");
			verifyWidgetForProblemsRendering("Events");
			removeWidgetInHomepage("Events");
		} else {
			logInfo("Events widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Events widget could not be added to home page.");
		}
	}  
										
		
		
		
		// FEATURED BLOGS Widgets

		@Test(priority=719)
		public void enableFeaturedBlogsWidget() throws Exception{
		logInfo("inside enableFeaturedBlogsWidget() Test");
		TaskMethods tm = new TaskMethods();
											
		setWidgetStatus("Featured Blogs",true);
												
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Featured Blogs");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Featured Blogs widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Featured Blogs widget not found in the widget pane.");
		} 
											
															
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
											
		// drag and drop the widget on the home page.
		dragAndDropWidget("Featured Blogs");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Featured Blogs");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Featured Blogs");
		}
											
		if(isWidgetAdded2Homepage==true){
			logInfo("Featured Blogs widget added to home page.");
			verifyWidgetForProblemsRendering("Featured Blogs");
			
			removeWidgetInHomepage("Featured Blogs");
		} else {
			logInfo("Featured Blogs widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Featured Blogs widget could not be added to home page.");
		}
	}  
										
										
				
		// FEATURED PHOTOS Widgets

		@Test(priority=721)
		public void enableFeaturedPhotosWidget() throws Exception{
		logInfo("inside enableFeaturedPhotosWidget() Test");
		TaskMethods tm = new TaskMethods();
													
		setWidgetStatus("Featured Photos",true);
														
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Featured Photos");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Featured Photos widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Featured Photos widget not found in the widget pane.");
		} 
													
																	
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
													
		// drag and drop the widget on the home page.
		dragAndDropWidget("Featured Photos");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Featured Photos");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Featured Photos");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("Featured Photos widget added to home page.");
			verifyWidgetForProblemsRendering("Featured Photos");
			removeWidgetInHomepage("Featured Photos");
		} else {
			logInfo("Featured Photos widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Featured Photos widget could not be added to home page.");
		}
	}  
												
												
			
		// FEATURED VIDEOS Widgets

		@Test(priority=723)
		public void enableFeaturedVideosWidget() throws Exception{
		logInfo("inside enableFeaturedVideosWidget() Test");
		TaskMethods tm = new TaskMethods();
													
		setWidgetStatus("Featured Videos",true);
														
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Featured Videos");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Featured Videos widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Featured Videos widget not found in the widget pane.");
		} 
													
																	
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
													
		// drag and drop the widget on the home page.
		dragAndDropWidget("Featured Videos");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Featured Videos");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Featured Videos");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("Featured Videos widget added to home page.");
			verifyWidgetForProblemsRendering("Featured Videos");
			removeWidgetInHomepage("Featured Videos");
		} else {
			logInfo("Featured Videos widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Featured Videos widget could not be added to home page.");
		}
	}  
												
				
		// FOLLOWING Widgets

		@Test(priority=725)
		public void enableFollowingWidget() throws Exception{
		logInfo("inside enableFollowingWidget() Test");
		TaskMethods tm = new TaskMethods();
													
		setWidgetStatus("Following",true);
														
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Following");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Following widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Following widget not found in the widget pane.");
		} 
													
																	
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
													
		// drag and drop the widget on the home page.
		dragAndDropWidget("Following");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Following");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Following");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("Following widget added to home page.");
			verifyWidgetForProblemsRendering("Following");
			removeWidgetInHomepage("Following");
		} else {
			logInfo("Following widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Following widget could not be added to home page.");
		}
	}  
		
			
		
		
		// Goals Widgets

		@Test(priority=727)
		public void enableGoalsWidget() throws Exception{
		logInfo("inside enableGoalsWidget() Test");
		TaskMethods tm = new TaskMethods();
													
		setWidgetStatus("Goals",true);
														
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Goals");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Goals widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Goals widget not found in the widget pane.");
		} 
													
																	
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
													
		// drag and drop the widget on the home page.
		dragAndDropWidget("Goals");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Goals");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Goals");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("Goals widget added to home page.");
			verifyWidgetForProblemsRendering("Goals");
			removeWidgetInHomepage("Goals");
		} else {
			logInfo("Goals widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Goals widget could not be added to home page.");
		}
	}  
												
												
					
		
		
		// MYBLOG Widgets

		@Test(priority=729)
		public void enableMyBlogWidget() throws Exception{
		logInfo("inside enableMyBlogWidget() Test");
		TaskMethods tm = new TaskMethods();
													
		setWidgetStatus("My Blog",true);
														
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("My Blog");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("My Blog widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "My Blog widget not found in the widget pane.");
		} 
													
																	
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
													
		// drag and drop the widget on the home page.
		dragAndDropWidget("My Blog");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("My Blog");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("My Blog");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("My Blog widget added to home page.");
			verifyWidgetForProblemsRendering("My Blog");
			removeWidgetInHomepage("My Blog");
		} else {
			logInfo("My Blog widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "My Blog widget could not be added to home page.");
		}
	}  
												
												
			
		
	// MY PHOTOS Widgets

		@Test(priority=731)
		public void enableMyPhotosWidget() throws Exception{
		logInfo("inside enableMyPhotosWidget() Test");
		TaskMethods tm = new TaskMethods();
													
		setWidgetStatus("My Photos",true);
														
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("My Photos");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("My Photos widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "My Photos widget not found in the widget pane.");
		} 
													
																	
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
													
		// drag and drop the widget on the home page.
		dragAndDropWidget("My Photos");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("My Photos");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("My Photos");
		}
													
		if(isWidgetAdded2Homepage==true){
			logInfo("My Photos widget added to home page.");
			verifyWidgetForProblemsRendering("My Photos");
			removeWidgetInHomepage("My Photos");
		} else {
			logInfo("My Photos widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "My Photos widget could not be added to home page.");
		}
	}  
												
												
			
	
		// MY VIDEOS Widgets

		@Test(priority=733)
		public void enableMyVideosWidget() throws Exception{
		logInfo("inside enableMyVideosWidget() Test");
		TaskMethods tm = new TaskMethods();
															
		setWidgetStatus("My Videos",true);
																
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("My Videos");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("My Videos widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "My Videos widget not found in the widget pane.");
		} 
															
																			
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
															
		// drag and drop the widget on the home page.
		dragAndDropWidget("My Videos");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("My Videos");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("My Videos");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("My Videos widget added to home page.");
			verifyWidgetForProblemsRendering("My Videos");
			removeWidgetInHomepage("My Videos");
		} else {
			logInfo("My Videos widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "My Videos widget could not be added to home page.");
		}
	}  
														
														
				
	
		
		// MY WEBSITES Widgets

		@Test(priority=735)
		public void enableMyWebsitesWidget() throws Exception{
		logInfo("inside enableMyWebsitesWidget() Test");
		TaskMethods tm = new TaskMethods();
															
		setWidgetStatus("My Websites",true);
																
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("My Websites");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("My Websites widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "My Websites widget not found in the widget pane.");
		} 
															
																			
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
															
		// drag and drop the widget on the home page.
		dragAndDropWidget("My Websites");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("My Websites");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("My Websites");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("My Websites widget added to home page.");
			verifyWidgetForProblemsRendering("My Websites");
			removeWidgetInHomepage("My Websites");
		} else {
			logInfo("My Websites widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "My Websites widget could not be added to home page.");
		}
	}  
														
			
		
		// NOTIFICATIONS Widgets

		@Test(priority=737)
		public void enableNotificationsWidget() throws Exception{
		logInfo("inside enableNotificationsWidget() Test");
		
		// Enable Notifications Widget	
		setWidgetStatus("Notifications",true);
		
		go2HomePage();
		cat.createNewCalendarEvent();
																
		// verify enabled widgets are present in widgets pane.
		go2HomePage();
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Notifications");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Notifications widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Notifications widget not found in the widget pane.");
		} 
															
																			
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
															

		// drag and drop the widget on the home page.
		dragAndDropWidget("Notifications");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Notifications");
		if(isWidgetAdded2Homepage==true){
			logInfo("Notifications widget added to home page.");
			
			String notification_text = "upcoming event(s) in the next 7 days";
			boolean isNotificationFound = verifyNotificationsInWidget(notification_text);  
			if(isNotificationFound==false){
				logInfo(notification_text + " notification not found in Notifications widget.");
				Assert.assertTrue(isNotificationFound, notification_text + " notification not found in Notifications widget.");
			}
		}
	}  
			
		// QUICKLINKS BUTTON VIEW Widgets

		@Test(priority=741)
		public void enableQuickLinksButtonViewWidget() throws Exception{
		logInfo("inside enableQuickLinksButtonViewWidget() Test");
		TaskMethods tm = new TaskMethods();
															
		setWidgetStatus("Quick Links Button View",true);
																
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Quick Links Button View");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Quick Links Button View widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Quick Links Button View widget not found in the widget pane.");
		} 
															
																			
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
															
		// drag and drop the widget on the home page.
		dragAndDropWidget("Quick Links Button View");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Quick Links Button View");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Quick Links Button View");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("Quick Links Button View widget added to home page.");
			verifyWidgetForProblemsRendering("Quick Links Button View");
			removeWidgetInHomepage("Quick Links Button View");
		} else {
			logInfo("Quick Links Button View widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Quick Links Button View widget could not be added to home page.");
		}
	}  
														
														
														
		
		
		
		// QUICKLINKS LIEW VIEW Widgets

		@Test(priority=743)
		public void enableQuickLinksListViewWidget() throws Exception{
		logInfo("inside enableQuickLinksButtonListWidget() Test");
		TaskMethods tm = new TaskMethods();
															
		setWidgetStatus("Quick Links List View",true);
																
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Quick Links List View");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Quick Links List View widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Quick Links List View widget not found in the widget pane.");
		} 
															
																			
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
															
		// drag and drop the widget on the home page.
		dragAndDropWidget("Quick Links List View");
		
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Quick Links List View");
		if(isWidgetAdded2Homepage==true){
			dragAndDropWidget("Quick Links List View");
			verifyWidgetForProblemsRendering("Quick Links List View");
			removeWidgetInHomepage("Quick Links List View");
		}	else {
			logInfo("Quick Links List View widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Quick Links List View widget could not be added to home page.");
		}
	}  
														
														
														
			
	
		
		// RECENT ACTIVITY Widgets

		@Test(priority=745)
		public void enableRecentActivityWidget() throws Exception{
		logInfo("inside enableRecentActivityWidget() Test");
		TaskMethods tm = new TaskMethods();
															
		setWidgetStatus("Recent Activity",true);
																
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Recent Activity");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Recent Activity widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Recent Activity widget not found in the widget pane.");
		} 
															
																			
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
															
		// drag and drop the widget on the home page.
		dragAndDropWidget("Recent Activity");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Recent Activity");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Recent Activity");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("Recent Activity widget added to home page.");
			verifyWidgetForProblemsRendering("Recent Activity");
			// removeWidgetInHomepage("Recent Activity");
		} else {
			logInfo("Recent Activity widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Recent Activity widget could not be added to home page.");
		}
	}  
														
														
														
				
		
		
		// RESOURCE LIBRARY Widgets

		@Test(priority=749)
		public void enableResourceLibraryWidget() throws Exception{
		logInfo("inside enableResourceLibraryWidget() Test");
		TaskMethods tm = new TaskMethods();
															
		setWidgetStatus("Resource Library",true);
		
																
		// verify enabled widgets are present in widgets pane.
		go2HomePage();
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Resource Library");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Resource Library widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Resource Library widget not found in the widget pane.");
		} 
															
																			
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
															
		// drag and drop the widget on the home page.
		dragAndDropWidget("Resource Library");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Resource Library");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Resource Library");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("Resource Library widget added to home page.");
			verifyWidgetForProblemsRendering("Resource Library");
			removeWidgetInHomepage("Resource Library");
		} else {
			logInfo("Resource Library widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Resource Library widget could not be added to home page.");
		}
	}  
														
														
														
			
		
		
		// SEARCH CONTACTS Widgets

		@Test(priority=751)
		public void enableSearchContactsWidget() throws Exception{
		logInfo("inside enableSearchContactsWidget() Test");
		TaskMethods tm = new TaskMethods();
															
		setWidgetStatus("Search Contacts",true);
																
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Search Contacts");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Search Contacts widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Search Contacts widget not found in the widget pane.");
		} 
															
																			
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
															
		// drag and drop the widget on the home page.
		dragAndDropWidget("Search Contacts");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Search Contacts");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Search Contacts");
		}
															
		if(isWidgetAdded2Homepage==true){
			logInfo("Search Contacts widget added to home page.");
			verifyWidgetForProblemsRendering("Search Contacts");
			removeWidgetInHomepage("Search Contacts");
		} else {
			logInfo("Search Contacts widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Search Contacts widget could not be added to home page.");
		}
	}  
														
			
		
		
		// SEARCH EVENTS Widgets

		@Test(priority=753)
		public void enableSearchEventsWidget() throws Exception{
		logInfo("inside enableSearchEventsWidget() Test");
		TaskMethods tm = new TaskMethods();
																	
		setWidgetStatus("Search Events",true);
																		
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Search Events");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Search Events widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Search Events widget not found in the widget pane.");
		} 
																	
																					
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
																	
		// drag and drop the widget on the home page.
		dragAndDropWidget("Search Events");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Search Events");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Search Events");
		}
																	
		if(isWidgetAdded2Homepage==true){
			logInfo("Search Events widget added to home page.");
			verifyWidgetForProblemsRendering("Search Events");
			removeWidgetInHomepage("Search Events");
		} else {
			logInfo("Search Events widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Search Events widget could not be added to home page.");
		}
	}  
																
				
		
		// ADD TASKS Widgets

		@Test(priority=755)
		public void enableTasksAddWidget() throws Exception{
		logInfo("inside enableTasksAddWidget() Test");
		TaskMethods tm = new TaskMethods();
																	
		setWidgetStatus("Tasks Add",true);
																		
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Tasks Add");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Tasks Add widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Tasks Add widget not found in the widget pane.");
		} 
																	
																					
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
																	
		// drag and drop the widget on the home page.
		dragAndDropWidget("Tasks Add");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tasks Add");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tasks Add");
		}
																	
		if(isWidgetAdded2Homepage==true){
			logInfo("Tasks Add widget added to home page.");
			verifyWidgetForProblemsRendering("Tasks Add");
			removeWidgetInHomepage("Tasks Add");
		} else {
			logInfo("Tasks Add widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tasks Add widget could not be added to home page.");
		}
	}  
	
		// TASKS COMPLETED Widget

	
		@Test(priority=757)
		public void enableTasksCompletedWidget() throws Exception{
		logInfo("inside enableTasksCompletedWidget() Test");
		TaskMethods tm = new TaskMethods();
																			
		setWidgetStatus("Tasks Completed",true);
																				
		// verify enabled widgets are present in widgets pane.
		boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Tasks Completed");
		if(!isWidgetPresentInWidgetPane==true){
			System.out.println("Tasks Completed widget not found in the widget pane.");
			Assert.assertTrue(isWidgetPresentInWidgetPane, "Tasks Completed widget not found in the widget pane.");
		} 
																			
																							
		// remove all widgets in home page.
		removeAllWidgetInHomepage();
																			
		// drag and drop the widget on the home page.
		dragAndDropWidget("Tasks Completed");
		boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Tasks Completed");
		while(isWidgetAdded2Homepage==false){
			dragAndDropWidget("Tasks Completed");
		}
																			
		if(isWidgetAdded2Homepage==true){
			logInfo("Tasks Completed widget added to home page.");
			verifyWidgetForProblemsRendering("Tasks Completed");
			removeWidgetInHomepage("Tasks Completed");
		} else {
			logInfo("Tasks Completed widget could not be added to home page.");
			Assert.assertTrue(isWidgetAdded2Homepage, "Tasks Completed widget could not be added to home page.");
		}
	}  

		
	// COMPANY OR CAROUSEL ADS
	
		@Test(priority=777)
		public void enableCarouselAdsWidget() throws Exception{
			logInfo("inside verifyCarouselAdsWidget() Test");
			
			// ENABLE CAROUSEL ADS IN ADMIN WIDGET
			
			setWidgetStatus("Carousel Ads",true);
						
			
			// VERIFY CAROUSEL WIDGET FOUND IN WIDGETS PANE AFTER ENABLING IT. IF FOUND DRAG THE WIDGET TO HOME PAGE AND VERIFY THE WIDGET.
			go2HomePage();
			ads.createNewAdd(urlAds2,"Published");
			
			go2HomePage();
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Carousel Ads");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Carousel Ads widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Carousel Ads widget not found in the widget pane.");
			} 
			
			
			// remove all widgets in home page.
			removeAllWidgetInHomepage();
			
			
			dragAndDropWidget("Carousel Ads");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Carousel Ads");
			while(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Carousel Ads");
			}
			
			
			if(isWidgetAdded2Homepage==true){
				logInfo("Carousel Ads widget added to home page.");
				verifyWidgetForProblemsRendering("Carousel Ads");
				boolean isAdMatchFound = verifyAdsInWidget("Healthy drinks");		// 
				if(!isAdMatchFound){
					Assert.assertTrue(isAdMatchFound, "Ad not found in Carousel Ads Widgets page.");
				}
			} else {
				logInfo("Carousel Ads widget could not be added to home page.");
				Assert.assertTrue(isWidgetAdded2Homepage, "Carousel Ads widget could not be added to home page.");
			}
			
			removeWidgetInHomepage("Carousel Ads");
		}
		
		
	// ADD CONTACT FROM WIDGET
		
	@Test(priority=781)
		public void addContactFromWidget() throws Exception{
			logInfo("inside addContactFromWidget() Test");
			
			// ENABLE ADD CONTACT WIDGET
			setWidgetStatus("Add Contact",true);
			
			
			// verify enabled widgets are present in widgets pane.
			go2HomePage();
			
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Add Contact");
			if(!isWidgetPresentInWidgetPane==true){
					System.out.println("Add Contact widget not found in the widget pane.");
					Assert.assertTrue(isWidgetPresentInWidgetPane, "Add Contact widget not found in the widget pane.");
			} 
						
										
			// remove all widgets in home page.
			removeAllWidgetInHomepage();
						
			// drag and drop the widget on the home page.
			dragAndDropWidget("Add Contact");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Add Contact");
			while(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Add Contact");
			}
			
			if(isWidgetAdded2Homepage==true){
				logInfo("Add Contact widget added to home page.");
				verifyWidgetForProblemsRendering("Add Contact");
				closeEditWidgetsPane();
				addContactFromWidget("TEST99","CONTACT99");
				confirmationMessage("Contact is created");
				Thread.sleep(5000);
				
			} else {
				logInfo("Add Contact widget could not be added to home page.");
				Assert.assertTrue(isWidgetAdded2Homepage, "Add Contact widget could not be added to home page.");
			}
			
			removeWidgetInHomepage("Add Contact");
		}	
		
		
	
		
	//****************************************** widgets content verification ***********************************************//
		
		@Test(priority=784)
		public void verifyCompanyNewsWidgetContent() throws Exception{
			logInfo("inside verifyCompanyNewsWidgetContent() Test");			
			nt.companyNewsCreation(title,excerpt, ranker5, languageList,subscripListOfMonat);			
			setWidgetStatus("Company News",true);
			go2HomePage();
			
			removeAllWidgetInHomepage();
			go2HomePage();
			
			// verify enabled widgets are present in widgets pane.
			
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Company News");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Company News widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Company News widget not found in the widget pane.");
			} 
			
			// verify if widget is present in home page. If present remove it.
			
			boolean isWidgetPresentInHomepage = verifyWidgetsInHomepage("Company News");
			if(isWidgetPresentInHomepage==true){
				removeWidgetInHomepage("Company News");
			}
			
			// drag and drop the widget on the home page.
					
			dragAndDropWidget("Company News");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Company News");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Company News");
			}
			
			if(verifyWidgetForProblemsRendering("Company News")){
				boolean isNewsPresent = verifyNewsPresentOnWidget(title);
				if(isNewsPresent==false){
					Assert.assertTrue(isNewsPresent, "Unable to  find company news on the company news widget.");
				}
				else{
					boolean isNewsPresentViewMore = verifyNewsInViewMore(title);
					if(isNewsPresentViewMore==false){
						Assert.assertTrue(isNewsPresentViewMore, "Unable to  find company news when click on view more.");
					}
				}
			}
			nt.news_DeleteNews();
				
		}  
		
		
		@Test(priority=785)
		public void verifyAddContactWidgetContent() throws Exception{
			logInfo("inside verifyAddContactWidgetContent() Test");
			
			setWidgetStatus("Add Contact",true);
			go2HomePage();
			
			removeAllWidgetInHomepage();
			go2HomePage();
			
			// verify enabled widgets are present in widgets pane.
			
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Add Contact");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Add Contact widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Add Contact widget not found in the widget pane.");
			} 
			
			// verify if widget is present in home page. If present remove it.
			
			boolean isWidgetPresentInHomepage = verifyWidgetsInHomepage("Add Contact");
			if(isWidgetPresentInHomepage==true){
				removeWidgetInHomepage("Add Contact");
			}
			
			// drag and drop the widget on the home page.
					
			
			dragAndDropWidget("Add Contact");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Add Contact");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Add Contact");
			}
			
			if(!verifyWidgetForProblemsRendering("Add Contact")){
				addContactFromWidget(contactFirstName_text,contactLastName_text);
				boolean isContactAdded= bm.verifyBusinessContact(contactFirstName_text,contactLastName_text);
				if(isContactAdded==false){
					System.out.println("Business contact could not be added from the Add Contact widget.");
					Assert.assertTrue(isContactAdded, "Business contact could not be added from the Add Contact widget.");
				}
				bm.deleteBusinessContact(contactFirstName_text,contactLastName_text);
			}
		}  
		
		
		@Test(priority=786)
		public void verifyBusinessAlertsWidgetContent() throws Exception{
			logInfo("inside verifyBusinessAlertsWidgetContent() Test");
			
			
			// Enable widgets in admin page
			
			setWidgetStatus("Business Alerts",true);
			go2HomePage();
			
			// Remove all widgets in home page.
			removeAllWidgetInHomepage();
		
			// verify enabled widgets are present in widgets pane.
			
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Business Alerts");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Business Alerts widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Business Alerts widget not found in the widget pane.");
			} 
			
			// verify if widget is present in home page. If present remove it.
			
			boolean isWidgetPresentInHomepage = verifyWidgetsInHomepage("Business Alerts");
			if(isWidgetPresentInHomepage==true){
				removeWidgetInHomepage("Business Alerts");
			}
			
			// drag and drop the widget on the home page.
					
			
			dragAndDropWidget("Business Alerts");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Business Alerts");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Business Alerts");
			}
			
			if(!verifyWidgetForProblemsRendering("Business Alerts")){
				boolean isTaskMessageFound= verifyTaskInBusinessAlerts(taskMessage);
				if(isTaskMessageFound==false){
					System.out.println("Unable to find the task message in Business Alerts widget.");
					Assert.assertTrue(isTaskMessageFound, "Unable to find the task message in Business Alerts widget.");
				}
			}
		}
		
		
		
		@Test(priority=790)
		public void verifyRecentActivityWidgetContent() throws Exception{
			logInfo("inside verifyRecentActivityWidgetContent() Test");
			
			cm.addBlogPost(addPostTitle_text);
			
			setWidgetStatus("Recent Activity",true);
			
			
			go2HomePage();
			removeAllWidgetInHomepage();
			
			// verify enabled widgets are present in widgets pane.
					
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Recent Activity");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Recent Activity widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Recent Activity widget not found in the widget pane.");
			} 
			
			
			
			// drag and drop the widget on the home page.
					
			dragAndDropWidget("Recent Activity");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Recent Activity");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Recent Activity");
			}
			
			if(!verifyWidgetForProblemsRendering("Recent Activity")){
				boolean isPostFound =  cm.verifyPostIsPresent(addPostTitle_text);
				if(isPostFound==false){
					System.out.println("Unable to find the blog post in Recent Activity widget.");
					Assert.assertTrue(isPostFound, "Unable to find the blog post in Recent Activity widget.");
				}
				pm.navigate2MyProfilePage();
				cm.deleteBlogPost(addPostTitle_text);
							
			}
		}
		
		
		// COMMUNITY STREAM WIDGET
		
		@Test(priority=791)
		public void verifyCommunityStreamWidgetContent() throws Exception{
			logInfo("inside verifyCommunityStreamWidgetContent() Test");
			
			setWidgetStatus("Recent Activity",true);
			setWidgetStatus("Community Stream",true);
			go2HomePage();
			
			
			// verify enabled widgets are present in widgets pane.
			
			boolean isCSWidgetPresentInWidgetPane = verifyWidgetPresent("Community Stream");
			if(!isCSWidgetPresentInWidgetPane==true){
				System.out.println("Community Stream widget not found in the widget pane.");
				Assert.assertTrue(isCSWidgetPresentInWidgetPane, "Community Stream widget not found in the widget pane.");
			} 
			
			boolean isRAWidgetPresentInWidgetPane = verifyWidgetPresent("Recent Activity");
			if(!isRAWidgetPresentInWidgetPane==true){
				System.out.println("Recent Activity widget not found in the widget pane.");
				Assert.assertTrue(isRAWidgetPresentInWidgetPane, "Recent Activity widget not found in the widget pane.");
			} 
			
			// drag and drop the widget on the home page.
			
			removeAllWidgetInHomepage();
			
			dragAndDropWidget("Recent Activity");
			
			boolean isRAWidgetAdded2Homepage = verifyWidgetsInHomepage("Recent Activity");
			System.out.println("isRAWidgetAdded2Homepage =" +isRAWidgetAdded2Homepage);
			if(isRAWidgetAdded2Homepage==false){
				dragAndDropWidget("Recent Activity");
			} 
			
			cm.addBlogPost(addPostTitle_text);
			removeWidgetInHomepage("Recent Activity");
			
			dragAndDropWidget("Community Stream");
			
			boolean isCSWidgetAdded2Homepage = verifyWidgetsInHomepage("Community Stream");
			System.out.println("isCSWidgetAdded2Homepage =" +isCSWidgetAdded2Homepage);
			if(isCSWidgetAdded2Homepage==false){
				dragAndDropWidget("Community Stream");
			} 
			
		    // verifyWidgetForProblemsRendering("Community Stream")){
				
				
			boolean isPostFound =  verifyCommunityStreamWidget(addPostTitle_text);
			if(isPostFound==false){
				logInfo(addPostTitle_text + " post not found in Community Stream widget.");
					pm.navigate2MyProfilePage();
					cm.deleteBlogPost(addPostTitle_text);
					Assert.assertTrue(isPostFound, addPostTitle_text + " post not found in Community Stream widget.");
				}

			 pm.navigate2MyProfilePage();
			 cm.deleteBlogPost(addPostTitle_text);
		}
	
		
		@Test(priority=792)
		public void verifyEventsWidgetContent() throws Exception{
			logInfo("inside verifyEventsWidgetContent() Test");
		
			setWidgetStatus("Events",true);
			
			// Remove all widgets in the home page.
			go2HomePage();
			removeAllWidgetInHomepage();
			
			
			// verify enabled widgets are present in widgets pane.
			go2HomePage();
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Events");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Events widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Events widget not found in the widget pane.");
			} 
			
						
			// drag and drop the widget on the home page.
					
			dragAndDropWidget("Events");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Events");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Events");
			}
			
			if(!verifyWidgetForProblemsRendering("Events")){
				closeEditWidgetsPane();
				Thread.sleep(5000);
				addEventFromWidget(newEventName_text);
				boolean isEventFound =  verifyEventInEventsWidget(newEventName_text);
				if(isEventFound==false){
					System.out.println("Unable to find the event in Events widget.");
					Assert.assertTrue(isEventFound, "Unable to find the event in Events widget.");
				}
				cal.deleteCalendarEvent("New Event",newEventName_text);
							
			}
		}
		
		
		@Test(priority=793)
		public void verifyGroupsWidgetContent() throws Exception{
			logInfo("inside verifyGroupsWidgetContent() Test");
			
			// Enable Contact Groups widget
			setWidgetStatus("Contact Groups",true);
			
			go2HomePage();
			bm.addGroup(groupName_text);

			boolean isGroupAdded = bm.verifyGroupIsPresent(groupName_text);  
			if(isGroupAdded==false){
			  logInfo(groupName_text + " group could not be created.");
			  Assert.assertTrue(isGroupAdded, groupName_text + " group could not be created.");
			}
			
		
			// verify enabled widgets are present in widgets pane.
			go2HomePage();
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Contact Groups");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Contact Groups widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Contact Groups widget not found in the widget pane.");
			} 
			
			// remove all widgets in home page.
			removeAllWidgetInHomepage();
			
			
			// drag and drop the widget
			
			dragAndDropWidget("Contact Groups");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Contact Groups");
			if(isWidgetAdded2Homepage==false){
				dragAndDropWidget("Contact Groups");
			}
					
			
			boolean isGroupFound = false;
			isGroupFound = verifyContactGroupsInWidget(groupName_text);
			if(isGroupFound==false){
				logInfo(groupName_text + " group not found inside the widget.");
				Assert.assertTrue(isGroupFound, groupName_text + " group not found inside the widget.");
			}
			
			bm.deleteGroup(groupName_text);
			confirmationMessage("Group Deleted.");
			isGroupFound = bm.verifyGroupIsPresent(groupName_text);
			System.out.println("isGroupFound =" +isGroupFound);
			removeWidgetInHomepage("Contact Groups");
		}
		
		
		// Goal Widget
		
		@Test(priority=794)
		public void verifyGoalWidgetContent() throws Exception{
			logInfo("inside verifyGoalWidgetContent() Test");
			
			setWidgetStatus("Goals",true);
			addNewGoal(Title_text);
			
			// verify enabled widgets are present in widgets pane.
			
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("Goals");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("Goals widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "Goals widget not found in the widget pane.");
			} 
			
							
			// remove all widgets in home page.
				removeAllWidgetInHomepage();
			
			// drag and drop the widget on the home page.
			dragAndDropWidget("Goals");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("Goals");
			if(isWidgetAdded2Homepage==true){
				logInfo("Goals widget added to home page.");
				boolean isGoalFound = verifyGoalsInWidget(Title_text);  
				if(isGoalFound==false){
					logInfo(Title_text + " goal not found in Goals widget.");
					Assert.assertTrue(isGoalFound, Title_text + " goal not found in Goals widget.");
				}
			}
		
		}	
		
		
		
		// MY PHOTOS WIDGET
		
		
		@Test(priority=795)
		public void verifyMyPhotoWidgetContent() throws Exception{
			logInfo("inside verifyMyPhotoWidgetContent() Test");
			
			setWidgetStatus("My Photos",true);
			pm.navigate2MyProfilePage();
			cm.addCommunityPhoto(photoTitle_text, visibilitySettings_text);
			// pt.profile_AddPhoto();
			
			// verify enabled widgets are present in widgets pane.
			
			boolean isWidgetPresentInWidgetPane = verifyWidgetPresent("My Photos");
			if(!isWidgetPresentInWidgetPane==true){
				System.out.println("My Photos widget not found in the widget pane.");
				Assert.assertTrue(isWidgetPresentInWidgetPane, "My Photos widget not found in the widget pane.");
			} 
			
							
			// remove all widgets in home page.
				removeAllWidgetInHomepage();
			
			// drag and drop the widget on the home page.
			dragAndDropWidget("My Photos");
			boolean isWidgetAdded2Homepage = verifyWidgetsInHomepage("My Photos");
			if(isWidgetAdded2Homepage==true){
				logInfo("My Photos widget added to home page.");
				
				boolean isMyPhotoFound = verifyMyPhotosInWidget(photoTitle_text);  
				if(isMyPhotoFound==false){
					logInfo(photoTitle_text + " photo not found in My Photos widget.");
					Assert.assertTrue(isMyPhotoFound, photoTitle_text + " photo not found in My Photos widget.");
				}
			}
		
		}	
	
		
		
		
		
		
}
