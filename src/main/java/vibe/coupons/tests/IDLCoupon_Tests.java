package vibe.coupons.tests;

import java.util.List;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.inbox.tests.InboxMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.tasks.tests.TaskMethods;
import common.Priority;
import common.TestBase;
import common.readProp;


@Priority(42)
public class IDLCoupon_Tests extends IDLCouponMethods {
	
	public static String vibeRecipient_text =  "";
	
	readProp prop = new readProp();
	
	@Test(priority=4201)
	public void createCoupon() throws Exception{
		System.out.println("inside createCoupon() Test");
		logInfo("inside createCoupon() Test");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		String recipientID = getMailID();
		navigate2Coupons();
		addCoupon(recipientID);
		boolean isCouponVerified = verifyCoupon("Coupon 01");
		if(!isCouponVerified){
			Assert.assertTrue(isCouponVerified, "Unable to verify the coupon.");
		}
		else{
			boolean isCouponMailVerified = verifyCouponMail("New Coupon");
			if(!isCouponMailVerified){
				Assert.assertTrue(isCouponVerified, "Unable to verify the coupon email.");
			}
		}

	}
	
	@Test(priority=4202)
	public void validateCoupon() throws Exception{
		System.out.println("inside validateCouponEmail() Test");
		logInfo("inside validateCouponEmail() Test");
		String recipientID = getMailID();
		navigate2Coupons();
		boolean isValidate = validateCouponRecipient();
		if(!isValidate){
			System.out.println("fdsf");
			Assert.assertTrue(isValidate, "Able to validate the coupon with multiple recipients.");
			waitOnElement("xpath","//*[@id='page']/div[8]/div/div/div[1]/div/div[3]/div/div[2]/button");
			clickOnElement("xpath","//*[@id='page']/div[8]/div/div/div[1]/div/div[3]/div/div[2]/button");
			Thread.sleep(5000);
		}
		else{
			validateCouponEmail(recipientID);
		}
	}
	
	@Test(priority=4203)
	public void reloginAsAdmin() throws Exception{
		System.out.println("inside reloginAsAdmin() Test");
		logInfo("inside reloginAsAdmin() Test");
		userLogout();
	}	

}

 	
 	
 	
 

