package vibe.profile.account;

import org.testng.annotations.Test;

import common.TestBase;

public class Profile_Tests extends ProfileMethods {

	@Test(priority=1)
	public void changeProfilePhoto() throws Exception{
		go2CommunityProfilePage();
		changeCommunityProfilePhoto();
		verifyProfilePage(imgProfile_text);
	}

}
