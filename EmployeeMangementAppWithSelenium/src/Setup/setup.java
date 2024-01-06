package Setup;

import org.testng.annotations.AfterMethod;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class setup {
	
	public WebDriver driver;
    protected ExtentReports extent = new ExtentReports();
    protected ExtentSparkReporter avent =new ExtentSparkReporter("Report.html") ;
	
	public void setup(String Browser) {
		if(Browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
		}
		else if(Browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(Browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
     	extent.attachReporter(avent);
		driver.get("https://65956794a3598900a1bfbd31--cerulean-torrone-e715a9.netlify.app/");
		driver.manage().window().maximize();
	}
	
	
		public void tearDown() {
		driver.close();
	  	extent.flush();

	}

}
