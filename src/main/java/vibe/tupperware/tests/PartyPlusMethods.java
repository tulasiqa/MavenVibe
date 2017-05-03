package vibe.tupperware.tests;

import java.util.List;
import org.openqa.selenium.WebElement;

import common.TestBase;

import org.openqa.selenium.By;
import org.testng.Assert;

public class PartyPlusMethods extends TestBase {
	
	
	public void selectTab(String tabName){
		logInfo("Entered into selectTab() method");
		back2Office();
		List <WebElement> tb = driver().findElements(By.cssSelector(appTabs));
		System.out.println(tb.size());
		boolean istabPresent =false;
		for (int i =1; i<= tb.size();i++){
			WebElement tabnames = driver().findElement(By.cssSelector(appTabsBfr+i+appTabsAft));
			 String tabbing  = tabnames.getText().trim();			
			 if (tabbing.equalsIgnoreCase(tabName)){
				 istabPresent=true;
				 tabnames.click();
				 break;
			 }			 
			 
		}if (istabPresent==false){
			Assert.assertTrue(istabPresent, tabName + "- tab is not present in Office" );
		}
	}
	
	public void validateNonPlatinumOrSilverUsers() throws Exception{
		logInfo("Entered into validateNonPlatinumOrSilverUsers() method");
		String ExpTitle = "Party Plus";
		Thread.sleep(4000);
		WebElement ppTitle = driver().findElement(By.cssSelector(pTitle));
		String PplusTitle = ppTitle.getText();
		System.out.println(PplusTitle);
		Assert.assertEquals(PplusTitle, ExpTitle);	
		waitOnElement("cssSelector", pClose);
		clickOnElement("cssSelector", pClose);
	}
	
	
	public void validatePlatinumUser() throws Exception{		
		logInfo("Entered into validateplatinumuser() method");
		Thread.sleep(5000);
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
				if(!w.equalsIgnoreCase(wndBeforeWindow)){
				driver().switchTo().window(w);					
				if ((driver().getCurrentUrl().contains("shoptest.tupperware.com"))){
					System.out.println("Succed!! Entered into One");
						}else if(driver().getCurrentUrl().contains("ordertest.tupperware.com")){
								System.out.println("Succed!! Entered into two");
						}else{
							Assert.assertNull(driver().getCurrentUrl());
								}				
				driver().switchTo().window(wndBeforeWindow);
				Thread.sleep(4000);
				break;	
							}
						}
		
				} 




		}





