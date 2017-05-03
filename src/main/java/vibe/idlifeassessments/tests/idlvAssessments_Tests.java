package vibe.idlifeassessments.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import common.readProp;

@Priority(31)
public class idlvAssessments_Tests extends idlvassessmentMethods  {
	readProp prop = new readProp();
	
	@Test(priority = 3000)
	public void idNutritionAssessmentTest() throws Exception{
		logInfo("inside idNutritionAssessmentTest.. test ");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		back2Office();
		gotoIDNutiritionPage();
		idlifeNutiritionAssesment();
	}
		
	
	@Test(priority = 3001)
	public void myexperienceAssessmentTest() throws Exception{
		logInfo("inside myexperienceAssessmentTest.. test ");		
		back2Office();
		gotoMyExperiencePage();
		 idlifeMyExperienceAssesment();
	
		}
	
		
	
	@Test(priority = 3002)
	public void idWellnessTest() throws Exception{
		// userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));
		back2Office();
		validateIDwellness();
		userLogout();
	}
	
	@Test(priority=3003)
	public void loginAsAdminFromAssessments() throws Exception{
		logInfo("inside loginAsAdminFromAssessments() Test");
		
		adminLogin();
		
		
	}
	
	
}
