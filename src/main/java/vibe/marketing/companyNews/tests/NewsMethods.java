
package vibe.marketing.companyNews.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;


import vibe.billing.tests.BillingMethods;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.ads.tests.AdsMethods;
import vibe.users.tests.UsersMethods;
import vibe.tasks.tests.TaskMethods;
import common.TestBase;


public class NewsMethods extends TestBase{	
	InboxMethods inbox = new InboxMethods();
	TaskMethods tm = new TaskMethods();
	UsersMethods um = new UsersMethods();
	BillingMethods bm = new BillingMethods();
	
	//Setters
	
	public void navigateCompanyNews() throws Exception{
		logInfo("Navigate to Marketing >> Company News Screen");
	/*	clickOnLink("linkText", gotoAdmin);
		clickOnLink("linkText", marketingLnk);
		clickOnLink("linkText", compNewslnk);	*/
		driver().navigate().to(appUrl+"/pyr_core/company_news");
			
	}
	
	public void selectAddCompanyNews() throws Exception{		 
		logInfo("Select button of Add Company NEWS.");
		try{			
			clickOnButton("cssSelector",compAddCompanyNews);
		}catch (WebDriverException we ){
			System.err.println("Failed!! Not able to select Add company NEWS Button.");
		}		
	}
		
	public void enterTitle (String title) throws Exception{
		logInfo("Enter enterTitle method");	
		inputTextClear("cssSelector", compTitle);
		inputText("cssSelector", compTitle, title);
		waitOnElement("cssSelector", compTitle);
		
		
	}
	
	public void enterExcerpt (String Excerpt) throws Exception{
		logInfo("Enter Excerpt - "+Excerpt);
		waitOnElement("cssSelector", compExcerpt);
		inputText("cssSelector", compExcerpt, Excerpt);	
	}
		
	
	public void rankDefinition(String ranker) throws Exception, InterruptedException{
		logInfo("Select check boxes of Define Rank");
		try{
		clickOnElement("cssSelector", compRank);
		Thread.sleep(3000);
		WebElement rank = driver().findElement(By.cssSelector(rankAll));
		if (rank.isSelected()){
			rank.click();
			Thread.sleep(2000);			
		}		
		List <WebElement> status = driver().findElements(By.cssSelector(comRankDef));
		for (WebElement st : status){			
			if (st.getText().equalsIgnoreCase(ranker)){
				if(!st.isSelected()){			
			st.click();	
			break;
			}}
		}}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to define Ranks.");
		}
	}


	
	public void marketLanguages(String[] language) throws Exception, InterruptedException{
		try{
		clickOnElement("cssSelector", compLang);
		Thread.sleep(2000);
		WebElement marketAll = driver().findElement(By.cssSelector(markAll));
		if (marketAll.isSelected()){
			marketAll.click();
			Thread.sleep(2000);
		}
		
		int langgs = language.length;
		List <WebElement> lang = driver().findElements(By.cssSelector(compLangDD));			
		for (WebElement langs : lang){	
			for (int i=0; i<=langgs-1; i++ )
			if (langs.getText().equalsIgnoreCase(language[i])){
				if(!langs.isSelected()){				
				langs.click();	
				break;
			}}
		}}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select Market Language.");
		}
	}
	
	
	public void SubscriptionPlanSlection(String[] SubcriptionPlan) throws Exception, InterruptedException{
		try{			
			clickOnElement("cssSelector", compSubPlan);
			
			WebElement subsAll = driver().findElement(By.cssSelector(subAll));
			if (subsAll.isSelected()){
				subsAll.click();
				Thread.sleep(3000);
				}
			boolean isSubFound = false;
			
			int subcription = SubcriptionPlan.length;
			
			List <WebElement> sub = driver().findElements(By.cssSelector(compSubPlanDD));			
			for (WebElement subPlan : sub){	
				for(int j=0; j<=subcription-1;j++){				
				if (subPlan.getText().equalsIgnoreCase(SubcriptionPlan[j])){
					isSubFound= true;
					if(!(subPlan.isSelected())){
					subPlan.click();
					break;
						}
				    }	
				}
			}if (isSubFound==false){
				Assert.assertTrue(isSubFound, SubcriptionPlan+" - subscription is not found");
			}
			
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select Subcription Plans.");
		}				
	}
	
	public void subscriptionSlectionOfEdited(String[] SubcriptionPlan) throws Exception{
		try{
			waitOnElement("cssSelector", compSubPlan);
			clickOnElement("cssSelector", compSubPlan);			
			WebElement subsAll = driver().findElement(By.cssSelector(subAll));
			if (!(subsAll.isSelected())){
				subsAll.click();
				waitOnElement("cssSelector", subAll);
				subsAll.click();			
				}
			boolean isSubFound = false;
			int subcription = SubcriptionPlan.length;			
			List <WebElement> sub = driver().findElements(By.cssSelector(compSubPlanDD));			
			for (WebElement subPlan : sub){	
				for(int j=0; j<=subcription-1;j++){				
				if (subPlan.getText().equalsIgnoreCase(SubcriptionPlan[j])){
					isSubFound= true;
					if(!(subPlan.isSelected())){
					subPlan.click();
					break;
						}
				    }	
				}
			}if (isSubFound==false){
				Assert.assertTrue(isSubFound, SubcriptionPlan+" - subscription is not found");
			}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select Subcription Plans.");
		}				
	}
	
	public void selectIsNews() throws Exception, InterruptedException{
		try{			
			WebElement OV = driver().findElement(By.cssSelector(compIsNews));	
			if(!OV.isSelected()){
				OV.click();			
			}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select check box Of IsNews .");
		}	
	}
	
	public void selectIsHighLight() throws Exception, InterruptedException{
		try{			
			WebElement OV = driver().findElement(By.cssSelector(compIsHighLights));	
			if(!OV.isSelected()){
				OV.click();
				Thread.sleep(5000);
			}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select check box Of IsHighLights .");
		}		
		
	}
	
	public void selectPublishImmediately() throws Exception, InterruptedException{
		try{			
			WebElement OV = driver().findElement(By.cssSelector(compPublishArticle));	
			if(!OV.isSelected()){
				OV.click();
				Thread.sleep(5000);
			}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select check box Of 'Publish this Article Immediately.");
		}			
	}

	public void clickOnPublishButton() throws Exception{
		waitOnElement("cssSelector", compPublish);	
		clickOnButton("cssSelector", compPublish);	
	}
	
	public void enterPublishDate(int FutueDate) throws Exception{
		String futDate = getDate(FutueDate, "MM/dd/yyyy")	;
		waitOnElement("cssSelector", compPublishDate);
		inputTextClear("cssSelector", compPublishDate);
		inputText("cssSelector", compPublishDate, futDate);	
		clickOnElement("cssSelector",compPublishClock);
		Thread.sleep(1000);
		clickOnElement("cssSelector",compPublishClock);
		Thread.sleep(2000);
	        }
	
	public void editNews(String title) throws Exception{	
		boolean isNews = false;
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		System.out.println(newsList.size());
		for (WebElement list : newsList){
			System.out.println("list is "+ list.getText());
			if(list.getText().contains(title)){	
				isNews = true;
				list.click();	
				waitOnElement("cssSelector", compEdit);
				clickOnElement("cssSelector", compEdit);
				break;
			}
		}if(isNews==false){
			Assert.assertTrue(isNews, title +" is not found");
		}
	}
	
	// verifies News in Admin Page
	public void isNewsTitlePresent(String title) throws Exception{	
		waitOnElement("cssSelector", companyNewsListTitle);
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		boolean isTitlePresent =false;		
		for (WebElement list : newsList){		
			System.out.println("titles "+ list.getText());
			if((list.getText()).equalsIgnoreCase(title)){
				System.out.println( title + " is match found.");
				isTitlePresent = true;			
				break;										
			}}
		if(isTitlePresent==false){
				Assert.assertTrue(isTitlePresent,title+" is not available in the grid" );			
				
			}		
		}	
	
	// verifies News in Admin Page - not to present
	public void isNewsTitleNotToPresent(String title) throws Exception{	
		logInfo("Entered into isNewsTitleNotToPresent() method");
		Thread.sleep(6000);
		waitOnElement("cssSelector", companyNewsListTitle);
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		boolean isTitlePresent =true;		
		for (WebElement list : newsList){		
			if((list.getText()).equalsIgnoreCase(title)){
				System.out.println( title + " is match found.");
				isTitlePresent = false;
				Assert.assertTrue(isTitlePresent,title+" is still available in the grid" );
				break;										
			}}
		if(isTitlePresent==true){
					System.out.println(title + " is not present. Success");
						
			}		
		}
	 //In Home page, drag News widget and select view more link and verify news
  public void verifyNewsInWidget(String isNewsOrHighlight, String title) throws Exception{
		logInfo("Entered into isNewsTitleNotToPresent method");
		    clickOnLink("linkText", isNewsOrHighlight);
		    Thread.sleep(5000);		    
		    waitOnElement("cssSelector", companyNewsListTitle);
		    List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
			boolean isTitlePresent =false;	
			System.out.println("list size "+newsList.size());			
			waitOnElement("linkText", isNewsOrHighlight);
			for (int i=1;i<=newsList.size(); i++){
				WebElement list = driver().findElement(By.cssSelector(newsWidTitleBfr+i+newsWidTitleAfr));
				System.out.println("titles "+ list.getText());
				if((list.getText()).equalsIgnoreCase(title)){
					System.out.println( title + " is match found.");
					isTitlePresent = true;
					Thread.sleep(5000);
					break;										
				}}
			if(isTitlePresent==false){
					Assert.assertTrue(isTitlePresent,title+" is not available in the grid" );			
					
				}		
			}	  
		   
	
	   
	   //In Home page, drag News widget and select view more link and verify news - not to present
	   	public void verifyNewsNotToPresentInWidget(String isNewsOrHighlight, String title) throws Exception{
	   		logInfo("Entered into isNewsTitleNotToPresent method");
	     	clickOnLink("linkText", isNewsOrHighlight);	
	   		
			List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
			System.out.println("Size is News is "+newsList.size());
			boolean isTitlePresent =false;		
			for (WebElement list : newsList){		
				if((list.getText()).equalsIgnoreCase(title)){				
					isTitlePresent = false;
					Assert.assertTrue(isTitlePresent,title+" is Still available in the grid" );	
					break;											
						}
				}
			if(isTitlePresent==true){
					System.out.println("Suceess!! Is not available ");			
					
				}					
				
			}	   
	   	
	   	
	   	//HomePage-  verifies News in Recent Post on left Panel
	   	public boolean verifyNewsInRecentPosts(String newsTitle){
	   		
	   		String Text = "Recent Posts";
	   		WebElement rec = driver().findElement(By.cssSelector(recent));
	   		Assert.assertEquals(rec.getText().trim(), Text);
	   		boolean isRecentPresent =false;
	   		List <WebElement> li = driver().findElements(By.cssSelector(recPostList));
	   		System.out.println(li.size());
	   		for (WebElement lis :li ){
	   			if(lis.getText().equalsIgnoreCase(newsTitle)){
	   				isRecentPresent = true;
	   				break;  				
	   			}	   			
	   		}if (isRecentPresent==false){
	   			Assert.assertTrue(isRecentPresent, newsTitle + " - News title is not present");
	   		}
			return isRecentPresent;   		
	   		
	   	}
	   
	   
	   
	
	
	   public void selectArchievedDate(int ArchievedDate) throws Exception{		   
		   String Date = getDate(ArchievedDate, "MM/dd/yyyy")	;		   
		   WebElement ad = driver().findElement(By.cssSelector(compScheduleArchive));
		   if(!ad.isSelected()){
			   ad.click();     }	
		   	inputTextClear("cssSelector", archDateField);
			inputText("cssSelector", archDateField, Date);		
			Thread.sleep(1000);
			inputTextClear("cssSelector", archTimeField);
			inputText("cssSelector", archTimeField, "00:00");
			Thread.sleep(1000);
		   }
		   
	   
		   public void validateArchievedDate(int archievedPastDate) throws Exception{		   
			   String Date = getDate(archievedPastDate, "MM/dd/yyyy")	;		   
			   WebElement ad = driver().findElement(By.cssSelector(compScheduleArchive));
			   if(!ad.isSelected()){
				   	ad.click();				   
			   					}
			   	inputTextClear("cssSelector", archDateField);
				inputText("cssSelector", archDateField, Date);		
				Thread.sleep(1000);
				clickOnPublishButton();
				confirmationMessage("Archive date needs a time!");
				Thread.sleep(4000);
				inputTextClear("cssSelector", archTimeField);
				inputText("cssSelector", archTimeField, "00:00");
				Thread.sleep(1000);	
				clickOnPublishButton();
				confirmationMessage("Archive Date should not be in the past");     
		   
	   }
	
	public void selectNewsBasedOnTitle(String title) throws Exception{	
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		boolean isTitlePresent =false;
		
		for (WebElement list : newsList){		
			if((list.getText()).equalsIgnoreCase(title)){
				System.out.println( title + " is match found.");
				isTitlePresent = true;
				list.click();
				break;										
			}}
		if(isTitlePresent==false){
				Assert.assertTrue(isTitlePresent,title+" is not available in the grid" );			
				
			}		
		}
	
		
	public void clickNewsIconBasedOnExcerpts(String expecrt){
		logInfo("entered into clickNewsIconBasedOnExcerpts() method");
		
		boolean ispresent = false;
		List<WebElement> exList =  driver().findElements(By.cssSelector(excerptDesc));
		
		for (int i=1; i<=exList.size(); i++){
			WebElement exrt = driver().findElement(By.cssSelector(newsTitleBfr+i+newsTitleAfr));
		String actualExcept = exrt.getText().trim();
		System.out.println("actualExcept "+ actualExcept);
	
			 if (actualExcept.contains(expecrt)){
				 ispresent =true;
				 exrt.click();
				 break;				 
			 } 
			 }if (ispresent==false){
				 Assert.assertTrue(ispresent, expecrt + " is not present.");
			 }
	}
	
	
	public void deleteNewsFromImageIcon() throws Exception{
		 
		logInfo("Delete the NEWS by hovering the curser on News Icon and the select Delete link.");
		WebElement  delete =  driver().findElement(By.linkText("Delete"));
		delete.click();
		clickOnElement("cssSelector", delInfo);	
		}
	
	// Drag News widget and select ViewMore Link 
	public void dragNewswidgets() throws Exception{
		tm.selectEditWidgetsFromHome();				
		dragCompanyNews();	
		clickOnElement("cssSelector",closeWidgetInHomeBranch);
		selectViewMoreLink();
		
	}
	
	public void dragCompanyNews() throws Exception{
		logInfo("Drag Company NEWS from Edit Widgets Link ");			
			WebElement from = driver().findElement(By.xpath(companyNEWSWidgets));	
			WebElement to = driver().findElement(By.xpath(dragWidgetToLocation));  //  dragWidgetToLocationInHome
			tm.dragAndDropAction(from, to);		
		Thread.sleep(5000);		
	}	   
	
	public void selectSaveAsDraft(){
		logInfo("Select Unpublish And Save as draft ");
		clickOnElement("cssSelector", "");
	}
	
	public void selectViewMoreLink () throws Exception{	
		List <WebElement> vm = driver().findElements(By.cssSelector(vieMorelnk));	
		System.out.println(vm.size());
		for (WebElement vms : vm){			
			vms.click();
			break;
		}		
	}
	
	
	public void sortMarket(String country) throws Exception{
		logInfo("Entered into sortMarket() method");	
		WebElement selMar=driver().findElement(By.cssSelector(sortMarking)) ;
		selMar.click();
		boolean ismarkPresent = false;
		List <WebElement> ma = driver().findElements(By.cssSelector(sortMark));
		for (WebElement mark : ma ){
			if(mark.getText().equalsIgnoreCase(country)){
				ismarkPresent=true;
				mark.click();
				break;
				
			}
		}if (ismarkPresent==false){
			Assert.assertTrue(ismarkPresent, country + " -market is not present");
		}
		
	}
	
public void sortLanguages(String language){
	logInfo("Entered into sortLanguages() method");
	WebElement selMar=driver().findElement(By.cssSelector(sortLangsdp)) ;
	selMar.click();		
	boolean isLangPresent=false;
	List <WebElement> ma = driver().findElements(By.cssSelector(sortLangs));	
	for (WebElement mark : ma ){		
	if(mark.getText().equalsIgnoreCase(language)){
		isLangPresent=true;
				mark.click();
			}
		}if (isLangPresent==false){
			Assert.assertTrue(isLangPresent, country + " -market is not present");
		}
		
	}
	
    public void sortSubscriptions(String subscription){
    	logInfo("Entered into sortSubscriptions() method ");
   	WebElement selMar=driver().findElement(By.cssSelector(sortSubsdp)) ;
	selMar.click();	
	boolean isSubPresent =false;
	List <WebElement> ma = driver().findElements(By.cssSelector(sortSubs));
	for (WebElement mark : ma ){
			if(mark.getText().equalsIgnoreCase(subscription)){
				isSubPresent= true;
			mark.click();
		        }		
	      }if (isSubPresent==false){
	    	  Assert.assertTrue(isSubPresent,subscription +" -subscription is not present" );
	      }
        }

    public void sortRanks(String Rank){
    	logInfo("Entered into sortMarket() method");
	WebElement selMar=driver().findElement(By.cssSelector(sortRankdp)) ;
	selMar.click();	
	boolean isRankPresent =false;
	List <WebElement> ma = driver().findElements(By.cssSelector(sortRank));
	for (WebElement mark : ma ){
		if(mark.getText().equalsIgnoreCase(Rank)){
			isRankPresent= true;
			mark.click();
		         }		
	          }	if (isRankPresent==false){
	        	  Assert.assertTrue(isRankPresent, Rank+ " - Ranker is not present");
	          }
    }
    
    public void sortType(String type){
    	logInfo("Entered into sortType() method");
	WebElement selMar=driver().findElement(By.cssSelector(sortTypedp)) ;
	selMar.click();
	boolean isTypePresent =false;
	List <WebElement> ma = driver().findElements(By.cssSelector(sortType));
	for (WebElement mark : ma ){
		if(mark.getText().equalsIgnoreCase(type)){
			isTypePresent= true;
			mark.click();
		    }    
		}if (isTypePresent==false){
			Assert.assertTrue(isTypePresent,type +" - type is not present" );
		}
	}
     

    public void sortStatus(String status) throws Exception{
    	logInfo("Entered into sortStatus() method");
    boolean isStatusPresent = false;
    Thread.sleep(6000);
    waitOnElement("cssSelector", sortStdp);
	WebElement selMar=driver().findElement(By.cssSelector(sortStdp)) ;
	selMar.click();
	List <WebElement> ma = driver().findElements(By.cssSelector(sortSt));
	for (WebElement mark : ma ){
		if(mark.getText().equalsIgnoreCase(status)){
			isStatusPresent = true;
			mark.click();
			waitOnElement("cssSelector", companyNewsListTitle);		
			break;
		        }
		}if (isStatusPresent==false){
			Assert.assertTrue(isStatusPresent, status + "- type of status is not present");
		}
	 }
	
	

	 public void sortTags(String file){	
		 logInfo("Entered into sortTags() method");
		WebElement selMar=driver().findElement(By.cssSelector(sortTagdp)) ;
		selMar.click();
		List <WebElement> ma = driver().findElements(By.cssSelector(sortTag));
		for (WebElement mark : ma ){
			if(mark.getText().equalsIgnoreCase(file)){
				mark.click();
			}
		}
		
	}
	

      public void searchNews(String search) throws Exception{
    	  inputText("cssSelector", searchNews, search);
    	  driver().findElement(By.cssSelector(searchNews)).submit();
    	  Thread.sleep(4000);
    	  searchStatus();
    	 
      }
      
      public void searchStatus(){
    	  List <WebElement > s = driver().findElements(By.cssSelector(record));
    	  for (WebElement ss :s){    		  
    		  System.out.println("Items are  "+ss.getText());
    	  }   	  
      }  
      
      
  	public void backToOffice() throws Exception, InterruptedException{
		Thread.sleep(5000);
		clickOnLink("linkText", "back to office");
	}
  	
  	
  	
  	public void createNewsTillSave(String NewsTitle, String excerpt ,String ranker,String[] markets,  String[] Subscription) throws Exception{
		logInfo("Create New Company News with subscription of "+Subscription );		
		navigateCompanyNews();
		clickOnButton("cssSelector",compAddCompanyNews);		
		inputTextClear("cssSelector", compTitle);
		inputText("cssSelector", compTitle, NewsTitle);
		waitOnElement("cssSelector", compTitle);
		waitOnElement("cssSelector", compExcerpt);
		inputText("cssSelector", compExcerpt, excerpt);	
		composeText("xpath",composeBody,composeBodyText);
		//rankDefinition(ranker);
		marketLanguages(markets);
		SubscriptionPlanSlection(Subscription);		
		uploadFile("Image",newsThumb);
		//uploadFile("PDF",newsPost);	
		selectIsNews();
		selectIsHighLight();		
  	}
  	
  	
  	public void companyNewsCreation(String NewsTitle, String excerpt ,String ranker,String[] markets,  String[] Subscription) throws Exception{
		logInfo("Create New Company News with subscription of "+Subscription );		
		createNewsTillSave(NewsTitle ,excerpt,ranker, markets,Subscription);	
		selectPublishImmediately();
		clickOnPublishButton();
		confirmationMessage(compNewsToasterText);		
	}
  	
  	
  	public void createNewsAsDraft(String NewsTitle, String excerpt ,String ranker ,String[] markets,  String[] Subscription) throws Exception{
		logInfo("Create New Company News with subscription of "+Subscription +" and save As Draft/Unpublish");		  
		//String excerpt = "Social media can improve your word of mouth marketing with 240 Ways";
		createNewsTillSave(NewsTitle ,excerpt,ranker, markets,Subscription);
		selectPublishImmediately();
		waitOnElement("cssSelector", saveAsDraft);
		clickOnButton("cssSelector", saveAsDraft);
		confirmationMessage(compNewsToasterText);	
		
		
	}
  	
  	public void createNewsWithDate(String NewsTitle,String ranker, String[] markets , String[] Subscription, int date) throws Exception{
    createNewsTillSave(NewsTitle ,excerpt,ranker, markets,Subscription);	
    enterPublishDate(date);			
    clickOnPublishButton();
    confirmationMessage(compNewsToasterText);
    
  	}
  	
  	public void modifyUsersSubscription(String firstName , String lastName,String consultantid, String subscription ) throws Exception{
  		 logInfo("Entered into modifyUsersSubscription() method");  	                                 
  	    um.go2Users(); 
  	    waitOnElement("cssSelector", btnAdvancedSearch);
  	    clickOnElement("cssSelector", btnAdvancedSearch);
  	    waitOnElement("cssSelector", inputAdvConsultantID);
  	    inputTextClear("cssSelector", inputAdvFirstName);
  	    inputText("cssSelector", inputAdvFirstName, firstName);   
  	    inputTextClear("cssSelector", inputAdvLastName);
  	    inputTextClear("cssSelector", inputAdvConsultantID);
  	    inputText("cssSelector", inputAdvLastName, lastName);	      
  	    inputText("cssSelector", inputAdvConsultantID, consultantid);
  	    waitOnElement("xpath", btnAdvSubmit);	
  	    clickOnElement("xpath", btnAdvSubmit);
  	    
  	    waitOnElement("cssSelector", footerMessage);  
  	    boolean isUserFound = um.verifyUserPresentWithFname(firstName);
  	    if(isUserFound){  
  	    	editUserWithFName(firstName);
  	 		waitOnElement("linkText", "Manage Subscription");
  	 		clickOnLink("linkText", "Manage Subscription");
  	    	
  	    	bm.changeSubscriptionPlan4User(subscription); 
  	    }

  	 	
  	  	}
  	  	
  	  	
  	  	public void editUserWithFName(String fName) throws Exception, Exception {
  			logInfo("inside editUser() method.");
  			waitOnElement("xpath", tblAdminUsers); // check and remove later
  			WebElement tblUsers = driver().findElement(By.xpath(tblAdminUsers));
  			List allUsers = tblUsers.findElements(By.tagName("tr"));
  			String before_name = "//*[@id='users']/table/tbody/tr[";
  			String after_name = "]/td[2]/a";
  			boolean isUserFound = false;
  			if (allUsers.size() > 0) {
  				for (int i = 1; i <= allUsers.size(); i++) {
  					WebElement x = driver().findElement(By.xpath(before_name + i + after_name));
  					waitOnElement("xpath", before_name + i + after_name); 
  					String name = x.getText().trim();
  					if (name.equalsIgnoreCase(fName)) {
  						isUserFound = true;					
  						x.click();
  						break;
  					}
  				}
  			}

  			if (isUserFound == false) {
  				Assert.assertTrue(isUserFound, fName + "User is not found");
  			}
  		}
  	
  	public void loginAsSubUser(String user) throws Exception{
  		
  		
	 	um.go2Users();
	 	um.searchUser(user);
	 	boolean isUserFound = um.verifyUserPresent(user);
	 	if(isUserFound){
	 		um.editUser(user);
	 		waitOnElement("linkText", "Login As User");
	 		clickOnLink("linkText", "Login As User");
	 		
	 	} 		
  	}
  	
  	
public void loginAsUser(String user) throws Exception{
  		
  		
	 	um.go2Users();
	 	um.searchUser(user);
	 	boolean isUserFound = um.verifyUserPresent(user);
	 	if(isUserFound){	 		
	 		clickOnLink("linkText", "Login As User");	 		
	 	} 
  		
  		
  	}
	
	public void loginAsUserWithID(String user) throws Exception{		
	 	um.go2Users();
	 	um.searchUser(user); 
	 	waitOnElement("linkText", "Login As User");	 		 		
	 	clickOnLink("linkText", "Login As User");	 		
	 	
			
			
		}


	
	public void verifyMonthlySubsWithdifferentUsers(String TitleOfNews, String user1, String user2) throws Exception{
		  logInfo("Login with distibutor User whose subscription is Yearly. \n"
		  		+ "Verify the NEWs in widgets which should be available. Again login with distibutor whose subscription is Monthly\n"
		  		+ "The News should be display/available in the widget.");		
		  
		   loginAsSubUser(user1);		  	  
		   //Newswidgets();
		   selectViewMoreLink();
		   isNewsTitlePresent(TitleOfNews);
		   editNews(TitleOfNews);	
		   System.out.println("Succesfully!! verified with first User." );		   
		   clickOnLink("cssSelector", logoutHere);
		   waitOnElement("xpath", searchUser);
		   loginAsSubUser(user2);  
		   dragNewswidgets();
		   selectViewMoreLink();
		   verifyNewsNotToPresentInWidget("All",TitleOfNews);
		   clickOnLink("cssSelector", logoutHere);
		   waitOnElement("xpath", searchUser);
	  
		  }
	
	public void verifiyYearllySubsWithdifferentUsers(String TitleOfNews) throws Exception{
		  logInfo("Login with distibutor User whose subscription is Yearly. \n"
		  		+ "Verify the NEWs in widgets which should be available. Again login with distibutor whose subscription is Monthly\n"
		  		+ "The News should be display/available in the widget.");
				  	 	
		        loginAsSubUser(VibeProMonthlyUser);	
		        dragNewswidgets();
				selectViewMoreLink();
				verifyNewsNotToPresentInWidget("All",TitleOfNews);  
				System.out.println("Succesfully!! verified with first User." );
				clickOnLink("cssSelector", logoutHere);
				Thread.sleep(2000); 
		 		     	
				loginAsSubUser(vibeProYearlyUser);  
				dragNewswidgets();
				selectViewMoreLink();
				isNewsTitlePresent(TitleOfNews);
				editNews(TitleOfNews);			  
				clickOnLink("cssSelector", logoutHere);
				Thread.sleep(2000); 
		 	 
		  }
  	
  	
  	public void verifyAdditionalInformation(String pdfFileName){
  		
  		List <WebElement> addInfo = driver().findElements(By.cssSelector(newsInfo));
  		System.out.println(addInfo.size());
  		int infoSize = addInfo.size();
  		String addInform = Integer.toString(infoSize);  		
  		boolean isfilePresent = false;
  		if (addInfo.size()==0){  			
  			Assert.assertEquals(addInform, "expected Additional files"); 			
			System.err.println("Not added additional information");
  			} else {
  				System.out.println("Successfully upload file.");
  				for (WebElement ai : addInfo){
  					if (ai.getText().trim().contains(pdfFileName)){
  						isfilePresent = true;
  					}
  				System.out.println("Files : "+ai.getText());  				
  			}  			
  		} if (isfilePresent==false){
  			Assert.assertTrue(isfilePresent, pdfFileName + " file is not present");
  		}
  	}
  	    
  	  public void createNewsAlertMessages(String NewsTitle , String Subscription ) throws Exception{
  		
  		String highlights =  "Is News and Is Highlights cannot be blank!";  
  		String titleAlert= "Content cannot be blank,Title cannot be blank";
  		String titleAlert2= "Title cannot be blank";
  		String contAlert  = "Content cannot be blank";
  		  
  		
		selectAddCompanyNews();
		clickOnPublishButton();
		confirmationMessage(highlights);
		selectIsHighLight();
		clickOnPublishButton();
		confirmationMessage(titleAlert);
		Thread.sleep(6000);
		enterTitle(NewsTitle);
		clickOnPublishButton();
		confirmationMessage(contAlert);
		Thread.sleep(7000);
		inputTextClear("cssSelector", compTitle);
		enterExcerpt(excerpt);
		composeText("xpath",composeBody,composeBodyText);		
		clickOnPublishButton();	
		confirmationMessage(titleAlert2);
		Thread.sleep(7000);
		enterTitle(NewsTitle);
		validateArchievedDate(-2);
		selectArchievedDate(2);		
		clickOnPublishButton();	
		confirmationMessage(compNewsToasterText);    		  
  		  
  	  }
  	  
  	public void editNewsInRecentPosts(String newsTitle){
   		
   		boolean isRecentPresent =false;
   		List <WebElement> li = driver().findElements(By.cssSelector(recPostList));
   		System.out.println(li.size());
   		for (WebElement lis :li ){
   			if(lis.getText().equalsIgnoreCase(newsTitle)){
   				isRecentPresent = true;
   				lis.click();
   				break;  				
   			}	   			
   		}if (isRecentPresent==false){
   			Assert.assertTrue(isRecentPresent, newsTitle + " - News title is not present");
   		}   		
   	}
  	
  	
	public void  verifyTagsofNews(String newsTag){
   		
   		String Text = "Tags";
   		WebElement rec = driver().findElement(By.cssSelector(tagsInWid));
   		Assert.assertEquals(rec.getText().trim(), Text);
   		boolean isTagPresent =false;
   		List <WebElement> li = driver().findElements(By.cssSelector(tagList));
   		System.out.println(li.size());
   		for (WebElement lis :li ){
   			if(lis.getText().equalsIgnoreCase(newsTag)){
   				isTagPresent = true;
   				break;  				
   			}	   			
   		}if (isTagPresent==false){
   			Assert.assertTrue(isTagPresent, newsTag + " - News tags is not present");
   		}
	
   		
   	}
	
	
	public void newsfieldValidate(String ranker) throws Exception{		
		 String newsTitle = "validateTitlewithmaxcharaters"+TestBase.generateRandomString()+" 1234567890abcdefghij1234567890abcdefghij1234567850";
		 String[] reptitle = StringUtils.split(newsTitle," ");
		 String splitedTitle = reptitle[0];
		selectAddCompanyNews();
		enterTitle(newsTitle);
		enterExcerpt(excerpt);
		composeText("xpath",composeBody,composeBodyText);
		rankDefinition(ranker);
		marketLanguages(languageList);
		SubscriptionPlanSlection(subscripListOfVibe);
		selectIsNews();		
		selectPublishImmediately();		
		clickOnPublishButton();
		confirmationMessage(compNewsToasterText);
		
	}
   
   
	public void validateNewsTitleSize(String title) throws Exception{	
		logInfo("Enetered into validateNewsTitleSize(String title) method");
		boolean isNews = false;
		List<WebElement> newsList =  driver().findElements(By.cssSelector(companyNewsListTitle));
		for (WebElement list : newsList){
			if(list.getText().contains(title)){	
				isNews = true;
				list.click();
				WebElement titSize = driver().findElement(By.cssSelector(titleNews));
				int titSiz = titSize.getText().length();
				String AcutTitleSize = Integer.toString(titSiz);
				System.out.println("size is "+ AcutTitleSize);
				Assert.assertEquals(AcutTitleSize, "50");
				break;
			}
		}if(isNews==false){
			Assert.assertTrue(isNews, title +" is not found");
		}
	}
	
	// eidted news will be updated with new file
	public void updateNewsWithFile() throws Exception{
		logInfo("Enetered into updateNewsWithFile() method");		
		 uploadFile("PDF",newsPost);	
		 clickOnPublishButton();
		 confirmationMessage(companyNewUpdated);
		
	}
  	
	public void updateNewsWithSubscription(String newsTitle, String[] subscription) throws Exception{		   
	     navigateCompanyNews();
		 isNewsTitlePresent(newsTitle);			  	  
		 editNews(newsTitle);
		 waitOnElement("cssSelector", compEdit);
		 clickOnElement("cssSelector", compEdit);
		// selectEditButton();
		 subscriptionSlectionOfEdited(subscription);
		 clickOnPublishButton();
		 confirmationMessage(companyNewUpdated);		 
	}
	
	public void updateNewsWithMarket(String newsTitle, String[] languages) throws Exception{		   
	     navigateCompanyNews();
		 isNewsTitlePresent(newsTitle);			  	  
		 editNews(newsTitle);
		 waitOnElement("cssSelector", compEdit);
		 clickOnElement("cssSelector", compEdit);
		 updateMaketOfEditedNews(languages);
		 clickOnPublishButton();
		 confirmationMessage(companyNewUpdated);		 
	}
	
	public void updateMaketOfEditedNews(String[] markets) throws Exception{
		try{
			waitOnElement("cssSelector", compLang);
			clickOnElement("cssSelector", compLang);			
			WebElement marketAll = driver().findElement(By.cssSelector(markAll));
			if (!(marketAll.isSelected())){
				marketAll.click();
				waitOnElement("cssSelector", markAll);
				marketAll.click();			
				}
			boolean ismarket = false;
			int subcription = markets.length;			
			List <WebElement> mar = driver().findElements(By.cssSelector(compLangDD));			
			for (WebElement mars : mar){	
				for(int j=0; j<=subcription-1;j++){				
				if (mars.getText().equalsIgnoreCase(markets[j])){
					ismarket = true;
					if(!(mars.isSelected())){
					mars.click();
					break;
						}
				    }	
				}
			}if(ismarket==false){
				Assert.assertTrue(ismarket, markets + "- market lang is not found");
			}
		}catch (WebDriverException we ){			
			System.err.println("Failed!! Not able to select markets.");
		}				
	}
  	
  	

}
