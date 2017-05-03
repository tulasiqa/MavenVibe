package common;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import vibe.myprofile.tests.MyProfileMethods;
import vibe.users.tests.UsersMethods;

public class TestBase extends DriverInitializer{
protected static final Log logger = LogFactory.getLog(TestBase.class);

	WebElement element, toElement;
	WebDriverWait wait;
	String By;
	ExtentTest test;
	JavascriptExecutor js;	
	
	readProp prop = new readProp();

	
	// This method is used to Login to the application.
	
	public void logIn(String userName, String passwd) throws Exception {
        try{
            logInfo("inside logIn method.");
            driver().navigate().to(appUrl + "/login");                
            waitOnElement("xpath", inputName);
            inputText("xpath", inputName, userName);
            inputText("xpath", inputPwd, passwd);
            if ((driver().getCurrentUrl().contains("tupperware"))){
                submitElement("xpath", inputPwd);                
            }else{
                clickOnButton("xpath", btnLogin);    
               }
            implicityWaits(30);                 
        }
        catch(Exception e){
            logInfo(e.getMessage());
           // logInfo("Failed!! unable to login to the application.");        
        }
       
    }	// This method is used to Logout from the application.	
	
	 
	 public void logOut() throws Exception{
			logInfo("inside logOut method.");
			back2Office();
			waitOnElement("cssSelector", lnkProfileDrpdn);
			clickOnElement("cssSelector", lnkProfileDrpdn);
			waitOnElement("cssSelector",logOut);
			clickOnElement("cssSelector",logOut);		
				
		}
	 
	// This method is used to Logout from the application.	
		
			 public void logOutInAvon() throws Exception{
					logInfo("inside logOut method.");
					back2Office();
					verifyElementPresent("cssSelector", lnkProfileDrpdn);
					clickOnElement("cssSelector", lnkProfileDrpdn);
					List <WebElement> log = driver().findElements(org.openqa.selenium.By.cssSelector(logOutAvon));
					for (WebElement logs : log){
						if (logs.getText().equalsIgnoreCase("Logout")){
							logs.click();
							break;
							}	
					}					
					Thread.sleep(5000);
					System.out.println("Logged Out");		
				}
		
	
	//This method is used to open a specified URL.
	
	public void openUrl(WebDriver driver, String url){
		try{
			driver().get(url);
		}
		catch(Exception e){
			logInfo("Failed!! unable to open the specified url.");		
		}
	}
	
	// This method is used to Maximize the browser window.
	
	public void maximizeBrowser() throws Exception{
		try{	
			driver().manage().window().maximize();
		}
		catch(Exception e){
			logInfo("Failed!! unable to maximize the browser window");		
		}
	}
	
	// This method is used to configure the log4j.xml file and can send the logging information to that file.
			
	public void logInfo(Object message) {
		try{	
			Logger Log = Logger.getLogger(Logger.class.getName());
			DOMConfigurator.configure("log4j.xml");
		    Log.info(message);
		}
		catch(Exception e){
			logInfo("Failed!! unable to log the information.");		
		}
		
 	} 		

	// This method is used to perform click operation on a specific element.
			
	public void clickOnElement(String Bytype, String locator) {	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			case "linkText":
				element = driver().findElement(ByLinkText.linkText(locator));
				break;
			default :
				System.out.println("Invalid type passed to clickOnElement."+locator);
				break;
			}
			if(element.isDisplayed() && element.isEnabled()){
				element.click();
			}
			   
		}
		catch(Exception e){
			logInfo("Failed!! Unable to click on an element."+locator);		
		}
		
	}
	
	// This method is used to submit an element.
	
	public void submitElement(String Bytype, String locator) {	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			case "linkText":
				element = driver().findElement(ByLinkText.linkText(locator));
				break;
			default :
				System.out.println("Invalid type passed to submitElement."+locator);
				break;
			}
			if(element.isDisplayed() && element.isEnabled()){
				element.submit();
			}
			   
		}
		catch(Exception e){
			logInfo("Failed!! Unable to submit the element."+locator);		
		}
		
	}
			
	// This method is used to perform click operation on a specified link.
		
	public void clickOnLink(String Bytype, String locator) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "linkText":
				element = driver().findElement(ByLinkText.linkText(locator));
				break;
			case "partialLinkText":
				element = driver().findElement(ByPartialLinkText.partialLinkText(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;	
			default :
				System.out.println("Invalid type passed to clickOnLink "+locator);
				break;
			}
			element.click();
		} 
		catch(Exception e) {
			logInfo("Failed!! unable to click on a Link."+locator);			
		}
	}


	// This method is used to verify the specified text and it returns a boolean value.
	public boolean verifyTextPresent(String someText){
	boolean isTextPresent = false;
	try{
		isTextPresent = driver().getPageSource().contains(someText);
		System.out.println("isTextPresent = " +isTextPresent);
		}
		catch(Exception e){
			logger.error("Failed!! Unable to locate the text."+someText); 
		}
		return isTextPresent;
	}

		
	// This method is used to verify the title of the page and comparing the Actual Text with the Expected Text.
	
	public void verifyTitle(String actual,String expected) throws Exception{
		try{
			actual = driver().getTitle();
			
			if (actual.equalsIgnoreCase(expected)){
				System.out.println("correct page title : " + "\"" + actual + "\"");
			}
			else{
				System.out.println("incorrect page title : " + "\"" + actual + "\"" + ". \n\t Title should be " + "\"" + expected + "\"" );
			}
		}
		catch(Exception e){
			logInfo("Failed!! miss matches with "+actual + "and "+ expected );
		}
	}

	// This method is used to verify if the specified element is present on the DOM page.
	
	public boolean verifyElementPresent(String Bytype,String locator) throws InterruptedException{
		boolean isElementPresent = false;
		try{
			switch(Bytype){
			case "xpath":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ByXPath.xpath(locator)));
				break;
			case "id":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ById.id(locator)));
				break;
			case "name":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ByName.name(locator)));
				break;
			case "className":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ByClassName.className(locator)));
				break;
			case "cssSelector":
				element = (new WebDriverWait(driver(), 20)).until(ExpectedConditions.visibilityOfElementLocated(ByCssSelector.cssSelector(locator)));
				break;
			default :
				System.out.println("Invalid type passed to verifyElementPresent."+locator);
				break;
			}
			
			if(element.isDisplayed())
				isElementPresent = true;

		}catch(Exception e){
	    	e.printStackTrace();
	    	System.out.println("specified element is not present"+locator);
	    }
		return isElementPresent;
	}
	
	// This method is used to verify if the specified link text is present on the page.
	
	public boolean verifyLinkPresent(String expValue) throws Exception{	
		 boolean flag = false;
		try{
		   	List<WebElement> links = driver().findElements(ByTagName.tagName("a"));
	    	for(WebElement x : links){
	    		String actValue = x.getText();
	    		if(actValue.equalsIgnoreCase(expValue)){
	    			flag = true;
	    			break;
	    		} 
	    	}
	    	 if(flag = false){
	    		 logInfo("The link "+ expValue + " : could not be found.");
	    	 }
	    	
		}catch(Exception e) {
			logInfo("Failed!! Unable to find a link " + expValue);			
		}
		 return flag;
	}
		
   	
	// This method is used to perform click operation on the specified button.
	
	public void clickOnButton(String Bytype, String locator) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to clickOnButton."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				element.click();
			}
		}
		catch(Exception e){
			logInfo("Failed!! Unable to click on a button."+locator);		
		}
		
	}
	
	// This method is used to double click on a specified element.
	
	public void doubleClick(String Bytype, String locator ) throws Exception{
		try{
			Actions action = new Actions(driver());
			
			switch(Bytype){
			case "xpath":
				action.moveToElement(driver().findElement(ByXPath.xpath(locator))).doubleClick().build().perform();
				break;
			case "id":
				action.moveToElement(driver().findElement(ById.id(locator))).doubleClick().build().perform();
				break;
			case "name":
				action.moveToElement(driver().findElement(ByName.name(locator))).doubleClick().build().perform();
				break;
			case "className":
				action.moveToElement(driver().findElement(ByClassName.className(locator))).doubleClick().build().perform();
				break;
			case "cssSelector":
				action.moveToElement(driver().findElement(ByCssSelector.cssSelector(locator))).doubleClick().build().perform();
				break;
			case "linkText":
				action.moveToElement(driver().findElement(ByLinkText.linkText(locator))).doubleClick().build().perform();
				break;
			case "tagName":
				action.moveToElement(driver().findElement(ByTagName.tagName(locator))).doubleClick().build().perform();
				break;
			case "partialLinkText":
				action.moveToElement(driver().findElement(ByPartialLinkText.partialLinkText(locator))).doubleClick().build().perform();
				break;
			default :
				System.out.println("Invalid type passed to clickOnButton."+locator);
				break;
			}
			
		}
		catch(Exception e){
			logInfo("Failed!! To double click on " + locator);		
		}
			
	}
	
	// This method is used to click on the text area.
	
	public void clickOnTextArea(String Bytype, String locator, String EnterTextArea) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			case "tagName":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to clickOnTextArea."+locator);
				break;
			}
			if(element.isDisplayed()){
				element.click();
				element.clear();
				element.sendKeys(EnterTextArea);
			}
			
		}
		catch(Exception e){
			logInfo("Failed!! Unable to click and enter text in the text area element."+locator);		
		}
		
	}
	
	// This method is used to enter the values into a text box
	
	public void inputText(String Bytype, String locator, String value) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to inputText."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				element.sendKeys(value);
			}
		}
		catch(Exception e){
			logInfo("Failed!! Unable to enter a text into a textbox."+locator);		
		}
	}
		
	// This method is used to clear text box values.
	
	public void inputTextClear(String Bytype, String locator) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to inputTextClear."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				element.clear();
			}
		}
		catch(Exception e){
			logInfo("Failed!! Unable to clear the text from a textbox."+locator);		
		}
	}

	// This method is used to select options from a drop down list. 
	
	public void selectFromDropdown(String Bytype, String locator, String selectType, String value) throws Exception{
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "tagName":
				element = driver().findElement(ByTagName.tagName(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to selectFromDropdown."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				Select select = new Select(element);
				if(selectType == "byVisibleText"){
					select.selectByVisibleText(value);
				} else if (selectType == "byValue"){
					select.selectByValue(value);
				} else if(selectType == "byIndex"){
					Integer ivalue = Integer.valueOf(value);
					select.selectByIndex(ivalue);
				}
			  }
		}
		catch(Exception e){
			logInfo("Failed!! Required option is not available or not select drop down list"+locator);		
		}
	}
	
	
	//This method is used to get the current selected item from drop down
	
	public String getCurrentSelectionFromDropdown(String Bytype, String locator) throws Exception{
		String value = null;
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "tagName":
				element = driver().findElement(ByTagName.tagName(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to selectFromDropdown."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				Select select = new Select(element);
				value = select.getFirstSelectedOption().getText().trim();
			}
		}
		catch(Exception e){
		logInfo("Failed!! Required option is not available or not select drop down list"+locator);
		}
		return value;
	}
			
	// This method is used to select either a radio buttons or check boxes.
	
	public void selectRadioOrCheckbox(String Bytype, String locator) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to selectRadioOrCheckbox."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled())
				element.click();
		}
		catch(Exception e){
			logInfo("Failed!! Unable to select the specified option" + locator);		
		}
	}	
	
	// This method is used to get the element text.
	
	public void getText(String Bytype, String locator, String String) throws Exception{	
		try{
			switch(Bytype){
			case "xpath":
				element = driver().findElement(ByXPath.xpath(locator));
				break;
			case "id":
				element = driver().findElement(ById.id(locator));
				break;
			case "name":
				element = driver().findElement(ByName.name(locator));
				break;
			case "className":
				element = driver().findElement(ByClassName.className(locator));
				break;
			case "cssSelector":
				element = driver().findElement(ByCssSelector.cssSelector(locator));
				break;
			default :
				System.out.println("Invalid type passed to getText."+locator);
				break;
			}
			
			if(element.isDisplayed() && element.isEnabled()){
				System.out.println(String +" : " +element.getText());
			}
		}
		catch(Exception e){
			logInfo("Failed!! Unable to get the text of an element " + locator);		
		}
		
	}
	
	// This method is used to wait until we found an element.
	
	public void implicityWaits(int i ) throws Exception{
		try{
			driver().manage().timeouts().implicitlyWait(i,TimeUnit.SECONDS);
		}
		catch(Exception e) {
			logInfo("Failed!! Page is not sync with webdriver within :" + i +" Seconds");			
		}
	}
	

	// This method is used to navigate to the home page.
	
	public void back2Office(){
		System.out.println("inside back2Office() method.");
		logInfo("inside back2Office() method.");		
		driver().navigate().to(appUrl);
	}
	

	
	// This method is used to get the current date.
	
	public static String getDate(int period,String format){
	     Calendar currentDate = Calendar.getInstance();
	     SimpleDateFormat formatter= new SimpleDateFormat(format);
	     currentDate.add(Calendar.DAY_OF_MONTH, period);
	     String date = formatter.format(currentDate.getTime());
	     return date;
	}
	  
	//This method is used to get the previous/next month date.
	public static String getDateByMonth(int period,String format){
	     Calendar currentDate = Calendar.getInstance();
	     SimpleDateFormat formatter= new SimpleDateFormat(format);
	     currentDate.add(Calendar.MONTH, period);
	     String date = formatter.format(currentDate.getTime());
	     return date;
	}
	
	//This method is used to get the previous/next year date.
	public static String getDateByYear(int period,String format){
	     Calendar currentDate = Calendar.getInstance();
	     SimpleDateFormat formatter= new SimpleDateFormat(format);
	     currentDate.add(Calendar.YEAR, period);
	     String date = formatter.format(currentDate.getTime());
	     return date;
	}
	
	// This method is used to generate a random number.
	
	public static int random(){
        Random r = new Random();
        int random_num = r.nextInt(1000);
        return random_num;
	}
	
	//This method is used to generate a random string.
	
    private static SecureRandom random = new SecureRandom();
    public static String generateRandomString() {
    	return new BigInteger(8, random).toString(64);
    }
  
    // This method is used to generate random number in s specified range.
    
    public static int generateRandomNumberInRange(int min,int max){
		logger.info("Generating random number in range - "+min+","+max);
		Random r = new Random();
		int num=r.nextInt(max-min) + min;
		logger.info("Generating random number = "+num);
		return num;
	}
	
	// This method is used to generate an email based on the Username.

		public static String getEmail(String userName, String url){
	     	String uName = userName.trim();
		/*	String[] parts = StringUtils.split(uName,".");
			String fName = parts[0];
			String lName = parts[1];*/
			String[] urlparts = url.split("/");
			String urlpart1 = urlparts[0];
			String urlpart2 = urlparts[2];
			String email = uName+"@"+urlpart2;
			return email;
	    }
		
		
		
		
	// This method is used to Wait for a element to present.
	
			public void waitOnElement(String Bytype,String locator) throws Exception, Exception{
				try{
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver())
						 .withTimeout(120, TimeUnit.SECONDS)
			    		 .pollingEvery(2, TimeUnit.SECONDS);
			   			   		 
					switch(Bytype){
					case "xpath":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByXPath.xpath(locator)));
						break;
					case "id":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ById.id(locator)));
						break;
					case "className":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByClassName.className(locator)));
						break;
					case "cssSelector":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByCssSelector.cssSelector(locator)));
						break;
					case "linkText":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByLinkText.linkText(locator)));
						break;
					case "partialLinkText":
						element = wait.until(ExpectedConditions.visibilityOfElementLocated(ByPartialLinkText.partialLinkText(locator)));
						break;
					default :
						System.out.println("Invalid type passed to waitOnElement.");
						break;
					}
					
					if(element.isDisplayed()){
		            	logInfo(element.getText() + " element is present");
		            	logInfo(element.getAttribute("value") + " element is present");
		            }
				}
				catch(Exception e){
					logInfo("Failed!! Unable to wait on this element " + locator);		
				}
				
			}	

   		
	// This method is used to get the system date
	
	public static String getSystemDate(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	// This method is used to wait until page being loaded.
	
	public void waitForPageToLoad()
	  {
	     (new WebDriverWait(driver(), DEFAULT_WAIT_TIME)).until(new ExpectedCondition<Boolean>() {
	      public Boolean apply(WebDriver d) {
	        return (((org.openqa.selenium.JavascriptExecutor) driver()).executeScript("return document.readyState").equals("complete"));
	      }
	    });
	 }
	
	//This method is used to check the toaster confirmation messages after create/delete/update actions
	
	public void confirmationMessage(String expectedConfMessage) throws Exception{
		WebDriverWait wait = new WebDriverWait(driver(), 10);		
		try{
			getText("xpath", confirmationMessage, "confirmation message is ");
			WebElement actualConfMsg = driver().findElement(ByXPath.xpath(confirmationMessage));
			Assert.assertEquals(actualConfMsg.getText(), expectedConfMessage);
		}
		catch(Exception e){
			logInfo("Failed!! Unable to capture confirmation message");
		}			
	}
		
	public void tabbing()throws IOException, AWTException {
		Robot rb= new Robot();
		rb.delay(2000);
		//	rb.keyPress(KeyEvent.VK_TAB);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		//	rb.delay(5000);
	}

		// Validate text in the webpage.
		
		public boolean validateTextPresentInPage(String Bytype, String locator, String expText){
			String value = null;
			boolean isTextFound = false;
			
			try{
				switch(Bytype){
				case "xpath":
					element = driver().findElement(ByXPath.xpath(locator));
					break;
				case "id":
					element = driver().findElement(ById.id(locator));
					break;
				case "name":
					element = driver().findElement(ByName.name(locator));
					break;
				case "tagName":
					element = driver().findElement(ByTagName.tagName(locator));
					break;
				case "className":
					element = driver().findElement(ByClassName.className(locator));
					break;
				case "cssSelector":
					element = driver().findElement(ByCssSelector.cssSelector(locator));
					break;
				default :
					System.out.println("Invalid type passed to selectFromDropdown."+locator);
					break;
				}
				
				if(element.isDisplayed() && element.isEnabled()){
					value = element.getText().trim(); 
					if(value.contains(expText)){
						logInfo(value + " match foud in the page.");
					}
				}
				
			} catch(Exception e){
				logInfo(value + " could not be in the page.");
				logger.error("Failed!! Required option is not available or not select drop down list"+locator);		
			}
			return isTextFound;
			
		}
	
		
		// Get text on the element
		
		public String getTextPresentOnElement(String Bytype, String locator) throws Exception{	
			String value = null;
			
			try{
				switch(Bytype){
				case "xpath":
					element = driver().findElement(ByXPath.xpath(locator));
					break;
				case "id":
					element = driver().findElement(ById.id(locator));
					break;
				case "name":
					element = driver().findElement(ByName.name(locator));
					break;
				case "className":
					element = driver().findElement(ByClassName.className(locator));
					break;
				case "cssSelector":
					element = driver().findElement(ByCssSelector.cssSelector(locator));
					break;
				default :
					System.out.println("Invalid type passed to getText."+locator);
					break;
				}
				
				if(element.isDisplayed() && element.isEnabled()){
					value = element.getText().trim();
					System.out.println("value =" +value);
				}
			}
			catch(Exception e){
				logger.error("Failed!! Unable to get the text of an element " + locator);		
			}
			
			return value;
		}
		
		
		 
		 public void scrollDown(String Bytype, String locator) throws Exception{
			 try{
				 js = (JavascriptExecutor)driver();        
				 
				switch(Bytype){
					case "xpath":
						element = driver().findElement(ByXPath.xpath(locator));
						break;
					case "id":
						element = driver().findElement(ById.id(locator));
						break;
					case "name":
						element = driver().findElement(ByName.name(locator));
						break;
					case "className":
						element = driver().findElement(ByClassName.className(locator));
						break;
					case "cssSelector":
						element = driver().findElement(ByCssSelector.cssSelector(locator));
						break;
					default :
						System.out.println("Invalid type passed to clickOnButton."+locator);
						break;
					}
					
					if(element.isDisplayed() && element.isEnabled()){
						 js.executeScript("arguments[0].scrollIntoView(true);", element);
					}
				}
				catch(Exception e){
					logger.error("Failed!! Unable to click on a button."+locator);		
				}
		 }
		 // Replacement of auto-it
		 public void uploadFile(String fileType,String cssLocator) throws Exception{
			 logInfo("inside uploadFile() method.");
			 
			 switch(fileType){
				case "Image":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\\testdata\\files\\icentris_image.jpg");
					break;
				case "Document":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\\testdata\\files\\icentris_doc.doc");
					break;
				case "PDF":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\\testdata\\files\\icentris_pdf.pdf");
					break;
				case "PPT":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("c:\\vibe\\testdata\\files\\icentris_ppt.pptx");
					break;
				case "Text":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\\testdata\\files\\icentris_text.txt");
					break;
				case "Video":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\testdata\\files\\icentris_video.mp4");
					break;
				case "Zip":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\\testdata\\files\\icentris_zip.zip");
					break;
				case "Audio":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\\testdata\\files\\icentris_audio.wav");
					break;
				case "Excel":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\\testdata\\files\\icentris_excel.xlsx");
					break;
				case "GIF":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\\testdata\\files\\icentris_gif.gif");
					break;
				case "MoreThan4MBFile":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\\testdata\\photos\\MoreThan4MbImg.jpg");
					break;
				case "MoreThan4MBPDF":
					driver().findElement(ByCssSelector.cssSelector(cssLocator)).sendKeys("C:\\vibe\\testdata\\files\\5mb_file.pdf");
					break;
				default :
					System.out.println("Invalid File Type mentiods.");
					break;
			 }
		 }
		// Replacement of RObot
		public void composeText(String Bytype, String locator, String text) {
			logInfo("inside composeText() method.");
			
			try{
				 js = (JavascriptExecutor)driver();        
				 
				switch(Bytype){
					case "xpath":
						element = driver().findElement(ByXPath.xpath(locator));
						break;
					case "id":
						element = driver().findElement(ById.id(locator));
						break;
					case "name":
						element = driver().findElement(ByName.name(locator));
						break;
					case "className":
						element = driver().findElement(ByClassName.className(locator));
						break;
					case "cssSelector":
						element = driver().findElement(ByCssSelector.cssSelector(locator));
						break;
					default :
						System.out.println("Invalid type passed to composeText method "+locator);
						break;
					}
					
					WebElement frame = element;
					driver().switchTo().frame(frame);
					WebElement body = driver().findElement(ByTagName.tagName("body"));
					body.sendKeys(text);
					driver().switchTo().defaultContent();
				
				}
				catch(Exception e){
					logger.error("Failed!! Unable to click on a button."+locator);		
				}

		}
		 
		public void confirmBootboxYes() throws Exception, Exception {
			logInfo("inside confirmBootboxYes() method.");
			waitOnElement("xpath","//button[text()='Yes']");
			driver().findElement(ByXPath.xpath("//button[text()='Yes']")).click();
		}
		
		public void confirmBootboxNo() throws Exception, Exception {
			logInfo("inside confirmBootboxNo() method.");
			waitOnElement("xpath","//button[text()='No']");
			driver().findElement(ByXPath.xpath("//button[text()='No']")).click();
		}
		
		// This method is being used to accept javascript alerts / confirmations
		
	public void confirmAlert(){
			logInfo("inside confirmJsAlertOrConfirmation() method.");
			Alert alert = driver().switchTo().alert();
			alert.accept();
		}
		
		// This method is being used to dismiss javascript alerts / confirmations
		
		public void dismissAlert(){
			logInfo("inside cancelJsAlertOrConfirmation() method.");
			Alert alert = driver().switchTo().alert();
			alert.dismiss();
		}
		
		// This method is being used to accept bootbox alert/confirmation boxes
		
		public void confirmOK()throws IOException, AWTException {
			logInfo("Inside confirmOK() method.");
			try{
				waitOnElement("cssSelector", deleteToOk);
				clickOnButton("cssSelector", deleteToOk);
			} 
			catch (Exception e) {
				logInfo("Failed!! unable to click on confirmOK");		
			}
		}
		
		// This method is being used to cancel bootbox alert/confirmation boxes
		public void confirmCancel()throws IOException, AWTException {
			logInfo("Inside confirmCancel() method.");
			try{
				waitOnElement("cssSelector", deleteToCancel);
				clickOnButton("cssSelector", deleteToCancel);
			} 
			catch (Exception e) {
				logInfo("Failed!! unable to click on confirmCancel");		
			}
		}	
		
		public boolean verifyFileExistsOnDisk(String filepath) throws InterruptedException{
			 logInfo("inside verifyFileExistsOnDisk() method");
			 File file = new File(filepath);
			 return file.exists();
		}	

 
		public void userLogin(String userName, String password) throws Exception{
			logInfo("inside userLogin() Method...");    
	        logOut();
	        logIn(userName,password);
		 }
		    
	    public void adminLogin() throws Exception{
	        logInfo("inside adminLogin() Method...");    
	        logOut();
	        logIn(adminUser_text,adminPwd_text);
	    }
		
	   public void loginAsUserFromAdvanced(String firstName, String lastName, String consultantid) throws Exception{           
	        logInfo("inside loginAsUserFromAdvanced() method");  
	    	UsersMethods um = new UsersMethods();                                    
	         um.go2Users(); 
	         waitOnElement("cssSelector", btnAdvancedSearch);
	         clickOnElement("cssSelector", btnAdvancedSearch);
	         waitOnElement("cssSelector", inputAdvConsultantID);
	         inputTextClear("cssSelector", inputAdvFirstName);
	         inputText("cssSelector", inputAdvFirstName, firstName);   
	         inputTextClear("cssSelector", inputAdvLastName);
	         inputTextClear("cssSelector", inputAdvConsultantID);
	         inputText("cssSelector", inputAdvLastName, lastName);	      
	         inputText("cssSelector", inputAdvConsultantID, consultantid);
	         waitOnElement("xpath", btnAdvSubmit);	
		     clickOnElement("xpath", btnAdvSubmit);	
		     waitOnElement("cssSelector", footerMessage);  
		     //clickOnElement("cssSelector", footerMessage); 
	         boolean isUserFound = um.verifyUserPresentWithFname(firstName);
	         if(isUserFound){                  
	                 clickOnLink("linkText", "Login As User");  
	         }
	 
	   }

	  
	  	public void userLogout() throws Exception{
	  			logInfo("inside userLogout() method");
	  			Thread.sleep(5000);
				waitOnElement("cssSelector", logoutHere);		 
				clickOnLink("cssSelector", logoutHere);
		 
	  	}
	    
	  	public void confirmToOk()throws IOException, AWTException {
            logInfo("Entered into confirmToOk() method ");        
            try{
                waitOnElement("cssSelector", btnDeleteResourceOk);
                clickOnButton("cssSelector", btnDeleteResourceOk);
                        
            }
            catch (Exception e) {
                logInfo("Failed!! unable to click on ok button on popup window");        
            }
        }
	    
	    
}
