package vibe.reports.tests;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import common.Priority;
import common.TestBase;
import vibe.tasks.tests.TaskMethods;


@Priority(45)
public class ReportsTest extends ReportsMethods{
	
	TaskMethods tk = new TaskMethods();
	
	@Test(priority=4501)
	public void reports_ValidateAllTablesWithTitles() throws Exception{
		 logInfo("Fetch All types of reports \n"
				+ "Retrive all Constriants with respective to Reports.\n");		
		 navigateReports();	
		 typesofReports();		
	}
	
	@Test(priority=4502)
	public void temp() throws Exception, InterruptedException{
		Thread.sleep(5000);
		driver().navigate().back();
		Thread.sleep(5000);
		//clickOnLink("linkText", "Back");
	}
	
	
	@Test(priority=4502)
	public void assertsNoOrder(){
		Assert.assertTrue( isNoOrderTitlePresent());
		Assert.assertTrue( isExportToPresent());
		Assert.assertTrue( isFilterOptionsPresent());
		Assert.assertTrue( isSaveReportPresent());
		Assert.assertTrue( isRunReportPresent());		
	}
      
	@Test(priority=4503)
	public void Report_TableDownLine() throws Exception{
		logInfo("Select DownLine Table and ");		
		
		navigateReports();			
		reportstable("Downline Search") ;
	}
	

	@Test(priority=4504)

	public void Report_CreateTaskAndVerifyInWidgets() throws Exception{
		logInfo("Select DownLine Table and Create New Task with future Date from Reports. \n"
				+ "Verify the same task in future Tasks Widget. Delete the same task");		
		//String  taskEvent1 ="Especially task is created for you with TaskId_ 119";
		
		navigateReports();			
		reportstable("Downline Search") ;
		exportList("Create Task");
		createTaskFromExportTo(taskEvent1, 4);
		confirmationMessage("Task Created");
		tk.navigate2BusinessTasks();
		tk.deleteAllWidgets();
		tk.selectEditWidgets();		   
	    tk.dragWidget(tasksFutureWidgets,dragWidgetToLocation);		  
	    tk.collapseEditWidget();
		tk.verifyTaskPresent(taskEvent1);
		tk.selectCreatedTask(taskEvent1);		 
		tk.deleteTaskFromTaskDetails() ;		
	}
	

	@Test(priority=4505)
	public void Report_SendBulkEmailToDownliners() throws Exception{
		 logInfo("Reports- Select DownLine Report, Send bulk emails to all Downline usres. ");
		 navigateReports();			
		 reportstable("Downline Search") ;
		 exportList("Send Bulk Email");
		 SendBulkMessage(subjectBulk);
		 confirmationMessage("Email sent");
	}


	@Test(priority=4506)
	public void Report_CreateEvent() throws Exception{
		 logInfo("Reports- Select DownLine Report, Send bulk emails to all Downline usres. ");		
		 //String EventText="Appointment With Hanuma Gangadhar";
		 navigateReports();			
		 reportstable("Downline Search") ;
		 exportList("Create Event");
		 createEvent(EventText, 45);
	}
	
	@Test(priority=4507)
	public void Report_sortColumns() throws Exception{
		 logInfo("Reports- Navigate to Report DownLine , search the ");
		 navigateReports();			
		 reportstable("Downline Search");		
		 getColumnHeadersInDownLineTable(sortFName);
		 getColumnHeadersInDownLineTable(sortFName);
		 retrieveDataInDownLineTable();			
	}
	
	@Test(priority=4508)
	public void Report_TreeReport() throws Exception{		
		 nav2TreeViewReports();
		/* handleTreeView();*/
		 closeTreeViewScreen();
		
	}
	

}
