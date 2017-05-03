package vibe.profile.account;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import common.TestBase;

public class ProfileMethods extends TestBase {
	
	

	// Change cover page in community profile.
	
		public void go2CommunityProfilePage(){
			logInfo("inside go2CommunityProfilePage() method.");
			driver().navigate().to(appUrl + "community/profile");
		}
		
		public void changeCommunityProfilePhoto() throws Exception{
			logInfo("inside changeCommunityProfilePhoto() method.");
			clickOnLink("linkText","Change Profile Photo");
			waitOnElement("cssSelector", btnCommunityProfileBrowse);
		//	Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Image.exe");
			 uploadFile("Image", btnCommunityProfileBrowse);
			clickOnElement("xpath",btnBrowseFileSave);
			clickOnElement("cssSelector",btnBrowseProfileSubmit);
		}
	
		
		public void verifyProfilePage(String expImg){
			logInfo("inside verifyProfilePage() method.");
			WebElement x = driver().findElement(By.xpath(imgProfile)); 		// imgProfile
			String imgText = x.getAttribute("alt").trim();
			System.out.println(imgText);
			boolean isProfilePhotoChanged = false;
			if(imgText.equalsIgnoreCase(imgProfile_text)){
				isProfilePhotoChanged = true;
				System.out.println("profile photo changed sucessfully.");
			}
			
			if(isProfilePhotoChanged==false){
				Assert.assertTrue(isProfilePhotoChanged, "profile photo could not be changed.");
			}
		}
	
		
		

}
