package TestRunner;

import Setup.setup;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import PagesObjectModel.Dashboard;
import PagesObjectModel.Login;


public class dashboradRunner extends setup {
	public Actions actions ;
	 public WebElement[][] twoDArray;
	 public WebDriverWait wait;
		SoftAssert soft;


	
	@Parameters("Browser")
	@BeforeTest
	public void BeforeTest(String Browser){
		setup(Browser);
		Login login=new Login(driver);
		Dashboard dashboard=login.loginData("admin@admin.com", "123");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 twoDArray=Dashboard.listTo2DArray(Dashboard.TableEmps(),Dashboard.TableEmps().size());
	         actions = new Actions(driver);
	         wait=new WebDriverWait(driver,Duration.ofSeconds(5));


	}
	@Test(priority=3)
	public void titleDisplay() {
		Assert.assertTrue(Dashboard.title().isDisplayed());
		ExtentTest test = extent.createTest("Title Display").assignDevice("chrome");
	}
	@Test(priority=4)
	public void Search() throws InterruptedException {
		String searchValue="ayham";
		Dashboard.search(searchValue);
        Thread.sleep(1000);
		Assert.assertTrue(Dashboard.TableEmps().get(0).getText().toString().contains(searchValue));
		ExtentTest test = extent.createTest(" Search").assignDevice("chrome");
		Dashboard.clearSearch(searchValue);
	}
	
	@Test(priority=5)
	public void emptySearch() throws InterruptedException {
		String searchValue=" ";
		Dashboard.search(searchValue);
        Thread.sleep(1000);
		Assert.assertFalse(Dashboard.TableEmps().get(0).getText().isEmpty());
		ExtentTest test = extent.createTest("Empty Search").assignDevice("chrome");
		Dashboard.clearSearch(searchValue);
	}
	@Test(priority=6)
	public void uniqueSearch() throws InterruptedException {
		String searchValue="ayham alia12345546355";
		Dashboard.search(searchValue);
        Thread.sleep(1000);
		Assert.assertEquals(Dashboard.TableEmps().get(0).getText(),searchValue);
		ExtentTest test = extent.createTest("unique Search").assignDevice("chrome");
	    Dashboard.clearSearch(searchValue);

	}
	@Test(priority=7)
	public void SearchWithUpperCase() throws InterruptedException {
		String searchValue="AYHAM";
		Dashboard.search(searchValue);
        Thread.sleep(1000);
    	Assert.assertTrue(Dashboard.TableEmps().get(0).isDisplayed());
		ExtentTest test = extent.createTest("Search With Upper Case").assignDevice("chrome");
	    Dashboard.clearSearch(searchValue);
	}
	
	@Test(priority=8)
	public void SearchWithSpecialChar() throws InterruptedException {
		String searchValue="ayham@";
		Dashboard.search(searchValue);
        Thread.sleep(1000);
    	Assert.assertEquals(Dashboard.TableEmps().size(),0);
		ExtentTest test = extent.createTest("Search With Special Char").assignDevice("chrome");
	    Dashboard.clearSearch(searchValue);
	}	
	//Pagination
	@Test(priority=1)
	public void paginationTest() throws InterruptedException {
	    ArrayList<String> names = new ArrayList<>();
	    List<WebElement> pages = Dashboard.pagesNum();
	    for (int i = 0; i < pages.size(); i++) {
	        for (WebElement ele : Dashboard.empName()) {
	            names.add(ele.getText());
	        }

	        if (i < pages.size() - 1) {
	            Dashboard.nextArrow().click();
	            Thread.sleep(500);
	        }
	    }
	    Assert.assertEquals(names.size(), 47);
		ExtentTest test = extent.createTest("Pagination next arrow").assignDevice("chrome");

	}
	@Test(priority=2)
	public void paginationPrevTest() throws InterruptedException {
	    ArrayList<String> names = new ArrayList<>();
	    List<WebElement> pages = Dashboard.pagesNum();
	    for (int i = 0; i < pages.size(); i++) {
	        for (WebElement ele : Dashboard.empName()) {
	            names.add(ele.getText());
	        }
	        if (i < pages.size() - 1) {
	            Dashboard.prevArrow().click();
	            Thread.sleep(500);
	        }
	    }
	    Assert.assertEquals(names.size(), 47);
		ExtentTest test = extent.createTest("Pagination previous arrow").assignDevice("chrome");

	}
	@Test(priority=9)
	public void navigateToReviewVocations() throws InterruptedException {
		Dashboard.navigateToReviewVocations().click();
        wait.until(ExpectedConditions.urlContains("ReviewVacation"));
		Assert.assertTrue(driver.getCurrentUrl().contains("ReviewVacation"));
		ExtentTest test = extent.createTest("Navigate To ReviewVocations").assignDevice("chrome");

		
	}
//	logout
	@Test(priority=10)
	public void logout() {
		Dashboard.logout().click();
		Assert.assertEquals(driver.getCurrentUrl(),"https://65956794a3598900a1bfbd31--cerulean-torrone-e715a9.netlify.app/" );
		System.out.println(driver.getCurrentUrl());
		ExtentTest test = extent.createTest("Logout Test").assignDevice("chrome");

		
	}



	
	

	@AfterTest
	public void afterTest() {
     driver.quit();
  	extent.flush();
	}
	

}
