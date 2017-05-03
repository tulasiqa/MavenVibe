package vibe.tasks.tests;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;


import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.contacts2.tests.BusinessContacts_Tests;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.users.tests.UsersMethods;
import vibe.widgets.tests.WidgetsMethods;
import common.Priority;
import common.readProp;


@Priority(14)
public class AVTasksTest extends AVTaskMethods {		
	BusinessContacts_Tests bct = new BusinessContacts_Tests();	
	BusinessContactsMethods bcm = new BusinessContactsMethods();
	WidgetsMethods wm = new WidgetsMethods();
	readProp prop = new readProp();

	@Test(priority=1401)
	public void enableAllTasksWidgets() throws Exception{
		logInfo("inside enableAllTasksWidgets() Test");
		
		   
		
		// enable the widgets in admin page.
			wm.setWidgetStatus("Tasks Add",true);
			wm.setWidgetStatus("Tasks Today",true);
			wm.setWidgetStatus("Tasks No Due Date",true);
			wm.setWidgetStatus("Tasks Future",true);
			wm.setWidgetStatus("Tasks Completed",true);
			wm.setWidgetStatus("Tasks Overdue",true);
			wm.setWidgetStatus("Tasks Incomplete",true);
		}
	
	@Test(priority=1402)
	public void loginWithRepOrManager() throws IOException, Exception{
		
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
	}
	

	@Test(priority=1402)
	public void dragAllTasksWidgets() throws Exception{
		  navigate2BusinessTasks();
		  dragAndDropWidgetFromTasksPage("Tasks Add","Left Panel");
		  dragAndDropWidgetFromTasksPage("Tasks Today","Left Panel");
		  dragAndDropWidgetFromTasksPage("Tasks No Due Date","Left Panel");
		  dragAndDropWidgetFromTasksPage("Tasks Future","Right Panel");
		  dragAndDropWidgetFromTasksPage("Tasks Completed","Right Panel");
		  dragAndDropWidgetFromTasksPage("Tasks Overdue","Right Panel");
		  dragAndDropWidgetFromTasksPage("Tasks Incomplete","Right Panel");			
		  collapseEditWidget();
	}
	
	
	@Test(priority=1404)
	public void searchTaskInWidget() throws Exception{
		logInfo("inside searchTaskInWidget() Test");
		
		navigate2BusinessTasks();		
		addTask(todaysTask_text,"TodaysTask");		
		boolean isTaskFoundInTodaysWidget = searchTaskPresentinWidget("TodaysTaskWidget",todaysTask_text);
		System.out.println("isTaskFoundInTodaysWidget =" +isTaskFoundInTodaysWidget);
		if (isTaskFoundInTodaysWidget==true){
			logInfo(todaysTask_text + " task is present in Today's Task widget.");
			deleteTask("TodaysTaskWidget",todaysTask_text);
			logInfo("Deleting the task " + todaysTask_text);
		} else {
			logInfo(todaysTask_text + " task could not be found in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget, todaysTask_text + " task could not be found in Today's Task widget.");
			
		}
	}
	
	@Test(priority=1406)
	public void addTodaysTasks() throws Exception{
		logInfo("inside addTodaysTasks() Test");
		
	   navigate2BusinessTasks();	
	   addTask(todaysTask_text,"TodaysTask");
		boolean isTaskFoundInTodaysWidget = verifyTaskPresentinWidget("TodaysTaskWidget",todaysTask_text);
		System.out.println("isTaskFoundInTodaysWidget =" +isTaskFoundInTodaysWidget);
		if (isTaskFoundInTodaysWidget==false){
			logInfo(todaysTask_text + " task should present in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget, todaysTask_text + " task should present in Today's Task widget.");
		} 
		
		boolean isTaskFoundIncompleteWidget = verifyTaskPresentinWidget("IncompleteTasksWidget",todaysTask_text);
		System.out.println("isTaskFoundIncompleteWidget =" +isTaskFoundIncompleteWidget);
		if (isTaskFoundIncompleteWidget==false){
			logInfo(todaysTask_text + " task should present in incomplete widget.");
			Assert.assertTrue(isTaskFoundIncompleteWidget, todaysTask_text + " task should present in Incomplete Task widget.");
		} 
		
		 deleteTask("TodaysTaskWidget",todaysTask_text);
	}
	
	
	
	@Test(priority=1409)
	public void markTaskAsComplete() throws Exception{
		logInfo("inside markTaskAsComplete() Test");
		
		navigate2BusinessTasks();	
		addTask(todaysTask_text,"TodaysTask");
		boolean isTaskFoundInTodaysWidget1 = verifyTaskPresentinWidget("TodaysTaskWidget",todaysTask_text);
		if (isTaskFoundInTodaysWidget1==false){
			logInfo(todaysTask_text + " task should not present in Today's Task widget.");
			Assert.assertTrue(isTaskFoundInTodaysWidget1, todaysTask_text + " task should not present in Today's Task widget.");
		} 
		
		markTaskAsComplete("TodaysTaskWidget", todaysTask_text);
		
		boolean isTaskFoundInCompleteWidget2 = verifyTaskPresentinWidget("IncompleteTasksWidget",todaysTask_text);
		if (isTaskFoundInCompleteWidget2==true){
			logInfo(todaysTask_text + " task should not present in Incomplete Task widget");
			Assert.assertFalse(isTaskFoundInCompleteWidget2, todaysTask_text + " task should not present in Incomplete Task widget");
		} 
		
		
		boolean isTaskFoundCompletedWidget2 = verifyTaskPresentinWidget("CompletedTasksWidget",todaysTask_text);
		if (isTaskFoundCompletedWidget2==false){
			logInfo(todaysTask_text + " task should present in Completed Task widget");
			Assert.assertTrue(isTaskFoundCompletedWidget2, todaysTask_text + " task should present in Completed Task widget");
		} 
		
		deleteTask("CompletedTasksWidget", todaysTask_text);
	}
	
	

	@Test(priority=1410)  
	public void markTaskAsIncomplete() throws Exception{
		logInfo("inside markTaskAsIncomplete() Test");
	
		navigate2BusinessTasks();
		addTask(todaysTask_text,"TodaysTask");		
			
		boolean isTaskFoundInTodaysWidget1 = verifyTaskPresentinWidget("TodaysTaskWidget",todaysTask_text);
		if (isTaskFoundInTodaysWidget1==true){
			markTaskAsComplete("TodaysTaskWidget", todaysTask_text);
		} 
			
		boolean isTaskFoundCompletedWidget = verifyTaskPresentinWidget("CompletedTasksWidget",todaysTask_text);
		if (isTaskFoundCompletedWidget==true){
			markTaskAsIncomplete("CompletedTasksWidget", todaysTask_text);
		}
		
		// Verify in Incomplete Task Widget
		
		boolean isTaskFoundInCompleteWidget = verifyTaskPresentinWidget("IncompleteTasksWidget",todaysTask_text);
		if (isTaskFoundInCompleteWidget==false){
			logInfo(todaysTask_text + " task should be present in InComplete Task widget");
			Assert.assertTrue(isTaskFoundInCompleteWidget, todaysTask_text + " task should be present in Incomplete Task widget");
		} 
		
		
		// Verify in Completed Task Widget
		
		boolean isTaskFoundCompletedWidget1 = verifyTaskPresentinWidget("CompletedTasksWidget",todaysTask_text);
		if (isTaskFoundCompletedWidget1==true){
			logInfo(todaysTask_text + " task should present in Completed Task widget");
			Assert.assertFalse(isTaskFoundCompletedWidget1, todaysTask_text + " task not be presented in Completed Task widget");
		} 
	
		deleteTask("IncompletedTasksWidget", todaysTask_text); 	
	 }
	
	
	
	
	
	@Test(priority=1411)  
	public void logOutAsUser() throws Exception{		
		userLogout();			
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
