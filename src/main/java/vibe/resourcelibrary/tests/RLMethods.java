package vibe.resourcelibrary.tests;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.TestBase;
import common.readProp;

public class RLMethods extends TestBase {
	  readProp prop  = new readProp();
  public void navigate2RL() throws Exception{
	  	  
	    System.out.println("inside the navigate2RLCategory() method..");
		logInfo("inside navigate2RLCategory() method.");
		driver().navigate().to(appUrl + "pyr_core/resources");
	    implicityWaits(10);
		WebElement newCategory = driver().findElement(ByLinkText.linkText("Manage Categories"));
		if(!newCategory.isDisplayed()){
			Assert.assertTrue(!newCategory.isDisplayed(), newCategory.getText().trim() + " link missing.");
		}
	}
	
	
	public void addPoductCategory(String prodCategory) throws Exception{
	
		// create a parent product category.
		System.out.println("inside the addParentCategory() method..");
		logInfo("inside addParentCategory() method.");
		
		// navigate to the Manage Categories page
		
		verifyLinkPresent("Manage Categories");
		clickOnLink("linkText", "Manage Categories");
		
		verifyLinkPresent("Add New Category");
		clickOnLink("linkText", "Add New Category");
		logInfo("clicked 'Add New Category' link in Manage Categories page.");
		
		// enter all the fields in new event calendar page.
		verifyElementPresent("cssSelector",inputAddNewCategory);
		inputText("cssSelector",inputAddNewCategory,prodCategory);
		
		WebElement e = driver().findElement(org.openqa.selenium.By.cssSelector(chkMarketAll));
		String isChecked = e.getAttribute("checked");
		if(isChecked.equalsIgnoreCase("false")){
		 selectRadioOrCheckbox("cssSelector",chkMarketAll);
		}
		
		implicityWaits(5);
		
		logInfo("Entered all the fields in Add/Edit Category page.");
		
		verifyElementPresent("cssSelector",btnCreateResourceCategory);
		clickOnButton("cssSelector",btnCreateResourceCategory);
		
		Thread.sleep(5000);
		
		logInfo("Clicked 'Create Resource Category' button in  Add/Edit Category page.");
		
	}

	
	public boolean verifyProductCategoryPresent(String prodCategory) throws Exception{
		System.out.println("inside verifyProductCategoryPresent() method.");
		logInfo("inside verifyProductCategoryPresent() method.");
		logInfo("Verifying " + prodCategory + " product category is present.");

		boolean isCategoryPresent = false;
	
		verifyElementPresent("xpath",productCategoryList);
				
		WebElement tblProdCategory = driver().findElement(org.openqa.selenium.By.xpath(productCategoryList));
		List allRows = tblProdCategory.findElements(ByTagName.tagName("tr"));
		System.out.println("No of rows in the product table = " + allRows.size());
		
		String before = "//*[@id='categories_list']/tbody/tr[";
		String after = "]/td[2]";
		for(int i=1;i<=allRows.size();i++){
			WebElement eprodName = driver().findElement(ByXPath.xpath(before+i+after));
			String prodName = eprodName.getText().trim();
			System.out.println("Product Name =" + prodName);
			
			if(prodName.equalsIgnoreCase(prodCategory.trim())){
				isCategoryPresent = true;
				break;
			} else {
				isCategoryPresent = false;
			}
		}
		return isCategoryPresent;
	}


	public void addNewResource(String prodCategory, String resourceType) throws Exception{
		logInfo("inside addNewResource() method.");
		System.out.println("inside addNewResource() method.");
		
		verifyLinkPresent("Add Resource");
		clickOnLink("linkText", "Add Resource");
		
		logInfo("clicked 'Add Resource' link in Resource Library page.");
		
		String fPath = projectPath + "\\testdata";
		
		// Enter all fields in the New Resource page.
		
		inputText("cssSelector",inputNewResourceTitle,newResourceTitle_text);
		inputText("cssSelector",inputNewResourceDescription,newResourceDescription_text);
		selectFromDropdown("cssSelector",drpdnNewResourceFileType,"byVisibleText",resourceType);
		selectFromDropdown("cssSelector",drpdnNewResourceStatus,"byVisibleText",newResourceStatus_text);
		inputTextClear("xpath",inputPublishDate);
		inputText("xpath",inputPublishDate,publishDate_text);
		
		// Upload .jpg file using Auto-IT script.
		
		clickOnButton("cssSelector",btnNewResourceFileBrowse);
		
		switch(resourceType){
		case "PDF" :
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_PDF.exe");
			Thread.sleep(10000);
			break;
		case "Text":
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Text.exe");
			Thread.sleep(10000);
			break;
		case "Document" :
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Doc.exe");
			Thread.sleep(10000);
			break;
		case "Spreadsheet" :
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Excel.exe");
			Thread.sleep(10000);
			break;
		case "ZIP" :
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Zip.exe");
			Thread.sleep(10000);
			break;
		case "Presentation" :
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_PPT.exe");
			Thread.sleep(10000);
			break;
		case "Image" :
			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Image.exe");
			Thread.sleep(10000);
			break;
		default :
			System.out.println("Invalid file type.");
			break;
		}
	
		// Select the last item in the product category (the recently ones gets added in the end).
		logInfo("selecting " + prodCategory);

		WebElement categoryTable = driver().findElement(ByXPath.xpath(tblNewResourceCategoryList));
		List allInputs = categoryTable.findElements(By.tagName("tr"));
		
		int lastIndx = allInputs.size();
		
		String beforeInput = "//*[@id='categories_list']/tbody/tr[";
		String afterInput = "]/td/input";
		WebElement input = driver().findElement(ByXPath.xpath(beforeInput+lastIndx+afterInput));
		input.click();
		
    	Thread.sleep(5000);
		clickOnButton("cssSelector",btnCreateResource);
	}
		
	
	public boolean verifyResourcePresent(String prodCategory, String resourceTitle) throws Exception{
		logInfo("inside verifyResourcePresent() method.");
		
		verifyElementPresent("xpath",drpdnSelectCategory);
		selectFromDropdown("xpath",drpdnSelectCategory,"byVisibleText",prodCategory);
		
		Thread.sleep(5000);
		
		WebElement tblResource = driver().findElement(By.xpath(tblResourceView));
		List allDivs = tblResource.findElements(By.tagName("div"));
		int count_divs = allDivs.size();
		System.out.println("Total divs = " +count_divs);
		
		boolean isResourcePresent = false;
		for(int i=1;i<=count_divs;i++){
			WebElement x = driver().findElement(ByXPath.xpath(resourceHeader));
			String title = x.getText().trim();
			if(title.equalsIgnoreCase(resourceTitle.trim())){
				System.out.println(resourceTitle + " match found in resource view page.");
				isResourcePresent = true;
				break;  	
			}
		}
		return isResourcePresent;
	}

	
	public void previewResource(String prodCategory, String resourceTitle) throws Exception{
		logInfo("inside previewResource() method.");
		
		verifyElementPresent("xpath",drpdnSelectCategory);
		selectFromDropdown("xpath",drpdnSelectCategory,"byVisibleText",prodCategory);
		
		Thread.sleep(5000);
		
		WebElement tblResource = driver().findElement(By.xpath(tblResourceView));
		List allDivs = tblResource.findElements(By.tagName("div"));
		int count_divs = allDivs.size();
		System.out.println("Total divs = " +count_divs);
		
		for(int i=1;i<=count_divs;i++){
			WebElement x = driver().findElement(ByXPath.xpath(resourceHeader));
			String title = x.getText().trim();
			if(title.equalsIgnoreCase(resourceTitle.trim())){
				System.out.println(resourceTitle + " match found in resource view page.");
				WebElement btn = driver().findElement(ByXPath.xpath(resourceBtn));
				btn.click();
			//	verifyLinkPresent("Preview");
				clickOnLink("linkText", "Preview");
				Thread.sleep(5000); 
				break;  	
			}
		}
	}
	
	public void closeResourcePreview() throws Exception{
		logInfo("inside closeResourcePreview() method.");
		verifyElementPresent("cssSelector",btnPreviewClose);
		clickOnElement("cssSelector",btnPreviewClose);
	}
	
	public void likeResource() throws Exception{
		logInfo("inside likeResource() method.");
		
		WebElement like = driver().findElement(By.cssSelector(elike));
		String likeCount = like.getText().trim();
		int likecount = Integer.parseInt(likeCount);
		logInfo("old like count = " +likecount);
		
		WebElement contentPane = driver().findElement(By.cssSelector(resourcePane));
		List<WebElement> allLinks = contentPane.findElements(By.tagName("a"));
		for(WebElement x : allLinks){
			String linkName = x.getText().trim();
			System.out.println("linkName = " +linkName);
			if(linkName.equalsIgnoreCase("Like")){
				x.click();
				logInfo("clicked on link " +linkName);
				System.out.println("clicked on link " +linkName);
				Thread.sleep(5000);
				break;
			}
		}
		
		WebElement newlike = driver().findElement(By.cssSelector(elike));
		String NewlikeCount = newlike.getText().trim();
		int newlikecount = Integer.parseInt(NewlikeCount);
		logInfo("new like count = " +newlikecount);
		
		if(newlikecount == likecount+1){
			logInfo("Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
			System.out.println("Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ").");
			Assert.assertTrue(newlikecount == likecount+1, "Likes(" + likecount + ") incremented sucessfully to Likes(" + newlikecount + ")." );
		} else {
			logInfo("Likes(" + likecount + ") could not be incremented to Likes(" + newlikecount + ").");
			System.out.println("Likes(" + likecount + ") could not be incremented to Likes(" + newlikecount + ").");
		}	Assert.assertTrue(newlikecount == likecount+1, "Likes(" + likecount + ") not incremented sucessfully to Likes(" + newlikecount + ")." );
	}
	

	public void shareResource() throws Exception{
		logInfo("inside shareResource() method.");
		
		WebElement share = driver().findElement(By.cssSelector(eShare));
		String sharesCount = share.getText().trim();
		int sharecount = Integer.parseInt(sharesCount);
		logInfo("old share count = " +sharecount);
		
		WebElement contentPane = driver().findElement(By.cssSelector(resourcePane));
		List<WebElement> allLinks = contentPane.findElements(By.tagName("a"));
		for(WebElement x : allLinks){
			String linkName = x.getText().trim();
			System.out.println("linkName = " +linkName);
			if(linkName.equalsIgnoreCase("Share")){
				x.click();
				logInfo("clicked on link " +linkName);
				System.out.println("clicked on link " +linkName);
				verifyElementPresent("cssSelector",btnResourceShare);
				clickOnButton("cssSelector",btnResourceShare);
				verifyElementPresent("cssSelector",txtareaShareComments);
				inputText("cssSelector",txtareaShareComments, shareComments_text);
				clickOnButton("xpath",btnCommentsShare);
				Thread.sleep(5000);
				verifyLinkPresent("Details");
				clickOnLink("linkText","Details");
				break;
			}
		}
		/*
		WebElement newshare = driver().findElement(By.cssSelector(eShare));
		String newShareCount = newshare.getText().trim();
		int newsharecount = Integer.parseInt(newShareCount);
		logInfo("new share count = " +newsharecount);
		
		if(newsharecount == sharecount+1){
			logInfo("Shares(" + sharecount  + ") incremented sucessfully to Shares(" + newsharecount + ").");
			System.out.println("Shares(" + sharecount  + ") incremented sucessfully to Shares(" + newsharecount + ").");
			// Assert.assertTrue(newsharecount == sharecount+1, "Shares(" + sharecount  + ") incremented sucessfully to Shares(" + newsharecount + ").");
		} else {
			logInfo("Shares(" + sharecount + ") could not be incremented to Shares(" + newsharecount + ").");
			System.out.println("Shares(" + sharecount + ") could not be incremented to Likes(" + newsharecount + ").");
		}	Assert.assertTrue(newsharecount == sharecount+1, "Shares(" + sharecount + ") not incremented sucessfully to Shares(" + newsharecount + ")." );
		*/
		clickOnButton("cssSelector",btnPreviewClose);
	}
	
	
	
	public void shareResource2FB() throws Exception{
		logInfo("inside shareResource2FB() method.");
		
		WebElement share = driver().findElement(By.cssSelector(eShare));
		String sharesCount = share.getText().trim();
		int sharecount = Integer.parseInt(sharesCount);
		logInfo("old share count = " +sharecount);
		
		WebElement contentPane = driver().findElement(By.cssSelector(resourcePane));
		List<WebElement> allLinks = contentPane.findElements(By.tagName("a"));
		for(WebElement x : allLinks){
			String linkName = x.getText().trim();
			System.out.println("linkName = " +linkName);
			if(linkName.equalsIgnoreCase("Share")){
				x.click();
				logInfo("clicked on link " +linkName);
				System.out.println("clicked on link " +linkName);
				waitOnElement("cssSelector",btnResourceShareSocialNetwork);
				clickOnButton("cssSelector",btnResourceShareSocialNetwork);
				
				waitOnElement("cssSelector",btnShareFacebook);
				clickOnElement("cssSelector",btnShareFacebook);
				Thread.sleep(10000);
				
				// Login to Facebook 
				
				String wndBeforeWindow = driver().getWindowHandle();
				System.out.println("Before window = " +wndBeforeWindow);
				
				for(String w : driver().getWindowHandles()){
					System.out.println("window = " +w);
					if(!w.equalsIgnoreCase(wndBeforeWindow)){
						driver().switchTo().window(w);
						System.out.println("inside window = " +w);
						waitOnElement("cssSelector",inputFBEmail);
						inputTextClear("cssSelector",inputFBEmail);
						inputText("cssSelector",inputFBEmail,fBEmail_text);
						inputText("cssSelector",inputFBPwd,fBPwd_text);
						
						clickOnButton("cssSelector",btnFBLogin);
						
						//waitOnElement("cssSelector",btnFBShareLink);
						clickOnButton("cssSelector",btnFBShareLink); 
						
						Thread.sleep(10000);
						driver().switchTo().window(wndBeforeWindow);
						break;
					}
				}
				break;
			}
		}
	}
	

	public void verifyResourceinCommunity(String resourceTitle) throws Exception{
		logInfo("inside verifyResourceinCommunity() method.");
		
		driver().navigate().to(appUrl + "community/wall");
		driver().navigate().refresh();
		Thread.sleep(5000);
		
		// String before_rescname = "//*[@id='widget-recent-activity']/div[2]/div[1]/div[";
		// String after_rescname = "]/div[1]/div[1]/p[2]/a";
		
		String before_rescname = "//div[@class='activities-stream']/div[";
		String after_rescname = "]/div/div[1]/p[2]/a";
		
		String before_hide = "//div[@class='activities-stream']/div[";
		String after_hide = "]/div/div[2]/span/span[4]/a";
				
		boolean isRescourceFound = false;
	
		for(int i=1;i<=10;i++){
				WebElement rescName = driver().findElement(By.xpath(before_rescname+i+after_rescname));
				String act_rescName = rescName.getText().trim();
				if(act_rescName.equalsIgnoreCase(resourceTitle)){
					logInfo(resourceTitle + " resource found in community page.");
					System.out.println(resourceTitle + " resource found in community page.");
					isRescourceFound = true;
					WebElement hide = driver().findElement(By.xpath(before_hide+i+after_hide));
					hide.click();
					logInfo("clicked on hide link for the resource " + resourceTitle);
					break;
				}
			}
	
	 if(isRescourceFound==false){
		 logInfo(resourceTitle + " resource not found in community page.");
		 System.out.println(resourceTitle + " resource not found in community page.");
		 Assert.assertTrue(isRescourceFound, resourceTitle + " resource not found in community page.");
	  }
	}
	

	public void emailResouce2WebSite() throws Exception{
		logInfo("inside emailResouce2WebSite() method.");
		WebElement contentPane = driver().findElement(By.cssSelector(resourcePane));
		List<WebElement> allLinks = contentPane.findElements(By.tagName("a"));
		for(WebElement x : allLinks){
			String linkName = x.getText().trim();
			System.out.println("linkName = " +linkName);
			if(linkName.equalsIgnoreCase("Email")){
				x.click();
				logInfo("clicked on link " +linkName);
				System.out.println("clicked on link " +linkName);			
				clickOnButton("cssSelector",btnCompose);
				
				String uName = prop.getLocatorForEnvironment(appUrl,"adminUser_text").trim();
				String[] parts = StringUtils.split(uName,".");
				String fName = parts[0];
				String lName = parts[1];
				String apUrl = appUrl;
				String[] urlparts = apUrl.split("/");
				String urlpart1 = urlparts[0];
				String urlpart2 = urlparts[2];
				String composeTo_text = fName+"."+lName+"@"+urlpart2;
			//	verifyElementPresent("cssSelector",inputComposeEmailTo);
			//	inputText("cssSelector",inputComposeEmailTo,composeTo_text);
				verifyElementPresent("xpath",inputComposeEmailTo);
				clickOnElement("xpath",inputComposeEmailTo);
				inputText("xpath",inputComposeEmailTo,composeTo_text);
				verifyElementPresent("cssSelector",inputComposeEmailSubject);
				inputText("cssSelector",inputComposeEmailSubject,composeEmailSubject_text);
				//	verifyLinkPresent("Send");
				clickOnLink("linkText","Send");
				logInfo("clicked on Send button of Compose Email page.");
				Thread.sleep(5000);
				break;
			}
		}	
	}
	
	
	public void deleteProductCategory(String pcategory) throws Exception{
		logInfo("inside deleteProductCategory() method.");
		
		driver().navigate().to(appUrl + "pyr_core/resource_categories");
		waitOnElement("xpath",productCategoryList);
		
		boolean isCategoryFound = false;
				
		WebElement tblProdCategory = driver().findElement(org.openqa.selenium.By.xpath(productCategoryList));
		List<WebElement> allRows = tblProdCategory.findElements(org.openqa.selenium.By.tagName("tr"));
		
		System.out.println("Total count of products = " +allRows.size());
		String before_resc = "//*[@id='categories_list']/tbody/tr[";
		String after_resc = "]/td[2]";
		
		String before_delete = "//*[@id='categories_list']/tbody/tr[";
		String after_delete = "]/td[3]/a[2]/i";
		
		for(int i=1;i<=allRows.size();i++){
			WebElement x = driver().findElement(By.xpath(before_resc+i+after_resc));
			String prod = x.getText().trim();
			if(prod.equalsIgnoreCase(pcategory)){
				isCategoryFound = true;
				System.out.println("i=" +i);
				System.out.println("category match found");
				WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
				delete.click();
				Thread.sleep(3000);
				confirmAlert();
				logInfo("clicked on the delete button for product " +parentCategory_text);
				Thread.sleep(5000);
				break;
			}
		}
		
		if(isCategoryFound= false){
			logInfo(parentCategory_text + " product category is not found.");
			Assert.assertTrue(isCategoryFound, parentCategory_text + " product category not found.");
		} 
	}
	
	
	 public void navigate2BusinessRL(){
		    System.out.println("inside the navigate2BusinessRL() method..");
			logInfo("inside navigate2BusinessRL() method.");
			driver().navigate().to(appUrl + "resource_library");
		}
	 
	 public void verifyCategoryInBusinessRL(String prodCategory) throws Exception{  
		 System.out.println("inside the verifyCategoryInBusinessRL() method..");
		 logInfo("inside verifyCategoryInBusinessRL() method.");
		 List allLi = driver().findElements(By.xpath(panelCategory));
		 int total_lis = allLi.size();
		 System.out.println("total resources =" +total_lis);
		 String before_li = "//*[@id='resource_library']/div/ul/li[";
		 String after_li = "]";  
		 String after_li1 = "]/a"; 
		 boolean isMatchFound = false;
		 
		 System.out.println("expCategoryName =" + prodCategory);
		 
		 for(int i=1;i<=total_lis;i++){
			 WebElement category = driver().findElement(By.xpath(before_li+i+after_li));
			 String categoryName = category.getText().trim();	
			 System.out.println("actCategoryName =" + categoryName);		 
			 if(categoryName.equalsIgnoreCase(prodCategory)){
				 System.out.println(categoryName + " category match found.");
				 isMatchFound = true;
				 WebElement category1 = driver().findElement(By.xpath(before_li+i+after_li1));
				 category1.click();
				 break;
			 }
		 }
	
		 if(isMatchFound==false){
			 System.out.println(prodCategory + " product category match not found in business Rl.");
			 logInfo(prodCategory + " product category match not found in business Rl.");
			 Assert.assertTrue(isMatchFound, prodCategory + " product category match not found in business Rl.");
		 }
	 }
	 
	
	 public void verifyResourceInBusinessRL(String resourceTitle) throws Exception{
		 System.out.println("inside the verifyResourceInBusinessRL() method..");
		 logInfo("inside verifyResourceInBusinessRL() method.");
	//	 inputTextClear("xpath",searchResource);
	// inputText("xpath",searchResource,resourceTitle);
	//	 Thread.sleep(5000);
		 waitOnElement("xpath",resourceBLPane);
		 
		 WebElement rescPane = driver().findElement(By.xpath(resourceBLPane));
		 List allRows = rescPane.findElements(By.tagName("div"));
		 int total_rows = allRows.size();
		 boolean isResourceFound = false;
		 
		 String before_header = "//*[@id='resource-manager-body']/div[1]/div[";
		 String after_header1 = "]/div[1]/div/div[3]/h4";
		 String after_header2 = "]/div[2]/div/div[3]/h4";
		 
		 if(total_rows>1){
			 for(int i=2;i<3;i++){			//i<=total_rows
				 WebElement header = driver().findElement(By.xpath(before_header+i+after_header1));
				 String headerName = header.getText().trim();
				 if(headerName.equalsIgnoreCase(resourceTitle)){
					 isResourceFound = true;
					 System.out.println(resourceTitle + " resource match found in business Rl.");
					 logInfo(resourceTitle + " resource match found in business Rl.");
					 break;
				 }
				 
				 WebElement header2 = driver().findElement(By.xpath(before_header+i+after_header2));
				 String headerName2 = header.getText().trim();
				 if(headerName2.equalsIgnoreCase(resourceTitle)){
					 isResourceFound = true;
					 System.out.println(resourceTitle + " resource match found in business Rl.");
					 logInfo(resourceTitle + " resource match found in business Rl.");
					 break;
				 }
			 }
		 }
		 
		 if(isResourceFound==false){
			 System.out.println(resourceTitle + " resource match not found in business Rl.");
			 logInfo(resourceTitle + " resource match not found in business Rl.");
			 Assert.assertTrue(isResourceFound, resourceTitle + " resource match not found in business Rl.");
		 }
	 }
	 
	 
		public void previewBusinessResource(String resourceTitle) throws Exception{
			logInfo("inside previewBusinessResource() method.");
			System.out.println("inside previewBusinessResource() method.");
			
			WebElement resPanel = driver().findElement(By.xpath(resourceBLPane));
			List allRows = resPanel.findElements(By.tagName("div"));
			int all_rows = allRows.size();
			String before_row = "//*[@id='resource-manager-body']/div/div[2]/div[";
			String after_row = "]/div/div[3]/h4";
			
			String before_toggle = "//*[@id='resource-manager-body']/div/div[2]/div[";
			String after_toggle = "]/div/div[1]/div[1]/button";
			
			String before_preview = "//*[@id='resource-manager-body']/div/div[2]/div[";
			String after_preview = "]/div/div[1]/div[1]/ul/li[1]";
			
			if(all_rows >0){
				for(int i=1;i<=all_rows;i++){
					WebElement e = driver().findElement(By.xpath(before_row+i+after_row));
					String resName = e.getText().trim();
					System.out.println("Res Name = " +resName);
					if(resName.equalsIgnoreCase(resourceTitle)){
						logInfo(resourceTitle + " match found in resource pane.");
						WebElement toggle = driver().findElement(By.xpath(before_toggle+i+after_toggle));
						toggle.click();
						clickOnLink("linkText","Preview");
						break;
					}
				}
			}
			
		}
		
		
		public boolean verifyResourcePreviewHeading(String resourceTitle) throws Exception{
			logInfo("inside verifyResourcePreviewHeading() method.");
			Thread.sleep(5000);
			WebElement header = driver().findElement(By.cssSelector(previewHeader));
			String headerName = header.getText().trim();
			System.out.println("name is "+ headerName);
			boolean isHeaderCorrect = false;
			if(headerName.equalsIgnoreCase(resourceTitle)){
				logInfo(resourceTitle + " matches the title in resource preview page.");
				isHeaderCorrect = true;
			}
			
			if(isHeaderCorrect==false){
				logInfo(resourceTitle + " does not matches the title in resource preview page.");
				Assert.assertTrue(isHeaderCorrect, " does not matches the title in resource preview page.");
				
			}
			return isHeaderCorrect;
		}
		
		
		
	
		
}
