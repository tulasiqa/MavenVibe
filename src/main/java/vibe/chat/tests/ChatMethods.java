package vibe.chat.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import vibe.calendar.tests.CalendarMethods;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.people.tests.PeopleMethods;
import vibe.reports.tests.ReportsMethods;
import vibe.tasks.tests.TaskMethods;
import vibe.training.tests.TrainingMethods;
import common.TestBase;

public class ChatMethods extends TestBase {
	CommunityMethods comm = new CommunityMethods();
	PeopleMethods ppl = new PeopleMethods();
	
	public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl); 
	
	/*public static String vibeUser(String userName){
     	String uName = userName.trim();
		String[] parts = StringUtils.split(uName,".");
		String fName = parts[0];
		String lName = parts[1];
		String chatter = fName+" "+lName;	
		return chatter;
  }*/
	
	

	public void navigateToChat(){
		logInfo("Navigating to Chat Screen ");
		try{
			clickOnElement("xpath", COMMUNICATIONS_Tab);
			clickOnLink("linkText", CHAT);
		}
		catch (Exception e){
			System.err.println("Failed!! Not able to navigate to Chat screen");
		}
	
	}
		
   public void chatOnlineUser() throws Exception{
		logInfo("Chat with Online User by selecting Sorting option.");
		selectFilterOption("Online");
		Thread.sleep(5000);
   }
   
   // Retrive from users from left panel 
   public void ListofUsers(String user) throws Exception{
	   boolean isUserPresent= false;
	   Thread.sleep(1000);
	   List<WebElement> users = driver().findElements(By.cssSelector(usersList));
	   System.out.println("No of users "+users.size());
	   for (WebElement userList : users){
		   System.out.println("Name are as : "+userList.getText());
		   if(userList.getText().contains(user)){
			   isUserPresent =true;
			   userList.click();
			   break;		   
		   }		   
	   }  if(isUserPresent==false) {
		   Assert.assertTrue(isUserPresent, user + " user is not found.");
	   }
		   
   }
   
   // Retrive from users from left panel 
   public void selectUser() throws Exception{
	   logInfo("Entered into selectUser() method");
	   waitOnElement("cssSelector", usersList);   
	   List<WebElement> users = driver().findElements(By.cssSelector(usersList));
	   System.out.println("No of users "+users.size());
	   for (WebElement userList : users){		   
		   String expUserName = userList.getText(); 
		   System.out.println("Name are as : "+expUserName);
			   userList.click();			   
			   break;		   
		  	   
	   }  
		   
   }
   
   
   // Send message with online user and verify the user and chat in Qucik Chat - recent messages 
   public void validateRecentChat() throws Exception{	   
	   List<WebElement> users = driver().findElements(By.cssSelector(usersList));
	   System.out.println("No of users "+users.size());
	   for (WebElement userList : users){		   
		   String expUserName = userList.getText(); 
		   System.out.println("Name are as : "+expUserName);
			   userList.click();
			   sendMsg(chatMessageText1);
			   back2Office();
			   expandNdSelectTypeOfChat("Recent Chats");
			   chatUsers(expUserName);	
			   validateChatMsg(chatMessageText1);
			   break;			  	   
	   }   
	   
   }
   
   /*public void selectRecentChatUser() throws Exception{	   
	   List<WebElement> users = driver().findElements(By.cssSelector(usersList));
	   System.out.println("No of users "+users.size());
	   for (WebElement userList : users){		   
		   String expUserName = userList.getText(); 
		   System.out.println("Name are as : "+expUserName);
			   userList.click();
			   sendMsg(chatMessageText1);
			   back2Office();
			   expandNdSelectTypeOfChat("Recent Chats");
			   chatUsers(expUserName);	
			   validateChatMsg(chatMessageText1);
			   break;			  	   
	   }   
	   
   }*/
   
    public boolean validateChatMsg(String message){
		boolean isMsgPresent = false;
		List <WebElement> msg = driver().findElements(By.cssSelector(chatMsgs));
		System.out.println("msg size"+ msg.size());
		for (WebElement msgs : msg){			
			if (msgs.getText().equalsIgnoreCase(message)){
				isMsgPresent=true;
				clickOnElement("cssSelector", chatCross);
				clickOnElement("cssSelector", quickChat);
				break;			
			}
		}if(isMsgPresent==false){
			Assert.assertTrue(isMsgPresent, message + " text message is not found in chat.");
		}		
		return isMsgPresent;
    	
    }
   
  
   
     
   
   public void validateErrorsOfNewMessage() throws Exception{
	  logInfo("Send Message without recipient And capture error message");
	  try{
		   selectNewMessageButton();	  
		   enterMsgInNewMessage(chatMessageText1);
		   sendMessage();
		   confirmationMessage("Please select a chat recipient.");
		   closeChatMessage();
   		}catch (Exception e){
   			logger.error("Failed!!  No error message is populated for chat recipient.");
	   }
	   
	   logInfo("Send Message without Text And capture error message"); 
	   try{
		   selectNewMessageButton();
		   enterRecipientID();
		   clearMsgInTextArea();   
		   sendMessage();
		   confirmationMessage("Please enter a message.");
	   }catch (Exception e){
		   logger.error("Failed!!  No error message is populated for blank text area.");
	   }
	   
   }
   
   public void SendMessageToAnyOne() throws Exception{
	   logInfo("Select New Message and send Text for required User");
	   selectNewMessageButton();
	   enterRecipientID();
	   enterMsgInNewMessage(chatMessageText1);
	   sendMessage();
   }
   
   public void  selectNewMessageButton() {
	   try{
		   clickOnElement("cssSelector", newMessage);
	   } 
	   catch (WebDriverException we){
		   logger.error("Failed!! Not able to clicked on "+ newMessage);
	   } 

   }
   
   public void enterRecipientID() throws Exception{
	   try{
		   Robot rb = new Robot();
		   inputText("cssSelector", chatRecipientsTo, "Ramesh Buridi");
		   Thread.sleep(5000);
	   }
	   catch (NoSuchElementException nse){
		   logger.error("Failed!! No able to enter Recipient ID");
	   }
   }
   
   public void enterMsgInNewMessage(String enterText) throws Exception{
	   try{
		   inputText("cssSelector", textArea, enterText);
	   }
	   catch (NoSuchElementException nse){
		   logger.error("Failed!! Not able to enter Text");
	   }
 
   }
   
   public void clearMsgInTextArea() throws Exception{
	   try{
		   inputTextClear("cssSelector", textArea);
	   }catch (NoSuchElementException nse){
		   logger.error("Failed!! Not able to enter Text");
		   
	   }
   }
   
   
   public void  sendMessage() throws Exception{
	   try{
		   clickOnButton("cssSelector", sendMessage);
	   }catch (NoSuchElementException nse){
		   logger.error("Failed!! Not able to select Send button");
		   
	   }
   }
   
   public void  closeChatMessage() throws Exception{
	   try{
		   Thread.sleep(10000);
		   clickOnButton("cssSelector", closeChat);
	   }catch (NoSuchElementException nse){
		   logger.error("Failed!! Not able to close Chat window");
		   
	   }
   }
   
   public void selectFilterOption(String enterFilter) throws Exception{
		logInfo(" Selecting Online people from Filter and trying to chat .");	
		clickOnButton("cssSelector", filter);
		List<WebElement> filterList = driver().findElements(By.cssSelector(chatFilterDropDown));	
		System.out.println("Options available in filter List : "+filterList.size());
		try{
			for (WebElement lists :filterList ) {
				System.out.println("Fliter Options  are : "+lists.getText());				
				if(lists.getText().equals(enterFilter)){
				/*	Actions act = new Actions(driver);
						
						act.clickAndHold().doubleClick();//doubleClick();
*/						lists.click();
					break;
		
				}}}catch (NoSuchElementException  e){      		
      		logger.error("Online Option is not avaible in the Filter List.");
      	}   
   }	
   
   
     
     public void sendMsgFomChatBox(String enterText) throws Exception{  
    	 try{   		
    		 
    		 waitOnElement("cssSelector", qcTextMsg);
    		 inputTextClear("cssSelector", qcTextMsg);
    	     inputText("cssSelector", qcTextMsg, enterText);
    		 clickOnButton("cssSelector", qcSend);
    		 
    	  }
    	 catch (Exception e){
    		 System.err.println("Failed !! Not able to Enter text in Chat Message");
    	 }
    	 Thread.sleep(5000);
	}
     
     public void sendMsg(String enterText) throws Exception{  
    	 try{
    		 waitOnElement("cssSelector", chatTextAreaField);  		
    		 inputText("cssSelector", chatTextAreaField, enterText);
    		 Thread.sleep(2000);
    		 clickOnButton("cssSelector", chatSendBtn);
    		 
    	  }
    	 catch (Exception e){
    		 System.err.println("Failed !! Not able to Enter text in Chat Message");
    	 }
    	 Thread.sleep(5000);
	}
     
     
     
     public void addfiles(String resourceTitle) throws Exception{  
    	 
    	 clickOnElement("cssSelector", chatResourceIcon);
    	 clickOnLink("linkText", "Newest Resources");
	     comm.resourceListWindow(resourceTitle);
	     Thread.sleep(4000);
    	 clickOnButton("xpath", qcSave);
	     Thread.sleep(2000);
	     inputTextClear("cssSelector", qcTextMsg);
	     inputText("cssSelector", qcTextMsg, chatMessageText1);
		 clickOnButton("cssSelector", qcSend); 	 
    	 
	}
     
 public void addToRemovefiles(String resourceTitle) throws Exception{  
    	 
    	 clickOnElement("cssSelector", chatResourceIcon);
    	 clickOnLink("linkText", "Newest Resources");
	     comm.resourceListWindow(resourceTitle);
	     Thread.sleep(4000);
    	 clickOnButton("xpath", qcSave);
	     Thread.sleep(2000);
	     inputTextClear("cssSelector", qcTextMsg);
	     inputText("cssSelector", qcTextMsg, chatMessageText1);
	     Thread.sleep(2000);
		 clickOnElement("csSelector", attachToggle);
		 Thread.sleep(1000);
		 clickOnElement ("cssSelector", removAttach);
		 
    	 
	}
     
     public boolean isAttachedPresentInChatWindow() throws InterruptedException{
    	 Thread.sleep(3000);
    	 boolean isattached= false;
    	 List <WebElement> at = driver().findElements(By.cssSelector(qcAttach));
    	 System.out.println("Size is "+ at.size());
    	 if(at.size()==0){
    		 isattached = false;
    		 Assert.assertTrue(isattached, "No attachment is not added");
    	 }else{
    	 for (WebElement attach : at){
    		 System.out.println(attach.getText());
    		 
    	 }}
		return isattached;
    	 
    	 
     }
   
    public boolean isNewMessagePresent(){    	  
		return getNewMessageTitle().matches(newMessageText);
    }
   
    public boolean isChatTitlePresent(){    	  
    	return getChatTitle().matches(chatTitleText);
    }
    
    public boolean isFilterByPresent(){    	  
    	return getFilterByTitle().matches(filterByText);
    }

    public String getChatTitle (){			
		return driver().findElement(By.cssSelector(chatPanelTitle)).getText();			
	}
		
	public String getNewMessageTitle (){	
		WebElement msg = driver().findElement(By.cssSelector(newMessage));
		System.out.println("mesage is "+msg.getText());
		return driver().findElement(By.cssSelector(newMessage)).getText();
	}	
	
	public String getFilterByTitle (){			
		return driver().findElement(By.cssSelector(filter)).getText();			
	}

	public void go2People(){
		logInfo("inside go2People() method.");
		driver().navigate().to(appUrl + "community/wall/people");
	}
		
	public void searchUser(String userName) throws Exception{
		logInfo("inside searchUser() method.");
		waitOnElement("cssSelector",search);
		inputText("cssSelector",search,userName + " ");
		
		clickOnElement("xpath",btnSearchPeople);
	}
	
	public void searchChatUser(String userName) throws Exception{
		logInfo("inside searchChatUser() method.");
		waitOnElement("cssSelector",chatSearch);
		inputText("cssSelector",chatSearch,userName + " ");
		submitElement("cssSelector",chatSearch);
		
	}
	
	
		
	public boolean verifyIfUserFound(String userName) throws Exception{
		logInfo("inside verifyIfUserFound() method.");
		WebElement people = driver().findElement(By.xpath(panelListPeople));
		List allPeoples = people.findElements(By.xpath("//*[@class='col-md-4 col-sm-6 col-xs-12']"));
		System.out.println("All Peoples = " + allPeoples.size());
		
		String before_name = "//*[@id='community-tabs']/div/div[2]/div/div/div[";
		String after_name = "]/div/div[2]/h4";
		
	//	String before_invite = "//*[@id='people_list']/div/div/div[";
	//	String after_invite = "]/div/div[3]/div/span/a/span";
		
	//	String before_invite = "//*[@id='community-tabs']/div/div[2]/div/div/div[";
	//	String after_invite = "]/div/div[3]/div/span/a/span";
		
		boolean isUserFound = false;
		if(allPeoples.size()>0){
		for(int i=1;i<=allPeoples.size();i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String peopleName = x.getText().trim();
			String uName = userName.trim();
			String[] parts = StringUtils.split(userName,".");   
			String fName = parts[0];
			String lName = parts[1];
			String name = fName + " " + lName;
			System.out.println("Name = " +name );
			if(peopleName.equalsIgnoreCase(name.trim())){
				logInfo(userName + " match found in the search people pane");
				isUserFound = true;
				/* WebElement e = driver().findElement(By.xpath(before_invite+i+after_invite));
				e.click();
				Thread.sleep(3000);
				confirmEventDeleteModal(); */
				break;
			}
		  }
		}
		
		if(isUserFound==false){
			logInfo(userName + " match NOT found in the search people pane");
			System.out.println(userName + " match NOT found in the search people pane");
		}
		
		return isUserFound;
	}
	
	
	public boolean isUserPresent(String userName){
		logInfo("inside isUserPresent() method.");
		
		boolean isUserFound = false;
		List <WebElement> userList = driver().findElements(By.cssSelector(users));
		 for (WebElement user:userList ){	
			 System.out.println(user.getText());			 
			if (user.getText().contains(userName)){
				isUserFound= true;
				break;			
			}	
			
		}if (isUserFound==false){
			Assert.assertTrue(isUserFound, userName +" - user is  not available");
		}		
		return isUserFound;	
	}
	
	public void sendChatMessage(String userName, String message) throws Exception{
		logInfo("inside isUserPresent() method.");
		
		boolean isUserFound = false;
		List <WebElement> userList = driver().findElements(By.cssSelector(users));
		for (WebElement user:userList ){
			System.out.println(user.getText());
			
			/*
		for (int i=5; i<=(userList.size()+5)*2; i++){
			WebElement user= driver().findElement(By.cssSelector(usersBfr+i+usersAfr));		*/	
			if (user.getText().contains(userName)){
				isUserFound= true;
				user.click();
				sendMsg(message);				
				break;				
				
			}
			
		}if (isUserFound==false){
			Assert.assertTrue(isUserFound, userName +" - user is  not available");
		}		
	}
	
	 public void validateMsgInChat(String expectedMsg){
		 logInfo("Entered into  validateMsgInChat() method.");
		 
		 boolean isMsgPresent = false;		 
		 List <WebElement> msgLi = driver().findElements(By.cssSelector(msgList));
		 System.out.println(" msg list"+ msgLi.size());
		 for (WebElement list : msgLi){
			 System.out.println("Msg is "+list.getText());
			 if (list.getText().contains(expectedMsg)){
				 isMsgPresent =true;
				 }
			 
		 }if (isMsgPresent ==false){
			 Assert.assertTrue(isMsgPresent, expectedMsg + " is not present in Chat Text Area.");
		 }
		 
	 }
	 
	
	
	
	
	public void inviteUser2Chat(String userName) throws Exception{
		logInfo("inside inviteUser2Chat() method.");
		
		WebElement people = driver().findElement(By.xpath(panelListPeople));
		List allPeoples = people.findElements(By.xpath("//*[@class='col-md-4 col-sm-6 col-xs-12']"));
		System.out.println("All Peoples = " + allPeoples.size());
		
		String before_name = "//*[@id='community-tabs']/div/div[2]/div/div/div[";
		String after_name = "]/div/div[2]/h4";
		
	//	String before_invite = "//*[@id='people_list']/div/div/div[";
	//	String after_invite = "]/div/div[3]/div/span/a/span";
		
		String before_invite = "//*[@id='community-tabs']/div/div[2]/div/div/div[";
		String after_invite = "]/div/div[3]/div/span/a/span";
		
		if(allPeoples.size()>0){
			boolean isUserFound = false;
			if(allPeoples.size()>0){
			for(int i=1;i<=allPeoples.size();i++){
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String peopleName = x.getText().trim();
				String uName = userName.trim();
				String[] parts = StringUtils.split(userName,".");   
				String fName = parts[0];
				String lName = parts[1];
				String name = fName + " " + lName;
				System.out.println("Name = " +name );
				if(peopleName.equalsIgnoreCase(name.trim())){
					logInfo(userName + " match found in the search people pane");
					isUserFound = true;
					WebElement e = driver().findElement(By.xpath(before_invite+i+after_invite));
					e.click();
					Thread.sleep(3000);
					 confirmOK();
					break;
				}
			  }
			}
		
			if(isUserFound==false){
				logInfo(userName + " match NOT found in the search people pane");
				System.out.println(userName + " match NOT found in the search people pane");
			}
		}
	}
	
	
	
	public void acceptChatRequest(String userName) throws Exception{
		logInfo("inside acceptChatRequest() method.");
		WebElement people = driver().findElement(By.xpath(panelListPeople));
		List allPeoples = people.findElements(By.xpath("//*[@class='col-md-4 col-sm-6 col-xs-12']"));
		System.out.println("All Peoples = " + allPeoples.size());
		
		//String before_name = "//*[@id='people_list']/div/div/div[";
		// String after_name = "]/div/div[2]/h4";
		
		String before_invite = "//*[@id='people_list']/div/div/div[";
		// String after_invite = "]/div/div[3]/div/span/a/span/i";
		String after_invite = "]/div/div[3]/div/span/a";
		
		if(allPeoples.size()>0){
		for(int i=1;i<=allPeoples.size();i++){
			
				WebElement inv = driver().findElement(By.xpath(before_invite+i+after_invite));
				waitOnElement("xpath",before_invite+i+after_invite);
				inv.click();
				Thread.sleep(3000);
				confirmOK();
				break;
			}
		}
	}
	
/*  // Orignal commented - use this original 
 * 

	public boolean verifyIfUserFound(String userName) throws Exception{
		logInfo("inside verifyIfUserFound() method.");
		
		WebElement people = driver().findElement(By.xpath(panelListPeople));
		List allPeoples = people.findElements(By.xpath("//*[@class='col-md-4 col-sm-6 col-xs-12']"));
		System.out.println("All Peoples = " + allPeoples.size());
		
		String before_name = "//*[@id='people_list']/div/div/div[";
		String after_name = "]/div/div[2]/h4";
		
		String before_invite = "//*[@id='people_list']/div/div/div[";
		String after_invite = "]/div/div[3]/div/span/a/span";
		
		boolean isUserFound = false;
		for(int i=1;i<=allPeoples.size();i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String peopleName = x.getText().trim();
			String uName = userName.trim();
			String[] parts = StringUtils.split(userName,".");   
			String fName = parts[0];
			String lName = parts[1];
			String name = fName + " " + lName;
			if(peopleName.equalsIgnoreCase(name.trim())){
				logInfo("matching user name = " +userName);
				isUserFound = true;
				break;
			}
		}
		
		if(isUserFound==false){
			logInfo(userName + " could not find the user");
			System.out.println(userName + " could not find the user");
		}
		return isUserFound;
	}
	

	
	public void inviteUser2Chat(String userName) throws Exception{
		logInfo("inside inviteUser2Chat() method.");
		
		WebElement people = driver().findElement(By.xpath(panelListPeople));
		List allPeoples = people.findElements(By.xpath("//*[@class='col-md-4 col-sm-6 col-xs-12']"));
		System.out.println("All Peoples = " + allPeoples.size());
		
		String before_name = "//*[@id='people_list']/div/div/div[";
		String after_name = "]/div/div[2]/h4";
		
		String before_invite = "//*[@id='people_list']/div/div/div[";
		String after_invite = "]/div/div[3]/div/span/a/span";
		
		for(int i=1;i<=allPeoples.size();i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String peopleName = x.getText().trim();
			String uName = userName.trim();
			String[] parts = StringUtils.split(userName,".");   
			String fName = parts[0];
			String lName = parts[1];
			String name = fName + " " + lName;
			if(peopleName.equalsIgnoreCase(name.trim())){
				logInfo("matching user name = " +userName);
				WebElement inv = driver().findElement(By.xpath(before_invite+i+after_invite));
				inv.click();
				Thread.sleep(3000);
				confirmEventDeleteModal();
				break;
			}
		}
		
	}
	

	
	public void acceptChatRequest(String userName) throws Exception{
		logInfo("inside acceptChatRequest() method.");
		
		WebElement people = driver().findElement(By.xpath(panelListPeople));
		List allPeoples = people.findElements(By.xpath("//*[@class='col-md-4 col-sm-6 col-xs-12']"));
		System.out.println("All Peoples = " + allPeoples.size());
		
		String before_name = "//*[@id='people_list']/div/div/div[";
		String after_name = "]/div/div[2]/h4";
		
		String before_invite = "//*[@id='people_list']/div/div/div[";
		String after_invite = "]/div/div[3]/div/span/a/span/i";
		
		for(int i=1;i<=allPeoples.size();i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String peopleName = x.getText().trim();
			String uName = userName.trim();
			String[] parts = StringUtils.split(userName,".");   
			String fName = parts[0];
			String lName = parts[1];
			String name = fName + " " + lName;
		
			if(peopleName.equalsIgnoreCase(name.trim())){
				logInfo("matching user name = " +userName);
				WebElement inv = driver().findElement(By.xpath(before_invite+i+after_invite));
				inv.click();
				Thread.sleep(3000);
				confirmEventDeleteModal();
				break;
			}
		}
	}
	
*/	
	
	public void go2ChatPage(){
		logInfo("inside go2ChatPage() method.");
		driver().navigate().to(appUrl + "community/chat_channels");
	}
	
	public boolean verifyUserPresentInChatPage(String userName) throws Exception{
		logInfo("inside verifyUserPresentInChatPage() method.");
		String uName = userName.trim();
		String[] parts = StringUtils.split(userName,".");   
		String fName = parts[0];
		String lName = parts[1];
		String exp_name = fName + " " + lName;
		System.out.println("Exp Name = " +exp_name);
		
		WebElement chatPanel = driver().findElement(By.xpath(panelChatUsers));
		List allUsers = chatPanel.findElements(By.className("user-name"));
		System.out.println("Total users = " +allUsers.size() );
		
		boolean isChatUserFound = false;
		String before_name = "//*[@id='chat-summaries']/div/div[";
		String after_name = "]/div[2]/div";
		
		if(allUsers.size()>=0){
		for(int i=1; i<=allUsers.size(); i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			System.out.println("Name = " +name);
			if(name.equalsIgnoreCase(exp_name)){
				isChatUserFound = true;
				x.click();
				logInfo(exp_name + " match found in chat page.");
			}
		  }
		}
		
		if(isChatUserFound==false){
			logInfo(exp_name + " match not found in chat page.");
		}
		
		return isChatUserFound;
	}
	
	
	public void sendMessage2ChatUser(String msg) throws Exception{
		logInfo("inside sendMessage2ChatUser() method.");
		inputTextClear("xpath",inputChatArea);
		inputText("xpath",inputChatArea,msg);
		clickOnLink("linkText","Send");
		Thread.sleep(3000);
		driver().navigate().refresh();
	}
	
	
	public void verifyMessageSent2User(String userName, String expMsg) throws Exception{
		logInfo("inside verifyMessageSent() method.");
		String uName = userName.trim();
		String[] parts = StringUtils.split(userName,".");   
		String fName = parts[0];
		String lName = parts[1];
		String exp_name = fName + " " + lName;
		System.out.println("Exp Name = " +exp_name);
		
		WebElement chatPanel = driver().findElement(By.xpath(panelChatUsers));
		List allUsers = chatPanel.findElements(By.className("user-name"));
		System.out.println("Total users = " +allUsers.size() );
		
		boolean isChatUserFound = false;
		boolean isChatMessgFound = false;
		String before_name = "//*[@id='chat-summaries']/div/div[";
		String after_name = "]/div[2]/div";
		
		String before_msg = "//*[@id='chat-summaries']/div/div[";
		String after_msg = "]/div[2]/div[2]";
		
		if(allUsers.size()>=0){
		for(int i=1; i<=allUsers.size(); i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			System.out.println("Name = " +name);
			if(name.equalsIgnoreCase(exp_name)){
				isChatUserFound = true;
				WebElement y = driver().findElement(By.xpath(before_msg+i+after_msg));
				String msg = y.getText().trim();
				if(msg.equalsIgnoreCase(expMsg)){
					logInfo(expMsg + " message found in chat page.");
					isChatMessgFound = true;
					break;
				}
			}
		  }
		}
		
		if(isChatUserFound==false){
			logInfo(exp_name + " user not found in chat page.");
		}
		if(isChatMessgFound==false){
			logInfo(expMsg + " message not found in chat page.");
		}
	}
	
	public void  updateCommunityProfile(String userName) throws Exception{
		logInfo("inside updateCommunityProfile() method.");
		String uName = userName.trim();
		String[] parts = StringUtils.split(userName,".");   
		String fName = parts[0];
		String lName = parts[1];
		String name = fName + " " + lName;
		back2Office();
		clickOnElement("xpath", eleDrpdnProfile);
		clickOnLink("linkText", "Account");
		waitOnElement("linkText","Community Profile");
		clickOnLink("linkText","Community Profile");

	}
	
	// Select options of Filter under chat users
	public void selectChatOptions(String toggleType) throws Exception{
		 logInfo("inside selectChatOptions(String toggleType) Method");
		 
		 clickOnElement("cssSelector", filterBy);
		 boolean istogglePresent = false;		 
		 List <WebElement> lit = driver().findElements(By.cssSelector(filterOptions));
		 System.out.println(lit.size()+ " size is ");
		 for (WebElement list :lit){
			 System.out.println("List is "+ list.getText());
			 if (list.getText().trim().equalsIgnoreCase(toggleType)){
				 istogglePresent = true;
				 list.click();
				 Thread.sleep(2000);
				 clickOnElement("cssSelector", filterBy);
				 Thread.sleep(2000);
				 break;	 
			 }			 
			 
		 }if (istogglePresent==false){
			 Assert.assertEquals(istogglePresent, toggleType+ " is not found in the toggle list");
			 
		 }		 
	 }	
	
	public void selectChatOptionsInQuickChat(String toggleType) throws Exception{
		 logInfo("inside selectChatOptionsInQuickChat(String toggleType) Method");
		 
		 clickOnElement("cssSelector", filterByInQC);
		 boolean istogglePresent = false;		 
		 List <WebElement> lit = driver().findElements(By.cssSelector(filterOptions));
		 System.out.println(lit.size()+ " size is ");
		 for (WebElement list :lit){
			 System.out.println("List is "+ list.getText());
			 if (list.getText().trim().equalsIgnoreCase(toggleType)){
				 istogglePresent = true;
				 list.click();
				 Thread.sleep(2000);
				 clickOnElement("cssSelector", filterByInQC);
				 Thread.sleep(4000);
				 break;	 
			 }			 
			 
		 }if (istogglePresent==false){
			 Assert.assertEquals(istogglePresent, toggleType+ " is not found in the toggle list");
			 
		 }		 
	 }	
	
	
	public void validateQuickChat() throws Exception{
		
		String qctext = "Quick Chat";
		WebElement qc = driver().findElement(By.cssSelector(quickChat));		
		Assert.assertEquals(qc.getText(), qctext);
		waitOnElement("cssSelector", quickChat);
	}
	
	
	// select Quick chat + icon and select appropriate chat type link.
	public boolean expandNdSelectTypeOfChat(String typeChat) throws Exception{
		logInfo("expandNdSelectTypeOfChat method" );	
		boolean isTypePresent = false;
		waitOnElement("cssSelector", chatPlus);
		clickOnElement("cssSelector", chatPlus);
		waitOnElement("cssSelector", chatType);	
		List <WebElement> types = driver().findElements(By.cssSelector(chatType));
		System.out.println("Types size is "+ types.size());
		for (WebElement allType : types){			
			if (allType.getText().contains(typeChat)){	
				isTypePresent= true;
				System.out.println("Types are "+ allType.getText());
				allType.click();
				break;
				}			
		}if (isTypePresent==false)	{
			Assert.assertTrue(isTypePresent, typeChat + " is not matched");
		}
		return isTypePresent;
		
	}
	
	  public boolean chatUsers(String chatUser) throws Exception, Exception{
		  boolean isUserPresent = false;
		  waitOnElement("cssSelector", chatUsersList);
		  List <WebElement> user =  driver().findElements(By.cssSelector(chatUsersList));
		  System.out.println("Users size"+ user.size());
		  for (WebElement users : user){	
			  System.out.println("Usersr are "+ users.getText());
			  if (users.getText().contains( chatUser)){
				  System.out.println("U "+ users.getText());
				  isUserPresent = true;				  
				  users.click();
				  break;				  
			  }			  
		  }if (isUserPresent==false){
			  Assert.assertTrue(isUserPresent, chatUser + " user is not found");
		  }	  
		return isUserPresent;
		  
	  }
	  
	  
	  
	  public void pendingIncomingList(String requestType, String chatUser) throws Exception{	
		  boolean isReqPresent= false;
		  List <WebElement> req = driver().findElements(By.cssSelector(qcChatReq));
		  System.out.println(req.size());
		  for (WebElement request : req){			  
			  System.out.println("list is "+ request.getText());	
			  if (request.getText().contains(requestType)){
				  isReqPresent=true;
				  incomingChatUsers(chatUser);
				  break;
				  
			  }
		  }if(isReqPresent==false){
			  Assert.assertTrue(isReqPresent,requestType+ " type is not found" );
		  }		  
	  }
	  
	  public void pendingOutgoingList(String requestType, String chatUser) throws Exception{	
		  boolean isReqPresent= false;
		  List <WebElement> req = driver().findElements(By.cssSelector(qcChatReq));
		  System.out.println(req.size());
		  for (WebElement request : req){			  
			  System.out.println("list is "+ request.getText());	
			  if (request.getText().contains(requestType)){
				  isReqPresent=true;
				  outgoingChatUsers(chatUser);
				  break;
				  
			  }
		  }if(isReqPresent==false){
			  Assert.assertTrue(isReqPresent,requestType+ " type is not found" );
		  }		  
	  }
	  
	  public boolean incomingChatUsers(String chatUser){
		  boolean isUserPresent = false;
		  List <WebElement> user =  driver().findElements(By.cssSelector(qcIncomUsers));
		  System.out.println("Users size"+ user.size());
		  for (WebElement users : user){	
			  System.out.println("Usersr are "+ users.getText());
			  if (users.getText().contains( chatUser)){
				  System.out.println("Usersr are "+ users.getText());
				  isUserPresent = true;				  
				  users.click();
				  break;				  
			  }			  
		  }if (isUserPresent==false){
			  Assert.assertTrue(isUserPresent, chatUser + " user is not found");
		  }	  
		return isUserPresent;
		  
	  }
	  
	  public boolean outgoingChatUsers(String chatUser){
		  boolean isUserPresent = false;
		  List <WebElement> user =  driver().findElements(By.cssSelector(qcOutGoingUsers));
		  System.out.println("Users size"+ user.size());
		  for (WebElement users : user){	
			  System.out.println("Usersr are "+ users.getText());
			  if (users.getText().contains( chatUser)){
				  System.out.println("Usersr are "+ users.getText());
				  isUserPresent = true;				  
				  users.click();
				  break;				  
			  }			  
		  }if (isUserPresent==false){
			  Assert.assertTrue(isUserPresent, chatUser + " user is not found");
		  }	  
		return isUserPresent;
		  
	  }
	  
	  
	  public void verifyChatRquests(){
		  
		  List <WebElement> cr = driver().findElements(By.cssSelector(req));
		  System.out.println("request size" + cr.size());
		  for (WebElement crs : cr){
			  String reqText = crs.getText().trim();
			  switch (reqText){
			  case "Reject Chat Request":
				  System.out.println("Match found : "+reqText);
				  break;
			  case "Accept Chat Request":
				  System.out.println("Match found : "+reqText);	
				  break;
			  case "Cancel Chat Invitation":
				  System.out.println("Match found : "+reqText);	
				  break;			 	  
			  default :
				  Assert.assertEquals(null, reqText+ " is unecessarily dispalyed.");		  
			  
			  }		  
		  }
		  
	  }
	  
	  // Send message to user who has not accepted invite to chat user.  
	  public void sendMsgToNotAcceptedUser() throws Exception{
		  
		  	 Thread.sleep(2000);
		     inputTextClear("cssSelector", qcTextMsg);
		     inputText("cssSelector", qcTextMsg, chatMessageText1);
			 clickOnButton("cssSelector", qcSend); 	
			 confirmationMessage("Cannot chat with this user.");		  
		  
	  }
	  
	  // Select QuickChat and Send send message to group of users
	  public void grpChat(String recepients, String recepients2, String message) throws Exception{
		  logInfo("Entered into grpChat() method");
		  Robot rb = new Robot();
		  clickOnElement("cssSelector", chatPlus);
		  clickOnElement("cssSelector", msgChatPlus);
		  inputText("cssSelector",recipientsTo, recepients);		  
		  Thread.sleep(3000);
		  rb.keyPress(KeyEvent.VK_TAB);
		  rb.keyRelease(KeyEvent.VK_TAB);
		  Thread.sleep(2000);
	      inputTextClear("cssSelector", qcTextMsg);
	      inputText("cssSelector", qcTextMsg, message);
 		  inputText("cssSelector",recipientsTo, recepients2);		  
		  Thread.sleep(3000);
		  rb.keyPress(KeyEvent.VK_TAB);
		  rb.keyRelease(KeyEvent.VK_TAB);
		  clickOnButton("cssSelector", qcSend); 
		  Thread.sleep(2000);
		  clickOnElement("cssSelector", chatCross);
			  
	  }
	  
	  
	  
	  public void addUserToChat(String uName, String email) throws Exception {
			logInfo("inside addUserToChat() method.");
			clickOnLink("linkText", "New Admin User");			
			waitOnElement("cssSelector", inputFirstName);
			inputText("cssSelector", inputFirstName, uName);
			inputText("cssSelector", inputLastName, txtLastName);
			inputText("xpath", inputUserEmail, email);
			inputText("xpath", inputUserName, uName);
			inputText("xpath", inputUserPassword, userPassword_text);
			inputText("xpath", inputUserPasswdConfirm, userPassword_text);
			selectFromDropdown("xpath", drpdnUserSecurityGrps, "byVisibleText", userSecurityGrps_text);
			clickOnElement("xpath", btnCreateUser);
			Thread.sleep(5000);

		}
	  
	  
	  
	  // in quick chat , selects user from Recent Chats list
	  public void selectUserFromRecentChats(String chatter) throws Exception{
		  logInfo("Entered into selectUserFromRecentChats() method");
		  waitOnElement("cssSelector", usersLis);
		  boolean isUser = false;
		  List <WebElement> usr = driver().findElements(By.cssSelector(usersLis));
				  System.out.println("Size of users is "+ usr.size());
			if (usr.size()==0){
				Assert.assertNotEquals(usr.size(), 0);
			}else{
		  for (int i=1; i<=usr.size(); i++){
			  WebElement usrs = driver().findElement(By.cssSelector(usersLisBfr+i+usersLisAft));
			  String chatUser = usrs.getText().trim();
			  if(chatUser.contains(chatter)){
				  isUser=true;
				  usrs.click();			  
				  break;
				  
			  }
			  
			  
		  }if (isUser==false){
			  Assert.assertTrue(isUser,chatter+ " -chatuser is not found");
		  }
		  
			}  
		  
		  
	  }
	  
	  
	 
	  
	  
	  
	  
	  
	  
	
}
