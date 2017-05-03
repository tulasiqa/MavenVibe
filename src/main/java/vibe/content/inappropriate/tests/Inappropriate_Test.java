package vibe.content.inappropriate.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import common.Priority;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;

@Priority(41)
public class Inappropriate_Test extends InappropriateMethods {
	NewsMethods nm = new NewsMethods();
	CommunityMethods comm = new CommunityMethods();
	InboxMethods inbox = new InboxMethods();
	
	
	@Test(priority=4101)
	public void validationInappropriateObjects() throws Exception{
		
		navigate2Inappropriate();
		validateInappropriateObjects();
		validateAlertMessages();
		
		
		
	}
	
	@Test(priority=4102)
	public void validateInapproWordsListSize() throws Exception{
		navigate2Inappropriate();
		validateBadWordsSize();		
		validateWords(badWordText);
		
	}
	
	@Test(priority=4103)
	public void validateInapproWords() throws Exception{
		navigate2Inappropriate();
		addNewBadWord(badWordText2);
		nm.loginAsSubUser(billingUser1_text);
		comm.addCommunityPhoto(badWordText2);
		userLogout();
		verifyPostedBadWords(badWordText2);	
	}
	
	@Test(priority=4104)
	public void validateEmailAlerts() throws Exception{		
		navigate2Inappropriate();
		emailalerts(badWordText2);		
	}
	
	@Test(priority=4105)
	public void validateEmailConfiguration() throws Exception{	
		
		navigate2Inappropriate();		
		verificationEmailConfig(badWordText2);		
	}
	
	
	@Test(priority=4106)
	public void sendEmailAndVerify() throws Exception{
		//String  badWordText2 = "ImproperWords67";
		
		navigate2Inappropriate();
		emailToUser(badWordText2);
		
		nm.loginAsSubUser(billingUser1_text);
		inbox.go2Inbox();
		/*inbox.selectVibeMailsWithSubject(badWordText2);
		inbox.deleteFilteredVibeMails();	*/	
		userLogout();
		
	}
	
	@Test(priority=4107)
	public void verifyPostedTitleOfWord() throws Exception{	
		
		//String  badWordText2 = "ImproperWords26";
		navigate2Inappropriate();
		validateWordWithPostedTitle(badWordText2);
		modifyPhotoTitle(subjectBulk);
		nm.userLogout();
		verifyPostedNotToBePresent(badWordText2);		
	}
	
	@Test(priority=4108)
	public void verifyPostedBlogOfWord() throws Exception{	
		
		//String  badWordText3 = "ImproperBlog554";
		navigate2Inappropriate();
		addNewBadWord(badWordText3);
		nm.loginAsSubUser(billingUser1_text);
		comm.postStatus(badWordText3);
		userLogout();
		navigate2Inappropriate();		
		selectURLLinkOfPosted(badWordText3);
		updateBlogPost(PhotoTitle_text4);
		handleBack();
		userLogout();
		verifyPostedNotToBePresent(badWordText3);	
	}
	
	@Test(priority=4109)
	public void validateReservedAlerts() throws Exception{		
		navigate2Inappropriate();
		selectSection(inapsectext2);
		validatReservedeAlerts();
	}
	
	@Test(priority=4110)
	public void validateReservedWords() throws Exception{
		navigate2Inappropriate();
		selectSection(inapsectext2);
		addReservedWord(reservedWordText);
		boolean wordPresent=isReservedWordPresent(reservedWordText);
		if (wordPresent = true){			
			deleteReservedWord(reservedWordText);
			isReserveWordNotToPresent(reservedWordText);			
		}if (wordPresent==false){
			Assert.assertTrue(wordPresent);
		}		
	}
	
	@Test(priority=4111)
	public void validateExport() throws Exception{
		navigate2Inappropriate();
		verifyInExportedFolder();	
	}
	
	
	
	@Test(priority=4112)
	public void deleteBadWord() throws Exception{
		//String badWordText2 = "badword45";
		navigate2Inappropriate();		
		deleteWord("All", badWordText2);
		deleteWord("All", badWordText3);
		
	}

}
