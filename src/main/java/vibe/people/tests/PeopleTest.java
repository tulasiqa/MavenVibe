package vibe.people.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.readProp;
@Priority(19)
public class PeopleTest extends PeopleMethods {	
	
	readProp prop = new readProp();
	
	//String userName_text = "automation123 qa123";
	//String userName_text2 = "ganesh gonella";
	
	@Test(priority =1901)
	public void people_searchPeople() throws Exception{
		logInfo("inside people_searchPeople() Test.");	
		go2PeoplePage();	
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		boolean isPeopleFound = verifyChatUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));	
		if(isPeopleFound==false){
			Assert.assertTrue(isPeopleFound, prop.getLocatorForEnvironment(appUrl,"userName_text") + " people match not found in people page.");
		}
	}	

		
	@Test(priority =1902)
	public void people_FollowAndUnfollowUser() throws Exception{
		logInfo("inside people_FollowAndUnfollowUser() Test.");
		int followersCountBefore, followersCountAfter;
		go2MyProfilePage();
		followersCountBefore = countMyprofileFollowers();
		System.out.println(followersCountBefore);
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text"),"Follow");
		confirmationMessage("You are now following "+ prop.getLocatorForEnvironment(appUrl,"userName_text"));
		go2MyProfilePage();
		followersCountAfter = countMyprofileFollowers();
		System.out.println(followersCountAfter);		
		Assert.assertEquals(followersCountAfter, followersCountBefore+1);		
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text"),"Unfollow");
		confirmationMessage("You are no longer following "+ prop.getLocatorForEnvironment(appUrl,"userName_text"));
		go2MyProfilePage();
		int afterUnFollower = countMyprofileFollowers();	
		Assert.assertEquals(afterUnFollower, followersCountBefore);		
	}
	
	
	
	@Test(priority =1903)
	public void people_ChatWithOnlineUsers() throws Exception{
		logInfo("inside people_ChatWithOnlineUsers() Test.");
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		chatPeople();
		//setChatFilter("Online","Newest User");
		sendMessage2ChatUser(chatString);
		boolean isMsgFound = verifyChatMessage(chatString);
		if(!isMsgFound){
			Assert.assertTrue(isMsgFound, "chat string not found in chat window");
		}
	}

	
	@Test(priority =1904)
	public void people_BlockAndUnblock() throws Exception{
		logInfo("inside people_BlockAndUnblock() Test.");
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));	
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),"Block User");
		confirmationMessage("You blocked "+ prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text1"));
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text2"));
		searchAndSelectTypeOfUser(prop.getLocatorForEnvironment(appUrl,"userName_text2"),"Unblock User");
		confirmationMessage("You unblocked "+ prop.getLocatorForEnvironment(appUrl,"userName_text2"));
	
	}
	
	
	@Test(priority =1905)
	public void people_ChatWithOfflineUsers() throws Exception{
		logInfo("inside people_ChatWithOfflineUsers() Test.");
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text"));	
		sendOfflineMessage();
	}
	
	
	@Test(priority =1906)
	public void people_verifyProfilePage() throws Exception{
		logInfo("inside people_ChatWithOfflineUsers() Test.");
		go2PeoplePage();
		searchUser(prop.getLocatorForEnvironment(appUrl,"userName_text"));
		boolean isPeopleFound = verifyPeopleList(prop.getLocatorForEnvironment(appUrl,"userName_text"));	
		if(isPeopleFound==false){
			Assert.assertTrue(isPeopleFound, prop.getLocatorForEnvironment(appUrl,"userName_text") + " people match not found in people page.");
		}
		followUser();
		go2MyProfilePage();
		boolean isMatchFound = verifyUserInMyprofilePage(prop.getLocatorForEnvironment(appUrl,"userName_text"));	
		if(isMatchFound==false){
			Assert.assertTrue(isMatchFound, "Title or Header did not match in user profile page.");
		}
		
	}
	
	
	
	
}
