package vibe.myprofile.tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import vibe.inbox.tests.InboxMethods;


@Priority(24)
public class GoalTest extends MyProfileMethods{
	
	MyProfileMethods profile = new MyProfileMethods();
	
	@Test(priority= 2401)
	public void goal_GoalCreation() throws Exception, Exception{
		logInfo("Create a Goal with future Date.");
		profile.navigate2MyProfilePage();
		profile.navGoals();
		profile.addGoalsLinkSelection();
		profile.titleTextEnter(Title_text);
		profile.DescriptionTextEnter();
		profile.completeByDate(5);
		profile.UploadImageInGoal();
		profile.showOnBoardCheckbox();
		profile.saveGoalButton();  
	
	}
	
	@Test(priority= 2402 ,dependsOnMethods = {"goal_GoalCreation"})
	public void goal_VerifyGoals() throws Exception, Exception{
		// String g = "Awesome goals to reach in 119 Days";
		profile.navigate2MyProfilePage();
		profile.navGoals();		
		profile.verifyGoalsIsPresent(Title_text);
		profile.navigate2MyProfilePage();
		profile.verifyMyRecentActivity(Title_text);
	}
	
	
	@Test(priority= 2403 )
	public void goal_VerifyBoardOnIcons() throws Exception, Exception{
		//String Title_text = "Awesome goals to reach in 171 Days";
		profile.navigate2MyProfilePage();
		profile.navGoals();	    
		profile.verifyGoalsIsPresent(Title_text);	      
	}
	
	@Test (priority= 2404 ,dependsOnMethods = {"goal_GoalCreation"})
	public void goal_DeleteGoal() throws Exception, Exception{
		logInfo("Delete the Goal - " +Title_text + "verify whether Goal form the list");
		 //String Title_text = "Awesome goals to reach in 119 Days";
		profile.navigate2MyProfilePage();
		profile.navGoals();
		profile.deleteGoals(Title_text);		
	}

	
	
}
