package vibe.marketing.companyNews.tests;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;
import common.Priority;
import common.TestBase;
import common.readProp;

import vibe.adminlinks.tests.adminMethods;

@Priority(16)
public class LpgnNews_Tests extends LpgnNewsMethods{
	readProp prop = new readProp();

	//"Create a company News and verifes in office and also in Users widget	
	
	
	@Test(priority=1601)	
	public void createCompanyNewsTest() throws Exception{	
		logInfo("inside createCompanyNews Test");	
		
		navigateCompanyNews();
		addCompanyNews("Company News1000" ,true, true, true);
		boolean isNewsPresent =  verifyCompanyNewsIsPresent("Company News1000");
		if(isNewsPresent==true){
			logInfo("Company News1000" + " Company news is present.");
		} else {
			logInfo("Company News1000" + " Company news is not present.");
			Assert.assertTrue(isNewsPresent, "Company News1000" + " Company news is not present.");
		}
		
	}
	
	@Test(priority=1601)	
	public void deleteCompanyNewsTest() throws Exception{	
		logInfo("inside deleteCompanyNewsTest Test");	
		
		boolean isNewsPresent = verifyCompanyNewsIsPresent("Company News1000");
		if(isNewsPresent== true){
			deleteCompanyNews("Company News1000");
		}
		
		boolean isNewsDeleted = verifyCompanyNewsIsPresent("Company News1000");
		if(isNewsDeleted== true){
			logInfo("Company News1000" + " Company news is not present.");
			Assert.assertTrue(isNewsPresent, "Company News1000" + " Company news is not present.");
			}
		}
	


	
		/*	@Test(priority=1601)	
			public void news_VerifyInOfficeAndUser() throws Exception{	
				logInfo("Entered into news_VerifyInOfficeAndUser test");	
				
				// String title = "Maximizing Social Media Marketing By 378 Ways";
				 String[] subscripList = null;
				 if (!(driver().getCurrentUrl().contains("monat"))){
					 subscripList = subscripListOfVibe;
				 	}else{
				 		subscripList = subscripListOfMonat;
				 	}
				 companyNewsCreation(title,excerpt, ranker5,languageList,subscripList);			
				 back2Office();
				 dragNewswidgets();
				 selectViewMoreLink();
				 verifyNewsInWidget("News", title);
				 verifyNewsInWidget("All", title);
				 verifyNewsInWidget("Highlights", title);
						 
			}
			
		
		// unpublish news and verifies in Office side, also in amin sorts with draft.
		//	+ deletes and verifies after that
		@Test(priority=1602)	
		public void news_SaveAsDraftndVerifyUserSide() throws Exception{
			logInfo("Create a company News with Title - '"+title5+"' for subscribers of ' "+subProMonthly +"' .");
			
			String[] subscripList = null;
			 if (!(driver().getCurrentUrl().contains("monat"))){
				 subscripList = subscripListOfVibe;
			 	}else{
			 		subscripList = subscripListOfMonat;
			 	}
			 createNewsAsDraft(title5,excerpt2, ranker5,languageList,subscripList);			 
			 navigateCompanyNews();	
			 sortStatus(st2);
			 isNewsTitlePresent(title5);	
			 sortStatus(st1);
			 isNewsTitleNotToPresent(title5);	 
			 back2Office();			 
			 selectViewMoreLink();		
			 verifyNewsNotToPresentInWidget("All",title5);			 
			 navigateCompanyNews();
	       	 clickNewsIconBasedOnExcerpts(excerpt2);
	       	 deleteNewsFromImageIcon();	
	       	 confirmationMessage("Company News is deleted");        //"Company news was successfully destroyed.");
	       	 isNewsTitleNotToPresent(title5);
		}
	
	//Creation of News with Subscription- Monthly .\n"
	//+ "The NEWS should be present in Company News widget of users whose has mothly Subcriptions.\n"
	//+ "Again check, the NEWS should not be present in Company News widget of users whose has Yearly Subcriptions.");	
	@Test (priority =1603,dependsOnMethods = {"news_VerifyInOfficeAndUser"})	
	public void news_VerifyMonthlyNewswithMonthlyNdYearlyUsers() throws Exception{
		logInfo("Entered into news_VerifyMonthlyNewswithMonthlyNdYearlyUsers() test");		
		 String[] subscript = null;
		 if ((driver().getCurrentUrl().contains("monat"))){
			 subscript = subProMonthlyUs;
		 	}else if((driver().getCurrentUrl().contains("master"))) {
		 	 subscript = subProMonthly;
		 	}else{
		 		subscript = subLite;
		 	}
		modifyUsersSubscription(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
				                prop.getLocatorForEnvironment(appUrl,"newsCon1"), prop.getLocatorForEnvironment(appUrl,"singleSubscription1"));
		modifyUsersSubscription(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
                                prop.getLocatorForEnvironment(appUrl,"newsCon2"), prop.getLocatorForEnvironment(appUrl,"singleSubscription2"));
		
		updateNewsWithSubscription(title,subscript);	
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
				                prop.getLocatorForEnvironment(appUrl,"newsCon1"));
		
		dragNewswidgets();
		verifyNewsInWidget("All", title);
		userLogout();
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
                prop.getLocatorForEnvironment(appUrl,"newsCon2"));
        dragNewswidgets();
        verifyNewsNotToPresentInWidget("All", title);
        userLogout();			
		}
	
	
         //"Creation of News with Subscription- Yearly .\n"
		//	+ "The NEWS should be present in Company News widget of users whose has Yearly Subcriptions.\n"
		//	+ "Again check, the NEWS should not be present in Company News widget of users whose has Monthly Subcriptions.\n"
		//	+ "Verify the same news in Recent Posts list");
	@Test(priority=1604,dependsOnMethods = {"news_VerifyInOfficeAndUser"})
	public void news_VerifyYearlyNewswithYearlyNdMonthlyUsers() throws Exception{	
		logInfo("Entered into news_VerifyYearlyNewswithYearlyNdMonthlyUsers() test");
		String title ="Future comes in 81";
		String[] subscript = null;
		 if ((driver().getCurrentUrl().contains("monat"))){
			 subscript = subProMonthlyCA;
		 	}else if((driver().getCurrentUrl().contains("master"))) {
		 	 subscript = subProYear;
		 	}else{
		 		subscript = subProBundle;
		 		}				
		updateNewsWithSubscription(title,subscript );	
		loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN2"),prop.getLocatorForEnvironment(appUrl,"newsLN2"),
                prop.getLocatorForEnvironment(appUrl,"newsCon2"));
		selectViewMoreLink();
        verifyNewsInWidget("All", title);
        userLogout();	
        loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),
                prop.getLocatorForEnvironment(appUrl,"newsCon1"));

        selectViewMoreLink();
		verifyNewsNotToPresentInWidget("All", title);
		userLogout();		
	}
	
	//Verify the created NEWS in main screen and Edit that NewS and update by uploading with PDF.
	@Test (priority =1605)		
	public void news_VerifyUpdateNews() throws Exception{	   
		logInfo("Entered into news_VerifyUpdateNews() test");
	        String title = "UnPublished News 368";	
		    String pdfFile = "icentris_pdf.pdf"; 
		    navigateCompanyNews();
		    isNewsTitlePresent(title);			  	  
		    editNews(title);		      
		    updateNewsWithFile();	
		    isNewsTitlePresent(title);			  	  
		    editNews(title);
		    verifyAdditionalInformation(pdfFile);	
	}
	
	//Create News with future Date. This News should not be published 
	//until Date matches with system Date.
	@Test(priority=1606)
	public void news_FutureNewsNdVerifyinWidget() throws Failure, Exception{
		logInfo("Entered into news_FutureNewsNdVerifyinWidget() test");				
		String[] subscripList = null;
		 if (!(driver().getCurrentUrl().contains("monat"))){
			 subscripList = subscripListOfVibe;
		 	}else{
		 		subscripList = subscripListOfMonat;
		 	}
		 navigateCompanyNews();		 
		 createNewsWithDate(title3, ranker5,languageList, subscripList,3);
	     back2Office();	
	     selectViewMoreLink();
	     verifyNewsNotToPresentInWidget("All", title3);	
	     
	  }		
	
	// Create News with todays date and verify presence in Widgets
	@Test(priority=1606)
	public void news_PresentNewsNdVerifyinWidget() throws Failure, Exception{
		logInfo("Entered into news_PresentNewsNdVerifyinWidget() test");	
		String[] subscripList = null;
		 if (!(driver().getCurrentUrl().contains("monat"))){
			 subscripList = subscripListOfVibe;
		 	}else{
		 		subscripList = subscripListOfMonat;
		 	}
		 navigateCompanyNews();		 
		 createNewsWithDate(title4, ranker5,languageList, subscripList,0);
	     back2Office();	
	     selectViewMoreLink();
	     verifyNewsInWidget("All", title4);	
	     verifyNewsInWidget("Highlights", title4);	
	     verifyNewsInWidget("News", title4);	
	  }
	
	
		
		
		// Deletes teh company news based on excerpt
		@Test (priority =1630)		
		public void news_DeleteNews() throws Exception{
			logInfo("Delete the Company News ");	
			//String excerpt = "Social media can improve your word of mouth marketing with 240 Ways";
	       	 navigateCompanyNews();
	       	 clickNewsIconBasedOnExcerpts(excerpt);
	       	 deleteNewsFromImageIcon();	
	       	 confirmationMessage("Company News is deleted");        //"Company news was successfully destroyed.");
	       	 isNewsTitleNotToPresent(title);

		}
		
		
		//String title ="Maximizing Social Media Marketing By 459 Ways";
		// Sort- marketing - Verifies with market and langagues 
		@Test(priority=1607)
		public void news_SortingNewsWithMarket () throws Exception{
			logInfo("Entered into news_SortingNewsWithMarket() test");
			 navigateCompanyNews();
			 sortMarket("US");
			 isNewsTitlePresent(title);
			 sortLanguages("Spanish");
			 isNewsTitleNotToPresent(title);
			 updateNewsWithMarket(title, languageList2);
			 sortMarket("US");
	    	 isNewsTitleNotToPresent(title);
			 sortMarket("CA");
			 isNewsTitlePresent(title); 
			 sortLanguages("Spanish");
			 isNewsTitleNotToPresent(title);
			 	 
		}*/
		
		

		
		
}
		
