package vibe.widgets.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import vibe.tasks.tests.TaskMethods;
import common.TestBase;

public class WidgetsMethods extends TestBase {
	
	
	public void go2HomePage(){
		logInfo("inside go2HomePage() method");
		driver().navigate().to(appUrl);
	}
	
	
	public void go2WidgetsPage(){
		logInfo("inside go2WidgetsPage() method");
		String widgetsUrl = appUrl + "pyr_core/widgets";
		driver().navigate().to(widgetsUrl);
	}
	
	public void go2EditWidgetsPage() throws Exception{
		logInfo("inside go2EditWidgetsPage() method.");
		System.out.println("inside go2EditWidgetsPage() method.");
		driver().navigate().to(appUrl);
		clickOnLink("linkText","Edit Widgets");
	}
	
	
	public void closeEditWidgetsPane() throws Exception, Exception{
		logInfo("inside go2EditWidgetsPage() method.");
		// waitOnElement("xpath","//*[@class='widget-picker']/div[1]/a[4]");
		clickOnElement("xpath","//*[@class='widget-picker']/div[1]/a[4]");
	}
	
	// Enable or disable the widget
	
	public void setWidgetStatus(String widgetName, boolean activateStatus) throws Exception{
		logInfo("inside setWidgetStatus() method.");
		System.out.println("inside setWidgetStatus() method.");
		
		go2WidgetsPage();

		WebElement widgetsTable = driver().findElement(By.cssSelector(adminWidgetsTable));
		List allWidgets = widgetsTable.findElements(By.tagName("tr"));
		int widget_count = allWidgets.size();
	
		String widget_before = "//table/tbody/tr[";
		String widget_after = "]/td[1]";
	
		for(int i=1;i<=widget_count;i++){
			WebElement e = driver().findElement(By.xpath(widget_before+i+widget_after));
			String widgName = e.getText().trim();
			boolean isStatusActive=false;
			System.out.println("Widget Name = " +widgName);
			if(widgName.equalsIgnoreCase(widgetName)){
					String before_edt = "//table/tbody/tr[";
					String after_edt = "]/td[10]/a/i";
					WebElement edt = driver().findElement(By.xpath(before_edt+i+after_edt));
					edt.click();
					waitOnElement("cssSelector",chkWidgetActive);
					WebElement active = driver().findElement(By.cssSelector(chkWidgetActive));
					boolean status = active.isSelected();
					if(!status==activateStatus){
						active.click();
					}
					clickOnButton("cssSelector",inputUpdateWidget);
					clickOnLink("linkText","Back");
					break;
					
				}
		}		
	}
	
	// Verify widget present in the Edit Widgets panel.
	
	public boolean verifyWidgetPresent(String widgetName) throws Exception{
		logInfo("inside verifyWidgetPresent() method.");
		System.out.println("inside verifyWidgetPresent() method.");
		go2EditWidgetsPage();
		
		boolean isWidgetPresent=false;	
		WebElement widgetPane = driver().findElement(By.cssSelector(widgetsPane));
		List<WebElement> allWidgets = widgetPane.findElements(By.className("primary"));
		int widgets_count = allWidgets.size();		
		System.out.println("size =" + widgets_count);
		for(WebElement x : allWidgets){
			String actualwidget = x.getText().trim();
			System.out.println(actualwidget);
			if(actualwidget.equalsIgnoreCase(widgetName)){
				System.out.println(actualwidget + " widget present in the widgets pane.");
				logInfo(actualwidget + " widget present in the widgets pane.");
				isWidgetPresent = true;
				break;
			}
		}
		
		if(isWidgetPresent==false){
			logInfo(widgetName + " widget not present in the widgets pane.");
		}
		
		return isWidgetPresent;
	}
	
	
	// Remove all widgets in home page.
	 
	public void removeAllWidgetInHomepage() throws Exception{
		logInfo("inside removeAllWidgetInHomepage() method..");
		System.out.println("inside removeAllWidgetInHomepage() method..");
		go2EditWidgetsPage();
		 
		 // Verifying the 1st container
		 
		System.out.println("1st Widget container");
		// waitOnElement("xpath","//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']");
		Thread.sleep(5000);
		WebElement widgetContainer = driver().findElement(By.xpath("//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']"));
		List<WebElement> allDivs = widgetContainer.findElements(By.cssSelector("div.widget-wrapper.edit_widget_wrapper"));
		int div_count = allDivs.size();
		System.out.println("Total divs in Widget container = " +div_count);
		 
		 if(div_count>0){
			 String before = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']/div[";
			 String after = "]/div[@class='widget-controls']";
			 
			 String beforeClose = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']/div[";
			 String afterClose = "]/div/span[1]/i";
			 
			 for(int i=div_count;i>0;i--){
				 WebElement close = driver().findElement(By.xpath(beforeClose+i+afterClose));
				 close.click();
				 Thread.sleep(3000);
			  }
		 }
		   
		// Verifying the 2nd container
			 
		 System.out.println("2nd Widget container");
		 
		 waitOnElement("xpath","//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]");
	 	 WebElement container2 = driver().findElement(By.xpath("//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]"));
		 List allContainer2Divs = container2.findElements(By.cssSelector("div.widget-wrapper.edit_widget_wrapper"));
		 int total_div2s = allContainer2Divs.size();
		 
		 System.out.println("Total divs in Div2 Widget container = " +total_div2s);
		 
		 if(total_div2s > 0){
			 String div2_before = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]/div[";
			 String div2_after = "]/div[@class='widget-controls']";
			 
			 String div2_beforeClose = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]/div[";
			 String div2_afterClose = "]/div[@class='widget-controls']/span[1]/i";
			 
			 for(int j=total_div2s;j>0;j--){
				 WebElement closeWidget = driver().findElement(By.xpath(div2_beforeClose+j+div2_afterClose));
				 closeWidget.click();
				 Thread.sleep(3000);
			 }
		 }
	 }
 	
	
	// drag and drop the widget from edit widgets panel.
	
		public void dragAndDropWidget(String widgetName) throws Exception{
			logInfo("inside dragAndDropWidget() method.");
			System.out.println("inside dragAndDropWidget() method.");		
			
			go2EditWidgetsPage();
			
			String widgName=null;
			switch(widgetName){
			
			case "About":
				widgName="//*[@id='source_pyr_community_widgets_about']";
				break;
			case "Action Items":
				widgName="//*[@id='source_pyr_core_widgets_action_items']";
				break;
			case "Add Contact":
				widgName="//*[@id='source_pyr_core_widgets_add_contact']";
				break;
			case "Business Alerts":
				widgName="//*[@id='source_pyr_community_widgets_business_alerts']";
				break;
			case "Calendar Types":
				widgName="//*[@id='source_pyr_crm_widgets_calendar_types']";
				break;
			case "Carousel Ads":
				widgName="//*[@id='source_pyr_core_widgets_carousel_ads_main']";
				break;
			case "Commission History":
				widgName="//*[@id='source_pyr_core_widgets_commission_history']";
				break;
			case "Community Stream":
				widgName="//*[@id='source_pyr_community_widgets_community_stream']";
				break;
			case "Company Ads":
				widgName="//*[@id='source_pyr_core_widgets_carousel_ads']";
				break;
			case "Company News":
				widgName="//*[@id='source_pyr_core_widgets_company_news_main']";
				break;
			case "Company Social Networks":
				widgName="//*[@id='source_pyr_community_widgets_social_networks']";
				break;
			case "Contact Groups":
				widgName="//*[@id='source_pyr_crm_widgets_contact_lists']";
				break;
			case "CRM Alerts":
				widgName="//*[@id='source_pyr_crm_widgets_crm_alerts']";
				break;
			case "Events":
				widgName="//*[@id='source_pyr_crm_widgets_events']";
				break;
			case "Following":
				widgName="//*[@id='source_pyr_community_widgets_following']";
				break;
			case "Featured Blogs":
				widgName="//*[@id='source_pyr_community_widgets_featured_blog']";
				break;
			case "Featured Photos":
				widgName="//*[@id='source_pyr_community_widgets_featured_photos']";
				break;
			case "Featured Videos":
				widgName="//*[@id='source_pyr_community_widgets_featured_videos']";
				break;
			case "Goals":
				widgName="//*[@id='source_pyr_community_widgets_goals']/span";
				break;	
			case "My Blog":
				widgName="//*[@id='source_pyr_community_widgets_blogs']";
				break;
			case "My Photos":
				widgName="//*[@id='source_pyr_community_widgets_photos']";
				break;
			case "My Posts":
				widgName="//*[@id='source_pyr_community_widgets_timeline']";
				break;
			case "My Videos":
				widgName="//*[@id='source_pyr_community_widgets_videos']";
				break;
			case "My Websites":
				widgName="//*[@id='source_pyr_pwp_widgets_my_sites']";
				break;
			case "Need Help":
				widgName="//*[@id='source_pyr_core_widgets_need_help']";
				break;
			case "Notifications":
				widgName="//*[@id='source_pyr_community_widgets_notifications']";
				break;
			case "Progress Report":
				widgName="//*[@id='source_pyr_tree_widgets_progress_report_main']";  // //*[@id='source_pyr_tree_widgets_progress_report']
				break;
			case "Report":
				widgName="//*[@id='source_pyr_tree_widgets_report_main']";
				break;
			case "Quick Links Button View":
				widgName="//*[@id='source_pyr_core_widgets_user_control_panel']";
				break;
			case "Quick Links List View":
				widgName="//*[@id='source_pyr_core_widgets_quick_links']";
				break;
			case "Recent Activity":
				widgName="//*[@id='source_pyr_community_widgets_recent_activity']";
				break;
			case "Resource Library":
				widgName="//*[@id='source_pyr_core_widgets_resource_categories']";
				break;
			case "Search Contacts":
				widgName="//*[@id='source_pyr_crm_widgets_search_contacts']";
				break;
			case "Search Events":
				widgName="//*[@id='source_pyr_crm_widgets_search_events']";
				break;
			case "Tabbed Information":
				widgName="//*[@id='source_pyr_core_widgets_tabbed_informational']";
				break;
			case "Tasks Add":
				widgName="//*[@id='source_pyr_core_widgets_tasks_add']";
				break;
			case "Tasks Completed":
				widgName="//*[@id='source_pyr_core_widgets_tasks_completed']";
				break;
			case "Tasks Completed Incomplete":
				widgName="//*[@id='source_pyr_core_widgets_tasks_completed_incomplete']";
				break;	
			case "Tasks Future":
				widgName="//*[@id='source_pyr_core_widgets_tasks_future']";
				break;	
			case "Tasks Incomplete":
				widgName="//*[@id='source_pyr_core_widgets_tasks_incomplete']";
				break;	
			case "Tasks No Due Date":
				widgName="//*[@id='source_pyr_core_widgets_tasks_no_due_date']";
				break;	
			case "Tasks Overdue":
				widgName="//*[@id='source_pyr_core_widgets_tasks_overdue']";
				break;	
			case "Tasks Today":
				widgName="//*[@id='source_pyr_core_widgets_tasks_today']";
				break;	
			case "Ticker":
				widgName="//*[@id='source_pyr_tree_widgets_ticker']";
				break;	
			case "Training":
				widgName="//*[@id='source_pyr_core_widgets_training_categories']";
				break;	
			case "Trending Photos":
				widgName="//*[@id='source_pyr_community_widgets_trending_photos']";
				break;	
			case "Trending Videos":
				widgName="//*[@id='source_pyr_community_widgets_trending_videos']";
				break;	
			case "User Activity":
				widgName="//*[@id='source_pyr_community_widgets_user_activity']";
				break;	
			}
			
			System.out.println(widgName);
			WebElement from = driver().findElement(By.xpath(widgName));
			WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));  // dragWidgetToLocation
			// tm.dragAndDropAction(from, to); 
			
			Actions builder = new Actions(driver());
			Action drag = builder.clickAndHold(from).moveToElement(to).release(to).build();
			drag.perform();	
			Thread.sleep(10000);
			
		}		 
			 
	
		// verify if the specified widget is present in the home page.
		
		 public boolean verifyWidgetsInHomepage(String widgetName) throws Exception{
			 logInfo("inside verifyWidgetsInHomepage() method..");
			 System.out.println("inside verifyWidgetsInHomepage() method.");
			 go2HomePage();
			 go2EditWidgetsPage();
			 boolean isWidgetFound = false;
			 
			 // verifying the 1st widget
			 
			 System.out.println("1st Widget container");
			 waitOnElement("xpath","//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']");
			 Thread.sleep(5000);
			 WebElement widgetContainer = driver().findElement(By.xpath("//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']"));
			 List<WebElement> allDivs = widgetContainer.findElements(By.cssSelector("div.widget-wrapper.edit_widget_wrapper"));
			 int div_count = allDivs.size();
			 System.out.println("Total divs in Widget container = " +div_count);
					 
				if(div_count>0){
						 
					String before = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']/div[";
					String after = "]/div[@class='widget-controls']";
						 
					for(int i=1;i<=div_count;i++){
						WebElement ewidget = driver().findElement(By.xpath(before+i+after));
						String actWidget = ewidget.getText().trim();
							 
						if(widgetName.equalsIgnoreCase(actWidget)){
							 isWidgetFound=true;
							 logInfo(widgetName + " widget found in home page inside 1st container");
							 break;
						}
					}
				 }  
						
				
		 // verifying 2nd container
				
				System.out.println("2nd Widget container");
				 
				waitOnElement("xpath","//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]");
				WebElement container2 = driver().findElement(By.xpath("//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]"));
				List allContainer2Divs = container2.findElements(By.cssSelector("div.widget-wrapper.edit_widget_wrapper"));
				int total_div2s = allContainer2Divs.size();
					 
				System.out.println("Total divs in Div2 Widget container = " +total_div2s);
					 
				if(total_div2s > 0){
					String div2_before = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]/div[";
					String div2_after = "]/div[@class='widget-controls']";
					 
					for(int j=1;j<=total_div2s;j++){
						WebElement ewidget2 = driver().findElement(By.xpath(div2_before+j+div2_after));
						String actWidget2 = ewidget2.getText().trim();
						if(widgetName.equalsIgnoreCase(actWidget2)){
							isWidgetFound=true;
						    logInfo(widgetName + " widget found in home page inside  2nd container");
							break;
						}
					}
				}	 
					 
				if(isWidgetFound==false){
					System.out.println(widgetName + " widget not found in home page.");
					// Assert.assertTrue(isWidgetFound, widgetName + " widget could not be found in home page.");
				}
			
				return isWidgetFound;
		 }
				
			
	
		// Remove the specified widget from the home page.
		 
		public void removeWidgetInHomepage(String widgetName) throws Exception{
			logInfo("inside removeWidgetInHomepage() method..");
			System.out.println("inside removeWidgetInHomepage() method..");
			go2EditWidgetsPage();
					 
			// boolean isWidgetFound=false;
					 
			// Verifying the 1st container
					 
			System.out.println("1st Widget container");
			waitOnElement("xpath","//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']");
			Thread.sleep(5000);
			WebElement widgetContainer = driver().findElement(By.xpath("//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']"));
			List<WebElement> allDivs = widgetContainer.findElements(By.cssSelector("div.widget-wrapper.edit_widget_wrapper"));
			int div_count = allDivs.size();
			System.out.println("Total divs in Widget container = " +div_count);
					 
			if(div_count>0){
						 
				String before = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']/div[";
				String after = "]/div[@class='widget-controls']";
						 
				String beforeClose = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']/div[";
				String afterClose = "]/div/span[1]/i";
						 
				for(int i=1;i<=div_count;i++){
					WebElement ewidget = driver().findElement(By.xpath(before+i+after));
					String actWidget = ewidget.getText().trim();
							 
					if(widgetName.equalsIgnoreCase(actWidget)){
						// isWidgetFound=true;
						 WebElement close = driver().findElement(By.xpath(beforeClose+i+afterClose));
						 close.click();
						 Thread.sleep(3000);
						 break;
					}
				}
			 }  
						
		
			// verifying 2nd container
				
			System.out.println("2nd Widget container");
					 
			waitOnElement("xpath","//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]");
			WebElement container2 = driver().findElement(By.xpath("//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]"));
			List allContainer2Divs = container2.findElements(By.cssSelector("div.widget-wrapper.edit_widget_wrapper"));
			int total_div2s = allContainer2Divs.size();
					 
			System.out.println("Total divs in Div2 Widget container = " +total_div2s);
					 
			if(total_div2s > 0){
				String div2_before = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]/div[";
				String div2_after = "]/div[@class='widget-controls']";
						 
				String div2_beforeClose = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]/div[";
				String div2_afterClose = "]/div[@class='widget-controls']/span[1]/i";
						 
				for(int j=1;j<=total_div2s;j++){
					WebElement ewidget2 = driver().findElement(By.xpath(div2_before+j+div2_after));
					String actWidget2 = ewidget2.getText().trim();
					if(widgetName.equalsIgnoreCase(actWidget2)){
						// isWidgetFound=true;
						WebElement closeWidget = driver().findElement(By.xpath(div2_beforeClose+j+div2_afterClose));
						closeWidget.click();
						Thread.sleep(3000);
						break;
					}
				}
			}	 
					 
			/*	if(isWidgetFound=false){
					System.out.println(widgetName + " widget not found in home page.");
					// Assert.assertTrue(isWidgetFound, widgetName + " widget could not be found in home page.");
				}*/
		}
					 
		 
		
		// Verify Problems Rendering Erros in the Widgets
		
		 public boolean verifyWidgetForProblemsRendering(String widgetName) throws Exception{
			 logInfo("inside verifyWidgetForProblemsRendering() method..");
			 System.out.println("inside verifyWidgetForProblemsRendering() method.");
			 // go2HomePage();
			 go2EditWidgetsPage();
			 boolean isWidgetFound = false;
			 boolean isWidgetErrorFound = false;
			 
			 // verifying the 1st widget
			 
			 System.out.println("1st Widget container");
			 waitOnElement("xpath","//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']");
			 Thread.sleep(5000);
			 WebElement widgetContainer = driver().findElement(By.xpath("//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']"));
			 List<WebElement> allDivs = widgetContainer.findElements(By.cssSelector("div.widget-wrapper.edit_widget_wrapper"));
			 int div_count = allDivs.size();
			 System.out.println("Total divs in Widget container = " +div_count);
					 
				if(div_count>0){
						 
					String before = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']/div[";
					String after = "]/div[@class='widget-controls']";
						 
					String before_paneltext = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[1]/div[@id='main-content']/div[";
					String after_paneltext = "]/div[2]/div/div[@class='panel-body ']";
					                          
					
					for(int i=1;i<=div_count;i++){
						WebElement ewidget = driver().findElement(By.xpath(before+i+after));
						String actWidget = ewidget.getText().trim();
							 
						if(widgetName.equalsIgnoreCase(actWidget)){
							 isWidgetFound=true;
							 WebElement etext = driver().findElement(By.xpath(before_paneltext+i+after_paneltext));
							 String panelText = etext.getText().trim();
							 if(panelText.contains("Problems rendering widget")){
								 isWidgetErrorFound=true;
							 	 Assert.assertFalse(isWidgetErrorFound, widgetName + " widget has problems rendering");
							 }
							 break;
						}
					}
				 }  
				
				// verifying 2nd container
				
				System.out.println("2nd Widget container");
				 
				waitOnElement("xpath","//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]");
				WebElement container2 = driver().findElement(By.xpath("//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]"));
				List allContainer2Divs = container2.findElements(By.cssSelector("div.widget-wrapper.edit_widget_wrapper"));
				int total_div2s = allContainer2Divs.size();
					 
				System.out.println("Total divs in Div2 Widget container = " +total_div2s);
					 
				if(total_div2s > 0){
					String div2_before = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]/div[";
					String div2_after = "]/div[@class='widget-controls']";
					
					// String before_paneltext2 = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]/div[";
					// String after_paneltext2 = "]/div[2]/div[1]/div[@class='panel-body ']";
					                              
					String before_paneltext2 = "//*[@id='widget_manager_home']/div[2]/div/div[@class='row']/div[2]/div[";
					String after_paneltext2 = "]/div[2]/div/div[2]";    // "]/div[2]/div/div[@class='panel-body ']";  // "]/div[1]/div/div[1]/div[2]";    
												 						// "]/div[2]/div/div[@class='panel panel-widget ']"; 
					for(int j=1;j<=total_div2s;j++){
						WebElement ewidget2 = driver().findElement(By.xpath(div2_before+j+div2_after));
						String actWidget2 = ewidget2.getText().trim();
						if(widgetName.equalsIgnoreCase(actWidget2)){
							isWidgetFound=true;
						    logInfo(widgetName + " widget found in home page inside  2nd container");
						    WebElement etext = driver().findElement(By.xpath(before_paneltext2+j+after_paneltext2));
							 String panelText = etext.getText().trim();
							 if(panelText.contains("Problems rendering widget")){
								 isWidgetErrorFound=true;
							 	 Assert.assertFalse(isWidgetErrorFound, widgetName + " widget has problems rendering");
							 }
							break;
						}
					}
				}
				return isWidgetErrorFound;	 
		 }			
		
		 
		 
		 // Verify content in Business Alerts widget.
		 
		 public boolean verifyTaskInBusinessAlerts(String taskMessage){
			 logInfo("Inside verifyTaskInBusinessAlerts() method");
			 boolean isTaskMessageFound = false;
			 
			 String before = "//*[@id='business_notifications']/li[";
			 String after = "]/div/div[2]/div[1]/a/div";
			 
			 List<WebElement> el = driver().findElements(By.xpath(businessAlertsList));
			 for(int i=1;i<=el.size();i++){
				 WebElement alertMessage = driver().findElement(By.xpath(before+i+after));
				 if(alertMessage.getText().trim().equalsIgnoreCase(taskMessage.trim())){
					 isTaskMessageFound = true;
					 break;
				 }
			 }
			 return isTaskMessageFound;
		 }
		 
		 
		 // Verify content in About Widget 
		 
		 public boolean verifyAboutWidgetContent() throws Exception, Exception{
			 logInfo("inside verifyAboutWidgetContent() method.");
			 
			 waitOnElement("xpath","//*[@id='about-widget']");
			// driver().navigate().to(appUrl + "pyr_core/account#my-account");
			//  driver().navigate().to(appUrl + "account#my-account");
			 driver().navigate().to(appUrl + "pyr_core/account#account-info");
			 
			 WebElement eGetProfileMail = driver().findElement(By.cssSelector("#pyr_core_profile_email_text"));
			 String profileMail = eGetProfileMail.getText().trim();
			 System.out.println("profileMail = " +profileMail);
			 
			 driver().navigate().to(appUrl);
			 WebElement eWidgetEmail = driver().findElement(By.xpath("//*[@id='about-widget']/div[2]/div[1]/div[3]/a"));
			 String widgetEmail = eWidgetEmail.getText().trim();
			 System.out.println("widgetEmail = " +widgetEmail);
			 
			 boolean isEMailMatchFound = false;
			 
			 if(profileMail.contains(widgetEmail)){
				 isEMailMatchFound = true;
				 logInfo(widgetEmail + " email match found.");
			 }
			 
			return isEMailMatchFound; 
		 }
	
	
	 // Verify content in Tabbed Information Widget.
		 
		 public boolean verifyNewsPresentInWidget(String news) throws Exception, Exception{
			 logInfo("inside verifyNewsPresentInWidget() method.");
			 
			 waitOnElement("xpath","//*[@id='tabbed-informational']");
			 boolean isNewsFound = false;
			 
			 WebElement panelWidgetsNews = driver().findElement(By.xpath("//*[@id='tabbed-informational']/div/div[1]/div[@id='news']/div[@class='media-group']"));
			 List allNews = panelWidgetsNews.findElements(By.cssSelector(".media"));
			 int total_news = allNews.size();
			 
			
			 String before_news = "//*[@id='tabbed-informational']/div/div[1]/div[@id='news']/div[@class='media-group']/div[";
			 String after_news = "]/div[@class='media-body']/h4/a";
			 
			 if(total_news>0){
				 for(int i=1;i<=total_news;i++){
					 WebElement eNews = driver().findElement(By.xpath(before_news+i+after_news));
					 String actnews = eNews.getText().trim();
					 if(actnews.equalsIgnoreCase(news)){
						 isNewsFound = true;
						 logInfo(news + " news match found in Tabbed Information widget.");
						 break;
					 }
				 }
			 }
			 
			return isNewsFound;
		 }
		 
		 
 		 //  Add Contacts from the Widget
			
		 public void addContactFromWidget(String fName, String lName) throws Exception, Exception{
			 logInfo("Inside AddContactFromWidget() method.");
			 
			 waitOnElement("xpath","//div[@id='add_contact']");
			 inputTextClear("xpath", "//div[@id='add_contact']/div[2]/form/div[1]/input");
			 inputText("xpath", "//div[@id='add_contact']/div[2]/form/div[1]/input", fName);
			 inputTextClear("xpath", "//div[@id='add_contact']/div[2]/form/div[2]/input");
			 inputText("xpath", "//div[@id='add_contact']/div[2]/form/div[2]/input", lName);
			 clickOnElement("xpath","//div[@id='add_contact']/div[2]/form/input[@value='Add Contact']");
			 
			// ConfirmationMessage("Contact is created");
		 }
	
	
		
		 
		 // verify content in Contact Groups widget.
		 
		 public boolean verifyContactGroupsInWidget(String groupName_text){
			 logInfo("Inside verifyContactGroupsInWidget() method.");
			 boolean isGrpFound = false;
			 
			 String before = "//div[@id='contact_lists']/div[2]/table/tbody/tr[";
			 String after = "]/td[2]/div/a";
			 
			 WebElement grpRows = driver().findElement(By.xpath("//div[@id='contact_lists']/div[2]/table/tbody"));
			 List allRows = grpRows.findElements(By.tagName("tr"));
			 System.out.println("allRows =" + allRows.size());
			 
			 if(allRows.size() >0){
			  for(int i=1;i<=allRows.size();i++){
				 WebElement grp = driver().findElement(By.xpath(before+i+after));
				 String actGrp = grp.getText().trim();
				 System.out.println("actGrp ="+ actGrp);
				 if(actGrp.contains(groupName_text.trim())){
					 isGrpFound = true;
					 System.out.println("isGrpFound =" +isGrpFound);
					 break;
				 }
			   }
			 }
			 return isGrpFound;
		 }
		 
	
		 // Verify contact in Events widget.
		 
		 
		 public boolean verifyEventInEventsWidget(String eventName){
			 logInfo("Inside verifyEventInEventsWidget() method.");
			 boolean isEventFound = false;
			 
			 String before = "//*[contains(@id,'event')][";
			 String after = "]/td/a";
			 
			 List<WebElement> el = driver().findElements(By.xpath(listEvents));
			 for(int i=1;i<=el.size();i++){
				 WebElement event = driver().findElement(By.xpath(before+i+after));
				 if(event.getText().trim().equalsIgnoreCase(eventName.trim())){
					 isEventFound = true;
					 break;
				 }
			 }
			 return isEventFound;
		 }
	
	
		 // Verify content in Community Stream widget.
		 
		 public boolean verifyCommunityStreamWidget(String postName) throws Exception, Exception{
			 logInfo("inside verifyCommunityStreamWidget() method.");
			 
			 waitOnElement("xpath","//*[@id='widget-recent-activity']");
			 boolean isPostFoundInWidget = false;
			 
			 WebElement panelCommunityStream = driver().findElement(By.xpath("//*[@id='widget-recent-activity']/div[2]/div[@class='activities-stream row']"));
			 List allPosts = panelCommunityStream.findElements(By.cssSelector(".pyr_community_activity.media"));
			 int all_posts = allPosts.size();
			System.out.println("all_posts =" +all_posts);
			 
			 String before_post = "//*[@id='widget-recent-activity']/div[2]/div[@class='activities-stream row']/div[";
			 String after_post = "]/div[@class='media-body']/div[@class='activity-content']/a";
					 
			 if(all_posts>0){
				 for(int i=1;i<=all_posts;i++){
					 WebElement epost = driver().findElement(By.xpath(before_post+i+after_post));
					 String actPost = epost.getText().trim();
					 if(actPost.equalsIgnoreCase(postName)){
						 isPostFoundInWidget = true;
						 logInfo(postName + " post match found inside the community stream widget.");
						 break;
					 }
				 }
			 }
			 
			return isPostFoundInWidget;
		 } 
	
	
		 // Verify content in CRM Alert widget.
		 
		 public boolean verifyTaskInCRMAlerts(String taskMessage){
			 logInfo("Inside verifyTaskInCRMAlerts() method");
			 boolean isTaskMessageFound = false;
			 
			 String before = "//*[@id='crm_alerts']/div[2]/ul/li[";
			 String after = "]/div/div[2]/div[1]/a/div";
			 
			 List<WebElement> el = driver().findElements(By.xpath(crmAlertsList));
			 for(int i=1;i<=el.size();i++){
				 WebElement alertMessage = driver().findElement(By.xpath(before+i+after));
				 if(alertMessage.getText().trim().equalsIgnoreCase(taskMessage.trim())){
					 isTaskMessageFound = true;
					 break;
				 }
			 }
			 return isTaskMessageFound;
		 }
	
		 
		 // Add Event from Events widget.
		 
		 public void addEventFromWidget(String eventName) throws Exception{
			 logInfo("Inside addEventFromWidget() method.");
			 // waitOnElement("cssSelector","#agenda-widget > .panel-body > .pull-right > .btn-group > a");
			 clickOnLink("cssSelector","#agenda-widget > .panel-body > .pull-right > .btn-group > a");
			// waitOnElement("xpath",inputEventName);
			 Thread.sleep(5000);
			 inputText("xpath",inputEventName,eventName);
			 clickOnButton("xpath",btnEventSave);
			 driver().navigate().refresh();
		 }
		 
		 
		 // Verify content in Company News widget.
		 
		 public boolean verifyNewsPresentOnWidget(String title){
			 logInfo("Inside verifyNewsPresentOnWidget() method");
			 boolean isNewsPresent = false;
			 
			 String before = "//*[@id='company_news']/div[2]/div[1]/div[";
			 String after = "]/div/h4/a";
			 
			 List<WebElement> el = driver().findElements(By.xpath(newsItems));
			 for(int i=1;i<=el.size();i++){
				 WebElement news = driver().findElement(By.xpath(before+i+after));
				 if(news.getText().trim().equalsIgnoreCase(title)){
					 isNewsPresent = true;
					 break;
				 }
			 }
			 return isNewsPresent;
		 }
		 
		 
		// Verify content in Company News when click on View More.
		 
		 public boolean verifyNewsInViewMore(String title){
			 logInfo("Inside verifyNewsInViewMore() method");
			 boolean isNewsPresentViewMore = false;
			 
			 String before = "//*[contains(@id,'news-')][";
			 String after = "]/div[@class='media-body']/h4/a";
			 
			 List<WebElement> el = driver().findElements(By.xpath(newsList));
			 for(int i=1;i<=el.size();i++){
				 WebElement news = driver().findElement(By.xpath(before+i+after));
				 if(news.getText().trim().equalsIgnoreCase(title)){
					 isNewsPresentViewMore = true;
					 break;
				 }
			 }
			 return isNewsPresentViewMore;
		 }
		 
		 
		// Verify content in Carousel Ads widget.
		 
		 public boolean verifyAdsInWidget(String adAlt){
			 logInfo("Inside verifyAdsInWidget() method.");
			 
			 WebElement adsContainer = driver().findElement(By.xpath("//div[@class='carousel-inner']"));
			 List allAds = adsContainer.findElements(By.tagName("div"));
			 int total_ads = allAds.size();
			 System.out.println("Total Ads = " +total_ads);
			 
			 String before_adName = "//div[@class='carousel-inner']/div[";
			 String after_adName = "]/a/img";
			 
			 boolean isImgAltFound = false;
			 
			 if(total_ads >0){
				 for(int i=1;i<=total_ads;i++){
					WebElement eAdName = driver().findElement(By.xpath(before_adName+i+after_adName));
					String alt = eAdName.getAttribute("alt");
					System.out.println("alt =" +alt);
					if(alt.equalsIgnoreCase(adAlt)){
						logInfo(adAlt + "image match found in Carosul Ads widget.");
						isImgAltFound = true;
					}
				 }
			 }
			return isImgAltFound;
		 }
		 
		 
		 
		 // Add goals for widgets
		 
		 public void addNewGoal(String goalName) throws Exception{
			 logInfo("Inside addNewGoal() method.");
			 
			 driver().navigate().to(appUrl + "community/profile");
			 clickOnLink("linkText","Goals");
			 clickOnLink("linkText","Add Goal");
			 
			 String futuredt = TestBase.getDate(+10, "MM/dd/yyyy");
			 System.out.println("futuredt =" +futuredt);
			 
			 inputTextClear("cssSelector","#pyr_community_goal_title");
			 inputText("cssSelector","#pyr_community_goal_title", goalName);
			 
			/* clickOnElement("cssSelector","#pyr_community_goal_image");
			 Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\coffeImage.exe");
			 Thread.sleep(5000);*/
			 
			 inputText("cssSelector","#pyr_community_goal_description", goalName+" desc");
			 // selectCalendarDate("xpath","//*[@id='new_pyr_community_goal']/div[4]/div[2]/span/button",futuredt);
			 
			 inputTextClear("xpath","//*[@id='pyr_community_goal_complete_by']");
			 inputText("xpath","//*[@id='pyr_community_goal_complete_by']",futuredt);
			 
			 
			 selectFromDropdown("cssSelector","#pyr_community_goal_visibility_setting_attributes_visibility_id","byVisibleText","COMMUNITY");
			 
			 clickOnButton("cssSelector", saveGoal);
			 
			// ConfirmationMessage("Create Goal");
			 
		 }
		 
		
		// Verify content in Goals widget.
		 
		 public boolean verifyGoalsInWidget(String goalName){
			 logInfo("Inside verifyGoalsInWidget() method.");
			 
			 WebElement goalsContainer = driver().findElement(By.xpath("//*[@id='my-goals']/div[2]"));
			 List allGoals = goalsContainer.findElements(By.cssSelector(".goal.media"));
			 int total_goals = allGoals.size();
			 System.out.println("Total Goals = " +total_goals);
			 
			 String before_goalName = "//*[@id='my-goals']/div[2]/div[";
			 String after_goalName = "]/div[2]/h4/a";
			 
			 boolean isGoalFound = false;
			 
			 if(total_goals >0){
				 for(int i=1;i<=total_goals;i++){
					WebElement eGoalName = driver().findElement(By.xpath(before_goalName+i+after_goalName));
					String actgoalName = eGoalName.getText().trim();
					System.out.println("goal name =" +actgoalName);
					if(actgoalName.equalsIgnoreCase(goalName)){
						logInfo(goalName + " goal match found in Goals widget.");
						isGoalFound = true;
					}
				 }
			 }
			return isGoalFound;
		 }
		 
		 
		 
	// Verify content in Notifications widget.
		 
		 public boolean verifyNotificationsInWidget(String notificationName){
			 logInfo("Inside verifyNotificationsInWidget() method.");
			 
			 WebElement notificationsContainer = driver().findElement(By.xpath("//*[@id='widget_notifications']/div[2]/ul"));
			 List allNotifications = notificationsContainer.findElements(By.tagName("li"));
			 int total_notifications = allNotifications.size();
			 System.out.println("Total Notifications = " +total_notifications);
			 
			 String before_notifName = "//*[@id='widget_notifications']/div[2]/ul/li[";
			 String after_notifName = "]/a";
			 
			 boolean isNotificationFound = false;
			 
			 if(total_notifications >0){
				 for(int i=1;i<=total_notifications;i++){
					WebElement eNotificationName = driver().findElement(By.xpath(before_notifName+i+after_notifName));
					String actnotificationName = eNotificationName.getText().trim();
					System.out.println("notification name =" +actnotificationName);
					if(actnotificationName.contains(notificationName)){
						logInfo(notificationName + " match found in Notifications widget.");
						isNotificationFound = true;
					}
				 }
			 }
			return isNotificationFound;
		 }
		 
		 
		 
		// Verify content in Calendar Types widget.
		 
		public void verifyCalendarTypesInWidget() throws Exception{
			logInfo("Inside verifyCalendarTypesInWidget() method.");
					 
			WebElement notificationsContainer = driver().findElement(By.xpath("//*[@id='calendar_types']/div[2]"));
			List<WebElement> allCalendartypes = notificationsContainer.findElements(By.cssSelector("div > a"));
			
			for(WebElement x : allCalendartypes){
				String actCaltype = x.getText().trim();
				
				switch(actCaltype) {
				case "All":
					clickOnLink("linkText","All");
					break;
				case "Personal":
					clickOnLink("linkText","Personal");
					break;
				case "Shared":
					clickOnLink("linkText","Shared");
					break;
				default :
					System.out.println("Invalid calendar type found.");
					break;
				
				}
			}
			
		}	 
		
		
		
		// verify content in My Photos widget.
		
		
		 public boolean verifyMyPhotosInWidget(String photoName){
			 logInfo("Inside verifyMyPhotosInWidget() method.");
			 
			 WebElement myPhotosContainer = driver().findElement(By.xpath("//*[@id='photos']/div[2]"));
			 List allPhotos = myPhotosContainer.findElements(By.cssSelector(".photo-image"));
			 int total_photos = allPhotos.size();
			 System.out.println("Total Photos = " +total_photos);
			 
			 String before_photoName = "//*[@id='photos']/div[2]/div[";
			 String after_photoName = "]/div[@class='photo-block']/div[1]";
			 
			 boolean isPhotoFound = false;
			 
			 if(total_photos >0){
				 for(int i=1;i<=total_photos-1;i++){
					WebElement ePhotoName = driver().findElement(By.xpath(before_photoName+i+after_photoName));
					String actphotoName = ePhotoName.getAttribute("title");
					System.out.println("Photo name =" +actphotoName);
					if(actphotoName.equalsIgnoreCase(photoName)){
						logInfo(photoName + " photo match found in My Photos widget.");
						isPhotoFound = true;
						break;
					}
				 }
			 }
			return isPhotoFound;
		 }
		
		 
		 //*************************Avon Methods **********************************//
		 
		    public void go2EditWidgetsInAvon() throws Exception{
		  		logInfo("inside go2EditWidgetsPage() method.");		  		
		  		driver().navigate().to(appUrl);
		  		clickOnLink("linkText","Edit Panels");
		  	}
		  //"Drag Progress Report from Edit Widgets Link ");
	  		public void dragProgressReport() throws Exception{  		
	  		logInfo("Entered into dragProgressReport() method");  		
	  			List <WebElement> ne = driver().findElements(By.xpath(progReportWidget));
	  			System.out.println(ne.size());
	  			for (WebElement ns : ne){			
	  				System.out.println(ns.getText());			
	  				if (ns.getText().contains("Progress Report")){				
	  					Thread.sleep(2000);
	  					WebElement from = driver().findElement(By.xpath(progReportWidget));	  					
	  					WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));
	  					dragAndDropAction(from, to);
	  					Thread.sleep(5000);
	  					
	  					
	  					break;
	  				}		
	  			}
	  		}
	  		
	  		
	  	//"Drag Business Dashboard from Edit Widgets Link ");
	  		public void dragPBusinessDashboardReport() throws Exception{  		
	  		logInfo("Entered into dragPBusinessDashboardReport() method");  		
	  						
	  					Thread.sleep(2000);
	  					WebElement from = driver().findElement(By.xpath(businessDashWidget));	  					
	  					WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));
	  					dragAndDropAction(from, to);
	  					Thread.sleep(5000);  					
	  					
	  			
	  		}
	  		
	  	//"Drag Business Dashboard from Edit Widgets Link ");
	  		public void dragPBusinessDashboardReport_old() throws Exception{  		
	  		logInfo("Entered into dragPBusinessDashboardReport() method");  		
	  			List <WebElement> ne = driver().findElements(By.xpath(businessDashWidget));
	  			System.out.println(ne.size());
	  			for (WebElement ns : ne){			
	  				System.out.println(ns.getText());			
	  				if (ns.getText().contains("Business Dashboard")){				
	  					Thread.sleep(2000);
	  					WebElement from = driver().findElement(By.xpath(businessDashWidget));	  					
	  					WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));
	  					dragAndDropAction(from, to);
	  					Thread.sleep(5000);  					
	  					break;
	  				}		
	  			}
	  		}
	  	
	  		public void dragAndDropAction(WebElement from, WebElement to) throws Exception{		
	  			logInfo("Dragging the widgets");
	  			Thread.sleep(5000);
	  			Actions builder = new Actions(driver());
	  			Action drag = builder.clickAndHold(from).
	  					moveToElement(to).
	  					release(to).
	  					build();
	  			drag.perform();			
	  		}
	  		
	  		
	  		public void deleteAllWidgets() throws Exception, InterruptedException{
	  				logger.info("Delete All Widgets on the Tasks Page");
	  			
	  			try{		
	  			List<WebElement> panelHead = driver().findElements(By.cssSelector(widgetsTitle));
	  			System.out.println("Total Number of Widgets are :  "+ panelHead.size());
	  			/*for (WebElement title :panelHead)	*/
	  			
	  			for(int i =1; i<= panelHead.size(); i++ ){
	  				
	  				go2EditWidgetsInAvon();		
	  				clickOnElement("cssSelector", deleteWidgets);				
	  				collapseEditWidget();	
	  					
	  			}}catch (Exception e){			
	  				logger.error("Failed!! Unable to delete widget.");
	  			}			
	  					
	  		}	
	  		
	  		 public void collapseEditWidget() throws InterruptedException, Exception{		
	  	    	clickOnElement("cssSelector",closeWidgetInHomeBranch);
	  		  		}
	  		 

	  		// Verify widget present in the Edit Widgets panel.
	  		
	  		public boolean verifyWidgetPresentInAvon(String widgetName) throws Exception{
	  			logInfo("Entered into  verifyWidgetPresentInAvon() method.");
	  			System.out.println("inside verifyWidgetPresentInAvon() method.");  			
	  			
	  			boolean isWidgetPresent=false;		  			
	  			List<WebElement> allWidgets = driver().findElements(By.cssSelector(widTitles));
	  			int widgets_count = allWidgets.size();		
	  			System.out.println("size =" + widgets_count);
	  			for(WebElement x : allWidgets){
	  				String actualwidget = x.getText().trim();
	  				System.out.println(actualwidget);
	  				if(actualwidget.equalsIgnoreCase(widgetName)){
	  					System.out.println(actualwidget + " widget present in the widgets pane.");
	  					logInfo(actualwidget + " widget present in the widgets pane.");
	  					isWidgetPresent = true;
	  					break;
	  				}
	  			}
	  			
	  			if(isWidgetPresent==false){
	  				logInfo(widgetName + " widget not present in the widgets pane.");
	  			}
	  			
	  			return isWidgetPresent;
	  		}	
	  		
	  		
	  		 //  Search Contact from the Widget.
			
			 public boolean searchContactFromWidget(String fName, String lName) throws Exception, Exception{
				 logInfo("Inside searchContactFromWidget() method.");
				 
				 waitOnElement("xpath","//div[@id='search_contacts']");
				 inputTextClear("xpath", "//div[@id='search_contacts']/div[2]/div/div[1]/input[@id='contact_query']");
				 inputText("xpath", "//div[@id='search_contacts']/div[2]/div/div[1]/input[@id='contact_query']", fName+" "+lName);
			//	 clickOnElement("xpath","//div[@id='search_contacts']/div[2]/div/div[2]/button[@id='search-contacts-widget-button']/i");
				 
				 String cName = fName+" "+lName;
				 boolean isContactMatchFound = false;
				 
				 WebElement eautoSearch = driver().findElement(By.cssSelector(".ui-autocomplete.ui-front.ui-menu.ui-widget.ui-widget-content"));
				 List<WebElement> allItems = eautoSearch.findElements(By.cssSelector(".ui-menu-item"));
				 if(allItems.size() >0){
					for(WebElement e : allItems){
						String x = e.getText().trim();
						System.out.println("x =" +x);
						if(x.equalsIgnoreCase(cName)){
							isContactMatchFound=true;
							logInfo(cName + " contact found in Search Contact widget.");
							e.click();
							break;
						}
					}
				 }
				 
				 if(isContactMatchFound==false){
					 logInfo(cName + " contact could not be found in Search Contact widget.");
					 Assert.assertTrue(isContactMatchFound, cName + " contact could not be found in Search Contact widget.");
				 }
				return isContactMatchFound;
			 }	
			 
			 
			//Ganga
				public void verifyContactsLinkInQuickLinks() throws Exception, Exception {
					 logInfo("Inside verifyContactsLinkInQuickLinks() method.");
					 String contactCount =	driver().findElement(By.xpath(avcontactCount)).getText();
					 System.out.println(contactCount);
					 waitOnElement("xpath",avcontactIcon);
					 clickOnElement("xpath",avcontactIcon);
					 waitOnElement("xpath",avContacts);
					 String expectedContactCount =  driver().findElement(By.xpath(avContacts)).getText();
					 System.out.println(expectedContactCount);
					 if(contactCount.contains(expectedContactCount)) {
						 
						 logInfo(contactCount +" matched with contacts count in the contacts page");
						 driver().navigate().to(appUrl);
					 }
					 else {
						 logInfo(contactCount +" not matched with contacts count in the contacts page"); 
						 driver().navigate().to(appUrl);
					 }
					 
				}		 
			
				//Ganga
				public void verifyCalendarLinkInQuickLinks() throws Exception, Exception {
					 logInfo("Inside verifyContactsLinkInQuickLinks() method.");
					 
					 waitOnElement("xpath",avcalendarIcon);
					 clickOnElement("xpath",avcalendarIcon);
					 waitOnElement("xpath",avCalendartitle);
					 System.out.println(driver().getTitle());
					Assert.assertEquals(driver().getTitle(), "VIBE - Calendar");
					driver().navigate().to(appUrl);
					 
				}		 
			
				

				//Ganga
				public void verifyTasksLinkInQuickLinks() throws Exception, Exception {
					 logInfo("Inside verifyTasksLinkInQuickLinks() method.");
					 
					 waitOnElement("xpath",avtasksIcon);
					 clickOnElement("xpath",avtasksIcon);
				//	 waitOnElement("xpath",avCalendartitle);
					 System.out.println(driver().getTitle());
					 Assert.assertEquals(driver().getTitle(), "VIBE - Tasks");
					 driver().navigate().to(appUrl);
			 
					 
				}	
		 
		
}
