package vibe.enrollment.tests;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import vibe.users.tests.UsersMethods;
import common.TestBase;
import common.readProp;


//Enrollment for branch-1-10.vibeoffice.com environment.

public class Monat_EnrollMethods extends TestBase{
	 readProp prop = new readProp();
	
	// Enrollment @ Admin methods	
	UsersMethods um = new UsersMethods();
	
	public void go2EnrollmentProfilePage(){
		logInfo("inside go2EnrollmentProfilePage() page.");
		driver().navigate().to(appUrl + "pyr_core/enrollment_profiles");
	}
	
	
	public void go2Users(){
		logInfo("inside go2Users() method.");
		driver().navigate().to(appUrl + "pyr_core/users");
	}
	
	
	public void loginAsUser(String user) throws Exception{          
		logInfo("inside loginAsUser() method.");
        um.go2Users();
        um.searchUser(user);
        boolean isUserFound = um.verifyUserPresent(user);
        if(isUserFound){             
            clickOnLink("linkText", "Login As User");             
        } 
    }
	
	
	public void selectEnrollProfileMarkets(String[] enableMarkets, String[] defaultMarkets) throws Exception{
		logInfo("inside selectEnrollProfileMarkets() method.");
		
		clickOnLink("linkText","Edit Markets");
		
		// Select Enable Markets languages
		waitOnElement("cssSelector","div.form-group.check_boxes.optional.pyr_core_enrollment_profile_market_ids");
		
		WebElement eMarkets = driver().findElement(By.cssSelector("div.form-group.check_boxes.optional.pyr_core_enrollment_profile_market_ids"));
		List allCheckboxes = eMarkets.findElements(By.className("checkbox"));
		int all_checks = allCheckboxes.size();
		
		String before_enbchk = "//*[@class='form-group check_boxes optional pyr_core_enrollment_profile_market_ids']/span[";
		String after_enbchk = "]/label/input";
		
		String before_enbName = "//*[@class='form-group check_boxes optional pyr_core_enrollment_profile_market_ids']/span[";
		String after_enbName = "]/label";
		
		int all_enableMarkets = enableMarkets.length;
		
		for(int i=1;i<=all_checks;i++){
			for(int j=0;j<=all_enableMarkets-1;j++){
				WebElement eEnbName = driver().findElement(By.xpath(before_enbName+i+after_enbName));
				String enbName = eEnbName.getText().trim();
				System.out.println("Enable Market Lang = " +enbName);
				System.out.println("Expected Enable Market = " +enableMarkets[j]);
				WebElement einput = driver().findElement(By.xpath(before_enbchk+i+after_enbchk));
				if(enbName.equalsIgnoreCase(enableMarkets[j]) ){  // && isSelected.equalsIgnoreCase("false")
					System.out.println("Matching Enable Market Lang ====> " +enbName);
					einput.click();
					Thread.sleep(1000);
					break;
				}
			}
		}
		
		
		// Select Default Markets languages
		
		WebElement edefMarkets = driver().findElement(By.cssSelector("div.form-group.check_boxes.optional.pyr_core_enrollment_profile_default_market_ids"));
		List allDefCheckboxes = edefMarkets.findElements(By.className("checkbox"));
		int all_defchecks = allDefCheckboxes.size();
		
		String before_defchk = "//*[@class='form-group check_boxes optional pyr_core_enrollment_profile_default_market_ids']/span[";
		String after_defchk = "]/label/input";
		
		String before_defName = "//*[@class='form-group check_boxes optional pyr_core_enrollment_profile_default_market_ids']/span[";
		String after_defName = "]/label";
		
		int all_defMarkets = defaultMarkets.length;
		
		for(int k=1;k<=all_defchecks;k++){
			for(int l=0;l<=all_defMarkets-1;l++){
				WebElement edefName = driver().findElement(By.xpath(before_defName+k+after_defName));
				String defName = edefName.getText().trim();
				System.out.println("Default Market Lang = " +defName);
				
				WebElement einput = driver().findElement(By.xpath(before_defchk+k+after_defchk));
				if(defName.equalsIgnoreCase(defaultMarkets[l]) ){  
					System.out.println("Matching Default Market Lang ====> " +defName);
					einput.click();
					Thread.sleep(1000);
					break;
				}
			}
		}
		
		
		clickOnElement("xpath","//*[@id='enrollment-profile-default-markets-modal']/div/div[1]/div[3]/button");
	}
	
	
	public void addPage(String pageName, String pageType) throws Exception{
		logInfo("inside addPage() method.");
		Thread.sleep(10000);
		clickOnLink("linkText","Add Page");
		Thread.sleep(5000);
		clickOnElement("cssSelector", "#pyr_core_enrollment_page_name");
		inputText("cssSelector", "#pyr_core_enrollment_page_name",pageName);
		clickOnElement("cssSelector", "#pyr_core_enrollment_page_description");
		inputText("cssSelector", "#pyr_core_enrollment_page_description", pageName+" description.");
		selectFromDropdown("cssSelector", "#pyr_core_enrollment_page_type", "byVisibleText", pageType);
		clickOnElement("xpath","//*[@id='new-page-create-modal']/div/div[1]/div[3]/button");
		Thread.sleep(5000);
	}
	
	
	public void addReviewPage(String pageName, String pageType) throws Exception{
		logInfo("inside addReviewPage() method.");
		Thread.sleep(10000);
		clickOnLink("linkText","Add Review Page");
		Thread.sleep(5000);
		clickOnElement("cssSelector", "#pyr_core_enrollment_page_name");
		inputText("cssSelector", "#pyr_core_enrollment_page_name",pageName);
		clickOnElement("cssSelector", "#pyr_core_enrollment_page_description");
		inputText("cssSelector", "#pyr_core_enrollment_page_description", pageName+" description.");
		selectFromDropdown("cssSelector", "#pyr_core_enrollment_page_type", "byVisibleText", pageType);
		clickOnElement("xpath","//*[@id='new-page-create-modal']/div/div[1]/div[3]/button");
		Thread.sleep(5000);
		
	}
    
	public void addNewProfile(String profileName, String backend) throws Exception{
		logInfo("inside addNewProfile() method.");
		
		clickOnLink("linkText", "Add Profile");
		// waitOnElement("cssSelector", "#pyr_core_enrollment_profile_name");
		Thread.sleep(5000);
		inputText("cssSelector", "#pyr_core_enrollment_profile_name", profileName);
		inputText("cssSelector","#pyr_core_enrollment_profile_description", profileName+ " description.");
		selectFromDropdown("cssSelector","#pyr_core_enrollment_profile_backend","byVisibleText",backend);
		
		clickOnElement("cssSelector",".modal-content  > .modal-footer  > .btn.btn-primary");
		// ConfirmationMessage("Profile created successfully.Redirecting to edit additional details.");
		
		selectEnrollProfileMarkets(enrollEnableMarketlist,enrollDefaultMarketlist);
		
		clickOnElement("cssSelector","form.simple_form.edit_pyr_core_enrollment_profile > div.pull-right > input.btn.btn-primary");
		
	}
	
	public boolean verifyEnrollmentProfileIsPresent(String profileName) throws IOException{
		logInfo("inside verifyEnrollmentProfileIsPresent() method.");
		
		 String enrollmentTbl = prop.getLocatorForEnvironment(appUrl,"enrollmentTbl");
		 String before_profile = prop.getLocatorForEnvironment(appUrl,"before_profile");
		 
		go2EnrollmentProfilePage();
		
		WebElement profilePanel = driver().findElement(By.xpath(enrollmentTbl));  // //*[@id='main-content']/table/tbody
		List allRows = profilePanel.findElements(By.tagName("tr"));
		int all_rows = allRows.size();
		
		//String before_profile = "//*[@id='main-content']/div/div[2]/table/tbody/tr[";  // //*[@id='main-content']/table/tbody/tr[
		String after_profile = "]/td[1]";
		boolean isProfileFound = false;
		
		if(all_rows>0){
			for(int i=1;i<=all_rows;i++){
				WebElement eProfile = driver().findElement(By.xpath(before_profile+i+after_profile));
				String actProfile = eProfile.getText().trim();
				if(actProfile.equalsIgnoreCase(profileName)){
					isProfileFound = true;
					logInfo(profileName + " profile match found in enrollment profile table.");
					break;
				}
			}
			
		}
		return isProfileFound;
	}
	
	
	
	public void editEnrollmentProfile(String profileName) throws IOException{
		logInfo("inside editEnrollmentProfile() method.");
		go2EnrollmentProfilePage();
		
		 String enrollmentTbl = prop.getLocatorForEnvironment(appUrl,"enrollmentTbl");
		 String before_profile = prop.getLocatorForEnvironment(appUrl,"before_profile");
		 String before_edit = prop.getLocatorForEnvironment(appUrl,"before_edit");
		 
		WebElement profilePanel = driver().findElement(By.xpath(enrollmentTbl));
		List allRows = profilePanel.findElements(By.tagName("tr"));
		int all_rows = allRows.size();
		
		// String before_profile = "//*[@id='main-content']/table/tbody/tr[";
		String after_profile = "]/td[1]";
		
		// String before_edit = "//*[@id='main-content']/table/tbody/tr[";
		String after_edit = "]/td[5]/a[1]/i";
				
		boolean isProfileFound = false;
		
		if(all_rows>0){
			for(int i=1;i<=all_rows;i++){
				WebElement eProfile = driver().findElement(By.xpath(before_profile+i+after_profile));
				String actProfile = eProfile.getText().trim();
				if(actProfile.equalsIgnoreCase(profileName)){
					isProfileFound = true;
					logInfo(profileName + " profile match found in enrollment profile table.");
					WebElement editProfile = driver().findElement(By.xpath(before_edit+i+after_edit));
					editProfile.click();
					break;
				}
			}
			
		}
	}
	
	
	public void addFields2Page(String pageName, String tabName, String[] selectedFields) throws Exception, Exception{
		logInfo("inside addFields2Page() method.");
		
		 String after_headingName = prop.getLocatorForEnvironment(appUrl,"after_headingName");
		 String after_collapse = prop.getLocatorForEnvironment(appUrl,"after_collapse");
		
		waitOnElement("xpath","//*[@id='pages_panel_group']");
		Thread.sleep(5000);
		
		WebElement pageContainer = driver().findElement(By.xpath("//*[@id='pages_panel_group']"));
		List allPages = pageContainer.findElements(By.cssSelector("div.panel.panel-default.panel-parent"));
		int total_pages = allPages.size();
		
		System.out.println("total_pages =" +total_pages);
		
		String before_headingName = "//*[@id='pages_panel_group']/div[";
		//String after_headingName = "]/div[@class='panel-heading']/div[@class='panel-title']/div[@class='row']/div[@class='col-md-8']/a/span";
		
		String before_collapse = "//*[@id='pages_panel_group']/div[";
		//String after_collapse = "]/div[@class='panel-heading']/div[@class='panel-title']/div[@class='row']/div[@class='col-md-1 text-right']/a[2]/i";
		
		String before_addFields = "//*[@id='pages_panel_group']/div[";
		String after_addFields = "]/div[@class='panel-collapse panel-body collapse in']/form/div[@class='row']/div[2]/a";
		
		if(total_pages >0){
			for(int i=1; i<=total_pages; i++){
				WebElement ePageheading = driver().findElement(By.xpath(before_headingName+i+after_headingName));
				String actPageHeading = ePageheading.getText().trim();
				if(actPageHeading.equalsIgnoreCase(pageName)){
					System.out.println("i = " +i);
					System.out.println(pageName + " page selected");
					logInfo(pageName + " page title match found.");
					WebElement ebtnCollapse = driver().findElement(By.xpath(before_collapse+i+after_collapse));
					ebtnCollapse.click();
					logInfo("clicked on expand/collapse button of page -> " +pageName );
					
					waitOnElement("xpath",before_addFields+i+after_addFields);
					
					WebElement eAddFields = driver().findElement(By.xpath(before_addFields+i+after_addFields));
					eAddFields.click();
					logInfo("clicked on Add Fields link of page -> " +pageName );
					Thread.sleep(5000);
					
					// Add Fields pane
					
					WebElement eSelectFieldsTab = driver().findElement(By.xpath("//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/ul"));
					List<WebElement> allTabs = eSelectFieldsTab.findElements(By.tagName("li"));
					for(WebElement x : allTabs){
						String actTabName = x.getText().trim();
						if(actTabName.equalsIgnoreCase(tabName)){
							logInfo(tabName + " tab match found in page " +pageName );
							x.click();
							logInfo("clicked on tab " +tabName );
							
							switch(actTabName){
							case "Select" :
								WebElement eSelectTabPanel = driver().findElement(By.xpath("//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/div[@class='tab-content']/div[1]/form/div[@class='row']/div"));
								List allCheckBoxes = eSelectTabPanel.findElements(By.tagName("span"));
								int total_checkboxes = allCheckBoxes.size();
								
								int selected_fields = selectedFields.length;
								
								String before_label = "//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/div[@class='tab-content']/div[1]/form/div[@class='row']/div/span[";
								String after_label = "]/label";
								
								String before_input = "//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/div[@class='tab-content']/div[1]/form/div[@class='row']/div/span[";
								String after_input = "]/label/input";
								
								
								for(int k=1;k<=total_checkboxes;k++){
									for(int l=0; l<=selected_fields-1; l++){
											WebElement eActlabel = driver().findElement(By.xpath(before_label+k+after_label));
											String actlabel = eActlabel.getText().trim();
											WebElement eChkinput = driver().findElement(By.xpath(before_input+k+after_input));
											if(actlabel.equalsIgnoreCase(selectedFields[l])){
												System.out.println("actlabel =" +actlabel);
												eChkinput.click();
												break;
											}
										}
									}
								
								clickOnElement("xpath","//*[@id='page-fields-config-modal']/div/div[1]/div[3]/button[1]");
								Thread.sleep(10000);
								ebtnCollapse.click();
								
							}
							
							break;
						}
					}
							
					break;
				}
			}
		}
		
		// remove later
		 clickOnElement("xpath","//*[@id='enrollment-profile-default-markets-modal']/div/div[1]/div[3]/button");
		 Thread.sleep(2000);
	}
	
	
	
	
	// configure Required Fields
	
		public void addRequiredFields2Page(String pageName, String tabName, String[] selectedFields) throws InterruptedException, IOException{
			logInfo("inside addRequiredFields2Page() method.");
			
			 String after_headingName = prop.getLocatorForEnvironment(appUrl,"after_headingName");
			 String after_collapse = prop.getLocatorForEnvironment(appUrl,"after_collapse");
			 
			WebElement pageContainer = driver().findElement(By.xpath("//*[@id='pages_panel_group']"));
			List allPages = pageContainer.findElements(By.cssSelector("div.panel.panel-default.panel-parent"));
			int total_pages = allPages.size();
			
			System.out.println("total_pages =" +total_pages);
			
			String before_headingName = "//*[@id='pages_panel_group']/div[";
			//String after_headingName = "]/div[@class='panel-heading']/div[@class='panel-title']/div[@class='row']/div[@class='col-md-8']/a/span";
			
			String before_collapse = "//*[@id='pages_panel_group']/div[";
			//String after_collapse = "]/div[@class='panel-heading']/div[@class='panel-title']/div[@class='row']/div[@class='col-md-1 text-right']/a[2]/i";
			
			String before_configFields = "//*[@id='pages_panel_group']/div[";
			String after_configFields = "]/div[@class='panel-collapse panel-body collapse in']/form/div[@class='row']/div[2]/a";
			
			if(total_pages >0){
				for(int i=1; i<=total_pages; i++){
					WebElement ePageheading = driver().findElement(By.xpath(before_headingName+i+after_headingName));
					String actPageHeading = ePageheading.getText().trim();
					if(actPageHeading.equalsIgnoreCase(pageName)){
						System.out.println("i = " +i);
						System.out.println(pageName + " page selected");
						logInfo(pageName + " page title match found.");
						WebElement ebtnCollapse = driver().findElement(By.xpath(before_collapse+i+after_collapse));
						ebtnCollapse.click();
						logInfo("clicked on expand/collapse button of page -> " +pageName );
						
						WebElement eAddFields = driver().findElement(By.xpath(before_configFields+i+after_configFields));
						eAddFields.click();
						logInfo("clicked on Configure Fields link of page -> " +pageName );
						Thread.sleep(5000);
						
						// Add Fields pane
						
						WebElement eSelectFieldsTab = driver().findElement(By.xpath("//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/ul"));
						List<WebElement> allTabs = eSelectFieldsTab.findElements(By.tagName("li"));
						for(WebElement x : allTabs){
							String actTabName = x.getText().trim();
							if(actTabName.equalsIgnoreCase(tabName)){
								logInfo(tabName + " tab match found in page " +pageName );
								x.click();
								logInfo("clicked on tab " +tabName );
								
								switch(actTabName){
								case "Required" :
									WebElement eReqTabPanel = driver().findElement(By.xpath("//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/div[@class='tab-content']/div[3]/form/div[@class='row']/div"));
									List allReqCheckBoxes = eReqTabPanel.findElements(By.tagName("span"));
									
									int total_reqcheckboxes = allReqCheckBoxes.size();
									
									int selected_fields = selectedFields.length;
									
									String before_label = "//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/div[@class='tab-content']/div[3]/form/div[@class='row']/div/span[";
									String after_label = "]/label";
									
									String before_input = "//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/div[@class='tab-content']/div[3]/form/div[@class='row']/div/span[";
									String after_input = "]/label/input";
									
									
									// unselect all the checkboxes in the required fields panel.
									
									for(int m=1;m<=total_reqcheckboxes;m++){
										WebElement eChkinput = driver().findElement(By.xpath(before_input+m+after_input));
										eChkinput.click();
									}
									
									// select the required fields only in the list
									
									for(int k=1;k<=total_reqcheckboxes;k++){
										for(int l=0; l<=selected_fields-1; l++){
												WebElement eActlabel = driver().findElement(By.xpath(before_label+k+after_label));
												String actlabel = eActlabel.getText().trim();
												WebElement eChkinput = driver().findElement(By.xpath(before_input+k+after_input));
												if(actlabel.equalsIgnoreCase(selectedFields[l])){
													System.out.println("actlabel =" +actlabel);
													if(!eChkinput.isSelected()){
														eChkinput.click();
													}
													break;
												}
											}
										}
									
									clickOnElement("xpath","//*[@id='page-fields-config-modal']/div/div[1]/div[3]/button[1]");
									 Thread.sleep(5000);
									 ebtnCollapse.click();
									 Thread.sleep(5000);
								}
								
								break;
							}
						}
								
						break;
					}
				}
			}
	
		}
		
	
	
		
		// Configure confirmation fields to the page.
		
		
		
		public void addConfirmFields2Page(String pageName, String tabName, String[] selectedFields) throws InterruptedException, IOException{
			logInfo("inside addConfirmFields2Page() method.");
			
			 String after_headingName = prop.getLocatorForEnvironment(appUrl,"after_headingName");
			 String after_collapse = prop.getLocatorForEnvironment(appUrl,"after_collapse");
			 
			
			WebElement pageContainer = driver().findElement(By.xpath("//*[@id='pages_panel_group']"));
			List allPages = pageContainer.findElements(By.cssSelector("div.panel.panel-default.panel-parent"));
			int total_pages = allPages.size();
			
			System.out.println("total_pages =" +total_pages);
			
			String before_headingName = "//*[@id='pages_panel_group']/div[";
			//String after_headingName = "]/div[@class='panel-heading']/div[@class='panel-title']/div[@class='row']/div[@class='col-md-8']/a/span";
			
			String before_collapse = "//*[@id='pages_panel_group']/div[";
			//String after_collapse = "]/div[@class='panel-heading']/div[@class='panel-title']/div[@class='row']/div[@class='col-md-1 text-right']/a[2]/i";
			
			String before_configFields = "//*[@id='pages_panel_group']/div[";
			String after_configFields = "]/div[@class='panel-collapse panel-body collapse in']/form/div[@class='row']/div[2]/a";
			
			if(total_pages >0){
				for(int i=1; i<=total_pages; i++){
					WebElement ePageheading = driver().findElement(By.xpath(before_headingName+i+after_headingName));
					String actPageHeading = ePageheading.getText().trim();
					if(actPageHeading.equalsIgnoreCase(pageName)){
						System.out.println("i = " +i);
						System.out.println(pageName + " page selected");
						logInfo(pageName + " page title match found.");
						WebElement ebtnCollapse = driver().findElement(By.xpath(before_collapse+i+after_collapse));
						ebtnCollapse.click();
						logInfo("clicked on expand/collapse button of page -> " +pageName );
						
						WebElement eAddFields = driver().findElement(By.xpath(before_configFields+i+after_configFields));
						eAddFields.click();
						logInfo("clicked on Configure Fields link of page -> " +pageName );
						Thread.sleep(5000);
						
						// Add Fields pane
						
						WebElement eFieldsTab = driver().findElement(By.xpath("//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/ul"));
						List<WebElement> allTabs = eFieldsTab.findElements(By.tagName("li"));
						for(WebElement x : allTabs){
							String actTabName = x.getText().trim();
							if(actTabName.equalsIgnoreCase(tabName)){
								logInfo(tabName + " tab match found in page " +pageName );
								x.click();
								logInfo("clicked on tab " +tabName );
								
								switch(actTabName){
								case "Confirm" :
									WebElement eConfirmTabPanel = driver().findElement(By.xpath("//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/div[@class='tab-content']/div[4]/form/div[@class='row']/div"));
									List allConfirmCheckBoxes = eConfirmTabPanel.findElements(By.tagName("span"));
									
									int total_confirmcheckboxes = allConfirmCheckBoxes.size();
									
									int selected_fields = selectedFields.length;
									
									String before_label = "//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/div[@class='tab-content']/div[4]/form/div[@class='row']/div/span[";
									String after_label = "]/label";
									
									String before_input = "//*[@id='page-fields-config-modal']/div[@class='modal-dialog ']/div[@class='modal-content']/div[@class='modal-body']/div[@class='tab-content']/div[4]/form/div[@class='row']/div/span[";
									String after_input = "]/label/input";
									
									
									// unselect all the checkboxes in the required fields panel.
									
									for(int m=1;m<=total_confirmcheckboxes;m++){
										WebElement eChkinput = driver().findElement(By.xpath(before_input+m+after_input));
										eChkinput.click();
									}
									
									// select the required fields only in the list
									
									for(int k=1;k<=total_confirmcheckboxes;k++){
										for(int l=0; l<=selected_fields-1; l++){
												WebElement eActlabel = driver().findElement(By.xpath(before_label+k+after_label));
												String actlabel = eActlabel.getText().trim();
												WebElement eChkinput = driver().findElement(By.xpath(before_input+k+after_input));
												if(actlabel.equalsIgnoreCase(selectedFields[l])){
													System.out.println("actlabel =" +actlabel);
													if(!eChkinput.isSelected()){
														eChkinput.click();
													}
													break;
												}
											}
										}
									
									System.out.println("------------- 1");
									clickOnElement("xpath","//*[@id='page-fields-config-modal']/div/div[1]/div[3]/button[1]");
									System.out.println("------------- 2");
									Thread.sleep(10000);
									ebtnCollapse.click();
									System.out.println("------------- 3");
								}
								
								break;
							}
						}
								
						break;
					}
				}
			}
	
		}
		
		
		
		// Profile Configuration tab
		
		/*public void selectEnrollmentProfileConfig(String tabName) throws Exception, Exception{
			logInfo("inside selectEnrollmentProfileConfig() method.");
			
			clickOnElement("xpath", "//*[@class='simple_form edit_pyr_core_enrollment_profile']/div[4]/a");
			
			waitOnElement("cssSelector","div.modal-dialog > div.modal-content");
			WebElement eprofileConfigPanel = driver().findElement(By.cssSelector("div.modal-dialog > div.modal-content > div.modal-body > ul"));
			List<WebElement> allProfileConfigTabs = eprofileConfigPanel.findElements(By.cssSelector("li > a"));
			int all_profiletabs = allProfileConfigTabs.size();
			System.out.println("Total tabs =" +all_profiletabs);
			
			String before_tab = "div.modal-dialog > div.modal-content > div.modal-body > ul > li:nth-child(";
			String after_tab = ") > a";
			boolean isProfileConfigTabFound = false;
			
			for(int i=1;i<=all_profiletabs;i++){
				WebElement x = driver().findElement(By.cssSelector(before_tab+i+after_tab));
				String actName = x.getText().trim();
				System.out.println("tab - " +actName);
				if(actName.equalsIgnoreCase(tabName)){
					logInfo(tabName + " tab match found.");
					isProfileConfigTabFound = true;
					x.click();
					Thread.sleep(5000);
					break;
				}
			}
		}*/
		
		public void selectEnrollmentProfileConfig(String tabName) throws Exception, Exception{
			logInfo("inside selectEnrollmentProfileConfig() method.");
			
			waitOnElement("linkText", "Configure Profile");
			clickOnLink("linkText", "Configure Profile");
			
			switch(tabName){
				case "Backend" :
					clickOnLink("linkText","Backend");
					Thread.sleep(3000);
					break;
				case "Product Config" :
					clickOnLink("linkText","Product Config");
					Thread.sleep(3000);
					break;
				case "Select" :
					clickOnLink("linkText","Select");
					Thread.sleep(3000);
					break;
				case "Default" :
					clickOnLink("linkText","Default");
					Thread.sleep(3000);
					break;
			}
				
		}
		
		
		
		// select profile configuration payment options
		
		public void selectPaymentOptions(String[] paymentOptions) throws Exception{
			logInfo("inside selectPaymentOptions() method.");
			
			selectEnrollmentProfileConfig("Backend");
			
			WebElement optionsPane = driver().findElement(By.xpath("//div[@class='form-group check_boxes optional pyr_core_enrollment_profile_payment_options']"));
			List<WebElement> allInputs = optionsPane.findElements(By.cssSelector("span"));
			int all_inputs = allInputs.size();
			
			String before_labelName = "//div[@class='form-group check_boxes optional pyr_core_enrollment_profile_payment_options']/span[";
			String after_labelName = "]/label";
			
			String before_input = "//div[@class='form-group check_boxes optional pyr_core_enrollment_profile_payment_options']/span[";
			String after_input = "]/label/input";
			
			int selected_inputs = paymentOptions.length;
			
			for(int i=1;i<=all_inputs;i++){
				for(int j=0;j<=selected_inputs-1;j++){
					WebElement elabelName = driver().findElement(By.xpath(before_labelName+i+after_labelName));
					String actlabelName = elabelName.getText().trim();
					
					if(actlabelName.equalsIgnoreCase(paymentOptions[j])){
						WebElement einput = driver().findElement(By.xpath(before_input+i+after_input));
						if(!einput.isSelected()){
							einput.click();
							Thread.sleep(3000);
						}
						
						break;
					}
				}
			}
			
			// clickOnElement("cssSelector","div.modal-content > div.modal-footer > button.btn.btn-primary");
			WebElement eSave = driver().findElement(By.xpath("//*[@id='profile-config-modal']/div/div[1]/div[3]/button"));
			eSave.click();
			
			//WebElement eCloseDialog = driver().findElement(By.xpath("//*[@id='profile-config-modal']/div/div[1]/div[1]/button"));
			//eCloseDialog.click();
			
			 clickOnElement("xpath","//*[@id='profile-config-modal']/div/div[1]/div[1]/button");
		}
		
		
		// select profile configuration fields.
		
		public void selectProfileConfigFields(String[] configFields) throws Exception{
			logInfo("inside selectProfileConfigFields() method.");
			
			selectEnrollmentProfileConfig("Select");
			
			WebElement selectPanel = driver().findElement(By.xpath("//*[@id='profile_config_select_fields']/form/div[@class='col-md-12']/div[@class='row']/div[1]"));
			List allChecks = selectPanel.findElements(By.tagName("span"));
			
			int total_checkboxes = allChecks.size();
			System.out.println("Total checkboxes =" +total_checkboxes);
			
			String before_labelName = "//*[@id='profile_config_select_fields']/form/div[@class='col-md-12']/div[@class='row']/div[1]/span[";
			String after_labelName = "]/label";
			
			String before_input = "//*[@id='profile_config_select_fields']/form/div[@class='col-md-12']/div[@class='row']/div[1]/span[";
			String after_input = "]/label/input";
			
			int selected_checkboxes = configFields.length;
			System.out.println("Total selection checkboxes =" +selected_checkboxes);
			
			for(int i=1;i<=total_checkboxes;i++){
				for(int j=0;j<=selected_checkboxes-1;j++){
					WebElement elabelName = driver().findElement(By.xpath(before_labelName+i+after_labelName));
					String actlabelName = elabelName.getText().trim();
					WebElement elabelInput = driver().findElement(By.xpath(before_input+i+after_input));
					if(actlabelName.equalsIgnoreCase(configFields[j])){
							if(!elabelInput.isSelected()){
							elabelInput.click();
							Thread.sleep(2000);
						}
						break;
					}
			     }
			}
			
			scrollDown("xpath", "//*[@id='profile-config-modal']/div/div[1]/div[3]/button");		
			WebElement eSave = driver().findElement(By.xpath("//*[@id='profile-config-modal']/div/div[1]/div[3]/button"));
			eSave.click();
			
			Thread.sleep(5000);
			WebElement eCloseDialog = driver().findElement(By.xpath("//*[@id='profile-config-modal']/div/div[1]/div[1]/button"));
			eCloseDialog.click();
		}
		
		
		// set profile configuration default fields.
		
		public void setProfileConfigurationDefaultFields() throws Exception{
			logInfo("inside setProfileConfigurationDefaultFields() method.");
			
			selectEnrollmentProfileConfig("Default");
			waitOnElement("xpath","//*[@class='simple_form edit_pyr_core_enrollment_profile']/div[@class='col-md-12']");
			
			inputTextClear("cssSelector","#pyr_core_enrollment_profile_first_name");
			inputText("cssSelector","#pyr_core_enrollment_profile_first_name",idlife2_enrollFirstName_text);
			
			inputTextClear("cssSelector","#pyr_core_enrollment_profile_last_name");
			inputText("cssSelector","#pyr_core_enrollment_profile_last_name",idlife2_enrollLastName_text );
			
			inputTextClear("cssSelector","#pyr_core_enrollment_profile_company_name");
			inputText("cssSelector","#pyr_core_enrollment_profile_company_name", idlife2_enrollCompnayName_text);
						
			WebElement eSave = driver().findElement(By.xpath("//*[@id='profile-config-modal']/div/div[1]/div[3]/button"));
			eSave.click();
			
			Thread.sleep(5000);
			WebElement eCloseDialog = driver().findElement(By.xpath("//*[@id='profile-config-modal']/div/div[1]/div[1]/button"));
			eCloseDialog.click();
		
		}
		
	// *********************************************************************************************************************************************
		
		
		// Enrollment @ User methods	
		
		public void go2BusinessEnrollmentPage(){
			logInfo("inside go2BusinessEnrollmentPage() method.");
			driver().navigate().to(appUrl + "pyr_core/v2_enrollments/select_market");
		}
		
		
		public boolean verifyEnrollMarketIsPresent(String marketName){
			logInfo("inside verifyEnrollMarketIsPresent() method.");
			boolean isMarketFound = false;
			
			WebElement paneEnrollMarkets = driver().findElement(By.cssSelector("div.row.choose_market_container > div.col-md-12"));
			List<WebElement> allMarkets = paneEnrollMarkets.findElements(By.cssSelector("div.col-md-2.text-center > a > h3"));
			for(WebElement x : allMarkets){
				String actMarket = x.getText().trim();
				if(actMarket.equalsIgnoreCase(marketName)){
					isMarketFound = true;
					logInfo(marketName + " markets found in Business Enrollment page.");
					break;
				}
			}
			return isMarketFound;
		}
		
		
		public void selectBusinessEnrollMarket(String marketName){
			logInfo("inside selectBusinessEnrollMarket() method.");
			boolean isMarketFound = false;
			
			WebElement paneEnrollMarkets = driver().findElement(By.cssSelector("div.row.choose_market_container > div.col-md-12"));
			List<WebElement> allMarkets = paneEnrollMarkets.findElements(By.cssSelector("div.col-md-2.text-center > a > h3"));
			for(WebElement x : allMarkets){
				String actMarket = x.getText().trim();
				if(actMarket.equalsIgnoreCase(marketName)){
					isMarketFound = true;
					logInfo(marketName + " markets found in Business Enrollment page.");
					x.click();
					logInfo("clicked on enrollment market - " + marketName);
					break;
				}
			}
			
		}
		
	
		public boolean verifyBusinessProfilePresent(String profileName){
			logInfo("inside verifyBusinessProfilePresent() method.");
			boolean isProfileFound = false;
			
			WebElement paneEnrollProfile = driver().findElement(By.cssSelector("div.choose_profile > table.table.table-striped > tbody"));
			List<WebElement> allProfiles = paneEnrollProfile.findElements(By.cssSelector("tr > td:nth-child(1)"));
			for(WebElement x : allProfiles){
				String actProfile = x.getText().trim();
				if(actProfile.equalsIgnoreCase(profileName)){
					isProfileFound = true;
					logInfo(profileName + " profile found in Business Enrollment page.");
					
					break;
				}
			}
			
			return isProfileFound;
		}
		
		public void submitEnrollmentPersonalDetails() throws Exception{
			logInfo("inside submitPersonalDetails() method.");
			
			//inputTextClear("cssSelector","#pyr_core_v2_enrollment_birthday");
			//inputText("cssSelector","#pyr_core_v2_enrollment_birthday",enrollPDBirthday_text);
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_day_phone");
			inputText("cssSelector","#pyr_core_v2_enrollment_day_phone",enrollPDDayPhone_text);
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_email");
			inputText("cssSelector","#pyr_core_v2_enrollment_email",enrollPDEmail_text);
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_first_name");
			inputText("cssSelector","#pyr_core_v2_enrollment_first_name",enrollPDFirstName_text);
			
			selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_gender","byVisibleText",enrollPDGender_text);
						
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_last_name");
			inputText("cssSelector","#pyr_core_v2_enrollment_last_name",enrollPDLastName_text);
			
			clickOnElement("linkText","Continue");
			
		}
		
		
		public void submitCreditCardCDetails() throws Exception{
			logInfo("inside submitCreditCardCDetails() method.");
			
			waitOnElement("linkText","Add / Change Credit Card");
			clickOnElement("linkText","Add / Change Credit Card");
			waitOnElement("cssSelector","pyr_core_v2_enrollment_tmp_credit_card_card_name");
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_tmp_credit_card_card_name");
			inputText("cssSelector","#pyr_core_v2_enrollment_tmp_credit_card_card_name",enrollLDCardName_text);
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_tmp_credit_card_card_number");
			inputText("cssSelector","#pyr_core_v2_enrollment_tmp_credit_card_card_number",enrollLDCardNumber_text);
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_tmp_credit_card_card_cvv");
			inputText("cssSelector","#pyr_core_v2_enrollment_tmp_credit_card_card_cvv",enrollLDCardCvv_text);
			
			selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_tmp_credit_card_card_expire_month","byVisibleText",enrollLDCardExpiryMonth_text);
			selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_tmp_credit_card_card_expire_year","byVisibleText",enrollLDCardExpiryYear_text);
			
			submitCCAddressDetails();
			}
		
		public void submitCCAddressDetails() throws Exception{
			logInfo("inside submitCCAddressDetails() method.");
			
			clickOnLink("linkText","Add Address");
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_tmp_billing_address_address1");
			inputText("cssSelector","#pyr_core_v2_enrollment_tmp_billing_address_address1",enrollLDAddress1_text);
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_tmp_billing_address_city");
			inputText("cssSelector","#pyr_core_v2_enrollment_tmp_billing_address_city",enrollLDCity_text);
			
			selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_tmp_billing_address_state","byVisibleText",enrollLDState_text);
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_tmp_billing_address_postal_code");
			inputText("cssSelector","#pyr_core_v2_enrollment_tmp_billing_address_postal_code",enrollLDPostalCode_text);
			
			selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_tmp_billing_address_country","byVisibleText",enrollLDCountry_text);
			
			clickOnElement("cssSelector","section.card-billing  > div.row > div.col-md-12 > a.btn.btn-primary.save-credit-card");
			
		}
		
		public void submitLoginDetails() throws Exception{
			logInfo("inside submitLoginDetails() method.");
			
			System.out.println(" ******** User Name = " +enrollDPUserName_text);
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_username");
			inputText("cssSelector","#pyr_core_v2_enrollment_username",enrollDPUserName_text);
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_password");
			inputText("cssSelector","#pyr_core_v2_enrollment_password",enrollLDPasswd_text);
			
			//inputTextClear("cssSelector","#pyr_core_v2_enrollment_confirm_password");
			//inputText("cssSelector","#pyr_core_v2_enrollment_confirm_password",enrollLDConfirmPasswd_text);
			
			clickOnLink("linkText","Continue");
			
		}
		
		
		public void selectSubscriptionPlan(String subscriptionName) throws Exception, Exception{  // String subscriptionName
			logInfo("inside selectSubscriptionPlan() method.");
			
			waitOnElement("xpath","//section[@class='subscription-with-promotion review']/div[1]");
			WebElement subscriptionPanel = driver().findElement(By.xpath("//section[@class='subscription-with-promotion review']/div[1]"));
			List allRows = subscriptionPanel.findElements(By.className("row"));
			int all_rows = allRows.size();
			System.out.println("all_rows =" + all_rows);
			
			boolean isMatchFound = false;
			
			if(all_rows>0){
				for(int i=2;i<=all_rows+1;i++){
					String before_row = "//section[@class='subscription-with-promotion review']/div[1]/div[";
					String after_row = "]";
					WebElement erows = driver().findElement(By.xpath(before_row+i+after_row));
					List allCols = erows.findElements(By.cssSelector(".col-md-6.plan"));
					int all_cols = allCols.size();
					System.out.println("all_cols =" + all_cols);
					
					if(all_cols>0){
						for(int j=1;j<=all_cols;j++){
							WebElement eName = driver().findElement(By.xpath("//section[@class='subscription-with-promotion review']/div[1]/div["+i+"]/div["+j+"]/div[2]/h3"));
							String name = eName.getText().trim();
							System.out.println("name =" + name);
							if(name.equalsIgnoreCase(subscriptionName)){
								isMatchFound = true;
								logInfo(subscriptionName + " subscription match found in Login Details page.");
								WebElement eSelect = driver().findElement(By.xpath("//section[@class='subscription-with-promotion review']/div[1]/div["+i+"]/div["+j+"]/div[@class='col-md-9']/span[2]/div/a"));
								eSelect.click();
								break;
							}
							
						}
					}
					
				}
				
			}
			
		}
		 
		
		public void acceptTOC() throws Exception{
			logInfo("inside acceptTOC() method.");
			
			Thread.sleep(5000);
			waitOnElement("cssSelector","#pyr_core_v2_enrollment_toc_acceptance");
			WebElement echkToc = driver().findElement(By.cssSelector("#pyr_core_v2_enrollment_toc_acceptance"));
			if(!echkToc.isSelected()){
				echkToc.click();
				logInfo("selected toc.");
			}
			
			waitOnElement("cssSelector","a.btn.btn-primary.btn.btn-enrollment-continue");
			clickOnElement("cssSelector","a.btn.btn-primary.btn.btn-enrollment-continue");
			//clickOnElement("linkText","Continue");
			Thread.sleep(5000);
			
		}
		
		public void startEnrollment(String profileName){
			logInfo("inside startEnrollment() method.");
					
			WebElement paneEnrollProfile = driver().findElement(By.cssSelector("div.choose_profile > table.table.table-striped > tbody"));
			List<WebElement> allProfiles = paneEnrollProfile.findElements(By.cssSelector("tr > td:nth-child(1)"));
			int total_profiles = allProfiles.size();
			System.out.println("total_profiles =" +total_profiles);
			
			String before_beforeName = "table.table.table-striped > tbody > tr:nth-child(";
			String after_afterName = ") > td:nth-child(1)";
			
			String before_startEnroll = "table > tbody > tr:nth-child(";
			String after_startEnroll = ") > td:nth-child(3)";
			
			if(total_profiles>1){
			for(int i=2;i<=total_profiles+1;i++){
				WebElement xProfielName = driver().findElement(By.cssSelector(before_beforeName+i+after_afterName));
				String actProfile = xProfielName.getText().trim();
				System.out.println("actProfile =" +actProfile);
				
				if(actProfile.equalsIgnoreCase(profileName)){
					logInfo("clicking on the profile - " +profileName);
					WebElement xStartEnroll = driver().findElement(By.cssSelector(before_startEnroll+i+after_startEnroll));
					xStartEnroll.click();
					logInfo("clicked on the profile - " +profileName);
					break;
				}
			}
		  }	
		}
		
	
		
		// Idlife2 specific
		
		
		public void expandOrCollapseEnrollPanel(String panelName) throws Exception, Exception{//String panelName){
			logInfo("inside expandOrCollapseEnrollPanel() method.");
			
			waitOnElement("xpath","//div[@id='accordion']");
			WebElement enrollPanel = driver().findElement(By.xpath("//div[@id='accordion']"));
			List allPanels = enrollPanel.findElements(By.cssSelector(".panel.panel-default.incomplete"));
			System.out.println("Total panes = " + allPanels.size() );
			
			String before_panelName = "//div[@id='accordion']/div[";
			String after_panelName = "]/div/h2/a[1]";
			
			if(allPanels.size() > 0){
				for(int i=1;i<=allPanels.size();i++){
					WebElement panel = driver().findElement(By.xpath(before_panelName+i+after_panelName));
					System.out.println("Heading = " + panel.getText().trim());
					String actPanelHeader = panel.getText().trim();
					if(actPanelHeader.equalsIgnoreCase(panelName)){
						panel.click();
						break;
					}
				}
			}
			
		}
		
		
		public void selectProductPack() throws Exception, Exception{  // String prodName
			logInfo("inside selectProductPack() method.");
			
			// expandOrCollapseEnrollPanel("Choose a Product Pack");
			
			WebElement paneProdPack = driver().findElement(By.xpath("//div[@class='starter-kits-table-container']"));
			List<WebElement> allProducts = paneProdPack.findElements(By.cssSelector("div.starter-kit"));
			int total_prods = allProducts.size();
			
			String before_prodName = "//div[@class='starter-kits-table-container']/div[";
			String after_prodName = "]/div[@class='row']/div[2]/h3";
			
			String before_select = "//div[@class='starter-kits-table-container']/div[";
			String after_select = "]/div[@class='row']/div[3]/div/a/label";
			
			if(total_prods > 0){
				for(int i=1;i<=total_prods;i++){
					WebElement prod = driver().findElement(By.xpath(before_prodName+i+after_prodName));
					String actProdName = prod.getText().trim();
					System.out.println("Prod = " + actProdName);
					
					WebElement select = driver().findElement(By.xpath(before_select+i+after_select));
					select.click();
					/*if(actProdName.equalsIgnoreCase(prodName)){
						WebElement select = driver.findElement(By.xpath(before_select+i+after_select));
						select.click();
						//break;
					}*/
				}
			}
			
		//	clickOnElement("cssSelector","div#accordion > div:nth-child(1) > div:nth-child(2) >   form > div.row > div.col-md-12.clearfix > div.continue > a.btn.btn-primary.btn-enrollment-continue");
			
			Thread.sleep(5000);
			// expandOrCollapseEnrollPanel("Choose a Product Pack");
		}
		
		
		
		public void selectAddationalProducts() throws Exception, Exception{
			logInfo("inside selectAddationalProducts() method.");
			
			// expandOrCollapseEnrollPanel("Select Additional Products");
			
			clickOnElement("cssSelector","div#accordion > div:nth-child(2) > div:nth-child(2) >   form > div.row > div.col-md-12.clearfix > div.continue > a.btn.btn-primary.btn-enrollment-continue");
			
			Thread.sleep(5000);
		//	expandOrCollapseEnrollPanel("Select Additional Products");
		}
		
		
		public void agreeTermsAndConditions() throws Exception, Exception{
			logInfo("inside agreeTermsAndConditions() method.");
			
		//	expandOrCollapseEnrollPanel("Agree To Terms and Conditions");
			
			clickOnElement("cssSelector","div#accordion > div:nth-child(3) > div:nth-child(2) >   form > section >div:nth-child(1) > div > div > div:nth-child(2) > div > div > label > input");
			clickOnElement("cssSelector","div#accordion > div:nth-child(3) > div:nth-child(2) >   form > section >div:nth-child(2)  > div > div:nth-child(2) > div > div > label > input");
			clickOnElement("cssSelector", "div#accordion > div:nth-child(3) > div:nth-child(2) >   form > section > div:nth-child(4) > div > div:nth-child(2) > div > div > label > input");
			clickOnElement("cssSelector", "div#accordion > div:nth-child(3) > div:nth-child(2) >   form > section > div:nth-child(6) > div > div:nth-child(2) > div > div > label > input");
			clickOnElement("cssSelector", "div#accordion > div:nth-child(3) > div:nth-child(2) >   form > section > div:nth-child(8) > div > div:nth-child(2) > div > div > label > input");
			
			clickOnElement("cssSelector","div#accordion > div:nth-child(3) > div:nth-child(2) >   form > div.row > div.col-md-12.clearfix > div.continue > a.btn.btn-primary.btn-enrollment-continue");
			
			Thread.sleep(5000);
		}
		
		
		public void idlifeEnrollPersonalInformation() throws Exception, Exception{
			logInfo("inside idlifeEnrollPersonalInformation() method.");
			
			inputText("cssSelector",eFirst,"abcdef");
			inputText("cssSelector",eLast,"pqrst");
			inputText("cssSelector",eSSN, "83789"+TestBase.generateRandomNumberInRange(1000,9999));   
			inputText("cssSelector",eHome,"1234567");
			inputText("cssSelector",eEmail,"vibe.icentris@gmail.com");
			selectFromDropdown("cssSelector",eGender,"byVisibleText","Male");
			inputText("cssSelector",eBirthDay,"12/12/1991");
			
			clickOnElement("cssSelector","div#accordion > div:nth-child(4) > div:nth-child(2) >   form > div.row > div.col-md-12.clearfix > div.continue > a.btn.btn-primary.btn-enrollment-continue");
			
			Thread.sleep(5000);
		}
			
		
		public void idlifeEnrollLoginInformation() throws Exception, Exception{
			logInfo("inside idlifeEnrollLoginInformation() method.");
			
			inputText("cssSelector",eUserName,"idlife"+TestBase.random());
			inputText("cssSelector",ePswd,"password1");
			inputText("cssSelector",eConPswd,"password1");
			
			clickOnElement("cssSelector","div#accordion > div:nth-child(5) > div:nth-child(2) >   form > div.row > div.col-md-12.clearfix > div.continue > a.btn.btn-primary.btn-enrollment-continue");
			
			Thread.sleep(5000);
			
		}
		
		
		public void idlifeEnrollShippingInformation() throws Exception, Exception{
			logInfo("inside idlifeEnrollShippingInformation() method.");
			
			// enter credit card details here. 
			
			clickOnElement("cssSelector","section#payment_option_credit  >  a.btn.btn-default.btn-sm");
			
			waitOnElement("cssSelector",eCardHolder);
			inputText("cssSelector",eCardHolder,"idlife user");
			inputText("cssSelector",eCardNo, "5555555555554444");
			inputText("cssSelector",eCardCvv,"123");
			selectFromDropdown("cssSelector",eExpireMon,"byVisibleText","May");
			selectFromDropdown("cssSelector",ccExpiryYear,"byVisibleText","2020");
			
			// enter address details here. 
			
			inputText("cssSelector",ccAddress,"Sandiego");
			inputText("cssSelector",ccCity, "Alaska");
			selectFromDropdown("cssSelector",ccState,"byVisibleText","Alaska");
			inputText("cssSelector",ccZipCode,"435678");
			selectFromDropdown("cssSelector","#pyr_core_v2_enrollment_tmp_billing_address_country","byVisibleText","United States");
			
			clickOnElement("cssSelector","a.btn.btn-primary.save-credit-card");
			Thread.sleep(5000);
			
		}
		
		
		public void idlifeEnrollInitialOrderShippingInfo() throws Exception, Exception{
			logInfo("inside idlifeEnrollInitialOrderShippingInfo() method.");
			
			// enter credit card details here. 
			
			clickOnElement("cssSelector","div.enrollment_shipping_options > a.btn.btn-default.btn-sm");
			
			waitOnElement("cssSelector", "a.btn.btn-xs.btn-primary.select-address");
			clickOnElement("cssSelector", "a.btn.btn-xs.btn-primary.select-address");
			Thread.sleep(10000);
			
			scrollDown("cssSelector","div#accordion > div:nth-child(6) > div:nth-child(2) >   form > div.row > div.col-md-12.clearfix > div.continue > a.btn.btn-primary.btn-enrollment-continue");
			clickOnElement("cssSelector","div#accordion > div:nth-child(6) > div:nth-child(2) >   form > div.row > div.col-md-12.clearfix > div.continue > a.btn.btn-primary.btn-enrollment-continue");
			Thread.sleep(5000);
		}
		
		
		public void idlifeSubmitOrder() throws Exception, Exception{
			logInfo("inside idlifeSubmitOrder() method.");
			
			selectRadioOrCheckbox("id","pyr_core_v2_enrollment_shipping_method_1");
			
			clickOnElement("cssSelector","div#accordion > div:nth-child(7) > div:nth-child(2) >   form > div.row > div.col-md-12.clearfix > div.continue > a.btn.btn-primary.btn-enrollment-continue");
			Thread.sleep(10000);
		}
		
		
		public void verifyEnrollmentIsSuccessful(){
			logInfo("inside verifyEnrollmentIsSuccessful() method.");
			
			WebElement msg = driver().findElement(By.cssSelector("#main-content"));
			String actMsg =  msg.getText().trim();
			logInfo("Enrollment msg = " +actMsg);
			
			if(!actMsg.contains("Your enrollment is successful")){
				Assert.assertFalse(true, "Enrollment is not successful.");
			}
	}
		
		public void houseHoldCustomer() throws Exception{	
			logInfo("inside houseHoldCustomer() method.");
		
			clickOnLink("linkText", "Business");
			clickOnLink("linkText", "Add Household Customer");
			waitOnElement("cssSelector",enrollFirstName);
			inputText("cssSelector",enrollFirstName,txtEnrollFirstName);
			inputText("cssSelector",enrollLastName,txtEnrollLastName);
			inputText("cssSelector",enrollEmail,email);
			//selectFromDropdown("cssSelector",enollBirthday,"byVisibleText",txtEnrollBirthday);
			inputText("cssSelector",enollBirthday,txtEnrollBirthday);
			inputText("cssSelector",enrollPhone,txtEnrollPhone);
			selectFromDropdown("cssSelector",enrollGender,"byVisibleText","Male");
			inputTextClear("cssSelector",enrollUserName);
			inputText("cssSelector",enrollUserName,txtEnrollUserName);
			inputTextClear("cssSelector",enrollPassword);
			inputText("cssSelector",enrollPassword,txtEnrollPassword);
			inputText("cssSelector",enrollConfirmPassword,txtEnrollPassword);
			waitOnElement("cssSelector",inputTOC);
			selectRadioOrCheckbox("cssSelector",inputTOC);
			clickOnElement("linkText","Continue");
			waitOnElement("linkText", "Continue");
			clickOnElement("linkText","Continue");
			waitOnElement("cssSelector", "#main-content");
			String userid=	driver().findElement(By.cssSelector("#main-content")).getText();
			System.out.println(userid);
			
			
		}
	

		
		public void monatEnrollmentStep1() throws Exception{
			logInfo("inside monatEnrollmentStep  method");
			inputTextClear("cssSelector", monatuserName);
			inputText("cssSelector",monatuserName, monatUserNameEnroll );
			selectProductPack();
			clickOnElement("xpath", monatEnrollmentContinue);

		}

		public void monatEnrollmentStep2() throws Exception, Exception{
			logInfo("inside monatEnrollmentStep2() method.");
			
			inputText("cssSelector",eFirst,"abcdef");
			inputText("cssSelector",eLast,"pqrst");
			inputText("cssSelector",enrollmentCompName,"VIBE");
			inputText("cssSelector",eEmail,"vibe.icentris@gmail.com");
			inputText("cssSelector",ePswd,"password1");
			inputText("cssSelector",eConPswd,"password1");
			inputText("cssSelector",eHome,"1234567");
			inputText("cssSelector",eBirthDay,"12/12/1991");
			selectFromDropdown("cssSelector",eGender,"byVisibleText","Male");
			selectFromDropdown("cssSelector",enrollmentlanguagePreference,"byVisibleText","English");
			inputText("cssSelector",eSSN, "83789"+TestBase.generateRandomNumberInRange(1000,9999));   
			enrollmentShippingAddress();
			inputText("cssSelector",eSt1,"Test");
			inputText("cssSelector",ePost,"84087");
			inputText("cssSelector",eCity,"woods cross");
			selectFromDropdown("cssSelector",eState,"byVisibleText","Utah");
			selectFromDropdown("cssSelector",eCountry,"byVisibleText","United States");
			clickOnElement("cssSelector",  enrollmentShippingAddressSave);
			
			driver().findElement(By.xpath(monatEnrollmentContinue)).click();
			//clickOnElement("xpath", monatEnrollmentContinue);
			
			
			
		}
		
		public void enrollmentShippingAddress() throws Exception{
			logInfo("inside enrollmentShippingAddress method");
			System.out.println("inside enrollmentShippingAddress method");
			clickOnElement("linkText","Add / Change Address");
			waitOnElement("cssSelector",enrollmentShippingAddressSave);
			inputText("cssSelector",enrollmentShippingAddress1,"Test");
			inputText("cssSelector",enrollmentShippingzipcode,"84087");
			inputText("cssSelector",enrollmentShippingCity,"woods cross");
			selectFromDropdown("cssSelector",enrollmentShippingState,"byVisibleText","Utah");
			selectFromDropdown("cssSelector",enrollmentShippingCountry,"byVisibleText","United States");
			clickOnElement("cssSelector",  enrollmentShippingAddressSave);
			
			
		}
		
		public void monatEnrollmentStep3() throws Exception, Exception{
			logInfo("inside monatEnrollmentStep3 method");
			
			//waitOnElement("xpath", monatEnrollmentContinue);
			//clickOnElement("xpath", monatEnrollmentContinue);
			Thread.sleep(6000);
		//	implicityWaits(15);
		//	waitOnElement("xpath", monatEnrollmentContinueStep3);
		
			driver().findElement(By.xpath(monatEnrollmentContinueStep3)).click();
		
			
		}
		
		public void acceptTAC() throws Exception{
			logInfo("inside acceptTAC() method.");
			
			Thread.sleep(5000);
			waitOnElement("cssSelector","#pyr_core_v2_enrollment_tac2");
			WebElement echkToc = driver().findElement(By.cssSelector("#pyr_core_v2_enrollment_tac2"));
			if(!echkToc.isSelected()){
				echkToc.click();
				logInfo("selected tAc.");
			}
			
			waitOnElement("cssSelector","a.btn.btn-primary.btn.btn-enrollment-continue");
			clickOnElement("cssSelector","a.btn.btn-primary.btn.btn-enrollment-continue");
			//clickOnElement("linkText","Continue");
			Thread.sleep(5000);
			
		}
		
		
		public void monatEnrollmentStep4() throws Exception, Exception{
			logInfo("inside monatEnrollmentStep4 method");
			waitOnElement("linkText","Click to Enter Payment");
			clickOnElement("linkText","Click to Enter Payment");
			
			inputTextClear("cssSelector","#pyr_core_v2_enrollment_tmp_credit_card_card_name");
			inputText("cssSelector","#pyr_core_v2_enrollment_tmp_credit_card_card_name",enrollLDCardName_text);
			
			inputTextClear("cssSelector",enrollmentCreditcardNumber);
			inputText("cssSelector",enrollmentCreditcardNumber,enrollLDCardNumber_text);
			
			inputTextClear("cssSelector",enrollmentCreditcardcvv);
			inputText("cssSelector",enrollmentCreditcardcvv,enrollLDCardCvv_text);
			
			selectFromDropdown("cssSelector",enrollmentCreditcardMonth,"byVisibleText",enrollLDCardExpiryMonth_text);
			selectFromDropdown("cssSelector",enrollmentCreditcardYear,"byVisibleText",enrollLDCardExpiryYear_text);
			submitCCAddressDetails();
			clickOnElement("cssSelector","section.card-billing  > div.row > div.col-md-12 > a.btn.btn-primary.save-credit-card");
			selectRadioOrCheckbox("id","pyr_core_v2_enrollment_shipping_method_1");
			acceptTOC();
			acceptTAC();
		}
		

		public void monatSubmitOrder() throws Exception, Exception{
			logInfo("inside monatSubmitOrder() method.");
			
			selectRadioOrCheckbox("id","pyr_core_v2_enrollment_shipping_method_1");
			driver().findElement(By.xpath(monatEnrollmentContinue)).click();
			//clickOnElement("cssSelector","div#accordion > div:nth-child(7) > div:nth-child(2) >   form > div.row > div.col-md-12.clearfix > div.continue > a.btn.btn-primary.btn-enrollment-continue");
			Thread.sleep(10000);
		}
		
		
}
