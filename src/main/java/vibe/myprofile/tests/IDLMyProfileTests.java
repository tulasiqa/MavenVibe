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

public class IDLMyProfileTests extends IDLMyProfileMethods{
	
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
	public void profile_verifyAddedBlogPost() throws Exception{
		logInfo("inside profile_AddPost() test");	
		navigate2MyProfilePage();
		go2AddBlogPost();
		addBlogPost(myPostTitle,"COMMUNITY",true);
	    verifyMyRecentActivity(myPostTitle);	    
	}
	
	@Test(priority= 2202)
	public void profile_verifyEditedBlogPost() throws Exception{
		logInfo("inside profile_AddPost() test");	
		updateBlogInProfile(myPostTitle, postTitleUpdated);
		navigate2MyProfilePage();
	    verifyMyRecentActivity(postTitleUpdated);
	    deleteBlogPostFromProfile(postTitleUpdated);
	    confirmationMessage("Post was successfully deleted.");
	}
	  
	
	@Test(priority= 2203)
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
	
	@Test(priority= 2204)
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
	
	@Test(priority= 2205)
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
	
	
	@Test(priority= 2206)
	public void profile_ChangeCoverPhoto() throws Exception{
		try{
			logInfo("inside profile_ChangeCoverPhoto() Test");	
			navigate2MyProfilePage();
			changeCoverPhoto();
			confirmationMessage("Profile cover updated");
			userLogout();
		}
		catch(Exception ex){
			userLogout();
		}
			
	  }

}
