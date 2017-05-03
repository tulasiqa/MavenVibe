package vibe.shopping.tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;


@Priority(20)
public class ShoppingTests extends ShoppingMethods {
	

	@Test(priority= 2001)
	public void shop_CreateProduct() throws Exception{
		logInfo("Inside shop_CreateProduct() Test");
		navigate2AdminShop();
		addNewProduct(pr1);
		updateMandatoryDetailsforProduct();
	}
	
	@Test(priority =2002)	  
	public void shop_RetrieveProductsAndCategoryTypes() throws Exception{
		
		logInfo("***************TestCases of Shopping*****************\n"
				+ " Navigate to Place Order and Retrieve Shop Panel, Category,SortBy \n"
				+ "**********************************************************");
		
		navigatePlaceOrder();
		getListOfShopPanel();	
		getListFromCategory();	
		Assert.assertTrue(isAddcartTextPresent());	
	}
	
	/*@Test(priority =2003, dependsOnMethods = {"verifyShopping"})
	
	public void shop_AssertsShopping(){
		Assert.assertTrue(isAddcartTextPresent());
		
	}
	*/
	
	@Test(priority =2004)
	public void shop_GetAndAddproducts() throws Exception{
		
		logInfo("***************TestCases of Shopping*****************\n"
				+ " Get all available products \n"
				+ " Search the Product and add to cart with Quanity \n"
				+ " retrieve total products from Cart \n"
				+ "and enter the shipping details and try to save and capture the alert message.");
		
		getShoppingproducts();
		searchAndAddProduct(cate2,pr2, 3);
	    getTodaysOrderDetailsandShipTheProduct();
	}
	
	@Test (priority =2005)
	public void shop_AutoshipProductsWithVerification() throws Exception{
		
		logInfo("***************TestCases of Shopping >> My Autoship*****************\n"
				+ "Navigate to Autoship and retrieve details of MyAutoship product\n"
				+ "Order new product with new address and verify the Ordered Products with their total Cost.");
		    navMyAutoshipCustomer();
			getProductsInAutoship();
			getItemsInAutoship();
			selectProductImage();
			getProductPriceDetailsInAutoship();
			getYourPriceInAutoship();			
			searchProduct(pr4);
			clickAddToCart();
			clickOnCheckOut();
			selectUseNewAddressOption();
			enterShippingAddressInAutoShip("New York")	;	
			continueTillDeliveryTheProduct();			
			navOrderHistory();
			retrieveProductsFromOrderHistory();	
	}	
	@Test(priority =2006)
	public void shop_WishlistProducts(){
		
		logInfo("***************TestCases of Shopping >> Wishlist*************************\n"
				+ "Navigate to Wishlist and add product and capute the view card details\n"
				+ "Empty the cart and capture the confirmation message of deleted products.\n"
				+ "****************************************************************************");
		navigateWishlist();
		handleWishlist()	;	
		
	}	
	
	@Test(priority=2007)
	public void addProductsToCart() throws Exception{
		logInfo("Inside addProductsToCart() Test");
		navigate2ShopPage();
		addProduct2Cart(testProductProduct1_text);
		verifyProductsInCart(testProductProduct1_text);
		deleteProductsInCart(testProductProduct1_text);
	}
	
	@Test(priority=2008)
	public void addProductsToWishList() throws Exception{
		logInfo("Inside addProductsToWishList() Test");
		navigate2ShopPage();
		addProduct2WishList(testProductProduct1_text);
		verifyProductsInWishlist(testProductProduct1_text);
		deleteProductsInWishlist(testProductProduct1_text);
	}
	
	
}
