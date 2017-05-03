package common;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

@Priority(1)
public class LoginCredentials extends TestBase{

	 @Test(priority=101)
	  public void login() throws Exception{	
		 	logInfo("inside login() method");
			logIn(adminUser_text,adminPwd_text);
	  }

	 
	 
}
