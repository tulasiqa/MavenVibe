package vibe.inbox.tests;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.webdriven.commands.DoubleClick;

import common.TestBase;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.tasks.tests.TaskMethods;
import vibe.users.tests.UsersMethods;

public class LpgnInboxMethods extends TestBase{
	
	// BusinessContactsMethods bm = new BusinessContactsMethods();
	
	// Login to gmail account with the specified user and password.
		
	public void loginGmail(String uname, String passwd) throws Exception{
			logInfo("inside loginGmail() method...");
			driver().navigate().to("https://accounts.google.com/ServiceLogin");	
			
			// waitOnElement("cssSelector",inputGmail);
			Thread.sleep(5000);
			inputText("cssSelector",inputGmail, uname);
			clickOnButton("cssSelector", btnGmailNext);
			/*Thread.sleep(3000);
			WebElement staysigned = driver().findElement(By.xpath(chkStaySignedIn));
			String isChecked = staysigned.getAttribute("checked").trim();
			if(isChecked.equalsIgnoreCase("checked")){
				staysigned.click();
			}*/
			waitOnElement("cssSelector",inputGmailPasswd);
			inputText("cssSelector",inputGmailPasswd, passwd);
			clickOnButton("cssSelector", btnSignIn);
			
			driver().navigate().to("https://mail.google.com/mail/#inbox");
			
			WebDriverWait wait = new WebDriverWait(driver(),120);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(drpdnGmailUserProfile)));
			
		/*	WebElement profile = driver().findElement(By.xpath(drpdnGmailUserProfile));
			if(profile.isDisplayed()){
				logInfo(profile.getText().trim() + " found..");
				Assert.assertEquals(profile.getText().trim(), gmailId_text, "Gmail login sucessfull.");
			}else{
				logInfo(profile.getText().trim() + " not found..");
				Assert.assertEquals(profile.getText().trim(), gmailId_text, "Gmail login unsucessfull.");
			} */
		}
			
	
	
	
		// Verify if emails is received to gmail from external sources.
					
		public boolean verifyInboxGmail(String subject){
			logInfo("inside verifyInboxGmails() method ...");
			WebElement gmailBody = driver().findElement(By.xpath(tblGmailBody));
			List allRows = gmailBody.findElements(By.tagName("tr"));
			int count = allRows.size();
						
			String before = "//table/tbody/tr[";
			String after = "]/td[6]/div/div/div/span[1]";
						
			String beforeChk = "//table/tbody/tr[";
			String afterChk = "]/td[2]/div[@role='checkbox']";
						
			boolean isMatchFound = false;
			for(int i=1;i<=count;i++){
				WebElement x = driver().findElement(By.xpath(before+i+after));
				String gmail_summary = x.getText().trim();
				System.out.println("Gmail subjects = " +gmail_summary);
				if(gmail_summary.equalsIgnoreCase(subject.trim())){
					isMatchFound=true;
					logInfo(subject + " email match found @row " +i);
					Assert.assertTrue(isMatchFound, subject + " email match found @row " +i);
				}
			}
						
			if(isMatchFound==false){
				logInfo(subject + " no email match found in Gmail.");
				// Assert.assertTrue(isMatchFound, subject + " no email match found");
			}
			return isMatchFound;
		}
					


	    // Compose and send an email from gmail.
				
		public void composeGmail(String to_recipients, String subject, String body) throws Exception{
			logInfo("inside composeGmail() method.");
			
			Thread.sleep(5000);
			waitOnElement("cssSelector",btnComposeGmail);
			clickOnButton("cssSelector",btnComposeGmail);
				
			WebElement eReceipient = driver().findElement(By.xpath(eReceipients));
			if(eReceipient.isDisplayed()){
				eReceipient.click();
				implicityWaits(5);
				logInfo("clicked gmail's Receipient element ...");
			}
					
			inputText("xpath",inputComposeTo,to_recipients);
					
			WebElement eSubject = driver().findElement(By.xpath(eSubjects));
			if(eSubject.isDisplayed()){
				eSubject.click();
			}
					
			inputText("xpath",inputComposeSubject, subject);
					
			WebElement eDesc = driver().findElement(By.xpath(inputComposeDesc));
			eDesc.click();
			inputText("xpath",inputComposeDesc,body);
				
			clickOnButton("xpath",btnComposeSend);
			Thread.sleep(5000);
					
		}
					
	// Sign out from gmail account.
					
	public void signoutGmail() throws Exception{
		logInfo("inside signoutGmail() method ...");
		WebDriverWait wait = new WebDriverWait(driver(),180);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(drpdnGmailUserProfile)));
					
		WebElement profile = driver().findElement(By.xpath(drpdnGmailUserProfile));
		if(profile.isDisplayed()){
			logInfo(profile.getText().trim() + " found..");
			profile.click();
			//	clickOnButton("cssSelector",btnGmailSignout);
			clickOnLink("linkText","Sign out");
						
			WebDriverWait signInWait = new WebDriverWait(driver(),180);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(btnSignIn)));
						
			WebElement signIn = driver().findElement(By.cssSelector(btnSignIn));
			if(signIn.isDisplayed()){
				logInfo("logged out from gmail sucessfully.");
				verifyLinkPresent("Sign in with a different account");
				clickOnLink("linkText","Sign in with a different account");
				verifyLinkPresent("Remove");
				clickOnLink("linkText","Remove");
				logInfo("clicked Remove link.");
				WebElement accountList = driver().findElement(By.xpath(gmailAccountList));
				List allAccounts = accountList.findElements(By.tagName("li"));
				int glist_count = allAccounts.size();
							
		//		String before_name = "//*[@id='account-list']/li[";
		//		String after_name = "]/button[@type='submit']/span[@class='account-name']";
				String before_name = "//*[@id='gaia_loginform']/ol/li[";
				String after_name = "]/button[1]/span[1]";
		
				if(glist_count>0){
					for(int i=2;i<=glist_count;i++){
						WebElement accName = driver().findElement(By.xpath(before_name+i+after_name));
						String name = accName.getText().trim();
						accName.click();
					}
				 }
					
					verifyLinkPresent("Done");
					clickOnLink("linkText","Done");
					Thread.sleep(5000);
				}else{
					logInfo("logged out from gmail is unsucessfull.");
				}
			}
		}

		
		// Login to vibe using the specified user and password.
			
		public void loginVibe(String userName, String passwd) throws Exception{
			logInfo("inside loginVibe() method..");
			System.out.println("inside loginVibe() method..");
		//	driver().manage().deleteAllCookies();
		//	driver().navigate().to("");
			driver().navigate().to(appUrl + "users/sign_in");
		/*	
			WebElement UserName = driver().findElement(By.xpath(inputName));
			if(!UserName.isDisplayed()){
				 clickOnLink("xpath",lnkProfileDrpdn);
				 clickOnLink("xpath",lnkLogout);
				 Thread.sleep(5000);
			}
		*/	
			waitOnElement("xpath",inputName);
		//	verifyElementPresent("xpath",inputName);
			inputText("xpath",inputName,userName);
			verifyElementPresent("xpath",inputPwd);
			inputText("xpath",inputPwd,passwd);
			clickOnButton("xpath", btnLogin);
			
			WebDriverWait wait = new WebDriverWait(driver(),120);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(lnkProfileDrpdn)));
			
			WebElement profile = driver().findElement(By.xpath(lnkProfileDrpdn));
			logInfo("profile = " +profile.getText().trim());
			
			if(profile.isDisplayed()){
				logInfo(profile.getText().trim() + " found..");
			//	Assert.assertEquals(profile.getText().trim(), userName, " vibe login sucessfull.");
			}else{
				logInfo(profile.getText().trim() + " not found..");
			//	Assert.assertEquals(profile.getText().trim(), userName, " vibe login unsucessfull.");
			}
		}
					

	   // Navigate to vibe communications -> inbox page.	
		
		public void go2Inbox() throws Exception{
			logInfo("inside go2Inbox() method..");
			driver().navigate().to(appUrl + "crm/messages");
			//	clickOnLink("linkText","Communications");
			//	clickOnLink("linkText","Inbox");
			
			WebDriverWait wait = new WebDriverWait(driver(),120);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.panel-title")));  // "div.col-md-10.panel-content"
			
			WebElement pane = driver().findElement(By.cssSelector("div.panel-title"));
			if(pane.isDisplayed()){
				logInfo(pane.getText().trim() + " found..");
			}else{
				logInfo(pane.getText().trim() + " not found..");
			}
		}
		
		
	// Verify if the specified email is present in the inbox panel and select the matching mail.
		
		public boolean verifyVibeInboxMail(String subject){
			logInfo("inside verifyVibeInboxMail() method..");
			boolean isEmailVerified = false;
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size();
			
			String before = "//*[@id='bulk-form']/table/tbody/tr[";
			String after = "]/td[5]/span[1]";	
			
			String beforeChk = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterChk = "]/td[1]/input";
			
			int matchCnt=0;
			
			for(int i=1;i<=count-1;i++){
				WebElement x = driver().findElement(By.xpath(before+i+after));
				String act_subject = x.getText().trim();
				if (act_subject.equalsIgnoreCase(subject)) {
					isEmailVerified = true;
					WebElement chk = driver().findElement(By.xpath(beforeChk+i+afterChk));
					chk.click();
					x.click();
					Reporter.log(subject + " match found.");
					logInfo(subject + " match found.");
					Assert.assertTrue(act_subject.equalsIgnoreCase(subject), "subject \'" + subject + "\' email match found in inbox.");
					matchCnt++;
					break;
				} 
			}
			
			if(matchCnt==0){
				logInfo(subject + " match not found.");
				Reporter.log(subject + " match not found.");
				Assert.assertTrue(false, "subject \'" + subject + "\' email match could not be found in inbox.");
			}
			return isEmailVerified;
		}
		

	// Verify the count of unread emails displayed in QuickLinks pane in the home page.
		
		public int verifyInboxQuickLinks() throws Exception{  
			logInfo("inside verifyInboxQuickLinks() method..");
			driver().navigate().to(appUrl);
			waitOnElement("linkText","Home");
	//		verifyLinkPresent("Home");
			clickOnLink("linkText","Home");
			WebElement inbox = driver().findElement(By.xpath(divInboxCount));
			String inboxCount = inbox.getText().trim();
			int count = Integer.parseInt(inboxCount);
			logInfo("count displayed in Quick Links = " +count);
			return count;
		}	
		

	// Set emails per page in inbox.

		public void setMailsPerPage(String MailsPerPage) throws Exception{
			logInfo("inside setMailsPerPage() method..");
			
			// waitOnElement("cssSelector",btnToggleMailsperPage);
			WebElement ebtnToggleMailsperPage = driver().findElement(By.cssSelector(btnToggleMailsperPage));
			ebtnToggleMailsperPage.click();
			waitOnElement("linkText",MailsPerPage);
			clickOnLink("linkText",MailsPerPage);
			Thread.sleep(3000);
		}
					
					
	// Retrieve the count of all emails by filter type - Read/Unread/All/Starred/Unstarred/Important/Unimportant.
					
		public int selectVibeMails(String filterName) throws Exception {
			logInfo("inside selectVibeMails() method..");
			
			//setMailsPerPage("100 emails per page");
			go2Inbox();
						
			verifyElementPresent("xpath",btnFilterInboxEmail);
			clickOnButton("xpath",btnFilterInboxEmail);
			
			// clickOnLink("linkText",filterName);
			switch(filterName){
			case "All":
				clickOnElement("xpath","//*[@id='bulk-controls-container']/div[1]/ul/li[1]/a");
				Thread.sleep(5000);
			case "None" :
				clickOnElement("xpath","//*[@id='bulk-controls-container']/div[1]/ul/li[2]/a");
				Thread.sleep(5000);
			case "Read" :
				clickOnElement("xpath","//*[@id='bulk-controls-container']/div[1]/ul/li[3]/a");
				Thread.sleep(5000);
			case "Unread" :
				clickOnElement("xpath","//*[@id='bulk-controls-container']/div[1]/ul/li[4]/a");
				Thread.sleep(5000);
			case "Starred" :
				clickOnElement("xpath","//*[@id='bulk-controls-container']/div[1]/ul/li[5]/a");
				Thread.sleep(5000);
			case "Unstarred" :
				clickOnElement("xpath","//*[@id='bulk-controls-container']/div[1]/ul/li[6]/a");
				Thread.sleep(5000);
			case "Important" :
				clickOnElement("xpath","//*[@id='bulk-controls-container']/div[1]/ul/li[7]/a");
			case "Unimportant" :
				clickOnElement("xpath","//*[@id='bulk-controls-container']/div[1]/ul/li[8]/a");
				Thread.sleep(5000);
			}
			
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size()-1;
			System.out.println("Total mails =" +count);
				
			String beforechk = "//*[@id='bulk-form']/table/tbody/tr["; 
			String afterchk = "]/td[1]/input";
			int chkCount=0;
			for(int i=1;i<=count-1;i++){
				WebElement chkBox = driver().findElement(By.xpath(beforechk+i+afterchk));
				if(chkBox.isSelected()){
					chkCount++;
				}
			}
						
			logInfo("Total selected emails in Inbox = " +chkCount);
			return chkCount;
		}
						
					
					
		public boolean selectVibeMailsWithSubject(String subject) throws Exception {
			logInfo("inside selectVibeMailsWithSubject() method..");
			
			// setMailsPerPage("100 emails per page");
			go2Inbox();
			setMailsPerPage("100 emails per page");
			
			waitOnElement("xpath",tblInboxBody);
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size();
						
			String before = "//*[@id='bulk-form']/table/tbody/tr[";
			String after = "]/td[5]/span[1]";	
						
			String beforeChk = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterChk = "]/td[1]/input";
			boolean isMatchFound = false;
						
			for(int i=1;i<=count-1;i++){
				WebElement x = driver().findElement(By.xpath(before+i+after));
				String act_subject = x.getText().trim();
				if (act_subject.equalsIgnoreCase(subject)) {
					isMatchFound = true;
					WebElement chk = driver().findElement(By.xpath(beforeChk+i+afterChk));
					chk.click();
					logInfo("email with subject \'" + subject + "\' match found @" + i);
					// Assert.assertTrue(act_subject.equalsIgnoreCase(subject), "email with subject \'" + subject + "\' match found @" + i);
				} 
			}
						
			if(isMatchFound==false){
				logInfo("No emails found with the specified subject : " + subject);
				// Assert.assertTrue(isMatchFound, "No emails found with the specified subject : " + subject);  // can comment later
			}
			return isMatchFound;
		}	
					
					
		public void markEmailStarred(String subject) throws Exception{
			logInfo("inside markEmailStarred() method..");
			go2Inbox();
						
			setMailsPerPage("100 emails per page");
			Thread.sleep(5000);
						
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size();
			boolean isEmailFound = false;
						
			String beforeSubj = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterSubj = "]/td[5]/span[1]";
								
			String beforeStarred = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterStarrted  = "]/td[3]/a/i";
						
			logInfo("Number of emails = " +count);
						
			for(int i=1;i<=count-1;i++){
				WebElement x = driver().findElement(By.xpath(beforeSubj+i+afterSubj));
				String act_subject = x.getText().trim();
				System.out.println("Actual Subject = " +act_subject );
				if (act_subject.equalsIgnoreCase(subject.trim())) {
					logInfo(act_subject + " email match found.");
					isEmailFound = true;
		//			implicityWaits(10);
					WebElement eleStarred = driver().findElement(By.xpath(beforeStarred+i+afterStarrted));
					eleStarred.click();
					break;
					}
			   }

			 if(isEmailFound==false) {
				 logInfo("\'" + subject + "\'  email match not found.");
				 Assert.assertTrue(false, "\'" + subject + "\'  email match not found.");
				}
		}
				     	
					
		public void verifyEmailStarred(String subject) throws InterruptedException, Exception{
			logInfo("inside verifyEmailStarred() method..");
						
			go2Inbox();
			clickOnLink("xpath", lnkStarred);
			Thread.sleep(5000);
						
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size()-1;
			boolean isEmailStarred = false;
			String beforeSubj = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterSubj = "]/td[5]/span[1]";
								
			logInfo("Number of emails = " +count);
						
			for(int i=1;i<=count-1;i++){
				WebElement x = driver().findElement(By.xpath(beforeSubj+i+afterSubj));
				String act_subject = x.getText().trim();
				if (act_subject.equalsIgnoreCase(subject.trim())) {
					logInfo(subject + " email match found and marked as starred.");
					isEmailStarred = true;
					break;
				} 
			} 
			
			if(isEmailStarred==false){
				logInfo(subject + " no emails marked as starred.");
				Assert.assertTrue(isEmailStarred, subject + " email could not be marked as starred.");
			}
		}


		public void markEmailsImportant(String subject) throws Exception{
			logInfo("inside markEmailsImportant() method..");
			go2Inbox();
		//	setMailsPerPage("100 emails per page");
						
			Thread.sleep(5000);
						
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size();
			boolean isEmailFound = false;			
			String beforeSubj = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterSubj = "]/td[5]/span[1]";
						                    
			String beforeImp = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterImp  = "]/td[4]/a/i";
						
			logInfo("Number of emails = " +count);
						
			for(int i=1;i<=count-1;i++){
				WebElement x = driver().findElement(By.xpath(beforeSubj+i+afterSubj));
				String act_subject = x.getText().trim();
				if (act_subject.equalsIgnoreCase(subject.trim())) {
					logInfo(subject + " : email match found.");
					isEmailFound = true;
					// implicityWaits(10);
					WebElement eleStarred = driver().findElement(By.xpath(beforeImp+i+afterImp));
					eleStarred.click();
					break;
				} 
			}	
				 
			 if(isEmailFound==false){
					logInfo(subject + " email match not found.");
					Assert.assertTrue(isEmailFound, subject + " match not found.");
					}
				
			}
				     	

		public void verifyImpEmails(String subject) throws InterruptedException, Exception{
			logInfo("inside verifyImpEmails() method..");
						
			clickOnLink("xpath", lnkImportant);
			Thread.sleep(5000);
						
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size();
			boolean isEmailMarked = false;	
			String beforeSubj = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterSubj = "]/td[5]/span[1]";
								
			logInfo("Number of emails = " +count);
						
			for(int i=1;i<=count-1;i++){
				WebElement x = driver().findElement(By.xpath(beforeSubj+i+afterSubj));
				String act_subject = x.getText().trim();
				if (act_subject.equalsIgnoreCase(subject.trim())) {
					logInfo(subject + " email match found and marked as important.");
					isEmailMarked=true;
					break;
				}
			}
			
			if(isEmailMarked==false){
				logInfo(subject + " no emails marked as found important.");
				Assert.assertTrue(isEmailMarked, subject + " no emails marked as found important.");
				}
		}

					
		public void createLabel(String labelName) throws Exception {
			logInfo("inside createLabel() method..");
						
			Thread.sleep(3000);
			verifyLinkPresent("Create New");
			clickOnLink("linkText","Create New");
			Thread.sleep(5000);
			waitOnElement("cssSelector",inputLabelName);
			clickOnElement("cssSelector",inputLabelName);
			inputText("cssSelector",inputLabelName,labelName_text);
			Thread.sleep(2000);
			clickOnButton("cssSelector",btnLabelSave);
						
			Thread.sleep(3000);
		}
		
						
	/*				
		public void applyLabel(String labelName) throws Exception{
			logInfo("inside applyLabel() method..");
			verifyElementPresent("xpath",btnApplyLabel);
			clickOnButton("xpath",btnApplyLabel);
						
			WebElement applyLabelPanels = driver().findElement(By.xpath(labelControl));
			List<WebElement> li_menus = applyLabelPanels.findElements(By.tagName("li"));
			int li_count = li_menus.size();
						
			String before = "//*[@id='bulk-controls']/div[2]/ul/li[";
			String after = "]/a";
						
			for(int i=1; i<=li_count;i++){
				WebElement li = driver().findElement(By.xpath(before+i+after));
				String liName = li.getText().trim();
				if(liName.equalsIgnoreCase(labelName)){
					li.click();
					logInfo("clicked on menu label " + liName);
					break;
				}
			}
//				Thread.sleep(5000);
		}
					*/
		
		
		// Apply label for emails
		
		public void applyLabel(String labelName) throws Exception{
			logInfo("inside applyLabel() method..");
			verifyElementPresent("xpath",btnApplyLabel);
			clickOnButton("xpath",btnApplyLabel);
						
			WebElement applyLabelPanels = driver().findElement(By.xpath(labelControl));
			List<WebElement> li_menus = applyLabelPanels.findElements(By.tagName("li"));
			int li_count = li_menus.size();
						
			String before = "//*[@id='bulk-controls']/div[2]/ul/li[";
			String after = "]/a";
						
			for(int i=1; i<=li_count;i++){
				WebElement li = driver().findElement(By.xpath(before+i+after));
				String liName = li.getText().trim();
				if(liName.equalsIgnoreCase(labelName)){
					li.click();
					logInfo("clicked on menu label " + liName);
					break;
				}
			}
		//		Thread.sleep(5000);
		}
					
					
		public boolean verifyLabeledEmail(String subject) throws Exception{
			logInfo("inside verifyLabeledEmail() method..");
			boolean isLabelFound = false;
			Thread.sleep(5000);
						
			WebElement gmailBody = driver().findElement(By.xpath(tblInboxBody));
			List allRows = gmailBody.findElements(By.tagName("tr"));
			int count = allRows.size();
						
			String beforeSubj = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterSubj = "]/td[5]/span[1]";
						
			String beforeChk = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterChk = "]/td[1]/input";
						
			String beforelbl = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterlbl = "]/td[5]/div[1]/span/a[1]";   // "]/td[5]/span[1]/a[1]";
						
			int matchCnt=0;
			for(int i=1;i<=count-1;i++){
			WebElement x = driver().findElement(By.xpath(beforeSubj+i+afterSubj));
			String act_subject = x.getText().trim();
			if (act_subject.equalsIgnoreCase(subject)) {
				logInfo(subject + " match found.");
				matchCnt++;
				WebElement input = driver().findElement(By.xpath(beforeChk+i+afterChk));
				input.click();
				WebElement elabel = driver().findElement(By.xpath(beforelbl+i+afterlbl));
				String lblName = elabel.getText().trim();
				if(lblName.equalsIgnoreCase(labelName_text)){
					logInfo("Email with " + subject + " has the label " + lblName);
					isLabelFound = true;
					// Assert.assertTrue(lblName.equalsIgnoreCase(labelName_text), "Email with " + subject + " has the label " + lblName );
				}
			} 
		}
				
				if(matchCnt==0){
					logInfo(subject + " match not found.");
					Assert.assertTrue(false, "No emails found with " + subject + " and has the label.");
				}
				return isLabelFound;
			}
					
			
		
		public void deleteLabel(String labelName) throws Exception{
			logInfo("inside deleteLabel() method...");
						
			Thread.sleep(5000);
			WebElement lablePanel = driver().findElement(By.xpath(lableSidePane));
			List li_items = lablePanel.findElements(By.xpath("li"));
			int li_count = li_items.size();
						
			String before = "//*[@id='email-navigation']/ul[3]/li[";
			String after = "]/a";
						
			String beforeClose = "//*[@id='email-navigation']/ul[3]/li[";
			String afterClose = "]/span/a/i";
						
			for(int i=1;i<=li_count;i++){
				WebElement link = driver().findElement(By.xpath(before+i+after));
				String linkName = link.getText().trim();
				if(linkName.equalsIgnoreCase(labelName.trim())){
					logInfo(linkName + " : label match found for deletion.");
					WebElement close = driver().findElement(By.xpath(beforeClose+i+afterClose));
					close.click();
					//confirmEventDeleteModal();
					confirmOK();
					Thread.sleep(3000);
					break;
				}
			}
		}	
						
						
		/*
		// Compose an email and send to another (internal/external) users.
					
		public void composeVibeEmail(String recipients, String subject) throws Exception {
			logInfo("inside composeVibeEmail() method...");
			go2Inbox();
							
			clickOnLink("linkText", "New Email");
							
			WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); //  inputVibeComposeTo
			composeTo.click();
					
			clickOnElement("cssSelector",recipientsTo);
			inputText("cssSelector",recipientsTo, recipients);
			inputText("cssSelector",subject_Mail, subject);
			
			Robot rb = new Robot();
			rb.delay(2000);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			rb.delay(2000);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyPress(KeyEvent.VK_I);
			rb.keyRelease(KeyEvent.VK_I);
			rb.keyPress(KeyEvent.VK_B);
			rb.keyRelease(KeyEvent.VK_B);
			rb.keyPress(KeyEvent.VK_E);
			rb.keyRelease(KeyEvent.VK_E);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			
			verifyLinkPresent("Send");
			clickOnLink("linkText","Send");
			
			System.out.println("sent mail to gmail with subject " +subject);
			Thread.sleep(10000);
		}*/
						
		
		// Compose an email and send to another (internal/external) users.
		
		public void composeVibeEmail(String recipients, String subject) throws Exception {
			logInfo("inside composeVibeEmail() method...");
			go2Inbox();
					
			clickOnLink("linkText", "New Email");
			
			// waitOnElement("cssSelector",recipientsTo);
			WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); //  inputVibeComposeTo
			inputText("cssSelector","div > div > input#recipients-to",recipients);
			composeTo.click();
			Thread.sleep(2000);
			waitOnElement("cssSelector","ol.mp_list > li");
			clickOnElement("cssSelector","ol.mp_list > li");
			
			System.out.println("recipients = " +recipients);
					
			clickOnElement("cssSelector",recipientsTo);
			Thread.sleep(5000);
			inputText("cssSelector",recipientsTo, recipients);
			Thread.sleep(5000);
			//	clickOnElement("xpath","//*[@class='mp_item mp_selectable mp_highlighted']");
					
			inputText("cssSelector",subject_Mail, subject);
			composeText("xpath",composeBody, inboxMsgBodyText);
			scrollDown("linkText", "Send");
	        
			verifyLinkPresent("Send");
			clickOnLink("linkText","Send");
					
			System.out.println("sent mail to gmail with subject " +subject);
			Thread.sleep(10000);
		
		}
		
		
		public void minimizeQuickChatWindow() throws InterruptedException{
			logInfo("inside minimizeQuickChatWindow() method.");
			
			boolean isQuickWindowPresent = verifyElementPresent("xpath","//*[@id='chat-sidebar']/div[1]/div[1]");
			if(isQuickWindowPresent){
				clickOnElement("xpath","//*[@id='chat-sidebar']/div[1]/div[1]");
			}
		}
		
		
				
		// Compose an email with attachments and send to another (internal/external) users.
							
		public void composeVibeEmailwithAttachment(String recipients, String subject) throws Exception {
			logInfo("inside composeVibeEmailwithAttachment() method...");
			go2Inbox();
								
			clickOnLink("linkText", "Compose");
								
			WebElement composeTo = driver().findElement(By.cssSelector(inputVibeComposeTo));
			composeTo.click();
			inputText("cssSelector",recipientsTo, recipients);
			inputText("cssSelector",subject_Mail, subject);
							  
			/*		
				clickOnLink("linkText", "Attach a File");
				Thread.sleep(5000);
				clickOnButton("xpath",btnBrowseFile);
				Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload.exe");
				Thread.sleep(15000);
				clickOnButton("xpath",attachToMessge);
				Thread.sleep(5000);
				clickOnButton("xpath",attachMailCLOSE);
				Thread.sleep(5000);
			*/		
									
			clickOnLink("linkText","Send");
			Thread.sleep(10000);
		}
								
						
		// Compose an email with attachments and send to another (internal/external) users.
							
		public void composeVibeEmailwithRescAttachment(String recipients, String subject, String categoryName, String resourceName) throws Exception {
				logInfo("inside composeVibeEmailwithRescAttachment() method...");
				go2Inbox();
												
				clickOnLink("linkText", "New Email");
				
				waitOnElement("cssSelector",inputVibeComposeTo);
				WebElement composeTo = driver().findElement(By.cssSelector(inputVibeComposeTo));
				composeTo.click();
				inputText("cssSelector",recipientsTo, recipients);
				inputText("cssSelector",subject_Mail, subject);
				clickOnLink("linkText", "Attach Resource File");
				Thread.sleep(10000);
					
				WebElement prodPanel = driver().findElement(By.xpath(prodCategoryPane));
				List<WebElement> allProds = prodPanel.findElements(By.tagName("li"));
				int prods = allProds.size()-1;
				String before = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[1]/ul/li[";
				String after = "]/a[2]";
										
				for(int i=1;i<=prods;i++){
					WebElement prod = driver().findElement(By.xpath(before+i+after));
					String prodName = prod.getText().trim();
					if(prodName.equalsIgnoreCase(categoryName)){
						logInfo(categoryName + " : product matche found.");
						prod.click();
						Thread.sleep(5000);
						WebElement resPanel = driver().findElement(By.xpath(resPane));
						List allLabels = resPanel.findElements(By.tagName("label"));
						int labels = allLabels.size();
						String beforeRec = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[2]/div[3]/label[";
						String afterRes = "]/div/div[2]/div[1]";
						for(int j=1;j<=labels;j++){
							WebElement resc = driver().findElement(By.xpath(beforeRec+j+afterRes));
							String resName = resc.getText().trim();
							if(resName.equalsIgnoreCase(resourceName)){
								logInfo( resourceName + " resource match found.");
								String beforeChk = "//*[@id='resources-modal']/div/div[1]/div[2]/div/div[2]/div[3]/label[";
								String afterChk = "]/div/div[2]/input";
								WebElement chk = driver().findElement(By.xpath(beforeChk+j+afterChk));
								chk.click();
							}
						}
					}
				}
					
				clickOnButton("xpath",btnRescSave);
				Thread.sleep(5000);
				clickOnLink("linkText","Send");
				Thread.sleep(10000);
			}
									
						
	// Verify if email with the specified subject created is shown in the inbox.
						
		public void verifyVibeInboxMails(String subject) throws Exception {
			logInfo("inside verifyVibeInboxMails() method...");
			go2Inbox();
							
			WebElement inboxPane = driver().findElement(By.xpath(inboxPanel));
			List allRows = inboxPane.findElements(By.tagName("tr"));
			int rows_count = allRows.size();
			Boolean isEmailFound = false;
			
			for(int i=1;i<=rows_count-1;i++){
				String beforeSummary = "//*[@id='bulk-form']/table/tbody/tr[";
				String afterSummary = "]/td[5]/span[1]";
				WebElement x = driver().findElement(By.xpath(beforeSummary+i+afterSummary));
				String summary = x.getText().trim();
					
				if(summary.equalsIgnoreCase(subject.trim())){
					isEmailFound = true;
					logInfo(subject + " email match found in inbox.");
			
					Assert.assertTrue(isEmailFound==true, subject + " email match found in inbox.");
					break;
				}
			}
							
			if(isEmailFound==false){
				logInfo(subject + " email match could not be found in inbox.");
				Assert.assertTrue(isEmailFound==true, subject + " email match could not be found in inbox.");
			}
		}
					

	// Delete vibe inbox mails based on the filter type - Read/Unread/All/Starred/Unstarred/Important/Unimportant.

		public void deleteFilteredVibeMails() throws Exception{
			logInfo("inside deleteFilteredVibeMails() method..");
								
			WebElement gmailBody = driver().findElement(By.xpath(tblInboxBody));
			List allRows = gmailBody.findElements(By.tagName("tr"));
			int count = allRows.size();
								
			String beforechk = "//*[@id='bulk-form']/table/tbody/tr["; 
			String afterchk = "]/td[1]/input";
								
			int chkCount=0;
			for(int i=1;i<=count-1;i++){
				WebElement chkBox = driver().findElement(By.xpath(beforechk+i+afterchk));
				if(chkBox.isSelected()){
					chkCount++;
				}
			}
								
			logInfo("no of emails selected to delete = " +chkCount);
			if(chkCount>0){
				verifyElementPresent("xpath",btnDeleteInboxMail);
				clickOnButton("xpath",btnDeleteInboxMail);
				Thread.sleep(5000);
			}
		}
						
					
	// Verify email deleted is displayed in the trashed folder.
						
		public void verifyTrashedEmails(String subject) throws Exception {
			logInfo("inside verifyTrashedEmails() method.");
			go2Inbox();
			clickOnLink("linkText", "Trash");
			Thread.sleep(5000);
			WebElement inboxPane = driver().findElement(By.xpath(inboxPanel));
			List allRows = inboxPane.findElements(By.tagName("tr"));
			int rows_count = allRows.size();
			Boolean isEmailFound = false;
			for(int i=1;i<=rows_count-1;i++){
				String beforeSummary = "//*[@id='bulk-form']/table/tbody/tr[";
				String afterSummary = "]/td[5]/span[1]";
				WebElement x = driver().findElement(By.xpath(beforeSummary+i+afterSummary));
				String summary = x.getText().trim();
				if(summary.equalsIgnoreCase(subject.trim())){
					isEmailFound = true;
					break;
				}
			}
							
			if(isEmailFound==true){
				logInfo("subject \'" + subject + "\' email deleted is found in trash folder.");
				Assert.assertTrue(isEmailFound==true, "subject \'" + subject + "\' email deleted is found in trash folder.");	
			} else {
				logInfo("subject \'" + subject + "\' email not found in the trash folder.");
				Assert.assertTrue(isEmailFound==true, "subject \'" + subject + "\' email not found in the trash folder.");
			}
		}

	
		// Set Notifications for the user account
		
		public void setNotifications2User(String userName) throws Exception {
			logInfo("inside setNotifications2User() method.");
			driver().navigate().to(appUrl + "pyr_core/account");
			
			clickOnLink("linkText","Notifications");
			WebElement notificationTbl = driver().findElement(By.xpath(paneNotifications));
			List allRows = notificationTbl.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			
			String before_name = "//*[@id='messaging']/div[1]/table/tbody/tr[";
			String after_name = "]/td[1]";
			
			String before_row = "//*[@id='messaging']/div[1]/table/tbody/tr[";
			String after_row = "]/td[2]/input";
			
			if(all_rows>0){
				for(int i=1;i<=all_rows;i++){
					WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
					String email = e.getText().trim();
					if(email.equalsIgnoreCase(userName)){
						WebElement radio = driver().findElement(By.xpath(before_row+i+after_row));
						radio.click();
						break;
					}
				}
			}
		}
		
		

		public boolean selectMailsWithSubject(String subject) throws Exception {
			logInfo("inside selectVibeMailsWithSubject() method..");
			//	setMailsPerPage("100 Emails Per Page");
			// go2Inbox();
			waitOnElement("xpath",tblInboxBody);
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size();
						
			String before = "//*[@id='bulk-form']/table/tbody/tr[";
			String after = "]/td[5]/span[1]";	
						
			String beforeChk = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterChk = "]/td[1]/input";
			boolean isMatchFound = false;
						
			for(int i=1;i<=count-1;i++){
				WebElement x = driver().findElement(By.xpath(before+i+after));
				String act_subject = x.getText().trim();
				// if (act_subject.matches(subject)) {
				// if (subject.contains(act_subject)) {  // use this later
				if (subject.equalsIgnoreCase(act_subject)){
					isMatchFound = true;
					WebElement chk = driver().findElement(By.xpath(beforeChk+i+afterChk));
					chk.click();
					logInfo("email with subject \'" + subject + "\' match found @" + i);
					// Assert.assertTrue(act_subject.equalsIgnoreCase(subject), "email with subject \'" + subject + "\' match found @" + i);
				} 
			}
						
			if(isMatchFound==false){
				logInfo("No emails found with the specified subject : " + subject);
				// Assert.assertTrue(isMatchFound, "No emails found with the specified subject : " + subject);
			}
			return isMatchFound;
		}	
				
		
		// count emails in inbox page
		
		
		public int countEmailsInInboxPage(String setMailsPerPage, String filter) throws Exception {
			logInfo("inside countEmailsInInboxPage() method.");
			
			setMailsPerPage(setMailsPerPage);
			go2Inbox();
						
			waitOnElement("xpath",btnFilterInboxEmail);
			clickOnButton("xpath",btnFilterInboxEmail);
			clickOnLink("linkText",filter);
						
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size()-1;
						
			logInfo("Total emails in Inbox = " +count);
			return count;
		}
						
		
		
		public boolean verifyEmailsCheckedForFilter(String subject, String filter) throws Exception {
			logInfo("inside verifyEmailsCheckedForFilter() method.");
			
			// go2Inbox();
			// setMailsPerPage("100 emails per page");
			
			waitOnElement("xpath",btnFilterInboxEmail);
			clickOnButton("xpath",btnFilterInboxEmail);
			clickOnLink("linkText",filter);
			
			waitOnElement("xpath",tblInboxBody);
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			
			String before_chk = "//*[@id='bulk-form']/table/tbody/tr[";
			String after_chk = "]/td[1]/input";
			
			String before = "//*[@id='bulk-form']/table/tbody/tr[";
			String after = "]/td[5]/span[1]";	
			boolean isEmailMatched = false;
			
			for(int i=1;i<=allRows.size()-1;i++){
				WebElement chk = driver().findElement(By.xpath(before_chk+i+after_chk));
				boolean isChecked = chk.isSelected();
				
				WebElement subj =  driver().findElement(By.xpath(before+i+after));
				String actSubj = subj.getText().trim();
						
				if(actSubj.equalsIgnoreCase(subject) && isChecked==true){
					logInfo(subject + " email match is found checked.");
					isEmailMatched = true;
					break;
				}
				Thread.sleep(1000);
			}
			
			return isEmailMatched;
		}
			
		
		

		public boolean viewEmailsWithSubject(String subject) throws Exception {
			logInfo("inside viewEmailsWithSubject() method..");
			
			// setMailsPerPage("100 Emails Per Page");
			
			// go2Inbox();
			waitOnElement("xpath",tblInboxBody);
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size();
						
			String before = "//*[@id='bulk-form']/table/tbody/tr[";
			String after = "]/td[5]/span[1]";	
						
			/*String beforeSub = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterSub = "]/td[2]";*/
			boolean isMatchFound = false;
						
			for(int i=1;i<=count-1;i++){
				WebElement x = driver().findElement(By.xpath(before+i+after));
				String act_subject = x.getText().trim();
				// if (act_subject.matches(subject)) {
				if (subject.contains(act_subject)) {
					isMatchFound = true;
					//WebElement sub = driver().findElement(By.xpath(beforeSub+i+afterSub));
					//sub.click();
					x.click();
					logInfo("email with subject \'" + subject + "\' match found @" + i);
					Thread.sleep(5000);
					break;
					// Assert.assertTrue(act_subject.equalsIgnoreCase(subject), "email with subject \'" + subject + "\' match found @" + i);
				} 
			}
						
			if(isMatchFound==false){
				logInfo("No emails found with the specified subject : " + subject);
				// Assert.assertTrue(isMatchFound, "No emails found with the specified subject : " + subject);
			}
			return isMatchFound;
		}	
				

		public int getInboxCntInQuickLinksWidget(){
			logInfo("inside getInboxCntInQuickLinksWidget() method");
			
			WebElement e = driver().findElement(By.xpath("//*[@id='user-control-panel']/div[2]/div/a[4]/div[1]"));
			String inboxCnt = e.getText().trim();
			int count = Integer.parseInt(inboxCnt);
			
			return count;
		}
		
		
		public void searchEmail(String emailSubject) throws Exception{
			logInfo("inside searchEmail() method");
			
			inputTextClear("xpath",inputSearch);
			inputText("xpath",inputSearch,emailSubject);
			clickOnElement("xpath",btnSearch);
			Thread.sleep(5000);
			
		}
		
		
		public void selectInboxFolder(String folderName) throws Exception, Exception{
			logInfo("inside selectInboxFolder() method");
			
			switch(folderName){
				case "Inbox" :
					// waitOnElement("xpath",lnkInbox);
					// clickOnElement("xpath",lnkInbox);
					// Thread.sleep(5000);
					driver().navigate().to(appUrl + "crm/messages");
					break;
				case "Starred":
					// waitOnElement("xpath",lnkStarred);
					// clickOnElement("xpath",lnkStarred);
					// Thread.sleep(5000);
					driver().navigate().to(appUrl + "crm/messages/starred");
					break;
				case "Important":
					// waitOnElement("xpath",lnkImportant);
					// clickOnElement("xpath",lnkImportant);
					// Thread.sleep(5000);
					driver().navigate().to(appUrl + "crm/messages/important");
					break;
				case "Sent":
					// waitOnElement("xpath",lnkSent);
					// clickOnElement("xpath",lnkSent);
					// Thread.sleep(5000);
					driver().navigate().to(appUrl + "crm/messages/sent");
					break;
				case "Attachments":
					// waitOnElement("xpath",lnkAttachments);
					// clickOnElement("xpath",lnkAttachments);
					// Thread.sleep(5000);
					driver().navigate().to(appUrl + "crm/messages/drive");
					break;
				case "Trash" :
					// waitOnElement("xpath",lnkTrash);
					// clickOnElement("xpath",lnkTrash);
					// Thread.sleep(10000);
					driver().navigate().to(appUrl + "crm/messages/trash");
					break;
				}
		}
		
		
		
		public void performMoreActionsForSelectedEmails(String actionName) throws Exception {
			logInfo("inside performMoreActionsForSelectedEmails() method.");
			// go2Inbox();
			// setMailsPerPage("100 emails per page");
			
			waitOnElement("xpath",btnMoreFilter);
			clickOnButton("xpath",btnMoreFilter);
			clickOnLink("linkText",actionName);
			Thread.sleep(5000);
		}
		
		public void go2Users() {
			logInfo("inside go2Users() method.");
			driver().navigate().to(appUrl + "pyr_core/users");
		}
		
		
		
		
		public void loginAsUser(String user) throws Exception{
			logInfo("inside loginAsUser() method.");
	  		go2Users();
		 	searchUser(user);
		 	boolean isUserFound = verifyUserPresent(user);
		 	if(isUserFound){
		 		clickOnLink("linkText", "Login As User");
		 		logInfo("clicked on Login As User button");
		 	} 
	  	}
		public void searchUser(String uName) throws Exception, Exception {
			logInfo("inside searchUser() method.");
			waitOnElement("xpath", searchUser);
			inputTextClear("xpath", searchUser);
			inputText("xpath", searchUser, uName + " ");
			clickOnButton("cssSelector", btnUserSearch);
			Thread.sleep(5000);
		}
		
		public boolean verifyUserPresent(String uName) throws Exception, Exception {
			logInfo("inside verifyUserPresent() method.");

			Thread.sleep(5000);
			waitOnElement("xpath", tblAdminUsers);
			WebElement tblUsers = driver().findElement(By.xpath(tblAdminUsers));
			List allUsers = tblUsers.findElements(By.tagName("tr"));
			String before_name = "//*[@id='users']/table/tbody/tr[";
			String after_name = "]/td[3]/a";
			boolean isUserFound = false;
			if (allUsers.size() > 0) {
				for (int i = 1; i <= allUsers.size(); i++) {
					WebElement tblmsg = driver().findElement(By.xpath("//*[@id='users']/table/tbody/tr"));
					List msgrow = tblmsg.findElements(By.tagName("td"));
					if (msgrow.size() > 1) {
						WebElement x = driver().findElement(By.xpath(before_name + i + after_name));
						String name = x.getText().trim();
						verifyElementPresent("xpath", before_name + i + after_name);
						if (name.equalsIgnoreCase(uName)) {
							isUserFound = true;
							logInfo(uName + " user found in user search page.");
							break;
						}
					}
				}
			}

			if (isUserFound == false) {
				logInfo(uName + " user not found in user search page.");
				Assert.assertTrue(isUserFound, uName + " user not found in user search page.");
			}
			return isUserFound;
		}
		
		
		public void go2Homepage(){
			logInfo("inside loginAsUser() method.");
			driver().navigate().to(appUrl);
		}
		
		public void submitReply(String subject) throws Exception, Exception{
			logInfo("inside submitReply() method.");
			
			waitOnElement("linkText","Reply");
			clickOnElement("linkText","Reply");
			
			waitOnElement("cssSelector",subject_Mail);
			inputTextClear("cssSelector",subject_Mail);
			inputText("cssSelector",subject_Mail, subject);
			
			Robot rb = new Robot();
			rb.delay(2000);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			rb.delay(2000);
			rb.keyPress(KeyEvent.VK_R);
			rb.keyRelease(KeyEvent.VK_R);
			rb.keyPress(KeyEvent.VK_E);
			rb.keyRelease(KeyEvent.VK_E);
			rb.keyPress(KeyEvent.VK_P);
			rb.keyRelease(KeyEvent.VK_P);
			rb.keyPress(KeyEvent.VK_L);
			rb.keyRelease(KeyEvent.VK_L);
			rb.keyPress(KeyEvent.VK_Y);
			rb.keyRelease(KeyEvent.VK_Y);
			
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			
			/*JavascriptExecutor js = (JavascriptExecutor)driver;         
	        WebElement send = driver().findElement(By.linkText("Send"));
	        js.executeScript("arguments[0].scrollIntoView(true);", send);*/
			
			scrollDown("linkText", "Send");
	        
			verifyLinkPresent("Send");
			clickOnLink("linkText","Send");
		}
		
		
		public void forwardEmail(String subject, String forwardRecepient) throws Exception, Exception{
			logInfo("inside forwardEmail() method.");
			
			waitOnElement("linkText","Forward");
			clickOnElement("linkText","Forward");
			
			WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); //  inputVibeComposeTo
			composeTo.click();
					
			System.out.println("recipients = " +forwardRecepient);
					
			clickOnElement("cssSelector",recipientsTo);
			Thread.sleep(5000);
			inputText("cssSelector",recipientsTo, forwardRecepient);
			Thread.sleep(5000);
			
			waitOnElement("cssSelector",subject_Mail);
			inputTextClear("cssSelector",subject_Mail);
			inputText("cssSelector",subject_Mail, subject);
			
			Robot rb = new Robot();
			rb.delay(2000);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			rb.delay(2000);
			rb.keyPress(KeyEvent.VK_F);
			rb.keyRelease(KeyEvent.VK_F);
			rb.keyPress(KeyEvent.VK_O);
			rb.keyRelease(KeyEvent.VK_O);
			rb.keyPress(KeyEvent.VK_R);
			rb.keyRelease(KeyEvent.VK_R);
			rb.keyPress(KeyEvent.VK_W);
			rb.keyRelease(KeyEvent.VK_W);
			rb.keyPress(KeyEvent.VK_A);
			rb.keyRelease(KeyEvent.VK_A);
			rb.keyPress(KeyEvent.VK_R);
			rb.keyRelease(KeyEvent.VK_R);
			rb.keyPress(KeyEvent.VK_D);
			rb.keyRelease(KeyEvent.VK_D);
			
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			
			/*JavascriptExecutor js = (JavascriptExecutor)driver;         
	        WebElement send = driver().findElement(By.linkText("Send"));
	        js.executeScript("arguments[0].scrollIntoView(true);", send);*/
			
			scrollDown("linkText", "Send");
	        
			verifyLinkPresent("Send");
			clickOnLink("linkText","Send");
		}
		
	
		// Compose an email with attachment
		
		public void composeEmailWithAttachment(String recipients, String subject) throws Exception {
			logInfo("inside composeEmailWithAttachment() method.");
			go2Inbox();
					
			clickOnLink("linkText", "New Email");
									
			WebElement composeTo = driver().findElement(By.cssSelector(recipientsTo)); //  inputVibeComposeTo
			composeTo.click();
					
			System.out.println("recipients = " +recipients);
					
			clickOnElement("cssSelector",recipientsTo);
			Thread.sleep(5000);
			inputText("cssSelector",recipientsTo, recipients);
			Thread.sleep(5000);
			//	clickOnElement("xpath","//*[@class='mp_item mp_selectable mp_highlighted']");
					
			inputText("cssSelector",subject_Mail, subject);
					
			Robot rb = new Robot();
			rb.delay(2000);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			rb.delay(2000);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyPress(KeyEvent.VK_I);
			rb.keyRelease(KeyEvent.VK_I);
			rb.keyPress(KeyEvent.VK_B);
			rb.keyRelease(KeyEvent.VK_B);
			rb.keyPress(KeyEvent.VK_E);
			rb.keyRelease(KeyEvent.VK_E);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			
			
			addAttachment("Image");
			
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
			
			Thread.sleep(10000);
		}
		
		
		public void addAttachment(String fileType) throws Exception, Exception{
			logInfo("inside addAttachment() method");
			
			clickOnElement("linkText","Attach a File");
						
			Thread.sleep(10000);
			waitOnElement("cssSelector","form.simple_form.new_pyr_crm_message > input#message-attach-file-field");
			/*clickOnElement("cssSelector","form.simple_form.new_pyr_crm_message > input#message-attach-file-field");
						
			uploadFile(fileType);*/
			uploadFile(fileType,"form.simple_form.new_pyr_crm_message > input#message-attach-file-field");
			
			clickOnElement("cssSelector","div.modal-content > div.modal-footer > button.btn.btn-default:nth-child(1)");
			Thread.sleep(30000);
		}
		
		public String composeEmailWithNoData() throws Exception{
			logInfo("inside composeEmailWithAttachment() method.");
			go2Inbox();
					
			clickOnLink("linkText", "New Email");
			
			verifyLinkPresent("Send");
			clickOnLink("linkText","Send");
			
			Thread.sleep(3000);
			waitOnElement("cssSelector", "div.modal-content > div.modal-body > div.bootbox-body");
			String msg  = getTextPresentOnElement("cssSelector", "div.modal-content > div.modal-body > div.bootbox-body");
			return msg;
			
		}
		
		
		public void unsubscribeEmail(String recepient2Addr, String optStatus, String recepientFromAddr) throws Exception, Exception{
			logInfo("inside unsubscribeEmail() method.");
			
			waitOnElement("linkText","Unsubscribe");
			clickOnLink("linkText","Unsubscribe");
			
			inputTextClear("cssSelector",inputToMail);
			inputText("cssSelector",inputToMail ,recepient2Addr);
			
			
			switch(optStatus){
				case "Optin":
					selectFromDropdown("cssSelector",drpdnOptStatus ,"byVisibleText", "opt in to mailings");
					break;
				case "Optout":
					selectFromDropdown("cssSelector",drpdnOptStatus ,"byVisibleText", "opt out of mailings");
					break;
			}
			
			inputTextClear("cssSelector",inputFromMail);
			inputText("cssSelector",inputFromMail ,recepientFromAddr);
			
			clickOnElement("cssSelector", btnUnsubscribeSubmit);
			Thread.sleep(3000);
		}
		
		
		public boolean verifyAttachmentIsPresent(String fromRecepient) throws Exception, Exception{
			logInfo("inside verifyAttachmentIsPresent() method.");
			
			String before_addr = "//*[@id='email-panel']/div[3]/div[";
			String after_addr = "]/div/div[1]/span";
			
			boolean isAttachPresent = false;
			waitOnElement("xpath","//*[@id='email-panel']/div[3]");
			WebElement attachPanel = driver().findElement(By.xpath("//*[@id='email-panel']/div[3]"));
			List allMails = attachPanel.findElements(By.cssSelector("div.col-md-12 > div.media.actionable.attachment-container"));
			int all_mails = allMails.size();
			System.out.println("Total mails =" +all_mails);
			if(all_mails >0){
				for(int i=1; i<=all_mails ;i++){
					WebElement x = driver().findElement(By.xpath(before_addr+i+after_addr));
					String addr = x.getText().trim();
					if(addr.contains(fromRecepient)){
						System.out.println(addr);
						isAttachPresent = true;
						logInfo(fromRecepient + " is present in Attachment folder.");
						break;
					}
				}
			}
			
			if(isAttachPresent==false){
					logInfo(fromRecepient + " is not present in Attachment folder.");
			}
			
			return isAttachPresent;
		}
		
		
		
		
		 public boolean verifyFileExistsOnDisk(String filepath){
			 logInfo("inside verifyFileExistsOnDisk() method");
			 File file = new File(filepath);
			 return file.exists();
		 }
				

		public boolean downloadAttachment(String fromRecepient) throws Exception{
			logInfo("inside downloadAttachment() method.");
			System.out.println("fromRecepient = " +fromRecepient);
			
			String before_addr = "//*[@id='email-panel']/div[3]/div[";
			String after_addr = "]/div/div[1]/span";
			
			String before_download = "//*[@id='email-panel']/div[3]/div[";
			String after_download = "]/div/div[3]/div[4]/a[2]";
			
			boolean isAttachPresent = false;
			boolean isFileExists = false;
			String filepath = null;
			
			waitOnElement("xpath","//*[@id='email-panel']/div[3]");
			WebElement attachPanel = driver().findElement(By.xpath("//*[@id='email-panel']/div[3]"));
			List allMails = attachPanel.findElements(By.cssSelector("div.col-md-12 > div.media.actionable.attachment-container"));
			int all_mails = allMails.size();
			System.out.println("Total mails =" +all_mails);
			fromRecepient = "To: " + fromRecepient;
			
			if(all_mails >0){
				for(int i=1; i<=all_mails ;i++){
					System.out.println("Row no = " +i);
					WebElement x = driver().findElement(By.xpath(before_addr+i+after_addr));
					String addr = x.getText().trim();
				
					System.out.println("addr = " +addr);
					System.out.println("fromRecepient = " +fromRecepient);
					if(addr.equalsIgnoreCase(fromRecepient)){
						isAttachPresent = true;
						WebElement download = driver().findElement(By.xpath(before_download+i+after_download));
						download.click();
						
						logInfo(fromRecepient + " is present in Attachment folder.");
						
						Thread.sleep(5000);
						Robot rb = new Robot();
						rb.keyPress(KeyEvent.VK_TAB);
						rb.keyRelease(KeyEvent.VK_TAB);
						rb.keyPress(KeyEvent.VK_TAB);
						rb.keyRelease(KeyEvent.VK_TAB);
						rb.keyPress(KeyEvent.VK_TAB);
						rb.keyRelease(KeyEvent.VK_TAB);
						rb.keyPress(KeyEvent.VK_ENTER);
						rb.keyRelease(KeyEvent.VK_ENTER);
						
						filepath = "c:\\vibe\\downloads\\";
						if(verifyFileExistsOnDisk(filepath+"icentris_image.jpg")){
							isFileExists = true;
							logInfo(filepath+"export.xls" + " file exists.");
						} 
						break;
					}
				}
			}
			
			/*if(isAttachPresent==false){
				logInfo(fromRecepient + " is not present in Attachment folder.");
			}*/
			
			if(isFileExists==false){
				logInfo(filepath+"icentris_image.jpg" + " file does not exists.");
			}
			return isFileExists;
		}
				
		
}

