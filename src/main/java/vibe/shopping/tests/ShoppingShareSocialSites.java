package vibe.shopping.tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;

@Priority(34) 
public class ShoppingShareSocialSites extends ShoppingMethods {
	
	@Test(priority = 3401)
	public void shop_ShareProductInFaceBook() throws Exception{
			
		logInfo("Select product and share the product details in FaceBook");
		
		searchProduct(pr3);
		selectProductIcon();
		selectFacebookIcon();
	
		shareInFaceBook();
		handleFirstWindow();
		
		
	}
	
	
	@Test(priority = 3402)
	public void shop_ShareProductInLinkedIn() throws Exception{
			
		logInfo("Select product and share the product details in LinkedIn");
		searchProduct(pr3);
		selectProductIcon();
		selectLinkedInIcon();
	
		shareInLinkedIn();
		handleFirstWindow();
		
	}
	
	@Test(priority = 3403)
	public void shop_ShareProductByGooglePlus() throws Exception{
			
		logInfo("Select product and share the product details in GooglePlus");
		searchProduct(pr2);
		selectProductIcon();
		selectGooglePlusIcon();
		
		googlePlusShare();
		handleFirstWindow();
		retrieveProductsFromOrderHistory();	
			}	
	
	@Test(priority = 3404)
	public void shop_ShareProductByTwitter() throws Exception{
		
		searchProduct(pr4);
		selectProductIcon();
		selectLinkedInIcon();
	
		shareInTwitter();
		handleFirstWindow();		
		/*logOutCustomer();*/
			
		
	}
	
	@Test(priority = 3405)
	public void shop_ShareProductByEmail() throws Exception{
			
		searchProduct(pr4);
		selectProductIcon();
		selectLinkedInIcon();
		
		shareInLinkedIn();
		handleFirstWindow();
		
	/*	logOutCustomer();*/
			
		
	}
	
	
	

}
