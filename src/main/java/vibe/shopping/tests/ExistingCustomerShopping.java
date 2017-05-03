package vibe.shopping.tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;

@Priority(32)
public class ExistingCustomerShopping extends ShoppingMethods {
	
	
	@Test(priority =3201)	  
	public void shop_ExistingCustomer() throws Exception{
		logInfo("Login as existing customer.");
		custShoppingNavigation();
		
		existingUserCredentials();
		confirmationMessage("You have successfully signed in.");
		//.confirmationMessage("Signed in successfully.");  // Yevo
		
	}
	
	@Test(priority =3202)	
	public void shop_PlaceOrderWithMultiprods() throws Exception{
		logInfo("TC_127- Login with existing customer and Place Order with multiple Products.");
		navigatePlaceOrderWithCustomer();
		searchAndAddProduct("Weight Loss","Adv Ambrotose", 1);
		navigatePlaceOrderWithCustomer();
		searchAndAddProduct("Skin Care","Sweet Almond Carrier Oil", 1);
		selectCheckOutButton()	;
		continueTillDeliveryTheProduct();
	}
	
	@Test(priority =3203)	
	public void shop_PlaceOrderWithMultiprodsbySelectingOnImage() throws Exception{
		logInfo("TC127 & TC128- Login with existing customer and Place Order with multiple Products.\n"
				+ "Adding products to Cart by selecting Product image.\n"
				+ "Go Back to Store Link on PlaceOrder Page, Verify the Taxon Pane List for confirmation.");
		
		navigatePlaceOrderWithCustomer();
		searchAndAddProductByImageselction(pr3, 4);
		navigatePlaceOrderWithCustomer();
		searchAndAddProductByImageselction(pr2, 5);
		selectCheckOutButton();
		continueTillDeliveryTheProduct();	
		goToStores();
		getNdVerifyTaxonList();
	}	
	
	@Test(priority=3204)
	public void shop_MultiAutoshipProd() throws Exception{
		logInfo("Multi Autoship the Products. Then add Shipping address, enter Credit card and confirm the Order.");
		navMyAutoshipCustomer();
		getAutoshipID();
		navMyAutoshipCustomer();
		myAutoshipDirectProducts(pr2);   // pr1
		isReviewProductMiniCardPresent();
		moreQuanityOfProducts(2);
		addToAutoship();		
		searchandAddProductDirectlysinAutoship(pr3);
		moreQuanityOfProducts(2);
		addToAutoship();			    
		clickOnNextButtonInProducts();
		enterShippingAddressInAutoShip(cityName);		    
		clickOnNextButtonInDelivary();
		CCPaymentSection(); 	   
		clickOnConfirmButton();
		confirmationMessage("Autoship was successfully created/updated");
		navMyAutoshipCustomer();
		getAutoshipID();
		getAutoshipProductList(); 
	}

	
	@Test(priority=3205, dependsOnMethods ={"shop_MultiAutoshipProd"})
	public void shop_NewProductwithexistingautoshipProductsWithVerification() throws Exception{
		logInfo("Open existing autoship & add new Product  and then shipping. ");
		navMyAutoshipCustomer();
		selectAutoshipID();
		searchandAddProductDirectlysinAutoship(pr3);
		moreQuanityOfProducts(2);
		addToAutoship();
		confirmationMessage(pr3 + " has been added to your autoship order.");
		clickOnNextButtonInProducts();
		enterShippingAddressInAutoShip(cityName);		    
		clickOnNextButtonInDelivary();
		CCPaymentSection(); 	   
		clickOnConfirmButton();
		confirmationMessage("Autoship was successfully created/updated");
		navMyAutoshipCustomer();
		getAutoshipID(); 
		getAutoshipProductList();
		navOrderHistory();
		retrieveProductsFromOrderHistory();
	}
	
	@Test(priority=3206)
	public void shop_ShareProductInFaceBook() throws Exception{
		logInfo("Select product and share the product details in FaceBook");
		navigatePlaceOrderWithCustomer();
		searchAndAddProduct(cate2, pr2, 1);
		/*selectProductsfromCategory("Products");
		searchItembyNameOrSku("Green");*/
		prodImageSelection();	
		selectFacebookIcon();
		
		shareInFaceBook();
		handleFirstWindow();
	}	
	
	@Test(priority=3207)
	public void shop_ShareProductByGooglePlus() throws Exception{
		logInfo("Select product and share the product details in GooglePlus");
		navigatePlaceOrderWithCustomer();
		selectProductsfromCategory("Products");
		searchItembyNameOrSku(pr1);
		prodImageSelection();
		selectGooglePlusIcon();
	
		googlePlusShare();
		handleFirstWindow();	
	}
	
	@Test(priority=3208)
	public void shop_VerifyPriceRange() throws Exception{
		logInfo("Select product and share the product details in GooglePlus");
		navigatePlaceOrderWithCustomer();		
		selectProductsfromCategory("Products");
		searchItembyNameOrSku(pr1);
		prodImageSelection();
		selectGooglePlusIcon();
		
		googlePlusShare();
		handleFirstWindow();	
	}
	
	@Test(priority=3209)
	public void shop_ProductsByPriceRange(){
		logInfo("Validate products based on Price Range selection.");	
		navigatePlaceOrderWithCustomer();
		selectTaxon(taxon1);
		getShoppingproducts();
		getShoppingPrice();
		/*PriceRangeSelection();*/		
		
	}
	
	
}
