package Project1;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFiles.MainPage;

public class Tc22AddToCartFromRecommendedItems {
	private WebDriver driver;
	private MainPage mainPage;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
	}

	@Test
	public void SearchPage() throws InterruptedException {
		String expectedHomePageTitle="Automation Exercise";
		String ActualHomePageTitle= driver.getTitle();
		if(ActualHomePageTitle.equals(expectedHomePageTitle)) {
			System.out.println("Successfully landed to Home Page");
		}
		else {
			System.out.println("Failed to Land on Home Page");
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        
        Thread.sleep(3000);
        //4. Verify 'RECOMMENDED ITEMS' are visible
        WebElement recommendedItem= driver.findElement(By.xpath("//h2[text()='recommended items']"));
        if(recommendedItem.isDisplayed()) {
        	System.out.println("'RECOMMENDED ITEMS' are visible");
        }
        else {
        	System.out.println("'RECOMMENDED ITEMS' are not visible");
        }
        Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class);
	
        //Click on 'Add To Cart' on Recommended product
       // driver.findElement(By.xpath("//*[@id=\"recommended-item-carousel\"]/div/div[2]/div[1]/div/div/div/a")).click();
        WebElement AddToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\\\"recommended-item-carousel\\\"]/div/div[2]/div[1]/div/div/div/a")));
        AddToCart.click();
        
        	//Click on 'View Cart' button
        
	WebElement ViewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[text()='View Cart']//parent::a")));
    ViewCart.click();
        //driver.findElement(By.xpath("//u[text()='View Cart']//parent::a")).click();
        
        //Verify that product is displayed in cart page
        driver.findElement(By.xpath("//*[@id=\"product-4\"]/td[2]/h4/a")).isDisplayed();
        
	}


	@AfterTest	
	public void tearDown() {
		


	}
}
