package vibe.shopping.tests;

import java.awt.AWTException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.LoginCredentials;
import common.Priority;
import common.TestBase;

@Priority(33)
public class NewCustomerShopsTests extends ShoppingMethods{
	
	@Test(priority =3301)	  
	public void shop_CustomerEnrollment() throws Exception{
		
		logInfo("***************TestCases of Shopping for Customer*****************\n"
				+ " Enroll new customer for shopping and capture confirmation message.");	
		Thread.sleep(5000);
		logOut();	
		custShoppingNavigation();
		
		registerNewCustomer(custFNText, custLNText);  //shopUser  //custFNText
		confirmationMessage("Welcome! You have signed up successfully.");
		/*Assert.assertEquals(driver.getTitle(), "VIBE");	*/		
		clickOnButton("cssSelector", "div.panel-footer > button.btn.btn-success.pull-right");
			}
	
	@Test(priority =3302)	  
	public void shop_VeiwCartVerification() throws Exception{
		
		logInfo("Select Products category and add product item with quantity. \n"
				+ "Verify view cart presence and retrieve details from View Cart.");
		
		navigatePlaceOrderWithCustomer();
		searchAndAddProduct(cate2,pr2, 2);
		isViewcartPresent();
		getItemsIncart();		
	} 
	
	@Test(priority =3303)	  
	public void shop_PlaceOrderWithMultiProducts() throws Exception{
		
		logInfo("Select Products category and add multi products with their quantity. ");	
		searchAndAddProduct(cate2,pr2, 1);
		searchAndAddProduct(cate3, pr3, 3);
		selectCheckOutButton();		
	}
	
	@Test(priority=3304)
	public void shop_AutoOrderWithSingleProd() throws Exception{
		
		logInfo("Autoship with Single Product through Shipping Address, Delivery and payment.");
		
		navMyAutoshipCustomer();
		myAutoshipDirectProducts(pr2);
		isReviewProductMiniCardPresent();
		moreQuanityOfProducts(2);
		addToAutoship();
		confirmationMessage(pr2 + " has been added to your autoship order.");
		clickOnNextButtonInProducts();
		enterShippingAddressInAutoShip(cityName);		    
		clickOnNextButtonInDelivary();
		CCPaymentSection(); 	   
		clickOnConfirmButton();
		confirmationMessage("Autoship was successfully created/updated");
		navMyAutoshipCustomer();
		getAutoshipID();
	}
	
	
	@Test(priority=3305)
	public void shop_MultiAutoshipProd() throws Exception{
		
		logInfo("Multi Autoship the Products. Then add Shipping address, enter Credit card and confirm the Order.");
		
		navMyAutoshipCustomer();
		getAutoshipID() ;
		navMyAutoshipCustomer();
		myAutoshipDirectProducts(pr2);
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
	
	@Test(priority=3306)
	public void shop_WishList() throws Exception{
		
		logInfo("Add product to WishList from OrderList and verify in wishList");
		navigatePlaceOrderWithCustomer();
		productsLnkInPane();
		searchItembyNameOrSku(pr3);
		prodImageSelection();		   
		clickonAddToWishlistButton();
		navigatePlaceOrderWithCustomer();
		productsLnkInPane();
		searchItembyNameOrSku(pr2);
		prodImageSelection();		   
		clickonAddToWishlistButton() ; 
		navigateWishlistinCustomer();
		getProductsFromMyWishlist(pr2);
	}
	
	@Test(priority = 3307)
	public void outOfCustomerCredentials() throws Exception{
		
		logoutFromAdmin();
		logIn(adminUser_text,adminPwd_text);
		
	}

	
}