package vibe.billing.tests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import common.TestBase;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import vibe.users.tests.UsersMethods;

public class BillingMethods extends TestBase {
	
	UsersMethods um = new UsersMethods();
	
	
	public void navigate2Subscription() throws Exception{
		logInfo("inside navigate2Subscription() method.");
		System.out.println("inside navigate2Subscription() method.");
		driver().navigate().to(appUrl + "pyr_core/account#subscription_tab");
	}
	
	public void navigate2Enrollment(){
		logInfo("inside navigate2Enrollment() method.");
		System.out.println("inside navigate2Enrollment() method.");
		driver().navigate().to(appUrl+ "pyr_core/v2_enrollments/select_market");
	}
	
	public void go2BillingSubscriptionAdmin(String userName) throws Exception, Exception{
		logInfo("inside go2BillingSubscriptionAdmin() method.");
		System.out.println("inside go2BillingSubscriptionAdmin() method.");
		um.go2Users();
	 	um.searchUser(userName);
	 	boolean isUserFound = um.verifyUserPresent(userName);
	 	if(isUserFound){
	 		um.editUser(userName);
	 		waitOnElement("linkText", "Manage Subscription");
	 		clickOnLink("linkText", "Manage Subscription");
	 	}
	}
	

	public boolean verifyAvailableSubscriptionPlans(String subscriptionPlan) throws Exception, Exception{
		logInfo("inside verifyAvailableSubscriptionPlans() method.");
		System.out.println("inside verifyAvailableSubscriptionPlans() method.");
		boolean isSubscriptionPlanVerified = false;
	
		waitOnElement("xpath",lstSubPlans);
		List<WebElement> plans = driver().findElements(By.xpath(lstSubPlans));
		
		String before_plan = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div[";
		String after_plan = "]/div/div[2]/h3";
		
		String before_plan_select = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div[";
		String after_plan_select = "]/div/div[4]/div[2]/a";
		
		for(int i=1;i<=plans.size();i++){
			waitOnElement("xpath",before_plan+i+after_plan);
			WebElement subPlan = driver().findElement(By.xpath(before_plan+i+after_plan));
			if(subPlan.getText().trim().equalsIgnoreCase(subscriptionPlan)){
				waitOnElement("xpath",before_plan_select+i+after_plan_select);
				WebElement subPlanSelect = driver().findElement(By.xpath(before_plan_select+i+after_plan_select));
		
				if(subPlanSelect.getText().trim().equalsIgnoreCase("Selected")){
					isSubscriptionPlanVerified = true;
					break;
				}

			}
		}
		return isSubscriptionPlanVerified;

	}
	
		
	public boolean selectSubscriptionPlan(String subscriptionPlan) throws Exception, Exception{
		logInfo("inside selectSubscriptionPlan() method.");
		System.out.println("inside selectSubscriptionPlan() method.");
		boolean isSubscriptionPlanSelected = false;
		

		waitOnElement("xpath",lstSubPlans);
		List<WebElement> plans = driver().findElements(By.xpath(lstSubPlans));
		
		String before_plan = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div[";
		String after_plan = "]/div/div[2]/h3";
		
		String before_plan_select = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div[";
		String after_plan_select = "]/div/div[4]/div[2]/a";
		
		for(int i=1;i<=plans.size();i++){
			waitOnElement("xpath",before_plan+i+after_plan);
			WebElement subPlan = driver().findElement(By.xpath(before_plan+i+after_plan));
			if(subPlan.getText().trim().equalsIgnoreCase(subscriptionPlan)){
				waitOnElement("xpath",before_plan_select+i+after_plan_select);
				WebElement subPlanSelect = driver().findElement(By.xpath(before_plan_select+i+after_plan_select));
				subPlanSelect.click();
				isSubscriptionPlanSelected = true;
				break;
	
			}
		}

		return isSubscriptionPlanSelected;
	}
	
	public boolean verifyRenewSubscriptionPlan(String subscriptionPlan) throws Exception, Exception{
		logInfo("inside verifyRenewSubscriptionPlan() method.");
		System.out.println("inside verifyRenewSubscriptionPlan() method.");
		boolean isSubscriptionPlanVerified = false;

		waitOnElement("xpath",lstSubPlans);
		List<WebElement> plans = driver().findElements(By.xpath(lstSubPlans));
		
		String before_plan = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div[";
		String after_plan = "]/div/div[2]/h3";
		
		String before_plan_select = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div[";
		String after_plan_select = "]/div/div[4]/div[2]/a";
		
		
		for(int i=1;i<=plans.size();i++){
			waitOnElement("xpath",before_plan+i+after_plan);
			WebElement subPlan = driver().findElement(By.xpath(before_plan+i+after_plan));
			if(subPlan.getText().trim().equalsIgnoreCase(subscriptionPlan)){
				waitOnElement("xpath",before_plan_select+i+after_plan_select);
				WebElement subPlanSelect = driver().findElement(By.xpath(before_plan_select+i+after_plan_select));
				
				if(subPlanSelect.getText().trim().equalsIgnoreCase("Renew")){
					isSubscriptionPlanVerified = true;
					break;
				}

			}
		}

		return isSubscriptionPlanVerified;
	}
	
	public boolean verifyCreditCardInformation() throws Exception, Exception{
		logInfo("inside verifyCreditCardInformation() method.");
		System.out.println("inside verifyCreditCardInformation() method.");
		boolean isValidated = false;
		waitOnElement("xpath",lnkEditCC);
		clickOnElement("xpath",lnkEditCC);
		/*Thread.sleep(3000);*/
		waitOnElement("cssSelector",inputCCName);
		inputTextClear("cssSelector",inputCCName);
		inputTextClear("cssSelector",inputCCNumber);
		inputTextClear("cssSelector",inputExpDate);
		inputTextClear("cssSelector",inputCVV);
		inputTextClear("cssSelector",inputAddress1);
		inputTextClear("cssSelector",inputCity);
		inputTextClear("cssSelector",inputPostalCode);
		selectFromDropdown("cssSelector",inputState,"byVisibleText"," ");
		
		waitOnElement("xpath",btnSubmitCC);
		clickOnElement("xpath",btnSubmitCC);
		
		//change all the input fields for the web elements once the validation msgs are displayed properly.
		
		WebElement cardName = driver().findElement(By.cssSelector(inputCCName));
		if(cardName.getText().trim().equalsIgnoreCase(validateText)){ 
			isValidated = true;
		}
		else{
			isValidated = false;
			Assert.assertTrue(isValidated,"Unable to validate the credit card name.");
		}
		if(isValidated){
			WebElement cardNumber = driver().findElement(By.cssSelector(inputCCNumber));
			if(cardNumber.getText().trim().equalsIgnoreCase(validateText)){
				isValidated = true;
			}else{
				isValidated = false;
				Assert.assertTrue(isValidated,"Unable to validate the credit card number.");
			}
		}
		if(isValidated){
			WebElement cvv = driver().findElement(By.cssSelector(inputCVV));
			if(cvv.getText().trim().equalsIgnoreCase(validateText)){
				isValidated = true;
			}else{
				isValidated = false;
				Assert.assertTrue(isValidated,"Unable to validate the CVV.");
			}
		}
		
		if(isValidated){
			WebElement address = driver().findElement(By.cssSelector(inputAddress1));
			if(address.getText().trim().equalsIgnoreCase(validateText)){
				isValidated = true;
			}else{
				isValidated = false;
				Assert.assertTrue(isValidated,"Unable to validate the credit card address.");
			}
		}
		if(isValidated){
			WebElement expDate = driver().findElement(By.cssSelector(inputExpDate));
			if(expDate.getText().trim().equalsIgnoreCase(validateText)){
				isValidated = true;
			}else{
				isValidated = false;
				Assert.assertTrue(isValidated,"Unable to validate the credit card expiry date.");
			}
		}
		if(isValidated){
			WebElement city = driver().findElement(By.cssSelector(inputCity));
			if(city.getText().trim().equalsIgnoreCase(validateText)){
				isValidated = true;
			}else{
				isValidated = false;
				Assert.assertTrue(isValidated,"Unable to validate the city.");
			}
		}
		if(isValidated){
			WebElement postalCode = driver().findElement(By.cssSelector(inputPostalCode));
			if(postalCode.getText().trim().equalsIgnoreCase(validateText)){
				isValidated = true;
			}else{
				isValidated = false;
				Assert.assertTrue(isValidated,"Unable to validate the postal code.");
			}
		}
		if(isValidated){
			WebElement state = driver().findElement(By.cssSelector(inputState));
			if(state.getText().trim().equalsIgnoreCase(validateText)){
				isValidated = true;
			}else{
				isValidated = false;
				Assert.assertTrue(isValidated,"Unable to validate the state.");
			}
		}
		if(isValidated){
			WebElement country = driver().findElement(By.cssSelector(inputCountry));
			if(country.getText().trim().equalsIgnoreCase(validateText)){
				isValidated = true;
			}else{
				isValidated = false;
				Assert.assertTrue(isValidated,"Unable to validate the country.");
			}
		}
		
		return isValidated;
	}
	
	public boolean verifyCVVByCardType(String cardType) throws Exception, Exception{
		logInfo("inside verifyCVVByCardType() method.");
		System.out.println("inside verifyCVVByCardType() method.");
		
		boolean isValidated = false;
		waitOnElement("xpath",lnkEditCC);
		clickOnElement("xpath",lnkEditCC);
		
//		Thread.sleep(3000);
		waitOnElement("cssSelector",inputCCNumber);
		inputTextClear("cssSelector",inputCCNumber);
		inputTextClear("cssSelector",inputCVV);
		
		switch(cardType){
		case "MasterCard" :
			System.out.println("MasterCard....");
			inputText("cssSelector",inputCCNumber,txtMasterCard);
			inputText("cssSelector",inputCVV,cvvFourDigits);
			break;
		case "Visa":
			System.out.println("Visa...");
			inputText("cssSelector",inputCCNumber,txtVisaCard);
			inputText("cssSelector",inputCVV,cvvFourDigits);
			break;
		case "AmericanExpress":
			System.out.println("AmericanExpress...");
			inputText("cssSelector",inputCCNumber,txtAmericanExpressCard);
			inputText("cssSelector",inputCVV,cvvFiveDigits);
			break;
		case "Discover":
			System.out.println("Discover...");
			inputText("cssSelector",inputCCNumber,txtDiscoverCard);
			inputText("cssSelector",inputCVV,cvvFourDigits);
			break;
		}
		
		waitOnElement("xpath",btnSubmitCC);
		clickOnElement("xpath",btnSubmitCC);
		
		WebElement errorMsg = driver().findElement(By.cssSelector(inputErrorMsg));
		if(errorMsg.getText().trim().equalsIgnoreCase(txtValidateCVVByType)){
			isValidated = true;
		}else{
			isValidated = false;
		}
		
		return isValidated;
	}
	
	public boolean verifyCVVAndExpiryDate() throws Exception{
		logInfo("inside verifyCVVAndExpiryDate() method.");
		System.out.println("inside verifyCVVAndExpiryDate() method.");
		
		boolean isValidated = false;
		waitOnElement("xpath",lnkEditCC);
		clickOnElement("xpath",lnkEditCC);
		inputTextClear("cssSelector",inputCCNumber);
		inputTextClear("cssSelector",inputCVV);
		inputTextClear("cssSelector",inputExpDate);
		inputText("cssSelector",inputCCNumber,txtMasterCard);
		inputText("cssSelector",inputCVV,invalidCVV);
		inputText("cssSelector",inputExpDate,invalidExpiryDate);
		
		waitOnElement("xpath",btnSubmitCC);
		clickOnElement("xpath",btnSubmitCC);
		
		WebElement errorMsg = driver().findElement(By.cssSelector(inputErrorMsg));
		if(errorMsg.getText().trim().equalsIgnoreCase(txtValidateCVVByType)){
			isValidated = true;
		}else{
			isValidated = false;
			Assert.assertTrue(isValidated,"Unable to validate the cvv with invalid chars.");
		}
		if(isValidated){
			WebElement errorMsg1 = driver().findElement(By.cssSelector(inputErrorMsg));
			if(errorMsg1.getText().trim().equalsIgnoreCase(txtInvalidExpiryDate)){
				isValidated = true;
			}else{
				isValidated = false;
				Assert.assertTrue(isValidated,"Unable to validate the expiry date with invalid input.");
			}
		}
		return isValidated;
	}
	
	public void enrollUser(String firstName,String lastName,String email,String userName,String subscriptionPlan) throws Exception, Exception{
		logInfo("inside enrollUser() method.");
		System.out.println("inside enrollUser() method.");
		List<WebElement> listMarkets = driver().findElements(By.xpath(lstMarkets));
		String before_market = "//*[@id='main-content']/div/div/div[1]/div/div[";
		String after_market = "]/a/h3";
		
		//select market
		for(int i=1;i<=listMarkets.size();i++){
			WebElement market = driver().findElement(By.xpath(before_market+i+after_market));
			if(market.getText().trim().equalsIgnoreCase("United States")){
				market.click();
				break;
			}
		}
		
		//Fill personal Info
		
		waitOnElement("cssSelector",enrollFirstName);
		inputText("cssSelector",enrollFirstName,firstName);
		inputText("cssSelector",enrollLastName,lastName);
		inputText("cssSelector",enrollEmail,email);
		selectFromDropdown("cssSelector",enollBirthday,"byVisibleText",txtEnrollBirthday);
		inputText("cssSelector",enrollPhone,txtEnrollPhone);
		selectFromDropdown("cssSelector",enrollGender,"byVisibleText","Male");
		clickOnElement("xpath", btnEnrollContinue);
		
		waitOnElement("cssSelector",enrollUserName);
		inputTextClear("cssSelector",enrollUserName);
		inputText("cssSelector",enrollUserName,userName);
		inputTextClear("cssSelector",enrollPassword);
		inputText("cssSelector",enrollPassword,txtEnrollPassword);
		inputText("cssSelector",enrollConfirmPassword,txtEnrollPassword);
		inputText("cssSelector",enrollAddress,txtEnrollAddress);
		inputText("cssSelector",enrollCity,txtEnrollCity);
		selectFromDropdown("cssSelector",enrollState,"byVisibleText",txtEnrollState);
		inputText("cssSelector",enrollZipCode,txtEnrollZip);
		
		List<WebElement> rows = driver().findElements(By.xpath(lstSubPlanRows));
		String before = "//*[contains(@id,'edit_pyr_core_v2_enrollment')]/section/div[2]/section/div/div[@class='row'][";
		String middle = "]/div[";
		String after = "]/div[2]/h3";
		
		String before_select = "//*[contains(@id,'edit_pyr_core_v2_enrollment')]/section/div[2]/section/div/div[@class='row'][";
		String middle_select = "]/div[";
		String after_select = "]/div[2]/span[2]/div/a";
		
		for(int i=1;i<=rows.size();i++){
			List<WebElement> cols = driver().findElements(By.xpath("//*[contains(@id,'edit_pyr_core_v2_enrollment')]/section/div[2]/section/div/div[@class='row']["+i+"]/div"));
			for(int j=1;j<=cols.size();j++){
				WebElement el = driver().findElement(By.xpath(before+i+middle+j+after));
				if(el.getText().trim().contains(subscriptionPlan)){
					WebElement select = driver().findElement(By.xpath(before_select+i+middle_select+j+after_select));
					select.click();
					break;
				}
			}
		
		}
		
		//Adding Credit Card information
		
		clickOnElement("xpath", btnAddCC);
		waitOnElement("cssSelector",ccName);
		inputText("cssSelector",ccName,userName);
		
		switch(subscriptionPlan){
		case "Vibe Lite - Monthly" : //Master card
			System.out.println("MasterCard with lite monthly....");
			inputText("cssSelector",ccNumber,txtMasterCard);
			inputText("cssSelector",ccCvv,cvvThreeDigits);
			break;
		case "Vibe Lite - Yearly":
			System.out.println("Visa with lite yearly....");
			inputText("cssSelector",ccNumber,txtVisaCard);
			inputText("cssSelector",ccCvv,cvvThreeDigits);
			break;
		case "Vibe Pro - Monthly":
			System.out.println("Discover with pro monthly....");
			inputText("cssSelector",ccNumber,txtDiscoverCard);
			inputText("cssSelector",ccCvv,cvvThreeDigits);
			break;
		case "Vibe Pro - Yearly":
			System.out.println("American Express with pro yearly...");
			inputText("cssSelector",ccNumber,txtAmericanExpressCard);
			inputText("cssSelector",ccCvv,cvvFourDigits);
			break;
		}
		
		selectFromDropdown("cssSelector",ccExpiryYear,"byVisibleText",txtCCExpiryDate);
		inputText("cssSelector",ccAddress,txtEnrollAddress);
		inputText("cssSelector",ccCity,txtEnrollCity);
		selectFromDropdown("cssSelector",ccState,"byVisibleText",txtEnrollState);
		inputText("cssSelector",ccZipCode,txtEnrollZip);
		clickOnElement("xpath", btnCCSave);
//		Thread.sleep(5000);
		waitOnElement("xpath", btnEnrollContinue);
		clickOnElement("xpath", btnEnrollContinue);
		
//		Thread.sleep(5000);
		waitOnElement("cssSelector",inputTOC);
		selectRadioOrCheckbox("cssSelector",inputTOC);
		clickOnElement("xpath", btnEnrollContinue);
//		Thread.sleep(6000);
		
	}
	
	public boolean verifyCCDetails(String cardType,String userName) throws Exception{
		logInfo("inside verifyCCDetails() method.");
		System.out.println("inside verifyCCDetails() method.");
//		Thread.sleep(5000);
		boolean isVerified = false;
		
		waitOnElement("xpath",cardName);
		WebElement name = driver().findElement(By.xpath(cardName));
		if(name.getText().trim().contains("BillingUser")){
			isVerified = true;
		}else{
			isVerified = false;
			Assert.assertTrue(isVerified,"Unable to verify the card name : " + name.getText().trim());
		}
		if(isVerified){
			waitOnElement("xpath",ccCardType);
			WebElement typeOfCard = driver().findElement(By.xpath(ccCardType));
			waitOnElement("xpath",cardNumber);
			WebElement cardNo = driver().findElement(By.xpath(cardNumber));

			switch(cardType){
			case "Master" : //Master card
				if(cardNo.getText().trim().contains("XXXX-XXXX-XXXX-4444") && typeOfCard.getText().trim().contains("MASTER")){
					isVerified = true;
				}
				break;
			case "Visa":
				if(cardNo.getText().trim().contains("XXXX-XXXX-XXXX-1111") && typeOfCard.getText().trim().contains("VISA")){
					isVerified = true;
				}
				break;
			case "Discover":
				if(cardNo.getText().trim().contains("XXXX-XXXX-XXXX-1117") && typeOfCard.getText().trim().contains("DISCOVER")){
					isVerified = true;
				}
				break;
			case "AmericanExpress":
				if(cardNo.getText().trim().contains("XXXX-XXXX-XXXX-0005") && typeOfCard.getText().trim().contains("AMEX")){
					isVerified = true;
				}
				break;
			}
			if(!isVerified){
				Assert.assertTrue(isVerified,"Unable to verify the card type and card number");
			}
			
		}
		if(isVerified){
			waitOnElement("xpath",cardAddress);
			WebElement address = driver().findElement(By.xpath(cardAddress));
			if(address.getText().trim().contains(txtEnrollAddress)){
				isVerified = true;
			}else{
				isVerified = false;
				Assert.assertTrue(isVerified,"Unable to verify the card address");
			}
		}
		if(isVerified){
			waitOnElement("xpath",cardCity);
			WebElement city = driver().findElement(By.xpath(cardCity));
			if(city.getText().trim().contains(txtEnrollCity)){
				isVerified = true;
			}else{
				isVerified = false;
				Assert.assertTrue(isVerified,"Unable to verify the card city");
			}
		}
		if(isVerified){
			waitOnElement("xpath",cardState);
			WebElement state = driver().findElement(By.xpath(cardState));
			if(state.getText().trim().contains(txtEnrollState)){
				isVerified = true;
			}else{
				isVerified = false;
				Assert.assertTrue(isVerified,"Unable to verify the card state");
			}
		}
		if(isVerified){
			waitOnElement("xpath",cardZip);
			WebElement zipCode = driver().findElement(By.xpath(cardZip));
			if(zipCode.getText().trim().contains(txtEnrollZip)){
				isVerified = true;
			}else{
				isVerified = false;
				Assert.assertTrue(isVerified,"Unable to verify the card zipcode");
			}
		}
		return isVerified;
	}
	
	
	public boolean upgradeSubscriptionBetSameSecurityGroups(String oldSubPlan,String newSubPlan,String nextBillingDate) throws Exception, Exception{
		logInfo("inside upgradeSubscriptionBetSameSecurityGroups() method.");
		System.out.println("inside upgradeSubscriptionBetSameSecurityGroups() method.");
//		Thread.sleep(5000);
		boolean isSubscriptionUpgraded = false;
		isSubscriptionUpgraded = selectSubscriptionPlan(newSubPlan);
		waitOnElement("xpath",btnSumbitSubscription);
		clickOnElement("xpath",btnSumbitSubscription);
//		Thread.sleep(5000);
		if(isSubscriptionUpgraded){
			//confirmationMessage("Subscription was successfully updated.");
			isSubscriptionUpgraded = verifySubscriptionPlanHeading(oldSubPlan,"Current Subscription");
		}
		else{
			isSubscriptionUpgraded = false;
			Assert.assertTrue(isSubscriptionUpgraded,"Unable to select the subscription plan and verify the subscription heading");
		}
		if(isSubscriptionUpgraded){
			if(!verifySubscriptionPlanExpiry(oldSubPlan,nextBillingDate)){
				isSubscriptionUpgraded = false;
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to verify the billing date.");
			}
		}
		if(isSubscriptionUpgraded){
			if(!verifySubscriptionPlanHeading(newSubPlan,"Next Subscription plan")){
				isSubscriptionUpgraded = false;
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to verify the next subscription plan");
			}
		}
		if(isSubscriptionUpgraded){
			isSubscriptionUpgraded = verifyRenewSubscriptionPlan(oldSubPlan);
			if(!isSubscriptionUpgraded){
				isSubscriptionUpgraded = false;
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to verify the Renew button for the old subscription plan");
			}
		}
		
		return isSubscriptionUpgraded;
	}
	
	public boolean upgradeSubscriptionBetDiffSecurityGroups(String oldSubPlan,String newSubPlan,String nextBillingDate) throws Exception, Exception{
		logInfo("inside upgradeSubscriptionBetDiffSecurityGroups() method.");
		System.out.println("inside upgradeSubscriptionBetDiffSecurityGroups() method.");
//		Thread.sleep(5000);
		boolean isSubscriptionUpgraded = false;
		
		isSubscriptionUpgraded = selectSubscriptionPlan(newSubPlan);
		waitOnElement("xpath",btnSumbitSubscription);
		clickOnElement("xpath",btnSumbitSubscription);
		
		if(isSubscriptionUpgraded){
//			Thread.sleep(5000);
//			confirmationMessage("Subscription was successfully updated.");
			isSubscriptionUpgraded = verifySubscriptionPlanHeading(newSubPlan,"Current Subscription");
			if(!isSubscriptionUpgraded){
				Assert.assertTrue(isSubscriptionUpgraded,"Unable to verify the subscription plan title.");
			}
		}
		
		if(!verifySubscriptionPlanExpiry(newSubPlan,nextBillingDate)){
			isSubscriptionUpgraded = false;
			Assert.assertTrue(isSubscriptionUpgraded,"Unable to verify the expiry date for current subscription.");
		}
		
		return isSubscriptionUpgraded;
	}
	
	public boolean verifySubscriptionPlanHeading(String subscriptionPlan,String subscriptionPlanHeading) throws Exception{
		logInfo("inside verifySubscriptionPlanHeading() method.");
		System.out.println("inside verifySubscriptionPlanHeading() method.");
		boolean isSubscriptionPlanVerified = false;

		waitOnElement("xpath",lstSubPlans);
		List<WebElement> plans = driver().findElements(By.xpath(lstSubPlans));
		
		String before_plan = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div[";
		String after_plan = "]/div/div[2]/h3";
		
		String before_sub_heading = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div[";
		String after_sub_heading = "]/div/div[1]/div/div[1]";
		
		for(int i=1;i<=plans.size();i++){
			waitOnElement("xpath",before_plan+i+after_plan);
			WebElement subPlan = driver().findElement(By.xpath(before_plan+i+after_plan));
			if(subPlan.getText().trim().equalsIgnoreCase(subscriptionPlan)){
				waitOnElement("xpath",before_sub_heading+i+after_sub_heading);
				WebElement subPlanHeading = driver().findElement(By.xpath(before_sub_heading+i+after_sub_heading));
				if(subPlanHeading.getText().trim().equalsIgnoreCase(subscriptionPlanHeading)){
					isSubscriptionPlanVerified = true;
					break;
				}

			}
		}

		return isSubscriptionPlanVerified;
		
	}
	
	public boolean verifySubscriptionPlanExpiry(String subscriptionPlan,String billingDate) throws Exception{
		logInfo("inside verifySubscriptionPlanExpiry() method.");
		System.out.println("inside verifySubscriptionPlanExpiry() method.");
		boolean isExpiryVerified = false;

		waitOnElement("xpath",lstSubPlans);
		List<WebElement> plans = driver().findElements(By.xpath(lstSubPlans));
		
		String before_plan = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div[";
		String after_plan = "]/div/div[2]/h3";
		
		String before_billing_date = "//*[contains(@id,'edit_pyr_core_subscription')]/div[1]/div/div[2]/div[";
		String after_billing_date = "]/div/div[1]/div/div[3]";
		
		for(int i=1;i<=plans.size();i++){
			waitOnElement("xpath",before_plan+i+after_plan);
			WebElement subPlan = driver().findElement(By.xpath(before_plan+i+after_plan));
			if(subPlan.getText().trim().equalsIgnoreCase(subscriptionPlan)){
				waitOnElement("xpath",before_billing_date+i+after_billing_date);
				WebElement subBillingDate = driver().findElement(By.xpath(before_billing_date+i+after_billing_date));
				if(subBillingDate.getText().contains(billingDate)){
					isExpiryVerified = true;
					break;
				}

			}
		}

		return isExpiryVerified;
		
	}
	
	
	// Change subscription plan for the user at Admin
	
		public void changeSubscriptionPlan4User(String subscriptionName) throws Exception, Exception{
				
			logInfo("inside changeSubscriptionPlan4User() method");
			waitOnElement("xpath","//*[@id='main-content']/table");
			
			String drpSubscriptionPlanName = "//*[@id='pyr_core_subscription_subscription_plan_id']";
			selectFromDropdown("xpath",drpSubscriptionPlanName,"byVisibleText",subscriptionName);
			clickOnElement("xpath","//input[@value='Update Subscription']");		
			confirmationMessage("Subscription was successfully updated.");
			
			}
	
	public boolean verifySubscriptionInAdmin(String subscriptionPlan,String billingDate) throws Exception, Exception{
		logInfo("inside verifySubscriptionInAdmin() method.");
		System.out.println("inside verifySubscriptionInAdmin() method.");
		boolean isSubscriptionPlanVerified = false;
		boolean isSubscriptionNotesVerified = false;
		
		waitOnElement("cssSelector",inputActive);
		WebElement subscription = driver().findElement(By.cssSelector(inputActive));
		isSubscriptionPlanVerified = subscription.isSelected();
		
		if(!isSubscriptionPlanVerified){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to verify the active checkbox for the subscription on the admin.");
		}
		
		if(isSubscriptionPlanVerified){
			WebElement nextBillingDate = driver().findElement(By.cssSelector(inputNextBillingDate));
			if(nextBillingDate.getAttribute("value").trim().equalsIgnoreCase(billingDate)){
				isSubscriptionPlanVerified = true;
			}
			else{
				isSubscriptionPlanVerified = false;
				Assert.assertTrue(isSubscriptionPlanVerified,"Unable to verify the billing date on the admin.");
			}
		}
		
		
		if(isSubscriptionPlanVerified){
			String planName = getCurrentSelectionFromDropdown("cssSelector",inputSubPlan);
			if(planName.equalsIgnoreCase(subscriptionPlan)){
				isSubscriptionPlanVerified = true;
			}
			else{
				isSubscriptionPlanVerified = false;
				Assert.assertTrue(isSubscriptionPlanVerified,"Unable to verify the subscription plan on the admin.");
			}
		}
		
		if(isSubscriptionPlanVerified){
			List<WebElement> lstSubNotes = driver().findElements(By.xpath("//*[@class='subscription-note-content']"));
			for(WebElement element : lstSubNotes){
				if(element.getText().contains(billingDate)){
					isSubscriptionNotesVerified = true;
					break;
				}
				
			}
			if(!isSubscriptionNotesVerified){
				isSubscriptionPlanVerified = isSubscriptionNotesVerified;
				Assert.assertTrue(isSubscriptionNotesVerified,"Unable to verify the billing date under subscription notes  on the admin.");
			}
			
			if(isSubscriptionNotesVerified){
				for(WebElement element : lstSubNotes){
					if(element.getText().contains(subscriptionPlan)){
						isSubscriptionPlanVerified = true;
						break;
					}
					
				}
				if(!isSubscriptionNotesVerified){
					isSubscriptionPlanVerified = isSubscriptionNotesVerified;
					Assert.assertTrue(isSubscriptionNotesVerified,"Unable to verify the subscription plan under subscription notes  on the admin.");
				}
			}
		}
		
		return isSubscriptionPlanVerified;
	}
	
	public boolean downgradeSubscription(String oldSubPlan,String newSubPlan,String nextBillingDate) throws Exception, Exception{
		logInfo("inside downgradeSubscriptionBetSameSecGroups() method.");
		System.out.println("inside downgradeSubscriptionBetSameSecGroups() method.");
//		Thread.sleep(5000);
		boolean isSubscriptionDowngraded = false;
		isSubscriptionDowngraded = selectSubscriptionPlan(newSubPlan);
		
		if(!isSubscriptionDowngraded){
			Assert.assertTrue(isSubscriptionDowngraded,"Unable to select the subscription plan when downgrade.");
		}
		
		waitOnElement("xpath",btnSumbitSubscription);
		clickOnElement("xpath",btnSumbitSubscription);
		
		if(isSubscriptionDowngraded){
			Thread.sleep(5000);
//			confirmationMessage("Subscription was successfully updated.");
			isSubscriptionDowngraded = verifySubscriptionPlanHeading(oldSubPlan,"Current Subscription");
			if(!isSubscriptionDowngraded){
				Assert.assertTrue(isSubscriptionDowngraded,"Unable to verify the subscription plan title.");
			}
		}
		
		if(isSubscriptionDowngraded){
			if(!verifySubscriptionPlanExpiry(oldSubPlan,nextBillingDate)){
				isSubscriptionDowngraded = false;
				Assert.assertTrue(isSubscriptionDowngraded,"Unable to verify the billing expiry date.");
			}
		}
		
		if(isSubscriptionDowngraded){
			if(!verifySubscriptionPlanHeading(newSubPlan,"Next Subscription plan")){
				isSubscriptionDowngraded = false;
				Assert.assertTrue(isSubscriptionDowngraded,"Unable to verify the next subscription plan");
			}
		}
		
		if(isSubscriptionDowngraded){
			isSubscriptionDowngraded = verifyRenewSubscriptionPlan(oldSubPlan);
			if(!isSubscriptionDowngraded){
				isSubscriptionDowngraded = false;
				Assert.assertTrue(isSubscriptionDowngraded,"Unable to verify the Renew button for the old subscription plan");
			}
		}
		
		return isSubscriptionDowngraded;
	}
	

	public void setUserActiveOrInactive(String userName, boolean isActive) throws Exception, Exception{
		logInfo("inside activeUsers() method");
		waitOnElement("xpath","//*[@id='main-content']/table");
		
		boolean isUserActive;
		WebElement e = driver().findElement(By.xpath("//*[@id='main-content']/table/tbody/tr[2]/td[1]/div[1]/div[1]/label/input"));
		isUserActive = e.isSelected();
		System.out.println("isSelected =" +isUserActive);
		if(isUserActive!=isActive){
			e.click();
			clickOnElement("xpath","//input[@value='Update Subscription']");
			confirmationMessage("Subscription was successfully updated.");
			verifySubscriptionNotesPresent("Active changed from ");
		}
	}
	
	
	// Change subscription plan for the user at Admin side
	
	public void changeSubscriptionPlanInAdmin(String subscriptionName) throws Exception, Exception{
			
		logInfo("inside changeSubscriptionPlanInAdmin() method");
		System.out.println("inside changeSubscriptionPlanInAdmin() method");
		waitOnElement("xpath","//*[@id='main-content']/table");
		
		String drpSubscriptionPlanName = "//*[@id='pyr_core_subscription_subscription_plan_id']";
		selectFromDropdown("xpath",drpSubscriptionPlanName,"byVisibleText",subscriptionName);
		clickOnElement("xpath","//input[@value='Update Subscription']");
		confirmationMessage("Subscription was successfully updated.");
	}
	
	
	// Change next billing plan for the user at Admin
	
	public void changeSubscriptionNextBillingDateInAdmin(String nextBillingDate) throws Exception, Exception{
		logInfo("inside changeSubscriptionNextBillingDate4User() method");
		waitOnElement("xpath","//*[@id='main-content']/table");
		
		// selectCalendarDate("xpath","//button[@class='btn btn-default']/span", nextBillingDate);
		
		inputTextClear("cssSelector","#pyr_core_subscription_next_billing_date");
		inputText("cssSelector","#pyr_core_subscription_next_billing_date",nextBillingDate);
		clickOnElement("xpath","//input[@value='Update Subscription']");
//		Thread.sleep(5000);
		
		confirmationMessage("Subscription was successfully updated.");
		}
	
	
	
	public boolean verifySubscriptionNotesPresent(String notes) throws Exception, Exception{
		logInfo("inside verifySubscriptionNotesPresent() method");
		boolean isNotesFound = false;
		waitOnElement("xpath","//*[@class='subscription-notes']");
		List<WebElement> allNotes = driver().findElements(By.xpath("//*[@class='subscription-note-content']"));
		System.out.println("Total notes =" +allNotes.size());
		for(WebElement note : allNotes){
			if(note.getText().trim().contains(notes.trim())){
				logInfo(notes + " match found.");
				isNotesFound=true;
				break;
			}
		}
		return isNotesFound;
	}
	
	
	public void setFreeCycleBilling(String freeCycleNotes) throws Exception, Exception{
		logInfo("inside setFreeCycleBilling() method");
		
		waitOnElement("xpath","//*[@id='btn_givefreecycle']/i");
		clickOnElement("xpath","//*[@id='btn_givefreecycle']/i");
//		Thread.sleep(3000);
		waitOnElement("xpath","//*[@id='notes_free_cycle']");
		inputText("xpath","//*[@id='notes_free_cycle']",freeCycleNotes);
		
		clickOnElement("xpath","//*[@id='modal_free_cycle']/div/div[1]/div[3]/button");
		confirmationMessage("Successfully updated Next Billing Date");
	}
	
	
	public void setRerunBilling() throws Exception, Exception{
		logInfo("inside setRerunBilling() method");
		
		waitOnElement("xpath","//*[@id='btn_rerunbilling']/i");
		clickOnElement("xpath","//*[@id='btn_rerunbilling']/i");
		
		try{
			confirmAlert();
		} catch(Exception e){
			e.getMessage();
		}
		
		confirmationMessage("Successfully ran billing for this user");
	}
	
	
	public void go2SetupSubscriptionPage(){
		System.out.println("inside go2SetupSubscriptionPage() method");
		logInfo("inside go2SetupSubscriptionPage() method");
		driver().navigate().to(appUrl + "pyr_core/subscription_plans");
	}
	
	public void setAllowDowngradeOption(boolean isAllowDowngradable) throws Exception, Exception{
		logInfo("inside setAllowDowngradeOption() method");
		waitOnElement("xpath","//*[@id='downgrade_options_allow']");
		boolean isDowngradable;
		WebElement e = driver().findElement(By.xpath("//*[@id='downgrade_options_allow']"));
		isDowngradable = e.isSelected();
		System.out.println("isSelected =" +isDowngradable);
		if(isDowngradable!=isAllowDowngradable){
			e.click();
		}
		
		clickOnElement("xpath","//input[@value='Save Changes']");
		confirmationMessage("SubscriptionPlans were successfully updated.");
	}
	
	public boolean verifyAvailableSubscriptionPlansAtUser(String inCorrectPlans[]) throws Exception, Exception{
		logInfo("inside verifyAvailableSubscriptionsPlanAtUser() method.");
		boolean isMismatchFound = false;
		
		waitOnElement("xpath","//div[@class='panel panel-default']/div[@class='panel-body']");
		WebElement panel = driver().findElement(By.xpath("//div[@class='panel panel-default']/div[@class='panel-body']"));
		List allRows = panel.findElements(By.className("row"));
		System.out.println("Total Rows =" +allRows.size());
		
		String before_name = "//div[@class='panel panel-default']/div[@class='panel-body']/div[";
		String after_name = "]/div/div/h3";
		
		int listSize = inCorrectPlans.length;
				
		if(allRows.size()>0){
			for(int i=1;i<=allRows.size();i++){
				WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
				String act_planName = e.getText().trim();
				for(int j=0;j<listSize;j++){
					String exp_planName = inCorrectPlans[j].toString().trim();
					if(act_planName.equalsIgnoreCase(exp_planName)){
						isMismatchFound = true;
						System.out.println(exp_planName + " is incorrect subscription plan");
						break;		// can be uncommented later
					}
				}
				
				System.out.println("act_planName =" +act_planName);
			}
		}
		return isMismatchFound;
	}	

	
	// update billing info
	
	
	public void editBillingInfo() throws Exception, Exception{
		logInfo("inside editBillingInfo() method");
		waitOnElement("linkText","Edit");
		
		clickOnLink("linkText","Edit");
		
		inputTextClear("cssSelector",inputCCName);
		inputText("cssSelector",inputCCName,adminUser_text);
		
		inputTextClear("cssSelector",inputCCNumber);
		inputText("cssSelector",inputCCNumber,txtVisaCard);
		
		inputTextClear("cssSelector",inputExpDate);
		inputText("cssSelector",inputExpDate,"12/20");
		
		inputTextClear("cssSelector",inputCVV);
		inputText("cssSelector",inputCVV,cvvThreeDigits);
		
		inputTextClear("cssSelector",inputAddress1);
		inputText("cssSelector",inputAddress1,"Address1");
		
		inputTextClear("cssSelector",inputCity);
		inputText("cssSelector",inputCity,"California");
		
		inputTextClear("cssSelector",inputPostalCode);
		inputText("cssSelector",inputPostalCode,"44444");
		
		selectFromDropdown("cssSelector",inputState,"byVisibleText","Utah");
		selectFromDropdown("cssSelector",inputCountry,"byVisibleText","United States");
		clickOnElement("xpath","//*[contains(@id,'edit_pyr_core_credit_card')]/div[11]/input");
		
	}
	
	
	public boolean verifyRefundStatus(String cardNo) throws Exception, Exception{
		logInfo("inside verifyRefundStatus() method");
		boolean isCardMatchFound = false;
		
		waitOnElement("xpath","//*[@id='billing_history']/div[2]");
		WebElement tblRefund = driver().findElement(By.xpath("//*[@id='billing_history']/div[2]"));
		List allRows = tblRefund.findElements(By.xpath("//div[@style='padding:10px;']"));
		System.out.println("Total rows =" +allRows.size());
		
		String before_card = "//*[@id='billing_history']/div[2]/div[";
		String after_card = "]/span[3]";
		
		String before_refund = "//*[@id='billing_history']/div[2]/div[";
		String after_refund = "]/span[4]/a";
		
		for(int i=allRows.size();i>=1;i--){
			WebElement x = driver().findElement(By.xpath(before_card+i+after_card));
			String card = x.getText().trim();
			if(card.contains(cardNo)){
				isCardMatchFound=true;
				logInfo(cardNo + " match found in refund table.");
				WebElement refund = driver().findElement(By.xpath(before_refund+i+after_refund));
				refund.click();
				submitRefundFee();
				confirmationMessage("Successfully refunded");
				break;
			}
		}
		return isCardMatchFound;
	}
	
	
	public void submitRefundFee() throws Exception, Exception{
		logInfo("inside submitRefundFee() method");
		waitOnElement("cssSelector","#amount");
		inputText("cssSelector","#amount","100");
		inputText("cssSelector","#notes_refund","Unhappy with the service");
		clickOnElement("cssSelector","#btn_refund");
	}
	
	
	
	
	public void updateSetupSubscriptionPlan(String subName, String isActive, String isFirsttime, String isInactive, String currency, String secGrp, String isExisting, String price, String billFrq, String setupFee, String markets ) throws Exception, Exception{
		logInfo("inside editSetupSubscriptionPlan() method");
		waitOnElement("xpath","//*[@id='main-content']/form/table[1]");
		
		WebElement tblSubscription = driver().findElement(By.xpath("//*[@id='main-content']/form/table[1]/tbody"));
		List allRows = tblSubscription.findElements(By.tagName("tr"));
		
		String before_name = "//*[@id='main-content']/form/table[1]/tbody/tr[";
		String after_name = "]/td[2]";
		String before_edit = "//*[@id='main-content']/form/table[1]/tbody/tr[";
		String after_edit = "]/td[14]/a/i";
		
		for(int i=1;i<=allRows.size();i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			WebElement edit = driver().findElement(By.xpath(before_edit+i+after_edit));
			
			if(name.equalsIgnoreCase(subName)){
				edit.click();
				
				// Enter subscription details here
				
				waitOnElement("cssSelector", "#subscription_plan_active");
				inputTextClear("cssSelector","#subscription_plan_name");
				inputText("cssSelector","#subscription_plan_name",subName);
				
				selectFromDropdown("cssSelector", "#subscription_plan_active","byVisibleText",isActive);
				selectFromDropdown("cssSelector", "#subscription_plan_first_time_subscribers","byVisibleText",isFirsttime);
				selectFromDropdown("cssSelector", "#subscription_plan_inactive_subscribers","byVisibleText",isInactive);
				selectFromDropdown("cssSelector", "#subscription_plan_currency","byVisibleText",currency);
				selectFromDropdown("cssSelector", "#subscription_plan_security_group_id","byVisibleText",secGrp);
				
				inputTextClear("cssSelector","#subscription_plan_price");
				inputText("cssSelector","#subscription_plan_price",price);
				
				selectFromDropdown("cssSelector", "#subscription_plan_billing_frequency","byVisibleText",billFrq);
				selectFromDropdown("cssSelector", "#subscription_plan_existing_subscribers","byVisibleText",isExisting);
				
				inputTextClear("cssSelector","#subscription_plan_setup_fee");
				inputText("cssSelector","#subscription_plan_setup_fee",setupFee);
				
				clickOnElement("xpath","//input[@value='Update Subscription Plan']");
				logInfo("clicked on 'Update Subscription Plan' button.");
				break;
			}
		}
	}
	
	public void updatePromotionDetails(String subName,String discPrice, String validDays,String validUntil, String startDate, String endDate, String secGrp, boolean isActive, boolean isFirstTimeUsers, boolean isExistingUsers, boolean isInactive ) throws Exception, Exception{
		logInfo("inside updatePromotionDetails() method");
		System.out.println("inside updatePromotionDetails() method");
				
		waitOnElement("xpath","//*[@id='main-content']/form/table[1]");
		
		WebElement tblSubscription = driver().findElement(By.xpath("//*[@id='main-content']/form/table[1]/tbody"));
		List allRows = tblSubscription.findElements(By.tagName("tr"));
		
		String before_name = "//*[@id='main-content']/form/table[1]/tbody/tr[";
		String after_name = "]/td[2]";
		String before_edit = "//*[@id='main-content']/form/table[1]/tbody/tr[";
		String after_edit = "]/td[14]/a/i";
		
		for(int i=1;i<=allRows.size();i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			WebElement edit = driver().findElement(By.xpath(before_edit+i+after_edit));
			
			if(name.equalsIgnoreCase(subName)){
				edit.click();
		
//				Thread.sleep(5000);
				clickOnLink("linkText", "Add Promotion");
//				Thread.sleep(3000);
				waitOnElement("cssSelector","#pyr_core_promotion_template_discount_price");
				inputTextClear("cssSelector","#pyr_core_promotion_template_discount_price");
				inputText("cssSelector","#pyr_core_promotion_template_discount_price",discPrice);
				
				waitOnElement("cssSelector","#pyr_core_promotion_template_valid_for_days");
				inputTextClear("cssSelector","#pyr_core_promotion_template_valid_for_days");
				inputText("cssSelector","#pyr_core_promotion_template_valid_for_days",validDays);
				
				waitOnElement("cssSelector","#pyr_core_promotion_template_valid_until");
				inputTextClear("cssSelector","#pyr_core_promotion_template_valid_until");
				inputText("cssSelector","#pyr_core_promotion_template_valid_until",validUntil);
				
				waitOnElement("cssSelector","#pyr_core_promotion_template_start_date");
				inputTextClear("cssSelector","#pyr_core_promotion_template_start_date");
				inputText("cssSelector","#pyr_core_promotion_template_start_date",startDate);
				
				waitOnElement("cssSelector","#pyr_core_promotion_template_end_date");
				inputTextClear("cssSelector","#pyr_core_promotion_template_end_date");
				inputText("cssSelector","#pyr_core_promotion_template_end_date",endDate);
				
				waitOnElement("cssSelector","#pyr_core_promotion_template_security_group_id");
				selectFromDropdown("cssSelector", "#pyr_core_promotion_template_security_group_id","byVisibleText",secGrp);
				
				waitOnElement("cssSelector","#pyr_core_promotion_template_active");
				WebElement eactive = driver().findElement(By.cssSelector("#pyr_core_promotion_template_active"));
				boolean iseactiveSelected = eactive.isSelected();
					if(iseactiveSelected!=isActive){
						eactive.click();
					}
				
				WebElement efirstTimeUsers = driver().findElement(By.cssSelector("#pyr_core_promotion_template_first_time_subscribers"));
				boolean firstTimeUsers = efirstTimeUsers.isSelected();
					if(firstTimeUsers!=isFirstTimeUsers){
						efirstTimeUsers.click();
					}
				
				
				WebElement eExistingUsers = driver().findElement(By.cssSelector("#pyr_core_promotion_template_existing_subscribers"));
				boolean existingUsers = eExistingUsers.isSelected();
					if(existingUsers!=isExistingUsers){
						eExistingUsers.click();
					}	
				
				
				WebElement einActiveUsers = driver().findElement(By.cssSelector("#pyr_core_promotion_template_inactive_subscribers"));
				boolean inActiveUsers = einActiveUsers.isSelected();
					if(existingUsers!=isInactive){
						einActiveUsers.click();
					}	
				
				clickOnElement("xpath","//button[contains(text(),'Save')]");
				logInfo("clicked on 'Save promotions' button.");
				
				clickOnElement("xpath","//input[@value='Update Subscription Plan']");
				logInfo("clicked on 'Update Subscription Plan' button.");
				break;
			}
		}
	}
	
	
	public void verifyPromotionsList(String subName, boolean isActive, String secGrp, String disc, String promo) throws Exception, Exception{ //(boolean isActiveUser , String disc, String period, boolean isFirsttimeUser, boolean isExistingUser, boolean isInactive) throws Exception, Exception{
		logInfo("inside verifyPromotionsList() method");
		System.out.println("inside verifyPromotionsList() method");
		
		waitOnElement("xpath","//*[@id='main-content']/form/table[1]");
		
		WebElement tblSubscription = driver().findElement(By.xpath("//*[@id='main-content']/form/table[1]/tbody"));
		List allRows = tblSubscription.findElements(By.tagName("tr"));
		
		String before_name = "//*[@id='main-content']/form/table[1]/tbody/tr[";
		String after_name = "]/td[2]";
		String before_edit = "//*[@id='main-content']/form/table[1]/tbody/tr[";
		String after_edit = "]/td[14]/a/i";
		
		for(int i=1;i<=allRows.size();i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			WebElement edit = driver().findElement(By.xpath(before_edit+i+after_edit));
			
			if(name.equalsIgnoreCase(subName)){
				edit.click();
				
				
				// Verify promotion table
				
				waitOnElement("xpath","//*[@id='promotion_templates_list']/div[2]");
		
				WebElement etblPromotions = driver().findElement(By.xpath("//*[@id='promotion_templates_list']"));
				List allPromotions = etblPromotions.findElements(By.className("row"));
				System.out.println("Total Promotions =" + allPromotions.size());
				
				String before_chkActive = "//*[@id='promotion_templates_list']/div[";
				String after_chkActive = "]/div[2]/input";
				
				String before_disc = "//*[@id='promotion_templates_list']/div[";
				String after_disc = "]/div[3]";
				
				String before_promoperiod = "//*[@id='promotion_templates_list']/div[";
				String after_promoperiod = "]/div[4]";
				
				for(int j=allPromotions.size();j>=2; j--){
					
					WebElement active = driver().findElement(By.xpath(before_chkActive+j+after_chkActive));
					boolean isActualActive = active.isSelected();
					if(isActualActive!=isActive){
						Assert.assertTrue(isActualActive!=isActive);
					}
					
					WebElement edisc = driver().findElement(By.xpath(before_disc+j+after_disc));
					String actdisc = edisc.getText().trim();
					System.out.println("actdisc =" +actdisc);
					disc = secGrp + " @ " + disc;
					disc = disc.trim();
					System.out.println("expdisc =" +disc);
					
					if(!actdisc.contains(disc)){
						Assert.assertTrue(false, " discount details not matching.");
					}
					
					WebElement epromo = driver().findElement(By.xpath(before_promoperiod+j+after_promoperiod));
					String actpromo = epromo.getText().trim();
					System.out.println("actpromo =" +actpromo);
					promo = promo + " days";
					System.out.println("exppromo =" +promo);
					if(!actpromo.contains(promo)){
						Assert.assertTrue(false, " promotion details not matching.");
					}
					break;
				}
				break;
			}
		}
	}
	
	 public void changeSubscriptionPlan() throws Exception, Exception{
		 logInfo("inside changeSubscriptionPlan() method");
		 System.out.println("inside changeSubscriptionPlan() method");
	     boolean isSubscriptionUpgraded = false;
	     isSubscriptionUpgraded = selectSubscriptionPlan(txtSubPlanLiteYearly);
	     waitOnElement("linkText","Cancel");
	     clickOnElement("linkText","Cancel");
	 }
	
	 public void updateSubscriptionDetails(String subPlan,String billingDate) throws Exception, Exception{
		 logInfo("inside updateSubscriptionDetails() method");
		 System.out.println("inside updateSubscriptionDetails() method");
		 waitOnElement("xpath","//*[@id='pyr_core_subscription_active']");
		 WebElement isActive = driver().findElement(By.xpath("//*[@id='pyr_core_subscription_active']"));
		 if(!isActive.isSelected()){
			 selectRadioOrCheckbox("xpath", "//*[@id='pyr_core_subscription_active']");
		 }
		 selectFromDropdown("xpath", "//*[@id='pyr_core_subscription_subscription_plan_id']", "byVisibleText", subPlan);
		 inputTextClear("xpath","//*[@id='pyr_core_subscription_next_billing_date']");
		 inputText("xpath","//*[@id='pyr_core_subscription_next_billing_date']",billingDate);
		 clickOnElement("xpath","//input[@value='Update Subscription']");
	 }
	 
	 public void updateSubscriptions(String userName,String subPlan) throws Exception{
		 logInfo("inside updateSubscriptions() method");
		 System.out.println("inside updateSubscriptions() method");
//		 Thread.sleep(5000);
		 logOut();
		 logIn(userName,nonadminPwd_text);
		 navigate2Subscription();
		 selectSubscriptionPlan(subPlan);
		 waitOnElement("xpath",btnSumbitSubscription);
		 clickOnElement("xpath",btnSumbitSubscription);
//		 Thread.sleep(5000);
	 }
	 
	 public void setAllowDowngrade() throws Exception{
		 logInfo("inside updateSubscriptions() method");
		 System.out.println("inside updateSubscriptions() method");
		 go2SetupSubscriptionPage();
		 WebElement allowDowngrade = driver().findElement(By.xpath("//*[@id='downgrade_options_allow']"));
		 if(!allowDowngrade.isSelected()){
			 selectRadioOrCheckbox("xpath", "//*[@id='downgrade_options_allow']");
		 }
		 clickOnElement("xpath","//*[@value='Save Changes']");
	 }
	 
	 
}
