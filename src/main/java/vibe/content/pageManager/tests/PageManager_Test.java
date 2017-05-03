package vibe.content.pageManager.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import common.Priority;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;

@Priority(42)
public class PageManager_Test extends PageManagerMethods {
	
	
	
	@Test(priority=4201)
	public void validatePageManager() throws Exception{
		
		nav2PageManager();
		verifyPageManager();		
	}
	
	
	@Test(priority=4202)
	public void validateSiteMap() throws Exception{		
		nav2PageManager();
		assertSiteMapLanguage();
	    verifySiteMap();
	    assertSiteMapLanguage();	
	}
	
	
	@Test(priority=4220)
	public void stopCMS() throws Exception{		
		nav2PageManager();
		selectAdminUserDropDwon("Stop CMS");
			
	}
		

}
