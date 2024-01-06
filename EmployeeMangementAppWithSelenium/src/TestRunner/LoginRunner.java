package TestRunner;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import PagesObjectModel.Dashboard;
import PagesObjectModel.Login;
import Setup.setup;
import Utils.Constant;
import Utils.ExcelUtilsData;


public class LoginRunner extends setup {
	@DataProvider(name="loginData")
	public static Object[][] getData() throws Exception {
		Object data [][]=ExcelUtilsData.TestData(Constant.Path_TestData, Constant.Sheet_TestData);
		return data;
	}
	@Parameters("Browser")
	@BeforeMethod
	public void beforeMethod(String Browser) {
		setup(Browser);

	}
	
	Login login;
	@Test(dataProvider="loginData")
	public void loginTestCase(String email,String password ,String status) throws InterruptedException {
		login=new Login(driver);
		Dashboard dash=login.loginData(email, password);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
 		if(status.equalsIgnoreCase("NotCorrect")) {
 				Assert.assertTrue(login.serverErrorMessage().isDisplayed());
 				ExtentTest test = extent.createTest("Login Test").assignDevice("chrome");

 			}
		else if(status.equalsIgnoreCase("Correct")) {
			Assert.assertTrue(dash.title().isDisplayed());
				ExtentTest test = extent.createTest("Login Test").assignDevice("chrome");

		}	
		else {
				Assert.assertTrue(login.clientErrorMessage().isDisplayed());
 				ExtentTest test = extent.createTest("Login Test").assignDevice("chrome");


		}
	}
	@AfterMethod
	public void afterTest() {
		tearDown()	;	
	}



}
