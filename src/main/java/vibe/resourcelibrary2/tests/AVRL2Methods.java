package vibe.resourcelibrary2.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.interactions.Actions;

import common.TestBase;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.YouTube.tests.YoutubeMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.resourcelibrary.tests.RLMethods;
import vibe.shopping.tests.ShoppingMethods;
import vibe.shopping2.tests.Shopping2Methods;
import vibe.training.tests.TrainingMethods;
import vibe.training.tests.Training_Tests;

public class AVRL2Methods extends TestBase{
	
	RLMethods rl = new RLMethods();
	InboxMethods inbox = new InboxMethods();
	ShoppingMethods sm = new ShoppingMethods();
	CommunityMethods cm = new CommunityMethods();
	MyProfileMethods profile = new MyProfileMethods();	
	YoutubeMethods ym = new YoutubeMethods();
	TrainingMethods tm = new TrainingMethods();
	Shopping2Methods sm2 = new Shopping2Methods();
	
	public static String vibeRecipient_text =  getEmail(adminUser_text,appUrl);
	     
	  public void navigate2AdminRL() throws Exception{
			logInfo("inside navigate2AdminRL() method.");
			driver().navigate().to(appUrl + "pyr_core/resources");
			waitOnElement("linkText", "Manage Categories");
			clickOnLink("linkText", "Manage Categories");
	  }
		
	  
	  public void navigate2UserRL(){
		    System.out.println("inside the navigate2UserRL() method..");
			logInfo("inside navigate2BusinessRL() method.");
			driver().navigate().to(appUrl + "crm/resource_library");
		}
		
		public void addResourceCategory(String prodCategory, String subCategory) throws Exception{
			logInfo("inside addResourceCategory() method.");
			waitOnElement("linkText", "Manage Categories");
			clickOnLink("linkText", "Manage Categories");
			
			verifyLinkPresent("Add New Category");
			clickOnLink("linkText", "Add New Category");
			logInfo("clicked 'Add New Category' link in Manage Categories page.");

			verifyElementPresent("cssSelector",inputAddNewCategory);
		//implicityWaits(10);
			waitOnElement("cssSelector",inputAddNewCategory);
			inputText("cssSelector",inputAddNewCategory,prodCategory);
			
			selectFromDropdown("cssSelector",drpdnParentCategory,"byVisibleText",subCategory);
			WebElement e = driver().findElement(By.cssSelector(chkMarketAll));
			String isChecked = e.getAttribute("checked");
			if(isChecked.equalsIgnoreCase("false")){
			 selectRadioOrCheckbox("cssSelector",chkMarketAll);
			}			
			clickOnButton("cssSelector",btnCreateResourceCategory);	
			getText("cssSelector", backRL, "Ret is ");
			clickOnButton("cssSelector", backRL);
			clickOnLink("linkText","Back");
		}
		
		public void addCanadianResourceCategory(String prodCategory, String subCategory) throws Exception{
			logInfo("inside addCanadianResourceCategory() method.");
			System.out.println("inside addCanadianResourceCategory() method.");
			waitOnElement("linkText", "Manage Categories");
			clickOnLink("linkText", "Manage Categories");
			
			verifyLinkPresent("Add New Category");
			clickOnLink("linkText", "Add New Category");
			logInfo("clicked 'Add New Category' link in Manage Categories page.");
			verifyElementPresent("cssSelector",inputAddNewCategory);
			inputText("cssSelector",inputAddNewCategory,prodCategory);
			
			selectFromDropdown("cssSelector",drpdnParentCategory,"byVisibleText",subCategory);
			waitOnElement("cssSelector",chkMarketAll);
			WebElement e = driver().findElement(By.cssSelector(chkMarketAll));
			String isChecked = e.getAttribute("checked");
			if(isChecked.equalsIgnoreCase("true")){
				selectRadioOrCheckbox("cssSelector",chkMarketAll);
			}
			
			List<WebElement> markets = driver().findElements(By.xpath("//*[@id='market_language_checkbox']/div/span"));
			String before = "//*[@id='market_language_checkbox']/div/span[";
			String after = "]/label";
			
			String before_checkbox = "//*[@id='market_language_checkbox']/div/span[";
			String after_checkbox = "]/label/input";
			
			for(int i=1;i<=markets.size();i++){
				WebElement market = driver().findElement(By.xpath(before+i+after));
				if(market.getText().trim().equalsIgnoreCase("CA (English)")){
					WebElement chkCanadianMarket = driver().findElement(By.xpath(before_checkbox+i+after_checkbox));
					chkCanadianMarket.click();
					break;
				}
			}
			
			verifyElementPresent("cssSelector",btnCreateResourceCategory);
			clickOnButton("cssSelector",btnCreateResourceCategory);	
			getText("cssSelector", backRL, "Ret is ");
			clickOnButton("cssSelector", backRL);
			clickOnLink("linkText","Back");
		}

		
		public boolean verifyResourceCategoryPresent(String prodCategory) throws Exception{
			System.out.println("inside verifyResourceCategoryPresent() method.");
			logInfo("inside verifyResourceCategoryPresent() method.");
			
			driver().navigate().to(appUrl + "pyr_core/resource_categories"); // remove if required
			boolean isCategoryPresent = false;
			waitOnElement("cssSelector",".ic-icon-regular.ic-icon-add");
			List<WebElement> allPlus = driver().findElements(By.cssSelector(".ic-icon-regular.ic-icon-add"));
			for(WebElement x : allPlus){
				x.click();
			}
			
			System.out.println("No of plus in the product table = " + allPlus.size());
			
			List<WebElement> allRows = driver().findElements(By.cssSelector(".col-md-5"));
			System.out.println("No of rows in the product table = " + allRows.size());
			
			for(WebElement x : allRows){
				String prodName = x.getText().trim();
				System.out.println(prodName);
				if(prodName.equalsIgnoreCase(prodCategory)){
					System.out.println("product found " + prodName);
					isCategoryPresent = true;
					x.click();
				}
			}
		
			clickOnLink("linkText","Back");

			return isCategoryPresent;
		}

			

		public void addNewResource(String resName,String pCategory, String resStatus, boolean resPublish, boolean isResNewest,String fileType) throws Exception{
			logInfo("inside addNewResource() method.");
			System.out.println("inside addNewResource() method.");
			
			waitOnElement("linkText", "Add Resource");
			clickOnLink("linkText", "Add Resource");			
			inputText("cssSelector",inputNewResourceTitle,resName);			
			addResourceThumbnail();
			selectAssets(fileType);			
			inputText("xpath",inputResourceTags,resourceTagName);	
			clickOnLink("linkText","Next");		
			selectResourceCategory(pCategory);
			selectRankDefs();
			selectMarketlangs();
			selectSubscriptionPlans();			
			selectFromDropdown("cssSelector",drpdnNewResourceStatus,"byVisibleText",newResourceStatus_text);		

			if(resPublish==true){
				WebElement epub = driver().findElement(By.cssSelector(compPublishArticle));
				epub.click();
				clickOnElement("xpath",inputPublishDate);
				inputText("xpath",inputPublishDate,"12/12/2016");
				inputTextClear("cssSelector",inputExpiryDate);
				inputText("cssSelector",inputExpiryDate,expiryDate_text); 
				//clickOnElement("cssSelector","span.input-group-btn > button.btn.btn-default");
			}
			
			if(isResNewest==true){
				WebElement eresnew = driver().findElement(By.xpath(chkNewResourceDisplayAsNewest));
				boolean isresNewchked = eresnew.isSelected();
				System.out.println("is res new chked =" +isresNewchked);
				selectRadioOrCheckbox("xpath",chkNewResourceDisplayAsNewest);
				inputTextClear("xpath",inputDisplayAsNewest);
				inputText("xpath",inputDisplayAsNewest,displayAsNewest_text);
				
			}
			
			clickOnButton("cssSelector",btnCreateNewResource);
		}
		
		public void addNewResourceWithMultipleAssets(String resName,String pCategory,String resStatus, boolean resPublish, boolean isResNewest) throws Exception{
			logInfo("inside addNewResource() method.");
			System.out.println("inside addNewResource() method.");
			
			waitOnElement("linkText", "Add Resource");
			clickOnLink("linkText", "Add Resource");			
			inputText("cssSelector",inputNewResourceTitle,resName);			
			addResourceThumbnail();
			selectAssets("Video");
			/*clickOnLink("linkText","Next");	
			clickOnLink("linkText","Previous");	
			selectAssets("Text");*/
			
			/*selectAssets("Document");
			selectAssets("Spreadsheet");
			selectAssets("ZIP");
			selectAssets("Presentation");
			selectAssets("Image");
			selectAssets("Video");*/
			inputText("xpath",inputResourceTags,resourceTagName);	
			clickOnLink("linkText","Next");		
			selectResourceCategory(pCategory);
			selectRankDefs();
			selectMarketlangs();
			selectSubscriptionPlans();			
			selectFromDropdown("cssSelector",drpdnNewResourceStatus,"byVisibleText",newResourceStatus_text);		

			if(resPublish==true){
				WebElement epub = driver().findElement(By.cssSelector(compPublishArticle));
				epub.click();
				clickOnElement("xpath",inputPublishDate);
				inputText("xpath",inputPublishDate,"12/12/2016");
				inputTextClear("cssSelector",inputExpiryDate);
				inputText("cssSelector",inputExpiryDate,expiryDate_text);
				//clickOnElement("cssSelector","span.input-group-btn > button.btn.btn-default");
				
			}
			
			if(isResNewest==true){
				WebElement eresnew = driver().findElement(By.xpath(chkNewResourceDisplayAsNewest));
				boolean isresNewchked = eresnew.isSelected();
				System.out.println("is res new chked =" +isresNewchked);
				selectRadioOrCheckbox("xpath",chkNewResourceDisplayAsNewest);
				inputTextClear("xpath",inputDisplayAsNewest);
				inputText("xpath",inputDisplayAsNewest,displayAsNewest_text);
			}
			
			clickOnButton("cssSelector",btnCreateNewResource);
			
		}
		
		public void selectAssets(String assetType) throws Exception{
			logInfo("inside selectAssets() method.");
			switch(assetType){
				case "PDF" :
					System.out.println("PDF....");
					addAsset(resourceAsset,"PDF");
					break;
				case "Text":
					System.out.println("Text...");
					addAsset(resourceAsset,"Text");
					break;
				case "Document":
					addAsset(resourceAsset,"Document");
					break;
				case "Spreadsheet" :
					addAsset(resourceAsset,"Spreadsheet");
					break;
				case "ZIP" :
					addAsset(resourceAsset,"ZIP");
					break;
				case "Presentation" :
					addAsset(resourceAsset,"Presentation");
					break;
				case "Image" :
					addAsset(resourceAsset,"Image");
					break;
				case "Video" :
					addAsset(resourceAsset,"Video");
					break;
				case "Content Block" :
					addAsset(resourceAsset,"Content Block");
					break;
				default :
					addAsset(resourceAsset,"Text");
					break;
					}
			  }
		

		public void addAsset(String assetName, String assetType) throws Exception{
			logInfo("inside addAsset() method.");
			System.out.println("inside addAsset() method.");
			
			clickOnLink("partialLinkText","Upload Assets");
			waitOnElement("cssSelector",inputAssetTitle);
			inputText("cssSelector",inputAssetTitle, assetType + " " + resourceAsset);
			selectFromDropdown("cssSelector",drpdnAssetFileType,"byVisibleText",assetType);
		
			inputText("cssSelector",textareaAssetFileDesc, assetType + " asset description.");
			
			if(assetType.equalsIgnoreCase("Video")){
				clickOnElement("xpath",uploadVideoYoutube);
			}	
			
			switch(assetType){
			case "PDF" :
				uploadFile("PDF",inputAssetPath);
				break;
			case "Text":
				uploadFile("Text",inputAssetPath);
				break;
			case "Document" :
				uploadFile("Document",inputAssetPath);
				break;
			case "Spreadsheet" :
				uploadFile("Excel",inputAssetPath);
				break;
			case "ZIP" :
				uploadFile("Zip",inputAssetPath);
				break;
			case "Presentation" :
				uploadFile("PPT",inputAssetPath);
				break;
			case "Image" :
				uploadFile("Image",inputAssetPath);
				break;
			case "Video" :
				selectYoutubeVideo(youtubeTitle);
				break;
			case "Content Block" :
				composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new asset");
				break;
			default :
				System.out.println("Invalid file type.");
				break;
			}
			
			clickOnButton("cssSelector",btnAddAsset);
			waitOnElement("partialLinkText","Upload Assets");
		}
		public void selectYoutubeVideo(String title) throws Exception{
			logInfo("Inside selectYoutubeVideo() method...");
			waitOnElement("xpath",listYoutubeVideos);
			List<WebElement> rows = driver().findElements(By.xpath(listYoutubeVideos));
			for(int i=2;i<=rows.size()+1;i++){
				String before = "//*[@id='youtube-videos-create-modal']/div/div[1]/div[2]/table/tbody/tr[";
				String after = "]/td[2]";
				String selectVideo = "]/td[1]";
				WebElement el = driver().findElement(By.xpath(before+i+after));
				if(el.getText().trim().equalsIgnoreCase(title.trim())){
					WebElement video = driver().findElement(By.xpath(before+i+selectVideo));
					video.click();
					break;
				}
			}
		}
		
		
		public void addResourceThumbnail() throws Exception{
			logInfo("inside addResourceAsset() method.");
			
		
			uploadFile("Image",inputBrowseThumbnail);
		}
			
		
		// This method selects resource category in AddNewResource pane.
		
		public void selectResourceCategory(String resCategory) throws Exception{ 
			System.out.println("inside selectResourceCategory() method");
			waitOnElement("xpath","//*[@id='categories_list']");
			WebElement catPanel = driver().findElement(By.xpath("//*[@id='categories_list']"));	
			List<WebElement> allRows = catPanel.findElements(By.tagName("li"));
			int cntChecks = allRows.size();
			System.out.println("Total checks = " +cntChecks );			
			if(cntChecks>0){
					for(int i=1;i<=cntChecks;i++){
						WebElement e = driver().findElement(By.xpath("//*[@id='categories_list']/li["+i+"]"));
						String name = e.getText().trim();
							if(name.contains(resCategory)){						
							WebElement pinput = driver().findElement(By.xpath("//*[@id='categories_list']/li["+i+"]/input"));
							pinput.click();							
							List allInputs = e.findElements(By.tagName("input"));							
							System.out.println("i =" +i + ", inputs = " +allInputs.size());
								if(allInputs.size()>=2){
									for(int j=2;j<=allInputs.size();j++){
										WebElement ce = driver().findElement(By.xpath("//*[@id='categories_list']/li["+i+"]"));
										String cname = ce.getText().trim();
										System.out.println("cname =" +cname);											
										WebElement input = driver().findElement(By.xpath("//*[@id='categories_list']/li["+i+"]/ul/li/input"));
										input.click();
										}
									}
								break;
							}		
					    }
					}
				}		
		
		
		public void unselectInputs(String panel) throws Exception{
			
			waitOnElement("xpath",panel);
			WebElement rankPane = driver().findElement(By.xpath(panel));
			List<WebElement> allRanks = rankPane.findElements(By.tagName("span"));
			int cntRanks = allRanks.size();
			System.out.println("Total checks = " +cntRanks );
			
			String before_rankInput = panel +"/span[";
			String after_rankInput = "]/label/input";
						
			for(int i=1;i<=cntRanks;i++){
				WebElement input = driver().findElement(By.xpath(before_rankInput+i+after_rankInput));
				if(input.isSelected()){
					System.out.println("isSelected =" +input.isSelected());
					input.click(); 
				 } 
			}
		}
		
		public void unselectInputs1(String panel) throws Exception{
			
			waitOnElement("xpath",panel);
			List<WebElement> allRanks = driver().findElements(By.xpath(panel));
			
			int cntRanks = allRanks.size();
			System.out.println("Total checks = " +cntRanks );
			
			
			String before_rankInput = "//*[@id='rank_definition_checkboxes']/div[";
			String after_rankInput = "]/div[1]/input[2]";
			
			for(int i=1;i<=cntRanks;i++){
			WebElement input = driver().findElement(By.xpath(before_rankInput+i+after_rankInput));
			if(input.isSelected()){
				System.out.println("isSelected =" +input.isSelected());
				input.click(); 
			 } 
			}
		}

		public void selectRankDefs() throws Exception{
			System.out.println("inside selectRankDefs() method");
			
			unselectInputs1(divRankdeflist);
			
			List<WebElement> allRanks = driver().findElements(By.xpath("//*[@id='rank_definition_checkboxes']/div"));
			int cntRanks = allRanks.size();
			System.out.println("Total checks = " +cntRanks );
			
			String before_rankName = "//*[@id='rank_definition_checkboxes']/div[";
			String after_rankName = "]/div[1]";
			
			String before_rankInput = "//*[@id='rank_definition_checkboxes']/div[";
			String after_rankInput = "]/div[1]/input[2]";
			
			String before_startdate = "//*[@id='rank_definition_checkboxes']/div[";
			String after_startdate = "]/div[2]/div/div[2]/span/button";
			
			String before_enddate = "//*[@id='rank_definition_checkboxes']/div[";
			String after_enddate = "]/div[3]/div/div[2]/span/button";
			
			
			int rankList = avonRankDefList.length;
			System.out.println("list size =" +rankList);
			
			
			for(int i=1;i<=cntRanks;i++){
				for(int j=0;j<=rankList-1;j++){
				WebElement erank = driver().findElement(By.xpath(before_rankName+i+after_rankName));
				String rankName = erank.getText().trim();
				System.out.println("Rank Name = " +rankName);
				
				WebElement einput = driver().findElement(By.xpath(before_rankInput+i+after_rankInput));
				// String isSelected = einput.getAttribute("checked").trim();
				//System.out.println("status=" +status);
				if(rankName.equalsIgnoreCase(avonRankDefList[j]) ){  // && isSelected.equalsIgnoreCase("false")
					einput.click();
					WebElement startDate = driver().findElement(By.xpath(before_startdate+i+after_startdate));
					startDate.click();
					clickOnElement("cssSelector", datepickerClose);
					String endDatepart1 = "#pyr_core_resource_rank_definition_resources_attributes_";
					String endDatepart2 = "_end_date";
					int k = i-1;
					String totalEnddate = endDatepart1+k+endDatepart2;
					
					WebElement endDate = driver().findElement(By.cssSelector(totalEnddate));	
					endDate.sendKeys(expiryDate_text);
					
					
					break;
				}  
	    	  }
			}
		}
		
		public void selectMarketlangs() throws Exception{
			System.out.println("inside selectMarketlangs() method");
			
			// unselectInputs("//*[@id='market_language_checkboxes']/div");
			unselectInputs(divMarketlist);
			
			WebElement rankPane = driver().findElement(By.xpath("//*[@id='market_language_checkboxes']/div"));
			List<WebElement> allRanks = rankPane.findElements(By.tagName("span"));
			int cntRanks = allRanks.size();
			System.out.println("Total checks = " +cntRanks );
			
			String before_langName = "//*[@id='market_language_checkboxes']/div/span[";
			String after_langName = "]/label";
			
			String before_langInput = "//*[@id='market_language_checkboxes']/div/span[";
			String after_langInput = "]/label/input";
			
			int langList = marketslanglist.length;
			System.out.println("list size =" +langList);
			
			for(int i=1;i<=cntRanks;i++){
				for(int j=0;j<=langList-1;j++){
				WebElement erank = driver().findElement(By.xpath(before_langName+i+after_langName));
				String rankName = erank.getText().trim();
				System.out.println("Rank Name = " +rankName);
				
				WebElement einput = driver().findElement(By.xpath(before_langInput+i+after_langInput));
				// String isSelected = einput.getAttribute("checked").trim();
				//System.out.println("status=" +status);
				if(rankName.equalsIgnoreCase(marketslanglist[j]) ){  // && isSelected.equalsIgnoreCase("false")
					einput.click();
					break;
				}
			  }
			}
		}
		
		
		public void selectSubscriptionPlans() throws Exception{
			System.out.println("inside selectSubscriptionPlans() method");
			
			// unselectInputs("//*[@id='subscription_plan_checkboxes']/div");
			unselectInputs(divSubscriptionlist);
			
			WebElement rankPane = driver().findElement(By.xpath("//*[@id='subscription_plan_checkboxes']/div"));
			List<WebElement> allRanks = rankPane.findElements(By.tagName("span"));
			int cntRanks = allRanks.size();
			System.out.println("Total checks = " +cntRanks );
			
			String before_langName = "//*[@id='subscription_plan_checkboxes']/div/span[";
			String after_langName = "]/label";
			
			String before_langInput = "//*[@id='subscription_plan_checkboxes']/div/span[";
			String after_langInput = "]/label/input";
			
			int subList = avsubplanslist.length;
			System.out.println("list size =" +subList);
			
			for(int i=1;i<=cntRanks;i++){
				for(int j=0;j<=subList-1;j++){
				WebElement erank = driver().findElement(By.xpath(before_langName+i+after_langName));
				String rankName = erank.getText().trim();
				System.out.println("Rank Name = " +rankName);
				
				WebElement einput = driver().findElement(By.xpath(before_langInput+i+after_langInput));
				// String isSelected = einput.getAttribute("checked").trim();
				//System.out.println("status=" +status);
				if(rankName.equalsIgnoreCase(avsubplanslist[j]) ){  // && isSelected.equalsIgnoreCase("false")
					einput.click();
			     	break;
				}
			  }
			}
		}
		
		
		// Select category from the filter in RL Admin page.
		
		public void selectCategoryFilter(String categoryName) throws Exception, Exception{
			logInfo("inside selectResourceFilter() method to view the resource."); 
			
			waitOnElement("cssSelector",categoriesFilter);
			
			WebElement e = driver().findElement(By.cssSelector(categoriesFilter));
			e.click();
			
			WebElement panel = driver().findElement(By.xpath("//div[@id='categories_id_container']/ul"));
			List<WebElement>  allItems = panel.findElements(By.tagName("li"));
			int all_lis = allItems.size();
			System.out.println("Total Lis =" +all_lis);
			
			String before_liName = "//div[@id='categories_id_container']/ul/li[contains(@class,'dropdown-option')][";
			String after_liName = "]/a";
			
			for(int i=1;i<=all_lis;i++){
				WebElement liName = driver().findElement(By.xpath(before_liName+i+after_liName));
				String itemName = liName.getText().trim();
				if(itemName.equalsIgnoreCase(categoryName)){
					System.out.println("Resource Name =" +itemName);
					liName.click();
					break;
				}
			}
		}
		
		public void selectResourceStatus(String status) throws Exception, Exception{
			logInfo("inside selectStatusFilter() method."); 
			implicityWaits(3);
			waitOnElement("cssSelector",btnAllStatuses);
			WebElement e = driver().findElement(By.cssSelector(btnAllStatuses));
			e.click();
			
			WebElement panel = driver().findElement(By.xpath("//div[@id='status_container']/ul"));
			List<WebElement>  allItems = panel.findElements(By.tagName("li"));
			int all_lis = allItems.size();
			System.out.println("Total Lis =" +all_lis);
			
			String before_liName = "//div[@id='status_container']/ul/li[";
			String after_liName = "]";
			
			for(int i=3;i<=all_lis;i++){
				WebElement liName = driver().findElement(By.xpath(before_liName+i+after_liName));
				String resStatus = liName.getText().trim();
				if(resStatus.equalsIgnoreCase(status)){
					System.out.println("Resource Status =" +resStatus);
					liName.click();
				}
			}
		}
		
		public void deleteResourceAdmin(String resName) throws Exception, Exception{
			logInfo("inside deleteResourceAdmin() method.");
			System.out.println("inside deleteResourceAdmin() method.");
			waitOnElement("cssSelector","i.ic-icon-regular.ic-icon-list");
			clickOnElement("cssSelector","i.ic-icon-regular.ic-icon-list");
			
			WebElement tblRes = driver().findElement(By.xpath("//*[@id='resource-table']/tbody"));
			List allRows = tblRes.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			
			String before_name = "//*[@id='resource-table']/tbody/tr[";
			String after_name = "]/td[2]/a";
			
			String before_btn = "//*[@id='resource-table']/tbody/tr[";
			String after_btn = "]/td[7]/div/div/div/button";
			
			String before_delete = "//*[@id='resource-table']/tbody/tr[";
			String after_delete = "]/td[7]/div/div/div/ul/li[3]/a";
			
			for(int i=1;i<=all_rows;i++){
				WebElement n = driver().findElement(By.xpath(before_name+i+after_name));
				String name = n.getText().trim();
				if(name.equalsIgnoreCase(resName)){
					WebElement btn = driver().findElement(By.xpath(before_btn+i+after_btn));
					btn.click();
					waitOnElement("xpath",before_delete+i+after_delete);
					WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
					delete.click();
					confirmOK();
					break;
				}
				i=i+2;
			}
		}
		
		public boolean deleteMultipleResources(String[] resourcesToDelete) throws Exception{
			logInfo("Inside deleteMultipleResources() method.");
			boolean isDeleteResources = false;
			List<WebElement> li = driver().findElements(By.xpath("//*[@id='index_page']/div/div/div"));
			int resBeforeDelete = li.size();
			String before_res = "//*[@id='index_page']/div[1]/div/div[";
			String after_res = "]/div/div[4]/div[1]";
			
			String before_check = "//*[@id='index_page']/div[1]/div/div[";
			String after_check = "]/div/div[2]/input";
			int cnt = 0;
			
			for(int i=1; i<=resBeforeDelete; i++){
				WebElement el = driver().findElement(By.xpath(before_res+i+after_res));
				String resName = el.getText().trim();
				if(resName.contains(resourcesToDelete[cnt])){
					driver().findElement(By.xpath(before_check+i+after_check)).click();
					isDeleteResources = true;
					cnt++;
					if(cnt==3)
						break;
				}
			}
			
			if(isDeleteResources){
				waitOnElement("linkText","Delete Selected");
				clickOnElement("linkText","Delete Selected");
				waitOnElement("xpath", confirmDeleteMulResources);
				clickOnElement("xpath", confirmDeleteMulResources);
			}
			return isDeleteResources;
		}
		
		public boolean moveResource2Category(String resName,String categoryToMove) throws Exception{
			logInfo("Inside moveResource() method.");
			boolean isResourceSelected = false;
			boolean isResourceMoved = false;
			
			List<WebElement> rows = driver().findElements(By.xpath(resourceRowsList));
			int rowsCnt = rows.size();
			
		
			String before_res_row = "//*[@id='index_page']/div[";
			String res_row = "]/div/div[";
			String after_res_row = "]/div/div[4]/div[1]";
			
			String before_check = "//*[@id='index_page']/div[";
			String res_check = "]/div/div[";
			String after_check = "]/div/div[2]/input";
						
			for(int i=1; i<=rowsCnt; i++){
				List<WebElement> cols = driver().findElements(By.xpath("//*[@id='index_page']/div["+i+"]/div/div/div"));
				
				for(int j=1;j<=cols.size();j++){
					WebElement el = driver().findElement(By.xpath(before_res_row+i+res_row+j+after_res_row));
					String resourceName = el.getText().trim();
					if(resourceName.equalsIgnoreCase(resName.trim())){
						waitOnElement("xpath",before_check+i+res_check+j+after_check);
						driver().findElement(By.xpath(before_check+i+res_check+j+after_check)).click();
						isResourceSelected = true;
						break;
					}
				}
				if(isResourceSelected){
					break;
				}
				
			}
			if(isResourceSelected){
				clickOnButton("xpath", btnMoveSelectedResource);
				List<WebElement> categories = driver().findElements(By.xpath(lstCategories));
				String before = "//*[@class='modal-body']/div/div[2]/ul/li[";
				String after = "]";
				
				String before_sub_cat = "//*[@class='modal-body']/div/div[2]/ul/ul[";
				String after_sub_cat = "]";
				
				String before_cat_check = "//*[@class='modal-body']/div/div[2]/ul/li[";
				String after_cat_check = "]/input";
				
				String before_sub_cat_check = "//*[@class='modal-body']/div/div[2]/ul/ul[";
				String after_sub_cat_check = "]/li/input";
				
				for(int i=1;i<=categories.size();i++){
					waitOnElement("xpath",before+i+after);
					WebElement el = driver().findElement(By.xpath(before+i+after));
					String category = el.getText().trim();
					if(category.contains(categoryToMove.trim())){
						isResourceMoved = true;
						waitOnElement("xpath",before_cat_check+i+after_cat_check);
						WebElement cate = driver().findElement(By.xpath(before_cat_check+i+after_cat_check));
						cate.click();
						break;
					}
				}
				if(!isResourceMoved){
					for(int i=1;i<=categories.size();i++){
						waitOnElement("xpath",before_sub_cat+i+after_sub_cat);
						WebElement el = driver().findElement(By.xpath(before_sub_cat+i+after_sub_cat));
						String category = el.getText().trim();
						if(category.contains(categoryToMove.trim())){
							isResourceMoved = true;
							waitOnElement("xpath",before_sub_cat_check+i+after_sub_cat_check);
							WebElement cate = driver().findElement(By.xpath(before_sub_cat_check+i+after_sub_cat_check));
							cate.click();
							break;
						}
					}
				}
				
				clickOnButton("xpath",btnMove);
				confirmationMessage("Resources was moved successfully.");
				
				waitOnElement("cssSelector","div > #categories_id_container > button > span.button-drop-down-display-text");
				WebElement cat = driver().findElement(By.cssSelector("div > #categories_id_container > button > span.button-drop-down-display-text"));
				if(!cat.getText().trim().contains("All Categories")){
					Assert.assertTrue(false, "Unable to reset the categories drop down to default selection, after moving resources.");
				}
			
				
			}
			
			return isResourceMoved;
		}
		
		// Verify if resource is present in RL Admin page.
		
		public boolean selectResource(String resource) throws Exception, Exception{
			logInfo("inside selectResource() method to view the resource."); 
			boolean isResourcePresent = false;	
			waitOnElement("xpath","//*[@id='index_page']/div/div");
			List<WebElement> rows = driver().findElements(By.xpath("//*[@id='index_page']/div/div"));
			int rowsCnt = rows.size();
					
			String before_res_row = "//*[@id='index_page']/div[";
			String res_row = "]/div/div[";
			String after_res_row = "]/div[1]/div[4]/div[1]";
			
			String before_view_file = "//*[@id='index_page']/div[";
			String view_file = "]/div/div[";
			String after_view_file = "]/div[1]/div[3]/div/div";
			
			for(int i=1; i<=rowsCnt; i++){
				
				waitOnElement("xpath","//*[@id='index_page']/div["+i+"]/div/div");
				List<WebElement> cols = driver().findElements(By.xpath("//*[@id='index_page']/div["+i+"]/div/div"));
								
				for(int j=1;j<=cols.size();j++){
					waitOnElement("xpath",before_res_row+i+res_row+j+after_res_row);
					WebElement res = driver().findElement(By.xpath(before_res_row+i+res_row+j+after_res_row));
					if (res.getText().equalsIgnoreCase(resource)){	
						isResourcePresent = true;
						String viewRes = before_view_file+i+view_file+j+after_view_file;
//						waitOnElement("xpath",viewRes);
						WebElement viewFile = driver().findElement(By.xpath(viewRes));
						viewFile.click();
						break;
					 }
				}
				if(isResourcePresent){
					break;
				}

			}
			return isResourcePresent;
			
			
	  }
		
		/*public boolean selectResource(String resource) throws Exception, Exception{
			logInfo("inside selectResource() method to view the resource."); 
			boolean isResourcePresnt = false;			
			List <WebElement> cc = driver().findElements(By.cssSelector(resourceItems));  //resourceItems   resourceItemsHeader
			System.out.println("Resouce "+ cc.size()); 
			
			String resBefore = "//*[@id='index_page']/div[1]/div/div[";
			String resAfter = "]/div/div[4]/div[1]";
			
			String before = "//*[@id='index_page']/div[1]/div/div[";
			String after = "]/div/div[3]/div/div";
			
			for(int i=1;i<=cc.size();i++){
				waitOnElement("xpath",resBefore+i+resAfter);
				String res = driver().findElement(By.xpath(resBefore+i+resAfter)).getText().trim();
				if(res.contains(resource)){
					isResourcePresnt=  true;
					waitOnElement("xpath",before+i+after);
					WebElement e = driver().findElement(By.xpath(before+i+after));
					e.click();
					break;
				}
			}
		return isResourcePresnt;
	  }*/

		public void verifyAssetInFB(String parentCat ,String Resource ) throws Exception{		
			selectcontrols("Details");
			waitOnElement("cssSelector",assetTitle);
			WebElement tit = driver().findElement(By.cssSelector(assetTitle));
			String assettitleOfRL = tit.getText();		
			
			waitOnElement("cssSelector",statisItem);
			List  <WebElement> ct = driver().findElements(By.cssSelector(statisItem));
			System.out.println(ct.size());
			for(int i=1 ; i<=ct.size(); i++){			
				WebElement item = driver().findElement(By.cssSelector(statisItem1+i+statisItem2));			
				if (item.getText().equalsIgnoreCase("Shares")){
					WebElement countbefore = driver().findElement(By.cssSelector(statisCount+i));
					String b4Share = countbefore.getText();
					int beforeShare = Integer.parseInt(b4Share);
					int afterShared = beforeShare+1;
					String afterSharedCount = Integer.toString(afterShared);
			
					selectcontrols("Share");
				    shareToSocial();
				    selectFBIcon();
				    sm.shareInFaceBook();
				    closeModalWindow();	
				    nav2marketBusinessRL();
				    selectCategoryFilter(parentCat);
				    selectResource(Resource);
				    selectcontrols("Details");
				    WebElement getcountLike = driver().findElement(By.cssSelector(statisCount+"4"));			
					System.out.println("count size for Shared is  "+b4Share);
					System.out.println("after Shared " + getcountLike.getText());
					Assert.assertEquals(getcountLike.getText(), afterSharedCount);	
				    break;
		    
				   /* profile.login2FBVerifyPostedDetails();  
					getPostsFromFB(assettitleOfRL) ; */
			
				}	
			}	
		}

		public void getPostsFromFB(String RLPost) throws Exception{	    
	 		  logInfo("Verify the Vibe shared RL in FaceBook");
	 		 boolean isSharedPostPresent=false;
	    	 List <WebElement> post = driver().findElements(By.cssSelector(fbPostTitle));
	    	 System.out.println("size is "+post.size());
	    	 
	    	 for (WebElement pp: post){	    		 
	    		 if (pp.getText().contains(RLPost)){
	    			 System.out.println("after matching  is "+pp.getText());
	    			 isSharedPostPresent = true;
	    			 break;
	    		 }   		 
	    	 } if (isSharedPostPresent ==false){
	    		 
	    		 System.err.println (RLPost+ " is not avaiable in FB");
	    		 Assert.assertTrue(isSharedPostPresent, RLPost + " is not avaiable in FB");
	    		 
	    	 }	
	    	 
	    	 clickOnElement("xpath", fbLogout); 	
				
			}
		
		
		// Resource Table view - admin
		
		public void selectResource(String resName, String mode) throws Exception, Exception{
			logInfo("inside selectResource() method.");
			System.out.println("inside selectResource() method.");
			
			waitOnElement("cssSelector","i.ic-icon-regular.ic-icon-list");
			clickOnElement("cssSelector","i.ic-icon-regular.ic-icon-list");

			//selectResourceFilter(parentCategory_text);
			waitOnElement("xpath","//*[@id='resource-table']/tbody");
			WebElement tblRes = driver().findElement(By.xpath("//*[@id='resource-table']/tbody"));
			List allRows = tblRes.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			System.out.println( "Resource size is "+all_rows);
			
			String before_name = "//*[@id='resource-table']/tbody/tr[";
			String after_name = "]/td[3]/a";
			
			String before_btn = "//*[@id='resource-table']/tbody/tr[";
			String after_btn = "]/td[7]/div/div/div/button";
			
			String before_preview = "//*[@id='resource-table']/tbody/tr[";
			String after_preview = "]/td[7]/div/div/div/ul/li[1]";
			
			String before_edit = "//*[@id='resource-table']/tbody/tr[";
			String after_edit = "]/td[7]/div/div/div/ul/li[2]";
			
			String before_delete = "//*[@id='resource-table']/tbody/tr[";
			String after_delete = "]/td[7]/div/div/div/ul/li[3]";
			
			String before_clone = "//*[@id='resource-table']/tbody/tr[";
			String after_clone = "]/td[7]/div/div/div/ul/li[4]";
			
			for(int i=1;i<=all_rows;i++){				
				WebElement n = driver().findElement(By.xpath(before_name+i+after_name));
				String name = n.getText().trim();
				System.out.println("Resource name "+name);
						
				if(name.equalsIgnoreCase(resName)){
					WebElement btn = driver().findElement(By.xpath(before_btn+i+after_btn));
					btn.click();
					switch(mode){
					case "Preview" :
						WebElement preview = driver().findElement(By.xpath(before_preview+i+after_preview));
						preview.click();
						break;
					case "Edit" :
						WebElement edit = driver().findElement(By.xpath(before_edit+i+after_edit));
						edit.click();
						// addMoreAssets();
						editResourceTitle();
						break;
					case "Delete" :
						WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
						delete.click();
						confirmOK();
						break;	
					case "Clone" :
						WebElement clone = driver().findElement(By.xpath(before_clone+i+after_clone));
						clone.click();
						cloneResource();
						break;	
					default :
						System.out.println("Invalid mode.");
						break;
				 }
				
				}
			}
		}
		
		
		public void addMoreAssets() throws Exception{
			logInfo("inside addMoreAssets() method.");
			selectAssets("Text");
			clickOnLink("linkText","Next");
			clickOnButton("cssSelector",btnCreateNewResource);
		}
		
		public void editResourceTitle() throws Exception{
			logInfo("inside editResourceTitle() method.");
			inputTextClear("cssSelector",inputNewResourceTitle);
			inputText("cssSelector",inputNewResourceTitle,newResourceTitleUpdated_text);
			clickOnLink("linkText","Next");
			clickOnButton("cssSelector",btnCreateNewResource);
		}
		
		public void cloneResource() throws Exception{
			logInfo("inside cloneResource() method.");
			inputTextClear("cssSelector",inputNewResourceTitle);
			inputText("cssSelector",inputNewResourceTitle,newCloneResourceTitle_text);
			clickOnLink("linkText","Next");
			clickOnButton("cssSelector",btnCreateNewResource);
			logInfo("clicked on update resource method.");
		}
		

		public boolean verifyResourcePresentInAdmin(String prodCategory, String resTitle) throws Exception{
			logInfo("inside verifyResourcePresentInAdmin() method.");
			System.out.println("inside verifyResourcePresentInAdmin() method.");
			boolean isResourcePresent =false;
			
			/*waitOnElement("cssSelector","i.ic-icon-regular.ic-icon-list");
			clickOnElement("cssSelector","i.ic-icon-regular.ic-icon-list");*/
			
			selectCategoryFilter(prodCategory);
			WebElement el = driver().findElement(By.xpath("//*[@id='resource-body-content']/div[1]"));
			String emptyList = el.getText().trim();
			if(!emptyList.equalsIgnoreCase("No Records Found")){
				WebElement tblRes = driver().findElement(By.xpath("//*[@id='resource-table']/tbody"));
				List allRows = tblRes.findElements(By.tagName("tr"));
				int all_rows = allRows.size();
				System.out.println(all_rows+ " EEEEEEEEEEEEE");
				
				String before_name = "//*[@id='resource-table']/tbody/tr[";
				String after_name = "]/td[2]/a";

				for(int i=1;i<=all_rows;i++){
					
					WebElement n = driver().findElement(By.xpath(before_name+i+after_name));
					String resName = n.getText().trim();
					System.out.println("Resource name " + resName);
					
					waitOnElement("xpath",before_name+i+after_name);
					
					
					if(resName.equalsIgnoreCase(resTitle)){
						isResourcePresent = true;
						logInfo(resTitle + " resource is present in admin RL.");
						break;
					}
				}
			}
			return isResourcePresent;
		}

		
		public void editProductCategory(String pcategory) throws Exception{
			logInfo("inside deleteProductCategory() method.");
			System.out.println("----------1");
			driver().navigate().to(appUrl + "pyr_core/resource_categories");
			// waitOnElement("xpath",productCategoryList);
			
			boolean isCategoryFound = false;
			
			List<WebElement> allPlus = driver().findElements(By.cssSelector(".ic-icon-regular.ic-icon-add"));
			for(WebElement x : allPlus){
				x.click();
			}
							
			WebElement tblResource = driver().findElement(By.xpath("//*[@id='index_page']/div[2]"));
			List parentDivs = tblResource.findElements(By.className("col-md-10"));
			int parent_divs = parentDivs.size();
			System.out.println("Total divs = " +parent_divs);
			
			String before_pname = "//*[@id='index_page']/div[2]/div[";
			String after_pname = "]/div[2]";
			
			String before_btn = "//*[@id='index_page']/div[2]/div[";
			String after_btn = "]/div[4]/div/button";
			
			String before_edit = "//*[@id='index_page']/div[2]/div[";
			String after_edit = "]/div[4]/div/ul/li[1]";
			
			for(int i=2;i<=parent_divs;i++){
				WebElement e = driver().findElement(By.xpath(before_pname+i+after_pname));
				String name = e.getText().trim();
				System.out.println("pname =" +name);
				if(pcategory.equalsIgnoreCase(name)){
					WebElement btn = driver().findElement(By.xpath(before_btn+i+after_btn));
					btn.click();
					
					WebElement edit = driver().findElement(By.xpath(before_edit+i+after_edit));
					edit.click();
					
					verifyElementPresent("cssSelector",inputAddNewCategory);
					inputTextClear("cssSelector",inputAddNewCategory);
					inputText("cssSelector",inputAddNewCategory,parentCategoryUpdated_text);

					clickOnButton("cssSelector",btnCreateResourceCategory);
					waitOnElement("xpath",tblManageCategories);
					
					break;
				}
			}
		}
		
		
		public void deleteProductCategory(String pcategory) throws Exception{
			logInfo("inside deleteProductCategory() method.");
			
			driver().navigate().to(appUrl + "pyr_core/resource_categories");
			waitOnElement("xpath",productCategoryList);
			
			boolean isCategoryFound = false;
			
			List<WebElement> allPlus = driver().findElements(By.cssSelector(".ic-icon-regular.ic-icon-add"));
			for(WebElement x : allPlus){
				x.click();
			}
							
			WebElement tblResource = driver().findElement(By.xpath("//*[@id='index_page']/div[2]"));
			List parentDivs = tblResource.findElements(By.className("col-md-10"));
			int parent_divs = parentDivs.size();
			System.out.println("Total divs = " +parent_divs);
			
			String before_pname = "//*[@id='index_page']/div[2]/div[";
			String after_pname = "]/div[2]";
			
			String before_btn = "//*[@id='index_page']/div[2]/div[";
			String after_btn = "]/div[4]/div/button";
			
			String before_delete = "//*[@id='index_page']/div[2]/div[";
			String after_delete = "]/div[4]/div/ul/li[2]";
			
			for(int i=2;i<=parent_divs;i++){
				WebElement e = driver().findElement(By.xpath(before_pname+i+after_pname));
				String name = e.getText().trim();
				System.out.println("pname =" +name);
				if(pcategory.equalsIgnoreCase(name)){
					WebElement btn = driver().findElement(By.xpath(before_btn+i+after_btn));
					btn.click();
					waitOnElement("xpath",before_delete+i+after_delete);
					WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
					delete.click();
					/*WebDriverWait wait = (WebDriverWait) (new WebDriverWait(driver, 10)).until(ExpectedConditions.alertIsPresent());
					driver().switchTo().alert().accept();*/
					
                    Alert alert = driver().switchTo().alert();
                    alert.accept();
					
					//confirmEventDeleteModal();
					break;
				}
			}
		}
		
		public boolean verifyResourcePreviewHeading(String resourceTitle) throws Exception{
			logInfo("inside verifyResourcePreviewHeading() method.");
			waitOnElement("cssSelector",previewHeader);
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
		
	
	// ============================================================================   
	
	public boolean verifyResource(String resourceName) throws Exception, Exception{
		logInfo("inside verifyResource() method to view the resource."); 
		boolean isResourcePresnt = false;			
		List <WebElement> cc = driver().findElements(By.cssSelector(resourceItems));  //resourceItems   resourceItemsHeader
		System.out.println("Resouce "+ cc.size()); 
		
		String resBefore = "//*[@id='index_page']/div[1]/div/div[";
		String resAfter = "]/div/div[3]/div[1]";
		
		String before = ".//*[@id='index_page']/div[1]/div/div[";
		String after = "]/div/div[2]/div/div";
		
		for(int i=1;i<=cc.size();i++){
			String res = driver().findElement(By.xpath(resBefore+i+resAfter)).getText().trim();
			if(res.contains(resourceName)){
				isResourcePresnt=  true;
				WebElement e = driver().findElement(By.xpath(before+i+after));
				e.click();
				break;
			}
		}
	return isResourcePresnt;
	}
	
	
	
	public boolean downloadAssetForResource(String resourceName) throws Exception, Exception{
		logInfo("Inside downloadAssetForResource() method.");
		boolean isDownloadLinkFound = false;
		
		previewResource(resourceName);
		
		 waitOnElement("cssSelector", resLink);
		 List<WebElement> resLinks = driver().findElements(By.cssSelector(resLink));
		 System.out.println("links size"+ resLinks.size());
		 for(WebElement link : resLinks){
			 System.out.println("link"+ link.getText());
			 if(link.getText().equalsIgnoreCase(txtDownload)){
				 isDownloadLinkFound = true;
				 link.click();				 
				 break;
			 }
		 }
		 
		 if(verifyFileExistsOnDisk(filepath+"icentris_pdf.pdf")){
	            isDownloadLinkFound = true;
	            logInfo(filepath+"icentris_pdf.pdf" + " file exists.");
	        }
	     		
		return isDownloadLinkFound;
	}
	
	public int verifyDownloadCount(String resourceName) throws Exception, Exception{
		logInfo("Inside verifyDownloadCount() method.");
		int count = 0;
		List <WebElement> resources = driver().findElements(By.cssSelector(resourceItems));
		for (WebElement res : resources ){
			System.out.println(res.getText());
			 if (res.getText().equals(resourceName)){
				 res.click();
				 break;
				 
				 
			 }
		}		
		
		waitOnElement("xpath", resourceActionsCnt);
		 List<WebElement> actionLinks = driver().findElements(By.xpath(resourceActionsCnt));
		 String before = "//*[@id='page']/div[7]/div/div/div[2]/div[2]/div[2]/div[1]/section[2]/div/span[";
		 String after = "]/div";
		 for(int i=1; i<=actionLinks.size(); i++){
			 WebElement action = driver().findElement(By.xpath(before+i+after));
			 WebElement txtAction = driver().findElement(By.xpath(before+i+"]/span"));
			 if(txtAction.getText().equalsIgnoreCase(actionDownload)){
				 count = Integer.parseInt(action.getText());
				 System.out.println("The download count is : "+count);
				 break;
			 }
			 
		 }
		return count;
	}
	
	public boolean searchResource(String resourceText) throws Exception{
		logInfo("Inside searchResource() method.");
		boolean isResourceFound = false;
		waitOnElement("xpath", txtSearchResource);
		inputTextClear("xpath", txtSearchResource);
		inputText("xpath", txtSearchResource, resourceText);
		implicityWaits(5);
		List <WebElement> resources = driver().findElements(By.cssSelector(resourceItems));
		for (WebElement res : resources ){
			 if (res.getText().contains(resourceText)){
				 isResourceFound = true;
			 }
		}
		return isResourceFound;
	}
	
	public boolean searchKeywordResource(String resourceText) throws Exception{
		logInfo("Inside searchResource() method.");
		boolean isResourceFound = false;
		waitOnElement("xpath", btnKeywordSearch);
		clickOnElement("xpath", btnKeywordSearch);
		implicityWaits(5);
		List <WebElement> resources = driver().findElements(By.xpath(lstKeywordSearchItems));
		String before = "//*[@id='keyword_search_report']/div[1]/table/tbody/tr[";
		String after = "]/td[1]";
		for (int i=1;i<=resources.size();i++){
			WebElement res = driver().findElement(By.xpath(before+i+after));
			 if (res.getText().contains(resourceText)){
				 isResourceFound = true;
				 break;
			 }
		}
		return isResourceFound;
	}
	
	public boolean filterResource(String resourceName,String filterBy) throws Exception{
		logInfo("Inside searchResource() method.");
		boolean isResourceFound = false;
		waitOnElement("xpath",ddlFilterType);
		driver().findElement(By.xpath(ddlFilterType)).click();
		List<WebElement> filterTypes = driver().findElements(By.xpath(filterResourceTypes));
		String before = "//*[@id='filter_by_container']/ul/li[@class='dropdown-option '][";
		String after = "]/a";
		for(int i=1; i<=filterTypes.size(); i++){
			System.out.println(filterTypes.size());
			 WebElement filterType = driver().findElement(By.xpath(before+i+after));
			 System.out.println(filterType.getText());
			 if(filterType.getText().equalsIgnoreCase(filterBy)){ 
				filterType.click();
				waitOnElement("cssSelector",resourceItems);
				List <WebElement> resources = driver().findElements(By.cssSelector(resourceItems));
				for (WebElement res : resources ){
					 if (res.getText().equalsIgnoreCase(resourceName)){
						 isResourceFound = true;
						 break;
					 }
				}
			 }
		}
		return isResourceFound;
	}
	
	public boolean displayResourceInListView(String resourceName) throws Exception{
		logInfo("Inside displayResourceInListView() method.");
		boolean isResourceFound = false;
		waitOnElement("xpath",btnListView);
		driver().findElement(By.xpath(btnListView)).click();
		waitOnElement("xpath",listResources);
		List<WebElement> resources = driver().findElements(By.xpath(listResources));
		String before =  "//*[@id='index_page']/div[1]/ul/li/ul[";// "//*[@id='index_page']/div[1]/table/tbody["; branch-1-10
		String after =  "]/li[2]/a"; // "]/tr/td[2]/a";
		/*int i=1;
		while(i<=resources.size()+2){*/
		for(int i=1;i<=resources.size();i++){
			waitOnElement("xpath",before+i+after);
			 WebElement res = driver().findElement(By.xpath(before+i+after));
			 if (res.getText().trim().equalsIgnoreCase(resourceName)){				 
				 isResourceFound = true;
				 break;
			 }
			 //i=i+2;
		}		 
		return isResourceFound;
	}
	
	public boolean previewResource(String resourceName) throws Exception{
		logInfo("Inside previewResource() method.");
		Thread.sleep(5000);
		waitOnElement("xpath","//*[@id='index_page']/div/div");		
		
		boolean isResourceFound = false;

		waitOnElement("cssSelector",resourceItems);
		List <WebElement> resources = driver().findElements(By.cssSelector(resourceItems));
		
		String resBefore = "//*[@id='index_page']/div[1]/div[";
		String resAfter = "]/div/div[2]/div"; 
		
		String before = "//*[@id='index_page']/div[1]/div["; 
		String after = "]/div/div[1]/div/div"; 
		
		for(int i=1;i<=resources.size();i++){
			Thread.sleep(5000);
			waitOnElement("xpath",resBefore+i+resAfter);
			String res = driver().findElement(By.xpath(resBefore+i+resAfter)).getText().trim();
			if(res.contains(resourceName)){
				WebElement e = driver().findElement(By.xpath(before+i+after));
				e.click(); 
				isResourceFound = true;
				break;
			}
			
		}		
		
		return isResourceFound;

	}

	
	public boolean verifyResourceNavigation(String resourceName) throws Exception{
		logInfo("Inside verifyResourceNavigation() method.");
		boolean navigationVerified = false;
		waitOnElement("linkText","Next Resource");
		clickOnElement("linkText","Next Resource");
		waitOnElement("cssSelector",previewHeader);
		WebElement resName = driver().findElement(By.cssSelector(previewHeader));
		if(!resName.getText().equalsIgnoreCase(resourceName)){
			navigationVerified = true;
		}
		return navigationVerified;
	}
	
	public boolean composeEmailAsAttachment(String resourceName,String parentCategory) throws Exception{
		logInfo("Inside composeEmailAsAttachment() method.");
		boolean isEmailSent = false; 
		inbox.setNotifications2User(vibeRecipient_text);
		navigate2UserRL();
		verifyParentCategory(parentCategory);
		previewResource(resourceName);
		/*waitOnElement("xpath",lnkEmail);
		driver().findElement(By.xpath(lnkEmail)).click();
		driver().findElement(By.xpath(btnComposeEmailAsAttachment)).click();
		sendMailWithRecipient(vibeRecipient_text);
		
		clickOnElement("xpath", browseInBr);		    	
    	uploadFile("Image");
		clickOnButton("xpath",btnAttach2Message);*/
		
		
		/*waitOnElement("cssSelector",".close");
		clickOnElement("cssSelector",".close");*/
/*		Alert alert = driver().switchTo().alert();
		alert.accept();*/
/*		 ( ( JavascriptExecutor ) _driver )
         .executeScript( "window.onbeforeunload = function(e){};" );*/
		
		/*waitOnElement("xpath","//button[contains(text(),'Leave Page')]");
		driver().findElement(By.xpath("//button[contains(text(),'Leave Page')]")).click();*/
		/*waitOnElement("xpath",btnSendmail);
		clickOnElement("xpath",btnSendmail);
		inbox.go2Inbox();
		isEmailSent = inbox.verifyVibeInboxMail(txtSubject);
		return isEmailSent;*/
		return true;
		
	}
	
	public void sendMailWithRecipient(String receipient) throws Exception, Exception{
		logInfo("Inside sendMailWithRecipient() method.");
    	inputText("cssSelector", recipientsTo, receipient);  
    	implicityWaits(10);
    	inputTextClear("cssSelector", mailSub);
    	inputText("cssSelector", mailSub, txtSubject); 
    	clickOnButton("xpath", btnAttachFile);
    }
	
	public boolean resourceAssetInEmailLinkToMyWebsite(String resourceName,String resAsset,String assetType,String parentCategory) throws Exception{
		logInfo("Inside resourceAssetInEmailLinkToMyWebsite() method.");
		boolean isAssetFound = false; 
		
		inbox.setNotifications2User(vibeRecipient_text);
		navigate2UserRL();
		verifyParentCategory(parentCategory);
		previewResource(resourceName);
		waitOnElement("xpath",lnkEmail);
		driver().findElement(By.xpath(lnkEmail)).click();
		waitOnElement("xpath",btnComposeEmailMyWebsite);
		driver().findElement(By.xpath(btnComposeEmailMyWebsite)).click();
		String recipient = vibeRecipient_text;
		inputText("cssSelector", recipientsTo, recipient);    
		implicityWaits(10);
    	inputTextClear("cssSelector", mailSub);
    	inputText("cssSelector", mailSub, txtSubject); 
    	clickOnElement("xpath",btnSend);
    	implicityWaits(10);
    	inbox.go2Inbox();
    	
    	inbox.verifyVibeInboxMail(txtSubject);
    	waitOnElement("xpath", lnkClick);
		driver().findElement(By.xpath(lnkClick)).click();
		waitOnElement("xpath",txtAsset);
		String assetName = driver().findElement(By.xpath(txtAsset)).getText().trim();
		String resourceAsset = assetType+" "+resAsset;
		if(assetName.equalsIgnoreCase(resourceAsset.trim())){
			isAssetFound = true;
		}
		return isAssetFound;

	}
	
	public boolean verifyParentCategory(String parentCategory) throws Exception{  
		 logInfo("inside verifyParentCategory() method.");
		 boolean isMatchFound = false;
		 String resourcePanelCategory = "//*[@class='panel-nav categories']/li";
		 String categoryName;
		 WebElement category;
		 List<WebElement> categories = driver().findElements(By.xpath(resourcePanelCategory));
		 System.out.println("total resources =" +categories.size());
		 String before = "//*[@class='panel-nav categories']/li[";
		 String after = "]/a";
		 for(int i=1;i<=categories.size();i++){
			 List<WebElement> count = driver().findElements(By.xpath(before+i+after));			 
			 if(count.size() > 1){
				 category = driver().findElement(By.xpath(before+i+"]/a[2]"));
				 categoryName = driver().findElement(By.xpath(before+i+"]/a[2]")).getText().trim();
			 }
			 else{
				 category = driver().findElement(By.xpath(before+i+after));
				 categoryName = driver().findElement(By.xpath(before+i+after)).getText().trim();
			 }			 
			 if(categoryName.equalsIgnoreCase(parentCategory)){
				 System.out.println(categoryName + " parent category match found.");
				 isMatchFound = true;
				 category.click();
				 break;
			 }
		 }
		 return isMatchFound;
	}	

	public void selectParentCategory(String parentCategory) throws Exception{  
		 logInfo("Select only parent category in Resource library Panel.");
		 boolean isParentCatPresent = false;
		
		 List<WebElement> categories = driver().findElements(By.xpath(panelCatMaster));
		 System.out.println("total resources =" +categories.size());	
		 for (WebElement cates : categories ){
	/*	 
		 for (int i=1; i <=categories.size(); i++){
			WebElement cates = driver().findElement(By.xpath(panelCat1+i+panelCat2));*/
			System.out.println("All cate are "+cates.getText()); 
			 String categoryName = cates.getText().trim();
			 
			if(categoryName.equalsIgnoreCase(parentCategory)){
				 System.out.println(categoryName + " parent category match found.");
				 isParentCatPresent = true;
				 cates.click();
				 break;
			 }
		 } if (isParentCatPresent==false){
			 Assert.assertTrue(isParentCatPresent,parentCategory+ " parent category match found." );
			 
		 	}
	 
		}	
	
	
/*	public void selectParentAndChildCategory(String parentCategory, String childCategory) throws Exception{
		
		if (driver().getCurrentUrl().contains("master")||
				driver().getCurrentUrl().contains("tupper")){
			selectChildCategory(parentCategory,childCategory);
		}else{
			selectChildCategoryInBranch(parentCategory,childCategory);
		}
		
	}*/
	
	/*public void selectChildCategoryInBranch(String parentCategory, String childCategory) throws Exception{  
		 logInfo("Select Parent catgegory + icon to extract and then select child category.");
		 boolean isParentCatPresent = false;
		 List<WebElement> categories = driver().findElements(By.xpath(panelCatBranch));
		
		 System.out.println("total resources =" +categories.size());	
		 for (WebElement cates : categories ){		 
		 for (int i=1; i <=categories.size(); i++){
			WebElement cates = driver().findElement(By.xpath(panelCat1Branch+i+panelCat2));
			System.out.println("All cate are "+cates.getText()); 
			 String categoryName = cates.getText().trim();
			 
			if(categoryName.equalsIgnoreCase(parentCategory)){				 
				 isParentCatPresent = true;
				 WebElement plus = driver().findElement(By.xpath(panelCat1Branch+i+panelPlus2));
				 plus.click();
				 selectChildCat(childCategory);
				 break;
			 }
		 } if (isParentCatPresent==false){
			 Assert.assertTrue(isParentCatPresent,parentCategory+ " parent category match found." );
			 
		 	}
	 
		}*/
	
	/*public void selectChildCategory(String parentCategory, String childCategory) throws Exception{  
		 logInfo("Select Parent catgegory + icon to extract and then select child category.");
		 boolean isParentCatPresent = false;
		 List<WebElement> categories = driver().findElements(By.xpath(panelCatMaster));		
		 System.out.println("total resources =" +categories.size());			 
		 for (int i=1; i <=categories.size(); i++){
			WebElement cates = driver().findElement(By.xpath(panelCat1+i+panelCat2));
			System.out.println("All cate are "+cates.getText()); 
			 String categoryName = cates.getText().trim();
			 
			if(categoryName.equalsIgnoreCase(parentCategory)){				 
				 isParentCatPresent = true;
				 WebElement plus = driver().findElement(By.xpath(panelCat1+i+panelPlus2));
				 plus.click();
				 selectChildCat(childCategory);
				 break;
			 }
		 } if (isParentCatPresent==false){
			 Assert.assertTrue(isParentCatPresent,parentCategory+ " parent category match found." );
			 
		 	}
	 
		}*/
	
	
	public void selectChildCat(String childcategory){
		 logInfo("Select child category based on Parent category ");
		 boolean ischildPresent = false;
		 List <WebElement> cc = driver().findElements(By.cssSelector(ccat));			 
		 for (WebElement cate : cc ){
			 System.out.println(cate.getText()+"All are ");
			 if (cate.getText().equals(childcategory)){	
				 ischildPresent = true;
				 System.out.println(cate.getText()+ " is child category..........");
				 cate.click();
				 break;
			 }
		 }if (ischildPresent==false){
			 Assert.assertTrue(ischildPresent, childcategory + " is not found under the parent category.");
		 }
	}
	
	public boolean verifyChildCategory(String parentCategory,String subCategory) throws Exception{  
		 logInfo("inside verifyChildCategory1() method.");
		 boolean isMatchFound = false;
		 String panelCategory = "//*[@id='main-content']/div/div[2]/ul";
		 List categories = driver().findElements(By.xpath(panelCategory));
		 System.out.println("total resources =" +categories.size());
		 String before = "//*[@id='main-content']/div/div[2]/ul/li[";
		 String after = "]/a";  
		 
		 String beforeCollapse = "//*[@id='main-content']/div/div[2]/ul/li[";
		 String afterCollapse = "]/a";
		 
		 String beforeChildCat = "//*[@id='main-content']/div/div[2]/ul/li[";
		 String afterChildCat = "]/ul/li/a";
		 
		 for(int i=1;i<=categories.size();i++){
			 WebElement category = driver().findElement(By.xpath(before+i+after));
			 String categoryName = category.getText().trim();
			 
			 if(categoryName.equalsIgnoreCase(parentCategory)){
				 System.out.println(categoryName + " parent category match found.");
				 List<WebElement> collapseParentCategory = driver().findElements(By.xpath(beforeCollapse+i+afterCollapse));
				 if(collapseParentCategory.size() > 1){
					 driver().findElement(By.xpath(beforeCollapse+i+afterCollapse+"[1]")).click();
					 WebElement childCategory = driver().findElement(By.xpath(beforeChildCat+i+afterChildCat));
					 String childCategoryName = childCategory.getText().trim();
					 if(childCategoryName.equalsIgnoreCase(subCategory)){
						 System.out.println(categoryName + " child category match found.");
						 isMatchFound = true;
					 }
				 }
				 break;
			 }
		 }
		 return isMatchFound;
	}
	
	public boolean getResourceTags(String tagName) throws Exception, Exception{
		logInfo("inside getResourceTags() method.");
		boolean isTagFound = false;
		waitOnElement("xpath",resTagsList);
		List<WebElement> tagsList = driver().findElements(By.xpath(resTagsList));
		for(WebElement tag : tagsList){
			 if (tag.getText().equalsIgnoreCase(tagName)){
				 isTagFound = true;
				 tag.click();
				 break;
			 }
		}
		return isTagFound;
	}
	
	public void writeReviewForAsset(String reviewTitle,String reviewDesc) throws Exception{
		logInfo("inside writeReviewForAsset() method.");
		waitOnElement("xpath",lnkWriteReview);
		WebElement writeReview = driver().findElement(By.xpath(lnkWriteReview));
		writeReview.click();
		waitOnElement("xpath",inputReviewTitle);
		inputText("xpath", inputReviewTitle, reviewTitle);
		inputText("xpath", inputReviewDesc, reviewDesc);
		clickOnElement("xpath", btnSaveReview);
	}
	
	public boolean verifyReview(String reviewTitle) throws Exception{
		logInfo("inside verifyReview() method.");
		boolean isReviewFound = false;
		implicityWaits(5);
		waitOnElement("xpath",panelReview);
		driver().findElement(By.xpath(panelReview)).click();
		waitOnElement("xpath",reviewsList);
		List<WebElement> reviews = driver().findElements(By.xpath(reviewsList));
		String before = "//*[@id='reviews']/section/div/div[";
		String after = "]/div[2]/div/div[2]/div";
		
		for(int i=1; i<reviews.size(); i++){
			waitOnElement("xpath",before+i+after);
			WebElement review = driver().findElement(By.xpath(before+i+after));
			if(review.getText().trim().equalsIgnoreCase(reviewTitle.trim())){
				isReviewFound = true;
				break;
			}
		}
		return isReviewFound;
	}
	
	
	public void nav2marketBusinessRL(){		   
		logInfo("Navigate to Resource Library");
		driver().navigate().to(appUrl + "pyr_core/resources");
	}
 
 	public void selectResourceInOffice(String resource){
		logInfo("Select child category based on Parent category ");		
		List <WebElement> cc = driver().findElements(By.cssSelector(resourceItems));
		System.out.println("Resouce "+ cc.size());			 
		for (WebElement cate : cc ){		
			 if (cate.getText().equals(resource)){				 
				 cate.click();
				 break;
			 }
		}		 
	}
 	
 		 
	public void retriveCounts(){		
		logInfo("Retrive count of all statistics items ");
		List  <WebElement> ct = driver().findElements(By.cssSelector(statisItem));
		System.out.println(ct.size());
		for(int i=1 ; i<=ct.size(); i++){
			WebElement item = driver().findElement(By.cssSelector(statisItem1+i+statisItem2));
			WebElement count = driver().findElement(By.cssSelector(statisCount+i));
			String items = item.getText();
			String counts = count.getText();
			System.out.println("For each "+items + " count is "+  counts );	
		}     
	}

	public void verifyLikeResources() throws Exception{		
		logInfo("Verify statistic Like count before and after like counts.");
		List  <WebElement> ct = driver().findElements(By.cssSelector(statisItem));
		System.out.println(ct.size());
		for(int i=1 ; i<=ct.size(); i++){			
			WebElement item = driver().findElement(By.cssSelector(statisItem1+i+statisItem2));			
			if (item.getText().equalsIgnoreCase("Likes")){
				WebElement countbefore = driver().findElement(By.xpath(likesCount));
				String b4Like = countbefore.getText();
				int beforelike = Integer.parseInt(b4Like);
				int afterLiked = beforelike+1;
				String afterLikedCount = Integer.toString(afterLiked);				
				int disLike = beforelike-1;
				String disLiked = Integer.toString(disLike);
				System.out.println("Before selecting like count is "+ b4Like);	
				waitOnElement("cssSelector",RLLikes);
				WebElement likes = driver().findElement(By.cssSelector(RLLikes));{			
				
				if (likes.getText().equals("Liked")){				
				likes.click();
				waitOnElement("xpath",likesCount);
				WebElement getcountLike = driver().findElement(By.xpath(likesCount));			
				System.out.println("count size for Liked "+b4Like);
				System.out.println(getcountLike.getText()+ "after disliked");
				Assert.assertEquals(getcountLike.getText(), disLiked);			
				break;						
			}
			else{				
				likes.click();
				waitOnElement("cssSelector",statisCount+3);
				WebElement getcountLike = driver().findElement(By.cssSelector(statisCount+3));	
				System.out.println("count size is like "+b4Like);
				System.out.println(getcountLike.getText()+ "after liked");
				Assert.assertEquals(getcountLike.getText(), afterLikedCount);		
				break;
			}}
		}
	}}
		

	public void selectcontrols(String controller) throws Exception{
		logInfo("Select Resource control like -Details, Share, Email  to foucs on it.");
		waitOnElement("cssSelector",RLControls);
		List <WebElement> con = driver().findElements(By.cssSelector(RLControls));		
		for (WebElement control: con){			
			String cont = control.getText().trim();			
			if (cont.equalsIgnoreCase(controller.trim())){
				control.click();
				break;
			}			
		}		
	}

	public void shareToSocial() throws Exception{
		logInfo("Verify the social Networks section and select Share to Social sites."); 
		waitOnElement("cssSelector",RLSocial);
		WebElement soc = driver().findElement(By.cssSelector(RLSocial));
		Assert.assertEquals(soc.getText(), RLSocialText);
		clickOnButton("cssSelector",btnResourceShareSocialNetwork);
	}


	public void selectFBIcon() throws Exception {
		waitOnElement("cssSelector",btnShareFacebook);
		clickOnElement("cssSelector",btnShareFacebook);	
	}
	
	public void selectTweetIcon() throws Exception {
		waitOnElement("cssSelector",TweetIcon);
		clickOnElement("cssSelector",TweetIcon);	
	}
	
	public void closeModalWindow() throws Exception{
		waitOnElement("cssSelector",btnClose);
		clickOnElement("cssSelector",btnClose);
		driver().navigate().refresh();
	}

	public void shareToCommunity() throws Exception{
		logInfo("Verify the social Networks section and select Share to Social sites."); 
		selectcontrols("Details");
		WebElement sc = driver().findElement(By.cssSelector(sharesCount)) ;
		String Beforeshare = sc.getText();		
		int Beforeshares = Integer.parseInt(Beforeshare);
		int AfterShared = Beforeshares+1;
		String afterShared = Integer.toString(AfterShared);
		
		selectcontrols("Share");
		WebElement com = driver().findElement(By.cssSelector(RLComm));
		Assert.assertEquals(com.getText(),RLCommText );
		clickOnButton("cssSelector",commShare);		
		verifyElementPresent("xpath",txtareaShareComments);
		inputText("xpath",txtareaShareComments, commentsText);		
		clickOnButton("cssSelector",comtsShare);
		confirmationMessage("This resource has been shared to the community.");	
		selectcontrols("Details");
		WebElement afterSharedcount = driver().findElement(By.cssSelector(sharesCount));	
		System.out.println("count is "+ afterSharedcount.getText());
		Assert.assertEquals(afterSharedcount.getText(), afterShared);	
		cm.navigate2CommunityPage();		
		cm.isActivitypresentInMyRecentActivities(commentsText);	
	}


	public void shareToMyWebsite() throws Exception{
		logInfo("Verify MyWebsite section and select it, Again verify 'Share To My Website' modal box.");		  
		selectcontrols("Share");
		waitOnElement("cssSelector",RLWebSite);
		WebElement com = driver().findElement(By.cssSelector(RLWebSite));
		Assert.assertEquals(com.getText(),RLWebsiteText );
		clickOnButton("cssSelector",webShare);		
		waitOnElement("cssSelector",webShareTitle);
		WebElement title = driver().findElement(By.cssSelector(webShareTitle));
		waitOnElement("cssSelector",shareLinktext);
		WebElement urlPath = driver().findElement(By.cssSelector(shareLinktext));
		Assert.assertEquals(title.getText(), webShareText);
		String expectedurl = urlPath.getText();
		System.out.println("expected url is "+expectedurl);
		clickOnButton("cssSelector", viewWebsite);
		handleMyWebsite();
	}
  
	public void shareInTwitter() throws Exception{
		PropertyConfigurator.configure("Log4j.properties");	
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
				waitOnElement("cssSelector", twtTweet);	
				clickOnButton("cssSelector", twtTweet);	
				driver().switchTo().window(wndBeforeWindow);
				break;
			}
		}  
	} 
 
 
	 public void handleMyWebsite() throws Exception{
		logInfo("Get focus on second window & get URl and close it.");
		String wndBeforeWindow = driver().getWindowHandle();	
		for(String w : driver().getWindowHandles()){
			if(!w.equalsIgnoreCase(wndBeforeWindow)){
				driver().switchTo().window(w);					
				System.out.println(driver().getCurrentUrl());
				System.out.println(driver().getTitle());					
				driver().switchTo().window(wndBeforeWindow);					
				break;	
			}
		}		
	 } 
 
 
	 public void login() throws Exception{
		 driver().navigate().to(appUrl);
		 System.out.println(driver().getTitle());
		 System.out.println("executed succesfully");
	 }

	 public void emailRL(String mailID) throws Exception{	
		 waitOnElement("xpath",linkText);
		 WebElement link = driver().findElement(By.cssSelector(linkText));
		 String emailText = link.getText().trim();
		 String expLinkText = link_Text.trim();		 
		 if(emailText.contains(expLinkText)){
			 System.out.println("Link mails has been send");
			 clickOnButton("cssSelector", linkCompBtn);
			 composeNSend(mailID);			 
		 }else if(driver().findElement(By.cssSelector(AttachText)).getText().trim().contains(attach_Text)){
			 
			 clickOnButton("cssSelector", attachCompBtn);
			 composeNSend(mailID);
			 
		 }else{
			 System.out.println("Above both options are aborted");
			 
		 }
	 }	 
	 
	 public void rlEmailWithAttachment(String mailID) throws Exception{		 
		 WebElement link = driver().findElement(By.cssSelector(linkText));
		 String emailText = link.getText().trim();
		 String expLinkText = link_Text.trim();		 
		 if(emailText.contains(expLinkText)){		 
			 clickOnButton("cssSelector", linkCompBtn);
			 cm.emailWithAttachment(mailID, subText);		
		   }else if(driver().findElement(By.cssSelector(AttachText)).getText().trim().contains(attach_Text)){
			 
			 clickOnButton("cssSelector", attachCompBtn);
			cm.emailWithAttachment(mailID, subText);
			 
		 }else{
			 System.out.println("Above both options are aborted");
			 
		 }
		 confirmationMessage("This resource has been sent to: "+mailID);
	}
 
    public void composeNSend(String mailId) throws Exception{
	   
	   /*verifyElementPresent("xpath",inputComposeEmailTo);
	   clickOnElement("xpath",inputComposeEmailTo);*/
	    waitOnElement("xpath",inputComposeEmailTo);
		inputText("xpath",inputComposeEmailTo,mailId);
		verifyElementPresent("cssSelector",inputComposeEmailSubject);
		inputText("cssSelector",inputComposeEmailSubject,composeEmailSubject_text);
		//	verifyLinkPresent("Send");
		clickOnLink("linkText","Send");
		logInfo("clicked on Send button of Compose Email page.");
		confirmationMessage("This resource has been sent to: "+mailId);
		Thread.sleep(5000);
		waitOnElement("xpath","//*[@id='page']/div[8]/div/div/div[1]/div/div[3]/div/div[2]/button");
		clickOnElement("xpath","//*[@id='page']/div[8]/div/div/div[1]/div/div[3]/div/div[2]/button");
    }

	public void emailSection (){
		List <WebElement> es = driver().findElements(By.cssSelector(esection));
		System.out.println(es.size());
		for (WebElement mailSec : es){		
			System.out.println(mailSec.getText());
			String emailText = mailSec.getText().trim();
			String attachText = "Email As An Attachment1";
			String linkText = "Email Link To My Website";
			if (emailText.contains(attachText)){
				System.out.println("succesed");				
				break;
			}		
		}
		
	}


	 public void verifyFileTypeMailRetriveShares(String type, String RL, String mailId) throws Exception{		 
		 logInfo("Retrive self mailId, Select Type of file in RL.If file type is other than website & video, \n"
		 		+ "send attached mail and assert the count of shares before & after sending mail. ");
		 clickOnElement("cssSelector", RLfilter);
		 waitOnElement("cssSelector",RLFiltOptions);	 
		 List <WebElement> fil = driver().findElements(By.cssSelector(RLFiltOptions));
		
		 for (WebElement filter: fil){
			/* System.out.println(filter.getText());*/
			if (filter.getText().equalsIgnoreCase(type)){	
				 System.out.println(filter.getText());
					filter.click();	
					verifyResource(RL);
					selectcontrols("Details");
			WebElement tp = driver().findElement(By.cssSelector(fileTyp))	;	
			String fileType = tp.getText();
			WebElement sc = driver().findElement(By.cssSelector(sharesCount)) ;
			String Beforeshare = sc.getText();	
			System.out.println("before count is "+Beforeshare);
			int Beforeshares = Integer.parseInt(Beforeshare);
			int sharedTwoTimes = Beforeshares+2;
			int AfterShared = Beforeshares+1;
			String afterShared = Integer.toString(AfterShared);
			String afterSharedTwoTimes = Integer.toString(sharedTwoTimes);
			if ((!fileType.equals("Website"))|| (!fileType.equals("Video")))	{				
				selectcontrols("Email");
				clickOnButton("cssSelector", linkCompBtn);
				composeNSend(mailId);	
				selectcontrols("Details");
				selectcontrols("Email");				
				clickOnButton("cssSelector", attachCompBtn);
				composeNSend(mailId);	
				selectcontrols("Details");
				WebElement afterSharedcount = driver().findElement(By.cssSelector(sharesCount));	
				System.out.println("After Sending mails, the count is "+ afterSharedcount.getText());
				Assert.assertEquals(afterSharedcount.getText(), afterSharedTwoTimes);			
				break;					
			}
			else{
				selectcontrols("Email");
				clickOnButton("cssSelector", linkCompBtn);
				composeNSend(mailId);				
				selectcontrols("Details");
				WebElement afterSharedcount = driver().findElement(By.cssSelector(sharesCount));	
				System.out.println("count is "+ afterSharedcount.getText());
				Assert.assertEquals(afterSharedcount.getText(), afterShared);			
				break;				
			}
		}
	}
}
 
 
 	public void filterType1(String type, String RL, String mailId) throws Exception{
	 
	 clickOnElement("cssSelector", RLfilter);		 
	 List <WebElement> fil = driver().findElements(By.cssSelector(RLFiltOptions));		 
	 for (WebElement filter: fil){
		if (filter.getText().equalsIgnoreCase(type)){			
				filter.click();				
				break;
					}
		if ((!filter.getText().equals("PDF"))|| (!filter.getText().equals("Video"))){
					selectResource(RL);
				selectcontrols("Email");
				 clickOnButton("cssSelector", linkCompBtn);
				 composeNSend(mailId);
				 break;		
		}else {
			
			
		}
	 }

 }
 
  public void handlePagination(){
	  List <WebElement> mod = driver().findElements(By.cssSelector(pagn));
	  System.out.println("size is "+ mod.size());
	  for (WebElement modal : mod){
		  
		  System.out.println(modal.getText());
	  }	  
	  
  }
  
  public void selectTypeOfFile(String type ) throws Exception{
 	 clickOnElement("cssSelector", RLfilter);
 	 waitOnElement("cssSelector",RLFiltOptions);		 
 	 List <WebElement> fil = driver().findElements(By.cssSelector(RLFiltOptions));    	
 	 for (WebElement filter: fil){    		 
 		if (filter.getText().equalsIgnoreCase(type)){     			 
 				filter.click(); 
 				break;
 		}
 	 }
  }
	
  
  public void verifyCategoryInBusinessRL(String prodCategory) throws Exception{  
		 System.out.println("inside the verifyCategoryInBusinessRL() method..");
		 logInfo("inside verifyCategoryInBusinessRL() method.");
		 
		 WebElement panel = driver().findElement(By.xpath(panelCategory));
		 List allLi = panel.findElements(By.tagName("li"));
		 int total_lis = allLi.size();
		 System.out.println("total resources =" +total_lis);
		 String before_li = "//*[@id='resource_library']/div/ul/li[";
		 String after_li = "]/a";  
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
				 WebElement e = driver().findElement(By.xpath(before_li+i+after_li));
				 e.click();
				 break;
			 }
		 }
	
		 if(isMatchFound==false){
			 System.out.println(prodCategory + " product category match not found in business Rl.");
			 logInfo(prodCategory + " product category match not found in business Rl.");
			 Assert.assertTrue(isMatchFound, prodCategory + " product category match not found in business Rl.");
		 }
	 }
  
  //********************************************** new methods ***************************************************//
  
  public boolean moveResourceCategories(String category1) throws Exception{  
	 System.out.println("inside the moveResourceCategories() method..");
	 logInfo("inside moveResourceCategories() method.");
	 
	 boolean isCategoryMoved = false;
	 WebElement source;
	 WebElement target;
	 Actions act = new Actions(driver());
	 
	 List<WebElement> allRows = driver().findElements(By.cssSelector(".col-md-5"));
	 System.out.println("No of rows in the product table = " + allRows.size());
	 
	 String before = "//*[@id='index_page']/div[2]/div[";
	 String after = "]/div[2]";
	 
	 String sourceBefore = "//*[@id='index_page']/div[2]/div[";
	 String sourceAfter = "]/div[5]/i";
	 
	 for(int i=1;i<=allRows.size();i++){
		String categoryName = driver().findElement(By.xpath(before+i+after)).getText().trim();
		System.out.println(categoryName);
		if(categoryName.equalsIgnoreCase(category1)){
			System.out.println("product found " + category1);
			source = driver().findElement(By.xpath(sourceBefore+i+sourceAfter));
			int j=i+1;
			target = driver().findElement(By.xpath(sourceBefore+j+sourceAfter));
			act.clickAndHold(source).build().perform();
			act.moveToElement(target).build().perform();
			act.release(source).build().perform();
			isCategoryMoved = true;
			break;
		}

	 }
	 return isCategoryMoved;

  }
  
  public boolean verifyBackBtnFromResourceCategories() throws Exception, Exception{
	  logInfo("Inside verifyBackBtnFromResourceCategories() method..");
	  boolean isResourceTitleVerified = false;
	  driver().get("");
	  waitOnElement("xpath",btnBackResources);
	  WebElement btnBack = driver().findElement(By.xpath(btnBackResources));
	  btnBack.click();
	  String resourceHeading = driver().findElement(By.xpath(txtResourceHeading)).getText();
	  if(resourceHeading.equalsIgnoreCase("Resource Library")){
		  isResourceTitleVerified = true;
	  }
	  return isResourceTitleVerified;
  }
  
  public boolean checkResourceTitleValidation() throws Exception{
	  logInfo("Inside checkResourceTitleValidation() method..");
	  boolean isResourceTitleValidated = false;
	  String resourceTitle = driver().findElement(By.xpath(validateTitle)).getText();
	  if(resourceTitle.contains("has already been taken")){
		  isResourceTitleValidated = true;
	  }
	  return isResourceTitleValidated;
  }
  
  public void editResourceAndAddMultipleAssets(String resource,String category) throws Exception, Exception{
	  logInfo("Inside editResourceAndAddMultipleAssets() method..");
	  selectCategoryFilter(category);
	  editResourceAdmin(resource);
	  selectAssets("Text");
  }
  
  public void editResourceAdmin(String resName) throws Exception, Exception{
		logInfo("inside editResourceAdmin() method.");
		waitOnElement("cssSelector",resListViewAdmin);
		clickOnElement("cssSelector",resListViewAdmin);
		
		List allRows = driver().findElements(By.xpath("//*[@id='resource-table']/tbody/tr[contains(@class,'parent_tr')]"));
		int all_rows = allRows.size();
		
		String before_name = "//*[@id='resource-table']/tbody[";
		String after_name = "]/tr[contains(@class,'parent_tr')]/td[2]/a";
		
		String before_btn = "//*[@id='resource-table']/tbody[";
		String after_btn = "]/tr[contains(@class,'parent_tr')]/td[7]/div/div/div/button";
		
		String before_edit = "//*[@id='resource-table']/tbody[";
		String after_edit = "]/tr[contains(@class,'parent_tr')]/td[7]/div/div/div/ul/li[2]";
		int i=1;
		while(i<=all_rows+2){
			WebElement n = driver().findElement(By.xpath(before_name+i+after_name));
			String name = n.getText().trim();
			if(name.equalsIgnoreCase(resName)){
				WebElement btn = driver().findElement(By.xpath(before_btn+i+after_btn));
				btn.click();
				waitOnElement("xpath",before_edit+i+after_edit);
				WebElement edit = driver().findElement(By.xpath(before_edit+i+after_edit));
				edit.click();
				break;
			}
			i=i+2;
		}
		
	}
  
  public void cloneResourceAdmin(String resName) throws Exception, Exception{
		logInfo("inside cloneResourceAdmin() method.");
		waitOnElement("cssSelector",resListViewAdmin);
		clickOnElement("cssSelector",resListViewAdmin);
		
		WebElement tblRes = driver().findElement(By.xpath("//*[@id='resource-table']/tbody"));
		List allRows = tblRes.findElements(By.tagName("tr"));
		int all_rows = allRows.size();
		
		String before_name = "//*[@id='index_page']/div[1]/div/div[";
		String after_name = "]/div/div[4]/div[1]";
		
		String before_btn = "//*[@id='index_page']/div[1]/div/div[";
		String after_btn = "]/div/div[1]/div/button";
		
		String before_clone = "//*[@id='index_page']/div[1]/div/div[";
		String after_clone = "]/div/div[1]/div/ul/li[4]";
		
		for(int i=1;i<=all_rows;i++){
			WebElement n = driver().findElement(By.xpath(before_name+i+after_name));
			String name = n.getText().trim();
			if(name.equalsIgnoreCase(resName)){
				WebElement btn = driver().findElement(By.xpath(before_btn+i+after_btn));
				btn.click();
				waitOnElement("xpath",before_clone+i+after_clone);
				WebElement clone = driver().findElement(By.xpath(before_clone+i+after_clone));
				clone.click();
				break;
			}
		}
		
	}
  
	//Select markets from the filter in RL Admin page.

	public void selectMarketFilter(String market) throws Exception, Exception{
		logInfo("inside selectMarketFilter() method"); 
		
		waitOnElement("cssSelector",marketsFilter);
		
		WebElement e = driver().findElement(By.cssSelector(marketsFilter));
		e.click();
		
		WebElement panel = driver().findElement(By.xpath(marketLanguageContainer));
		List<WebElement>  allItems = panel.findElements(By.tagName("li"));
		int all_lis = allItems.size();
		System.out.println("Total Lis =" +all_lis);
		
		String before_liName = "//div[@id='market_language_container']/ul/li[";
		String after_liName = "]";
		
		for(int i=3;i<=all_lis;i++){
			WebElement liName = driver().findElement(By.xpath(before_liName+i+after_liName));
			String itemName = liName.getText().trim();
			if(itemName.equalsIgnoreCase(market)){
				System.out.println("market =" +itemName);
				liName.click();
			}
		}
	}
	
	//Select filter type from the filter dropdown in RL Admin page.
	public void selectFilterType(String filterType) throws Exception, Exception{
		logInfo("inside selectMarketFilter() method"); 
		
		waitOnElement("cssSelector",drpdownFilterType);
		
		WebElement e = driver().findElement(By.cssSelector(drpdownFilterType));
		e.click();
		
		WebElement panel = driver().findElement(By.xpath(filterTypesContainer));
		List<WebElement>  allItems = panel.findElements(By.tagName("li"));
		int all_lis = allItems.size();
		System.out.println("Total Lis =" +all_lis);
		
		String before_liName = "//div[@id='filter_by_container']/ul/li[";
		String after_liName = "]";
		
		for(int i=3;i<=all_lis;i++){
			WebElement liName = driver().findElement(By.xpath(before_liName+i+after_liName));
			String itemName = liName.getText().trim();
			if(itemName.equalsIgnoreCase(filterType)){
				System.out.println("market =" +itemName);
				liName.click();
			}
		}
	}
	
	//sort by newest resources on the admin side
	public void sortByNewestResourcesAdmin(String sortOption) throws Exception, Exception{
		logInfo("inside sortByNewestResourcesAdmin() method"); 
		
		waitOnElement("cssSelector",drpdownSortOption);
		
		WebElement e = driver().findElement(By.cssSelector(drpdownSortOption));
		e.click();
		
		WebElement panel = driver().findElement(By.xpath(sortOptionsContainer));
		List<WebElement>  allItems = panel.findElements(By.tagName("li"));
		int all_lis = allItems.size();
		System.out.println("Total Lis =" +all_lis);
		
		String before_liName = "//div[@id='sort_by_container']/ul/li[";
		String after_liName = "]";
		
		for(int i=3;i<=all_lis;i++){
			WebElement liName = driver().findElement(By.xpath(before_liName+i+after_liName));
			String itemName = liName.getText().trim();
			if(itemName.equalsIgnoreCase(sortOption)){
				System.out.println("sort option =" +itemName);
				liName.click();
			}
		}
	}
	
	//sort by newest resources on the admin side
	public void sortByPublishDate(String sortOption) throws Exception, Exception{
		logInfo("inside sortByPublishDate() method"); 
		System.out.println("inside sortByPublishDate() method"); 
		waitOnElement("cssSelector",drpdownSortOption);
		
		WebElement e = driver().findElement(By.cssSelector(drpdownSortOption));
		e.click();
		
		WebElement panel = driver().findElement(By.xpath(sortOptionsContainer));
		List<WebElement>  allItems = panel.findElements(By.tagName("li"));
		int all_lis = allItems.size();
		System.out.println("Total Lis =" +all_lis);
		
		String before_liName = "//div[@id='sort_by_container']/ul/li[";
		String after_liName = "]/a";
		
		for(int i=3;i<=all_lis;i++){
			WebElement liName = driver().findElement(By.xpath(before_liName+i+after_liName));
			String itemName = liName.getText().trim();
			if(itemName.equalsIgnoreCase(sortOption)){
				System.out.println("sort option =" +itemName);
				liName.click();
				break;
			}
		}
	}
	
		
	public boolean verifyDownloadOption(){
		logInfo("Inside verifyDownloadOption() method..");
		System.out.println("Inside verifyDownloadOption() method..");
		boolean isDownloadPresent = false;
		List<WebElement> options = driver().findElements(By.xpath(resourceOptions));
		if(options.size()>3){
			isDownloadPresent = true;
		}
		return isDownloadPresent;
	}
	
	public void addResourceCategoryWithoutMarkets(String prodCategory, String subCategory) throws Exception{
		logInfo("inside addResourceCategory() method.");
		waitOnElement("linkText", "Manage Categories");
		clickOnLink("linkText", "Manage Categories");
		
		verifyLinkPresent("Add New Category");
		clickOnLink("linkText", "Add New Category");
		logInfo("clicked 'Add New Category' link in Manage Categories page.");
		
		verifyElementPresent("cssSelector",inputAddNewCategory);
		inputText("cssSelector",inputAddNewCategory,prodCategory);
		
		selectFromDropdown("cssSelector",drpdnParentCategory,"byVisibleText",subCategory);
		
		verifyElementPresent("cssSelector",btnCreateResourceCategory);
		clickOnButton("cssSelector",btnCreateResourceCategory);	
		getText("cssSelector", backRL, "Ret is ");
		clickOnButton("cssSelector", backRL);
		clickOnLink("linkText","Back");
	}
		
	public void writeCommentForAsset(String postTitle) throws Exception{
		logInfo("inside writePostForAsset() method.");
		System.out.println("inside writePostForAsset() method.");
		waitOnElement("cssSelector",inputCommentAsset);
		inputText("cssSelector",inputCommentAsset,postTitle);
		waitOnElement("xpath",btnCommentAsset);
		clickOnElement("xpath", btnCommentAsset);
		
	}
	
	public boolean verifyCommentOnAsset(String comment) throws Exception{
		logInfo("inside verifyCommentOnAsset() method.");
		boolean isCommentFound = false;
		waitOnElement("xpath",commentsList);
		List<WebElement> comments = driver().findElements(By.xpath(commentsList));
		String before = "//*[@id='comments']/section/div[2]/div[";
		String after = "]/div/div[1]";
		for(int i=1; i<=comments.size(); i++){
			waitOnElement("xpath",before+i+after);
			WebElement commentTitle = driver().findElement(By.xpath(before+i+after));
			if(commentTitle.getText().trim().equalsIgnoreCase(comment)){
				isCommentFound = true;
				break;
			}
		}
		return isCommentFound;
	}
	
	//Select markets from the filter in RL Admin page.

		public void selectAssetTypeFilter(String assetType) throws Exception, Exception{
			logInfo("inside selectAssetTypeFilter() method"); 
			
			waitOnElement("xpath","//*[@id='resourceable_types_container']/button");
			clickOnElement("xpath","//*[@id='resourceable_types_container']/button");
			
			WebElement panel = driver().findElement(By.xpath(assetsTypeContainer));
			List<WebElement>  allItems = panel.findElements(By.tagName("li"));
			int all_lis = allItems.size();
			System.out.println("Total Lis =" +all_lis);
			
			String before_liName = "//div[@id='resourceable_types_container']/ul/li[";
			String after_liName = "]";
			
			for(int i=1;i<=all_lis;i++){
				waitOnElement("xpath",before_liName+i+after_liName);
				WebElement liName = driver().findElement(By.xpath(before_liName+i+after_liName));
				String itemName = liName.getText().trim();
				if(itemName.equalsIgnoreCase(assetType.trim())){
					System.out.println("market =" +itemName);
					liName.click();
					break;
				}
			}
		}
		
		
		public boolean verifyAddResourceValidations() throws Exception, Exception{
			logInfo("Inside verifyResourceValidationsAdd() method..");
			System.out.println("Inside verifyResourceValidationsAdd() method..");
			boolean isValidationsVerified = false;
			
			waitOnElement("linkText", "Add Resource");
			clickOnLink("linkText", "Add Resource");
			clickOnElement("cssSelector",btnNewResourceIconBrowse);
			uploadFile("Text", uploaded);
			clickOnLink("linkText","Next");
			clickOnButton("cssSelector",btnCreateNewResource);
			
			waitOnElement("xpath", "publishDateValidation");
			WebElement  el= driver().findElement(By.xpath(publishDateValidation));
			String validatePublishDate = el.getText().trim();
			if(validatePublishDate.equalsIgnoreCase(txtPublishDateValidation.trim())){
				isValidationsVerified = true;
				waitOnElement("xpath",btnPublishOK);
				clickOnElement("xpath",btnPublishOK);
				waitOnElement("cssSelector", "compPublishArticle");
				WebElement epub = driver().findElement(By.cssSelector(compPublishArticle));
				epub.click();
				
			}
			if(isValidationsVerified){
				waitOnElement("cssSelector", "btnCreateNewResource");
				clickOnButton("cssSelector",btnCreateNewResource);
				List<WebElement> validationMsgs = driver().findElements(By.xpath(lstResourceValidations));
				int j=0;
				for(int i=1;i<=validationMsgs.size();i++){
					WebElement message = driver().findElement(By.xpath("//*[@id='new_pyr_core_resource']/div[1]/ul/li["+i+"]"));
					String msg = message.getText().trim();
					if(msg.contains(resourceValidationMessages[j])){
						isValidationsVerified = true;
					}
					else{
						isValidationsVerified = false;
					}
					j++;
					
				}
			}
			
			return isValidationsVerified;
		}
		
		public boolean verifyAssetValidationsAdd() throws Exception, Exception{
			logInfo("Inside verifyAssetValidationsAdd() method..");
			System.out.println("Inside verifyAssetValidationsAdd() method..");
			boolean isValidationsVerified = false;
			waitOnElement("linkText", "Add Resource");
			clickOnLink("linkText", "Add Resource");
			
			waitOnElement("partialLinkText", "Upload Assets");
			clickOnLink("partialLinkText","Upload Assets");
			implicityWaits(5);
			waitOnElement("cssSelector",btnAddAsset);
			clickOnElement("cssSelector",btnAddAsset);
			waitOnElement("cssSelector", validateAssets);
			WebElement title= driver().findElement(By.cssSelector(validateAssets));
			String validateAssetTitle = title.getText().trim();
			if(validateAssetTitle.equalsIgnoreCase(txtValidateAssetName)){
				isValidationsVerified = true;
				clickOnElement("xpath",btnValidateOK);
				implicityWaits(5);
			}
			if(isValidationsVerified){
				inputText("cssSelector",inputAssetTitle, resourceAsset);
				clickOnButton("cssSelector",btnAddAsset);
				waitOnElement("cssSelector", validateAssets);
				WebElement  fileType= driver().findElement(By.cssSelector(validateAssets));
				String validateFileType = fileType.getText().trim();
				if(validateFileType.equalsIgnoreCase(txtValidateAssetFileType)){
					isValidationsVerified = true;
					clickOnButton("xpath",btnValidateOK);
					implicityWaits(5);
				}
			}
			if(isValidationsVerified){
				waitOnElement("cssSelector", drpdnAssetFileType);
				selectFromDropdown("cssSelector",drpdnAssetFileType,"byVisibleText",assetTypePDF);
				clickOnButton("cssSelector",btnAddAsset);
				waitOnElement("cssSelector", validateAssets);
				WebElement uploadfile= driver().findElement(By.cssSelector(validateAssets));
				String validateFile = uploadfile.getText().trim();
				if(validateFile.equalsIgnoreCase(txtValidateAssetFile)){
					isValidationsVerified = true;
					clickOnButton("xpath",btnValidateOK);
					implicityWaits(5);
				}
			}
			if(isValidationsVerified){
				if(assetTypePDF.equals("PDF")){
					uploadFile("Text",inputAssetPath);
					waitOnElement("cssSelector",validateAssets);
					WebElement fileType= driver().findElement(By.cssSelector(validateAssets));
					String validateFileType = fileType.getText().trim();
					if(validateFileType.equalsIgnoreCase(txtValidatePDFFile)){
						isValidationsVerified = true;
						clickOnButton("xpath",btnValidateOK);
						implicityWaits(5);
					}

				}
			}
			return isValidationsVerified;
		}
		
		public boolean verifyAssetValidationsEdit() throws Exception, Exception{
			logInfo("Inside verifyAssetValidationsEdit() method..");
			System.out.println("Inside verifyAssetValidationsEdit() method..");
			boolean isValidationsVerified = false;
			
			waitOnElement("xpath",lstAssets);
			List<WebElement> el = driver().findElements(By.xpath(lstAssets));
			String before = "//*[@id='resources_assets_list']/tbody/tr[";
			String after = "]/td[4]/div";
			
			String before_delete = "//*[@id='resources_assets_list']/tbody/tr[";
			String after_delete = "]/td[4]/div/ul/li[1]";
			
			for(int i=2;i<=el.size();i++){
				waitOnElement("xpath",before+i+after);
				WebElement editOptions = driver().findElement(By.xpath(before+i+after));
				editOptions.click();
				waitOnElement("xpath",before_delete+i+after_delete);
				WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
				delete.click();
				break;
			}
			waitOnElement("cssSelector",inputAssetTitle);
			inputTextClear("cssSelector",inputAssetTitle);
			waitOnElement("cssSelector",btnAddAsset);
			clickOnButton("cssSelector",btnAddAsset);
			waitOnElement("cssSelector", validateAssets);
			WebElement title= driver().findElement(By.cssSelector(validateAssets));
			String validateAssetTitle = title.getText().trim();
			if(validateAssetTitle.equalsIgnoreCase(txtValidateAssetName)){
				isValidationsVerified = true;
				clickOnButton("xpath",btnValidateOK);
			}

			if(isValidationsVerified){
				inputText("cssSelector",inputAssetTitle, resourceAsset);
				clickOnElement("xpath", editFilePath);
				clickOnButton("cssSelector",btnAddAsset);
				waitOnElement("cssSelector", validateAssets);
				WebElement  filePath= driver().findElement(By.cssSelector(validateAssets));
				String validateFilePath = filePath.getText().trim();
				if(validateFilePath.equalsIgnoreCase(txtValidateAssetFile)){
					isValidationsVerified = true;
					clickOnButton("xpath",btnValidateOK);
				}
			}
			return isValidationsVerified;
		}
		
		public boolean verifyResourceValidationsEdit() throws Exception, Exception{
			logInfo("Inside verifyResourceValidationsEdit() method..");
			System.out.println("Inside verifyResourceValidationsEdit() method..");
			boolean isValidationsVerified = false;
			
			clickOnButton("cssSelector",btnCreateNewResource);
			waitOnElement("xpath", "publishDateValidation");
			WebElement  el= driver().findElement(By.xpath(publishDateValidation));
			String validatePublishDate = el.getText().trim();
			if(txtPublishDateValidation.equalsIgnoreCase(txtPublishDateValidation)){
				isValidationsVerified = true;
				clickOnButton("xpath",btnPublishOK);
				waitOnElement("cssSelector", "#pyr_core_resource_publish_date");
				inputText("cssSelector","#pyr_core_resource_publish_date",getDate(2,"MM/dd/yyyy"));
			}
			if(isValidationsVerified){
				waitOnElement("cssSelector", "btnCreateNewResource");
				clickOnButton("cssSelector",btnCreateNewResource);
				List<WebElement> validationMsgs = driver().findElements(By.xpath(lstResourceValidations));
				int j=0;
				for(int i=1;i<=validationMsgs.size();i++){
					WebElement message = driver().findElement(By.xpath("//*[contains(@id,'edit_pyr_core_resource')]/div[1]/ul/li["+i+"]"));
					String msg = message.getText().trim();
					if(msg.contains(resourceValidationMessages[j])){
						isValidationsVerified = true;
					}
					else{
						isValidationsVerified = false;
					}
					j++;
					
				}
			}
			
			return isValidationsVerified;
		}
		
		public void clearAllResourceValues() throws Exception, Exception{
			logInfo("Inside verifyResourceValidationsEdit() method..");
			System.out.println("Inside verifyResourceValidationsEdit() method..");
			inputTextClear("cssSelector",inputNewResourceTitle);
			waitOnElement("xpath", lnkEditAsset);
			clickOnElement("xpath", lnkEditAsset);
			clearAssets();
//			clickOnLink("linkText","Next");
			waitOnElement("xpath","//*[@id='res_step_forward']");
			clickOnElement("xpath","//*[@id='res_step_forward']");
			clearResourceCategorySelections();
			unselectCheckboxes("//*[@id='market_all']");
			unselectCheckboxes("//*[@id='rank_all']");
			unselectCheckboxes("//*[@id='subscription_all']");
			/*unselectInputs1(divRankdeflist);
			unselectInputs(divMarketlist);
			unselectInputs(divSubscriptionlist);*/
			/*waitOnElement("xpath",drpdnNewResourceStatus);
			selectFromDropdown("cssSelector",drpdnNewResourceStatus,"byVisibleText","Select");*/
			/*WebElement publishResource = driver().findElement(By.cssSelector(compPublishArticle));
			publishResource.click();*/
			inputTextClear("cssSelector","#pyr_core_resource_publish_date");
			WebElement displayAsNewest = driver().findElement(By.xpath(chkNewResourceDisplayAsNewest));
			if(displayAsNewest.isSelected()){
				displayAsNewest.click();
			}
			//clickOnButton("cssSelector",btnCreateNewResource);
		}
		
		public void unselectCheckboxes(String checkAll){
			logInfo("Inside unselectCheckboxes() method.");
			System.out.println("Inside unselectCheckboxes() method.");
			WebElement e = driver().findElement(By.xpath(checkAll));
			if(e.isSelected()){
				e.click();
			}
		}
		public void clearAssets() throws Exception, Exception{
			logInfo("Inside clearAssets() method..");
			List<WebElement> el = driver().findElements(By.xpath(lstAssets));
			String before = "//*[@id='resources_assets_list']/tbody/tr[";
			String after = "]/td[4]/div";
			
			String before_delete = "//*[@id='resources_assets_list']/tbody/tr[";
			String after_delete = "]/td[4]/div/ul/li[2]";
			
			for(int i=2;i<=el.size()+1;i++){
				waitOnElement("xpath",before+i+after);
				WebElement edit = driver().findElement(By.xpath(before+i+after));
				edit.click();
				waitOnElement("xpath",before_delete+i+after_delete);
				WebElement delete = driver().findElement(By.xpath(before_delete+i+after_delete));
				delete.click();
				/*confirmEventDeleteModal();
				confirmResourceDeleteModal();*/
				Alert alert = driver().switchTo().alert();
				alert.accept();
				break;
			}
		}
		
		public void clearResourceCategorySelections() throws Exception, Exception{  
			logInfo("inside clearResourceCategorySelections() method");
			System.out.println("inside clearResourceCategorySelections() method");
			waitOnElement("xpath","//*[@id='categories_list']/li");
			List<WebElement> allRows = driver().findElements(By.xpath("//*[@id='categories_list']/li"));	// //*[@id='categories_list']/tbody
			
			int cntChecks = allRows.size();
			System.out.println("Total checks = " +cntChecks );			
			if(cntChecks>0){
				for(int i=1;i<=cntChecks;i++){
					WebElement e = driver().findElement(By.xpath("//*[@id='categories_list']/li["+i+"]"));
					WebElement pinput = driver().findElement(By.xpath("//*[@id='categories_list']/li["+i+"]/input"));
					if(pinput.isSelected()){
						pinput.click();	
					}
					
					List allInputs = e.findElements(By.tagName("input"));							
					System.out.println("i =" +i + ", inputs = " +allInputs.size());
					if(allInputs.size()>=2){
						for(int j=2;j<=allInputs.size();j++){
							WebElement ce = driver().findElement(By.xpath("//*[@id='categories_list']/li["+i+"]/ul/li[" + (j-1) +"]"));
							WebElement input = driver().findElement(By.xpath("//*[@id='categories_list']/li["+i+"]/ul/li[" + (j-1) +"]/input"));
							if(input.isSelected()){
								input.click();	
							}
						}
					}

				}
			 }
		}
		
		
		public boolean verifyClonedResourceValidations() throws Exception, Exception{
			logInfo("inside verifyClonedResourceValidations() method");
			System.out.println("inside verifyClonedResourceValidations() method");
			boolean isValidationsVerified = false;
			clickOnLink("linkText", "Next");
			waitOnElement("cssSelector", "btnCreateNewResource");
			clickOnButton("cssSelector",btnCreateNewResource);
			waitOnElement("cssSelector",resTitleValidate);
			WebElement title = driver().findElement(By.cssSelector(resTitleValidate));
			if(title.getText().trim().equalsIgnoreCase(txtResTitleValidation)){
				isValidationsVerified = true;
			}
			return isValidationsVerified;
		}
		
		public void verifyEnableResourceOptions() throws Exception{
			logInfo("inside verifyEnableOrDisableResourceOptions() method");
			System.out.println("inside verifyEnableOrDisableResourceOptions() method");
			clickOnLink("linkText", "Settings");
			enableOrDisableResourceOptions("Resource Market Filter","On");
			enableOrDisableResourceOptions("Enable Resource Asset Comments","On");
			enableOrDisableResourceOptions("Enable Resource Asset Reviews","On");
			enableOrDisableResourceOptions("Resources Viewed By Me","On");
			enableOrDisableResourceOptions("Resources Viewed By Others","On");
			enableOrDisableResourceOptions("Ability To View Past By Resources","");
			enableOrDisableResourceOptions("Resource Library Layout","Grid");
			enableOrDisableResourceOptions("Share Resources To Social Networks","On");
			enableOrDisableResourceOptions("Share Resources To Community","On");
			enableOrDisableResourceOptions("Share Resources To Pwp","On");
			clickOnButton("xpath", btnUpdateResourceOptions);
		}
		
		public void verifyDisableResourceOptions() throws Exception{
			logInfo("inside verifyDisableResourceOptions() method");
			System.out.println("inside verifyDisableResourceOptions() method");
			clickOnLink("linkText", "Settings");
			enableOrDisableResourceOptions("Resource Market Filter","Off");
			enableOrDisableResourceOptions("Enable Resource Asset Comments","Off");
			enableOrDisableResourceOptions("Enable Resource Asset Reviews","Off");
			enableOrDisableResourceOptions("Resources Viewed By Me","Off");
			enableOrDisableResourceOptions("Resources Viewed By Others","Off");
			enableOrDisableResourceOptions("Ability To View Past By Resources","Off");
			enableOrDisableResourceOptions("Resource Library Layout","Grid");
			enableOrDisableResourceOptions("Share Resources To Social Networks","Off");
			enableOrDisableResourceOptions("Share Resources To Community","Off");
			enableOrDisableResourceOptions("Share Resources To Pwp","Off");
			clickOnButton("xpath", btnUpdateResourceOptions);
		}
		
		public void enableOrDisableResourceOptions(String resourceOption, String enableOption) throws Exception{
			logInfo("inside verifyResourceOptions() method");
			System.out.println("inside verifyResourceOptions() method");
			switch(resourceOption){
			case "Resource Market Filter" :
				System.out.println("Resource Market Filter....");
				selectResourceOptionsSettings(resourceOption,enableOption);
				break;
			case "Enable Resource Asset Comments":
				System.out.println("Enable Resource Asset Comments...");
				selectResourceOptionsSettings(resourceOption,enableOption);
				break;
			case "Enable Resource Asset Reviews":
				System.out.println("Enable Resource Asset Reviews");
				selectResourceOptionsSettings(resourceOption,enableOption);
				break;
			case "Resources Viewed By Me" :
				System.out.println("Resources Viewed By Me");
				selectResourceOptionsSettings(resourceOption,enableOption);
				break;
			case "Resources Viewed By Others" :
				System.out.println("Resources Viewed By Others");
				selectResourceOptionsSettings(resourceOption,enableOption);
				break;
			case "Ability To View Past By Resources" :
				System.out.println("Ability To View Past By Resources");
				enableNewestResources();
				break;
			case "Resource Library Layout" :
				System.out.println("Resource Library Layout");
				selectResourceOptionsSettings(resourceOption,enableOption);
				break;
			
			}
		}
		
		public void selectResourceOptionsSettings(String resourceOption,String enable) throws Exception{
			logInfo("inside selectResourceOptionsSettings() method");
			System.out.println("inside selectResourceOptionsSettings() method");
			List<WebElement> el = driver().findElements(By.xpath(lstResourceOptions));
			String before_name = "//*[@id='main-content']/table/tbody/tr[";
			String after_name = "]/td[1]";
			
			String before_option = "//*[@id='main-content']/table/tbody/tr[";
			String after_option = "]/td[3]/select";
			
			String before_past_resources = "//*[@id='main-content']/table/tbody/tr[";
			String after_past_resources = "]/td[3]/input";
			
			for(int i=1;i<=el.size();i++){
				WebElement ele = driver().findElement(By.xpath(before_name+i+after_name));
				String optionText = ele.getText().trim();
				if(optionText.equalsIgnoreCase(resourceOption)){
					if(!resourceOption.equalsIgnoreCase("Ability To View Past By Resources")){
						String option = before_option+i+after_option;
						selectFromDropdown("xpath",option,"byVisibleText",enable);
						break;
					}
					else{
						inputText("xpath", before_past_resources+i+after_past_resources, "14");
					}
					
				}
			}
			
		}
		
		public void enableNewestResources() throws Exception, Exception{
			logInfo("inside enableNewestResources() method");
			System.out.println("inside enableNewestResources() method");
			waitOnElement("xpath","//*[@type='text']");
			inputTextClear("xpath","//*[@type='text']");
		}
		
		public boolean verifyResourcesViewedByMe(String resource) throws Exception, Exception{
			logInfo("inside verifyResourcesViewedByMe() method");
			System.out.println("inside verifyResourcesViewedByMe() method");
			boolean isResourceFound = false;
			waitOnElement("linkText", "You've Viewed");
			clickOnElement("linkText", "You've Viewed");
			isResourceFound = verifyResource(resource);
			return isResourceFound;
		}
		
		public boolean verifyResourcesViewedByOthers(String resource) throws Exception, Exception{
			logInfo("inside verifyResourceOptions() method");
			System.out.println("inside verifyResourceOptions() method");
			boolean isResourceFound = false;
			waitOnElement("linkText", "Popular");
			clickOnLink("linkText", "Popular");
			isResourceFound = verifyResource(resource);
			return isResourceFound;
		}
		
		public boolean verifyResourceCountOnPagination() throws Exception, Exception{
			logInfo("inside verifyResourceCountOnPagination() method");
			System.out.println("inside verifyResourceCountOnPagination() method");
			boolean isResourcesCountVerified = false;
			waitOnElement("cssSelector", resourceItems);
			List <WebElement> resources = driver().findElements(By.cssSelector(resourceItems));
			int resSize = resources.size();
			if(resSize<=10){
				isResourcesCountVerified = true;
				clickOnElement("linkText", "Next");
				List <WebElement> resourcesNextPage = driver().findElements(By.cssSelector(resourceItems));
				if(resourcesNextPage.size()<=10){
					isResourcesCountVerified = true;
				}
			}
						
			return isResourcesCountVerified;
		}

		public boolean verifyCategoryDuplication(String category) throws Exception{
			logInfo("inside verifyCategoryDuplication() method");
			System.out.println("inside verifyCategoryDuplication() method");
			boolean isCategoryNameVerified = false;
			verifyLinkPresent("Add New Category");
			clickOnLink("linkText", "Add New Category");
			logInfo("clicked 'Add New Category' link in Manage Categories page.");
			
			waitOnElement("cssSelector",inputAddNewCategory);
			inputText("cssSelector",inputAddNewCategory,category);
			waitOnElement("cssSelector",btnCreateResourceCategory);
			clickOnButton("cssSelector",btnCreateResourceCategory);	
			waitOnElement("cssSelector",resTitleValidate);
			WebElement categoryName = driver().findElement(By.cssSelector(resTitleValidate));
			if(categoryName.getText().trim().equalsIgnoreCase(txtResTitleValidation)){
				isCategoryNameVerified = true;
			}
			return isCategoryNameVerified;
		}
		
		public void verifyMaxSizeFileUpload() throws Exception{
			logInfo("inside verifyMaxSizeFileUpload() method");
			System.out.println("inside verifyMaxSizeFileUpload() method");
			waitOnElement("xpath",lnkEmail);
			driver().findElement(By.xpath(lnkEmail)).click();
			driver().findElement(By.xpath(btnComposeEmailAsAttachment)).click();
			sendMailWithRecipient(vibeRecipient_text);
   	
		//	clickOnElement("xpath", browseInBr);		    	
	    	uploadFile("MoreThan4MBPDF", browseInBr);
			waitOnElement("xpath",btnAttach2Message);  
			clickOnButton("xpath",btnAttach2Message);  
			confirmationMessage("5mb_file.pdf could not be saved.\n Max file size is 4MB");
		}
		
		public boolean verifyRemoveAttchment() throws Exception{
			logInfo("inside verifyRemoveAttchment() method");
			System.out.println("inside verifyRemoveAttchment() method");
			boolean isRemoveAttachmentVerified = false;
			waitOnElement("xpath",lnkEmail);
			driver().findElement(By.xpath(lnkEmail)).click();
			driver().findElement(By.xpath(btnComposeEmailAsAttachment)).click();
			sendMailWithRecipient(vibeRecipient_text);
			
			waitOnElement("xpath", browseInMas);	
				    	
			uploadFile("Audio", browseInMas);
			waitOnElement("xpath",btnAttach2Message);  
			clickOnButton("xpath",btnAttach2Message);  
			
	    	waitOnElement("xpath",filesList);
			List<WebElement> el = driver().findElements(By.xpath(filesList));
			String before = "//div[@class='attachment'][";
			String after = "]/div[2]/a";
			
			for(int i=1;i<=el.size();i++){
				WebElement file = driver().findElement(By.xpath(before+i+after));
				if(file.getText().trim().equalsIgnoreCase("Audio_Icon3.jpg")){
					WebElement ddlRemoveAttchment = driver().findElement(By.xpath("//div[@class='attachment']["+i+"]/div[4]/div"));
					ddlRemoveAttchment.click();
					
					WebElement removeAttachment = driver().findElement(By.xpath("//div[@class='attachment']["+i+"]/div[4]/div/ul/li/a"));
					removeAttachment.click();
					break;
				}
			}
			waitOnElement("xpath",filesList);
			List<WebElement> el1 = driver().findElements(By.xpath(filesList));
			for(int i=1;i<=el1.size();i++){
				waitOnElement("xpath",before+i+after);
				WebElement file = driver().findElement(By.xpath(before+i+after));
				if(file.getText().trim().equalsIgnoreCase("icentris_audio.wav")){
					isRemoveAttachmentVerified = true;
					break;
				}
			}
			return isRemoveAttachmentVerified;
		}
		
		/*public boolean filterResourceByTrainingAssets(String trainingAsset) throws Exception, Exception{
			logInfo("inside filterResourcesByTrainingAssets() method");
			System.out.println("inside filterResourcesByTrainingAssets() method");
			boolean isResourceFound = false;
			nav2marketBusinessRL();
			
			waitOnElement("cssSelector",resListViewAdmin);
			clickOnElement("cssSelector",resListViewAdmin);
			
			selectAssetTypeFilter("Training Assets");
			waitOnElement("xpath","//*[@id='resource-table']/tbody/tr[contains(@class,'parent_tr')]");
			List<WebElement> tblRes = driver().findElements(By.xpath("//*[@id='resource-table']/tbody/tr[contains(@class,'parent_tr')]"));			
			
			String before_name = "//*[@id='resource-table']/tbody[";
			String after_name = "]/tr[contains(@class,'parent_tr')]/td[2]/a";
	
			int i=1;
			while(i<=tblRes.size()+2){
				WebElement n = driver().findElement(By.xpath(before_name+i+after_name));
				String name = n.getText().trim();
				if(name.contains(trainingAsset)){
					isResourceFound = true;
					n.click();
					break;
				}			

				i=i+2;
			}
			
			return isResourceFound;
		}*/
		
		public boolean filterResourceBySpreeAssets(String spreeAssetTitle) throws Exception, Exception{
			logInfo("inside filterResourceBySpreeAssets() method");
			System.out.println("inside filterResourceBySpreeAssets() method");
			boolean isResourceFound = false;
			
			nav2marketBusinessRL();
			selectAssetTypeFilter("Spree Assets");
			waitOnElement("cssSelector",resListViewAdmin);
			clickOnElement("cssSelector",resListViewAdmin);
			
			WebElement tblRes = driver().findElement(By.xpath("//*[@id='resource-table']/tbody"));
			List allRows = tblRes.findElements(By.tagName("tr"));
			int all_rows = allRows.size();
			
			String before_name = "//*[@id='resource-table']/tbody/tr[";
			String after_name = "]/td[2]/a";
			
					
			for(int i=1;i<=all_rows;i++){
				WebElement n = driver().findElement(By.xpath(before_name+i+after_name));
				String name = n.getText().trim();
				if(name.contains(spreeAssetTitle)){
					isResourceFound = true;
					n.click();
					break;
				}
			}
			
			return isResourceFound;
		}
		
		public boolean searchResourceListView(String resText) throws Exception{
			logInfo("inside searchResourceListView() method");
			System.out.println("inside searchResourceListView() method");
			boolean isResourceFound = false;
			
			verifyParentCategory(parentCategory_text1);
			waitOnElement("cssSelector",resListViewAdmin);
			clickOnElement("cssSelector",resListViewAdmin);
			searchResource(searchResourceText);
			
			waitOnElement("xpath",listResources);
			List<WebElement> resources = driver().findElements(By.xpath(listResources));
			String before = "//*[@id='index_page']/div[1]/table/tbody[";
			String after = "]/tr/td[2]/a";
			int i=1;
			while(i<=resources.size()+2){
				waitOnElement("xpath",before+i+after);
				 WebElement res = driver().findElement(By.xpath(before+i+after));
				 if (res.getText().trim().contains(resText)){				 
					 isResourceFound = true;
					 break;
				 }
				 i=i+2;
			}	
			if(isResourceFound){
				waitOnElement("xpath",txtSearchResource);
				inputTextClear("xpath", txtSearchResource);
				waitOnElement("xpath",listResources);
				int j=1;
				while(i<=resources.size()+2){
					waitOnElement("xpath",before+i+after);
					 WebElement res = driver().findElement(By.xpath(before+j+after));
					 if (res.getText().trim().contains(resText)){				 
						 isResourceFound = true;
						 break;
					 }
					 j=j+2;
				}
			}
			return isResourceFound;
		}
   
		
		public void createSpreeAsset(String assetTitle) throws Exception{
			logInfo("inside createSpreeAsset() method");
			System.out.println("inside createSpreeAsset() method");
			sm2.navigate2AdminShop();
			sm2.createNewVirtualProduct(assetTitle);
			sm2.navigate2AdminShop();
			boolean isProdFound = sm2.searchProductByNameOrSKU("Product Name",assetTitle,"All");
			if(isProdFound){
				logInfo("product match found in search page.");
				sm2.selectProductByName(assetTitle,"All");
				updateProductAssets(assetTitle);
			
			}
		}
		
		public void updateProductAssets(String assetTitle) throws Exception, Exception{
	    	logInfo("inside updateProductAssets() method.");
	    	System.out.println("inside updateProductAssets() method.");
	    	clickOnElement("xpath",lnkShopAssets);
	    	
	    	// Add New Document
	    	
	    	clickOnLink("linkText","New Document");
	    	waitOnElement("xpath",inputProdAssetTitle);
	    	inputText("xpath",inputProdAssetTitle,assetTitle);	    	
	    	uploadFile("PDF", browseDocument);
			clickOnElement("xpath",btnCreateProductAsset);
		}
		
		public void createTrainingAsset(String trainingCategory, String trainingSeries, String trainingAsset) throws Exception{
			logInfo("inside createTrainingAsset() method.");
	    	System.out.println("inside createTrainingAsset() method.");
	    	tm.go2TrainingPage();
	    	tm.addTrainingCategory(trainingCategory,ranker2,language1,txtSubPlanProMonthly);
			boolean isCategoryFound = tm.verifyCategoryPresent(trainingCategory);
			if(isCategoryFound){
				tm.go2AddSeriesPage(trainingCategory);
				logInfo("Adding series without dependency.");
				tm.addTrainingSeries(trainingSeries,seriesDesc1);
				boolean isSeriesFound = tm.verifySeriesPresent(trainingSeries);
				if(isSeriesFound){
					logInfo("Adding training without dependency.");
					tm.addTraining2Series(trainingSeries,trainingAsset,trainingDesc,fileType,uploadPdfPath1);
					boolean isTrainingFound = tm.verifyTrainingPresent(trainingAsset);
					if(!isTrainingFound){
						Assert.assertTrue(isTrainingFound, trainingAsset + " training could not be added.");
					}
				}
				else{
					Assert.assertTrue(isSeriesFound, trainingSeries + " series could not be added.");
				}
			}
			else{
				Assert.assertTrue(isCategoryFound, trainingCategory + " category could not be added.");
			}
			
			logInfo("Added single training series successfully.");
		}
		
		public void downloadAndVerifyPresentationAsset() throws Exception, Exception{
			logInfo("inside downloadAndVerifyPresentationAsset() method.");
	    	System.out.println("inside downloadAndVerifyPresentationAsset() method.");
	    	
	    	waitOnElement("cssSelector",".download>span");
	    	clickOnElement("cssSelector",".download>span");
	    //	downloadFile();
	    	/*if(inbox.verifyFileExistsOnDisk(filepath+"icentris_ppt.pptx")){
				isFileExists = true;
			} */

		}
		
		public boolean updateResourceRanksAndVerify(String category,String resource) throws Exception, Exception{
			logInfo("inside updateResourceRanksAndVerify() method.");
	    	System.out.println("inside updateResourceRanksAndVerify() method.");
	    	boolean isRanksVerified = false;
	    	waitOnElement("linkText","Next");
	    	clickOnElement("linkText","Next");
	    	selectRankDefs();
	    	clickOnElement("xpath","//*[@id='pyr_core_resource_submit']");
	    	nav2marketBusinessRL();
	    	selectCategoryFilter(category);
			editResourceAdmin(resource);
			waitOnElement("linkText","Next");
	    	clickOnElement("linkText","Next");
	    	
	    	List<WebElement> allRanks = driver().findElements(By.xpath("//*[@id='rank_definition_checkboxes']/div"));
			int cntRanks = allRanks.size();
			System.out.println("Total checks = " +cntRanks );
			
			String before_rankName = "//*[@id='rank_definition_checkboxes']/div[";
			String after_rankName = "]/div[1]";
			
			String before_rankInput = "//*[@id='rank_definition_checkboxes']/div[";
			String after_rankInput = "]/div[1]/input[2]";

			int rankList = rankdeflist.length;
			System.out.println("list size =" +rankList);
			
			
			for(int i=1;i<=cntRanks;i++){
				for(int j=0;j<=rankList-1;j++){
					WebElement erank = driver().findElement(By.xpath(before_rankName+i+after_rankName));
					String rankName = erank.getText().trim();
					System.out.println("Rank Name = " +rankName);
					
					WebElement einput = driver().findElement(By.xpath(before_rankInput+i+after_rankInput));
					// String isSelected = einput.getAttribute("checked").trim();
					//System.out.println("status=" +status);
					if(rankName.equalsIgnoreCase(rankdeflist[j]) ){  // && isSelected.equalsIgnoreCase("false")
						if(einput.isSelected()){
							isRanksVerified=true;
							break;
						}
					}  
				}
			}
			return isRanksVerified;
	    	
		}
		
		
		
   
		
}

