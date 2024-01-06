package PagesObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	
	public static WebDriver driver;
	
	public Login(WebDriver driver) {
		this.driver=driver;		
		
	}
	By email=By.className("input1");
	By password=By.className("in");
	By button=By.xpath("//*[@type='submit']");
	By clientErrorMessgae=By.cssSelector(".message");
	By serverErrorMessgae=By.className("swal2-icon-error");

	
	
	
	public void FillEmail(String Email) {
		driver.findElement(email).sendKeys(Email);	
	}
	public void FillPassword(String Password) {
		driver.findElement(password).sendKeys(Password);
	}
	public 	Dashboard ClickButton() {
		driver.findElement(button).click();
		return new Dashboard(driver);
	}
    public  WebElement clientErrorMessage() {
      WebElement errorMessage = driver.findElement(clientErrorMessgae);
      return errorMessage;
    }
    public WebElement serverErrorMessage() {
        WebElement errorMessage = driver.findElement(serverErrorMessgae);
        return errorMessage;
    }
    
    public Dashboard loginData(String email,String password) {
    	FillEmail(email);
    	FillPassword(password);
    	return ClickButton();
    }
	
}
