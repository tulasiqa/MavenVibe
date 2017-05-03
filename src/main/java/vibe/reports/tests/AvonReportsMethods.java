package vibe.reports.tests;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.apache.commons.lang3.StringUtils;

import common.TestBase;
import common.readProp;
import vibe.marketing.companyNews.tests.NewsMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

public class AvonReportsMethods extends TestBase {
	
	NewsMethods nm = new NewsMethods();
	//ReportsMethods rm = new ReportsMethods();
	readProp prop  = new readProp();
	
	
	
	public void navigateReports() throws Exception{
		logInfo("Navigating to Reports ");				
		try{	
			clickOnElement("cssSelector", REPORTS_tab);			
			driver().navigate().to(appUrl+"reports/run_report");
	
		}catch (NoSuchElementException nse){
			System.err.println("Failed!! No such element is found" );
			
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
	
	public void go2LeadershiPerformancepage(){
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=leadershipPerformance");
	}
	
	public void login2AvonLeadership(String userName, String passwd) throws Exception{
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/login");
		waitOnElement("xpath","//input[@name='USERNAME']");
		inputTextClear("xpath","//input[@name='USERNAME']");
		inputText("xpath","//input[@name='USERNAME']",userName);
		inputTextClear("xpath","//input[@name='PASSWORD']");
		inputText("xpath","//input[@name='PASSWORD']",passwd);
		selectFromDropdown("xpath","//select[@name='marketLocaleCode']","byVisibleText","United States-en");
		clickOnElement("xpath","//input[@value='Login']");
		Thread.sleep(10000);
	}
	
	
	public int getRowsCntInGrid() throws Exception, Exception{
		logInfo("inside getRowsCntInGrid() methhod");
		waitOnElement("xpath","//table[@id='reportTable1']/tbody");
		WebElement tbl = driver().findElement(By.xpath("//table[@id='reportTable1']/tbody"));
		List allRows = tbl.findElements(By.tagName("tr"));
		int all_rows = allRows.size();
		all_rows = all_rows/2;
		System.out.println("all_rows = " +all_rows);
		return all_rows;
	}
	
	
	public void go2Window(){
		logInfo("inside go2Window() methhod");
		String curWindow = driver().getTitle();
		System.out.println("curWindow = " +curWindow);
		Set<String> allWindowHandles = driver().getWindowHandles();
		int cnt = allWindowHandles.size();
		System.out.println("cnt of windows = " +cnt);
		for (String window : allWindowHandles) {
				//if (currentWindowHandle.equals(curWindow)) {
				driver().switchTo().window(window);
				String title = driver().getTitle();
				System.out.println("window = " +window + ", Window Title = " +title);
			//	break;
			// }
		}
	}
	
	public void closeChildWindow() throws Exception{ 
		logInfo("inside foucsOnMainWindow() method.");
		scrollDown("cssSelector", leadChildWinClose);
		clickOnElement("cssSelector", leadChildWinClose); 
		        Thread.sleep(5000);
		        driver().switchTo().window(""); 
		 }
	
	
	public void logoutAvonDLP() throws Exception, Exception{
		logInfo("inside logoutAvonDLP() methhod");
		String logOut = "Log Out";
		 boolean isTabPresent = false;
		waitOnElement("cssSelector",leadHeadTabs);
	    List<WebElement> tab = driver().findElements(By.cssSelector(leadHeadTabs)); 
	       for(WebElement tabs :tab){    
	    	   System.out.println("Tab name = " + tabs.getText().trim());
	    	   if (tabs.getText().trim().equalsIgnoreCase(logOut)){ 
	    		   System.out.println("Tab name = " + tabs.getText().trim() + "-----> match found");
	    	   isTabPresent =true;
	    	   tabs.click();  
	    	   break;
	    	   }     
	       }
	       
	       if(isTabPresent ==false){
	        	logInfo(logOut+"- tab is not present");
	        	Assert.assertTrue(isTabPresent , logOut+"- tab is not present");
	        }
		
	}
	
	
	public void logoutAvonLeadership() throws Exception, Exception{
		waitOnElement("linkText","Log Out");
		clickOnLink("linkText","Log Out");
		
	}
	
	public void showReport(String repId) throws Exception, Exception{
		
		waitOnElement("xpath","//select[@name='leadershipPerformance$$LEVELS_DEEP$$entry']");
		selectFromDropdown("xpath","//select[@name='leadershipPerformance$$LEVELS_DEEP$$entry']","byVisibleText","First, Second and Third");
		waitOnElement("xpath","//button[@id='showButton']");
		clickOnElement("xpath","//button[@id='showButton']");
	}
	
	public int getRowsCntInLeadershipDlp() throws Exception, Exception{
		waitOnElement("xpath","//table[@id='reportTable1']/tbody");
		WebElement tbl = driver().findElement(By.xpath("//table[@id='reportTable1']/tbody"));
		List allRows = tbl.findElements(By.tagName("tr"));
		int all_rows = allRows.size();
		System.out.println("No of Row User in Table = " +all_rows);
		return all_rows;
	}
	
	
	
	public boolean verifyAccountIsPresent(String acctNo) throws Exception, Exception{
		boolean isAcctFound = false;
		waitOnElement("xpath","//table[@id='reportTable1']/tbody");
		WebElement tbl = driver().findElement(By.xpath("//table[@id='reportTable1']/tbody"));
		List allRows = tbl.findElements(By.tagName("tr"));
		
		String before_acctName = "//table[@id='reportTable1']/tbody/tr[";
		String after_acctName = "]/td[25]";
		
		// String before_email = "//table[@id='reportTable1']/tbody/tr[";
		// String after_email = "]/td[2]/a";
		
		String before_header = "//table[@id='reportTable1']/tbody/tr[";
		String after_header = "]/th[4]/a";
		
		int all_rows = allRows.size();
		all_rows = all_rows/2;
		System.out.println("verifyAccountIsPresent all_rows = " +all_rows);
		
		for(int i=1;i<=allRows.size();i=i+2){
			WebElement acct = driver().findElement(By.xpath(before_acctName+i+after_acctName));
			String actAcct = acct.getText().trim();
			System.out.println("actAcct = " +actAcct);
			if(actAcct.equalsIgnoreCase(acctNo)){
				WebElement h4 = driver().findElement(By.xpath(before_header+i+after_header));
				h4.click();
				isAcctFound = true;
				break;
			}
		}
		return isAcctFound;
	}
	
	
	public void foucsOnChildWindow() throws Exception{
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
				if(!w.equalsIgnoreCase(wndBeforeWindow)){
				driver().switchTo().window(w);
				break;				
				}
		   }	
		}
	
	
	public void foucsOnMainWindow() throws Exception{	
		String logOut = "Log Out";
		
		scrollDown("cssSelector", leadChildWinClose);
		clickOnElement("cssSelector", leadChildWinClose);				
        Thread.sleep(5000);
        driver().switchTo().window(""); 
        boolean isTabPresent = false;
        List<WebElement> tab = driver().findElements(By.cssSelector(leadHeadTabs)); 
        for(WebElement tabs :tab){        	
        	if (tabs.getText().trim().equalsIgnoreCase(logOut)){ 
        		isTabPresent =true;
        		tabs.click();        		
        		break;
        	}
        	
        }if(isTabPresent ==false){
        	Assert.assertTrue(isTabPresent , logOut+"- tab is not present");
        }
        
	   
	  }
				
	
	
	
	public void captureLeadershipData() throws Exception, Exception{		
		waitOnElement("linkText","Leadership");
		clickOnLink("linkText","Leadership");		
		waitOnElement("xpath","//*[@id='section1']/tbody/tr[2]/td[1]/table/tbody/tr[3]/td");
		String ytd_earnings = driver().findElement(By.xpath("//*[@id='section1']/tbody/tr[2]/td[1]/table/tbody/tr[3]/td")).getText().trim();
		String rollup_campagin = driver().findElement(By.xpath("//*[@id='section1']/tbody/tr[2]/td[1]/table/tbody/tr[2]/td")).getText().trim();
		System.out.println("ytd_earnings = " +ytd_earnings);
		System.out.println("rollup_campagin = " +rollup_campagin);
		Thread.sleep(2000);
		
		leadData(ytd_earnings, rollup_campagin);
		
	}
	
	
	private void leadData(String Sytd_earnings, String Srollup_campagin) {
		System.out.println("Earnings"+ Sytd_earnings );
        System.out.println("Camp "+ Srollup_campagin );
        
        
		
	}
	
	

	public void capturePerformanceData() throws Exception, Exception{
		waitOnElement("linkText","Performance");
		clickOnLink("linkText","Performance");
		
		String total_cmp =  driver().findElement(By.xpath("//*[@id='performanceTable3']/tbody/tr[3]/td[1]")).getText().trim();
		String avg_cmp = driver().findElement(By.xpath("//*[@id='performanceTable3']/tbody/tr[4]/td[1]")).getText().trim();
		String py_award_sales =  driver().findElement(By.xpath("//*[@id='performanceTable3']/tbody/tr[3]/td[2]")).getText().trim();
		String cy_award_sales =  driver().findElement(By.xpath("//*[@id='performanceTable3']/tbody/tr[3]/td[3]")).getText().trim();
		String net_sales = driver().findElement(By.xpath("//*[@id='performanceTable2']/tbody/tr[4]/td[1]")).getText().trim();
		
		System.out.println("total_cmp = " +total_cmp);
		System.out.println("avg_cmp = " +avg_cmp);
		System.out.println("py_award_sales = " +py_award_sales);
		System.out.println("cy_award_sales = " +cy_award_sales);
		System.out.println("net_sales = " +net_sales);
		Thread.sleep(2000);
	}
	
	
	public void captureProfileData() throws Exception, Exception{
		waitOnElement("linkText","Profile");
		clickOnLink("linkText","Profile");
		
		String birthday =  driver().findElement(By.xpath("//*[@id='profileTable1'][1]/tbody/tr[2]/td[2]/table[1]/tbody/tr[1]/td")).getText().trim();
		System.out.println("birthday = " +birthday);
		Thread.sleep(2000);
	}
	
	
	public void captureGeneralData() throws Exception, Exception{
		
		String acctNo =  driver().findElement(By.xpath("//*[@id='globalDetail']/tbody/tr[1]/td[1]")).getText().trim();
		String Phone =  driver().findElement(By.xpath("//*[@id='globalDetail']/tbody/tr[1]/td[2]")).getText().trim();
		String Email =  driver().findElement(By.xpath("//*[@id='globalDetail']/tbody/tr[2]/td[1]")).getText().trim();
		System.out.println("acctNo = " +acctNo);
		System.out.println("Phone = " +Phone);
		System.out.println("Email = " +Email);		
		Thread.sleep(5000);
	}
	
	
	

	public void go2LeadershipHomepage(){
		logInfo("inside go2LeadershipHomepage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drb");
	}
	
	
	public void go2AdditionsReportpage(){
		logInfo("inside go2AdditionsReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=newAppointments");
	}
	
	public void go2AvonAnniversariesReportpage(){
		logInfo("inside go2AvonAnniversariesReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=anniversary");
	}
	
	public void go2BirthdaysReportpage(){
		logInfo("inside go2BirthdaysReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=birthday");
	}
	
	public void go2DirectDeliveryReportpage(){
		logInfo("inside go2DirectDeliveryReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=directDelivery");
	}
	
	public void go2FastTrackReportpage(){
		logInfo("inside go2FastTrackReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=fastTrack");
	}
	
	public void go2Followuppage(){
		logInfo("inside go2Followuppage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=followUp");
	}
	
	public void go2GeoSearchReportpage(){
		logInfo("inside go2GeoSearchReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=radiusSearch");
	}
	
	public void go2HoldOrderspage(){
		logInfo("inside go2HoldOrderspage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=holdOrder");
	}
	
	
	public void go2InactivityReportpage(){
		logInfo("inside go2InactivityReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=inactivity");
	}
	
	public void go2LeadershipPerfReportpage(){
		logInfo("inside go2LeadershipPerfReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=leadershipPerformance");
	}
	
	public void go2NewPromotersReportpage(){
		logInfo("inside go2NewPromotersReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=newUplines");
	}
	
	public void go2NoOrdersReportpage(){
		logInfo("inside go2NoOrdersReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=noorder");
	}
	
	public void go2OrdersReportpage(){
		logInfo("inside go2OrdersReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=ordered");
	}
	
	public void go2PastDueReportpage(){
		logInfo("inside go2PastDueReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=pastDue");
	}
	
	public void go2PendingAppointmentsReportpage(){
		logInfo("inside go2PendingAppointmentsReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=pendingAppointments");
	}
	
	public void go2PresidentsClubReportpage(){
		logInfo("inside go2PresidentsClubReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=presidentsClub");
	}
	
	public void go2Recognitionpage(){
		logInfo("inside go2Recognitionpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=recognition");
	}
	
	public void go2RemovalsReportpage(){
		logInfo("inside go2RemovalsReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=removals");
	}
	
	public void go2RepSearchReportpage(){
		logInfo("inside go2RepSearchReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=downlineSearch");
	}
	
	public void go2SalesReportpage(){
		logInfo("inside go2SalesReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=sales");
	}
	
	public void go2TitleAdvancementsReportpage(){
		logInfo("inside go2TitleAdvancementsReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=promotions");
	}
	
	public void go2DemotionsReportpage(){
		logInfo("inside go2DemotionsReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=demotions");
	}
	
	public void go2TitlesAtRiskReportpage(){
		logInfo("inside go2TitlesAtRiskReportpage() methhod");
		driver().navigate().to("http://leadershipdlm.avon.com/esuite/control/drbConstraints?reportName=titleAtRisk");
	}
	
	public void showReport(String reportName, String accNo, String generation) throws Exception, Exception{
		logInfo("inside showReport() method");
		
		switch(reportName){
		case "Additions Report":
			waitOnElement("xpath","//select[@name='newAppointments$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='newAppointments$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "Avon Anniversaries":
			waitOnElement("xpath","//select[@name='anniversary$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='anniversary$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "Birthday":
			waitOnElement("xpath","//select[@name='birthday$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='birthday$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "Direct Delivery":
			waitOnElement("xpath","//select[@name='directDelivery$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='directDelivery$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "Fast Track":
			waitOnElement("xpath","//select[@name='fastTrack$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='fastTrack$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "Follow Up":
			waitOnElement("xpath","//select[@name='followUp$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='followUp$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "Geographic Search":
			waitOnElement("xpath","//select[@name='radiusSearch$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='radiusSearch$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Hold Orders":
			waitOnElement("xpath","//select[@name='holdOrder$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='holdOrder$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Inactivity Report":
			waitOnElement("xpath","//select[@name='inactivity$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='inactivity$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Leadership Performance Report":
			waitOnElement("xpath","//select[@name='leadershipPerformance$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='leadershipPerformance$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "New Promoters":
			waitOnElement("xpath","//select[@name='newUplines$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='newUplines$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "No Orders":
			waitOnElement("xpath","//select[@name='noOrder$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='noOrder$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Orders":
			waitOnElement("xpath","//select[@name='ordered$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='ordered$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Past Due":
			waitOnElement("xpath","//select[@name='pastDue$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='pastDue$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Pending Apointments":
			waitOnElement("xpath","//select[@name='pendingAppointments$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='pendingAppointments$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Presidents Club":
			waitOnElement("xpath","//select[@name='presidentsClub$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='presidentsClub$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Recognition":
			waitOnElement("xpath","//select[@name='recognition$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='recognition$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Removals":
			waitOnElement("xpath","//select[@name='removals$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='removals$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Representative Search":
			waitOnElement("xpath","//select[@name='downlineSearch$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='downlineSearch$$LEVELS_DEEP$$entry']","byVisibleText",generation);	
			break;
		case "Sales":
			waitOnElement("xpath","//select[@name='sales$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='sales$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "Title Advancements":
			waitOnElement("xpath","//select[@name='promotions$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='promotions$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "Title Demotions":
			waitOnElement("xpath","//select[@name='demotions$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='demotions$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "Title At Risk":
			waitOnElement("xpath","//select[@name='titleAtRisk$$LEVELS_DEEP$$entry']");
			selectFromDropdown("xpath","//select[@name='titleAtRisk$$LEVELS_DEEP$$entry']","byVisibleText",generation);
			break;
		case "Tree View":
			waitOnElement("xpath","//select[@name='levelsDeep']");
			selectFromDropdown("xpath","//select[@name='levelsDeep']","byVisibleText",generation);
			break;
		}
		
		waitOnElement("xpath","//button[@id='showButton']");
		clickOnElement("xpath","//button[@id='showButton']");
	}
	
	
	public String returnFirstAccountNoInReport(String reportName) throws Exception, Exception{
		logInfo("inside returnFirstAccountNoInReport() method");
		String actAcctNo = null;
		
		waitOnElement("xpath","//table[@id='reportTable1']/tbody");
		WebElement tbl = driver().findElement(By.xpath("//table[@id='reportTable1']/tbody"));
		List allRows = tbl.findElements(By.tagName("tr"));
		
		String before_acctNo = "//table[@id='reportTable1']/tbody/tr[";
		String before_acctName = "//table[@id='reportTable1']/tbody/tr[";
		String after_acctNo = null;
		String after_acctName = null;
		
		switch(reportName){
		case "Additions Report":
			after_acctNo = "]/td[12]";
			after_acctName = "]/th[4]/a";
			break;
		case "Avon Anniversaries":
			after_acctNo = "]/td[9]";
			after_acctName = "]/th[2]/a";
			break;
		case "Birthday":
			after_acctNo = "]/td[8]";
			after_acctName = "]/th[2]/a";
			break;
		case "Direct Delivery":
			after_acctNo = "]/td[25]";
			after_acctName = "]/td[4]/a";
			break;
		case "Fast Track":
			// after_acctNo = "]/td[25]";			// no records
			// after_acctName = "]/th[4]/a";		// no records
			break;
		case "Follow Up":
			after_acctNo = "]/td[16]";
			after_acctName = "]/th[4]/a";
			break;
		case "Geographic Search":
		//	after_acctNo = "]/td[16]";		// invalid zipcode
		//	after_acctName = "]/th[4]/a";	// invalid zipcode
			break;
		case "Hold Orders":
			after_acctNo = "]/td[15]";
			after_acctName = "]/th[4]/a";
			break;
		case "Inactivity Report":
			after_acctNo = "]/td[8]";
			after_acctName = "]/th[4]/a";	
			break;
		case "Leadership Performance Report":
			after_acctNo = "]/td[25]";
			after_acctName = "]/th[4]/a";
			break;
		case "New Promoters":
			after_acctNo = "]/td[9]";
			after_acctName = "]/th[4]/a";
		case "No Orders":
			after_acctNo = "]/td[10]";
			after_acctName = "]/th[4]/a";
			break;
		case "Orders":
			after_acctNo = "]/td[15]";
			after_acctName = "]/th[4]/a";
			break;
		case "Past Due":
			after_acctNo = "]/td[5]";
			after_acctName = "]/th[2]/a";
			break;
		case "Pending Appointments":
			after_acctNo = "]/td[3]";
			after_acctName = "]/th[4]/a";	
			break;
		case "Presidents Club":
			after_acctNo = "]/td[12]";
			after_acctName = "]/th[4]/a";
			break;
		case "Recognition":
			after_acctNo = "]/td[7]";
			after_acctName = "]/th[4]/a";
			break;
		case "Removals":
			after_acctNo = "]/td[13]";
			after_acctName = "]/th[2]/a";
			break;
		case "Representative Search":
			after_acctNo = "]/td[3]";
			after_acctName = "]/th[2]/a";
			break;
		case "Sales":
			after_acctNo = "]/td[18]";
			after_acctName = "]/th[4]/a";
			break;
		case "Title Advancements":
			// after_acctNo = "]/td[18]";		// no records
			// after_acctName = "]/th[4]/a";	// no records
			break;
		case "Title Demotions":
			// after_acctNo = "]/td[18]";		// no records
			// after_acctName = "]/th[4]/a";	// no records
			break;
		case "Title At Risk":
			after_acctNo = "]/td[18]";
			after_acctName = "]/th[2]/a";
			break;
		case "Tree View":
			// after_acctNo = "]/td[18]";		// cannot automate tree view
			// after_acctName = "]/th[4]/a";	// cannot automate tree view
			break;
		}
		
		int all_rows = allRows.size();
		int totalCountOfRows = all_rows/2;
		System.out.println("Total rows for Report = " +totalCountOfRows);
		
		if(all_rows >0){
		for(int i=1;i<=allRows.size();i=i+2){
			WebElement actAccountNo = driver().findElement(By.xpath(before_acctNo+i+after_acctNo));
			actAcctNo = actAccountNo.getText().trim();
			System.out.println("actAcctNo = " +actAcctNo);
			if(i==1){
				WebElement actAccountName = driver().findElement(By.xpath(before_acctName+i+after_acctName));
				actAccountName.click();
				break;
			}
		 }
		}
		return actAcctNo;
	}
	
	
	/***************************************************************************************************************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
       // open user- Representative  details by column with row data 	
		public void selectRepresentativeDetails(String colName, String value) throws Exception{			
				WebElement result = driver().findElement(By.cssSelector(resultsCount));
				String actResultsFound =result.getText();
				System.out.println("ActResult Count is "+actResultsFound );
				boolean isColumnPresent = false;
				List <WebElement> colmns = driver().findElements(By.cssSelector(tabColmns));	
				System.out.println("columns size "+colmns.size());
				for (int i=1 ; i<=colmns.size()-4; i++ ){				
					WebElement allColumns = driver().findElement(By.cssSelector(tabColmnsBfr+i+tabColmnsAft));
					if(allColumns.getText().equalsIgnoreCase(colName)){
						System.out.println("col Name "+allColumns.getText());
						isColumnPresent = true;
						boolean isRowValuePresent = false;
						List<WebElement> selectLink = driver().findElements(By.cssSelector(colItemsBfr+i+colItemsAfr));
						System.out.println("size in rows "+ selectLink.size());
						for (int j =1; j<=selectLink.size();j++){
							WebElement rowValue = driver().findElement(By.cssSelector(colItemNameBfr+j+colItemNameAfr+i+colItemsAfr));
							System.out.println(rowValue.getText());
							
							/*js.executeScript("window.scrollBy(0,-250)", "");*/
							//js.executeScript("arguments[0].scrollIntoView(true);", rowValue); 
							
							if(rowValue.getText().equalsIgnoreCase(value)){
								String element = colItemNameBfr+j+colItemNameAfr+i+colItemsAfr;
								System.out.println("exepected account s "+rowValue.getText());
								isRowValuePresent=true;	
							/*	JavascriptExecutor js = (JavascriptExecutor)driver;		
								WebElement scroll = driver().findElement(By.cssSelector(colItemNameBfr+j+colItemNameAfr+i+colItemsAfr));
								js.executeScript("arguments[0].scrollIntoView(true);", scroll);*/
								//js.executeScript("window.scrollBy(0,-50)",scroll ); 
								Thread.sleep(4000);	
								
								/*scroll.click();	*/
								waitOnElement("cssSelector", element);								
								rowValue.click();
								System.out.println("Selected Account Number");
								/*Actions act = new Actions (driver);
								act.doubleClick(rowValue).build().perform();*/														
								break;
							}}if(isRowValuePresent==false){
								Assert.assertTrue(isRowValuePresent, value + " value is not present in column under "+ colName );
								
							}break;				
						}}if(isColumnPresent==false){
							
							Assert.assertTrue(isColumnPresent, colName + " column is not present in Table" );
						}					
				}


   public void selectRandomUserFromTable() throws Exception, Exception{
	waitOnElement("xpath","//table[@id='reportTable1']/tbody");
	WebElement tbl = driver().findElement(By.xpath("//table[@id='reportTable1']/tbody"));
	List allRows = tbl.findElements(By.tagName("tr"));
	int all_rows = allRows.size()/2;
	System.out.println("No of Row User in Table = " +all_rows);	
	Random r = new Random();
	int min=1;
	int max = all_rows;
	int numRamdom=r.nextInt(max-min) + min;
	System.out.println("Generated Ramdom number is "+numRamdom);
	String before_acctName = "//table[@id='reportTable1']/tbody/tr[";
	String after_header = "]/th[4]/a";
	String after_acctName = "]/td[25]";
	
	WebElement ramUser = driver().findElement(By.xpath(before_acctName+"101"+after_header));
	 JavascriptExecutor js = (JavascriptExecutor)driver();   	 
	 js.executeScript("arguments[0].scrollIntoView(true);", ramUser);    	
	 
	String actAcct = ramUser.getText().trim();
	System.out.println("actAcct = " +actAcct);
	Thread.sleep(3000);
	ramUser.click();
	Actions act = new Actions(driver());
	
	act.doubleClick(ramUser).build().perform();
	
     }
   
   
     public void updateGenerate(int classType) throws Exception{
    	 logInfo("Entered into updateGenerate(int classType) method.");
    	 String value = Integer.toString(classType);
    	 waitOnElement("cssSelector", advancedFilterOpt);
    	 clickOnElement("cssSelector", advancedFilterOpt);
    	 inputTextClear("cssSelector", generationFrom);
    	 inputTextClear("cssSelector", generationTo);
    	 inputText("cssSelector", generationFrom, "1");
    	 inputText("cssSelector", generationTo, value);
    	 waitOnElement("cssSelector", runReports);
    	 clickOnElement("cssSelector", runReports);
     }
   
   public void validateRepresentativeDetailsFromTable(String EsuiteReport, String avonReportTable, String columnName) throws Exception, Exception{
	    logInfo("Entered into validateRepresentativeDetailsFromTable() method.");
	    int expectRows = getRowsCntInGrid();
	    String accNO = returnFirstAccountNoInReport(EsuiteReport);
		System.out.println("account Number= " +accNO);
		go2Window();	 
	    waitOnElement("linkText","Leadership");
		clickOnLink("linkText","Leadership");		
		waitOnElement("xpath","//*[@id='section1']/tbody/tr[2]/td[1]/table/tbody/tr[3]/td");
		String ytd_earnings = driver().findElement(By.xpath("//*[@id='section1']/tbody/tr[2]/td[1]/table/tbody/tr[3]/td")).getText().trim();
		String rollup_campagin = driver().findElement(By.xpath("//*[@id='section1']/tbody/tr[2]/td[1]/table/tbody/tr[2]/td")).getText().trim();
		System.out.println("ytd_earnings = " +ytd_earnings);
		System.out.println("rollup_campagin = " +rollup_campagin);		
		waitOnElement("linkText","Performance");
		clickOnLink("linkText","Performance");		
		String total_cmp =  driver().findElement(By.xpath("//*[@id='performanceTable3']/tbody/tr[3]/td[1]")).getText().trim();
		String avg_cmp = driver().findElement(By.xpath("//*[@id='performanceTable3']/tbody/tr[4]/td[1]")).getText().trim();
		String py_award_sales =  driver().findElement(By.xpath("//*[@id='performanceTable3']/tbody/tr[3]/td[2]")).getText().trim();
		String cy_award_sales =  driver().findElement(By.xpath("//*[@id='performanceTable3']/tbody/tr[3]/td[3]")).getText().trim();
		String net_sales = driver().findElement(By.xpath("//*[@id='performanceTable2']/tbody/tr[4]/td[1]")).getText().trim();
		
		System.out.println("total_cmp = " +total_cmp);
		System.out.println("avg_cmp = " +avg_cmp);
		System.out.println("py_award_sales = " +py_award_sales);
		System.out.println("cy_award_sales = " +cy_award_sales);
		System.out.println("net_sales = " +net_sales);
		Thread.sleep(2000);
		
		
		waitOnElement("linkText","Profile");
		clickOnLink("linkText","Profile");
		
		String birthday =  driver().findElement(By.xpath("//*[@id='profileTable1'][1]/tbody/tr[2]/td[2]/table[1]/tbody/tr[1]/td")).getText().trim();
		System.out.println("birthday = " +birthday);
		Thread.sleep(2000);		
		String acctNo =  driver().findElement(By.xpath("//*[@id='globalDetail']/tbody/tr[1]/td[1]")).getText().trim();
		String Phone =  driver().findElement(By.xpath("//*[@id='globalDetail']/tbody/tr[1]/td[2]")).getText().trim();
		String Email =  driver().findElement(By.xpath("//*[@id='globalDetail']/tbody/tr[2]/td[1]")).getText().trim();
		
		String[] parts = StringUtils.split(acctNo," ");
		String accountNumber = parts[0];
		System.out.println("acctNo = " +acctNo);
		System.out.println("accountNumber = " +accountNumber);
		
		System.out.println("Phone = " +Phone);
		System.out.println("Email = " +Email);			
		collectedData(ytd_earnings,rollup_campagin,total_cmp,avg_cmp,py_award_sales,cy_award_sales,net_sales, birthday, acctNo, Phone , Email);
			
		foucsOnMainWindow();
		logIn(adminUser_text,adminPwd_text);
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		navigateReports();	
		reportstable(avonReportTable) ;
		updateGenerate(1);
		
		
		int result = resultFound();	
		if ((result==expectRows)||(result==(expectRows+1))||(result==(expectRows-1))){
			System.out.println("Success!! count is around expected "+result);
			
		}else{
			
		  Assert.assertEquals(result, expectRows);
		}
		
		setColumnsVisibilities();
		// selectAllFilterOptions(columnName);		
		 selectRepresentativeDetails(columnName, accountNumber);	   
		 profileSections("Profile");		
		 personalProfile("Account Number", accountNumber);
		 personalProfile("Email", Email);		 
		 personalInfomation("Birthday",birthday);
		 salesPerformance(total_cmp,avg_cmp,py_award_sales,cy_award_sales );
		
		
	}
   
   public int resultFound() throws Exception{	   
	   WebElement count = driver().findElement(By.cssSelector(result));
	   String resCount = count.getText().trim();
	    int resultCount = Integer.parseInt(resCount);	   
	   System.out.println("Result count is "+resultCount );
	   return resultCount;
	   
   }
   

private void collectedData(String ytd_earnings, String rollup_campagin, String total_cmp, String avg_cmp,
		String py_award_sales, String cy_award_sales, String net_sales, String birthday, String acctNo, String phone,
		String email) {
	System.out.println("Ytd Earnings "+ytd_earnings);
	System.out.println("RollUp "+ rollup_campagin);
	System.out.println("Total CMP "+total_cmp);
	System.out.println("AVG Cmp "+ avg_cmp);
	System.out.println("PY Award Sales "+py_award_sales);
	System.out.println("CY Award Sales  "+ cy_award_sales);
	System.out.println("SalesNet  "+net_sales);
	System.out.println("BirthDay "+ birthday);
	System.out.println("Acc No "+ acctNo);
	System.out.println("Phone No "+phone);
	System.out.println("Email "+ email);
	
	
}


//Select Add/Remove Column, Then select column labels from Output section for visibility of columns under table
public void setColumnsVisibilities() throws Exception{	
	logInfo("Entered into setColumnsVisibilities() method");
	
	clickOnElement("cssSelector", addRemoveColumns);
	String attClass = driver().findElement(By.cssSelector(addRemoveColumns)).getAttribute("class");
	System.out.println("attClass" + attClass);
	if (attClass.contains("collapsed")){
		clickOnElement("cssSelector", addRemoveColumns);
	}else{
		System.out.println("Sucess opened");
	}
	
	
	
	
	
	
	
	
	
	
	
	waitOnElement("cssSelector", colVisbltyBtn);
	clickOnElement ("cssSelector", colVisbltyBtn);
	Thread.sleep(3000);
	Actions act = new Actions (driver());    	
	WebElement hovers = driver().findElement(By.cssSelector(colVisbility));
	act.moveToElement(hovers).click().build().perform();	
	Thread.sleep(4000);	
	List <WebElement> colmns = driver().findElements(By.cssSelector(colVisbility));
	System.out.println(colmns.size());
	for (int i=1; i<=colmns.size(); i++){
	WebElement columns = driver().findElement(By.cssSelector(colVisbilityBfr+i+colVisbilityAft));			
	if(!((columns.getText().equalsIgnoreCase("Acct. Num"))||(columns.getText().equalsIgnoreCase("Email"))
			||(columns.getText().equalsIgnoreCase("Silver Ambassador thru Silver Leader in 1st Gen.")))){				
			columns.click();
		}
	}Thread.sleep(8000);	
	clickOnElement ("cssSelector", resultsCount);
  }


//  Here set columns visibilties of the table
    public void setColumnsVisibilities_old() throws Exception{	
    	waitOnElement("cssSelector", colVisbltyBtn);
    	clickOnElement ("cssSelector", colVisbltyBtn);
    	Thread.sleep(3000);
    	Actions act = new Actions (driver());    	
    	WebElement hovers = driver().findElement(By.cssSelector(colVisbility));
    	act.moveToElement(hovers).click().build().perform();	
		Thread.sleep(4000);	
		List <WebElement> colmns = driver().findElements(By.cssSelector(colVisbility));
		System.out.println(colmns.size());
		for (int i=1; i<=colmns.size(); i++){
		WebElement columns = driver().findElement(By.cssSelector(colVisbilityBfr+i+colVisbilityAft));			
		if(!((columns.getText().equalsIgnoreCase("Acct. Num"))||(columns.getText().equalsIgnoreCase("Email"))
				||(columns.getText().equalsIgnoreCase("Silver Ambassador thru Silver Leader in 1st Gen.")))){				
				columns.click();
			}
		}Thread.sleep(8000);	
		clickOnElement ("cssSelector", resultsCount);
      }
    
    
    public void selectAllFilterOptions(String columnName) throws Exception{
    	
    	clickOnElement ("cssSelector",filterOp );
    	Thread.sleep(2000);
    	
    	selectFromDropdown("cssSelector", filterAchievementTitleBetween, "byVisibleText", "Promoter");
    	List <WebElement> op = driver().findElements(By.cssSelector(outputFilterOptions));    	
    	for (int i =3; i<=op.size(); i++){
    		WebElement opName = driver().findElement(By.cssSelector(outputFilterOpBfr+i+outputFilterOpAfr));    		
    		WebElement opChkBox = driver().findElement(By.cssSelector(outputFilterOpBfr+i+outputFilterOpAfr+filterChkBox));
    		if(!opChkBox.isSelected()){
				opChkBox.click();   				
			}}
    	selectFilterOption(columnName);
    	Thread.sleep(3000);
    	clickOnElement("cssSelector", runReport);
    	
    	}
      public void selectFilterOption(String filterOption1) throws Exception{
    	 Thread.sleep(2000);
 	    boolean isOptionPresent = false; 
 		List <WebElement> op2 = driver().findElements(By.cssSelector(outputFilterOptions));   
 		for (int i =3; i<=op2.size(); i++){
     		WebElement opName2 = driver().findElement(By.cssSelector(outputFilterOpBfr+i+outputFilterOpAfr));     		
     		WebElement opChkBox2 = driver().findElement(By.cssSelector(outputFilterOpBfr+i+outputFilterOpAfr+filterChkBox));
     		if (!(opName2.getText().equalsIgnoreCase(filterOption1))){
     			isOptionPresent = true;     			
 			    if(opChkBox2.isSelected()){
 				   opChkBox2.click();   				
 						}
 			       	}
 		     }if (isOptionPresent==false){
 		    	 Assert.assertTrue(isOptionPresent, filterOption1 + " filter option is not present in OutputFilters" );
 		     }
     }
    	
    	
    
    
    
      public void personalProfile (String detailType, String expectedData) throws Exception{	  
    	   boolean isPresent = false;
    	   waitOnElement("cssSelector", pProfile2);
    	   List <WebElement> proSec = driver().findElements(By.cssSelector(pProfile2));
    	   System.out.println("No of details "+ proSec.size());
    	   for (int i=1; i<=proSec.size(); i++){
    		   WebElement profdetls = driver().findElement(By.cssSelector(pProfile2Bfr+i+pProfile2Aft));
    		   String profileDetails = profdetls.getText().trim();
    		   System.out.println(profileDetails);
    		
    		   if (profileDetails.equalsIgnoreCase(detailType)){
    			   System.out.println(profileDetails + "inside");
    			   isPresent= true;
    			   WebElement profdata = driver().findElement(By.cssSelector(pProfile2Bfr+i+pProfile2DataAft));
    			   String data = profdata.getText();
    			   System.out.println(detailType+ " "+ profdata.getText());
    			   String[] parts = StringUtils.split(data," ");
    				String ActualData = parts[0];
    			   
    			   Assert.assertEquals(ActualData, expectedData);
    			   break; 
    			   	   }  		   
    		   
    	   }if (isPresent==false){
    		   Assert.assertTrue(isPresent, detailType + " is not present in Personal Profile.");
    	   }   	  
      }
      
      
      public void profileSections(String sectionName) throws Exception{
    	  boolean isSecPresent = false;    	 
    	  waitOnElement("cssSelector", "div.panel-title");
    	  List <WebElement> sec = driver().findElements(By.cssSelector(profSec));
    	  System.out.println("Sec "+ sec.size());
    	  for (int i=1; i<=sec.size();i++){
    		  Thread.sleep(3000);
    		WebElement sections = driver().findElement(By.cssSelector(profSecBfr+i+profSecAft));   
    		  System.out.println("sections are "+ sections.getText());
    		  if (sections.getText().equalsIgnoreCase(sectionName)){
    			  isSecPresent= true;
    			  sections.click();
    			  break;
    		  }
    	  }if (isSecPresent==false){
    		  Assert.assertTrue(isSecPresent, sectionName+" is not present");
    	  }    	  
      }
      
      
      public void personalInfomation(String sectionName, String expectedValue){
    	  boolean isSecPresent = false;
    	  List <WebElement> sec = driver().findElements(By.cssSelector(perInfo));
    	  System.out.println("SecData "+ sec.size());
    	  for (int i=1; i<=sec.size(); i++){
    		  WebElement name = driver().findElement(By.cssSelector(perInfoBfr+i+perInfoAftr));
    		  System.out.println("sectData are "+ name.getText());
    		  if (name.getText().equalsIgnoreCase(sectionName)){
    			  isSecPresent= true;
    			  WebElement nameOfData = driver().findElement(By.cssSelector(perInfoBfr+i+perInfoDataAftr));
    			  String actualValue = nameOfData.getText().trim();
    			  System.out.println("actualValue of "  +sectionName + " "+ actualValue);
    			  Assert.assertEquals(actualValue, expectedValue);    			 
    			  break;
    		  }
    	  }if (isSecPresent==false){
    		  Assert.assertTrue(isSecPresent, sectionName+" is not present");
    	  }    	  
      }
      
      public void salesPerformance(String expTotal_cmp, String expAvg_cmp,
    			String expPy_award_sales, String expCy_award_sales/*, String expAvgPYAwards, String expAvgCYAwards*/) throws Exception{
    	  
    	  profileSections("Sales Performance");
    	  
    	  WebElement tCMP = driver().findElement(By.cssSelector(totalCMP));
    	  String tcmp = tCMP.getText();    	  
    	  String[] partsCMP = StringUtils.split(tcmp," ");
  		  String ActTotalCMP = partsCMP[0]+partsCMP[1];
    	  
    	  WebElement AvgeCMP = driver().findElement(By.cssSelector(AvgCMP));
    	  String avgCMP = AvgeCMP.getText();
    	  String[] partsAvg = StringUtils.split(avgCMP," ");
    	  String ActAvgCMP = partsAvg[0]+partsAvg[1];
    	  
    	  WebElement TPYAwards = driver().findElement(By.cssSelector(TotalPYAwards));
    	  String ActTotalPYAwards = TPYAwards.getText();
    	  
    	  WebElement AvgePYAwards = driver().findElement(By.cssSelector(AvgPYAwards));
    	  String ActAvgPYAwards = AvgePYAwards.getText();
    	  
    	  WebElement TCYAwards = driver().findElement(By.cssSelector(TotalCYAwards));
    	  String ActTotalCYAwards = TCYAwards.getText();
    	  
    	  WebElement AvgeCYAwards = driver().findElement(By.cssSelector(AvgCYAwards));
    	  String ActAvgCYAwards = AvgeCYAwards.getText();
    	  
    	  System.out.println("ActTotalCMP         " + ActTotalCMP   + " " + expTotal_cmp);
    	  System.out.println("ActAvgCMP    " + ActAvgCMP+ "   " +expAvg_cmp );
    	  System.out.println("ActTotalPYAwards  " + ActTotalPYAwards+ " " +expPy_award_sales );
    	  System.out.println("ActAvgPYAwards      " + ActAvgPYAwards );
    	  System.out.println("ActTotalCYAwards   " + ActTotalCYAwards+ " " +expCy_award_sales );
    	  System.out.println("ActAvgCYAwards    " + ActAvgCYAwards);
    	  
    	  Assert.assertEquals(ActTotalCMP, expTotal_cmp);
    	  Assert.assertEquals(ActAvgCMP,  expAvg_cmp);
    	  Assert.assertEquals(ActTotalPYAwards, expPy_award_sales);    	 
    	  Assert.assertEquals(ActTotalCYAwards, expCy_award_sales);
    	  /*Assert.assertEquals(ActAvgPYAwards, expAvgPYAwards);
    	  Assert.assertEquals(ActAvgCYAwards, expAvgCYAwards);*/
    	    	 
      }
      
      
      public void userLogotNdAdminLogout() throws Exception{
    	  
    	 waitOnElement("cssSelector",btnCloseGroup);	
 		 clickOnElement("cssSelector",btnCloseGroup);
 		 driver().navigate().refresh();
 		
 		 userLogout(); 		 
 		 driver().navigate().to(appUrl);
 		 
 		 logOutInAvon();
 		 
    	  
      }
      
      
      public void contactList(String typeOfContact) throws Exception{
 		waitOnElement("cssSelector", conatctButton);
 		 boolean isInContact = false;
 		 clickOnElement("cssSelector", conatctButton); 		 	
 		 List <WebElement> exList = driver().findElements(By.cssSelector(conatctDP));
 		 System.out.println(exList.size());
 		 for (WebElement el : exList){		
 		    System.out.println(el.getText());
 			if (el.getText().equalsIgnoreCase(typeOfContact)){
 				isInContact = true;
 				el.click();
 				break;
 			}
 		 }if (isInContact==false){
 			 Assert.assertTrue(isInContact, typeOfContact + " is not present in Contatct options");
 		 }

 	 }
      
      
      //Send Bulk Email to all with Subject & Message. 
      public void SendBulkMail(String message) throws Exception{		  
  		logInfo("Entered into SendBulkMail(String message) method");  	
  		waitOnElement("cssSelector", bulkSubject);
  		inputText("cssSelector", bulkSubject, message); 
  		composeText("xpath",composeBody,composeBodyText);
  		inputText("cssSelector", bulkMsg, msgText);		
  			
  		clickOnButton("cssSelector", bulkSendMail);		 

  	 }
      
      
      public void go2EditWidgetsInAvon() throws Exception{
  		logInfo("inside go2EditWidgetsPage() method.");
  		System.out.println("inside go2EditWidgetsPage() method.");
  		driver().navigate().to(appUrl);
  		clickOnLink("linkText","Edit Panels");
  	}
      
      public void collapseEditWidget() throws InterruptedException, Exception{		
	    	clickOnElement("cssSelector",closeWidgetInHomeBranch);
		  		}
      
   // drag and drop the widget from edit widgets panel.
  	
  		public void dragAndDropWidget(String widgetName) throws Exception{
  			logInfo("inside dragAndDropWidget() method.");
  			System.out.println("inside dragAndDropWidget() method.");		
  			
  			 go2EditWidgetsInAvon();
  			
  			String widgName=null;
  			switch(widgetName){ 			
  			
  			case "Notifications":
  				widgName="//*[@id='source_pyr_community_widgets_notifications']";
  				break;
  			case "Progress Report":
  				widgName="//*[@id='source_pyr_tree_widgets_progress_report_main']";  // //*[@id='source_pyr_tree_widgets_progress_report']
  				break;
  			case "Business Dashboard":
  				widgName="//*[@id='source_application_widgets_kpi_main']";  // //*[@id='source_pyr_tree_widgets_progress_report']
  				break;	
  			case "Action Alerts":
  				widgName="//*[@id='source_pyr_tree_widgets_alert_actions']";  // //*[@id='source_pyr_tree_widgets_progress_report']
  				break;	
  			case "Search Contacts":
				widgName="//*[@id='source_pyr_crm_widgets_search_contacts']";
				break;	
  			case "Relationship Manager Quick Links":
				widgName="//*[@id='source_pyr_core_widgets_user_control_panel']";
				break;	
  			case "Banners":
				widgName="//*[@id='source_pyr_core_widgets_carousel_ads_main']";
				break;
  			case "Business Alerts":
				widgName="//*[@id='source_pyr_community_widgets_business_alerts']";
				break;
  			
  			}
  			
  			System.out.println(widgName);
  			WebElement from = driver().findElement(By.xpath(widgName));
  			WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));  // dragWidgetToLocation
  			
  			
  			Actions builder = new Actions(driver());
  			Action drag = builder.clickAndHold(from).moveToElement(to).release(to).build();
  			drag.perform();	
  			Thread.sleep(10000);
  			
  			collapseEditWidget();	
  			
  		}	
  		
  		/*//"Drag Progress Report from Edit Widgets Link ");
  		public void dragProgressReport() throws Exception{  		
  		logInfo("Entered into dragProgressReport() method");  		
  			List <WebElement> ne = driver().findElements(By.xpath(progReportWidget));
  			System.out.println(ne.size());
  			for (WebElement ns : ne){			
  				System.out.println(ns.getText());			
  				if (ns.getText().contains("Progress Report")){				
  					Thread.sleep(2000);
  					WebElement from = driver().findElement(By.xpath(progReportWidget));
  					WebElement to = driver().findElement(By.cssSelector(dragLocation));
  					//WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));
  					dragAndDropAction(from, to);
  					Thread.sleep(5000);
  					break;
  				}		
  			}
  		}
  	
  		public void dragAndDropAction(WebElement from, WebElement to) throws Exception{		
  			logInfo("Dragging the widgets");
  			Thread.sleep(5000);
  			Actions builder = new Actions(driver);
  			Action drag = builder.clickAndHold(from).
  					moveToElement(to).
  					release(to).
  					build();
  			drag.perform();			
  		}
    */
  		
  	public void verifyItemsInWidget() throws Exception{
  		logInfo("Entered into verifyItemsInWidget() method ");
  		String expDownTitle = "Downline Leaders";
  		WebElement downTit = driver().findElement(By.cssSelector(downTitle));
  		String actTitle = downTit.getText().trim();
  		System.out.println(actTitle);
  		Assert.assertEquals(actTitle, expDownTitle);  
  		WebElement email = driver().findElement(By.cssSelector(repEmail));
  		String actEmail= email.getText().trim();
  		System.out.println(actEmail);
  		
  		WebElement rank = driver().findElement(By.cssSelector(repRank));
  		String actRank= rank.getText().trim();
  		System.out.println(actRank);
  		
  		WebElement ids = driver().findElement(By.cssSelector(actId));
  		String actRepId = ids.getText().trim();
  		System.out.println(actRepId);
  		Assert.assertEquals(actRepId, repID);
  		waitOnElement("cssSelector", repName);
  		clickOnElement("cssSelector", repName);
  		profileSections("Profile");	
  		personalProfile("Email", actEmail);	
  		WebElement expLead = driver().findElement(By.cssSelector(ldShipTitle));
  		String expectedTitle = expLead.getText().trim();
  		System.out.println(expectedTitle);
  		Assert.assertEquals(actRank, expectedTitle);
  		
  		
  	}
  	
  	
  	public void verifyBusinessDashBoard() throws Exception{
  		logInfo("Entered into verifyBusinessDashBoard() method ");  		
  		waitOnElement("linkText", "Show More");
  		clickOnLink("linkText", "Show More");
  		verifyCampaign("Current Campaign");
  		verifyCampaign("Next Campaign");
  		verifyCampaign("Previous Campaign");
  	
  	}
  	
  	
  	public void verifyCampaign(String campaignType) throws Exception, Exception{
  		logInfo("Entered into verifyCampaign() method ");
  		boolean isfound = false;
  		clickOnElement("cssSelector", camp);
  		waitOnElement("cssSelector", "input#widgets_search_text");
  		List <WebElement> camps = driver().findElements(By.cssSelector(campOp));
  		System.out.println("size us "+ camps.size());
  		for (WebElement campnign :camps){
  			String campaign = campnign.getText().trim();
  			
  			if (campaign.contains(campaignType)){
  				isfound =true;
  				System.out.println(campaign);
  				break; 				
  			}
  			
  		}if (isfound==false){
  			Assert.assertTrue(isfound, campaignType+ "is not found " );
  		}
  		 		
  	}
  	
  	
  	public void verifylinksOfActionAlerts() throws Exception{
  		logInfo("Entered into verifylinksOfActionAlerts method.");
  		
  		List <WebElement> links = driver().findElements(By.cssSelector(acLink));
  		System.out.println("size links "+ links.size());
  		for (int i=1; i<=links.size(); i++){
  			WebElement link = driver().findElement(By.cssSelector(acLinkBfr+i+acLinkAft));
  			String LinkText =link.getText().trim();  			
  			String[] spitLink = StringUtils.split(LinkText," ");
  			String count = spitLink[0];
  			int expectedCount = Integer.parseInt(count);
  			System.out.println(i+ " count is "+ expectedCount);
  			link.click();
  			waitOnElement("xpath", reportTableFrame);
  			int actualcount  = resultFound();
  			System.out.println("actula count "+ actualcount);
  			Assert.assertEquals(actualcount, expectedCount);
  			back2Office();
  		}
  		
  	}
  		
  		
  		 //  Search Contact from the Widget.
		
		 public boolean searchContactFromWidget(String contactName) throws Exception, Exception{
			 logInfo("Inside searchContactFromWidget() method.");
			 Thread.sleep(10000);
			 waitOnElement("xpath","//div[@id='search_contacts']");
			 inputTextClear("xpath", "//div[@id='search_contacts']/div[2]/div/div[1]/input[@id='contact_query']");
			 inputText("xpath", "//div[@id='search_contacts']/div[2]/div/div[1]/input[@id='contact_query']",contactName );
			 clickOnElement("xpath","//div[@id='search_contacts']/div[2]/div/div[2]/button[@id='search-contacts-widget-button']/i");
			Thread.sleep(10000);
			 boolean isContactMatchFound = false;
			 
			 WebElement eautoSearch = driver().findElement(By.cssSelector(".ui-autocomplete.ui-front.ui-menu.ui-widget.ui-widget-content"));
			 List<WebElement> allItems = eautoSearch.findElements(By.cssSelector(".ui-menu-item"));
			 if(allItems.size() >0){
				for(WebElement e : allItems){
					String x = e.getText().trim();
					System.out.println("x =" +x);
					if(x.equalsIgnoreCase(contactName)){
						isContactMatchFound=true;
						logInfo(contactName + " contact found in Search Contact widget.");
						e.click();
						waitOnElement("cssSelector", contName);
						WebElement actNa = driver().findElement(By.cssSelector(contName));
						String actualName = actNa.getText().trim();
						System.out.println("actualName"+ actualName);
						
						Assert.assertEquals(actualName, contactName);
						break;
					}
				}
			 }
			 
			 if(isContactMatchFound==false){
				 logInfo(contactName + " contact could not be found in Search Contact widget.");
				 Assert.assertTrue(isContactMatchFound, contactName + " contact could not be found in Search Contact widget.");
			 }
			return isContactMatchFound;
		 }
	  
  		
  		
		  	public void verifylinksOfBusinessAlerts() throws Exception{
		  		logInfo("Entered into verifylinksOfActionAlerts method.");
		  		
		  		List <WebElement> links = driver().findElements(By.cssSelector(acLink));
		  		System.out.println("size links "+ links.size());
		  		for (int i=1; i<=links.size(); i++){
		  			WebElement link = driver().findElement(By.cssSelector(acLinkBfr+i+acLinkAft));
		  			String LinkText =link.getText().trim();  			
		  			String[] spitLink = StringUtils.split(LinkText," ");
		  			String count = spitLink[0];
		  			int expectedCount = Integer.parseInt(count);
		  			System.out.println(i+ " count is "+ expectedCount);
		  			link.click();
		  			waitOnElement("xpath", reportTableFrame);
		  			int actualcount  = resultFound();
		  			System.out.println("actula count "+ actualcount);
		  			Assert.assertEquals(actualcount, expectedCount);
		  			back2Office();
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
	 
	 public void typesofReports() throws Exception, Exception{		 
			logInfo("Assertions all types of reports with their Table title.");	
				List <WebElement> reportstable = driver().findElements(By.cssSelector(rpTab));
				System.out.println("No of Reports in the table is "+reportstable.size());
				if(reportstable.size()==0){
					Assert.assertNotEquals(reportstable.size(), 0);
				}else{
				
				for (int i=2; i<=reportstable.size()+1; i++){				
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
							}
						}
				}
				
		}
  	
	 public void createEvent(String event, int noOfDaysToCurrent) throws Exception{
		  
			logInfo("Create Event from Reports");
			String eventDate = getDate(noOfDaysToCurrent, "MM/dd/yyyy")	;	
			String endDate = getDate(noOfDaysToCurrent+2, "MM/dd/yyyy")	;
			//clickOnLink("linkText", "Add Event");
			waitOnElement("cssSelector", repAddevent);
			clickOnElement("cssSelector", repAddevent);
			waitOnElement("cssSelector", evName);
			inputText("cssSelector", evName, event); 
			inputText("cssSelector",sDate, eventDate ); 
			inputText("cssSelector",eDate, endDate );
			clickOnButton("cssSelector", eventSave);
			confirmationMessage("Your event has been created.");
			Thread.sleep(7000);
			selectEvent(event);
			inputText("cssSelector",sub, event ); 
			inputText("cssSelector",eventbody, subjectBulk);
			clickOnButton("cssSelector", sendInv);
			confirmationMessage("Invitations Sent");
			Thread.sleep(5000);

		 }
	 
	 public void createEvent_old(String event, int noOfDaysToCurrent) throws Exception{
		  
			logInfo("Create Event from Reports");
			String eventDate = getDate(noOfDaysToCurrent, "MM/dd/yyyy")	;	
			String endDate = getDate(noOfDaysToCurrent+2, "MM/dd/yyyy")	;
			clickOnLink("linkText", "Add Event");
			waitOnElement("cssSelector", evName);
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
	 
	 public void selectEvent (String EventText ) throws Exception{	
			//clickOnElement("cssSelector ", eventID);
			Thread.sleep(2000);
			WebElement eve = driver().findElement(By.cssSelector(eventID));		
			Select sel = new Select (eve);
			sel.selectByVisibleText(EventText);		
		}
  		
  	
  	
  	
  	
  		
  		
}