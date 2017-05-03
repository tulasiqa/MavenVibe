package vibe.mycommunity.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.glass.events.KeyEvent;

import vibe.myprofile.tests.MyProfileMethods;
import vibe.mywebsite.tests.MyWebsite_Tests;
import vibe.widgets.tests.WidgetsMethods;
import common.TestBase;

public class CommunityMethods extends TestBase{
	
	MyProfileMethods pm = new MyProfileMethods();
	WidgetsMethods wm = new WidgetsMethods();
	MyWebsite_Tests mw= new MyWebsite_Tests();
	String reportText = "Above content seems to be abused - "+generateRandomString();

	public void reportAboutPhoto() throws Exception{
		navigateCommunity();	
		selectReportLink();
		enterReportAndSubmit();
	}

   public void commentOnPhoto() throws Exception{
	    	
	   logInfo("Comment the photo and verify the commented text under photo");  
	   try{ 
		 navigateCommunity();
		 verifyLinksUnderPhoto();
		 verifyCommentLink();
		 enterCommentsAndMatch();
	  }catch (Exception e){
		  logger.error((new Exception().getStackTrace()[0].getMethodName())+"    failed!!");
	  }
	  logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");

   }	

   public void navigateCommunity() throws Exception{
		 	
		logInfo("Navigating to Community >> community");
		try{
			clickOnLink("linkText", "Community");
			implicityWaits(5);			
			clickOnElement("xpath", secondaryCommunityTab);
			implicityWaits(5);
		}catch (Exception e){
			logger.error("Failed!! Not able to navigate to community screen.");
			
		}
			
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
		}
	
	public void clickOnAddPhotoVideoLink() throws Exception{
		 
		logInfo("Select AddPhoto/Video link in Community page ");
		
		try{
			
			clickOnLink("linkText", lnkAddPhotoVideo);
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new Exception("failed !! Not able to select a link of AddPhoto/Video");
		}
		
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
		
	}

	public void addNewPhotoAndShare() throws Exception{ 
		 
		logInfo("Add Photo/video screen - Enter details of Photo Url and Share it ");
	
		try{
			inputText("xpath",inputPhotoURL,photoURL_text);
			inputText("xpath",inputPhotoTitle,photoTitle_text);
			inputText("xpath",inputPhotoDesc,photoDesc_text);
			inputText("xpath",inputPhotoTags,photoTags_text);
			
			selectFromDropdown("xpath",drpdnVisibilitySettings,"byVisibleText",visibilitySettings_text);
			clickOnButton("xpath",btnSharePhoto);
			}
		catch (Exception e){
			logger.error("Failed!! unable to enter in mandatory fields of Add Photo/video.");
		}
		
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
	}

	public void selectPhotoImage() throws Exception{
		 
		logInfo("select Photo Image ");	
		try{
			clickOnButton("xpath", photoImage);
		}catch (Exception e){
			logger.error("Failed!! Not able to select photo Image");
					
		}	
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
	}
	
	public void verifyLinksUnderPhoto() throws Exception{
		 
		logInfo("Verify all Links are present!");	
		try{
			verifyLinkPresent("Report");
			verifyLinkPresent("Comment");
			verifyLinkPresent("Like");
			verifyLinkPresent("Share");
			verifyLinkPresent("Feature");
			
		}catch (NoSuchElementException nse){
			logger.error("Failed!! some of the links are missing.");			
		}	
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
		
	}
	
	public void selectReportLink() throws Exception, InterruptedException{
		 
		logInfo("Select Report Link");	
		try{
			clickOnLink("linkText","Report");
//			Thread.sleep(5000);
			
		}catch (NoSuchElementException nse){
			logger.error("Failed!! No such Report link is present");			
		}
		
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
		
	}
	
	public void enterReportAndSubmit() throws Exception{
		 
		logInfo("Report - Enter the text and submit as abuse. ");	
		try{	
			
	         /*driver().findElement(By.cssSelector(reportTextArea)).sendKeys(reportText); 	*/
	    	inputText("cssSelector",reportTextArea,reportText );		//reportText		 reportTextArea
	    	
	    	Robot rb = new Robot();
	    	/*rb.keyPress(KeyEvent.VK_1);
	    	rb.keyPress(KeyEvent.VK_2);
	    	rb.keyPress(KeyEvent.VK_3);
	    	rb.keyPress(KeyEvent.VK_4);*/
	    	clickOnElement("cssSelector", reportButton);
	    	rb.keyPress(KeyEvent.VK_TAB);
	    	rb.keyRelease(KeyEvent.VK_TAB);
	    	
	    	
	    	rb.keyPress(KeyEvent.VK_ENTER);
	    	rb.keyRelease(KeyEvent.VK_ENTER);
			
		}catch (NoSuchElementException nse){
			logger.error("Failed!! No such Report link is present");			
		}
		
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
		
	}
	
	
	public void verifyCommentLink() throws Exception{
		 
		logInfo("Verify whether Link comment is present!");	
		try{
			verifyLinkPresent("Comment");
		}catch (NoSuchElementException nse){
			logger.error("Failed!! No such Comment link is present");			
		}	
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
		
	}
	
	public void enterCommentsAndMatch() throws Exception{
		 
		logInfo("Select comment Link and enter your comments and Post it");	
		try{
			enterComment();	
			matchEnteredComments();	
			
		}catch (NoSuchElementException nse){
			logger.error("Failed!! Either Comments or matches mismatched!");			
		}
		
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
		
	}
	
	public void enterComment() throws Exception {
		
		try{ 
			clickOnLink("linkText","Comment");
			inputText("cssSelector",commentTextArea, commentText );
			clickOnElement("cssSelector", btnPost);
//			Thread.sleep(5000);
			}catch(NoSuchElementException nse){
				logger.error("Failed!! No such link is present to select");
				
			}
		
	}

	public boolean matchEnteredComments(){
		 
		logInfo("Match the entered comments which is "+commentText );
		return driver().findElement(By.cssSelector(commentedText)).getText().equals(commentText);	
		}

	public void deleteComment() throws Exception{
    	 clickOnLink("xpath", delComment);
		 logInfo("Deleted Succesfully");
	 }

	public void selectFeature () throws Exception{
		 
		logInfo("Trying to select 'Feature' link");	
		try{
			getText("cssSelector", feature, "Link name is ");
		    clickOnLink("cssSelector", feature);
		   }catch (Exception e){
			logger.error("Failed!! Not able to select Feature link");
					
		}
		
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
	}
	
	
	public void selectLike () throws Exception{
		 
		logInfo("Trying to select 'Like' link");	
		try{
	    getText("cssSelector", like, "Link name is ");
		clickOnLink("cssSelector", like);
		}catch (Exception e){
			logger.error("Failed!! Not able to select Like link");
					
		}
		
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
	}
	
	public void selectComment () throws Exception{
		 
		logInfo("Trying to select 'Comment' link");	
		try{
		getText("cssSelector", comment, "Link name is ");
		clickOnElement("cssSelector", comment);
		}catch (Exception e){
			logger.error("Failed!! Not able to select Comment link");
					
		}
		
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
	}
	
	public void selectEdit() throws Exception{
		 
		logInfo("Trying to select 'Edit' link");	
		try{
			clickOnLink("cssSelector", edit);
		}catch (Exception e){
			logger.error("Failed!! Not able to select edit link");
					
		}
		 
	}	

   public void assertActivities(){
	   WebElement feat = driver().findElement(By.cssSelector(feature));
	   WebElement lk = driver().findElement(By.cssSelector(like));
	   WebElement comm = driver().findElement(By.cssSelector(comment));
       WebElement shr = driver().findElement(By.cssSelector(share));
      /* WebElement hde = driver().findElement(By.cssSelector(selector));*/
	   
	   Assert.assertEquals(feat.getText(), "Feature");
	   Assert.assertEquals(lk.getText(), "Like");
	   Assert.assertEquals(comm.getText(), "Comment");
	   Assert.assertEquals(shr.getText(), "Share");
	   
	    
   }


	public void retrieveItems (){
		List <WebElement> select = driver().findElements(By.cssSelector(selection));
		System.out.println("get count of "+select.size() );
		for (WebElement items:select ){
			
			System.out.println("items are : "+ items.getText());
			items.click();
			System.out.println("exec");
		}
		 
		
	}
	
	
	public void sharePhotoOrVideoTypeofSelection(String slection) throws Exception{
		 	
		logInfo("Select "+ slection +" from selction");
		
		try{
			Select select = new Select(driver().findElement(By.cssSelector(selection)));
			select.selectByVisibleText(slection);
			
			
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception ("Failed!!  Not able select of Selection");
					}
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
		
	}

	public void navigate2ReportedAbuseUnderDistManager() throws Exception{
		 	
		logInfo("Navigate to Go To Admin >> Distributor manager >> Reported Abuse");
		try{
		clickOnLink("xpath", lnkGotoAdmin);
		clickOnLink("linkText", distributorManagerLink);
		clickOnLink("linkText", reportAbuseLink);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception ("Failed!!  Not able to navigate to Reported Abuse");
					}
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
	

	}

    public void isAbuseReportnotTobePresent(String abuseReport) throws Exception{
    	 	
    	logInfo("Trying to verify is abuse Report present in the list of Pending Actions under Reported Abuse.");
    	
    	boolean isreportPresent = true;
//    	Thread.sleep(5000);
    	waitOnElement("cssSelector",abuseList);
    	List <WebElement> reportList = driver().findElements(By.cssSelector(abuseList));    	
    	for (int i=1; i <=reportList.size(); i++)    		
    	{ 
    		WebElement rp = driver().findElement(By.cssSelector(abList1+i+abList2));
    		System.out.println("Items are : "+rp.getText());
    		if(rp.getText().equalsIgnoreCase(abuseReport)){
    			
    			isreportPresent = false;
    			
    		}
    		
    	}if(isreportPresent==true){
    		
    		logInfo(abuseReport + " is not matched in Pending Actions list.");
    		Assert.assertTrue(isreportPresent, abuseReport +" is not matched in Pending Actions list.");
    		
    	}

    }  
		

	public void selectAbusedFromPendingAction(String abuseReport) throws Exception{
		 	
		logInfo("Select Abused Report from Pending Action and then delete.");
		try{
			List <WebElement> reportList = driver().findElements(By.cssSelector(abuseList));
			System.out.println("Number of reported Itmes are " +reportList.size());
		
			for (int i=1; i <=reportList.size(); i++)
			{ 
				WebElement rp = driver().findElement(By.cssSelector(abList1+i+abList2));
				System.out.println("Items are : "+rp.getText());
				if(rp.getText().equalsIgnoreCase(abuseReport)){
					rp.click();
					clickOnElement("cssSelector", deleteFromDistMan);
//					Thread.sleep(5000);
					waitOnElement("cssSelector", deleteOkFromDistMan);
					clickOnButton("cssSelector", deleteOkFromDistMan);
					confirmationMessage(confMsgPhotoDeleted);	
					break;
				}
	
			}
		
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception ("Failed!!  Not able to retrieve Reported Abuse List");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
		
	}
  
	public void restoreAbusedReport(String abuseReport) throws Exception{
		 	
		logInfo("Restore the abused report.");
		try{
		
		List <WebElement> reportList = driver().findElements(By.cssSelector(abuseList));
		System.out.println("Number of reported Itmes are " +reportList.size());
		for (int i=1; i <=reportList.size(); i++)
		{ 
			WebElement rp = driver().findElement(By.cssSelector(abList1+i+abList2));
			WebElement restore = driver().findElement(By.cssSelector(abRestore1+i+abRestore2));
			System.out.println("Items are : "+rp.getText());
			if(rp.getText().equalsIgnoreCase(abuseReport)){
				if(restore.isEnabled()){			
				restore.click();
				
				/*Thread.sleep(3000);*/
				//confirmEventDeleteModal();
				confirmOK();
				
				break;
			}}
			
		}}
		catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception ("Failed!!  Not able to retrieve Reported Abuse List");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");

	 
	
	}

	public void deleteAbusedReport(String abuseReport) throws Exception{
		 	
		logInfo("Delete abused Report.");
		try{
			waitOnElement("cssSelector",abuseList);
			List <WebElement> reportList = driver().findElements(By.cssSelector(abuseList));
			System.out.println("Number of reported Itmes are " +reportList.size());
			for (int i=1; i <=reportList.size(); i++)
			{ 
				WebElement rp = driver().findElement(By.cssSelector(abDel1+i+abDel1));
				WebElement restore = driver().findElement(By.cssSelector(abRestore1+i+abRestore2));
				System.out.println("Items are : "+rp.getText());
				if(rp.getText().equalsIgnoreCase(abuseReport)){
					if(restore.isEnabled()){
					System.out.println(restore.getText());
					restore.click();
					Robot rb = new Robot();
					rb.delay(5000);
					rb.keyPress(KeyEvent.VK_ENTER);
					rb.keyRelease(KeyEvent.VK_ENTER);
					break;
				}}	
		}}
		catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new Exception ("Failed!!  Not able to retrieve Reported Abuse List");
		}
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
		 
	
	}

	public void selectProperAbusedReport() throws Exception{
		 
		logInfo("Select "+ inputPhotoTitle_text + "Abused Report and Delete");
		try{
		List <WebElement> reportList = driver().findElements(By.xpath(reportAbuseList));
		
		for(WebElement reports:reportList ){
			System.out.println("Items are : "+reports.getText());
			if(reports.getText().equals(inputPhotoTitle_text)){
				Actions actions = new Actions(driver());
				reports.click();
				actions.clickAndHold().release().build().perform();
				implicityWaits(10);
				break;
			}
			
		}}catch(Exception e){
			logger.error("Failed!!  "+ inputPhotoTitle_text+ "is not avaialble to select");
					}
		logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
	
		 
	}

      public void sendEmailToRepoters(String subject) throws Exception{
    	  
    	   
    	  logInfo("Verify Email Configuration Window and send Email with Subject & Reason to All Reporters.");
	
//    	  Thread.sleep(5000);
    	  driver().switchTo().frame("Email Configuration");
    	  /*for (String winHandle : driver().getWindowHandles()) {
    		    driver().switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
    		}*/
    	  /*Robot rb = new Robot();
    	  rb.delay(5000);
    	  rb.keyPress(KeyEvent.VK_TAB);
    	  rb.keyRelease(KeyEvent.VK_TAB);
    	  rb.keyPress(KeyEvent.VK_TAB);
    	  rb.keyRelease(KeyEvent.VK_TAB);*/
    	  
    	/*  driver().navigate().refresh();*/
    	  
    	/* driver().switchTo().window("Email Configuration");
    	  System.out.println(driver().findElement(By.cssSelector(eConfigTitle)).getText());*/
    	  
    	/*  Assert.assertTrue(isEmailConfigurationPresent());*/
    	  getText("cssSelector", eSub, "sub is ");
    	  /*inputTextClear("cssSelector", eSub);
    	  inputText("cssSelector", eSub, subject);
    	  inputTextClear("cssSelector", eReason);
    	  inputText("cssSelector", eReason, subject);
    	  clickOnElement("cssSelector", eSendMail); */   	 
	
	     
      }

    
      
     public boolean isEmailConfigurationPresent(){
  		return getEmailConfiguration().matches(eConfigTitleText);
  		
  	}
      
    public String getEmailConfiguration(){  		
  		return driver().findElement(By.cssSelector(eConfigTitle)).getText();
  	}

	public void verifyAllTypesPosts(String postName) throws Exception{    	
    	List<WebElement> allPhotos = driver().findElements(By.cssSelector("div.post-heading > h3 > a"));
		logInfo("Total posts =" +allPhotos.size());    		
		boolean isMatchFound = false;
		for(WebElement x : allPhotos){
			String actVal = x.getText().trim(); 
			isMatchFound =true;
			
			if (actVal.equalsIgnoreCase(postName)){
				
				Assert.assertTrue(isMatchFound, postName);
				System.out.println("Successfully !! Post is identified as :  " +postName);
				
				implicityWaits(5);   				
						
					} else    					
				
		/*if(isMatchFound==false)*/{
			Assert.assertTrue(isMatchFound, postName+ "is not avaiable in the grid");
			
		}}

    }

    public void verifyPhotos(String photoDesc) throws Exception, IOException, AWTException{    	
       	List<WebElement> allPhotos = driver().findElements(By.cssSelector(photosFrame));
		logInfo("Total posts =" +allPhotos.size());    		
		boolean isMatchFound = false;
		for(WebElement x : allPhotos){
			String actVal = x.getText(); 
			isMatchFound =true;
			
			if (actVal.equalsIgnoreCase(photoDesc)){
				
				Assert.assertTrue(isMatchFound, photoDesc);
				System.out.println("Successfully !! Photo is identified and name as :  " +photoDesc);
				
				implicityWaits(5); 
				
				pm.deletePhoto();
				
						
					}/* else    					
				
		if(isMatchFound==false){
			Assert.assertTrue(isMatchFound, photoDesc+ " photo is not avaiable in the grid");
			
		}*/}        
        
        }
    
    public void AssertsCommunity(){
    	Assert.assertTrue(isDefaultPostTextPresent());
    	 
    }
    
    
    public boolean isDefaultPostTextPresent(){
		 	
		logInfo("Assert-  Default Post Text in TextArea");
		return getDefaultPostText().matches(postText);
		
	}
    
    
    public String getDefaultPostText(){
		return driver().findElement(By.cssSelector(inputCommunityStatus)).getText();
	}
    
    
    public void getcommHeader(){
	  driver().findElement(By.cssSelector(comHeader)).getText();
	}
    
    public boolean isAddPhotoVedioPresent(){
		 	
		logInfo("Assert-Link Add Photo/Vedio present.");
		return getlinkAddPhotoVedio().matches(lnkAddPhotoVideo);
		
	}
    
    
    public String getlinkAddPhotoVedio(){
		return driver().findElement(By.cssSelector(comPhoto)).getText();
	}
    
    public boolean isAddBlogPostPresent(){
		 	
		logInfo("Assert-Link Add Blog Post present");
		return getBlogPost().matches(lnkAddBlog);
		
	}
    
    
    public String getBlogPost(){
		return driver().findElement(By.cssSelector(comBlog)).getText();
	}
    
    
    public void sharethePost(String postName) throws Exception {
    	 			
    	logInfo("Verify the post and Share it.");		
		//navigate2CommunityPage();		
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allMessages.size());		
		
		String after_name = "]/div/div[1]";		
		String after_share = "]/div/div[2]/span[1]/span[3]/a";
				
		System.out.println("Total messages ="+ allMessages.size());
		for(int i=1;i<=allMessages.size();i++){
			WebElement x = driver().findElement(By.xpath(activitiesBefore+i+after_name));
			String name = x.getText().trim();
			
		
			if(name.contains(postName)){
				System.out.println("post are "+name);
				logInfo(postName + " community post match found.");
				isMatchFound = true;
				WebElement e = driver().findElement(By.xpath(activitiesBefore+i+after_share));			
				e.click();
				logInfo("clicked on the link " + e.getText().trim());
				
				break;
			}
		}
		
		 if(isMatchFound==false){
			logInfo(postName + " post match not found in community wall.");
			Assert.assertTrue(isMatchFound, postName + " post match not found in community wall.");
		}
		 
		  
	}   
    
    public boolean likeThePost(String postName) throws Exception {
	 	
	    logInfo("Verify appropriate post and select Like.");	   
	    navigate2CommunityPage();	   
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		 boolean isVerified = false;	
		logInfo("Total posts =" +allMessages.size());		
		String after_name = "]/div/div[1]";		
		String after_like = "]/div/div[2]/span[1]/span[1]/a";			
		for(int i=1;i<=allMessages.size();i++){
			WebElement x = driver().findElement(By.xpath(activitiesBefore+i+after_name));
			String name = x.getText().trim();			
			if(name.contains(postName)){				
				isVerified = true;
				waitOnElement("xpath",activitiesBefore+i+after_like);
				WebElement selectLike = driver().findElement(By.xpath(activitiesBefore+i+after_like));				
//				Thread.sleep(4000);
				selectLike.click();
//				Thread.sleep(4000);				
				navigate2CommunityPage();				
				break;
			}
		}
		
		 if(isVerified==false){
			logInfo(postName + " post match not found in community wall.");
			Assert.assertTrue(isVerified, postName + " post match not found in community wall.");
		} 
		 return isVerified;
	}
    
    
    public void avatarUserNameSelection(String postName) throws Exception {
    	 	
	    logInfo("Verify appropriate post and select Avatar.");	   
	    navigate2CommunityPage();	   
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allMessages.size());		
		String after_name = "]/div/div[1]";		
		String after_username = "]/div/h4/a";
		
		for(int i=1;i<=allMessages.size();i++){
			WebElement x = driver().findElement(By.xpath(activitiesBefore+i+after_name));
			String name = x.getText().trim();			
			if(name.contains(postName)){				
				isMatchFound = true;
				WebElement selectLike = driver().findElement(By.xpath(activitiesBefore+i+after_username));				
//				Thread.sleep(4000);
				selectLike.click();						
								
				break;
			}
		}
		
		 if(isMatchFound==false){
			logInfo(postName + " post match not found in community wall.");
			Assert.assertTrue(isMatchFound, postName + " post match not found in community wall.");
		} 
	}
    
    public void hidePostStatus(String statusMessage) throws Exception {
    	 	
	    logInfo("Verify appropriate post and select Like.");
	    navigate2CommunityPage();		
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allStatus = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;			
		String after_name = "]/div/div[1]";		
		String after_hide = "]/div/div[2]/span[1]/span[4]/a";	
	
		for(int i=1;i<=allStatus.size();i++){
		WebElement e = driver().findElement(By.xpath(activitiesBefore+i+after_name));
		String status = e.getText().trim();
			if(status.matches(statusMessage)){
				isMatchFound = true;
				WebElement hide = driver().findElement(By.xpath(activitiesBefore+i+after_hide));
//				Thread.sleep(4000);
				hide.click();			
				break;
			}
		}
		
	   if(isMatchFound==false){
			logger.error(statusMessage + " status post match not found in community wall.");
			Assert.assertTrue(isMatchFound, statusMessage + " status post match not found in community wall.");
		} 
	}
    
public void likeThePostComments(String postName) throws Exception {
	 	
	    logInfo("Verify appropriate post and select Like.");	   
	    navigate2CommunityPage();	   
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allMessages.size());		
		String after_name = "]/div/div[1]";		
		String after_like = "]/div/div[2]/span[1]/span[1]/a";			
		for(int i=1;i<=allMessages.size();i++){
			WebElement x = driver().findElement(By.xpath(activitiesBefore+i+after_name));
			String name = x.getText().trim();			
			if(name.contains(postName)){				
				isMatchFound = true;
				WebElement selectLike = driver().findElement(By.xpath(activitiesBefore+i+after_like));				
//				Thread.sleep(4000);
				selectLike.click();
//				Thread.sleep(4000);				
				navigate2CommunityPage();				
				break;
			}
		}
		
		 if(isMatchFound==false){
			logInfo(postName + " post match not found in community wall.");
			Assert.assertTrue(isMatchFound, postName + " post match not found in community wall.");
		} 		
	}
	
    
    
    public void retriveLikeCount(String postName) throws Exception {
    	logInfo("Verify the post & like it and then Get the No of Like counts from like Icon.");	
    	navigate2CommunityPage();
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		String after_name = "]/div/div[1]";		
		String after_LikeCount = "]/div/div[2]/span[2]/span[2]/span";		
		for(int i=1;i<=allMessages.size();i++){
			WebElement x = driver().findElement(By.xpath(activitiesBefore+i+after_name));
			String name = x.getText().trim();		
			if(name.contains(postName)){				
				WebElement e = driver().findElement(By.xpath(activitiesBefore+i+after_LikeCount));
				String likeCount = e.getText();
				int intLike = Integer.parseInt(likeCount);
				System.out.println("After liked "+intLike);	
				likeThePostComments(postName);
				break;
			}
			
		}  
	} 
    
    public void validatePostComment() throws Exception {
    	logInfo("Validating Post textbox without entering data.");		
//		Thread.sleep(2000);	

    	waitOnElement("xpath",btnCommunityPost);
		clickOnElement("xpath",btnCommunityPost);
	
		/*inputText("cssSelector",inputCommunityStatus,statusMessage)*/;
		
		 
	}

    
    public void addPhotoInCommunity(String photoTitle, String communityType) throws Exception {
		logInfo("inside addCommunityPhoto() method ...");
		navigate2CommunityPage();
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");
//		Thread.sleep(5000);
		verifyElementPresent("xpath",inputPhotoURL);
		inputText("xpath",inputPhotoURL,photoURL_text);
		inputText("xpath",inputPhotoTitle,photoTitle);
		inputText("xpath",inputPhotoDesc,photoDesc_text);
		inputText("xpath",inputPhotoTags,photoTags_text);
		selectFromDropdown("xpath",drpdnVisibilitySettings,"byVisibleText",communityType);
		clickOnButton("xpath",btnSharePhoto);
		 
		}
    

    public void navigate2CommunityPage() throws InterruptedException{
		logInfo("inside navigate2CommunityPage() method..");
//		Thread.sleep(5000);
		driver().navigate().to(appUrl + "community/wall");
	}
	
	
	
	public void addBlogPost(String postName) throws Exception{
		logInfo("inside addBlogPost() method..");
		navigate2CommunityPage();
		
		clickOnLink("linkText", "Add Blog Post");
//		Thread.sleep(4000);
		waitOnElement("cssSelector",inputAddPostTitle);
		inputText("cssSelector",inputAddPostTitle,postName); 
		
		composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new blog");
//		composeTextOnElement();
		inputText("cssSelector",inputAddPostExcerpt,addPostExcerpt_text);
		selectFromDropdown("cssSelector",drpdnAddPostVisibility,"byVisibleText",visibility_text_community);
		selectRadioOrCheckbox("cssSelector", chkPublishPost);
		clickOnButton("cssSelector",btnCreatePost);
		String url = driver().getCurrentUrl();
		if(url.contains("idlife-stage")){
			waitOnElement("xpath","//*[contains(@id,'rules_web_alert_modal_')]/div/div[1]/div/div/div[3]/button");
			clickOnElement("xpath","//*[contains(@id,'rules_web_alert_modal_')]/div/div[1]/div/div/div[3]/button");
		}
		logInfo("clicked on create post button.");
	}


	
	public boolean verifyPostIsPresent(String postName) throws Exception {
		logInfo("inside verifyPostisPresent() method.");
		Thread.sleep(5000);
		waitOnElement("xpath",paneCommunityWall);
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPhotos = panel.findElements(By.xpath(paneCommunityWallContent));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allPhotos.size());

		for(WebElement x : allPhotos){
			String actPostName = x.getText().trim();
			System.out.println(x.getText().trim());
			if (actPostName.equalsIgnoreCase(postName)){   
				logInfo(postName + " community post match found.");
				isMatchFound = true;
				break;
			}
		}
		
		if(isMatchFound==false){
			logInfo(postName + " community post match not found.");
			Assert.assertTrue(isMatchFound, postName + " community post match not found.");
		}
		return isMatchFound;
	}
	
	// implemenetd on July -27
	
	public void deleteCommunityPost(String postName) throws Exception {
		logInfo("inside deleteCommunityPost() method.");
		navigate2CommunityPage();		
		List <WebElement> allVideos = driver().findElements(By.xpath(paneFeaturedVideos));
		int totalVideos = allVideos.size();
		logInfo("Total images in Featured Photos panel = " +totalVideos);			
		boolean isMatchFound = false;			
		List<WebElement> allMessages= driver().findElements(By.cssSelector(commList));
		if(totalVideos >0){				
			for (WebElement all : allMessages){
				String videoName = all.getText().trim();
				System.out.println("Image name = " +videoName);
				if(videoName.contains(postName)){
					isMatchFound = true;
					System.out.println(" match found in featured widget = " +videoName);
					all.click();
					try {
						WebElement delete = driver().findElement(By.linkText("Delete"));
						delete.click();
						confirmOK();
						break;
					 }
					 catch (Exception e) {
						 Assert.assertFalse(true,"Unable to find the delete link in the workspace.");
					 }
					
				}
			}
		}
		
		if(isMatchFound==false){
			logInfo(postName + " community post match not found to delete.");
			Assert.assertTrue(isMatchFound, postName + " community post match not found to delete.");
		}
	}

	
	public void deleteCommunityPost_old(String postName) throws Exception {
		logInfo("inside deleteCommunityPost() method.");
		navigate2CommunityPage();
		
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPhotos = panel.findElements(By.xpath(paneCommunityWallContent));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allPhotos.size());
		
		for(WebElement x : allPhotos){
			String actPostName = x.getText().trim();
			implicityWaits(2);
			if (actPostName.contains(postName)){    
				logInfo(postName + " community post match found to delete.");
				x.click();
//				Thread.sleep(5000);

				waitOnElement("linkText","Delete");
				clickOnLink("linkText","Delete");
//				Thread.sleep(2000);
				 Robot robot = new Robot();
				 robot.keyPress(KeyEvent.VK_ENTER);	
				 robot.keyRelease(KeyEvent.VK_ENTER);
				isMatchFound = true;
				break;
			}
		}
		
		if(isMatchFound==false){
			logInfo(postName + " community post match not found to delete.");
			Assert.assertTrue(isMatchFound, postName + " community post match not found to delete.");
		}
	}
	
	
	public void addCommunityPhoto(String photoTitle) throws Exception {
		logInfo("inside addCommunityPhoto() method ...");
		navigate2CommunityPage();
//		Thread.sleep(5000);
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");
//		Thread.sleep(5000);
		waitOnElement("xpath",inputPhotoURL);
//		Thread.sleep(3000);
		inputText("xpath",inputPhotoURL,photoURL_text);
		inputText("xpath",inputPhotoTitle,photoTitle);
		System.out.println("title is "+ photoTitle);
		inputText("xpath",inputPhotoDesc,photoDesc_text);
		inputText("xpath",inputPhotoTags,photoTags_text);
		selectFromDropdown("xpath",drpdnVisibilitySettings,"byVisibleText",visibilitySettings_text);
		clickOnButton("xpath",btnSharePhoto);
		logInfo("clicked on share button of Community - AddPhoto page.");
		}
	
	
	public void addCommunityVideo(String videoTitle) throws Exception {
		logInfo("inside addCommunityVideo() method ...");
		navigate2CommunityPage();
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");
	
		verifyLinkPresent("Add Video");
		clickOnLink("linkText", "Add Video");
		
		verifyElementPresent("xpath",inputVideoURL);
		inputText("xpath",inputVideoURL,videoURL_text);
		inputText("xpath",inputVideoTitle,videoTitle);
		inputText("xpath",inputVideoDesc,videoDesc_text);
		inputText("xpath",inputVideoTags,videoTags_text);
		selectFromDropdown("cssSelector",drpdnVisibilityVideoSettings,"byVisibleText",drpdnVisibilityVideoSettings_text);
		clickOnButton("xpath",btnShareVideo);
		logInfo("clicked on share button of Community - AddVideo page.");

	}

	public void postStatus(String statusMessage) throws Exception {
		logInfo("inside postStatus() method.");
		navigate2CommunityPage();
		
		verifyElementPresent("cssSelector",inputCommunityStatus);
		inputText("cssSelector",inputCommunityStatus,statusMessage);
		clickOnElement("xpath",btnCommunityPost);
		Thread.sleep(4000);
	}	

	
	public boolean verifyStatusPostIsPresent(String statusMessage) throws Exception {
		logInfo("inside verifyStatusPostIsPresent() method.");
		//navigate2CommunityPage();
		waitOnElement("xpath",paneCommunityWall);
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		waitOnElement("xpath",paneCommunityWallStatus);
		List<WebElement> allStatus = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allStatus.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";
	
		for(int i=1;i<=allStatus.size();i++){
			waitOnElement("xpath",before_name+i+after_name);
			WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
			System.out.println(e.getText().trim());
			String status = e.getText().trim();
				if(status.matches(statusMessage)){
					isMatchFound = true;
					logInfo(statusMessage + " status post match found.");
					break;
				}
			}
		
		if(isMatchFound==false){
			logInfo(statusMessage + " status post match not found.");
			Assert.assertTrue(isMatchFound, statusMessage + " status post match not found in community wall.");
		}
		return isMatchFound;
	}
	
	public void postActivityLinks(String linkStatus) throws InterruptedException{
		logInfo("inside postActivityLinks() method.");
		List<WebElement> links = driver().findElements(By.xpath("//div[@class='activities-stream']/div[1]/div[1]/div[2]/span[1]/span"));
		int  allLInks = links.size();
		String beforePath = "//div[@class='activities-stream']/div[1]/div[1]/div[2]/span[1]/span[";
		String afterPath = "]";
		for(int i=1;i<=allLInks;i++){
			
			WebElement e = driver().findElement(By.xpath(beforePath+i+afterPath));
			String expectedLinkName = e.getText().trim();
			System.out.println("Available links: "+ expectedLinkName);
			if(expectedLinkName.equalsIgnoreCase(linkStatus)){
				e.click();
				Thread.sleep(5000);
				logInfo("clicked on" + linkStatus +"button");
				System.out.println("clicked on" + linkStatus +"button");
				break;
			}
			 Assert.assertEquals(expectedLinkName, linkStatus);
		}
		
	}
	
	
	public boolean verifyPostActivityLinks( String posName, String linkStatus) throws Exception, Exception{
		logInfo("inside verifyPostActivityLinks() method.");
		
		
		
		waitOnElement("xpath",paneCommunityWall);
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		waitOnElement("xpath",paneCommunityWallStatus);
		
		

		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";
		String allsStatus = "]/div[1]/div[2]/span[1]/span";
		String statusBfr = "]/div[1]/div[2]/span[1]/span[";
		String statusAft = "]";		   
		     boolean isLinkPresents = false;
		     
		      List<WebElement> allStatus = driver().findElements(By.xpath("//div[@class='activities-stream']/div/div/div[1]"));
				for(int i=1;i<=allStatus.size();i++){
					WebElement post = driver().findElement(By.xpath(before_name+i+after_name));
					 if (post.getText().equalsIgnoreCase(posName)){						 
						 /*isPostPresent =true;*/
						 List <WebElement > status = driver().findElements(By.xpath(before_name+i+allsStatus));						 
							int   allLInks= status.size();							
							
							for(int j=1;j<=allLInks;j++){
								WebElement st = driver().findElement(By.xpath(before_name+i+statusBfr+j+statusAft));
								if (st.getText().equalsIgnoreCase(linkStatus)){
									isLinkPresents =true;
									break;								
								}}
							}if (isLinkPresents==false){
							Assert.assertTrue(isLinkPresents,linkStatus+ "is not present" );
							}				 
						 
						 
						 
				}
				return isLinkPresents;			 
						 
					 
		
		

	}
	
	
	
	
	public void hideStatusPost(String statusMessage) throws Exception {
		logInfo("inside hideStatusPost() method.");
		navigate2CommunityPage();
		
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allStatus = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allStatus.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";
		
		String before_hide = "//div[@class='activities-stream']/div[";
		String after_hide = "]/div/div[2]/span[1]/span[3]";   // /a
	
		for(int i=1;i<=allStatus.size();i++){
		WebElement e = driver().findElement(By.xpath(before_name+i+after_name));
		String status = e.getText().trim();
			if(status.matches(statusMessage)){
				isMatchFound = true;
				WebElement hide = driver().findElement(By.xpath(before_hide+i+after_hide));
				hide.click();
				logInfo("clicked on hide button of " +status);
				break;
			}
		}
		
	   if(isMatchFound==false){
			logInfo(statusMessage + " status post match not found in community wall.");
			Assert.assertTrue(isMatchFound, statusMessage + " status post match not found in community wall.");
		}
	}
	
	
	public void featurCommunityPost(String postName) throws Exception {
		logInfo("inside featurCommunityPost() method.");
	//	System.out.println("inside featurCommunityPost() method.");
		navigate2CommunityPage();
		waitOnElement("xpath",paneCommunityWall);
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPhotos = panel.findElements(By.xpath(paneCommunityWallContent));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allPhotos.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";
		
		String before_feature = "//div[@class='activities-stream']/div[";
		String after_feature = "]/div/div[2]/span[1]/span[4]/a";
				
		System.out.println("Total photos ="+ allPhotos.size());
		for(int i=1;i<=allPhotos.size();i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			if(name.contains(postName)){
				logInfo(postName + " community post match found.");
				isMatchFound = true;
				WebElement e = driver().findElement(By.xpath(before_feature+i+after_feature));
				e.click();
				logInfo("clicked on the link " + e.getText().trim());
				navigate2CommunityPage();
				break;
			}
		}
		
		 if(isMatchFound==false){
			logInfo(postName + " post match not found in community wall.");
			Assert.assertTrue(isMatchFound, postName + " post match not found in community wall.");
		}
	}

	
	
	public void verifyFeaturedPhotos(String postName) throws Exception {
		logInfo("inside verifyFeaturedPhotos() method.");
		//	System.out.println("inside featurCommunityPost() method.");
			navigate2CommunityPage();
			driver().navigate().refresh();
			waitOnElement("xpath", paneFeaturedPhotos);
			
			WebElement featuredPhotoPane = driver().findElement(By.xpath(paneFeaturedPhotos));
			List allImages = featuredPhotoPane.findElements(By.tagName("div"));
			int totalImages = allImages.size();
			logInfo("Total images in Featured Photos panel = " +totalImages);
			boolean isMatchFound = false;
			String before_img = "//*[@data-widget-path='widgets/featured_photos']/div/div/div[2]/div[";
			String after_img = "]/div/div";
			if(totalImages >0){
				for(int i=1;i<=6;i++){
					WebElement e = driver().findElement(By.xpath(before_img+i+after_img));
					String imgName = e.getAttribute("title").trim();
				//	System.out.println("Image name = " +imgName);
					if(imgName.contains(postName)){
						isMatchFound = true;
						System.out.println("Image match found in featured photos = " +imgName);
						e.click();
						break;
					}
				}
			}
		
			 if(isMatchFound==false){
					logInfo(postName + " image match not found in featured photos.");
					Assert.assertTrue(isMatchFound, postName + " image match not found in featured photos.");
				}
		}
	
	
	public void verifyFeaturedVideos_old(String postName) throws Exception {
		logInfo("inside verifyFeaturedVideos() method.");
		//	System.out.println("inside featurCommunityPost() method.");
			navigate2CommunityPage();
			driver().navigate().refresh();			
			
			/*WebElement featuredPhotoPane = driver().findElement(By.xpath(paneFeaturedVideos));
			List allVideos = featuredPhotoPane.findElements(By.tagName("div"));*/
			List <WebElement> allVideos = driver().findElements(By.xpath(paneFeaturedVideos));
			int totalVideos = allVideos.size();
			logInfo("Total images in Featured Photos panel = " +totalVideos);
			System.out.println("Total vedios in Featured Photos panel = " +totalVideos);
			boolean isMatchFound = false;
			String before_video ="//*[@class='pyr_community_activity media']/div/div/div[2]/div["  ;        //"//*[@data-widget-path='widgets/featured_videos']/div/div/div[2]/div[";
			String after_video = "]/div/div";
			
			List<WebElement> allMessages= driver().findElements(By.cssSelector(commList));
			if(totalVideos >0){
				/*for(int i=1;i<=totalVideos;i++){
					WebElement e = driver().findElement(By.xpath(postedList));
					//String videoName = e.getAttribute("title").trim();
					String videoName = e.getText().trim();*/
				for (WebElement all : allMessages){
					String videoName = all.getText().trim();
					System.out.println("Image name = " +videoName);
					if(videoName.contains(postName)){
						isMatchFound = true;
						System.out.println("Video match found in featured videos = " +videoName);
						all.click();
						break;
					}
				}
			}
		
			 if(isMatchFound==false){
					logInfo(postName + " video match not found in featured videos.");
					Assert.assertTrue(isMatchFound, postName + " video match not found in featured videos.");
				}
		}
	
	
	
	public void verifyFeaturedVideos(String postName) throws Exception {
		logInfo("inside verifyFeaturedVideos() method.");		
			navigate2CommunityPage();
			driver().navigate().refresh();		
			List <WebElement> allVideos = driver().findElements(By.xpath(paneFeaturedVideos));
			int totalVideos = allVideos.size();
			logInfo("Total images in Featured Photos panel = " +totalVideos);			
			boolean isMatchFound = false;			
			List<WebElement> allMessages= driver().findElements(By.cssSelector(commList));
			if(totalVideos >0){				
				for (WebElement all : allMessages){
					String videoName = all.getText().trim();
					System.out.println("Image name = " +videoName);
					if(videoName.contains(postName)){
						isMatchFound = true;
						System.out.println("Video match found in featured videos = " +videoName);						
						break;
					}
				}
			}
		
			 if(isMatchFound==false){
					logInfo(postName + " video match not found in featured videos.");
					Assert.assertTrue(isMatchFound, postName + " video match not found in featured videos.");
				}
		}
	
	
	
	public boolean likeStatusOnCommunity(String postName , String status) throws Exception {
		logInfo("inside likeStatusOnCommunity() method.");
		boolean isLiked = false;
		waitOnElement("xpath",paneCommunityWall);
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allMessages.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";		
			System.out.println("Total messages ="+ allMessages.size());
		for(int i=1;i<=allMessages.size();i++){
			waitOnElement("xpath",before_name+i+after_name);
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			System.out.println(name+ "is the name");
			if(name.equalsIgnoreCase(postName)){
				logInfo(postName + " community post match found.");
				isMatchFound = true;
				postActivityLinks(status);				
				break;
			}
		}
		
		 return isLiked;
	}
	
	public boolean unlikeStatusOnCommunity(String postName) throws Exception {
		logInfo("inside likeStatusOnCommunity() method.");
		boolean isUnlike = false;
		
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allMessages.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]/a";
		
		String before_unlike = "//div[@class='activities-stream']/div[";
		String after_unlike = "]/div/div[2]/span[1]/span[1]/a";
				
		System.out.println("Total messages ="+ allMessages.size());
		for(int i=1;i<=allMessages.size();i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			if(name.equalsIgnoreCase(postName)){
				logInfo(postName + " community post match found.");
				isMatchFound = true;
				WebElement e = driver().findElement(By.xpath(before_unlike+i+after_unlike));
				e.click();
				logInfo("clicked on the link " + e.getText().trim());
//				Thread.sleep(2000);
				if(e.getText().equalsIgnoreCase("Like"))
					isUnlike = true;
				navigate2CommunityPage();
				break;
			}
		}
		
		 return isUnlike;
	}

	
	public void commentStatusOnCommunity(String postName, String status) throws Exception {
		logInfo("inside commentStatusOnCommunity() method.");
		navigate2CommunityPage();
		waitOnElement("xpath","paneCommunityWall");
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allMessages.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";
		
		String before_comment = "//div[@class='activities-stream']/div[";
		String after_comment = "]/div/div[2]/span[1]/span[2]";  // /a
				
		System.out.println("Total messages ="+ allMessages.size());
		for(int i=1;i<=allMessages.size();i++){
			waitOnElement("xpath",before_name+i+after_name);
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			if(name.contains(postName)){
				logInfo(postName + " community status post match found.");
				isMatchFound = true;
				WebElement e = driver().findElement(By.xpath(before_comment+i+after_comment));
				e.click();
				logInfo("clicked on the link " + e.getText().trim());
				inputTextClear("cssSelector",inputCommunityStatusComment);
				inputText("cssSelector",inputCommunityStatusComment,communityStatusComment_text);
				clickOnButton("cssSelector",btnCommunityStatusComment);
				navigate2CommunityPage();
				break;
			}
		}
		
		 if(isMatchFound==false){
			logInfo(postName + " status post match not found in community wall.");
			Assert.assertTrue(isMatchFound, postName + " status post match not found in community wall.");
		}
	}
	
	
	public void reportStatusOnCommunity(String postName) throws Exception {
		logInfo("inside reportStatusOnCommunity() method.");
	//	System.out.println("inside featurCommunityPost() method.");
		navigate2CommunityPage();
		
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		logInfo("Total posts =" +allMessages.size());
		
		String before_name = "//div[@class='activities-stream row']/div[";
		String after_name = "]/div/div[1]";
		
		String before_like = "//div[@class='activities-stream row']/div[";
		String after_like = "]/div/div[2]/span[1]/span[1]/a";
				
		System.out.println("Total messages ="+ allMessages.size());
		for(int i=1;i<=allMessages.size();i++){
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			if(name.contains(postName)){
				logInfo(postName + " community post match found.");
				isMatchFound = true;
				WebElement e = driver().findElement(By.xpath(before_like+i+after_like));
				e.click();
				logInfo("clicked on the link " + e.getText().trim());
				navigate2CommunityPage();
				break;
			}
		}
		
		 if(isMatchFound==false){
			logInfo(postName + " post match not found in community wall.");
			Assert.assertTrue(isMatchFound, postName + " post match not found in community wall.");
		}
	}
	
	public void navigate2MyProfilePage(){
		logInfo("inside navigate2MyProfilePage() method..");
		driver().navigate().to(appUrl + "community/profile");
	}
	
	public void addBlogPost(String postName, String privacy, Boolean published) throws Exception{
		logInfo("inside addBlogPost() method..");
		clickOnLink("linkText", "Add Blog Post");
		waitOnElement("cssSelector",inputAddPostTitle);
		inputText("cssSelector",inputAddPostTitle,postName); 
		composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new blog");
//		composeTextOnElement();
		inputText("cssSelector",inputAddPostExcerpt,addPostExcerpt_text);
		selectFromDropdown("cssSelector",drpdnAddPostVisibility,"byVisibleText",privacy);
		if(published)
			selectRadioOrCheckbox("cssSelector", chkPublishPost);
		clickOnButton("cssSelector",btnCreatePost);
		Thread.sleep(3000);
		clickOnButton("cssSelector",okFirstTime);
		logInfo("clicked on create post button.");
	}
	
	
	// Verify blog post in MyProfile page
	
	public boolean verifyPostInMyProfilePage(String postName) throws Exception {
		logInfo("inside verifyPostInMyProfilePage() method.");
		navigate2MyProfilePage();
		
		boolean isMatchFound = false;
		WebElement myRecentActivity = driver().findElement(By.cssSelector(panelMyRecentActivity));
		List allRows = myRecentActivity.findElements(By.tagName("div"));
	//	int total_rows = allRows.size();
		System.out.println("Post Name = " +postName);
		String item_beforeleft = "//div[@id='profile-timeline']/div[2]/div[";
		String item_afterleft = "]/div[1]/div[1]/div[1]";
		
		for(int i=1;i<=6;i=i+2){
			System.out.println("left i =" +i);
			WebElement left = driver().findElement(By.xpath(item_beforeleft+i+item_afterleft));
			String name = left.getText().trim();
			System.out.println("Name = " +name);
			if(postName.trim().equalsIgnoreCase(name)){
				isMatchFound = true;
				logInfo(postName + " match found in Recent Activity pane in Myprofile page.");
				break;
			}
		}
		
		String item_beforeright = "//div[@id='profile-timeline']/div[2]/div[";
		String item_afterright = "]/div[2]/div[1]/div[2]/div[1]/div[1]";
		
		for(int i=2;i<=6;i=i+2){
			System.out.println("right i =" +i);
			WebElement right = driver().findElement(By.xpath(item_beforeright+i+item_afterright));
			String name = right.getText().trim();
			System.out.println("Name = " +name);
			if(postName.trim().equalsIgnoreCase(name)){
				isMatchFound = true;
				logInfo(postName + " match found in Recent Activity pane in Myprofile page1.");
				break;
			}
		}
		
		if(isMatchFound==false){
			logInfo(postName + " post "
					+ " not found in Recent Activity pane in Myprofile page.");
		//	Assert.assertFalse(isMatchFound, postName + " post match not found in Recent Activity pane in Myprofile page.");
		}
		return isMatchFound;
	}
	

	
	public void addCommunityPhoto(String photoTitle, String privacy) throws Exception {
		logInfo("inside addCommunityPhoto() method ...");
		navigate2CommunityPage();
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");
//		Thread.sleep(5000);
		waitOnElement("xpath",inputPhotoURL);
		inputText("xpath",inputPhotoURL,photoURL_text);
		inputText("xpath",inputPhotoTitle,photoTitle);
		inputText("xpath",inputPhotoDesc,photoDesc_text);
		inputText("xpath",inputPhotoTags,photoTags_text);
		selectFromDropdown("xpath",drpdnVisibilitySettings,"byVisibleText",privacy);
		clickOnButton("xpath",btnSharePhoto);
		logInfo("clicked on share button of Community - AddPhoto page.");
		}

	
	public void addCommunityVideo(String videoTitle, String privacy)  throws Exception {
		logInfo("inside addCommunityVideo() method ...");
		navigate2CommunityPage();
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");
	
		verifyLinkPresent("Add New Video");
		clickOnLink("linkText", "Add New Video");
		
		verifyElementPresent("xpath",inputVideoURL);
		inputText("xpath",inputVideoURL,videoURL_text);
		inputText("xpath",inputVideoTitle,videoTitle);
		inputText("xpath",inputVideoDesc,videoDesc_text);
		inputText("xpath",inputVideoTags,videoTags_text);
		selectFromDropdown("cssSelector",drpdnVisibilityVideoSettings,"byVisibleText",privacy);
		clickOnButton("xpath",btnShareVideo);
		logInfo("clicked on share button of Community - AddVideo page.");
		
//		Thread.sleep(10000);
	}
	
	public void sendMailWithoutRecipient() throws Exception, Exception{
    	 	
    	logInfo("Enter recipient mailId & compose and send a mail");
//    	Thread.sleep(5000);     	
    	inputTextClear("cssSelector", mailSub);
    	inputText("cssSelector", mailSub, subText); 
    	clickOnButton("cssSelector", send_Mail);
//    	Thread.sleep(3000);
    	String alrtMsg = driver().findElement(By.cssSelector(alertMsg)).getText();  
    	System.out.println(alrtMsg);
    	try{    	
    		if(!alrtMsg.contains(alertMsgText)){
    			Assert.assertFalse(true, "Unable to validate the alert message when sending an email without recipient.");
    		}
    	
    	}catch (Exception e ){
    		logger.error("Failed!! Alert message is not displayed as per expected." );
    	}
    	clickOnButton("cssSelector", alrtOk); 
//    	Thread.sleep(3000);
    	  	
    }
	
	public void sendMailWithInvalidRecipient(String invalidMail) throws Exception, Exception{
    	 	
    	logInfo("Enter recipient Invalid mailId & compose and send a mail");
    	
//    	Thread.sleep(5000);    	

    	waitOnElement("cssSelector",recipientsTo);
    	inputText("cssSelector", recipientsTo, invalidMail);    	
//    	Thread.sleep(5000);    
    	inputTextClear("cssSelector", mailSub);
    	inputText("cssSelector", mailSub, subText); 
    	clickOnButton("cssSelector", send_Mail);
//    	Thread.sleep(2000);
    	waitOnElement("cssSelector",alertMsg);
    	String alrtMsg = driver().findElement(By.cssSelector(alertMsg)).getText();  
    	System.out.println(alrtMsg);
    	try{
    	Assert.assertEquals(alrtMsg, alertInvalidMsgText + invalidMail);
    	}catch (Exception e ){
    		logger.error("Failed!! Alert message is not displayed as per expected." );
    	}
    	clickOnButton("cssSelector", alrtOk);  
    	
    	  	
    }
	
	public void PhotoWithoutImage(String photoTitle) throws Exception {
		
		navigate2CommunityPage();
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");
//		Thread.sleep(5000);
		verifyElementPresent("xpath",inputPhotoURL);
		/*inputText("xpath",inputPhotoURL,photoURL_text);*/
		inputText("xpath",inputPhotoTitle,photoTitle);
		System.out.println("title is "+ photoTitle);
		inputText("xpath",inputPhotoDesc,photoDesc_text);
		inputText("xpath",inputPhotoTags,photoTags_text);
		selectFromDropdown("xpath",drpdnVisibilitySettings,"byVisibleText",visibilitySettings_text);
		clickOnButton("xpath",btnSharePhoto);		
		WebElement alertMsgs = driver().findElement(By.cssSelector(imgAlert));
		System.out.println(alertMsgs.getText());
		String actualMsg = alertMsgs.getText();
		Assert.assertEquals(actualMsg, imgAlertText);
		closePhotoModalWindow();

	}

	public void PhotoWithoutTitle() throws Exception {
		
		navigate2CommunityPage();
		verifyLinkPresent("Add Photo / Video");
		clickOnLink("linkText", "Add Photo / Video");
//		Thread.sleep(5000);
		verifyElementPresent("xpath",inputPhotoURL);
		inputText("xpath",inputPhotoURL,photoURL_text);
		inputTextClear("xpath",inputPhotoTitle);		
		inputText("xpath",inputPhotoDesc,photoDesc_text);
		inputText("xpath",inputPhotoTags,photoTags_text);
		selectFromDropdown("xpath",drpdnVisibilitySettings,"byVisibleText",visibilitySettings_text);
		clickOnButton("xpath",btnSharePhoto);		
		WebElement alertMsgs = driver().findElement(By.cssSelector(imgAlert));
		System.out.println(alertMsgs.getText());
		String actualMsg = alertMsgs.getText();
		Assert.assertEquals(actualMsg, imgAlertText);
		closePhotoModalWindow();
		
		
		}
	
	 public void closePhotoModalWindow() throws Exception{
		 
//		 Thread.sleep(2000);
		 clickOnElement("xpath", closePhoto);
		 
	 }
	 
	 
	 public void reportThePosted(String postName) throws Exception {
	    	 	
		    logInfo("Verify appropriate posted and select Report Link.");	   
		    navigate2CommunityPage();
		    for (int i=1; i<=1;i++){
				 clickOnLink("cssSelector",vieMor );
//				 Thread.sleep(2000);
				 }
		    
		    String frames =  "//div/div/div[2]/div/div/div/div[1]";

		    waitOnElement("xpath",paneCommunityWall);
			WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
			List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		    /*List<WebElement> allMessages = driver().findElements(By.xpath(frames));*/
			System.out.println("Size is "+ allMessages.size() );
			/*boolean isMatchFound = false;*/
			logInfo("Total posts =" +allMessages.size());		
			String after_name = "]/div/div[1]";		
			String after_Report  = "]/div/div[2]/span[1]/span[4]/a";	
			String after_Report2 = "]/div/div[2]/span[1]/span[5]/a";	
			for(int i=1;i<=allMessages.size();i++){
				WebElement x = driver().findElement(By.xpath(activitiesBefore+i+after_name));
				String name = x.getText().trim();
				System.out.println(i + " " + name);
				if(name.contains(postName)){	
					System.out.println(name + "Match*******************************");	
//					Thread.sleep(5000);

					waitOnElement("xpath",activitiesBefore+i+after_Report);
					WebElement selectReport = driver().findElement(By.xpath(activitiesBefore+i+after_Report));
					WebElement Report2 = driver().findElement(By.xpath(activitiesBefore+i+after_Report2));
					System.out.println(selectReport.getText() + "report text");
					/*isMatchFound = true;*/
					
					if(selectReport.getText().equals("Report")){
						
						selectReport.click();
						break;
						
					}else{					
						System.out.println(Report2.getText() + "  report2 text");
						Report2.click();
						break;
						
					}
					
					/*selectReportLink();*/
					
					/*WebElement selectReport = driver().findElement(By.xpath(activitiesBefore+i+after_like));
					System.out.println(selectReport.getText());
					if(selectReport.getText().equals("Report")){
						isMatchFound = true;
						Thread.sleep(4000);
						selectReport.click();							
						break;
						
					}*/
					
				}
			}
			
			 /*if(isMatchFound==false){
				logInfo(postName + " posted match not found in community wall.");
				Assert.assertTrue(isMatchFound, postName + " posted match not found in community wall.");
			}*/
		}
	 
	 
	 public void createPhoto(String photoTitle, String visibilitySettingOption ) throws Exception {
		  			
	    	logInfo("Create a new Post by Photo through community");
			navigate2CommunityPage();
			verifyLinkPresent("Add Photo / Video");
			clickOnLink("linkText", "Add Photo / Video");
//			Thread.sleep(5000);
			waitOnElement("xpath",inputPhotoURL);
			inputText("xpath",inputPhotoURL,photoURL_text);
			inputText("xpath",inputPhotoTitle,photoTitle);		
			inputText("xpath",inputPhotoDesc,photoDesc_text);
			inputText("xpath",inputPhotoTags,photoTags_text);
			selectFromDropdown("xpath",drpdnVisibilitySettings,"byVisibleText",visibilitySettingOption);
			clickOnButton("xpath",btnSharePhoto);			
			}
	
	 public void addVideoNoUrl(String videoTitle) throws Exception {			
			verifyLinkPresent("Add Photo / Video");
			clickOnLink("linkText", "Add Photo / Video");		
			verifyLinkPresent("Add Video");
			clickOnLink("linkText", "Add Video");			
			verifyElementPresent("xpath",inputVideoURL);			
			inputText("xpath",inputVideoTitle,videoTitle);
			inputText("xpath",inputVideoDesc,videoDesc_text);
			inputText("xpath",inputVideoTags,videoTags_text);
			selectFromDropdown("cssSelector",drpdnVisibilityVideoSettings,"byVisibleText",drpdnVisibilityVideoSettings_text);
			clickOnButton("xpath",btnShareVideo);
			assertAlertMessage(imgAlertText );
			
			
			}
	 
	 public void assertAlertMessage(String alertMsg) throws Exception{
			WebElement alertMsgs = driver().findElement(By.cssSelector(imgAlert));
			System.out.println(alertMsgs.getText());
			String actualMsg = alertMsgs.getText();
			Assert.assertEquals(actualMsg, alertMsg);
			closePhotoModalWindow();
	 }			

	 public void invalidVideoUrl() throws Exception {				
			verifyLinkPresent("Add Photo / Video");
			clickOnLink("linkText", "Add Photo / Video");		
			verifyLinkPresent("Add Video");
			clickOnLink("linkText", "Add Video");			
			verifyElementPresent("xpath",inputVideoURL);
			inputText("xpath",inputVideoURL,invalidUrl);
			clickOnButton("xpath",btnShareVideo);
//			Thread.sleep(6000);
			waitOnElement("cssSelector",invalidUrlAlert);
			WebElement alertVideoMsgs = driver().findElement(By.cssSelector(invalidUrlAlert));			
			String actualMsg = alertVideoMsgs.getText();			
			Assert.assertEquals(actualMsg, invalidUrlTxt);
			closePhotoModalWindow();
	 }
	 
	 
	 public void isActivityPresentInCommunity(String photoTitle) throws Exception {
	    	 			
		     logInfo("Verify the activity like photo, video, blog presence in community page");	
			navigate2CommunityPage();	
			 for (int i=0; i<=2;i++){
				 clickOnLink("cssSelector",vieMor );
//				 Thread.sleep(2000);
				 }
			boolean isPhotoMatchs = false;		
			List<WebElement> allMessages= driver().findElements(By.cssSelector("div > div.activity-content > a"));
			System.out.println("Total messages ="+ allMessages.size());		
			for (WebElement al :allMessages ){			
				System.out.println(al.getText());			
				if(al.getText().contains(photoTitle)){				
					System.out.println(al.getText() + " Posted community photo  is matched.");
					isPhotoMatchs = true;
					break;
			}}if(isPhotoMatchs==false){
				logInfo(photoTitle + " posted photo is not found in community.");
				Assert.assertTrue(isPhotoMatchs, photoTitle + " posted photo is not found in community.");
			}		
		}
	    
	  public void isActivityNotToPresentInCommunity(String photoTitle) throws Exception {
	    	 			
		    logInfo("Verify the activity like photo, video, blog presence in community page");	
			navigate2CommunityPage();	
			 for (int i=0; i<=2;i++){
				 clickOnLink("cssSelector",vieMor );
//				 Thread.sleep(2000);
				 }
			boolean isPhotoMatchs = false;		
			List<WebElement> allMessages= driver().findElements(By.cssSelector(commList));			
			for (WebElement al :allMessages ){					
				if(al.getText().contains(photoTitle)){					
					System.err.println(al.getText() + " Posted community activity is matched.");
					isPhotoMatchs = true;
					Assert.assertTrue(isPhotoMatchs, photoTitle +  " Posted community activity is matched.");
					break;
			}}if(isPhotoMatchs==false){
				System.out.println(photoTitle + " posted activity is not found in community.");
				logInfo(photoTitle + " posted activity is not found in community.");			
			}		
	 }
	    
	    
    public boolean isActivitypresentInMyRecentActivities(String activityName) throws Exception{
		  			
	     logInfo("Verify the activity like photo, video, blog presence in my Recent activity(MyProfile)");
		 navigate2MyProfilePage();
		 for (int i=0; i<=2;i++){
		 clickOnLink("cssSelector",vieMor );
//		 Thread.sleep(2000);
		 			}
		 boolean isactivityPresent = false;
		 List <WebElement> act = driver().findElements(By.cssSelector(actList));
		 System.out.println(act.size());		 
		 for (WebElement acts : act){			 
			 if (acts.getText().contains(activityName)){
				 System.out.println(acts.getText());
				 isactivityPresent= true;				 
				 break;
			 }}if (isactivityPresent == false){			 
			 Assert.assertTrue(isactivityPresent, activityName +" is mismatched in My Recent Activities List." );
		 } 		 
		return isactivityPresent;	 
	 }
		 
    public void closeSocialList() throws Exception{
//    	Thread.sleep(2000);
    	waitOnElement("cssSelector", socSiteClose);
    	clickOnElement("cssSelector", socSiteClose);
    	
    }
    
    public void verifyInPWPList(String postName) throws Exception{
    	mw.verifyWebsiteIsPresent(postName);
    }
	
    public boolean validatePostWithoutTitle() throws Exception{
		boolean isValidateTitle = false;
		logInfo("inside verifyPostWithoutTitle() method..");
		clickOnLink("linkText", addBlogPost);
		waitOnElement("cssSelector",inputAddPostTitle);
		inputTextClear("cssSelector",inputAddPostTitle); 
//		composeTextOnElement();
		composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new blog");
		inputText("cssSelector",inputAddPostExcerpt,addPostExcerpt_text);
		selectFromDropdown("cssSelector",drpdnAddPostVisibility,"byVisibleText",visibility_text_community);
		selectRadioOrCheckbox("cssSelector", chkPublishPost);
		clickOnButton("cssSelector",btnCreatePost);
		logInfo("clicked on create post button without giving title.");
		String titleToValidate = driver().findElement(By.xpath(validateElement)).getText();
		System.out.println("The error msg is :"+titleToValidate);
		if(titleToValidate.equalsIgnoreCase(validateText))
			return isValidateTitle = true;
		else
			return isValidateTitle = false;

	}
	
	public boolean validatePostWithoutBody(String postName) throws Exception{
		logInfo("inside verifyPostWithoutTitle() method..");
		clickOnLink("linkText", addBlogPost);
		waitOnElement("cssSelector",inputAddPostTitle);
		inputText("cssSelector",inputAddPostTitle,postName); 
		inputText("cssSelector",inputAddPostExcerpt,addPostExcerpt_text);
		selectFromDropdown("cssSelector",drpdnAddPostVisibility,"byVisibleText",visibility_text_community);
		selectRadioOrCheckbox("cssSelector", chkPublishPost);
		clickOnButton("cssSelector",btnCreatePost);
		logInfo("clicked on create post button without giving title.");
		String bodyToValidate = driver().findElement(By.xpath(validateElement)).getText();
		System.out.println("The error msg is : "+bodyToValidate);
		if(bodyToValidate.equalsIgnoreCase(validateText))
			return true;
		else
			return false;

	}
	
	public boolean verifyVisibilitySettingToCommunity() throws Exception{
		logInfo("inside verifyPostWithoutTitle() method..");
		clickOnLink("linkText", addBlogPost);
		Select visibility=new Select(driver().findElement(By.xpath(ddlVisibility)));
		List<WebElement> oSize=visibility.getOptions();
		System.out.println(visibility.getOptions().get(0).getText());
		if(visibility.getOptions().get(0).getText().equalsIgnoreCase("COMMUNITY"))
			return true;
		else
			return false;

	}
	
	public boolean verifyDraftPost(String postName) throws Exception{
		logInfo("inside verifyDraftPost() method..");
		clickOnLink("linkText", lnkBlog);
		boolean isPostPresent = false;
		int draftsCount = driver().findElements(By.xpath(draftsTable)).size();
		String before = "//*[@id='user-activity-tabs']/div/div[1]/div[1]/div[2]/article[";
		String after = "]/div[1]/h3/a";
		for(int i=1; i <= draftsCount; i++ ){
			WebElement post = driver().findElement(By.xpath(before+i+after));
			String draftPost = post.getText().trim();
			if(draftPost.equalsIgnoreCase(postName.trim())){
				isPostPresent = true;
				break;
			}
			else{
				isPostPresent = false;
			}
		}
		System.out.println("Post "+postName+" is located in drafts.");
		logInfo("Post "+postName+" is located in drafts.");
		return isPostPresent;
		
	}
	
	public boolean updateDraftPost(String postName,String postName1) throws Exception{
		logInfo("inside updateDraftPost() method..");
		clickOnLink("linkText", lnkBlog);
		boolean isPostPresent = false;
		int draftsCount = driver().findElements(By.xpath(draftsTable)).size();
		
		String before = "//*[@id='user-activity-tabs']/div/div[1]/div[1]/div[2]/article[";
		String after = "]/div[1]/h3/a";
		
		String editDraftBefore = "//*[@id='user-activity-tabs']/div/div[1]/div[1]/div[2]/article[";
		String editDraftAfter = "]/div[2]/div[@class='actions']/a[1]";
		
		for(int i=1; i <= draftsCount; i++ ){
			WebElement post = driver().findElement(By.xpath(before+i+after));
			String draftPost = post.getText().trim();
			if(draftPost.equalsIgnoreCase(postName.trim())){
				isPostPresent = true;
				driver().findElement(By.xpath(editDraftBefore+i+editDraftAfter)).click();
				inputTextClear("cssSelector", inputAddPostTitle);
				inputText("cssSelector",inputAddPostTitle,postName1); 
				clickOnButton("xpath", btnUpdateDraft);
				break;
			}
		}
		System.out.println("Post "+postName+" has been updated successfully with "+postName1);
		logInfo("Post "+postName+" has been updated successfully with "+postName1);
		return isPostPresent;
	}
	
	public boolean verifyBlogInCommunityWall_Old(String postName) throws Exception{
		logInfo("inside verifyBlogInCommunityWall() method.");
		boolean isPostPresent = false;
//		Thread.sleep(5000);
		String before = "//*[contains(@id,'pyr_community_activity')][";
		String after = "]/div[contains(@id,'activity_post')]/div[1]/a";
		waitOnElement("xpath","//div[contains(@id, 'activity_post')]");
		int communityStreamCount = driver().findElements(By.xpath("//div[contains(@id, 'activity_post')]")).size();
		for(int i=1; i<=communityStreamCount;i++){
			WebElement post = driver().findElement(By.xpath(before+i+after));
			String blogPost = post.getText().trim();
			System.out.println("blogs are "+ blogPost);
			if(blogPost.equalsIgnoreCase(postName.trim())){
				isPostPresent = true;
				break;
			}
			else{
				isPostPresent = false;
			}
		}
		logInfo(postName + " is verified in the community wall.");
		return isPostPresent;
	}
	
	
   public boolean verifyBlogInCommunityWall(String postName) throws Exception {
	 	
	    logInfo("Verify appropriate Blog");	   
	    Thread.sleep(5000);
	    waitOnElement("xpath",paneCommunityWall);
	    WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		 boolean isVerified = false;	
		logInfo("Total posts =" +allMessages.size());		
		String after_name = "]/div/div[1]";				
		for(int i=1;i<=allMessages.size();i++){
			WebElement x = driver().findElement(By.xpath(activitiesBefore+i+after_name));
			String name = x.getText().trim();	
			System.out.println("blogs" + name);
			if(name.contains(postName)){				
				isVerified = true;							
				break;
			}
		}
		
		 if(isVerified==false){
			logInfo(postName + " post match not found in community wall.");
			Assert.assertTrue(isVerified, postName + " post match not found in community wall.");
		} 
		 return isVerified;
	}
	
	public boolean verifyWidgetInCommunityWall(String widgetName) throws Exception{
		 logInfo("inside verifyWidgetInCommunityWall() method..");
		 System.out.println("inside verifyWidgetInCommunityWall() method.");
		 waitOnElement("xpath",communityPageWidgetContainer);
		 WebElement widgetContainer = driver().findElement(By.xpath(communityPageWidgetContainer));
		 List<WebElement> allDivs = widgetContainer.findElements(By.tagName("div"));
		 int div_count = allDivs.size();
		 System.out.println("Total divs in Widget container = " +div_count);
		 boolean isWidgetFound=false;
		 boolean isSearchNeeded = true;
		 
		 // Verifying the 1st container
		 
		 if(div_count>2){
			 System.out.println("inside 1st container");
			 WebElement container1 = driver().findElement(By.xpath(communityPageDiv1Container));
			 List allContainer1Divs = container1.findElements(By.className("panel-body"));
				
			 int total_div1s = allContainer1Divs.size();
			 System.out.println("Total divs in Div1 Widget container = " +total_div1s);
			 
			 String before = "//*[@id='widget_manager_community']/div[2]/div/div/div[1]/div/div[";
			 String after = "]/div[1]";
			 if(total_div1s >= 1){
				 for(int i=1;i<=total_div1s;i++){
					 WebElement x = driver().findElement(By.xpath(before+i+after));
					 String headerName = x.getText().trim();
					 System.out.println("Header Name = " + headerName);
				 
					 if(headerName.equalsIgnoreCase(widgetName)){
						 System.out.println("Widget match found : " +widgetName);
						 isWidgetFound=true;
						 isSearchNeeded = false;
						 break;
					 }
				 } 
			  }
			 
		   // Verifying the 2nd container
			if(isSearchNeeded==true){
			 System.out.println("inside 2nd container");
			 WebElement container2 = driver().findElement(By.xpath(communityPageDiv2Container));
			 List allContainer2Divs = container2.findElements(By.className("widget-content"));   // widget-controls
			 int total_div2s = allContainer2Divs.size();
			 System.out.println("Total divs in Div2 Widget container = " +total_div2s);
			 
			 if(total_div2s >= 1){
				 String div2_before = "//*[@id='widget_manager_community']/div[2]/div/div/div[2]/div[";
				 String div2_after = "]/div[1]";
				 
				 for(int j=1;j<=total_div2s;j++){
					 WebElement x = driver().findElement(By.xpath(div2_before+j+div2_after));
					 String headerName = x.getText().trim();
					 System.out.println("Header Name = " + headerName);
					 if(headerName.equalsIgnoreCase(widgetName)){
						 System.out.println("Widget match found : " +widgetName);
						 isWidgetFound=true;
						 break;
					 }
				 }
			 }
		  }
		}
		return isWidgetFound;
	 }
	
	public void go2EditWidgetsPage() throws Exception{
		logInfo("inside go2EditWidgetsPage() method..");
		System.out.println("inside go2EditWidgetsPage() method.");
		waitOnElement("linkText","Edit Widgets");
		clickOnLink("linkText","Edit Widgets");
	}
	
	public boolean verifyBlogPostEditableInCommunityWall(String postName) throws Exception, Exception{
		logInfo("inside verifyBlogPostEditableInCommunityWall() method.");
		boolean isBlogEditable = false;
		String before = "//*[@id='widget-recent-activity']/div[1]/div[2]/div[@class='pyr_community_activity media'][";
		String after = "]/div[1]/div[@class='activity-content']/a";
		int communityStreamCount = driver().findElements(By.xpath(communityStreamActivities)).size();
		
		for(int i=1; i<=communityStreamCount;i++){
			WebElement post = driver().findElement(By.xpath(before+i+after));
			String blogPost = post.getText().trim();
			if(blogPost.equalsIgnoreCase(postName.trim())){
				post.click();
				break;
			}
			
		}
//		Thread.sleep(3000);
		waitOnElement("xpath",postInCommunityWall);
		String editablePost = driver().findElement(By.xpath(postInCommunityWall)).getText();
		System.out.println("Actual value : "+editablePost);
		System.out.println("Expected value : "+postName);
		if(editablePost.equalsIgnoreCase(postName)){
			isBlogEditable = true;
//			Thread.sleep(2000);
			/*waitOnElement("xpath",btnClosePost);
			driver().findElement(By.xpath(btnClosePost)).click();
			System.out.println("verified "+postName+" is editable on the community wall.");*/
		}
		logInfo("verified "+postName+" is editable on the community wall.");
		return isBlogEditable;
		
	}
	
	public void verifyWidgetPresent(String widgetName) throws Exception {
		logInfo("inside verifyWidgetPresent() method.");
		navigate2CommunityPage();
    	go2EditWidgetsPage();
    	boolean isWidgetPresent = verifyWidgetInCommunityWall(widgetName);
    	if(!isWidgetPresent)
    		wm.dragAndDropWidget(widgetName);
    	logInfo(widgetName +" is located on the community wall.");
	}
	
	public boolean commentsCountForNewBlog(String postName) throws Exception{
		logInfo("inside commentsCountForNewBlog() method.");
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPosts = panel.findElements(By.xpath(paneCommunityWallContent));
		boolean isPostFound = false;
		
		String before = "//div[@class='activities-stream']/div[";
		String after = "]/div/div[1]/a";
		
		String commentsBefore = "//div[@class='activities-stream']/div[";
		String commentsAfter = "]/div/div[@class='community_activity_action']/span[2]/span[1]";
				
		for(int i=1;i<=allPosts.size();i++){
			String blogPostName = driver().findElement(By.xpath(before+i+after)).getText().trim();
			if (blogPostName.equalsIgnoreCase(postName.trim())){ 
				System.out.println(postName + " community post match found.");
				String commentsValue = driver().findElement(By.xpath(commentsBefore+i+commentsAfter)).getText();
				int commentsCount = Integer.parseInt(commentsValue);
				if(commentsCount == 0)
					isPostFound = true;
				System.out.println("comments count for this post "+postName+" is "+commentsCount);
				break; 
				
			}
		}
		return isPostFound;
	}
	
	public boolean verifyCommentsCountForBlog(String postName) throws Exception{
		logInfo("inside verifyCommentsCountForBlog() method.");
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPosts = panel.findElements(By.xpath(paneCommunityWallContent));
		boolean isCommentsFound = false;
		
		String before = "//div[@class='activities-stream']/div[";
		String after = "]/div/div[1]/a";
		
		String commentsBefore = "//div[@class='activities-stream']/div[";
		String commentsAfter = "]/div/div[@class='community_activity_action']/span[2]/span[1]";
		
		String lnkCommentBefore = "//div[@class='activities-stream']/div[";
		String lnkCommentAfter = "]/div/div[@class='community_activity_action']/span[1]/span[2]/a";	
				
		for(int i=1;i<=allPosts.size();i++){
			String blogPostName = driver().findElement(By.xpath(before+i+after)).getText().trim();
			if (blogPostName.equalsIgnoreCase(postName.trim())){ 
				driver().findElement(By.xpath(lnkCommentBefore+i+lnkCommentAfter)).click();
//				Thread.sleep(3000);
				waitOnElement("xpath",txtComment);
				inputText("xpath",txtComment, commentBlogText );
				clickOnElement("cssSelector", btnPost);
				int commentsCount = getCommentsCountForBlog(postName);
				if(commentsCount > 0)
					isCommentsFound = true;
				break; 
				
			}
		}
		return isCommentsFound;
	}
	
	public int getCommentsCountForBlog(String postName) throws Exception{
		logInfo("inside getCommentsCountForBlog() method.");
		int commentsCount = 0;
		navigate2CommunityPage();
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPosts = panel.findElements(By.xpath(paneCommunityWallContent));
				
		String before = "//div[@class='activities-stream']/div[";
		String after = "]/div/div[1]/a";
		
		String commentsBefore = "//div[@class='activities-stream']/div[";
		String commentsAfter = "]/div/div[@class='community_activity_action']/span[2]/span[1]";
				
		for(int i=1;i<=allPosts.size();i++){
			String blogPostName = driver().findElement(By.xpath(before+i+after)).getText().trim();
			if (blogPostName.equalsIgnoreCase(postName.trim())){ 
				System.out.println(postName + " community post match found.");
				String commentsValue = driver().findElement(By.xpath(commentsBefore+i+commentsAfter)).getText();
				commentsCount = Integer.parseInt(commentsValue);
				System.out.println("comments count for this post "+postName+" is "+commentsCount);
				break; 
				
			}
		}
		return commentsCount;
	}
	
	public boolean verifyUserProfileForBlog(String postName) throws InterruptedException, Exception{
		logInfo("inside verifyUserProfileForBlog() method.");
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPosts = panel.findElements(By.xpath(paneCommunityWallContent));
		boolean isUserProfileFound = false;
		
		String before = "//div[@class='activities-stream']/div[";
		String after = "]/div/div[1]/a";
		
		String userNameBefore = "//div[@class='activities-stream']/div[";
		String userNameAfter = "]/div/h4/a";
		
		for(int i=1;i<=allPosts.size();i++){
			String blogPostName = driver().findElement(By.xpath(before+i+after)).getText().trim();
			if (blogPostName.equalsIgnoreCase(postName.trim())){ 
				WebElement userName = driver().findElement(By.xpath(userNameBefore+i+userNameAfter));
				String uName = userName.getText();
				userName.click();
				implicityWaits(5);
				String profileUser = driver().findElement(By.xpath(profileUserName)).getText();
				if(profileUser.equalsIgnoreCase(uName))
					isUserProfileFound = true;
				break; 
				
			}
		}
		return isUserProfileFound;
	}
	
	public boolean verifyReportLinkForSelfBlog(String postName) throws InterruptedException, Exception{
		logInfo("inside verifyReportLinkForSelfBlog() method.");
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPosts = panel.findElements(By.xpath(paneCommunityWallContent));
		boolean isReportLinkFound = true;
		
		String before = "//div[@class='activities-stream']/div[";
		String after = "]/div/div[1]/a";
		
		for(int i=1;i<=allPosts.size();i++){
			String blogPostName = driver().findElement(By.xpath(before+i+after)).getText().trim();
			if (blogPostName.equalsIgnoreCase(postName.trim())){ 
				if(!verifyLinkPresent("Report"))
					isReportLinkFound = false;
				break;
			}
		}
		return isReportLinkFound;
	}
	
	public boolean verifyReportAbuseForBlog(String postName) throws Exception{
		logInfo("Inside verifyReportAbuseForBlog() method.");
		navigate2CommunityPage();
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPosts = panel.findElements(By.xpath(paneCommunityWallContent));
		boolean isReportAbusedForPost = false;
		
		String before = "//div[@class='activities-stream']/div[";
		String after = "]/div/div[1]/a";
		
		for(int i=1; i<=allPosts.size(); i++){
			String blogPostName = driver().findElement(By.xpath(before+i+after)).getText().trim();
			if (blogPostName.equalsIgnoreCase(postName.trim())){ 
					selectReportLink();
					inputText("cssSelector",reportTextArea,reportText );
					clickOnElement("cssSelector", reportButton);
//					enterReportAndSubmit();
					isReportAbusedForPost = true;
					break;

			}
		}
		return isReportAbusedForPost;
	}
	
	public boolean removeProfileImage() throws Exception {
		logInfo("inside removeImage() method..");
		boolean isRemovedImage = false;
		clickOnLink("linkText", btnRemoveImage);
		//confirmEventDeleteModal();
		confirmOK();
    	waitOnElement("xpath", imageSrc);
    	WebElement element = driver().findElement(By.xpath(imageSrc));
    	String removedImageSrc = element.getAttribute("alt");
    	if(removedImageSrc.contains(txtRemoveImage))
    		isRemovedImage = true;
    	System.out.println(isRemovedImage);
    	return isRemovedImage;
	}
	
	public boolean addProfilePhoto() throws Exception{
		logInfo("inside addProfilePhoto() method..");
		boolean isProfilePhotoAdded = false;
		clickOnLink("linkText", CHANGEPROFILEPHOTO_text);
//		Thread.sleep(3000);
		waitOnElement("cssSelector", btnBrowseProfilePhoto);
/*		clickOnElement("cssSelector", btnBrowseProfilePhoto);
		Thread.sleep(6000);
		Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\ProfileImage.exe");*/
		uploadFile("Image",btnBrowseProfilePhoto);
//    	Thread.sleep(10000);
		waitOnElement("xpath",btnSaveProfilePhoto);
    	driver().findElement(By.xpath(btnSaveProfilePhoto)).click();
//    	Thread.sleep(10000);
    	waitOnElement("xpath",btnProfileSubmit);
    	driver().findElement(By.xpath(btnProfileSubmit)).click();
//    	Thread.sleep(5000);
    	waitOnElement("xpath", imageSrc);
    	WebElement element = driver().findElement(By.xpath(imageSrc));
    	String imageSrc = element.getAttribute("alt");
    	if(!imageSrc.contains(txtRemoveImage))
    		isProfilePhotoAdded = true;
    	return isProfilePhotoAdded;
	}
	
	public boolean removeCoverPhoto() throws Exception {
		logInfo("inside removeCoverPhoto() method..");
		boolean isRemovedImage = false;
		clickOnLink("linkText", btnRemoveCoverPhoto);
	//	confirmEventDeleteModal();
		confirmOK();
    	waitOnElement("xpath", coverPhoto);
    	WebElement element = driver().findElement(By.xpath(coverPhoto));
    	String removedImageSrc = element.getAttribute("style");
    	if(removedImageSrc.contains(coverPhotoSrc))
    		isRemovedImage = true;
    	System.out.println("Removed cover photo : "+isRemovedImage);
    	logInfo("Removed cover photo : "+isRemovedImage);
    	return isRemovedImage;
	}
	
	public boolean addCoverPhoto() throws Exception{
		logInfo("inside addCoverPhoto() method..");
		boolean isCoverPhotoAdded = false;
		clickOnLink("linkText", CHANGECOVERPHOTO_text);
		/*implicityWaits(3);
		clickOnElement("cssSelector", btnBrowseCoverPhoto);
    	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Image.exe");
    	implicityWaits(5);*/
		uploadFile("Image",btnBrowseCoverPhoto);
    	driver().findElement(By.xpath(btnSaveCoverPhoto)).click();
    	implicityWaits(10);
    	driver().findElement(By.xpath(btnProfileSubmit)).click();
    	implicityWaits(5);
    	waitOnElement("xpath", imageSrc);
    	WebElement element = driver().findElement(By.xpath(coverPhoto));
    	String imageSrc = element.getAttribute("style");
    	if(!imageSrc.contains(coverPhotoSrc))
    		isCoverPhotoAdded = true;
    	return isCoverPhotoAdded;
	}
	
	public boolean validateProfilePhoto() throws Exception {
		logInfo("inside validateProfilePhoto() method..");
		boolean isPhotoValidated = false;
		clickOnLink("linkText", CHANGEPROFILEPHOTO_text);
//		Thread.sleep(5000);
		/*waitOnElement("cssSelector",btnBrowseProfilePhoto);
    	clickOnElement("cssSelector", btnBrowseProfilePhoto);*/
    	uploadFile("PDF",btnBrowseProfilePhoto);
    	/*Thread.sleep(5000);
    	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_PDF.exe");
    	Thread.sleep(10000);*/
    	driver().findElement(By.xpath(btnSaveProfilePhoto)).click();
//    	Thread.sleep(10000);
    	waitOnElement("xpath",ValidatePhoto);
    	String txtProfilePhoto = driver().findElement(By.xpath(ValidatePhoto)).getText();
    	if(txtProfilePhoto.equalsIgnoreCase(txtValidateProfilePhoto))
    		isPhotoValidated = true;
    	return isPhotoValidated;
	}
	
	public boolean validateCoverPhoto() throws Exception {
		logInfo("inside validateCoverPhoto() method..");
		boolean isPhotoValidated = false;
		clickOnLink("linkText", CHANGECOVERPHOTO_text);
		
		uploadFile("PDF",browseTochangePhoto);
    	/*implicityWaits(3);
    	clickOnElement("cssSelector", browseTochangePhoto);
    	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_PDF.exe");
    	implicityWaits(5);*/
    	driver().findElement(By.xpath(btnSaveCoverPhoto)).click();
    	implicityWaits(10);
    	String txtCoverPhoto = driver().findElement(By.xpath(ValidatePhoto)).getText();
    	if(txtCoverPhoto.equalsIgnoreCase(txtValidateCoverPhoto))
    		isPhotoValidated = true;
    	return isPhotoValidated;
	}
	
	public boolean verifyViewAllLinkFeaturedPhotos() throws Exception{
		logInfo("inside verifyViewAllLinkFeaturedPhotos() method..");
		boolean isViewAllLinkFound = false;
		waitOnElement("xpath", lnkViewAllFeaturedPhotos);
		WebElement viewAllFeaturedPhotos = driver().findElement(By.xpath(lnkViewAllFeaturedPhotos));
		if(viewAllFeaturedPhotos!=null)
			isViewAllLinkFound = true;
		return isViewAllLinkFound;
	}
	
	public boolean verifyViewAllLinkFeaturedVideos() throws Exception{
		logInfo("inside verifyViewAllLinkFeaturedVideos() method..");
		boolean isViewAllLinkFound = false;
		waitOnElement("xpath", lnkViewAllFeaturedVideos);
		WebElement viewAllFeaturedVideos = driver().findElement(By.xpath(lnkViewAllFeaturedVideos));
		if(viewAllFeaturedVideos!=null)
			isViewAllLinkFound = true;
		return isViewAllLinkFound;
	}
	
	public boolean verifyViewAllLinkFeaturedBlogs() throws Exception{
		logInfo("inside verifyViewAllLinkFeaturedBlogs() method..");
		boolean isViewAllLinkFound = false;
		waitOnElement("xpath", lnkViewAllFeaturedBlogs);
		WebElement viewAllFeaturedBlogs = driver().findElement(By.xpath(lnkViewAllFeaturedBlogs));
		if(viewAllFeaturedBlogs!=null)
			isViewAllLinkFound = true;
		return isViewAllLinkFound;
	}
	
	public boolean verifyViewAllPhotos() throws Exception{
		logInfo("inside verifyViewAllPhotos() method..");
		boolean isViewAllLinkFound = false;
		WebElement viewAllPhotos = driver().findElement(By.xpath(viewAll));
		if(viewAllPhotos!=null){
			isViewAllLinkFound = true;
			viewAllPhotos.click();
		}
		return isViewAllLinkFound;
	}
	
	public boolean verifyViewAllVideos() throws Exception{
		logInfo("inside verifyViewAllVideos() method..");
		boolean isViewAllLinkFound = false;
		WebElement viewAllVideosList = driver().findElement(By.xpath(viewAllVideos));
		if(viewAllVideosList!=null){
			isViewAllLinkFound = true;
			viewAllVideosList.click();
		}
		return isViewAllLinkFound;
	}
	
	public boolean deleteBlogPost(String postName) throws Exception{
		logInfo("inside deleteBlogPost() method..");
		clickOnLink("linkText", lnkBlog);
		boolean isDeleted = false;
		int blogsCount = driver().findElements(By.xpath(draftsTable)).size();
		String before = "//*[@id='user-activity-tabs']/div/div[1]/div[2]/div[1]/div[2]/article[";
		String after = "]/div[1]/h3/a";
		
		String deleteBefore = "//*[@id='user-activity-tabs']/div/div[1]/div[2]/div[1]/div[2]/article[";
		String deleteAfter = "]/div[2]/div/div[2]/a[2]";
		for(int i=1; i <= blogsCount; i++ ){
			WebElement post = driver().findElement(By.xpath(before+i+after));
			String draftPost = post.getText().trim();
			if(draftPost.equalsIgnoreCase(postName.trim())){
				waitOnElement("xpath", deleteBefore+i+deleteAfter);
				driver().findElement(By.xpath(deleteBefore+i+deleteAfter)).click();
				//confirmEventDeleteModal();
				confirmOK();
				isDeleted = true;
				break;
			}
			else{
				isDeleted = false;
			}
		}
		System.out.println("Post "+postName+" is located in drafts.");
		logInfo("Post "+postName+" is located in published blog posts.");
		return isDeleted;
	}
	
	public boolean verifyPostPresent(String postName) throws Exception{
		logInfo("inside verifyPostPresent() method..");
		clickOnLink("linkText", lnkBlog);
		boolean isPostDeleted = false;
		int draftsCount = driver().findElements(By.xpath(draftsTable)).size();
		String before = "//*[@id='user-activity-tabs']/div/div[1]/div[2]/div[1]/div[2]/article[";
		String after = "]/div[1]/h3/a";

		for(int i=1; i <= draftsCount; i++ ){
			WebElement post = driver().findElement(By.xpath(before+i+after));
			String draftPost = post.getText().trim();
			if(draftPost.equalsIgnoreCase(postName.trim())){
				isPostDeleted = true;
				break;
			}
			else{
				isPostDeleted = false;
			}
		}
		System.out.println("Post "+postName+" is deleted in drafts.");
		logInfo("Post "+postName+" is deleted in drafts.");
		return isPostDeleted;
	}
	
	public boolean verifyFeaturePhotosByTagName(String photoName,String tagName) throws Exception {
	    logInfo("inside verifyFeaturesPhotosByTagName() method.");
	    boolean isTaggedPhotosFound = false;
		waitOnElement("xpath", paneFeaturedPhotos);
		WebElement featuredPhotoPane = driver().findElement(By.xpath(paneFeaturedPhotos));
		List allImages = featuredPhotoPane.findElements(By.tagName("div"));
		int totalImages = allImages.size();
		logInfo("Total images in Featured Photos panel = " +totalImages);
		boolean isTagFound = false;
		String before_img = "//*[@data-widget-path='widgets/featured_photos']/div/div/div[2]/div[";
		String after_img = "]/div/div[1]";

		if(totalImages >0){
			for(int i=1;i<=6;i++){
				WebElement e = driver().findElement(By.xpath(before_img+i+after_img));
				String imgName = e.getAttribute("title").trim();
				if(imgName.contains(photoName)){
					System.out.println("Image match found in featured photos = " +imgName);
					WebElement element = driver().findElement(By.xpath(before_img+i+after_img));
					break;
				}
			}
		}
		implicityWaits(5);
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPhotos = panel.findElements(By.xpath(paneCommunityWallContent));
		logInfo("Total photos =" +allPhotos.size());
		
		for(WebElement x : allPhotos){
			String actPostName = x.getText().trim();
			implicityWaits(2);
			if (actPostName.contains(photoName)){    
				logInfo(photoName + ": community photo match found.");
				x.click();
				implicityWaits(5);
				break;
			}
		}
	
		String photoTagName = driver().findElement(By.xpath(photoTag)).getText();
		if(photoTagName.contains(tagName)){
			isTagFound = true;
			driver().findElement(By.xpath(photoTag)).click();
		}
		
		String taggedPhotoBefore = "//*[@id='community-tabs']/div[1]/div[2]/div[";
		String taggedPhotoAfter = "]/span";
		List<WebElement> taggedPhotosCount = driver().findElements(By.xpath(taggedPhotosList));
		
		for(int i=1;i<= taggedPhotosCount.size();i++){
			WebElement e = driver().findElement(By.xpath(taggedPhotoBefore+i+taggedPhotoAfter));
			if(e.getText().equalsIgnoreCase(photoTitle_text))
				isTaggedPhotosFound = true;
		}
		logInfo("Tagged photos found : "+photoTitle_text + " with the tag name "+tagName); 
		return isTaggedPhotosFound;

	}
	
	public boolean verifyFeatureVideosByTagName(String video,String tagName) throws Exception {
	    logInfo("inside verifyFeatureVideosByTagName() method.");
	    boolean isTaggedVideosFound = false;
		waitOnElement("xpath", paneFeaturedVideos);
		WebElement featuredVideoPane = driver().findElement(By.xpath(paneFeaturedVideos));
		List allVideos = featuredVideoPane.findElements(By.tagName("div"));
		int totalVideos = allVideos.size();
		logInfo("Total videos in Featured Videos panel = " +totalVideos);
		boolean isTagFound = false;
		String before_video = "//*[@data-widget-path='widgets/featured_videos']/div/div/div[2]/div[";
		String after_video = "]/div/div[1]";

		if(totalVideos >0){
			for(int i=1;i<=6;i++){
				WebElement e = driver().findElement(By.xpath(before_video+i+after_video));
				String videoName = e.getAttribute("title").trim();
				if(videoName.contains(video)){
					System.out.println("Video match found in featured photos = " +videoName);
					WebElement element = driver().findElement(By.xpath(before_video+i+after_video));
					break;
				}
			}
		}
		implicityWaits(5);
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allPhotos = panel.findElements(By.xpath(paneCommunityWallContent));
		logInfo("Total videos =" +allPhotos.size());
		
		for(WebElement x : allPhotos){
			String actVideoName = x.getText().trim();
			implicityWaits(2);
			if (actVideoName.contains(video)){    
				logInfo(video + " community video match found.");
				x.click();
				implicityWaits(5);
				break;
			}
		}
	
		String videoTagName = driver().findElement(By.xpath(photoTag)).getText();
		if(videoTagName.contains(tagName)){
			isTagFound = true;
			driver().findElement(By.xpath(photoTag)).click();
		}

		String taggedVideoBefore = "//*[@id='community-tabs']/div[2]/div/div[";
		String taggedVideoAfter = "]/span";
		List<WebElement> taggedVideosCount = driver().findElements(By.xpath(taggedVideosList));
		
		for(int i=1;i<= taggedVideosCount.size();i++){
			WebElement e = driver().findElement(By.xpath(taggedVideoBefore+i+taggedVideoAfter));
			if(e.getText().equalsIgnoreCase(video))
				isTaggedVideosFound = true;
		}
		logInfo("Tagged videos found : "+video + " with the tag name "+tagName); 
		return isTaggedVideosFound;

	}
	
	public void shareByEmail(String mailId, String subject) throws Exception, Exception{
    	PropertyConfigurator.configure("Log4j.properties");	
    	logger.info("Enter recipient mailId & compose and send a mail");
    	recepAndSubject(mailId, subject);    	
    	clickOnButton("cssSelector", send_Mail);     	
    }
	
	 public void recepAndSubject(String emailId, String subject) throws Exception{
		logInfo("Inside recepAndSubject() method.");
    	waitOnElement("cssSelector",recipientsTo);   	
    	inputText("cssSelector", recipientsTo, emailId);    	
//    	Thread.sleep(5000);
    	waitOnElement("cssSelector", mailSub);
    	inputTextClear("cssSelector", mailSub);
    	inputText("cssSelector", mailSub, subject); 
//    	Thread.sleep(3000);
	    	
	 }
	    
	 public void emailWithAttachment(String mailId, String subject) throws Exception, Exception{    	
	    	logInfo("Enter recipient mailId & compose and send a mail");
	    	String Attach1 = "Attach To Message" ;
	    	String Attach2 = "Insert Into Message Editor" ;    	
	    	recepAndSubject(mailId, subject);    	
	    	clickOnButton("cssSelector", attach);
	      	uploadFile("Image",browseInBr);
	    	/*Thread.sleep(2000);	    	
	    	selectBrowse();	    	
	    	Thread.sleep(4000);
	    	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\ProfAudio.exe");
			Thread.sleep(5000);*/
			uploadFooter(Attach1);  			
	    	
	    	clickOnButton("cssSelector", send_Mail);    	
		 }
	 
	 public void emailWithResourceAttachment(String mailId, String subject, String resourceTitle) throws Exception, Exception{    	
	    	logInfo("Enter recipient mailId & compose and send a mail");
	    	String Attach2 = "Insert Into Message Editor" ;    	
	    	recepAndSubject(mailId, subject);  
	    	
	    	clickOnButton("cssSelector", attachResource);
	    	clickOnLink("linkText", "Newest Resources");
	    	resourceListWindow(resourceTitle);
//	    	Thread.sleep(3000);
	    	waitOnElement("cssSelector",resSelectCount);
	    	WebElement count = driver().findElement(By.cssSelector(resSelectCount));
			System.out.println(count.getText());
			clickOnButton("cssSelector", saveResource);
//			Thread.sleep(2000);
			waitOnElement("cssSelector", send_Mail);
			clickOnButton("cssSelector", send_Mail);
			
			
	    	/*waitOnElement("xpath", send_Mail);
	    	 */   	
		 }
	 
	 
	 public boolean resourceListWindow(String resourceName) throws Exception{
		 
		 boolean isResourcePresent = false;
//		 Thread.sleep(2000);		 
		 String resListplus = "> a > i";
		 String resSelect =  ") > div > div > div > div > div.media-body > input";
		 waitOnElement("cssSelector",resList);
		 List <WebElement> rl=  driver().findElements(By.cssSelector(resList));
		 System.out.println("Resource size is "+ rl.size());
		 if (rl.size()==0){
			 System.err.println("Sorry!! No new resource are available");
			 
		 }else{		 
			 for (int i =2; i<=rl.size(); i++){
			 WebElement resName = driver().findElement(By.cssSelector(resListB4r+i+resListAfr));
			 System.out.println("Resource are "+ resName.getText());
			 if(resName.getText().contains(resourceName)){				 
				 isResourcePresent= true;
				 WebElement resrPlus = driver().findElement(By.cssSelector(resListB4r+i+resListAfr+resListplus));
				 resrPlus.click();
				 List <WebElement> selectRes=  driver().findElements(By.cssSelector(resListB4r+i+resSelect));
				 System.out.println("No of resource are "+selectRes.size() );
				 for (WebElement select : selectRes ){					 
					 if (!select.isSelected()){
						 select.click();
					 }
				 }
				 
				 break;			 
				 
			 }
			 
		 } } if (isResourcePresent==false){
			 Assert.assertTrue(isResourcePresent, resourceName+" resource is not found.");
		 }
		return isResourcePresent;
		 

	 }

	 public void selectBrowse() throws Exception{
		logInfo("Inside selectBrowse() method");
		clickOnElement("xpath", browseInBr);
		
 	/*if (driver().getCurrentUrl().contains("master")){
 		System.out.println(driver().getCurrentUrl());
 		clickOnElement("xpath", browseInMas);		    		
 	}else {	   		
 		clickOnElement("xpath", browseInBr);	    		
 	}
	*/
	 }
	      
	    
 public void uploadFooter(String attachType) throws Exception{
 	logInfo("Inside uploadFooter() method");
 	List <WebElement> att = driver().findElements(By.cssSelector(foot));
 	System.out.println("foters size "+att.size());
 	for (WebElement attach: att){	 
 		System.out.println("footers"+ attach.getText());
 		if(attach.getText().equalsIgnoreCase(attachType));{	    			
 			attach.click();
// 			Thread.sleep(5000);
 			break;
 		}
 	}
 }
 
 
 public boolean selectShare(String postName) throws Exception {
		logInfo("Select Share link under posted" + postName);
		
	
		WebElement panel = driver().findElement(By.xpath(paneCommunityWall));
		List<WebElement> allMessages = panel.findElements(By.xpath(paneCommunityWallStatus));
		boolean isMatchFound = false;
		System.out.println("Total posts =" +allMessages.size());
		logInfo("Total posts =" +allMessages.size());
		
		String before_name = "//div[@class='activities-stream']/div[";
		String after_name = "]/div/div[1]";		// /a
		
		String before_share = "//div[@class='activities-stream']/div[";
		String after_share = "]/div/div[2]/span[1]/span[3]";		// /a
					
		System.out.println("Total messages ="+ allMessages.size());
		for(int i=1;i<=allMessages.size();i++){
			waitOnElement("xpath",before_name+i+after_name);
			WebElement x = driver().findElement(By.xpath(before_name+i+after_name));
			String name = x.getText().trim();
			System.out.println("Post are " +name);
			
			if(name.equalsIgnoreCase(postName)){
				logInfo(postName + " community post match found.");
				isMatchFound = true;
				WebElement e = driver().findElement(By.xpath(before_share+i+after_share));
				e.click();
				logInfo("clicked on the link " + e.getText().trim());
//				Thread.sleep(2000);				
				break;
			}
		} if (isMatchFound==false)
		{
			Assert.assertTrue(isMatchFound, postName +" is not found to share." );
		}
		
		 return isMatchFound;
	}
 
 
 
 public void loginGooglePlus(String uname, String passwd) throws Exception{
		logInfo("Acess into google plus account with credentials");
		driver().navigate().to("https://plus.google.com/");	
		clickOnButton("cssSelector", "a#gb_70");
		inputText("cssSelector",inputGmail, uname);
		clickOnButton("cssSelector", btnGmailNext);
		/*WebElement staysigned = driver().findElement(By.xpath(chkStaySignedIn));
		String isChecked = staysigned.getAttribute("checked").trim();
		if(isChecked.equalsIgnoreCase("checked")){
			staysigned.click();
			}*/
		waitOnElement("cssSelector",inputGmailPasswd);
		inputText("cssSelector",inputGmailPasswd, passwd);
		clickOnButton("cssSelector", btnSignIn);
		
 
 }
 
 

	public boolean verifyInGooglePlus(String postName) throws Exception{
		
		clickOnElement("cssSelector", googAll);
		boolean isPostPresent  = false;
		
		List <WebElement> list = driver().findElements(By.cssSelector(googPostList));
		System.out.println(list.size());
		for (WebElement glist: list){
			
			System.out.println(glist.getText()+ " in google plus");
			
			if (glist.getText().trim().contains(postName)){
				isPostPresent = true;					
				System.out.println("Google Puls Post name is "+ glist.getText());
				break;
			}	
			
		}if (isPostPresent==false){
			
			Assert.assertTrue(isPostPresent, postName + " is not found in googlePlus page" );
		}	
		
		clickOnElement("cssSelector", googAccountIcon);
		clickOnButton("cssSelector", googLogout);
//		Thread.sleep(3000);
		return isPostPresent;
		
		
	}	 
	 
	 
	

    
	
}
    			
    	
    	
    



