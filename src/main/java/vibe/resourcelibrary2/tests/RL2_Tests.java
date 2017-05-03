package vibe.resourcelibrary2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Priority(9)
public class RL2_Tests extends RL2Methods {
	
	@Test(priority=900)
	public void enableRLSettings() throws Exception{
		logInfo("inside enableRLSettings() Test");
		System.out.println("inside enableRLSettings() Test");
		nav2marketBusinessRL();
		verifyEnableResourceOptions();
		
	}
	
    @Test(priority=901)
	public void createRLCategory() throws Exception{
		logInfo("inside createRLCategory() Test");
		System.out.println("inside createRLCategory() Test");
		nav2marketBusinessRL();
		addResourceCategory(parentCategory_text,"None");
		nav2marketBusinessRL();
		addResourceCategory(childCategory_text,parentCategory_text);
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text);
		if(isAdded= false){
			logInfo(parentCategory_text + " product category could not be added.");
			Assert.assertTrue(isAdded, parentCategory_text + " product category could not be added.");
		}
	}

	
	@Test(priority=902)
	public void addResource2Category() throws Exception{
		logInfo("inside addResource2Category() Test");
		System.out.println("inside addResource2Category() Test -->");
		nav2marketBusinessRL();
				
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text);
		if(isAdded==true){
		addNewResource(newResourceTitle_text, parentCategory_text, newResourceStatus_text, true,true,"PDF");
		confirmationMessage("Resource is created");
		selectCategoryFilter(parentCategory_text);
		selectResource(newResourceTitle_text);
		boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text);
		if(isHeadingMatch==false){
			logInfo(newResourceTitle_text + " does not matches the title in resource preview page.");
			Assert.assertTrue(isHeadingMatch, newResourceTitle_text + " does not matches the title in resource preview page.");
		}
		
	}
	}
	
 	@Test(priority=903)
	public void editRLCategory() throws Exception{
 		
		logInfo("inside editRLCategory() Test");
		System.out.println("inside editRLCategory() Test");
		nav2marketBusinessRL();
		editProductCategory(parentCategory_text);
		boolean isAdded = verifyResourceCategoryPresent(parentCategoryUpdated_text);
		if(isAdded= false){
			logInfo(parentCategoryUpdated_text + " product category could not be updated.");
			Assert.assertTrue(isAdded, parentCategoryUpdated_text + " product category could not be updated.");
		}
	}	 	
	
	@Test(priority=904)
	public void cloneResourceInAdmin() throws Exception{
	
		logInfo("inside cloneResourceInAdmin() Test");
		System.out.println("inside cloneResourceInAdmin() Test");
		nav2marketBusinessRL();
		selectCategoryFilter(parentCategoryUpdated_text);
		cloneResourceAdmin(newResourceTitle_text);
		cloneResource();
		boolean isResPresent = verifyResourcePresentInAdmin(parentCategoryUpdated_text, newCloneResourceTitle_text);
		if(isResPresent==false){
			logInfo(newCloneResourceTitle_text + " resource could not be found.");
			Assert.assertTrue(isResPresent, newCloneResourceTitle_text + " resource could not found.");
		}
	}
	
	@Test(priority=905)
	public void editResourceInAdmin() throws Exception{
		logInfo("inside editResourceInAdmin() Test");
		System.out.println("inside editResourceInAdmin() Test");
		nav2marketBusinessRL();
		selectCategoryFilter(parentCategoryUpdated_text);
		editResourceAdmin(newCloneResourceTitle_text);
		editResourceTitle();
		confirmationMessage("Resource is updated");
		boolean isResPresent = verifyResourcePresentInAdmin(parentCategoryUpdated_text, newResourceTitleUpdated_text);
		if(isResPresent==false){
			logInfo(newResourceTitleUpdated_text + " resource could not be updated.");
			Assert.assertTrue(isResPresent, newResourceTitleUpdated_text + " resource could not be updated.");
		}
	}
	

	//TC2397 - Verify the resource in a specific category

	@Test(priority=906)
	public void addResourceToCategory() throws Exception{
				
		logInfo("inside addResourceToCategory() Test");
		System.out.println("inside addResourceToCategory() Test");
		nav2marketBusinessRL();
		addResourceCategory(parentCategory_text1,"None");
		nav2marketBusinessRL();
		addResourceCategory(childCategory_text1,parentCategory_text1);
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		// for master we need to keep "true" for 5th parameter.
		if(isAdded==true){
			addNewResource(newResourceTitle_text1, parentCategory_text1, newResourceStatus_text, true,true,"PDF");   
			selectCategoryFilter(parentCategory_text1);
			boolean isResourcePresent = selectResourceListView(newResourceTitle_text1);
			if(isResourcePresent==false){
				logInfo(newResourceTitle_text1 + " Unable to view the resource.");
				Assert.assertTrue(isResourcePresent, newResourceTitle_text1 + " Unable to view the resource.");
			}
			/*boolean isHeadingMatch = verifyResourceitPreviewHeading(newResourceTitle_text1);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text1 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text1 + " does not matches the title in resource preview page.");
			}*/
		}
	}
	
	
	//TC2397 - Verify the resource in a specific category
	@Test(priority=907)
	public void verifyResourceInCategory() throws Exception{
		logInfo("inside verifyResourceInCategory() Test");
		navigate2UserRL();
		boolean flag = verifyParentCategory(parentCategory_text1);
		Assert.assertTrue(verifyResource(newResourceTitle_text1));
		
	}
	
	//TC2398 - Verify resource in Newest Resources when the resource has marked to see in Newest.
	@Test(priority=908)
	public void verifyResourceInNewest() throws Exception{
		logInfo("inside verifyResourceInNewest() Test");
		navigate2UserRL();
		Assert.assertTrue(verifyResource(newResourceTitle_text1));
		
	}
	
	//TC2400 & TC2416 - Verify the download functionality for the resource asset.
	@Test(priority=909)
	public void verifyDownloadAsset() throws Exception{ 
		logInfo("inside verifyDownloadAsset() Test");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		boolean isDownloaded = downloadAssetForResource(newResourceTitle_text1);
		if(!isDownloaded){
			Assert.assertTrue(isDownloaded, "Unable to download a resource "+ newResourceTitle_text1);
		}
				
	}
	
	//TC2401 - Verify the search resource functionality.
	@Test(priority=910)
	public void verifySearchResourceUser() throws Exception{
		logInfo("inside verifySearchResource() Test");
	
		navigate2UserRL();
		Assert.assertTrue(searchResourceListView(searchResourceText));
	}
	
	@Test(priority=911)
	public void verifySearchResourceListView() throws Exception{
		logInfo("inside verifySearchResource() Test");
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		Assert.assertTrue(searchResourceListView(searchResourceText));
	}
	
	@Test(priority=912)
	public void verifySearchResourceAdmin() throws Exception{
		logInfo("inside verifySearchResourceAdmin() Test");
		System.out.println("inside verifySearchResourceAdmin() Test");
		nav2marketBusinessRL();
		Assert.assertTrue(searchResource(searchResourceText));
		try{
			waitOnElement("cssSelector",categoriesFilter);
			WebElement e = driver().findElement(By.cssSelector(categoriesFilter));
			if(!e.isDisplayed()){
				Assert.assertTrue(false, "Unable to find the categories dropdown after search a resource from admin.");
			}
		}
		catch(Exception ex){
			Assert.assertTrue(false, "Unable to find the categories dropdown after search a resource from admin.");
		}
		
		
	}
	
	@Test(priority=913)
	public void verifySearchResourceByTrainingAssets() throws Exception{
		logInfo("inside verifySearchResourceByTrainingAssets() Test");
		System.out.println("inside verifySearchResourceByTrainingAssets() Test");
		nav2marketBusinessRL();
		selectAssetTypeFilter("Training Assets");
		Assert.assertTrue(searchResource(searchTrainingAssetText));
	}
	
	//TC2402 - Verify filtering the resource by file type.
	@Test(priority=914)
	public void verifyFilterResource() throws Exception{
		logInfo("inside verifyFilterResource() Test");
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		Assert.assertTrue(filterResource(newResourceTitle_text1,filterByPDF));
	}
	
	//TC2405 - Verify the resource in List View.
	@Test(priority=915)
	public void verifyResourcesinListView() throws Exception{
		logInfo("inside verifyResourcesinListView() Test");
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		Assert.assertTrue(displayResourceInListView(newResourceTitle_text1));
	}
	
	//TC2406 & TC2408 - Verify the resource preview.
	@Test(priority=916)
	public void verifyPreviewResourceListView() throws Exception{
		logInfo("inside verifyPreviewResource() Test");
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		Thread.sleep(5000);
		boolean isResourceFound = previewResourceListView(newResourceTitle_text1);
		if(!isResourceFound){
			Assert.assertTrue(isResourceFound, "Unable to preview the resource "+ newResourceTitle_text1);
		}
		//Assert.assertTrue(verifyResourcePreviewHeading(newResourceTitle_text1));
	
	}
	
	
	
	
	
	//TC2407 - Verify navigation links between resources.
	@Test(priority=917)
	public void verifyResourceNavigations() throws Exception{
		
		logInfo("inside verifyResourceNavigations() Test");
		nav2marketBusinessRL();
		addNewResource(newResourceTitle_text2, parentCategory_text1, newResourceStatus_text, true,true,"PDF");
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		previewResourceListView(newResourceTitle_text2);
		verifyResourcePreviewHeading(newResourceTitle_text2);
		Assert.assertTrue(verifyResourceNavigation(newResourceTitle_text2));
		
	}
	
	@Test(priority=918)
	public void verifyResourceNavigationsAdmin() throws Exception{
	
		logInfo("inside verifyResourceNavigationsAdmin() Test");
		nav2marketBusinessRL();
		selectCategoryFilter(parentCategory_text1);
		selectResource(newResourceTitle_text2);
		Assert.assertTrue(verifyResourceNavigation(newResourceTitle_text2));
		
	}
	
	//TC3445
	@Test(priority=919)
	public void resourcesViewedByMe() throws Exception{
		
		logInfo("inside resourcesViewedByMe() Test");
		navigate2UserRL();
		boolean isResourceFound = verifyResourcesViewedByMe(newResourceTitle_text2);
		if(isResourceFound==false){
			Assert.assertTrue(isResourceFound,"Unable to view the resources which are viewed by me.");
		}
		
	}
	
	//TC3446
	@Test(priority=920)
	public void resourcesViewedByOthers() throws Exception{
		logInfo("inside resourcesViewedByOthers() Test");
		navigate2UserRL();
		boolean isResourceFound = verifyResourcesViewedByOthers(newResourceTitle_text2);
		if(isResourceFound==true){
			Assert.assertFalse(isResourceFound,"Unable to view the resources which are viewed by others.");
		}
		
	}
	//TC2413 & TC2414 - Verify compose email as attachment for a resource.
	@Test(priority=921)
	public void verifyComposeEmailAsAttachment() throws Exception{
				
		logInfo("inside verifyComposeEmailAsAttachment() Test");
		boolean isEmailSent = composeEmailAsAttachment(newResourceTitle_text2,parentCategory_text1);
		if(isEmailSent==false){
			Assert.assertTrue(isEmailSent,"Unable to send an email as an attachment.");
		}
		
	}
	
	//TC2415 - Verify asset in the recipients mail and view the asset.
	@Test(priority=922)
	public void verifyAssetInEmailLinkToMyWebsite() throws Exception{
		
		logInfo("inside verifyAssetInEmailLinkToMyWebsite() Test");
		boolean isEmailInAssetVerified = resourceAssetInEmailLinkToMyWebsite(newResourceTitle_text2,resourceAsset,assetTypePDF,parentCategory_text1);
		if(isEmailInAssetVerified==false){
			Assert.assertTrue(isEmailInAssetVerified,"Unable to verify the resource asset in the email.");
		}
	}
	
	//TC2418 - Verify resource category without a resource attached to it.
	@Test(priority=923)
	public void verifyCategoryWithoutResources() throws Exception{
		logInfo("inside verifyCategoryWithoutResources() Test");
		nav2marketBusinessRL();
		addResourceCategory(parentCategory_text2,"None");
		navigate2UserRL();
		Assert.assertFalse(verifyParentCategory(parentCategory_text2));
	}
	
	//TC2419 - Verify resource sub category without a resource attached to it.
	@Test(priority=924)
	public void verifySubCategoryWithoutResources() throws Exception{
		
		logInfo("inside verifySubCategoryWithoutResources() Test");
		boolean isParentcategoryFound = false;
		nav2marketBusinessRL();
		addResourceCategory(childCategory_text2,parentCategory_text1);
		navigate2UserRL();
		if(verifyParentCategory(parentCategory_text1)){
			Assert.assertFalse(verifyChildCategory(parentCategory_text1,childCategory_text2));
		}
		else{
			Assert.assertTrue(isParentcategoryFound, parentCategory_text1 +" parent category not found.");
		}
			
	}
	
		
	@Test(priority=925)
	public void verifyPreviewResourceGridView() throws Exception{
		logInfo("inside verifyPreviewResourceGridView() Test");
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		boolean isResourceFound = previewResourceGridView(newResourceTitle_text1);
		if(!isResourceFound){
			Assert.assertTrue(isResourceFound, "Unable to preview the resource "+ newResourceTitle_text1);
		}
		//Assert.assertTrue(verifyResourcePreviewHeading(newResourceTitle_text1));
	
	}
	
	
	
	//TC2420 & TC2421 - Verify resource tags availability based on distributor's rank.
  //	@Test(priority=925)
  	public void verifyResourceTagsByRank() throws Exception{
  		
  		logInfo("inside verifyResourceTagsByRank() Test");
  		boolean isTagFound = false;
  		//Need to set the rank for the logged in user.
  		/*logOut();
      	logIn(masDistlogin,masDistPswd);*/
  		navigate2UserRL();
  		if(verifyParentCategory(parentCategory_text1)){
  			Assert.assertTrue(getResourceTags(resourceTagName));
  			WebElement resourcesYouViewed = driver().findElement(By.cssSelector("#rl_you_viewed>a"));
  			resourcesYouViewed.click();
  			isTagFound = getResourceTags(resourceTagName);
  			if(!isTagFound){
  				Assert.assertFalse(isTagFound,"Unable to get resource tags by clicking on 'You have viewed' link");
  			}
  			
  			WebElement popular = driver().findElement(By.cssSelector("#rl_popular>a"));
  			popular.click();
  			isTagFound = getResourceTags(resourceTagName);
  			if(!isTagFound){
  				Assert.assertFalse(isTagFound,"Unable to get resource tags by clicking on 'Popular' link");
  			}
  			
  			
  		}
  		else{
  			Assert.assertFalse(isTagFound, resourceTagName +"tag not found.");
  		}
  		/*logOut();
  		logIn(adminUser_text,adminPwd_text);*/
  		
  	}
  	
  	//TC2422 - Verify resource availability by respective tags.
  	@Test(priority=926)
  	public void verifyResourcesByTags() throws Exception{
  		
  		logInfo("inside verifyResourcesByTags() Test");
  		boolean isTagFound = false;
  		navigate2UserRL();
  		verifyParentCategory(parentCategory_text1);
  		if(getResourceTags(resourceTagName)){
  			Assert.assertTrue(verifyResource(newResourceTitle_text1)); //newResourceTitle_text2
  		}
  		else{
  			Assert.assertFalse(isTagFound, resourceTagName +"tag not found.");
  		}
  		
  	}
	
	//TC2424 - Verify write review for a resource.
	@Test(priority=927)
	public void postReviewForAsset() throws Exception{
		
		logInfo("inside postReviewForAsset() Test");
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		previewResourceListView(newResourceTitle_text2);
		writeReviewForAsset(reviewTitle,reviewDesc);
		Assert.assertTrue(verifyReview(reviewTitle));
	}	
	
	@Test(priority=928)
    public void checkExpiredResource() throws Exception{
		
		logInfo("inside checkExpiredResource() Test");
		nav2marketBusinessRL();
    	selectResourceStatus("Expired");
    	Assert.assertFalse(selectResource(newResourceTitle_text1));
	}
	
	@Test(priority=929)
    public void checkActiveResource() throws Exception{
		
		logInfo("inside checkActiveResource() Test");
		nav2marketBusinessRL();
    	selectResourceStatus("Active");
    	Assert.assertTrue(selectResource(newResourceTitle_text1));
	}
	
	//TC3429
	@Test(priority=930)
    public void checkLast7DaysResources() throws Exception{
		logInfo("inside checkLast7DaysResources() Test");
		nav2marketBusinessRL();
    	selectResourceStatus("Last 7 Days");
    	Assert.assertTrue(selectResource(newResourceTitle_text1));
	}
	
	@Test(priority=931)
    public void checkLastMonthResources() throws Exception{
		logInfo("inside checkLastMonthResources() Test");
		nav2marketBusinessRL();
    	selectResourceStatus("Last Month");
    	Assert.assertTrue(selectResource(newResourceTitle_text1));
	}
	
	@Test(priority=932)
    public void checkLast3MonthsResources() throws Exception{
		logInfo("inside checkLast3MonthsResources() Test");
		nav2marketBusinessRL();
    	selectResourceStatus("Last 3 Months");
    	Assert.assertTrue(selectResource(newResourceTitle_text1));
	}
	
	@Test(priority=933)
    public void checkPendingResource() throws Exception{
		logInfo("inside checkPendingResource() Test");
		nav2marketBusinessRL();
    	selectResourceStatus("Pending");
    	Assert.assertFalse(selectResource(newResourceTitle_text1));
	}

	@Test(priority=935)
	public void filterResourcesByTrainingAssets() throws Exception, Exception{ 
		logInfo("inside filterResourcesByTrainingAssets() Test");
		System.out.println("inside filterResourcesByTrainingAssets() Test");
		createTrainingAsset(trainingCategory,trainingSeries,trainingAssetTitle);
		nav2marketBusinessRL();
		selectAssetTypeFilter("Training Assets");
		boolean isResourceFound = selectResource(trainingAssetTitle);
		if(!isResourceFound){
			Assert.assertTrue(isResourceFound, "Unable to find the resource by filtering with training assets.");
		}
		
	}
	
	@Test(priority=936) //this is blocked for adding spree asset
	public void filterResourcesBySpreeAssets() throws Exception, Exception{
		logInfo("inside filterResourcesBySpreeAssets() Test");
		System.out.println("inside filterResourcesBySpreeAssets() Test");
		createSpreeAsset(spreeAssetTitle);
		Assert.assertTrue(filterResourceBySpreeAssets(spreeAssetTitle), "Unable to find the resource by filtering with spree assets.");
	}
    
    //TC2347
    @Test(priority=937)
    public void moveResourceCategoriesInAdmin() throws Exception{
    		    	
    	logInfo("inside moveResourceCategoriesInAdmin() Test");
		System.out.println("inside moveResourceCategoriesInAdmin() Test");
		navigate2AdminRL();
		boolean isCategoriesMoved = moveResourceCategories(parentCategory_text1);
		if(isCategoriesMoved==false){
   			logInfo(parentCategory_text1 + " Unable to move the resource category.");
   			Assert.assertTrue(isCategoriesMoved, parentCategory_text1 + " Unable to move the resource category.");
   		}
	}
    
    //TC2353
    @Test(priority=938)
    public void verifyBackFromResourceCategoriesPage() throws Exception{
    	logInfo("inside verifyBackFromResourceCategoriesPage() Test");
		System.out.println("inside verifyBackFromResourceCategoriesPage() Test");
		navigate2AdminRL();
		boolean getResourceTitle = verifyBackBtnFromResourceCategories();
		if(getResourceTitle==false){
   			logInfo("Unable to go to the resources page.");
   			Assert.assertTrue(getResourceTitle, "Unable to go to the resources page.");
   		}
	}
    
    //TC2355
    @Test(priority=939)
    public void verifyResourceTitleValidation() throws Exception{
    	    	
    	logInfo("inside verifyResourceTitleDuplication() Test");
		System.out.println("inside verifyResourceTitleDuplication() Test");
		nav2marketBusinessRL();	
		addNewResource(newResourceTitle_text1, parentCategory_text2, newResourceStatus_text, true,true,"PDF");
		boolean isResourceTitleValidated = checkResourceTitleValidation();
		if(isResourceTitleValidated==false){
   			logInfo("Unable to validate the resource title when enter duplicate title.");
   			Assert.assertTrue(isResourceTitleValidated, "Unable to validate the resource title when enter duplicate title.");
   		}
	}
    
    //TC2357
    @Test(priority=940)
    public void addResourceWithVideoAsset() throws Exception{
    	    	    	
     	logInfo("inside addResourceWithVideoAsset() Test");
		System.out.println("inside addResourceWithVideoAsset() Test -->");
		ym.nav2YouTube();
		ym.addNewVideo(youtubeTitle,youtubeDesc);
		
		nav2marketBusinessRL();
				
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		if(isAdded==true){
			
			addNewResource(newResourceTitle_text3, parentCategory_text1, newResourceStatus_text, true,true,"Video");
			nav2marketBusinessRL();
			selectCategoryFilter(parentCategory_text1);
			selectResource(newResourceTitle_text3);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text3);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text3 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text3 + " does not matches the title in resource preview page.");
			}
		}
	
	}
    
    @Test(priority=941)
    public void addResourceWithTextAsset() throws Exception{
	    logInfo("inside addResourceWithTextAsset() Test");
		System.out.println("inside addResourceWithTextAsset() Test -->");
		nav2marketBusinessRL();
				
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		if(isAdded==true){
			addNewResource(newResourceTitle_text4, parentCategory_text1, newResourceStatus_text, true,true,"Text");
			nav2marketBusinessRL();
			selectCategoryFilter(parentCategory_text1);
			selectResource(newResourceTitle_text4);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text4);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text4 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text4 + " does not matches the title in resource preview page.");
			}
		}
	
	}
    
    @Test(priority=942)
    public void addResourceWithDocumentAsset() throws Exception{
	    logInfo("inside addResourceWithDocumentAsset() Test");
		System.out.println("inside addResourceWithDocumentAsset() Test ..");
		nav2marketBusinessRL();
				
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		if(isAdded==true){
			addNewResource(newResourceTitle_text5, parentCategory_text1, newResourceStatus_text, true,true,"Document");
			nav2marketBusinessRL();
			selectCategoryFilter(parentCategory_text1);
			selectResource(newResourceTitle_text5);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text5);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text5 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text5 + " does not matches the title in resource preview page.");
			}
		}
	
	}
    
    @Test(priority=943)
    public void addResourceWithSpreadsheetAsset() throws Exception{
    	logInfo("inside addResourceWithSpreadsheetAsset() Test");
		System.out.println("inside addResourceWithSpreadsheetAsset() Test ..");
		nav2marketBusinessRL();
				
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		if(isAdded==true){
			addNewResource(newResourceTitle_text6, parentCategory_text1, newResourceStatus_text, true,true,"Spreadsheet");
			nav2marketBusinessRL();
			selectCategoryFilter(parentCategory_text1);
			selectResource(newResourceTitle_text6);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text6);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text6 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text6 + " does not matches the title in resource preview page.");
			}
		}
	
	}
    
    @Test(priority=944)
    public void addResourceWithZIPAsset() throws Exception{
	    logInfo("inside addResourceWithZIPAsset() Test");
		System.out.println("inside addResourceWithZIPAsset() Test ..");
		nav2marketBusinessRL();
				
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		if(isAdded==true){
			addNewResource(newResourceTitle_text7, parentCategory_text1, newResourceStatus_text, true,true,"ZIP");
			nav2marketBusinessRL();
			selectCategoryFilter(parentCategory_text1);
			selectResource(newResourceTitle_text7);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text7);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text7 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text7 + " does not matches the title in resource preview page.");
			}
		}
	
	}
    
    @Test(priority=945)
    public void addResourceWithPresentationAsset() throws Exception{
	    logInfo("inside addResourceWithPresentationAsset() Test");
		System.out.println("inside addResourceWithZIPAsset() Test ..");
		nav2marketBusinessRL();
				
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		if(isAdded==true){
			addNewResource(newResourceTitle_text8, parentCategory_text1, newResourceStatus_text, true,true,"Presentation");
			selectCategoryFilter(parentCategory_text1);
			selectResource(newResourceTitle_text8);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text8);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text8 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text8 + " does not matches the title in resource preview page.");
			}
		}
	
	}
    
    @Test(priority=946)
    public void addResourceWithImageAsset() throws Exception{
	    logInfo("inside addResourceWithImageAsset() Test");
		System.out.println("inside addResourceWithImageAsset() Test ..");
		nav2marketBusinessRL();
				
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		if(isAdded==true){
			addNewResource(newResourceTitle_text9, parentCategory_text1, newResourceStatus_text, true,true,"Image");
			nav2marketBusinessRL();
			selectCategoryFilter(parentCategory_text1);
			selectResource(newResourceTitle_text9);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text9);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text9 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text9 + " does not matches the title in resource preview page.");
			}
		}
	
	}
    
    @Test(priority=947)
    public void addResourceWithContentBlockAsset() throws Exception{
        logInfo("inside addResourceWithContentBlockAsset() Test");
		System.out.println("inside addResourceWithContentBlockAsset() Test ..");
		nav2marketBusinessRL();
				
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		if(isAdded==true){
			addNewResource(newResourceTitle_text10, parentCategory_text1, newResourceStatus_text, true,true,"Content Block");
			nav2marketBusinessRL();
			addNewResource(newResourceTitle_text11, parentCategory_text1, newResourceStatus_text, true,true,"PDF");
			nav2marketBusinessRL();
			selectCategoryFilter(parentCategory_text1);
			selectResource(newResourceTitle_text10);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text10);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text10 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text10 + " does not matches the title in resource preview page.");
			}
		}
	
	}
    
    @Test(priority=948)
    public void filterResourceByMarketLanguages() throws Exception{
     	logInfo("inside filterResourceByMarketLanguages() Test");
		System.out.println("inside filterResourceByMarketLanguages() Test ..");
		nav2marketBusinessRL();
		selectMarketFilter(canadianMarket);
		boolean isResourceFound = selectResource(newResourceTitle_text4);
		if(isResourceFound==true){
			logInfo(newResourceTitle_text4 + " Unable to find the resource in Canadian market.");
			Assert.assertFalse(isResourceFound, newResourceTitle_text4 + " Unable to find the resource in Canadian market.");
		}
	
	}
    
    
    //TC2363 - Verify filtering the resource by file type in admin side.
  	@Test(priority=949)
  	public void verifyResourceByFileTypeAdmin() throws Exception{
  	
  		logInfo("inside verifyResourceByFileTypeAdmin() Test");
  		nav2marketBusinessRL();
  		selectCategoryFilter(parentCategory_text1);
  		selectFilterType(fileType);
  		boolean isResourceFound = selectResource(newResourceTitle_text2);
		if(isResourceFound==false){
			logInfo(newResourceTitle_text2 + " Unable to find the resource in PDF filter type.");
			Assert.assertTrue(isResourceFound, newResourceTitle_text2 + " Unable to find the resource in PDF filter type.");
		}

  	}
  	
    //TC2364 - sorting the resource by newest on admin side.
  	@Test(priority=950)
  	public void sortResourceByNewestAdmin() throws Exception{
  		logInfo("inside sortResourceByNewestAdmin() Test");
  		nav2marketBusinessRL();
  		selectCategoryFilter(parentCategory_text1);
  		sortByNewestResourcesAdmin("Newest");
  		boolean isResourceFound = selectResource(newResourceTitle_text1);
		if(isResourceFound==false){
			logInfo(newResourceTitle_text1 + " Unable to find the resource by sorting the type as Newest.");
			Assert.assertTrue(isResourceFound, newResourceTitle_text1 + " Unable to find the resource by sorting the type as Newest.");
		}

  	}
  	

	
  	
    //TC2383- verify download option availability for a video resource
  	@Test(priority=951)
  	public void verifyDownloadForVideoResource() throws Exception{
  		  		
  		logInfo("inside verifyDownloadForVideoResource() Test");
  		nav2marketBusinessRL();
  		selectCategoryFilter(parentCategory_text1);
  		selectResource(newResourceTitle_text3);
  		boolean isDownloadFound = verifyDownloadOption();
		if(isDownloadFound==true){
			logInfo(newResourceTitle_text3 + " Can see the download option for a video type asset,but it shouldnt present.");
			Assert.assertFalse(isDownloadFound, newResourceTitle_text3 + " Can see the download option for a video type asset,but it shouldnt present.");
		}

  	}
    
    //TC2395 - Verify resource category without a resource attached to it.
  	@Test(priority=952)
  	public void verifyCategoryWithoutMarkets() throws Exception{
  		logInfo("inside verifyCategoryWithoutMarkets() Test");
  		navigate2AdminRL();
  		addResourceCategoryWithoutMarkets(parentCategory_text3,"None");
  		// need to logout from admin and logged in as user to verify this
  		logOut();
  		logIn(nonadminUser_text,nonadminPwd_text);

  		navigate2UserRL();
  		boolean isCategoryFound = verifyParentCategory(parentCategory_text3);
		if(isCategoryFound==true){
			logInfo(parentCategory_text3 + " able to see the resource category without masrkets on the user side.");
			Assert.assertFalse(isCategoryFound, parentCategory_text3 + " able to see the resource category without masrkets on the user side.");
		}

  	}
  	
  	//TC2396
  	@Test(priority=953)
    public void filterResourceByMarketLanguagesUser() throws Exception{
  		
    	logInfo("inside filterResourceByMarketLanguagesUser() Test");
		System.out.println("inside filterResourceByMarketLanguagesUser() Test ..");
		logOut();
  		logIn(adminUser_text,adminPwd_text);
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		selectMarketFilter(canadianMarket);
		boolean isResourceFound = verifyResource(newResourceTitle_text4);
		if(isResourceFound==true){
			logInfo(newResourceTitle_text4 + " Unable to find the resource in Canadian market.");
			Assert.assertFalse(isResourceFound, newResourceTitle_text4 + " Unable to find the resource in Canadian market.");
		}
	
	}
  	
  	@Test(priority=954)
  	public void sortResourceByNewestUser() throws Exception{
  		logInfo("inside sortResourceByNewestUser() Test");
  		navigate2UserRL();
		boolean flag = verifyParentCategory(parentCategory_text1);
		sortByNewestResourcesAdmin("Newest");
  		boolean isResourceFound = verifyResource(newResourceTitle_text1); //newResourceTitle_text4
		if(isResourceFound==false){
			logInfo(newResourceTitle_text1 + " Unable to find the resource by sorting the type as Newest.");
			Assert.assertTrue(isResourceFound, newResourceTitle_text1 + " Unable to find the resource by sorting the type as Newest.");
		}

  	}
  	
  	//TC2423 - write a post for an asset.
  	@Test(priority=955)
  	public void postCommentForAsset() throws Exception{ 
 	   	
  		logInfo("inside postCommentForAsset() Test");
  		navigate2UserRL();
  		verifyParentCategory(parentCategory_text1);
  		previewResourceListView(newResourceTitle_text1); //newResourceTitle_text4
  		writeCommentForAsset(postTitle);
  		boolean isCommentFound = verifyCommentOnAsset(postTitle);
		if(isCommentFound==false){
			logInfo(postTitle + " Unable to find the comment on resource asset.");
			Assert.assertTrue(isCommentFound, postTitle + " Unable to find the comment on resource asset.");
		}
  		
  	}	
  	
    //TC2451
  	@Test(priority=956)
    public void filterResourceByAssetTypes() throws Exception{
  		logInfo("inside filterResourceByAssetTypes() Test");
		System.out.println("inside filterResourceByAssetTypes() Test ..");
		nav2marketBusinessRL();
		selectAssetTypeFilter(assetType);
		selectCategoryFilter(parentCategory_text1);
		boolean isResourceFound = selectResource(newResourceTitle_text1); //newResourceTitle_text4
		if(isResourceFound==false){
			logInfo(newResourceTitle_text1 + " Unable to find the resource in resource library asset types.");
			Assert.assertTrue(isResourceFound, newResourceTitle_text1 + " Unable to find the resource in resource library asset types.");
		}
	
	}
  	
    //TC3421
  	@Test(priority=957)
    public void addResourceValidations() throws Exception{
    	logInfo("inside addResourceValidations() Test");
		System.out.println("inside addResourceValidations() Test ..");
		nav2marketBusinessRL();
		boolean isValidationsVerified = verifyAddResourceValidations();
		if(isValidationsVerified==false){
			logInfo(" Unable to verify all the validations while adding a resource.");
			Assert.assertTrue(isValidationsVerified, " Unable to verify all the validations while adding a resource.");
		}
	
	}
  	
  	
  	
  	//TC3422 && TC3423
  	@Test(priority=958)
    public void verifyAddAssetValidations() throws Exception{
    	logInfo("inside verifyAddAssetValidations() Test");
		System.out.println("inside verifyAddAssetValidations() Test ..");
		nav2marketBusinessRL();
		boolean isValidationsVerified = verifyAssetValidationsAdd();
		if(isValidationsVerified==false){
			logInfo("Unable to verify validations while adding an asset.");
			Assert.assertTrue(isValidationsVerified, "Unable to verify validations while adding an asset.");
		}
	
	}
  	
  	@Test(priority=959)
    public void verifyEditAssetValidations() throws Exception{
  		
  		logInfo("inside verifyEditAssetValidations() Test");
		System.out.println("inside verifyEditAssetValidations() Test ..");
		nav2marketBusinessRL();
		selectCategoryFilter(parentCategory_text1);
		editResourceAdmin(newResourceTitle_text4);
		boolean isValidationsVerified = verifyAssetValidationsEdit();
		if(isValidationsVerified==false){
			logInfo("Unable to verify validations while adding an asset.");
			Assert.assertTrue(isValidationsVerified, "Unable to verify validations while adding an asset.");
		}
	
	}
  	
  	//TC3426
  	@Test(priority=960)
    public void cloneResourceTitleValidation() throws Exception{
  		    	
  		logInfo("inside cloneResourceTitleValidation() Test");
		System.out.println("inside cloneResourceTitleValidation() Test ..");
		nav2marketBusinessRL();
		selectCategoryFilter(parentCategory_text1);
		cloneResourceAdmin(newResourceTitle_text4);
		boolean isValidationsVerified = verifyClonedResourceValidations();
		if(isValidationsVerified==false){
			logInfo("Unable to verify resource title validation while cloning a resource.");
			Assert.assertTrue(isValidationsVerified, "Unable to verify resource title validation while cloning a resource.");
		}
	
	}
    
     	
  	//TC3432
  	@Test(priority=961)
    public void enableResourceOptions() throws Exception{

  		logInfo("inside enableResourceOptions() Test");
		System.out.println("inside enableResourceOptions() Test ..");
		nav2marketBusinessRL();
		verifyEnableResourceOptions();
		navigate2UserRL();
		WebElement marketsFilter = driver().findElement(By.xpath(ddlResourceMarkets));
		if(!marketsFilter.isDisplayed()){
			Assert.assertTrue(marketsFilter.isDisplayed(),"Unable to find markets filter on the user side of resource library page.");
		}
		
		WebElement resViewedByOthers = driver().findElement(By.linkText("Popular"));
		if(!resViewedByOthers.isDisplayed()){
			Assert.assertTrue(resViewedByOthers.isDisplayed(),"Unable to find Resources Viewed by others link on the user side of resource library page.");
		}
		
		WebElement resourceLayout = driver().findElement(By.xpath(resLayout));
		String layout = resourceLayout.getAttribute("title");
		if(layout == "Grid"){
			if(resourceLayout.isDisplayed()){
				Assert.assertFalse(resourceLayout.isDisplayed(),"Unable to change the resource library layout.");
			}
		}
		
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		Assert.assertTrue(verifyResource(newResourceTitle_text4));
		
		waitOnElement("xpath",resCommentsTab);
		WebElement assetComments = driver().findElement(By.xpath(resCommentsTab));
		if(!assetComments.isDisplayed()){
			Assert.assertTrue(assetComments.isDisplayed(),"Unable to find assets comments section.");
		}
		waitOnElement("xpath",resReviewsTab);
		WebElement assetReviews = driver().findElement(By.xpath(resReviewsTab));
		if(!assetReviews.isDisplayed()){
			Assert.assertTrue(assetReviews.isDisplayed(),"Unable to find assets reviews section.");
		}
		
		waitOnElement("xpath",lnkShare);
		clickOnElement("xpath",lnkShare);
		implicityWaits(3);
		WebElement shareResToSocialNetworks = driver().findElement(By.xpath(txtShare2SocialNetworks));
		if(!shareResToSocialNetworks.isDisplayed()){
			Assert.assertTrue(shareResToSocialNetworks.isDisplayed(),"Unable to find share to social networks link.");
		}
		
		WebElement shareResToCommunity = driver().findElement(By.xpath(txtShare2Community));
		if(!shareResToCommunity.isDisplayed()){
			Assert.assertTrue(shareResToCommunity.isDisplayed(),"Unable to find share to community link.");
		}
		
		WebElement shareResToPwp = driver().findElement(By.xpath(txtShare2Community));
		if(!shareResToPwp.isDisplayed()){
			Assert.assertTrue(shareResToPwp.isDisplayed(),"Unable to find share to pwp link.");
		}
		
		
	}
    
  	//TC3447
  	@Test(priority=962)
  	public void checkResourcesPagination() throws Exception, Exception{
  	    	    	
  		logInfo("Inside checkResourcesPagination Test...");
  		System.out.println("Inside checkResourcesPagination Test...");
  		navigate2UserRL();
  		verifyParentCategory(parentCategory_text1);
  		boolean isResourceCountVerified = verifyResourceCountOnPagination();
		if(isResourceCountVerified==false){
			logInfo("Resources count per page has been exceeded the maximum limit.");
			Assert.assertTrue(isResourceCountVerified, "Resources count per page has been exceeded the maximum limit.");
		} 
  	}
  	
  	//TC3452
  	@Test(priority=963)
  	public void validateResourceCategoryName() throws Exception{
  	   	
  		logInfo("Inside validateResourceCategoryName Test...");
  		System.out.println("Inside validateResourceCategoryName Test...");
  		navigate2AdminRL();
  		boolean isCategoryNameVerified = verifyCategoryDuplication(parentCategory_text1);
  		if(isCategoryNameVerified==false){
			logInfo("Category Name cannot be verified for duplication.");
			Assert.assertTrue(isCategoryNameVerified, "Category Name cannot be verified for duplication.");
		} 
 
  	}
  	

  	//TC2372
    @Test(priority=964)
    public void likeResource() throws Exception{
		
    	logInfo("Inside likeResource() method");   
	    navigate2UserRL();	    
	    verifyParentCategory(parentCategory_text1);
	    previewResourceListView(newResourceTitle_text1); //newResourceTitle_text4
	    verifyLikeResources();
	    rl.closeResourcePreview();  
	}	
    
    //TC2374
    @Test(priority=965)
    public void RL_ShareInFaceBook() throws Exception{
    	
    	logInfo("TC2374 - To share a resource link to social networks like Facebook, Twitter etc");    		
	    navigate2UserRL();
	    verifyParentCategory(parentCategory_text1);
	    previewResourceListView(newResourceTitle_text4);
	    verifyAssetInFB(parentCategory_text1, newResourceTitle_text4);            
	}  	
    
    //TC2374
    @Test(priority=966)   	
    public void RL_ShareInTweet() throws Exception{
    	
    	logInfo("TC2374 - To share a resource link to social networks like Facebook, Twitter etc");    		
	    navigate2UserRL();	  
	    verifyParentCategory(parentCategory_text1);
	    previewResourceListView(newResourceTitle_text4);
	    selectcontrols("Share");
	    shareToSocial();
	    selectTweetIcon();      
	    sm.shareInTwitter();
	    closeModalWindow();
    	
	   /* profile.login2Twitter() ;
   	 profile.verifyPostsInTwitter(videoText);
   	 lc.login();  */
	}
    
    //TC2374
    @Test(priority=967)	
    public void share2Community() throws Exception{
    	
    	logInfo("inside share2Community() method.");    		
		 driver().navigate().to(appUrl );
	    navigate2UserRL();	    
	    verifyParentCategory(parentCategory_text1);
	    previewResourceListView(newResourceTitle_text1); //newResourceTitle_text4
	    shareToCommunity();    
	    


	}
    
   @Test(priority=968)
   public void share2PWP() throws Exception{
	   try{
		    logInfo("inside share2PWP() Test");
		    
	   	    navigate2UserRL();       
		    verifyParentCategory(parentCategory_text1);
		    previewResourceListView(newResourceTitle_text1); //newResourceTitle_text4
	        boolean isResAssetShared = shareToMyWebsite(resourceAsset);
	        
	        if(!isResAssetShared){
	        	Assert.assertTrue(isResAssetShared, "Unable to share the resource to the PWP.");
	        }
	   }
	   catch(Exception ex){
		  driver().navigate().to(appUrl);
	   }

    }
    
    @Test(priority=969)
    public void share2Email() throws Exception{
    	    	
    	try{
    		logInfo("inside RL_ShareToEmail() Test");
    		profile.navigateMyProfileAccount("Notifications");
        	String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();       
           	//String selfmailId = getEmail(adminUser_text,appUrl);
        	navigate2UserRL();    	     
    		verifyParentCategory(parentCategory_text1);
    		//Assert.assertTrue(false, "Unable to see the compose email popup");
    		previewResourceListView(newResourceTitle_text1); //newResourceTitle_text4
    		shareResourceByEmail(newResourceTitle_text1,selfmailId);
    		
    	}
    	catch(Exception ex){
    		driver().navigate().to(appUrl);
    		
    	}
      	
    }
    
   @Test(priority=970)
    public void RL_validatetEmailswithType() throws Exception{ 
	   
	    logInfo("inside RL_validatetEmailswithType() Test");
    	profile.navigateMyProfileAccount("Notifications");
    	String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
	    
	    //String selfmailId = getEmail(adminUser_text,appUrl);
	    
    	navigate2UserRL();
    	verifyParentCategory(parentCategory_text1);
        verifyFileTypeMailRetriveShares("PDF",newResourceTitle_text2, selfmailId); 
        rl.closeResourcePreview();  
        
    }
    //TC2377
    @Test(priority=971)
    public void RL_verifyAttchmentInRecipMails() throws Exception{   
    	
    	logInfo("TC2377- Verify Resource file attachment under Recipient email");
    	profile.navigateMyProfileAccount("Notifications");
    	String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();    	
    	

    	// String selfmailId = getEmail(adminUser_text,appUrl);
    	
    	nav2marketBusinessRL();
    	selectCategoryFilter(parentCategory_text1);
    	selectResource(newResourceTitle_text4);
    	selectcontrols("Email");
		clickOnButton("cssSelector", linkCompBtn);
		composeNSend(selfmailId);    	
		inbox.go2Inbox();
		inbox.selectVibeMailsWithSubject(composeEmailSubject_text);
		inbox.deleteFilteredVibeMails();
   }

    @Test(priority=972)
    public void RL_ShareToCommunityFromAdmin() throws Exception{
    	
	    logInfo("TC2374 - To share a resource link to community people from Admin");    		
	    nav2marketBusinessRL();
	    selectCategoryFilter(parentCategory_text1);
	    selectResource(newResourceTitle_text4);
	    verifyAssetInFB(parentCategory_text1, newResourceTitle_text4);   
	}
    
    @Test(priority=973)
    public void RL_ShareToWebSiteFromAdmin() throws Exception{
    	
    	logInfo("TC2374 - To share a resource link to website from Admin");    		
	    nav2marketBusinessRL();	  
	    selectCategoryFilter(parentCategory_text1);
    	selectResource(newResourceTitle_text4);     
	    emailSection();   
	}
    
     
    
    // Verify the keyword search resource functionality.
  	@Test(priority=974)
  	public void verifyKeywordSearchResource() throws Exception{
  		String searchResourceKeywordText = "resource";
  		logInfo("inside verifyKeywordSearchResource() Test");
  		nav2marketBusinessRL();
  		Assert.assertTrue(searchKeywordResource(searchResourceKeywordText));
  	}
  	
  	@Test(priority=975)
    public void editResourceValidations() throws Exception{
	
    	logInfo("inside editResourceValidations() Test");
		System.out.println("inside editResourceValidations() Test ..");
		nav2marketBusinessRL();
		selectCategoryFilter(parentCategory_text1);
		editResourceAdmin(newResourceTitle_text4);
		clearAllResourceValues();
		boolean isValidationsVerified = verifyResourceValidationsEdit();
		if(isValidationsVerified==false){
			logInfo("Unable to verify all the validations while editing a resource.");
			Assert.assertTrue(isValidationsVerified, "Unable to verify all the validations while editing a resource.");
		}
	
	}
  	
  	@Test(priority=976)
    public void RL_ShareEmailWithAttachment() throws Exception{
  		    	
  		logInfo("Share Resource to email recipients with attachment.");   
  		// For time being I have commented the below, as data is not getting under Notifications tab.
  		
    	/*profile.navigateMyProfileAccount("Notifications");
    	String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();*/    
  		
  		String selfmailId = getEmail(adminUser_text,appUrl);
    	navigate2UserRL();       
    	verifyParentCategory(parentCategory_text1);
    	previewResourceListView(newResourceTitle_text4);
        selectcontrols("Email");        
        rlEmailWithAttachment(selfmailId);
    } 
  	
  	@Test(priority=977)
	public void verifyverifyDownloadAssetPresentationAsset() throws Exception{
  		int downloadCnt = 0;
		logInfo("inside verifyDownloadPresentationAsset() Test");
		System.out.println("inside verifyDownloadPresentationAsset() Test");
		downloadCnt = verifyDownloadCount(newResourceTitle_text8);
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		previewResourceListView(newResourceTitle_text8);
		downloadAndVerifyPresentationAsset();
		navigate2UserRL();
		if(downloadCnt < verifyDownloadCount(newResourceTitle_text8)){
			Assert.assertTrue(true, newResourceTitle_text8 +" presentation asset is downloaded.");
		}
		else{
			Assert.assertTrue(false, newResourceTitle_text8 +" presentation asset is not downloaded.");
		}
		
	}
  	
  	@Test(priority=978)
  	public void verifyResourceRankPersists() throws Exception, Exception{
  		logInfo("inside verifyResourceRankPersists() Test");
		System.out.println("inside verifyResourceRankPersists() Test");
		nav2marketBusinessRL();
		selectCategoryFilter(parentCategory_text1);
		editResourceAdmin(newResourceTitle_text8);
		boolean isRanksVerified = updateResourceRanksAndVerify(parentCategory_text1,newResourceTitle_text8);
		if(!isRanksVerified){
			Assert.assertTrue(isRanksVerified, "Unable to persist the updated ranks for resource.");
		}
  	}
  	
   	@Test(priority=979)
  	public void sortResourceByPublishDate() throws Exception{
  		logInfo("inside sortResourceByPublishDate() Test");
  		System.out.println("inside sortResourceByPublishDate() Test");
  		nav2marketBusinessRL();
  		selectCategoryFilter(parentCategory_text1);
  		sortByPublishDate("Publish Date");
  		boolean isResourceFound = selectResource(newResourceTitle_text1); //newResourceTitle_text8
		if(isResourceFound==false){
			logInfo(newResourceTitle_text1 + " Unable to find the resource by sorting with Publish Date.");
			Assert.assertTrue(isResourceFound, newResourceTitle_text1 + " Unable to find the resource by sorting with Publish Date.");
		}

  	}
  	
   	@Test(priority=980)
    public void verifyCACategoy4USUser() throws Exception{
     	logInfo("inside verifyCACategoy4USUser() Test");
		System.out.println("inside verifyCACategoy4USUser() Test ..");
		nav2marketBusinessRL();
		addCanadianResourceCategory(canadianCategory_text1,"None");
		nav2marketBusinessRL();
		addNewResource(canadianResource, canadianCategory_text1, newResourceStatus_text, true,true,"PDF");
		back2Office();
		logOut();
		logIn(distributorUser1_text,distributorUser1Pwd_text);
		navigate2UserRL();
		boolean isVerified = verifyParentCategory(canadianCategory_text1);
		if(isVerified){
			Assert.assertFalse(isVerified, "Able to see the Canadian resource category with the US user");
		}

	}
  	
  	@Test(priority=981)
	public void moveResources2Category() throws Exception{
		  		
		nav2marketBusinessRL();
		logInfo("inside moveResources2Category() Test");
		selectCategoryFilter(parentCategory_text1);
		Assert.assertTrue(moveResource2Category(newResourceTitle_text6,parentCategory_text2));
		
	}
  	
	@Test(priority=982)
	public void deleteMultipleResources() throws Exception{
		logInfo("inside deleteMultipleResources() Test");
		nav2marketBusinessRL();
		selectCategoryFilter(parentCategory_text1);
		if(deleteMultipleResources(deleteResources)){
			confirmationMessage("Resource was successfully deleted.");
		} 
		
	}
	
    
    @Test(priority=983)
	public void reloginAsAdmin() throws Exception{
		System.out.println("inside reloginAsAdmin() Test");
		logInfo("inside reloginAsAdmin() Test");
	    logOut();
	    logIn(adminUser_text, adminPwd_text);  		  
	}
    
    @Test(priority=984)
   	public void deleteResourceInAdmin() throws Exception{
    	
		logInfo("inside deleteResourceInAdmin() Test");
   		System.out.println("inside deleteResourceInAdmin() Test");
   		back2Office();
   		userLogout();
   		nav2marketBusinessRL();
   		selectCategoryFilter(parentCategory_text1);
   		deleteResourceAdminListView(newResourceTitle_text1); //newResourceTitle_text2
   		confirmationMessage("Resource was successfully deleted.");
   		/*boolean isResPresent = displayResourceInListView(newResourceTitle_text1);
   		if(isResPresent==true){
   			logInfo(newResourceTitle_text1 + " resource could not be deleted.");
   			Assert.assertFalse(isResPresent, newResourceTitle_text1 + " resource could not be deleted.");
   		} */
   	}
    
    @Test(priority=985)
 	public void moveResources2SubCategory() throws Exception, Exception{
 		    	
 		logInfo("inside moveResources2Category() Test");
 		System.out.println("Inside removeAttachment Test...");
 		nav2marketBusinessRL();
 		selectCategoryFilter(parentCategory_text1);
 		Assert.assertTrue(moveResource2Category(newResourceTitle_text5,childCategory_text1));
 	}
    
    @Test(priority=986)
	public void deleteRLCategory() throws Exception{
       	logInfo("inside deleteRLCategory() Test");
	  	navigate2AdminRL();
		deleteProductCategory(parentCategory_text1);
		confirmationMessage("Resource Category deleted successfully.");
		//In smoke we need to comment out the below 2 parent categories, as we haven't created them for smoke.
		/*deleteProductCategory(parentCategory_text2);
		deleteProductCategory(parentCategory_text3);*/
		/*boolean isDeleted = verifyResourceCategoryPresent(parentCategory_text1);
		if(isDeleted==true){
			logInfo(parentCategory_text1 + " product category could not be deleted.");
			Assert.assertFalse(isDeleted, parentCategory_text1 + " product category could not be deleted.");
		}*/
	}
    
	//TC3450
  	@Test(priority=987)
  	public void uploadAttachment2MaxSixe() throws Exception{
  				
  		logInfo("Inside uploadAttachment2MaxSixe Test...");
  		System.out.println("Inside uploadAttachment2MaxSixe Test...");
  		navigate2UserRL();
  		verifyParentCategory(parentCategory_text1);
  		previewResourceListView(newResourceTitle_text4);
 		verifyMaxSizeFileUpload();
  	}
  	
  	 //TC3451
  	@Test(priority=988)
  	public void removeAttachment() throws Exception{
  		
  		logInfo("Inside removeAttachment Test...");
  		System.out.println("Inside removeAttachment Test...");
  		navigate2UserRL();
  		verifyParentCategory(parentCategory_text1);
  		previewResourceListView(newResourceTitle_text4);
  		//This page is asking you to confirm that you want to leave - data you have entered may not be saved
  		
  		boolean isAttachedFileRemoved = verifyRemoveAttchment();
  		if(isAttachedFileRemoved==true){
			logInfo("Unable to remove the attcahed file.");
			Assert.assertFalse(isAttachedFileRemoved, "Unable to remove the attcahed file.");
		} 
  	}
  	

    
    @Test(priority=989)
    public void disableResourceOptions() throws Exception{
     	
  		logInfo("inside disableResourceOptions() Test");
		System.out.println("inside disableResourceOptions() Test ..");
		nav2marketBusinessRL();
		verifyDisableResourceOptions();
		
		nav2marketBusinessRL();
		try{
			WebElement statusFilter = driver().findElement(By.xpath("//*[@id='status_container']/button"));
			WebElement resourceFilter = driver().findElement(By.xpath("//*[@id='resourceable_types_container']/button"));
			if(!statusFilter.isDisplayed() || !resourceFilter.isDisplayed()){
				Assert.assertTrue(false, "Unable to find the Status and Resource filters, market filter is disabled.");
			}
		}
		catch(Exception e) {
			Assert.assertTrue(false, "Unable to find the Status and Resource filters, market filter is disabled.");
		}
		
		navigate2UserRL();
		try{
			 WebElement marketsFilter = driver().findElement(By.xpath(ddlResourceMarkets));
		 }
		 catch (Exception e) {
			 Assert.assertFalse(false,"Unable to find markets filter on the user side of resource library page.");
		 }
		
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		verifyResource(newResourceTitle_text4);
		 try {
			 WebElement shareOption = driver().findElement(By.xpath(resShareOption));
		 }
		 catch (Exception e) {
			 Assert.assertFalse(false,"Unable to find share option for the resource.");
		 }
		
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		verifyResource(newResourceTitle_text4);
		 try {
			 WebElement assetComments = driver().findElement(By.xpath(resCommentsTab));
		 }
		 catch (Exception e) {
			 Assert.assertFalse(false,"Unable to find assets comments section.");
		 }
				
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		verifyResource(newResourceTitle_text4);
		try {
			WebElement assetReviews = driver().findElement(By.xpath(resReviewsTab));
		 }
		 catch (Exception e) {
			 Assert.assertFalse(false,"Unable to find assets reviews section.");
		 }
		
		navigate2UserRL();
		try {
			WebElement resViewedByOthers = driver().findElement(By.linkText("Popular"));
		 }
		 catch (Exception e) {
			 Assert.assertFalse(false,"Unable to find Resources Viewed by others link on the user side of resource library page.");
		 }
		
		navigate2UserRL();
		WebElement resourceLayout = driver().findElement(By.xpath(resLayout));
		String layout = resourceLayout.getAttribute("title");
		if(layout == "Grid"){
			if(resourceLayout.isDisplayed()){
				Assert.assertFalse(resourceLayout.isDisplayed(),"Unable to change the resource library layout.");
			}
		}
	}
	
    
	
}
