package vibe.monatGear.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import org.openqa.selenium.By;

import common.TestBase;


public class MonatGearMethods extends TestBase{
	
	public void nav2USGear() throws Exception{	
		logInfo("Entered monatGearUS() method");
		driver().navigate().to("https://www.monatgear.com/store/catalog");
		
		}
	
	public void nav2CAGear() throws Exception{	
		logInfo("Entered monatGearUS() method");
		driver().navigate().to("https://www.monatgearcanada.com");
		
		}
	
	public void validateUSGearHomePage(){
		logInfo("Entered validateUSGearHomePage() method");
		String expStore = "https://www.monatgear.com/store";
		
		WebElement store = driver().findElement(By.cssSelector(storeUS));
		String actStore = store.getAttribute("href");		
		Assert.assertEquals(actStore, expStore);
		
		
	}
	
	
	public void getListOfTabs (){
		
		List <WebElement> tabs = driver().findElements(By.cssSelector(tabsList));
		System.out.println("get size"+ tabs.size());
		for (WebElement tabLis : tabs ){
			System.out.println(tabLis.getText());
		}
		
		
		
		
		
	}
	
	
	

}
