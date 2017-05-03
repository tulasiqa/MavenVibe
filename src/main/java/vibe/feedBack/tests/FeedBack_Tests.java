package vibe.feedBack.tests;

import org.junit.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import vibe.calendar.tests.CalendarMethods;
import vibe.contacts2.tests.BusinessContactsMethods;
import vibe.contacts2.tests.BusinessContacts_Tests;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.people.tests.PeopleMethods;
import vibe.reports.tests.ReportsMethods;
import vibe.resourcelibrary2.tests.RL2Methods;
import vibe.tasks.tests.TaskMethods;
import vibe.training.tests.TrainingMethods;
import vibe.users.tests.UsersMethods;

@Priority(35)
public class FeedBack_Tests extends FeedBackMethods {
	
	CalendarMethods cm = new CalendarMethods();
	BusinessContactsMethods bc = new BusinessContactsMethods();
	InboxMethods inbox = new InboxMethods();
	CommunityMethods comm = new CommunityMethods();
	MyProfileMethods profile = new MyProfileMethods();
	PeopleMethods ppl = new PeopleMethods();
	ReportsMethods reports = new ReportsMethods();
	RL2Methods rl2 = new RL2Methods();
	TaskMethods tm = new TaskMethods();
	TrainingMethods train = new TrainingMethods(); 
	NewsMethods nm = new NewsMethods();
	BusinessContacts_Tests cont = new BusinessContacts_Tests();
	UsersMethods um = new UsersMethods();
	

	
	
	@Test(priority=3501)
	public void verifyFeedbackLinkInAllScreens() throws Exception{
		logInfo("Entered verifyFeedbackLinkInAllScreens() test");
		back2Office();
		verifyFeedbackLink();		
		bc.go2ContactsPage();
		verifyFeedbackLink();
		inbox.go2Inbox();
		verifyFeedbackLink();
		comm.navigate2CommunityPage();
		verifyFeedbackLink();
		profile.navigate2MyProfilePage();
		verifyFeedbackLink();
		ppl.go2PeoplePage();
		verifyFeedbackLink();
		reports.navigate2Report();
		verifyFeedbackLink();
		rl2.navigate2UserRL();
		verifyFeedbackLink();
		tm.navigate2BusinessTasks();
		verifyFeedbackLink();
		
	}
	
	@Test(priority=3502)
	public void validateFeedBack() throws Exception{
		logInfo("Entered validateFeedBack() test");
		back2Office();
		navigate2Feedback();
		objectsVerifications();
		validateFeedBackFields();		
	}
	
	@Test(priority=3503)
	public void createReport() throws Exception{
		logInfo("Entered createReport() Test");		
		back2Office();
		navigate2Feedback();
		reportIssue(fbTitcketTitle);	
		
	}
	
	@Test(priority=3504)
	public void validateAdminComments() throws Exception{	
		logInfo("Entered validateAdminComments() test");
		navigate2Feedback();
		boolean isTicket = validateTickets(fbTitcketTitle);
			if(isTicket = true){
				commentToTicket(fbTitcketTitle, fbcomment);
				verifyCommentAndDelete(fbcomment);
				confirmationMessage("Comment Deleted");
				
			}if(isTicket==false){
				Assert.assertTrue(isTicket);
			}	
				
		boolean isticketPresent= verifyTicketInAdmin(fbTitcketTitle);
		if (isticketPresent= true){
			addCommentFeedbackInAdmin(fbTitcketTitle, EventMeeting2);
			back2Office();
			navigate2Feedback();			
			verifyCommentAndDelete(EventMeeting2);			
		}if(isticketPresent==false){
			Assert.assertTrue(isticketPresent);
		}	
	}
	
	@Test(priority=3505)
	public void validateReportAndDelete() throws Exception{
		logInfo("Entered validateReportAndDelete() test");
		back2Office();
		navigate2Feedback();
		validateTickets(fbTitcketTitle);
		commentToTicket(fbTitcketTitle, fbcomment);
		verifyCommentAndDelete(fbcomment);
		confirmationMessage("Comment Deleted");
		
		boolean isticketPresent= verifyTicketInAdmin(fbTitcketTitle);
		if (isticketPresent= true){
			deleteTicketInAdmin(fbTitcketTitle);
			ticketNotToBePresentInAdmin(fbTitcketTitle);			
		}if(isticketPresent==false){
			Assert.assertTrue(isticketPresent);
		}	
	}	
	
	@Test(priority=3506)
	public void validateFeedbackWithVoteNdComment() throws Exception{		
		logInfo("Entered validateFeedbackWithVoteNdComment() Test");			
		back2Office();
		navigate2Feedback();
		System.out.println(fbFeedbackTitle);
		createFeedback(fbFeedbackTitle);		
		navigate2Feedback();		
		boolean isFeedbackPresent= validateFeedbacks(fbFeedbackTitle);
		if (isFeedbackPresent= true){
			validateVotedFeedback(fbFeedbackTitle);			
			commentFeedback(fbFeedbackTitle, commentText);
			
		}if(isFeedbackPresent==false){
			Assert.assertTrue(isFeedbackPresent);
		}		
	}
	
	@Test(priority =3507)
	public void validateSearchedTickets() throws Exception{	
		logInfo("Entered validateSearchedTickets() test");
		back2Office();
		navigate2Feedback();
		System.out.println(fbTitcketTitle);
		reportIssue(fbTitcketTitle);	
		navigate2Feedback();
		searchNdVerifyTickets(fbTitcketTitle);	
	}
		
	@Test(priority =3508)
	public void validateSearchedFeedbacks() throws Exception{	
		logInfo("Entered validateSearchedFeedbacks() test");
		//String fbFeedbackTitle = "Awesome photo 35";
		navigate2Feedback();
		searchNdVerifyFeedbacks(fbFeedbackTitle);	
	}
	
	@Test(priority =3509)
	public void validateInvalidSearchsForFeedbacks() throws Exception{	
		logInfo("Entered validateInvalidSearchsForFeedbacks() test");
		String fbFeedbackTitle = "%euireuiyre%90#@"+TestBase.generateRandomNumberInRange(10, 5000);
		navigate2Feedback();
		invalidSearchs(fbFeedbackTitle);	
	}
	
	@Test(priority =3510)
	public void validateFilterTickets() throws Exception{
		logInfo("Entered validateFilterTickets() test");
		//String fbTitcketTitle= "The worst feeling is feeling 249";
		navigate2Feedback();
		navigate2FeedbackTickets();
		searchFilters("4 Months", fbTitcketTitle);		
		searchFilters("2 Months", fbTitcketTitle);		
		searchFilters("6 Months", fbTitcketTitle);
		searchFilters("All", fbTitcketTitle);
		searchFilters("All Unresolved", fbTitcketTitle);
	}
	
	@Test(priority =3511)
	public void validateBoundariesForFeedback() throws Exception{
		logInfo("Entered validateBoundariesForFeedback() test");
		navigate2Feedback();
		verifyBoundaries();	
		
	}
	
	@Test(priority =3512)
	public void validateUsersTicketAndReport() throws Exception {
		logInfo("Entered validateUsersTicketAndReport() test");
		//todo -  Change this user to newly created Enrollment user.
		nm.loginAsSubUser(VibeProMonthlyUser);	 	
		System.out.println(fbUsersTitcketTitle);
		System.out.println(fbUsersTitcketComment);
		navigate2Feedback();
		reportIssue(fbUsersTitcketTitle);
		navigate2Feedback();		
		commentToTicket(fbUsersTitcketTitle, fbUsersTitcketComment);
		userLogout();		
		back2Office();
		navigate2AdminFeedback();
		addCommentFeedbackInAdmin(fbUsersTitcketTitle, adminComment);
		nm.loginAsSubUser(VibeProMonthlyUser);	
		navigate2Feedback();
		boolean isticketPresent=false;
		if(isticketPresent =validateTickets(fbUsersTitcketTitle)){
			verifyCommentedAndReport(adminComment);
			userLogout();
			navigate2AdminFeedback();
			verifyReportedInAdmin(fbUsersTitcketTitle);
		}	if(isticketPresent==false){
			Assert.assertTrue(isticketPresent);
		}		
	}
	
	
	@Test(priority =3513)
	public void validateAdminFeedbacksBasedOnSections() throws Exception{
		logInfo("Entered validateAdminFeedbacksBasedOnSections() test");
		//String fbUsersTitcketTitle = "The worst feeling is feeling 123456";	
		
		navigate2AdminFeedback();
		verifySectionsInAdmin(fbUsersTitcketTitle);
		
	}
	
	@Test(priority=3514)
	public void validatFeedbackAndDelete() throws Exception{	
		logInfo("Entered validatFeedbackAndDelete() test");
		
		//String fbFeedbackTitle = "Awesome photo 61";		
		boolean isFeedbackPresent= verifyFeedbackInAdmin(fbFeedbackTitle);
		if (isFeedbackPresent= true){
			deleteFeedbackInAdmin(fbFeedbackTitle);
			feedbackNotToBePresentInAdmin(fbFeedbackTitle);			
		}if(isFeedbackPresent==false){
			Assert.assertTrue(isFeedbackPresent);
		}	
	}
	
	
	
	
	
	
	

}
