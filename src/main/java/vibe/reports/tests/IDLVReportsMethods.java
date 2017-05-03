package vibe.reports.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Asserts;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.sun.org.apache.bcel.internal.classfile.Method;

import common.TestBase;
import common.readProp;
import static java.util.Arrays.asList;

import org.openqa.selenium.By.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import vibe.ecards.tests.EcardMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.tasks.tests.TaskMethods;

public class IDLVReportsMethods extends TestBase {
	
	EcardMethods em = new EcardMethods();
	readProp prop = new readProp();
	MyProfileMethods profile = new MyProfileMethods();
	InboxMethods inbox = new InboxMethods();
	
	public void navigateReports() throws Exception{
		logInfo("Navigating to Reports ");				
		try{	
			clickOnElement("cssSelector", REPORTS_tab);			
			driver().navigate().to(appUrl+"reports/run_report");
	
		}catch (NoSuchElementException nse){
			System.err.println("Failed!! No such element is found" );
			
		}
	}
	
	public void nav2TreeViewReports() throws Exception, InterruptedException{
		logInfo("Navigating to Reports ");				
		try{	
			clickOnElement("cssSelector", REPORTS_tab);	
			clickOnLink("linkText", "Tree View");
			Thread.sleep(5000);
	
		}catch (NoSuchElementException nse){
			System.err.println("Failed!! No such element is found" );
			
		}
	}
	
	public void nav2AnniversaryReports() throws Exception, InterruptedException{
		logInfo("inside nav2AnniversaryReports method");
		logInfo("Navigating to Reports ");				
		try{	
			clickOnLink("linkText", "Reports");
			clickOnLink("linkText", "Anniversary");
		
		}catch (NoSuchElementException nse){
			System.err.println("Failed!! No such element is found" );
			
		}
	}
	
	public void nav2ReportsfromMenu(String reportName) throws Exception{
		
		logInfo("inside nav2ReportsfromMenu method ");				
		try{	
			clickOnLink("linkText", "Reports");
			clickOnLink("linkText", reportName);
		
		}catch (NoSuchElementException nse){
			System.err.println("Failed!! No such element is found" );
		}		
		
		
	}
	public void nav2savedReports(String savedReport) throws Exception, InterruptedException{
		logInfo("inside nav2savedReports method");
				
		try{	
			navigateReports	();
			clickOnLink("linkText", savedReport);
		
		}catch (NoSuchElementException nse){
			System.err.println("Failed!! No such element is found" );
			
		}
	}
	
	public void secondaryListUnderReports() throws Exception{
		logInfo("Retreive all secondary content under Reports ");
		try{
			
			List <WebElement> reportsLists = driver().findElements(By.cssSelector(reportsList));
			System.out.println("Count of Secondary options is "+reportsLists.size());
			for (WebElement list : reportsLists){
				System.out.println("option  -  "+list.getText());
			}
	
			}catch(Exception e){
				System.err.println("Failed!!  not able to retreive list of Reports ");
						}
			
		}
		
	//"Retreive Report Titles from Table and select link of table 
	public void reportstable(String report) throws Exception{	 
		logInfo("Entered into reportstable(String report)");	
		
			boolean isTablePresent = false;
			waitOnElement("xpath", reportTableFrame);
			List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
			for (int i =2; i<=reportstable.size(); i++)			
			{						
				WebElement rp = driver().findElement(By.xpath(rp1+i+rp2))	;
				System.out.println(rp.getText());
				if (rp.getText().equalsIgnoreCase(report)){		
					isTablePresent = true;
					rp.click();					
					break;
				}
			}if (isTablePresent==false){
				Assert.assertTrue(isTablePresent,  report + " - report is not present.");
			}
		
			
		}	
	
	public void typesofReports() throws Exception, Exception{		 
		logInfo("Assertions all types of reports with their Table title.");					
			List <WebElement> reportstable = driver().findElements(By.cssSelector(rpTab));
			System.out.println("No of Reports in the table is "+reportstable.size());
			for (int i=2; i<=reportstable.size()+1-6; i++){				
			WebElement title = driver().findElement(By.cssSelector(rptitle1+i+rptitle2));
			String ReportTitle = title.getText().toLowerCase();		
			System.out.println("ReportTitle is "+ReportTitle);				
			waitOnElement("cssSelector", rptitle1+i+rptitle2);
			title.click();
			if(driver().findElement(By.cssSelector(error404)).getText().equals(notImplimentedText)){
				WebElement errMsg = driver().findElement(By.cssSelector(notImplimented));
				System.out.println(errMsg.getText());
				Assert.assertEquals(errMsg.getText(), ReportTitle);	
				
			}else{			
			WebElement pageTit = driver().findElement(By.cssSelector(repTitle));
			String pageTitle = pageTit.getText().toLowerCase();
			System.out.println("page  Title is "+ pageTitle);
			Assert.assertEquals(ReportTitle,pageTitle);	
			waitOnElement("cssSelector", repTitle);
			navigateReports();
			
			}}
			
	}

	public void partialImplementation () throws Exception{
		if (driver().findElements(By.cssSelector(consts)).size() !=0 && !"".equals(driver().findElement(By.cssSelector(consts)).getText()))
		{
		getReportConstriants();				
		clickRunReport();
		navigateReports();
		implicityWaits(20);
		} 
	}
	
	 public void exportList(String eportList) throws Exception{
		 Thread.sleep(4000);
		 boolean isInExport = false;
		 clickOnElement("cssSelector", exportBtn);
		 Thread.sleep(2000);		
		 List <WebElement> exList = driver().findElements(By.cssSelector(exportdp));
		 System.out.println(exList.size());
		 for (WebElement el : exList){		
		    System.out.println(el.getText());
			if (el.getText().equalsIgnoreCase(eportList)){
				isInExport = true;
				el.click();
			}
		 }if (isInExport==false){
			 Assert.assertTrue(isInExport, eportList + " is not present in Export options");
		 }

	 }
	
	 
	 public void createTaskFromExportTo(String task, int noOfDaysToCurrent) throws Exception{
		  
			logInfo(" Create a Task from ExportTo dropdown and ");
			String enterDate = getDate(noOfDaysToCurrent, "MM/dd/yyyy")	;
			Thread.sleep(5000);
			inputText("cssSelector", exDesc, task);
			/*inputText("cssSelector", exDueDate, enterDate);*/
			Thread.sleep(5000);
			inputText("cssSelector", exNotes, "Sample Test");
			clickOnButton("cssSelector", exCreatask);		

	 }
	 
	 public void SendBulkMessage(String message) throws Exception{
		  
		logInfo("Send Bulk Email to all with Subject & Message. ");
		WebElement selAl =  driver().findElement(By.cssSelector(bulkSelectAll));
		Thread.sleep(5000);
		inputText("cssSelector", bulkSubject, message); 
		composeText("xpath",composeBody,composeBodyText);
		inputText("cssSelector", bulkMsg, msgText);		
		if (!selAl.isSelected()){
			selAl.click();			
		}	
		clickOnButton("cssSelector", bulkSendEMail);	

	 }
	 
	 public void createEvent(String event, int noOfDaysToCurrent) throws Exception{
		  
		logInfo("Create Event from Reports");
		String eventDate = getDate(noOfDaysToCurrent, "MM/dd/yyyy")	;	
		String endDate = getDate(noOfDaysToCurrent+2, "MM/dd/yyyy")	;
		clickOnLink("linkText", "Add Event");
		Thread.sleep(5000);
		inputText("cssSelector", evName, EventText); 
		inputText("cssSelector",sDate, eventDate ); 
		inputText("cssSelector",eDate, endDate );
		clickOnButton("cssSelector", eventSave);
		confirmationMessage("Your event has been created.");
		Thread.sleep(7000);
		selectEvent(event);
		inputText("cssSelector",sub, EventText ); 
		inputText("cssSelector",eventbody, subjectBulk);
		clickOnButton("cssSelector", sendInv);
		confirmationMessage("Invitations Sent");
		Thread.sleep(5000);

	 }
	 
	 public void deleteCalendarEvent(String Event) throws Exception{
			 
		logInfo("inside deleteCalendarEvent() method...");
		clickOnLink("linkText", lnkBusiness);
		clickOnLink("linkText", lnkCalendar);
		
		// Verify if the event is present in the dashboard.
		
		implicityWaits(10);
		
		List<WebElement> allElements = driver().findElements(By.cssSelector("tbody > tr > td > a > div > span.fc-title")) ;          //(By.xpath(eleMatchEvent));
		System.out.println("get siz e is "+allElements.size());	
		for(WebElement element: allElements){
				System.out.println("Evenets"+element);
				if(element.getText().equalsIgnoreCase(Event)){
					System.out.println("events are "+element.getText());
					System.out.println(Event + " - Event match found ...");
					element.click();
					
					break;
				}
			}
			
	}
	 
	 public void deleteEvent(String Event) throws Exception{
		
			logInfo("inside deleteEvent() method...");
			
			clickOnButton("xpath",btnDeleteEvent);
		
			Thread.sleep(2000);
			
			try{
				 (new Robot()).keyPress(java.awt.event.KeyEvent.VK_ENTER);
				 (new Robot()).keyRelease(java.awt.event.KeyEvent.VK_ENTER);
			} catch (Exception e){
				
			}
			
			boolean isElementRemoved = false;
			List<WebElement> allElements = driver().findElements(By.xpath(eleMatchEvent));
			for(WebElement element: allElements){
				
				if (element.getText().equalsIgnoreCase(Event)){
					isElementRemoved = false;
				}
			}
				
			if (isElementRemoved=true){
					System.out.println(Event + " is removed");
					logInfo(Event + " is removed");
				} else {
					System.out.println(Event + " is not removed");
					logInfo(Event + " is not removed");
					
			}
				
		}
	 
	public void selectEvent (String EventText ) throws Exception{	
		//clickOnElement("cssSelector ", eventID);
		Thread.sleep(2000);
		WebElement eve = driver().findElement(By.cssSelector(eventID));		
		Select sel = new Select (eve);
		sel.selectByVisibleText(EventText);		
	}
		
	 public void getColumnHeadersInDownLineTable(String sort) throws InterruptedException{
		 
		 List <WebElement> tb = driver().findElements(By.cssSelector(tabContent));		 
		 for (int i =1 ; i<=tb.size(); i++ ){
			 WebElement col = driver().findElement(By.cssSelector(col1+i+col2));			
	      if (col.getText().equalsIgnoreCase(sort)){	    	
	    	col.click();
	    	Thread.sleep(5000);
	    	break;
	    }
			 
		 }

	 }
	 

	public void retrieveDataInDownLineTable() throws InterruptedException{	
			 List <WebElement> tb = driver().findElements(By.cssSelector(dataTable));		 
			 for (WebElement tbs: tb ){				 
				 System.out.println(tbs.getText());			 
			 }		 
	}


	public void selectNoOderLink() throws Exception{
		 
		logInfo("Selecting No Order Link from the table ");		
		try{			
			doubleClick("cssSelector", noOrder);
			
			/*List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
			for (WebElement table : reportstable){
				Actions action = new Actions(driver);		
				if(table.getText().equals("No Orders")){
				//	Thread.sleep(2000);
					action.moveToElement(table).doubleClick().clickAndHold().release().build().perform();					
					break;
				}				
			}*/}catch(Exception e){
				System.err.println("Failed!!  No Orders option is not available");
						}
	
	}
			
	public void selectCommissionsReportLink() throws Exception{
		 
		logInfo("Selecting Commissions Report Link from the table ");		
		try{
			
			doubleClick("cssSelector", commision);
			/*List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
			for (WebElement table : reportstable){
				Actions action = new Actions(driver);				
				if(table.getText().equals("Commissions Report")){					
					action.moveToElement(table).doubleClick().clickAndHold().release().build().perform();					
					break;
				}				
			}*/}catch(Exception e){
				System.err.println("Failed!!  Commissions Report option is not available");
						}
					}
	
	public void selectVolumeByPeriodLink() throws Exception{
		 
		logInfo("Selecting Volume By Period Report Link from the table ");		
		try{			
			doubleClick("cssSelector", volumeByperiod);
			/*List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
			for (WebElement table : reportstable){
				Actions action = new Actions(driver);					
				if(table.getText().equals("Volume By Period")){					
					action.moveToElement(table).doubleClick().clickAndHold().release().build().perform();					
					break;
				}				
			}*/}catch(Exception e){
				System.err.println("Failed!!  Volume By Period option is not available");
						}
			
		}
	
	public void selectDownlineSearchLink() throws Exception{
		 
		logInfo("Selecting Downline Search Report Link from the table ");		
		try{
			doubleClick("cssSelector", downlineSearch);
			/*
			List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
			for (WebElement table : reportstable){
				Actions action = new Actions(driver);				
				if(table.getText().equals("Downline Search")){					
					action.moveToElement(table).doubleClick().clickAndHold().release().build().perform();					
					break;
				}				
			}*/}catch(Exception e){
				System.err.println("Failed!!  Downline Search option is not available");
						}
			
		}
	
	public void selectOrdersReportLink() throws Exception{
		 
		logInfo("Selecting Orders Report Link from the table ");		
		try{			
			doubleClick("cssSelector", ordersReport);
			/*List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
			for (WebElement table : reportstable){
				Actions action = new Actions(driver);				
				if(table.getText().equals("Orders Report")){					
					action.moveToElement(table).doubleClick().clickAndHold().release().build().perform();					
					break;
				}				
			}*/}catch(Exception e){
				System.err.println("Failed!!  Orders Report option is not available");
						}
			
		}
	
	public void selectGeneralReportLink() throws Exception{
		 
		logInfo("Selecting General Report Link from the table ");		
		try{
			doubleClick("cssSelector", generalReport);
			/*List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
			for (WebElement table : reportstable){
				Actions action = new Actions(driver);					
				System.out.println("table option us "+table.getText());
				if(table.getText().equals("General Report")){					
					action.moveToElement(table).doubleClick().clickAndHold().release().build().perform();					
					break;
				}				
			}*/}catch(Exception e){
				System.err.println("Failed!!  General Report option is not available");
						}
			
		}
	
	public void selectAnniversaryReportLink() throws Exception{
		 
		logInfo("Selecting Anniversary Report Link from the table ");		
		try{	
			doubleClick("cssSelector", anniversary);
			/*List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
			for (WebElement table : reportstable){
				Actions action = new Actions(driver);					
				System.out.println("table option us "+table.getText());
				if(table.getText().equals("Anniversary")){					
					action.moveToElement(table).doubleClick().clickAndHold().release().build().perform();					
					break;
				}				
			}*/}catch(Exception e){
				System.err.println("Failed!! Anniversary option is not available");
						}
					}
	
	public void selectBirthdayReportLink() throws Exception{
		 
		logInfo("Selecting Birthday Report Link from the table ");		
		try{	
		//	doubleClick("cssSelector", birthDayReport);
			clickOnLink("linkText", "Reports");
			clickOnLink("linkText", "Anniversary");
			/*List <WebElement> reportstable = driver().findElements(By.xpath(reportTableFrame));
			for (WebElement table : reportstable){
				Actions action = new Actions(driver);					
				System.out.println("table option us "+table.getText());
				if(table.getText().equals("Birthday Report")){					
					action.moveToElement(table).doubleClick().clickAndHold().release().build().perform();					
					break;
				}				
			}*/}catch(Exception e){
				System.err.println("Failed!!  Birthday Report option is not available");
						}
			
		}
	
	
	
	public boolean isExportToPresent(){
		return getexportTo().matches(exportTo_TEXT);
		
	}
	
	public String getexportTo(){
		 
		logInfo("Matches - ExportTo");
		return driver().findElement(By.cssSelector(exportToButton)).getText();
	}
	
	public boolean isFilterOptionsPresent(){
		 
		logInfo("Matches - Filter Options");
		return getFilterOptions().matches(filterOptions_TEXT);
		
	}
	
	public boolean isNoOrderTitlePresent(){
		 
		logInfo("Matches - Title");
		return getNoOrderTitle().matches(titleNoOrder_TEXT);
		
	}
	
	public boolean isSaveReportPresent(){
		 
		logInfo("Matches - Save Report Button");
		return getSaveReport().matches(saveReport_TEXT);
		
	}
	
	public boolean isRunReportPresent(){
		 
		logInfo("Matches - Run Report button");
		return getRunReport().matches(runreport_TEXT);
		
	}
	
	public String getFilterOptions(){
		return driver().findElement(By.cssSelector(filterOptionsButton)).getText();
	}
	
	public String getNoOrderTitle(){
		return driver().findElement(By.cssSelector(titleNoOrder)).getText();
	}
	
	public String getSaveReport(){
		return driver().findElement(By.cssSelector(saveReport)).getText();
	}
	
	public String getRunReport(){
		return driver().findElement(By.cssSelector(runReport)).getText();
	}
	
	public void constraints() throws Exception{
		
		if (driver().findElements(By.cssSelector(consts)).size() !=0 && !"".equals(driver().findElement(By.cssSelector(consts)).getText()))
		{
			
			partialImplementation ();
			
			
		}else  if (driver().findElement(By.cssSelector(notImplimented)).getText().equals(notImplimentedText)){
			System.out.println("****This reported Page is not yet implemented***");						
			driver().navigate().back();
		}else {
			clickOnElement("cssSelector", filterOptionsButton);
			partialImplementation ();
		}}
		
	
	
	
	public void getReportConstriants() throws Exception{
		 
		logInfo("Retrieve all constraints from Report ");		
		try{
			clickOnElement("cssSelector", filterOptionsButton);
			
			WebElement co = driver().findElement(By.cssSelector(consts));
			System.out.println(co.getText());
			
			List <WebElement> constraint = driver().findElements(By.cssSelector(generalReportBody));
			System.out.println("The number of "+constraint.size() + " constraints are available." );
			for (WebElement constraints : constraint){								
				System.out.println("Constraints are : "+constraints.getText());						
			}}catch(Exception e){
				System.err.println("Failed!!  To obtain constraints of Report");
						}
		
					
		}
	
	
	public void clickSaveReport() throws Exception{
		 
		logInfo("clicking Save Report Button in Reports");	
		clickOnButton("xpath", saveReport);
		
	}
	
	public void clickRunReport() throws Exception{
		 
		logInfo("clicking Run Reports Button in Reports");	
		clickOnButton("cssSelector", runReport);
		
	}
	
	public void handleTreeView() throws Exception{
		
		/*Tree_SelectRanks("Diamond");
		Tree_SelectNodes();
		Tree_SelectLevel(2);
		Tree_SelectDownLineSource("Sponsor");;
		Tree_SelectTreeType("Zoomable Partition Ayout");
		Tree_High("Highlight Inactive Nodes");*/
		Tree_SVG();
	}
	
	public void closeTreeViewScreen() throws Exception{
		
		clickOnElement("cssSelector", prop.getLocatorForEnvironment(appUrl,"btnCloseWebsite"));
		Thread.sleep(5000);
		
	}
	
	public void Tree_SelectRanks(String Rank) throws Exception{
		 
		logInfo("Select Rank in Tree View Report");	
		WebElement nod =  driver().findElement(By.cssSelector(treRank));
		
		Select sel = new Select(nod);
		sel.selectByVisibleText(Rank);
		
		
		
	}
	public void Tree_SelectTreeType(String treeType) throws Exception{
		 
		logInfo("Select Tree Type in Tree View Report");	
		/*WebElement nod =  driver().findElement(By.cssSelector(treRank));
		
		Select sel = new Select(nod);
		sel.selectByVisibleText(treeType);*/
		WebElement nod =  driver().findElement(By.cssSelector(treType));
		nod.click();
		List <WebElement> rd = driver().findElements(By.cssSelector(treType));		
		for (WebElement rr:rd){
			System.out.println("Types are  "+rr.getText());
			if (rr.getText().equals(treeType))			
			rr.click();
			break;
		}
		
		
		
		
	}
	
	public void Tree_SelectNodes() throws Exception{
		 
		logInfo("Retrieve Nodes in Tree View Report");	
		WebElement nod =  driver().findElement(By.cssSelector(treNode));
		
		Select sel = new Select(nod);
		sel.selectByVisibleText("Highlighted Nodes");

	}
	
	public void Tree_SelectLevel(int level) throws Exception{
		 
		logInfo("Select Level  in Tree View Report");	
		WebElement nod =  driver().findElement(By.cssSelector(treLevDep));
		String value = String.valueOf(level);
		Select sel = new Select(nod);
		sel.selectByVisibleText(value);	
		
	}
	
	public void Tree_High(String radio){
		
		List <WebElement> rd = driver().findElements(By.cssSelector(treHiLightRadio));
		System.out.println(" Radios are"+ rd.size());
		for (WebElement rr:rd){
			if (rr.getText().equalsIgnoreCase(radio)){
			System.out.println("items are "+rr.getText());
			if (!rr.isSelected()){
				rr.click();
				break;
			}
		}}
		
		
	}
	
	public void Tree_SelectDownLineSource(String source) throws Exception{
		 
		logInfo("Select DownLine Source in Tree View Report");	
		WebElement nod =  driver().findElement(By.cssSelector(treDownSource));
		
		Select sel = new Select(nod);
		sel.selectByVisibleText(source);
		
	}
	  
	public void Tree_Submit(){
		 
		logInfo("Select Submit in Tree View report");
		clickOnElement("cssSelector", treSubmit);
		
	}
	  
	public void Tree_SendMessage() throws Exception{
		 
		logInfo("Select Send Message in Tree View report");
		clickOnLink("linkText", treMsg);
		
	}
	
	public void Tree_ViewProfile() throws Exception{
		 
		logInfo("Select SView Profile in Tree View report");
		clickOnLink("linkText", treProfile);
		
	}
	
	public void Tree_Follow() throws Exception{
		 
		logInfo("Select Follow link in Tree View report");
		clickOnLink("linkText", treeFollow);
		
	}
	
	public void Tree_SVG() throws Exception, InterruptedException{
		 
		logInfo("Click on SVG in Tree View.");
		WebElement svg = driver().findElement(By.cssSelector(treImg));
		svg.click();	
		
		 Actions dragger = new Actions(driver());
	        WebElement draggablePartOfScrollbar = driver().findElement(By.cssSelector(treImg));

	        // drag downwards
	        int numberOfPixelsToDragTheScrollbarDown = 100;
	        for (int i=100;i<10000;i=i+numberOfPixelsToDragTheScrollbarDown){
	            try{
	        // this causes a gradual drag of the scroll bar, 100 units at a time
	        dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
	       
	            }catch(Exception e1){}
	        } 

	        // now drag opposite way (downwards)
	        numberOfPixelsToDragTheScrollbarDown = -50;
	        for (int i=500;i>10;i=i+numberOfPixelsToDragTheScrollbarDown){
	        // this causes a gradual drag of the scroll bar, -10 units at a time
	        dragger.moveToElement(draggablePartOfScrollbar).clickAndHold().moveByOffset(0,numberOfPixelsToDragTheScrollbarDown).release().perform();
	       
	        }
	}
	 
	
	// **********************************user specific tests*************************************************//
	
	public void navigate2AdminReport(){
		logInfo("inside navigate2AdminReport() method..");
		driver().navigate().to(appUrl + "reports/reports");
	}
	
	public void navigate2Report(){
		logInfo("inside navigate2Report() method..");
		driver().navigate().to(appUrl + "reports/run_report");
	}
	
	
	public void createNewReport(String reportName) throws Exception{
		logInfo("inside createNewReport() method..");
		verifyLinkPresent("Create Report");
		clickOnLink("linkText","Create Report");
		
		verifyElementPresent("xpath",inputReportName);
		inputText("xpath",inputReportName,reportName);
		inputText("xpath",textareaReportDesc,reportDesc_text);
		inputText("xpath",inputActiveStart,activeStart_text);
		selectRadioOrCheckbox("xpath",chkReportNoEndDate);
		
		WebElement securityRoles = driver().findElement(By.xpath(divSecurityRoles));
		List allChks = securityRoles.findElements(By.tagName("span"));
		int all_chks = allChks.size();
		
		String before_chk = "//*[@id='new_reports_report']/div/div[2]/div/span[";
		String after_chk = "]/label/input";
		
		for(int i=1;i<=all_chks;i++){
			WebElement x = driver().findElement(By.xpath(before_chk+i+after_chk));
			x.click();
		}
	
		clickOnButton("xpath",btnContinue);
		logInfo("submitting report for " +reportName_text);
		
		// 2nd page
		
		verifyElementPresent("xpath",eleTreePlacement);
		clickOnElement("xpath",eleTreePlacement);
		selectRadioOrCheckbox("xpath",chkUplineConstraint);
		selectRadioOrCheckbox("xpath",chkOutputConstraint);
		clickOnButton("xpath",btnP2Continue);
		Thread.sleep(5000);
		clickOnButton("xpath",btnP2Continue);
					
		// 4th page
		
		verifyLinkPresent("4");
		clickOnLink("linkText","4");
	//	verifyElementPresent("xpath",chkReportPublished);
		selectRadioOrCheckbox("xpath",chkReportPublished);
		clickOnButton("cssSelector",btnReportSubmit);
	}
	
	public boolean verifyReportPresent(String reportName) throws Exception{
		logInfo("inside verifyReportPresent() method..");
		WebElement e = driver().findElement(By.xpath(tblReport));
		List allRows = e.findElements(By.tagName("tr"));
		int all_rows = allRows.size();
		String before_row = "//*[@id='main-content']/div/table/tbody/tr[";
		String after_row = "]/td[1]/a";
		boolean isMatchFound = false;
		for(int i=2;i<=all_rows;i++){
			WebElement x = driver().findElement(By.xpath(before_row+i+after_row));
			String title = x.getText().trim();
			if(title.equalsIgnoreCase(reportName)){
				isMatchFound = true;
				logInfo(reportName +" report match found.");
				break;
			}
		}
		
		if(isMatchFound==false){
			logInfo(reportName +" report match not found.");
			Assert.assertTrue(isMatchFound, reportName + " report not found in reports page.");
		}
		return isMatchFound;
		
	}
	

	   public int resultFound() throws Exception{	   
		   logInfo("Inside resultFound method");
		   Thread.sleep(5000);
		   waitOnElement("cssSelector", result);
		   WebElement count = driver().findElement(By.cssSelector(result));
		   String resCount = count.getText().trim();
		   int resultCount = Integer.parseInt(resCount);	   
		   System.out.println("Result found is "+resultCount );
		   return resultCount;
		   
	   }
   
	  public void reportResultValidation() throws Exception{ 
		  logInfo("Inside reportResultValidation method");
		  int expectcdResultCount = resultFound();		 
		    Thread.sleep(5000);
			List<WebElement> rowResult = driver().findElements(By.cssSelector(rowResultsCount));
			int actualResultCount = rowResult.size();			
			System.out.println("Actuals Row results found " +actualResultCount );
			if ((actualResultCount==expectcdResultCount)||(actualResultCount==(expectcdResultCount+1))||(actualResultCount==(expectcdResultCount-1))){
				System.out.println("Success!! count is around expected "+actualResultCount);
				
			}else{
			Assert.assertEquals(actualResultCount, expectcdResultCount);
			}
	  }
	 
	  // To deselect all the output fields and select the fields based on the arraylist
	    public void selectFilterOption(String[] filterOption1) throws Exception{
	    	logInfo("inside selectFilterOption method");
	    	clickOnElement ("cssSelector",filterOp );
	
	 	    boolean isOptionPresent = false; 
	 	   
	 		List <WebElement> op1 = driver().findElements(By.cssSelector(outputFields)); 
	 	
	 		for(WebElement op : op1){	 			
	 			op.click();
	 		}
	 		List <WebElement> op2 = driver().findElements(By.cssSelector(outputFilterOptions)); 
	 		int cntOutputVal = op2.size();
	 		System.out.println("Total checks = " +cntOutputVal );
	 		for (int i =1; i<=op2.size(); i++){
	     		WebElement opName2 = driver().findElement(By.cssSelector(outputFilterOpBfr+i+outputFilterOpAfr));     		
	     	//	WebElement opChkBox2 = driver().findElement(By.cssSelector(outputFilterOpBfr+i+outputFilterOpAfr+filterChkBox));
	     		System.out.println(opName2.getText());
	     		for(int j=0;j<outputList.length;j++){
	     		if ((opName2.getText().equalsIgnoreCase(filterOption1[j]))){
	     			isOptionPresent = true; 	
	     			opName2.click();  
	 			    //	break;
	 				}
	     		}
	     		
	     	
	 		     }
	 		clickOnElement("cssSelector", runReport);
	 		if (isOptionPresent==false){
	 		    	 Assert.assertTrue(isOptionPresent, filterOption1 + " filter option is not present in OutputFilters" );
	 		     }
	     }
	 
	    //To save a report 
	    public void saveReport() throws Exception{
	    	selectFilterOption(outputList);
	    	waitOnElement("cssSelector", saveReportButton);
	    	clickOnButton("cssSelector", saveReportButton);
	    	waitOnElement("cssSelector", saveReportName);
	    	inputTextClear("cssSelector", saveReportName);
	    	inputText("cssSelector", saveReportName, savedReport_text);
	    	inputTextClear("cssSelector", saveReportDescription);
	    	inputText("cssSelector", saveReportDescription, "This is save report description");
	    	clickOnButton("xpath", saveReport);
	
	    }
	
	    // To verify the saved report in the reports landing page under saved reports section
	    public boolean verifySavedReportPresent(String reportName) throws Exception{
			logInfo("inside verifyReportPresent() method..");
			
			navigateReports	();
			boolean isMatchFound = false;
			
		/*	
			WebElement e = driver().findElement(By.xpath("//*[@id='saved-reports']/div[2]"));
			List allRows = e.findElements(By.tagName("ul"));
			int all_rows = allRows.size();
			String before_row = "//*[@id='saved-reports']/div[2]/ul/li[";
			String after_row = "]/div/div[1]/a";
			
			for(int i=01;i<=all_rows;i++){
				WebElement x = driver().findElement(By.xpath(before_row+i+after_row));
				String title = x.getText().trim();
				System.out.println(title);
				if(title.equalsIgnoreCase(reportName)){
					isMatchFound = true;
					logInfo(reportName +" report match found.");
					break;
				}
			}*/
			
			String expected = driver().findElement(By.linkText(reportName)).getText();
			System.out.println("saved report name is "+expected);
			
			if(expected.equalsIgnoreCase(reportName)){
				isMatchFound = true;
				
			}
			if(isMatchFound==false){				
				Assert.assertTrue(isMatchFound, reportName+" report match not found.");
			}
			return isMatchFound;			
		}
	public void deleteSavedReport(String reportName) throws Exception, IOException, AWTException{
		logInfo("inside verifyReportPresent() method..");
		navigateReports	();
		List <WebElement> e = driver().findElements(By.xpath("//*[@id='saved-reports']/div[2]/ul/li"));
		
		int all_rows = e.size();
		System.out.println(all_rows);
		String before_row = "//*[@id='saved-reports']/div[2]/ul/li[";
		String after_row = "]/div/div[1]/a";		
		String deleteAfter_row = "]/div/div[3]/a";
		boolean isMatchFound = false;
		for(int i=1;i<=all_rows;i++){
			WebElement x = driver().findElement(By.xpath(before_row+i+after_row));
			String title = x.getText().trim();
			System.out.println(title);
			if(title.equalsIgnoreCase(reportName)){
				isMatchFound = true;
			WebElement y = 	driver().findElement(By.xpath(before_row+i+deleteAfter_row));
				y.click();
				confirmOK();
				break;
			}
			
		}if(isMatchFound==false){
			Assert.assertTrue(isMatchFound, reportName+ " not found saved report");
		}
		
	}
	
	
	public void exportTo(String dropDownName) throws Exception{
		logInfo("inside exportToCSV() method..");
		Thread.sleep(5000);
		clickOnElement("cssSelector", exportBtn);
		boolean isOp = false;
		List<WebElement> drpDwn = driver().findElements(By.xpath("//*[@id='report_dropdown_menu']/li"));
		String before_path = "//*[@id='report_dropdown_menu']/li[";
		String after_path = "]/a";
		 int d = drpDwn.size();
			for(int i=1;i<=d;i++){
				WebElement x = driver().findElement(By.xpath(before_path+i+after_path));
				String title = x.getText().trim();
				System.out.println("");
				if(title.equalsIgnoreCase(dropDownName)){
					isOp= true;
					x.click();
					break;
				
				}
			}if (isOp==false){
				Assert.assertTrue(isOp,dropDownName + " is not present in export List");
			}
			    	   
		
				
	}		
	
	
	
	public void validateExportMsg() throws Exception, Exception{
		String msg = "#report-export-message";
		String proBar = "div.progress-bar.progress-bar-striped.active";
        
        String msgTxt1 ="Your report job has been queued and will start soon.";
        String msgTxt2 = "We are gathering the data for your report file...";
        String msgTxt3 = "Report export complete. The file will be downloaded shortly.";
		waitOnElement("cssSelector", msg);
		WebElement status1 = driver().findElement(By.cssSelector(msg));		
		if(status1.getText().equalsIgnoreCase(msgTxt1)){
			
			 waitOnElement("cssSelector", proBar);
			 WebElement progress = driver().findElement(By.cssSelector(proBar));
			 System.out.println("progress "+progress.getText());			 
			 if(progress.getText().contains("%")){
				 Thread.sleep(5000);
				 waitOnElement("cssSelector", msg);
				 WebElement status2 = driver().findElement(By.cssSelector(msg));			
			    	System.out.println("2 "+status2.getText());
			    	if((status2.getText().equals(msgTxt2))||
			    			(status2.getText().equals(msgTxt3))	){		
			    	 System.out.println("Success fully downloaded");			    	
						 
			    	}
			    	else{
			    		 Assert.assertEquals(status2.getText(),msgTxt2);
		   			    	}			 
			 }	    	 
		    		 }else{
		    			 Assert.assertEquals(status1.getText(), msgTxt1);
		    		 }
		Thread.sleep(4000);
	}
	
	/*String parentEcard = "Ecard Parent 75";
	String ecardtempName = "template13";*/
	public void sendEcard(String mailId) throws Exception{
		logInfo("Inside sendEcard Method");	
		String parentEcard ="Motivation";
		String ecardtempName = "Motivation";
		em.selectcatNdEcard(parentEcard, ecardtempName);
		em.enterDetails(ecardCatSubText);		
		em.sendManualMail(mailId, ecardCatSubText2);	
		inbox.go2Inbox();
		inbox.selectVibeMailsWithSubject(ecardCatSubText);
		inbox.deleteFilteredVibeMails();
		
	}
	
}

