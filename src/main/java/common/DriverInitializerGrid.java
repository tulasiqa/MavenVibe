package common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

public class DriverInitializerGrid extends ExtentReportNG {
	
	public DesiredCapabilities capabilities;
	public static ExtentReports extent;
	public static final ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>();
	
	@Parameters({"browser","appUrl"})
	@BeforeTest
	 	
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
			    		//WebDriver driver = new FirefoxDriver(fprofile);	
			    		
			    		capabilities = DesiredCapabilities.firefox();
			    		capabilities.setCapability(FirefoxDriver.PROFILE, fprofile);
			    		capabilities.setPlatform(Platform.WINDOWS);
			    		capabilities.setBrowserName("firefox");
//			    		capabilities.setCapability("maxInstances", 3);
			    		
			    		
			    	}else if(browser.equalsIgnoreCase("chrome")){
			    		 System.setProperty("webdriver.chrome.driver",chrome_driver);
			    		
			    		 HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		    	         chromePrefs.put("profile.default_content_settings.popups", 0);
		    	         chromePrefs.put("download.default_directory", filepath);
		    	         ChromeOptions options = new ChromeOptions();
		    	         HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
		    	         options.setExperimentalOption("prefs", chromePrefs);
			    	       
			    		capabilities = DesiredCapabilities.chrome();
			    		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			    		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			    		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			    		capabilities.setPlatform(Platform.WINDOWS);
			    		capabilities.setBrowserName("chrome");
			    					    		
			    					    		
			    	}else if(browser.equalsIgnoreCase("ie")){
			    		System.setProperty("webdriver.ie.driver",ie_driver);
			    		capabilities = DesiredCapabilities.internetExplorer();
			    		capabilities.setPlatform(Platform.WINDOWS);
			    		
			       	}
			    	webDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities));
			    		    		
		            
			    	webDriver.get().manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			    	webDriver.get().manage().window().maximize();
			    	webDriver.get().get(appUrl);
		    		return webDriver.get();
		    		
				  } catch(Exception e){
				    	e.getMessage();
				    }
				return webDriver.get();
	}

	public WebDriver driver(){
		return webDriver.get();
	}

}
