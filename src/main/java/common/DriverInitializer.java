package common;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class DriverInitializer extends ExtentReportNG {
	
	public static WebDriver driver;
	public static ExtentReports extent;
	
	@Parameters({"browser","appUrl"})
	@BeforeSuite
	 	
	public WebDriver openBrowser(String browser, String appUrl) throws MalformedURLException {
	  
	   //  launch the specified browser 
		    
		        try{
			    	if(browser.equalsIgnoreCase("Firefox")){
			    		    		
			    		FirefoxProfile fprofile = new FirefoxProfile();
			    		
			    		//Set Location to store files after downloading.
			    		fprofile.setPreference("browser.download.dir", filepath);
			    		fprofile.setPreference("browser.download.folderList", 2);
			    		
			    		//Set Preference to not show file download confirmation dialogue using MIME types Of different file extension types.
			    		fprofile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
			    				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"//MIME types Of MS Excel File.
			    				+ "application/pdf;" //MIME types Of PDF File.
			    				+ "application/vnd.openxmlformats-officedocument.wordprocessingml.document;" //MIME types Of MS doc File.
			    				+ "text/plain;" //MIME types Of text File.
			    				+ "text/csv"); //MIME types Of CSV File.
			    		fprofile.setPreference( "browser.download.manager.showWhenStarting", false );
			    		fprofile.setPreference( "pdfjs.disabled", true );
			    		
			    		//Pass fprofile parameter In webdriver to use preferences to download file.
			    		driver = new FirefoxDriver(fprofile);	
			    		driver.manage().window().maximize();
			    		driver.get(appUrl);
			    		return driver;
			    		
			    	}else if(browser.equalsIgnoreCase("chrome")){
			    		System.setProperty("webdriver.chrome.driver",chrome_driver);
			    		driver = new ChromeDriver();
			    		driver.manage().window().maximize();
			    		driver.get(appUrl);
			    		return driver;
			    	}else if(browser.equalsIgnoreCase("ie")){
			    		System.setProperty("webdriver.ie.driver",ie_driver);
			    		driver = new InternetExplorerDriver();
			    		driver.manage().window().maximize();
			    		driver.get(appUrl);
			    		return driver;
			       	}
				  } catch(Exception e){
				    	e.getMessage();
				    }
				return driver;
	}

	
		public static WebDriver driver(){
	    return driver;
	}

	
	
	
}
