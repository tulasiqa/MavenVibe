package vibe.setup.tests;

import java.util.List;

import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebElement;

import common.TestBase;

import org.openqa.selenium.By;
import org.testng.Assert;


public class Setup_SubscriptionPlan_Methods extends TestBase {
	
	public void go2SubscriptionPlan(){
		logInfo("inside go2SubscriptionPlan() method.");
		driver().navigate().to(appUrl + "pyr_core/subscription_plans");
	}
	
	public void go2UsersSubscriptionPage() throws  Exception{
		logInfo("inside go2UsersSubscriptionPage() method.");
		driver().navigate().to(appUrl + "account");
	//	verifyLinkPresent("Subscription");
		waitOnElement("linkText","Subscription");
		clickOnLink("linkText","Subscription");
		
	}
	
	public void createSubscriptionPlan(String planName) throws Exception{
		logInfo("inside createSubscriptionPlan() method.");
		verifyLinkPresent("Create New Subscription Plan");
		clickOnLink("linkText","Create New Subscription Plan");
		
		verifyElementPresent("xpath",inputSubscriptionName);
		inputText("xpath",inputSubscriptionName,planName);
		inputText("xpath",inputSubscriptionPrice,subscriptionPrice_text);
		clickOnButton("xpath",btnCreateSubscriptionPlan);
	}
	
	public void editSubscriptionMarkets(String planName) throws Exception{
		logInfo("inside editSubscriptionMarkets() method.");
		WebElement tbl = driver().findElement(By.xpath(tblSubscriptionplan));
		List allRows = tbl.findElements(By.tagName("tr"));
		int all_rows = allRows.size();
		
		String before_name = "//*[@id='main-content']/form/table[1]/tbody/tr[";
		String after_name = "]/td[2]";
		
		String after_marketsedit = "]/td[10]/a/i";
		
		for(int i=1;i<=all_rows;i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String subName = x.getText().trim();
			if(subName.equalsIgnoreCase(planName)){
				logInfo(planName + " subscription plan match found.");
				WebElement editMarkets = driver().findElement(By.xpath(before_name+i+after_marketsedit));
				editMarkets.click();
				
				// Select Market dialog
				WebElement tblSelectMarket = driver().findElement(By.xpath(tblSubscriptionSelectMarket));
				List allChks = tblSelectMarket.findElements(By.tagName("span"));
				int all_chks = allChks.size();
				String before_chk = "//form[@class='simple_form edit_pyr_core_subscription_plan']/div[@class='form-group check_boxes optional pyr_core_subscription_plan_market_ids']/span[";
				String after_chk = "]/label/input";
				
				for(int j=1;j<=all_chks;j++){
					WebElement chk = driver().findElement(By.xpath(before_chk+j+after_chk));
					chk.click();
					}
				clickOnButton("xpath",btnSaveSubscriptionSelectMarket);
				Thread.sleep(5000);
				break;
			}
		}
	}

	
	public void verifyAvailableSubscriptions(String planName) throws Exception{
		logInfo("inside verifyAvailableSubscriptions() method.");
		verifyElementPresent("xpath",tblAvailableSubscriptions);
		WebElement tblsubscriptions = driver().findElement(By.xpath(tblAvailableSubscriptions));
		List allRows = tblsubscriptions.findElements(By.tagName("div"));
		int all_rows = allRows.size();
		String before_name = "//*[@id='_new_billing_form']/div[2]/div[2]/div[";
		String after_name = "]/div/div/h3";
		boolean isSubscriptionFound = false;
		if(all_rows>0){
			for(int i=1;i<=all_rows;i++){
				WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
				String name = x.getText().trim();
				if(name.equalsIgnoreCase(planName)){
					logInfo(planName + " subscription plan found in Available Subscriptions section.");
					isSubscriptionFound = true;
					break;
				}
			}
			if(isSubscriptionFound==false){
				logInfo(planName + " subscription plan match not found in Available Subscriptions section.");
				Assert.assertTrue(isSubscriptionFound, planName + " subscription plan match not found in Available Subscriptions section.");
			}
		}
	}
}
