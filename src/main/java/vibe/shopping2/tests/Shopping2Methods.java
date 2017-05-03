package vibe.shopping2.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.apache.commons.lang3.StringUtils;


import common.TestBase;
import common.readProp;
import vibe.mycommunity.tests.CommunityMethods;

public class Shopping2Methods extends TestBase{
	
	CommunityMethods cm = new CommunityMethods();
	readProp prop = new readProp();
	

	// Navigate to admin options type page.
	
	 public void navigate2AdminOptionTypes() throws Exception{
	    	logInfo("inside navigate2AdminShop() method.");
	    	driver().navigate().to(appUrl + "shop/admin/option_types");		
	    	waitOnElement("xpath",lnkAdminNewOptionTypes);
	    }
	
	 
	// create new option types from admin
	 
	 public void createNewOptionTypes(String optionName, String optionPresentation) throws Exception{
	    	logInfo("inside createNewOptionTypes() method.");
	    	clickOnElement("xpath",lnkAdminNewOptionTypes);
	    	inputTextClear("xpath",inputOptionTypeName);
	    	inputText("xpath",inputOptionTypeName,optionName);
	    	inputTextClear("xpath",inputOptionTypePresentaion);
	    	inputText("xpath",inputOptionTypePresentaion,optionPresentation);
	    	clickOnElement("cssSelector",btnCreateOptionType);
	    	
	    	clickOnLink("linkText","Add Option Value");
	    	clickOnLink("linkText","Add Option Value");
	    	clickOnLink("linkText","Add Option Value");
	    	
	    	String[] flavour = {"Elachi", "Ginger", "Green Tea", "Black Tea"};
	    	int arr_flavours = flavour.length;
	    	
	    	WebElement tblOptionValues = driver().findElement(By.xpath("//tbody[@id='option_values']"));
	    	List allOptions = tblOptionValues.findElements(By.tagName("tr"));
	    	int all_options = allOptions.size();
	    	
	    	String before_OptionName = "//tbody[@id='option_values']/tr[";
	    	String after_OptionName = "]/td[2]/input";
	    	
	    	String before_OptionDisplay = "//tbody[@id='option_values']/tr[";
	    	String after_OptionDisplay	 = "]/td[3]/input";
	    	
	    	for(int i=1;i<=all_options;i++){
	    			inputText("xpath",before_OptionName+i+after_OptionName,flavour[i-1]);
	    			inputText("xpath",before_OptionDisplay+i+after_OptionDisplay,flavour[i-1]);
	    	}
	    	
	    	clickOnElement("cssSelector",btnUpdatePrices);
	    }
	 
	 
	 // Verify if option type is present
	 
	 public boolean verifyOptionTypeIsPresent(String optiontypeName) throws Exception{
		    logInfo("inside verifyOptionTypeIsPresent() method.");
		    
		    boolean isOptionTypeFound = false;
		    
		    WebElement tbl  = driver().findElement(By.xpath(tblOptionTypes));
		    List allRows = tbl.findElements(By.tagName("tr"));
		    int all_rows = allRows.size();
		    
		    String before_name = "//table[@id='listing_option_types']/tbody/tr[";
		    String after_name = "]/td[2]";
		    
		    if(all_rows > 0){
		    	for(int i=1;i<=all_rows;i++){
		    		WebElement optionName = driver().findElement(By.xpath(before_name+i+after_name));
		    		String actName = optionName.getText().trim();
		    		if(actName.equalsIgnoreCase(optiontypeName)){
		    			isOptionTypeFound = true;
		    			break;
		    		}
		    	}
		    }
			return isOptionTypeFound;
		    
		 }
	 	
	 
	 // delete option types
	 
	 public void deleteOptionTypes(String optiontypeName) throws Exception{
	    logInfo("inside deleteOptionTypes() method.");
	    	
	    WebElement tbl  = driver().findElement(By.xpath(tblOptionTypes));
	    List allRows = tbl.findElements(By.tagName("tr"));
	    int all_rows = allRows.size();
	    
	    String before_name = "//table[@id='listing_option_types']/tbody/tr[";
	    String after_name = "]/td[2]";
	    
	    String before_delete = "//table[@id='listing_option_types']/tbody/tr[";
	    String after_delete = "]/td[4]/a[2]";
	    
	    if(all_rows > 0){
	    	for(int i=1;i<=all_rows;i++){
	    		
	    		WebElement optionName = driver().findElement(By.xpath(before_name+i+after_name));
	    		String actName = optionName.getText().trim();
	    		if(actName.equalsIgnoreCase(optiontypeName)){
	    			WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
	    			delete.click();
	    			// Thread.sleep(2000);
	    		//	confirmEventDeleteModal();
	    			confirmOK();
	    			break;
	    		}
	    	}
	    }
	    
	 }
	 
	 
	// navigate to admin shop products page
	 
    public void navigate2AdminShop() throws Exception{
    	logInfo("inside navigate2AdminShop() method.");
    	driver().navigate().to(appUrl + "shop/admin/products");		
    	waitOnElement("xpath",lnkAdminNewProduct);
    }


    // Create a new product at admin
    
    public void createNewProduct(String prodName) throws Exception{
    	logInfo("inside createNewProduct() method.");
    
    	verifyElementPresent("xpath",lnkAdminNewProduct);
    	clickOnElement("xpath",lnkAdminNewProduct);
    	
    	verifyElementPresent("xpath",inputShopProductName);
    	inputText("xpath",inputShopProductName,prodName);   
    	
    	inputTextClear("xpath",inputShopMasterPrice);
    	inputText("xpath",inputShopMasterPrice,shopMasterPrice_text);
    		
    	inputTextClear("xpath",inputShopProductAvailableOn);
    	inputText("xpath",inputShopProductAvailableOn,shopProductAvailableOn_text);
    	    	
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
    	
     }
    
    
  // Create a new virtual product at admin
    
    public void createNewVirtualProduct(String prodName) throws Exception{
    	logInfo("inside createNewVirtualProduct() method.");
    	Robot robo = new Robot();
    		
    	verifyElementPresent("xpath",lnkAdminNewProduct);
    	clickOnElement("xpath",lnkAdminNewProduct);
    	
    	verifyElementPresent("xpath",inputShopProductName);
    	inputText("xpath",inputShopProductName,prodName);   
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
    
    
    
    public boolean searchProductByNameOrSKU(String searchType, String value, String searchMarket) throws Exception{
    	logInfo("inside searchProductByNameOrSKU() method.");
    	
    	inputTextClear("xpath",inputSearchProdName);
    	inputTextClear("xpath",inputSearchSKUName);
    	
    	switch(searchType){
	    	case "Product Name" :
	    		inputText("xpath",inputSearchProdName,value);
	    		break;
	    	case "SKU" :
	    		inputText("xpath",inputSearchSKUName,value);
	    		break;
	    	}
    	
	    	
	    	selectFromDropdown("xpath",inputSearchMarket,"byVisibleText",searchMarket);
	    	clickOnButton("xpath",btnSearchProduct);
	    	Thread.sleep(10000);
	    	boolean isProdFound = false;
	    	
	    	waitOnElement("xpath",panelProductsList);
	    	String beforeSKU_Name = "//*[@id='listing_products']/tbody/tr[";
	    	String afterSKU_Name = "]/td[1]";
	    	
	    	String beforeProd_Name = "//*[@id='listing_products']/tbody/tr[";
	    	String afterProd_Name = "]/td[4]/a";
	    	
	    	WebElement prodPanel = driver().findElement(By.xpath("//table[@id='listing_products']/tbody"));
	    	List allProducts = prodPanel.findElements(By.tagName("tr"));
	    	int all_prods = allProducts.size();
	    		    	
	    	System.out.println("Total matching products = " +all_prods);
	 
	    	if(all_prods > 0){
	    		for(int i=1;i<=all_prods;i++){
	    			
	    			switch(searchType){
	    	    	case "Product Name" :
	    	    		WebElement prod = driver().findElement(By.xpath(beforeProd_Name+i+afterProd_Name));
		    			String actProd = prod.getText().trim();
		    			System.out.println("actProd = " +actProd);
		    			if(actProd.equalsIgnoreCase(value)){
		    				System.out.println("matching value = " +value);
		    				isProdFound = true;
		    				logInfo(value + " Product Name match found.");
		    				break;
		    			}
		    			
	    	    	case "SKU" :
	    	    		WebElement sku = driver().findElement(By.xpath(beforeSKU_Name+i+afterSKU_Name));
		    			String actSKU = sku.getText().trim();
		    			System.out.println("actSKU = " +actSKU);
		    			if(actSKU.equalsIgnoreCase(value)){
		    				logInfo(value + " SKU match found.");
		    				isProdFound = true;
		    				break;
		    			}
			    	}
	    			
	    		}
	    	}
    	
  		return isProdFound;
   	
    }
    
    
    
    public boolean selectProductByName(String prodName, String searchMarket) throws Exception{
    	logInfo("inside selectProductByName() method.");
    	
    	 inputTextClear("xpath",inputSearchProdName);
	     inputText("xpath",inputSearchProdName, prodName );
	     selectFromDropdown("xpath",inputSearchMarket,"byVisibleText",searchMarket);
	     clickOnButton("xpath",btnSearchProduct);
	     Thread.sleep(10000);
     	 boolean isProdFound = false;
	     
     	waitOnElement("xpath",panelProductsList);
    	   	
    	String beforeProd_Name = "//*[@id='listing_products']/tbody/tr[";
    	String afterProd_Name = "]/td[4]/a";
    	
    	WebElement prodPanel = driver().findElement(By.xpath("//table[@id='listing_products']/tbody"));
    	List allProducts = prodPanel.findElements(By.tagName("tr"));
    	int all_prods = allProducts.size();
    		    	
    	System.out.println("Total matching products = " +all_prods);
    	
    	if(all_prods > 0){
    		for(int i=1;i<=all_prods;i++){
    			WebElement prod = driver().findElement(By.xpath(beforeProd_Name+i+afterProd_Name));
    			String actProd = prod.getText().trim();
    			System.out.println("actProd = " +actProd);
    			if(actProd.equalsIgnoreCase(prodName)){
    				logInfo(prodName + " Product Name match found.");
    				isProdFound = true;
    				prod.click();
    				break;
    			}
    		
    		}
    	}
		return isProdFound;
	 }
    
    
    
    public boolean selectProductBySKU(String skuName, String searchMarket) throws Exception{
    	logInfo("inside selectProductBySKU() method.");
    	
    	inputTextClear("xpath",inputSearchSKUName);
		inputText("xpath",inputSearchSKUName,skuName);
	     selectFromDropdown("xpath",inputSearchMarket,"byVisibleText",searchMarket);
	     clickOnButton("xpath",btnSearchProduct);
	     Thread.sleep(10000);
     	 boolean isProdFound = false;
	     
     	waitOnElement("xpath",panelProductsList);
    	   	
    	String beforeProd_Name = "//*[@id='listing_products']/tbody/tr[";
    	String afterProd_Name = "]/td[4]/a";
    	
    	WebElement prodPanel = driver().findElement(By.xpath("//table[@id='listing_products']/tbody"));
    	List allProducts = prodPanel.findElements(By.tagName("tr"));
    	int all_prods = allProducts.size();
    		    	
    	System.out.println("Total matching products = " +all_prods);
    	
    	if(all_prods > 0){
    		for(int i=1;i<=all_prods;i++){
    			WebElement prod = driver().findElement(By.xpath(beforeProd_Name+i+afterProd_Name));
    			String actProd = prod.getText().trim();
    			System.out.println("actProd = " +actProd);
    			if(actProd.equalsIgnoreCase(skuName)){
    				logInfo(skuName + " Product Name match found.");
    				isProdFound = true;
    				prod.click();
    				break;
    			}
    		
    		}
    	}
		return isProdFound;
	 }
    
    
    public void updateProductDetails(String prodName, String masterPrice, String costPrice, String costCurrency) throws Exception{
    	logInfo("inside updateProductDetails() method.");
    	Robot robo = new Robot();
    	
    	clickOnElement("xpath",lnkShopDetails);
    	
    	waitOnElement("xpath",inputShopProductName);
    	inputTextClear("xpath",inputShopProductName);
    	inputText("xpath",inputShopProductName,prodName);
    	
    	inputTextClear("xpath",inputShopMasterPrice);
    	inputText("xpath",inputShopMasterPrice,masterPrice);
    	
    	inputTextClear("xpath",inputCostPrice);
    	inputText("xpath",inputCostPrice, costPrice);
    	
    	inputTextClear("xpath",inputProdCostCurrency);
    	inputText("xpath",inputProdCostCurrency, costCurrency);
    	
    	/*inputTextClear("xpath",inputShopProductAvailableOn);
    	inputText("xpath",inputShopProductAvailableOn,"2016/08/29");
    	clickOnElement("xpath",inputShopProductAvailableOn);
    	
    	robo.keyPress(KeyEvent.VK_TAB);
    	robo.keyRelease(KeyEvent.VK_TAB);
    	Thread.sleep(5000);*/
    	
    	// Taxons
    	
    	clickOnElement("xpath",inputProdTaxons);
    	Thread.sleep(5000);
    	
    	WebElement panelTaxons = driver().findElement(By.xpath("//*[@id='select2-drop']/ul"));
    	List alltaxons = panelTaxons.findElements(By.tagName("li"));
    	int all_taxons = alltaxons.size();
    	System.out.println("all_taxons =" + all_taxons);
    	
    	String before_taxon = "//*[@id='select2-drop']/ul/li[";
    	String after_taxon = "]/div";
    	
    	if(all_taxons>0){
	    	for(int i=1;i<=all_taxons;i++){
	    		WebElement e = driver().findElement(By.xpath(before_taxon+i+after_taxon));
	    		String acttaxon = e.getText().trim();
	    		System.out.println("acttaxon =" +acttaxon);
	    		if(acttaxon.equalsIgnoreCase("Categories -> Vibe Upgrade")){
	    			e.click();
	    			break;
	    		}
	    	}
    	}
    	
    	
    	clickOnElement("cssSelector",btnShopProductUpdate);
    }

    
    public void updateVirtualProductDetails(String prodName,String sku, String masterPrice, String costPrice, String costCurrency) throws Exception{
    	logInfo("inside updateVirtualProductDetails() method.");
    	
    	clickOnElement("xpath",lnkShopDetails);
    	
    	waitOnElement("xpath",inputShopProductName);
    	inputTextClear("xpath",inputShopProductName);
    	inputText("xpath",inputShopProductName,prodName);
    	
    	inputTextClear("xpath",inputShopMasterPrice);
    	inputText("xpath",inputShopMasterPrice,masterPrice);
    	
    	inputTextClear("xpath",inputCostPrice);
    	inputText("xpath",inputCostPrice, costPrice);
    	
    	inputTextClear("xpath",inputProdCostCurrency);
    	inputText("xpath",inputProdCostCurrency, costCurrency);
    	
    	inputTextClear("xpath",inputShopProductAvailableOn);
    	inputText("xpath",inputShopProductAvailableOn,"2016/08/29");
    	
    	inputTextClear("xpath",inputShopProductSKU);
    	inputText("xpath",inputShopProductSKU, sku);
    	
    	
    	// Taxons
    	
    	clickOnElement("xpath",inputProdTaxons);
    	Thread.sleep(5000);
    	
    	waitOnElement("xpath","//*[@id='select2-drop']/ul");
    	WebElement panelTaxons = driver().findElement(By.xpath("//*[@id='select2-drop']/ul"));
    	List alltaxons = panelTaxons.findElements(By.tagName("li"));
    	int all_taxons = alltaxons.size();
    	System.out.println("all_taxons =" + all_taxons);
    	
    	String before_taxon = "//*[@id='select2-drop']/ul/li[";
    	String after_taxon = "]/div";
    	
    	if(all_taxons>0){
	    	for(int i=1;i<=all_taxons;i++){
	    		WebElement e = driver().findElement(By.xpath(before_taxon+i+after_taxon));
	    		String acttaxon = e.getText().trim();
	    		System.out.println("acttaxon =" +acttaxon);
	    		if(acttaxon.equalsIgnoreCase("Categories -> VIBE shopping")){
	    			e.click();
	    			Thread.sleep(3000);
	    			break;
	    		}
	    	}
    	}
    	
    	// Option Types
    	
    	clickOnElement("xpath",inputProdOptionTypes);
    	Thread.sleep(5000);
    	
    	waitOnElement("xpath","//*[@id='select2-drop']/ul");
    	WebElement panelOptionTypes = driver().findElement(By.xpath("//*[@id='select2-drop']/ul"));
    	List alloptions = panelOptionTypes.findElements(By.tagName("li"));
    	int all_options = alloptions.size();
    	
    	String before_option = "//*[@id='select2-drop']/ul/li[";
    	String after_option = "]/div";
    	
    	if(all_options>0){
	    	for(int j=1;j<=all_options;j++){
	    		WebElement e = driver().findElement(By.xpath(before_option+j+after_option));
	    		String actoption = e.getText().trim();
	    		System.out.println("actoption =" +actoption);
	    		if(actoption.equalsIgnoreCase(txtOptiontypeName+" ("+txtOptiontypeName+")")){
	    			e.click();
	    			Thread.sleep(3000);
	    			break;
	    		}
	    	}
    	}
    	
    	
    	clickOnElement("cssSelector",btnShopProductUpdate);
    }

    
    public void updateProductImage() throws Exception, Exception{
    	logInfo("inside updateProductImage() method.");
    	
    	clickOnElement("xpath",lnkShopImage);
    	
    	clickOnLink("linkText","Add Image");
    	
    	waitOnElement("xpath",browseAttachment);
    	 uploadFile("Image", browseAttachment);
    	/*//Thread.sleep(3000);
    	
    	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\OrganicTeaIndia.exe");
		Thread.sleep(10000);*/
    	
		inputText("xpath",inputAltText,"organic alternate");
		clickOnElement("cssSelector",btnCreateShopImg);
    }
    
    
    
    public void updateProductVariants() throws Exception, Exception{
    	logInfo("inside updateProductVariants() method.");
    	
    	clickOnElement("xpath",lnkShopVariants);
    	
    	clickOnLink("linkText","Variant From Existing Product");
    	clickOnElement("xpath",drpdnVariantOption);
    	clickOnElement("xpath","//div[@id='select2-drop']/ul[@class='select2-results']/li[2]/div");
    	inputText("xpath",inputSKUValue,txtSkuValue );  // 0101191009
    	clickOnElement("cssSelector",btnUpdatePrices);
    	Thread.sleep(10000);
    	
    	/*clickOnLink("linkText","Variant From Existing Product");
    	clickOnElement("xpath",drpdnVariantOption);
    	clickOnElement("xpath","//div[@id='select2-drop']/ul[@class='select2-results']/li[3]/div");
    	inputText("xpath",inputSKUValue,"10-0004");
    	clickOnElement("cssSelector",btnUpdatePrices);
    	
    	clickOnLink("linkText","Variant From Existing Product");
    	clickOnElement("xpath",drpdnVariantOption);
    	clickOnElement("xpath","//div[@id='select2-drop']/ul[@class='select2-results']/li[4]/div");
    	inputText("xpath",inputSKUValue,"15-0006");
    	clickOnElement("cssSelector",btnUpdatePrices);
    	
    	clickOnLink("linkText","Variant From Existing Product");
    	clickOnElement("xpath",drpdnVariantOption);
    	clickOnElement("xpath","//div[@id='select2-drop']/ul[@class='select2-results']/li[5]/div");
    	inputText("xpath",inputSKUValue,"15-0011");
    	clickOnElement("cssSelector",btnUpdatePrices);*/
   	
    }
    
    public void updateProductPrices() throws Exception, Exception{
    	logInfo("inside updateProductPrices() method.");
    	
    	clickOnElement("xpath",lnkShopPrices);
    	
    	inputText("xpath",inputRetailPrice,"800");
    	inputText("xpath",inputRetailPV,"801");
    	inputText("xpath",inputRetailCV,"802");
    	
    	inputText("xpath",inputPreferedCustomerPrice,"600");
    	inputText("xpath",inputPreferedCustomerPV,"601");
    	inputText("xpath",inputPreferedCustomerCV,"602");
    	
    	inputTextClear("xpath",inputWholesalePrice);
    	inputText("xpath",inputWholesalePrice,"300");
    	inputTextClear("xpath",inputWholesalePV);
    	inputText("xpath",inputWholesalePV,"301");
    	inputTextClear("xpath",inputWholesaleCV);
    	inputText("xpath",inputWholesaleCV,"302");
    	
    	clickOnElement("cssSelector",btnUpdatePrices);
    	
    }
    
    public void updateProductAssets(String assetTitle) throws Exception, Exception{
    	logInfo("inside updateProductAssets() method.");
    	
    	clickOnElement("xpath",lnkShopAssets);
    	
    	// Add New Document
    	
    	clickOnLink("linkText","New Document");
    	waitOnElement("xpath",inputProdAssetTitle);
    	inputText("xpath",inputProdAssetTitle,assetTitle);
    	waitOnElement("cssSelector",browseDocument);
    	 uploadFile("PDF", browseDocument);
    	
    	/*Thread.sleep(3000);
    	
    	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_PDF.exe");
		Thread.sleep(10000);
		*/
		clickOnElement("xpath",btnCreateProductAsset);
		
		// Add New Video
		
		clickOnLink("linkText","New Video");
    	waitOnElement("xpath",selectVideoType);
    	selectFromDropdown("xpath",selectVideoType, "byVisibleText", "URL");
    	inputText("xpath",inputShoppingVideoTitle, txtVideoAsset);
    	inputText("xpath",inputShoppingVideoURL, "https://www.youtube.com/watch?v=e2qbJVe80kw");
    	
		clickOnElement("xpath",btnCreateVideoAsset);
    }
    
    
    
    
    
    public void go2AdminShopSettingsPage() throws Exception, Exception{
    	logInfo("inside go2AdminShopSettingsPage() method.");
    	driver().navigate().to(appUrl + "shop/admin/general_settings/edit");		
    	Assert.assertTrue(verifyTextPresent("General Settings"));
    }
    
    
     public void setAdminFeatureSettings(String settingsName, String value) throws Exception{
    	logInfo("inside setAdminFeatureSettings() method.");
    	
    	clickOnLink("linkText","Features Settings");
    	
    	switch(settingsName){
	    	case "Markets" :
	    		inputTextClear("cssSelector",inputPreferenceLiveMarkets);
	    		inputText("cssSelector",inputPreferenceLiveMarkets,value);
	    		break;
	    	case "Nfr Markets" :
	    		inputTextClear("cssSelector",inputPreferenceNFRMarkets);
	    		inputText("cssSelector",inputPreferenceNFRMarkets,value);
	    		break;
	    	case "Billing Address Settings" :
	    		selectFromDropdown("cssSelector",drpdnPreferenceBillingAddressSettings,"byVisibleText",value);
	    		break;
	    	case "Reviews Settings" :
	    		selectFromDropdown("cssSelector",drpdnPreferenceReviewsSettings,"byVisibleText",value);
	    		break;
	    	case "Wishlist Settings" :
	    		selectFromDropdown("cssSelector",drpdnPreferenceWishlistSettings,"byVisibleText", value);
	    		break;
	    	case "Analytics Settings" :
	    		selectFromDropdown("cssSelector",drpdnPreferenceAnalyticsSettings,"byVisibleText", value);
	    		break;	
	    	case "Recently Viewed Settings" :
	    		selectFromDropdown("cssSelector",drpdnPreferenceShowRecentlyReviewsItems,"byVisibleText", value);
	    		break;	
	    	case "Show Most Popular" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesShowMostPopularCategories,"byVisibleText", value);
	    		break;	
	    	case "Allow Orders In Other Markets" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesAllowOrdersInOtherMarkets,"byVisibleText", value);
	    		break;		
	    	case "Show Reorder In Order History" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesShowReorderInOrderHistory,"byVisibleText", value);
	    		break;	
	    	case "Can Delete Final Autoship" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesCanDeleteFinalAutoship,"byVisibleText", value);
	    		break;	
	    	case "Can Dispaly Autoship On Homepage" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesCanDisplayAutoshipOnHomepage,"byVisibleText", value);
	    		break;	
	    	case "Can Dispaly Autoship On Cartpage" :
	    		selectFromDropdown("cssSelector",drpdnPreferencesCanDisplayAutoshipOnCartpage,"byVisibleText", value);
	    		break;	
	    	case "Single Autoship" :
	    		selectRadioOrCheckbox("cssSelector",radioPreferenceSingleAUtoship);
	    		break;
	    	case "Multiple Autoships" :
	    		selectRadioOrCheckbox("cssSelector",radioPreferenceMultipleAUtoship);
	    		break;	
    	}
    	
    	clickOnElement("cssSelector",btnUpdateFetureSettings);
    	Thread.sleep(3000);
    	confirmationMessage("Successfully Updated");
    	Thread.sleep(5000);
    	
    }
    
    
    // Shopping User side methods
    
     public void go2Homepage() throws Exception{
 		logInfo("inside go2Homepage() method");
 		
 		driver().navigate().to(appUrl);
 	}
     
     
     public void shopLogout() throws Exception, Exception{
    	 logInfo("inside shopLogout() method");
    	 
    	 clickOnElement("cssSelector",shopProfileDrpdn);
    	 clickOnElement("cssSelector",shopLogout);
    	 
     }
     
	public void selectMarket(String market) throws Exception{
		logInfo("inside selectMarket() method");
		
		selectFromDropdown("cssSelector",selectMark,"byVisibleText",market);
		clickOnButton("cssSelector", markOkBtn);
	}
    
	public void custShoppingNavigation() throws Exception{
		logInfo("inside custShoppingNavigation() method");
		
		driver().navigate().to(appUrl + "shop/login");
	}
	
	
	
	//Select Products from search field and the select the product
    
  /*  public void searchItembyNameOrSku(String nameorSku ) throws Exception{   
    	logInfo("inside searchItembyNameOrSku() method");
	  	inputText("cssSelector", searchByName, nameorSku);
	  	submitElement("cssSelector", searchByName); 
	  	boolean isProdPresent = false;
			List <WebElement> lis = driver().findElements(By.cssSelector(productList));
			System.out.println(lis.size());
			for (WebElement lt : lis){
				System.out.println(lt.getText());
				if (lt.getText().trim().contains(nameorSku)){	
					isProdPresent=true;
					lt.click();
					Thread.sleep(5000);
					// clickOnLink("linkText", "View Details");
					break;
				}			
			}if(isProdPresent==false){
				Assert.assertTrue(isProdPresent, nameorSku+ " is not found" );
			}  		
		}
	*/
    
    public boolean verifyProductReviewsPresent(String prodName){
    	logInfo("inside verifyProductReviewsPresent() method");
    	
    	boolean isReviewPanePresent = false;
    	
    	WebElement reviewPane = driver().findElement(By.xpath(panelReviews));
    	if(reviewPane.isDisplayed()){
    		isReviewPanePresent = true;
    		System.out.println("isReviewPanePresent = " +isReviewPanePresent);
    	}
    	
    	return isReviewPanePresent;
    }
    
    
    public void existingUserCredentials(String shopUser, String passwd, String seletMarket) throws Exception {
  	  // logInfo("Entered existingUserCredentials() method");   
  	  	logInfo("inside  existingUserCredentials() method");   
  	  	Thread.sleep(5000);
		logOut();	
		custShoppingNavigation();
		selectMarket(seletMarket);		
		// clickOnLink("linkText", "Login");
		waitOnElement("cssSelector","ul.nav.navbar-nav > li:nth-child(1) > a > span");
		clickOnElement("cssSelector","ul.nav.navbar-nav > li:nth-child(1) > a > span");		// login link
		Thread.sleep(2000);		
		//driver().navigate().refresh();
		//Thread.sleep(2000);		
		waitOnElement("xpath", inputName);
		inputTextClear("xpath", inputName);
		inputText("xpath", inputName, shopUser);
		if (driver().getCurrentUrl().contains("branch")){ 
		inputText("cssSelector", custShopPwdInBranch, passwd);
		}else{
			inputText("cssSelector", custShopPwdInMaster, passwd);
		}
	    clickOnButton("cssSelector", userloginBtn);
	    Thread.sleep(10000);
	}
    
    
    public int verifyMarketSelectionsCnt() throws Exception {
    	  logInfo("Entered verifyMarketSelections() method"); 
    	  
    	  clickOnElement("cssSelector","div.pull-right.current-market>a");

			Select select = new Select(driver().findElement(org.openqa.selenium.By.cssSelector("select#market")));
			List <WebElement> allOptions = select.getOptions();
			for(WebElement x : allOptions){
				System.out.println(x.getText().trim());
			}
			
			int cnt = allOptions.size();
			
			clickOnElement("cssSelector", "div.modal-content > div.modal-footer > button.btn.btn-default ");
			Thread.sleep(5000);
			return cnt;
    }
    
    
   
 
   /* // select button either Next or Save& Continue in Shipping 
	    public void selectNextRSaveNdContinueInShipping(String typeNextOrSave) throws Exception{    	  
	  	 logInfo("entered into selectNextRSaveNdContinueInShipping(String typeNextOrSave) method");
	  	  if (typeNextOrSave=="next"){    	  
	  	  clickOnButton("cssSelector", shipNext);
	  	  
	  	  System.out.println("selected Next");
	  	  } else {    		 
	  		  clickOnButton("cssSelector", shipSave); 
	  		  System.out.println("selected save");
	  	  }Thread.sleep(2000);
	    }*/
    
    public int verifyBillingAddressSettings() throws Exception{
    	logInfo("inside verifyBillingAddressSettings() method.");	
    	
    	selectNextRSaveNdContinueInShipping("next");
		selectNextRSaveNdContinueInShipping("next");
		
		clickOnElement("cssSelector","#use_existing_card_no");
		clickOnElement("cssSelector","#use_existing_billing_address_no");
		
		Select select = new Select(driver().findElement(By.cssSelector("#order_payment_source_address_country_id")));
		List allOptions = select.getOptions();
		int all_items = allOptions.size();
		System.out.println("all_items = "+all_items);
		
		return all_items;
    }
    
    
    public void go2orderHistoryPage(){
    	logInfo("inside go2orderHistoryPage() method.");	
    	driver().navigate().to(appUrl + "shop/orderhistory");
    }
    
    public void go2AutoshipPage(){
    	logInfo("inside go2AutoshipPage() method.");	
    	driver().navigate().to(appUrl + "shop/autoships");
    }
    
    
    public boolean verifyAutoshipSettings() throws Exception{
	    	logInfo("inside verifyAutoshipSettings() method.");	
	    	
	    	go2AutoshipPage();
	    		    	
	    	boolean isNewAutoshipPresent = verifyElementPresent("xpath","//*[@id='main-content']/div/div[1]/div[2]/div/div[2]/a");
	    	if(isNewAutoshipPresent){
	    		logInfo("isNewAutoshipPresent =" +isNewAutoshipPresent);
	    		clickOnElement("xpath","//*[@id='main-content']/div/div[1]/div[2]/div/div[2]/a");
	    	}
	    	
	    	waitOnElement("cssSelector","div.panel-tools > nav > ul.nav.navbar-nav.navbar-right  > li:nth-child(2) > a");
	    	
	    	WebElement myAutoships = driver().findElement(By.cssSelector("div.panel-tools > nav > ul.nav.navbar-nav.navbar-right  > li:nth-child(2) > a"));
	    	String act_text = myAutoships.getText().trim();
	    	System.out.println("act_text =" +act_text);
	    	boolean isMyAutoshipPresent = false;
	    	if(act_text.equalsIgnoreCase("My Autoships")){
	    		isMyAutoshipPresent = true;
	    		logInfo("isMyAutoshipPresent =" +isMyAutoshipPresent);
	    	}
	    	
	    	System.out.println("isMyAutoshipPresent =" +isMyAutoshipPresent);
	    	
		return isMyAutoshipPresent;
    }
    
    
    // delete a product from admin
    
    public void deleteProduct(String prodName) throws Exception, Exception{
    	logInfo("inside deleteProduct() method");
    	
    	
    	waitOnElement("xpath",panelProductsList);
    	
    	String before_delete = "//*[@id='listing_products']/tbody/tr[";
    	String after_delete = "]/td[6]/a[2]";
    	
    	String beforeProd_Name = "//*[@id='listing_products']/tbody/tr[";
    	String afterProd_Name = "]/td[4]/a";
    	
    	WebElement prodPanel = driver().findElement(By.xpath("//table[@id='listing_products']/tbody"));
    	List allProducts = prodPanel.findElements(By.tagName("tr"));
    	int all_prods = allProducts.size();
    		    	
    	System.out.println("Total matching products = " +all_prods);
    	if(all_prods>0){
    		for(int i=1;i<=all_prods;i++){
    			WebElement ename = driver().findElement(By.xpath(beforeProd_Name+i+afterProd_Name));
    			String actProd = ename.getText().trim();
    			if(actProd.equalsIgnoreCase(prodName)){
    				WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
    				delete.click();
    				Thread.sleep(2000);
    				try{
    					//confirmEventDeleteModal();
    					confirmOK();
    				} catch(Exception e) {
    					e.getMessage();
    				}
    				Thread.sleep(2000);
    				break;
    			}
    		}
    	
    		
    	}
    }
	
	
	
	// Ravi
	public void customerShopNavigation() throws Exception{
		logInfo("Enter url for customer shopping");	
		System.out.println("Shop Url");
		Thread.sleep(3000);			
		driver().navigate().to(appUrl + "/shop/login"); 			
		selectCustMarket("United States");
		
		driver().navigate().to(appUrl + "/shop/login"); 	
		}
	
		
	public void selectCustMarket(String market) throws Exception{
		waitOnElement("linkText", market);
		clickOnLink("linkText", market);
		
		/*WebElement mrt = driver().findElement(By.cssSelector(selectMark));
		Select select = new Select (mrt);
		select.selectByVisibleText(market);*/
		
		
	}
	
	public void nav2CustShopping(){
		logInfo("Enter url for shopping without user.");			
		driver().navigate().to(appUrl + "/shop");  
	}
	
	public void nav2OrderHistory(){
		logInfo("Enter url for shopping without user.");			
		driver().navigate().to(appUrl + "/shop/orderhistory"); 
	}

	public void navMarketInEnrollment(){
		logInfo("Enter url to access Market .");			
		driver().navigate().to(appUrl +"/pyr_core/v2_enrollments/select_market");
	}
	
	
	public void nav2WishList() throws Exception{
		logInfo("Enter url to access Market .");	
		Thread.sleep(3000);
		driver().navigate().to(appUrl +"shop/wishlists");
	}
	
	public void nav2ShopCart() throws Exception{
		logInfo("Enter url to access Market .");	
		Thread.sleep(3000);
		driver().navigate().to(appUrl +"shop/cart");
	}
	
	public void logoutFromShopCustomer() throws Exception{
		logInfo("Entered into logoutFromShopCustomer() method");
		System.out.println("Entered into logoutFromShopCustomer() method");
		Thread.sleep(7000);
		clickOnElement("cssSelector", userIconInShop);
		clickOnElement("cssSelector", logoutCust);
		confirmationMessage("Signed out successfully.");
		
		
	}
	
	public void registerNewCustomer(String Fname, String Lname) throws Exception {
		
		logInfo("Register New Customer with all fields.");
		try{	
		registration(Fname, Lname);						
		}catch (Exception e){			
			logger.error("Failed!! Some mandatory fields are missing.");
		}			
		
	}
	
	
	public void registration(String Fname, String Lname) throws Exception {
		
		logInfo("Register New Customer with all fields.");
		try{
			
		clickOnLink("linkText", "Login");
		clickOnButton("cssSelector", registerNew);	
		inputTextClear("cssSelector", custFN);
		Thread.sleep(2000);
		inputText("cssSelector", custFN, Fname);
		inputText("cssSelector", custLN, Lname);
		inputText("cssSelector", custEmail, (Fname+Lname+"@icentris.com"));
		inputText("cssSelector", custPhone, "1234567891");
		inputText("cssSelector", custAd1, "Roxbury Drive");
		inputText("cssSelector", custAd2, "Beverly Hills");
		inputText("cssSelector", custCounty, "El Dorado");
		inputText("cssSelector", custCity, "CA");
		stateSelection("Arkansas");			
		inputText("cssSelector", custZip, "90210");
		inputTextClear("cssSelector", custUserName);
		inputText("cssSelector", custUserName, Fname);
		inputText("cssSelector", custPwd, custPwdText);
		inputText("cssSelector", custConPwd, custPwdText);			
		Thread.sleep(4000);			
		clickOnButton("cssSelector", custCreate);	
		confirmationMessage("Welcome! You have signed up successfully.");
		Thread.sleep(4000);		
		if(driver().findElement(By.cssSelector(error404)).getText().equals(notImplimentedText)){
			System.out.println("Enterd eroor message");
			WebElement panelTit = driver().findElement(By.cssSelector(error404));
			System.out.println(panelTit.getText());
			Assert.assertEquals(panelTit.getText(), "Save");	
		}else {
			WebElement panelTit = driver().findElement(By.cssSelector(panelTitle));
			System.out.println(panelTit.getText());
			Assert.assertEquals(panelTit.getText(), "Shop");	
			System.out.println("Eaxctly as shop");
			
		}
		
					
		}catch (Exception e){
			
			logger.error("Failed!! Some mandatory fields are missing.");
		}			
		
	}
	
	
	public void genderAndDOB(String gender) throws Exception{
		
		inputText("cssSelector", custDOB, "02/01/1990");		
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
	
	public void stateSelection (String enterState) throws Exception{
		Thread.sleep(2000);
		clickOnElement("cssSelector", custState);
		boolean isState= false;	
		List <WebElement> ss = driver().findElements(By.cssSelector(custStateList));
		for (WebElement sts : ss){
			if (sts.getText().equalsIgnoreCase(enterState)){
				isState = true;
				Actions action = new Actions(driver()).doubleClick(sts);
				action.build().perform();				
				break;
			}
			
		}if (isState==false){
			Assert.assertTrue(isState, enterState +" is not present");
		}
		
		

	}
	
	public void selectState(String enterState) throws Exception, Exception{			
		logInfo("Select "+ enterState +" State from the USA States");
		   
		    Thread.sleep(2000);
		    clickOnElement("cssSelector", state);
		    
		    Select stat = new Select(driver().findElement(By.cssSelector(state)));
		    stat.selectByVisibleText(enterState);
		    Thread.sleep(1000);
		    stat.selectByVisibleText(enterState);
		    
			
			}	
	
	public void selectState_0ld(String enterState) throws Exception, Exception{			
		logInfo("Select "+ enterState +" State from the USA States");
		    boolean isStateFound = false;
		    Thread.sleep(2000);
		    clickOnElement("cssSelector", state);
			List<WebElement> stat = driver().findElements(By.cssSelector(stateOpt));
			for (WebElement state: stat){
				if (state.getText().equalsIgnoreCase(enterState)){
					isStateFound =true;
					System.out.println(enterState + " state is found");
					state.click();
					Thread.sleep(2000);
					break;					
				}				
			}if (isStateFound==false){
				Assert.assertTrue(isStateFound , enterState+ " state is not avaiable In dropdown" );
			}			
			
			}	
	
	
      public void validateFields(String Fname, String Lname) throws Exception {
		
		logInfo("Register New Customer with all fields.");
		try{			
		clickOnLink("linkText", "Login");
		clickOnButton("cssSelector", registerNew);	
		inputTextClear("cssSelector", custFN);
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);		
		WebElement title = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+title.getText());		
		Assert.assertEquals(title.getText(), imgAlertText); 
		
		Thread.sleep(2000);
		inputText("cssSelector", custFN, Fname);
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);
		WebElement ln = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+ln.getText());		
		Assert.assertEquals(ln.getText(), imgAlertText);
		
		Thread.sleep(2000);
		inputText("cssSelector", custLN, Lname);
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);
		WebElement eml = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+eml.getText());		
		Assert.assertEquals(eml.getText(), emailAlert);
		
		inputText("cssSelector", custEmail, (Fname+"."+Lname+"@icentris.com"));
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);
		WebElement ad1 = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+ad1.getText());		
		Assert.assertEquals(ad1.getText(), custAlertText);
		
		inputText("cssSelector", custAd1, "Roxbury Drive");
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);
		WebElement zip = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+zip.getText());		
		Assert.assertEquals(zip.getText(), custAlertText);
		inputText("cssSelector", custZip, "90210");
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);
		WebElement cit = driver().findElement(By.cssSelector(resTitleValidate));
		System.out.println("aert message is "+cit.getText());		
		Assert.assertEquals(cit.getText(), custAlertText);
		Thread.sleep(1000);
		inputText("cssSelector", custCity, "CA");
		inputTextClear("cssSelector", custUserName);
		inputText("cssSelector", custUserName, Fname);
		inputText("cssSelector", custPwd, custPwdText);
		inputText("cssSelector", custConPwd, custPwdText);		
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);		
		WebElement country = driver().findElement(By.cssSelector(alertMessage));
		System.out.println("aert message is "+country.getText());	
		Assert.assertEquals(country.getText(), countryAlert);
		stateSelection("Arkansas");		
		Thread.sleep(1000);			
		clickOnButton("cssSelector", custCreate);	
		confirmationMessage("Welcome! You have signed up successfully.");
							
		}catch (Exception e){
			
			logInfo("Failed!! Some mandatory fields are missing.");
		}			
		
	}
      
      
      // logout from Admin and login with existing shopping customer
      public void existingUserCredentials(String shopUser) throws Exception {
    	logInfo("Entered existingUserCredentials() method");    	    
    	
  		logOut();	
  		customerShopNavigation();
  		waitOnElement("linkText", "Login");  		
  		clickOnLink("linkText", "Login");  		
  		selectCustMarket("United States");		
		/*clickOnButton("cssSelector", markOkBtn); 	*/	
		waitOnElement("cssSelector", returnExist);	
		clickOnButton("cssSelector", returnExist);	
		waitOnElement("cssSelector", inputName);	
		WebElement name = driver().findElement(By.xpath(inputName));
		System.out.println(name.getAttribute("placeholder"));
		
		inputTextClear("xpath", inputName);			
		inputText("xpath", inputName, shopUser);
		waitOnElement("cssSelector", custShopPwdInMaster);	
		inputText("cssSelector", custShopPwdInMaster, custPwdText);
		waitOnElement("cssSelector", custShopPwdInMaster);
		waitOnElement("cssSelector", custShopPwdInMaster);
		submitElement("cssSelector", custShopPwdInMaster);
				
			/*if(driver().findElement(By.cssSelector(error404)).getText().equals(notImplimentedText)){
				System.out.println("Enterd eroor message");
				WebElement panelTit = driver().findElement(By.cssSelector(error404));
				System.out.println(panelTit.getText());
				Assert.assertEquals(panelTit.getText(), "Save");	
			}else {
				WebElement panelTit = driver().findElement(By.cssSelector(panelTitle));
				System.out.println(panelTit.getText());
				Assert.assertEquals(panelTit.getText(), "Shop");			
			}*/		
		}
      
      
      
      public void nav2AutoshipCustomer(){			
  		logInfo("Navigating My Autoship with customer credentials");	
  		driver().navigate().to(appUrl +"/shop/autoships");
  		
  	}
      
      
      //Select Products Option from Category Drop dow
      public void selectProductsfromCategory(String categoryItems) throws Exception{			
  		logInfo("Entered into selectProductsfromCategory(String categoryItems) method");
  		Select select = new Select(driver().findElement(By.cssSelector(selectCategory)));
		select.selectByVisibleText(categoryItems);			
  	}
      
      //Select Products from search field and the select the product
      public void searchItembyNameOrSku(String nameorSku ) throws Exception{    	  
    	inputText("cssSelector", searchByName, nameorSku);
    	submitElement("cssSelector", searchByName); 
    	boolean isProdPresent = false;
  		List <WebElement> lis = driver().findElements(By.cssSelector(productList));
  		System.out.println(lis.size());
  		for (WebElement lt : lis){
  			System.out.println(lt.getText());
  			if (lt.getText().trim().contains(nameorSku)){	
  				isProdPresent=true;
  				lt.click();  				
  				break;
  			}			
  		}if(isProdPresent==false){
  			Assert.assertTrue(isProdPresent, nameorSku+ " is not found" );
  		}  		
  	}
      
      public void addQuanityNdCheckOut(int moreQuantiy) throws Exception{		
  		logInfo("Entering number of Quanity of products");		
  		try{ 
  			/*String qty = Integer.toString(moreQuantiy);
  			submitElement("cssSelector", qtyField);
  			inputTextClear("cssSelector", qtyField);
  			inputText("cssSelector", qtyField, qty);*/
  			if (driver().getCurrentUrl().contains("master")||
  				 driver().getCurrentUrl().contains("monat")||
  				driver().getCurrentUrl().contains("idlife")){ 
  					for(int i=1; i <=moreQuantiy-1; i++){  
  		  				waitOnElement("cssSelector", moreIcon); 
  		  				clickOnButton("cssSelector", moreIcon);
  					}
  				selectRadioOrCheckbox("cssSelector", oneTime);  		    
     		  }  			
  			clickOnButton("cssSelector", addTocart);  
			//waitOnElement("linkText", "View Cart");
			clickOnLink("linkText", "View Cart");			
			waitOnElement("cssSelector", checkOut);
			clickOnButton("cssSelector", checkOut);	  			
  			}catch(Exception e){
  			logInfo("Failed!! Not able to enter quanity of products");
  		}	
  		
  		}
      
      public void addAutoshipQuanityNdCheckOut(int moreQuantiy) throws Exception{		
    		logInfo("Entering number of Quanity of products");		
    		try{
    			Thread.sleep(2000);
    			for(int i=1; i <=moreQuantiy-1; i++){  				
    				clickOnButton("cssSelector", moreIcon);  				
    				Thread.sleep(3000);
    			}
    			selectRadioOrCheckbox("cssSelector", autoshipRadio);
    			
    				clickOnButton("cssSelector", addTocart);
    				clickOnLink("linkText", "View Cart");
    				Thread.sleep(2000);
    				/*driver().navigate().to(appUrl+ "shop/");    // this is temp implemented
    				Thread.sleep(2000);
    				clickOnButton("cssSelector", checkOut);	*/
    				Thread.sleep(2000);
    				clickOnButton("cssSelector", checkOut);	
    				Thread.sleep(8000);
    				
    			}catch(Exception e){
    			logInfo("Failed!! Not able to enter quanity of products");
    		}	
    		
    		}
      
      
      public void addQuanityNdCheckoutOfNonUser(int moreQuantiy) throws Exception{		
    		logInfo("Entering number of Quanity of products");		
    		try{
    			Thread.sleep(2000);
    			for(int i=1; i <=moreQuantiy-1; i++){  				
    				clickOnButton("cssSelector", moreIcon);  				
    				Thread.sleep(3000);
    			}
    			    selectRadioOrCheckbox("cssSelector", oneTime);
    				clickOnButton("cssSelector", addTocart);
    				clickOnLink("linkText", "View Cart");
    				Thread.sleep(4000);
    				/*driver().navigate().to(appUrl+ "shop/");    // this is temp implemented
    				Thread.sleep(2000);
    				clickOnButton("cssSelector", checkOut);		*/
    				clickOnButton("cssSelector", checkOut);	
    				
    			}catch(Exception e){
    			logInfo("Failed!! Not able to enter quanity of products");
    		}	
    		
    		}
      
      //add quanity in autoship and then checkOut
      public void addQuanityInAutoShipNdCheckOut(int moreQuantiy) throws Exception{		
    		logInfo("Entered addQuanityInAutoShipNdCheckOut() method");		
    		try{
    			Thread.sleep(2000);
    			for(int i=1; i <=moreQuantiy-1; i++){  				
    				clickOnButton("cssSelector", moreIcon);  				
    				Thread.sleep(3000);  
    									}   			
    			clickOnElement("cssSelector", btnAddToAutoship);    				
    			Thread.sleep(2000); 		    				
    			}catch(Exception e){
    			logInfo("Failed!! Not able to enter quanity of products in Autoship");
    		}	
    		
    		}
      
  	public boolean verifyProductInWishList(String product) throws Exception{    	
  	  
  	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
  	  System.out.println(lis.size());
  	  boolean isProdPresent = false;
  	  for (int i=1; i<=lis.size(); i++){
  		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+wlItemAft));
  		  System.out.println("products are"+ prod.getText());
  		  if (prod.getText().contains(product)){
  			  isProdPresent=true;    			  
  			  break;
  		  }    		  
  	  }if (isProdPresent==false){
  		  Assert.assertTrue(isProdPresent, product + " is not present." );
  	  }
		return isProdPresent; 	  
  	  
    }
    	
    	public void verifyProductNotToPresentInWishList(String product) throws Exception{    	
    	  
    	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
    	  System.out.println(lis.size());
    	  boolean isProdPresent = true;
    	  for (int i=1; i<=lis.size(); i++){
    		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+wlItemAft));
    		  System.out.println("products are"+ prod.getText());
    		  if (prod.getText().contains(product)){
    			  isProdPresent=false;  
    			  Assert.assertTrue(isProdPresent, isProdPresent + " is still present." );
    			  break;
    		  }    		  
    	  }if (isProdPresent==true){
    		Assert.assertNotSame(isProdPresent, product);
    	  }     	  
      }
    	
    	
    	public boolean verifyProductInCart(String product) throws Exception{    	
    	  
    	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
    	  System.out.println(lis.size());
    	  boolean isProdPresent = false;
    	  for (int i=1; i<=lis.size()*2; i++){
    		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+cartItemAft));
    		  System.out.println("products are in Item"+ prod.getText());
    		  if (prod.getText().contains(product)){
    			  isProdPresent=true;    			  
    			  break;
    		  }i++;    		  
    	  }if (isProdPresent==false){
    		  Assert.assertTrue(isProdPresent, product + " is not present in CartList" );
    	  }
		return isProdPresent; 	  
    	  
      }
    
    	
    	 public void removeProductFromCart(String product) throws Exception{    	  
     	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
     	  System.out.println(lis.size());
     	  boolean isProdPresent = false;
     	  for (int i=1; i<=lis.size()*2; i++){
     		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+cartItemAft));
     		  System.out.println("products are"+ prod.getText());
     		  if (prod.getText().contains(product)){
     			  isProdPresent=true;
     			  WebElement del = driver().findElement(By.cssSelector(wishListBfr+i+cartDeleteAft));
     			  del.click();
     			  break;
     		  }  i++;  		  
     	  }if (isProdPresent==false){
     		  Assert.assertTrue(isProdPresent, product + " is not present." );
     	  }
       }
    	 
    	public void verifyProductNotToPresentInCartList(String product) throws Exception{  	
      	  
      	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
      	  System.out.println(lis.size());
      	  boolean isProdPresent = true;
      	  for (int i=1; i<=lis.size()*2; i++){
      		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+cartItemAft));
      		  System.out.println("products are"+ prod.getText());
      		  if (prod.getText().contains(product)){
      			  isProdPresent=false;  
      			  Assert.assertTrue(isProdPresent, isProdPresent + " is still present." );
      			  break;
      		  } i++;   		  
      	  }if (isProdPresent==true){
      		Assert.assertNotSame(isProdPresent, product);
      	  }     	  
        } 	
    	public void validIncndDescQty(String product, int increaseQty, int decreaseQty) throws Exception{    	  
       	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
       	  System.out.println(lis.size());
       	  boolean isProdPresent = false;
       	  for (int i=1; i<=lis.size()*2; i++){
       		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+cartItemAft));
       		  System.out.println("products are"+ prod.getText());
       		  if (prod.getText().contains(product)){
       			  isProdPresent=true;
       			  Thread.sleep(3000);
       			  WebElement retrieveQty = driver().findElement(By.cssSelector(wishListBfr+i+cartQtyAft));
       			  String  qty= retrieveQty.getAttribute("value").trim();
       			  System.out.println("qty" + qty);
       			  int current = Integer.parseInt(qty);  
       			  
       			  int expIncrQty =  current+ increaseQty;
       			  int expDecsQty = expIncrQty-decreaseQty;
       			  String expctedIncreasedQty = Integer.toString(expIncrQty);
       			  String expctedDescreasedQty = Integer.toString(expDecsQty);
       			 System.out.println("current Qty "+current);
       			System.out.println("expctedIncreasedQty "+expctedIncreasedQty);
       			System.out.println("expctedDescreasedQty Qty "+expctedDescreasedQty);
       			  
       			  for (int j=1; j<=increaseQty; j++){
       				 WebElement incre = driver().findElement(By.cssSelector(wishListBfr+i+cartIncQtyAft));
       				 incre.click();        				  
       			  }
       			 WebElement incredQty = driver().findElement(By.cssSelector(wishListBfr+i+cartQtyAft));
       			 String actualIncreasedQty = incredQty.getAttribute("value").trim();  
       			System.out.println("actualIncreasedQty "+actualIncreasedQty);
       			 Assert.assertEquals(actualIncreasedQty, expctedIncreasedQty);         			  
       			  
       			  
       			 for (int k=1; k<=decreaseQty; k++){
       				 WebElement incre = driver().findElement(By.cssSelector(wishListBfr+i+cartDescQtyAft));
       				 incre.click();        				  
       			  }
       			 WebElement descQty = driver().findElement(By.cssSelector(wishListBfr+i+cartQtyAft));
      			 String actualDescreasedQty = descQty.getAttribute("value").trim();
      			 System.out.println("actualDescreasedQty "+actualDescreasedQty);
      			 int actualDesc = Integer.parseInt(actualDescreasedQty);
      			  if(actualDesc==0){
      				  Assert.assertEquals(actualDescreasedQty, "0");        				  
      			  }else{
      				  Assert.assertEquals(actualDescreasedQty, expctedDescreasedQty);
      			  } 			  
       			
       			  break;
       		  }  i++;  		  
       	  }if (isProdPresent==false){
       		  Assert.assertTrue(isProdPresent, product + " is not present." );
       	  }
         }
    
    	public void writeAReview (String review, int rateStar_1_to_5) throws Exception{
    		logInfo("Entered into writeAReview() method.");     
    		String msg = "Thanks for submitting review, Please note that all reviews are moderated for quality and relevance.";
    		clickOnLink("cssSelector", writeReview);
    		Thread.sleep(3000);      		
    		starRating(rateStar_1_to_5);
    		inputTextClear("cssSelector", revTitle);
    		inputText("cssSelector", revTitle, review);
    		inputText("cssSelector", revComment, review);
    		Thread.sleep(3000);
    		clickOnButton("cssSelector", revSubmit);
    		confirmationMessage(msg);
    		Thread.sleep(3000);
    		System.out.println("completed");
    		
    	}
    		
    		public void starRating (int rateStar_1_to_5) throws Exception{
        		logInfo("Entered into starRating() method.");  		
        		String rated = Integer.toString(rateStar_1_to_5);
        		/*if (rateStar_1_to_5<=0||rateStar_1_to_5<5) {      			
        			Assert.assertEquals(rateStar_1_to_5, "0");
        		}else*/{
        			switch (rated){
        			case "1":
        				clickOnElement("cssSelector", singleStar);
        				break;
        			case "2":
        				clickOnElement("cssSelector", twoStar);
        				break;
        			case "3":
        				clickOnElement("cssSelector", threeStar);
        				break;
        			case "4":
        				clickOnElement("cssSelector", fourStar);
        				break;
        			case "5":
        				clickOnElement("cssSelector", fiveStar);
        				break;
        			default:
        				System.err.println("entered unnecessary Star rated");
        				Assert.assertNull(rated);
        			 }
        			
        		}
    		
    	}
    		
    public void acceptReviewFromAdmin(String product, String userName, String reviewTitle) throws Exception{
  	  selectSideMenuInAdmin("Reviews");
  	  searchReviewsInAdmin(product, userName, reviewTitle);
  	  
  	  
  	  
    }
			
	
   //Can be select side menu like .. Products, OptionTypes, Properties etc.. 		
    public void selectSideMenuInAdmin(String menuType) throws Exception{
  	  logInfo("Enetered into selectSideMenuInAdmin() method");
  	  boolean isMenuPresent = false;
  	  Thread.sleep(3000);
  	  List <WebElement> menu = driver().findElements(By.cssSelector(sideMenu));
  	  System.out.println(menu.size());
  	  for (WebElement menus : menu){
  		  if (menus.getText().trim().equalsIgnoreCase(menuType)){
  			  isMenuPresent = true;
  			  menus.click();
  			  Thread.sleep(2000);
  			  break;
  		  }
  	  }if (isMenuPresent==false){
  		  Assert.assertTrue(isMenuPresent, menuType + " is not present in Menu list");
  	  }   	  
    }
    
    // In Admin - enters text in Review content and selects Reviews based on Product 
    public void searchReviewsInAdmin(String product ,String userName, String reviewTitle) throws Exception{
  	  logInfo("Enetered into searchReviewsInAdmin() method");
  	  Thread.sleep(3000);
  	  inputTextClear("cssSelector", reviewInputText);
  	  inputText("cssSelector", reviewInputText, reviewTitle);
  	  submitElement("cssSelector", reviewInputText);
  	  Thread.sleep(3000);
  	  boolean isProdPresent = false;
  	  List <WebElement> lis = driver().findElements(By.cssSelector(reviewList));
  	  System.out.println("size "+ lis.size());
  	  for (int i = 1; i<=lis.size(); i++){    		  
  		  WebElement element = driver().findElement(By.cssSelector(reviewListBfr+i+reviewListAft));
  		  String productName = element.getText().trim();
  		  if (productName.equalsIgnoreCase(product)){
  			  isProdPresent = true;
  			  WebElement shopUser = driver().findElement(By.cssSelector(reviewListBfr+i+reviewUserAft));
  			  System.out.println("Table user"+shopUser.getText().trim());
  			  Assert.assertEquals(shopUser.getText().trim(), userName);    			  
  			  Thread.sleep(4000);
  			  WebElement approve = driver().findElement(By.cssSelector(reviewListBfr+i+reviewApproveAft));
  			  approve.click();
  			  confirmationMessage("Review approved");    			  
  			  break; 
  		  }    		  
  		  
  	  }if (isProdPresent==false){
  		  Assert.assertTrue(isProdPresent, product + " product is not present in review Table");
  	  }    	  
    }
    
    public String getUserName(){
  	  logInfo("Enetered into getUserName() method");
  	  WebElement user = driver().findElement(By.cssSelector(userName));
  	  String userName = user.getText().trim();    
  	  System.out.println("userName "+userName);
		  return userName;
  	  
    }
    
    public void validateReviews(String reviewTitle){
  	  boolean isPresent = false;
  	  List <WebElement> rev = driver().findElements(By.cssSelector(review));
  	  System.out.println(rev.size());
  	  for (int i= 2; i<= rev.size()+1; i++){    		  
  		  WebElement revs = driver().findElement(By.cssSelector(reviewCom1+i+reviewCom2));
  		  System.out.println("revs.getText()" + revs.getText());
  		  if (revs.getText().equalsIgnoreCase(reviewTitle)){
  			  System.out.println("Suceess");
  			  isPresent = true;    			  
  			  break;   			  
  		  }   		  
  	  }if (isPresent ==false){
  		  Assert.assertTrue(isPresent, reviewTitle + " review is not present.");
  	  }
  	  
    }
    
    
    public void shopPanels() throws Exception{
  	  logInfo("Entered into shopPanels() method.");
  	  
  	List<WebElement> shopPane = driver().findElements(By.cssSelector(shopPanel));
		System.out.println("s : "+shopPane.size());
		for(WebElement shopPanels : shopPane){			
			shopPanels.click();  			
			WebElement tit = driver().findElement(By.cssSelector(titlePrice));
			String title = tit.getText().trim();
			Assert.assertEquals(title, titlePriceText);    			
			break;
		}    	  
    }
    
    public void rangeLevelPrice(int level_1_To_4) throws Exception, Exception{
  	  
  	  String priceChkBx1 = "#sidebar_products_search > div > ul > li:nth-child("+level_1_To_4+") > input";
  	  clickOnElement("cssSelector", priceChkBx1);
  	  Thread.sleep(3000);
		  waitOnElement("cssSelector", taxonSearch);
		  clickOnButton("cssSelector", taxonSearch);
		  Thread.sleep(3000);
  	  
  	  
    }
    
    public void rangeValidate(int lowRangePrice, int highRangePrice) throws Exception{
  	  logInfo("Entered into rangeValidate() method.");
  	 
  	  List <WebElement> pr = driver().findElements(By.cssSelector(price));
  	  System.out.println("size is "+ pr.size());
  	  for (int i =1 ; i<=pr.size(); i++){    		  
  		  WebElement prices = driver().findElement(By.cssSelector(priceBfr+i+priceAfr));
  		  String value = prices.getText().trim();
  		  String[] splitValue = StringUtils.split(value,"$");
  		  String [] amount = StringUtils.split(splitValue[0]," ");    		 
  		  String [] amonutDivde = StringUtils.split(amount[0],".");
  		  int actPrice = Integer.parseInt(amonutDivde[0]);    		  
  		  System.out.println(" spliteed value is "+ actPrice);
  		  if (actPrice<lowRangePrice  || actPrice > highRangePrice){
  			  Assert.assertSame(actPrice, highRangePrice);
  			  break;
  		  }else {
  			  System.out.println("Success!! Price Range Passed");
  		  }
  		  
  	  }
  	  
  	  
  	  
  	  
    }
      
      public void selectNext() throws Exception{
    	  logInfo("Enetered into selectNext() method");
    	  
    	  if (driver().getCurrentUrl().contains("master")){  		    
    		    clickOnButton("cssSelector", saveAndContinue);  		    
    		  }else{  
    			 Thread.sleep(3000); 
    			clickOnButton("cssSelector", shippingNext);
    			System.out.println("Selected next in Shipping");
    			  
    		  }
    	  
      }
      
      // select button either Next or Save& Continue in Shipping 
      public void selectNextRSaveNdContinueInShipping(String typeNextOrSave) throws Exception{    	  
    	 logInfo("entered into selectNextRSaveNdContinueInShipping(String typeNextOrSave) method");
    	  
    	 
    	 
    	 if (typeNextOrSave=="next"){      		  
    	  clickOnButton("cssSelector", shipNext);    	  
    	  System.out.println("selected Next");
    	  } else {    		 
    		  clickOnButton("cssSelector", shipSave); 
    		  System.out.println("selected save");
    	  }Thread.sleep(4000);
    	  
      }
      
      public void selectNextInDelivery () throws Exception{    	  
     	 logInfo("entered into selectNextInDelivery() method");
     	  waitOnElement("cssSelector", delTitle);   
     	  
     	  if (driver().findElement(By.cssSelector(delTitle)).getText().contains(delTitleTxt)){
     	  waitOnElement("cssSelector", shippingNext);     
     	  clickOnButton("cssSelector", shippingNext);
       }
      
      }
      
      
      
      
      public void emptyCartProducts() throws Exception{  		
  			clickOnButton("cssSelector", emptycart);
  			confirmOK(); 	
  			confirmationMessage("Products deleted successfully");
  			Thread.sleep(5000);
  			WebElement emp = driver().findElement(By.cssSelector(emptyAlert));
  			System.out.println(emp.getText());
  			Assert.assertEquals(emp.getText(), emptyAlertText);

  	}
      
      
      public void emailMyWishList() throws Exception{
    	  
    	  clickOnButton("cssSelector", addToWishlistBtn);
    	  clickOnButton("cssSelector", emailWishBtn);    	  
    	  cm.shareByEmail((custFNText+custLNText+"@icentris.com"), shopSubject);
    	  
    	  
      }
      
      public void add2CartFromWishList(String product) throws Exception{
    	  
    	  clickOnButton("cssSelector", addToWishlistBtn);
    	  
    	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
    	  System.out.println(lis.size());
    	  boolean isProdPresent = false;
    	  for (int i=1; i<=lis.size(); i++){
    		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+wishListAft));
    		  System.out.println("products are"+ prod.getText());
    		  if (prod.getText().contains(product)){
    			  isProdPresent=true;
    			  WebElement add2Cart = driver().findElement(By.cssSelector(wishListBfr+i+add2CartAft));
    			  add2Cart.click();
    			  break;
    		  }    		  
    	  }if (isProdPresent==false){
    		  Assert.assertTrue(isProdPresent, product + " is not present." );
    	  }
    	  
    	  
      }
      
      public void removeProductFromWishList(String product) throws Exception{    	  
    	  List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
    	  System.out.println(lis.size());
    	  boolean isProdPresent = false;
    	  for (int i=1; i<=lis.size(); i++){
    		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+wishListAft));
    		  System.out.println("products are"+ prod.getText());
    		  if (prod.getText().contains(product)){
    			  isProdPresent=true;
    			  WebElement add2Cart = driver().findElement(By.cssSelector(wishListBfr+i+wlReoveAft));
    			  add2Cart.click();
    			  break;
    		  }    		  
    	  }if (isProdPresent==false){
    		  Assert.assertTrue(isProdPresent, product + " is not present." );
    	  }
    	  
    	  
      }
      
      
      public void addQuanityNdCheckOutFromTodaysOrder(String product, int moreQuantiy) throws Exception{		
    		logInfo("Entering number of Quanity of products in Todays Order");		
    		
    			Thread.sleep(2000);    			
    			List <WebElement> lis = driver().findElements(By.cssSelector(wishList));
    	    	  System.out.println(lis.size());
    	    	  boolean isProdPresent = false;
    	    	  for (int i=1; i<=lis.size()*2; i++){
    	    		  WebElement prod = driver().findElement(By.cssSelector(wishListBfr+i+wishListAft));
    	    		  System.out.println("products are"+ prod.getText());
    	    		  if (prod.getText().contains(product)){
    	    			  isProdPresent=true;    	    			  
    	    			  for(int j=1; j <=moreQuantiy-1; j++){  	
    	    				  WebElement quan = driver().findElement(By.cssSelector(wishListBfr+i+quanPlustAft));
    	    				  quan.click(); 				
    	      				Thread.sleep(1000);
    	      				
    	      				}    	    			  
    	    			
	    				clickOnButton("cssSelector", addTocart);
	    				clickOnLink("linkText", "View Cart");
	    				Thread.sleep(2000);    				
	    				clickOnButton("cssSelector", checkOut);		
    	    				
    	    				
    	    			  
    	    			  
    	    			  
    	    			  break;
    	    		  } i++;  
    	    	  }if (isProdPresent==false){
    	    		  Assert.assertTrue(isProdPresent, product + " is not present." );
    	    	  }
    			
    			
    			
    			/*for(int i=1; i <=moreQuantiy-1; i++){  				
    				clickOnButton("cssSelector", moreIcon);  				
    				Thread.sleep(3000);
    				} */
    			
    			
    			
    			
    			    
    				
    			
    			
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
			}	/*selectNextRSaveNdContinueInShipping("next");*/	
			System.out.println("Select next in Payment");
		}
      
      public void creditCardDetails() throws Exception {
    	  logInfo("Entered into creditCardDetails() method");
    	    String CCName = prop.getLocatorForEnvironment(appUrl,"CCName");			
			System.out.println("ccname is "+CCName);
			inputText("cssSelector", CCName, custFNText); 
			inputText("cssSelector", CCNumber, CCCardNo);
			inputText("cssSelector",CCExpire, "08/19");
			inputText("cssSelector",CCCardCode, "489");		
			clickOnButton("cssSelector", shippingNext);
  			System.out.println("Selected next in CC payment");			
		}
     
      //selects place Order confirmation and capture message
      public void placeOrderWithConfimation() throws Exception{
    	logInfo("Entered into placeOrder () method"); 
    	waitOnElement("cssSelector", placeOrder);
    	getText("cssSelector", placeOrder," Text of place is ");
    	clickOnButton("cssSelector", placeOrder);
  		confirmationMessage("Your order has been processed successfully"); 	  	
  		
      }
      
      //selects place Order confirmation and capture message and then close it      
      public void placeOrderNdClose() throws Exception{
      	logInfo("Entered into placeOrder () method");    	  
      	clickOnButton("cssSelector", placeOrder);
	    confirmationMessage("Your order has been processed successfully"); 	
	    clickOnElement("xpath", orderClose);
	    Thread.sleep(2000);	
	    clickOnLink("linkText", "Go Back To Store");
		Thread.sleep(3000);
		WebElement tit = driver().findElement(By.cssSelector(shopTitle));
		Assert.assertEquals(tit.getText(), "Shop");  	    
        }
      
      // verify ordered history details with their Id and also product
      public void verifyOrderHistoryDetails(String productName) throws Exception{  
    	  logInfo("Entered into verifyOrderHistoryDetails() method");   	  
    	  System.out.println("Entered into verifyOrderHistoryDetails() method");
    	  WebElement orderId = driver().findElement(By.cssSelector(orderIdText));
    	  String orderText = orderId.getText();
    	  System.out.println("orderText "+orderText);
    	  waitOnElement("xpath", orderClose);
    	  clickOnElement("xpath", orderClose);
    	     	 
    	 // String orderText = "710987990"; 
    	  
    	  nav2OrderHistory();
    	  List <WebElement> ordList = driver().findElements(By.cssSelector(orderList));
    	  int listSize = ordList.size();
    	  System.out.println("listSize"+ listSize);
    	  boolean ispresent = false;
    	  for(int i=1; i<=listSize; i++){
    		  WebElement ordId = driver().findElement(By.cssSelector(ordIdBfr+i+ordIdAft));
    		  String orderIds=  ordId.getText(); 
    		  System.out.println(orderIds);
    		  String[] splitted = orderIds.split("#"); 
    		  System.out.println("splitted[1] "+splitted[1]);
    		   if (orderText.contains(splitted[1])){
    			   ispresent= true;
    			   System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
    			   Thread.sleep(5000);
    			   WebElement detBtn= driver().findElement(By.cssSelector(ordIdBfr+i+ordDetailsBtn)) ;
    			   detBtn.click();
    			   WebElement deatiledOrderId = driver().findElement(By.cssSelector(orderHist));
    			   System.out.println("Detailed is"+ deatiledOrderId.getText());  
    			   if (deatiledOrderId.getText().contains(splitted[1])){
    				   verifyItemsInOrderHistory(productName);
    				   clickOnLink("linkText", "Go Back To Store");    				     				  				   
    				   break;  
    			   }   			  
    			   
    			   break;   			   
    		   }    		  
    	  }if (ispresent==false){    		  
    		  Assert.assertTrue(ispresent,  orderText+ " is not present in OrderHistory" );
    	  }      	  
      }
      
      //verifies particular item is present in OrderHistory w.r.t OrderId
      public void verifyItemsInOrderHistory(String productName){    
    	  logInfo("Entered verifyItemsInOrderHistory(String productName) method");
    	  List <WebElement> it = driver().findElements(By.cssSelector(orderedItems));
    	  boolean isItemPresent = false;
    	  for (WebElement its :it){
    		  System.out.println("items "+ its.getText());
    		  if(its.getText().trim().contains(productName)){
    			  isItemPresent = true;
    			  System.out.println("items is present");
    			  
    			  break;  			  
    		  }   		  
    	  }if(isItemPresent==false){
    		  Assert.assertTrue(isItemPresent, productName + " - item is not present");
    	  }   	  
      }
      
      
      //Search AutoShip products and navigate to shop and add products.
      
      public void autoshipWithProducts(String productName)throws Exception{
		logInfo("Entered into autoshipWithProducts() method");	 
		clickOnButton("cssSelector", createAutoBtn);
		searchNAddProductDirectlysinAutoship(productName); 	
		
		/*
  		try{  			
  			if(driver().findElement(By.cssSelector(createAutoBtn)).getText().equals("Create New Autoship")){  			
  			logInfo("No products have been added to this Autoship. So Shops the product and adds to Autoship");
  			clickOnButton("cssSelector", createAutoBtn);
  			searchNAddProductDirectlysinAutoship(productName); 			
  		}else {  			
  			logInfo("Already user has autoship products, So doing transaction to those products");
  			searchNAddProductDirectlysinAutoship(productName);  			
  		}} catch(Exception e ){  			
  			logInfo("Not able to handle");
  		}	*/
  		
  	}
      
      //Search and Add product in autoship.
      public void searchNAddProductDirectlysinAutoship(String Product) throws Exception{
    	logInfo("Entered searchandAddProductDirectlysinAutoship() method");       
  		Actions act = new Actions(driver());
  		WebElement pro = driver().findElement(By.cssSelector(autoSearch));
  		inputTextClear("cssSelector", autoSearch);
  		inputText("cssSelector", autoSearch,Product );
  		Thread.sleep(5000);
  		act.click(pro).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
  		Thread.sleep(2000);
  	}   
      

      public void autoshipProducts(String Product, int moreQuantiy) throws Exception{
    	  logInfo("Entered into autoshipProducts(String Product, int moreQuantiy) method");
    	  
    	  searchNAddProductDirectlysinAutoship(Product);    	  
  			Thread.sleep(2000);
  			for(int i=1; i <=moreQuantiy-1; i++){				
  				clickOnButton("cssSelector", moreIcon);
  			}
  				
  				WebElement title = driver().findElement(By.cssSelector("h3#product_name"));
  				String prName = title.getText();
  				Thread.sleep(3000);
  				clickOnButton("cssSelector", addAutoBtn);   			
  				Thread.sleep(3000);
  				confirmationMessage(prName + " has been added to your autoship order.");
  				clickOnButton("cssSelector", nextInProducts);
  				Thread.sleep(4000);   	  
      }
      
     
      
     // select Next buttons in Shipping and also Payment sections in Autoship  
      public void NextInShippingNDeliNPayment() throws Exception{
    	  logInfo("inside NextInShippingNPayment() method"); 
    	    Thread.sleep(3000);	
    	    clickOnButton("cssSelector", nextShipping);
			Thread.sleep(7000);			  			
			clickOnButton("xpath", nextDelivary);
			Thread.sleep(3000);			            
		    clickOnButton("cssSelector", nextPayment);
			Thread.sleep(5000);	
    	  
  		/*try{	
  			if (driver().getCurrentUrl().contains("mannatech")){
  				clickOnButton("cssSelector", nextShipping);
  				Thread.sleep(7000);
  				keepAsAddressVerification();
  				clickOnElement("xpath", nextDelivary);
  				Thread.sleep(4000);
  				keepAsAddressVerification();  				
  					}else{
		  				clickOnButton("cssSelector", nextShipping);
		  				Thread.sleep(4000);			  			
		  				clickOnElement("xpath", nextDelivary);
        				Thread.sleep(3000);			  				           
        				            }
		  			    clickOnButton("cssSelector", nextPayment);
						Thread.sleep(5000);						
  					}catch(Exception e){
  						logInfo("Failed!! Unable to select Next buttons in AutoShip");
  						}*/

  	}
      
      // Handle to Keep As Address Verification in Mannatech
      public void keepAsAddressVerification() throws Exception{
    	logInfo("inside keepAsAddressVerification() method"); 
  		selectRadioOrCheckbox("xpath", keepAs);			
  		clickOnButton("cssSelector", keepSave);
  	}
      
      public void autoIdHandle() throws Exception{
    	  logInfo("inside autoIdHandle() method"); 
    	  WebElement autShipId = driver().findElement(By.cssSelector(autoId));
    	  System.out.println(autShipId.getText());    	  
    	  clickOnLink("cssSelector", autoId);
    	  Thread.sleep(2000);
    	  clickOnLink("linkText", "Edit Autoship");
      }
      
      // Delete latest created Autoship 
      public void deleteAutoShip() throws Exception{ 
    	  logInfo("inside deleteAutoShip() method"); 
    	  List <WebElement> lists = driver().findElements(By.cssSelector(autoList));
    	  System.out.println("Size is "+ lists.size());
    	  int autoSize = lists.size()+1;
    	  for (int i=autoSize; i>=1; i--){
    		  WebElement del = driver().findElement(By.cssSelector(autoListBfr+autoSize+autoDeleAft));
    		  del.click();
    		  confirmOK();
    		  confirmationMessage("Scheduled Delivery is deleted");
    		  break;   		  
    	  }
    	  
      }
     
      // fetch the autoship Id and compares/assert  in my autoship page
      public void validateAutoShipId() throws Exception{  
    	  logInfo("inside validateAutoShipId() method"); 
    	//  String expAutoId = ": 90139302";
    	  
    	  WebElement auto = driver().findElement(By.cssSelector(autoship_id));    	  
    	  String expAutoId=  auto.getText(); 
		  String[] expAutoSplitted = expAutoId.split(": ");
		  System.out.println("Expected id is "+ expAutoSplitted[1]);
		  Thread.sleep(3000);
    	  nav2AutoshipCustomer();   	 
    	  
    	     	  
    	  boolean isIdPresent = false;
    	  List <WebElement> lis = driver().findElements(By.cssSelector(autoList));
    	  System.out.println("Size is "+ lis.size());
    	  for (WebElement list: lis){    		  
    		  String actAutoId=  list.getText(); 
    		  String[] actAutoSplitted = actAutoId.split("#");
    		   System.out.println("Product cost is "+ actAutoSplitted[1]); 
    		   if (actAutoSplitted[1].equalsIgnoreCase(expAutoSplitted[1])){
    			   isIdPresent= true;
    			   break;   			   
    		   }	  
    		  
    	  } if (isIdPresent==false){
    		  Assert.assertTrue(isIdPresent, expAutoSplitted[1] + "is not present in MyAutoship List");
    	  }
    	  
      }
      
      // Get AutoShipId and compare it in MyAutoship page and then edit it. 
      public void editAutoShipId(){
    	  logInfo("Inside editAutoShipId() method");
    	  WebElement auto = driver().findElement(By.cssSelector(autoship_id));    	  
    	  String expAutoId=  auto.getText();     	
		  String[] expAutoSplitted = expAutoId.split(": ");
		  System.out.println("Expected id is "+ expAutoSplitted[1]);
    	  nav2AutoshipCustomer();
    	  boolean isIdPresent = false;
    	  List <WebElement> lis = driver().findElements(By.cssSelector(autoList));
    	  System.out.println("Size is "+ lis.size());
    	  for (int i =1; i<=lis.size(); i++){
    		  WebElement list= driver().findElement(By.cssSelector(autoListBfr+i+autoListAft));    	  		  
    		  String actAutoId=  list.getText(); 
    		  String[] actAutoSplitted = actAutoId.split("#");
    		   System.out.println("Product cost is "+ actAutoSplitted[1]); 
    		   if (actAutoSplitted[1].equalsIgnoreCase(expAutoSplitted[1])){
    			   isIdPresent= true;
    			   WebElement edit = driver().findElement(By.cssSelector(autoListBfr+i+autoEditAft));
    			   edit.click();
    			   break;   			   
    		   }	  
    		  
    	  } if (isIdPresent==false){
    		  Assert.assertTrue(isIdPresent, expAutoSplitted[1] + "is not present in MyAutoship List");
    	  }
    	  
      }
      
      
   // Get AutoShipId and compare it in MyAutoship page and then delete it and failly verify. 
      public void deleteAutoshipId() throws Exception, AWTException{
    	  logInfo("Inside editAutoShipId() method");
    	  WebElement auto = driver().findElement(By.cssSelector(autoship_id));    	  
    	  String expAutoId=  auto.getText(); 
    	 // String expAutoId = ": 90130084";
		  String[] expAutoSplitted = expAutoId.split(": ");
		  System.out.println("Expected id is "+ expAutoSplitted[1]);
    	  nav2AutoshipCustomer();
    	  boolean isIdPresent = false;
    	  List <WebElement> lis = driver().findElements(By.cssSelector(autoList));
    	  System.out.println("Size is "+ lis.size());
    	  for (int i =1; i<=lis.size(); i++){
    		  WebElement list= driver().findElement(By.cssSelector(autoListBfr+i+autoListAft));    	  		  
    		  String actAutoId=  list.getText(); 
    		  String[] actAutoSplitted = actAutoId.split("#");
    		   System.out.println("Product cost is "+ actAutoSplitted[1]); 
    		   if (actAutoSplitted[1].equalsIgnoreCase(expAutoSplitted[1])){
    			   isIdPresent= true;
    			   WebElement delete = driver().findElement(By.cssSelector(autoListBfr+i+autoDeleAft));
    			   delete.click();
    			   confirmOK();
    	    	   confirmationMessage("Autoship is deleted");
    	    	   
    	    	  nav2AutoshipCustomer();
    	     	  boolean isIdNotToPresent = true;
    	     	 List <WebElement> shipList = driver().findElements(By.cssSelector(autoList));
    	    	 System.out.println("Size is "+ shipList.size());
    	    	 for (int j =1; j<=shipList.size(); j++){
    	    		  WebElement listIds= driver().findElement(By.cssSelector(autoListBfr+j+autoListAft));    	  		  
    	    		  String listIdss=  listIds.getText(); 
    	    		  String[] actListIds = listIdss.split("#");
    	    		   System.out.println("Product cost is "+ actListIds[1]);
    	    		   if (actListIds[1].equalsIgnoreCase(expAutoSplitted[1])){
    	    			   isIdNotToPresent= false;
    	    			   Assert.assertTrue(isIdNotToPresent, expAutoSplitted[1]+ " - autoId is still present, failed!!");    	    			   
    	    			   break;    	    		     	 
    	    	 }}if (isIdNotToPresent==true){
    	    		 System.out.println(expAutoSplitted[1] +" is not present");
    	    	 } 	    	   
    			   break;   			   
    		   }	  
    		  
    	  } if (isIdPresent==false){
    		  Assert.assertTrue(isIdPresent, expAutoSplitted[1] + "is not present in MyAutoship List");
    	  }
    	  
      }
      
      
      public void guestUser(String email) throws Exception{ 
    	  Thread.sleep(2000);
    	  if (driver().getCurrentUrl().contains("master")){ 
    	  selectRadioOrCheckbox("cssSelector", guestCheckOut);
    	  }else{    		  
    	    	clickOnLink("linkText", "Guest Checkout");
    	  }  
    	  Thread.sleep(4000);
    	  inputTextClear("cssSelector", shopEmail);
    	  inputText("cssSelector", shopEmail, email);
    	  Thread.sleep(4000);
    	  clickOnButton("cssSelector", emailCont);
    	  
      }
      
public void custUserLogin(String existingshopUser) throws Exception{
	
    	//selectRadioOrCheckbox("cssSelector", custCheckOut);
	    //clickOnElement ("cssSelector", exisCustLogin);
    	Thread.sleep(2000);
    	clickOnLink("linkText", "Returning Customer");    	
    	Thread.sleep(2000);
    	inputTextClear("xpath", inputName);
  		Thread.sleep(2000);
  		inputText("xpath", inputName, existingshopUser);		
  		Thread.sleep(2020);		
  		inputText("cssSelector", custShopPwdInBranch, custPwdText);    		
  		Thread.sleep(3000);
  	    clickOnButton("cssSelector", userloginBtn);     
    	  
      }
      
      
      public void shippingAddress(String Fname, String Lname ) throws Exception{    	  
    	  
  	    inputTextClear("cssSelector", fName);
  	    inputTextClear("cssSelector", lName);
  		inputText("cssSelector", fName, Fname);
  		inputText("cssSelector", lName, Lname);
  		inputTextClear("cssSelector", street1);
  		inputTextClear("cssSelector", street2);
  		inputText("cssSelector", street1, "Roxbury Drive");
  		inputText("cssSelector", street2, "Beverly Hills");
  		inputTextClear("cssSelector", city);
  		inputText("cssSelector", city, cityName);   
  		inputTextClear("cssSelector", phone);
  		inputText("cssSelector", phone, "1234567891");
  		inputTextClear("cssSelector", zipCode);
  		inputText("cssSelector", zipCode, "90210");
  		inputTextClear("cssSelector", county);
  		inputText("cssSelector", county, "El Dorado");			
  	    selectFromDropdown("cssSelector", state, "byIndex", "2");
  	    waitOnElement("cssSelector", state);
  	    clickOnButton("cssSelector", shippingNext);  		
  		System.out.println("Selected next in address"); 		    
      }
      
      
      public void temp(){
    	  String temp = driver().getTitle();
    				Assert.assertEquals(temp , "Vibe");
    	  
      }
      
      
      public void shipProductToNewAddress(String Fname, String Lname ) throws Exception{    	  
    	  clickOnElement("Xpath", radioUseNewAddr);
    	  shippingAddress(Fname, Lname);      	  
      }
      
      public void addNewCCDetails() throws Exception{
    	  clickOnElement("xpath", radioNewPaymentDetails);
    	  creditCardDetails();
    	  
    	  
    	  
      }
      
      public void creditCardValidation() throws Exception{
    	  logInfo("Entered into creditCardDetails() method");
			Thread.sleep(4000);	
			clickOnButton("cssSelector", shippingNext);
			confirmationMessage("Please enter valid data for Card Number, Expiration, Card Code Fields");
			Thread.sleep(3000);
			ccAlerts();
			Thread.sleep(3000);
			inputText("cssSelector",CCExpire, "08/19");
			Thread.sleep(2000);
			clickOnButton("cssSelector", shippingNext);
			confirmationMessage("Please enter valid data for Card Number, Card Code Fields");
			Thread.sleep(3000);
			ccAlerts();	
		}
      
      
      public void ccAlerts (){
    	  
    	  List <WebElement> al = driver().findElements(By.cssSelector(ccAlerts));
    	  System.out.println(al.size());
    	  for (WebElement alert : al){
    		  String alertMsg = alert.getText().trim();
    		  switch (alertMsg) {    		  
    		  case "Payments credit card Month is not a number":
    			  System.out.println("match found : "+alertMsg );
    			  break;    			  
    		  case "Payments credit card Year is not a number":
    			  System.out.println("match found : "+alertMsg );
    			  break;
    			  
    		  case "Payments credit card Number can't be blank":
    			  System.out.println("match found : "+alertMsg );
    			  break;
    			  
    		  case "Payments credit card Verification Value can't be blank":
    			  System.out.println("match found : "+alertMsg );
    			  break;
    			  
    		  case "Payments credit card Name can't be blank":
    			  System.out.println("match found : "+alertMsg );
    			  break;    			  
    		  default:
    			  System.out.println("match found : "+alertMsg );
    			  break;
    		  }
    		  
    		  
    		  
    	  }   	  
    	  
      }
      
      
      public void creditCardDetailsFirstTime() throws Exception {
    	  logInfo("Entered into creditCardDetails() method");
			
			String CCName = prop.getLocatorForEnvironment(appUrl,"CCName");
			
			System.out.println("ccname is "+CCName);
			inputText("cssSelector", CCName, custFNText); 
			inputText("cssSelector", CCNumber, CCCardNo);
			inputText("cssSelector",CCExpire, "08/19");
			inputText("cssSelector",CCCardCode, "489");		
				clickOnButton("cssSelector", shippingNext);
	  			System.out.println("Selected next in CC payment");
			
		}
      
      
      public void handleFromCheckOutToSummaryInAutoship() throws Exception{    	  
    	  
    	  selectNextInAutoshipDelivery();
    	  selectNextInPayment();
    	  clickOnButton("cssSelector", confirm);
		  confirmationMessage("Autoship was successfully created/updated");
    	  
      }
      
      public void selectNextInAutoshipDelivery () throws Exception{    	  
      	 logInfo("entered into selectNextInDelivery() method");      	    
      	  WebElement del = driver().findElement(By.cssSelector(delTitle));
      	  System.out.println("Next in delevery "+del.getText());
      	  
      	  
      	  if (del.getText().trim().contains(delAutoTitleTxt)){
      		  
      		System.out.println("trying to pick clander");
      		calenderDAtePicker(5);      		  		  
      		clickOnButton("cssSelector", shippingNext);
      		  
      	  }else{
      		  System.err.println("Not cliked Next button in Delivery section");
      	  }	  
      	  
        }
      
      public void selectNextInPayment() throws Exception{    	  
       	 logInfo("entered into selectNextInDelivery() method");
       	  Thread.sleep(4000);    
       	  WebElement pay = driver().findElement(By.cssSelector(paymentTitle));
       	  System.out.println("Next in delevery "+pay.getText());
       	  Assert.assertEquals(pay.getText().trim(), paymentTitleText);
       	  clickOnButton("cssSelector", shippingNext);
         }
      
      
      
   /// IdLife - 04012017
      
      public void IDLife_registration(String Fname, String Lname) throws Exception {
  		
  		logInfo("Register New Customer with all fields.");
  		try{
  		waitOnElement("cssSelector", registerNew);	
  		clickOnButton("cssSelector", registerNew); 		
  		waitOnElement("cssSelector", custFN);
  		inputTextClear("cssSelector", custFN);
  		inputText("cssSelector", custFN, Fname);
  		inputText("cssSelector", custLN, Lname);
  		inputText("cssSelector", custEmail, (Fname+Lname+"@icentris.com"));
  		inputTextClear("cssSelector", custPhone);
  		inputText("cssSelector", custPhone, "1234567891");
  		/*inputText("cssSelector", custAd1, "Roxbury Drive");
  		inputText("cssSelector", custAd2, "Beverly Hills");
  		inputText("cssSelector", custCounty, "El Dorado");
  		inputText("cssSelector", custCity, "CA");
  		inputText("cssSelector", custZip, "90210");
  		stateSelection("Arkansas");	*/	
  		genderAndDOB("Male");
  		
  		inputTextClear("cssSelector", custUserName);
  		inputText("cssSelector", custUserName, Fname);
  		inputText("cssSelector", custPwd, custPwdText);
  		inputText("cssSelector", custConPwd, custPwdText);	
  		inputTextClear("cssSelector", custFN);
  		inputText("cssSelector", custFN, Fname);
  	    //waitOnElement("cssSelector", custDOB);	
  	    inputText("cssSelector", birthDate, "02/02/1999");
  		clickOnButton("cssSelector", custCreate);	
  		confirmationMessage("Account created successfully");  		
  		/*if(driver().findElement(By.cssSelector(error404)).getText().equals(notImplimentedText)){
  			System.out.println("Enterd eroor message");
  			WebElement panelTit = driver().findElement(By.cssSelector(error404));
  			System.out.println(panelTit.getText());
  			Assert.assertEquals(panelTit.getText(), "Save");	
  		}else {
  			WebElement panelTit = driver().findElement(By.cssSelector(panelTitle));
  			System.out.println(panelTit.getText());
  			Assert.assertEquals(panelTit.getText(), "Back To Shop");	
  			System.out.println("Eaxctly as shop");  			
  				} 	*/
  			}catch (Exception e){  			
  			logInfo("Failed!! Some mandatory fields are missing.");
  		}	
  	}
      
      
      
   public void IDLife_exsitingCustomer(String custUser, String passwd) throws Exception{	   
	    waitOnElement("cssSelector", returnExist);
	    clickOnButton("cssSelector", returnExist);
	    waitOnElement("xpath", inputName);	
		inputTextClear("xpath", inputName);	
		waitOnElement("xpath", inputName);	
		inputText("xpath", inputName, custUser);		
		waitOnElement("xpath", inputName);
		inputText("cssSelector", custShopPwdInMaster, passwd);
		waitOnElement("cssSelector", custShopPwdInMaster);
		submitElement("cssSelector", custShopPwdInMaster);   
	   
   }
      
   public void shippingAddressDetails(String Fname, String Lname ) throws Exception{    	  
	   waitOnElement("cssSelector", shippingNext);	   
	   WebElement section = driver().findElement(By.cssSelector(shippingAddsec));
	   String fields = section.getText();
	   System.out.println("fiels" +fields );	   
	   if(fields.contains("Add New Address")){		
		   waitOnElement("cssSelector", shippingNext);  	
		   clickOnButton("cssSelector", shippingNext);  	
	   }else{
		   shippingAddress(Fname, Lname);		   
	   		} 
	   }
      
   
   public void addAutoshipQuanityNdCheckOutInIDLife(int moreQuantiy) throws Exception{		
		logInfo("Entering number of Quanity of products");		
		try{		
			for(int i=1; i <=moreQuantiy-1; i++){  				
				clickOnButton("cssSelector", moreQuantAuto);  				
				waitOnElement("cssSelector", moreQuantAuto);  
			}
			   getText("cssSelector", shedDeleBtn, "shed ");
			    waitOnElement("cssSelector", shedDeleBtn);
				clickOnButton("cssSelector", shedDeleBtn);
				clickOnLink("linkText", "View Cart");				
				waitOnElement("cssSelector", checkOut);	
				clickOnButton("cssSelector", checkOut);	
				waitOnElement("cssSelector", checkOut);	
				clickOnButton("cssSelector", checkOut);	
				
				
			}catch(Exception e){
			logInfo("Failed!! Not able to enter quanity of products");
		}	
		
		}
      
    	  
   public void handleFromCheckOutToSummaryInAutoshipInIDL() throws Exception{    	  
 	  
 	  selectNextInAutoshipDelivery();
 	  selectNextInPayment();
 	  waitOnElement("cssSelector", confirm);
 	  clickOnButton("cssSelector", confirm);
	  confirmationMessage("Autoship was successfully created/updated");
 	  
   }
     
   
   
   public void selectCalendarDate(String caldate) throws Exception{
		logInfo("Inside selectCalendarDate() method.");
		
		/*SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
	    SimpleDateFormat format2 = new SimpleDateFormat("MMM/dd/yyyy");
	    Date dateobj = format1.parse(caldate);
	    String date = format2.format(dateobj);
	    System.out.println("newdt =" +date);*/
	    
		String date =caldate;
		String arrdate[] = date.split("/");
		String expmonth = arrdate[0];
		String expday = arrdate[1];
		String expyear = arrdate[2];
		System.out.println("month =" +expmonth);
		System.out.println("day =" +expday);
		System.out.println("year =" +expyear);
				
		//click on date picker
		//driver.findElement(By.xpath(calendarDatePicker)).click();
		
				
		// select year
		
		WebElement mhead = driver().findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[1]/th[2]"));   // //th[@class='picker-switch']
		String month_header = mhead.getText().trim();
		System.out.println("Month Header =" +month_header);
		mhead.click();
		
		Thread.sleep(2000);
		WebElement yhead = driver().findElement(By.xpath("//div[@class='datepicker-months']/table/thead/tr/th[2]"));  // //th[@class='picker-switch']
		String year_header = yhead.getText().trim();
		System.out.println("year header =" +year_header);
		yhead.click();
		
		Thread.sleep(2000);
		WebElement dtyear = driver().findElement(By.xpath("//div[@class='datepicker-years']/table/tbody/tr/td"));
		List <WebElement> years = dtyear.findElements(By.tagName("span"));
		System.out.println("spans =" +years.size());
		String before_yspan = "//div[@class='datepicker-years']/table/tbody/tr/td/span[";
		String after_yspan = "]";
		for(int j=1; j<= years.size(); j++){
			 WebElement eyear = driver().findElement(By.xpath(before_yspan+j+after_yspan));
			 String year = eyear.getText().trim();
			 if(year.equalsIgnoreCase(expyear)){
				 eyear.click();
				 Thread.sleep(2000);
				 break;
			 }
			}

		// select month
		 	Thread.sleep(2000);
			WebElement dtmonth = driver().findElement(By.xpath("//div[@class='datepicker-months']/table/tbody"));
			List <WebElement> spans = dtmonth.findElements(By.tagName("span"));
			System.out.println("spans =" +spans.size());
			String before_span = "//div[@class='datepicker-months']/table/tbody/tr/td/span[";
			String after_span = "]";
							
			for(int i=1; i<= spans.size(); i++){
			 WebElement emonth = driver().findElement(By.xpath(before_span+i+after_span));
			 String month = emonth.getText().trim();
			 if(month.equalsIgnoreCase(expmonth)){
				 emonth.click();
				 Thread.sleep(2000);
				 break;
			 }
		   }
							
		
		// select day
				
		WebElement dtpicker = driver().findElement(By.xpath("//div[@class='datepicker']/div[@class='datepicker-days']/table/tbody")); 
		List <WebElement> alldays = dtpicker.findElements(By.className("day"));
		System.out.println("days =" + alldays.size());
				
		for(int rows=1;rows <=6;rows++){
			for(int cols=1; cols<=7;cols++){
				WebElement dt = driver().findElement(By.xpath("//div[@class='datepicker']/div[@class='datepicker-days']/table/tbody/tr[" + rows + "]/td[" + cols + "]"));
				if(dt.getText().equalsIgnoreCase(expday)){
				System.out.println("Actual day =" +dt.getText() + " ,Expected day =" +expday);
				dt.click();
				break;
				}
			}
		}
	 }	
   
   
  //Monat 
   
   public void addQuanityNdCheckOutInMonat(int moreQuantiy) throws Exception{		
 		logInfo("Entering number of Quanity of products");		
 		try{ 
 			String qty = Integer.toString(moreQuantiy);
 			submitElement("cssSelector", qtyField);
 			inputTextClear("cssSelector", qtyField);
 			inputText("cssSelector", qtyField, qty);			
 			selectRadioOrCheckbox("cssSelector", oneTime); 
 			clickOnButton("cssSelector", addTocartMonat);  
			waitOnElement("linkText", "View Cart");
			clickOnLink("linkText", "View Cart");			
			waitOnElement("cssSelector", checkOut);
			clickOnButton("cssSelector", checkOut);	  			
 			}catch(Exception e){
 			logInfo("Failed!! Not able to enter quanity of products");
 		}	
 		
 		}
   
   
   public void calenderDAtePicker(int FutueDate) throws Exception{  
	   JavascriptExecutor js = (JavascriptExecutor)driver(); 	  
       String dateField = "div.input-group.date.date_picker > input#autoship_next_run_date";
       implicityWaits(5);         
       WebElement da = driver().findElement(By.cssSelector(dateField));
       js.executeScript("document.getElementById('autoship_next_run_date').value= '03/30/2017'");
       
       
   }
   
   
   
  

}
	


