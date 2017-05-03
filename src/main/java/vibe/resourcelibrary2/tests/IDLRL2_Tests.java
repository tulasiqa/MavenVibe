package vibe.resourcelibrary2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@Priority(9)
public class IDLRL2_Tests extends IDLRL2Methods {
	
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
			addNewResource(newResourceTitle_text1, parentCategory_text1, newResourceStatus_text, true,false,"PDF");   
			selectCategoryFilter(parentCategory_text1);
			boolean isResourcePresent = selectResource(newResourceTitle_text1);
			if(isResourcePresent==false){
				logInfo(newResourceTitle_text1 + " Unable to view the resource.");
				Assert.assertTrue(isResourcePresent, newResourceTitle_text1 + " Unable to view the resource.");
			}
			/*boolean isHeadingMatch = verifyResourcePreviewHeading(newResourceTitle_text1);
			if(isHeadingMatch==false){
				logInfo(newResourceTitle_text1 + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeadingMatch, newResourceTitle_text1 + " does not matches the title in resource preview page.");
			}*/
		}
	}
	
	@Test(priority=902)
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

	//TC2400 & TC2416 - Verify the download functionality for the resource asset.
		@Test(priority=903)
		public void verifyDownloadAsset() throws Exception{ 
			logInfo("inside verifyDownloadAsset() Test");
			//userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			navigate2UserRL();
			verifyParentCategory(parentCategory_text1);
			boolean isDownloaded = downloadAssetForResource(newResourceTitle_text1);
			if(!isDownloaded){
				Assert.assertTrue(isDownloaded, "Unable to download a resource "+ newResourceTitle_text1);
			}
					
		}
  	
		   //TC2401 - Verify the search resource functionality.
	  	@Test(priority=904)
	  	public void verifySearchResourceUser() throws Exception{
	  		logInfo("inside verifySearchResource() Test");
	  		navigate2UserRL();
	  		Assert.assertTrue(searchResource(searchResourceText));
	  	}
	  	
		//TC2405 - Verify the resource in List View.
		@Test(priority=905)
		public void verifyResourcesinListView() throws Exception{
			logInfo("inside verifyResourcesinListView() Test");
			navigate2UserRL();
			verifyParentCategory(parentCategory_text1);
			Thread.sleep(5000);
			Assert.assertTrue(displayResourceInListView(newResourceTitle_text1));
		}
		
	
	
	//TC2406 & TC2408 - Verify the resource preview.
	@Test(priority=906)
	public void verifyPreviewResource() throws Exception{
		logInfo("inside verifyPreviewResource() Test");
		navigate2UserRL();
		verifyParentCategory(parentCategory_text1);
		boolean isResourceFound = previewResource(newResourceTitle_text1);
		if(!isResourceFound){
			Assert.assertTrue(isResourceFound, "Unable to preview the resource "+ newResourceTitle_text1);
		}
			
	}

  	//TC2372
    @Test(priority=907)
    public void likeResource() throws Exception{
    	    	
       	logInfo("Inside likeResource() method"); 
       	navigate2UserRL();	    
	    verifyParentCategory(parentCategory_text1);
		previewResource(newResourceTitle_text1); //newResourceTitle_text4
	    verifyLikeResources();
	    rl.closeResourcePreview();  
	}	
    
    //TC2374
    @Test(priority=908)	
    public void share2Community() throws Exception{
    	logInfo("inside share2Community() method.");    		
	    navigate2UserRL();	    
	    verifyParentCategory(parentCategory_text1);
	    previewResource(newResourceTitle_text1); //newResourceTitle_text4
	    shareToCommunity();      
	}
    
    
    @Test(priority=909)
    public void share2PWP() throws Exception{
    	logInfo("inside share2PWP() Test");
 	    navigate2UserRL();       
 	    verifyParentCategory(parentCategory_text1);
 		previewResource(newResourceTitle_text1); //newResourceTitle_text4
 		shareToMyWebsite();
        login();  
     	
     }

    
    @Test(priority=910)
    public void share2Email() throws Exception{
      try{
    	    logInfo("inside RL_ShareToEmail() Test");
    	    profile.navigateMyProfileAccount("Notifications");
        	String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();       
          	
          	//String selfmailId = getEmail(adminUser_text,appUrl);
        	navigate2UserRL();    	     
    		verifyParentCategory(parentCategory_text1);
    		previewResource(newResourceTitle_text1); //newResourceTitle_text4
    		/*boolean isResourceSharedByMail = shareResourceByEmail(newResourceTitle_text1);
    		if(!isResourceSharedByMail){
    			Assert.assertTrue(isResourceSharedByMail,"Unabel to share the resource "+newResourceTitle_text1+" by email.");
    		}*/
             selectcontrols("Email");
            emailRL(selfmailId); 
            userLogout();
            
      }
      catch(Exception ex){
    	  userLogout();
      }
    	
    }
     
    @Test(priority=912)
   	public void deleteResourceInAdmin() throws Exception{
		logInfo("inside deleteResourceInAdmin() Test");
   		System.out.println("inside deleteResourceInAdmin() Test");
   		nav2marketBusinessRL();
   		selectCategoryFilter(parentCategory_text1);
   		deleteResourceAdmin(newResourceTitle_text1); //newResourceTitle_text2
   		confirmationMessage("Resource was successfully deleted.");
   	}

    @Test(priority=913)
 	public void deleteRLCategory() throws Exception{
     	logInfo("inside deleteRLCategory() Test");
 	  	navigate2AdminRL();
 		deleteProductCategory(parentCategory_text1);
 		confirmationMessage("Resource Category deleted successfully.");
 		
 	}

	
}
