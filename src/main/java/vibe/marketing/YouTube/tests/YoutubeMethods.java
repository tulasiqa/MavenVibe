package vibe.marketing.YouTube.tests;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

import common.TestBase;

import vibe.inbox.tests.InboxMethods;
import vibe.marketing.companyNews.tests.NewsMethods;
import vibe.tasks.tests.TaskMethods;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.openqa.selenium.By;

public class YoutubeMethods extends TestBase{
	
	public void nav2YouTube() throws Exception{
	  logInfo("Navigate Marketing >> Upload Videos to Youtubes.");
  	  try{
			driver().navigate().to(appUrl + "community/videos/youtube_videos");
			Thread.sleep(5000);
	  }catch(Exception e){
			logger.error("Failed!! Not able to naviagte to Youtube Screen");	
	   }	
	}
	
	//Creates new video.
	public void addNewVideo(String title, String description ) throws Exception{
		logInfo("Entered into addNewVideo(String title) method");	  	  
	  	clickOnButton("cssSelector", addUtube); 
	  	inputTextClear("cssSelector", UTitle);
	  	inputTextClear("cssSelector", UDesc);
	  	inputText("cssSelector", UTitle, title);	  	
	  	inputText("cssSelector", UDesc, description);
	  	clickOnButton("cssSelector", UContinue);
	  	Thread.sleep(2000);
	  	waitOnElement("cssSelector", btnUploadYoutubeVideo);
	  	 uploadFile("Video", btnUploadYoutubeVideo);
	  /*	Thread.sleep(6000);
		Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Video.exe");
		Thread.sleep(10000);*/
		clickOnButton("cssSelector", btnUploadVideo);
	}
	
	public void temp() throws  InterruptedException{
		Thread.sleep(5000);
		driver().navigate().back();
		Thread.sleep(5000);
		//clickOnLink("linkText", "Back");
	}
	
	// verifies the youtube with title and aslo description
	public void verifyYouTube(String title, String description){
		boolean ispresent = false;
		List <WebElement> ls = driver().findElements(By.cssSelector(tubeList));		
		for (int i= 2; i<=ls.size()+1; i++){
			WebElement titles = driver().findElement(By.cssSelector(tubeListBfr+i+tubeListAfr));						
				if (titles.getText().equalsIgnoreCase(title)){
					ispresent=true;
					WebElement desc = driver().findElement(By.cssSelector(tubeListBfr+i+tubedescAfr));
					String actualDesc = desc.getText();
					Assert.assertEquals(actualDesc, description);					
					break;		
				}			
		}if (ispresent==false){
			Assert.assertTrue(ispresent, title + "is not present");
		}
	}
	
	// verifies the youtube with title and aslo description
		public void verifyNotToPresentUTube(String title){
			boolean ispresent = true;
			List <WebElement> ls = driver().findElements(By.cssSelector(tubeList));		
			for (int i= 2; i<=ls.size()+1; i++){
				WebElement titles = driver().findElement(By.cssSelector(tubeListBfr+i+tubeListAfr));								
					if (titles.getText().equalsIgnoreCase(title)){
						ispresent=false;
						Assert.assertTrue(ispresent, title + "is still present");										
						break;		
					}			
			}if (ispresent==true){
				System.out.println("Suceess !! "+title +" is not present");
			}
		}
	
	
	public void backToOffice() throws  Exception{
		Thread.sleep(5000);
		clickOnLink("linkText", "back to office");
	}
	
	
	public void editYouTube(String title) throws Exception{
		boolean ispresent = false;
		List <WebElement> ls = driver().findElements(By.cssSelector(tubeList));		
		for (int i= 2; i<=ls.size()+1; i++){
			WebElement titles = driver().findElement(By.cssSelector(tubeListBfr+i+tubeListAfr));					
				if (titles.getText().equalsIgnoreCase(title)){
					ispresent=true;
					WebElement desc = driver().findElement(By.cssSelector(tubeListBfr+i+tubeEditAfr));
					desc.click();	
					Thread.sleep(3000);
					break;		
				}			
		}if (ispresent==false){
			Assert.assertTrue(ispresent, title + "is not present");
		}
	}
	
	public void updateyoutube(String title, String updatedTitle) throws Exception{
		editYouTube(title);		
		inputTextClear("cssSelector", UTitle);
	  	inputText("cssSelector", UTitle, updatedTitle);
	  	clickOnButton("cssSelector", Usave);	  	
	  	confirmationMessage("Video was successfully updated");
	  	Thread.sleep(3000);
	  	WebElement uploadTitle = driver().findElement(By.cssSelector(tubeListTitle));
	  	Assert.assertEquals(uploadTitle.getText(), tubeListTitleText);		
	}
	
	
	
	
	public void deleteYouTubeVedio(String title) throws Exception{
		boolean ispresent = false;
		driver().navigate().refresh();
		List <WebElement> ls = driver().findElements(By.cssSelector(tubeList));
		System.out.println("size is "+ls.size());
		for (int i= 2; i<=ls.size()+1; i++){
			WebElement titles = driver().findElement(By.cssSelector(tubeListBfr+i+tubeListAfr));
			System.out.println("List "+titles.getText());
				if (titles.getText().equalsIgnoreCase(title)){
					ispresent=true;
					WebElement dele = driver().findElement(By.cssSelector(tubeListBfr+i+tubeDelAfr));
					dele.click();	
					Thread.sleep(3000);
					confirmOK();
					confirmationMessage("Video was successfully deleted");	
					Thread.sleep(3000);
					break;		
				}			
		}if (ispresent==false){
			Assert.assertTrue(ispresent, title + "is not present");
		}
	}
	
	// deletes all youtube list
	public void deleteAllYouTubeVedios() throws Exception{	
		logInfo("Entered into deleteAllYouTubeVedios method");
		driver().navigate().refresh();
		waitOnElement("cssSelector", tubeList);
		List <WebElement> ls = driver().findElements(By.cssSelector(tubeList));
		System.out.println("size is "+ls.size());
		for (int i= 2; i<=ls.size()+1; i++){
		//for (int i=ls.size()+1; i<=2; i--){
			WebElement titles = driver().findElement(By.cssSelector(tubeListBfr+i+tubeListAfr));
			System.out.println("List "+titles.getText());				
					WebElement dele = driver().findElement(By.cssSelector(tubeListBfr+i+tubeDelAfr));
					dele.click();						
					confirmOK();
					confirmationMessage("Video was successfully deleted");	
					
		}
			
	}
		
		
}
