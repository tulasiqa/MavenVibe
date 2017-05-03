package vibe.ecards.tests;


import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;


import common.TestBase;

import vibe.myprofile.tests.MyProfileMethods;
import vibe.resourcelibrary2.tests.RL2Methods;
import vibe.shopping.tests.ShoppingMethods;

import org.openqa.selenium.By.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

    public class EcardMethods extends TestBase {    	
    	  	
    	RL2Methods rl2 = new RL2Methods();
    	ShoppingMethods sm = new ShoppingMethods();
    	MyProfileMethods profile = new MyProfileMethods();

    	
    	public void nav2AdminEcard() throws Exception{
    		logInfo("Entered into nav2Ecard() method");
    		driver().navigate().to(appUrl + "admin/crm/ecard_templates"); 
    		
    	}	 

	
	public void nav2Ecard() throws Exception{
		logInfo("Entered into nav2Ecard() method");
		driver().navigate().to(appUrl + "crm/ecard_templates"); 
		Thread.sleep(2000);		
	}
	
	public void nav2EcardTemp() throws Exception{
		logInfo("Entered into nav2Ecard() method");
		driver().navigate().to(appUrl + "admin/crm/ecard_templates/new?size=640X480"); 
		Thread.sleep(2000);		
	}
	
	public void nav2EcardCategories() throws Exception{
		logInfo("Entered into nav2EcardCategories() method");
		nav2AdminEcard();
		listOfPanelTitles("Manage Categories");
	
	}
	
	
	public void filterCategory(String eCategoryName) throws Exception{
		logInfo("Entered into filterCategory() method ");		
		clickOnElement("cssSelector", catDrops);
		Thread.sleep(2000);
		boolean isCatPresent = false;
		List <WebElement> cat = driver().findElements(By.cssSelector(catDropsList));
		System.out.println(cat.size());
		for (int i=3; i<= cat.size()+3; i++){
			WebElement catName = driver().findElement(By.cssSelector(catDropsListBfr+i+catDropsListAft));
			String category = catName.getText().trim();
			System.out.println(category);
			if (category.equalsIgnoreCase(eCategoryName)){
				isCatPresent =true;
				catName.click();
				break;		
			}
			
		}if (isCatPresent==false){
			Assert.assertTrue(isCatPresent, eCategoryName + " - category is not present in dropdown.");
		}
		
		
		
	}
	
	public void filterMarket(String eMarket) throws Exception{
		logInfo("Entered into filterMarket() method ");
		
		clickOnElement("cssSelector", markDrops);
		Thread.sleep(2000);
		boolean isMarketPresent = false;
		List <WebElement> cat = driver().findElements(By.cssSelector(markDropsList));
		System.out.println(cat.size());
		for (int i=3; i<= cat.size()+3; i++){
			WebElement marName = driver().findElement(By.cssSelector(markDropsListBfr+i+catDropsListAft));
			String market = marName.getText().trim();
			System.out.println(market);
			if (market.equalsIgnoreCase(eMarket)){
				isMarketPresent =true;
				marName.click();
				break;		
			}
			
		}if (isMarketPresent==false){
			Assert.assertTrue(isMarketPresent, eMarket + " - Market is not present in dropdown.");
		}
		
		
	}
	
	public void filterSubscription(String eSubscrption) throws Exception{
		logInfo("Entered into filterSubscription() method ");
		
		clickOnElement("cssSelector", subDrops);
		Thread.sleep(2000);
		boolean isSubPresent = false;
		List <WebElement> cat = driver().findElements(By.cssSelector(subDropsList));
		System.out.println(cat.size());
		for (int i=3; i<= cat.size()+3; i++){
			WebElement subName = driver().findElement(By.cssSelector(subDropsListBfr+i+catDropsListAft));
			String subscp = subName.getText().trim();
			System.out.println(subscp);
			if (subscp.equalsIgnoreCase(eSubscrption)){
				isSubPresent =true;
				subName.click();
				break;	
			}
			
		}if (isSubPresent==false){
			Assert.assertTrue(isSubPresent, eSubscrption + " - Subscription is not present in dropdown.");
		}
	 }
	
	public void filterRanks(String eRanker) throws Exception{
		logInfo("Entered into filterRanks() method ");
		
		clickOnElement("cssSelector", rankDrops);
		Thread.sleep(2000);
		boolean isRankPresent = false;
		List <WebElement> ra = driver().findElements(By.cssSelector(rankDropsList));
		System.out.println(ra.size());
		for (int i=3; i<= ra.size()+3; i++){
			WebElement rankName = driver().findElement(By.cssSelector(rankDropsListBfr+i+catDropsListAft));
			String ranker = rankName.getText().trim();
			System.out.println(ranker);
			if (ranker.equalsIgnoreCase(eRanker)){
				isRankPresent =true;
				rankName.click();
				break;		
			}
			
		}if (isRankPresent==false){
			Assert.assertTrue(isRankPresent, eRanker + " - Ranker is not present in dropdown.");
		}
	}
	
	public void selectCardSize(int size_1_to_4) throws Exception{
		logInfo("Entered into selectCardSize(int size_1_to_4) method ");
		Thread.sleep(2000);
		WebElement testMsg = driver().findElement(By.cssSelector(sizeInfo));
		Assert.assertEquals(testMsg.getText().trim(), sizeInfoText);	
		List <WebElement> card = driver().findElements(By.cssSelector(cardSizes));
		System.out.println("new cards size "+card.size());
		if ((size_1_to_4<=0)||(size_1_to_4>4)){
		WebElement cardSize = driver().findElement(By.cssSelector(cardSizesBfr+size_1_to_4+cardSizesAfr));
		Actions act = new Actions(driver());
		act.doubleClick(cardSize).build().perform();	
		cardSize.click();
		
		}		
	}
	
	//Selects panel tabs of List Ecard Templates like NewEcard, Setting , Managecat, Background etc.
	public void listOfPanelTitles(String listPanel){
		logInfo("Entered into listOfPanelTitles() method ");
		boolean isListPresent = false;
		List <WebElement> li = driver().findElements(By.cssSelector(mangCatLis));
		System.out.println("size us "+li.size());
		for (WebElement lis :li){
			System.out.println(lis.getText());
			if (lis.getText().equalsIgnoreCase(listPanel)){
				isListPresent=true;
				lis.click();
				break;			
			}
			
		}if (isListPresent==false){
			Assert.assertTrue(isListPresent, listPanel + " panel is not found under Listing Ecard Templates");
		}
		
		
	}
	
	public void addEcardCategory(String catName, String parentCat) throws Exception{
		logInfo("Entered into addEcardCategory() method ");
		
		listOfPanelTitles("Manage Categories");		
		waitOnElement("cssSelector", eAddNewCat);
		clickOnButton("cssSelector", eAddNewCat);
		waitOnElement("cssSelector",eCatName);	
		inputText("cssSelector",eCatName,catName);		
		selectFromDropdown("cssSelector",eCatParSelect,"byVisibleText",parentCat);
		waitOnElement("cssSelector",eCardCreate);	
		clickOnButton("cssSelector",eCardCreate);	
		confirmationMessage("Ecards Category created successfully.");
	}
	
	
	public boolean validateCategories(String category, String subCategory) throws Exception{
		logInfo("Entered into validateCategories() method ");		
		nav2EcardCategories();		
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));
		System.out.println("size"+ cat.size());	
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){
				isParentPresent = true;
				WebElement plusClick = driver().findElement(By.cssSelector(catListBfr+i+catListPlus));
				plusClick.click();
				Thread.sleep(3000);
				
				List <WebElement> subCat = driver().findElements(By.cssSelector(catListBfr+i+subCatList));
				System.out.println("Sub cat Size "+ subCat.size());
				boolean isSubPresent = false;
				for (int j =1; j<=subCat.size(); j++){
					WebElement subCatNm = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+subCatListAfr));
					String subCatgName  = subCatNm.getText().trim(); 
					System.out.println("subCatgName "+ subCatgName);
					if (subCatgName.equalsIgnoreCase(subCategory)){
						isSubPresent = true;
						System.out.println("Sucess!! presnet");						
						break;					
					}					
				}if(isSubPresent==false){
					Assert.assertTrue(isSubPresent, subCategory + " Subcategory is not present under "+category);
				}				
				break;
			}			
		}if(isParentPresent==false){
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}
		return isParentPresent;			
	}
	
	
	public boolean updateChildCategories(String category, String subCategory, String updateCategory) throws Exception{
		logInfo("Entered into updateChildCategories() method ");		
		nav2EcardCategories();		
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));
		System.out.println("size"+ cat.size());	
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){
				isParentPresent = true;
				WebElement plusClick = driver().findElement(By.cssSelector(catListBfr+i+catListPlus));
				plusClick.click();
				Thread.sleep(3000);				
				List <WebElement> subCat = driver().findElements(By.cssSelector(catListBfr+i+subCatList));
				System.out.println("Sub cat Size "+ subCat.size());
				boolean isSubPresent = false;
				for (int j =1; j<=subCat.size(); j++){
					WebElement subCatNm = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+subCatListAfr));
					String subCatgName  = subCatNm.getText().trim(); 
					System.out.println("subCatgName "+ subCatgName);
					if (subCatgName.equalsIgnoreCase(subCategory)){
						isSubPresent = true;
						System.out.println("Sucess!! presnet");							
						WebElement actionClick = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+catActionAfr));
						actionClick.click();
						Thread.sleep(3000);
						WebElement edit = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+catActionAfr+catActionEdit));
						edit.click();
						Thread.sleep(3000);
						inputTextClear("cssSelector",eCatName);
						inputText("cssSelector",eCatName,updateCategory);
						Thread.sleep(3000);
						clickOnButton("cssSelector", updateCat);
						confirmationMessage("Ecard Category updated successfully.");	
						Thread.sleep(4000);
						break;					
					}					
				}if(isSubPresent==false){
					Assert.assertTrue(isSubPresent, subCategory + " Subcategory is not present under "+category);
				}				
				break;
			}			
		}if(isParentPresent==false){
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}
		return isParentPresent;			
	}
	
	
	public void deleteChildCategories(String category, String subCategory) throws Exception{
		logInfo("Entered into deleteChildCategories() method ");		
		nav2EcardCategories();		
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));
		System.out.println("size"+ cat.size());	
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){
				System.out.println("pCatgName "+pCatgName );
				isParentPresent = true;
				WebElement plusClick = driver().findElement(By.cssSelector(catListBfr+i+catListPlus));
				plusClick.click();
				Thread.sleep(4000);
				List <WebElement> subCat = driver().findElements(By.cssSelector(catListBfr+i+subCatList));
				System.out.println("Sub cat Size "+ subCat.size());
				boolean isSubPresent = false;
				for (int j =1; j<=subCat.size(); j++){
					WebElement subCatNm = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+subCatListAfr));
					String subCatgName  = subCatNm.getText().trim(); 
					System.out.println("subCatgName "+ subCatgName);
					if (subCatgName.equalsIgnoreCase(subCategory)){
						isSubPresent = true;
						System.out.println("Sucess!! presnet");							
						WebElement actionClick = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+catActionAfr));
						actionClick.click();
						Thread.sleep(3000);
						WebElement delete = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+catActionAfr+catActionDelete));
						delete.click();
						Thread.sleep(3000);
						confirmToDelete();
						confirmationMessage("Ecard Category deleted successfully.");	
						Thread.sleep(3000);
						break;					
					}					
				}if(isSubPresent==false){
					Assert.assertTrue(isSubPresent, subCategory + " Subcategory is not present under "+category);
				}				
				break;
			}			
		}if(isParentPresent==false){
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}
				
	}
	
	public boolean NotPresentChildCategories(String category, String subCategory) throws Exception{
		logInfo("Entered into updateChildCategories() method ");		
		nav2EcardCategories();		
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));
		System.out.println("size"+ cat.size());	
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){
				isParentPresent = true;
				WebElement plusClick = driver().findElement(By.cssSelector(catListBfr+i+catListPlus));
				plusClick.click();
				Thread.sleep(3000);				
				List <WebElement> subCat = driver().findElements(By.cssSelector(catListBfr+i+subCatList));
				System.out.println("Sub cat Size "+ subCat.size());
				boolean isSubPresent = true;
				for (int j =1; j<=subCat.size(); j++){
					WebElement subCatNm = driver().findElement(By.cssSelector(catListBfr+i+subCatListBfr+j+subCatListAfr));
					String subCatgName  = subCatNm.getText().trim(); 
					System.out.println("subCatgName "+ subCatgName);
					if (subCatgName.equalsIgnoreCase(subCategory)){
						isSubPresent = false;
						 Assert.assertTrue(isSubPresent, subCategory + " Subcategory is still present under "+category);
						Thread.sleep(4000);
						break;					
					}					
				}if(isSubPresent==true){
					System.out.println("Sucess!! Not present Subcategory under "+category);
					
				}				
				break;
			}			
		}if(isParentPresent==false){
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}
		return isParentPresent;			
	}
	
	
	public void deleteParentCategories(String category) throws Exception{
		logInfo("Entered into deleteParentCategories() method ");		
		nav2EcardCategories();		
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));
		System.out.println("size"+ cat.size());	
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){
				isParentPresent = true;				
						System.out.println("Sucess!! presnet");							
						WebElement actionClick = driver().findElement(By.cssSelector(catListBfr+i+catActionAfr));
						actionClick.click();
						Thread.sleep(3000);
						WebElement delete = driver().findElement(By.cssSelector(catListBfr+i+catActionAfr+catActionDelete));
						delete.click();
						Thread.sleep(3000);
						confirmToDelete();
						confirmationMessage("Ecard Category deleted successfully.");	
						Thread.sleep(3000);
						break;						
			}			
		}if(isParentPresent==false){
			Assert.assertTrue(isParentPresent, category + " Parent Category is not present.");
		}
				
	}
	
	public void NotPresentParentCategories(String category) throws Exception{
		logInfo("Entered into NotPresentParentCategories() method ");		
		nav2EcardCategories();		
		List <WebElement> cat = driver().findElements(By.cssSelector(catList));
		System.out.println("size"+ cat.size());	
		boolean isParentPresent = true;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(catListBfr+i+catListAfr));
			String pCatgName  = catNm.getText().trim();
			if (pCatgName.equalsIgnoreCase(category)){		
						isParentPresent = false;
						Assert.assertTrue(isParentPresent, category + " Subcategory is still present under "+category);
						Thread.sleep(4000);
						break;					
					}					
				}if(isParentPresent==true){
					System.out.println("Sucess!! Not present in category  ");
					
				}				
						
	}
	
	
	
    
    public void confirmToDelete()throws IOException, AWTException {
    	logInfo("Entered into confirmToDelete() method ");		
		try{
			Thread.sleep(2000);
			clickOnButton("cssSelector", btnDeleteResourceOk);
			Thread.sleep(2000);			
		} 
		catch (Exception e) {
			logInfo("Failed!! unable to click on confirmDeleteModal");		
		}
	}
    
    
	   public void createECard(String ecardName,String category, String childCategory ) throws Exception{
		   logInfo("Entered into createECard() method ");		
			String status = "Active";
		   clickOnButton("cssSelector", newCard);
			//selectCardSize(size_1_to_4);
			nav2EcardTemp();
			waitOnElement("cssSelector", eName);	
			WebElement nam = driver().findElement(By.cssSelector(eName));
			WebElement desc = driver().findElement(By.cssSelector(eDescrp));
			nam.clear();
			desc.clear();
			nam.sendKeys(ecardName);
			desc.sendKeys(ecardName);
			
			if ((driver().getCurrentUrl().contains("monat"))||
				(driver().getCurrentUrl().contains("idlife"))){
			     selectFromDropdown("id", ecardStatus, "byIndex", "1");
				}
			clickOnElement("cssSelector", pubNow);
			waitOnElement("cssSelector", browseBtn);		
			uploadFile("Image", browseBtn);
			selectCatInThemeSetting(category,childCategory);			
			waitOnElement("cssSelector", ePublish);		
			clickOnElement("cssSelector", ePublish);
			confirmationMessage("Ecard template was successfully created.");
				
		}
    
    public void selectCatLinkOptions(String catOptions){
    	logInfo("Entered into selectCatLinkOptions() method ");		
    	List <WebElement> op = driver().findElements(By.cssSelector(eCatLinkOptins));
    	System.out.println("link option size "+op.size());
    	boolean isOptPresnce = false;
    	for (WebElement opt :op){
    		System.out.println(opt.getText().trim());
    		if(opt.getText().trim().equalsIgnoreCase(catOptions)){
    			isOptPresnce=true;
    			opt.click();
    			break;
    		}
    		
    	}if (isOptPresnce==false){
    		Assert.assertTrue(isOptPresnce, catOptions +" link is not present in Theme Settings");
    	}
    	
    }
    
    public void selectCatInThemeSetting(String category, String childCategory) throws Exception{
    	logInfo("Entered into selectCatInThemeSetting() method ");
    	
    	selectCatLinkOptions("Categories");
    	selectParentCateInThemeSettings(category);
    	//selectChildCateInThemeSettings(category, childCategory);
    }
    
    
    public void selectParentCateInThemeSettings(String category) throws Exception{
		logInfo("Entered into selectParentCateInThemeSettings() method ");		
		Thread.sleep(3000);	
		List <WebElement> cat = driver().findElements(By.cssSelector(eCatChk));
		System.out.println("size"+ cat.size());	
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(eCatChkBfr+i+eCatChkAft));
			String pCatgName  = catNm.getText().trim();
			System.out.println("pCatgName "+pCatgName);
			if (pCatgName.contains(category)){		
						isParentPresent = true;
						WebElement catNmChk = driver().findElement(By.cssSelector(eCatChkBfr+i+eCatChkBoxAft));
						if (!catNmChk.isSelected()){
							catNmChk.click();						
						}						
						break;					
					}					
				}if(isParentPresent==false){
					Assert.assertTrue(isParentPresent, category + " category is not present under theme settings");										
				}				
						
	}
    
    public void selectChildCateInThemeSettings(String category, String childCategory) throws Exception{
		logInfo("Entered into selectParentCateInThemeSettings() method ");		
			
		List <WebElement> cat = driver().findElements(By.cssSelector(eCatChk));
		System.out.println("size"+ cat.size());	
		boolean isParentPresent = false;
		for (int i=1; i<=cat.size(); i++){			
			WebElement catNm = driver().findElement(By.cssSelector(eCatChkBfr+i+eCatChkAft));
			String pCatgName  = catNm.getText().trim();
			System.out.println("pCatgName "+pCatgName);
			if (pCatgName.contains(category)){		
						isParentPresent = true;
						//WebElement catNmChk = driver().findElement(By.cssSelector(eCatChkBfr+i+eChildCatChkBoxAft));
						List <WebElement> childCat = driver().findElements(By.cssSelector(eCatChkBfr+i+eChildCatChkBoxAft));
						System.out.println("Child cat Size "+childCat.size());
						boolean isChild = false;
						for (WebElement cc :childCat ){
							System.out.println("child cat are "+cc.getText());
							String cCatgName  = cc.getText().trim();
							if (cCatgName.equalsIgnoreCase(childCategory)){	
								isChild = true;
								WebElement childCatBox = driver().findElement(By.xpath(eChildCatChkBoxAftx+i+eChildCatChkBoxAft2x));
								if (!childCatBox.isSelected()){
									childCatBox.click();
									Thread.sleep(4000);
								}						
								break;	
							}
						}if(isChild==false){
							Assert.assertTrue(isChild, childCategory + " category is not present under "+category);										
						}
						
						
					break;	
										
					}					
				}if(isParentPresent==false){
					Assert.assertTrue(isParentPresent, category + " category is not present under theme settings");										
				}				
						
	}
	
  
	//users Side Test methods
	public void verifyCategoryName(String categoryName){
		logInfo("Enetered into verifyCategoryName(String categoryName) method");
		
		boolean isCatPresent= false;
		List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCat));
		System.out.println("list is "+ lib.size());
		for (int i = 1; i<=lib.size(); i++ ){			
			WebElement catName = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatAft));
			System.out.println("Name are "+ catName.getText());
			if (catName.getText().equalsIgnoreCase(categoryName)){
				isCatPresent =true;	
				break;				
			}			
		}if (isCatPresent==false){
			Assert.assertTrue(isCatPresent, categoryName +" is not present in Library list.");
		}		
	}
	
	public void selectcategory(String categoryName) throws Exception{		
  	  logInfo("Entered into selectcatNdEcard(String categoryName, String cardName) method");
		boolean isCatPresent= false;
		List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCat));
		System.out.println("list is "+ lib.size());
		for (int i = 1; i<=lib.size(); i++ ){			
			WebElement catName = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatAft));			
			if (catName.getText().equalsIgnoreCase(categoryName)){
				isCatPresent =true;
				catName.click();
				Thread.sleep(2000);								
				break;				
			}			
		}if (isCatPresent==false){
			Assert.assertTrue(isCatPresent, categoryName +" is not present in Library list.");
		}
    }
	
	public void selectHistory(String categoryName) throws Exception{		
	  	  logInfo("Entered into selectcatNdEcard(String categoryName, String cardName) method");
			boolean isCatPresent= false;
			List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCatDir));
			System.out.println("list is "+ lib.size());
			for (int i = 1; i<=lib.size(); i++ ){			
				WebElement catName = driver().findElement(By.cssSelector(eCardParentCatDirBfr+i+eCardParentCatDirAft));			
				System.out.println("list are"+ catName.getText());
				if (catName.getText().equalsIgnoreCase(categoryName)){
					isCatPresent =true;
					catName.click();
					Thread.sleep(2000);								
					break;				
				}			
			}if (isCatPresent==false){
				Assert.assertTrue(isCatPresent, categoryName +" is not present in Library list.");
			}
	    }
	
	
	public void selectCategory(String categoryName) throws Exception{		
  	  logInfo("Enetered into selectCategory(String categoryName) method");
		boolean isCatPresent= false;
		waitOnElement("cssSelector",eCardParentCat);
		List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCat));
		System.out.println("list is "+ lib.size());
		for (int i = 1; i<=lib.size(); i++ ){			
			WebElement catName = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatAft));			
			if (catName.getText().equalsIgnoreCase(categoryName)){
				System.out.println("list name  is "+ catName.getText());
				isCatPresent =true;
				catName.click();							
				break;				
			}			
		}if (isCatPresent==false){
			Assert.assertTrue(isCatPresent, categoryName +" is not present in Library list.");
		}
    }
	
	
      public void selectcatNdEcard(String categoryName, String cardName) throws Exception{		
    	  logInfo("Enetered into selectcatNdEcard(String categoryName, String cardName) method");
		boolean isCatPresent= false;
		waitOnElement("cssSelector",eCardParentCat);
		List <WebElement> lib = driver().findElements(By.cssSelector(eCardParentCat));
		System.out.println("list is "+ lib.size());
		for (int i = 1; i<=lib.size(); i++ ){			
			WebElement catName = driver().findElement(By.cssSelector(eCardParentCatBfr+i+eCardParentCatAft));			
			if (catName.getText().equalsIgnoreCase(categoryName)){				
				isCatPresent =true;
				catName.click();				
				editEcard(cardName);				
				break;				
			}			
		}if (isCatPresent==false){
			Assert.assertTrue(isCatPresent, categoryName +" is not present in Library list.");
		}
      }
      
      
      public void verifyEcard(String cardName) throws Exception{
    	  logInfo("Enetered into verifyEcard(String cardName) method");
    	  boolean isTemptPresent= false;
    	  Thread.sleep(3000);
    	  List <WebElement> rows = driver().findElements(By.cssSelector(eCardrows)); 
    	  System.out.println("rows"+ rows.size());
    	  for (int j = 1; j<=rows.size(); j++) {    		  
    		  List <WebElement> cardTemp =  driver().findElements(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTemp));        	  
        	  for (int i=1; i<=cardTemp.size(); i++){    		  
        		  WebElement name = driver().findElement(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempAfr));        		  
        		  if(name.getText().equalsIgnoreCase(cardName)){
        			  isTemptPresent=true;     
        			  System.out.println(cardName+ " is present.");
        			  break;    			  
        		  }  
    	  } 
      }  if (isTemptPresent ==false){
		  Assert.assertTrue(isTemptPresent, cardName +" is not available in Templatelist");   	  
      			}
      }
	
      
      public void editEcard(String cardName){
    	  logInfo("Entered into editEcard(String cardName) method");
    	  boolean isTemptPresent= false;    	  
    	  List <WebElement> rows = driver().findElements(By.cssSelector(eCardrows));    	 
    	  for (int j = 1; j<=rows.size(); j++) {    		  
    		  List <WebElement> cardTemp =  driver().findElements(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTemp));        	  
        	  for (int i=1; i<=cardTemp.size(); i++){    		  
        		  WebElement name = driver().findElement(By.cssSelector(eCardrowsBfr+j+eCardrowsAft+eCardTempBfr+i+eCardTempAfr));        		  
        		  if(name.getText().equalsIgnoreCase(cardName)){
        			  isTemptPresent=true;
        			  name.click();
        			  break;    			  
        		  }  
    	  } 
      }  if (isTemptPresent ==false){
		  Assert.assertTrue(isTemptPresent, cardName +" is not available in Template");   	  
      			}
      }
      
     
    		  
    
     
     public void enterDetails(String subject) throws Exception{
    	 logInfo("Enetered into enterDetails(String subject) method");
    	 waitOnElement ("cssSelector", ecardSub);
    	 inputTextClear("cssSelector", ecardSub);
    	 inputText("cssSelector", ecardSub, subject);      	 
    
    	 
    	 //remove after implemented with new enchancments
    	 if ((driver().getCurrentUrl().contains("idlife"))||
    			 (driver().getCurrentUrl().contains("avon"))){    
    		/* JavascriptExecutor js = (JavascriptExecutor)driver();   
        	 WebElement save = driver().findElement(By.cssSelector(ecardNext));
        	 js.executeScript("arguments[0].scrollIntoView(true);", save);  */
        	 System.out.println("coming");
        	 clickOnElement("xpath","//form/div[7]/div[2]");
        	 inputText("xpath","//form/div[7]/div[2]",composeBodyText);    
        	 clickOnElement("xpath","//form/div[8]/div[2]");
        	 inputText("xpath","//form/div[8]/div[2]",composeBodyText);    
        
    	/*	 composeText("xpath",composeBodyMsg,composeBodyText);  
       	  	clickOnElement("xpath",composeFooter);
        	 composeText("xpath",composeFooter,composeBodyText);         	 */
    	     }else{
    	    	 System.out.println("coming in else");
    	    	 inputText("cssSelector",compBodyMsg,composeBodyText);    
    	    	 inputText("cssSelector",bodyCompose,composeBodyText);    
    	    	 inputText("cssSelector",footerCompose,composeBodyText); 
    	    	  
    	     }       	 
     }
     
     
     
     public void saveCardAs(String name) throws Exception{
    	 logInfo("Entered into saveCardAs(String name) method");
    	 Thread.sleep(5000);
    	 if ((driver().getCurrentUrl().contains("idlife"))||
    			 (driver().getCurrentUrl().contains("avon"))){ 
    		 waitOnElement("cssSelector", ecardSaveAs_old); 
    		 getText("cssSelector", ecardSaveAs_old,"sav");
        	 clickOnButton("cssSelector", ecardSaveAs_old); 
        	 
    	     }else{
    	    	 waitOnElement("cssSelector", ecardSaveAs); 
    	    	 clickOnButton("cssSelector", ecardSaveAs); 
    	     }   	 
    	
    	 waitOnElement("cssSelector", foot);
    	 inputText("cssSelector", temptitleInput, name);
    	 clickOnButton("cssSelector", foot); 
    	 confirmationMessage("Ecard Template was successfully saved.");
     }
     
     public void verifyEcardInSavedEcards(String ecardName) throws Exception{
    	 logInfo("Enetered into verifyEcardInSavedEcards(String ecardName) method");
      	  boolean isTemptPresent= false;
      	  clickOnLink("linkText", "Saved Ecards");
      	  Thread.sleep(3000);
      	  List <WebElement> rows = driver().findElements(By.cssSelector(savedTitles)); 
      	  System.out.println("rows"+ rows.size());
      	  for (WebElement title: rows){
      		  System.out.println(title.getText());
      		  if (title.getText().equalsIgnoreCase(ecardName)){
      			isTemptPresent =true;      					
      			break;
      			  
      		  }
      		  
      	  } if (isTemptPresent ==false){
   		  Assert.assertTrue(isTemptPresent, ecardName +" is not available in Template");   	  
   		}
      	  
        }
     
     
     public void verifyNdEditEcard(String ecardName) throws Exception{
    	 logInfo("Enetered into verifyNdEditEcard(String ecardName) method");
   	  boolean isTemptPresent= false;
   	  Thread.sleep(3000);
   	  List <WebElement> rows = driver().findElements(By.cssSelector(savedTitles)); 
   	  System.out.println("rows"+ rows.size());
   	  for (WebElement title: rows){
   		  System.out.println(title.getText());
   		  if (title.getText().equalsIgnoreCase(ecardName)){
   			isTemptPresent =true;
   			title.click();   			
   			break;
   			  
   		  }
   		  
   	  } if (isTemptPresent ==false){
		  Assert.assertTrue(isTemptPresent, ecardName +" is not available in Template");   	  
		}
   	  
     }
     
     
     public void deleteSavedEcard(String ecardName) throws Exception{
    	 logInfo("Enetered into deleteSavedEcard(String ecardName) method");
      	  boolean isTemptPresent= false;
      	  Thread.sleep(3000);
      	  List <WebElement> rows = driver().findElements(By.cssSelector(savedTitles)); 
      	  System.out.println("rows"+ rows.size());      	  
      	  for (int i=1; i<=rows.size(); i++){
      		  WebElement  title = driver().findElement(By.cssSelector(savedECardTempBfr+i+eCardTempAfr));    
      		  System.out.println(title.getText());
      		  if (title.getText().equalsIgnoreCase(ecardName)){
      			isTemptPresent =true;
      			Actions act = new Actions(driver());      			
      			WebElement toggle =  driver().findElement(By.cssSelector(savedECardTempBfr+i+eCardToggleDropDown));
      			act.moveToElement(toggle).build().perform();
      			System.out.println("title is present");
      			
      			toggle.click();
      			break;
      		  }     		  
      	  } if (isTemptPresent ==false){
   		  Assert.assertTrue(isTemptPresent, ecardName +" is not available in Template");   	  
   		}   }
     
   
    		  
    	public void verifyNdSendSavedEcardMail(String name) throws Exception{
    		logInfo("Entered into verifyNdSendSavedEcardMail(String name) method");
    		clickOnLink("linkText", "Saved Ecards");
    		verifyNdEditEcard(name);
    		Thread.sleep(3000);
    		JavascriptExecutor je = (JavascriptExecutor)driver();
    		WebElement next = driver().findElement(By.cssSelector(ecardNext));
       	    je.executeScript("arguments[0].scrollIntoView(true);", next);
       	    clickOnButton("cssSelector", ecardNext);
       	    Thread.sleep(2000);
       	 
    		clickOnLink("linkText", "Add Group");
    	}
    	
    	
    	// De selects All group check box and select passed group name .
    	public void mailGroup(String groupName) throws Exception{
    		logInfo("Entered into mailGroup(String groupName) method."); 
    		waitOnElement("cssSelector", mailSelectAll);
    		WebElement allGrp = driver().findElement(By.cssSelector(mailSelectAll));    		
    		if(allGrp.isSelected()){
    			allGrp.click();
    		}
    			Thread.sleep(2000);
    			JavascriptExecutor js = (JavascriptExecutor)driver();  
    			WebElement addBtn = driver().findElement(By.cssSelector(addGrpAdd));
    	    	 js.executeScript("arguments[0].scrollIntoView(true);", addBtn);    	 	
    			
    		boolean isGroupPresent = false;    		
    		List<WebElement> grp = driver().findElements(By.cssSelector(mailGrp));
    		System.out.println("group size "+ grp.size());
    		for (int i = 3; i<=grp.size(); i++){    			
    			WebElement grpName = driver().findElement(By.cssSelector(mailGrpBfr+i+mailGrpAft));
    			System.out.println(grpName.getText()+ " Name");
    			if (grpName.getText().contains(groupName)){
    				isGroupPresent=true;
    				WebElement chkbx = driver().findElement(By.cssSelector(mailGrpBfr+i+mailGrpChkBox));
    				chkbx.click();
    				waitOnElement("cssSelector", addGrpAdd);
    				clickOnButton("cssSelector", addGrpAdd);
    				break;
    			}    			
    		}if (isGroupPresent==false){
    			Assert.assertTrue(isGroupPresent, groupName+ " group is not present!!");
    			
    		}  		
    	}
    	
    	public void sendMailToGroup(String groupName) throws Exception{
    	logInfo("Enetered into sendMail(String name) method");
       	 
    	clickOnButton("cssSelector", ecardSaveAs); 
    	waitOnElement("cssSelector", foot);
    	
    	/*
       	clickOnButton("cssSelector", ecardNext);        	
       	clickOnLink("linkText", "Add Group");       	
       	waitOnElement("linkText", "Add Group");
        mailGroup(groupName);        	
       	waitOnElement("cssSelector", ecardMailSend);      
       	clickOnButton("cssSelector", ecardMailSend);
       	Thread.sleep(4000);
       	WebElement confMsg = driver().findElement(By.cssSelector(ecardConfrim));
       	System.out.println(confMsg.getText());
       	Assert.assertEquals(confMsg.getText(), ecardConfrimText);       	
       	clickOnLink("linkText", "Start");
       	Thread.sleep(3000);*/
       	
        }
    	
    	public void fetchSelfMailId() throws Exception{ 		

    		profile.navigateMyProfileAccount("Notifications");
    		String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
    		System.out.println(selfmailId);
    		
    	}
    	
    	
    	public void sendManualMail(String mailId, String subject) throws Exception{
        	logInfo("Entered into sendMail(String name) method");
        	
        	/*if ((driver().getCurrentUrl().contains("idlife"))){    
        		waitOnElement("cssSelector", ecardNext); 
        		clickOnButton("cssSelector", ecardNext); 
               	waitOnElement("linkText", "Add Manually");      
                clickOnLink("linkText", "Add Manually");           
               	waitOnElement("cssSelector", inputManualEmail);
               	inputText("cssSelector", inputManualEmail, mailId);
               	clickOnButton("cssSelector", addGrpAdd);
                waitOnElement("cssSelector", ecardMailSend);	
               	clickOnButton("cssSelector", ecardMailSend);
               	waitOnElement("cssSelector", ecardConfrim);
               	WebElement confMsg = driver().findElement(By.cssSelector(ecardConfrim));
               	System.out.println(confMsg.getText());
               	Assert.assertEquals(confMsg.getText(), ecardConfrimText);       	
               	clickOnLink("linkText", "Start");
               	
       	     }else{*/
       	    	clickOnButton("cssSelector", mailIcon);    
            	waitOnElement("cssSelector", inputEmailEcard);
            	inputText("cssSelector", inputEmailEcard, mailId);                    	
               	inputTextClear("cssSelector", inputComposeEmailSubject);
    			inputText("cssSelector", inputComposeEmailSubject,subject  );
    			
    			Thread.sleep(2000);  // kept to handle compose body 		
    			waitOnElement("cssSelector", ecardMailSendBtn);         	
               	clickOnButton("cssSelector", ecardMailSendBtn);
       //        	confirmationMessage("Message sent to: "+mailId);        	    	 
       	    
           	/*WebElement confMsg = driver().findElement(By.cssSelector(ecardConfrim));
           	System.out.println(confMsg.getText());
           	Assert.assertEquals(confMsg.getText(), ecardConfrimText);       	
           	clickOnLink("linkText", "Start");*/
        	
            }
    	
    	
    	//  Select Next in "Enter Content" section and compose a mail an then catches toaster message  	
    	public void sendManualMailInIDLife(String mailId, String subject) throws Exception{
        	logInfo("Enetered into sendMail(String name) method");           	 
           	clickOnButton("cssSelector", ecardNext);            	          
           	waitOnElement("cssSelector", inputManualEmail);
           	inputText("cssSelector", inputEmailEcard, mailId);                    	
           	inputTextClear("cssSelector", inputComposeEmailSubject);
			inputText("cssSelector", inputComposeEmailSubject,subject  );
			Thread.sleep(2000);  // kept to handle compose body 
			waitOnElement("cssSelector", inputComposeEmailSubject);
			composeText("xpath",composeBody,composeBodyText);  
			getText("cssSelector", ecardMailSendBtn, "test "); 
			waitOnElement("cssSelector", ecardMailSendBtn);         	
           	clickOnButton("cssSelector", ecardMailSendBtn);
           	confirmationMessage("Message sent to: "+mailId);             	
            }
    	
    	
    	public void sendManualMailToManyRecepients(String mailId, int noOfMails) throws Exception{
        	logInfo("Enetered into sendMail(String name) method");
           	 
           	clickOnButton("cssSelector", ecardNext); 
           	Thread.sleep(2000);
           	clickOnLink("linkText", "Add Manually");           
           	Thread.sleep(2000);
           	inputText("cssSelector", inputManualEmail, mailId);
           	
           	for (int i= 3; i<=noOfMails+2; i++){
           		String mailId1 = "vibers"+TestBase.generateRandomString()+i+"@icentris.com";
           		System.out.println(mailId1);
           		Thread.sleep(4000);
           		clickOnElement("cssSelector", moremailsLink);           		
           		WebElement mailField = driver().findElement(By.cssSelector(mailBfr+i+mailAfr));
           		mailField.sendKeys(mailId1);
           		
           	}          	
           	
           	clickOnButton("cssSelector", addGrpAdd);
           	Thread.sleep(2000);           	
           	clickOnButton("cssSelector", ecardMailSend);
           	Thread.sleep(4000);
           	WebElement confMsg = driver().findElement(By.cssSelector(ecardConfrim));
           	System.out.println(confMsg.getText());
           	Assert.assertEquals(confMsg.getText(), ecardConfrimText);       	
           	clickOnLink("linkText", "Start");
           	Thread.sleep(3000);
           	
            }
    	
    	
    	public void validationMails(String mailId) throws Exception{
        	logInfo("Enetered into validationMails(String name) method");
        	
        	clickOnButton("cssSelector", mailIcon);    
        	waitOnElement("cssSelector", inputEmailEcard);     		
			waitOnElement("cssSelector", ecardMailSendBtn);         	
           	clickOnButton("cssSelector", ecardMailSendBtn);
           	waitOnElement("cssSelector", recipAlert);            
           	WebElement recp = driver().findElement(By.cssSelector(recipAlert));
           	System.out.println("Text is "+ recp.getText());
           	Assert.assertEquals(recp.getText(), recipAlertText);
           	clickOnButton("cssSelector", recpOkBtn);       
           	
       
           	
            }   	
    	
    	public void gridNdLsitView() throws Exception{
        	logInfo("Entered into validationMails(String name) method");
        	
        	clickOnElement ("cssSelector",ecardGridView);
        	WebElement tit = driver().findElement(By.cssSelector(ecardFirstTitle));
        	String expTitle = tit.getText();
        	System.out.println("expecte title is "+expTitle);
        	
        	clickOnElement ("cssSelector",ecardListView);
           	 
        	WebElement listTit = driver().findElement(By.cssSelector(ecardListTitle));
        	String ActTitle = listTit.getText();
        	System.out.println("Actual title "+ ActTitle);
        	
        	Assert.assertEquals(ActTitle, expTitle);
        	clickOnElement ("cssSelector",ecardGridView);           	
            }
    	
    	public void gridNdLsitViewInSavedCards(String savedCardName) throws Exception{
        	logInfo("Entered into validationMails(String name) method");
        	
        	Thread.sleep(4000);
    		clickOnLink("linkText", savedCard);
        	clickOnElement ("cssSelector",ecardGridView);
        	WebElement tit = driver().findElement(By.cssSelector(ecardFirstTitle));
        	String actTitleInGrid = tit.getText();
        	System.out.println("expecte title is "+actTitleInGrid);
        	Assert.assertEquals(actTitleInGrid, savedCardName);
        	
        	clickOnElement ("cssSelector",ecardListView);
           	 
        	WebElement listTit = driver().findElement(By.cssSelector(ecardListTitle));
        	String ActTitleInList = listTit.getText();
        	System.out.println("Actual title "+ ActTitleInList);
        	
        	Assert.assertEquals(ActTitleInList, savedCardName);
        	clickOnElement ("cssSelector",ecardGridView);
           	
            }
    	
    	public void editEcardInListView(String ecardName) throws Exception{
    		boolean ispresent= false;    		
    		clickOnLink("linkText", savedCard);    		
    		Thread.sleep(6000);
    		clickOnElement ("cssSelector",ecardListView);
    		Thread.sleep(4000);
    		
    		List <WebElement> crd = driver().findElements(By.cssSelector(listcards));
    		System.out.println("size is "+crd.size());
    		for (int i=1; i<=crd.size(); i++){
    			WebElement name = driver().findElement(By.cssSelector(cardNameBfr+i+cardNameAfr));
    			System.out.println("name are "+ name.getText());
    			if (name.getText().equalsIgnoreCase(ecardName)){
    				ispresent = true;
    				Thread.sleep(2000);
    				WebElement more = driver().findElement(By.cssSelector(cardNameBfr+i+cardOptionsAfr));
    				more.click();
    				Thread.sleep(2000);
    				WebElement edit = driver().findElement(By.cssSelector(cardNameBfr+i+cardOptionEdit));
    				edit.click();
    				Thread.sleep(2000);   				
    				break;   				
    			}    			
    		}if (ispresent==false){
    			Assert.assertTrue(ispresent, ecardName + " - is not found in saved Card list" );
    		}
    		
    	}    	
    	
    	
    	public void deleteEcardInListView(String ecardName) throws Exception{
    		boolean ispresent= false;    		
    		clickOnLink("linkText", savedCard);    		
    		Thread.sleep(6000);
    		clickOnElement ("cssSelector",ecardListView);
    		Thread.sleep(4000);
    		
    		List <WebElement> crd = driver().findElements(By.cssSelector(listcards));
    		System.out.println("size is "+crd.size());
    		for (int i=1; i<=crd.size(); i++){
    			WebElement name = driver().findElement(By.cssSelector(cardNameBfr+i+cardNameAfr));
    			System.out.println("name are "+ name.getText());
    			if (name.getText().equalsIgnoreCase(ecardName)){
    				ispresent = true;
    				Thread.sleep(2000);
    				WebElement more = driver().findElement(By.cssSelector(cardNameBfr+i+cardOptionsAfr));
    				more.click();
    				Thread.sleep(2000);
    				WebElement delete = driver().findElement(By.cssSelector(cardNameBfr+i+cardOptionDelete));
    				delete.click();
    				Thread.sleep(2000);
    				clickOnButton("cssSelector", deleteOkBtn);
    				confirmationMessage(ecardName+" was deleted successfully");    
    				Thread.sleep(4000);
    				break;   				
    			}    			
    		}if (ispresent==false){
    			Assert.assertTrue(ispresent, ecardName + " - is not found in saved Card list" );
    		}
    		
    	}
    	
    	public void notToPresentSavedEcards(String ecardName) throws Exception{
       	 logInfo("Entered into notToPresentSavedEcards(String ecardName) method");
         	  boolean isTemptPresent= true;
         	 Thread.sleep(3000);
         	  clickOnLink("linkText",savedCard);
         	  Thread.sleep(3000);
         	  List <WebElement> rows = driver().findElements(By.cssSelector(savedTitles)); 
         	  System.out.println("rows"+ rows.size());
         	  for (WebElement title: rows){
         		  System.out.println(title.getText());
         		  if (title.getText().equalsIgnoreCase(ecardName)){
         			 Assert.assertTrue(isTemptPresent, ecardName +" is still present in List");   	 
         			isTemptPresent =false;      					
         			break;         			  
         		  }         		  
         	  } if (isTemptPresent ==true){
         		  System.out.println("success!! not present.");      		   
         	  			}
           }
    	
    	
    	public void sendMailToGroupNdManual(String mailId, int noOfMails, String groupName) throws Exception{
        	logInfo("Enetered into sendMail(String name) method");
           	 
           	clickOnButton("cssSelector", ecardNext); 
           	Thread.sleep(2000);
           	clickOnLink("linkText", "Add Manually");           
           	Thread.sleep(2000);
           	inputText("cssSelector", inputManualEmail, mailId);
           	
           	for (int i= 3; i<=noOfMails+2; i++){
           		String mailId1 = "vibers"+TestBase.generateRandomString()+i+"@icentris.com";
           		System.out.println(mailId1);
           		Thread.sleep(4000);
           		clickOnElement("cssSelector", moremailsLink);           		
           		WebElement mailField = driver().findElement(By.cssSelector(mailBfr+i+mailAfr));
           		mailField.sendKeys(mailId1);           		
           	}         	
           	
           	clickOnButton("cssSelector", addGrpAdd);
           	Thread.sleep(2000); 
           	
           	clickOnLink("linkText", "Add Group");
           	mailGroup(groupName);
           	clickOnButton("cssSelector", addGrpAdd);
           	Thread.sleep(2000);
           	
           	clickOnButton("cssSelector", ecardMailSend);
           	Thread.sleep(4000);
           	WebElement confMsg = driver().findElement(By.cssSelector(ecardConfrim));
           	System.out.println(confMsg.getText());
           	Assert.assertEquals(confMsg.getText(), ecardConfrimText);       	
           	clickOnLink("linkText", "Start");
           	Thread.sleep(3000);
           	
            }
    	
    	
    	public void validateSubject(String subject) throws Exception{
       	 logInfo("Enetered into enterDetails(String subject) method");
       	 Thread.sleep(3000);  
       	 inputTextClear("cssSelector", ecardSub);
       	 inputText("cssSelector", ecardSub, subject);
        	
       	 Thread.sleep(3000); 	
         tabbing();
         clickOnElement("cssSelector", readbleSubject);
       	 Thread.sleep(2000);       	 
       	 WebElement actSub = driver().findElement(By.cssSelector(readbleSubject));
       	 System.out.println("getText"+ actSub.getText()); 
       	 Assert.assertEquals(actSub.getText(), subject);    	 	 
       	 
        }
    	
    	public void validateSubjectLength() throws Exception{
          	 logInfo("Enetered into enterDetails(String subject) method");
          	 String subject = "123456"+TestBase.generateRandomNumberInRange(1000, 9999);
          	 Thread.sleep(3000);  
          	 inputTextClear("cssSelector", ecardSub);
          	// Test -1 Enters 250 characters and retrieves same size 
          	 for (int i=1; i<=25; i++){
          		Thread.sleep(1000);
          	 inputText("cssSelector", ecardSub, subject);
          	 }
          	 Thread.sleep(3000); 	
             tabbing();
             clickOnElement("cssSelector", readbleSubject);
          	 Thread.sleep(2000);       	 
          	 WebElement actSub = driver().findElement(By.cssSelector(ecardSub));          	 
          	 String subSize = actSub.getAttribute("value");
      	     int size =  subSize.length();
      	     if (size==250){
      	    	 System.out.println("Success!! Subject field is accepting max 250 characters");
      	     }else {
      	    	 Assert.assertEquals(size, 250);
      	    	 
      	     }   // Test-1 - Ends
      	     
      	     // Test -2 Enters more than 250 characters and retrieves 250 characters  size only. 
      	     Thread.sleep(3000);  
        	 inputTextClear("cssSelector", ecardSub);
        	
        	 for (int i=1; i<=26; i++){
        		Thread.sleep(1000);
        	 inputText("cssSelector", ecardSub, subject);
        	 }
        	 Thread.sleep(3000); 	
        	 tabbing();
        	 clickOnElement("cssSelector", readbleSubject);
        	 Thread.sleep(2000);       	 
        	 WebElement actSub2 = driver().findElement(By.cssSelector(ecardSub));        	
        	 String subSize2 = actSub2.getAttribute("value");
    	     int size2 =  subSize2.length();
    	     if (size2==250){
    	    	 System.out.println("Success!! Subject field is accepting max 250 characters");
    	     }else {
    	    	 Assert.assertEquals(size2, 250);
    	    	 
    	     }      	     
    	     Thread.sleep(3000);  
        	 inputTextClear("cssSelector", ecardSub);   	
    	}
    	
    	public void gridNdLsitViewEcardSizes() throws Exception{
        	logInfo("Entered into gridNdLsitViewEcardSizes() method");
        	
        	clickOnElement ("cssSelector",ecardGridView);
        	List<WebElement> gsize = driver().findElements(By.cssSelector(ecardFirstTitle));
        	int gridSize = gsize.size();
        	System.out.println("grid size is "+ gridSize);
        	Thread.sleep(2000);
        	
        	
        	clickOnElement ("cssSelector",ecardListView);
        	List<WebElement> lSize = driver().findElements(By.cssSelector(ecardListTitle));
        	int listSize = lSize.size();
        	System.out.println("list size is "+ listSize);           	 
        	
        	Assert.assertEquals(gridSize, listSize);
        	clickOnElement ("cssSelector",ecardGridView);           	
            }	
    	public void validateMail(String mailId) throws Exception{
        	logInfo("Entered into validateMail(String mailId, int noOfMail) method");
        	
        	String mailId1 = "vibers"+TestBase.generateRandomString()+"@gmail.com";
        	String invalidmail = "vibers"+TestBase.generateRandomString();
       		System.out.println(mailId1);
       		Thread.sleep(4000);
           	 
           	clickOnButton("cssSelector", ecardNext); 
           	Thread.sleep(2000);
           	clickOnLink("linkText", "Add Manually");  
           	Thread.sleep(2000);
           	inputText("cssSelector", inputManualEmail, invalidmail); 
           	clickOnButton("cssSelector", addGrpAdd);
           	Thread.sleep(2000); 
           	inputTextClear("cssSelector", inputManualEmail);           	
           	Thread.sleep(2000);
           	WebElement header = driver().findElement(By.cssSelector(headerMail));
           	System.out.println(header.getText());
           	Assert.assertEquals(header.getText(), headerMailText);
           	
           	inputTextClear("cssSelector", inputManualEmail); 
           	
           	
           	inputText("cssSelector", inputManualEmail, mailId);   
           	clickOnElement("cssSelector", moremailsLink);   
           	inputText("cssSelector", mailBfr+3+mailAfr, mailId1); 
           	Thread.sleep(2000);
           	clickOnLink("linkText", "Remove");
         	clickOnButton("cssSelector", addGrpAdd);
           	Thread.sleep(2000); 
           	WebElement mailIds = driver().findElement(By.cssSelector(mailText));
           	String givenMailId = mailIds.getText();
           	System.out.println("givenMailId "+ givenMailId);
           	Assert.assertEquals(givenMailId, mailId+"x");
           	Thread.sleep(2000);
        	clickOnButton("cssSelector", ecardMailSend);
        	clickOnLink("linkText", "Start");         		
           		          		
           	}  
    	
    	public void assertPageObjects(String categoryName, String cardName) throws Exception{
    		
    		WebElement library = driver().findElement(By.cssSelector(libTitle));
    		System.out.println("Get text "+ library.getText());
    		Assert.assertEquals(library.getText(), libTitleText);
    		WebElement seacrh = driver().findElement(By.cssSelector(ecardSearch));
    		System.out.println("Get text "+seacrh.getAttribute("placeholder"));
    		Assert.assertEquals(seacrh.getAttribute("placeholder"), ecrdSearchPlaceHolder);
    		/*selectcatNdEcard(categoryName, cardName);
    		Thread.sleep(3000);
    		clickOnElement("cssSelector", subHint);
    		Actions act = new Actions(driver);
    		WebElement hints = driver().findElement(By.cssSelector(subHint));
    		act.moveToElement(hints).build().perform();
    		Thread.sleep(3000);
    		WebElement hintMessage = driver().findElement(By.cssSelector(subHint));
    		System.out.println("Hint mssgae is "+ hintMessage.getText());
    		Assert.assertEquals(hintMessage.getText(), hintMsg);*/
    		
    		
    		
    	}
    	
    	
    	public void searchEcard(String ecardName) throws Exception{
    		logInfo("Entered into searchEcard(String ecardName) method");    		
    		inputTextClear("cssSelector", ecardSearch);
    		inputText("cssSelector", ecardSearch, ecardName);
    		Thread.sleep(2000);
    		submitElement("cssSelector", ecardSearch);
    		Thread.sleep(2000);
    	}
    	
    	
    	public void shareToSocial() throws Exception{
    		logInfo("Verify the social Networks section and select Share to Social sites."); 		
    		WebElement soc = driver().findElement(By.cssSelector(RLSocial));
    		Assert.assertEquals(soc.getText(), RLSocialText);
    		clickOnButton("cssSelector",btnResourceShareSocialNetwork);
    	}
           	
    	
    	public void shareInSocialSites() throws Exception{    		
    		clickOnLink("linkText", "Share Ecard");
    		Thread.sleep(4000);
    		WebElement sharBtn = driver().findElement(By.cssSelector(socialNetwork)) ;
    		Assert.assertEquals(sharBtn.getText(), "Share");
    		clickOnElement("cssSelector",socialNetwork);    		
    	}
    	
    	public void shareIncommunity(String comment) throws Exception{    		
    		clickOnLink("linkText", "Share Ecard");
    		Thread.sleep(4000);
    		WebElement com = driver().findElement(By.cssSelector(RLComm));
    		Assert.assertEquals(com.getText(),RLCommText );
    		clickOnButton("cssSelector",commShare);		
    		Thread.sleep(4000);
    		verifyElementPresent("cssSelector",ecardComm);
    		inputText("cssSelector",ecardComm, comment);		
    		clickOnButton("cssSelector",commShareBtn);
    		confirmationMessage("This ecard has been shared to the community.");	
    		Thread.sleep(4000); 		
    	}
    	
    	
    	public void shareInWebsite() throws Exception{    		
    		clickOnLink("linkText", "Share Ecard");
    		Thread.sleep(4000);   		
    		WebElement com = driver().findElement(By.cssSelector(RLWebSite));
    		Assert.assertEquals(com.getText(),RLWebsiteText );
    		clickOnButton("cssSelector",webShare);		
    		Thread.sleep(4000);	
    		WebElement title = driver().findElement(By.cssSelector(webShareTitle));
    		WebElement urlPath = driver().findElement(By.cssSelector(shareLinktext));
    		Assert.assertEquals(title.getText(), webShareText);
    		String expectedurl = urlPath.getText();
    		System.out.println("expected url is "+expectedurl);
    		clickOnButton("cssSelector", viewWebsite);
    		rl2.handleMyWebsite();
    		rl2.closeModalWindow();
    		
    	
    	}
    	
    	
    	public void avonCreateECard(String ecardName,String category, String childCategory ) throws Exception{
 		   logInfo("Entered into createECard() method ");		
 			clickOnButton("cssSelector", newCard); 			
 			nav2EcardTemp();
 			Thread.sleep(4000);
 			WebElement nam = driver().findElement(By.cssSelector(eName));
 			WebElement desc = driver().findElement(By.cssSelector(eDescrp));
 			nam.clear();
 			desc.clear();
 			nam.sendKeys(ecardName);
 			desc.sendKeys(ecardName);
 			avonSelectCatInThemeSetting(category,childCategory);
 			/*waitOnElement("cssSelector", browseBtn);			
 			clickOnElement("cssSelector", browseBtn);
 			Thread.sleep(6000);
 			Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\coffeImage.exe"); 			
 			Thread.sleep(4000);
 			
 			
 			clickOnElement("cssSelector", ePublish);
 			confirmationMessage("Ecard template was successfully created.");*/
 			
 			
 		}	
    	
    	
    	public void avonSelectCatInThemeSetting(String category, String childCategory) throws Exception{
        	logInfo("Entered into selectCatInThemeSetting() method ");
        	
        	selectCatLinkOptions("Categories");
        	avonSelectParentCateInThemeSettings(category);
        	//selectChildCateInThemeSettings(category, childCategory);
        }
    	
    	 public void avonSelectParentCateInThemeSettings(String category) throws Exception{
    			logInfo("Entered into selectParentCateInThemeSettings() method ");    			
    			waitOnElement("cssSelector", AVeCatChk);
    			List <WebElement> cat = driver().findElements(By.cssSelector(AVeCatChk));
    			System.out.println("size"+ cat.size());	
    			boolean isParentPresent = false;
    			for (int i=1; i<=cat.size(); i++){			
    				WebElement catNm = driver().findElement(By.cssSelector(AVeCatChkBfr+i+AVeCatChkAft));
    				String pCatgName  = catNm.getText().trim();
    				System.out.println(pCatgName);
    				if (pCatgName.contains(category)){		
    							isParentPresent = true;
    							WebElement catNmChk = driver().findElement(By.cssSelector(AVeCatChkBfr+i+AVeCatChkBoxAft));
    							if (!catNmChk.isSelected()){
    								catNmChk.click();
    								Thread.sleep(4000);
    							}						
    							break;					
    						}					
    					}if(isParentPresent==false){
    						Assert.assertTrue(isParentPresent, category + " category is not present under theme settings");										
    					}				
    							
    		}
    	 
    	 public void selectHistoryList(String pastDays) throws Exception{		
    	  	  logInfo("Entered into selectcatNdEcard(String categoryName, String cardName) method");
    			boolean isCatPresent= false;
    			List <WebElement> lib = driver().findElements(By.cssSelector(hisList));
    			System.out.println("list is "+ lib.size());
    			for (int i = 1; i<=lib.size(); i++ ){			
    				WebElement catName = driver().findElement(By.cssSelector(hisListBfr+i+hisListAfr));			
    				if (catName.getText().equalsIgnoreCase(pastDays)){
    					isCatPresent =true;
    					System.out.println("catName.getText()" + catName.getText());
    					catName.click(); 
    					waitOnElement("cssSelector", ".panel-title");
    					break;				
    				}			
    			}if (isCatPresent==false){
    				Assert.assertTrue(isCatPresent, pastDays +" is not present in History list.");
    			}
    	    }
    	  
    	 
    	 public void verfiyHistoryCard(String cardName){
       	  logInfo("Entered into verfiyHistoryCard(String cardName) method");
       	  boolean isTemptPresent= false;    	  
       	  List <WebElement> rows = driver().findElements(By.cssSelector(hisCardrows));    	 
       	  for (int j = 1; j<=rows.size(); j++) {    		  
       		  List <WebElement> cardTemp =  driver().findElements(By.cssSelector(hisCardrowsBfr+j+hisCardrowsAft+eCardTemp));        	  
           	  for (int i=1; i<=cardTemp.size(); i++){    		  
           		  WebElement name = driver().findElement(By.cssSelector(hisCardrowsBfr+j+hisCardrowsAft+eCardTempBfr+i+eCardTempAfr));        		  
           		  if(name.getText().equalsIgnoreCase(cardName)){
           			  isTemptPresent=true;           			 
           			  break;    			  
           		  }  
       	  } 
         }  if (isTemptPresent ==false){
   		  Assert.assertTrue(isTemptPresent, cardName +" is not available in Template");   	  
         			}
         }	 
    	  
	
}
	
