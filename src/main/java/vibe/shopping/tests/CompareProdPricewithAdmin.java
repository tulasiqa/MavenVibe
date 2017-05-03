package vibe.shopping.tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;


@Priority(30)
public class CompareProdPricewithAdmin extends ShoppingMethods{

	@Test(priority= 3001)
	public void shop_AddProductWithCustomer () throws Exception{
		logInfo("As customer, verify price of MyAutoship product");
		navMyAutoshipCustomer();
		getProductsInAutoship();
		autoshipWithProducts(pr1);
		selectProductImage();
		getProductPriceDetailsInAutoship();
		/*getYourPriceInAutoship()	;		
		searchProduct("Vitamin Green");
		*/
		;
	}
	
	
	
	
	
	
	

}
