package vibe.reports.tests;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.tasks.tests.TaskMethods;


@Priority(45)
public class IDLVReportsTest extends IDLVReportsMethods{
	
	TaskMethods tk = new TaskMethods();
	 readProp prop = new readProp();
		ReportsMethods rm = new ReportsMethods();
	 
	//Fetch All types of reports, retrieve all Constriants with respective to Reports		
	
	@Test(priority=4501)
	public void reports_ValidateAllTablesWithTitles() throws Exception{
		 logInfo("Entered into reports_ValidateAllTablesWithTitles() Test");
		 loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		/* navigateReports();	
		 typesofReports();*/		
	}
	
	//@Test(priority=4502)
	public void temp() throws Exception, InterruptedException{
		Thread.sleep(5000);
		driver().navigate().back();
		Thread.sleep(5000);
		//clickOnLink("linkText", "Back");
	}	
	
	@Test(priority = 4511)
 	public void report_CreateEvent() throws Exception{
 	logInfo("inside Report_CreateEvent test");
 	nav2ReportsfromMenu("Anniversary");
	exportTo("Create Event");
	 createEvent(EventText, 45);
 	  }
	
	@Test(priority = 4512)
 	public void report_SendBulkEmailToDownliners() throws Exception{
 	logInfo("inside Report_SendBulkEmailToDownliners test");
 	nav2ReportsfromMenu("Anniversary");
	 exportTo("Send Bulk Email");
	 SendBulkMessage(subjectBulk);
	 confirmationMessage("Email sent");

 		}
 	
 	 	
	@Test(priority = 4513)
 	public void report_AnniversaryReportSendEcard() throws Exception{
 	logInfo("inside report_AnniversaryReportSendEcard test");
 	profile.navigateMyProfileAccount("Notifications");
	String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
	System.out.println(selfmailId);	
 	nav2ReportsfromMenu("Anniversary");
	exportTo("Send Ecard");
	sendEcard(selfmailId);
	
 		}
	
	@Test(priority=4520)
	public void report_AnniversaryReportResultValidation() throws Exception{	
		logInfo("inside report_AnniversaryReportResultValidation test");		
		nav2ReportsfromMenu("Anniversary");
		reportResultValidation();		
	}
	
	

	@Test(priority = 4525)
	public void report_AnniversarySaveReport() throws Exception{	 
	 logInfo("inside report_AnniversaryReportFilterOptions test");
	 nav2ReportsfromMenu("Anniversary");
	 saveReport();
	 boolean isReportFound = verifySavedReportPresent(savedReport_text);
	 
	if(isReportFound==true){
			System.out.println("match found..");
			deleteSavedReport(savedReport_text);
			
			}
	
 }
 

 	@Test(priority = 4526)
 	public void report_AnniversaryReportExporttoCSV() throws Exception{
 	logInfo("inside report_AnniversaryReportExporttoCSV test");
 	nav2ReportsfromMenu("Anniversary");
	exportTo("Csv");
	//validateExportMsg();
 		}
 	
 	

 	@Test(priority = 4527)
 	public void report_AnniversaryReportExporttopdf() throws Exception{
 	logInfo("inside report_AnniversaryReportExporttopdf test");
 	nav2ReportsfromMenu("Anniversary");
	exportTo("Pdf");
 		}
 	

 	@Test(priority = 4528)
 	public void report_AnniversaryReportExporttoTabDelimited() throws Exception{
 	logInfo("inside report_AnniversaryReportExporttoTabDelimited test");
 	nav2ReportsfromMenu("Anniversary");
	exportTo("Tab Delimited");
 		}
 	
 	
 	
 
	@Test(priority = 4531)
 	public void report_AnniversaryReportLabels() throws Exception{
 	logInfo("inside report_AnniversaryReportSendEcard test");
 	nav2ReportsfromMenu("Anniversary");
	exportTo("Labels");
 		}
 		
	@Test(priority=4532)
	public void report_AssocaiteSearchReportResult() throws Exception{	
		logInfo("inside report_AssocaiteSearchReportResult test");
		nav2ReportsfromMenu("Associate Search.");
		reportResultValidation();
	}
	
	@Test(priority=4533)
	public void report_BirthdayReportResult() throws Exception{	
		logInfo("inside report_BirthdayReportResult test");
		nav2ReportsfromMenu("Birthdays");
		reportResultValidation();
	}
	
	@Test(priority=4534)
	public void report_ClosetoRankReportResult() throws Exception{	
		logInfo("inside report_ClosetoRankReportResult test");
		nav2ReportsfromMenu("Close To Rank");
		reportResultValidation();
	}
	
	
	@Test(priority=4535)
	public void report_CommissionsReportResult() throws Exception{	
		logInfo("inside report_CommissionsReportResult test");
		nav2ReportsfromMenu("Commissions Report");
		reportResultValidation();
	}
	
	@Test(priority=4536)
	public void report_CustomerSearchReportResult() throws Exception{	
		logInfo("inside report_CustomerSearchReportResult test");
		nav2ReportsfromMenu("Customer Search");
		reportResultValidation();
	}
	
	@Test(priority=4537)
	public void report_FirstTimeSponsorReportResult() throws Exception{	
		logInfo("inside report_FirstTimeSponsorReportResult test");
		nav2ReportsfromMenu("First Time Sponsor");
		reportResultValidation();
	}
	
	@Test(priority=4538)
	public void report_HouseholdCustomrsReportResult() throws Exception{	
		logInfo("inside report_FirstTimeSponsorReportResult test");
		nav2ReportsfromMenu("Household Customers");
		reportResultValidation();
	}
	
	@Test(priority=4539)
	public void report_NewAssociateReportResult() throws Exception{	
		logInfo("inside report_FirstTimeSponsorReportResult test");
		nav2ReportsfromMenu("New Associate");
		reportResultValidation();
	}
	
	
	@Test(priority=4540)
	public void report_NewCustomerReportResult() throws Exception{	
		logInfo("inside report_NewCustomerReportResult test");
		nav2ReportsfromMenu("New Customers");
		reportResultValidation();
	}
	
	@Test(priority=4541)
	public void report_SponsorsReportResult() throws Exception{	
		logInfo("inside report_SponsorsReportResult test");
		nav2ReportsfromMenu("Sponsors Report");
		reportResultValidation();
	}
	
	@Test(priority=4545)
	public void report_VolumeByPeriodReportResult() throws Exception{	
		logInfo("inside report_VolumeByPeriodReportResult test");
		nav2ReportsfromMenu("Volume By Period");
		reportResultValidation();
	}
	
	@Test(priority=4560)
	public void userLogoutFromReports() throws Exception{
		logInfo("inside loginAsAdminFromReports() Test");
		
		userLogout();
		
		
	}
	
	
	
}