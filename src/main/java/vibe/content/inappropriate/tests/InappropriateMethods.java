package vibe.content.inappropriate.tests;

import common.TestBase;
import vibe.contacts2.tests.BusinessContactsMethods;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class InappropriateMethods extends TestBase {
	
	BusinessContactsMethods bc = new BusinessContactsMethods();
	
	
	public void navigate2Inappropriate() throws Exception{	
		Thread.sleep(3000);
		driver().navigate().to(appUrl+ "pyr_core/badwords");	
		Thread.sleep(4000);
	}
	
	public void validateInappropriateObjects(){		
		WebElement tit = driver().findElement(By.cssSelector(inapTitle));
		Assert.assertEquals(tit.getText().trim(), inapTitleText);
		
		WebElement search = driver().findElement(By.cssSelector(inapsearchBtn));
		String actSearch = search.getAttribute("value");
		Assert.assertEquals(actSearch, "Search");
		
		WebElement searchAll = driver().findElement(By.cssSelector(inapSearchAllBtn));
		String actSearchAll = searchAll.getAttribute("value");
		Assert.assertEquals(actSearchAll, "Search All Words");
		
		WebElement addNew = driver().findElement(By.cssSelector(inapAddNew));
		String actaddNew = addNew.getAttribute("value");
		Assert.assertEquals(actaddNew, "Add New Word");
		
		
		List <WebElement> sec  = driver().findElements(By.cssSelector(inapSections));
		System.out.println(sec.size()+ "size is ");
		for (WebElement section : sec){
			String sectionText = section.getText().trim();
			switch (sectionText){
			case "Inappropriate Words" : 
				 System.out.println("Match found : "+sectionText);
				  break;
			case "Reserved Words For Site" : 
				 System.out.println("Match found : "+sectionText);
				  break;			
			default :
			 Assert.assertEquals(null, sectionText+ " is unnecessarily dispalyed.");			
			
			}		
		}	
	}
	
	
	public void validateAlertMessages() throws Exception{			
		clickOnButton("cssSelector", inapsearchBtn);
		confirmationMessage(alertSearchText);
		Thread.sleep(6000);
		clickOnButton("cssSelector", inapAddNew);
		confirmationMessage(alertAddNewText);
		Thread.sleep(6000);
		clickOnLink("linkText", inapDeletLnk);
		confirmationMessage(alertDeleteText);	
		Thread.sleep(6000);		
	}
	
	public void selectLanguages(String language) throws Exception{		
		selectFromDropdown("cssSelector", inapLang, "byVisibleText", language);
		clickOnElement ("cssSelector", "input.btn.btn-link");
		/*clickOnLink("linkText", "Filter");*/
		Thread.sleep(2000);
	}
	
	
	public void validateBadWordsSize() throws Exception{		
		selectLanguages("All");			
		List<WebElement> badSize = driver().findElements(By.cssSelector(inapWordListOption));
		System.out.println("All size is "+badSize.size());		
		Thread.sleep(5000);
		selectLanguages("English");		
		List<WebElement> engBadSize = driver().findElements(By.cssSelector(inapWordListOption));
		engBadSize.size();
		System.out.println(engBadSize.size());
		Assert.assertNotEquals(badSize.size(),engBadSize.size());		
	}
	
	
	public void validateWords(String badWord) throws Exception{
		inputTextClear("cssSelector", inapSearchField);
		inputText("cssSelector", inapSearchField, badWord);
		clickOnButton("cssSelector", inapAddNew);
		confirmationMessage(confText);
		Thread.sleep(10000);
		inputTextClear("cssSelector", inapSearchField);
		inputText("cssSelector", inapSearchField, badWord);
		clickOnButton("cssSelector", inapAddNew);
		confirmationMessage(confDupeText);
		isWordPresent("All", badWord);
		isWordPresent("English", badWord);
		isWordNotToPresent("Spanish", badWord);
		isWordNotToPresent("Afar", badWord);	
	}
	
	public void addNewBadWord(String badWord) throws Exception{
		inputTextClear("cssSelector", inapSearchField);
		inputText("cssSelector", inapSearchField, badWord);
		clickOnButton("cssSelector", inapAddNew);
		confirmationMessage(confText);
		Thread.sleep(10000);
	}
	
	public boolean isWordPresent(String language, String badWord) throws Exception{
		selectLanguages(language);
		boolean isWorded = false;
		List<WebElement> badSize = driver().findElements(By.cssSelector(inapWordListOption));
		System.out.println("All size is "+badSize.size());
		for (WebElement bads :badSize){
			if(bads.getText().equalsIgnoreCase(badWord)){
				isWorded =true;
				bads.click();
				break;
			}			
		}if (isWorded==false){			
			Assert.assertTrue(isWorded, badWord+ " - word is not present");
		}
		return isWorded;
		
	}
	
	public boolean isWordNotToPresent(String language, String badWord) throws Exception{
		selectLanguages(language);
		boolean isWorded = true;
		List<WebElement> badSize = driver().findElements(By.cssSelector(inapWordListOption));
		System.out.println("All size is "+badSize.size());
		if (badSize.size()==0){
			System.out.println("Successfuly no words present like -"+ badWord);
			Assert.assertNotEquals(null, badWord);
		}
		for (WebElement bads :badSize){
			if(bads.getText().equalsIgnoreCase(badWord)){
				isWorded =false;
				bads.click();
				Assert.assertTrue(isWorded, badWord+ " - word is still present for language - "+language);
				break;
			}			
		}if (isWorded==true){			
			Assert.assertNotEquals(isWorded, badWord);
		}
		return isWorded;
		
	}
	
	public void deleteWord(String language, String badWord) throws Exception{
		selectLanguages(language);
		boolean isWorded = false;
		List<WebElement> badSize = driver().findElements(By.cssSelector(inapWordListOption));
		System.out.println("All size is "+badSize.size());
		for (WebElement bads :badSize){
			if(bads.getText().equalsIgnoreCase(badWord)){
				isWorded =true;
				bads.click();
				Thread.sleep(2000);
				clickOnLink("linkText",inapDeletLnk );
				confirmationMessage(deleText);
				Thread.sleep(6000);
				break;
			}			
		}if (isWorded==false){			
			Assert.assertTrue(isWorded, badWord+ " - word is not present");
		}
		
	}
	
	
	public void verifyPostedBadWords(String inappropriateWord) throws Exception{		
		navigate2Inappropriate();
		clickOnButton("cssSelector", inapSearchAllBtn);
		Thread.sleep(5000);
		boolean iswordPresent = false;
		List <WebElement> word = driver().findElements(By.cssSelector(wordList));
		System.out.println("List size "+ word.size());
		for (int i=1; i<=word.size(); i++){
			WebElement badWord = driver().findElement(By.cssSelector(wordListBefore+i+wordListAfter));
			if(badWord.getText().trim().equalsIgnoreCase(inappropriateWord)){
				iswordPresent =true;				
				break;			
			}		
		}if (iswordPresent==false){
			Assert.assertTrue(iswordPresent, inappropriateWord + " - word is not posted." );			
		}	
	}
	
	public void verifyPostedNotToBePresent(String inappropriateWord) throws Exception{		
		navigate2Inappropriate();
		clickOnButton("cssSelector", inapSearchAllBtn);
		Thread.sleep(5000);
		boolean iswordPresent = true;
		List <WebElement> word = driver().findElements(By.cssSelector(wordList));
		System.out.println("List size "+ word.size());
		for (int i=1; i<=word.size(); i++){
			WebElement badWord = driver().findElement(By.cssSelector(wordListBefore+i+wordListAfter));
			if(badWord.getText().trim().equalsIgnoreCase(inappropriateWord)){
				iswordPresent =false;	
				Assert.assertTrue(iswordPresent, inappropriateWord + " - word is stillpresent." );	
				break;			
			}		
		}if (iswordPresent==true){
			System.out.println("Successfully!! Inappropriate word is not  found.");			
		}	
	}
	
	
	
	public void emailToUser(String inappropriateWord) throws Exception{			
		clickOnButton("cssSelector", inapSearchAllBtn);
		Thread.sleep(5000);		
		boolean iswordPresent = false;
		List <WebElement> word = driver().findElements(By.cssSelector(wordList));
		System.out.println("List size "+ word.size());
		for (int i=1; i<=word.size(); i++){
			WebElement badWord = driver().findElement(By.cssSelector(wordListBefore+i+wordListAfter));
			if(badWord.getText().trim().equalsIgnoreCase(inappropriateWord)){
				iswordPresent =true;
				WebElement email= driver().findElement(By.cssSelector(wordListBefore+i+emailAfter));
				email.click();
				Thread.sleep(3000);
				inputText("cssSelector", inapSubjeText, inappropriateWord);
				inputText("cssSelector", inapBody, badWordText2+" is a badword, so remove it");
				Thread.sleep(2000);
				clickOnElement("cssSelector", emailSubmit);
				confirmationMessage("Email sent to the user.");				
				break;			
			}		
		}if (iswordPresent==false){
			Assert.assertTrue(iswordPresent, inappropriateWord + " - word is not posted." );			
		}	
	}
	
	public void emailalerts(String inappropriateWord) throws Exception{			
		clickOnButton("cssSelector", inapSearchAllBtn);
		Thread.sleep(5000);		
		boolean iswordPresent = false;
		List <WebElement> word = driver().findElements(By.cssSelector(wordList));
		System.out.println("List size "+ word.size());
		for (int i=1; i<=word.size(); i++){
			WebElement badWord = driver().findElement(By.cssSelector(wordListBefore+i+wordListAfter));
			if(badWord.getText().trim().equalsIgnoreCase(inappropriateWord)){
				iswordPresent =true;
				WebElement email= driver().findElement(By.cssSelector(wordListBefore+i+emailAfter));
				email.click();
				Thread.sleep(3000);
				inputTextClear("cssSelector", inapSubjeText);
				inputTextClear("cssSelector", inapBody);				
				clickOnElement("cssSelector", emailSubmit);
				confirmationMessage("Subject cannot be blank.");	
				Thread.sleep(6000);
				inputText("cssSelector", inapSubjeText, inappropriateWord);
				clickOnElement("cssSelector", emailSubmit);
				confirmationMessage("Body cannot be blank.");	
				Thread.sleep(6000);				
				inputText("cssSelector", inapBody, badWordText2+" is a badword, so remove it");
				Thread.sleep(2000);
				clickOnElement("cssSelector", emailSubmit);
				confirmationMessage("Email sent to the user.");					
				break;			
			}		
		}if (iswordPresent==false){
			Assert.assertTrue(iswordPresent, inappropriateWord + " - word is not posted." );			
		}	
	}
	
	public void verificationEmailConfig(String inappropriateWord ) throws Exception{		
		clickOnLink("linkText", inapEmailLnk);
		Thread.sleep(3000);
		inputTextClear("cssSelector", inapSubjeText);
		inputTextClear("cssSelector", inapBody);		
		inputText("cssSelector", inapSubjeText, emailSubText);
		inputText("cssSelector", inapBody,emailBodyText);		
		Thread.sleep(3000);
		clickOnElement("cssSelector", emailConfigSubmit);
		confirmationMessage("Template updated successfully.");
		Thread.sleep(6000);
		
		
		clickOnButton("cssSelector", inapSearchAllBtn);
		Thread.sleep(5000);		
		boolean iswordPresent = false;
		List <WebElement> word = driver().findElements(By.cssSelector(wordList));
		System.out.println("List size "+ word.size());
		for (int i=1; i<=word.size(); i++){
			WebElement badWord = driver().findElement(By.cssSelector(wordListBefore+i+wordListAfter));
			if(badWord.getText().trim().equalsIgnoreCase(inappropriateWord)){
				iswordPresent =true;
				WebElement email= driver().findElement(By.cssSelector(wordListBefore+i+emailAfter));
				email.click();
				Thread.sleep(3000);				
				WebElement actSub = driver().findElement(By.cssSelector(inapSubjeText));
				String ActualSubject =actSub.getAttribute("value");
				Assert.assertEquals(ActualSubject, emailSubText);				
				WebElement actBody = driver().findElement(By.cssSelector(inapBody));
				String actualBody = actBody.getAttribute("value");
				Assert.assertEquals(actualBody, emailBodyText);
				Thread.sleep(2000);
				clickOnElement("cssSelector", emailSubmit);
				confirmationMessage("Email sent to the user.");					
				break;			
			}	
			
		}if (iswordPresent==false){
			Assert.assertTrue(iswordPresent, inappropriateWord + " - word is not posted." );			
		}		
	}
	
	public void selectURLLinkOfPosted(String inappropriateWord) throws Exception{			
		clickOnButton("cssSelector", inapSearchAllBtn);
		Thread.sleep(5000);		
		boolean iswordPresent = false;
		List <WebElement> word = driver().findElements(By.cssSelector(wordList));		
		for (int i=1; i<=word.size(); i++){
			WebElement badWord = driver().findElement(By.cssSelector(wordListBefore+i+wordListAfter));
			String badwordText = badWord.getText().trim();
			if(badwordText.equalsIgnoreCase(inappropriateWord)){
				iswordPresent =true;				
				WebElement email= driver().findElement(By.cssSelector(wordListBefore+i+UrlAfter));
				email.click();
				Thread.sleep(3000);						
				break;			
			}		
		}if (iswordPresent==false){
			Assert.assertTrue(iswordPresent, inappropriateWord + " - word is not posted." );			
		}	
	}
	
	public void updateBlogPost(String statusMessage) throws Exception{
		
		clickOnLink("linkText", "Edit");
		Thread.sleep(5000);
		
		inputTextClear("xpath", blogstatusTextArea);
		inputText("xpath",blogstatusTextArea,statusMessage);
		Thread.sleep(2000);		
		clickOnElement("cssSelector",updateStatus);
		confirmationMessage(updateText);		
		Thread.sleep(2000);		
	}
	
	
	public void validateWordWithPostedTitle(String inappropriateWord) throws Exception{			
		clickOnButton("cssSelector", inapSearchAllBtn);
		Thread.sleep(5000);		
		boolean iswordPresent = false;
		List <WebElement> word = driver().findElements(By.cssSelector(wordList));
		System.out.println("List size "+ word.size());
		for (int i=1; i<=word.size(); i++){
			WebElement badWord = driver().findElement(By.cssSelector(wordListBefore+i+wordListAfter));
			String badwordText = badWord.getText().trim();
			if(badwordText.equalsIgnoreCase(inappropriateWord)){
				iswordPresent =true;
				int expWordLengTh = badwordText.length();	
				System.out.println("expWordLengTh"+ expWordLengTh);
				WebElement email= driver().findElement(By.cssSelector(wordListBefore+i+UrlAfter));
				email.click();
				Thread.sleep(3000);
				WebElement tit = driver().findElement(By.cssSelector(photoTitle));
				int actTtitleLenght = tit.getText().trim().length();
				System.out.println("actTtitleLenght " + actTtitleLenght);
				Assert.assertEquals(actTtitleLenght, expWordLengTh);			
				break;			
			}		
		}if (iswordPresent==false){
			Assert.assertTrue(iswordPresent, inappropriateWord + " - word is not posted." );			
		}	
	}
	
	
	public void modifyPhotoTitle(String modifyTitle) throws Exception{
		clickOnLink("linkText", "Edit");		
		Thread.sleep(4000);
		inputTextClear("xpath",inputPhotoTitle);	
		inputText("xpath",inputPhotoTitle,modifyTitle);		
		Thread.sleep(3000);
		clickOnButton("cssSelector",SharePhoto);
		Thread.sleep(5000);
		
	}
	
	public void handleBack() throws Exception{
		clickOnLink("linkText", "Back");
		navigate2Inappropriate();		
	}
	
	
	
	
	public void selectSection(String leftSection) throws Exception{
		boolean isPresent = false;
		List <WebElement> sec  = driver().findElements(By.cssSelector(inapSections));
		System.out.println(sec.size()+ "size is ");
		for (WebElement section : sec){
			String sectionText = section.getText().trim();
			if (sectionText.equalsIgnoreCase(leftSection)){
				isPresent =true;
				section.click();
				Thread.sleep(3000);
				break;
			}
				
		}if(isPresent==false){
			Assert.assertTrue(isPresent,leftSection + " is not found");
		}
		
	}
	
	public void validatReservedeAlerts() throws Exception{
		
		clickOnElement("cssSelector", resAdd);
		confirmationMessage(alertAddNewText);
		Thread.sleep(6000);
		WebElement ser = driver().findElement(By.cssSelector(resSearchField));
		String actPlaceHolder = ser.getAttribute("placeholder")	;	
		Assert.assertEquals(actPlaceHolder, searchPlaceHolder);
		
		clickOnButton("cssSelector", resDel);
		confirmationMessage(alertDeleteText);  // alertResDeleteText
		Thread.sleep(6000);		
	}
	
	public void addReservedWord(String reserveWord) throws Exception{
		inputTextClear("cssSelector", resSearchField);
		inputText("cssSelector", resSearchField,reserveWord);
		Thread.sleep(2000);
		clickOnElement("cssSelector", resAdd);
		confirmationMessage(confText);
		Thread.sleep(6000);
	}
	
	public boolean isReservedWordPresent(String reserveWord) throws Exception{
		
		boolean isWorded = false;
		List<WebElement> badSize = driver().findElements(By.cssSelector(reseList));
		System.out.println("reserve size is "+badSize.size());
		for (WebElement bads :badSize){
			if(bads.getText().equalsIgnoreCase(reserveWord)){
				isWorded =true;
				System.out.println(reserveWord + "is present");
				Thread.sleep(2000);
				break;
			}			
		}if (isWorded==false){			
			Assert.assertTrue(isWorded, reserveWord+ " - word is not present");
		}
		return isWorded;
		
	}
	
	public boolean isReserveWordNotToPresent(String reserveWord) throws Exception{	
		boolean isWorded = true;
		List<WebElement> badSize = driver().findElements(By.cssSelector(reseList));
		System.out.println("All size is "+badSize.size());
		if (badSize.size()==0){
			System.out.println("Successfuly no words present like -"+ reserveWord);
			Assert.assertNotEquals(null, reserveWord);
		}
		for (WebElement bads :badSize){
			if(bads.getText().equalsIgnoreCase(reserveWord)){
				isWorded =false;
				bads.click();
				Thread.sleep(2000);
				Assert.assertTrue(isWorded, reserveWord+ " - word is still present ");
				break;
			}			
		}if (isWorded==true){			
			Assert.assertNotEquals(isWorded, reserveWord);
		}
		return isWorded;
		
	}
	
public void deleteReservedWord(String reserveWord) throws Exception{
		
		boolean isWorded = false;
		List<WebElement> badSize = driver().findElements(By.cssSelector(reseList));
		System.out.println("reserve size is "+badSize.size());
		for (WebElement bads :badSize){
			if(bads.getText().equalsIgnoreCase(reserveWord)){
				isWorded =true;
				bads.click();
				Thread.sleep(4000);
				clickOnButton("cssSelector", resDel);
				confirmationMessage(deleText);
				Thread.sleep(6000);
				break;
			}			
		}if (isWorded==false){			
			Assert.assertTrue(isWorded, reserveWord+ " - word is not present");
		}
		
		
	}

		public void verifyInExportedFolder() throws Exception{			
			clickOnLink("linkText", inapExportLnk);		
			Thread.sleep(6000);
			boolean isFileExists = false;
			String exportDate = "2016-07-22";
			String filepath = projectPath+"\\downloads\\";
			if(bc.verifyFileExistsOnDisk(filepath+"export-"+exportDate+".xls")){
				logInfo(filepath+"export.xls" + " file exists.");
			} else {
				logInfo(filepath+"export.xls" + " file does not exists.");
			}
		}
		
		
		
	
	

}
