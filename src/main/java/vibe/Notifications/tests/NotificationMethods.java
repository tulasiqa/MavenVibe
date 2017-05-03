package vibe.Notifications.tests;

import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import com.sun.glass.ui.Robot;

import common.TestBase;
import common.readProp;
import vibe.enrollment.tests.EnrollMethods;
import vibe.tasks.tests.TaskMethods;

public class NotificationMethods extends TestBase{
	
	TaskMethods tk = new TaskMethods();
	readProp prop = new readProp();
	
	/*public void navHome() throws Exception{
		logInfo("Navigate to Home Page");
		
	}*/
	
	public void navAlerts() throws Exception{
		logInfo("Navigate to Alerts screen by selecting Alert Icon on the Top.");
		driver().navigate().to(appUrl);		
		waitOnElement("cssSelector", notiIcon);
		clickOnElement("cssSelector", notiIcon);
		waitOnElement("cssSelector", viewAllNoti);
		clickOnElement("cssSelector",viewAllNoti );
		
	}
	
	public void navMailNotifications() throws Exception{		
		logInfo("Navigate to Mails Notification screen by selecting Notification Icon on the top.");		
		driver().navigate().to(appUrl);
		Thread.sleep(5000);
		waitOnElement("cssSelector", mailNotiBr);
		clickOnElement("cssSelector", mailNotiBr);
		Thread.sleep(2000);
		clickOnElement("cssSelector",viewAllNoti1 );
		Thread.sleep(4000);
		
		/*
		System.out.println(driver().getCurrentUrl());		
		if(driver().getCurrentUrl().contains("branch")){	
			waitOnElement("cssSelector", mailNotiBr);
			clickOnElement("cssSelector", mailNotiBr);
			Thread.sleep(2000);
			clickOnElement("cssSelector",viewAllNoti1 );
			Thread.sleep(4000);	
		}else {
			clickOnElement("cssSelector", mailNotiMaster);
			Thread.sleep(2000);
			clickOnElement("cssSelector",viewAllNoti );
			Thread.sleep(4000);	
		}	*/	
		
	}
	
	public void getPanelTitle(String expPanelTitle){
		 
		logInfo("Assert the Panel Title with "+expPanelTitle);		
		WebElement ti = driver().findElement(By.cssSelector(panelTitle));		
		Assert.assertEquals(ti.getText(), expPanelTitle);		
		
	}
	 
	 public void getCountOfAll() throws Exception{
		  getText("xpath", AllCount, "No of count from All ");		
	  }	
	
	  public void getCountOfAlerts() throws Exception{
		  getText("cssSelector", alertCount, "No of Alerts ");
		
	  }
	
	  public void getCountOfMails() throws Exception{
		  getText("cssSelector", mailcount, "No of mails ");		  
		 
	  }
	
	public void getListOfNotifactionPanel(){
		 
		logInfo("Retrieve all list of Tools from Pane.");		
		List <WebElement> ts = driver().findElements(By.cssSelector(toolsPane));		
		for (WebElement tols :ts){			
			String itm = tols.getText();			
		switch (itm){
			case "All" :
				Assert.assertEquals("All", itm);
				break;
			case "Tools" :
				Assert.assertEquals("Tools", itm);
				break;
			case "Comment" :
				Assert.assertEquals("Comment", itm);
				break;
			case "Status" :
				Assert.assertEquals("Status", itm);
				break;
			
			case "Background Job" :
				Assert.assertEquals("Background Job", itm);
				break;
			case "Login History" :
				Assert.assertEquals("Login History", itm);
				break;			
			case "Resource" :
				Assert.assertEquals("Resource", itm);
				break;
			case "Profile" :
				Assert.assertEquals("Profile", itm);
				break;			
			case "Message Recipient" :
				Assert.assertEquals("Message Recipient", itm);
				break;
			case "User Task" :
				Assert.assertEquals("User Task", itm);
				break;
			case "Message" :
				Assert.assertEquals("Message", itm);
				break;
			case "Dismissed Notifications" :
				Assert.assertEquals("Dismissed Notifications", itm);
				break;				
			default:
				System.err.println(itm);				
		}	} 		
		
	}
	
	public void selectNotifactionPanel(String tools){		 
		logInfo("Select"+tools+" from Notification Panel .");	
		boolean ispanel = false;
		List <WebElement> ts = driver().findElements(By.cssSelector(toolsPane));		
		for (WebElement tols :ts){				
			if (tols.getText().contains(tools)){		
				ispanel =true;
				tols.click();
				break;
			}			
		} 	if (ispanel==false)	{
			
			Assert.assertTrue(ispanel, tools + " is not present in left panel." );
		}
	}
	
	
	public void getListOfPriority(){
		logInfo("Retrieve all list of Priority from left Panel.");		
		List <WebElement> ts = driver().findElements(By.cssSelector(prityList));
		System.out.println("size of priorty"+ ts.size());
		for (WebElement tols :ts){		
			String itm = tols.getText().trim();	
			System.out.println(itm);
			switch (itm){
				case "All" :
					Assert.assertEquals("All", itm);
					break;
				case "Low" :
					Assert.assertEquals("Low", itm);
					break;
				case "Medium" :
					Assert.assertEquals("Medium", itm);
					break;
				case "High" :
					Assert.assertEquals("High", itm);
					break;				
				case "Urgent" :
					Assert.assertEquals("Urgent", itm);
					break;
				case "Critical" :
					Assert.assertEquals("Critical", itm);
					break;			
			default:
				System.err.println(itm);			
		}	}			
		
	}
	//	Retrieve all list of Priority from left Panel.	
	public void selectPriority(String priority){
		 logInfo("Entered into selectPriority() method ");	
		 
		boolean isPriorityFound = false;		
		List <WebElement> ts = driver().findElements(By.cssSelector(prityList));	
		System.out.println(ts.size()+ "siz e is ");
		for (int i = 1; i<=ts.size(); i++){			
			WebElement tols = driver().findElement(By.cssSelector(prityList1+i+prityList2));				
			String itm = tols.getText();
			System.out.println("List are "+ itm);
			if (itm.equalsIgnoreCase(priority)){
				System.out.println("List is "+ itm);
				isPriorityFound =true;
				tols.click();
				break;
			}			
		  } if (isPriorityFound==false){
			  Assert.assertTrue(isPriorityFound, priority + "is not found.");
		  }
		}	
	
	public void allMessageHeadersInPanel(){		 
		logInfo("Retrive all Message in Content Panel.");		
		List <WebElement> ts = driver().findElements(By.cssSelector(paContMsg)); 
		System.out.println("No of mails "+ts.size());	
		if (ts.size()==0){
			WebElement noti = driver().findElement(By.cssSelector(noNot));
			System.out.println(noti.getText().trim());
			Assert.assertEquals(noti.getText().trim(), "You Don't Have Any Notifications");
			System.out.println("No mails are available");			
		}else{
			for (int i =2; i<=ts.size(); i++){
			WebElement tols = driver().findElement(By.cssSelector(paContMsg1+i+paContMsg2));				
			System.out.println(tols.getText());			
		                 }	
			}   }
	public void forwardMailFromPanel() throws Exception{		
		logInfo("Select the message and send Reply to same Mail from Panel");
		//clickOnButton("cssSelector", markAllSeen);
		Thread.sleep(3000);
		List <WebElement> ts = driver().findElements(By.cssSelector(msgLink));	
		
		for (WebElement tols :ts){		
			System.out.println("clicked ontit");
			tols.click();
			isViewingEmailPresent();
			forwardToMail();		
			break;			
		}
	}
	
	public void selectMsgOfChatUser(String msgText) throws Exception{		 
		logInfo("Access User profile based on message which has 'You received an invitation to chat' in Panel Content.");		
		    boolean isFound = false;
			List <WebElement> ts = driver().findElements(By.cssSelector(paContSub));
			System.out.println(ts.size());
			for (WebElement tols :ts){
				System.out.println(tols.getText()+" list");
			if (tols.getText().contains(msgText)){	
				isFound =true;
				tols.click();
			    break;
		          }}if (isFound==false){
		        	  Assert.assertTrue(isFound, msgText+ " is not found");
		          }
	         }
	public void isViewingEmailPresent() throws Exception, InterruptedException{
		 
		logInfo("Verify the modal window of 'Viewing Email'.");
		WebElement act = driver().findElement(By.cssSelector(viewMail));
		String expText = "Viewing Email";
		Assert.assertEquals(act.getText(), expText);
	         }
	//Select Reply to opened mailed.
	public void replyToMail() throws Exception{		 
		logInfo("Entered into replyToMail() method ");
		clickOnElement("cssSelector", ReplyMail);	
		
       waitOnElement("cssSelector", sendMail);	
		clickOnButton("cssSelector", sendMail);	 
	      }
	
	public void forwardToMail() throws Exception{
		 
		logInfo("Select forward to opened mailed.");
		clickOnLink("linkText", "Forward");
		waitOnElement("cssSelector", toMail);
		inputText("cssSelector", toMail, recipient); 
		waitOnElement("cssSelector", toMail);
		inputText ("cssSelector", notSub, "Sample");		
	    waitOnElement("cssSelector", sendMail);	
		clickOnButton("cssSelector", sendMail);			
		confirmationMessage(prop.getLocatorForEnvironment(appUrl,"toasterMailMsg")+recipient );
		   }	
	
	public void retrieveCountsWithTasks() throws InterruptedException, Exception, Exception{		 
		logInfo("Retrieve alerts count before & After , creation of Task ");
		
		driver().navigate().to(appUrl);		
		Thread.sleep(3000);		
		WebElement ac = driver().findElement(By.cssSelector(alertCount));
		String al = ac.getText();
		int alertsCount = Integer.parseInt(al);
		int increasedAlerts= alertsCount+1;	
		String increasedAlertsCount = Integer.toString(increasedAlerts);
		System.out.println("Normal count is "+ alertsCount);		
	/*	Assert.assertEquals(al, increasedAlertsCount);		
		System.out.println("Succesd");*/		
		tk.navigate2BusinessTasks();
		tk.deleteAllWidgets();
		tk.selectEditWidgets();		  
		tk.dragWidget(tasksAddWidgets,dragWidgetToLocation);		  
		tk.collapseEditWidget();
		tk.NoDueDateTask(taskEvent3);	
		
		driver().navigate().to(appUrl);		
		Thread.sleep(3000);
		WebElement aftcount = driver().findElement(By.cssSelector(alertCount));
		String afterCount = aftcount.getText();
		/*int afterAlertCount = Integer.parseInt(afterCount);*/
		System.out.println(afterCount + " and  "+ increasedAlertsCount);  		
		/*Assert.assertEquals(afterCount, increasedAlertsCount);	*/	
	}
	
	
	public void avon_navNotifications() throws Exception{
		logInfo("Navigate to Alerts screen by selecting Alert Icon on the Top.");
		driver().navigate().to(appUrl);		
		waitOnElement("cssSelector", avonNotiIcon);
		clickOnElement("cssSelector", avonNotiIcon);
		waitOnElement("cssSelector", viewAllNoti);
		clickOnElement("cssSelector",viewAllNoti );
		
	}
	
 //	logInfo("Retrieve all list of Priority from left Panel.");	
	public void selectPriorityInAvon(String priority){
		 logInfo("Entered into selectPriorityInAvon() method ");		 
		boolean isPriorityFound = false;		
		List <WebElement> ts = driver().findElements(By.cssSelector(avonPrityList));	
		System.out.println(ts.size()+ "siz e is ");
		for (int i = 1; i<=ts.size(); i++){			
			WebElement tols = driver().findElement(By.cssSelector(avonPrityList1+i+avonPrityList2));				
			String itm = tols.getText();
			System.out.println("List are "+ itm);
			if (itm.equalsIgnoreCase(priority)){
				System.out.println("List is "+ itm);
				isPriorityFound =true;
				tols.click();
				break;
			}			
		  } if (isPriorityFound==false){
			  Assert.assertTrue(isPriorityFound, priority + "is not found.");
		  }
		}	
	
	
	public void getListOfNotifactionPanelInAvon(){
		 
		logInfo("Retrieve all list of Tools from Pane.");		
		List <WebElement> ts = driver().findElements(By.cssSelector(toolsPane));	
		System.out.println("No of items are "+ts.size());
		for (WebElement tols :ts){			
			String itm = tols.getText();			
		switch (itm){
			case "All" :
				Assert.assertEquals("All", itm);
				break;		
			case "Business" :
				Assert.assertEquals("Business", itm);
				break;
			case "Dismissed Notifications" :
				Assert.assertEquals("Dismissed Notifications", itm);
				break;				
			default:
				System.err.println(itm);				
		}	} 		
		
	}
	
	
	public void verifyMarkAllSeen() throws Exception{
		String expSeen = "Mark All Seen";
		WebElement seen = driver().findElement(By.cssSelector(markAllSeen));
		Assert.assertEquals(seen.getText().trim(), expSeen);
		clickOnElement("cssSelector",markAllSeen);
		waitOnElement("cssSelector",alertCount);
		WebElement ac = driver().findElement(By.cssSelector(alertCount));
		String al = ac.getText();
		System.out.println("retrived count is "+al);
		Assert.assertEquals(al, "0");
		
		
		
		
	}
	
	
}
