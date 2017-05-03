package vibe.shopping2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.LoginCredentials;
import common.Priority;
import common.TestBase;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.mywebsite.tests.MyWebSite_Methods;
import vibe.shopping.tests.ShoppingMethods;
@Priority(114)
public class IDlifeShoppingTests extends Shopping2Methods{
	
	
	LoginCredentials lc = new LoginCredentials();
	ShoppingMethods shop = new ShoppingMethods();
	MyProfileMethods profile = new MyProfileMethods();
	MyWebSite_Methods pwp = new MyWebSite_Methods();
	
	
	//String custFNText = "viber.242";
  // String txtVirtualProdName1 = "Stout Advice";
	
	

		
		String  txtVirtualProdName11 = "Appetite Chew";
		String  txtVirtualProdName31 ="Variant pro";                      //"Variant pro";           //master Stout Advice
		String  txtVirtualProdName21 = "Energy Shot 12 Pack";
		String  txtVirtualProdName41 ="Energy 10 Pack";
	
		
	//Selects url form PWP page and then Place order Single Product	
	@Test(priority=11433)
	public void placeOrderProducts() throws Exception{		
		logInfo("Entered placeOrderProducts() Test");		
		String website = "https://sivakancherla.idlife-stage.vibeoffice.com";
		String pwpUserWebUrl =null;

		boolean isWebsiteFound = pwp.verifyWebsiteIsPresent(website);
		if(isWebsiteFound){
		logInfo("updating the website");		
		pwpUserWebUrl = pwp.getUrl(website);
		}else{
			Assert.assertTrue(isWebsiteFound, website + " website not found.");
			}
		
		logOut();
		Thread.sleep(11400);
		driver().navigate().to(pwpUserWebUrl);
		Thread.sleep(11400);
		driver().navigate().to(pwpUserWebUrl);
		driver().navigate().to(pwpUserWebUrl + "/shop/login"); 			
		selectCustMarket("United States");
		searchItembyNameOrSku(txtVirtualProdName11);
		addQuanityNdCheckOut(2);
		IDLife_registration(custFNText, custLNText);		
		shippingAddressDetails(custFNText, custLNText);
		selectNextInDelivery();	
		handleCCPayment();		
		placeOrderWithConfimation();
		
		//existingUserCredentials("viber.623");
		verifyOrderHistoryDetails(txtVirtualProdName11);	
		
		
		
	}
	
	//logInfo("Place order Single Product through Shipping Address, Delivery and payment.");	
	@Test(priority=11434)
	public void placeOrderWithMultiProducts() throws Exception{		
		logInfo("Entered into placeOrderWithMultiProducts() test");	
		
		//existingUserCredentials("viber.623");
		nav2CustShopping();		
		searchItembyNameOrSku(txtVirtualProdName11);
		addQuanityNdCheckOut(3);
		nav2CustShopping();		
		searchItembyNameOrSku(txtVirtualProdName21);
		addQuanityNdCheckOut(2);
		selectNext();
		selectNextInDelivery(); // select next in delivery
		selectNext(); 
		creditCardDetails();
		placeOrderNdClose();			
	}
	
	//TC3645 -Deleting Products From The shopping cart page	
	@Test(priority=11435)
	public void emptyTheProducts() throws Exception{
		logInfo("Entered emptyTheProducts() Test");
		//existingUserCredentials("viber.1597");
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName11);		
		emptyCartProducts();		
	}
	
	

	
	// Autoship
	@Test(priority=11438)
	public void validateSingleAutoshipProduct() throws Exception{
		logInfo("Entered validateSingleAutoshipProduct() Test");
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName41);
		addAutoshipQuanityNdCheckOutInIDLife(3);
		shippingAddressDetails(custFNText, custLNText);
		handleFromCheckOutToSummaryInAutoshipInIDL();
		validateAutoShipId();		
		deleteAutoShip();		
	
	}
	
	
	@Test(priority=11439)
	public void shipMultiProductsByAutoship() throws Exception{	
		logInfo("Entered shipMultiProductsByAutoship() Test");
		
		nav2AutoshipCustomer();
		autoshipWithProducts(txtVirtualProdName11);
		addQuanityInAutoShipNdCheckOut(2);
		handleFromCheckOutToSummaryInAutoship();
		editAutoShipId();	
		searchNAddProductDirectlysinAutoship(txtVirtualProdName21);
		addQuanityInAutoShipNdCheckOut(4);
		handleFromCheckOutToSummaryInAutoship();
		validateAutoShipId();
	}	
	
	
	@Test(priority=11440)
	public void deleteNdVerifyAutoship() throws Exception{
		logInfo("Entered deleteNdVerifyAutoship() Test");
				
		nav2AutoshipCustomer();
		autoshipWithProducts(txtVirtualProdName21);
		addQuanityInAutoShipNdCheckOut(2);
		handleFromCheckOutToSummaryInAutoship();
		deleteAutoshipId();
	}
	
	
	@Test(priority=11441)
	public void shoppingNdLoginWithShopUser() throws Exception{	
		logInfo("Entered shoppingNdLoginWithShopUser() Test");		
		
		nav2CustShopping();
		Thread.sleep(5000);
		logoutFromShopCustomer();
		customerShopNavigation();
		searchItembyNameOrSku(txtVirtualProdName31);
		addQuanityNdCheckoutOfNonUser(3);
		custUserLogin(custFNText);
		shippingAddress(custFNText, custLNText);	
		selectNextInDelivery();
		creditCardDetails();
		placeOrderWithConfimation();
		verifyOrderHistoryDetails(txtVirtualProdName31);
		
	}
	
	
	@Test(priority=11442)
	public void shareInAllSocialSites() throws Exception{
		logInfo("Entered shareInAllSocialSites() Test");		
		
		//existingUserCredentials("tulasi.236");  //temporaty
		customerShopNavigation();
		searchItembyNameOrSku(txtVirtualProdName31);		
		shop.selectFacebookIcon();		
		shop.shareInFaceBook();			
		profile.close();
		shop.selectLinkedInIcon();
		shop.shareInLinkedIn();
		profile.close();		
		shop.selectTwitterInIcon();    	
    	shop.shareInTwitter();
    	profile.close();    	 
    	shop.selectGooglePlusIcon();     	
     	shop.shareInGooglePlus(gmailId_text, gmailPwd_text);
     	profile.close();
		
	}
	
	
	@Test(priority=11443, dependsOnMethods = {"shareInAllSocialSites"})
	public void veriyProductInFaceBook() throws Exception{	
		logInfo("Entered veriyProductInFaceBook() Test");
		profile.login2FBVerifyPostedDetails();  
		profile.getPostsFromFBFrom2ndPart(txtVirtualProdName31) ; 	
			
	}	
	
	@Test(priority=11444, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInGooglePlus() throws Exception{
		logInfo("Entered verifyProductInGooglePlus() Test");
		shop.loginGooglePlus(gmailId_text, gmailPwd_text);
    	shop.verifyInGooglePlusForShopProducts(txtVirtualProdName31);		
	}
	
	@Test(priority=11445, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInTwitter() throws Exception{
		logInfo("Entered verifyProductInTwitter() Test");
	 profile.login2Twitter() ;
   	 profile.verifyPostsInTwitter(txtVirtualProdName31);	   	
		
	}
	
	@Test(priority=11446, dependsOnMethods = {"shareInAllSocialSites"})
	public void verifyProductInLinkedIn() throws Exception{
		logInfo("Entered verifyProductInLinkedIn() Test");
		try{
		profile.login2LinkedIn();		
	    profile.verifyPostInLinkedAccount(txtVirtualProdName31);	    
	    lc.login();	    
	    existingUserCredentials(custFNText);
		}catch (Exception ex){
			System.out.println("entered into catch");
			
			
		}
	}
	
	@Test(priority=11447)
	public void relogin() throws Exception{	
		logInfo("Entered temporarily() Test");
		lc.login();	    
	    existingUserCredentials(custFNText);
		
	} 
	
	@Test(priority=11448)
	public void shareProductByEmail() throws Exception{	
		logInfo("Entered shareProductByEmail() Test");
		customerShopNavigation();
		searchItembyNameOrSku(txtVirtualProdName11);			
		shop.selectEmailIcon();		
		shop.shareByEmail(guestEmail,subText);
		//ConfirmationMessage("Your photo has been sent to "+selfmailId);  //barnch
    	confirmationMessage("Message sent to: "+guestEmail); //master
		profile.close();	
		
		
	}
	
	
	@Test(priority=11449)
	public void shopWithGuestUser() throws Exception{
		logInfo("Entered shopWithGuestUser() Test");
		
		//logOut();
		logoutFromShopCustomer();		
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName21);
		addQuanityNdCheckoutOfNonUser(3);
		guestUser(guestEmail);
		shippingAddress(guestFname, guestLname);
		selectNext();
		temp();	  // to be continued after issue resolved		
		
		
	/*	lc.login();
		existingUserCredentials(custFNText);    
		Thread.sleep(5000);*/
		
		
	}
	
	// This is implemeneted tempritly 
	@Test(priority=11450)
	public void temporarily() throws Exception{	
		logInfo("Entered temporarily() Test");
		driver().navigate().back();
		lc.login();
		existingUserCredentials(custFNText);    
		Thread.sleep(5000);
		
	}	
	
	//String custFNText = "viber.231";
	
	@Test(priority=11451)
	public void combineOrderNdAutoship() throws Exception{
		logInfo("Entered ValiditionCustomerFields() Test");		
		
		//existingUserCredentials(custFNText);      // Temp - remove
		Thread.sleep(5000);
		
		customerShopNavigation();
		searchItembyNameOrSku(txtVirtualProdName11);		
		
	}
	
	
	@Test(priority=11499)
	public void placeOrderWithNewDetails() throws Exception{
		logInfo("Entered placeOrderWithNewDetails() Test");
		String Fname = "Mike"+ generateRandomNumberInRange(1, 111);
		String Lname = "Nelson"+ generateRandomNumberInRange(1, 111);
		
		//existingUserCredentials(custFNText);      // Temp - remove
		Thread.sleep(5000);
		logoutFromShopCustomer();
		customerShopNavigation();
		searchItembyNameOrSku(txtVirtualProdName11);
		addQuanityNdCheckOut(3);
		shipProductToNewAddress(Fname, Lname);
		selectNext(); // select next in delivery
		addNewCCDetails();	
		placeOrderWithConfimation();	
		
	}
	
	@Test(priority=11452)
	public void RemoveProductFromWishList() throws Exception{
		logInfo("Entered ValiditionCustomerFields() Test");
		
		//existingUserCredentials("viber.1609");
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);			
		add2CartFromWishList(txtVirtualProdName31);
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName21);			
		add2CartFromWishList(txtVirtualProdName21);		
		nav2WishList();	
		
		boolean ispresent = verifyProductInWishList(txtVirtualProdName31);
		if (ispresent ==true){
			removeProductFromWishList(txtVirtualProdName31);
			nav2WishList();
			verifyProductNotToPresentInWishList(txtVirtualProdName31);	
			
		}if(ispresent==false)	{
			Assert.assertTrue(ispresent, txtVirtualProdName31 +" prouct is not Present in wishList");
		}
	}
	
	@Test(priority=11453)
	public void validateIncAndDescQTY() throws Exception{
		logInfo("Entered ValiditionCustomerFields() Test");		
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);
		addQuanityNdCheckOut(3);		
		nav2ShopCart();			
		boolean ispresent = verifyProductInCart(txtVirtualProdName31);
		if (ispresent ==true){
			validIncndDescQty(txtVirtualProdName31, 3, 2);						
		}if(ispresent==false){
			Assert.assertTrue(ispresent, txtVirtualProdName31 +" prouct is not Present in Shop Cart List");
		}
	}
	
	@Test(priority=11454)
	public void RemoveProductFromCart() throws Exception{
		logInfo("Entered RemoveProductFromCart() Test");		
		//existingUserCredentials("viber.1609");
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);
		addQuanityNdCheckOut(3);		
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName21);			
		addQuanityNdCheckOut(2);	
		nav2ShopCart();			
		boolean ispresent = verifyProductInCart(txtVirtualProdName31);
		if (ispresent ==true){
			removeProductFromCart(txtVirtualProdName31);
			nav2ShopCart();	
			verifyProductNotToPresentInCartList(txtVirtualProdName31);			
		}if(ispresent==false)	{
			Assert.assertTrue(ispresent, txtVirtualProdName31 +" prouct is not Present in Shop Cart List");
		}
	}
	
	@Test(priority=11455)
	public void validateProductReviews() throws Exception{
		logInfo("Entered validateProductReviews() Test");		
		//existingUserCredentials("viber.1609");
		String shopUserName = getUserName();
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);
		writeAReview(prodReviewText, 3);		
		logoutFromShopCustomer();
		logIn(adminUser_text,adminPwd_text);
		navigate2AdminShop();
		acceptReviewFromAdmin(txtVirtualProdName31, shopUserName, prodReviewText );
		existingUserCredentials(custFNText);
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName31);
		validateReviews(prodReviewText);
		
	}
	
	@Test(priority=11456)
	public void validatePriceRanges() throws Exception{
		logInfo("Entered validateProductReviews() Test");		
		//existingUserCredentials("viber.1609");
		nav2CustShopping();
		shopPanels();
		rangeLevelPrice(1);
		rangeValidate(0,20);
		nav2CustShopping();
		shopPanels();
		rangeLevelPrice(2);
		rangeValidate(20,60);
		shopPanels();
		rangeLevelPrice(3);
		rangeValidate(60,100);
		shopPanels();
		rangeLevelPrice(4);
		rangeValidate(100,140);
		shopPanels();
		rangeLevelPrice(5);
		rangeValidate(180,500);
	}
	

	
	@Test(priority=11470)
	public void reloginAsAdminFromShopping() throws Exception{
		
		logoutFromShopCustomer();
		logIn(adminUser_text,adminPwd_text);
		
	}
	
	
	
	
	
	
}
