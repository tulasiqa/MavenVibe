package vibe.setting.tests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.By;
import common.TestBase;


public class IDLSettingsMethods extends TestBase{
	
	public void go2Accounts() throws Exception{
		logInfo("inside go2Accounts() method.");
		System.out.println("inside go2Accounts() method.");
		driver().navigate().to(appUrl + "pyr_core/account#account-info");
	}
	
	public void go2SpecialSettings(){
		logInfo("inside go2SpecialSettings() method.");
		System.out.println("inside go2SpecialSettings() method.");
		driver().navigate().to(appUrl + "pyr_core/pyr_admin/get_special_settings");
	}
	
	public void go2PaymentSettings(){
		logInfo("inside go2PaymentSettings() method.");
		System.out.println("inside go2PaymentSettings() method.");
		driver().navigate().to(appUrl + "pyr_core/pyr_admin/payment_settings");
	}
	
	
	public void selectAccountsSection(String sectionName) throws InterruptedException{
		 logInfo("inside selectAccountsSection() method.");
		 System.out.println("inside selectAccountsSection() method.");
		
		 List<WebElement> accountPanelFocus = driver().findElements(By.xpath("//*[@id='main-content']/div/div[2]/ul/li"));
		 System.out.println("No of Account sections List : "+accountPanelFocus.size());   
		 String before = "//*[@id='main-content']/div/div[2]/ul/li[";
		 String after = "]/a";

         for(int i=1 ; i <= accountPanelFocus.size(); i++){
             WebElement accountSection = driver().findElement(By.xpath(before+i+after));
             if(accountSection.getText().trim().contains(sectionName)){
            	 accountSection.click();
//            	 Thread.sleep(3000);
            	 break;
            }
	    }
	}
	
	public boolean verifyAccntInfo(){
		logInfo("inside selectAccountsSection() method.");
		System.out.println("inside selectAccountsSection() method.");
		boolean isVerified = false;
		try{
			WebElement firstName =  driver().findElement(By.cssSelector(inputAccntFirstName));
			WebElement lastName =  driver().findElement(By.cssSelector(inputAccntLastName));
			if(firstName.isDisplayed() && lastName.isDisplayed()){
				isVerified = true;
			}
		}
		catch(Exception ex){
			isVerified = false;
			Assert.assertTrue(isVerified, "Unable to verify the account information.");
		}
		
		return isVerified;
				
	}
	
	public void updateAccountInfo(String firstName,String lastName,String email,String city) throws Exception{
		logInfo("inside selectAccountsSection() method.");
		System.out.println("inside selectAccountsSection() method.");
				
		waitOnElement("cssSelector",inputAccntFirstName);
		inputTextClear("cssSelector",inputAccntFirstName);
		inputText("cssSelector",inputAccntFirstName,firstName);
		inputTextClear("cssSelector",inputAccntLastName);
		inputText("cssSelector",inputAccntLastName,lastName);
		inputTextClear("cssSelector",inputAccntEmail);
		inputText("cssSelector",inputAccntEmail,email);
		inputTextClear("cssSelector",inputAccntAddress1);
		inputText("cssSelector",inputAccntAddress1,city);
		inputTextClear("cssSelector",inputAccntCity);
		inputText("cssSelector",inputAccntCity,city);
		clickOnElement("cssSelector",btnUpdateAccntInfo);
		confirmationMessage("Information Updated");
//		Thread.sleep(3000);
	}
	
	public boolean verifyCommunityProfileInfo(){
		logInfo("inside verifyCommunityProfileInfo() method.");
		System.out.println("inside verifyCommunityProfileInfo() method.");
		boolean isVerified = false;
		try{
			WebElement firstName =  driver().findElement(By.xpath(lblCommFirstName));
			WebElement lastName =  driver().findElement(By.xpath(lblCommLastName));
			if(firstName.isDisplayed() && lastName.isDisplayed()){
				isVerified = true;
			}
		}
		catch(Exception ex){
			isVerified = false;
			Assert.assertTrue(isVerified, "Unable to verify the community profile information.");
		}
		
		return isVerified;
				
	}
	
	public boolean verifyCommProfileInfoInAboutSection(String fname,String myStory) throws Exception{
		logInfo("inside verifyCommProfileInfoInAboutSection() method..");
		System.out.println("inside verifyCommProfileInfoInAboutSection() method.");
		boolean isVerified = false;
		driver().navigate().to(appUrl+"community/profile");
		WebElement firstName = driver().findElement(By.xpath("//*[@id='about-widget']/div[2]/div[1]/div[2]/div/span"));
		if(firstName.getText().trim().contains(fname)){
			isVerified = true;
		}
		else{
			Assert.assertTrue(isVerified, "Unable to verify the updated first name in About section.");
		}
		if(isVerified){
			WebElement story = driver().findElement(By.xpath("//*[@id='about-widget']/div[2]/div[2]/div[5]"));
			if(story.getText().trim().contains(myStory)){
				isVerified = true;
			}
			else{
				Assert.assertTrue(isVerified, "Unable to verify the updated My Story in About section.");
			}
		}
		if(isVerified){
			WebElement fbLink = driver().findElement(By.xpath("//*[@id='about-widget']/div[2]/div[4]/div/div/div/a"));
			if(fbLink.getAttribute("href").contains(communityFbook_text)){
				isVerified = true;
			}
			else{
				Assert.assertTrue(isVerified, "Unable to verify the facebook link in About section.");
			}
		}
    	return isVerified;
	}
	
	public boolean verifyPrivateInfoInAboutSection(String userName) throws Exception{
		logInfo("inside verifyPrivateInfoInAboutSection() method");
		System.out.println("inside verifyPrivateInfoInAboutSection() method");
		boolean isVerified = false;
		
		driver().navigate().to(appUrl+"community/wall/people");
		waitOnElement("xpath",inputPeopleSearch);
		inputText("xpath",inputPeopleSearch,userName);
//		Thread.sleep(3000);
		waitOnElement("xpath","//*[@id='people_list']/div/div[1]/div/div/div[2]/h4/a");
		clickOnElement("xpath","//*[@id='people_list']/div/div[1]/div/div/div[2]/h4/a");
		
		try{
			waitOnElement("xpath","//*[@id='about-widget']/div[2]/div[1]/div[3]/div/span/a");
			WebElement email = driver().findElement(By.xpath("//*[@id='about-widget']/div[2]/div[1]/div[3]/div/span/a"));
			WebElement phone = driver().findElement(By.xpath("//*[@id='about-widget']/div[2]/div[1]/div[4]/div/span"));
			if(email.isDisplayed() && phone.isDisplayed()){
				isVerified = false;
				Assert.assertTrue(isVerified, "Able to see the private information under Accounts section.");
			}
		}
		catch(Exception ex){
			isVerified = true;
		}
		return isVerified;
	}
	
	public boolean verifyResetCommunityInfo() throws Exception, Exception{
		logInfo("inside verifyPrivateInfoInAboutSection() method");
		System.out.println("inside verifyPrivateInfoInAboutSection() method");
		boolean isVerified = false;
		
		waitOnElement("xpath",btnEditPhone);
		clickOnElement("xpath",btnEditPhone);
//		Thread.sleep(3000);
		waitOnElement("cssSelector",inputCommPhone);
		inputTextClear("cssSelector",inputCommPhone);
		inputText("cssSelector",inputCommPhone,"1234567891");
		clickOnElement("xpath",btnCommunityPhoneSave);
		waitOnElement("linkText","Reset");
		clickOnElement("linkText","Reset");
		waitOnElement("xpath",txtDisplayPhone);
		WebElement phone = driver().findElement(By.xpath(txtDisplayPhone));
		if(phone.getText().trim().equalsIgnoreCase("12345")){
			isVerified = true;
		}
		return isVerified;
	}
	
	public boolean verifyUpdatedUserInfo(String fname, String lname) throws Exception{
		logInfo("inside verifyUpdatedUserInfo() method");
		System.out.println("inside verifyUpdatedUserInfo() method");
		boolean isVerified = false;
		clickOnElement("linkText","Login As User");
		go2Accounts();
		selectAccountsSection("Community Profile");
		waitOnElement("xpath",txtDisplayFirstName);
		WebElement firstName = driver().findElement(By.xpath(txtDisplayFirstName));
		WebElement lastName = driver().findElement(By.xpath(txtDisplayLastName));
		if(firstName.getText().trim().equalsIgnoreCase(fname) && lastName.getText().trim().equalsIgnoreCase(lname)){
			isVerified = true;
		}
//		clickOnElement("xpath",lnkBack2Admin);
//		Thread.sleep(5000);
		logOut();
		logIn(nonadminUser_text,nonadminPwd_text);
		return isVerified;
	}
	
	public boolean verifyChangeUserPassword() throws Exception, Exception{
		logInfo("inside verifyChangeUserPassword() method");
		System.out.println("inside verifyChangeUserPassword() method");
		boolean isVerified = false;
		
		waitOnElement("linkText","Security");
		clickOnElement("linkText","Security");
//		Thread.sleep(3000);
		waitOnElement("linkText",inputCurrentPwd);
		inputTextClear("cssSelector",inputCurrentPwd);
		inputText("cssSelector",inputCurrentPwd,adminPwd_text);
		inputText("cssSelector",inputNewPwd,newPassword_text);
		inputText("cssSelector",inputConfirmPwd,newPassword_text);
//		Thread.sleep(3000);
		waitOnElement("xpath",btnSecuritySave);
		clickOnElement("xpath",btnSecuritySave);
		confirmationMessage("Password updated successfully.");
		
		//validate password fields
		
		inputTextClear("cssSelector",inputCurrentPwd);
		inputText("cssSelector",inputCurrentPwd,"vibe");
		inputText("cssSelector",inputNewPwd,newPassword_text);
		inputText("cssSelector",inputConfirmPwd,newPassword_text);
		waitOnElement("xpath",btnSecuritySave);
		clickOnButton("xpath",btnSecuritySave);
		Thread.sleep(5000);
		waitOnElement("cssSelector",".help-block");
		WebElement txtValidate = driver().findElement(By.cssSelector(".help-block"));
		if(txtValidate.getText().trim().contains("is invalid")){
			isVerified = true;
		}
		else{
			isVerified = false;
			Assert.assertTrue(isVerified, "Unable to validate the current password with invalid data.");
		}
		if(isVerified){
			Thread.sleep(3000);
			waitOnElement("cssSelector",inputCurrentPwd);
			inputText("cssSelector",inputCurrentPwd,adminPwd_text);
			inputText("cssSelector",inputNewPwd,newPassword_text);
			inputText("cssSelector",inputConfirmPwd,"vibe");
			waitOnElement("xpath",btnSecuritySave);
			clickOnButton("xpath",btnSecuritySave);
			waitOnElement("cssSelector","#password_confirm");
			WebElement txtValidatePwd = driver().findElement(By.cssSelector("#password_confirm"));
			if(txtValidatePwd.getText().trim().contains("New Password and Password Confirmation values doesn't match")){
				isVerified = true;
			}
			else{
				isVerified = false;
				Assert.assertTrue(isVerified, "Unable to validate the invalid confirm password with valid password data.");
			} 
		}
		return isVerified;
	}
	
	public void verifyEmailNotificationSettings() throws Exception, Exception{
		logInfo("inside verifyEmailNotificationSettings() method");
		System.out.println("inside verifyEmailNotificationSettings() method");
		waitOnElement("linkText","Notifications");
		clickOnElement("linkText","Notifications");
		waitOnElement("xpath","//*[@id='new_email']");
		inputText("xpath","//*[@id='new_email']","sample"+TestBase.random()+"@icentris.com");
		clickOnElement("xpath","//*[@id='add_other_email']");
		confirmationMessage("Email Added");
	}
	
	public void verifyCellNotificationSettings() throws Exception, Exception{
		logInfo("inside verifyCellNotificationSettings() method");
		System.out.println("inside verifyCellNotificationSettings() method");
		waitOnElement("linkText","Notifications");
		clickOnElement("linkText","Notifications");
		waitOnElement("xpath","//*[@id='new_cell']");
		inputText("xpath","//*[@id='new_cell']","1234567891");
		clickOnElement("xpath","//*[@id='new_cell_submit']");
		confirmationMessage("Cell phone updated");
	}
	
	public void verifyPasswordRecoveryEmail() throws Exception, Exception{
		logInfo("inside verifyPasswordRecoveryEmail() method");
		System.out.println("inside verifyPasswordRecoveryEmail() method");
				
		waitOnElement("linkText","Notifications");
		clickOnElement("linkText","Notifications");
//		Thread.sleep(5000);
		waitOnElement("xpath","//*[@id='messaging']/div[1]/table/tbody/tr");
		List<WebElement> emails = driver().findElements(By.xpath("//*[@id='messaging']/div[1]/table/tbody/tr"));
		String before = "//*[@id='messaging']/div[1]/table/tbody/tr[";
		String after = "]/td[1]";
		
		String before_pwd_recovery = "//*[@id='messaging']/div[1]/table/tbody/tr[";
		String after_pwd_recovery = "]/td[3]/input";
		
	/*	selectRadioOrCheckbox("xpath","//*[@id='messaging']/div[1]/table/tbody/tr[2]/td[3]/input");
		confirmationMessage("Password recovery email updated");*/
		
		String email = getEmail(adminUser_text,appUrl);
		
		for (int i=2;i<=emails.size();i++){
			/*WebElement e = driver().findElement(By.xpath(before+i+after));
			if(e.getText().trim().equalsIgnoreCase(email.trim())){
				Thread.sleep(3000);*/
				WebElement e = driver().findElement(By.xpath(before_pwd_recovery+i+after_pwd_recovery));
				if(!e.isSelected()){
					selectRadioOrCheckbox("xpath",before_pwd_recovery+i+after_pwd_recovery);
					confirmationMessage("Password recovery email updated");
					break;
				}
//			}
		}
		
		

	}
	
	public void verifyEmailNotifications() throws Exception, Exception{
		logInfo("inside verifyEmailNotifications() method");
		System.out.println("inside verifyEmailNotifications() method");
				
		waitOnElement("linkText","Notifications");
		clickOnElement("linkText","Notifications");
		
		List<WebElement> emails = driver().findElements(By.xpath("//*[@id='messaging']/div[1]/table/tbody/tr"));
		String before = "//*[@id='messaging']/div[1]/table/tbody/tr[";
		String after = "]/td[1]";
		
		String before_notify_email = "//*[@id='messaging']/div[1]/table/tbody/tr[";
		String after_notify_email = "]/td[2]/input";
		
		selectRadioOrCheckbox("xpath", "//*[@id='messaging']/div[1]/table/tbody/tr[2]/td[2]/input");
		confirmationMessage("You will be notified via email");
		
		/*String email = getEmail(adminUser_text,appUrl);
		
		for (int i=1;i<=emails.size();i++){
			WebElement e = driver().findElement(By.xpath(before+i+after));
			if(e.getText().trim().equalsIgnoreCase(email.trim())){
				WebElement emailNotification = driver().findElement(By.xpath(before_notify_email+i+after_notify_email));
				selectRadioOrCheckbox("xpath",before_notify_email+i+after_notify_email);
				confirmationMessage("You will be notified via email");
				break;
			}
		}*/

	}
	
	public void verifyChangeLanguageSettings() throws Exception, Exception{
		logInfo("inside verifyChangeLanguageSettings() method");
		System.out.println("inside verifyChangeLanguageSettings() method");
		Thread.sleep(5000);		
		waitOnElement("linkText","Language Settings");
		clickOnElement("linkText","Language Settings");
		selectFromDropdown("xpath","//*[@id='pyr_core_language_settings_form_language']","byVisibleText","English");
		waitOnElement("xpath","//*[@id='new_pyr_core_language_settings_form']/div[4]/div/input");
		clickOnElement("xpath","//*[@id='new_pyr_core_language_settings_form']/div[4]/div/input");
		confirmationMessage("Web Office Language Settings - Successfully Updated");
	}
	
	public void enableAllAccountSideNavs() throws Exception, Exception{
		logInfo("inside enableAllAccountSideNavs() method");
		System.out.println("inside enableAllAccountSideNavs() method");
		try{
			waitOnElement("linkText","Account side nav items");
			clickOnElement("linkText","Account side nav items");
			waitOnElement("xpath","//*[contains(@id,'edit_pyr_core_app_setting')]/div/div[1]/label");
			
			List<WebElement> e = driver().findElements(By.xpath("//*[contains(@id,'edit_pyr_core_app_setting')]/div/div[1]/label"));
			String before = "//*[contains(@id,'edit_pyr_core_app_setting')]/div[";
			String after = "]/div/label/input";
			
			WebElement el = driver().findElement(By.xpath("//*[contains(@id,'edit_pyr_core_app_setting')]/div[2]/div/label/input"));
			if(!el.isDisplayed()){
				Assert.assertTrue(el.isDisplayed(),"Unable to list all the nav items.");
			}
			
			for(int i=2;i<=e.size();i++){
				WebElement nav = driver().findElement(By.xpath(before+i+after));
				if(!nav.isSelected()){
					selectRadioOrCheckbox("xpath", before+i+after);
				}
			}
			clickOnElement("xpath","//input[@value='Update']");
			}
		catch(Exception ex){
			Assert.assertTrue(false,"Unable to list all the nav items.");
		}
	}
	
	public boolean verifyCommPaymentOptions() throws Exception, Exception{
		logInfo("inside verifyCommPaymentOptions() method");
		System.out.println("inside verifyCommPaymentOptions() method");
		boolean isVerified = false;
		waitOnElement("linkText","Commission Payment");
		clickOnElement("linkText","Commission Payment");
		
		WebElement checkPayment = driver().findElement(By.xpath("//*[@id='payout-methods']/div/div[1]/div/div/h4"));
		if(checkPayment.getText().trim().equalsIgnoreCase("Check")){
			isVerified = true;
		}
		if(isVerified){
			WebElement directDeposit = driver().findElement(By.xpath("//*[@id='payout-methods']/div/div[2]/div/div/h4"));
			if(directDeposit.getText().trim().equalsIgnoreCase("Direct Deposit")){
				isVerified = true;
			}
		}
		if(isVerified){
			WebElement paymentCard = driver().findElement(By.xpath("//*[@id='payout-methods']/div/div[3]/div/div/h4"));
			if(paymentCard.getText().trim().equalsIgnoreCase("Payment Card")){
				isVerified = true;
			}
		}
		return isVerified;
	}
	
	public void enableConfigurePaymentOptions() throws Exception{
		logInfo("inside enableConfigurePaymentOptions() method.");
		System.out.println("inside enableConfigurePaymentOptions() method.");
		waitOnElement("xpath","//*[@id='payment_settings_commission_payments_check_settings_check']");
		selectFromDropdown("xpath","//*[@id='payment_settings_commission_payments_check_settings_check']","byVisibleText","Yes");
		selectFromDropdown("xpath","//*[@id='payment_settings_commission_payments_direct_deposit_settings_direct_deposit']","byVisibleText","Yes");
		selectFromDropdown("xpath","//*[@id='payment_settings_commission_payments_payment_card_settings_payment_card']","byVisibleText","Yes");
		clickOnElement("xpath","//*[@id='main-content']/div/div[1]/form/div[4]/input");
		confirmationMessage("Updated");
	}
	
	public void selectMarketsForPaymentOptions() throws Exception, Exception{
		logInfo("inside enableConfigurePaymentOptions() method.");
		System.out.println("inside enableConfigurePaymentOptions() method.");
		waitOnElement("xpath","//*[@id='main-content']/div/div[1]/form/div[contains(@class,'settings')]");
		List<WebElement> paymentMethods = driver().findElements(By.xpath("//*[@id='main-content']/div/div[1]/form/div[contains(@class,'settings')]"));
		String before = "//*[@id='main-content']/div/div[1]/form/div[";
		String after = "]/div[1]/div/div/label";
	
		for(int i=1;i<=paymentMethods.size();i++){
			WebElement payMethod = driver().findElement(By.xpath(before+i+after));
			String paymentMode = payMethod.getText().trim();
			switch (paymentMode) {
			case "Check":
				clickOnElement("xpath","//*[@id='main-content']/div/div[1]/form/div[1]/div[2]/a");
				selectMarkets();
//				Thread.sleep(5000);
				break;

			case "Direct Deposit":
				clickOnElement("xpath","//*[@id='main-content']/div/div[1]/form/div[2]/div[2]/a");
				selectMarkets();
//				Thread.sleep(5000);
				break;
			case "Payment Card":
				clickOnElement("xpath","//*[@id='main-content']/div/div[1]/form/div[3]/div[2]/a");
				selectMarkets();
//				Thread.sleep(5000);
				break;
			}
		}
		
	}
	
	public void selectMarkets() throws Exception, Exception{
		logInfo("inside enableConfigurePaymentOptions() method.");
		System.out.println("inside enableConfigurePaymentOptions() method.");
		waitOnElement("xpath","//*[@class='markets-input']/span/label/input");
		List<WebElement> markets = driver().findElements(By.xpath("//*[@class='markets-input']/span/label/input"));
		for(WebElement e : markets){
			if(!e.isSelected()){
				e.click();
			}
		}
//		Thread.sleep(5000);
		waitOnElement("xpath","//*[@id='sub-settings-create-modal']/div/div[1]/div[3]/button");
		clickOnElement("xpath","//*[@id='sub-settings-create-modal']/div/div[1]/div[3]/button");
		confirmationMessage("Successfully updated Settings");
	}
	
	
}

