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
import org.testng.Reporter;

import vibe.inbox.tests.InboxMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.tasks.tests.TaskMethods;
import common.TestBase;
import common.readProp;

public class IDLCouponMethods extends TestBase{
	
	  MyProfileMethods profile = new MyProfileMethods();
	  InboxMethods inbox = new InboxMethods();
	  
	  public void navigate2Coupons() throws Exception{
			logInfo("inside navigate2Coupons() method.");
			System.out.println("inside navigate2Coupons() method.");
			driver().navigate().to(appUrl + "crm/my_coupons");
	  }
	  

public void addCoupon(String recipientID) throws Exception{
                logInfo("inside addCoupon() method.");
                System.out.println("inside addCoupon() method.");
                waitOnElement("xpath","//input[@type='submit']");
                clickOnElement("xpath","//input[@type='submit']");
              // waitOnElement("xpath",txtCouponCode);  //not required
                //clickOnElement("xpath",txtCouponCode);
                waitOnElement("xpath",btnContinueCoupon);
                clickOnElement("xpath",btnContinueCoupon);
                String endsAtDate = getDateByMonth( 1, "MM/dd/yyyy");
                waitOnElement("xpath",endsAt);
                inputText("xpath",endsAt,endsAtDate);
                inputText("xpath",couponMessage,"New Coupon");
                inputText("xpath",couponRecipientsTo,recipientID);
                waitOnElement("cssSelector",ddlRecipient);
                clickOnElement("cssSelector",ddlRecipient);
                waitOnElement("cssSelector",btnSendCoupon);
                clickOnElement("cssSelector",btnSendCoupon);
                
         }

	  
	  public String getMailID() throws Exception{
		  logInfo("inside getMailID() method.");
		  System.out.println("inside getMailID() method.");
		  driver().navigate().to(appUrl + "pyr_core/account#account-info");
		  waitOnElement("linkText","Notifications");
		  clickOnElement("linkText","Notifications");
          String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
          System.out.println(selfmailId);
          return selfmailId;
	  }
	  
	  public boolean verifyCoupon(String couponTitle) throws Exception{
		  logInfo("inside verifyCoupon() method.");
		  System.out.println("inside verifyCoupon() method.");
		  boolean isVerified = false;
		  driver().navigate().to(appUrl + "crm/coupons");
		  waitOnElement("xpath","//*[@class='coupons-grid']/div[1]/div/div/div/div[2]/img[contains(@alt,'Coupon')]");
		  List<WebElement> coupons = driver().findElements(By.xpath("//*[@class='coupons-grid']/div[1]/div/div/div/div[2]/img[contains(@alt,'Coupon')]"));
		  String before = "//*[@class='coupons-grid']/div[1]/div[";
		  String after = "]/div/div/div[2]/img[contains(@alt,'Coupon')]";
		  
		  for(int i=1;i<=coupons.size();i++){
			  WebElement coupon = driver().findElement(By.xpath(before+i+after));
			  if(coupon.getAttribute("alt").contains(couponTitle)){
				  isVerified = true;
				  break;
			  }
			  
		  }
		  
		  return isVerified;
	  }
	  
	  public boolean verifyCouponMail(String couponSubject) throws Exception{
		  logInfo("inside verifyCouponMail() method.");
		  System.out.println("inside verifyCouponMail() method.");
		  boolean isEmailVerified = false;
		  inbox.go2Inbox();
		  isEmailVerified = verifyVibeInboxMail(couponSubject);
		  return isEmailVerified;

	  }
	  
	  public boolean verifyVibeInboxMail(String subject){
			logInfo("inside verifyVibeInboxMail() method..");
			boolean isEmailVerified = false;
			WebElement e = driver().findElement(By.xpath(tblInboxBody));
			List allRows = e.findElements(By.tagName("tr"));
			int count = allRows.size();
			
			String before = "//*[@id='bulk-form']/table/tbody/tr[";
			String after = "]/td[5]/span[2]";	
			
			String beforeChk = "//*[@id='bulk-form']/table/tbody/tr[";
			String afterChk = "]/td[1]/input";
			
			int matchCnt=0;
			
			for(int i=1;i<=count-1;i++){
				WebElement x = driver().findElement(By.xpath(before+i+after));
				String act_subject = x.getText().trim();
				if (act_subject.contains(subject)) {
					isEmailVerified = true;
					WebElement chk = driver().findElement(By.xpath(beforeChk+i+afterChk));
					chk.click();
					x.click();
					Reporter.log(subject + " match found.");
					logInfo(subject + " match found.");
					Assert.assertTrue(act_subject.contains(subject), "subject \'" + subject + "\' email match found in inbox.");
					matchCnt++;
					break;
				} 
			}
			
			if(matchCnt==0){
				logInfo(subject + " match not found.");
				Reporter.log(subject + " match not found.");
				Assert.assertTrue(false, "subject \'" + subject + "\' email match could not be found in inbox.");
			}
			return isEmailVerified;
		}
	  
	   public void validateCouponEmail(String recipientID) throws Exception{
		  logInfo("inside validateCouponEmail() method.");
		  System.out.println("inside validateCouponEmail() method.");
		  String endsAtDate = getDateByMonth( 1, "MM/dd/yyyy");
		  waitOnElement("xpath",endsAt);
		  inputText("xpath",endsAt,endsAtDate);
		  inputText("xpath",couponMessage,"New Coupon");
		  inputText("xpath",couponRecipientsTo,recipientID);
		  waitOnElement("cssSelector",ddlRecipient);
		  clickOnElement("cssSelector",ddlRecipient);
		  inputText("xpath",couponRecipientsTo,recipientID);
		  confirmationMessage("One Email allowed for a coupon");
		  
	  }
	   
	   public boolean validateCouponRecipient() throws Exception{
			  logInfo("inside validateCouponRecipient() method.");
			  System.out.println("inside validateCouponRecipient() method.");
			  boolean isValidate = false;
			  waitOnElement("xpath","//input[@type='submit']");
			  clickOnElement("xpath","//input[@type='submit']");
			  waitOnElement("xpath",txtCouponCode);
			  clickOnElement("xpath",txtCouponCode);
			  clickOnElement("xpath",btnContinueCoupon);
			  clickOnElement("cssSelector",btnSendCoupon);
			  Thread.sleep(5000);
			  waitOnElement("cssSelector",".bootbox-body");
			  String validateMessage = driver().findElement(By.cssSelector(".bootbox-body")).getText();
			  if(validateMessage.contains("You don't have any valid recipients.")){
				  isValidate = true;
				  waitOnElement("xpath", "//button[text()='OK']");
				  clickOnButton("xpath", "//button[text()='OK']");
			  }
			  
			  return isValidate;
			  
	  }
	  
}

