package vibe.shopping.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import common.TestBase;


public class ShoppingMethods extends TestBase{
	
	String categoryItems = null;
	String FirstName,LastName, address1,address2,cityName,zip, phoneNo = null;
	
	
	
	public void navigatePlaceOrder(){
			
		logInfo("Navigating to Shopping >> Place Orders");
		shopping().click();
		placeOrders().click();	
		
	}
	
	public void navigatePlaceOrderWithCustomer(){
			
		logInfo("Navigating to Shopping >> Place Orders");
		driver().navigate().to(appUrl+ "/shop/");
	
	}
	
	
	
	public void navigateMyAutoship(){
			
		logInfo("Navigating to Shopping >> My Autoship");
		shopping().click();
		driver().navigate().to(appUrl+"/shop/autoships");
		
	}
	
	public void navMyAutoshipCustomer(){
			
		logInfo("Navigating My Autoship with customer credentials");	
		driver().navigate().to(appUrl +"/shop/autoships");
		
	}
	

	public void navOrderHistory(){
			
		logInfo("Navigating Order History with customer credentials");		
		orderHistory().click();	
		
	}
	
	public void navigateWishlist(){
			
		logInfo("Navigating to Shopping >> Wishlist");
		shopping().click();
		wishlist().click();	
		
	}
	
	public void navigateWishlistinCustomer(){
			
		logInfo("Navigating to Wishlist in Customer");		
		wishlist().click();	
		
	}
	
	public void searchAndAddProduct(String Category, String productName, int quanity) throws Exception{
			
		logInfo("Select the Products and Add it to Cart with quanity");
		if (driver().getCurrentUrl().contains("mannatech")||
			driver().getCurrentUrl().contains("master")){

			searchItembyNameOrSku(productName);		
			moreQuanityOfProducts(quanity);
			clickAddToCart();			
			}else{		
				selectProductsfromCategory(Category);
				searchItembyNameOrSku(productName);		
				moreQuanityOfProducts(quanity);
				clickAddToCart();		
			}
	
	}
	
	public void searchAndAddProductByImageselction(String productName, int quanity) throws Exception{
			
		logInfo("Select the Products and Add it to Cart with quanity");
		//selectProductsfromCategory("Products");
		searchItembyNameOrSku(productName);
		prodImageSelection();
		moreQuanityOfProducts(quanity);
		clickAddToCart();		
		
	
	}
	
	public void searchProduct(String productName) throws Exception{
			
		logInfo("Select the Products and Add it to Cart with quanity");
		//selectProductsfromCategory("Products");
		searchItembyNameOrSku(productName);	
	
	}
	
	public void getTodaysOrderDetailsandShipTheProduct() throws Exception{
		
		logInfo("View & get the Today's Order Details \n"
				+ "Enter the all fileds in the shipping address and click on Save to continue \n"
				+ "Capture an alert message as Invalid Address, zipcode");
		viewTotalOrderDetails();
		selectCheckOutButton();
		enterShippingAddressInOrder("New York");
		getAllStatesInUSA()	;	
		clickOnSaveAndContinueButton();		
		
		
	}
	
	public void viewTotalOrderDetails() throws Exception{		
		clickOnViewcart();
		getSubTotal();
		getPV();
		getTotal();			
	}

	
	public void selectUseNewAddressOption() throws Exception{
		clickOnElement("xpath", radioAddress);		
		
	}
	
	
	public void enterShippingAddressInOrder(String enterState) throws Exception{
		
		logInfo("Enter details of Shipping address ");
		
		try{
		Thread.sleep(5000);
		inputText("cssSelector", fName, FirstName);
		inputText("cssSelector", lName, LastName);
		inputText("cssSelector", street1, address1);
		inputText("cssSelector", street2, address2);
		inputText("cssSelector", city, cityName);
		inputText("cssSelector", phone, phoneNo);
		inputText("cssSelector", zipCode, zip);	
		selectState(enterState);
		}catch (Exception e){
			logger.error("Failed !Not entered some of mandatory fields");
			
		}
			
	}
	
	public void handleWishlist(){
		try{
			selectContinueShopping();
			enterQuanityOfProducts("3");
			clickAddToCart();
			viewTotalOrderDetails();
			selectEmptyCartButton();
			emptyCartconfirmationMessage();
		
		}catch(Exception e){
			logger.error("Failed!! Unable to select Save & Continue button");
			}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );
		}
	
	public void getShoppingproducts(){
			
		logInfo("retrieve all available product Name in Place Order.");
		try{
		List<WebElement> products = driver().findElements(By.cssSelector(totalItems));
		System.out.println("Number of Items for selected product is  : "+products.size());
		for(WebElement ProdList : products){			
			System.out.println("Items are : " +ProdList.getText());
		}}catch(Exception e){
			logger.error("Failed!! Not able to retrieved Products list");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Succeed" );
	}	
	
	public void getShoppingPrice(){
			
		logInfo("retrieve all available Price in Place Order.");
		try{
		List<WebElement> products = driver().findElements(By.cssSelector(ProdPrice));
		System.out.println("Number of price values is  : "+products.size());
		for(WebElement ProdList : products){
			
			String cost = ProdList.getText();
						
			String [] splitted = cost.split("[$ ]"); 	
			int inSpit = Integer.parseInt(splitted[1]);
			System.out.println("price  are : " +splitted[1]);			
			System.out.println("integre values"+inSpit);
			Thread.sleep(2000);
			System.out.println(cost);
		}}catch(Exception e){
			logger.error("Failed!! Not able to retrieved Products price list");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Succeed" );
	}	
	
	public void selectProductsfromCategory(String categoryItems) throws Exception{
			
		logInfo("Select Products Option from Category Drop down");
		
		try{
			Select select = new Select(driver().findElement(By.cssSelector(selectCategory)));
			select.selectByVisibleText(categoryItems);
			
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception ("Failed!!  not selected Product Category from dropdown ");
					}
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
	}
		
	  public void selectCreateNewAutoship() throws Exception{
		  clickOnButton("cssSelector", createAutoBtn);		  
		  
	  }
	  
	  
	public void autoshipWithProducts(String productName)throws Exception{
			
		logInfo("Search AutoShip products and navigate to shop and add products.");
		
		try{
			
			selectCreateNewAutoship();
			selectShopLink();
			selectProductsfromCategory("");
			searchItembyNameOrSku(productName);
			/*if(driver().findElement(By.cssSelector(createAutoBtn)).getText().equals("Create New Autoship"))
			{
			logInfo("No products have been added to this Autoship. So Shops the product and adds to Autoship");
			selectCreateNewAutoship();
			selectShopLink()
			.selectProductsfromCategory("")
			.searchItembyNameOrSku(productName);
			
			
		}else {
			
			logInfo("Already user has autoship products, So doing transaction to those products");
			 selectEditAutoshipLink()
			.getProductsInAutoship();
			
		}*/} catch(Exception e ){
			
			logger.error("Not able to ahndle");
		}	
		
		
	}
	
	public void myAutoshipDirectProducts(String productName)throws Exception{
			
		logInfo("MyAutoship - Search and add products directly, if no autoship products available.\n"
				+ "if already have autoship, do the shipping of that product. ");
		   String myAutoship =  "Create New Autoship";
		   String myShedule =  "Create New Scheduled Delivery";
		   
		   
		 WebElement create= driver().findElement(By.cssSelector(createAutoBtn)) ;
		 System.out.println(create.getText() +"   Autoship text is");
		
		try{		
		
			if(driver().findElement(By.cssSelector(createAutoBtn)).getText().equals(myAutoship)){
			logInfo("No products have been added to this Autoship. So search and adds to Autoship");
			selectCreateNewAutoship();
			searchandAddProductDirectlysinAutoship(productName)	;	
			}else if (driver().findElement(By.cssSelector(createAutoBtn)).getText().equals(myShedule)){
				
				selectCreateNewAutoship();
				searchandAddProductDirectlysinAutoship(productName)	;	
			
		}else  {
			
			logInfo("Already user has autoship products, So doing transaction to those products");
			selectEditAutoshipLink();
			getProductsInAutoship();
			
		}} catch(Exception e ){
			
			logger.error("Not able to handle myAutoshipDirectProducts method");
		}	
	}
	
	 public void searchandAddProductDirectlysinAutoship(String Product) throws Exception{
			
		logInfo("Search and Add product in autoship.");
		
		try{
		
		Actions act = new Actions(driver());
		WebElement pro = driver().findElement(By.cssSelector(autoSearch));
		inputTextClear("cssSelector", autoSearch);
		inputText("cssSelector", autoSearch,Product );
		Thread.sleep(5000);
		act.click(pro).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		}catch (WebDriverException we){
			
			logger.error("Fails, Not able to handle search and Add product");
		}

	}
	
	
	public void selectShopLink(){	
		driver().findElement(By.linkText("Shop")).click();
	}
	
	public void selectAutoshipOrderLink(){	
		driver().findElement(By.linkText("Autoship Order")).click();
	}
	
	public void selectEditAutoshipLink(){	
		driver().findElement(By.linkText("Edit Autoship")).click();	
	}
	
	
	public void selectContinueShopping() throws Exception{	
		clickOnButton("cssSelector", contShopping);
	}
	
	
	
	public WebElement shopping(){	
		return driver().findElement(By.linkText(shopping_TAB));
	
	}
	
	public WebElement placeOrders(){	
		return driver().findElement(By.linkText(placeOrder_TAB));
	
	}
	
	public WebElement myAutoship(){	
		return driver().findElement(By.linkText(myAutoship_TAB));
	
	}
	
	public WebElement wishlist(){	
		return driver().findElement(By.linkText(wishlist_TAB));
	
	}
	
	public WebElement orderHistory(){	
		return driver().findElement(By.linkText(orderHistory_TAB));
	
	}
	
	
	public void getListOfShopPanel(){
			
		logInfo("retrieve List from Shop Panel ");
		try{
		List<WebElement> shopPane = driver().findElements(By.cssSelector(shopPanel));
		System.out.println("Number of Items under shop is : "+shopPane.size());
		for(WebElement shopPanels : shopPane){			
			System.out.println("Items under shop are : " +shopPanels.getText());
		}}catch(Exception e){
			logger.error("Failed!! Not able to retrieved Shop panel list");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Succeed" );
		
	}
	
	public void getListFromCategory() throws Exception{
			
		logInfo("retrieve List from Select Category Drop down");
		
		try{
			List<WebElement> Category = driver().findElements(By.cssSelector(selectCategory));
			System.out.println("Number of Itmes in Category is : "+Category.size());
			for (WebElement catList : Category){
				System.out.println("Items are : "+catList.getText());
			}
			
		}catch (NoSuchElementException nse){
			logger.error("Failed!! No such element is found" );			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception ("Failed!!  not selected Category Tab ");
					}
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
	}
	
	
	public void searchItembyNameOrSku(String nameorSku ) throws Exception{
		driver().findElement(By.cssSelector(searchByName)).sendKeys(nameorSku);
		driver().findElement(By.cssSelector(searchByName)).submit();
		
		List <WebElement> lis = driver().findElements(By.cssSelector(productList));
		System.out.println(lis.size());
		for (WebElement lt : lis){
			System.out.println(lt.getText());
			if (lt.getText().trim().contains(nameorSku)){				
				lt.click();
				break;
			}			
		}		
		clickOnLink("linkText", "View Details");
		logInfo("Searched Item with "+ nameorSku);
	}
	
	public void clickonAddToAutoshipButton(){
					
		try{			
		clickOnButton("cssSelector", addToAutoshipBtn);
		Thread.sleep(7000);
					
		}catch(Exception e){
			logger.error("Failed!! Not able click AddToAutoship button");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Succeed" );
		
	}
	
	public void clickonAddToWishlistButton(){
					
		try{			
		clickOnButton("cssSelector", addToWishlistBtn);		
					
		}catch(Exception e){
			logger.error("Failed!! Not able click AddToWishlist button");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Succeed" );
		
	}
	
	public void clickAddToCart(){
			
		logInfo("Add to cart the Items");
		try{			
		clickOnButton("cssSelector", addTocart);
		
					
		}catch(Exception e){
			logger.error("Failed!! Not able to add the items to cart");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Succeed" );
	}
	
	public boolean isAddcartTextPresent(){
			
		logInfo("Matches - Add Cart Text");		
		return getAddCartText().matches(addCart_TEXT);
	}
	
	public boolean isTodaysOrderHeaderPresent(){
			
		logInfo("Matches - TodaysOrder Header");		
		return getTodaysOrderText().matches(todaysOrderheader_TEXT);
		
	}
	

	public String getAddCartText(){
		return  driver().findElement(By.cssSelector(addTocart)).getText();
		
	}
	
	public String getTodaysOrderText(){
		return driver().findElement(By.cssSelector(todaysOrderheader)).getText();
		
	}
	
	public void enterQuanityOfProducts(String quanity){
		
		logInfo("Entering number of Quanity of products");
		try{
			Thread.sleep(2000);
			driver().findElement(By.cssSelector(itemQuanity)).clear();
			/*inputTextClear("cssSelector", itemQuanity);*/
			Thread.sleep(2000);
			inputText("cssSelector", itemQuanity, quanity);
			
			
			}catch(Exception e){
			logger.error("Failed!! Not able to enter quanity of products");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );
		
	}
	
	public void moreQuanityOfProducts(int moreQuantiy) throws Exception{		
		logInfo("Entering number of Quanity of products");		
		try{
			Thread.sleep(2000);
			for(int i=1; i <=moreQuantiy-1; i++){				
				clickOnButton("cssSelector", moreIcon);
				Thread.sleep(3000);
				
			}}catch(Exception e){
			logger.error("Failed!! Not able to enter quanity of products");
		}		

	}
	
	public void getItemsIncart() throws Exception{
		
		getText("cssSelector", miniCartHeader, "Header");
		getText("cssSelector", miniSubTotal, "subTotal");
		getText("cssSelector", miniPV, "PV is");
		getText("xpath", miniViewCart, "view is");
		getText("cssSelector", miniCheckOut, "check is");
		clickOnElement("cssSelector",miniCheckOut );

	}
	
	public void isViewcartPresent() throws InterruptedException{		
		
		logInfo("Verify is View Cart present !");	
		Thread.sleep(3000);
		WebElement viw = driver().findElement(By.xpath(miniViewCart));		
		Assert.assertEquals(viw.getText(), "View Cart");		
	}
	 
	
	public void isReviewProductMiniCardPresent(){		
		
		logInfo("Verify 'is Review Product Cart present!'");		
		WebElement viw = driver().findElement(By.cssSelector(revProdHeader));		
		Assert.assertEquals(viw.getText(), revProductHeaderText);
		System.out.println("header is "+viw.getText());

	}
	 
	
	public void clickOnViewcart() throws Exception{
		try{
		Thread.sleep(5000);	
		clickOnButton("xpath", miniViewCart);
		}catch(Exception e){
			logger.error("Failed!! Unable to click on View Cart button");
			}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );
	
	}
	
	public void clickOnCheckOut() throws Exception{
		
		logInfo("Select CheckOut button in Mini cart window");		
		try{	
		clickOnButton("cssSelector", miniCheckOut);
		}catch(Exception e){
		logger.error("Failed!! Unable to click on Checkout button");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );
		
	}
	
	public void getSubTotal (){
		try{
			getText("cssSelector", subTotal, "Subtotal is : ");
			}catch(Exception e){
			logger.error("Failed!! Unable to get subTotal Value");
			}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );
			
		
	}
	
	public void getPV(){
		try{
			getText("cssSelector", PV, "Total PV is : ");
			}catch(Exception e){
			logger.error("Failed!! Unable to get Total PV Value");
			}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );
			
		
	}
	
	public void getTotal (){
		try{
			getText("cssSelector", total, "Total is : ");
			}catch(Exception e){
			logger.error("Failed!! Unable to get Total Value");
			}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );
			
		
	}
	
	public void emptyCartconfirmationMessage() throws Exception{
		
		try{
		getText("xpath", confirmationMessage, "confirmation message is ");
		WebElement actualConfMsg = driver().findElement(By.xpath(confirmationMessage));
		Assert.assertEquals(emptyCartConfirmation_TEXT, actualConfMsg.getText());
		}catch(Exception e){
			logger.error("Failed!! Unable to capture confirmation message");
			}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );

	}
	
	public void selectCheckOutButton(){
		
		try{
			Thread.sleep(2000);			
			clickOnButton("cssSelector", checkOut);
			}catch(Exception e){
			logger.error("Failed!! Unable to select CheckOut button");
			}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );	

	}
	
	public void continueTillDeliveryTheProduct() throws Exception{
		logInfo("Select checkOut , continue the process till delivery the products");
		
		if (driver().getCurrentUrl().contains("manna")){		
		clickOnButton("cssSelector", checkOutOrder);
		clickOnSaveAndContinueButton();
		
		clickonSaveAndContinueDelivary();
		clickOnSaveAndContinuePayment();
		clickOnPlaceOrderButton() ;
		
	}else {
		
		clickOnSaveAndContinueButton();
		clickonSaveAndContinueDelivary();
		clickOnSaveAndContinuePayment();
		clickOnPlaceOrderButton() ;
		
		
	}}
	
	
	public void clickOnNextButtonInProducts(){
	logInfo("Select Next button in Products section.");
		
		try{
			
			clickOnButton("cssSelector", nextInProducts);
			Thread.sleep(4000);
			}catch(Exception e){
			logger.error("Failed!! Unable to select Next button");
			}

	}

	public void clickOnNextButtonInShipping() throws Exception{
		try{	
			if (driver().getCurrentUrl().contains("mannatech")){
				clickOnButton("cssSelector", nextShipping);
				Thread.sleep(7000);
				keepAsAddressVerification();		
			}else{
				clickOnButton("cssSelector", nextShipping);
				Thread.sleep(3000);			
			}}catch(Exception e){
			logger.error("Failed!! Unable to select Next button");
			}

	}
	
	public void clickOnNextButtonInDelivary(){
		logInfo("Select 'Next' in Delivery section.");	
		
		try{	
			if (driver().getCurrentUrl().contains("mannatech")){
				clickOnButton("xpath", nextDelivary);
				Thread.sleep(4000);
				keepAsAddressVerification();		
			}else{
				clickOnButton("xpath", nextDelivary);
				Thread.sleep(3000);	
			}}catch(Exception e){
			logger.error("Failed!! Unable to select Next button in Delivery section");
			}	

	}

	public void clickOnNextButtonInpayment(){	
		try{
			clickOnButton("cssSelector", nextPayment);
			Thread.sleep(5000);
			}catch(Exception e){
			logger.error("Failed!! Unable to select Next button");
			}
	}
	
	public void clickOnConfirmButton(){	
		logInfo("Select Confirm button.");
		
		try{
			
			clickOnButton("cssSelector", confirm);		
			}catch(Exception e){
			logger.error("Failed!! Unable to select confirm button");
			}	
	}

	public void selectEmptyCartButton(){
		try{
			clickOnButton("cssSelector", emptycart);
			}catch(Exception e){
			logger.error("Failed!! Unable to select Empty Cart button");
			}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );

	}
	
	public void selectContinueShoppingButton(){
		try{
			clickOnButton("cssSelector", continueShopping);
			}catch(Exception e){
			logger.error("Failed!! Unable to select Continue Shopping button");
			}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );		
	}
	
	
	public void getAllStatesInUSA(){
		
		logInfo("retrieve all State of USA");
		try{
		List<WebElement> states = driver().findElements(By.cssSelector(state));
		System.out.println("Number of available States in USA : "+states.size());
		for(WebElement stateList : states){			
			System.out.println("States are : " +stateList.getText());
		}}catch(Exception e){
			logger.error("Failed!! Not able to States list");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Succeed" );
		
	}	
	
	public void selectState(String enterState) throws Exception{			
		logInfo("Select "+ enterState +" State from the USA States");
		
		try{
			Select select = new Select(driver().findElement(By.cssSelector(state)));
			select.selectByVisibleText(enterState);
			
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception ("Failed!!  Entered state is not avaiable in USA States ");
					}
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");

	}

	  public void addressInAutoShip(String enterState) throws Exception{
			
			logInfo("Enter details of Shipping address ");
			
			try{
			Thread.sleep(5000);
			inputText("cssSelector", fNameAs, FirstName);
			inputText("cssSelector", lNameAs, LastName);
			inputText("cssSelector", street1AS, address1);
			inputText("cssSelector", street2AS, address2);
			inputText("cssSelector", cityAS, cityName);
			inputText("cssSelector", phoneAS, phoneNo);
			inputText("cssSelector", zipCodeAS, zip);	
			selectStateinAutoship(enterState);
			
			
			}catch (Exception e){
				logger.error("Failed !1 Not entered some of mandatory fields");
				
			}
		
		}
		
		
		public void selectStateinAutoship(String enterState) throws Exception{
				
			logInfo("Select "+ enterState +" State from the USA States");
			
			try{
				Select select = new Select(driver().findElement(By.cssSelector(stateAS)));
				select.selectByVisibleText(enterState);
				
				
			}catch(Exception e){
				logger.error(e.getMessage(),e);
				throw new Exception ("Failed!!  Entered state is not avaiable in USA States ");
						}
			logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");

		}
		
		
	public void clickOnSaveAndContinueButton()throws Exception{	
		
	try{
	
		if (driver().getCurrentUrl().contains("mann")){
			
			clickOnButton("cssSelector", saveAndContinue);
			Thread.sleep(8000);
			keepAsAddressVerification();					
		}else {
			clickOnButton("cssSelector", saveAndContinue);
			Thread.sleep(8000);
		}}catch(Exception e){
			logger.error("Failed!! Unable to select Save & Continue button");
			getText("cssSelector", alertMessage, "The alert message as : ");
				}		
		}
	
	public void keepAsAddressVerification() throws Exception{
		logInfo("Handle to Keep As Address Verification in Mannatech");
		selectRadioOrCheckbox("xpath", keepAs);			
		clickOnButton("cssSelector", keepSave);
	}
	public void clickonSaveAndContinueDelivary() throws Exception{
		try{
		Thread.sleep(3000);	
		clickOnButton("cssSelector", saveAndContinueOfdelivary);	
		Thread.sleep(8000);
	}catch(Exception e){
		logger.error("Failed!! Unable to select Save & Continue button of Delivary");
		getText("cssSelector", alertMessage, "The alert message as : ");
		}
	logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );
		
       }
	
	
	public void clickOnSaveAndContinuePayment() throws Exception{
		try{
			
			if (driver().getCurrentUrl().contains("mann")){
				handleCCPayment()	;			
				clickOnButton("cssSelector", saveAndContinueOfPayment);					
				Thread.sleep(8000);
				keepAsAddressVerification();		
			}else {
				handleCCPayment()	;			
			clickOnButton("cssSelector", saveAndContinueOfPayment);	
				Thread.sleep(8000);
			}}catch(Exception e){
				logger.error("Failed!! Unable to select Save & Continue button of Payment");
				getText("cssSelector", alertMessage, "The alert message as : ");
					}		
			}		
       
	
	public void clickOnPlaceOrderButton() throws Exception{
		try{
		clickOnButton("cssSelector", placeOrder);
		confirmationMessage("Your order has been processed successfully");
	}catch(Exception e){
		logger.error("Failed!! Unable to select Place Order button");
		getText("cssSelector", alertMessage, "The alert message as : ");
		}
	logInfo((new Exception().getStackTrace()[0].getMethodName()) + "   Success" );
	}
	
	
	public void getProductsInAutoship() throws Exception{
		
		getText("cssSelector", itemName, "Product name is  ");
				
	}
	
	public void getItemsInAutoship() throws Exception{
		
		getText("cssSelector", productsInAuto, "text is ");		
				
	}
	
	public void selectProductImage() throws Exception{
		clickOnButton("cssSelector", productimage);
	}
	
	public void selectProductIcon() throws Exception{
		clickOnButton("cssSelector", productIcon);
	}

	public void getProductPriceDetailsInAutoship(){
	
		List<WebElement> prices = driver().findElements(By.cssSelector(priceDetails));
		for (WebElement pricesList: prices){		
			System.out.println(pricesList.getText());
		}	
    }
	
	public void getYourPriceInAutoship() throws Exception{
		
	 String prodPrice = 	driver().findElement(By.cssSelector(prodYourPrice)).getText();
	 
	 String[] splitted = prodPrice.split("[$ ]");
	 System.out.println("Product cost is "+ splitted[1]);
	 
	 /*Pattern p = Pattern.compile("(\\d+)");
	 Matcher m = p.matcher(test);
	 while(m.find())
	 {
	     System.out.println(m.start());
	 }

		*/
	}
	
	public void  navigateAdminProductsDetails() throws Exception{
			
		logInfo("Navigate to Go To Admin >> Shops >> Product");
		
		try{
			clickOnLink("xpath", lnkGotoAdmin);
			clickOnLink("linkText", shopsLink);
			clickOnLink("linkText", ProductsLink);
			}catch(Exception e){
				logger.error(e.getMessage(),e);
				throw new Exception ("Failed!!  Not able to navigate to Shop Products in Admin");
						}
			logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
			
	}
	
	
	public void retrieveProductsFromOrderHistory() throws Exception{
			
		logInfo("Retrive all products from Order History");
		WebElement cost =  driver().findElement(By.cssSelector(orderHistoryTotal));
		try{
		List <WebElement> orders = driver().findElements(By.cssSelector(auotItems));          //orderHistoryItems));
		System.out.println("Total No of Orders are "+orders.size());
		int i, od = orders.size();
		for (i=2; i<=od+1; i++){
			WebElement orderId = driver().findElement(By.cssSelector(ordIds1+i+ordIds2));
			WebElement productName = driver().findElement(By.cssSelector(prod1+i+prod2));		
			WebElement pv = driver().findElement(By.cssSelector(pv1+i+pv2));
			WebElement total  = driver().findElement(By.cssSelector(tot1+i+tot2));			
			System.out.println("Autoshop Id - "+orderId.getText()+ " and product - " +productName.getText()+" with their PV value is "+ pv.getText() + " and Total Value is "+total.getText());
			Reporter.log("Autoshop Id - "+orderId.getText()+ " and product - " +productName.getText()+" with their PV value is "+ pv.getText() + " and Total Value is "+total.getText());
			
		}
		
		/*
		for (WebElement orderList: orders){
	
			
			
			System.out.println("Product is "+ orderList.getText()  );//+ "  and Total Cost : "+cost.getText());
			
		}*/
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception ("Failed!!  Not able Retrive the products from Order History");
					} 
		
	}

	public void handleFirstWindow(){
		String winHandleBefore = driver().getWindowHandle();
		driver().switchTo().window(winHandleBefore);

	}
	
	
	public void selectFacebookIcon() throws Exception{
		logInfo("Select FaceBook Icon for sharing");
		waitOnElement("cssSelector", facebook);
		clickOnElement("cssSelector", facebook);
		Thread.sleep(10000);
		
	}
	
   public void selectLinkedInIcon() throws Exception{
	   logInfo("Select LinkedIn Icon for sharing");
	   clickOnElement("cssSelector", linkedIn);		
		
	}
   
   public void selectTwitterInIcon() throws Exception, Exception{
		logInfo("Select LinkedIn Icon for sharing");
		Thread.sleep(2000);
		clickOnElement("cssSelector", twitterIcon);		

	}
	

    public void selectGooglePlusIcon() throws Exception, InterruptedException{
    		
		logInfo("Select GooglePlus Icon for sharing  to share the posts.");
		Thread.sleep(4000);
		clickOnElement("cssSelector", googlePlus);	
    }
    
    public void shareInGooglePlus(String gmail, String passwd) throws Exception{
		logInfo("Enter Gmail credentials to share Posts");
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
				if(!w.equalsIgnoreCase(wndBeforeWindow)){
		           driver().switchTo().window(w);
		           Thread.sleep(3000);
					inputTextClear("cssSelector",inputGmail);
					inputText("cssSelector",inputGmail, gmail);
					clickOnButton("cssSelector", btnGmailNext);
					WebElement staysigned = driver().findElement(By.xpath(chkStaySignedIn));
					String isChecked = staysigned.getAttribute("checked").trim();
					if(isChecked.equalsIgnoreCase("checked")){
						staysigned.click();
			}
			
			inputText("cssSelector",inputGmailPasswd, passwd);
			clickOnButton("cssSelector", btnSignIn);
			Thread.sleep(4000);
			getText("cssSelector", gmailComment, "product is");
			inputText("cssSelector", gmailComment, "Superb product");
			clickOnButton("xpath", gmailShare);
		/*clickOnLink("linkText", "Close this window");*/	
		Thread.sleep(8000);
		driver().switchTo().window(wndBeforeWindow);
		break;
		}
				}
	
	}
    
    
    
    public void loginGooglePlus(String uname, String passwd) throws Exception{
		logInfo("Acess into google plus account with credentials");
		driver().navigate().to("https://plus.google.com/");	
		clickOnButton("cssSelector", "a#gb_70");
		inputText("cssSelector",inputGmail, uname);
		clickOnButton("cssSelector", btnGmailNext);
		Thread.sleep(3000);		
		waitOnElement("cssSelector",inputGmailPasswd);
		inputText("cssSelector",inputGmailPasswd, passwd);
		clickOnButton("cssSelector", btnSignIn);
		
 
 }
 
 

	public boolean verifyInGooglePlus(String postName) throws Exception{
		
		clickOnElement("cssSelector", googAll);
		clickOnElement ("cssSelector",gogHome );
		
		boolean isPostPresent  = false;
		
		List <WebElement> list = driver().findElements(By.cssSelector(googPostList));
		System.out.println(list.size());
		for (WebElement glist: list){
			
			System.out.println(glist.getText()+ " in google plus");
			
			if (glist.getText().trim().contains(postName)){
				isPostPresent = true;					
				System.out.println("Google Puls Post name is "+ glist.getText());
				break;
			}	
			
		}if (isPostPresent==false){
			
			Assert.assertTrue(isPostPresent, postName + " is not found in googlePlus page" );
		}	
		
		clickOnElement("cssSelector", googAccountIcon);
		clickOnButton("cssSelector", googLogout);
		Thread.sleep(3000);
		return isPostPresent;
		
		
	}	 
	
	
public boolean verifyInGooglePlusForShopProducts(String postName) throws Exception{
		
		clickOnElement("cssSelector", googAll);
		clickOnElement ("cssSelector",gogHome );
		
		boolean isPostPresent  = false;
		
		List <WebElement> list = driver().findElements(By.cssSelector(gogList));
		System.out.println(list.size());
		for (WebElement glist: list){
			
			System.out.println(glist.getText()+ " in google plus");
			
			if (glist.getText().trim().contains(postName)){
				isPostPresent = true;					
				System.out.println("Google Puls product name is "+ glist.getText());
				break;
			}	
			
		}if (isPostPresent==false){
			
			Assert.assertTrue(isPostPresent, postName + " is not found in googlePlus page" );
		}	
		
		clickOnElement("cssSelector", googAccountIcon);
		clickOnButton("cssSelector", googLogout);
		Thread.sleep(3000);
		return isPostPresent;
		
		
	}	 
	 
	
    
    
    public void selectEmailIcon() throws Exception{
    		
	logInfo("Select Email Icon for sharing");
	Thread.sleep(2000);
	clickOnElement("cssSelector", shareByMail);	
	
    }
    
    public void selectPWPInIcon() throws Exception, Exception{
		logInfo("Select LinkedIn Icon for sharing");
		Thread.sleep(2000);		
		clickOnElement("cssSelector", pwp);		
	}
    

    public void shareInFaceBook() throws Exception{
		
	logInfo("Enter login credentials in FaceBook to share the posts.");
	Thread.sleep(5000);
	String wndBeforeWindow = driver().getWindowHandle();	
	for(String w : driver().getWindowHandles()){
			if(!w.equalsIgnoreCase(wndBeforeWindow)){
			driver().switchTo().window(w);			
			waitOnElement("cssSelector", fbEmail);
			inputText("cssSelector", fbEmail, fBEmail_text2);
			Thread.sleep(5000);
			inputText("cssSelector", fbPassword, fBPwd_text2);	
			submitElement("cssSelector", fbPassword);
			//clickOnButton("cssSelector", fbLoginButton);
			Thread.sleep(5000);			
			clickOnButton("name", fbShareLink);	
			Thread.sleep(8000);
			driver().switchTo().window(wndBeforeWindow);
			/*driver().manage().deleteAllCookies();*/
			break;	
		}
			}
	
	
} 


    public void shareInLinkedIn() throws Exception{
		
		logInfo("Enter login credentials in LinkedIn to share the posts.");
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
				if(!w.equalsIgnoreCase(wndBeforeWindow)){
				driver().switchTo().window(w);
		waitOnElement("cssSelector", linkedInEmail);	
		inputText("cssSelector", linkedInEmail, fBEmail_text);
		inputText("cssSelector", linkedInPwd, fBPwd_text);	
		clickOnButton("cssSelector", linkedInLogin);	
		inputText("cssSelector", linkedShareaUpdate, "Hey Its a Good Product");	
		clickOnButton("cssSelector", linkedShare);
		Thread.sleep(7000);
		clickOnLink("linkText", "Close this window");	
		Thread.sleep(8000);
		driver().switchTo().window(wndBeforeWindow);
		break;
		}
				}
	
    } 

    public void shareInTwitter() throws Exception{
		
	logInfo("Enter login credentials in Twitter to share the posts.");
	String wndBeforeWindow = driver().getWindowHandle();	
	for(String w : driver().getWindowHandles()){
	if(!w.equalsIgnoreCase(wndBeforeWindow)){
	driver().switchTo().window(w);
	waitOnElement("cssSelector", twitLogin);
	inputTextClear("cssSelector", twitLogin);
	inputText("cssSelector", twitLogin, fBEmail_text);
	inputTextClear("cssSelector", twitPswD);
	inputText("cssSelector", twitPswD, fBPwd_text);	
	submitElement("cssSelector", twitPswD);
	Thread.sleep(2000);
	clickOnButton("cssSelector", twtTweet);	
	Thread.sleep(3000);	
	driver().switchTo().window(wndBeforeWindow);
	break;
	}}  
	} 

	public void shareProdByGoogle() throws Exception{
		logInfo("Enter login credentials in FaceBook");
		clickOnElement("cssSelector", googlePlus);

	}


	public void retrieveGoogleAccounts() throws Exception{
	
		List <WebElement> acc = driver().findElements(By.cssSelector(googleAccounts));
		System.out.println("get size "+ acc.size());
		for (WebElement googleAcc: acc){
			System.out.println(googleAcc.getText());
			
		}
	}

	public void googlePlusShare() throws Exception{
		String googleText = " Awesome Product, my count is "+generateRandomNumberInRange(5, 500);
		inputText("cssSelector", googleEmail, "icentrissoft@gmail.com");
		driver().findElement(By.cssSelector(googleEmail)).submit();
		inputText("cssSelector", googlePassword, "testing@123");
		driver().findElement(By.cssSelector(googlePassword)).submit();
		/*inputText("cssSelector", googleMsg, googleText);*/
		clickOnButton("xpath", googleShare);

	}
	
	public void recepAndSubject(String emailId, String subject) throws Exception{
		logInfo("Inside recepAndSubject() method.");
    	Thread.sleep(5000);    	
    	inputText("cssSelector", recipientsTo, emailId);    	
    	Thread.sleep(5000);
    	inputTextClear("cssSelector", mailSub);
    	inputText("cssSelector", mailSub, subject); 
    	Thread.sleep(3000);	    	
	 }
	
	public void shareByEmail(String mailId, String subject) throws Exception, Exception{    	
    	logInfo("Enter recipient mailId & compose and send a mail");
    	recepAndSubject(mailId, subject);    	
    	clickOnButton("cssSelector", send_Mail);     	
    }
	
	
	
	


	public void handleMarket() throws AWTException, InterruptedException{
	
	Robot rb = new Robot();
	rb.delay(1000);
	/*rb.keyPress(KeyEvent.VK_TAB);
	rb.delay(1000);
	rb.keyPress(KeyEvent.VK_TAB);
	rb.delay(1000);*/
	rb.keyPress(KeyEvent.VK_TAB);
	rb.delay(1000);
	rb.keyPress(KeyEvent.VK_ENTER);
	rb.delay(10000);

	
	/*driver().navigate().back();
	Thread.sleep(10000);
	rb.delay(1000);
	rb.keyPress(KeyEvent.VK_TAB);
	rb.delay(1000);
	rb.keyPress(KeyEvent.VK_ENTER);
	*/
	
	
}

	public void handleTabbing() throws AWTException, InterruptedException{
		
		Robot rb = new Robot();
		rb.delay(1000);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.delay(1000);
		rb.keyPress(KeyEvent.VK_TAB);	
		rb.delay(1000);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.delay(1000);
		
	}

		public void custShoppingNavigation() throws Exception{
			logInfo("Enter url for customer shopping");
			Thread.sleep(2000);	
			System.out.println("Try to reogin with customer");
			driver().navigate().to(appUrl + "/shop/login");   //master Vibe
			//driver().navigate().to(yevoURL + "/shop/login");  
	
			}
		
		public void openShoppingPageWithoutUser(){
			logInfo("Enter url for shopping without user.");			
			driver().navigate().to(appUrl + "/shop");   //master Vibe
		}

		public void navMarketInEnrollment(){
			logInfo("Enter url to access Market .");			
			driver().navigate().to(appUrl +"/pyr_core/v2_enrollments/select_market");
		}

		public void existingUserCredentials() throws Exception {
			inputTextClear("xpath", inputName);
			inputText("xpath", inputName, shopUser);
			Thread.sleep(1000);
			inputTextClear("xpath", inputPwd);			
			inputText("xpath", inputPwd, shopPwd);
			submitElement("xpath", inputPwd);
			/*clickOnButton("cssSelector",loginBtn );*/
			
		}
		
		
		public void registerNewCustomer(String Fname, String Lname) throws Exception {
			
			logInfo("Register New Customer with all fields.");
			try{	
				if (driver().getTitle().contains("idlife")){
					registration(Fname, Lname);
					genderAndDOB("Male");        // This is required for IDlife 					
				}else{
					registration(Fname, Lname);	
					
				}		
			Thread.sleep(1000);			
			clickOnButton("cssSelector", custCreate);				
			}catch (Exception e){
				
				logger.error("Failed!! Some mandatory fields are missing.");
			}			
			
		}
		
		public void registration(String Fname, String Lname) throws Exception {
			
			logInfo("Register New Customer with all fields.");
			try{				
			clickOnButton("cssSelector", registerNew);	
			inputTextClear("cssSelector", custFN);
			inputText("cssSelector", custFN, Fname);
			inputText("cssSelector", custLN, Lname);
			inputText("cssSelector", custEmail, "tulasi.ravi@icentris.com");
			inputText("cssSelector", custPhone, "1234567891");
			inputText("cssSelector", custAd1, "Roxbury Drive");
			inputText("cssSelector", custAd2, "Beverly Hills");
			inputText("cssSelector", custCity, "CA");
			stateSelection(9);			
			inputText("cssSelector", custZip, "90210");
			inputTextClear("cssSelector", custUserName);
			inputText("cssSelector", custUserName, Fname);
			inputText("cssSelector", custPwd, custPwdText);
			inputText("cssSelector", custConPwd, custPwdText);
			getText("cssSelector", custCreate, "Text us ");
						
			}catch (Exception e){
				
				logger.error("Failed!! Some mandatory fields are missing.");
			}			
			
		}
		
		
		public void genderAndDOB(String gender) throws Exception{
			
			inputText("cssSelector", custDOB, "02/01/1990");
			Thread.sleep(2000);
			driver().findElement(By.cssSelector(custGender)).click();
			List <WebElement> dob =driver().findElements(By.cssSelector(custGenderOpt));
			for (WebElement dd : dob){
				System.out.println(dd.getText());
				if (dd.getText().equalsIgnoreCase(gender)){
					Actions act = new Actions(driver());
					act.doubleClick(dd).build().perform();
					
					break;
				}
				
				
			}

		}
				
		public void creditCardDetails() throws Exception {
			Thread.sleep(4000);
			inputText("cssSelector", CCName, custFNText);
			inputText("cssSelector", CCNumber, CCCardNo);
			inputText("cssSelector",CCExpire, "08/19");
			inputText("cssSelector",CCCardCode, "489");		

		}
		
		public void stateSelection ( int count){
			
			WebElement st = driver().findElement(By.cssSelector(custState));
			Select s = new Select (st);
			s.selectByIndex(count);

		}
		
		
		
		public void addToAutoship() throws Exception{
			
			logInfo("Select Add To Delivery Button");
			
			WebElement title = driver().findElement(By.cssSelector("h3#product_name"));
			String prName = title.getText();
			
			if (driver().getCurrentUrl().contains("manna")){
			Thread.sleep(2000);				
			clickOnButton("cssSelector", addAutoBtnMan);	
			}else{
			
		//	clickOnButton("cssSelector", addAutoBtn);   // vibe Master			
			clickOnButton("cssSelector", addSchd);   //IDlIfe			
			Thread.sleep(3000);
			}
			
			confirmationMessage(prName + " has been added to your autoship order.");
			
		}
		
		public void getTextOfReviewProduct() throws Exception{
			
			getText("cssSelector ", revProdHeader, "RP");
			
		}
		
		public void productsLnkInPane() throws Exception{			
			clickOnLink("linkText", "Products");			

		}
		
		public void CCPaymentSection() throws Exception{ 
			
			handleCCPayment();
			clickOnNextButtonInpayment();
		}
		
		
		public void getAutoshipID() throws Exception{
				
			logInfo("Retrive all Autoship Id from MyAutoship.");			
			List <WebElement> id = driver().findElements(By.cssSelector(autoID));
			if(id.size()==0){
				logInfo("MyAutoship does not have any existing AutoIds.");				
			}else{
			for (WebElement AId: id){				
				System.out.println("test is "+AId.getText());								
			}}	
		}
		
		public void selectAutoshipID() throws Exception{	
				
			logInfo("Select Autoship Id if myAutoship has existing. ");
			List <WebElement> id = driver().findElements(By.cssSelector(autoID));
			if(id.size()==0){
				logInfo("MyAutoship does not have any existing AutoIds.");				
			}else{
			for (WebElement AId: id){
				logInfo("Select AutoshipId.");
				WebElement ed = driver().findElement(By.cssSelector(ASEdit));
				System.out.println(ed.getText());
				ed.click();
				break;
								
			}}

		}
		 
		
		public void getAutoshipProductList() throws Exception{		
				
			List <WebElement> id = driver().findElements(By.cssSelector(autoProdName));
			System.out.println("Siz is "+id.size());
			for (WebElement AId: id){
				System.out.println("Autoship product : "+AId.getText());
				
			}

		}
		
		public void handleCCPayment() throws Exception{
				
			List <WebElement> po = driver().findElements(By.cssSelector(paymentOptions));			
			if (po.size()==0){
				logInfo("No existing card details, Entering New card Details.");
				creditCardDetails();
				
			}else {				
				logInfo("Select Use new Card option and enter Credit Card Details.");
				clickOnElement("xpath", payOp);
				Thread.sleep(2000);
				creditCardDetails();
					
			}				
		}
 
		
		public void enterShippingAddressInAutoShip(String state) throws Exception{
				
			List <WebElement> po = driver().findElements(By.cssSelector(addOptions));			
			if (po.size()==0){
				logInfo("No existing Address, So entering New card Details.");
				addressInAutoShip(state);
				clickOnNextButtonInShipping();
				
			}else {				
				logInfo("Select existing address and select Next button.");
				clickOnNextButtonInShipping();
				Thread.sleep(2000);
			}
				
		}
		
		public void prodImageSelection(){			
			clickOnElement("cssSelector", prodImage);
		}
		
		public void getProductsFromMyWishlist(String prodName){
			List <WebElement> ls = driver().findElements(By.cssSelector(prodNameInWishlist));			
			for (WebElement items : ls){
				String getval = items.getText();
				{
					if(prodName.equals(getval)){
					Assert.assertTrue(getval.matches(prodName));
				    System.out.println(getval + " match found");
				     break;					
				}		}		
				
			}			
		}
		
		public void goToStores(){
				
			logInfo("Select link 'Go Back To Store'. ");			
			try{clickOnLink("linkText", goBackToStore);
				
			}catch(Exception e){				
				logger.error("Failed!! - Not able to identify link 'Go Back To Store'");
			}				
		}		
		
         public void getNdVerifyTaxonList(){
        	 	
        	logInfo("Verify the Taxons list in Place Order page.");
			List <WebElement> tx = driver().findElements(By.cssSelector(taxonList));			
			for (WebElement txs: tx){					
			switch(txs.getText()){			
			case "Tools" :
				Assert.assertTrue(txs.getText().matches("Tools"));
				break;
			case "Events" :
				Assert.assertTrue(txs.getText().matches("Events"));
				break;
			case "Products" :
				Assert.assertTrue(txs.getText().matches("Products"));
				break;			
			default:
				System.out.println(" More taxons are added as "+txs.getText() );
				break;
			}}
	
		}
		
         public void selectTaxon(String taxon){
        	 	
         	logInfo("Select the Taxons - "+ taxon+" in the left side of the screen in Place Order page.");
 			List <WebElement> tx = driver().findElements(By.cssSelector(taxonList));	
 			System.out.println("size is "+tx.size());
 			for (WebElement txs: tx){	 				
 				if (txs.getText().equals(taxon)){	
 				
 					if (txs.isEnabled()){
 						txs.click(); 	 					
 	 					break;
 						
 					}
 					
 					
 				} 				
 			}
	
 		}
 		
         
         public void PriceRangeSelection(){
        	 	
        	 logInfo("Retrieve and select the Price Range checkboxes");
        	 List <WebElement> tx = driver().findElements(By.cssSelector(pricRang));			
  			for (WebElement txs: tx){		
  				
  				System.out.println("Price ranges are "+txs.getText());
  				txs.click();
  				
  				}      	 

         }
         
         public void consultPriceIconSelect(){
        	 	
        	 logInfo("Select question mark Icon of Consultant Price. ");        	 
        	 clickOnElement("cssSelector", conPricIcon);       	 

         }
      
         
      //Consultant Enrollments
         
         public void isConsultantPriceModalPresent(){
        	 	
        	logInfo("Verify Consultant Price modal present. ");  
        	WebElement con = driver().findElement(By.cssSelector(cpHead));        	
        	Assert.assertEquals(con.getText(), cpHeadText);        	      	 
	 
         }
         
         public void selectEnrollNow() throws Exception{
        	 	
        	 logInfo("Select Enroll Now button in Consulatant Price Modal window.");
        	 try{
        	 clickOnButton("cssSelector", cpEnroll);
        	 }catch (WebDriverException we ){
        		 logger.error("Failed !! not select Enroll Now button.");;
        	 }
        	 
         }
         
         public void selectCloseEnroll() throws Exception{
        	 	
        	 logInfo("Select close button in Consulatant Price Modal window.");
        	 try{
        	 clickOnButton("cssSelector", cpClose);
        	 }catch (WebDriverException we ){
        		 logger.error("Failed !! not select close button in Consultant Price .");;
        	 }
        	 
         }
         
         
         public void selectCountryForEnrollment(String country){
        	 	
        	 logInfo("Select the country of "+country+" , for enrollment.");
        	 List <WebElement> con = driver().findElements(By.cssSelector(contList));
        	 System.out.println("countries Size : "+ con.size());
        	 for (WebElement  cntry: con){
        		 
        		 if (cntry.getText().equals(country)){        			 
        			 cntry.click();
        			 break;        			 
        		 }
        		 
        	 }

         }
         
         
         public void packageSelection(String packs){
        	 	
        	 logInfo("Select "+packs+" , for enrollment.");
        	 List <WebElement> con = driver().findElements(By.cssSelector(packSize));
        	 System.out.println("Packages Size : "+ con.size());
        	for (int i =1; i<=con.size(); i++){
        		 
        		WebElement packages = driver().findElement(By.cssSelector(pacName1+i+pacName2));
        		WebElement selection = driver().findElement(By.cssSelector(pacSelect1+i+pacSelect2));
        		
        		System.out.println("Package list - "+packages.getText());        		
        		
        		 if (packages.getText().equals(packs)){  
        			 
        			 selection.click();
        			 break;        			 
        		 }
        		 
        	 }

         }

         
       //******************************Created for Smoke Production.**********************************************************************

      public void navigate2AdminShop() throws Exception{
      	logInfo("inside navigate2AdminShop() method.");
      	driver().navigate().to(appUrl + "/shop/admin/products");		
      	waitOnElement("xpath",lnkAdminNewProduct);
      }

      public void addNewProduct(String prodName) throws Exception{
      	logInfo("inside addNewProduct() method.");
      	Robot robo = new Robot();
      		
      	verifyElementPresent("xpath",lnkAdminNewProduct);
      	clickOnElement("xpath",lnkAdminNewProduct);
      	
      	verifyElementPresent("xpath",inputShopProductName);
      	inputText("xpath",inputShopProductName,prodName);   // shopProductName_text
      	inputText("xpath",inputShopProductSKU,shopProductSKU_text);
      	inputTextClear("xpath",inputShopMasterPrice);
      	inputText("xpath",inputShopMasterPrice,shopMasterPrice_text);
      		
      	inputTextClear("xpath",inputShopProductAvailableOn);
      	inputText("xpath",inputShopProductAvailableOn,shopProductAvailableOn_text);
      	robo.keyPress(KeyEvent.VK_TAB);
      	robo.keyRelease(KeyEvent.VK_TAB);
      	Thread.sleep(5000);
      	
      	verifyElementPresent("xpath",eleShopShippingCategory);
      	clickOnElement("xpath",eleShopShippingCategory);
      	
      	WebElement tbl = driver().findElement(By.xpath(drpdnShopTaxons));
      	List allItems = tbl.findElements(By.tagName("li"));
      	int all_items = allItems.size();
      	String before_item = "//*[@id='select2-drop']/ul[@id='select2-results-2']/li[";
      	String after_item = "]/div";
      	
      	if(all_items>0){
      		for(int j=1;j<=all_items;j++){
      			WebElement item = driver().findElement(By.xpath(before_item+j+after_item));
      			String item_name = item.getText().trim();
      			System.out.println(item_name);
      			if(item_name.matches(shopShippingCategory_text)){
      				System.out.println("clicked item " + shopShippingCategory_text);
      				item.click();
      				break;
      			} 
      		}
      	}
      	
      	clickOnButton("cssSelector",btnShopProductCreate);
      	
      	waitOnElement("cssSelector",divShopMarketCheckboxGroup);
      	verifyElementPresent("cssSelector",divShopMarketCheckboxGroup);
      	WebElement chkUS = driver().findElement(By.xpath("//*[@id='market_language_checkbox']/input[1]"));
      	if(chkUS.isDisplayed()){
      		chkUS.click();
      	}
      	clickOnButton("cssSelector",btnShopProductUpdate);
      	logInfo("clicked to save the product " +prodName);
       }
      
      
      public void updateMandatoryDetailsforProduct() throws Exception{
    	  clickOnElement("cssSelector", details);
    	  Thread.sleep(5000);
    	  selectTaxon();
    	  clickOnButton("cssSelector",btnShopProductUpdate);

      }
      
      public void selectTaxon(){
    	  clickOnElement("cssSelector", taxon);
    	  List <WebElement> tList = driver().findElements(By.cssSelector(taxon)) ;
    	  System.out.println(tList.size());    	  
    	  for (WebElement li :tList){    		  
    		  System.out.println(li.getText());
    	  }
    	  
      }


      public void navigate2ShopPage() throws Exception{
      	logInfo("inside navigate2ShopPage() method.");
      	driver().navigate().to(appUrl + "shop"); 
      	driver().navigate().refresh();
      	waitOnElement("xpath",shopProductsPanel);
      }


      // Verify if product is present in user shop page.

      public void verifyShopProductIsPresent(String prodName) throws Exception{
      	logInfo("inside verifyShopProductIsPresent() method.");
      	
      	WebElement prodPanel = driver().findElement(By.xpath(shopProductsPanel));
      	List allProds = prodPanel.findElements(By.tagName("div"));
      	
      	int all_prods = allProds.size();
      	System.out.println("Total Products =" + all_prods);
      	String before_prod = "//*[@id='products']/div[";
      	String after_prod = "]/div/div[2]/a";
      	boolean isProdFound = false;
      	for(int i=1;i<=12;i++){
      		WebElement x = driver().findElement(By.xpath(before_prod+i+after_prod));
      		String name = x.getText().trim();
      		System.out.println("product name = " +name);
      		if(name.equalsIgnoreCase(prodName)){
      			logInfo(prodName + " product found in shop place order page.");
      			isProdFound = true;
      			break;
      		}
      	}
      	
      	if(isProdFound==false){
      		logInfo(prodName + " product not found in shop place order page.");
      		Assert.assertTrue(isProdFound, prodName + " product not found in shop place order page." );
      	}
      	
      }


      public void addProduct2Cart(String prodName) throws Exception{
      	logInfo("inside addProduct2Cart() method.");
      	System.out.println("inside addProduct2Cart() method.");
      	waitOnElement("xpath",shopProductsPanel);
      	
      	selectFromDropdown("xpath",drpdnSelectProductCategory,"byVisibleText","Products");
      	inputTextClear("xpath",inputSearchProductNameSKU);
      	inputText("xpath",inputSearchProductNameSKU, prodName+" ");
      	
      	waitOnElement("xpath",shopProductsPanel);
      	WebElement x = driver().findElement(By.xpath("//*[@class='col-md-3 col-sm-6 col-xs-12 product-list-item']/div/div[2]/a"));
      	String pName = x.getText().trim();
      	System.out.println("Product Name =" + pName);
      	
      	x.click();
      	waitOnElement("xpath",btnAddToCart);
      	clickOnElement("xpath",btnAddToCart);
      	
      	waitOnElement("linkText","View Cart");
      	clickOnLink("linkText","View Cart");
      	logInfo("clicked on link 'View Cart'.");

      }

      public void addProduct2WishList(String prodName) throws Exception{
      	logInfo("inside addProduct2WishList() method.");
      	System.out.println("inside addProduct2WishList() method.");
      	waitOnElement("xpath",shopProductsPanel);
      	
      	selectFromDropdown("xpath",drpdnSelectProductCategory,"byVisibleText","Products");
      	inputTextClear("xpath",inputSearchProductNameSKU);
      	inputText("xpath",inputSearchProductNameSKU, prodName+" ");
      	
      	waitOnElement("xpath",shopProductsPanel);
      	WebElement x = driver().findElement(By.xpath("//*[@class='col-md-3 col-sm-6 col-xs-12 product-list-item']/div/div[2]/a"));
      	String pName = x.getText().trim();
      	System.out.println("Product Name =" + pName);
      	
      	x.click();
      	verifyLinkPresent("ADD TO WISHLIST");	
      	clickOnLink("linkText","ADD TO WISHLIST");
      	logInfo("clicked on link 'Add to Wishlist'.");
      }

      public void verifyProductsInWishlist(String prodName) throws Exception, Exception{
      	logInfo("inside verifyProductsInWishlist() method.");
      	System.out.println("inside verifyProductsInWishlist() method.");
      	
      	driver().navigate().to(appUrl + "shop/wishlists");
      	waitOnElement("xpath",tblWishlist);
      	
      	WebElement wishlist = driver().findElement(By.xpath(tblWishlist));
      	List allRows = wishlist.findElements(By.tagName("tr"));
      	int all_rows = allRows.size();
      	System.out.println("Total items in Wishlist = " +all_rows);
      	
      	String before_item = "//*[@id='line_items']/tr["; 
      	String after_item = "]/td[3]";
      	boolean isProductFound = false;
      	
      	if(all_rows >0){
      		for(int i=1;i<=all_rows;i++){
      			WebElement x = driver().findElement(By.xpath(before_item+i+after_item));
      			String itemName = x.getText().trim();
      			if(itemName.equalsIgnoreCase(prodName)){
      				isProductFound= true;
      				logInfo(prodName + " product match found in the wishlist.");
      				break;
      			}
      		}
      	}
      	
      	if(isProductFound==false){
      		logInfo(prodName + " product not found in the wishlist.");
      		Assert.assertTrue(isProductFound , prodName + " product not found in the wishlist.");
      	}
      }

      public void verifyProductsInCart(String prodName) throws Exception, Exception{
      	logInfo("inside verifyProductsInCart() method.");
      	System.out.println("inside verifyProductsInCart() method.");
      	
      	driver().navigate().to(appUrl + "shop/cart");
      	waitOnElement("xpath",tblWishlist);
      	
      	WebElement wishlist = driver().findElement(By.xpath(tblWishlist));
      	List allRows = wishlist.findElements(By.tagName("tr"));
      	int all_rows = allRows.size();
      	System.out.println("Total items in Wishlist = " +all_rows);
      	
      	String before_item = "//*[@id='line_items']/tr["; 
      	String after_item = "]/td[2]/h4";
      	boolean isProductFound = false;
      	
      	if(all_rows >0){
      		for(int i=1;i<=all_rows;i++){
      			WebElement x = driver().findElement(By.xpath(before_item+i+after_item));
      			String itemName = x.getText().trim();
      			if(itemName.equalsIgnoreCase(prodName)){
      				isProductFound= true;
      				logInfo(prodName + " product match found in the shopping cart.");
      				break;
      			}
      		}
      	}
      	
      	if(isProductFound==false){
      		logInfo(prodName + " product not found in the wishlist.");
      		Assert.assertTrue(isProductFound , prodName + " product not found in the shopping cart.");
      	}
      }


      public void deleteProductsInCart(String prodName) throws Exception, Exception{
      	logInfo("inside deleteProductsInCart() method.");
      	System.out.println("inside deleteProductsInCart() method.");
      	
      	driver().navigate().to(appUrl + "shop/cart");
      	waitOnElement("xpath",tblWishlist);
      	
      	WebElement wishlist = driver().findElement(By.xpath(tblWishlist));
      	List allRows = wishlist.findElements(By.tagName("tr"));
      	int all_rows = allRows.size();
      	System.out.println("Total items in Wishlist = " +all_rows);
      	
      	String before_item = "//*[@id='line_items']/tr["; 
      	String after_item = "]/td[2]/h4";
      	
      	String before_delete = "//*[@id='line_items']/tr["; 
      	String after_delete = "]/td[6]/a/i";
      	boolean isProductFound = false;
      	
      	if(all_rows >0){
      		for(int i=1;i<=all_rows;i++){
      			WebElement x = driver().findElement(By.xpath(before_item+i+after_item));
      			String itemName = x.getText().trim();
      			if(itemName.equalsIgnoreCase(prodName)){
      				isProductFound= true;
      				logInfo(prodName + " product match found in the shopping cart.");
      				WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
      				delete.click();
      				logInfo("clicked on the delete item in the shopping cart.");
      				break;
      			}
      		}
      	}
      	
      	if(isProductFound==false){
      		logInfo(prodName + " product not found in the wishlist.");
      		Assert.assertTrue(isProductFound , prodName + " product not found in the shopping cart.");
      	}
      }


      public void deleteProductsInWishlist(String prodName) throws Exception{
      	logInfo("inside verifyProductsInWishlist() method.");
      	System.out.println("inside verifyProductsInWishlist() method.");
      	
      	driver().navigate().to(appUrl + "shop/wishlists");
      	waitOnElement("xpath",tblWishlist);
      	
      	WebElement wishlist = driver().findElement(By.xpath(tblWishlist));
      	List allRows = wishlist.findElements(By.tagName("tr"));
      	int all_rows = allRows.size();
      	System.out.println("Total items in Wishlist = " +all_rows);
      	
      	String before_item = "//*[@id='line_items']/tr["; 
      	String after_item = "]/td[3]";
      	
      	String before_delete = "//*[@id='line_items']/tr["; 
      	String after_delete = "]/td[5]/a";
      	
      	boolean isProductFound = false;
      	
      	if(all_rows >0){
      		for(int i=1;i<=all_rows;i++){
      			WebElement x = driver().findElement(By.xpath(before_item+i+after_item));
      			String itemName = x.getText().trim();
      			if(itemName.equalsIgnoreCase(prodName)){
      				isProductFound= true;
      				logInfo(prodName + " product match found in the wishlist.");
      				WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
      				delete.click();
      				logInfo("clicked on 'Remove from wishlist' link in product wishlist page.");
      				break;
      			}
      		}
      	}
      	
      	if(isProductFound==false){
      		logInfo(prodName + " product not found in the wishlist.");
      		Assert.assertTrue(isProductFound , prodName + " product not found in the wishlist.");
      	}
      }

      public void addNewVirtualProduct(String vprodName) throws Exception{
      	logInfo("inside addNewVirtualProduct() method.");
      	Robot robo = new Robot();
      		
      	verifyElementPresent("xpath",lnkAdminNewProduct);
      	clickOnElement("xpath",lnkAdminNewProduct);
      	
      	verifyElementPresent("xpath",inputShopProductName);
      	inputText("xpath",inputShopProductName,shopProductName_text);
      	inputText("xpath",inputShopProductSKU,shopVProductSKU_text);
      	inputTextClear("xpath",inputShopMasterPrice);
      	inputText("xpath",inputShopMasterPrice,shopVMasterPrice_text);
      		
      	inputTextClear("xpath",inputShopProductAvailableOn);
      	inputText("xpath",inputShopProductAvailableOn,shopVProductAvailableOn_text);
      	robo.keyPress(KeyEvent.VK_TAB);
      	robo.keyRelease(KeyEvent.VK_TAB);
      	Thread.sleep(5000);
      	
      	
      	verifyElementPresent("xpath",eleShopShippingCategory);
      	clickOnElement("xpath",eleShopShippingCategory);
      	
      	WebElement tbl = driver().findElement(By.xpath(drpdnShopTaxons));
      	List allItems = tbl.findElements(By.tagName("li"));
      	int all_items = allItems.size();
      	String before_item = "//*[@id='select2-drop']/ul[@id='select2-results-2']/li[";
      	String after_item = "]/div";
      	
      	if(all_items>0){
      		for(int j=1;j<=all_items;j++){
      			WebElement item = driver().findElement(By.xpath(before_item+j+after_item));
      			String item_name = item.getText().trim();
      			System.out.println(item_name);
      			if(item_name.matches(shopShippingCategory_text)){
      				System.out.println("clicked item " + shopShippingCategory_text);
      				item.click();
      				break;
      			} 
      		}
      	}
      	
      	clickOnButton("cssSelector",btnShopProductCreate);      	
      	waitOnElement("cssSelector",divShopMarketCheckboxGroup);
      	verifyElementPresent("cssSelector",divShopMarketCheckboxGroup);
      	WebElement chkUS = driver().findElement(By.xpath("//*[@id='market_language_checkbox']/input[1]"));
      	if(chkUS.isDisplayed()){
      		chkUS.click();
      	}
      	clickOnButton("cssSelector",btnShopProductUpdate);
       }
      
      
      public void logoutFromAdmin() throws Exception{
  		logInfo("inside logOut method.");
  		Thread.sleep(5000);
  		waitOnElement("cssSelector", lnkProfileDrpdn);
  		clickOnElement("cssSelector", lnkProfileDrpdn);
  		clickOnElement("cssSelector",logOut);
  		Thread.sleep(4000);
      }
      
         
         
}

