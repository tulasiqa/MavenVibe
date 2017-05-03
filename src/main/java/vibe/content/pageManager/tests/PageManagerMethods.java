package vibe.content.pageManager.tests;

import common.TestBase;
import vibe.contacts2.tests.BusinessContactsMethods;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.File;
import java.util.List;

public class PageManagerMethods extends TestBase {	
	
	public void nav2PageManager() throws Exception{			
		driver().navigate().to(appUrl+ "pyr_core/pages/manager/index");		
	}
	
       public void verifyPageManager() throws Exception{			
		logInfo("Inside verifyPageManager() method");	
		
		WebElement tit = driver().findElement(By.cssSelector(pmTitle));
		Assert.assertEquals(tit.getText().trim(), pmTitletext);		
	    }
	
       
      public void verifySiteMap() throws Exception, Exception{    
    	  logInfo("Entered into validateSiteMap() method");
    	  selectAdminUserDropDwon("Start CMS");
    	  toolstoggle(adminUser_text );
    	  cms_content("BASE CONTENT");
    	  toolstoggle("English");
    	  selectLang("CA (English)");
    	  
    	  
    	  
      }
	
      
      public void selectAdminUserDropDwon(String toggleList) throws Exception, Exception{
    	  logInfo("Entered into selectAdminUserDropDwon(String toggleList) method");
    	  waitOnElement("cssSelector", pmAdminUser);
    	  clickOnElement("cssSelector", pmAdminUser);
    	  boolean istoogleFound =false;
    	  List <WebElement> adm = driver().findElements(By.cssSelector(pmAdmUserDP));
    	  for (WebElement dp : adm ){
    		  String dpList = dp.getText().trim();
    		  System.out.println(dp.getText().trim());
    		  
    		  if (dpList.equalsIgnoreCase(toggleList)){
    			  istoogleFound=true;
    			  dp.click();
    			  break;
    			  
    		  }
    		  
    	  }if (istoogleFound==false){
    		  Assert.assertTrue(istoogleFound, toggleList+" is not found under AdminUser");
    	  }  	  
    	  
      }
      
      public void toolstoggle(String toogleName) throws Exception{
    	  
    	  Thread.sleep(5000);
    	  waitOnElement("cssSelector", pmTools);
    	  List <WebElement> tool = driver().findElements(By.cssSelector(pmTools));
    	  System.out.println(tool.size());
    	  for (WebElement list :tool){
    		  String lists = list.getText().trim();
    		  System.out.println(list.getText().trim());
    		  if (lists.contains(toogleName)){    			 
    			  list.click();    			  
    			  break;
    		  }    		  
    	  }    	  
      }
      
      public void selectLang(String languge) throws Exception, Exception{
    	  waitOnElement("cssSelector", pmLangs);
    	  boolean isPresent = false;
    	  List <WebElement> li = driver().findElements(By.cssSelector(pmLangs));
    	  System.out.println(li.size());
    	  for (int i =1 ; i<=li.size(); i++){
    		  WebElement lang = driver().findElement(By.cssSelector(pmLangsBfr+i+pmLangsAfr));
    		  String languages = lang.getText().trim();
    		  if (languages.equalsIgnoreCase(languge)){
    			  isPresent= true;
    			  System.out.println("Lang"+languages);
    			  lang.click();
    			  waitOnElement("cssSelector", pmLangs);
    			  break;
    		  }
    	  }if (isPresent==false){
    		  Assert.assertTrue(isPresent,languge + " - lang is not present" );
    	  }
    	  
      }
		
		
		
	public void cms_content(String content) throws Exception{
	
		waitOnElement("cssSelector", pmContent);
		boolean isCont = false;
		List<WebElement> bas = driver().findElements(By.cssSelector(pmContent));
		System.out.println(bas.size());
		for (WebElement base :bas){		
			String bCont = base.getText().trim();
			System.out.println("bCont"+ bCont);
			if (bCont.equalsIgnoreCase(content)){
				isCont=true;
				base.click();
			}
		}if (isCont==false){
			Assert.assertTrue(isCont, content+ " is not found");
		}
		
		
	}
	
	
	public void assertSiteMapLanguage() throws Exception, Exception{
		logInfo("Entered Into assertSiteMapLanguage() method");		
		Thread.sleep(4000);
		waitOnElement("cssSelector", pmSiteInher1);		
		WebElement whole = driver().findElement(By.cssSelector(pmSiteInher));
		System.out.println(whole.getText().trim());
		String text = whole.getText().trim();			
		if (text.contains(pmSiteInherText)){
			WebElement partial = driver().findElement(By.cssSelector(pmSiteInher));
			String secondText = partial.getText().trim();
			System.out.println(secondText);
			if (secondText.contains(pmSiteInherText2)){
				System.out.println("succesd");				
			}else{
				Assert.assertEquals(secondText, pmSiteInherText2);
			}			
		}else{
			Assert.assertEquals(text, pmSiteInherText);
		}		
	}
	

}
