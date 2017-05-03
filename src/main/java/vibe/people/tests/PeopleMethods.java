package vibe.people.tests;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import common.TestBase;


public class PeopleMethods extends TestBase{	
	
	
	public void go2PeoplePage() throws Exception{
		logInfo("inside go2PeoplePage() method ");
		clickOnLink("linkText", "Community");
		implicityWaits(10);
		clickOnLink("linkText", peopleTab);		
		implicityWaits(20);
	}
	

	public void searchUser(String name ) throws Exception{
		logInfo("inside searchUser() method ");
		inputTextClear("cssSelector", search);
		inputText("cssSelector", search, name);
		clickOnElement("cssSelector", search);
		/*clickOnButton("cssSelector", searchIcon);	*/
		Thread.sleep(5000);
	}
	
	public void peopleList_base () throws Exception{
		logInfo("inside peopleList_base() method ");
		WebElement people = driver().findElement(By.cssSelector(peopleFrame));
		List<WebElement> peopleList = driver().findElements(By.cssSelector(peopleFrame));
		Thread.sleep(1000);
		for (int i=1 ; i <= peopleList.size(); i++){
			 WebElement peopleLists = driver().findElement(By.xpath(peopleList1+i+peopleList2));
			 System.out.println(i+ " is Captured People name  : "+peopleLists.getText());
			//accountLinks.click();
			implicityWaits(30);
		}
		logInfo("Successfully!! verified people informations***************");
    }
		
		
	public boolean verifyPeopleList(String person) throws Exception, Exception{
		logInfo("trying to fetch people based on WILD Card search.  ");
	    boolean isPeopleFound = false;
	    Thread.sleep(3000);
	    waitOnElement("cssSelector", peopleFrame);
		List<WebElement> peopleList = driver().findElements(By.cssSelector(peopleFrame));
		System.out.println(peopleList.size());
		for (WebElement pl : peopleList ) {					
		System.out.println(pl.getText());
			if (pl.getText().contains(person)){				
				isPeopleFound = true;
				System.out.println("matched "+pl.getText());
				break;
			} 
		}
		
		return isPeopleFound;	
		
	}	
	
	public void retrieveFilterAndSort() throws Exception{
		logInfo("info retrieveFilterAndSort() method");
		clickOnButton("cssSelector", filter);	
		List<WebElement> filterList = driver().findElements(By.cssSelector(filterDown));
		System.out.println("Options available in filter List : "+filterList.size())	;
		for (WebElement lists :filterList ) {
		System.out.println("Fliter Options  are : "+lists.getText());
		Reporter.log("Fliter Options  are : "+lists.getText());
		}
		Thread.sleep(5000);
		logInfo(" Sort lista as follows:");
		clickOnButton("cssSelector", filter);
		clickOnButton("cssSelector", sort);	
		List<WebElement> sortList = driver().findElements(By.cssSelector(sortDown));
		System.out.println("Options available in Sort List : "+sortList.size())	;
		for (WebElement sortLists :sortList ) {
		System.out.println("Sort Options  are : "+sortLists.getText());
		Reporter.log("Sort Options  are : "+sortLists.getText());
		}
		clickOnButton("cssSelector", sort);	
		logInfo("***********Sucessfully!! Retrived Both *************");
	}
	
	/*
	public void setChatFilter(String peopleFilter, String trendingFilter) throws Exception{
		logInfo("inside setChatFilter() method.");	
		waitOnElement("cssSelector", filter);
		clickOnElement("cssSelector", filter);
		clickOnElement("cssSelector", sort);
		String drpdownFilter = "div#filter_container > ul.dropdown-menu.right-menu";
		selectFromDropdown("cssSelector", drpdownFilter, "byVisibleText", peopleFilter);
		boolean isItemsFound = false;
		List<WebElement> allItems = driver().findElements(By.cssSelector("div#filter_container > ul.dropdown-menu.right-menu > li.dropdown-option"));
		System.out.println("Total Items = " + allItems.size());
		Thread.sleep(3000);
		for (WebElement li : allItems ) {
			System.out.println("---------------------4");
			System.out.println(li.getText().trim());
			System.out.println("---------------------5");
			//li.click();
			if (li.getText().contains(message)){
				isItemsFound = true;
				System.out.println(li.getText().contains(message));
				break;
			} 
		}
	}*/
	
	public void chatPeople() throws Exception{
		logInfo("inside chatPeople() method.");	
		if(driver().findElement(By.cssSelector(inviteToChat)).getText().equals("Invite To Chat")){		
			clickOnButton("cssSelector", inviteToChat);
			Thread.sleep(4000);
			getText("xpath", inviteOkButton, "Text");
			clickOnElement("cssSelector", inviteOkButton);			
			//confirmToDeleteModalbox();
			//confirmEventDeleteModal();
			}	
		 else {
			// clickOnLink("xpath", chat);	
			 sendMessage2ChatUser(chatString);	
		}
			
	}
		
	
	public void sendMessage2ChatUser(String message) throws Exception{	
		logInfo("inside chatMessenger() method.");	
		waitOnElement("xpath", chat);	
		clickOnLink("xpath", chat);	
		
		waitOnElement("cssSelector", chatTextArea);
		inputText("cssSelector", chatTextArea, message);
		clickOnElement("cssSelector", chatSend);
				
		clickOnElement("cssSelector", chatClose);		
		logInfo("Succeed! Chatted with Online People");
	}
		
		
	public boolean verifyChatMessage(String message) throws Exception{
		logInfo("inside verifyChatMessage() method.");
		boolean isMessageFound = false;
		waitOnElement("xpath", chat);	
		clickOnLink("xpath", chat);	
		waitOnElement("cssSelector", ".chat-messages");
	
		List<WebElement> allMessages = driver().findElements(By.cssSelector("li > div.media.photo >  div.media-body > .message.current-user"));
		for (WebElement pl : allMessages ) {			
			System.out.println(pl.getText());		
		
			if (pl.getText().contains(message)){
				isMessageFound = true;
				System.out.println(pl.getText().contains(message));
				break;
			} 
		}
		return isMessageFound;
	}
		
	public void followUser() throws Exception{
		logInfo("inside followUser() method.");
		waitOnElement("cssSelector",follow);
		WebElement efollow = driver().findElement(By.cssSelector(follow));
		boolean isFollowPresent = false;
		if(efollow.isDisplayed()){		
			isFollowPresent = true;
			doubleClick("cssSelector", follow);
			waitOnElement("cssSelector", unFollow);
			
		} 
		if(!isFollowPresent){			
			Assert.assertTrue(isFollowPresent, "Follow User link is not present");
		}
	}
	
	public void unfollowUser() throws Exception{
		logInfo("inside unfollowUser() method.");
		waitOnElement("cssSelector",unFollow);
		WebElement efollow = driver().findElement(By.cssSelector(unFollow));
		boolean isUnfollowPresent = false;
		if(efollow.isDisplayed()){			
			isUnfollowPresent = true;
			clickOnElement("cssSelector", unFollow);
			waitOnElement("cssSelector", follow);	
		} 
		if(!isUnfollowPresent){			
			Assert.assertTrue(isUnfollowPresent, "Unfollow User link is not present");
		}
	}
	
	
	
		
	public void sendOfflineMessage() throws InterruptedException, Exception{	
		logInfo("Trying to chat with Online People");
		try{	
		waitOnElement("cssSelector",lnkOfflineMessage);
		clickOnLink("cssSelector",lnkOfflineMessage);
		
		inputText("cssSelector", chatTextArea, "Hello ");
		clickOnElement("cssSelector", chatSend);
		}catch (NoSuchElementException nse){
			logger.error("Either chat is not invoked or Elements are not identified");
		}
		Thread.sleep(1000);
		inputText("cssSelector", chatTextArea, chatString);
		clickOnElement("cssSelector", chatSend);
		clickOnElement("cssSelector", chatClose);		
		logInfo("Succeed! Chatted with Online People");
	}
	
	
	public int countMyprofileFollowers() throws Exception{
		logInfo("inside countMyprofileFollowers() method");
		List<WebElement> followersList = driver().findElements(By.cssSelector("div.media-heading > a"));
		System.out.println("follwers size "+followersList.size());
		
		if(followersList.size() >0){
		for (WebElement follower : followersList) {			
			System.out.println("Total Followers = " + follower.getText());		
			}
		}
		return followersList.size(); 
		}
	
	
	public void go2MyProfilePage(){
		logInfo("inside go2MyProfilePage() method");
		driver().navigate().to(appUrl + "/community/profile");
	}
	
	public boolean verifyUserInMyprofilePage(String uname) throws Exception{		
		logInfo("inside selectFollowerInMyprofilePage() method");
		
		List<WebElement> followersList = driver().findElements(By.cssSelector("div.media-heading>a"));
		System.out.println(followersList.size());
		boolean isProfileMatchFound = false;
		if(followersList.size() >0){
		for (WebElement follower : followersList) {	
			if(follower.getText().trim().contains(uname)){
				System.out.println("Total Followers = " + follower.getText());		
				follower.click();
			//	waitOnElement("cssSelector",".col-md-9.col-sm-6.col-xs-6.profile-name");
			//	WebElement eHeader = driver().findElement(By.cssSelector(".col-md-9.col-sm-6.col-xs-6.profile-name"));
				waitOnElement("cssSelector",".col-md-9.profile-name");
				WebElement eHeader = driver().findElement(By.cssSelector(".col-md-9.profile-name"));
				
				String profileHeader = eHeader.getText().trim();
				if(profileHeader.contains(uname)){
					logInfo("Header name in profile =" +profileHeader);
					isProfileMatchFound = true;
				}
			}
		  }
		}
		return isProfileMatchFound;
	}
	
	
	// search user and select chat link based on user exact matches.
		public boolean verifyChatUser(String searchUser) throws Exception, Exception{
			logInfo("Entered into verifyChatUser() method");
			waitOnElement("cssSelector", pplList);
			boolean isPresent = false;			
			List <WebElement> pl = driver().findElements(By.cssSelector(pplList));
			System.out.println("ppl size"+ pl.size());
			for (int i =1 ; i<=pl.size(); i++ ){
				WebElement user = driver().findElement(By.cssSelector(pplListBfr+ i+ pplListAft));
				String userName = user.getText().trim();
				if (userName.equalsIgnoreCase(searchUser)){
					isPresent = true;	
					System.out.println("Identifed chat user - "+userName);
					break;
					
				}
				
			}if (isPresent==false){
				Assert.assertEquals(isPresent, searchUser+  " user is not found");
			}
			return isPresent;
		}
	
	
	// search user and select chat link based on user exact matches.
	public void searchAndChatWithUser(String searchUser) throws Exception{
		logInfo("Entered into searchAndChatWithUser() method");
		Thread.sleep(5000);
		waitOnElement("cssSelector", search);
		boolean isPresent = false;		
		List <WebElement> pl = driver().findElements(By.cssSelector(pplList));
		System.out.println("ppl size"+ pl.size());
		for (int i =1 ; i<=pl.size(); i++ ){
			WebElement user = driver().findElement(By.cssSelector(pplListBfr+ i+ pplListAft));
			String userName = user.getText().trim();
			if (userName.contains(searchUser)){
				isPresent = true;
				WebElement chatLink = driver().findElement(By.cssSelector(pplListBfr+ i+ chatAft));
				chatLink.click();
				break;
				
			}
			
		}if (isPresent==false){
			Assert.assertEquals(isPresent, searchUser+  " user is not found");
		}		
	}
	
	// search user and select to follow or block or unfollow or unblock the user
		public void searchAndSelectTypeOfUser(String searchUser, String type) throws Exception{
			logInfo("Entered into searchAndChatWithUser() method");
			Thread.sleep(5000);
			waitOnElement("cssSelector", search);
			boolean isPresent = false;		
			List <WebElement> pl = driver().findElements(By.cssSelector(pplList));
			System.out.println("ppl size"+ pl.size());
			for (int i =1 ; i<=pl.size(); i++ ){
				WebElement user = driver().findElement(By.cssSelector(pplListBfr+ i+ pplListAft));
				String userName = user.getText().trim();
				if (userName.contains(searchUser)){
					isPresent = true;					
					switch (type){
					case "Follow" :
						WebElement follows = driver().findElement(By.cssSelector(pplListBfr+ i+ chatFollw));
						follows.click();
						break;						
					case "Unfollow" :
						WebElement unfollows = driver().findElement(By.cssSelector(pplListBfr+ i+ chatFollw));
						unfollows.click();
						break;
					case "Unblock User" :
						WebElement unblocks = driver().findElement(By.cssSelector(pplListBfr+ i+ chatFollw));
						unblocks.click();
						break;						
					case "Block User" :
						WebElement blocks = driver().findElement(By.cssSelector(pplListBfr+ i+ blockUsr));
						blocks.click();
						break;						
					default:
							System.err.println(type + " type is not present for user");
							Assert.assertNotNull(type);
					
					}	
					
				}
				
			}if (isPresent==false){
				Assert.assertEquals(isPresent, searchUser+  " user is not found");
			}
			
			
			
			
		}
	
	
	
	

}


