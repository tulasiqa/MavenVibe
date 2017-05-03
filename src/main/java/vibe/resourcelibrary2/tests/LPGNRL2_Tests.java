package vibe.resourcelibrary2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.readProp;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.users.tests.UsersMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Priority(9)
public class LPGNRL2_Tests extends LPGNRL2Methods {
	readProp prop = new readProp();

	@Test(priority=900)
	public void enableRLSettings() throws Exception{
		logInfo("inside enableRLSettings() Test");
		System.out.println("inside enableRLSettings() Test");
		nav2marketBusinessRL();
		verifyEnableResourceOptions();
		
	}

	//TC2397 - Verify the resource in a specific category
	@Test(priority=901)
	public void addResourceToCategory() throws Exception{

		logInfo("inside addResourceToCategory() Test");
		System.out.println("inside addResourceToCategory() Test");
		nav2marketBusinessRL();
		addResourceCategory(parentCategory_text1,"None");
		nav2marketBusinessRL();
		addResourceCategory(childCategory_text1,parentCategory_text1);
		boolean isAdded = verifyResourceCategoryPresent(parentCategory_text1);
		if(isAdded==true){
			addNewResource(newResourceTitle_text1, parentCategory_text1, newResourceStatus_text, true,true,"PDF");
			selectCategoryFilter(parentCategory_text1);
			selectResource(newResourceTitle_text1);
			boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text1);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text1 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text1 + " does not matches the title in resource preview page.");
			}
		}
	}

	@Test(priority=902)
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
	
	
	//TC2401 - Verify the search resource functionality.
	@Test(priority=903)
	public void verifySearchResourceUser() throws Exception{
		logInfo("inside verifySearchResource() Test");
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		navigate2UserRL();
		Assert.assertTrue(searchResource(searchResourceText));
	}
		
		
	//TC2405 - Verify the resource in List View.
	@Test(priority=904)
	public void verifyResourcesinListView() throws Exception{
		logInfo("inside verifyResourcesinListView() Test");
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		Assert.assertTrue(displayResourceInListView(newResourceTitle_text1));
	}
	
	
	//TC2406 & TC2408 - Verify the resource preview.
	@Test(priority=905)
	public void verifyPreviewResource() throws Exception{
		logInfo("inside verifyPreviewResource() Test");
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		previewResource(newResourceTitle_text1);
		Assert.assertTrue(verifyResourcePreviewHeading(newResourceTitle_text1));
		
	}

	//TC2400 & TC2416 - Verify the download functionality for the resource asset.
	@Test(priority=906)
    public void verifyDownloadAsset() throws Exception{
        logInfo("inside verifyDownloadAsset() Test");
        navigate2UserRL();
        verifyParentCategory(parentCategory_text1);
        boolean isDownloaded = downloadAssetForResource(newResourceTitle_text1);
        if(!isDownloaded){
            Assert.assertTrue(isDownloaded, "Unable to download a resource "+ newResourceTitle_text1);
        }
                
    }
	
 	@Test(priority=907)
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
  	@Test(priority=908)
  	public void postCommentForAsset() throws Exception{  	
  		logInfo("inside postCommentForAsset() Test");
  		navigate2UserRL();
  		verifyParentCategory(parentCategory_text1);
  		previewResource(newResourceTitle_text1); //newResourceTitle_text4
  		writeCommentForAsset(postTitle);
  		boolean isCommentFound = verifyCommentOnAsset(postTitle);
		if(isCommentFound==false){
			logInfo(postTitle + " Unable to find the comment on resource asset.");
			Assert.assertTrue(isCommentFound, postTitle + " Unable to find the comment on resource asset.");
		}
  		
  	}	

  	//TC2372
    @Test(priority=909)
    public void likeResource() throws Exception{
    	logInfo("Inside likeResource() method");  
    	navigate2UserRL();	    
	    verifyParentCategory(parentCategory_text1);
		previewResource(newResourceTitle_text1); //newResourceTitle_text4
	    verifyLikeResources();
	    Thread.sleep(5000);
	    rl.closeResourcePreview();  
	}	

   @Test(priority=910)
   public void share2PWP() throws Exception{
	    logInfo("inside share2PWP() Test");
	    navigate2UserRL();       
	    verifyParentCategory(parentCategory_text1);
		previewResource(newResourceTitle_text1); //newResourceTitle_text4
        shareToMyWebsite();
    }
    
    @Test(priority=911)
    public void share2Email() throws Exception{
    	try{
    		logInfo("inside RL_ShareToEmail() Test");
           	profile.navigateMyProfileAccount("Notifications");
        	String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();       
        	//String selfmailId = getEmail(adminUser_text,appUrl);

        	navigate2UserRL();    	     
    		verifyParentCategory(parentCategory_text1);
    		previewResource(newResourceTitle_text1); //newResourceTitle_text4
            selectcontrols("Email");
            emailRL(selfmailId); 
            userLogout();
            back2Office();
            adminLogin();
    	}
    	catch(Exception ex){
    		 userLogout();
             back2Office();
             adminLogin();
    	}
    	
    }
    
    @Test(priority=912)
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
    
    @Test(priority=913)
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
    
    @Test(priority=914)
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
    
    @Test(priority=915)
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
    
    @Test(priority=916)
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
    
    @Test(priority=917)
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
    
    @Test(priority=918)
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
    
    @Test(priority=919)
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

    @Test(priority=920)
   	public void deleteResourceInAdmin() throws Exception{
    	    	
		logInfo("inside deleteResourceInAdmin() Test");
   		System.out.println("inside deleteResourceInAdmin() Test");
   		nav2marketBusinessRL();
   		selectCategoryFilter(parentCategory_text1);
   		deleteResourceAdmin(newResourceTitle_text1); //newResourceTitle_text2
   		confirmationMessage("Resource was successfully deleted.");
   		
   	}
    

    @Test(priority=921)
	public void deleteRLCategory() throws Exception{
    	
    	logInfo("inside deleteRLCategory() Test");
    	System.out.println("inside deleteRLCategory() Test");
	  	navigate2AdminRL();
		deleteProductCategory(parentCategory_text1);
		confirmationMessage("Resource Category deleted successfully.");
	}

	
}
