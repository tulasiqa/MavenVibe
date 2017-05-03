package vibe.shopping2.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.apache.commons.lang3.StringUtils;


import common.TestBase;
import common.readProp;
import vibe.mycommunity.tests.CommunityMethods;

public class VTUP_ShoppingMethods extends TestBase{

	

	 public void gotoPlaceOrdersPage() throws Exception{
		 	logInfo("inside gotoPlaceOrdersPage() method");
		 	clickOnLink("linkText", "My Sales");
			clickOnLink("linkText", "Place Order");
			
	 }
	
	 public void gotoAddRecruitsPage() throws Exception{
		 	logInfo("inside gotoAddRecruitsPage() method");
		 	clickOnLink("linkText", "My Sales");
			clickOnLink("linkText", "Add Recruits");
			
	 }
	
	 public void gotoPerosnalSummaryPage() throws Exception{
		 	logInfo("inside gotoPerosnalSummaryPage() method");
		 	clickOnLink("linkText", "My Sales");
			clickOnLink("linkText", "Personal Summary");
			
	 }
	
	 public void gotoCommissionsPage() throws Exception{
		 	logInfo("inside gotoCommissionsPage() method");
		 	clickOnLink("linkText", "My Sales");
			clickOnLink("linkText", "Commissions");
			
	 }
	 
	 public void gotoMyTeamPage() throws Exception{
		 	logInfo("inside gotoCommissionsPage() method");
		 	clickOnLink("linkText", "My Sales");
			clickOnLink("linkText", "My Team");
			
	 }
	 
	
	public void validatePlaceOrders() throws Exception{
		logInfo("Entered into validatePlaceOrders() method");
		gotoPlaceOrdersPage();
		String winHandleBefore = driver().getWindowHandle();
		 for(String winHandle : driver().getWindowHandles()){
		    driver().switchTo().window(winHandle);
		}
		 waitOnElement("xpath","//*[@id='log_pswd']/tbody/tr[2]/td[1]");
		 String expectedTit = driver().findElement(By.xpath("//*[@id='log_pswd']/tbody/tr[2]/td[1]")).getText();
		 System.out.println(expectedTit);
		 driver().close();
		 driver().switchTo().window(winHandleBefore);
		 Assert.assertEquals(expectedTit, "Consultant Number");
		 	
		}

	
	

	public void validateAddRecruitsPage() throws Exception{
		logInfo("Entered into validateAddRecruitsPage() method");
		gotoAddRecruitsPage();
		String winHandleBefore = driver().getWindowHandle();
		 for(String winHandle : driver().getWindowHandles()){
		    driver().switchTo().window(winHandle);
		}
		 waitOnElement("xpath","//*[@id='log_pswd']/tbody/tr[2]/td[1]");
		 String expectedTit = driver().findElement(By.xpath("//*[@id='log_pswd']/tbody/tr[2]/td[1]")).getText();
		 System.out.println(expectedTit);
		 driver().close();
		 driver().switchTo().window(winHandleBefore);
		 Assert.assertEquals(expectedTit, "Consultant Number");
		 	
		}
	
	public void validatePersonalSummaryPage() throws Exception{
		logInfo("Entered into validatePersonalSummaryPage() method");
		gotoPerosnalSummaryPage();
		String winHandleBefore = driver().getWindowHandle();
		 for(String winHandle : driver().getWindowHandles()){
		    driver().switchTo().window(winHandle);
		}
		 waitOnElement("xpath","//*[@id='log_pswd']/tbody/tr[2]/td[1]");
		 String expectedTit = driver().findElement(By.xpath("//*[@id='log_pswd']/tbody/tr[2]/td[1]")).getText();
		 System.out.println(expectedTit);
		 driver().close();
		 driver().switchTo().window(winHandleBefore);
		 Assert.assertEquals(expectedTit, "Consultant Number");
		 	
		}	
	

	public void validateCommissionsPage() throws Exception{
		logInfo("Entered into validateCommissionsPage() method");
		 gotoCommissionsPage();
		String winHandleBefore = driver().getWindowHandle();
		 for(String winHandle : driver().getWindowHandles()){
		    driver().switchTo().window(winHandle);
		}
		 waitOnElement("xpath","//*[@id='log_pswd']/tbody/tr[2]/td[1]");
		 String expectedTit = driver().findElement(By.xpath("//*[@id='log_pswd']/tbody/tr[2]/td[1]")).getText();
		 System.out.println(expectedTit);
		 driver().close();
		 driver().switchTo().window(winHandleBefore);
		 Assert.assertEquals(expectedTit, "Consultant Number");
		 	
		}	
	
	public void validateMyTeamPage() throws Exception{
		logInfo("Entered into validateMyTeamPage() method");
		gotoMyTeamPage();
		String winHandleBefore = driver().getWindowHandle();
		 for(String winHandle : driver().getWindowHandles()){
		    driver().switchTo().window(winHandle);
		}
		 waitOnElement("xpath","//*[@id='log_pswd']/tbody/tr[2]/td[1]");
		 String expectedTit = driver().findElement(By.xpath("//*[@id='log_pswd']/tbody/tr[2]/td[1]")).getText();
		 System.out.println(expectedTit);
		 driver().close();
		 driver().switchTo().window(winHandleBefore);
		 Assert.assertEquals(expectedTit, "Consultant Number");
		 	
		}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	


