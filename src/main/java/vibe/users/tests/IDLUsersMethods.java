package vibe.users.tests;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import common.TestBase;
import vibe.inbox.tests.InboxMethods;
import vibe.mywebsite.tests.MyWebSite_Methods;

public class IDLUsersMethods extends TestBase {

	InboxMethods inbox = new InboxMethods();
	MyWebSite_Methods wm = new MyWebSite_Methods();
	
	String userEmail_text = "enroll16@icentris.com";
	String consultantId = "11170155";
	String enrollUsername = "enrolluser1";
	
	public void go2Users() {
		logInfo("inside go2Users() method.");
		driver().navigate().to(appUrl + "pyr_core/users");
	}
	
	public void go2AdminUsers() {
		logInfo("inside go2AdminUsers() method.");
		driver().navigate().to(appUrl + "pyr_core/users/admin_users");
	}
	
	public void go2PasswordSettings() {
		logInfo("inside go2PasswordSettings() method.");
		driver().navigate().to(appUrl + "pyr_core/pyr_admin/password_settings");
	}

	public static String getEmail(String userName, String url) {

		String uName = userName.trim();
		String[] urlparts = url.split("/");
		String urlpart1 = urlparts[0];
		String urlpart2 = urlparts[2];
		String email = userName + "@" + urlpart2;
		return email;
	}

	public void addAdminUser(String uName, String email) throws Exception, Exception {
		logInfo("inside addUser() method.");
		clickOnLink("linkText", "New Admin User");
		waitOnElement("cssSelector", inputFirstName);
		inputText("cssSelector", inputFirstName, txtFirstName);
		inputText("cssSelector", inputLastName, txtLastName);
		inputText("xpath", inputUserEmail, email);
		inputText("xpath", inputUserName, uName);
		inputText("xpath", inputUserPassword, userPassword_text);
		inputText("xpath", inputUserPasswdConfirm, userPassword_text);
		selectFromDropdown("xpath", drpdnUserSecurityGrps, "byVisibleText", userSecurityGrps_text);
		clickOnElement("xpath", idlBtnCreateUser);
	}

	public void searchUser(String uName) throws Exception, Exception {
		logInfo("inside searchUser() method.");
		waitOnElement("xpath", searchUser);
		inputTextClear("xpath", searchUser);
		inputText("xpath", searchUser, uName + " ");
		waitOnElement("cssSelector", btnUserSearch);
		clickOnButton("cssSelector", btnUserSearch);
	}
	
	public void searchAdminUser(String uName) throws Exception, Exception {
		logInfo("inside searchAdminUser() method.");
		go2AdminUsers();
		waitOnElement("xpath", searchAdminUser);
		inputTextClear("xpath", searchAdminUser);
		inputText("xpath", searchAdminUser, uName);
	}

	public boolean verifyUserPresent(String uName) throws Exception, Exception {
		logInfo("inside verifyUserPresent() method.");
		Thread.sleep(5000);
		waitOnElement("xpath", tblAdminUsers);
		WebElement tblUsers = driver().findElement(By.xpath(tblAdminUsers));
		List allUsers = tblUsers.findElements(By.tagName("tr"));
		String before_name = "//*[@id='users']/table/tbody/tr[";
		String after_name = "]/td[4]/a";
		boolean isUserFound = false;
		if (allUsers.size() > 0) {
			for (int i = 1; i <= allUsers.size(); i++) {
				waitOnElement("xpath","//*[@id='users']/table/tbody/tr");
				WebElement tblmsg = driver().findElement(By.xpath("//*[@id='users']/table/tbody/tr"));
				List msgrow = tblmsg.findElements(By.tagName("td"));
				System.out.println("Sixe is "+msgrow.size());
				if (msgrow.size() > 1) {
					waitOnElement("xpath",before_name + i + after_name);
					WebElement x = driver().findElement(By.xpath(before_name + i + after_name));
					String name = x.getText().trim();
					System.out.println("Name"+name);
					if (name.equalsIgnoreCase(uName.trim())) {
						isUserFound = true;
						logInfo(uName + " user found in user search page.");
						break;
					}
				}
			}
		}

		if (isUserFound == false) {
			logInfo(uName + " user not found in user search page.");
			Assert.assertTrue(isUserFound, uName + " user not found in user search page.");
		}
		return isUserFound;
	}
	
	public boolean verifyAdminUserPresent(String uName) throws Exception, Exception {
		logInfo("inside verifyAdminUserPresent() method.");
		System.out.println("inside verifyAdminUserPresent() method.");
		Thread.sleep(10000);
		waitOnElement("xpath", lstAdminUsers);
		WebElement tblAdminUsers = driver().findElement(By.xpath(lstAdminUsers));
		List allAdminUsers = tblAdminUsers.findElements(By.tagName("tr"));
		String before_name = "//*[@id='admin_users']/tbody/tr[";
		String after_name = "]/td[3]/a";
		boolean isAdminUserFound = false;
		if (allAdminUsers.size() > 0) {
			for (int i = 1; i <= allAdminUsers.size(); i++) {
				waitOnElement("xpath",before_name + i + after_name);
				WebElement x = driver().findElement(By.xpath(before_name + i + after_name));
				String name = x.getText().trim();
				verifyElementPresent("xpath", before_name + i + after_name);
				if (name.equalsIgnoreCase(uName)) {
					isAdminUserFound = true;
					logInfo(uName + " user found in admin user search page.");
					break;
				}

			}
		}

		if (isAdminUserFound == false) {
			logInfo(uName + " user not found in admin user search page.");
			Assert.assertTrue(isAdminUserFound, uName + " user not found in admin user search page.");
		}
		return isAdminUserFound;
	}

	public boolean logInAsUser(String uName) throws Exception, Exception {
		logInfo("inside verifyUserPresent() method.");
		waitOnElement("xpath", tblAdminUsers);
		WebElement tblUsers = driver().findElement(By.xpath(tblAdminUsers));
		List allUsers = tblUsers.findElements(By.tagName("tr"));
		String before_name = "//*[@id='users']/table/tbody/tr[";
		String after_name = "]/td[3]/a";

		String before_login = "//*[@id='users']/table/tbody/tr[";
		String after_login = "]/td[12]/a";

		boolean isUserFound = false;
		if (allUsers.size() > 0) {
			for (int i = 1; i <= allUsers.size(); i++) {
				WebElement tblmsg = driver().findElement(By.xpath("//*[@id='users']/table/tbody/tr"));
				List msgrow = tblmsg.findElements(By.tagName("td"));
				if (msgrow.size() > 1) {
					WebElement x = driver().findElement(By.xpath(before_name + i + after_name));
					String name = x.getText().trim();
					verifyElementPresent("xpath", before_name + i + after_name);
					if (name.equalsIgnoreCase(uName)) {
						isUserFound = true;
						logInfo(uName + " user found in user search page.");
						WebElement loginUser = driver().findElement(By.xpath(before_login + i + after_login));
						loginUser.click();
						break;
					}
				}
			}
		}

		if (isUserFound == false) {
			logInfo(uName + " user not found in user search page.");
			Assert.assertTrue(isUserFound, uName + " user not found in user search page.");
		}
		return isUserFound;
	}

	public void editUser(String uName) throws Exception, Exception {
		logInfo("inside editUser() method.");
		waitOnElement("xpath", tblAdminUsers); // check and remove later
		WebElement tblUsers = driver().findElement(By.xpath(tblAdminUsers));
		List allUsers = tblUsers.findElements(By.tagName("tr"));
		String before_name = "//*[@id='users']/table/tbody/tr[";
		String after_name = "]/td[4]/a";
		boolean isUserFound = false;
		if (allUsers.size() > 0) {
			for (int i = 1; i <= allUsers.size(); i++) {
				WebElement x = driver().findElement(By.xpath(before_name + i + after_name));
				waitOnElement("xpath", before_name + i + after_name); 
				String name = x.getText().trim();
				if (name.equalsIgnoreCase(uName)) {
					isUserFound = true;
					logInfo(uName + " user found in user search page.");
					x.click();
					break;
				}
			}
		}

		if (isUserFound == false) {
			logInfo(uName + " user not found in user search page.");
		}
	}
	
	public void editAdminUser(String uName) throws Exception, Exception {
		logInfo("inside editAdminUser() method.");
		waitOnElement("xpath", lstAdminUsers);
		WebElement tblAdminUsers = driver().findElement(By.xpath(lstAdminUsers));
		List allAdminUsers = tblAdminUsers.findElements(By.tagName("tr"));
		String before_name = "//*[@id='admin_users']/tbody/tr[";
		String after_name = "]/td[3]/a";
		boolean isAdminUserFound = false;
		
		if (allAdminUsers.size() > 0) {
			for (int i = 1; i <= allAdminUsers.size(); i++) {
				WebElement x = driver().findElement(By.xpath(before_name + i + after_name));
				waitOnElement("xpath", before_name + i + after_name); 
				String name = x.getText().trim();
				if (name.equalsIgnoreCase(uName)) {
					isAdminUserFound = true;
					logInfo(uName + " user found in admin user search page.");
					x.click();
					break;
				}
			}
		}

		if (isAdminUserFound == false) {
			logInfo(uName + " user not found in user search page.");
		}
	}

	public void addNotes(String uName) throws Exception, Exception {
		logInfo("inside addNotes() method.");

		waitOnElement("linkText", "Add Note");
		clickOnLink("linkText", "Add Note");

		inputText("xpath", inputUserNotes, uName);
		clickOnElement("xpath", btnUserCreateNote);
	}

	public void changeUserName(String firstName, String lastName) throws Exception {
		logInfo("inside changeUserName() method.");
		waitOnElement("linkText", "Edit User");
		clickOnLink("linkText", "Edit User");
		waitOnElement("xpath", inputUserFName);
		inputTextClear("xpath", inputUserFName);
		inputText("xpath", inputUserFName, firstName);
		waitOnElement("xpath", inputUserLName);
		inputTextClear("xpath", inputUserLName);
		inputText("xpath", inputUserLName, lastName);
		clickOnElement("xpath", btnUpdateUser);
		confirmationMessage("User was successfully updated.");
	}

	public void changeUserPasswd(String userName) throws Exception {
		logInfo("inside changeUserPasswd() method.");

		clickOnLink("linkText", "Reset Password");
		confirmAlert();
	}

	public void addUserRoles(String roleName) throws Exception {
		logInfo("inside addUserRoles() method");

		driver().navigate().to(appUrl + "pyr_core/roles/user#");

		waitOnElement("linkText", "Add Role");
		clickOnLink("linkText", "Add Role");

		inputText("xpath", inputRoleName, roleName_text);
		inputText("xpath", inputRoleDesc, roleDesc_text);
		clickOnElement("cssSelector", btnOK);
	}

	public void uncheckAllRoles() throws Exception {
		logInfo("inside uncheckAllRoles() method");

		// verifyLinkPresent("Manage Admin Roles");
		clickOnLink("linkText", "Manage Admin Roles");

		WebElement x = driver().findElement(By.xpath(manageSecurityGroups));
		List allGroups = x.findElements(By.tagName("span"));
		int all_grps = allGroups.size();

		String before_label = "//*[@class='form-group check_boxes optional user_security_groups']/span[";
		String after_label = "]/label/input";
		boolean isChecked = false;
		for (int i = 1; i <= all_grps; i++) {
			WebElement label = driver().findElement(By.xpath(before_label + i + after_label));

			isChecked = label.isSelected();
			if (isChecked == true) {
				String lblName = label.getText().trim();
				System.out.println("Uncheck Label =" + lblName);
				label.click();
			}
		}

		clickOnElement("xpath", btnUpdateUser);
	}

	public void addRoles2User(String[] roles) throws Exception {
		logInfo("inside addRoles2User() method");

		uncheckAllRoles();

		verifyLinkPresent("Manage Admin Roles");
		clickOnLink("linkText", "Manage Admin Roles");

		WebElement x = driver().findElement(By.xpath(manageSecurityGroups));
		List allGroups = x.findElements(By.tagName("span"));
		int all_grps = allGroups.size();

		String before_label = "//*[@class='form-group check_boxes optional user_security_groups']/span[";
		String after_label = "]/label";

		int all_roles = roles.length;

		for (int i = 1; i <= all_grps; i++) {
			WebElement label = driver().findElement(By.xpath(before_label + i + after_label));
			String lblName = label.getText().trim();
			System.out.println("Name =" + lblName);

			for (String ele : roles) {
				System.out.println("Array Name =" + ele);
				if (ele.equalsIgnoreCase(lblName)) {
					label.click();
					break;
				}
			}
		}

		clickOnElement("xpath", btnUpdateUser);
		confirmationMessage("User Roles was successfully updated.");
	}
	
	public boolean verifyUserRole(String[] roles) throws Exception{
		logInfo("inside verifyUserRole() method");
		System.out.println("inside verifyUserRole() method");
		boolean isVerified = false;
		
		verifyLinkPresent("Manage Admin Roles");
		clickOnLink("linkText", "Manage Admin Roles");
		
		WebElement x = driver().findElement(By.xpath(manageSecurityGroups));
		List allGroups = x.findElements(By.tagName("span"));
		int all_grps = allGroups.size();

		String before_label = "//*[@class='form-group check_boxes optional user_security_groups']/span[";
		String after_label = "]/label";

		int all_roles = roles.length;

		for (int i = 1; i <= all_grps; i++) {
			WebElement label = driver().findElement(By.xpath(before_label + i + after_label));
			String lblName = label.getText().trim();
			System.out.println("Name =" + lblName);

			for (String ele : roles) {
				System.out.println("Array Name =" + ele);
				if (ele.equalsIgnoreCase(lblName)) {
					if(label.isSelected()){
						isVerified = true;
						break;
					}
					
				}
			}
		}
		return isVerified;
	}
 
	public boolean verifySortOrderCols(String fname, String lname, String userName, String ID, String userEmail,
			String isActive) throws Exception, Exception {
		logInfo("inside verifySortOrderCols() method");
		System.out.println("inside verifySortOrderCols() method");
		boolean isSortOrderVerified = false;
		
		waitOnElement("xpath", adminRow);
		WebElement el = driver().findElement(By.xpath(adminRow));

		clickOnElement("xpath", sortFirstName);
		waitOnElement("xpath", adminRow);
		String firstName = driver().findElement(By.xpath(txtUserFirstName)).getText();
		clickOnElement("xpath", sortLastName);
		waitOnElement("xpath", adminRow);
		String lastName = driver().findElement(By.xpath(txtUserLastName)).getText();
		clickOnElement("xpath", sortUserName);
		waitOnElement("xpath", adminRow);
		String user = driver().findElement(By.xpath(txtUserName)).getText();
		/*clickOnElement("xpath", sortID);
		waitOnElement("xpath", adminRow);
		String id = driver().findElement(By.xpath(txtUserID)).getText();
		clickOnElement("xpath", sortEmail);
		waitOnElement("xpath", adminRow);
		String email = driver().findElement(By.xpath(txtUserEmail)).getText();*/
		waitOnElement("xpath", adminRow);
		clickOnElement("xpath", sortByStatus);
		waitOnElement("xpath", adminRow);
		String active = driver().findElement(By.xpath(txtUserStatus)).getText();
		/*if (firstName.trim().equalsIgnoreCase(fname) && lastName.trim().equalsIgnoreCase(lname)
				&& user.trim().equalsIgnoreCase(userName) && id.trim().equalsIgnoreCase(ID)
				&& email.trim().equalsIgnoreCase(userEmail) && active.trim().equalsIgnoreCase(isActive)) {
			isSortOrderVerified = true;
		}*/
		
		if (firstName.trim().equalsIgnoreCase(fname) && lastName.trim().equalsIgnoreCase(lname)
				&& user.trim().equalsIgnoreCase(userName) && active.trim().equalsIgnoreCase(isActive)) {
			isSortOrderVerified = true;
		}
		return isSortOrderVerified;

	}

	public boolean verifyLoggedInUser(String userName) throws Exception, Exception {
		logInfo("inside verifySortOrderCols() method");
		System.out.println("inside verifySortOrderCols() method");
		boolean isVerified = false;
		logInAsUser(userName);
		waitOnElement("cssSelector", txtLoggedInMsg);
		String loggedInMsg = driver().findElement(By.cssSelector(txtLoggedInMsg)).getText().trim();
		if (loggedInMsg.contains("You are logged in as ")) {
			isVerified = true;
		}
		if (isVerified) {
			waitOnElement("cssSelector", txtClickHere);
			clickOnElement("cssSelector", txtClickHere);
			waitOnElement("cssSelector", txtpageTitle);
			String panelTitle = driver().findElement(By.cssSelector(txtpageTitle)).getText().trim();
			if (panelTitle.contains("User Management")) {
				isVerified = true;
			}
		}
		return isVerified;
	}

	public boolean verifyUserDetailsPage(String userName) throws Exception, Exception {
		logInfo("inside verifyUserDetailsPage() method");
		System.out.println("inside verifyUserDetailsPage() method");
		boolean isDetailsVerified = false;

		waitOnElement("xpath", userRow);
		clickOnElement("xpath", txtUserFirstName);
		waitOnElement("linkText", "User Management");
		String txtUserMgmt = driver().findElement(By.cssSelector(txtpageTitle)).getText().trim();
		if (txtUserMgmt.contains("User Manager")) {
			isDetailsVerified = true;
		} else {
			Assert.assertTrue(isDetailsVerified, "Unable to view the user details when click on first name.");
		}
		if (isDetailsVerified) {
			clickOnElement("linkText", "User Management");
			waitOnElement("cssSelector", txtPageHeading);
			searchUser(userName);
			waitOnElement("xpath", userRow);
			clickOnElement("xpath", txtUserLastName);
			waitOnElement("linkText", "User Management");
			String txtUserMgmt1 = driver().findElement(By.cssSelector(txtpageTitle)).getText().trim();
			if (txtUserMgmt1.contains("User Manager")) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to view the user details when click on last name.");
			}
		}
		if (isDetailsVerified) {
			clickOnElement("linkText", "User Management");
			waitOnElement("cssSelector", txtPageHeading);
			searchUser(userName);
			waitOnElement("xpath", userRow);
			clickOnElement("xpath", txtUserName);
			waitOnElement("linkText", "User Management");
			String txtUserMgmt2 = driver().findElement(By.cssSelector(txtpageTitle)).getText().trim();
			if (txtUserMgmt2.contains("User Manager")) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to view the user details when click on username.");
			}
		}

		return isDetailsVerified;
	}

	public boolean verifyUserDetailsByAdvancedSearch(String userLastName, String userFirstName, String consultantID, String userName, String userEmail, String userMarket) throws Exception, Exception {
		logInfo("inside verifyUserDetailsPage() method");
		System.out.println("inside verifyUserDetailsPage() method");
		boolean isDetailsVerified = false;
		waitOnElement("cssSelector", btnAdvancedSearch);
		clickOnElement("cssSelector", btnAdvancedSearch);
		waitOnElement("cssSelector", inputAdvLastName);
		inputText("cssSelector", inputAdvLastName, userLastName);
		clickOnElement("xpath", btnAdvSubmit);
		waitOnElement("xpath", userRow);
		String lastName = driver().findElement(By.xpath(txtUserLastName)).getText();
		if (lastName.equalsIgnoreCase(userLastName)) {
			isDetailsVerified = true;
		} else {
			Assert.assertTrue(isDetailsVerified, "Unable to search using last name " + userLastName);
		}
		
		if (isDetailsVerified) {
			clickOnElement("cssSelector", btnAdvancedSearch);
			waitOnElement("cssSelector", inputAdvFirstName);
			inputText("cssSelector", inputAdvFirstName, userFirstName);
			clickOnElement("xpath", btnAdvSubmit);

			waitOnElement("xpath", userRow);
			String firstName = driver().findElement(By.xpath(txtUserFirstName)).getText();
			if (firstName.equalsIgnoreCase(userFirstName)) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to search using first name " + userFirstName);
			}
			
		}
		
		if (isDetailsVerified) {
			clickOnElement("cssSelector", btnAdvancedSearch);
			waitOnElement("cssSelector", inputAdvConsultantID);
			inputText("cssSelector", inputAdvConsultantID, consultantID); 
			clickOnElement("xpath", btnAdvSubmit);

			waitOnElement("xpath", userRow);
			String conslId = driver().findElement(By.xpath(txtUserID)).getText();
			if (conslId.contains(consultantID)) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to search using consultant id " + consultantID);
			}
		}
		
		if (isDetailsVerified) {
			clickOnElement("cssSelector", btnAdvancedSearch);
			waitOnElement("cssSelector", inputAdvUserName);
			inputText("cssSelector", inputAdvUserName, userName);
			clickOnElement("xpath", btnAdvSubmit);

			waitOnElement("xpath", userRow);
			String txtUser = driver().findElement(By.xpath(txtUserName)).getText();
			if (txtUser.contains(userName)) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to search using username " + userName);
			}
		}
		if (isDetailsVerified) {
			clickOnElement("cssSelector", btnAdvancedSearch);
			waitOnElement("cssSelector", inputAdvEmail);
			inputText("cssSelector", inputAdvEmail, userEmail);
			clickOnElement("xpath", btnAdvSubmit);

			waitOnElement("xpath", userRow);
			String email = driver().findElement(By.xpath(txtUserEmail)).getText();
			if (email.contains(userEmail)) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to search using email " + userEmail);
			}
		}
		if (isDetailsVerified) {
			clickOnElement("cssSelector", btnAdvancedSearch);
			waitOnElement("cssSelector", inputAdvCountry);
			selectFromDropdown("cssSelector", inputAdvCountry, "byVisibleText", "United States");
			selectFromDropdown("cssSelector", inputAdvState, "byVisibleText", "Utah");
			clickOnElement("xpath", btnAdvSubmit);

			waitOnElement("xpath", userRow);
			String userCountry = driver().findElement(By.xpath(txtUserCountry)).getText();
			String userState = driver().findElement(By.xpath(txtUserState)).getText();
			if (userCountry.contains("US") && userState.contains("UT")) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to search using country & state " + txtUserCountry + "," + txtUserState);
			}
		}
		if (isDetailsVerified) {
			clickOnElement("cssSelector", btnAdvancedSearch);
			waitOnElement("cssSelector", inputAdvStatus);
			inputText("cssSelector", inputAdvStatus, "Active");
			clickOnElement("xpath", btnAdvSubmit);

			waitOnElement("xpath", userRow);
			String status = driver().findElement(By.xpath(txtUserStatus)).getText();
			if (status.contains("Active")) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to search using status Active");
			}
		}
		/*if (isDetailsVerified) {
			clickOnElement("cssSelector", btnAdvancedSearch);
			waitOnElement("cssSelector", inputAdvRank);
			selectFromDropdown("cssSelector", inputAdvRank, "byVisibleText", enrollUserRank);
			clickOnElement("xpath", btnAdvSubmit);

			waitOnElement("xpath", userRow);
			String rank = driver().findElement(By.xpath(txtUserRank)).getText();
			if (rank.contains(enrollUserRank)) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to search using rank " + enrollUserRank);
			}
		}*/
		
		/*if (isDetailsVerified) {
			clickOnElement("cssSelector", btnAdvancedSearch);
			waitOnElement("cssSelector", inputAdvSubscription);
			selectFromDropdown("cssSelector", inputAdvSubscription, "byVisibleText", enrollUserSubscription);
			clickOnElement("xpath", btnAdvSubmit);

			waitOnElement("xpath", userRow);
			String subscriptionUser = driver().findElement(By.xpath(txtUserName)).getText();
			if (subscriptionUser.contains(enrollUsername)) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to search using subscription for user " + enrollUsername);
			}
		}
		*/
		
		if (isDetailsVerified) {
			clickOnElement("cssSelector", btnAdvancedSearch);
			waitOnElement("cssSelector", inputAdvMarket);
			selectFromDropdown("cssSelector", inputAdvMarket, "byVisibleText", enrollUserMarket);
			clickOnElement("xpath", btnAdvSubmit);

			waitOnElement("xpath", userRow);
			String market = driver().findElement(By.xpath(txtUserMarket)).getText();
			if (market.contains(userMarket)) {
				isDetailsVerified = true;
			} else {
				Assert.assertTrue(isDetailsVerified, "Unable to search using market " + userMarket);
			}
		}
		return isDetailsVerified;
	}

	public boolean verifyValidationsForAdminUser() throws Exception, Exception {
		logInfo("inside verifyValidationsForAdminUser() method");
		System.out.println("inside verifyValidationsForAdminUser() method");
		boolean isValidationsVerified = false;

		clickOnLink("linkText", "New Admin User");
		waitOnElement("cssSelector", inputFirstName);
		clickOnElement("xpath", idlBtnCreateUser);
		confirmationMessage("Please select atleast one Group");

		waitOnElement("xpath", drpdnUserSecurityGrps);
		selectFromDropdown("xpath", drpdnUserSecurityGrps, "byVisibleText", userSecurityGrps_text);
		clickOnElement("xpath", idlBtnCreateUser);
		confirmationMessage("User creation failed");

		String firstName = driver().findElement(By.xpath("//*[@id='new_user']/div/div[1]/div[1]/span")).getText().trim();
		if (firstName.contains("can't be blank")) {
			isValidationsVerified = true;
		} else {
			isValidationsVerified = false;
			Assert.assertTrue(isValidationsVerified, "Unable to validate the first name when creating admin user.");
		}

		if (isValidationsVerified) {
			String lastName = driver().findElement(By.xpath("//*[@id='new_user']/div/div[1]/div[2]/span")).getText()
					.trim();
			if (lastName.contains("can't be blank")) {
				isValidationsVerified = true;
			} else {
				isValidationsVerified = false;
				Assert.assertTrue(isValidationsVerified, "Unable to validate the last name when creating admin user.");
			}
		}

		if (isValidationsVerified) {
			String email = driver().findElement(By.xpath("//*[@id='new_user']/div/div[1]/div[3]/span")).getText().trim();
			if (email.contains("can't be blank")) {
				isValidationsVerified = true;
			} else {
				isValidationsVerified = false;
				Assert.assertTrue(isValidationsVerified, "Unable to validate the email when creating admin user.");
			}
		}

		if (isValidationsVerified) {
			String username = driver().findElement(By.xpath("//*[@id='new_user']/div/div[1]/div[4]/span")).getText()
					.trim();
			if (username.contains("can't be blank")) {
				isValidationsVerified = true;
			} else {
				isValidationsVerified = false;
				Assert.assertTrue(isValidationsVerified, "Unable to validate the username when creating admin user.");
			}
		}

		if (isValidationsVerified) {
			String password = driver().findElement(By.xpath("//*[@id='new_user']/div/div[1]/div[5]/span")).getText()
					.trim();
			if (password.contains("can't be blank")) {
				isValidationsVerified = true;
			} else {
				isValidationsVerified = false;
				Assert.assertTrue(isValidationsVerified, "Unable to validate the password when creating admin user.");
			}
		}

		if (isValidationsVerified) {
			String confirmPwd = driver().findElement(By.xpath("//*[@id='new_user']/div/div[1]/div[6]/span")).getText()
					.trim();
			if (confirmPwd.contains("can't be blank")) {
				isValidationsVerified = true;
			} else {
				isValidationsVerified = false;
				Assert.assertTrue(isValidationsVerified,
						"Unable to validate the confirm password when creating admin user.");
			}
		}

		if (isValidationsVerified) {
			inputText("xpath", inputUserEmail, "testuser@icentris");
			String invalidEmailMsg = driver().findElement(By.xpath("//*[@id='new_user']/div/div[1]/div[3]/span"))
					.getText().trim();
			if (invalidEmailMsg.contains("is invalid")) {
				isValidationsVerified = true;
			} else {
				isValidationsVerified = false;
				Assert.assertTrue(isValidationsVerified, "Unable to validate with invalid email testuser@icentris");
			}
		}

		if (isValidationsVerified) {
			inputText("xpath", inputUserPassword, "12345");
			inputText("xpath", inputUserPasswdConfirm, "12345");
			String pwd = driver().findElement(By.xpath("//*[@id='new_user']/div/div[1]/div[5]/span")).getText().trim();
			String confirmPwd = driver().findElement(By.xpath("//*[@id='new_user']/div/div[1]/div[6]/span")).getText()
					.trim();
			clickOnElement("xpath", idlBtnCreateUser);
			if (pwd.contains("is invalid") && confirmPwd.contains("is invalid")) {
				isValidationsVerified = true;
			} else {
				isValidationsVerified = false;
				Assert.assertTrue(isValidationsVerified, "Unable to validate the password fields.");
			}
		}

		return isValidationsVerified;
	}

	public boolean verifyResetPassword(String userName) throws Exception, Exception {
		logInfo("inside verifyResetPassword() method");
		System.out.println("inside verifyResetPassword() method");
		boolean isEmailVerified = false;
		boolean isResetPwdFieldsVerified = true;

		waitOnElement("xpath", btnResetPwd);
		clickOnElement("xpath", btnResetPwd);
		/*waitOnElement("xpath", btnConfirmResetPwdOk);
		clickOnElement("xpath", btnConfirmResetPwdOk);*/
		confirmOK();
		confirmationMessage("Email sent with password reset instructions");

		/*back2Office();
		inbox.go2Inbox();
		isEmailVerified = inbox.verifyVibeInboxMail(subjectResetEmail);

		if (isEmailVerified) {
			waitOnElement("linkText", "Change my password");
			clickOnElement("linkText", "Change my password");
			waitOnElement("cssSelector", inputResetPwd);
			isResetPwdFieldsVerified = verifyElementPresent("cssSelector", inputResetPwd);
			if (isResetPwdFieldsVerified) {
				isResetPwdFieldsVerified = verifyElementPresent("cssSelector", inputResetConfirmPwd);
			}
		} else {
			Assert.assertTrue(isEmailVerified,
					"Unable to verify the email in the inbox, after the password has been reset.");
		}
		if (isResetPwdFieldsVerified) {
			inputText("cssSelector", inputResetPwd, "password123");
			inputText("cssSelector", inputResetConfirmPwd, "password123");
			clickOnElement("xpath", btnUpdatePwd);
			confirmationMessage("Password has been reset!");
			implicityWaits(3);
			logOut();
			logIn(userName, "password123");
			confirmationMessage("Signed in successfully.");
			implicityWaits(5);
		
		} else {
			Assert.assertTrue(isResetPwdFieldsVerified, "Unable to verify the password & confirm password fields.");
		}*/

		return isResetPwdFieldsVerified;
	}

	public boolean validateBlankResetPasswordFields(String userName) throws Exception, Exception {
		logInfo("inside validateBlankResetPasswordFields() method");
		System.out.println("inside validateBlankResetPasswordFields() method");
		boolean isResetPwdFieldsVerified = false;
		boolean isEmailVerified = false;

		waitOnElement("xpath", btnResetPwd);
		clickOnElement("xpath", btnResetPwd);
		waitOnElement("xpath", btnConfirmResetPwdOk);
		clickOnElement("xpath", btnConfirmResetPwdOk);
		confirmationMessage("Email sent with password reset instructions");

		back2Office();
		inbox.go2Inbox();
		isEmailVerified = inbox.verifyVibeInboxMail(subjectResetEmail);

		if (isEmailVerified) {
			waitOnElement("linkText", "Change my password");
			clickOnElement("linkText", "Change my password");
			waitOnElement("cssSelector", inputResetPwd);
			isResetPwdFieldsVerified = verifyElementPresent("cssSelector", inputResetPwd);
			if (isResetPwdFieldsVerified) {
				isResetPwdFieldsVerified = verifyElementPresent("cssSelector", inputResetConfirmPwd);
			}
		} else {
			Assert.assertTrue(isEmailVerified,"Unable to verify the email in the inbox, after the password has been reset.");
		}

		if (isResetPwdFieldsVerified) {
			clickOnElement("xpath", btnUpdatePwd);
			waitOnElement("cssSelector",validateResetPwd);
			String validatePwd = driver().findElement(By.cssSelector(validateResetPwd)).getText().trim();
			String validateConfirmPwd = driver().findElement(By.cssSelector(validateConfirmResetPwd)).getText().trim();
			if (!validatePwd.contains("Can't be blank") || !validateConfirmPwd.contains("Can't be blank")) {
				isResetPwdFieldsVerified = false;
				Assert.assertTrue(isResetPwdFieldsVerified,
						"Unable to validate the blank password fields, after reset the password");
			}
		} 
		else{
			Assert.assertTrue(isResetPwdFieldsVerified,"Unable to verify the reset password fields, after clicking 'Change my password' link on the email when password reset.");
		}

		if (isResetPwdFieldsVerified) {
			inputText("cssSelector", inputResetPwd, "password123");
			clickOnElement("xpath", btnUpdatePwd);
			waitOnElement("cssSelector",validateConfirmResetPwd);
			String validateConfirmPwd = driver().findElement(By.cssSelector(validateConfirmResetPwd)).getText().trim();
			if (!validateConfirmPwd.contains("Can't be blank")) {
				isResetPwdFieldsVerified = false;
				Assert.assertTrue(isResetPwdFieldsVerified,"Unable to validate the blank confirm password field, after reset the password");
			}
		}

		if (isResetPwdFieldsVerified) {
			inputTextClear("cssSelector",inputResetPwd);
			inputText("cssSelector", inputResetConfirmPwd, "password123");
			clickOnElement("xpath", btnUpdatePwd);
			waitOnElement("cssSelector",validateResetPwd);
			String validateNewPwd = driver().findElement(By.cssSelector(validateResetPwd)).getText().trim();
			if (!validateNewPwd.contains("Can't be blank")) {
				isResetPwdFieldsVerified = false;
				Assert.assertTrue(isResetPwdFieldsVerified,"Unable to validate the blank new password field, after reset the password");
			}
		}
		
		if (isResetPwdFieldsVerified) {
			inputText("cssSelector", inputResetPwd, "password123");
			clickOnElement("xpath", btnUpdatePwd);
			implicityWaits(5);
		}
		
		return isResetPwdFieldsVerified;
	}
	
	public boolean validateResetPasswordFields(String userName) throws Exception, Exception {
		logInfo("inside validateResetPasswordFields() method");
		System.out.println("inside validateResetPasswordFields() method");
		boolean isResetPwdFieldsVerified = false;
		boolean isEmailVerified = false;

		waitOnElement("xpath", btnResetPwd);
		clickOnElement("xpath", btnResetPwd);
		waitOnElement("xpath", btnConfirmResetPwdOk);
		clickOnElement("xpath", btnConfirmResetPwdOk);
		confirmationMessage("Email sent with password reset instructions");

		back2Office();
		inbox.go2Inbox();
		isEmailVerified = inbox.verifyVibeInboxMail(subjectResetEmail);

		if (isEmailVerified) {
			waitOnElement("linkText", "Change my password");
			clickOnElement("linkText", "Change my password");
			waitOnElement("cssSelector", inputResetPwd);
			isResetPwdFieldsVerified = verifyElementPresent("cssSelector", inputResetPwd);
			if (isResetPwdFieldsVerified) {
				isResetPwdFieldsVerified = verifyElementPresent("cssSelector", inputResetConfirmPwd);
			}
		} else {
			Assert.assertTrue(isEmailVerified,"Unable to verify the email in the inbox, after the password has been reset.");
		}

		if (isResetPwdFieldsVerified) {
			inputText("cssSelector", inputResetPwd, "password");
			inputText("cssSelector", inputResetConfirmPwd, "password");
			clickOnElement("xpath", btnUpdatePwd);
			String validateText = driver().findElement(By.cssSelector(passwordsValidate)).getText().trim();
			if (!validateText.contains("is invalid")) {
				isResetPwdFieldsVerified = false;
				Assert.assertTrue(false, "Unable to validate the password fields with only alplabets.");
			}
		}
		else{
			Assert.assertTrue(isResetPwdFieldsVerified,"Unable to verify the reset password fields, after clicking 'Change my password' link on the email when password reset.");
		}

		if (isResetPwdFieldsVerified) {
			inputText("cssSelector", inputResetPwd, "12345");
			inputText("cssSelector", inputResetConfirmPwd, "12345");
			clickOnElement("xpath", btnUpdatePwd);
			String validateText = driver().findElement(By.cssSelector(passwordsValidate)).getText().trim();
			if (!validateText.contains("is invalid")) {
				isResetPwdFieldsVerified = false;
				Assert.assertTrue(false, "Unable to validate the password fields with only numbers.");
			}
		}

		if (isResetPwdFieldsVerified) {
			inputText("cssSelector", inputResetPwd, "12ab!@");
			inputText("cssSelector", inputResetConfirmPwd, "12ab!@");
			clickOnElement("xpath", btnUpdatePwd);
			implicityWaits(5);
			String validateText = driver().findElement(By.cssSelector(passwordsValidate)).getText().trim();
			if (!validateText.contains("is invalid")) {
				isResetPwdFieldsVerified = false;
				Assert.assertTrue(false, "Unable to validate the password fields with special characters and less than 7 characters.");
			}

		}

		if (isResetPwdFieldsVerified) {
			inputText("cssSelector", inputResetPwd, "12345");
			inputText("cssSelector", inputResetConfirmPwd, "abcde");
			clickOnElement("xpath", btnUpdatePwd);
			waitOnElement("cssSelector", validatePwdMatch);
			String validateText = driver().findElement(By.cssSelector(validatePwdMatch)).getText().trim();
			if (!validateText.contains("Password Confirmation Doesn't match with New Password")) {
				isResetPwdFieldsVerified = false;
				Assert.assertTrue(false,"Unable to validate the mismatch inputs for password and confirm password fields.");
			}

		}
		
		return isResetPwdFieldsVerified;
	}
	
	public boolean validateDenyOldPasswords(String userName) throws Exception, Exception {
		logInfo("inside validateDenyOldPasswords() method");
		System.out.println("inside validateDenyOldPasswords() method");
		boolean isResetPwdFieldsVerified = false;
		boolean isEmailVerified = false;

		waitOnElement("xpath", btnResetPwd);
		clickOnElement("xpath", btnResetPwd);
		waitOnElement("xpath", btnConfirmResetPwdOk);
		clickOnElement("xpath", btnConfirmResetPwdOk);
		confirmationMessage("Email sent with password reset instructions");

		back2Office();
		inbox.go2Inbox();
		isEmailVerified = inbox.verifyVibeInboxMail(subjectResetEmail);

		if (isEmailVerified) {
			waitOnElement("linkText", "Change my password");
			clickOnElement("linkText", "Change my password");
			waitOnElement("cssSelector", inputResetPwd);
			isResetPwdFieldsVerified = verifyElementPresent("cssSelector", inputResetPwd);
			if (isResetPwdFieldsVerified) {
				
				isResetPwdFieldsVerified = verifyElementPresent("cssSelector", inputResetConfirmPwd);
			}
		} else {
			Assert.assertTrue(isEmailVerified,"Unable to verify the email in the inbox, after the password has been reset.");
		}

		if (isResetPwdFieldsVerified) {
			inputText("cssSelector", inputResetPwd, "password1");
			inputText("cssSelector", inputResetConfirmPwd, "password1");
			clickOnElement("xpath", btnUpdatePwd);
			waitOnElement("cssSelector", validatePwdMatch);
			String validateText = driver().findElement(By.cssSelector(validatePwdMatch)).getText().trim();
			if (!validateText.contains("already taken")) {
				isResetPwdFieldsVerified = false;
				Assert.assertTrue(false,"New Password should not match with the previously used 2 passwords.");
			}
			
		}
		else{
			Assert.assertTrue(isResetPwdFieldsVerified,"Unable to verify the reset password fields, after clicking 'Change my password' link on the email when password reset.");
		}

		return isResetPwdFieldsVerified;
	}
	
	public void changePasswordSettings(String optionValue) throws Exception {
		logInfo("inside changePasswordSettings() method..");
		System.out.println("inside changePasswordSettings() method..");
		selectFromDropdown("xpath", drpdownPwdRegex, "byVisibleText", optionValue);
		clickOnElement("xpath", btnUpdatePwdSettings);
	}
	
	public void changeDenyPasswordSettings(String optionValue1,String optionValue2) throws Exception {
		logInfo("inside changeDenyPasswordSettings() method..");
		System.out.println("inside changeDenyPasswordSettings() method..");
		List el = driver().findElements(By.xpath("//*[@id='main-content']/table/tbody/tr"));
		
		String before = "//*[@id='main-content']/table/tbody/tr[";
		String after = "]/td[1]";
		
		String before_drpdown = "//*[@id='main-content']/table/tbody/tr[";
		String after_drpdown = "]/td[3]/select";
		
		for(int i=1;i<=el.size();i++){
			WebElement e = driver().findElement(By.xpath(before+i+after));
			if(e.getText().trim().contains(optionValue1)){
				selectFromDropdown("xpath",before_drpdown+i+after_drpdown,"byVisibleText","2");
			}
			if(e.getText().trim().contains(optionValue2)){
				selectFromDropdown("xpath",before_drpdown+i+after_drpdown,"byVisibleText","Yes");
				break;
			}
		}
		
		clickOnElement("xpath", btnUpdatePwdSettings);
	}

	public boolean verifyAccountSectionFieldsAndWebsites(String websiteName) throws Exception, Exception{
		logInfo("inside verifyAccountSectionFieldsAndWebsites() method..");
		System.out.println("inside verifyAccountSectionFieldsAndWebsites() method..");
		boolean isDetailsVerified = false;
		waitOnElement("partialLinkText","Edit User");
		clickOnElement("partialLinkText","Edit User");
		waitOnElement("xpath","//label[contains(.,'First Name')]");
		isDetailsVerified = verifyElementPresent("xpath","//label[contains(.,'First Name')]");
		if(!isDetailsVerified){
			Assert.assertTrue(isDetailsVerified, "Unable to verify the First name under accounts section");
		}
		
		if(isDetailsVerified){
			isDetailsVerified = verifyElementPresent("xpath","//label[contains(.,'Last Name')]");
			if(!isDetailsVerified){
				Assert.assertTrue(isDetailsVerified, "Unable to verify the Last name under accounts section");
			}
		}
		
		if(isDetailsVerified){
			isDetailsVerified = verifyElementPresent("xpath","//label[contains(.,'Email')]");
			if(!isDetailsVerified){
				Assert.assertTrue(isDetailsVerified, "Unable to verify the Email under accounts section");
			}
		}

		if(isDetailsVerified){
			isDetailsVerified = verifyElementPresent("xpath","//label[contains(.,'Distributor')]");
			if(!isDetailsVerified){
				Assert.assertTrue(isDetailsVerified, "Unable to verify the Distributor under accounts section");
			}
		}
		
		if(isDetailsVerified){
			isDetailsVerified = verifyElementPresent("xpath","//label[contains(.,'Phone')]");
			if(!isDetailsVerified){
				Assert.assertTrue(isDetailsVerified, "Unable to verify the phone under accounts section");
			}
		}
		if(isDetailsVerified){
			isDetailsVerified = verifyElementPresent("xpath","//label[contains(.,'User Market')]");
			if(!isDetailsVerified){
				Assert.assertTrue(isDetailsVerified, "Unable to verify the User Market under accounts section");
			}
		}
		if(isDetailsVerified){
			isDetailsVerified = verifyElementPresent("xpath","//label[contains(.,'Website')]");
			if(!isDetailsVerified){
				Assert.assertTrue(isDetailsVerified, "Unable to verify the Website under accounts section");
			}
		}
		if(isDetailsVerified){
			List websites = driver().findElements(By.xpath(lstWebSites));
			String before = "//*[contains(@id,'edit_user')]/div[4]/div/table/tbody/tr[";
			String after = "]/td[1]/a";
			
			for(int i=1;i<=websites.size();i++){
				WebElement el = driver().findElement(By.xpath(before+i+after));
				if(el.getText().trim().contains(websiteName)){
					isDetailsVerified = true;
					break;
				}
			}
		}
		else{
			Assert.assertTrue(isDetailsVerified, "Unable to verify the web sites under account section.");
		}
		if(isDetailsVerified){
			clickOnElement("xpath",tabLogin);
			waitOnElement("xpath","//label[contains(.,'Username')]");
			isDetailsVerified = verifyElementPresent("xpath","//label[contains(.,'Username')]");
			if(!isDetailsVerified){
				isDetailsVerified = false;
				Assert.assertTrue(isDetailsVerified, "Unable to verify the username under Login section");
			}
		}
		
		if(isDetailsVerified){
			clickOnElement("xpath",tabSubscriptions);
			waitOnElement("xpath","//label[contains(.,'Subscription Plan')]");
			isDetailsVerified = verifyElementPresent("xpath","//label[contains(.,'Subscription Plan')]");
			if(!isDetailsVerified){
				isDetailsVerified = false;
				Assert.assertTrue(isDetailsVerified, "Unable to verify the subscription plan under Subscriptions section");
			}
		}
		
		if(isDetailsVerified){
			isDetailsVerified = verifyElementPresent("xpath","//label[contains(.,'Sub Created At')]");
			if(!isDetailsVerified){
				isDetailsVerified = false;
				Assert.assertTrue(isDetailsVerified, "Unable to verify the sub created at label under Subscriptions section");
			}
		}
		

		if(isDetailsVerified){
			isDetailsVerified = verifyElementPresent("xpath",".//label[contains(.,'Security Groups')]");
			if(!isDetailsVerified){
				isDetailsVerified = false;
				Assert.assertTrue(isDetailsVerified, "Unable to verify the security groups under Subscriptions section");
			}
		}
		
		return isDetailsVerified;
	}
	
	public boolean verifyUpdateUserDetails(String UserName,String websiteName) throws Exception, Exception{
		logInfo("inside verifyUpdateUserDetails() method..");
		System.out.println("inside verifyUpdateUserDetails() method..");
		boolean isVerified = false;
		waitOnElement("partialLinkText","Edit User");
		clickOnElement("partialLinkText","Edit User");
		
		waitOnElement("cssSelector",inputFirstName);
		inputTextClear("cssSelector",inputFirstName);
		inputText("cssSelector",inputFirstName,txtFirstName);
		
		inputTextClear("cssSelector",inputLastName);
		inputText("cssSelector",inputLastName,txtLastName);
		
		inputTextClear("cssSelector",inputPhone);
		inputText("cssSelector",inputPhone,"12345");
		
		inputTextClear("cssSelector",inputEmail);
		inputText("cssSelector",inputEmail,userEmail_text);
		
		clickOnElement("xpath",btnUpdateUserDetails);
		confirmationMessage("User was successfully updated.");
		
		waitOnElement("cssSelector",txtEmail);
		String email = driver().findElement(By.cssSelector(txtEmail)).getText().trim();
		if(email.contains(userEmail_text)){
			isVerified = true;
		}
		else{
			Assert.assertTrue(isVerified, "Unable to update the email.");
		}
		
		if(isVerified){
			isVerified = verifyUpdatedUserDetailsOnUserSide();
		}
		else{
			Assert.assertTrue(isVerified, "Unable to verify the updated user details on the user side.");
		}
		if(isVerified){
			isVerified = updateSecurityGroupNSubPlan(txtSubPlanLiteYearly,"admin");
		}
		else{
			Assert.assertTrue(isVerified, "Unable to update user subscription and security group details.");
		}
		return isVerified;
	}
	
	public boolean verifyUpdatedUserDetailsOnUserSide() throws Exception, Exception{
		logInfo("Inside verifyUpdatedUserDetailsOnUserSide() method..");
		System.out.println("Inside verifyUpdatedUserDetailsOnUserSide() method..");
		boolean isVerified = false;
		
		waitOnElement("partialLinkText","Login As User");
		clickOnElement("partialLinkText","Login As User");
		
		waitOnElement("cssSelector",".caution_msg");
		go2AccountInfo();
		
		waitOnElement("cssSelector",inputAccntFirstName);
		String firstName = driver().findElement(By.cssSelector(inputAccntFirstName)).getText().trim();
		String lastName = driver().findElement(By.cssSelector(inputAccntLastName)).getText().trim();
		String email = driver().findElement(By.cssSelector(inputAccntEmail)).getText().trim();
		String phone = driver().findElement(By.cssSelector(inputAccntPhone)).getText().trim();
		
		if(firstName.equalsIgnoreCase(txtFirstName) && lastName.equalsIgnoreCase(inputAccntLastName) && email.equalsIgnoreCase(inputAccntEmail) && phone.equalsIgnoreCase(inputAccntPhone)){
			isVerified = true;
		}
		else{
			isVerified = false;
			Assert.assertTrue(isVerified, "Unable to verify the user details, under the accounts section on the user side.");
		}
		return isVerified;
	}
	
	public void go2AccountInfo(){
		logInfo("Inside go2AccountInfo() method..");
		System.out.println("Inside go2AccountInfo() method..");
		driver().navigate().to(appUrl + "pyr_core/account#account-info");
	}
	
	public boolean updateWebSiteName(String websiteURL_text) throws Exception, Exception{
		logInfo("Inside verifyMyWebsitePresent() method.");
		wm.go2MyWebsite();
		waitOnElement("xpath",panelActiveMyWebsites);		
		List allRows = driver().findElements(By.xpath(panelActiveMyWebsites));
		String before_website = "//*[@id='active_websites']/div[@class='col-md-6'][";
		String after_website = "]/div/div[3]/h4[2]";
		
		String before_button = "//*[@id='active_websites']/div[@class='col-md-6'][";
		String after_button = "]/div/div[1]/div/button";
		
		boolean isWebsiteFound = false;
		if(allRows.size()>0)	{	
			for(int i=1;i<=allRows.size();i++){
				WebElement name = driver().findElement(By.xpath(before_website+i+after_website));
				String actwebsite_name = name.getText().trim();				
				if(actwebsite_name.contains(websiteURL_text)){
					isWebsiteFound = true;
					logInfo(websiteURL_text + " website match found in my website page.");
					WebElement el = driver().findElement(By.xpath(before_button+i+after_button));
					el.click();
					waitOnElement("xpath","//*[contains(@id,'more')]/ul/li[2]/a");
					clickOnElement("xpath","//*[contains(@id,'more')]/ul/li[2]/a");
					
					waitOnElement("cssSelector",inputPwpSite);
					inputTextClear("cssSelector",inputPwpSite);
					inputText("cssSelector",inputPwpSite,websiteName_text+"123");
					clickOnElement("xpath",btnUpdatePwp);
					confirmationMessage("Your website has been updated.");
					break;
				}
			}
		 }
		 
		if(isWebsiteFound==false){
			logInfo(websiteURL_text + " website match not found in my website page.");
			Assert.assertTrue(isWebsiteFound, websiteURL_text + " website match not found in my website page.");
		}
		return isWebsiteFound;
	}
	
	public boolean updateSecurityGroupNSubPlan(String subPlan,String secGrp) throws Exception, Exception{
		logInfo("Inside updateSecurityGroupNSubPlan() method..");
		System.out.println("Inside updateSecurityGroupNSubPlan() method..");
		boolean isUpdated = false;
		
		waitOnElement("partialLinkText","Edit User");
		clickOnElement("partialLinkText","Edit User");
		waitOnElement("xpath",tabSubscriptions);
		clickOnElement("xpath",tabSubscriptions);
		
		waitOnElement("cssSelector",drpSubPlan);
		String before_change = getCurrentSelectionFromDropdown("xpath",drpSubPlan);
		System.out.println("before_change =" +before_change);
		selectFromDropdown("xpath",drpSubPlan,"byVisibleText",subPlan);
		
		List<WebElement> secGroups = driver().findElements(By.cssSelector(lstSecGroups));
		
		for(WebElement el : secGroups){
			if(el.getText().trim().contains(secGrp.trim())){
				el.click();
				break;
			}
		}
		clickOnElement("xpath",btnUpdatePwp);
		confirmationMessage("User was successfully updated.");
		
		waitOnElement("partialLinkText","Edit User");
		clickOnElement("partialLinkText","Edit User");
		
		waitOnElement("linkText","Show User");
		clickOnElement("linkText","Show User");
		
		if(verifyElementPresent("partialLinkText","Edit User")){
			waitOnElement("xpath",tabSubscriptions);
			clickOnElement("xpath",tabSubscriptions);
			
			String aftersubPlan = getCurrentSelectionFromDropdown("xpath",drpSubPlan);
			System.out.println("after sub Plan =" +aftersubPlan);
			if(!aftersubPlan.equalsIgnoreCase(subPlan)){
				isUpdated = true;
			}
			else{
				Assert.assertTrue(isUpdated, "Unable to update the subscription plan.");
			}
			if(isUpdated){
				for(WebElement el : secGroups){
					if(el.getText().trim().contains(secGrp.trim())){
						if(el.isSelected()){
							isUpdated = true;
							break;
						}
						else{
							isUpdated = false;
							Assert.assertTrue(isUpdated,"Unable to update the security group.");
						}
						
					}
				}
			}
		}
		else{
			Assert.assertTrue(false, "Unable to verify the back link from edit user page.");
		}
		return isUpdated;
	}

	public boolean verifyValidateEmail() throws Exception, Exception{
		logInfo("Inside verifyValidateEmail() method..");
		System.out.println("Inside verifyValidateEmail() method..");
		boolean isValidated = false;
		waitOnElement("partialLinkText","Edit User");
		clickOnElement("partialLinkText","Edit User");
		waitOnElement("cssSelector",inputEmail);
		inputTextClear("cssSelector",inputEmail);
		clickOnElement("xpath",btnUpdateUserDetails);
		waitOnElement("cssSelector",".help-block");
		String validateEmail = driver().findElement(By.cssSelector(".help-block")).getText().trim();
		if(validateEmail.contains("can't be blank")){
			isValidated = true;
		}
		return isValidated;
	}
	
	public boolean verifyUserStatistics() throws Exception, Exception{
		logInfo("Inside verifyUserStatistics() method..");
		System.out.println("Inside verifyUserStatistics() method..");
		boolean isVerified = false;
		waitOnElement("xpath",tabUserStats);
		clickOnElement("xpath",tabUserStats);
		waitOnElement("xpath",lblLoginHistory);
		String loginHistory = driver().findElement(By.xpath(lblLoginHistory)).getText().trim();
		String status = driver().findElement(By.xpath(lblStatus)).getText().trim();
		if(loginHistory.contains("Login History") && status.contains("Status")){
			isVerified = true;
		}
		return isVerified;
	}
	

	//advanced search and finding exact user to login as user in Avon
    public void loginAsUserFromAdvanced(String user, String enrollType) throws Exception{           
         logInfo("inside loginAsUserFromAdvanced() method");  
                                             
          go2Users(); 
          waitOnElement("cssSelector", btnAdvancedSearch);
          clickOnElement("cssSelector", btnAdvancedSearch);
          waitOnElement("cssSelector", inputAdvConsultantID);
          inputText("cssSelector", inputAdvConsultantID, user);   
	      selectFromDropdown("cssSelector", inputenrollType, "byVisibleText", enrollType);
	      clickOnElement("xpath", btnAdvSubmit);
	      clickOnElement("cssSelector", footerMessage);   
          boolean isUserFound = verifyUserPresent(user);
          if(isUserFound){                  
                  clickOnLink("linkText", "Login As User");  
          }
   
    }

    public void loginAsUserFromAdvanced(String firstName, String lastName, String consultantid) throws Exception{           
        logInfo("inside loginAsUserFromAdvanced() method");  
                                            
         go2Users(); 
         waitOnElement("cssSelector", btnAdvancedSearch);
         clickOnElement("cssSelector", btnAdvancedSearch);
         waitOnElement("cssSelector", inputAdvConsultantID);
         inputTextClear("cssSelector", inputAdvFirstName);
         inputText("cssSelector", inputAdvFirstName, firstName);   
         inputTextClear("cssSelector", inputAdvLastName);
         inputText("cssSelector", inputAdvLastName, lastName);   
      
         inputText("cssSelector", inputAdvConsultantID, consultantid);   
	   
	      clickOnElement("xpath", btnAdvSubmit);
	      clickOnElement("cssSelector", footerMessage);   
          boolean isUserFound = verifyUserPresent(firstName);
         if(isUserFound){                  
                 clickOnLink("linkText", "Login As User");  
         }
 
   }

    
  	public void userLogout() throws Exception{
  			logInfo("inside userLogout() method");
			waitOnElement("cssSelector", logoutHere);		 
			clickOnLink("cssSelector", logoutHere);
	 
 }
  	
  	public boolean verifyUserPresentWithFname(String fname) throws Exception, Exception {
		logInfo("inside verifyUserPresent() method.");
		waitOnElement("xpath", tblAdminUsers);
		WebElement tblUsers = driver().findElement(By.xpath(tblAdminUsers));
		List allUsers = tblUsers.findElements(By.tagName("tr"));
		String before_name = "//*[@id='users']/table/tbody/tr[";
		String after_name = "]/td[2]/a";
		boolean isUserFound = false;
		if (allUsers.size() > 0) {
			for (int i = 1; i <= allUsers.size(); i++) {
				waitOnElement("xpath","//*[@id='users']/table/tbody/tr");
				WebElement tblmsg = driver().findElement(By.xpath("//*[@id='users']/table/tbody/tr"));
				List msgrow = tblmsg.findElements(By.tagName("td"));
				System.out.println("Sixe is "+msgrow.size());
				if (msgrow.size() > 1) {
					waitOnElement("xpath",before_name + i + after_name);
					WebElement x = driver().findElement(By.xpath(before_name + i + after_name));
					String name = x.getText().trim();
					System.out.println("Name"+name);
					if (name.equalsIgnoreCase(fname.trim())) {
						isUserFound = true;					
						break;
					}
				}
			}
		}

		if (isUserFound == false) {			
			Assert.assertTrue(isUserFound, fname + " user not found in user search page.");
		}
		return isUserFound;
	}
    
    
}
