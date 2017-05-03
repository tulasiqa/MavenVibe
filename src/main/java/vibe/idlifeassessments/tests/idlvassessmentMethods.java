package vibe.idlifeassessments.tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import common.TestBase;
import common.readProp;

public class idlvassessmentMethods extends TestBase {	
	 String idnpanel2a = "//*[@id='site-content']/form[1]/section/article/div";
	 String idnpanel2b = "//*[@id='site-content']/form[2]/section/article[1]/div";
	 String idnpanel2c = "//*[@id='site-content']/form[3]/section/article[1]/div";
	 String idnpanel2d = "//*[@id='site-content']/form[3]/section/article[2]/div";
	 String idnpanel2e = "//*[@id='site-content']/form[3]/section/article[3]/div";
	 String idnpanel2f = "//*[@id='site-content']/form[4]/section/article[1]/div";
	 String idnpanel2g = "//*[@id='site-content']/form[4]/section/article[2]/div";
	 String idnpanel2h = "//*[@id='site-content']/form[5]/section/article[1]/div";
	 String idnpanel2i = "//*[@id='site-content']/form[5]/section/article[2]/div";
	 String idnpanel2j = "//*[@id='site-content']/form[5]/section/article[3]/div";
	 String idnpanel2k = "//*[@id='site-content']/form[5]/section/article[4]/div";
	 String idnpanel2l = "//*[@id='site-content']/form[5]/section/article[5]/div";
	 String idnpanel2m = "//*[@id='site-content']/form[5]/section/article[6]/div";

	 
	 String selectState = "//*[@id='site-content']/form[3]/section/article[3]/div/div/select";
	 String btnNext = "a#next-button";
	 
	 String idnpanel3a = "//*[@id='site-content']/form[1]/section/article[1]/div";
	 String idnpanel3b = "//*[@id='site-content']/form[1]/section/article[2]/div";
	 String idnpanel3c = "//*[@id='site-content']/form[2]/section/article[1]/div";
	 String idnpanel3d = "//*[@id='site-content']/form[2]/section/article[2]/div";
	 String idnpanel3e = "//*[@id='site-content']/form[2]/section/article[3]/div";
	 String idnpanel3f = "//*[@id='site-content']/form[3]/section/article[1]/div";
	 String idnpanel3g = "//*[@id='site-content']/form[3]/section/article[2]/div";
	 String idnpanel3h = "//*[@id='site-content']/form[4]/section/article/div";
	 String idnpanel3i = "//*[@id='site-content']/form[5]/section/article/div";
	 
	 String idnpanel4a = "//*[@id='site-content']/form[1]/section/article/div";
	 String idnpanel4b = "//*[@id='site-content']/form[2]/section/article/div";
	 String idnpanel4c = "//*[@id='site-content']/form[3]/section/article[1]/div";
	 String idnpanel4d = "//*[@id='site-content']/form[3]/section/article[2]/div";
	 String idnpanel4e = "//*[@id='site-content']/form[4]/section/article[1]/div";
	 String idnpanel4f = "//*[@id='site-content']/form[4]/section/article[2]/div";
	 String idnpanel4g = "//*[@id='site-content']/form[5]/section/article[1]/div";
	 String idnpanel4h = "//*[@id='site-content']/form[5]/section/article[2]/div";
	 
	 
	 String idninput5a1 = "//*[@id='site-content']/form[1]/section/article[1]/div/input[1]";
	 String idninput5a2 = "//*[@id='site-content']/form[1]/section/article[1]/div/input[2]";
	 String idninput5b1 = "//*[@id='site-content']/form[1]/section/article[2]/div/input[1]";
	 String idnpanel5c = "//*[@id='site-content']/form[2]/section/article[1]/div";
	 String idnpanel5d = "//*[@id='site-content']/form[2]/section/article[2]/div";
	 String idnpanel5e = "//*[@id='site-content']/form[3]/section/article[1]/div";
	 String idnpanel5f = "//*[@id='site-content']/form[3]/section/article[2]/div";
	 
	 String scroll = "#item-list-scroll > div:nth-child(1) > span";
	 
	 String idnpanel8a = "//*[@id='site-content']/form[1]/section/article/div";
	 String idnpanel8b = "//*[@id='site-content']/form[2]/section/article/div";
	 
	 // Methods
	 
	 public void gotoIDNutiritionPage() throws Exception{
		 clickOnLink("linkText", "IDNutrition");
		 
	 }
	 
	 
	 public void gotoMyExperiencePage() throws Exception{
		 clickOnLink("linkText", "My Experience");
		 
	 }
	 
	 public void gotoIdWellnessPage() throws Exception{
		 clickOnLink("linkText", "IDWellness");
		 
	 }
	  
	 
	
	public void selectCheckboxes(String panelName) throws Exception, Exception{ // , String selectCheckboxes[]
		logInfo("inside selectCheckboxes() method");
		
		waitOnElement("xpath", panelName);
		clickOnElement("xpath", panelName);
		WebElement panel = driver().findElement(By.xpath(panelName));
		List allChecks = panel.findElements(By.className("answer"));
		System.out.println("Total check boxes = " +allChecks.size());
		
		for(int i=3;i<=allChecks.size()+2;i++){
			System.out.println("i =" +i);
			waitOnElement("xpath",panelName+"/div["+i+"]/label/span");
			WebElement chk = driver().findElement(By.xpath(panelName+"/div["+i+"]/label/span"));
			chk.click();
			// Thread.sleep(1000);
		}
	}
	 
		
	
	
	public void idlifeNutiritionAssesment() throws Exception{
		logInfo("inside idNutiritionAssesment method");
		
		
	
		clickOnButton("id", newAssessmentBtn);
		Thread.sleep(10000);
		selectCheckboxes(idnpanel2a);
		selectCheckboxes(idnpanel2b);
		selectCheckboxes(idnpanel2c);
		selectCheckboxes(idnpanel2d);
		selectFromDropdown("xpath",selectState,"byVisibleText","Alaska");
		selectCheckboxes(idnpanel2f);
		selectCheckboxes(idnpanel2g);
		selectCheckboxes(idnpanel2h);
		selectCheckboxes(idnpanel2i);
		selectCheckboxes(idnpanel2j);

		clickOnElement("xpath","//*[@id='site-content']/form[5]/section/article[1]/div/div[4]/label/span");
		selectCheckboxes(idnpanel2k);
		selectCheckboxes(idnpanel2l);
		selectCheckboxes(idnpanel2m);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		selectCheckboxes(idnpanel3a);
		selectCheckboxes(idnpanel3b);
		selectCheckboxes(idnpanel3c);
		selectCheckboxes(idnpanel3d);
		selectCheckboxes(idnpanel3e);
		selectCheckboxes(idnpanel3f);
		selectCheckboxes(idnpanel3g);
		selectCheckboxes(idnpanel3h);
		selectCheckboxes(idnpanel3i);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		selectCheckboxes(idnpanel4a);
		selectCheckboxes(idnpanel4b);
		selectCheckboxes(idnpanel4c);
		selectCheckboxes(idnpanel4d);
		selectCheckboxes(idnpanel4e);
		selectCheckboxes(idnpanel4f);
		selectCheckboxes(idnpanel4g);
		selectCheckboxes(idnpanel4h);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		inputText("xpath",idninput5a1,"7");
		inputText("xpath",idninput5a2,"20");
		inputText("xpath",idninput5b1,"10");
		selectCheckboxes(idnpanel5c);
		selectCheckboxes(idnpanel5d);
		selectCheckboxes(idnpanel5e);
		selectCheckboxes(idnpanel5f);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		clickOnElement("cssSelector", scroll);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		clickOnElement("cssSelector", scroll);
		clickOnButton("cssSelector",btnNext);
		 Thread.sleep(10000);
		
		waitOnElement("cssSelector","#cart-checkout");
		clickOnButton("cssSelector","#cart-checkout");
		waitOnElement("cssSelector","#cart-checkout-nosub");
		clickOnButton("cssSelector","#cart-checkout-nosub");
		
		WebElement cart = driver().findElement(By.xpath("//table[@id='cart-detail']/tbody"));
		List all_items = cart.findElements(By.className("line-item"));
		int total_items = all_items.size();
		
		String before_prod = "//table[@id='cart-detail']/tbody/tr[";
		String after_prod = "]/td[2]/h4";
		
		boolean isProdFound = false;
		
		if(total_items>0){
			for(int i=1;i<=total_items;i++){
				WebElement eprod = driver().findElement(By.xpath(before_prod+i+after_prod));
				String actProd = eprod.getText().trim();
				if(actProd.equalsIgnoreCase("IDNutrition")){
					logInfo("Idlife Product Assessment match found : " +actProd);
					isProdFound = true;
					break;
				}
			}
		}
		
		if(isProdFound==false){
			logInfo("IDNutrition Idlife Product Assessment match found.");
			Assert.assertTrue(isProdFound, "IDNutrition Idlife Product Assessment match found.");
			
		}
	}
	
	
	
	public void idlifeMyExperienceAssesment() throws Exception{
		logInfo("inside idlifeMyExperienceAssesment method");	
		clickOnButton("id", newAssessmentBtn);
    selectCheckboxes(idnpanel2a);
		selectCheckboxes(idnpanel2b);
		selectCheckboxes(idnpanel2c);
		selectCheckboxes(idnpanel2d);
		selectFromDropdown("xpath",selectState,"byVisibleText","Alaska");
		selectCheckboxes(idnpanel2f);
		selectCheckboxes(idnpanel2g);
		selectCheckboxes(idnpanel2h);
		selectCheckboxes(idnpanel2i);
		selectCheckboxes(idnpanel2j);
		selectCheckboxes(idnpanel2k);
		selectCheckboxes(idnpanel2l);
		selectCheckboxes(idnpanel2m);

		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		selectCheckboxes(idnpanel3a);
		selectCheckboxes(idnpanel3b);
		selectCheckboxes(idnpanel3c);
		selectCheckboxes(idnpanel3d);
		selectCheckboxes(idnpanel3e);
		selectCheckboxes(idnpanel3f);
		selectCheckboxes(idnpanel3g);
		selectCheckboxes(idnpanel3h);
		selectCheckboxes(idnpanel3i);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		selectCheckboxes(idnpanel4a);
		selectCheckboxes(idnpanel4b);
		selectCheckboxes(idnpanel4c);
		selectCheckboxes(idnpanel4d);
		selectCheckboxes(idnpanel4e);
		selectCheckboxes(idnpanel4f);
		selectCheckboxes(idnpanel4g);
		selectCheckboxes(idnpanel4h);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		inputText("xpath",idninput5a1,"7");
		inputText("xpath",idninput5a2,"20");
		inputText("xpath",idninput5b1,"10");
		selectCheckboxes(idnpanel5c);
		selectCheckboxes(idnpanel5d);
		selectCheckboxes(idnpanel5e);
		selectCheckboxes(idnpanel5f);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		clickOnElement("cssSelector", scroll);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		clickOnElement("cssSelector", scroll);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		selectCheckboxes(idnpanel8a);
		selectCheckboxes(idnpanel8b);
		clickOnButton("cssSelector",btnNext);
		Thread.sleep(10000);
		
		waitOnElement("cssSelector","#cart-checkout");
		clickOnButton("cssSelector","#cart-checkout");
		waitOnElement("cssSelector","#cart-checkout-nosub");
		clickOnButton("cssSelector","#cart-checkout-nosub");
	
		WebElement cart = driver().findElement(By.xpath("//table[@id='cart-detail']/tbody"));
		List all_items = cart.findElements(By.className("line-item"));
		int total_items = all_items.size();
		
		String before_prod = "//table[@id='cart-detail']/tbody/tr[";
		String after_prod = "]/td[2]/h4/a";
		
		boolean isProdFound = false;
		
		if(total_items>0){
			for(int i=total_items;i<=total_items;i--){
				WebElement eprod = driver().findElement(By.xpath(before_prod+i+after_prod));
				String actProd = eprod.getText().trim();
				System.out.println("actProd =" + actProd);
				if(actProd.contains("IDLife Experience")){
					logInfo("IDLife Experience Product Assessment match found : " +actProd);
					isProdFound = true;
					break;
				}
			}
		}
		
		if(isProdFound==false){
			logInfo("IDLife Experience Product Assessment match not found.");
			Assert.assertTrue(isProdFound, "IDLife Experience Product Assessment match not found.");
			
		}
	}
	
	
	
	public void validateIDwellness() throws Exception{
		 logInfo("inside validateIDwellness method ");
		 gotoIdWellnessPage();
		 String winHandleBefore = driver().getWindowHandle();
		 for(String winHandle : driver().getWindowHandles()){
		    driver().switchTo().window(winHandle);
		}
		 waitOnElement("cssSelector",expectedTitle);
		 String expectedTit = driver().findElement(By.cssSelector(expectedTitle)).getText();
		 System.out.println(expectedTit);
		 driver().close();
		 driver().switchTo().window(winHandleBefore);
		 Assert.assertEquals(expectedTit, "My Plan");
		 
	 }
		
	
}
