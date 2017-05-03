package vibe.resourcelibrary.tests;

import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import vibe.inbox.tests.InboxMethods;
import common.Priority;
import common.TestBase;

@Priority(9)
public class RL_Tests extends RLMethods {
	
	InboxMethods inbox = new InboxMethods();
	
	@Test(priority=901)
	public void createResource() throws Exception{
		System.out.println("inside the createResource Test..");
		navigate2RL();
		addPoductCategory(parentCategory_text);
		boolean isAdded = verifyProductCategoryPresent(parentCategory_text);
		if(isAdded= false){
			logInfo(parentCategory_text + " product category could not be added.");
			Assert.assertTrue(isAdded, parentCategory_text + " product category could not be added.");
		}
		navigate2RL();
		addNewResource(parentCategory_text, "Text");
		navigate2RL();
		boolean isResourceAvailable =  verifyResourcePresent(parentCategory_text,newResourceTitle_text);
		if(isResourceAvailable = true){
			logInfo(newResourceTitle_text + " resource is present.");
			Assert.assertTrue(isResourceAvailable, newResourceTitle_text + " resource is present.");
		} else {
			logInfo(newResourceTitle_text + " resource is not present.");
			Assert.assertTrue(isResourceAvailable, newResourceTitle_text + " resource is not present.");
		}
	}

	
	@Test(priority=902, dependsOnMethods = {"createResource"})
	public void likeResource() throws Exception{
		System.out.println("inside the likeResource() Test..");
		logInfo("inside the createResource() Test..");
		navigate2RL();
		boolean isResourceAvailable =  verifyResourcePresent(parentCategory_text,newResourceTitle_text);
		if(isResourceAvailable = true){
			previewResource(parentCategory_text,newResourceTitle_text); 
			likeResource();
			closeResourcePreview();
		} else {
			Assert.assertTrue(isResourceAvailable, newResourceTitle_text + " resource is not present.");
		}
	}
	
	
	@Test(priority=903, dependsOnMethods = {"createResource"})
	public void shareResource() throws Exception{
		System.out.println("inside the shareResource() Test..");
		logInfo("inside the shareResource() Test..");
		navigate2RL();
		boolean isResourceAvailable =  verifyResourcePresent(parentCategory_text,newResourceTitle_text);
		if(isResourceAvailable = true){
			previewResource(parentCategory_text,newResourceTitle_text); 
			shareResource();
			closeResourcePreview();
			verifyResourceinCommunity(newResourceTitle_text);
		//	back2Office();
		} else {
			Assert.assertTrue(isResourceAvailable, newResourceTitle_text + " resource is not present.");
		}
	}
	
	
	
	@Test(priority=904, dependsOnMethods = {"createResource"})
	public void emailResource() throws Exception{
		System.out.println("inside the emailResource() Test..");
		logInfo("inside the emailResource() Test..");
		
		navigate2RL();
		boolean isResourceAvailable =  verifyResourcePresent(parentCategory_text,newResourceTitle_text);
		if(isResourceAvailable = true){
			previewResource(parentCategory_text,newResourceTitle_text);
			emailResouce2WebSite();
			closeResourcePreview();
			back2Office();
			inbox.go2Inbox();
			inbox.verifyVibeInboxMail(composeEmailSubject_text);
		} else {
		Assert.assertTrue(isResourceAvailable, newResourceTitle_text + " resource is not present.");
		}
	}
	
	

	@Test(priority=905, dependsOnMethods = {"createResource"})
	public void shareResource2FB() throws Exception{
		System.out.println("inside the shareResource2FB() Test..");
		logInfo("inside the shareResource2FB() Test..");
		navigate2RL();
		boolean isResourceAvailable =  verifyResourcePresent(parentCategory_text,newResourceTitle_text);
		if(isResourceAvailable = true){
			previewResource(parentCategory_text,newResourceTitle_text);
			shareResource2FB();
			closeResourcePreview();
			back2Office();
		
		} else {
			Assert.assertTrue(isResourceAvailable, newResourceTitle_text + " resource is not present.");
		}
	}

	@Test(priority=906, dependsOnMethods = {"createResource"})
	public void previewBusinessRL() throws Exception{
		System.out.println("inside the previewBusinessRL() Test..");
		logInfo("inside the previewBusinessRL() Test..");
		navigate2BusinessRL();
		verifyCategoryInBusinessRL(parentCategory4Prod_text);
		verifyResourceInBusinessRL(newResourceTitle4Prod_text);
		previewBusinessResource(newResourceTitle4Prod_text);
		verifyResourcePreviewHeading(newResourceTitle4Prod_text);
		closeResourcePreview();
	}
	
	
	@Test(priority=907, dependsOnMethods = {"createResource"})
	public void deleteCategory() throws Exception{
		System.out.println("inside the deleteProductCategory() Test..");
		logInfo("inside the deleteProductCategory() Test..");
		navigate2RL();
		deleteProductCategory(parentCategory_text);
		
	}	

}
