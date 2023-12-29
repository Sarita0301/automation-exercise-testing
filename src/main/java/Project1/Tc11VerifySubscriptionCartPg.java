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

public class Tc11VerifySubscriptionCartPg {
	private WebDriver driver;
	private MainPage mainPage;


	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
	}

	@Test
	public void CartPage() throws InterruptedException {
		String expectedHomePageTitle="Automation Exercise";
		String ActualHomePageTitle= driver.getTitle();
		if(ActualHomePageTitle.equals(expectedHomePageTitle)) {
			System.out.println("Successfully landed to Home Page");
		}
		else {
			System.out.println("Failed to Land on Home Page");
		}
		
		// Click 'Cart' button
		driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Verify text 'SUBSCRIPTION' in the footer
        Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class);
        WebElement subscriptionText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Subscription']")));
        if (subscriptionText.isDisplayed()) {
            System.out.println("Text 'SUBSCRIPTION' is visible in the footer");
        } else {
            System.out.println("Text 'SUBSCRIPTION' is not visible in the footer");
        }

        // Enter email address in input and click arrow button
        WebElement emailInput = driver.findElement(By.xpath("//input[@id='susbscribe_email']"));
        emailInput.sendKeys("Sarita@gmail.com");
        
        WebElement arrowButton = driver.findElement(By.xpath("//button[@id='subscribe']"));
        arrowButton.click();

        // Verify success message 'You have been successfully subscribed!' is visible
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='You have been successfully subscribed!']")));
        if (successMessage.isDisplayed()) {
            System.out.println("Success message 'You have been successfully subscribed!' is visible");
        } else {
            System.out.println("Success message is not visible");
        }	}

	@AfterTest	
	public void tearDown() {
		driver.quit();

	}
	
	
	
	}
	
		

