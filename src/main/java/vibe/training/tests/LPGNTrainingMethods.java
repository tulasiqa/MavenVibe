package vibe.training.tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.By;
import common.TestBase;
import common.readProp;


public class LPGNTrainingMethods extends TestBase {
	readProp prop = new readProp();
	
	public void go2TrainingPage(){
		logInfo("Inside go2TrainingPage() method");
		driver().navigate().to(appUrl+"pyr_core/training_categories");
	}
	
	public void addTrainingCategory	(String caterogyName,String ranker,String marketLanguage,String subPlan) throws Exception{
		logInfo("Inside addTrainingCategory() method");
		clickOnLink("linkText", btnAddTrainingCategory);
		waitOnElement("id", inputCategoryName);
		inputText("id", inputCategoryName, caterogyName);
		//waitOnElement("xpath","//*[@id='pyr_core_training_category_status']");
	//	selectFromDropdown("xpath", "//*[@id='pyr_core_training_category_status']", "byVisibleText", "Active");
	//	selectRadioOrCheckbox("xpath", "//*[@id='publish_now']");
		
		waitOnElement("xpath", rankDef);
		clickOnElement("xpath", rankDef);
		waitOnElement("xpath", chkRankAll);
		WebElement rank=driver().findElement(By.xpath(chkRankAll));
		if(rank.isSelected()){
			rank.click();
		}
		List<WebElement> chkRankDef = driver().findElements(By.cssSelector(adRankDefineDD));
		for(WebElement element : chkRankDef){
			if(element.getText().equals(ranker)){
				element.click();
				break;
			}
		}

		clickOnElement("xpath", marketLang);
		waitOnElement("xpath", chkMarketsAll);
		WebElement market=driver().findElement(By.xpath(chkMarketsAll));
		if(market.isSelected()){
			market.click();
		}
		List<WebElement> chkMarketLang = driver().findElements(By.cssSelector(adMarketLangChkBox));
		for(WebElement element : chkMarketLang){
			if(element.getText().equals(marketLanguage)){
				element.click();
				break;
			}
		}		

		clickOnElement("xpath", subscriptionPlan);
		waitOnElement("xpath", chkSubscriptionAll);
		WebElement subscription=driver().findElement(By.xpath(chkSubscriptionAll));
		if(subscription.isSelected()){
			subscription.click();
		}
		List<WebElement> chkSubscriptionPlan = driver().findElements(By.cssSelector(adSubPlanChkBox));
		for(WebElement element : chkSubscriptionPlan){
			if(element.getText().equals(subPlan)){
				element.click();
				break;
			}
		}			
		clickOnElement("xpath",btnCreateTrainingCategory);
		logInfo("Training Category has been added successfully.");
	}
	
	public boolean verifyCategoryPresent(String categoryName) throws Exception, Exception{
        logInfo("inside verifyCategoryPresent() method.");
//        Thread.sleep(3000);
        waitOnElement("id",tableTrainingCategories);
        WebElement tblCategories = driver().findElement(By.id(tableTrainingCategories));
        List<WebElement> categories = tblCategories.findElements(By.tagName("a"));
        boolean isCategoryFound = false;
               
        for(WebElement cat : categories){
			if(cat.getText().equalsIgnoreCase(categoryName)){
				isCategoryFound = true;
				logInfo(categoryName + " category found in training categories page.");
				break;
			}
		}
        
        if(isCategoryFound == false){
            logInfo(categoryName + " category not found in training categories page.");
            Assert.assertTrue(isCategoryFound, categoryName + " category not found in training categories page.");
        }
     return isCategoryFound;
    }
	
	public void go2AddSeriesPage(String categoryName){
		try{
		logInfo("Inside go2AddSeriesPage() method");
		go2TrainingPage();
//		Thread.sleep(3000);
		waitOnElement("id",tableTrainingCategories);
		WebElement element = driver().findElement(By.id(tableTrainingCategories));
		List<WebElement> categories = element.findElements(By.tagName("a"));
		
		for(WebElement cat : categories){
			if(cat.getText().equalsIgnoreCase(categoryName)){
				cat.click();
				break;
			}
		}
		logInfo("Getting Training series page successfully.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addTrainingSeries(String seriesName,String description) throws Exception{
		try{
			logInfo("Inside addTrainingSeries() method without dependency.");
			driver().findElement(By.xpath(btnAddSeries)).click();
			inputText("xpath",trainingSeriesName, seriesName );
			inputText("xpath", trainingSeriesDesc, description);
			clickOnButton("xpath", createTrainingSeries);
			logInfo("Added Training Series without dependency successfully.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addTrainingSeries(String seriesName,String description,String dependency[]) throws Exception{
		try{
			logInfo("Inside addTrainingSeries() method with dependency being selected.");
			driver().findElement(By.xpath(btnAddSeries)).click();
			inputText("xpath",trainingSeriesName, seriesName );
			inputText("xpath", trainingSeriesDesc, description);
			Select dependencies = new Select(driver().findElement(By.xpath(trainingSeriesDependency)));
			List<WebElement> options = dependencies.getOptions();
			int oSize = options.size();
			for(int i=0; i < oSize; i++){
				String optionVal=dependencies.getOptions().get(i).getText();
				for(int j=0; j < dependency.length; j++){
					if(optionVal.equalsIgnoreCase(dependency[j].toString())){
						dependencies.selectByVisibleText(dependency[j].toString());
					}
				}
			}
			clickOnButton("xpath", createTrainingSeries);
			logInfo("Added Training Series successfully.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifySeriesPresent(String seriesName) throws Exception, Exception{
        logInfo("inside verifySeriesPresent() method.");
        waitOnElement("id",tableSeries);
        WebElement tblSeries = driver().findElement(By.id(tableSeries));
        List<WebElement> series = tblSeries.findElements(By.tagName("a"));
        boolean isSeriesFound = false;
               
        for(WebElement se : series){
			if(se.getText().equalsIgnoreCase(seriesName)){
				isSeriesFound = true;
				logInfo(seriesName + " series found in training series page.");
				break;
			}
		}
        
        if(isSeriesFound == false){
            logInfo(seriesName + " series not found in training series page.");
            Assert.assertTrue(isSeriesFound, seriesName + " series not found in training series page.");
        }
     return isSeriesFound;
    }
	
	public void addTraining2Series(String seriesName,String title,String desc,String type,String path){
		try{
			logInfo("Inside addTraining2Series() method");
			waitOnElement("xpath",tableSeries);
			WebElement element = driver().findElement(By.id(tableSeries));
			List<WebElement> trainingSeries = element.findElements(By.tagName("a"));
			
			for(WebElement ts : trainingSeries){
				if(ts.getText().equalsIgnoreCase(seriesName)){
					ts.click();
					break;
				}
			}
			clickOnElement("xpath", btnAddTraining);
			waitOnElement("xpath",inputAddVideoTitile);
			inputText("xpath",inputAddVideoTitile, title );
			inputText("xpath",inputAddVideoDesc, desc );
			selectFromDropdown("xpath", selectFileType, "byVisibleText", "PDF");
			uploadFile("PDF",selectFilePath);
			System.out.println(driver().findElement(By.xpath(prop.getLocatorForEnvironment(appUrl,"btnCreateTrainingVideo"))).getText());
			clickOnElement("xpath", prop.getLocatorForEnvironment(appUrl,"btnCreateTrainingVideo"));
			clickOnElement("xpath", backToSeries);
			logInfo("Added training to the series successfully.");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addTraining2Series(String seriesName,String title,String desc,String type,String path,String[] dependency) throws Exception{
		try{
			logInfo("Inside addPdf2Series() method");
			WebElement element = driver().findElement(By.id(tableSeries));
			List<WebElement> trainingSeries = element.findElements(By.tagName("a"));
			
			for(WebElement ts : trainingSeries){
				if(ts.getText().equalsIgnoreCase(seriesName)){
					ts.click();
					break;
				}
			}
			clickOnElement("xpath", btnAddTraining);
			inputText("xpath",inputAddVideoTitile, title );
			inputText("xpath",inputAddVideoDesc, desc );
			selectFromDropdown("xpath", selectFileType, "byVisibleText", "PDF");
			/*Select pdf = new Select(driver().findElement(By.xpath(selectFileType)));
			pdf.selectByVisibleText(type);
			waitOnElement("xPath",selectFilePath);*/
			uploadFile("PDF",selectFilePath);
		/*	driver().findElement(By.cssSelector(selectFilePath)).click();
			Runtime.getRuntime().exec(path);*/
//			Thread.sleep(2000);
			waitOnElement("xpath",trainingDependency);
			Select dependencies = new Select(driver().findElement(By.xpath(trainingDependency)));
			List<WebElement> options = dependencies.getOptions();
			int oSize = options.size();
			
			for(int i=0; i < oSize; i++){
				String optionVal=dependencies.getOptions().get(i).getText();
				for(int j=0; j < dependency.length; j++){
					if(optionVal.equalsIgnoreCase(dependency[j].toString())){
						dependencies.selectByVisibleText(dependency[j].toString());
					}
				}
			}
			clickOnElement("xpath", prop.getLocatorForEnvironment(appUrl,"btnCreateTrainingVideo"));
			clickOnElement("xpath", backToSeries);
			logInfo("Added training to the series with dependency successfully.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean verifyTrainingPresent(String trainingTitle) throws Exception, Exception{
        logInfo("inside verifyTrainingPresent() method.");
        Thread.sleep(5000);
        waitOnElement("id",tableTrainings);
        WebElement tblTrainings = driver().findElement(By.id(tableTrainings));
        List<WebElement> trainings = tblTrainings.findElements(By.tagName("a"));
        boolean isTrainingsFound = false;
               
        for(WebElement tr : trainings){
			if(tr.getText().equalsIgnoreCase(trainingTitle)){
				isTrainingsFound = true;
				logInfo(trainingTitle + " training found in the training page.");
				break;
			}
		}
        
        if(isTrainingsFound == false){
            logInfo(trainingTitle + " training not found in training series page.");
            Assert.assertTrue(isTrainingsFound, trainingTitle + " training not found in the training page.");
        }
     return isTrainingsFound;
    }
	
	public void back2Series(){
		clickOnElement("xpath", back2ListOfSeries);
	}
	
	
	public void go2TrainingUsers() throws IOException, Exception{
		logInfo("Inside go2TrainingUsers() method.");
		System.out.println("Inside go2TrainingUsers() method.");
		driver().navigate().to(appUrl+"pyr_core/training_users");
	}
	
	public boolean verifyCategoryPresentAtUser(String categoryName) throws Exception{
		waitOnElement("xpath",categoryPanel);
		WebElement element = driver().findElement(By.xpath(categoryPanel));
		List<WebElement> trainingCategories = element.findElements(By.tagName("a"));
		boolean isCategoryPresent = false;
		for(WebElement tc : trainingCategories){
			if(tc.getText().equalsIgnoreCase(categoryName)){
				isCategoryPresent = true;
				break;
			}
		}
		return isCategoryPresent;
	}
	
	void selectCategory(String categoryName) throws Exception, Exception {
		waitOnElement("xpath",categoryPanel);
		WebElement element = driver().findElement(By.xpath(categoryPanel));
		List<WebElement> trainingCategories = element.findElements(By.tagName("a"));
		
		for(WebElement tc : trainingCategories){
			if(tc.getText().equalsIgnoreCase(categoryName)){
				tc.click();
				break;
			}
		}
	}

	public boolean verifySeriesAtUser(String seriesName) throws Exception{
		waitOnElement("xpath",seriesPanel);
		Thread.sleep(3000);
		WebElement tblSeries = driver().findElement(By.xpath(seriesPanel));
		List<WebElement> seriesList = tblSeries.findElements(By.xpath(listSeries));
		String before = "//*[@id='main-content']/div/div[3]/div[";
		String after = "]/div[1]/div[2]/a";
		boolean isSeriesPresent = false;
		for(int i=1; i <= seriesList.size(); i++){
			waitOnElement("xpath",before+i+after);
			WebElement eSeriesName = driver().findElement(By.xpath(before+i+after));
			String series = eSeriesName.getText().trim();
			if(series.equalsIgnoreCase(seriesName.trim())){
				isSeriesPresent = true;
				break;
			}
			else{
				isSeriesPresent = false;
			}
		}
		return isSeriesPresent;
	}
	
	
	public boolean verifyTrainingAtUser(String trainingName) throws Exception{
		Thread.sleep(3000);
		waitOnElement("xpath",seriesPanel);
		WebElement tblTrainings = driver().findElement(By.xpath(seriesPanel));
		List<WebElement> trainingsList = tblTrainings.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[1]/div/div[2]/div[";
		String after = "]/a";
		boolean isTrainingPresent = false;
		for(int i=1; i <= trainingsList.size(); i++){
			waitOnElement("xpath",before+i+after);
			WebElement eTrainingName = driver().findElement(By.xpath(before+i+after));
			String training = eTrainingName.getText().trim();
			if(training.equalsIgnoreCase(trainingName.trim())){
				isTrainingPresent = true;
				break;
			}
			else{
				isTrainingPresent = false;
			}
		}
		
		return isTrainingPresent;
	}
	
	public void checkTrainingDependencyError(String trainingName){
		System.out.println("entered checkTrainingDependency");
		WebElement tblTrainings = driver().findElement(By.xpath(seriesPanel));
		List<WebElement> trainingsList = tblTrainings.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[1]/div[2]/div[2]/div[";
		String after = "]/a";
		for(int i=1; i <= trainingsList.size(); i++){
			WebElement eTrainingName = driver().findElement(By.xpath(before+i+after));
			String training = eTrainingName.getText().trim();
			if(training.equalsIgnoreCase(trainingName.trim())){
				eTrainingName.click();
				break;
			}
		}
	}
	
	public void completeTraining(String trainingName) throws Exception{
		logInfo("Inside completeTraining() method.");
		waitOnElement("xpath", seriesPanel);
		WebElement tblTrainings = driver().findElement(By.xpath(seriesPanel));
		List<WebElement> trainingsList = tblTrainings.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[1]/div[";
		String after = "]/div[2]/div/a";
		for(int i=1; i <= trainingsList.size(); i++){
			WebElement eTrainingName = driver().findElement(By.xpath(before+i+after));
			String training = eTrainingName.getText().trim();
			if(training.equalsIgnoreCase(trainingName.trim())){
				eTrainingName.click();
				break;
			}
		}
		waitOnElement("xpath","//*[@id='page']/div[8]/div/div/div[1]/div/div[3]/div/div/button");
		clickOnElement("xpath", "//*[@id='page']/div[8]/div/div/div[1]/div/div[3]/div/div/button");
		WebElement verifyTrainingChecked = driver().findElement(By.cssSelector("input[checked='checked']"));
		if(verifyTrainingChecked.isSelected()){
			logInfo("Successfully completed the training series.");
		}
	}
	
	public void completeAllTrainings() throws Exception{
		logInfo("Inside completeAllTrainings() method.");
		waitOnElement("xpath",seriesPanel);
		WebElement tblSeries = driver().findElement(By.xpath(seriesPanel));
		List<WebElement> list = tblSeries.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[1]/div[";
		String after = "]/div[2]/div/a";
		for(int i=1; i <= tblSeries.findElements((By.xpath(listSeries))).size(); i++){
			
			waitOnElement("xpath",before+i+after);
			List<WebElement> trainings = driver().findElements(By.xpath(before+i+after));
			String before1 = "//*[@id='main-content']/div/div[3]/div[1]/div[";
			String middle = "]/div[2]/div[";
			String after1 = "]/a";
			for(int j=1; j <= trainings.size(); j++){
				waitOnElement("xpath",before1+i+middle+j+after1);
				WebElement tr = driver().findElement(By.xpath(before1+i+middle+j+after1));
				System.out.println(tr.getText());
				tr.click();
				waitOnElement("xpath", closeTrainingWindow);
				clickOnElement("xpath", closeTrainingWindow);
			}
		}
		logInfo("Successfully completed multiple training series.");
		
	}
	
	public boolean verifyAllTrainingsCompleted() throws InterruptedException{
		logInfo("Inside verifyAllTrainingsCompleted() method.");
		WebElement tblSeries = driver().findElement(By.xpath(seriesPanel));
		List<WebElement> list = tblSeries.findElements((By.xpath(listSeries)));
		String before = "//*[@id='main-content']/div/div[3]/div[1]/div[";
		String after = "]/div[2]/div/a";
		boolean isVerifiedAllTrainings = false;
		for(int i=1; i <= tblSeries.findElements((By.xpath(listSeries))).size(); i++){
			List<WebElement> trainings = driver().findElements(By.xpath(before+i+after));
			String before1 = "//*[@id='main-content']/div/div[3]/div[1]/div[";
			String middle = "]/div[2]/div[";
			String after1 = "]/input";
			for(int j=1; j <= trainings.size(); j++){
				WebElement tr = driver().findElement(By.xpath(before1+i+middle+j+after1));
				if(tr.isSelected()){
					isVerifiedAllTrainings = true;
				}
				else{
					isVerifiedAllTrainings = false;
				}
			}
		}
		logInfo("Successfully verified the completion of all training series of a category.");
		System.out.println(isVerifiedAllTrainings);
		return isVerifiedAllTrainings;
	}
	
	public void deleteCategory(String trainingCategory) throws Exception, Exception{
		logInfo("Inside deleteCategory() method.");
		System.out.println("Inside deleteCategory() method.");
		boolean isCategoryFound = true;
		waitOnElement("xpath","//*[@id='training_categories_panel_list']/div");
		List<WebElement> categories = driver().findElements(By.xpath("//*[@id='training_categories_panel_list']/div"));
		
		String before = "//*[@id='training_categories_panel_list']/div[";
		String after = "]/div/div[1]/span/a";
		
		String beforeDelete = "//*[@id='training_categories_panel_list']/div[";
		String afterDelete = "]/div/div[2]/a[3]";
		
		for(int i=1;i<=categories.size();i++){
			waitOnElement("xpath",before+i+after);
			WebElement category = driver().findElement(By.xpath(before+i+after));
			if(category.getText().trim().equalsIgnoreCase(trainingCategory)){
				isCategoryFound = true;
				System.out.println("Trianing category found "+trainingCategory);
				WebElement el = driver().findElement(By.xpath(beforeDelete+i+afterDelete));
				el.click();
				confirmOK();
				confirmationMessage("Training Category has been deleted successfully.");
			}
		}
	
	}
	

}


