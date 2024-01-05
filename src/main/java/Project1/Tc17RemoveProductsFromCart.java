package Project1;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
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

public class Tc17RemoveProductsFromCart {
	private WebDriver driver;
	private MainPage mainPage;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
	}

	@Test
	public void SearchPage() {
		String expectedHomePageTitle="Automation Exercise";
		String ActualHomePageTitle= driver.getTitle();
		Assert.assertEquals(ActualHomePageTitle, expectedHomePageTitle, "Failed to Land on Home Page");

		
		//1st product Add to Cart
		driver.findElement(By.xpath("/html/body/section[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/div[1]/a")).click();
		
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class);

		WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/div[1]/div/div[2]/div/div[1]/div/div/div[3]/button")));
		continueShoppingButton.click();

		//Click 'Cart' button
		driver.findElement(By.xpath("//a[@href='/view_cart']//parent::li")).click(); 

		//Verify that cart page is displayed
		WebElement CartPage=driver.findElement(By.xpath("//li[@class='active']"));
		Assert.assertTrue(CartPage.isDisplayed(),"Cart page is Not displayed");

		//Click 'X' button corresponding to particular product
		WebElement Xbutton=driver.findElement(By.xpath("//i[@class='fa fa-times']"));
		Xbutton.click();
		// Verify that the product is removed from the cart
		WebElement isProductRemoved =driver.findElement(By.xpath("//p[@class='text-center']"));
		if (isProductRemoved.isDisplayed()) {
			System.out.println("Cart is empty! Click here to buy products.");
		} else {
			System.out.println("failed to delete the Product");
		}
	}

	@AfterTest	public void tearDown() {
		driver.quit();

	}


}
