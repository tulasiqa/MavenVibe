package vibe.feedBack.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import common.TestBase;
import vibe.marketing.companyNews.tests.NewsMethods;


public class FeedBackMethods extends TestBase {
	NewsMethods nm = new NewsMethods();
	
	
	public void navigate2Feedback() throws Exception{	
		logInfo("Entered navigate2Feedback() method");
		Thread.sleep(3000);
		clickOnLink("cssSelector", navFeedBack);	
		Thread.sleep(6000);
		}
	
	public void navigate2AdminFeedback(){
		logInfo("Entered navigate2AdminFeedback() method");
		
		driver().navigate().to(appUrl+"community/admin_feedbacks");
	}
	
		public void navigate2FeedbackTickets() throws Exception{
			logInfo("Entered navigate2FeedbackTickets() method");
			 Thread.sleep(4000);
			 clickOnElement("cssSelector", fbReport);
			 Thread.sleep(3000);
			 clickOnButton("cssSelector", fbViewTickets);	
			 Thread.sleep(10000);		
		}
		
	public void verifyFeedbackLink() throws Exception{	
		logInfo("Entered objectsVerifications() method");
		Thread.sleep(4000);
		WebElement feed = driver().findElement(By.cssSelector(navFeedBack));		
		Assert.assertEquals(feed.getText().trim(), feedbackText);		
		}
	
	public void objectsVerifications() throws Exception{
		logInfo("Entered objectsVerifications() method");
		Thread.sleep(4000);
		WebElement tit = driver().findElement(By.cssSelector(fbTitle));
		System.out.println(tit.getText());
		Assert.assertEquals(tit.getText(), fbTitleText);
		WebElement vTickets = driver().findElement(By.cssSelector(fbViewTickets));		
		System.out.println(vTickets.getText());
		Assert.assertEquals(vTickets.getText(), fbViewText);
		WebElement reportIssue = driver().findElement(By.cssSelector(fbReport));
		System.out.println(reportIssue.getText());
		Assert.assertEquals(reportIssue.getText(), fbReportText);
		Thread.sleep(3000);
		WebElement feedBack = driver().findElement(By.xpath(fbFeedback));		
		System.out.println(feedBack.getText());
		Assert.assertEquals(feedBack.getText(), fbFeedbackText);
	
		}
	
	 public void validateFeedBackFields() throws Exception{
		logInfo("Entered into validateFeedBackFields() method");
		clickOnButton("cssSelector", fbSubmit);
		WebElement title = driver().findElement(By.cssSelector(imgAlert));
		System.out.println("aert message is "+title.getText());		
		Assert.assertEquals(title.getText(), imgAlertText); 
	 	}
	 
	 // Report an issue 
	 public void reportIssue(String reportTitle) throws Exception{
		 logInfo ("Entered  reportIssue() method");
		 clickOnElement("cssSelector", fbReport);
		 Thread.sleep(3000);		 
		 inputTextClear("cssSelector", fbMsgTitle);
		 inputText("cssSelector", fbMsgTitle, reportTitle);		 
		//Thread.sleep(2000);
		 waitOnElement("cssSelector", fbChooseFile);
		 uploadFile("Image", fbChooseFile);
		/* Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Image.exe");
	     implicityWaits(5);
	     Thread.sleep(3000);*/
		 inputText("xpath", fbDescp, fbReportText+TestBase.generateRandomNumberInRange(10, 5000));	
		 Thread.sleep(8000);
	     WebElement submit = driver().findElement(By.cssSelector(fbSubmit));
		 submit.click();		 
		 Thread.sleep(15000);		
	 }
	 
	 //select View tickets and validate the latest created Report
	 public boolean validateTickets(String ticket) throws Exception{
		 logInfo("Inside validateTickets() method");
		 Thread.sleep(2000);
		 clickOnElement("cssSelector", fbReport);
		 Thread.sleep(3000);		 
		 clickOnButton("cssSelector", fbViewTickets);		 
		 List<WebElement> tic = driver().findElements(By.cssSelector(fbTickList));
		 System.out.println(tic.size());
		 boolean isTicketPresent = false;
		 for (int i=2; i<=tic.size()*2; i++){
			 WebElement tList = driver().findElement(By.cssSelector(fbTickList1+i+fbTickList2));			 
			 System.out.println(tList.getText());
			 if(tList.getText().equalsIgnoreCase(ticket)){
				 isTicketPresent=true;
				 System.out.println(tList.getText()+ "is found");				 
				 break;
			 }
			 i++;
		 }if (isTicketPresent==false){
			 Assert.assertTrue(isTicketPresent, ticket +" is not found");
		 }
		return isTicketPresent;
		 
	 }
	 
	 
	 public void commentToTicket(String ticket, String commentText) throws Exception{
		 logInfo("Inside commentToTicket() method");		 
		 Thread.sleep(2000);
		 clickOnElement("cssSelector", fbReport);
		 Thread.sleep(3000);		 
		 clickOnButton("cssSelector", fbViewTickets);		 
		 List<WebElement> tic = driver().findElements(By.cssSelector(fbTickList));		
		 boolean isTicketPresent = false;
		 for (int i=2; i<=tic.size()*2; i++){
			 WebElement tList = driver().findElement(By.cssSelector(fbTickList1+i+fbTickList2));			
			 if(tList.getText().equalsIgnoreCase(ticket)){				 
				 isTicketPresent=true;
				 int j= i+1;
				 WebElement comment = driver().findElement(By.cssSelector(fbTickList1+j+fbTicComment));
				 System.out.println(tList.getText()+ "is found");
				 comment.click();
				 Thread.sleep(4000);
				 inputText("cssSelector", fbCommentTextField, commentText);				 
				 clickOnButton("cssSelector", fbCommSubmit);
				 break;
			 }
			 i++;
		 }if (isTicketPresent==false){
			 Assert.assertTrue(isTicketPresent, ticket +" is not found");
		 }
		 
	 }
	 
	 
	 public void verifyCommentAndDelete(String ticketComment) throws Exception{
		 logInfo("Entered verifyCommentAndDelete() method");
		 Thread.sleep(3000);
		 clickOnButton("cssSelector", fbViewTickets);		 
		 List<WebElement> com = driver().findElements(By.cssSelector(fbExistComment));
		 System.out.println(com.size()+ " comment is");
		 boolean iscommPresent = false;
		 for (int i=1; i<=com.size(); i++){
			 WebElement et =driver().findElement(By.cssSelector(fbExistCommentB4r+i+fbExistCommentA4r));
			 System.out.println(et.getText()+ " coment are");
			 if(et.getText().trim().equalsIgnoreCase(ticketComment)){
				 
				 iscommPresent=true;
				 Thread.sleep(3000);
				 System.out.println("Test is "+et.getText());
				 WebElement del = driver().findElement(By.cssSelector(fbExistCommentB4r+i+fbDelete));
				 del.click();				 
				 break;	
				 }			 
		 }if(iscommPresent==false){
			 Assert.assertTrue(iscommPresent,comment+" is not found");
		 }		 
	 }
	 
	 public void verifyCommentedAndReport(String comment) throws Exception{
		 logInfo("Entered verifyCommentedAndReport() method");
		 Thread.sleep(3000);
		 clickOnButton("cssSelector", fbViewTickets);		 
		 List<WebElement> com = driver().findElements(By.cssSelector(fbExistComment));
		 System.out.println(com.size());
		 boolean iscommPresent = false;
		 for (int i=1; i<=com.size()*2; i++){
			 WebElement et =driver().findElement(By.cssSelector(fbExistCommentB4r+i+fbExistCommentA4r));
			 System.out.println(et.getText());
			 if(et.getText().equalsIgnoreCase(comment)){
				 iscommPresent=true;
				 Thread.sleep(3000);
				 System.out.println("Test is "+et.getText());
				 WebElement rep = driver().findElement(By.cssSelector(fbExistCommentB4r+i+fbReportLnk));
				 rep.click();
				 inputText("cssSelector", fbReportTextarea, claBodyText);
				 clickOnButton("cssSelector", fbRepbtn);
				 confirmationMessage("Report has been recorded");
				 break;	
				 }i++;			 
		 }if(iscommPresent==false){
			 Assert.assertTrue(iscommPresent,comment+" is not found");
		 }		 
	 }
	 
	 
	 public boolean verifyTicketInAdmin(String ticket) throws Exception{
		 logInfo("Entered verifyTicketInAdmin() method");
		 
		 boolean isfeedbackInAdmin = false;
		 navigate2AdminFeedback();
		 Thread.sleep(3000);
		 clickOnLink("cssSelector", fbAdmTickAll);
		 Thread.sleep(3000);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 System.out.println(list.getText());
			 if (list.getText().trim().equalsIgnoreCase(ticket)){
				 isfeedbackInAdmin =true;
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==false){
			 Assert.assertTrue(isfeedbackInAdmin, ticket +" - ticket is not found.");
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
	 
        public boolean ticketNotToBePresentInAdmin(String ticket) throws Exception{
        	logInfo("Entered ticketNotToBePresentInAdmin() method");
		 
		 boolean isfeedbackInAdmin = true;
		 navigate2AdminFeedback();
		 clickOnLink("cssSelector", fbAdmTickAll);
		 Thread.sleep(3000);		
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));			 
			 if (list.getText().trim().equalsIgnoreCase(ticket)){
				 isfeedbackInAdmin =false;
				 Assert.assertTrue(isfeedbackInAdmin, ticket +" - ticket is still present.");
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==true){
			 System.out.println("No feedback is present");
			
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
    public boolean deleteTicketInAdmin(String ticket) throws Exception{
    	logInfo("Entered deleteFeedbackInAdmin(String ticket) method");
		 boolean isfeedbackInAdmin = false;		
		 Thread.sleep(3000);
		 clickOnLink("cssSelector", fbAdmTickAll);
		 Thread.sleep(3000);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 System.out.println(list.getText());
			 if (list.getText().trim().equalsIgnoreCase(ticket)){
				 isfeedbackInAdmin =true;
				 WebElement del = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListChkBxAtr));
				 if (!del.isSelected()){
					 del.click();	
					 Thread.sleep(4000);					 
				 		}				 
				 clickOnElement("cssSelector",fbAdminDelete );				
				 confirmationMessage("Ticket(s) successfully deleted.");				 
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==false){
			 Assert.assertTrue(isfeedbackInAdmin, ticket +" - ticket is not found.");
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
    // create feedback
	 public void createFeedback(String reportTitle) throws Exception{
		 logInfo ("Entered  createFeedback() method");		
		 Thread.sleep(2000);
		 clickOnElement("xpath", fbFeedback);
		 Thread.sleep(3000);
		 inputTextClear("cssSelector", fbMsgTitle);
		 inputText("cssSelector", fbMsgTitle, reportTitle);
		 waitOnElement("cssSelector", fbChooseFile);
		 uploadFile("Image", fbChooseFile);
		/* Runtime.getRuntime().exec(projectPath + "\\testdata\\auto-it\\fileUpload_Image.exe");
	     implicityWaits(5);
		 Thread.sleep(2000);*/
		 inputText("xpath", fbDescp, fbFeedbackcomment);	
		 Thread.sleep(5000);
	     WebElement submit = driver().findElement(By.cssSelector(fbFeedbackSubmit));
		 submit.click();
		 Thread.sleep(10000);
		 confirmationMessage("Feedback successfully created");
	 }
	 
	 
	//select View feedback and validate the latest created feedbacks
		 public boolean validateFeedbacks(String feedback) throws Exception{
			 logInfo("Inside validateFeedbacks() method");			
			 Thread.sleep(2000);
			 clickOnElement("xpath", fbFeedback);
			 Thread.sleep(3000);
			 clickOnButton("cssSelector", fbViewFeedback);			 
			 List<WebElement> tic = driver().findElements(By.cssSelector(fbFeedList));			 
			 boolean isTicketPresent = false;
			 for (int i=2; i<=tic.size()*2; i++){
				 WebElement tList = driver().findElement(By.cssSelector(fbFeedList1+i+fbFeedList2));				
				 if(tList.getText().equalsIgnoreCase(feedback)){
					 isTicketPresent=true;					 			 
					 break;
				 }
				 
			 }if (isTicketPresent==false){
				 Assert.assertTrue(isTicketPresent, feedback +" is not found");
			 }
			return isTicketPresent;
			 
		 }		 
		 
		 //select View feedback and validate the latest created feedbacks
		 public void commentFeedback(String feedback, String comment) throws Exception{
			 logInfo("Inside commentFeedback() method");
			 Thread.sleep(5000);				 
			 List<WebElement> tic = driver().findElements(By.cssSelector(fbFeedList));
			 System.out.println(tic.size());
			 boolean isTicketPresent = false;
			 for (int i=2; i<=tic.size()*2; i++){
				 WebElement tList = driver().findElement(By.cssSelector(fbFeedList1+i+fbFeedList2));		 
				 System.out.println("Retrieved as "+tList.getText());
				 if(tList.getText().equalsIgnoreCase(feedback)){					
					 isTicketPresent=true;
					 Thread.sleep(3000);
					 int j= i+1;
					 WebElement countCom = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedCommentCount));							 
					 String count= countCom.getText();
					 String[] parts = StringUtils.split(count," ");
					 String countsCom = parts[0];
					 int con =Integer.parseInt(countsCom);
					 int aftercomneted = con+1;
					 String afterExpCount = Integer.toString(aftercomneted);   
					 
					 System.out.println("Before commented the count is "+countsCom + " And also expected count woud be "+ afterExpCount);							 
					 
					 WebElement com = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedComment));
					 com.click();					 
					 Thread.sleep(3000);
					 inputText("cssSelector",fbCommnet,comment);
					 Thread.sleep(4000);
					 getText("cssSelector",fbCommPost, "Post text is ");
					 clickOnButton("cssSelector",fbCommPost);						
					 Thread.sleep(5000);					 
					 WebElement aftCount = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedCommentCount));
					 System.out.println("After "+aftCount.getText());
					 String Aftercount= aftCount.getText();
					 String[] afterParts = StringUtils.split(Aftercount," ");
					 String retriveAfterCount = afterParts[0];							 
					 Assert.assertEquals(retriveAfterCount, afterExpCount);							 
					 break;
				 }	i++;					 
			 }if (isTicketPresent==false){
				 Assert.assertTrue(isTicketPresent, feedback +" is not found");
			 }
			 
		 }
				 
	 	//select View feedback and validate the voted feedback
		public void validateVotedFeedback(String feedback ) throws Exception{
			 logInfo("Inside validateVotedFeedback() method");			
			 Thread.sleep(2000);
			 clickOnElement("xpath", fbFeedback);
			 Thread.sleep(3000);
			 clickOnButton("cssSelector", fbViewFeedback);			 
			 List<WebElement> tic = driver().findElements(By.cssSelector(fbFeedList));
			 System.out.println(tic.size());
			 boolean isTicketPresent = false;
			 for (int i=2; i<=tic.size()*2; i++){
				 WebElement tList = driver().findElement(By.cssSelector(fbFeedList1+i+fbFeedList2));		 
				 
				 if(tList.getText().equalsIgnoreCase(feedback)){
					 isTicketPresent=true;
					 int j= i+1;
					 WebElement countCom = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedVoteCount));							 
					 String count= countCom.getText();
					 String[] parts = StringUtils.split(count," ");
					 String countsCom = parts[0];
					 int con =Integer.parseInt(countsCom);
					 int aftercomneted = con+1;
					 String afterExpCount = Integer.toString(aftercomneted);   
					 
					 System.out.println("Before vote, the count is "+countsCom + " And also expected count woud be "+ afterExpCount);							 
					 
					 WebElement com = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedVote));
					 com.click();
					 Thread.sleep(6000);							 
					 
					 WebElement aftCount = driver().findElement(By.cssSelector(fbFeedList1+j+fbFeedVoteCount));
					 System.out.println("After "+aftCount.getText());
					 String Aftercount= aftCount.getText();
					 String[] afterParts = StringUtils.split(Aftercount," ");
					 String retriveAfterCount = afterParts[0];
					 
					 Assert.assertEquals(retriveAfterCount, afterExpCount);							 
					 break;
				 }
				 i++;
			 }if (isTicketPresent==false){
				 Assert.assertTrue(isTicketPresent, feedback +" is not found");
			 }				 
			}	 
		 
		 public boolean addCommentFeedbackInAdmin(String ticket, String comments) throws Exception{
			 logInfo("Entered addCommentFeedbackInAdmin() method");
			 boolean isfeedbackInAdmin = false;
			 navigate2AdminFeedback();
			 Thread.sleep(3000);
			 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
			 System.out.println("Size of list is "+ lis.size());
			 for (int i=2 ;i<=lis.size()*2; i++){
				 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
				 System.out.println(list.getText());
				 if (list.getText().trim().equalsIgnoreCase(ticket)){
					 isfeedbackInAdmin =true;					
					 list.click();
					 Thread.sleep(3000);
					 clickOnLink("linkText","Add Comment");
					 Thread.sleep(2000);
					 inputText("cssSelector", fbCommentTextField, comments);							
					 clickOnButton("cssSelector", fbCommSubmit);
					 
					 break;
				 }	 
				 
			 }if(isfeedbackInAdmin==false){
				 Assert.assertTrue(isfeedbackInAdmin, ticket +" - ticket is not found.");
			 }
			return isfeedbackInAdmin;	 
		 }
		 
		 
	    public void searchNdVerifyTickets(String ticket) throws Exception{
	    	logInfo("Entered searchNdVerifyTickets() method");
	    	//searches tickets
	    	Thread.sleep(3000);
			 clickOnButton("cssSelector", fbViewTickets);	
			 inputTextClear("cssSelector", fbTickSearh);
			 inputText("cssSelector", fbTickSearh,ticket);
			 Thread.sleep(2000);						 
			 WebElement search = driver().findElement(By.cssSelector(fbTickSearh)) ;
			 search.sendKeys(Keys.TAB);
			 search.sendKeys(Keys.ENTER);						 
			 Thread.sleep(2000);						 
			 List<WebElement> com = driver().findElements(By.cssSelector(fbTickList));
			 System.out.println(com.size());
			 boolean isTicketPresent = false;
			 if (com.size()==1){
				 for(WebElement tick : com){
					 if(tick.getText().equalsIgnoreCase(ticket)){
						 isTicketPresent =true;
						 System.out.println(tick.getText()+ " is present");
						 break;									 
					 }								 
				 }if(isTicketPresent==false){
					 Assert.assertTrue(isTicketPresent, ticket + " is not found in search result");
				 }							 
			 }else if (com.size()==0){
				 Assert.assertNull(ticket);
				 
			 }else{
				 Assert.assertNull(ticket +" -tickets results retrived more than one." , null);
			 }
	    	
	    }		    
				    
				    
	    public void searchNdVerifyFeedbacks(String feedback) throws Exception{
	    	logInfo("Entered searchNdVerifyFeedbacks() method");
	     //searches feedback
    	 Thread.sleep(2000);
		 clickOnElement("xpath", fbFeedback);
		 Thread.sleep(3000);
		 clickOnButton("cssSelector", fbViewFeedback);	 
		 inputTextClear("cssSelector", fbFeedbackSearh);
		 inputText("cssSelector", fbFeedbackSearh,feedback);
		 Thread.sleep(2000);						 
		 WebElement search = driver().findElement(By.cssSelector(fbFeedbackSearh)) ;
		 search.sendKeys(Keys.TAB);
		 search.sendKeys(Keys.ENTER);						 
		 Thread.sleep(2000);						 
		 List<WebElement> com = driver().findElements(By.cssSelector(fbFeedList));
		 System.out.println(com.size());
		 boolean isFeedbackPresent = false;
		 if (com.size()==1){
			 for(WebElement tick : com){
				 if(tick.getText().equalsIgnoreCase(feedback)){
					 isFeedbackPresent =true;
					 System.out.println(tick.getText()+ " is present");
					 break;									 
				 }								 
			 }if(isFeedbackPresent==false){
				 Assert.assertTrue(isFeedbackPresent, feedback + " is not found in search result");
			 }							 
		 }else if (com.size()==0){
			 Assert.assertNull(null);			 
		 }else{
			 Assert.assertNull(feedback +" -feedback results retrived more than one." , null);
		 }	    	
	    }
	    
	 public void invalidSearchs(String invalidSearch) throws Exception{	   
		 logInfo("Entered invalidSearchs() method");
    	 Thread.sleep(3000);	    	
		 clickOnElement("xpath", fbFeedback);
		 Thread.sleep(3000);
		 clickOnButton("cssSelector", fbViewFeedback);	
		 Thread.sleep(4000);
		 inputTextClear("cssSelector", fbFeedbackSearh);
		 inputText("cssSelector", fbFeedbackSearh,invalidSearch);
		 Thread.sleep(2000);						 
		 WebElement search = driver().findElement(By.cssSelector(fbFeedbackSearh)) ;
		 search.sendKeys(Keys.TAB);
		 search.sendKeys(Keys.ENTER);						 
		 Thread.sleep(2000);	
		 WebElement fed = driver().findElement(By.cssSelector(fbFeedbackInvalidSearh));
		 System.out.println(fed.getText());
		 Assert.assertEquals(fed.getText(), InvalidFeedbackText);
		 Thread.sleep(2000);
	// searching with invalid data for tickets	 
		 navigate2Feedback();
		 Thread.sleep(3000);
		 clickOnButton("cssSelector", fbViewTickets);	
		 Thread.sleep(4000);
		 inputTextClear("cssSelector", fbTickSearh);
		 inputText("cssSelector", fbTickSearh,invalidSearch);
		 Thread.sleep(2000);						 
		 WebElement tic = driver().findElement(By.cssSelector(fbTickSearh)) ;
		 tic.sendKeys(Keys.TAB);
		 tic.sendKeys(Keys.ENTER);						 
		 Thread.sleep(2000);
		 WebElement tick = driver().findElement(By.cssSelector(fbTicketsInvalidSearh));
		 System.out.println(tick.getText());
		 Assert.assertEquals(tick.getText(), InvalidTicketText);					
	   }
	 
	 
	 public void searchFilters(String filterOption, String ticket) throws Exception{	
		 logInfo(" Entered into 'searchFilters' method");
		 Thread.sleep(3000);
		 clickOnElement ("cssSelector", fbFilter);
		 Thread.sleep(4000);
		 boolean isOptionPresent = false;
		 List <WebElement> lis = driver().findElements(By.cssSelector(filterOpt));
		 System.out.println("Size is "+ lis.size());
		 for (WebElement opt : lis){
			 System.out.println(opt.getText());
			 if(opt.getText().equalsIgnoreCase(filterOption)){
				 isOptionPresent=true;
				 opt.click();
				 validateTickets(ticket);				 
				 break;
			 }			 
		 }if(isOptionPresent==false){
			 Assert.assertTrue(isOptionPresent, filterOption +" option is not found");
		 }		 
	 }
	 
	 
	 public void getTicketLenths(String ticket) throws Exception{
		 logInfo("Inside validateTickets() method");		 
		 clickOnButton("cssSelector", fbViewTickets);		 
		 List<WebElement> tic = driver().findElements(By.cssSelector(fbTickList));
		 boolean isTicketPresent = false;
		 for (int i=1; i<=tic.size()*2; i++){
			 WebElement tList = driver().findElement(By.cssSelector(fbTickList1+i+fbTickList2));	
			 String actualTicket = tList.getText();
			 System.out.println(actualTicket);
			 if(actualTicket.contains(ticket)){
				isTicketPresent=true;
				int lenOfTick = actualTicket.length();
				 String lengthOfTickets = Integer.toString(lenOfTick);				 				 
				 Assert.assertEquals(lengthOfTickets, "50");
				 break;
			 }
			 i++;
		 }if (isTicketPresent==false){
			 Assert.assertTrue(isTicketPresent, ticket +" is not found");
		 }
		 
	 }
	 
	 public boolean getFeedbackLength(String feedback) throws Exception{
		 logInfo("Inside validateFeedbackLength() method");			
		 Thread.sleep(2000);
		 clickOnElement("xpath", fbFeedback);
		 Thread.sleep(3000);
		 clickOnButton("cssSelector", fbViewFeedback);			 
		 List<WebElement> tic = driver().findElements(By.cssSelector(fbFeedList));		 
		 boolean isTicketPresent = false;
		 for (int i=1; i<=tic.size(); i++){
			 WebElement tList = driver().findElement(By.cssSelector(fbFeedList1+i+fbFeedList2));			 
			 String feedText = tList.getText();
			 if(feedText.contains(feedback)){
				 isTicketPresent=true;
				 int feedSize = feedText.length();
				 String feedActualSize = Integer.toString(feedSize)	;				 
				 Assert.assertEquals(feedActualSize, "50");;
				 break;
			 }
			 
		 }if (isTicketPresent==false){
			 Assert.assertTrue(isTicketPresent, feedback +" is not found");
		 }
		return isTicketPresent;
		 
	 }
	 
	 
	 
	 
	 public void verifyBoundaries() throws Exception{
		 logInfo("Entered objectsVerifications() method");
		 //String reportTitle = "valiadtinBoundriesTitle121 1234567890a";
		 String reportTitle = "validateTitlewithmaxcharaters"+TestBase.generateRandomString()+" 1234567890abcdefghij1234567890abcdefghij1234567850";
		 String[] reptitle = StringUtils.split(reportTitle," ");
		 String splitedTitle = reptitle[0];
		 clickOnElement("cssSelector", fbReport);
		 Thread.sleep(3000);
		 inputTextClear("cssSelector", fbMsgTitle);
		 inputText("cssSelector", fbMsgTitle, reportTitle);
		 implicityWaits(5);
		 Thread.sleep(2000);
		 inputText("xpath", fbDescp, reportTitle);	
		 Thread.sleep(5000);
	     WebElement submit = driver().findElement(By.cssSelector(fbSubmit));
		 submit.click();
		 Thread.sleep(10000);		 
	     navigate2Feedback();	
		 Thread.sleep(3000);
		 getTicketLenths(splitedTitle);
		 
		 navigate2Feedback();
		 createFeedback(reportTitle);
		 navigate2Feedback();
		 getFeedbackLength(splitedTitle);		 
	 }
	 
	 public boolean verifyReportedInAdmin(String ticket) throws Exception{
		 logInfo("Entered verifyReportedInAdmin() method");
		 boolean isfeedbackInAdmin = false;		 
		 Thread.sleep(3000);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 System.out.println(list.getText());
			 if (list.getText().trim().equalsIgnoreCase(ticket)){
				 isfeedbackInAdmin =true;
				 list.click();				 
				 Thread.sleep(2000);
				 WebElement reported = driver().findElement(By.cssSelector(fbAdminRep));
				 System.out.println(reported.getText());
				 Assert.assertEquals(reported.getText(), "Reported");				 
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==false){
			 Assert.assertTrue(isfeedbackInAdmin, ticket +" - ticket is not found.");
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 public boolean reportNoTobePresentInAdmin(String ticket) throws Exception{	
		 logInfo("Entered reportNoTobePresentInAdmin() method");
		 boolean isTicketInAdmin = true;		 
		 Thread.sleep(3000);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 System.out.println(list.getText());
			 if (list.getText().trim().equalsIgnoreCase(ticket)){
				 isTicketInAdmin =false;
				 Assert.assertTrue(isTicketInAdmin, ticket +" is still present.");								 
				 break;
			 }	 
			 
		 }if(isTicketInAdmin==true){
			 Assert.assertNotSame(isTicketInAdmin, ticket);
		 }
		return isTicketInAdmin;	 
	 }
	 
	 
	 
	 
	 
	 public void verifySectionsInAdmin(String ticket) throws Exception{
		 logInfo("Entered verifySectionsInAdmin() method");
		 
		 clickOnLink("cssSelector", fbAdmTickAll);
		 Thread.sleep(3000);
		 verifyTicketInAdmin(ticket);
		 clickOnLink("cssSelector", fbAdmTickOpen);
		 Thread.sleep(3000);
		 verifyTicketInAdmin(ticket);
		 clickOnLink("cssSelector", fbAdmTickClosed);
		 Thread.sleep(3000);
		 reportNoTobePresentInAdmin(ticket);
		 
	 }
	 
public boolean verifyFeedbackInAdmin(String feedback) throws Exception{
	logInfo("Entered verifyFeedbackInAdmin() method");
		 
		 boolean isfeedbackInAdmin = false;
		 navigate2AdminFeedback();
		 Thread.sleep(3000);
		 clickOnLink("cssSelector", fbAdmFeedAll);
		 Thread.sleep(3000);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 System.out.println(list.getText());
			 if (list.getText().trim().equalsIgnoreCase(feedback)){
				 isfeedbackInAdmin =true;
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==false){
			 Assert.assertTrue(isfeedbackInAdmin, feedback +" - feedback is not found.");
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
	 
        public boolean feedbackNotToBePresentInAdmin(String feedback) throws Exception{
        	logInfo("Entered feedbackNotToBePresentInAdmin() method");
		 
		 boolean isfeedbackInAdmin = true;
		 navigate2AdminFeedback();
		 clickOnLink("cssSelector", fbAdmFeedAll);
		 Thread.sleep(3000);		
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));			 
			 if (list.getText().trim().equalsIgnoreCase(feedback)){
				 isfeedbackInAdmin =false;
				 Assert.assertTrue(isfeedbackInAdmin, feedback +" - feedback is still present.");
				 break;
			 }	 
			 
		 }if(isfeedbackInAdmin==true){
			 System.out.println("No feedback is present");
			
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
    public boolean deleteFeedbackInAdmin(String feedback) throws Exception{
    	logInfo("Entered deleteFeedbackInAdmin(String feedback) method");
		 boolean isfeedbackInAdmin = false;		
		 Thread.sleep(3000);
		 clickOnLink("cssSelector", fbAdmFeedAll);
		 Thread.sleep(3000);
		 List <WebElement> lis = driver().findElements(By.cssSelector(fdAdminList));
		 System.out.println("Size of list is "+ lis.size());
		 for (int i=2 ;i<=lis.size(); i++){
			 WebElement list = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListAtr));
			 System.out.println(list.getText());
			 if (list.getText().trim().equalsIgnoreCase(feedback)){
				 isfeedbackInAdmin =true;
				 WebElement del = driver().findElement(By.cssSelector(fdAdminListB4r+i+fdAdminListChkBxAtr));
				 if (!del.isSelected()){
					 del.click();	
					 Thread.sleep(4000);					 
				 		}				 
				 clickOnElement("cssSelector",feedDeleteInAdmin);				
				 confirmationMessage("Feedback(s) successfully deleted.");				 
				 break;
			 }	 
			
		 }if(isfeedbackInAdmin==false){
			 Assert.assertTrue(isfeedbackInAdmin, feedback +" - feedback is not found.");
		 }
		return isfeedbackInAdmin;	 
	 }
	 
	 
	 
	 
	 
	 
}
