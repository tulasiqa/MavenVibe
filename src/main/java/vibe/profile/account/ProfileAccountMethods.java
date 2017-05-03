package vibe.profile.account;

import org.openqa.selenium.WebElement;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import common.TestBase;

public class ProfileAccountMethods extends TestBase {
	
	

	   // Change cover page in community profile.
	
		public void go2CommunityProfilePage(){
			logInfo("inside go2CommunityProfilePage() method.");
			driver().navigate().to(appUrl + "community/profile");
		}
		
		public void changeCommunityProfilePhoto() throws Exception{
			logInfo("inside changeCommunityProfilePhoto() method.");
			clickOnLink("linkText","Change Profile Photo");
			waitOnElement("cssSelector", btnCommunityProfileBrowse);
			uploadFile("Image", btnCommunityProfileBrowse);
			//Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Image.exe");
			clickOnElement("xpath",btnBrowseFileSave);
			clickOnElement("cssSelector",btnBrowseProfileSubmit);
		}
		
		public void updateCommProfileInfo() throws Exception{
			logInfo("inside updateCommProfileInfo() method.");
			System.out.println("inside updateCommProfileInfo() method.");
			WebElement eleProfileTable = driver().findElement(org.openqa.selenium.By.xpath(profileTable));
			List<WebElement> rows_table = eleProfileTable.findElements(By.tagName("tr"));
			int rc = rows_table.size();
			System.out.println("count of rows = " +rc);
			
			List <WebElement> eleProfileTableCol = driver().findElements(org.openqa.selenium.By.xpath(profileTableCol));
			int cc = eleProfileTableCol.size();
			System.out.println("count of columns = " +cc);
			
			
			// edit the First Name in the table and save it.
			
			WebElement eCommunityFNameEdit = driver().findElement(By.xpath(btnCommunityFNameEdit));
			eCommunityFNameEdit.click();
			Thread.sleep(3000);
			waitOnElement("cssSelector",inputCommunityFName);
			inputTextClear("cssSelector",inputCommunityFName);
			inputText("cssSelector",inputCommunityFName,communityFName_text);
			clickOnButton("xpath",btnCommunityFNameSave);
			Thread.sleep(3000);
			waitOnElement("xpath",btnEditPhone);
			clickOnElement("xpath",btnEditPhone);
			waitOnElement("xpath",inputCommPhone);
			Thread.sleep(3000);
			inputTextClear("xpath",inputCommPhone);
			inputText("xpath",inputCommPhone,"12345");
			clickOnElement("xpath",btnCommunityPhoneSave);
			
			Thread.sleep(5000);
			
			// set the radio buttons
			
			selectRadioOrCheckbox("xpath", radioCommunityProfileEmail);
			selectRadioOrCheckbox("xpath", radioCommunityProfilePhone);
			selectRadioOrCheckbox("xpath", radioCommunityProfileFax);
			selectRadioOrCheckbox("xpath", radioCommunityProfileCell);
			
			// read all the data in the web table.
				
			for(int i=1; i<=rc-1;i++){
				for (int j=1;j<=1;j++){
				
					WebElement e = driver().findElement(By.xpath(p1+i+p2+j+p3));
					
					// System.out.println(e.getText());
					String getValue = e.getText().trim();
					
					switch (getValue){
					case "First Name":
						System.out.println("match found : " + getValue);
						break;
					case "Last Name":
						System.out.println("match found : " + getValue);
						break;
					case "Email":
						System.out.println("match found : " + getValue);
						break;
					case "Phone":
						System.out.println("match found : " + getValue);
						break;
					case "Fax":
						System.out.println("match found : " + getValue);
						break;
					case "Cell":
						System.out.println("match found : " + getValue);
						break;
					case "Birth Date":
						System.out.println("match found : " + getValue);
						break;
					case "Address":
						System.out.println("match found : " + getValue);
						break;
					case "My Story":
						System.out.println("match found : " + getValue);
						break;
					case "Reset":
						System.out.println("match found : " + getValue);
						break;	
					default:
						System.out.println("incorrect match found : " + getValue);
						break;
						
					}
				 }
			 }
				Thread.sleep(3000);
				waitOnElement("xpath",inputProfileMyStory);
				inputTextClear("xpath",inputProfileMyStory);
				inputText("xpath",inputProfileMyStory,profileMyStory_text);
				Thread.sleep(3000);
				waitOnElement("cssSelector",inputCommunityFbook);
			    inputTextClear("cssSelector",inputCommunityFbook);
				inputText("cssSelector",inputCommunityFbook,communityFbook_text);
				Thread.sleep(3000);
				waitOnElement("cssSelector",inputCommunityTwittwer);
				inputTextClear("cssSelector",inputCommunityTwittwer);
				inputText("cssSelector",inputCommunityTwittwer,communityTwittwer_text);
				Thread.sleep(3000);
				waitOnElement("xpath",btnCommunityUpdate);
				clickOnElement("xpath",btnCommunityUpdate);
				confirmationMessage("Account updated");
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
