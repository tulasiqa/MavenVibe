package vibe.myprofile.tests;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;

import common.LoginCredentials;
import common.TestBase;
import common.readProp;
import vibe.inbox.tests.InboxMethods;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.shopping.tests.ShoppingMethods;
import vibe.tasks.tests.TaskMethods;

public class MyProfileMethods extends TestBase {
	
	TaskMethods tm = new TaskMethods();
	readProp prop = new readProp();
	
	// Jayadev's methods
	
		public void navigate2MyProfilePage() throws InterruptedException {
			logInfo("inside navigate2MyProfilePage() method");
			driver().navigate().to(appUrl + "community/profile");
		}
		
		
		public void go2EditProfilePage() throws Exception{
			logInfo("inside go2EditProfilePage() method");
			driver().navigate().to(appUrl + "account#my-account");
		}
		
		public String getSrcForImage(String img){
			logInfo("inside getSrcForImage() method.");
			WebElement eimg = driver().findElement(By.xpath(img));  
			String src = eimg.getAttribute("src");
			src = src.trim();
			String tagName = eimg.getTagName();
			String alt = eimg.getAttribute("alt");
			System.out.println("Src =" +src);
			System.out.println("TagName =" +tagName);
			System.out.println("Alt =" +alt);
			return alt;
		}
		
		public void verifyAndDeletePhotoInMyProfilePage(String photoTitle) throws Exception{
			logInfo("inside verifyPhotoInMyProfilePage() method.");
			verifyLinkPresent("Photos");
			clickOnLink("linkText","Photos");
			WebElement container = driver().findElement(By.xpath("//div[@class='photos featured']"));
			List allPhotos = container.findElements(By.xpath("//div[contains(@class,'col-md-3 col-sm-12')]"));
			int total_photos = allPhotos.size();
			System.out.println("Total Photos =" +total_photos);
			
			String before_name = "//div[@class='photos featured']/div[";
			String after_name = "]/div[@class='photo-image']/a/img";
			
			for(int i=1;i<=total_photos;i++){
				System.out.println("i =" +i);
				WebElement name = driver().findElement(By.xpath(before_name+i+after_name));
				name.click();
				// Thread.sleep(5000);
				/*waitOnElement("xpath","//div[@class='modal-title']");
				String actTitle = driver().findElement(By.xpath("//div[@class='modal-title']")).getText();
				System.out.println("actTitle =" +actTitle.trim());
				
				if(actTitle.trim().equalsIgnoreCase(photoTitle)){
					System.out.println(photoTitle + " photo match found in MyProfile page");
					clickOnLink("linkText","Delete");
					confirmEventDeleteModal();
					break;
				} 
				*/
				clickOnElement("xpath","//div[@class='close-container']/button[@class='close']/i");
					
				/*if(!actTitle.trim().equalsIgnoreCase(photoTitle)){
					waitOnElement("xpath","//div[@class='close-container']/button[@class='close']/i");
					clickOnElement("xpath","//div[@class='close-container']/button[@class='close']/i");
				}*/
				
				
			}
			
		}

	
	public void nav2CommunityPage() throws InterruptedException{
		logInfo("inside navigate2CommunityPage() method..");
		Thread.sleep(5000);
		driver().navigate().to(appUrl + "community/wall");
	}
	
	
	public void assertMyProfilePage() throws InterruptedException{
		String expectedTitle = "VIBE - Profile Activity";
		Thread.sleep(2000);
		Assert.assertEquals(driver().getTitle(), expectedTitle);		
	}	
	
	public void verifyMyRecentActivity(String activity){
		List <WebElement> act = driver().findElements(By.cssSelector(actMsg));
		System.out.println("Activities size "+ act.size());
		boolean isActivityPresent = false;
		for (WebElement acts : act){
			System.out.println(acts.getText());			
			if (acts.getText().contains(activity)){
				isActivityPresent = true;
				System.out.println(acts.getText()+" matches");
				break;
			}
			
		} if(isActivityPresent==false){			
			Assert.assertTrue(isActivityPresent, activity + " status post match not found in  myProfile-Recent Activities.");
		}		

	}
	
	public void verifyPostNotPresentInMyRecentActivity(String activity){
		List <WebElement> act = driver().findElements(By.cssSelector(actMsg));
		System.out.println("Activities size "+ act.size());
		boolean isActivityPresent = true;
		for (WebElement acts : act){
			System.out.println(acts.getText());			
			if (acts.getText().contains(activity)){
				isActivityPresent = false;
				System.out.println(acts.getText()+" matches");
				Assert.assertTrue(isActivityPresent, activity + " status post matches in  myProfile.");				
				break;
			}
			
		} if(isActivityPresent==true){
			System.out.println("Suceesss! Not matched in Recent activities");
			
		}		

	}
		
	public void verifyAddBlogPhotoVideo(String postText) throws IOException, AWTException{
		logInfo("VerifyProfile & Post a Blog, Photo, Video");		
		
		try{			
			navigate2MyProfilePage();		
			verifyObjects();
			postBlog(postText);			
			addPhotoForMyProfile(inputPhotoTitle_text);
			verifyPhotoOrVideoPresent(inputPhotoTitle_text);
			addVideoForMyProfile(inputVideoTitle_text);	
			verifyPhotoOrVideoPresent(inputVideoTitle_text);
		} catch (Exception e){
			
		e.printStackTrace();
		}
		
	}	
  
	
	public void postBlog(String text) throws Exception{
		selectBlog();		
		enterTitleOfAddPost(text);
		//profile.handleBody();
		enterBodyOfAddPost();
		enterExcerptOfAddPost();
		SelectPublishChkBox();		
		createPost();
		//confirmationMessage("Post is created");
	}
	
	
	
	
	public void selectLinkActivity() throws Exception{
		clickOnLink("linkText", activity);
	}
	
	public void cselectLinkPhotos() throws Exception{
		clickOnLink("cssSelector", photos);
	}
	
	public void selectLinkVideo() throws Exception{
		clickOnLink("cssSelector", videos);
		
	}
	public void selectLinkBlog() throws Exception{
		clickOnButton("linkText", blog);
		
	}
	
	public boolean verifyObjects() throws Exception{
		 
		logInfo("**** Assert all objects of My Profile Page********************");
				
		getText("xpath", eventsWidget,"Event Name");
		
		String  actualActivity = driver().findElement(By.linkText("Activity")).getText();
		String  actualPhotos = driver().findElement(By.linkText("Photos")).getText(); 
		String  actualVideos = driver().findElement(By.linkText("Videos")).getText(); 
		String  actualBlog = driver().findElement(By.linkText("Blog")).getText(); 
		String  actualChangeCoverPhoto = driver().findElement(By.linkText("Change Cover Photo")).getText(); 
		String  actualChangeProfilePhoto = driver().findElement(By.linkText("Change Profile Photo")).getText(); 
		
		Assert.assertEquals(ACTIVITY_text, actualActivity);
		Assert.assertEquals(actualPhotos,PHOTOS_text);		
		Assert.assertEquals(BLOG_text,actualBlog);
		Assert.assertEquals(VIDEOS_text,actualVideos);
		Assert.assertEquals(CHANGECOVERPHOTO_text, actualChangeCoverPhoto);
		Assert.assertEquals(CHANGEPROFILEPHOTO_text, actualChangeProfilePhoto);		
		System.out.println("Assertion is completed for all objetcs");
		return true;		
		
	}
	
	public void selectLinkAddBlogPost() throws Exception{
		clickOnLink("linkText", addBlogPost);
		
	}

 public void selectLinkAddPhotoOrVideo() throws Exception{
	clickOnLink("linkText", addPhotoVideo);		
	}

 public void enterTitleOfAddPost(String text) throws Exception{
	clickOnTextArea("id", title_addPost, text);
 }

 public void selectBlog() throws Exception{
	 	
	logInfo("Trying to select The Blog link from My Profile and Add Blog button");
	try{
	clickOnLink("linkText", "Blog");
	clickOnButton("cssSelector", addBlogButton);
		}
		catch(Exception e){
	logger.error(e.getMessage(),e);
	throw new Exception ("Failed!!  not selected Blog and AddBlog button");
			}
	logInfo((new Exception().getStackTrace()[0].getMethodName())+"    Success");
	
}

	
	public void enterBodyOfAddPost()throws IOException, AWTException, Exception {
	//	composeTextOnElement();	
		composeText("xpath","//iframe[contains(@title,'Rich Text Editor')]","This is a new post");

	}

	public void enterExcerptOfAddPost() throws Exception{
		
		clickOnTextArea("cssSelector", excert,"excerpt" );
	}
	
   public void SelectPublishChkBox(){
	   
	   WebElement pub = driver().findElement(By.cssSelector(pulish));
	   if (!pub.isSelected()){
		   pub.click();
	   }
   }

 
      public void createPost() throws Exception{
    	  clickOnButton("cssSelector", createPost);
       }


public void addPhotoForMyProfile(String photoTitle) throws Exception{	
	 
	logInfo("Adding photo to my Profile");
	navigate2MyProfilePage();
	//clickOnLink("linkText", lnkAddPhotoVideo);
	clickOnLink("linkText","Photos");
	Thread.sleep(3000);
	clickOnButton("cssSelector", addPhotoButton);
	Thread.sleep(7000);	
	inputText("xpath",inputPhotoURL,inputPhotoURL_text);	
	inputText("xpath",inputPhotoTitle,photoTitle);
	inputText("xpath",inputPhotoDesc,inputPhotoDesc_text);
	inputText("xpath",inputPhotoTags,inputPhotoTags_text);	
	clickOnButton("xpath",btnSharePhoto);	
	logInfo("Successfully Added photo to my Profile");
	
	try{
		confirmationMessage("Your photo has been added");
		Thread.sleep(5000);
		}catch(Exception e){
			logger.error("Warning!! confirmation message is not populated in time");
			
		}
	}


public void addPhotoFromMyProfile(String photoTitle) throws Exception{	
	 
	logInfo("Adding photo to my Profile");
	navigate2MyProfilePage();
	//clickOnLink("linkText", lnkAddPhotoVideo);
	clickOnLink("linkText","Photos");
	Thread.sleep(3000);
	clickOnLink("linkText", "Add Photo");
	Thread.sleep(7000);	
	inputText("xpath",inputPhotoURL,inputPhotoURL_text);	
	inputText("xpath",inputPhotoTitle,photoTitle);
	inputText("xpath",inputPhotoDesc,inputPhotoDesc_text);
	inputText("xpath",inputPhotoTags,inputPhotoTags_text);	
	clickOnButton("xpath",btnSharePhoto);	
	logInfo("Successfully Added photo to my Profile");
	
	try{
		confirmationMessage("Your photo has been added");
		Thread.sleep(5000);
		}catch(Exception e){
			logger.error("Warning!! confirmation message is not populated in time");
			
		}
	}


    public void verifyPhotoOrVideoPresent(String photoRVedio){
    	 
    	logInfo("Verify the presence of Photo or video in my profile list.");
    	
    	
    	boolean isPhotoPresent =false;
    	List <WebElement> pp = driver().findElements(By.cssSelector(photoList));
    	System.out.println(pp.size());
    	for(WebElement pl :pp){
    		if(pl.getText().equalsIgnoreCase(photoRVedio)){
    			
    			isPhotoPresent=true;
    			break;
    		}
    		
    	}if (isPhotoPresent==false){
    		Assert.assertTrue(isPhotoPresent, photos +" - photo is not found. Failed!!" );
    	}
    }

public void addVideoForMyProfile(String videoTitle) throws Exception{
	 
	logInfo("Adding video to my Profile");
	try{
	navigate2MyProfilePage();
	clickOnLink("linkText", "Videos");
	clickOnButton("cssSelector", addPhotoButton);
	Thread.sleep(5000);
	inputText("xpath",inputVideoURL,inputVideoURL_text);
	Thread.sleep(5000);
	inputText("xpath",inputVideoTitle,videoTitle);
	inputText("xpath",inputVideoDesc,inputVideoDesc_text);
	inputText("xpath",inputVideoTags,inputVideoTags_text);
	
	selectFromDropdown("cssSelector",drpdnVisibilityVideoSettings,"byVisibleText",drpdnVisibilityVideoSettings_text);

	clickOnButton("cssSelector",shareVideoButton);
	logInfo("Successfully Added Video to my Profile");	
			}catch (Exception e) {
					logger.error("failed !! not able to enter video details. ");	
				}
	try{
	/*confirmationMessage("Your video has been added");*/
		clickOnLink("linkText", "Videos");
			}catch(Exception e){
				logger.error("Warning!! confirmation message is not populated in time");
		
				}
		}

	public void changeCoverPhotoAndProfile() throws IOException, AWTException, Exception{
	 
	logInfo("Both Change Cover photo and Profile from ProfContent");
	navigate2MyProfilePage();
	clickOnLink("linkText", CHANGECOVERPHOTO_text);
//	waitOnElement("cssSelector", browseTochangePhoto);
	waitOnElement("cssSelector", browseTochangePhoto);
	 uploadFile("Image", browseTochangePhoto);
	/*inbox.acessLocalDrive();
	inbox.acessPhotos();
	inbox.accessPhotos2();
	Thread.sleep(3000);*/
	/*Thread.sleep(3000);
	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\ProfAnnaver.exe");
	Thread.sleep(3000);*/
	doubleClick("cssSelector", updateUserbutton);
	logInfo("********Successfully !! Uploaded both cover photo and Profile .************");
    
}

   

public void editProfileAndVerifyAccountLinks() throws Exception{
	 
	logInfo("Edit my Profile and verify All Accounts links and check pages");
	navigate2MyProfilePage();
	clickOnButton("cssSelector", editProfile);
	List<WebElement> accountPanelFocus = driver().findElements(By.xpath(accountPanelSize));
	System.out.println("No of Account Panel List : "+accountPanelFocus.size());	{	
	    	for (int i=1 ; i <= accountPanelFocus.size(); i++){		    		 
		    		 WebElement accountLinks = driver().findElement(By.xpath(accountLinks1+i+accountLinks2));
					System.out.println("Selected Accounts Link is : "+accountLinks.getText());
					accountLinks.click();
					implicityWaits(30);		
					}		    	
				}
	 	    }

			public String getAccountProfileFirstName() throws Exception, Exception{
				logInfo("Entered into getAccountProfileFirstName () method ");
				navigateMyProfileAccount("Community Profile");				
				waitOnElement("xpath",txtDisplayFirstName);
				/*clickOnElement ("cssSelector", editFName);			
				Thread.sleep(3000);
				waitOnElement("cssSelector",inputCommunityFName);
				inputTextClear("cssSelector",inputCommunityFName);
				Thread.sleep(3000);
				inputText("cssSelector",inputCommunityFName,custFNText);
				clickOnButton("xpath",btnCommunityFNameSave);*/				
				WebElement fn = driver().findElement(By.xpath(txtDisplayFirstName));
				String FirstName = fn.getText();				
				return FirstName;
				
			}



       public void navigateNotificationsAndRetrieveMailId() throws Exception{
			navigateMyProfileAccount("Notifications");
			getMailId();
			
	
}

public void getMailId() throws Exception{
	getText("cssSelector", mailId, "mails is ");
	WebElement mails = driver().findElement(By.cssSelector(mailId));	
	String id = mails.getText(); 
	
}

public void navigateMyProfileAccount(String AccountName) throws Exception{	 
	logInfo("Edit my Profile and navigate to Notifications page.");
	back2Office();
	waitOnElement("cssSelector", lnkProfileDrpdn);        
    clickOnElement("cssSelector", lnkProfileDrpdn);        
    waitOnElement("cssSelector",accounts);        
    clickOnElement("cssSelector",accounts);
	boolean isAccountPresent = false;
	List<WebElement> accountPanelFocus = driver().findElements(By.xpath(accountPanelSize));
	System.out.println("size "+ accountPanelFocus.size());
		{	
	    	String aft = ") > a";
			String bfr = "div.panel.panel-columnar.panel-widget > div > ul.panel-nav > li:nth-child(";
			for (int i=1 ; i <= accountPanelFocus.size(); i++){		    		 
		    		WebElement accountLinks = driver().findElement(By.cssSelector(bfr+i+aft));                   //(accountLinks1+i+accountLinks2));					
					accountLinks.click();	
					System.out.println(accountLinks.getText());
					implicityWaits(30);{
					if (accountLinks.getText().equals(AccountName)){
						isAccountPresent=true;
						break;
					}	}				
		    	}		
			
		 } if (isAccountPresent ==false){
			 Assert.assertTrue(isAccountPresent, AccountName);
			 
		 }	 
	    } 

public void navigateMyProfileAccount_old(String AccountName) throws Exception{	 
	logInfo("Edit my Profile and navigate to Notifications page.");
	
	navigate2MyProfilePage();
	waitOnElement("cssSelector", editProfile);	
	clickOnButton("cssSelector", editProfile);	
	boolean isAccountPresent = false;
	List<WebElement> accountPanelFocus = driver().findElements(By.xpath(accountPanelSize));
	System.out.println("size "+ accountPanelFocus.size());
		{	
	    	for (int i=2 ; i <= accountPanelFocus.size(); i++){		    		 
		    		 WebElement accountLinks = driver().findElement(By.xpath(accountLinks1+i+accountLinks2));					
					accountLinks.click();					
					implicityWaits(30);{
					if (accountLinks.getText().equals(AccountName)){
						isAccountPresent=true;
						break;
					}	}				
		    	}
		    	
		 } if (isAccountPresent ==false){
			 Assert.assertTrue(isAccountPresent, AccountName);
			 
		 }	 
	    } 





    
// To do - EditPhoto and Video functionality is changed, we have to update the scripts to the same Dec 20 16

    public void editPhoto() throws Exception{
	 
	logInfo("Editing Photo from my profile.");	
	navigate2MyProfilePage();	
	clickOnLink("linkText","Photos");
	waitOnElement("cssSelector", myPhotos);
	clickOnElement("cssSelector", myPhotos);
	clickOnElement("cssSelector", editlnk);	
	//editedPhotoDetails();	
	
	}
    
    public void editVideo() throws Exception{
    	 
    	logInfo("Editing Video from my profile.");	
    	navigate2MyProfilePage();	
    	doubleClick("xpath", myVideo);
		clickOnButton("xpath", videoImage);
		Thread.sleep(5000);
		clickOnLink("linkText", "Edit");
    	//clickOnElement("cssSelector", edtVedio);
    	Thread.sleep(5000);
    	//editedPhotoDetails();  	
    	
    	}
  /*  
    public void editedPhotoDetails() throws Exception{
    	 
    	logInfo("Update the photo details.");
    	Thread.sleep(3000);    	
    	Robot rb = new Robot();
    	rb.keyPress(KeyEvent.VK_TAB);
    	rb.keyRelease(KeyEvent.VK_TAB);
    	rb.delay(2000);
    	rb.keyPress(KeyEvent.VK_TAB);
    	rb.keyRelease(KeyEvent.VK_TAB);
    	rb.keyPress(KeyEvent.VK_ENTER);
    	rb.keyRelease(KeyEvent.VK_ENTER);    	
    } */

    public void isTitlePresent() throws InterruptedException{
    	Thread.sleep(5000);
    	WebElement tt= driver().findElement(By.xpath(btnSharePhoto));
    	String title = tt.getText();
    	System.out.println("title is "+title);
    	Assert.assertEquals(title, "Share Photo");   	
    	
    }
    
    
    public void changeProfileAvatar() throws IOException, AWTException, Exception{
    	logInfo("inside changeProfileAvatar() method.");
    	clickOnElement("xpath", "//div[@class='profile-update-picture']/a/i");
    	
    	waitOnElement("cssSelector", profileAvatar);
    	uploadFile("Image", profileAvatar);
    	    	
    	waitOnElement("cssSelector",saveButton);
    	clickOnElement("cssSelector",saveButton);
    	waitOnElement("cssSelector",photoSubmit);
    	clickOnElement("cssSelector",photoSubmit);

    }
    
    public void changeCoverPhoto() throws IOException, AWTException, Exception{
    	logInfo("inside changeCoverPhoto() method.");
    	verifyElementPresent("xpath", "//div[@class='profile-update-cover-photo']/a/i");
    	clickOnElement("xpath", "//div[@class='profile-update-cover-photo']/a/i");
    	
    	waitOnElement("cssSelector",changeCoverPic);
    	uploadFile("Image", changeCoverPic);
    
    	waitOnElement("cssSelector",saveBtnOfCover);
    	clickOnElement("cssSelector",saveBtnOfCover);
    	
    	
    	waitOnElement("cssSelector",photoSubmit);
    	clickOnElement("cssSelector",photoSubmit);
    	
    }    
    
   
    

	
	public void deletePhoto(){
		 
		logInfo("Deleting Photo from my profile.");
		try{
		navigate2MyProfilePage();	
		clickOnLink("linkText","Photos");	
		waitOnElement("cssSelector", myPhotos);
		clickOnElement("cssSelector", myPhotos);		
		clickOnButton("xpath", photoImage);
		delete();
		/*confirmationMessage(confMsgPhotoDeleted);	*/	
		}catch (Exception e){
			System.err.println("Failed!!!  Not able to capture , confirmation message ");
		}	
	
	}
	
	public void selectDelete() throws Exception{
		 
		logInfo("Select Delete link, when select photo or Video. ");
		
		try{
			//System.out.println("size is "+driver().findElements(By.cssSelector(deleteFromDistMan)).size());
			getText("cssSelector", deleteFromDistMan, "Link is ");
			clickOnElement("cssSelector", deleteFromDistMan);
			Thread.sleep(5000);
			clickOnButton("cssSelector", deleteOkFromDistMan);
			confirmationMessage(confMsgPhotoDeleted);		
			}catch (Exception e){
				logger.error("Failed!!!  Not able to capture , confirmation message ");
				System.err.println("Failed!!!  Not able to capture , confirmation message ");
			}
		back2Office();	

    }	
	 
	
	
	
	
	
	public void deleteVideo() throws Exception{	
		 
		logInfo("Deleting My video ");
		
		doubleClick("xpath", myVideo);
		clickOnButton("xpath", videoImage);
		/*clickOnLink("xpath", photoDelete);
		clickOnButton("xpath", deleteOk);*/
		delete();
		try{
			//confirmationMessage(confMsgVideoDeleted);
			}catch(Exception e){
				logger.error("Warning!! confirmation message is not populated in time");
				
			}	}
	
	
	public void communityProfileAndVerifyAccountLinks() throws Exception{
		// todo complete functionality
		
		 
		logInfo("************** Edit my Profile and community profile a*********");
		navigate2MyProfilePage();
		clickOnButton("cssSelector", editProfile);
		
	}
	

	public void selectVideoInMyProfile() throws Exception{
		 
		logInfo("Navigate to My Profile page and select video.");
		navigate2MyProfilePage();	
		doubleClick("xpath", myVideo);
		clickOnButton("xpath", videoImage);
		clickOnButton("xpath", photoImage);		
		Thread.sleep(5000);
		
	}
	
	
	public void navGoals() throws Exception, InterruptedException{
		
		clickOnLink("linkText", goal);
		Thread.sleep(4000);		
		
	}
	
	public void addGoalsLinkSelection() throws Exception, InterruptedException{
		
		clickOnLink("linkText", addGoal);
		Thread.sleep(5000);		
		
	}
	
	
	public void titleTextEnter(String title) throws Exception{
		Thread.sleep(2000);
		inputText("cssSelector",goalTitle,title);		
		
	}

	public void DescriptionTextEnter() throws Exception{
	
	inputText("cssSelector",goalDesc,Description_text);
	
	
	}
	public void completeByDate(int days) throws Exception{
		
		String NoOfDays = getDate(days, "MM/dd/yyyy");
		inputText("cssSelector", completeBy, NoOfDays);		
		
		}
	public void showOnBoardCheckbox() throws Exception{
		
		WebElement board = driver().findElement(By.cssSelector(showOnBrd));
		if(!board.isSelected()){			
			board.click();
		}	
		
	}

    public void UploadImageInGoal() throws Exception{		
		waitOnElement("cssSelector", goalUpload);
		uploadFile("Image", goalUpload);
		/*Thread.sleep(3000);
		Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\ProfAudio.exe");
		Thread.sleep(5000);	
		*/
	}

	public void saveGoalButton() throws Exception{				
	clickOnButton("cssSelector", saveGoal);
	Thread.sleep(5000);		
	}
	
	
	public void verifyGoalsIsPresent(String goalName) throws Exception{
		
		Thread.sleep(4000);
		List <WebElement> gl = driver().findElements(By.cssSelector(goalsList));
		System.out.println("Goals size "+gl.size());		
		boolean isGoalpresent = false;
		for (WebElement gls : gl){			
			if (gls.getText().equalsIgnoreCase(goalName)){				
				isGoalpresent= true;	
				break;				
			}
			
		}if (isGoalpresent ==false){
			
			Assert.assertTrue(isGoalpresent, goalName + " : is not available in the Goals list");
		}
	
	
	}
	
	

	
	public void deleteGoals(String goal) throws Exception{
			
			List <WebElement> gl = driver().findElements(By.cssSelector(goalsList));
			System.out.println("Goals size "+gl.size());
			
			for (WebElement gls : gl){	
				String glname = gls.getText();
				
				if(glname.equalsIgnoreCase(goal)){				
					gls.click();
					Thread.sleep(4000);	
					delete();
					break;
					
				}
					
			}
		
		}

	

   public void delete() throws Exception{
			
			try{	
				Thread.sleep(5000);
				List <WebElement> del = driver().findElements(By.cssSelector(deletelnk));				
				for (WebElement dels : del){					
					if (dels.getText().equalsIgnoreCase("Delete")){						
						dels.click();
							
						waitOnElement("cssSelector", comDeleteOk);		
						clickOnButton("cssSelector", comDeleteOk);						
						break;
					}					
				}				
			}catch(Exception e){				
				logger.error("Failed !! Not able to deleted .");
				
			}
		}

     public void close() throws Exception{
			
			try{	
				//Thread.sleep(5000);
				waitOnElement("cssSelector", prop.getLocatorForEnvironment(appUrl,"btnCloseWebsite"));
				clickOnElement("cssSelector", prop.getLocatorForEnvironment(appUrl,"btnCloseWebsite"));						
			}catch(Exception e){				
				logger.error("Failed !! Not able to deleted .");
				
			}

		}
     
     public void login2FBVerifyPostedDetails() throws Exception{
    	  
    		logInfo("Login into FaceBook");	
    		Thread.sleep(3000);
    		driver().manage().deleteAllCookies();
    		Thread.sleep(5000);
    	    driver().navigate().to("https://www.facebook.com/");
    	    waitOnElement("cssSelector", fbEmail);
			inputText("cssSelector", fbEmail, fBEmail_text2);
			Thread.sleep(5000);
			inputText("cssSelector", fbPwd, fBPwd_text2);
			Thread.sleep(3000);
			submitElement("cssSelector", fbPwd);
			clickOnElement("xpath", "//*[@id='u_0_c']");
			
		}

     public void getPostsFromFB(String photoPost) throws Exception{    	  
		  logInfo("Verify the Vibe shared post in FaceBook which will retrive from second part");
	
	 boolean isSharedPostPresent=false;
   	 List <WebElement> post = driver().findElements(By.xpath("//*[@class='mbs _6m6 _2cnj _5s6c']/a"));
   	 System.out.println("FB size is "+post.size());
   	 
   	 for (WebElement pp: post){    
   		 
   		 if (pp.getText().contains(photoPost)){
   			 System.out.println("after matching  is "+pp.getText());
   			 isSharedPostPresent = true;
   			 break;
   		 }   		 
   	 } if (isSharedPostPresent ==false){   		 
   		 System.err.println (photoPost+ " is not avaiable in FB");
   		 Assert.assertTrue(isSharedPostPresent, photoPost + " is not avaiable in FB");
   		 
   	 }
   	 waitOnElement("cssSelector",fbNavgiation );    	 
   	 clickOnElement("cssSelector",fbNavgiation );    
   	 waitOnElement("xpath", fbLogout);  
	 clickOnElement("xpath", fbLogout);
	 logIn(adminUser_text,adminPwd_text);
			
		 
     }
     
     
     public void getPostsFromFBOfFirstPart(String photoPost) throws Exception{    	  
 		  logInfo("Verify the Vibe shared post in FaceBook");
 		 boolean isSharedPostPresent=false;
    	 List <WebElement> post = driver().findElements(By.cssSelector(fbPost));
    	 System.out.println("FB size is "+post.size());
    	 
    	 for (WebElement pp: post){     		 
    		 if (pp.getText().contains(photoPost)){
    			 System.out.println("after matching  is "+pp.getText());
    			 isSharedPostPresent = true;
    			 break;
    		 }   		 
    	 } if (isSharedPostPresent ==false){    		 
    		 System.err.println (photoPost+ " is not avaiable in FB");
    		 Assert.assertTrue(isSharedPostPresent, photoPost + " is not avaiable in FB");    		 
    	 }
    	 waitOnElement("cssSelector",fbNavgiation );    	 
       	 clickOnElement("cssSelector",fbNavgiation );    
       	 waitOnElement("xpath", fbLogout);  
    	 clickOnElement("xpath", fbLogout); 
		}
     
     
  public void getPostsFromFBFrom2ndPart(String photoPost) throws Exception{    	  
     logInfo("Verify the Vibe shared post in FaceBook which will retrive from second part");
	 boolean isSharedPostPresent=false;
     List <WebElement> post = driver().findElements(By.cssSelector(fbPost2ndPart));
   	 System.out.println("FB size is "+post.size());   	 
   	 for (WebElement pp: post){    
   		 System.out.println(pp.getText());
   		 if (pp.getText().contains(photoPost)){
   			 System.out.println("after matching in FB   is "+pp.getText());
   			 isSharedPostPresent = true;
   			 break;
   		 }   		 
   	 } if (isSharedPostPresent ==false){
   		 
   		 System.err.println (photoPost+ " is not avaiable in FB");
   		 Assert.assertTrue(isSharedPostPresent, photoPost + " is not avaiable in FB");
   		 
   	 }	
   	waitOnElement("cssSelector",fbNavgiation );    	 
  	clickOnElement("cssSelector",fbNavgiation );    
  	waitOnElement("xpath", fbLogout);  
	clickOnElement("xpath", fbLogout); 
			
		}
    
     public void login2LinkedIn() throws Exception{
    	  
    		logInfo("Login into LinkedIn account ");	
    		driver().manage().deleteAllCookies();
    		Thread.sleep(5000);
    	    driver().navigate().to("https://www.linkedin.com/");
    	    waitOnElement("cssSelector", linkedEmail);
			inputText("cssSelector", linkedEmail, fBEmail_text);
			Thread.sleep(5000);
			inputText("cssSelector", linkedPwd, fBPwd_text);
			Thread.sleep(3000);
			submitElement("cssSelector", linkedPwd);	
		}
     
     
     public void verifyPostInLinkedAccount(String postTitle) throws Exception{
    	  
 		logInfo("LinkedIn- Select your updates and verify the posted photos from community wall. And then logout");
    	 
 		WebElement prof = driver().findElement(By.cssSelector(lipro)); 		
 		Actions action = new Actions(driver());
 		action.moveToElement(prof).build().perform();
 		clickOnLink("linkText", "Your Updates"); 		
 		System.out.println(driver().getTitle()); 		
 		boolean isPostPresent = false; 		
 		/*if (driver().getTitle().equals(liTitle)){*/
 		List <WebElement> li = driver().findElements(By.cssSelector(liList));
 		System.out.println("get size "+ li.size());
 		for (WebElement ls: li){  			
 			if(ls.getText().contains(postTitle)){
 				isPostPresent = true;
 				break;				
 			}
 			
 		}  if (isPostPresent ==false){
   		 
   		 System.err.println (postTitle+ " is not present in LinkedIn");
   		 Assert.assertTrue(isPostPresent, postTitle+ " is not present in LinkedIn");
   		 
   	 }	
 		Thread.sleep(5000);
 		WebElement acc = driver().findElement(By.cssSelector(liAcc));
 		action.moveToElement(acc).build().perform();
 		clickOnLink("linkText", "Sign Out");
 		driver().manage().deleteAllCookies();
 		
 		
 		
 		/*}else{
 			driver().manage().deleteAllCookies();
 			Assert.assertEquals(driver().getTitle(), liTitle);
 			System.err.println("Not able to opened 'Your Updates' screen.");
 		}
    	 */
     }
     
     
     
     public void login2Twitter1() throws Exception{
    	  
    		logInfo("Login into Twitter account ");	
    		driver().manage().deleteAllCookies();
    		Thread.sleep(5000);
    	    driver().navigate().to("https://twitter.com/login");     	    
    	    Thread.sleep(3000);
			inputText("cssSelector", twtUser, fBEmail_text);
			Thread.sleep(2000);
			
			WebElement remmber = driver().findElement(By.cssSelector(remember));
			if (remmber.isSelected()){
				
				remmber.click();
			}
			
			inputText("cssSelector", twtPwd, fBPwd_text);
			Thread.sleep(3000);
			
			submitElement("cssSelector", twtPwd);	
		}
     
     public void login2Twitter() throws Exception{
      
     logInfo("Login into Twitter account ");	
    		
            driver().manage().deleteAllCookies();
    		Thread.sleep(5000);
    	    driver().navigate().to("https://twitter.com/login");     	    
    	    Thread.sleep(6000);     	    
    	    String title = "Login on Twitter";
    	    System.out.println(driver().getTitle());
    	    
    	    
    	    if (driver().getTitle().contains(title)){      	    
        	    logInfo("Trying to enter login credentials of Twitter");
        	    Thread.sleep(2000);
    			inputText("cssSelector", twtUser, fBEmail_text);
    			Thread.sleep(2000);			
    			WebElement remmber = driver().findElement(By.cssSelector(remember));
    			if (remmber.isSelected()){				
    				remmber.click();
    			}			
    			inputText("cssSelector", twtPwd, fBPwd_text);
    			Thread.sleep(3000);			
    			submitElement("cssSelector", twtPwd);	
    			
    		}/*else if (driver().findElement(By.cssSelector(tweet)).getText().equalsIgnoreCase("Tweet")){	    	
    	    	logInfo("Already logged in Twitter.");
    	    	System.out.println(driver().getTitle());
    	    }*/
        	    else{
    			System.out.println("Failed!! Something happened.");
    			
    		}
        	    
        }
     
     public void verifyPostsInTwitter(String twitterPost) throws Exception{
    	  
 		  logInfo("Verify the Vibe shared post in Twitter account");
 		 boolean isSharedPostPresent=false;
    	 List <WebElement> post = driver().findElements(By.cssSelector(twtPosts));
    	 System.out.println("size is "+post.size());
    	 
    	 for (WebElement pp: post){    
    		 
    		 if (pp.getText().contains(twitterPost)){
    			 System.out.println("after matching  is "+pp.getText());
    			 isSharedPostPresent = true;
    			 break;
    		 }   		 
    	 } if (isSharedPostPresent ==false){
    		 
    		 System.err.println (twitterPost+ " is not avaiable in Twitter");
    		 Assert.assertTrue(isSharedPostPresent, twitterPost + " is not avaiable in Twitter");
    		 
    	 }	
    	 
    	 clickOnElement("cssSelector", twtlog);
    	 Thread.sleep(2000);
    	 
    	 clickOnElement("cssSelector", twtLogout);
    	 logIn(adminUser_text,adminPwd_text);
		}
     
		
		public void addTaskSelect() throws Exception, InterruptedException{
			clickOnLink("linkText", "Add Task");
			Thread.sleep(4000);			
		}
		
		
		public void tasksPanelList(String tasks) throws Exception{
			
			// tasks ="This task is added from Goal which order no is : 82";
			List <WebElement> gol = driver().findElements(By.cssSelector(tasksPanel));
			System.out.println("Size of Goal is "+ gol.size());
			
			for (WebElement goalist: gol ){		
				String goaler = goalist.getText();				
				if (goaler.equals(tasks)){					
					goalist.click();
					break;
				}
						
			}			
		}

	
		public void deleteTasksFromGoal() throws Exception{
			//tasks = "This task is added from Goal which order no is : 83";
		
			List <WebElement> gl = driver().findElements(By.cssSelector(tasksPanel));
			System.out.println("Goals size "+gl.size());			
			for (int i =1; i<=gl.size(); i++){	
				WebElement gls = driver().findElement(By.cssSelector(tasksPanel));
					gls.click();
					Thread.sleep(4000);	
					tm.deleteTaskFromTaskDetails();						
				}				
		
		}
		
		public void deletionAll() throws Exception{
					
			List <WebElement> photo = driver().findElements(By.cssSelector(imgs));
			System.out.println(photo.size());
			for (WebElement ph :photo ){				
				WebElement li = driver().findElement(By.xpath(img));				
				li.click();
				delete();
				/*confirmationMessage(confMsgPhotoDeleted);*/	
				
			}		
		
		}
		

		public void clickPhotoLink() throws Exception{
			
			clickOnLink("linkText","Photos");
			Thread.sleep(2000);		
		}
		
       public MyProfileMethods clickVedioLink() throws Exception{
			
    	   doubleClick("xpath", myVideo);
		   Thread.sleep(2000);	
		   return this;
			
		}
		
		
       public void logoutFromDistbutor() throws Exception {
    	   
    	waitOnElement("xpath", lnkProfileDrpdn);
   		clickOnElement("xpath", profileImg);
   		clickOnElement("cssSelector",logOut);		
   		waitOnElement("linkText", "Sign In");
    	   
       }

      public void go2ProfileBlog() throws Exception, Exception{
    	  waitOnElement("linkText","Blog");
    	  clickOnLink("linkText","Blog");
      }
       
       
      public void go2AddBlogPost() throws Exception, Exception{
    	  waitOnElement("linkText","Add Blog Post");
    	  clickOnLink("linkText","Add Blog Post");
      }
      
      public boolean addProfilePhoto() throws Exception{
  		logInfo("inside addProfilePhoto() method..");
  		boolean isProfilePhotoAdded = false;
  		clickOnLink("linkText", CHANGEPROFILEPHOTO_text);
  		implicityWaits(3);
  		waitOnElement("cssSelector", btnBrowseProfilePhoto);
  		 uploadFile("Image", btnBrowseProfilePhoto);
      	/*Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\ProfileImage.exe");*/
      	implicityWaits(5);
      	driver().findElement(By.xpath(btnSaveProfilePhoto)).click();
      	implicityWaits(5);
      	driver().findElement(By.xpath(btnProfileSubmit)).click();
      	implicityWaits(5);
      	waitOnElement("xpath", imageSrc);
      	WebElement element = driver().findElement(By.xpath(imageSrc));
      	String imageSrc = element.getAttribute("alt");
      	if(!imageSrc.contains(txtRemoveImage))
      		isProfilePhotoAdded = true;
      	return isProfilePhotoAdded;
  	}
  	
      public boolean addCoverPhoto() throws Exception{
  		logInfo("inside addCoverPhoto() method..");
  		boolean isCoverPhotoAdded = false;
  		clickOnLink("linkText", CHANGECOVERPHOTO_text);
  		implicityWaits(3);
  		waitOnElement("cssSelector", btnBrowseCoverPhoto);
  		 uploadFile("Image", btnBrowseCoverPhoto);
  		//Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Image.exe");
      	implicityWaits(5);
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
  	
  	
  	public boolean removeCoverPhoto() throws Exception {
  		logInfo("inside removeCoverPhoto() method..");
  		boolean isRemovedImage = false;
  		clickOnLink("linkText", btnRemoveCoverPhoto);
  		//confirmEventDeleteModal();
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
  	

	
	public boolean validateProfilePhoto() throws Exception {
		logInfo("inside validateProfilePhoto() method..");
		boolean isPhotoValidated = false;
		clickOnLink("linkText", CHANGEPROFILEPHOTO_text);
		implicityWaits(3);
    	waitOnElement("cssSelector", btnBrowseProfilePhoto);
    	 uploadFile("pdf", btnBrowseProfilePhoto);
    /*	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_PDF.exe");*/
    	implicityWaits(5);
    	driver().findElement(By.xpath(btnSaveProfilePhoto)).click();
    	implicityWaits(10);
    	String txtCoverPhoto = driver().findElement(By.xpath(ValidatePhoto)).getText();
    	if(txtCoverPhoto.equalsIgnoreCase(txtValidateProfilePhoto))
    		isPhotoValidated = true;
    	return isPhotoValidated;
	}
	
	public boolean validateCoverPhoto() throws Exception {
		logInfo("inside validateCoverPhoto() method..");
		boolean isPhotoValidated = false;
		clickOnLink("linkText", CHANGECOVERPHOTO_text);
		implicityWaits(3);
    	waitOnElement("cssSelector", browseTochangePhoto);
    	uploadFile("pdf", browseTochangePhoto);
    	//Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_PDF.exe");
    	implicityWaits(5);
    	driver().findElement(By.xpath(btnSaveCoverPhoto)).click();
    	implicityWaits(10);
    	String txtCoverPhoto = driver().findElement(By.xpath(ValidatePhoto)).getText();
    	if(txtCoverPhoto.equalsIgnoreCase(txtValidateCoverPhoto))
    		isPhotoValidated = true;
    	return isPhotoValidated;
	}
	
	
	//Ravi - implemeneted On 09/21/2016
	
	 public void updateBlogInProfile(String postName, String NewBlogName) throws Exception{
		   boolean isPresent = false;
		   navigate2MyProfilePage();
		   clickOnLink("cssSelector", blog);
//		   Thread.sleep(4000);
		   waitOnElement("cssSelector",blgList);
		   List <WebElement> blg = driver().findElements(By.cssSelector(blgList));
		   for (WebElement blogs : blg){
			   if (blogs.getText().equalsIgnoreCase(postName)){				   
				   isPresent =true;
				   blogs.click();
				   Thread.sleep(2000);
				   clickOnLink("linkText", "Edit");
				   Thread.sleep(2000);
				   inputTextClear("cssSelector",inputAddPostTitle);
				   inputText("cssSelector",inputAddPostTitle,NewBlogName); 
				   Thread.sleep(3000);	
				   clickOnButton("cssSelector",btnCreatePost);
				   confirmationMessage("Post was successfully updated.");
				   break;
			   }
			   
		   }if(isPresent==false){
			   Assert.assertTrue(isPresent, postName + " - post is not present");
		   }   
		   
	 }
	
	
	public void deleteBlogPostFromProfile(String postName) throws Exception{
		   boolean isPresent = false;
		   navigate2MyProfilePage();
		   clickOnLink("cssSelector", blog);	   
		   Thread.sleep(3000);
		   List <WebElement> blg = driver().findElements(By.cssSelector(blgList));
		   for (WebElement blogs : blg){
			   if (blogs.getText().equalsIgnoreCase(postName)){				   
				   isPresent =true;
				   blogs.click();
				   Thread.sleep(2000);
				   clickOnLink("linkText", "Delete");
				   confirmOK();				   
				   break;
			   }
			   
		   }if(isPresent==false){
			   Assert.assertTrue(isPresent, postName + " - post is not present");
		   }	   
	   }
	
	
	public void editPhotoToUpdate(String existTitle , String newTitle) throws Exception{		
		    nav2CommunityPage();
			Thread.sleep(5000);	
			clickOnLink("linkText", existTitle);
			clickOnLink("cssSelector", edit);
			Thread.sleep(2000);
			inputTextClear("xpath",inputPhotoTitle);
			inputText("xpath",inputPhotoTitle,newTitle);
			Thread.sleep(3000);
			clickOnButton("cssSelector",shareBtn);
			confirmationMessage("Your photo has been updated.");
			Thread.sleep(6000);		
	}
	
	 public void deletePhotoToUpdate(String existTitle , String newTitle) throws Exception{		
		nav2CommunityPage();
		Thread.sleep(5000);	
		clickOnLink("linkText", existTitle);
		clickOnLink("cssSelector", edit);
		Thread.sleep(2000);
		inputTextClear("xpath",inputPhotoTitle);
		inputText("xpath",inputPhotoTitle,newTitle);
		clickOnButton("xpath",btnSharePhoto);
		confirmationMessage("Your photo has been updated.");
		Thread.sleep(3000);		
	 		}
	 
	 
	 public void deleteVideoInProfile(String videoTitle) throws Exception{			
			navigate2MyProfilePage();
			clickOnLink("cssSelector", videos);	   
			Thread.sleep(3000);
			boolean isPresent = false;
			List<WebElement> vTitl = driver().findElements(By.cssSelector(titVid));
			System.out.println(vTitl.size());
			for (int i=1; i<=vTitl.size(); i++){
				WebElement vidT = driver().findElement(By.cssSelector(titVidBfr+i+titVidAft));
				if(vidT.getText().equalsIgnoreCase(videoTitle)){
					isPresent= true;
					WebElement imgIcon = driver().findElement(By.cssSelector(titVidBfr+i+titVidImgAft));
					imgIcon.click();
					Thread.sleep(5000);
					clickOnLink("linkText","Delete");
					Thread.sleep(2000);
					confirmOK();
					confirmationMessage("Video is deleted");
					break;
					
				}
				
				
			}if(isPresent==false){
				Assert.assertTrue(isPresent, videoTitle +" -video is not present in My profile");
			}
			
			
			
		}
	 
	 
	 public void deleteAllVideosInProfile() throws Exception{			
			clickOnLink("cssSelector", videos);	   
			Thread.sleep(3000);			
			List<WebElement> vTitl = driver().findElements(By.cssSelector(vidAllImgs));
			System.out.println(vTitl.size());
			for(WebElement all:vTitl ){			
				    all.click();
					Thread.sleep(5000);
					clickOnLink("linkText","Delete");
					Thread.sleep(2000);
					confirmOK();
					//confirmationMessage("Video is deleted");
					Thread.sleep(3000);
					navigate2MyProfilePage();
					clickOnLink("cssSelector", videos);	   
					Thread.sleep(3000);	
					
				}	
			
		}
	
	
      
}
