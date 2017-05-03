
package vibe.resourcelibrary2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.readProp;

import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.users.tests.UsersMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Priority(13)
public class AVRL2_Tests extends AVRL2Methods {
	
	readProp prop = new readProp();
	
	@Test(priority=1300)
	public void enableRLSettings() throws Exception{
		logInfo("inside enableRLSettings() Test");
		System.out.println("inside enableRLSettings() Test");
		nav2marketBusinessRL();
		verifyEnableResourceOptions();
		
	}

	@Test(priority=1301)
	public void addResourceToCategory() throws Exception{

		logInfo("inside addResourceToCategory() Test");
		System.out.println("inside addResourceToCategory() Test");
		nav2marketBusinessRL();
		addResourceCategory(parentCategory_text1,"None");
		nav2marketBusinessRL();
		addResourceCategory(childCategory_text1,parentCategory_text1);
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		if(isAdded==true){
			addNewResource(newResourceTitle_text1, parentCategory_text1, newResourceStatus_text, true,true,"Image");
			selectCategoryFilter(parentCategory_text1);
			selectResource(newResourceTitle_text1);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text1);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text1 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text1 + " does not matches the title in resource preview page.");
			}
		}
	}
	
	
	
	@Test(priority=1302)
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
    @Test(priority=1303)
    public void addResourceWithVideoAsset() throws Exception{
    	    	    	
     	logInfo("inside addResourceWithVideoAsset() Test");
		System.out.println("inside addResourceWithVideoAsset() Test");
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

    @Test(priority=1304)
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
    
    @Test(priority=1305)
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
    
    @Test(priority=1306)
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
    
    @Test(priority=1307)
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
    
    @Test(priority=1308)
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
    
    @Test(priority=1309)
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
    
  //TC2400 & TC2416 - Verify the download functionality for the resource asset.
  	@Test(priority=1310)
  	public void verifyDownloadAsset() throws Exception{ 
  		logInfo("inside verifyDownloadAsset() Test");
  		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
  		navigate2UserRL();
  		verifyParentCategory(parentCategory_text1);
  		boolean isDownloaded = downloadAssetForResource(newResourceTitle_text1);
  		if(!isDownloaded){
  			Assert.assertTrue(isDownloaded, "Unable to download a resource "+ newResourceTitle_text1);
  		}
  				
  	}

      @Test(priority=1311)
      public void verifyPreviewResource() throws Exception{
          logInfo("inside verifyPreviewResource() Test");
          navigate2UserRL();
          verifyParentCategory(parentCategory_text1); //parentCategory_text1
          previewResource(newResourceTitle_text1);
          Assert.assertTrue(verifyResourcePreviewHeading(newResourceTitle_text1));
         
      }	
  	
  	//TC2401 - Verify the search resource functionality.
  	@Test(priority=1312)
  	public void verifySearchResourceUser() throws Exception{
  		logInfo("inside verifySearchResource() Test");	
  		navigate2UserRL();
  		Assert.assertTrue(searchResource(newResourceTitle_text1));
  	}
  	
  	//TC2424 - Verify write review for a resource.
  	@Test(priority=1313)
  	public void postReviewForAsset() throws Exception{
  		try{
  			logInfo("inside postReviewForAsset() Test");
  	  		navigate2UserRL();
  	  		verifyParentCategory(parentCategory_text1);
  	  		previewResource(newResourceTitle_text2);
  	  		writeReviewForAsset(reviewTitle,reviewDesc);
  	  		Assert.assertTrue(verifyReview(reviewTitle));
  	  		logOutInAvon();
  	  		logIn(adminUser_text,adminPwd_text);
  	  		
  		}
  		catch(Exception ex){
  			logOutInAvon();
  	  		logIn(adminUser_text,adminPwd_text);
  		}
  		
  	}	
	
	@Test(priority=1314)
   	public void deleteResourceInAdmin() throws Exception{
		logInfo("inside deleteResourceInAdmin() Test");
   		System.out.println("inside deleteResourceInAdmin() Test");	
   		nav2marketBusinessRL();
   		selectCategoryFilter(parentCategory_text1);
   		deleteResourceAdmin(newResourceTitle_text1); //newResourceTitle_text2
   		confirmationMessage("Resource was successfully deleted.");
   	}
	    
	  
    @Test(priority=1315)
	public void deleteRLCategory() throws Exception{
    	logInfo("inside deleteRLCategory() Test");
	  	navigate2AdminRL();
		deleteProductCategory(parentCategory_text1);
		confirmationMessage("Resource Category deleted successfully.");
		//In smoke we need to comment out the below 2 parent categories, as we haven't created them for smoke.
		/*deleteProductCategory(parentCategory_text2);
		deleteProductCategory(parentCategory_text3);*/
		
	}
	
	
}
