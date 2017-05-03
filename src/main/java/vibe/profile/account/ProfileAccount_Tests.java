package vibe.profile.account;

import java.util.List;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;


@Priority(25)
public class ProfileAccount_Tests extends ProfileAccountMethods {

	@Test(priority=2501)
	public void changeProfilePhoto() throws Exception{
		go2CommunityProfilePage();
		changeCommunityProfilePhoto();
		verifyProfilePage(imgProfile_text);
	}
	
	// ******************************** Profile_AccountInformation tests ********************************//
	
	  @Test(priority=2502)
	  public void accountProfile() throws Exception {

		logInfo("inside test case : ******* Profile_Account ******** ");
		
		// Create a business contact.
			
		clickOnElement("xpath", eleDrpdnProfile);
		clickOnLink("linkText", "Account");		
		implicityWaits(10);		
	//	clickOnLink("linkText", "Security");	
		
      List<WebElement> accountPanelFocus = driver().findElements(org.openqa.selenium.By.xpath(accountPanelSize));
      System.out.println("No of Account Panel List : "+accountPanelFocus.size());            
    //      for(WebElement accountPanelLists : accountPanelFocus) {
             for(int i=1 ; i <= accountPanelFocus.size(); i++){
                 WebElement accountLinks = driver().findElement(org.openqa.selenium.By.xpath(accountLinks1+i+accountLinks2));
                 System.out.println("Selected Accounts Link is : "+accountLinks.getText());
                 accountLinks.click();
                 Thread.sleep(10000);
                
                }
    }
	  
	  
	  //************************ Profile_CommunityProfileUpdate tests *************************************//

	  
	  
    @Test(priority=2503)
	public void updateCommunityProfile() throws Exception {

	logInfo("inside test case : ******* Profile_CommunityProfileUpdate ******** ");
		
	// Navigate to profile dropdown -> Account -> Community Profile menu.
	
	clickOnElement("xpath", eleDrpdnProfile);
	clickOnLink("linkText", "Account");
				
	logInfo("clicked Profile dropdown -> Account in Home page.");
			
	logInfo("verifying the update of community profile page.");
	
	clickOnLink("linkText","Community Profile");
	
	implicityWaits(10);
	
	Thread.sleep(10000);
	
	// getting the count of rows and columns in the web table.
	
	
		updateCommProfileInfo();
		
		// verify if the community profile is updated.
		
      }
  
  
  //***********************************Profile_LanguageSettings tests*****************************************//
  
    @Test(priority=2504)
	public void verifyLanguageSettings() throws Exception {

	logInfo("inside test case : ******* Profile_LanguageSettings  ************ ");
		
	// Navigate to profile dropdown -> Account -> Language Settings menu.
	
	clickOnElement("xpath", eleDrpdnProfile);
	clickOnLink("linkText", "Account");
				
	logInfo("clicked Profile dropdown -> Language Settings menu.");
			
	logInfo("verifying the links in Language Settings page.");
	
	clickOnLink("linkText","Language Settings");
	
	selectFromDropdown("cssSelector",drpdnLanguage,"byVisibleText",language_text);
	selectFromDropdown("cssSelector",drpdnLanguageTimezone,"byVisibleText",languageTimezone_text);
	clickOnButton("xpath",btnLanuageSave);
	
	// verify if the language settings are updated.
  }
  
  
  //**************************************Profile_LinkedAccounts tests *******************************************//
  
    @Test(priority=2505)
	public void verifyLinkedAccounts() throws Exception {

	logInfo("inside test case : ******* Profile_LinkedAccounts ******** ");
		
	// Navigate to profile dropdown -> Account -> Linked Accounts menu.
	
	clickOnElement("xpath", eleDrpdnProfile);
	clickOnLink("linkText", "Account");
				
	logInfo("clicked Profile dropdown -> Linked Accounts menu.");
			
	logInfo("verifying the links in Linked Accounts page.");
	
	clickOnLink("linkText","Linked Accounts");
	
	implicityWaits(10);
	
		
	// getting the count of rows and columns in the web table.
	
    WebElement epaneLinkedAccounts = driver().findElement(org.openqa.selenium.By.cssSelector(paneLinkedAccounts));
    List<WebElement> allLinks = epaneLinkedAccounts.findElements(org.openqa.selenium.By.tagName("a"));
    
    logInfo("count of links Linked Accounts page :" + allLinks.size());
    
    for (WebElement x : allLinks){
    	String getValue = x.getText().trim();
   
    	switch (getValue){
		case "Activate Facebook Integration":
			logInfo("match found : " + getValue);
			break;
		case "Activate Twitter Integration":
			logInfo("match found : " + getValue);
			break;
		case "Activate Google Integration":
			logInfo("match found : " + getValue);
			break;
		case "Activate Yahoo Integration":
			logInfo("match found : " + getValue);
			break;
		case "Activate LinkedIn Integration":
			logInfo("match found : " + getValue);
			break;
		case "Activate Instagram Integration":
			logInfo("match found : " + getValue);
			break;	
		default:
			logInfo("incorrect match found : " + getValue);
			break;		
    		}
    	
    	}
    		
    }
  
  
  //*********************************************Profile_Security tests ************************************//
  
    @Test(priority=2506)
	public void verifyPasswordChange() throws Exception {

	logInfo("inside test case : ******* Profile_Security  ************ ");
		
	// Navigate to profile dropdown -> Account -> Security menu.
	
	clickOnElement("xpath", eleDrpdnProfile);
	clickOnLink("linkText", "Account");
	
	clickOnLink("linkText", "Security");
				
	logInfo("clicked Security link in Security link.");
	
	// verify changing the existing password.
	
	inputText("cssSelector",inputCurrentPwd,adminPwd_text);
	inputText("cssSelector",inputNewPwd,newPassword_text);
	inputText("cssSelector",inputConfirmPwd,newPassword_text);
		
	clickOnButton("cssSelector",btnSecuritySave);
	
	// verify if the header displayed is correct on the page.
	
    WebElement eSecurityPanel = driver().findElement(org.openqa.selenium.By.cssSelector(securityPanel));
    
    List<WebElement> allHeaders = eSecurityPanel.findElements(org.openqa.selenium.By.tagName("h4"));

    for(WebElement x : allHeaders){
    	String getValue = x.getText();
    	if (getValue.equalsIgnoreCase(adminUser_text)){
    		logInfo("header text matches with the user logged");
    	} else {
    		logInfo("header text does not matches with the user logged");
    	}
    	
    }
	
  }
  
  
  //*************************************AllAccounts tests*****************************************//
  
    @Test(priority=2507)
	public void allAcounts () throws Exception{
		logInfo("Handling Accounts information, Community Profile\n"
				+ "Language Settings, Linked Accounts and Security");
		
		
		try{
		accountProfile();
		updateCommunityProfile();
		verifyLanguageSettings();
		verifyLinkedAccounts();
		verifyPasswordChange();
	}
	catch(Exception e){
		throw new Exception("Failed!!  unable to handle one or more methods ");
	}
		
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");	
		
	}
    
    
}
