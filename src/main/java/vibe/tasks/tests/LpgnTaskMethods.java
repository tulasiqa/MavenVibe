package vibe.tasks.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import vibe.contacts.tests.BusinessContactsMethods;
import vibe.contacts.tests.BusinessContacts_Tests;
import vibe.reports.tests.ReportsMethods;
import vibe.widgets.tests.WidgetsMethods;

import com.thoughtworks.selenium.webdriven.commands.GetText;

import common.TestBase;
import common.readProp;

public class LpgnTaskMethods extends TestBase{
	
	WidgetsMethods widgets= new WidgetsMethods();
	ReportsMethods rep = new ReportsMethods();
	readProp prop = new readProp();
	String widgName;
	WebElement to;
	
	
	public LpgnTaskMethods verifyandDragWidget(String widgetName) throws Exception{	
		logInfo("inside verifyandDragWidget() method.");
		widgets.verifyWidgetPresent(widgetName);
		widgets.dragAndDropWidget(widgetName);
		return this;
	}
	
	public void selectEditWidgets() throws Exception{
		logInfo("inside selectEditWidgets() method");		
		clickOnLink("linkText", "Edit Widgets");
		/*clickOnLink("cssSelector", editWidgetsLink);*/
	}
	
	public void selectEditWidgetsFromHome() throws Exception{
		//	doubleClickOnLinkAndText("cssSelector", editWidgetsLink);
		waitOnElement("cssSelector", editWidgetsLinkHome);
		clickOnElement("cssSelector", editWidgetsLinkHome);
		
	}
		
	public void collapseEditWidget() throws InterruptedException, Exception{
		logInfo("inside collapseEditWidget() method.");
		clickOnElement("xpath",closeWidget);			
	}
	
	public void getListOfWidgets() throws Exception{
		//	doubleClickOnLinkAndText("cssSelector", editWidgetsLink);
		clickOnElement("cssSelector", editWidgetsLink);
		List <WebElement> widgetslist = driver().findElements(By.xpath(widgetList));
            for (WebElement widget : widgetslist ){
        	 System.out.println(widget.getText());
        
         }	   
	}
		
	public void AddEvent() throws InterruptedException, Exception{
		PropertyConfigurator.configure("Log4j.properties");	
		logger.info("****Add new event*******");
		clickOnElement("xpath", eventsWidget);
		getText("xpath", eventsWidget,"Event Name");
		dragAndDropEvents();
		clickOnButton("cssSelector", addEvent);
	}
	
	public void dragAndDropAction(WebElement from, WebElement to) throws Exception{		
		logInfo("Dragging the widgets");		
		Actions builder = new Actions(driver());
		Action drag = builder.clickAndHold(from).
				moveToElement(to).
				release(to).
				build();
		drag.perform();			
	}
	
	
	public LpgnTaskMethods dragAndDropEvents() throws InterruptedException{
		PropertyConfigurator.configure("Log4j.properties");	
		logger.info("Drag and Add EVENT from Edit Widgets Link ");		
		try{			
			WebElement from = driver().findElement(By.xpath(eventsWidget));
			WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));
			dragAndDropAction(from, to);		
		}catch (Exception e){
			System.err.println("Failed !! Not able to drag the Events");
		}
		return this;
		
		
	}
	
	public void getAllWidgets(){
		try{
		List <WebElement > frame = driver().findElements(By.xpath(widgetsAreEnabled));
		System.out.println("Items in frame :"+frame.size());
		for (WebElement frames : frame){
			System.out.println("items are "+ frames.getText());
		}
		
	}catch (Exception e){
		System.err.println("Failed !! Not able to retrive ALL Widgets");
	}}
	
		
	public void addTaskFromGoal(String task, int noOfDaysToCurrent) throws Exception{
		PropertyConfigurator.configure("Log4j.properties");	
		logger.info("Adding Task with either future or Past Date.");
		try{	
		String futDate = getDate(noOfDaysToCurrent, "MM/dd/yyyy")	;
		waitOnElement("xpath", taskDescriptionInput);
		inputText("xpath", taskDescriptionInput, task);		
		inputText("cssSelector", dateField,futDate );
		waitOnElement("cssSelector", saveTask);		
		clickOnElement("cssSelector", saveTask);	
		confirmationMessage("Your task has been created.");
		//confirmationMessage("Task has been created successfully.");		
		}catch (Exception e){
			logger.error("Failed!! not able to add Task");			
		}		
		logger.info("Successfull!! Added task and also captured confirmation message.");
		
	}
	
	public void NoDueDateTask(String task) throws Exception{
		PropertyConfigurator.configure("Log4j.properties");	
		logger.info("Adding Task without Date (No due Date) ");
		try{		
		inputText("xpath", taskDescriptionInput, task);			
		clickOnElement("cssSelector", addTask1);
			
		}catch (Exception e){
			logger.error("Failed!! not able to add Task");				
		}
		confirmationMessage("Your task has been created.");
		//confirmationMessage("Task has been created successfully.");			
		logger.info("Successfull!! Added task and also captured confirmation message.");
		
	}
	
	public void AddTaskwithExpectedDate(String task, int noOfDaysToCurrent) throws Exception{
		PropertyConfigurator.configure("Log4j.properties");	
		logger.info("Adding Task with either future or Past Date  - "+task );
		try{	
		String futDate = getDate(noOfDaysToCurrent, "MM/dd/yyyy")	;
		waitOnElement("xpath", taskDescriptionInput);
		inputText("xpath", taskDescriptionInput, task);		
		inputText("cssSelector", dateField,futDate );
		/*selectPriority();*/
		clickOnElement("cssSelector", addTask1);
		confirmationMessage("Your task has been created.");
		//confirmationMessage("Task has been created successfully."); 		
		}catch (Exception e){
			logger.error("Failed!! not able to add Task");			
		}		
		logger.info("Successfull!! Added task and also captured confirmation message.");
		
	}	
	
	
	public void AddTaskWithoutDescription() throws InterruptedException, Exception{	
		logger.info("Adding Task without Task description and capture the alert message ");
		try{
		doubleClick("cssSelector", datePicker1);
		clickOnElement("cssSelector", addTask1);	
		confirmationMessage(confMsgTaskDescription);	
		}catch (NoSuchElementException nse){
			logger.error("Not able to select locator"+datePicker);		
		}catch (Exception e){
			logger.error("Failed!! not able to Add Task");				
		}
	} 
	
	public void getWidgetsWithTitle(){		
		logInfo("Retrieve All Widgets on the Tasks Page");		
		List<WebElement> panelHead = driver().findElements(By.cssSelector(widgetsTitle));
		System.out.println("Total Number of Widgets are :  "+ panelHead.size());
		for (WebElement title :panelHead){
			System.out.println("Widgets with their Title is :"+title.getText());		
		}	
	}
	
	public void deleteAllWidgets() throws Exception, InterruptedException{
		logInfo("inside deleteAllWidgets() method");
		
		List<WebElement> panelHead = driver().findElements(By.cssSelector(widgetsTitle));
		System.out.println("Total Number of Widgets are :  "+ panelHead.size());		
		for(int i =1; i<= panelHead.size(); i++ ){			
			selectEditWidgets();			
			clickOnElement("cssSelector", deleteWidgets);
		//	collapseEditWidget();					
		}				
	}	
	
	public void makeTodaysTaskToComplete() throws InterruptedException{		
		logInfo("Select todays Task Check box");
		try{
		waitOnElement("cssSelector", taskCheckBox);	
		List<WebElement> checks =  driver().findElements(By.cssSelector(taskCheckBox));
		System.out.println("No of tasks are "+checks.size());
		for (WebElement checkboxes : checks)
			if(!checkboxes.isSelected()){
				checkboxes.click();
				confirmationMessage("Task has been marked complete");				
				break;	
			}		
		}catch (Exception e){
			logger.error("Fails!! Trying to select more than one check box");			
		}		
	}
	
	public void makeTaskToComplete(String task) throws InterruptedException{	
		logInfo("Select Task from Widgets and make a 'Mark As Complete' and capture the confirmation message.");
		try{
			selectTaskToComplete(task);
			confirmationMessage("Task has been marked complete");			
		}catch (Exception e){
			logger.error("Fails!! Trying to select more than one check box");
			
		}
	}
	
	public void deleteTaskFromTaskDetails() throws Exception{
		PropertyConfigurator.configure("Log4j.properties");	
		logger.info("Select currently created Task and then Delete the task.");	
		waitOnElement("cssSelector", deleteTaskDetailsbtn);
		clickOnElement("cssSelector", deleteTaskDetailsbtn);
		deleteConfirm();
		confirmationMessage("Your task has been deleted.");		
		driver().navigate().refresh();				
	}		
	
	public void deleteAllCompletedTasks() throws Exception, InterruptedException{		
		logInfo("Select Completed Tasks and then delete ");
		try{
			List<WebElement> list =  driver().findElements(By.cssSelector(taskCheckBox));
			for (int i =1; i<=list.size(); i++){
				selectLinkCompletedTask();	
				deleteTaskSelect();
				/*clickOnElement("xpath", deleteTaskDetailsbtn);*/
				deleteConfirm();
				confirmationMessage("Your task has been deleted.");				
				driver().navigate().refresh();		
								
			}}catch (Exception e) {		
			logger.error("Failed!! Unable to delete Task from Completed Tasks.");						
		}
	}
		
	public void deleteAllInCompletedTasks() throws Exception, InterruptedException{
		PropertyConfigurator.configure("Log4j.properties");	
		logger.info("Select InComplete Tasks and then delete ");
		try{
			List<WebElement> list =  driver().findElements(By.cssSelector(taskCheckBox));
			for (int i =1; i<=list.size(); i++){
				selectLinkInCompletedTask();
				deleteTaskFromTaskDetails();
				/*deleteTaskSelect();
				clickOnElement("xpath", deleteTaskDetailsbtn);
				deleteConfirm() 
				.confirmationMessage("Your task has been deleted.");
				waitOnElement(6000);
				driver().navigate().refresh();
				deleteAllWidgets();
				
				selectEditWidgets()
				.deleteAllWidgets()
				.collapseEditWidget();		*/	
				
                deleteAllWidgets();				
				selectEditWidgets();
				deleteAllWidgets();
				collapseEditWidget();
			}}catch (Exception e) {		
			logger.error("Failed!! Unable to delete Task from Completed Tasks.");						
		}
	}	

	
	
	
	//Setters
	
	
	/*public void confirmationMessage(String expectedConfMessage) throws Exception{
		WebDriverWait wait = new WebDriverWait(driver, 10);		
		try{
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(confirmationMessage)));
		getText("xpath", confirmationMessage, "confirmation message is ");
		WebElement actualConfMsg = driver().findElement(By.xpath(confirmationMessage));
		Assert.assertEquals(actualConfMsg.getText(), expectedConfMessage);
		Reporter.getOutput();
		Reporter.log("Toast/Confirmation message is : "+actualConfMsg.getText());		
		}catch(Exception e){
			logger.error("Failed!! Unable to capture confirmation message");
			Reporter.getOutput();
			}		
		logger.info((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );
		
		}*/
	
	
	public void clickTaskLink() throws Exception{
		try{
			doubleClick("cssSelector", taskLink);
			
		}catch (Exception e) {
		logger.error("Failed!! unable to select Link Task.");
				}
		logger.info((new Exception().getStackTrace()[0].getMethodName())+"    Success");
			
	}
	
	public void getItemsFromModal(){
		List <WebElement> modal= driver().findElements(By.xpath("//*[@id='task-detail-modal']/div/div[1]"));
		System.out.println("get size "+modal.size());
		for (WebElement md : modal){
			System.out.println("All are"+md.getText());			
		}
				
	}
	
	
	public void deleteConfirm() throws Exception{
		try{
		waitOnElement("cssSelector", deleteConfirmButton);
		clickOnElement("cssSelector", deleteConfirmButton);
		}catch (Exception e) {
			logger.error("Failed!! unable to delete Task from confirmation Message.");					
		}
		logger.info((new Exception().getStackTrace()[0].getMethodName())+"    Success");
	}
	
	public void selectLinkCompletedTask() throws Exception{
		
		try{
			clickOnElement("cssSelector", lnkcompletedTask);
		}catch (Exception e) 
		{
			logger.error("Failed!! unable to delete Task from confirmation Message.");	
		}
		
	}
	
public void selectLinkInCompletedTask() throws Exception{
		
		try{
			getText("cssSelector", lnkIncompletedTask, "Link name is ");
			clickOnElement("cssSelector", lnkIncompletedTask);
		}catch (Exception e) 
		{
			logger.error("Failed!! unable to delete Task from confirmation Message.");	
					}		
	}
	

  public void getListOfCompletedTasks(){	  
	  try{
		  List<WebElement> list =  driver().findElements(By.cssSelector(taskCheckBox));
		System.out.println("No of tasks are "+list.size());
			
	  }catch (Exception e){
		  logger.error("Failed! Not able to retrieve completed tasks list size");		  
	  }	
		  
  }  
  
public void selectCreatedTask(String task){	  
	  try{		
		  List<WebElement> list =  driver().findElements(By.cssSelector(taskName));
		 /* List<WebElement> list =  driver().findElements(By.tagName("a"));*/	
		  System.out.println("total is "+list.size());
		  
			for (WebElement names : list) {				
				if (names.getText().equalsIgnoreCase(task)){
					System.out.println("tasks Name is "+names.getText());					
					Reporter.log("Newly created task is :"+names.getText());
					names.click();
					break;
					
					
				}}
			
			
	  }catch (Exception e){
		  logger.error("Failed! Not able to retrieve and select task.");		  
	  }
	  
  } 


public void selectCreatedTaskFromListView(String task){	  

	  try{			 
		  List<WebElement> list =  driver().findElements(By.cssSelector(taskName1));				 
		  System.out.println("total is "+list.size());	  
		  for (WebElement names : list) {			
			  if (task.equals(names.getText())){				 
				  System.out.println("Task is "+ names.getText());
				  names.click();
				  break;
				    }
		  }
		  
		  
			/*for (WebElement names : list) {
				String tasksList = names.getText();
				
				System.out.println("12 tasks Name is "+tasksList);
				if (tasksList.equalsIgnoreCase(task))
				if (task.equalsIgnoreCase(tasksList))
				{			
				System.out.println("a tasks Name is "+tasksList);					
				Reporter.log("Newly created task is :"+tasksList);
				Actions action = new Actions(driver);
				action.moveToElement((WebElement) list).doubleClick().build().perform();
				names.click();
				waitOnElement(5000);
				
				}}*/
			
			
	  		}catch (Exception e){
	  			logger.error("Failed! Not able to retrieve completed tasks list size");		  
	  					}		  
			} 

		public void verifyTaskInWidget(String task){	
		PropertyConfigurator.configure("Log4j.properties");
		logger.info("Trying to identify the task - "+ task +" by assertion"); 	  
		  	List<WebElement> list =  driver().findElements(By.cssSelector(taskName));
			System.out.println("No of tasks are "+list.size());
			boolean isMatchFound = false;
			for (WebElement names : list){
				String tk = names.getText();			
			if (tk.equalsIgnoreCase(task)){
				isMatchFound = true;				
			Assert.assertTrue(tk.equalsIgnoreCase(task), "Task - "+task+ " is available in the grid.");	
			}}
			try{			
			if (isMatchFound==false){
				Assert.assertTrue(false, "Failed!! Task - "+task+ " is Not available in the grid.");		
				} }catch(Exception ex){				
				    logger.error("Failed!! Task - "+task+ " is Not available in the grid List.");
			}  
		}
		
		public void verifyTaskInWidgetFromListView(String task){
		PropertyConfigurator.configure("Log4j.properties");
		logger.info("Trying to identify the task - "+ task +" by assertion"); 			
			driver().navigate().refresh();
		    List<WebElement> list =  driver().findElements(By.cssSelector(taskName1));
			System.out.println("No of tasks are "+list.size());
			boolean isMatchFound = false;
			for (WebElement names : list){
				String tk = names.getText();			
				if (task.equals(tk)){
				isMatchFound = true;				
			Assert.assertTrue(tk.equalsIgnoreCase(task), "Task - "+task+ " is available in the grid.");	
			}}
			try{			
			if (isMatchFound==false){
				Assert.assertTrue(false, "Failed!! Task - "+task+ " is Not available in the grid.");		
				
			} }catch(Exception ex){				
				logger.error("Failed!! Task - "+task+ " is Not available in the grid List.");
			}  
		}

		public void selectTaskToComplete(String task){	  
			try{
		  WebElement checks =  driver().findElement(By.cssSelector(taskCheckBox));
		  List<WebElement> list =  driver().findElements(By.cssSelector(taskName));
			System.out.println("No of tasks are "+list.size());
			for (WebElement names : list) {
				System.out.println("Names are "+names.getText());
				Reporter.log("Task Names are :"+names.getText());
				if (names.getText().equals(task)){
					System.out.println("id is "+ checks.getCssValue("id"));
					System.out.println("attribute is "+ checks.getCssValue("id"));
					System.out.println("id is "+ checks.getCssValue("id"));	
					checks.click();					
					break;
				}				
			}			
	  }catch (Exception e){
		  logger.error("Failed! Not able to retrieve completed tasks list size");		  
	  						}		  
					}  

		public void editTask() throws Exception{	
			try{				
				driver().switchTo().frame(1);
				clickOnLink("linkText", "Edit");
				//clickOnLink("xpath", editTask);					
				}catch (Exception e) {
					logger.error("Failed!! unable to Edit the Task.");					
				}			
		}
		
		public void editAndUpdateTask (int Date) throws Exception{			
			String futDate = getDate(Date, "MM/dd/yyyy");			
			selectEdit();			
			/*inputText("cssSelector", dateField,futDate );
			selectPriority();				
			composeTextOnElement();*/
			updateTask ();		
				}
		
		public void markAsComplete () throws Exception{
			selectMarkAsComplete();
			confirmationMessage("Task has been marked complete");
						
					}
		
		public void markAsInComplete () throws Exception{
			selectMarkAsComplete();
			confirmationMessage("Task has been marked incomplete");						
					}
		
		public void selectEdit (){
			try{		
			List <WebElement> lis = driver().findElements(By.xpath(taskItems));			
			WebElement edit = driver().findElement(By.xpath(editTask));
			for (WebElement li :lis)			
			if(!(li.getText()).equals("View Details"))	{				
				Actions actions = new Actions(driver());
				actions.moveToElement(edit);				
				actions.doubleClick().build().perform();		
				break;	}}		
		catch (Exception e) {
			logger.error("Failed!! unable to Mark As Completed the Task.");					
		}}	
		
	public void selectMarkAsComplete (){
		try{		
		List <WebElement> lis = driver().findElements(By.cssSelector(taskItems));
		System.out.println("Size is "+lis.size());
		WebElement mark = driver().findElement(By.xpath(markComplete));	
		waitOnElement("xpath",markComplete);		
		for (WebElement li :lis){					
		if(!(li.getText()).equals("Edit"))	{			
			mark.click();			
			break;			
		}}}	catch (Exception e)	{
		logger.error("Failed!! unable to Mark As Complete/Incomplete the Task.");					
	}}
	
	public void deleteTaskSelect (){
		try{
		waitOnElement("xpath", taskItems);	
		List <WebElement> lis = driver().findElements(By.xpath(taskItems));			
		WebElement deleteTask = driver().findElement(By.xpath(deleteTaskbtn));
		for (WebElement li :lis)			
		if(!(li.getText()).equals("View Details"))	{				
			Actions actions = new Actions(driver());
			actions.moveToElement(deleteTask);				
			actions.doubleClick().build().perform();		
			break;	}}		
	catch (Exception e) {
		logger.error("Failed!! unable to click on Delete Task.");					
	}}	
		
		
		public void selectPriority (){			 
			try{
			Select dd = new Select(driver().findElement(By.id(priority)))	;
			dd.selectByValue("Medium");					
			}catch (Exception e) {
				logger.error("Failed!! unable to select priority.");					
			}	}
		
		public void updateTask () throws Exception{
			
			System.out.println(selectUpdateTask().getText());
			selectUpdateTask().click();			
			confirmationMessage("Your task has been updated.");	
					}
		
		public WebElement selectUpdateTask(){
			return driver().findElement(By.cssSelector(updateTask));
			
						}
		public void CreateTaskinModalwindow() throws Exception, InterruptedException{
			PropertyConfigurator.configure("Log4j.properties");	
			logger.info("Create a Task with the Modal window");			
			try{
				waitOnElement("xpath", taskDescriptionInput);
				inputText("xpath", taskDescriptionInput, taskEvent1);
				waitOnElement("cssSelector", saveTask);	
				clickOnButton("cssSelector", saveTask);				
			}catch(Exception e){
				logger.error("Failed!! Not able to create a task with Modal window");				
			}
			//confirmationMessage("Task has been created successfully.");
			confirmationMessage("Your task has been created.");			
		}
		
		public void selectNextStepslnk() throws Exception{	
			clickOnLink("linkText", "Next Steps");		
				}
		
		public void homePage() throws Exception{	
			clickOnLink("linkText", "Home");
				}		
  
		public void sortTakeAction() throws Exception{
			try{
				getText("cssSelector",sortTakeAction, "Sorting with");
				clickOnElement("cssSelector",sortTakeAction );
				}catch(NoSuchElementException nse){				
				logger.error("Failed!! Not able to select TakeAction Sorting");
			}		
		}
		
		public void sortPriority() throws Exception{
			try{
				getText("cssSelector",sortPriority, "Sorting with");
			clickOnElement("cssSelector",sortPriority );
			}catch(NoSuchElementException nse){				
				logger.error("Failed!! Not able to select Priority Sorting");
			}	
			
		}
		
		public void sortDueDate() throws Exception{
			try{
			getText("cssSelector",sortDueDate, "Sorting with");	
			clickOnElement("cssSelector",sortDueDate );
			clickOnElement("cssSelector",sortDueDate );
			clickOnElement("cssSelector",sortPriority );
			clickOnElement("cssSelector",sortTakeAction );
			
			}catch(NoSuchElementException nse){
				
				logger.error("Failed!! Not able to select DueDate Sorting");
			}			
		}
		
		public void selectViewContanctInWidget(){
			try{
			clickOnElement("cssSelector",viewConatct );
			}catch(NoSuchElementException nse){
				
				logger.error("Failed!! Not able to select ViewConatct in the Widget");
			}catch(Exception e){
				
				logger.error("Failed!! Not able to select ViewConatct in the Widget - Exception");
			}		
		}  
	
		public boolean verifyTaskPresent(String taskName) throws Exception{	
					
			PropertyConfigurator.configure("Log4j.properties");	
			logger.info("Verifying the task - '  "+taskName+" ' is available in the widget");
			Reporter.log("Verifying whether'  "+taskName+" ' is available in the widget");
			try{			
			List<WebElement> allTasks = driver().findElements(By.cssSelector(taskName1));
			int widgets_count = allTasks.size();		
			boolean isWidgetPresent=false;		
			for(WebElement x : allTasks){
				String actualwidget = x.getText().trim();
				System.out.println(actualwidget+ " tasks are ");
				if(actualwidget.equalsIgnoreCase(taskName)){
					System.out.println("'" +actualwidget + "' - is available in the widget");
					Reporter.log("'" +actualwidget + "' - is available in the widget");
					isWidgetPresent = true;
					break;
					
				} }return isWidgetPresent;			
			
			}catch(Exception e){
				logger.error("Not able to handle this method - Widgets_Methods.verifyTaskPresent");
				Reporter.log("Not able to handle this method - Widgets_Methods.verifyWidgetPresent");
				
			}	return false;	
		}	
		
		
	// ********************************************************************************************************
	
		
		// Jayadev
		
		public void dragWidget(String widgetFrom,String widgetTo) throws Exception{
			logInfo("Inside dragWidget() method");
			WebElement from = driver().findElement(By.cssSelector(widgetFrom));
			WebElement to = driver().findElement(By.xpath(widgetTo));
			waitOnElement("cssSelector",widgetFrom);
			dragAndDropAction(from, to);			
		}
		

		public void navigate2BusinessTasks(){
			logInfo("inside navigate2BusinessTasks() method.");
			driver().navigate().to(appUrl + "pyr_core/user_tasks");
		}
		
		public void go2EditWidgetsPage() throws Exception{
			logInfo("inside go2EditWidgetsPage() method.");
			System.out.println("inside go2EditWidgetsPage() method.");
			// driver().navigate().to(appUrl);
			clickOnLink("linkText","Edit Widgets");
		}
		
		// drag and drop the widget from user tasks page.
		
			public void dragAndDropWidgetFromTasksPage(String widgetName, String panelLocation) throws Exception{
				logInfo("inside dragAndDropWidgetFromTasksPage() method.");
				System.out.println("inside dragAndDropWidget() method.");
				
				go2EditWidgetsPage();
				
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
				
							
				WebElement from = driver().findElement(By.xpath(widgName));
				
				switch(panelLocation){
				case "Left Panel":
					to = driver().findElement(By.xpath(tasksLeftPane));
					break;
				case "Right Panel":
					to = driver().findElement(By.xpath(tasksRightPane));
					break;
				}
				
				Actions builder = new Actions(driver());
				Action drag = builder.clickAndHold(from).moveToElement(to).release(to).build();
				drag.perform();					
			}		 
		
	// Add a new task 
		
		public void addTask(String taskName, String taskType) throws Exception{
			logInfo("inside addTask() method");
			
			String currentdt = TestBase.getSystemDate();
			System.out.println("currentdt =" +currentdt);
			
			String futuredt = TestBase.getDate(+1, "MM/dd/yyyy");
			System.out.println("futuredt =" +futuredt);
			
			String pastdt = TestBase.getDate(-1, "MM/dd/yyyy");
			System.out.println("pastdt =" +pastdt);
			
			String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
			inputTextClear("xpath", taskDescriptionInput);
			
			// inputText("cssSelector",inputTaskActionItem, vibeRecipient_text);
			
			switch(taskType){
			case "TodaysTask":
				inputText("xpath", taskDescriptionInput, taskName);	
				inputTextClear("cssSelector","#pyr_core_user_task_due_date");
				inputText("cssSelector","#pyr_core_user_task_due_date", currentdt);
				//selectCalendarDate(currentdt);				
				break;
			case "FutureTask":
				inputText("xpath", taskDescriptionInput, taskName);	
				inputTextClear("xpath","//*[@id='pyr_core_user_task_due_date']");
				inputText("xpath", "//*[@id='pyr_core_user_task_due_date']" , futuredt);
				// selectCalendarDate(futuredt);			
				break;
			case "OverdueTask":
				inputText("xpath", taskDescriptionInput, taskName);	
				inputTextClear("xpath","//*[@id='pyr_core_user_task_due_date']");
				inputText("xpath", "//*[@id='pyr_core_user_task_due_date']" , pastdt);
				break;
			case "NoDueDateTask":
				inputText("xpath", taskDescriptionInput, taskName);	
				inputTextClear("xpath","//*[@id='pyr_core_user_task_due_date']");				
				break;
			case "NoTaskTitle":
				inputTextClear("xpath", taskDescriptionInput);	
				inputTextClear("xpath","//*[@id='pyr_core_user_task_due_date']");
			}
			
			waitOnElement("cssSelector",priority);
			selectFromDropdown("cssSelector",priority,"byVisibleText","Medium");
			
			clickOnButton("xpath","//input[@value='Add Task']");			
			confirmationMessage("Your task has been created.");
	
			
		}
		
	
		// Verifies if the task is present in the specific widget.
		
		public boolean verifyTaskPresentinWidget(String panelName,String taskName) throws Exception, Exception{
			logInfo("inside verifyTaskPresentinWidget() method.");
			
			String pName = "";
		
			
			switch(panelName){
			case "TodaysTaskWidget":
				pName = "//*[@id='tasks_due_today']";  //*[@id='source_pyr_core_widgets_tasks_today']
				waitOnElement("xpath",pName);
				break;
			case "FutureTaskWidget":
				pName = "//*[@id='tasks_future']";
				waitOnElement("xpath",pName);
				break;
			case "CompletedTasksWidget" :
				pName = "//*[@id='tasks_completed']";
				waitOnElement("xpath",pName);
				break;
			case "IncompleteTasksWidget" :
				pName = "//*[@id='tasks_incomplete']";
				waitOnElement("xpath",pName);
				break;
			case "NoDueDateWidget" :
				pName = "//*[@id='tasks_no_due_date']";
				waitOnElement("xpath",pName);
				break;
			case "OverDueWidget" :
				pName = "//*[@id='tasks_overdue']";
				waitOnElement("xpath",pName);
				break;	
			}
			
			// String path = "/div[2]/div/table/tbody";    // "/div[2]/div/div/div/table/tbody";    // master = "/div[2]/div/div/div/table/tbody";  //    
			
			String path = prop.getLocatorForEnvironment(appUrl,"path");
			System.out.println("path =" +path);
			
			waitOnElement("xpath", pName+path);
			WebElement taskPane = driver().findElement(By.xpath(pName+path));
			
			List allRows = taskPane.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			
			String before_name = pName + path + "/tr[";
			String after_name = "]/td[2]/a"; 
		
			boolean isTaskFound = false;
			System.out.println("Total Tasks= " +all_rows);
			if(all_rows>0){
				for(int i=1;i<=all_rows;i++){
					WebElement name = driver().findElement(By.xpath(before_name+i+after_name));
					String task_name = name.getText().trim();
					System.out.println("Task Name =" +task_name);
					if(task_name.equalsIgnoreCase(taskName)){
						System.out.println(task_name + " task found in " + panelName + " pane.");
						logInfo(task_name + " task found in " + panelName + " pane.");
						isTaskFound = true;
						break;
					}
				}
			}
			
			if(isTaskFound==false){
				System.out.println(taskName +  " task not found in " + panelName + " pane.");
				logInfo(taskName + " task not found in " + panelName + " pane.");
			}
			return isTaskFound;
			
		}
		
		
	// Deletes the selected Task
		
		public void deleteTask(String panelName,String taskName) throws Exception, Exception{
			logInfo("inside deleteTask() method.");
			
			String pName = "";
			
			switch(panelName){
			case "TodaysTaskWidget":
				pName = "//*[@id='tasks_due_today']";
				break;
			case "CompletedTasksWidget" :
				pName = "//*[@id='tasks_completed']";
				break;
			case "IncompletedTasksWidget" :
				pName = "//*[@id='tasks_incomplete']";
				break;
			case "NoDueDateWidget" :
				pName = "//*[@id='tasks_no_due_date']";
				break;
			case "FutureTaskWidget":
				pName = "//*[@id='tasks_future']";
				break;
			}
			
			// waitOnElement("cssSelector",panelTasksNoDueDate);
			 WebElement taskPane = driver().findElement(By.xpath(pName));
			
			List allRows = taskPane.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			
			// String path = "/div[2]/div/table/tbody";     //  "/div[2]/div/div/div/table/tbody";  // "/div[2]/div/table/tbody" 		// "/div[2]/table/tbody"; 
			String path = prop.getLocatorForEnvironment(appUrl,"path");
			System.out.println("Template Name =" +path);
			
			String before_name = pName + path+ "/tr[";
			String after_name = "]/td[2]/a"; 
		
			boolean isTaskFound = false;
			System.out.println("Total Tasks= " +all_rows);
			if(all_rows>0){
				for(int i=1;i<=all_rows;i++){
					WebElement name = driver().findElement(By.xpath(before_name+i+after_name));
					String task_name = name.getText().trim();
					System.out.println("Task Name =" +task_name);
					if(task_name.equalsIgnoreCase(taskName)){
						System.out.println(task_name + " task found in " + panelName + " pane.");
						logInfo(task_name + " task found in " + panelName + " pane.");
						name.click();
						waitOnElement("linkText","Delete Task");
						verifyLinkPresent("Delete Task");
						clickOnLink("linkText","Delete Task");
						confirmOK();
						logInfo("clicked on Delete Task button");
						confirmationMessage("Your task has been deleted.");					
						isTaskFound = true;
						break;
					}
				}
			}
			
			if(isTaskFound==false){
				logInfo(taskName + " task not found in " + panelName + " pane.");
			}
			
		}

	
		// Mark Task as Complete
		
	
		public void markTaskAsComplete(String panelName,String taskName) throws Exception, Exception{
			logInfo("inside deleteTask() method.");
			
			String pName = "";
			
			switch(panelName){
			case "TodaysTaskWidget":
				pName = "//*[@id='tasks_due_today']";
				break;
			case "IncompletedTasksWidget" :
				pName = "//*[@id='tasks_incomplete']";
				break;
			case "NoDueDateWidget" :
				pName = "//*[@id='tasks_no_due_date']";
				break;
			case "FutureTaskWidget":
				pName = "//*[@id='tasks_future']";
				break;
			}
			
			// String path = "/div[2]/div/table/tbody";      // "/div[2]/table/tbody";    //  "/div[2]/div/div/div/table/tbody";  // "/div[2]/div/table/tbody" // 
			String path = prop.getLocatorForEnvironment(appUrl,"path");
			System.out.println("Template Name =" +path);
			
			WebElement taskPane = driver().findElement(By.xpath(pName + path));
			
			List allRows = taskPane.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			
			String before_name = pName + path + "/tr[";
			String after_name = "]/td[2]/a"; 
		
			boolean isTaskFound = false;
			System.out.println("Total Tasks= " +all_rows);
			if(all_rows>0){
				for(int i=1;i<=all_rows;i++){
					WebElement name = driver().findElement(By.xpath(before_name+i+after_name));
					String task_name = name.getText().trim();
					System.out.println("Task Name =" +task_name);
					if(task_name.equalsIgnoreCase(taskName)){
						System.out.println(task_name + " task found in " + panelName + " pane.");
						logInfo(task_name + " task found in " + panelName + " pane.");
						name.click();
						waitOnElement("xpath","//*[contains(text(),'Mark As Complete')]");
						clickOnElement("xpath","//*[contains(text(),'Mark As Complete')]");
						
						logInfo("clicked on Mark As Complete button");
						isTaskFound = true;
						break;
					}
				}
			}
			
			if(isTaskFound==false){
				logInfo(taskName + " task not found in " + panelName + " pane.");
			}
		}


	// Mark Task as Incomplete
		
		public void markTaskAsIncomplete(String panelName,String taskName) throws Exception, Exception{
			logInfo("inside markTaskAsIncomplete() method.");
			
			String pName = "";
			
			switch(panelName){
			case "CompletedTasksWidget" :
				pName = "//*[@id='tasks_completed']";
				break;
			}
			
			// String path =  "/div[2]/div/div/div/table/tbody";  // "/div[2]/table/tbody"; 
			String path = prop.getLocatorForEnvironment(appUrl,"path");
			System.out.println("Template Name =" +path);
			
			WebElement taskPane = driver().findElement(By.xpath(pName + path));
			
			List allRows = taskPane.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			
			String before_name = pName + path + "/tr[";
			String after_name = "]/td[2]/a"; 
		
			boolean isTaskFound = false;
			System.out.println("Total Tasks= " +all_rows);
			if(all_rows>0){
				for(int i=1;i<=all_rows;i++){
					WebElement name = driver().findElement(By.xpath(before_name+i+after_name));
					String task_name = name.getText().trim();
					System.out.println("Task Name =" +task_name);
					if(task_name.equalsIgnoreCase(taskName)){
						System.out.println(task_name + " task found in " + panelName + " pane.");
						logInfo(task_name + " task found in " + panelName + " pane.");
						name.click();
						waitOnElement("xpath","//*[contains(text(),'Mark As Incomplete')]");
						clickOnElement("xpath","//*[contains(text(),'Mark As Incomplete')]");
						logInfo("clicked on Mark As Incomplete button");						
						isTaskFound = true;
						break;
					}
				}
			}
			
			if(isTaskFound==false){
				logInfo(taskName + " task not found in " + panelName + " pane.");
			}
		}
		

		
	// Complete Task thru check box
		
		public boolean completeTask(String taskName){
			logInfo("inside completeTask() method.");
			WebElement taskPane = driver().findElement(By.cssSelector(panelTasksToday));
			
			List allRows = taskPane.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			
			String before_name = "//*[@id='tasks_due_today']/div[2]/div/div/div/table/tbody/tr[";
			String after_name = "]/td[2]/a"; 
			
			String before_check = "//*[@id='tasks_due_today']/div[2]/div/div/div/table/tbody/tr[";
			String after_check = "]/td[1]/input";
		
			boolean isTaskCompleted = false;
			System.out.println("Total Tasks= " +all_rows);
			if(all_rows>0){
				for(int i=1;i<=all_rows;i++){
					WebElement name = driver().findElement(By.xpath(before_name+i+after_name));
					String task_name = name.getText().trim();
					System.out.println("Task Name =" +task_name);
					if(task_name.equalsIgnoreCase(taskName)){
						System.out.println(task_name + " task found in the today's task widget");
						logInfo(task_name + " completing the today's task");
						WebElement el = driver().findElement(By.xpath(before_check+i+after_check));
						el.click();
						isTaskCompleted = true;
						break;
					}
				}
			}
			
			return isTaskCompleted;
			
		}
		
		
		
		// Verify if the task is present in the first row in the widget
		
		public boolean verifyTaskPresentinFirstRowInWidget(String panelName,String taskName) throws Exception, Exception{
			logInfo("inside verifyTaskPresentinFirstRowInWidget() method.");
			
			String pName = "";
			
			switch(panelName){
			case "TodaysTaskWidget":
				pName = "//*[@id='tasks_due_today']";  
				break;
			case "FutureTaskWidget":
				pName = "//*[@id='tasks_future']";
				break;
			case "CompletedTasksWidget" :
				pName = "//*[@id='tasks_completed']";
				break;
			case "IncompleteTasksWidget" :
				pName = "//*[@id='tasks_incomplete']";
				break;
			case "NoDueDateWidget" :
				pName = "//*[@id='tasks_no_due_date']";
				break;
			case "OverDueWidget" :
				pName = "//*[@id='tasks_overdue']";
				break;	
			}
			
			// String path = "/div[2]/div/table/tbody";    // "/div[2]/div/div/div/table/tbody";    // master = "/div[2]/div/div/div/table/tbody";  //    
			
			String path = prop.getLocatorForEnvironment(appUrl,"path");
			System.out.println("path =" +path);
			
			waitOnElement("xpath", pName+path);
			WebElement taskPane = driver().findElement(By.xpath(pName+path));
			
			List allRows = taskPane.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			
			String name = pName + path + "/tr[1]/td[2]/a";
			
			boolean isTaskFound = false;
			System.out.println("Total Tasks= " +all_rows);
			if(all_rows>0){
					WebElement ename = driver().findElement(By.xpath(name));
					String task_name = ename.getText().trim();
					System.out.println("Task Name =" +task_name);
					if(task_name.equalsIgnoreCase(taskName)){
						System.out.println(task_name + " task found in " + panelName + " pane.");
						logInfo(task_name + " task found in " + panelName + " pane.");
						isTaskFound = true;
					}
			}
			
			if(isTaskFound==false){
				System.out.println(taskName +  " task not found in " + panelName + " pane.");
				logInfo(taskName + " task not found in " + panelName + " pane.");
			}
			return isTaskFound;
			
		}
		 
			
		// Search a task inside the widget
		
		public boolean searchTaskPresentinWidget(String panelName,String taskName) throws Exception, Exception{
			logInfo("inside searchTaskPresentinWidget() method.");
			driver().navigate().refresh();
			
			String pName = "";
			
			switch(panelName){
			case "TodaysTaskWidget":
				pName = "//*[@id='tasks_due_today']";  //*[@id='source_pyr_core_widgets_tasks_today']
				break;
			case "FutureTaskWidget":
				pName = "//*[@id='tasks_future']";
				break;
			case "CompletedTasksWidget" :
				pName = "//*[@id='tasks_completed']";
				break;
			case "IncompleteTasksWidget" :
				pName = "//*[@id='tasks_incomplete']";
				break;
			case "NoDueDateWidget" :
				pName = "//*[@id='tasks_no_due_date']";
				break;
			case "OverDueWidget" :
				pName = "//*[@id='tasks_overdue']";
				break;	
			}
			
			
			// String path = "/div[2]/div/table/tbody";    // "/div[2]/div/div/div/table/tbody";    // master = "/div[2]/div/div/div/table/tbody";  //    
			
			String taskSearchPath = "/div[1]/div[2]/span/input";
			
			String path = prop.getLocatorForEnvironment(appUrl,"path");
			System.out.println("path =" +path);
			
			// Enter text inside the search widget field
			
			waitOnElement("xpath", pName+taskSearchPath);
			inputTextClear("xpath",pName+taskSearchPath);
			inputText("xpath",pName+taskSearchPath, taskName);
						
						
			// verify the widget table			
						
			waitOnElement("xpath", pName+path);
			WebElement taskPane = driver().findElement(By.xpath(pName+path));
			
			List allRows = taskPane.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			
			String before_name = pName + path + "/tr[";
			String after_name = "]/td[2]/a"; 
		
			boolean isTaskFound = false;
			System.out.println("Total Tasks= " +all_rows);
			if(all_rows>0){
				for(int i=1;i<=all_rows;i++){
					WebElement name = driver().findElement(By.xpath(before_name+i+after_name));
					String task_name = name.getText().trim();
					System.out.println("Task Name =" +task_name);
					if(task_name.equalsIgnoreCase(taskName)){
						System.out.println(task_name + " task found in " + panelName + " pane.");
						logInfo(task_name + " task found in " + panelName + " pane.");
						isTaskFound = true;
						break;
					}
				}
			}
			
			if(isTaskFound==false){
				System.out.println(taskName +  " task not found in " + panelName + " pane.");
				logInfo(taskName + " task not found in " + panelName + " pane.");
			}
			return isTaskFound;
			
		}
		
		
		
		// Click fields to either sort the field in ascending or descending order in the Widget
		
		public void sortTaskByField(String panelName, String fieldName) throws Exception, Exception{
			logInfo("inside sortTaskByField() method.");
					
			String pName = "";
					
			switch(panelName){
			case "TodaysTaskWidget":
				pName = "//*[@id='tasks_due_today']";  
				break;
			case "FutureTaskWidget":
				pName = "//*[@id='tasks_future']";
				break;
			case "CompletedTasksWidget" :
				pName = "//*[@id='tasks_completed']";
				break;
			case "IncompleteTasksWidget" :
				pName = "//*[@id='tasks_incomplete']";
				break;
			case "NoDueDateWidget" :
				pName = "//*[@id='tasks_no_due_date']";
				break;
			case "OverDueWidget" :
				pName = "//*[@id='tasks_overdue']";
				break;	
			}
			
			
			String taskFieldPath = "";
			
			switch(fieldName){
			case "Task":
				taskFieldPath = "/div[2]/div[1]/div[1]/div[1]/table/thead/tr/th[2]";   // /div[2]/div[1]/table/thead/tr/th[2]
				break;
			case "Take Action":
				taskFieldPath = "/div[2]/div[1]/div[1]/div[1]/table/thead/tr/th[2]"; 	// /div[2]/div[1]/table/thead/tr/th[3]
				break;
			case "Priority" :
				taskFieldPath = "/div[2]/div[1]/div[1]/div[1]/table/thead/tr/th[2]";			// /div[2]/div[1]/table/thead/tr/th[4]
				break;
			}
			
		
			// verify the widget table			
						
			waitOnElement("xpath", pName+taskFieldPath);
			WebElement etaskField = driver().findElement(By.xpath(pName+taskFieldPath));
			etaskField.click();			
			
		}
	
 
}