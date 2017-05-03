package vibe.mycommunity.tests;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import common.LoginCredentials;
import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.people.tests.PeopleMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(8)
public class Community_Tests extends CommunityMethods {
	
	MyProfileMethods profile = new MyProfileMethods();
	ShoppingMethods shop= new ShoppingMethods();
	
	InboxMethods inbox = new InboxMethods();
	NewsMethods news = new NewsMethods();
	readProp prop = new readProp();
	
	String restoreSubject = "Its not an Issue on comments.";
	String deleteSubject = "Intolerance, comments are not acceptable";
	String TEXT = "New blog "+ TestBase.random();
	
	@Test(priority=801)	
    public void comm_createPhotoToShare() throws Exception{	
	     
		logInfo("Create the Photo");   
		addCommunityPhoto(inputPhotoTitle_text2);
	}
	
	@Test(priority=802)
	public void createBlogPost() throws Exception{
		
		logInfo("inside createBlogPost() Test..");
		addBlogPost(addPostTitle_text);		
		boolean isPostFound =  verifyPostIsPresent(addPostTitle_text);
		if(isPostFound==true){
			deleteCommunityPost(addPostTitle_text);
		}
	}

	@Test(priority=803)
	public void postStatusOnCommunity() throws Exception{
		logInfo("inside postStatusOnCommunity() Test..");
		

		postStatus(communityStatus_text);
		boolean isPostFound =  verifyStatusPostIsPresent(communityStatus_text);
		if(isPostFound==true){
			System.out.println("status post found...");
			 likeStatusOnCommunity(communityStatus_text, "Like");
			 
			 boolean isLiked = verifyPostActivityLinks(communityStatus_text, "Liked");
				
			 
		///	 commentStatusOnCommunity(communityStatus_text);
			// hideStatusPost(communityStatus_text);
		}
	}

	@Test(priority=804)
	public void featureCommunityPhoto() throws Exception{
		logInfo("inside featureCommunityPhoto() Test..");
		addCommunityPhoto(photoTitle_text);		
		boolean isPostFound =  verifyPostIsPresent(photoTitle_text); 
		if(isPostFound==true){
			 featurCommunityPost(photoTitle_text);
			 verifyFeaturedPhotos(photoTitle_text); 
			 deleteCommunityPost(photoTitle_text);
			}
		}

	@Test(priority=805)
	public void featureCommunityVideo() throws Exception{
		logInfo("inside featureCommunityVideo() Test..");
		addCommunityVideo(videoTitle_text);		
		boolean isPostFound =  verifyPostIsPresent(videoTitle_text);
		if(isPostFound==true){
			
			 featurCommunityPost(videoTitle_text); 
		
			 verifyFeaturedVideos(videoTitle_text); 
			 deleteCommunityPost(videoTitle_text);
		}
	}	
		
	@Test(priority=806)
	public void comm_AddPhotoWithDistributorCredentials() throws Exception{
		 
		logInfo("Login with distributor credentials and add photo to community .");		
//		Thread.sleep(4000);
		news.loginAsSubUser(VibeProMonthlyUser);	
		profile.addPhotoFromMyProfile(inputPhotoTitle_text2);	
		news.userLogout();
		
	
	}
	
	
	@Test (priority=807,dependsOnMethods = {"comm_AddPhotoWithDistributorCredentials"})
	public void AbuseReport_HandleAbuseReportAndDelete() throws Exception{		 
		logInfo("Abuse Report on photo, Naviagte to Reported Abuse and delete the Reported Photo from Vibe");	
		try{
			navigate2CommunityPage();
			reportThePosted(inputPhotoTitle_text2);
			enterReportAndSubmit();	
		    confirmationMessage("Report has been recorded");
			navigate2ReportedAbuseUnderDistManager();
		    selectAbusedFromPendingAction(inputPhotoTitle_text2);
			isAbuseReportnotTobePresent(inputPhotoTitle_text2);
			back2Office();

		}catch (Exception e ){
			logger.error("Failed !! Sorry Something went wrong while deleting Abused Report");
		}
	}


	@Test (priority=808)
	public void CommentPhoto() throws Exception{
		 
		logInfo("*******handleCommunities*********");		
		 commentOnPhoto();		
	}
		
	@Test(priority=809)
	public void AbuseReport_RestoreThePhoto() throws Exception{
		 
		logInfo("Add photo with distributor and again login with another user to report abused photo. \n"
				+ "Go to Admin, verify the reports and make restore the photo.");		
		navigate2CommunityPage();
//		Thread.sleep(4000);
		shop.logoutFromAdmin();
		// logIn(masDistlogin, masDistPswd);		
		logIn(adminUser_text,adminPwd_text);
		profile.addPhotoForMyProfile(PhotoTitle_text1);	
//		Thread.sleep(6000);
		shop.logoutFromAdmin();
		logIn(adminUser_text,adminPwd_text);		
		navigate2CommunityPage();
		reportThePosted(PhotoTitle_text1);
		enterReportAndSubmit();	
	    confirmationMessage("Report has been recorded");
		navigate2ReportedAbuseUnderDistManager();
		restoreAbusedReport(PhotoTitle_text1);		
		sendEmailToRepoters(restoreSubject);
		
	}

	
	@Test(priority=810)
	public void AbuseReport_DeleteAbusedPhoto() throws Exception{
		 
		logInfo("Add photo with distributor and again login with another user to report abused photo. \n"
				+ "Go to Admin, verify the reports and delete the photo with comments.Reverify the photo is deleted from distributor community.");		
		shop.logoutFromAdmin();
		// logIn(masDistlogin, adminPwd_text); 	
		logIn(adminUser_text,adminPwd_text);
		profile.addPhotoForMyProfile(PhotoTitle_text1);		
		shop.logoutFromAdmin();
		// logIn(adminUser_text,adminPwd_text);
		logIn(adminUser_text,adminPwd_text);
		navigate2CommunityPage();
		reportThePosted(PhotoTitle_text1);
		enterReportAndSubmit();	
	    confirmationMessage("Report has been recorded");
		navigate2ReportedAbuseUnderDistManager();
		restoreAbusedReport(PhotoTitle_text2);
		sendEmailToRepoters(restoreSubject);
		
	}
	
	@Test(priority=811)
	public void comm_AssertsOjects() throws Exception{
		 
		logInfo("TC634- Assertion all objects.");
    	navigate2CommunityPage();     	       
    	WebElement communityTitle = driver().findElement(By.cssSelector(comHeader));    
    	Assert.assertEquals(communityTitle.getText(), ComHeaderText);
    	Assert.assertTrue( isAddPhotoVedioPresent());
    	Assert.assertTrue( isAddBlogPostPresent());		  	
    	
    }
	
    @Test(priority=811)	
    public void SharePhotoInFaceBook() throws Exception{		     
		logInfo("Share the Photo in FaceBook and again verify same  by logging into FaceBook");
		
		navigate2CommunityPage(); 	
		addCommunityPhoto(inputPhotoTitle_text);
		clickOnLink("linkText", inputPhotoTitle_text);
		shop.selectFacebookIcon();		
		shop.shareInFaceBook();
		shop.selectLinkedInIcon();
		shop.shareInLinkedIn();		
		shop.selectTwitterInIcon();    	
    	shop.shareInTwitter();
      //  shop.selectGooglePlusIcon();    	
    	//shop.shareInGooglePlus(gmailId_text, gmailPwd_text);		
		profile.close();		
		profile.login2FBVerifyPostedDetails();  
		profile.getPostsFromFB(inputPhotoTitle_text) ; 	
		logIn(adminUser_text,adminPwd_text);
		
	}
    
    @Test(priority=812)	
    public void SharePhotoInLinkedIn() throws Exception{
     
	logInfo("Share the Photo in LinkedIn");	
    navigate2CommunityPage(); 	

	
	navigate2CommunityPage(); 
	for (int i=0; i<=2;i++){
		 clickOnLink("cssSelector",vieMor );
		 }	
	waitOnElement("linkText", inputPhotoTitle_text);	
	clickOnLink("linkText", inputPhotoTitle_text);	
	shop.selectLinkedInIcon();
	shop.shareInLinkedIn();
	profile.close();
    profile.login2LinkedIn();
    profile.verifyPostInLinkedAccount(inputPhotoTitle_text);
    //     lc.login();
    logIn(adminUser_text,adminPwd_text);  
	
           }
    
    @Test(priority=813)	
    public void SharePhotoInTwitter() throws Exception{	
         
    	logInfo("TC703 - Verify share photo on Twitter.");      
    	navigate2CommunityPage(); 	
    	sharethePost(inputPhotoTitle_text);    
    	shop.selectTwitterInIcon();    	
    	shop.shareInTwitter();
    	 closeSocialList();
    	 profile.login2Twitter() ;
    	 profile.verifyPostsInTwitter(inputPhotoTitle_text);
    	// lc.login();   
    	 logIn(adminUser_text,adminPwd_text);
    }
    
    @Test(priority=814)	
    public void SharePhotoInGooglePlus() throws Exception{	         
    	logInfo("TC702 - Verify share photo on Google++.");         	
    	navigate2CommunityPage(); 
    	/*for (int i=0; i<=3;i++){
			 clickOnLink("cssSelector",vieMor );
			 Thread.sleep(2000);
			 }*/
    	Thread.sleep(3000);	
    	sharethePost(inputPhotoTitle_text);    
    	shop.selectGooglePlusIcon(); 	
    	shop.shareInGooglePlus(gmailId_text, gmailPwd_text);
    	closeSocialList();	
    	loginGooglePlus(gmailId_text, gmailPwd_text);
    	verifyInGooglePlus(inputPhotoTitle_text);
    	logIn(adminUser_text,adminPwd_text);
    	
   }  
    
    @Test(priority=816)	
    public void reLoginAsAdminFromCommunity() throws Exception{
    	
    	logIn(adminUser_text,adminPwd_text);
    	
    }
    
    @Test(priority=816)	
    public void SharePhotoByEmail() throws Exception{	
    	logInfo("TC648,TC649,TC650 TC663 - VerifyEmail with blank , invalid  and Share photo by sending email.");  
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
		System.out.println(selfmailId);		
		navigate2CommunityPage();		
		waitOnElement("linkText", inputPhotoTitle_text);	
		clickOnLink("linkText", inputPhotoTitle_text);	
		if(driver().getCurrentUrl().contains("master")){
			waitOnElement("xpath",btnResEmail);
			clickOnElement("xpath",btnResEmail);
		}
		else{
			shop.selectEmailIcon();
		}
		
		sendMailWithoutRecipient();
		//sendMailWithInvalidRecipient(invalidMailId);
		shareByEmail(selfmailId,subText);
		waitOnElement("xpath","//*[@id='toast-container']/div/div");
		String toasterMsg = driver().findElement(By.xpath("//*[@id='toast-container']/div/div")).getText().trim();
		if(driver().getCurrentUrl().contains("master")){
			if(!toasterMsg.contains("Your photo has been sent to")){
				Assert.assertTrue(false, "Unable to share the photo by Email.");
			}
		}
		else{
			if(!toasterMsg.contains("Message sent to")){
				Assert.assertTrue(false, "Unable to share the photo by Email.");
			}
		}
		
		
		//confirmationMessage(prop.getLocatorForEnvironment(appUrl,"toasterMsg")+selfmailId); 
		profile.close();	
   }   
    
  
    @Test(priority=817)	
    public void SharePhotoInPWP() throws Exception{	
    	logInfo("TC705 - Verify Share photo/video to PWP"); 
    	navigate2CommunityPage();    	
    	sharethePost(inputPhotoTitle_text);    
    	shop.selectPWPInIcon(); 
    	confirmationMessage("Your photo has been shared to your personal website.");
    	waitOnElement("cssSelector",".close");
    	clickOnElement("cssSelector",".close");
    	/*verifyInPWPList(inputPhotoTitle_text);
    	navigate2CommunityPage(); 	*/
    }
    
    
    @Test(priority=818)	
    public void SharePhotoByEmailWithAttachment() throws Exception{		    	
    	String inputPhotoTitle_text = "Test Photo 210";
		logInfo("TC706 - Verify share photo/video using share by email with attachment"); 
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
		System.out.println(selfmailId);
		
	    navigate2CommunityPage();
		Thread.sleep(5000);	
		clickOnLink("linkText", inputPhotoTitle_text);	
		shop.selectEmailIcon();	
		emailWithAttachment(selfmailId,subText);
		profile.close();
		inbox.go2Inbox();
		inbox.selectVibeMailsWithSubject(subText);
		inbox.deleteFilteredVibeMails();

    }  
    
    

    @Test(priority=819)	
    public void ChngLikedToLike() throws Exception{
	     
	 	logInfo("TC637 - Verify changing LIKED to LIKE option for posted status, retrive the count of likes.");
	 	navigate2CommunityPage();
	 	postStatus(PhotoTitle_text4);
	 	likeThePost(PhotoTitle_text4);
	 	retriveLikeCount(PhotoTitle_text4);
	 	likeThePost(PhotoTitle_text4);
    }
    
    @Test(priority=820)	
    public void comm_ValidatePostButton() throws Exception{	     
	 	logInfo("TC639 - Verify Post button validation when doesn't enter data in comments input box. \n"
	 			+ "Validation message should display 'Status can't be blank'.");	
	 	
	 	navigate2CommunityPage(); 	
	 	validatePostComment();
	 	confirmationMessage("Status can't be blank");
	 	
    }
    
    @Test(priority=821)	
    public void comm_ValidateMyprofileFromAvatar() throws Exception{
	     
	 	logInfo("TC641 - Verify user name link clickable for self posted status.\n"
	 			+ "Able to navaigate to MyProfile page , assert with title.");
	 	
	 	postStatus(PhotoTitle_text2);
	 	navigate2CommunityPage();
	 	avatarUserNameSelection(PhotoTitle_text2);
	 	profile.assertMyProfilePage(); 	
    }
    
    @Test(priority=822)	
    public void comm_HidePostAndVerifyInMyProfile() throws Exception{
	     
	 	logInfo("TC642 - Verify Hide link for posts and assert Toast message.\n"
	 			+ "Same post should be visible on Community > My profile page.");
	    postStatus(PhotoTitle_text3);
	    navigate2CommunityPage();
	    for (int i=0; i<=3;i++){
			 clickOnLink("cssSelector",vieMor );
			 Thread.sleep(2000);
			 }
		hidePostStatus(PhotoTitle_text3);
		confirmationMessage("Activity hidden from community stream");
		navigate2CommunityPage();
		isActivityNotToPresentInCommunity(PhotoTitle_text3);
		profile.navigate2MyProfilePage();
		profile.verifyMyRecentActivity("Posted a status: \""+PhotoTitle_text3+"\""); 	
 	
    }
    
    @Test(priority= 823)
	public void comm_VerifyPostedPhotoVisibleToCommunity() throws Exception{
		 
		logInfo("TC_671:Verify 'posted photo visible to community.");
		navigate2CommunityPage();
		addPhotoInCommunity(addCommunityPhotoTitle_text, visibilitySettings_text);
		confirmationMessage(confMsgPhoto);
		assertActivities();
		
	}
    
    @Test(priority=824)	
    public void comm_ValidateMandatoryFields4Photos() throws Exception{	
    	 
    	logInfo("TC668 - Verify Share photo button without selecting Image");
    	navigate2CommunityPage();
    	PhotoWithoutImage(photoTitle_text2);    		 
    	  	
    }
    
    @Test(priority=825)	
    public void comm_ValidateMandatoryTitle4Photos() throws Exception{	
    	 
    	logInfo("TC669 - Verify Share photo button with out giving Title.");       		
    	navigate2CommunityPage();
    	PhotoWithoutTitle();
    } 
    
    @Test(priority=826)	
    public void comm_ValidatePhotoInCommunity() throws Exception{	
    	 
    	logInfo("TC671 - Verify 'posted photo visible to community.");      		
  		createPhoto(commphoto, visibility_text_community);
    	isActivityPresentInCommunity(commphoto);
    	isActivitypresentInMyRecentActivities(commphoto);  		 
    	  	
    }
    
    @Test(priority=827)	
    public void comm_ValidatePrivatePhoto() throws Exception{    	  
    	logInfo("TC672 - Verify 'posted photo' visible to Private.");      		
        createPhoto(commPrivatePhoto, visibility_text_private);
    	isActivityNotToPresentInCommunity(commPrivatePhoto);
    	isActivitypresentInMyRecentActivities(commPrivatePhoto);  		 
    	  	
    }    
    
    @Test(priority=828)	
    public void comm_ValidateVideoWithoutUrl() throws Exception{	
    	  
    		logInfo("TC678 - Verify Share video button with out giving Video url.");    				
    		navigate2CommunityPage();
    		addVideoNoUrl(photoTitle_text2);    		 
    }
    
    @Test(priority=829)	
    public void comm_ValidateVideoInvalidUrl() throws Exception{	
    	  
    		logInfo("TC678 - Verify Share video button with out giving Video url.");    		   		
    		navigate2CommunityPage();
    		invalidVideoUrl();    		 
    }
    
   
    
    
    //TC709
    @Test(priority=833)
    public void validatePostWithoutName() throws Exception{
    	 navigate2CommunityPage();
    	Assert.assertTrue( validatePostWithoutTitle());
    }
    
    //TC710
    @Test(priority=834)
    public void validatePostWithoutBody() throws Exception{
    	 navigate2CommunityPage();
    	Assert.assertTrue( validatePostWithoutBody(addPostTitle_text));
    }
    
    //TC711
    @Test(priority=835)
    public void verifyVisibilitySetting() throws Exception{
    	 navigate2CommunityPage();
    	Assert.assertTrue( verifyVisibilitySettingToCommunity());
    }
    
    //TC713
    @Test(priority=836)
    public void verifyDraftPost() throws Exception{
    	 navigate2CommunityPage();
    	 addBlogPost(addPostTitle_text, visibility_text_community, false);
    	profile.navigate2MyProfilePage();
    	Assert.assertTrue( verifyDraftPost(addPostTitle_text));
    }
    
    //TC714
    @Test(priority=837)
    public void editDraftPost() throws Exception{
    	 profile.navigate2MyProfilePage();
    	 Assert.assertTrue(updateDraftPost(addPostTitle_text,addPostTitle_text1));
    	 confirmationMessage(txtUpdatePost);
    }
    
   
    
    //Mandatory test to execute on Blogs.
    @Test(priority=838)
    public void addBlogPostInCommunity() throws Exception{
    	 addBlogPost(addPostTitle_text);
    	 Assert.assertTrue( verifyBlogInCommunityWall(addPostTitle_text));
    }
    
    //TC720
    @Test(priority=839)
    public void likeBlogPost() throws Exception{
    	    	
    	navigate2CommunityPage();
    	Assert.assertTrue( likeThePost(addPostTitle_text));
    }
    
    //TC723
    @Test(priority=840)
    public void addBlogpostComments() throws Exception{
    	navigate2CommunityPage();
    	Assert.assertTrue( verifyCommentsCountForBlog(addPostTitle_text));
    }
    
    //TC736
    @Test(priority=840)
    public void deleteBlogPost() throws Exception{
 	   navigate2MyProfilePage();
 	   boolean isDeleted =  deleteBlogPost(addPostTitle_text);
 	   if(isDeleted)
 		   Assert.assertFalse( verifyPostPresent(addPostTitle_text));
 		   
    }
    //Mandatory test to execute on Blogs.
    @Test(priority=840)
    public void verifyWidgetPresentInCommunityWall() throws Exception{
    	 verifyWidgetPresent(widgetRecentActivity); 
    }
    
    //TC716
    @Test(priority=841)
    public void verifyPrivateBlogPost() throws Exception{
    	 navigate2CommunityPage();
		clickOnLink("linkText", addBlogPost);
		 addBlogPost(addPostTitle_text,visibility_text_private,true);
    	Assert.assertFalse( verifyBlogInCommunityWall(addPostTitle_text));
    }
    
    //TC717	
    @Test(priority=842)
    public void verifyPublicBlogPost() throws Exception{
    	 navigate2CommunityPage();
		clickOnLink("linkText", addBlogPost);
		 addBlogPost(addPostTitle_text,visibility_text_public,true);
    	Assert.assertTrue( verifyBlogInCommunityWall(addPostTitle_text));
    }
    
    //TC719
    @Test(priority=843)
    public void verifyBlogPostEditableInCommunityWall() throws Exception{
    	navigate2CommunityPage();
    	Assert.assertTrue( verifyBlogPostEditableInCommunityWall(addPostTitle_text));
    }
    
    //TC721
    @Test(priority=845)
    public void changedLikedToLike() throws Exception{
    	 navigate2CommunityPage();
    	Assert.assertTrue( unlikeStatusOnCommunity(addPostTitle_text));
    }
    
    //TC722
    @Test(priority=846)
    public void verifyCommentsCountToNewBlog() throws Exception{
    	 navigate2CommunityPage();
    	Assert.assertTrue( commentsCountForNewBlog(addPostTitle_text));
    }
    
    
    
    //TC728
    @Test(priority=848)
    public void editUserNameForBlog() throws Exception{
    	 navigate2CommunityPage();
    	Assert.assertTrue( verifyUserProfileForBlog(addPostTitle_text));
    }
    
    //TC737
    @Test(priority=849)
    public void verifyReportLinkForBlog() throws Exception{
    	 navigate2CommunityPage();
    	Assert.assertFalse( verifyReportLinkForSelfBlog(addPostTitle_text));
    }
    
  
    //TC747
    @Test(priority=856)
    public void verifyViewAllFeaturedPhotos() throws Exception{
	   	 navigate2CommunityPage();
	   	 verifyWidgetPresent(widgetFeaturedPhotos); 
	   	Assert.assertTrue( verifyViewAllLinkFeaturedPhotos());
    }
   //TC749
   @Test(priority=857)
   public void verifyViewAllFeaturedVideos() throws Exception{
	    navigate2CommunityPage();
	    verifyWidgetPresent(widgetFeaturedVideos); 
	   Assert.assertTrue( verifyViewAllLinkFeaturedVideos());
   }

   //Bug has been logged for this PYR-15248
   //TC751
   @Test(priority=858)
   public void verifyViewAllFeaturedBlogs() throws Exception{
	   	 navigate2CommunityPage();
	   	 verifyWidgetPresent(widgetFeaturedBlogs); 
	   	Assert.assertTrue( verifyViewAllLinkFeaturedBlogs());
   }
   
   //TC753
   @Test(priority=859)
   public void verifyPhotosByTagName() throws Exception{
	     addCommunityPhoto(photoTitle_text);		
		boolean isPostFound =  verifyPostIsPresent(photoTitle_text); 
		if(isPostFound==true){
			 featurCommunityPost(photoTitle_text);
			 verifyFeaturedPhotos(photoTitle_text); 
			Assert.assertTrue( verifyFeaturePhotosByTagName(photoTitle_text,photoTags_text));
		}
	   
   }
   
   //TC755
   @Test(priority=860)
   public void verifyVideosByTagName() throws Exception{
	     addCommunityVideo(videoTitle_text);		
		boolean isPostFound =  verifyPostIsPresent(videoTitle_text); 
		if(isPostFound==true){
			 featurCommunityPost(videoTitle_text); 
			 verifyFeaturedVideos(videoTitle_text); 
			Assert.assertTrue( verifyFeatureVideosByTagName(videoTitle_text,inputVideoTags_text));
		}
	   
   }
         
   //TC765
   @Test(priority=861)
   public void verifyViewAllForPhotos() throws Exception{
	   	 navigate2MyProfilePage();
	   	Assert.assertTrue( verifyViewAllPhotos());
   }
   
   //TC766
   @Test(priority=862)
   public void verifyViewAllForVideos() throws Exception{
	   	 navigate2MyProfilePage();
	   	Assert.assertTrue( verifyViewAllVideos());
   }
   
   
   @Test(priority=861)	
   public void comm_ShareVideoInTwitter() throws Exception{	
        
   	logInfo("TC703 - Verify share video on Twitter.");
   	addCommunityVideo(videoText);    	
   	navigate2CommunityPage(); 	
   	Thread.sleep(5000);	
   	sharethePost(videoText);    
   	shop.selectTwitterInIcon();    	
   	shop.shareInTwitter();
   	 closeSocialList();
   	 profile.login2Twitter() ;
   	 profile.verifyPostsInTwitter(videoText);
   	// lc.login();  
   	 logIn(adminUser_text,adminPwd_text);
  }
   

   @Test(priority=862)	
   public void comm_ShareVideoInFB() throws Exception{	    
		logInfo("TC643 - Verify posted status share on Facebook.");	   
		sharethePost(videoText);   
		shop.selectFacebookIcon();	
	    shop.shareInFaceBook();	
		profile.close();
		profile.login2FBVerifyPostedDetails();  
		profile.getPostsFromFB(videoText) ; 	
		logIn(adminUser_text,adminPwd_text);   
	}
      
 
   @Test(priority=863)	
   public void comm_ShareVedioInLinkedIn() throws Exception{    
	logInfo("Share the Photo in LinkedIn");     
	navigate2CommunityPage(); 
	for (int i=0; i<=2;i++){
		 clickOnLink("cssSelector",vieMor );
		 Thread.sleep(2000);
		 }	
	Thread.sleep(5000);	
	clickOnLink("linkText", videoText);	
	shop.selectLinkedInIcon();
	shop.shareInLinkedIn();
	profile.close();
   profile.login2LinkedIn() ;
   profile.verifyPostInLinkedAccount(videoText);
   logIn(adminUser_text,adminPwd_text);    
 
	
          }
   
   
   @Test(priority=864)	
   public void comm_ShareVideoByEmailWithNdWithoutAttachment() throws Exception{	    
		logInfo(" - Verify share video using share by email");  
		//String selfmailId  = getEmail(adminUser_text, appUrl);
		
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
		System.out.println(selfmailId);
		
	    navigate2CommunityPage();
		Thread.sleep(5000);	
		clickOnLink("linkText", videoText);	
		shop.selectEmailIcon();	
		shareByEmail(selfmailId,subText );
		//confirmationMessage("Your vedio has been sent to "+mailId);
		/*confirmationMessage("Message sent to: "+mailId); */
		profile.close();
		driver().navigate().refresh();
		clickOnLink("linkText", videoText);	
		shop.selectEmailIcon();	
		emailWithAttachment(selfmailId,subText2 );		
		profile.close();		
		inbox.go2Inbox();
		inbox.selectVibeMailsWithSubject(subText);
		inbox.deleteFilteredVibeMails();	
		inbox.selectVibeMailsWithSubject(subText2);		
		inbox.deleteFilteredVibeMails();		

   }  
   
   
   @Test(priority=865)	
   public void comm_ShareVideoInPWP() throws Exception{	
   	logInfo("TC705 - Verify Share photo/video to PWP");    

   	navigate2CommunityPage(); 	
   	Thread.sleep(5000);	
   	sharethePost(videoText);    
   	shop.selectPWPInIcon(); 
   	confirmationMessage("Your video has been shared to your personal website.");
   	closeSocialList();
   	verifyInPWPList(videoText);
   	navigate2CommunityPage(); 	
   }
   
   
  
   @Test(priority=866)
   public void comm_SharePostInTwitter() throws Exception{	       
   	logInfo("TC703 - Verify share post on Twitter.");   
   	navigate2CommunityPage(); 
   	postStatus(communityStatus_text);
   	selectShare(communityStatus_text);	  
   	shop.selectTwitterInIcon();    	
   	shop.shareInTwitter();
   	closeSocialList();
   	profile.login2Twitter() ;
   	profile.verifyPostsInTwitter(communityStatus_text);
   	logIn(adminUser_text,adminPwd_text);   
   	 
   }
   


@Test(priority=867)	
   public void comm_SharePostInLinkedIn() throws Exception{    
	logInfo("Share the Photo in LinkedIn"); 
	
	System.out.println(communityStatus_text);
	 navigate2CommunityPage(); 
	 for (int i=0; i<=2;i++){
		 clickOnLink("cssSelector",vieMor );
		 Thread.sleep(2000);
		 }
	selectShare(communityStatus_text);		
	shop.selectLinkedInIcon();
	shop.shareInLinkedIn();
	profile.close();
	profile.login2LinkedIn() ;
	profile.verifyPostInLinkedAccount(communityStatus_text);
	logIn(adminUser_text,adminPwd_text);  
	
          }   
@Test(priority=868)	
public void comm_SharePostInFaceBook() throws Exception{	    
		logInfo("TC643 - Verify posted status share on Facebook.");
		
		navigate2CommunityPage(); 	
		for (int i=0; i<=2;i++){
			 clickOnLink("cssSelector",vieMor );
			 Thread.sleep(2000);
			 }	
		selectShare(communityStatus_text);			
		shop.selectFacebookIcon();		
		shop.shareInFaceBook();			
		profile.close();
		profile.login2FBVerifyPostedDetails();  
		profile.getPostsFromFBFrom2ndPart(communityStatus_text) ;           
		 logIn(adminUser_text,adminPwd_text);		
	}

	//Verify share video using share by email
   @Test(priority=869)	
   public void comm_SharePostByEmail() throws Exception{	    
		logInfo("Verify share video using share by email"); 	
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
		System.out.println(selfmailId);
		//String selfmailId = getEmail(adminUser_text, appUrl);
		navigate2CommunityPage(); 			
		selectShare(communityStatus_text);	
		shop.selectEmailIcon();	
		
		shareByEmail(selfmailId,subText );
		//confirmationMessage("Your vedio has been sent to "+mailId);
		confirmationMessage("Message sent to: "+mailId); 

		profile.close();				
		inbox.go2Inbox();
		inbox.selectVibeMailsWithSubject(subText);
		inbox.deleteFilteredVibeMails();	
   }  
   
   
   
   
   
   @Test(priority=870)	
   public void comm_SharePostInPWP() throws Exception{	
	  	logInfo("TC705 - Verify Share photo/video to PWP");
	   	navigate2CommunityPage(); 			
	   	selectShare(communityStatus_text);	  
	   	shop.selectPWPInIcon(); 
	   	confirmationMessage("Shared.");
	   	closeSocialList();
	   	verifyInPWPList(communityStatus_text);
	   	navigate2CommunityPage(); 	
   }
 
   
   @Test(priority=871)	
   public void comm_ValidatePostByEmail() throws Exception{	    
		logInfo("TC3209 - Validate Post while sending a mail"); 
		String Attach1 = "Attach To Message" ;
    	String Attach2 = "Insert Into Message Editor" ;
    	String Attach3 = "close" ; 
//    	String selfmailId = getEmail(adminUser_text, appUrl);
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
		System.out.println(selfmailId);
		navigate2CommunityPage(); 			
		selectShare(communityStatus_text);	
		shop.selectEmailIcon();	
		sendMailWithoutRecipient();
		recepAndSubject(selfmailId, subText);    	
    	clickOnButton("cssSelector", attach);
    	Thread.sleep(2000);	  
    	uploadFooter(Attach2);  
    	String alrtMsg = driver().findElement(By.cssSelector(alertMsg)).getText();  
    	System.out.println(alrtMsg);
    	try{    		
    	Assert.assertEquals(alrtMsg, alertAttachMsgText);
    	}catch (Exception e ){
    		logger.error("Failed!! Alert message is not displayed as per expected." );
    	}
    	clickOnButton("cssSelector", alrtOk); 
    	Thread.sleep(2000);	  
    	uploadFooter(Attach2);  
    	try{    		
        	Assert.assertEquals(alrtMsg, alertAttachMsgText);
        	}catch (Exception e ){
        		logger.error("Failed!! Alert message is not displayed as per expected." );
        	}
    		Thread.sleep(3000);
        	clickOnButton("cssSelector", alrtOk); 
        	uploadFooter(Attach3);   
        	Thread.sleep(2000);
		clickOnButton("cssSelector", send_Mail);  		
		shareByEmail(selfmailId,subText );		
		confirmationMessage("Message sent to: "+mailId); 
		profile.close();				
		inbox.go2Inbox();
		inbox.selectVibeMailsWithSubject(subText);
		inbox.deleteFilteredVibeMails();
   }  
   
   
   
   @Test(priority=872)	
   public void comm_SharePhotoByResource() throws Exception{	    
		logInfo("TC3216 -Verify share photo using share by email with resource"); 		
		profile.navigateMyProfileAccount("Notifications");
		String selfmailId = driver().findElement(By.cssSelector(mailId)).getText();
		System.out.println(selfmailId);
	    navigate2CommunityPage();
		Thread.sleep(5000);		
		addCommunityPhoto(inputPhotoTitle_text2);	
		navigate2CommunityPage();
		clickOnLink("linkText", inputPhotoTitle_text2);	
		shop.selectEmailIcon();				
		emailWithResourceAttachment(selfmailId,subText2,"CorporateImage2787");		
		profile.close();		
   }  
   
   
   @Test(priority=826)	
   public void comm_ValidateGoalInCommunity() throws Exception{	   	 
   	logInfo("TC3393 - Verify 'posted goal visible to community.");      		
 	createPhoto(commphoto, visibility_text_community);
   	isActivityPresentInCommunity(commphoto);
   	isActivitypresentInMyRecentActivities(commphoto);  		 
   	  	
   }
   
   @Test(priority=827)	
   public void comm_ValidatePrivateGoal() throws Exception{    	  
   	logInfo("TC3393 - Verify 'posted goal' visible to Private.");      		
    createPhoto(commPrivatePhoto, visibility_text_private);
   	isActivityNotToPresentInCommunity(commPrivatePhoto);
   	isActivitypresentInMyRecentActivities(commPrivatePhoto);  		 
   	  	
   }  
   
   
   //Monat
   
   @Test(priority=806)
	public void monatComm_AddPhotoWithDistributorCredentials() throws Exception{
		 
		logInfo("Login with distributor credentials and add photo to community .");	
		news.loginAsUser(prop.getLocatorForEnvironment(appUrl,"loginUser"));
		//userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));	
		profile.addPhotoFromMyProfile(inputPhotoTitle_text2);	
		//adminLogin();
		
	
	}
	
	
	@Test (priority=807,dependsOnMethods = {"monatComm_AddPhotoWithDistributorCredentials"})
	public void monatAbuseReport_HandleAbuseReportAndDelete() throws Exception{		 
		logInfo("Abuse Report on photo, Naviagte to Reported Abuse and delete the Reported Photo from Vibe");	
		//String inputPhotoTitle_text2 = "flowers 137";
		try{
			
			adminLogin();
			navigate2CommunityPage();
			reportThePosted(inputPhotoTitle_text2);
			enterReportAndSubmit();	
		    confirmationMessage("Report has been recorded");
			navigate2ReportedAbuseUnderDistManager();
		    selectAbusedFromPendingAction(inputPhotoTitle_text2);
			isAbuseReportnotTobePresent(inputPhotoTitle_text2);
			back2Office();

		}catch (Exception e ){
			logger.error("Failed !! Sorry Something went wrong while deleting Abused Report");
		}
	}
   

    
    
	
}
