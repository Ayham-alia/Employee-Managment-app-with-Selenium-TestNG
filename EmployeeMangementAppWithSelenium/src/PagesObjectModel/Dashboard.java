package PagesObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Dashboard {
	
	static WebDriver driver;
	
	 
	public Dashboard(WebDriver driver) {
		this.driver=driver;
		
	}
	
	public static WebElement title() {
		return driver.findElement(By.cssSelector(".mainDashboard h1"));	
	}
	public static void search(String searchData) {
		driver.findElement(By.xpath("//*[@type='search']")).sendKeys(searchData);
	}
	public static void clearSearch(String searchData) {
		driver.findElement(By.xpath("//*[@type='search']")).clear();
	}
	public static List<WebElement> TableEmps() {
		return driver.findElements(By.cssSelector(".tbody tr td"));
	}
	public static WebElement checkIn(int index) {
		return  driver.findElements(By.xpath("//*[text()='Check in']")).get(index);
	}
	public static WebElement checkOut(int index) {
		return driver.findElements(By.xpath("//*[text()='Check out']")).get(index);
	}
	public static WebElement Msg(){
		return driver.findElement(By.cssSelector(".sucssesMsg"));
	}
	public static WebElement alertButton(){
		return driver.findElement(By.xpath("\"//*[text()='OK']\""));
	}
	public static List<WebElement> empName(){
		return driver.findElements(By.cssSelector(".tbody tr td:nth-child(1)"));
	}
	public static WebElement nextArrow(){
		return driver.findElement(By.xpath("//*[@rel='next']"));
	}
	public static WebElement prevArrow(){
		return driver.findElement(By.xpath("//*[@rel='prev']"));
	}
	public static List<WebElement> pagesNum(){
		return driver.findElements(By.cssSelector(".page-link"));
	}
	public static WebElement specificPage(int numpage){
		return driver.findElement(By.cssSelector("ul.Pagination li.page-item:nth-child("+numpage+"):not(.page-link) a.page-link"));
	}
	public static WebElement logout(){
		return driver.findElement(By.cssSelector(".ActionsMenu a:last-child"));
	}
	public static WebElement navigateToReviewVocations(){
		return driver.findElement(By.cssSelector(".ActionsMenu a:nth-child(2)"));
	}




    public static WebElement[][] listTo2DArray(List<WebElement> list, int subarraySize) {
        int fullRows = list.size() / subarraySize;
        int remainingElements = list.size() % subarraySize;
        int totalRows = fullRows + (remainingElements > 0 ? 1 : 0);

        WebElement[][] array = new WebElement[totalRows][];

        for (int i = 0; i < totalRows; i++) {
            int rowSize = (i < fullRows) ? subarraySize : remainingElements;
            array[i] = new WebElement[rowSize];
            for (int j = 0; j < rowSize; j++) {
                array[i][j] = list.get(i * subarraySize + j);
            }
        }

        return array;
    }

	
	
	
	

}
