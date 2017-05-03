package vibe.myprofile.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import common.Priority;
import common.TestBase;
import common.readProp;
import vibe.mycommunity.tests.CommunityMethods;
import vibe.shopping.tests.ShoppingMethods;

@Priority(22)

public class MyProfileTests extends MyProfileMethods{
	
	ShoppingMethods shop= new ShoppingMethods();
	CommunityMethods comm = new CommunityMethods();		
	readProp prop = new readProp();
	String postText1 =" my post no is " +generateRandomNumberInRange(7, 777);
	
	     
		@Test(priority= 2200)
		public void loginAsUserForMyProfile() throws Exception{
			
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
	                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			
		}

		@Test(priority= 2201)
		public void profile_AddBlogPost() throws Exception{
			logInfo("inside profile_AddBlogPost() Test");	
			navigate2MyProfilePage();
			comm.addBlogPost(addPostTitle_text,"COMMUNITY",true);		
			boolean isBlogPostFound = comm.verifyPostIsPresent(addPostTitle_text);
			if(isBlogPostFound==true){
				logInfo(addPostTitle_text + " blog found in community wall page.");
				comm.deleteCommunityPost(addPostTitle_text);
			} else {
				logInfo(addPostTitle_text + " blog not found in community wall page.");
				Assert.assertTrue(isBlogPostFound, addPostTitle_text + " blog not found in community wall page.");
			}
		}
		
		@Test(priority= 2202)
		public void profile_AddPhoto() throws Exception{
			logInfo("inside profile_AddPhoto() Test");	
			navigate2MyProfilePage();
			comm.addCommunityPhoto(photoTitle_text, visibilitySettings_text);
			boolean isPhotoFound = comm.verifyPostIsPresent(photoTitle_text); 
			if(isPhotoFound==true){
				logInfo(photoTitle_text + " photo found in community wall page.");
				comm.deleteCommunityPost(photoTitle_text);
			} else {
				logInfo(photoTitle_text + " photo not found in community wall page.");
				Assert.assertTrue(isPhotoFound, photoTitle_text + " photo not found in community wall page.");
			}
		}

		
		@Test(priority= 2203)
		public void profile_AddVideo() throws Exception{
				logInfo("inside profile_AddVideo() Test");	
				navigate2MyProfilePage();
				comm.addCommunityVideo(videoTitle_text, visibilitySettings_text);
				boolean isVideoFound = comm.verifyPostIsPresent(videoTitle_text); 
				if(isVideoFound==true){
					logInfo(videoTitle_text + " video found in community wall page.");
					comm.deleteCommunityPost(videoTitle_text);
				} else {
					logInfo(videoTitle_text + " video not found in community wall page.");
					Assert.assertTrue(isVideoFound, videoTitle_text + " video not found in community wall page.");
				}
		  }
		

		@Test(priority= 2204)
		public void profile_ChangeProfilePhoto() throws Exception{
				logInfo("inside profile_ChangeProfilePhoto() Test");	
				navigate2MyProfilePage();
				String actSrc = getSrcForImage("//div[starts-with(@class,'profile-picture')]/img");
				System.out.println("actSrc =" +actSrc);
				changeProfileAvatar();
				confirmationMessage("Picture updated");
				navigate2MyProfilePage();
				String actSrc1 = getSrcForImage("//div[starts-with(@class,'profile-picture')]/img");
				System.out.println("actSrc1 =" +actSrc1);
				if(!actSrc.equalsIgnoreCase(actSrc1)){
					Assert.assertFalse(true, "Unable to change the profile photo.");
				}
				
		  }
		
		
		@Test(priority= 2205)
		public void profile_ChangeCoverPhoto() throws Exception{
				logInfo("inside profile_ChangeCoverPhoto() Test");	
				navigate2MyProfilePage();
				changeCoverPhoto();
				confirmationMessage("Profile cover updated");
		  }
		
		
		
		@Test(priority=2206)
		public void DeletePhoto() throws Exception {
			logInfo("inside DeletePhoto Test");
			navigate2MyProfilePage();
			verifyAndDeletePhotoInMyProfilePage("Small");
			
			}

	@Test(priority= 2201)
	public void profile_verifyAddedBlogPost() throws Exception{
		logInfo("inside profile_AddPost() test");	
		navigate2MyProfilePage();
		go2AddBlogPost();
		comm.addBlogPost(myPostTitle,"COMMUNITY",true);
	    verifyMyRecentActivity(myPostTitle);	    
	}
	
	@Test(priority= 2201)
	public void profile_verifyEditedBlogPost() throws Exception{
		logInfo("inside profile_AddPost() test");	
		updateBlogInProfile(myPostTitle, postTitleUpdated);
		navigate2MyProfilePage();
	    verifyMyRecentActivity(postTitleUpdated);
	    deleteBlogPostFromProfile(postTitleUpdated);
	    confirmationMessage("Post was successfully deleted.");
	}
	  
	
	@Test(priority= 2202)
	public void profile_AddEditNdDeletePhoto() throws Exception{
		logInfo("Entered into profile_verifyAddedPhoto() test");
		navigate2MyProfilePage();
		comm.addCommunityPhoto(myProfPhotoTitle, visibilitySettings_text);
		navigate2MyProfilePage();
		verifyMyRecentActivity(myProfPhotoTitle);
		editPhotoToUpdate(myProfPhotoTitle, titleUpdated);
		navigate2MyProfilePage();
		verifyMyRecentActivity(titleUpdated);
		comm.deleteCommunityPost(titleUpdated);
		confirmationMessage("Photo is deleted");
		navigate2MyProfilePage();
		verifyPostNotPresentInMyRecentActivity(titleUpdated);	
	}	
	
	@Test(priority= 2203)
	public void profile_AddEditNdDeleteVideo() throws Exception{
		logInfo("Entered into profile_AddEditNdDeleteVedio() test");
		navigate2MyProfilePage();
		comm.addCommunityVideo(videoTitle);
		navigate2MyProfilePage();
		verifyMyRecentActivity("A Video From YouTube");
		deleteVideoInProfile(videoTitle);			
		navigate2MyProfilePage();
		verifyPostNotPresentInMyRecentActivity(videoTitle);	
	}	
	
	@Test(priority= 2203)
	public void profile_DeleteAllVideos() throws Exception{
		logInfo("Entered into profile_AddEditNdDeleteVedio() test");
		navigate2MyProfilePage();		
		deleteAllVideosInProfile();			
		
	}

	@Test(priority=2204)
	public void profile_ChangePhotoAndVideoThenDeleteBoth() throws Exception {
		 
		logInfo("Verify all Account links and change the cover Photo \n"
				+ "Delete already added photo and video (tear down method)");
		editProfileAndVerifyAccountLinks();
		/*profile.changeCoverPhotoAndProfile();*/
	    navigate2MyProfilePage();		
		deletePhoto();
		deleteVideo();		
		
		}
	
	@Test(priority= 2205)
	public void profile_ShareVideoInFB() throws Exception{
		logInfo("Share a video in FaceBook. ");
		selectVideoInMyProfile();
		shop.selectFacebookIcon();		
		shop.shareInFaceBook();		
		delete();
		confirmationMessage("Video is deleted");
		
	}
	
	
	@Test(priority= 2206)
	public void profile_EditPhoto() throws Exception{
		logInfo("TC_686:Verify Edit photo. ");
		navigate2MyProfilePage();
		addPhotoForMyProfile(inputPhotoDesc_text);
		editPhoto();
		confirmationMessage("Your photo has been updated."); 
	}
	
	@Test(priority= 2207)
	public void profile_EditVideo() throws Exception{
		logInfo("TC_687:Verify edit video.");
		navigate2MyProfilePage();
		addVideoForMyProfile(inputVideoTitle_text);
		editVideo();
		confirmationMessage("Updated."); 
	}
	
	 @Test (priority=2208)
	    public void profile_DeleteAllPhotos() throws Exception{
	    	logInfo("Delete all Photos from My Profile");
			navigate2MyProfilePage();
			clickPhotoLink();			
			deletionAll();	   
			
	    }
		
	 @Test (priority=2209)
	    public void userlogoutFromMyProfile() throws Exception{
	    	logInfo("logoutFromMyProfile test");
			navigate2MyProfilePage();
			clickPhotoLink();			
			deletionAll();	   
			userLogout();
	    }
	 
	 
	  //TC741
	    @Test(priority=850)
	    public void verifyChangeProfilePhoto() throws Exception{
	    	navigate2MyProfilePage();
	    	Assert.assertTrue( addProfilePhoto());
	    }
	    
	    //TC742
	    @Test(priority=851)
	    public void verifyChangeCoverPhoto() throws Exception{
	    	navigate2MyProfilePage();
	    	Assert.assertTrue( addCoverPhoto());
	    }
	    
	    //TC743
	    @Test(priority=852)
	    public void verifyRemoveProfileImage() throws Exception{
	    	navigate2MyProfilePage();
	    	Assert.assertTrue( removeProfileImage());
	    	 addProfilePhoto();
	    }
	    
	    //TC744
	    @Test(priority=853)
	    public void verifyRemoveCoverPhoto() throws Exception{
	    	navigate2MyProfilePage();
	    	Assert.assertTrue( removeCoverPhoto());
	    	 addCoverPhoto();
	    }
	    
	    //TC745
	    @Test(priority=854)
	    public void validateInvalidCoverPhoto() throws Exception{
	    	navigate2MyProfilePage();
	    	Assert.assertTrue( validateCoverPhoto());
	    }

	    //TC746
	    @Test(priority=855)
	    public void validateInvalidProfilePhoto() throws Exception{
	    	navigate2MyProfilePage();
	    	Assert.assertTrue( validateProfilePhoto());
	    }


}
