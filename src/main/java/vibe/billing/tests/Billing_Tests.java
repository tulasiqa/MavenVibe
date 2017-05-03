package vibe.billing.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import common.Priority;
import common.TestBase;
import vibe.users.tests.UsersMethods;


@Priority(38)
public class Billing_Tests extends BillingMethods {
	
	UsersMethods um = new UsersMethods();
	
	@Test(priority=3800)
	public void enableBillingUsers() throws Exception, Exception{
		
		String user1 = "automation.enroll1";
		String user2 = "automation.enroll2";
		String user3 = "automation.enroll3";
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		
		logInfo("Inside enableBillingUsers() test..");
		System.out.println("Inside enableBillingUsers() test..");
		
		//go to admin & update subscriptions
		go2BillingSubscriptionAdmin(user1);
		updateSubscriptionDetails(txtSubPlanLiteMonthly,billingDate);
		go2BillingSubscriptionAdmin(user2);
		updateSubscriptionDetails(txtSubPlanLiteMonthly,billingDate);
		go2BillingSubscriptionAdmin(user3);
		updateSubscriptionDetails(txtSubPlanProMonthly,billingDate);
		
		setAllowDowngrade();
		
		//go back to user side & update subscriptions
		back2Office();
		updateSubscriptions(user1,txtSubPlanLiteMonthly);
		updateSubscriptions(user3,txtSubPlanProMonthly);
	}
	//TC3712
	//@Test(priority=3801)
	public void enrollLiteMonthlyUser() throws Exception, Exception{
		logInfo("Inside enrollLiteMonthlyUser() test..");
		System.out.println("Inside enrollLiteMonthlyUser() test..");
		navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail1,txtEnrollUserName1,txtSubPlanLiteMonthly);
		logOut();
		logIn(txtEnrollUserName1,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanLiteMonthly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanLiteMonthly);
		}

		boolean isCCInfoVerified = verifyCCDetails("Master",txtEnrollUserName1);
		if(isCCInfoVerified==false){
			Assert.assertTrue(isCCInfoVerified,"Unable to verify the credit card details for the subscription plan : "+txtSubPlanLiteMonthly);
		}
		logOut();
		logIn(adminUser_text,adminPwd_text);
	}
	

	//TC3713
	//@Test(priority=3802)
	public void enrollLiteYearlyUser() throws Exception, Exception{
		logInfo("Inside enrollLiteYearlyUser() test..");
		System.out.println("Inside enrollLiteYearlyUser() test..");
		navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail2,txtEnrollUserName2,txtSubPlanLiteYearly);
		logOut();
		logIn(txtEnrollUserName2,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanLiteYearly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanLiteYearly);
		}
		
		boolean isCCInfoVerified = verifyCCDetails("Visa",txtEnrollUserName2);
		if(isCCInfoVerified==false){
			Assert.assertTrue(isCCInfoVerified,"Unable to verify the credit card details for the subscription plan : "+txtSubPlanLiteYearly);
		}
		logOut();
		logIn(adminUser_text,adminPwd_text);
	}
	
	//TC3714
	//@Test(priority=3803)
	public void enrollProMonthlyUser() throws Exception, Exception{
		logInfo("Inside enrollProMonthlyUser() test..");
		System.out.println("Inside enrollProMonthlyUser() test..");
		navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail3,txtEnrollUserName3,txtSubPlanProMonthly);
		logOut();
		logIn(txtEnrollUserName3,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanProMonthly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanProMonthly);
		}
		
		boolean isCCInfoVerified = verifyCCDetails("Discover",txtEnrollUserName3);
		if(isCCInfoVerified==false){
			Assert.assertTrue(isCCInfoVerified,"Unable to verify the credit card details for the subscription plan : "+txtSubPlanProMonthly);
		}
		logOut();
		logIn(adminUser_text,adminPwd_text);
	}
	
	//TC3715
	//@Test(priority=3804)
	public void enrollProYearlyUser() throws Exception, Exception{
		logInfo("Inside enrollProYearlyUser() test..");
		System.out.println("Inside enrollProYearlyUser() test..");
		navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail4,txtEnrollUserName4,txtSubPlanProYearly);
		logOut();
		logIn(txtEnrollUserName4,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanProYearly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanProYearly);
		}
		
		boolean isCCInfoVerified = verifyCCDetails("AmericanExpress",txtEnrollUserName4);
		if(isCCInfoVerified==false){
			Assert.assertTrue(isCCInfoVerified,"Unable to verify the credit card details for the subscription plan : "+txtSubPlanProYearly);
		}

	}
	
	//TC3696
	@Test(priority=3805)
	public void verifySubscriptionsPlansForLiteMonthly() throws Exception, Exception{
		String txtEnrollUserName1 = "enroll1";
		
		logInfo("Inside verifySubscriptionsPlans() test..");
		System.out.println("Inside verifySubscriptionsPlans() test..");
//		Thread.sleep(5000);
		logOut();
		logIn(txtEnrollUserName1,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanLiteMonthly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanLiteMonthly);
		}
	}
	
   //TC3697
	@Test(priority=3806)
	public void verifySubscriptionsPlansForLiteYearly() throws Exception, Exception{
		String txtEnrollUserName2 = "enroll2";
		
		logInfo("Inside SubscriptionsPlansForLiteYearly() test..");
		System.out.println("Inside SubscriptionsPlansForLiteYearly() test..");
		logOut();
		logIn(txtEnrollUserName2,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanLiteYearly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanLiteYearly);
		}
	}
	
	//TC3698
	@Test(priority=3807)
	public void verifySubscriptionsPlansForProMonthly() throws Exception, Exception{
		String txtEnrollUserName3 = "enroll3";
		
		logInfo("Inside SubscriptionsPlansForProMonthly() test..");
		System.out.println("Inside SubscriptionsPlansForProMonthly() test..");
		logOut();
		logIn(txtEnrollUserName3,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanProMonthly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanProMonthly);
		}
	}
	
	//TC3699
	@Test(priority=3808)
	public void verifySubscriptionsPlansForProYearly() throws Exception, Exception{
		String txtEnrollUserName4 = "enroll4";
		
		logInfo("Inside SubscriptionsPlansForProYearly() test..");
		System.out.println("Inside SubscriptionsPlansForProYearly() test..");
		logOut();
		logIn(txtEnrollUserName4,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionPlanVerified = verifyAvailableSubscriptionPlans(txtSubPlanProYearly);
		if(isSubscriptionPlanVerified==false){
			Assert.assertTrue(isSubscriptionPlanVerified,"Unable to find the subscription plan : "+txtSubPlanProYearly);
		}
		
	}
	
	//TC3700 - TC3702, TC3709-TC3711
	@Test(priority=3809)
	public void validateCreditCardInfo() throws Exception, Exception{
		String txtEnrollUserName1 = "enroll1";
		
		logInfo("Inside validateCreditCardInfo() test..");
		System.out.println("Inside validateCreditCardInfo() test..");
		logOut();
		logIn(txtEnrollUserName1,txtEnrollPassword);
		navigate2Subscription();
		boolean isCCValidated = verifyCreditCardInformation();
		if(isCCValidated==false){
			Assert.assertTrue(isCCValidated,"Unable to validate the credit card name.");
		}
	}
	
	//TC3703
	@Test(priority=38010)
	public void validateCvvOfMasterCard() throws Exception, Exception{
		String txtEnrollUserName1 = "enroll1";
		
		logInfo("Inside validateCvvOfMasterCard() test..");
		System.out.println("Inside validateCvvOfMasterCard() test..");
		logOut();
		logIn(txtEnrollUserName1,txtEnrollPassword);
		navigate2Subscription();
		boolean isValidatedCVV = verifyCVVByCardType("MasterCard");
		if(isValidatedCVV==false){
			Assert.assertTrue(isValidatedCVV,"Unable to validate the cvv of Master card.");
		}
	}
	
	//TC3704
	@Test(priority=38011)
	public void validateCvvOfVisaCard() throws Exception, Exception{
		String txtEnrollUserName2 = "enroll2";
		
		logInfo("Inside validateCvvOfVisaCard() test..");
		System.out.println("Inside validateCvvOfVisaCard() test..");
		logOut();
		logIn(txtEnrollUserName2,txtEnrollPassword);
		navigate2Subscription();
		boolean isCCNameValidated = verifyCVVByCardType("Visa");
		if(isCCNameValidated==false){
			Assert.assertTrue(isCCNameValidated,"Unable to validate the cvv of Visa card.");
		}
	}
	
	//TC3705
	@Test(priority=38012)
	public void validateCvvOfDiscoverCard() throws Exception, Exception{
		String txtEnrollUserName3 = "enroll3";
		
		logInfo("Inside validateCvvOfDiscoverCard() test..");
		System.out.println("Inside validateCvvOfDiscoverCard() test..");
		logOut();
		logIn(txtEnrollUserName3,txtEnrollPassword);
		navigate2Subscription();
		boolean isValidatedCVV = verifyCVVByCardType("Discover");
		if(isValidatedCVV==false){
			Assert.assertTrue(isValidatedCVV,"Unable to validate the cvv of Discover card.");
		}
	}
		
	//TC3706
	@Test(priority=38013)
	public void validateCvvOfAmericanExpressCard() throws Exception, Exception{
		String txtEnrollUserName4 = "enroll4";
		
		logInfo("Inside validateCvvOfAmericanExpressCard() test..");
		System.out.println("Inside validateCvvOfAmericanExpressCard() test..");
		logOut();
		logIn(txtEnrollUserName4,txtEnrollPassword);
		navigate2Subscription();
		boolean isCCNameValidated = verifyCVVByCardType("AmericanExpress");
		if(isCCNameValidated==false){
			Assert.assertTrue(isCCNameValidated,"Unable to validate the cvv of American Express card.");
		}
	}
	
	//TC3707 & TC3708
	@Test(priority=38014)
	public void validateInvalidCvvAndExpiryDate() throws Exception, Exception{
		String txtEnrollUserName4 = "enroll4";
		logInfo("Inside validateInvalidCvvAndExpiryDate() test..");
		System.out.println("Inside validateInvalidCvvAndExpiryDate() test..");
		logOut();
		logIn(txtEnrollUserName4,txtEnrollPassword);
		navigate2Subscription();
		boolean isCvvValidated = verifyCVVAndExpiryDate();
		if(isCvvValidated==false){
			Assert.assertTrue(isCvvValidated,"Unable to validate the cvv & expiry date with invalid inputs .");
		}
	}
	
	// ***************************Upgrade scenarios **************************************//
	
	//TC3716
	@Test(priority=38015)
	public void upgradeFromLiteMonthly2LiteYearly() throws Exception{
		String txtEnrollUserName1 = "enroll1";
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside upgradeFromLiteMonthly2LiteYearly() test..");
		System.out.println("Inside upgradeFromLiteMonthly2LiteYearly() test..");
		logOut();
		logIn(txtEnrollUserName1,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionUpgraded = upgradeSubscriptionBetSameSecurityGroups(txtSubPlanLiteMonthly,txtSubPlanLiteYearly,billingDate);
		if(isSubscriptionUpgraded==false){
			Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite monthly to lite yearly.");
		}
	}
	
	//TC3717
	@Test(priority=38016)
	public void verifySubFromLiteMonthly2LiteYearlyAdmin() throws Exception{
		String txtEnrollUserName1 = "enroll1";
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteMonthly2LiteYearlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteMonthly2LiteYearlyAdmin() test..");
		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName1);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanLiteMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from lite monthly to lite yearly on the admin side.");
		}
	}
	
	//TC3718
	@Test(priority=38017)
	public void upgradeFromLiteMonthly2ProMonthly() throws Exception{
		String txtEnrollUserName1 = "automation.enroll1";
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside upgradeFromLiteMonthly2ProMonthly() test..");
		System.out.println("Inside upgradeFromLiteMonthly2ProMonthly() test..");
		back2Office();
		logOut();
		logIn(txtEnrollUserName1,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionUpgraded = upgradeSubscriptionBetDiffSecurityGroups(txtSubPlanLiteMonthly,txtSubPlanProMonthly,billingDate);
		if(isSubscriptionUpgraded==false){
			Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite monthly to pro monthly.");
		}
	}
	
	//TC3719
	@Test(priority=38018)
	public void verifySubFromLiteMonthly2ProMonthlyAdmin() throws Exception{
		String txtEnrollUserName1 = "automation.enroll1"; 
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteMonthly2ProMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteMonthly2ProMonthlyAdmin() test..");
//		Thread.sleep(5000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName1);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from lite monthly to pro monthly on the admin side.");
		}
	}
	
	//TC3720
	@Test(priority=38019)
	public void upgradeFromLiteMonthly2ProYearly() throws Exception{
		String txtEnrollUserName11 = "enroll11";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside upgradeFromLiteMonthly2ProYearly() test..");
		System.out.println("Inside upgradeFromLiteMonthly2ProYearly() test..");
		/*navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail11,txtEnrollUserName11,txtSubPlanLiteMonthly);*/
		back2Office();
		logOut();
		logIn(txtEnrollUserName11,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionUpgraded = upgradeSubscriptionBetDiffSecurityGroups(txtSubPlanLiteMonthly,txtSubPlanProYearly,billingDate);
		if(isSubscriptionUpgraded==false){
			Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite monthly to pro yearly.");
		}
	}
	
	//TC3721
	@Test(priority=38020)
	public void verifySubFromLiteMonthly2ProYearlyAdmin() throws Exception{
		String txtEnrollUserName11 = "enroll11";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteMonthly2ProYearlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteMonthly2ProYearlyAdmin() test..");

		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName11);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from lite monthly to pro yearly on the admin side.");
		}
	}
	
	//TC3722
	@Test(priority=38021)
	public void upgradeFromLiteYearly2ProMonthly() throws Exception{
		String txtEnrollUserName2 = "enroll2";
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside upgradeFromLiteYearly2ProMonthly() test..");
		System.out.println("Inside upgradeFromLiteYearly2ProMonthly() test..");
		back2Office();
		logOut();
		logIn(txtEnrollUserName2,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionUpgraded = upgradeSubscriptionBetDiffSecurityGroups(txtSubPlanLiteYearly,txtSubPlanProMonthly,billingDate);
		if(isSubscriptionUpgraded==false){
			Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite yearly to pro monthly.");
		}
	}
	
	//TC3723
	@Test(priority=38022)
	public void verifySubFromLiteYearly2ProMonthlyAdmin() throws Exception{
		String txtEnrollUserName2 = "enroll2";
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteYearly2ProMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteYearly2ProMonthlyAdmin() test..");

		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName2);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from lite yearly to pro monthly on the admin side.");
		}
	}
	
	//TC3724
	@Test(priority=38023)
	public void upgradeFromLiteYearly2ProYearly() throws Exception{
		String txtEnrollUserName21 = "enroll21";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside upgradeFromLiteYearly2ProYearly() test..");
		System.out.println("Inside upgradeFromLiteYearly2ProYearly() test..");
		
		/*navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail21,txtEnrollUserName21,txtSubPlanLiteYearly);
		Thread.sleep(3000);*/
		back2Office();
		logOut();
		logIn(txtEnrollUserName21,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionUpgraded = upgradeSubscriptionBetDiffSecurityGroups(txtSubPlanLiteYearly,txtSubPlanProYearly,billingDate);
		if(isSubscriptionUpgraded==false){
			Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from lite yearly to pro yearly.");
		}
	}
	
	//TC3725
	@Test(priority=38024)
	public void verifySubFromLiteYearly2ProYearlyAdmin() throws Exception{
		String txtEnrollUserName21 = "enroll21";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteYearly2ProYearlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteYearly2ProYearlyAdmin() test..");

		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName21);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from lite yearly to pro yearly on the admin side.");
		}
	}
	
	//TC3726
	@Test(priority=38025)
	public void upgradeFromProMonthly2ProYearly() throws Exception{
		String txtEnrollUserName3 = "enroll3";
		
		String billingDate =  getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside upgradeFromProMonthly2ProYearly() test..");
		System.out.println("Inside upgradeFromProMonthly2ProYearly() test..");
		back2Office();
		logOut();
		logIn(txtEnrollUserName3,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionUpgraded = upgradeSubscriptionBetSameSecurityGroups(txtSubPlanProMonthly,txtSubPlanProYearly,billingDate);
		if(isSubscriptionUpgraded==false){
			Assert.assertTrue(isSubscriptionUpgraded,"Unable to upgrade the subscription from pro monthly to pro yearly.");
		}
	}
	
	//TC3727
	@Test(priority=38026)
	public void verifySubFromProMonthly2ProYearlyAdmin() throws Exception{
		String txtEnrollUserName3 = "enroll3";
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProMonthly2ProYearlyAdmin() test..");
		System.out.println("Inside verifySubFromProMonthly2ProYearlyAdmin() test..");

		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName3);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the upgraded subscription details from pro monthly to pro yearly on the admin side.");
		}
	}
	
	//********************************** Downgrade scenarios***********************************************
	
	
	//TC3728
	@Test(priority=38027)
	public void downgradeFromProYearly2ProMonthly() throws Exception{
		String txtEnrollUserName4 = "enroll4";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside downgradeFromProYearly2ProMonthly() test..");
		System.out.println("Inside downgradeFromProYearly2ProMonthly() test..");
		back2Office();
		logOut();
		logIn(txtEnrollUserName4,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanProYearly,txtSubPlanProMonthly,billingDate);
		if(isSubscriptionDowngraded==false){
			Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro yearly to pro monthly.");
		}
	}
	
	//TC3729
	@Test(priority=38028)
	public void verifySubFromProYearly2ProMonthlyAdmin() throws Exception{
		String txtEnrollUserName4 = "enroll4";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProYearly2ProMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromProYearly2ProMonthlyAdmin() test..");

		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName4);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from pro yearly to pro monthly on the admin side.");
		}
	}
	
	//TC3730
	@Test(priority=38029)
	public void downgradeFromProYearly2LiteYearly() throws Exception{
		String txtEnrollUserName4 = "enroll4";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside downgradeFromProYearly2LiteYearly() test..");
		System.out.println("Inside downgradeFromProYearly2LiteYearly() test..");
		back2Office();
		logOut();
		logIn(txtEnrollUserName4,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanProYearly,txtSubPlanLiteYearly,billingDate);
		if(isSubscriptionDowngraded==false){
			Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro yearly to lite yearly.");
		}
	}
	
	//TC3731
	@Test(priority=38030)
	public void verifySubFromProYearly2LiteYearlyAdmin() throws Exception{
		String txtEnrollUserName4 = "enroll4";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProYearly2LiteYearlyAdmin() test..");
		System.out.println("Inside verifySubFromProYearly2LiteYearlyAdmin() test..");

		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName4);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from pro yearly to lite yearly on the admin side.");
		}
	}
	
	//TC3732
	@Test(priority=38031)
	public void downgradeFromProYearly2LiteMonthly() throws Exception{
		String txtEnrollUserName4 = "enroll4";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside downgradeFromProYearly2LiteMonthly() test..");
		System.out.println("Inside downgradeFromProYearly2LiteMonthly() test..");
		back2Office();
		logOut();
		logIn(txtEnrollUserName4,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanProYearly,txtSubPlanLiteMonthly,billingDate);
		if(isSubscriptionDowngraded==false){
			Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro yearly to lite monthly.");
		}
	}
	
	//TC3733
	@Test(priority=38032)
	public void verifySubFromProYearly2LiteMonthlyAdmin() throws Exception{
		String txtEnrollUserName4 = "enroll4";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProYearly2LiteMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromProYearly2LiteMonthlyAdmin() test..");

		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName4);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from pro yearly to lite monthly on the admin side.");
		}
	}
	
	//TC3734
	@Test(priority=38033)
	public void downgradeFromProMonthly2LiteYearly() throws Exception{
		String txtEnrollUserName31 = "automation.enroll3"; 
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside downgradeFromProMonthly2LiteYearly() test..");
		System.out.println("Inside downgradeFromProMonthly2LiteYearly() test..");
		
		/*navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail31,txtEnrollUserName31,txtSubPlanProMonthly);
		Thread.sleep(3000);*/
		back2Office();
		logOut();
		logIn(txtEnrollUserName31,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanProMonthly,txtSubPlanLiteYearly,billingDate);
		if(isSubscriptionDowngraded==false){
			Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro monthly to lite yearly.");
		}
		logOut();
		logIn(adminUser_text,adminPwd_text);
	}
	
	//TC3735
	@Test(priority=38034)
	public void verifySubFromProMonthly2LiteYearlyAdmin() throws Exception{
		String txtEnrollUserName31 = "automation.enroll3";
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProMonthly2LiteYearlyAdmin() test..");
		System.out.println("Inside verifySubFromProMonthly2LiteYearlyAdmin() test..");

		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName31);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from pro monthly to lite yearly on the admin side.");
		}
	}
	
	//TC3736
	@Test(priority=38035)
	public void downgradeFromProMonthly2LiteMonthly() throws Exception{
		String txtEnrollUserName31 = "enroll31"; //"enroll31";
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside downgradeFromProMonthly2LiteMonthly() test..");
		System.out.println("Inside downgradeFromProMonthly2LiteMonthly() test..");
		back2Office();
		logOut();
		logIn(txtEnrollUserName31,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanProMonthly,txtSubPlanLiteMonthly,billingDate);
		if(isSubscriptionDowngraded==false){
			Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from pro monthly to lite monthly.");
		}
	}
	
	//TC3737
	@Test(priority=38036)
	public void verifySubFromProMonthly2LiteMonthlyAdmin() throws Exception{
		String txtEnrollUserName31 = "enroll31";
		
		String billingDate = getDateByMonth(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromProMonthly2LiteMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromProMonthly2LiteMonthlyAdmin() test..");

		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName31);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanProMonthly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from pro monthly to lite monthly on the admin side.");
		}
	}
	
	//TC3738
	@Test(priority=38037)
	public void downgradeFromLiteYearly2LiteMonthly() throws Exception{
		String txtEnrollUserName22 = "enroll22";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside downgradeFromLiteYearly2LiteMonthly() test..");
		System.out.println("Inside downgradeFromLiteYearly2LiteMonthly() test..");
		
		/*navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail22,txtEnrollUserName22,txtSubPlanLiteYearly);
		Thread.sleep(3000);*/
		back2Office();
		logOut();
		logIn(txtEnrollUserName22,txtEnrollPassword);
		navigate2Subscription();
		boolean isSubscriptionDowngraded = downgradeSubscription(txtSubPlanLiteYearly,txtSubPlanLiteMonthly,billingDate);
		if(isSubscriptionDowngraded==false){
			Assert.assertTrue(isSubscriptionDowngraded,"Unable to downgrade the subscription from lite yearly to lite monthly.");
		}
	}
	
	//TC3739
	@Test(priority=38038)
	public void verifySubFromLiteYearly2LiteMonthlyAdmin() throws Exception{
		String txtEnrollUserName22 = "enroll22";
		
		String billingDate = getDateByYear(1, "MM/dd/yyyy");
		logInfo("Inside verifySubFromLiteYearly2LiteMonthlyAdmin() test..");
		System.out.println("Inside verifySubFromLiteYearly2LiteMonthlyAdmin() test..");

		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName22);
		boolean isUpgradeSubVerified = verifySubscriptionInAdmin(txtSubPlanLiteYearly,billingDate); 
		if(isUpgradeSubVerified==false){
			Assert.assertTrue(isUpgradeSubVerified,"Unable to verify the downgraded subscription details from lite yearly to lite monthly on the admin side.");
		}
	}
	

	@Test(priority=38039)
	public void verifyActivateUsers() throws Exception, Exception{
		String txtEnrollUserName5 = "enroll5";
		
		logInfo("inside verifyActivateUsers Test");
		System.out.println("Inside verifyActivateUsers() test..");
		
		/*navigate2Enrollment();
		enrollUser(txtEnrollFirstName,txtEnrollLastName,txtEnrollEmail5,txtEnrollUserName5,txtSubPlanLiteYearly);
		Thread.sleep(3000);*/
		
		go2BillingSubscriptionAdmin(txtEnrollUserName5);
		setUserActiveOrInactive(txtEnrollUserName5,true);
		changeSubscriptionPlanInAdmin(txtSubPlanProYearly);
		
		back2Office();
		logOut();
		logIn(txtEnrollUserName5,txtEnrollPassword);
		
		navigate2Subscription();
		boolean isSubscriptionFound = verifySubscriptionPlanHeading(txtSubPlanProYearly,"Current Subscription");
		if(isSubscriptionFound==false){
			System.out.println("isSubscriptionFound =" +isSubscriptionFound);
			Assert.assertTrue(isSubscriptionFound, " subscription did not match at the user.");
		}
		

	}
	
	@Test(priority=38040)
	public void verifyInActivateUsers() throws Exception, Exception{
		String txtEnrollUserName31 = "automation.enroll3"; 
		
		logInfo("inside verifyInActivateUsers Test");
	/*	logOut();
		logIn(adminUser_text,adminPwd_text);*/
		
		go2BillingSubscriptionAdmin(txtEnrollUserName31);
		setUserActiveOrInactive(txtEnrollUserName31,false);
		back2Office();
		logOut();
		logIn(txtEnrollUserName31,txtEnrollPassword);
//		Thread.sleep(5000);
		navigate2Subscription();
		confirmationMessage("Your subscription is not currently active.");
//		Thread.sleep(3000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
	}
	
	
	@Test(priority=38041)
	public void verifyIncorrectNextBillingDate() throws Exception, Exception{
		String txtEnrollUserName5 = "enroll5";
		
		logInfo("inside verifyIncorrectNextBillingDate Test");
//		Thread.sleep(5000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
		go2BillingSubscriptionAdmin(txtEnrollUserName5);
		String pastdt = TestBase.getDate(-2, "MM/dd/yyyy");
		System.out.println("pastdt =" +pastdt);
		changeSubscriptionNextBillingDateInAdmin(pastdt);
		waitOnElement("xpath","//span[@class='help-block']");
		WebElement eMsg = driver().findElement(By.xpath("//span[@class='help-block']"));	// error message
		String errMsg = eMsg.getText().trim();
		Assert.assertNotEquals(errMsg.equalsIgnoreCase("should not be in the past"), "Next billing date cannot be past date.");
	}
	
	
	@Test(priority=38042)
	public void verifyValidNextBillingDate() throws Exception, Exception{
		String txtEnrollUserName5 = "enroll5";
		
		logInfo("inside verifyValidNextBillingDate Test");
		go2BillingSubscriptionAdmin(txtEnrollUserName5);
		String before_change= driver().findElement(By.cssSelector("#pyr_core_subscription_next_billing_date")).getAttribute("value");
		String futuredt = TestBase.getDate(4, "MM/dd/yyyy");
		changeSubscriptionNextBillingDateInAdmin(futuredt);
		confirmationMessage("Subscription was successfully updated.");
		String after_change= driver().findElement(By.cssSelector("#pyr_core_subscription_next_billing_date")).getAttribute("value");
		String exp_value = "Next billing date changed from " + before_change + " 00:00:00 CDT" + " to " + after_change + " 00:00:00 CDT";
		System.out.println("exp_value =" +exp_value);
		Boolean isNotesFound = verifySubscriptionNotesPresent(exp_value);
		System.out.println("isNotesFound =" +isNotesFound);
		if(isNotesFound==false){
			Assert.assertTrue(isNotesFound, " Next billing date not found in the subscription notes." );
		}
	}
	
	@Test(priority=38043)
	public void setBillingFreecycle() throws Exception, Exception{
		String txtEnrollUserName1 = "automation.enroll1"; 
		logInfo("inside setBillingFreecycle Test");
//		Thread.sleep(5000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
		String freecycleNotes = "CUSTOMER REQUEST";
		go2BillingSubscriptionAdmin(txtEnrollUserName1);
		setFreeCycleBilling(freecycleNotes);
		boolean isNotesMatchFound = verifySubscriptionNotesPresent("FREE CYCLE - "+freecycleNotes);
		Assert.assertTrue(isNotesMatchFound, "FREE CYCLE - "+freecycleNotes + " match not found.");
	}
	

	@Test(priority=38044)
	public void refundBillingAmount() throws Exception, Exception{
		String txtEnrollUserName5 = "enroll5";
		
		logInfo("inside refundBillingAmount Test");
		go2BillingSubscriptionAdmin(txtEnrollUserName5);
		boolean isMatchFound = verifyRefundStatus("4444");
		if(isMatchFound==true){
			boolean isNotesMatchFound = verifySubscriptionNotesPresent("Unhappy with the service");
			Assert.assertTrue(isNotesMatchFound, "Unhappy with the service" + " match not found." );
		}
	}
	
	
	@Test(priority=38045)
	public void setSubscriptionPlanInAdmin() throws Exception, Exception{
		String txtEnrollUserName5 = "enroll5";
		
		logInfo("inside setSubscriptionPlanInAdmin Test");
		go2BillingSubscriptionAdmin(txtEnrollUserName5);
		String drpSubscriptionPlanName = "//*[@id='pyr_core_subscription_subscription_plan_id']";
		String before_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("before_change =" +before_change);
		changeSubscriptionPlanInAdmin(txtSubPlanLiteYearly);
		String after_change = getCurrentSelectionFromDropdown("xpath",drpSubscriptionPlanName);
		System.out.println("after_change =" +after_change);
		boolean isNotesMatchFound = verifySubscriptionNotesPresent("Subscription plan changed from "+before_change+" to "+after_change);
		Assert.assertTrue(isNotesMatchFound, "Subscription plan changed from "+before_change+" to "+after_change + " match not found.");
	}
	
	@Test(priority=38046)
	public void uncheckAllowDowngradeOption() throws Exception, Exception{
		String txtEnrollUserName5 = "enroll5";
		
		logInfo("inside uncheckAllowDowngradeOption Test");
		go2SetupSubscriptionPage();
		setAllowDowngradeOption(false);
		go2BillingSubscriptionAdmin(txtEnrollUserName5);
		setUserActiveOrInactive(txtEnrollUserName5,true);
		changeSubscriptionPlanInAdmin(txtSubPlanProYearly);
		
		back2Office();
		logOut();
		logIn(txtEnrollUserName5,txtEnrollPassword);
		
		navigate2Subscription();	
		boolean isSubscriptionFound = verifySubscriptionPlanHeading(txtSubPlanProYearly,"Current Subscription");
		if(isSubscriptionFound==true){
			String[] blacklistPlans = {txtSubPlanLiteYearly,txtSubPlanLiteMonthly};
			boolean isMismatchFound = verifyAvailableSubscriptionPlansAtUser(blacklistPlans);
			if(isMismatchFound==true){
				logInfo("Invlaid subscriptions found in Available subscriptions page.");
				Assert.assertFalse(isMismatchFound, "Invlaid subscriptions found in Available subscriptions page.");
			}
		}
	}
	
	
	@Test(priority=38047)
	public void updateCCInfoAtUser() throws Exception, Exception{
		String txtEnrollUserName1 = "automation.enroll1";
		try{
			logInfo("inside updateCCInfoAtUser Test");
//			Thread.sleep(3000);
			logOut();
			logIn(txtEnrollUserName1,txtEnrollPassword);
			navigate2Subscription();	
			editBillingInfo();
			confirmationMessage("credit card successfully updated.");
//			Thread.sleep(3000);
			logOut();
			logIn(adminUser_text,adminPwd_text);
		}
		catch(Exception ex){
//			Thread.sleep(3000);
			logOut();
			logIn(adminUser_text,adminPwd_text);
		}
		
	}

	@Test(priority=38048)
	public void addDiscPromotion2SubscriptionPlan() throws Exception, Exception{
		String txtEnrollUserName5 = "enroll5";
		
		logInfo("inside addPromotion2SubscriptionPlan Test");
//		Thread.sleep(5000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
		
		go2SetupSubscriptionPage();
		updateSetupSubscriptionPlan(txtSubPlanProYearly,"Yes", "Yes", "No", "USD", "pro", "Yes", "200" , "Yearly", "20" , "US" );
		go2SetupSubscriptionPage();
		updatePromotionDetails(txtSubPlanProYearly, "25.0", "10", "", "06/16/2016 09:03 AM", "06/16/2020 09:03 AM", "pro", true, true, true, false );
		confirmationMessage("Subscription plan was successfully updated");
		go2SetupSubscriptionPage();
		verifyPromotionsList(txtSubPlanProYearly, true, "pro", "25.0", "10");
		go2BillingSubscriptionAdmin(txtEnrollUserName5);
		setUserActiveOrInactive(txtEnrollUserName5,true);
		changeSubscriptionPlanInAdmin(txtSubPlanProYearly);
		
		back2Office();
		logOut();
		logIn(txtEnrollUserName5,txtEnrollPassword);
		
		navigate2Subscription();	
		boolean isSubscriptionMatchFound = verifySubscriptionPlanHeading(txtSubPlanProYearly,"Current Subscription");
		if(isSubscriptionMatchFound==false){
			Assert.assertTrue(isSubscriptionMatchFound, " subscription title did not match at user");
		}
	}
	
	
	@Test(priority=38049)
	public void addFreePromotion2SubscriptionPlan() throws Exception, Exception{
		String txtEnrollUserName5 = "enroll5";
		
		logInfo("inside addPromotion2SubscriptionPlan Test");
//		Thread.sleep(5000);
		logOut();
		logIn(adminUser_text,adminPwd_text);
		
		go2SetupSubscriptionPage();
		updateSetupSubscriptionPlan(txtSubPlanProYearly,"Yes", "Yes", "No", "USD", "pro", "Yes", "200" , "Yearly", "20" , "US" );
		go2SetupSubscriptionPage();
		updatePromotionDetails(txtSubPlanProYearly, "0.0", "10", "", "06/16/2016 09:03 AM", "06/16/2020 09:03 AM", "pro", true, true, true, false );
		confirmationMessage("Subscription plan was successfully updated");
		go2SetupSubscriptionPage();
		verifyPromotionsList(txtSubPlanProYearly, true, "pro", "0.0", "10");
		go2BillingSubscriptionAdmin(txtEnrollUserName5);
		setUserActiveOrInactive(txtEnrollUserName5,true);
		changeSubscriptionPlanInAdmin(txtSubPlanProYearly);
		
		back2Office();
		logOut();
		logIn(txtEnrollUserName5,txtEnrollPassword);
		
		navigate2Subscription();	
		boolean isSubscriptionMatchFound = verifySubscriptionPlanHeading(txtSubPlanProYearly,"Current Subscription");
		if(isSubscriptionMatchFound==false){
			Assert.assertTrue(isSubscriptionMatchFound, " subscription title did not match at user");
		}
	}
	
	@Test(priority=38050)
	public void setRerunBillingTest() throws Exception{
		String txtEnrollUserName31 = "automation.enroll3"; 
		logOut();
		logIn(adminUser_text,adminPwd_text);
		logInfo("inside setRerunBilling Test");
		go2BillingSubscriptionAdmin(txtEnrollUserName31);
		setRerunBilling();
		boolean isNotesMatchFound = verifySubscriptionNotesPresent("Next billing date changed from ");
		Assert.assertTrue(isNotesMatchFound, "Next billing date changed from " + " match not found." );
	}
	
	@Test(priority=38051)
	public void reloginAsAdmin() throws Exception{
		System.out.println("inside reloginAsAdmin() Test");
		logInfo("inside reloginAsAdmin() Test");
	    logOut();
	    logIn(adminUser_text, adminPwd_text);  		  
	}	
		
}
