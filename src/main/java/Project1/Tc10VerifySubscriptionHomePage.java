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
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFiles.MainPage;

public class Tc10VerifySubscriptionHomePage {
	private WebDriver driver;
	private MainPage mainPage;


	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
	}

	@Test
	public void Subscription_Home_pg()  {
		String expectedHomePageTitle="Automation Exercise";
		String ActualHomePageTitle= driver.getTitle();
		Assert.assertEquals(ActualHomePageTitle, expectedHomePageTitle, "Failed to Land on Home Page");

		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Verify text 'SUBSCRIPTION' in the footer
        Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class);
        WebElement subscriptionText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Subscription']")));
        Assert.assertTrue(subscriptionText.isDisplayed(), "Text 'SUBSCRIPTION' is not visible in the footer");


        // Enter email address in input and click arrow button
        WebElement emailInput = driver.findElement(By.xpath("//input[@id='susbscribe_email']"));
        emailInput.sendKeys("Sarita@gmail.com");
        
        WebElement arrowButton = driver.findElement(By.xpath("//button[@id='subscribe']"));
        arrowButton.click();

        // Verify success message 'You have been successfully subscribed!' is visible
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='You have been successfully subscribed!']")));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message 'You have been successfully subscribed!' is not visible");
	}

	@AfterTest	
	public void tearDown() {
		driver.quit();

	}

	
}
