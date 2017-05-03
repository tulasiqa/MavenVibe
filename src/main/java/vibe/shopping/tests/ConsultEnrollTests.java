package vibe.shopping.tests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;

import vibe.enrollment.tests.EnrollMethods;

@Priority(31)
public class ConsultEnrollTests extends ShoppingMethods{
		
	EnrollMethods em = new EnrollMethods();
		
	/*@Test(priority =3101)	  
	public void shop_ConsultantEnrollment() throws Exception{
		logInfo("TC149 -  Consultant should be upgraded to distributor and \n"
				+ "Should display Consultant ID and navigate to the distributor Home page");		
		logOut();
		openShoppingPageWithoutUser();		
		handleSecondWindow();		
		selectTaxon(taxon1);
		searchItembyNameOrSku(pr5);
		prodImageSelection();
		consultPriceIconSelect();
		isConsultantPriceModalPresent();
		selectEnrollNow();
		selectCountryForEnrollment(country1);
		packageSelection(packsText3);
		em.continueKitSelectionInEnroll();
		em.personalInformation();
		em.continuePersonalInfoInEnroll();
		em.continueProdSelectionInEnroll();
		em.creditcardBillingDetails();
		em.continueBillingInEnroll();
		em.continueShippingInfoInEnroll();	
		em.shippingMethod();
		em.continueShippingMethodInEnroll();
		em.isConitnueInShippingMethodPresent();

	} */
}

