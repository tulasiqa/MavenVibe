package vibe.training.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.Priority;
import common.TestBase;
import common.readProp;


@Priority(10)
public class Training_Tests extends TrainingMethods {

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
	
	//@Test(priority=1002)
	public void addMultipleTrainingSeries() throws Exception{
		logInfo("This method adds multiple training series, which means single category, multiple series and multiple trainings.");
		go2TrainingPage();
		addTrainingCategory(categoryName2,prop.getLocatorForEnvironment(appUrl,"rank"),language1,prop.getLocatorForEnvironment(appUrl,"subPlan"));
		//addTrainingCategory(categoryName2,ranker2,language1,subs1);
		boolean isCategory2Found = verifyCategoryPresent(categoryName2);
		if(isCategory2Found){
			go2AddSeriesPage(categoryName2);
			
			// Add training series without dependency
			logInfo("Adding series without dependency.");
			addTrainingSeries(seriesName1,seriesDesc1);
			boolean isSeriesFound = verifySeriesPresent(seriesName1);
			if(isSeriesFound){
				addTraining2Series(seriesName1,trainingTitle,trainingDesc,fileType,uploadPdfPath1);
//				Thread.sleep(8000);
				boolean isTrainingFound = verifyTrainingPresent(trainingTitle);
				if(!isTrainingFound){
					Assert.assertTrue(isTrainingFound, trainingTitle + " training could not be added.");
				}
			}
			else{
				Assert.assertTrue(isSeriesFound, seriesName1 + " series could not be added.");
			}
			
			// Add training series with dependency
			go2AddSeriesPage(categoryName2);
			logInfo("Adding series with dependency.");
			addTrainingSeries(seriesName2,seriesDesc2,seriesWithDependency1);
			boolean isSeries2Found = verifySeriesPresent(seriesName2);
			if(isSeries2Found){
				// Add training with dependency
				addTraining2Series(seriesName2,trainingTitle,trainingDesc,fileType,uploadPdfPath1);
//				Thread.sleep(8000);
				boolean isTraining2Found = verifyTrainingPresent(trainingTitle);
				if(isTraining2Found){
					back2Series();
					addTraining2Series(seriesName2,trainingTitle2,trainingDesc2,fileType,uploadPdfPath1,trainingWithDependency1);
//					Thread.sleep(8000);
				}
				else{
					Assert.assertTrue(isTraining2Found, trainingTitle2 + " training could not be added.");
				}
			}
			else{
				Assert.assertTrue(isSeries2Found, seriesName2 + " series could not be added.");
			}
			
			// Add training series with multiple dependencies
			go2AddSeriesPage(categoryName2);
			logInfo("Adding series with multiple dependencies.");
			addTrainingSeries(seriesName3,seriesDesc3,seriesWithDependency2);
			boolean isSeries3Found = verifySeriesPresent(seriesName3);
			if(isSeries3Found){
				
				// Add training with multiple dependencies
				addTraining2Series(seriesName3,trainingTitle,trainingDesc,fileType,uploadPdfPath1);
//				Thread.sleep(8000);
				boolean isTraining3Found = verifyTrainingPresent(trainingTitle);
				if(isTraining3Found){
					back2Series();
					addTraining2Series(seriesName3,trainingTitle2,trainingDesc2,fileType,uploadPdfPath1,trainingWithDependency1);
//					Thread.sleep(8000);
					back2Series();
					addTraining2Series(seriesName3,trainingTitle3,trainingDesc3,fileType,uploadPdfPath1,trainingWithDependency2);
//					Thread.sleep(8000);
				}
				else{
					Assert.assertTrue(isTraining3Found, trainingTitle3 + " training could not be added.");
				}

			}
			else{
				Assert.assertTrue(isSeries3Found, seriesName3 + " series could not be added.");
			}
			
		}
		else{
			Assert.assertTrue(isCategory2Found, categoryName2 + " category could not be added.");
		}
		
		logInfo("Added multiple training series successfully.");
	}
	
	@Test(priority=1003)
	public void verifySeriesDependency() throws Exception{
		logInfo("This method verifies whether the first series is completed before going to the second series.");
		go2TrainingUsers();
		logInfo("verify category present at user side");
		boolean isCategoryPresent = verifyCategoryPresentAtUser(categoryName2);
		if (isCategoryPresent)
			selectCategory(categoryName2);
		logInfo("verify series present at user side");
//		Thread.sleep(5000);
		boolean isSeriesPresent = verifySeriesAtUser(seriesName2);
		logInfo("verify training present at user side");
//		Thread.sleep(3000);
		boolean isTrainingPresent = verifyTrainingAtUser(trainingTitle2);
		
		if(isCategoryPresent){
			if(isSeriesPresent){
				if(isTrainingPresent){
					logInfo("checking the dependency factor of training series.");
					checkTrainingDependencyError(trainingTitle2);
					logInfo("Verifying the confirmation message when the series depend on some other series.");
//					Thread.sleep(3000);
					confirmationMessage("Please Complete the dependent series: " + seriesName1);
				}
				else{
					Assert.assertTrue(isTrainingPresent, trainingTitle2 + " training could not be found.");
				}
			}
			else{
				Assert.assertTrue(isSeriesPresent, seriesName2 + " series could not be found.");
			}
		}
		else{
			Assert.assertTrue(isCategoryPresent, categoryName2 + " category could not be found.");
		}
		
		logInfo("verified confirmation message after checking the series dependency.");
		
	}
	
	@Test(priority=1004)
	public void completeSingleTraining() throws Exception{
		try{
			logInfo("This method completes single training series");
			//userLogin(prop.getLocatorForEnvironment(appUrl,"User"),prop.getLocatorForEnvironment(appUrl,"Passwd"));
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
		}
		catch(Exception ex){
			userLogout();
		}
		
		
	}

	@Test(priority=1005)
	public void completeMultipleTrainings() throws Exception{
		
		logInfo("This method completes all training series of a category");
		go2TrainingUsers();
		logInfo("verify category present"); 
		boolean isCategoryPresent = verifyCategoryPresentAtUser(categoryName2);
		if(isCategoryPresent){
			selectCategory(categoryName2);
			completeAllTrainings();
			boolean isVerifyAllTrainings = verifyAllTrainingsCompleted();
			if(isVerifyAllTrainings){
				logInfo("Completed all trainings successfully.");
			}
			else{
				Assert.assertTrue(isVerifyAllTrainings, "Unable to complete all the series in this category : " + categoryName2);
			}
			
		}
		else{
			Assert.assertTrue(isCategoryPresent, categoryName2+" category could not be found.");
		}
		logInfo("Completed multiple training series successfully.");
	}
	
	@Test(priority=1006)
	public void deleteTrainingcategory() throws Exception{
			System.out.println("inside deleteTrainingcategory() Test");
			logInfo("inside deleteTrainingcategory() Test");
			go2TrainingPage();
			deleteCategory(categoryName);
				
	}


	@Test(priority=1002)
	public void vtup_addSingleTrainingSeries() throws Exception{
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
				vtup_addTraining2Series(seriesName1,trainingTitle,trainingDesc,fileType,uploadPdfPath1);
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
	

	
	
	
	
}
