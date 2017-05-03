package vibe.shopping2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.LoginCredentials;
import common.Priority;
import common.TestBase;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.shopping.tests.ShoppingMethods;
@Priority(114)
public class Shopping2Tests extends Shopping2Methods{
	
	
	LoginCredentials lc = new LoginCredentials();
	ShoppingMethods shop = new ShoppingMethods();
	MyProfileMethods profile = new MyProfileMethods();
	
	
	
	

	@Test(priority= 11401)
	public void createOptionTypes() throws Exception{
		logInfo("inside createOptionTypes() Test");
		navigate2AdminOptionTypes();
		createNewOptionTypes(txtOptiontypeName,txtOptiontypeName);
		boolean isOptiontypeFound = verifyOptionTypeIsPresent(txtOptiontypeName);
		if(isOptiontypeFound==false){
			logInfo(txtOptiontypeName + " option type could not be created.");
			Assert.assertTrue(isOptiontypeFound, txtOptiontypeName + " option type could not be created.");
		}
	}

	@Test(priority= 11402)
	public void createVirtualProduct() throws Exception{
		logInfo("inside createVirtualProduct() Test");
		
		navigate2AdminShop();
		createNewVirtualProduct(txtVirtualProdName1);
		navigate2AdminShop();
		boolean isProdFound = false;
		isProdFound = searchProductByNameOrSKU("Product Name",txtVirtualProdName1,"All");				
		if(!isProdFound){
			Assert.assertTrue(isProdFound, " product match not found.");
		}
	}
	
	
	@Test(priority= 11404)
	public void editVirtualProduct() throws Exception{
		logInfo("inside editVirtualProduct() Test");
		navigate2AdminShop();
		boolean isProdFound = false;
		isProdFound = searchProductByNameOrSKU("Product Name",txtVirtualProdName1,"All");
		if(isProdFound){
			logInfo("product match found in search page.");
			selectProductByName(txtVirtualProdName1,"All");
			updateVirtualProductDetails(txtVirtualProdName1,txtProdSKU, "1000","900","10");
			updateProductImage();
			// updateProductVariants();
			updateProductPrices();
			// updateProductAssets(txtVirtualProdName1);
		} else {
			logInfo(txtVirtualProdName1 + " product match not found in search page.");
			Assert.assertTrue(isProdFound, txtVirtualProdName1 + " product match not found in search page.");
		}
	}
	
	
	@Test(priority= 11405)
	public void setAdminMarketSettings() throws Exception{
		logInfo("inside setAdminMarketSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Markets",txtPreferenceLiveMarkets);
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isChangeLinkPresent = verifyElementPresent("cssSelector","div.pull-right.current-market>a");
		
		if(isChangeLinkPresent==true){
				int act_items = verifyMarketSelectionsCnt();
				
		} else {
			logInfo("isChangeLinkPresent =" +isChangeLinkPresent);
			// Assert.assertFalse(isChangeLinkPresent, " Change link is not visible.");
		} 
		
		shopLogout();
		logIn(adminUser_text,adminPwd_text);
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Markets","US,CA");
		go2Homepage();
		
		Assert.assertFalse(isChangeLinkPresent, " Change link is not visible.");
	}
	
	
	
	@Test(priority= 11406)
	public void setAdminNfrMarketSettings() throws Exception{
		logInfo("inside setAdminNfrMarketSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Nfr Markets",txtPreferenceNFRMarkets);
		
	} 
	
	
	@Test(priority= 11407)
	public void setAdminBillingAddressSettings() throws Exception{
		logInfo("inside setAdminBillingAddressSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Markets","US,CA");
		setAdminFeatureSettings("Billing Address Settings","No");
		back2Office(); 
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		addQuanityNdCheckOut(1);
		int act_cnt = verifyBillingAddressSettings();
		if(act_cnt==1){
			logInfo("correct count");
		} else {
			logInfo("incorrect count");
			// Assert.assertTrue(act_cnt==1, "Country has incorrect count");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		go2AdminShopSettingsPage();
		Assert.assertTrue(act_cnt==1, "Country has incorrect count");
		setAdminFeatureSettings("Billing Address Settings", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		addQuanityNdCheckOut(1);
		int act_cnt1 = verifyBillingAddressSettings();
		if(act_cnt1==2){
			logInfo("correct count");
		} else {
			logInfo("incorrect count");
			Assert.assertTrue(act_cnt1==2, "Country has incorrect count");
		}
				
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		
	}
	

	@Test(priority= 11408)
	public void setAdminReviewsSettings() throws Exception{
		logInfo("inside setAdminReviewsSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Reviews Settings", "No");  // txtPreferenceReviewsSettings
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isWriteReviewsPresent = verifyLinkPresent("Write A Review");
		if(isWriteReviewsPresent){
			logInfo("isWriteReviewsPresent =" +isWriteReviewsPresent);
			// Assert.assertFalse(isWriteReviewsPresent, " Reviews pane should not be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		go2AdminShopSettingsPage();
		Assert.assertFalse(isWriteReviewsPresent, " Reviews pane should not be visible.");
		setAdminFeatureSettings("Reviews Settings", "Yes");
		go2Homepage(); 
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		
		boolean isWriteReviewsPresent1 = verifyLinkPresent("Write A Review");
		if(isWriteReviewsPresent1==false){
			logInfo("isWriteReviewsPresent1 =" +isWriteReviewsPresent1);
			// Assert.assertTrue(isWriteReviewsPresent1, " Reviews pane should be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		Assert.assertTrue(isWriteReviewsPresent1, " Reviews pane should be visible.");
	}
	
	@Test(priority= 11409)
	public void setAdminWishlistSettings() throws Exception{
		logInfo("inside setAdminWishlistSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Wishlist Settings","No"); 
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isWishlistPresent = verifyLinkPresent("Add to Wishlist");
		if(isWishlistPresent){
			logInfo("isWishlistPresent =" +isWishlistPresent);
			// Assert.assertFalse(isWishlistPresent, " Wishlist should not be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		go2AdminShopSettingsPage();
		Assert.assertFalse(isWishlistPresent, " Wishlist should not be visible.");
		setAdminFeatureSettings("Wishlist Settings", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isWishlistPresent1 = verifyLinkPresent("Add to Wishlist");
		if(isWishlistPresent1==false){
			logInfo("isWishlistPresent1 =" +isWishlistPresent1);
			// Assert.assertTrue(isWishlistPresent1, " Wishlist should be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		Assert.assertTrue(isWishlistPresent1, " Wishlist should be visible.");
	}
	
	
	@Test(priority= 11410)
	public void setAdminAnalyticsSettings() throws Exception{
		logInfo("inside setAdminAnalyticsSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Analytics Settings","No");  
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isAnalyticsPresent = verifyElementPresent("xpath",panelProdAnalytics);
		if(isAnalyticsPresent){
			logInfo("isAnalyticsPresent =" +isAnalyticsPresent);
			// Assert.assertFalse(isAnalyticsPresent, " Product Analytics should not be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		go2AdminShopSettingsPage();
		Assert.assertFalse(isAnalyticsPresent, " Product Analytics should not be visible.");
		setAdminFeatureSettings("Analytics Settings", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isAnalyticsPresent1 = verifyElementPresent("xpath",panelProdAnalytics);
		if(isAnalyticsPresent1==false){
			logInfo("isAnalyticsPresent1 =" +isAnalyticsPresent1);
			// Assert.assertTrue(isAnalyticsPresent1, " Product Analytics should be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		Assert.assertTrue(isAnalyticsPresent1, " Product Analytics should be visible.");
	}
	
	
	@Test(priority= 11411)
	public void setAdminShowRecentlyViewedItemsSettings() throws Exception{
		logInfo("inside setAdminShowRecentlyViewedItemsSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Recently Viewed Settings","No");
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isRecentlyViewedPresent = verifyElementPresent("xpath",panelRecentlyViewed);
		if(isRecentlyViewedPresent){
			logInfo("isRecentlyViewedPresent =" +isRecentlyViewedPresent);
			// Assert.assertFalse(isRecentlyViewedPresent, " Recently Viewed pane should not be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		go2AdminShopSettingsPage();
		Assert.assertFalse(isRecentlyViewedPresent, " Recently Viewed pane should not be visible.");
		setAdminFeatureSettings("Recently Viewed Settings", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		searchItembyNameOrSku(txtVirtualProdName);
		boolean isRecentlyViewedPresent1 = verifyElementPresent("xpath",panelRecentlyViewed);
		if(isRecentlyViewedPresent1==false){
			logInfo("isRecentlyViewedPresent1 =" +isRecentlyViewedPresent1);
			// Assert.assertTrue(isRecentlyViewedPresent1, " Recently Viewed pane should be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		Assert.assertTrue(isRecentlyViewedPresent1, " Recently Viewed pane should be visible.");
		
	}
	
	
	@Test(priority= 11412)
	public void setAdminShowMostPopularCategoriesSettings() throws Exception{
		logInfo("inside setAdminShowMostPopularCategoriesSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Show Most Popular","No"); 
		
	}
	
	
	@Test(priority= 11413)
	public void setAdminAllowOrdersInOtherMarketsSettings() throws Exception{
		logInfo("inside setAdminAllowOrdersInOtherMarketsSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Allow Orders In Other Markets", "No"); 
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isChangeLinkPresent = verifyElementPresent("cssSelector","div.pull-right.current-market>a");
		if(isChangeLinkPresent){
			logInfo("isChangeLinkPresent =" +isChangeLinkPresent);
			// Assert.assertFalse(isChangeLinkPresent, " Change link should not be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		go2AdminShopSettingsPage();
		Assert.assertFalse(isChangeLinkPresent, " Change link should not be visible.");
		setAdminFeatureSettings("Allow Orders In Other Markets", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isChangeLinkPresent1 = verifyElementPresent("cssSelector","div.pull-right.current-market>a");
		if(isChangeLinkPresent1==false){
			logInfo("isChangeLinkPresent1 =" +isChangeLinkPresent1);
			// Assert.assertTrue(isChangeLinkPresent1, " Change link should be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		Assert.assertTrue(isChangeLinkPresent1, " Change link should be visible.");
	}
	
	
	@Test(priority= 11414)
	public void setAdminShowReorderInOrderHistorySettings() throws Exception{
		logInfo("inside setAdminShowReorderInOrderHistorySettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Show Reorder In Order History","No"); 
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isReorderPresent = verifyElementPresent("xpath",lnkReorder);
		System.out.println("isReorderPresent =" +isReorderPresent);
		if(isReorderPresent){
			logInfo(" Reorder link should not be visible.");
			// Assert.assertFalse(isReorderPresent, " Reorder link should not be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		go2AdminShopSettingsPage();
		Assert.assertFalse(isReorderPresent, " Reorder link should not be visible.");
		setAdminFeatureSettings("Show Reorder In Order History", "Yes");
		go2Homepage();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		go2orderHistoryPage();
		boolean isReorderPresent1 = verifyElementPresent("xpath",lnkReorder);
		System.out.println("isReorderPresent1 =" +isReorderPresent1);
		if(isReorderPresent1==false){
			logInfo(" Reorder link should be visible.");
			// Assert.assertTrue(isReorderPresent1, " Reorder link should be present.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		Assert.assertTrue(isReorderPresent1, " Reorder link should be present.");
	}
	
	
	@Test(priority= 11415)
	public void setAdminCanDeleteFinalAutoshipSettings() throws Exception{
		logInfo("inside setAdminCanDeleteFinalAutoshipSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Can Delete Final Autoship",txtPreferencesCanDeleteFinalAutoship);
		
		
	}
	
	
	@Test(priority= 11416)
	public void setAdminCanDisplayAutoshipOnHomepageSettings() throws Exception{
		logInfo("inside setAdminCanDisplayAutoshipOnHomepageSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Can Dispaly Autoship On Homepage","No");
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isAdd2AutoshipPresent = verifyElementPresent("cssSelector","button#add-to-autoship-button_on");
		System.out.println("isAdd2AutoshipPresent = " +isAdd2AutoshipPresent);
		if(isAdd2AutoshipPresent){
			logInfo(" Add To Autoship button should not be present");
			// Assert.assertFalse(isAdd2AutoshipPresent, " Add To Autoship button should not be present");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Can Dispaly Autoship On Homepage", "Yes");
		Assert.assertFalse(isAdd2AutoshipPresent, " Add To Autoship button should not be present");
		back2Office();
		
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isAdd2AutoshipPresent1 = verifyElementPresent("cssSelector","button#add-to-autoship-button_on");
		System.out.println("isAdd2AutoshipPresent1 = " +isAdd2AutoshipPresent1);
		if(isAdd2AutoshipPresent1==false){
			logInfo(" Add To Autoship button should be present");
			// Assert.assertFalse(isAdd2AutoshipPresent1, " Add To Autoship button should be present");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		Assert.assertFalse(isAdd2AutoshipPresent1, " Add To Autoship button should be present");
	}
	
	
	@Test(priority= 11417)
	public void setAdminCanDisplayAutoshipOnCartpageSettings() throws Exception{
		logInfo("inside setAdminCanDisplayAutoshipOnCartpageSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Can Dispaly Autoship On Cartpage","Yes"); 
		
		
	}
	
	
	
	@Test(priority= 11418)
	public void setAdminSingleAutoshipSettings() throws Exception{
		logInfo("inside setAdminSingleAutoshipSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Single Autoship","Yes"); 
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isMyAutoshipsPresent = verifyAutoshipSettings();
		if(isMyAutoshipsPresent){
			logInfo("My Autoships link should not be visible.");
			// Assert.assertFalse(isMyAutoshipsPresent, "My Autoships link should not be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		Assert.assertFalse(isMyAutoshipsPresent, "My Autoships link should not be visible.");
	}
	
	
	@Test(priority= 11419)
	public void setAdminMultipleAutoshipSettings() throws Exception{
		logInfo("inside setAdminMultipleAutoshipSettings() Test");
		
		go2AdminShopSettingsPage();
		setAdminFeatureSettings("Multiple Autoships","Yes"); 
		back2Office();
		existingUserCredentials(shopUser,shopPwd, "United States");
		boolean isMyAutoshipsPresent = verifyAutoshipSettings();
		if(isMyAutoshipsPresent==false){
			logInfo("My Autoships link should be visible.");
			// Assert.assertTrue(isMyAutoshipsPresent, "My Autoships link should be visible.");
		}
		
		shopLogout();
		go2Homepage();
		logIn(adminUser_text,adminPwd_text);
		Assert.assertTrue(isMyAutoshipsPresent, "My Autoships link should be visible.");
	}
	
	
	@Test(priority= 11420)
	public void deleteOptionTypes() throws Exception{
		logInfo("inside deleteOptionTypes() Test");
		navigate2AdminOptionTypes();
		boolean isOptiontypeFound = verifyOptionTypeIsPresent(txtOptiontypeName);
		if(isOptiontypeFound){
			deleteOptionTypes(txtOptiontypeName);
		}
		
		boolean isOptiontypeDeleted = verifyOptionTypeIsPresent(txtOptiontypeName);
		if(isOptiontypeDeleted){
			logInfo(" Option Type could not be deleted.");
			Assert.assertFalse(isOptiontypeDeleted, " Option Type could not be deleted.");
		}
		
		validateTextPresentInPage("xpath", alertSuccess, alertSuccessMsg);
	}
	
	
	@Test(priority= 11421)
	public void deleteShopProduct() throws Exception{
		logInfo("inside deleteShopProduct() Test");
		navigate2AdminShop();
		
		boolean isProdFound = false;
		isProdFound = searchProductByNameOrSKU("Product Name",txtVirtualProdName,"All");
		if(isProdFound){
			logInfo("product match found in search page.");
			deleteProduct(txtVirtualProdName);
			
		} else {
			Assert.assertTrue(isProdFound, txtVirtualProdName + " product could not be found.");
		}
	
		navigate2AdminShop();
		boolean isProdDeleted = false;
		isProdDeleted = searchProductByNameOrSKU("Product Name",txtVirtualProdName,"All");
		if(isProdDeleted==false){
			Assert.assertTrue(isProdDeleted, txtVirtualProdName + " product could not be deleted.");
		}
	}
	
	
	
	
	
	
	//Ravi
	// Validating all fields of New customer enrollment 
	//	@Test(priority =114114)	  
		public void validitionCustomerFields() throws Exception{	
			logInfo("Entered ValiditionCustomerFields() Test");
			String custFNText = "tulasi."+TestBase.generateRandomNumberInRange(100,1140); 
			Thread.sleep(5000);
			Thread.sleep(5000);
			logOut();	
			customerShopNavigation();	
			validateFields(custFNText, custLNText);  //shopUser  //custFNText	
			Thread.sleep(5000);
			nav2CustShopping();		
			searchItembyNameOrSku(txtVirtualProdName31);
			addQuanityNdCheckOut(2);
			shippingAddress(custFNText, custLNText);
			selectNext();
			selectNext();
			creditCardValidation();
			
			logoutFromShopCustomer();
			logIn(adminUser_text,adminPwd_text);
			
				}
		

		String  txtVirtualProdName11 = "Energy Sticks";
		String  txtVirtualProdName31 ="Stout Advice";                      //"Variant pro";           //master Stout Advice
		String  txtVirtualProdName21 = "Stout Advice";
        String  txtVirtualProdName41 = "Stout Advice";
	
		
		
	
	//TC3636 logInfo("Enroll new customer for shopping and capture confirmation message.");	
	@Test(priority =11432)	  
	public void newShopCustomerEnrollment() throws Exception{		
		logInfo("Entered NewShopCustomerEnrollment() Test");
		
	    driver().navigate().to(appUrl);
		logOut();			
		customerShopNavigation();	
		System.out.println(custFNText);
		registerNewCustomer(custFNText, custLNText);  //shopUser  //custFNText		
			}
	
	
	
	//Place order Single Product through Shipping Address, Delivery and payment.	
	@Test(priority=11433 /*, dependsOnMethods ={"newShopCustomerEnrollment"}*/)
	public void placeOrderProducts() throws Exception{		
		logInfo("Entered placeOrderProducts() Test");
		
		existingUserCredentials("atom.2397");
		nav2CustShopping();		
		searchItembyNameOrSku(txtVirtualProdName11);
		addQuanityNdCheckOut(2);
		shippingAddress(custFNText, custLNText);
		selectNextInDelivery();			
		creditCardDetailsFirstTime();
		placeOrderWithConfimation();
		verifyOrderHistoryDetails(txtVirtualProdName11);
		
	}
	
	//logInfo("Place order Single Product through Shipping Address, Delivery and payment.");	
	@Test(priority=11434)
	public void placeOrderWithMultiProducts() throws Exception{		
		logInfo("Entered into placeOrderWithMultiProducts() test");	
		
		//existingUserCredentials("viber.2618");
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
	
	
	@Test(priority=11436)
	public void emailNdVerifyMyWishList() throws Exception{	
		logInfo("Entered emailNdVerifyMyWishList() Test");	
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName11);
		emailMyWishList();
		confirmationMessage("Your message has been sent");	
		
		
	}
	
	@Test(priority=11437)
	public void shipProductFromMyWish() throws Exception{
		logInfo("Entered ValiditionCustomerFields() Test");
		
		//existingUserCredentials("viber.1609");
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName11);			
		add2CartFromWishList(txtVirtualProdName11);
		addQuanityNdCheckOutFromTodaysOrder(txtVirtualProdName11, 3);
		selectNext();
		selectNextInDelivery(); // select next in delivery
		creditCardDetails();
		placeOrderWithConfimation();
		verifyOrderHistoryDetails(txtVirtualProdName11);		
	}
	
	
	// Autoship
	@Test(priority=11438)
	public void validateSingleAutoshipProduct() throws Exception{
		logInfo("Entered validateSingleAutoshipProduct() Test");
		
		//existingUserCredentials("viber.1609");
		nav2CustShopping();
		searchItembyNameOrSku(txtVirtualProdName41);
		addAutoshipQuanityNdCheckOut(1);		
		handleFromCheckOutToSummaryInAutoship();
		validateAutoShipId();
		nav2AutoshipCustomer();	
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
	

	
	
	
	
	
	
	
	
}
