package vibe.training.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import common.readProp;


@Priority(10)
public class LPGNTraining_Tests extends LPGNTrainingMethods {

	@Test(priority=1001)
	public void addSingleTrainingSeries() throws Exception{
		logInfo("This method adds single training series, which means single category,series and training.");
		go2TrainingPage();
		addTrainingCategory(categoryName,prop.getLocatorForEnvironment(appUrl,"rank"),language1,prop.getLocatorForEnvironment(appUrl,"subPlan"));
		boolean isCategoryFound = verifyCategoryPresent(categoryName);
		if(isCategoryFound){
			go2AddSeriesPage(categoryName);
			logInfo("Adding series without dependency.");
			addTrainingSeries(seriesName1,seriesDesc1);
			boolean isSeriesFound = verifySeriesPresent(seriesName1);
			if(isSeriesFound){
				logInfo("Adding training without dependency.");
				addTraining2Series(seriesName1,trainingTitle,trainingDesc,fileType,uploadPdfPath1);
				boolean isTrainingFound = verifyTrainingPresent(trainingTitle);
				if(!isTrainingFound){
					Assert.assertTrue(isTrainingFound, trainingTitle + " training could not be added.");
				}
			}
			else{
				Assert.assertTrue(isSeriesFound, seriesName1 + " series could not be added.");
			}
		}
		else{
			Assert.assertTrue(isCategoryFound, categoryName + " category could not be added.");
		}
		
		logInfo("Added single training series successfully.");
	}
	
	
	
	@Test(priority=1002)
	public void completeSingleTraining() throws Exception{
		try{
			logInfo("This method completes single training series");
			loginAsUserFromAdvanced(prop.getLocatorForEnvironment(appUrl,"newsFN1"),prop.getLocatorForEnvironment(appUrl,"newsLN1"),prop.getLocatorForEnvironment(appUrl,"newsCon1"));
			go2TrainingUsers();
			logInfo("verify category present");
			boolean isCategoryPresent = verifyCategoryPresentAtUser(categoryName);
			if(isCategoryPresent)
			selectCategory(categoryName);
			logInfo("verify series present");
			boolean isSeriesPresent = verifySeriesAtUser(seriesName1);
			logInfo("verify training present");
			boolean isTrainingPresent = verifyTrainingAtUser(trainingTitle);
			
			if(isCategoryPresent){
				if(isSeriesPresent){
					if(isTrainingPresent){
						logInfo("checking the dependency factor of training series.");
						completeTraining(trainingTitle);
						logInfo("Verifying the training is completed for this series");
						
					}
					else{
						Assert.assertTrue(isTrainingPresent, trainingTitle + " training could not be found.");
					}
				}
				else{
					Assert.assertTrue(isSeriesPresent, seriesName1 + " series could not be found.");
				}
			}
			else{
				Assert.assertTrue(isCategoryPresent, categoryName + " category could not be found.");
			}
			
			logInfo("Completed training successfully.");
			userLogout();
			back2Office();
			adminLogin();
		}
		catch(Exception ex){
			userLogout();
			back2Office();
			adminLogin();
		}
		
		
	}
	
	@Test(priority=1003)
	public void deleteTrainingcategory() throws Exception{
		System.out.println("inside deleteTrainingcategory() Test");
		logInfo("inside deleteTrainingcategory() Test");
		go2TrainingPage();
		deleteCategory(categoryName);
	}
	

}
