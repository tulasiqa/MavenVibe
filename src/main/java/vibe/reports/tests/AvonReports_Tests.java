package vibe.reports.tests;



import org.testng.annotations.Test;
import common.Priority;
import common.readProp;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.tasks.tests.TaskMethods;
import vibe.users.tests.UsersMethods;


@Priority(45)
public class AvonReports_Tests extends AvonReportsMethods{
	
	TaskMethods tk = new TaskMethods();
	NewsMethods nm = new NewsMethods();
	//ReportsMethods rm = new ReportsMethods();
	 UsersMethods um = new UsersMethods();
	 readProp prop  = new readProp();
	 
	 
	 
	String pwd = "avonrocks1";	
	String columnName1 = "Acct. Num";
	String columnName2 = "Acct. No.";
	String columnName3 = "Rep ID";
	String columnName4 = "Acct. Num.";
	
	
	//Verifies all reports with their table and title names.
	
		@Test(priority=4500)
		public void avon_ValidateAllTablesWithTitles() throws Exception{
			 logInfo("Entered into avon_ValidateAllTablesWithTitles() test");				
			 loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
		                  prop.getLocatorForEnvironment(appUrl,"newsCon1"));	
			 /*navigateReports();	
			 typesofReports();*/		
		}
		
		//Reports- Select DownLine Report, Send bulk emails to all Downline usres. 
		//@Test(priority=4501)
		public void Report_SendBulkEmailToDownliners() throws Exception{
			 logInfo("Entered into Report_SendBulkEmailToDownliners() test");			
			navigateReports();
			reportstable("3-Day Follow Up") ;
			contactList("Send Bulk Email");
			SendBulkMail(subjectBulk);
		    confirmationMessage("Email sent");
		 
		}


		@Test(priority=4502)
		public void Report_CreateEvent() throws Exception{
			 logInfo("Reports- Select DownLine Report, Send bulk emails to all Downline usres. ");		
			 //String EventText="Appointment With Hanuma Gangadhar";
			 navigateReports();		
			 reportstable("3-Day Follow Up") ;			 
			 contactList("Create Event");
			 createEvent(EventText, 13);	
			 userLogout();
			
		}
		
			
		
	
	@Test(priority=4506)
	public void verifyAdditionsReport() throws Exception{
		logInfo("inside verifyAdditionsReport() Test");
		
		logOutInAvon();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);
		go2AdditionsReportpage();
		showReport("Additions Report",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		
		if(total_rows>=1000){
			go2AdditionsReportpage();
			showReport("Additions Report",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}		
		validateRepresentativeDetailsFromTable("Additions Report","Additions/New Appointments",columnName1);		
	}
	

	
	@Test(priority=4507)
	public void verifyAvonAnniversariesReport() throws Exception{
		logInfo("inside verifyAvonAnniversariesReport() Test");
		
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2AvonAnniversariesReportpage();
		showReport("Avon Anniversaries",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2AvonAnniversariesReportpage();
			showReport("Avon Anniversaries",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
/*		String accNO = returnFirstAccountNoInReport("Avon Anniversaries");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		validateRepresentativeDetailsFromTable("Avon Anniversaries","Avon Anniversaries",columnName2);
		
		
		
	}
	
	
	@Test(priority=4508)
	public void verifyAvonBirthdayReport() throws Exception{
		logInfo("inside verifyAvonBirthdayReport() Test");
		
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2BirthdaysReportpage();
		showReport("Birthday",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2BirthdaysReportpage();
			showReport("Birthday",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
	/*	String accNO = returnFirstAccountNoInReport("Birthday");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		validateRepresentativeDetailsFromTable("Birthday","Birthdays",columnName1);
		
	}

	
	//@Test(priority=4504)
	public void verifyDirectDeliveryReport() throws Exception{
		logInfo("inside verifyDirectDeliveryReport() Test");
		
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);	
		go2DirectDeliveryReportpage();
		showReport("Direct Delivery",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2DirectDeliveryReportpage();
			showReport("Direct Delivery",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
	/*	
		String accNO = returnFirstAccountNoInReport("Direct Delivery");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		validateRepresentativeDetailsFromTable("Direct Delivery","eStore Sales (Direct Delivery)", columnName2);
	}
	
	
	//@Test(priority=4505)
	public void verifyFastTrackReport() throws Exception{
		logInfo("inside verifyFastTrackReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);	
		go2FastTrackReportpage();
		showReport("Fast Track",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2FastTrackReportpage();
			showReport("Fast Track",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
	/*	String accNO = returnFirstAccountNoInReport("Fast Track");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		
		validateRepresentativeDetailsFromTable("Fast Track","Avon Anniversaries", columnName2);
	}
	
	
	@Test(priority=4509)
	public void verifyFollowUpReport() throws Exception{
		logInfo("inside verifyFollowUpReport() Test");
		
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2Followuppage();
		showReport("Follow Up",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2Followuppage();
			showReport("Follow Up",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
	/*	String accNO = returnFirstAccountNoInReport("Follow Up");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		
		validateRepresentativeDetailsFromTable("Follow Up","3-Day Follow Up", columnName1);
	}
	
	
	//@Test(priority=4507)
	public void verifyGeographicalSearchReport() throws Exception{
		logInfo("inside verifyGeographicalSearchReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2GeoSearchReportpage();
		showReport("Geographic Search",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2GeoSearchReportpage();
			showReport("Geographic Search",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
		String accNO = returnFirstAccountNoInReport("Geographic Search");
		System.out.println("accNO =" +accNO);
		go2Window();
		validateRepresentativeDetailsFromTable("Geographic Search","Geographic Search", columnName2);
	}
	
	
	@Test(priority=4510)
	public void verifyHoldOrdersReport() throws Exception{
		logInfo("inside verifyHoldOrdersReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2HoldOrderspage();
		showReport("Hold Orders",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2HoldOrderspage();
			showReport("Hold Orders",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
		String accNO = returnFirstAccountNoInReport("Hold Orders");
		System.out.println("accNO =" +accNO);
		go2Window();
		validateRepresentativeDetailsFromTable("Hold Orders","Hold Orders",columnName1);
	}
	
	
	@Test(priority=4511)
	public void verifyInactivityReport() throws Exception{
		logInfo("inside verifyInactivityReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2InactivityReportpage();
		showReport("Inactivity Report",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2InactivityReportpage();
			showReport("Inactivity Report",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
		String accNO = returnFirstAccountNoInReport("Inactivity Report");
		System.out.println("accNO =" +accNO);
		go2Window();
		
		validateRepresentativeDetailsFromTable("Inactivity Report","No Orders/Inactivity", columnName1);
	}
	
	

	@Test(priority=4512)
	public void verifyLeadershipPerformanceReport() throws Exception{
		logInfo("inside verifyLeadershipPerformanceReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2LeadershipPerfReportpage();
		showReport("Leadership Performance Report",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2LeadershipPerfReportpage();
			showReport("Leadership Performance Report",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
	/*	String accNO = returnFirstAccountNoInReport("Leadership Performance Report");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		validateRepresentativeDetailsFromTable("Leadership Performance Report","Leadership Performance", columnName1);
	}
	
	
	@Test(priority=4513)
	public void verifyNewPromotersReport() throws Exception{
		logInfo("inside verifyNewPromotersReport() Test");
		
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2NewPromotersReportpage();
		showReport("New Promoters",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2NewPromotersReportpage();
			showReport("New Promoters",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		/*
		String accNO = returnFirstAccountNoInReport("New Promoters");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		validateRepresentativeDetailsFromTable("New Promoters","New Promoters",columnName1);
	}
	
	

	@Test(priority=4514)
	public void verifyNoOrdersReport() throws Exception{
		logInfo("inside verifyNoOrdersReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2NoOrdersReportpage();
		showReport("No Orders",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2NoOrdersReportpage();
			showReport("No Orders",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
		String accNO = returnFirstAccountNoInReport("No Orders");
		System.out.println("accNO =" +accNO);
		go2Window();
		validateRepresentativeDetailsFromTable("No Orders","No Orders/Inactivity", columnName1);
	}
	
	
	@Test(priority=4515)
	public void verifyOrdersReport() throws Exception{
		logInfo("inside verifyOrdersReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2OrdersReportpage();
		showReport("Orders",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2OrdersReportpage();
			showReport("Orders",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
		String accNO = returnFirstAccountNoInReport("Orders");
		System.out.println("accNO =" +accNO);
		go2Window();
		validateRepresentativeDetailsFromTable("Orders", "Orders",columnName3);
	}
	
	
	@Test(priority=4516)
	public void verifyPastDueReport() throws Exception{
		logInfo("inside verifyPastDueReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);	
		go2PastDueReportpage();
		showReport("Past Due",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2PastDueReportpage();
			showReport("Past Due",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
	/*	
		String accNO = returnFirstAccountNoInReport("Past Due");
		System.out.println("accNO =" +accNO);
		go2Window();	*/	
		validateRepresentativeDetailsFromTable("Past Due","Past Due", columnName4);
	}
	
	
	@Test(priority=4517)
	public void verifyPendingAppointmentsReport() throws Exception{
		logInfo("inside verifyPendingAppointmentsReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2PendingAppointmentsReportpage();
		showReport("Pending Apointments",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2PendingAppointmentsReportpage();
			showReport("Pending Apointments",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
	/*	
		String accNO = returnFirstAccountNoInReport("Pending Appointments");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		
		validateRepresentativeDetailsFromTable("Pending Appointments","Pending Appointments", columnName4);
	}
	
	
	@Test(priority=4518)
	public void verifyPresidentsClubReport() throws Exception{
		logInfo("inside verifyPresidentsClubReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);	
		go2PresidentsClubReportpage();
		showReport("Presidents Club",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2PresidentsClubReportpage();
			showReport("Presidents Club",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
	/*	
		String accNO = returnFirstAccountNoInReport("Presidents Club");
		System.out.println("accNO =" +accNO);
		go2Window();
		*/
		validateRepresentativeDetailsFromTable("Presidents Club","President's Club", columnName4);
	}
	
	//@Test(priority=4517)
	public void verifyRecognitionReport() throws Exception{
		logInfo("inside verifyRecognitionReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2Recognitionpage();
		showReport("Recognition",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2Recognitionpage();
			showReport("Recognition",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
	/*	String accNO = returnFirstAccountNoInReport("Recognition");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		
		validateRepresentativeDetailsFromTable("Recognition","Recognition", columnName1);
	}
	
	
	@Test(priority=4519)
	public void verifyRemovalsReport() throws Exception{
		logInfo("inside verifyRemovalsReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);	
		go2RemovalsReportpage();
		showReport("Removals",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2RemovalsReportpage();
			showReport("Removals",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
		validateRepresentativeDetailsFromTable("Removals","Removals",columnName4);
	}
	
	
	@Test(priority=4520)
	public void verifyRepresentativeSearchReport() throws Exception{
		logInfo("inside verifyRepresentativeSearchReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2RepSearchReportpage();
		showReport("Representative Search",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2RepSearchReportpage();
			showReport("Representative Search",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
	/*	
		String accNO = returnFirstAccountNoInReport("Representative Search");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		validateRepresentativeDetailsFromTable("Representative Search","Representative Search", columnName1);
	}
	
	
	@Test(priority=4521)
	public void verifySalesReport() throws Exception{
		logInfo("inside verifySalesReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2SalesReportpage();
		showReport("Sales",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2SalesReportpage();
			showReport("Sales",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
	/*	
		String accNO = returnFirstAccountNoInReport("Sales");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		
		validateRepresentativeDetailsFromTable("Sales","Sales", columnName4);
	}
	
	//@Test(priority=4521)
	public void verifyTitleAdvancementsReport() throws Exception{
		logInfo("inside verifyTitleAdvancementsReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);	
		go2TitleAdvancementsReportpage();
		showReport("Title Advancements",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2TitleAdvancementsReportpage();
			showReport("Title Advancements",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
/*		String accNO = returnFirstAccountNoInReport("Title Advancements");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		
		validateRepresentativeDetailsFromTable("Title Advancements","Title Advancements",columnName1);
	}
	
	
	
	
	//@Test(priority=4522)
	public void verifyTitleDemotionsReport() throws Exception{
		logInfo("inside verifyTitleDemotionsReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2DemotionsReportpage();
		showReport("Title Demotions",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2DemotionsReportpage();
			showReport("Title Demotions",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
	/*	String accNO = returnFirstAccountNoInReport("Title Demotions");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		
		validateRepresentativeDetailsFromTable("Title Demotions","Title Demotions", columnName1);
	}
	
	
	//@Test(priority=4523)
	public void verifyTitlesAtRiskReport() throws Exception{
		logInfo("inside verifyTitlesAtRiskReport() Test");
		userLogotNdAdminLogout();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);		
		go2TitlesAtRiskReportpage();
		showReport("Title At Risk",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First, Second and Third");
		int total_rows = getRowsCntInGrid();
		if(total_rows>=1000){
			go2TitlesAtRiskReportpage();
			showReport("Title At Risk",prop.getLocatorForEnvironment(appUrl,"newsCon1"),"First");
		}
		
/*		String accNO = returnFirstAccountNoInReport("Title At Risk");
		System.out.println("accNO =" +accNO);
		go2Window();*/
		
		validateRepresentativeDetailsFromTable("Title At Risk","Title At Risk", columnName1);
	}
	
	
	
	
	
	//Verifies all reports with their table and title names.
	
	
	

	//@Test(priority=3702)
	public void verifyAvonLeadershipPerformanceReport() throws Exception{
		logInfo("inside verifyLeadershipPerformanceReport() Test");
		
		logOutInAvon();
		login2AvonLeadership(prop.getLocatorForEnvironment(appUrl,"newsCon1"),pwd);	
		go2LeadershiPerformancepage();		
		showReport(prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		getRowsCntInLeadershipDlp();		
		verifyAccountIsPresent("76074010");
		foucsOnChildWindow();		
		//validateRepresentativeDetailsFromTable();
		
	}
	
	
	
	//@Test(priority=3703)
	public void avon_Validate() throws Exception{
		 logInfo("Entered into avon_ValidateAllTablesWithTitles() test");	
	
		// nm.loginAsUser(prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		
		 navigateReports();	
		 reportstable("Leadership Performance") ;
		
		 selectAllFilterOptions(columnName1);		 
		 
		 selectRepresentativeDetails("Acct. Num", "76074010");  //12142554  14756807
		 profileSections("Profile");
		 personalProfile("Account Number", "76074010");			 
		 personalInfomation("Birthday", "April 28");
		 //salesPerformance(total_cmp,avg_cmp,py_award_sales,cy_award_sales );
		
	}
	
	
//	@Test(priority=4524)
	public void Report_CreateTaskAndVerifyInWidgets() throws Exception{
		logInfo("Select DownLine Table and Create New Task with future Date from Reports. \n"
				+ "Verify the same task in future Tasks Widget. Delete the same task");		
		//String  taskEvent1 ="Especially task is created for you with TaskId_ 119";
		
		//nm.loginAsUser(prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		
		navigateReports();
		reportstable("3-Day Follow Up") ;
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
	

	
	

}
