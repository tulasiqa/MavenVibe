package vibe.monatGear.tests;

import org.testng.annotations.Test;

import common.Priority;

@Priority(50)
public class MonatGearTest extends MonatGearMethods{
	
	
	@Test(priority=5001)
	public void validateUSMonatGear() throws Exception{
		
		logOut();		
		nav2USGear();
		validateUSGearHomePage();
		getListOfTabs();
		
		
	}
	

}
