package vibe.tasks.tests;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import common.Priority;
import common.TestBase;

import vibe.contacts.tests.BusinessContactsMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.myprofile.tests.GoalTest;
import vibe.myprofile.tests.MyProfileMethods;

@Priority(36)
public class GoalTasks extends MyProfileMethods{
	
	TaskMethods tk = new TaskMethods();
	GoalTest gt = new GoalTest();
	
	@Test(priority=3601)
	public void GoalTasks_createTaskFromGoal () throws Exception{
		logInfo("Add Task from Goal");		
		gt.goal_GoalCreation();
		navigate2MyProfilePage();
		navGoals();
		addTaskSelect();
		tk.addTaskFromGoal(taskEvent6, 1);
		  
	}
	
	@Test(priority=3602, dependsOnMethods = {"GoalTasks_createTaskFromGoal"})
	public void GoalTasks_MarkGoalTaskAsComplete () throws Exception{
		logInfo("Select created in Goal page and mark as Complete\n"
				+ "Verify the same task under 'Task Complete' Widget.");
		
		//String taskEvent6 = "This task is added from Goal whose order no is : 166";
		navigate2MyProfilePage();
	    navGoals();		  
		tasksPanelList(taskEvent6);		  
	    tk.markAsComplete();	

	}
	
	@Test(priority=3603)
	public void GoalTasks_deleteAllTasksFromGoal() throws InterruptedException, Exception{
		logInfo("Tear Down all Goal Tasks.");
	    navigate2MyProfilePage();
	    navGoals();
	    deleteTasksFromGoal();		
	}

}
