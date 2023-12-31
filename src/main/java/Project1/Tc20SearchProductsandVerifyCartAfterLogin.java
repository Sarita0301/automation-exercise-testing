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

import PageFiles.LoginPageTc2;
import PageFiles.MainPage;

public class Tc20SearchProductsandVerifyCartAfterLogin {
	private WebDriver driver;
	private MainPage mainPage;
	private LoginPageTc2 loginPage;
	String SearchItem="Blue Top";

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
		loginPage = new LoginPageTc2(driver);
	}

	@Test
	public void SearchPageVerify() {
		String expectedHomePageTitle="Automation Exercise";
		String ActualHomePageTitle= driver.getTitle();
		Assert.assertEquals(ActualHomePageTitle, expectedHomePageTitle, "Failed to Land on Home Page");

		//Click on 'Products' button
		driver.findElement(By.xpath("//a[@href='/products']")).click();

		//Verify user is navigated to ALL PRODUCTS page successfully

		String expectedproductTitle= "Automation Exercise - All Products";
		String ActualproductTitle=driver.getTitle();
		Assert.assertEquals(ActualproductTitle,expectedproductTitle, "Failed to Navigate to all Products page.");

		//Enter product name in search input and click search button
		WebElement searchP=driver.findElement(By.xpath("//input[@name='search']"));
		searchP.sendKeys(SearchItem);
		driver.findElement(By.xpath("//button[@type='button']")).click();

		// The search products is visible
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class);
		WebElement SearchProductList=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='title text-center']")));
		Assert.assertTrue(SearchProductList.isDisplayed(),"Search Products is not visible");

		//Verify all the products related to search are visible

		WebElement SearchedProductVisibile=driver.findElement(By.xpath("/html/body/section[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/div[1]/p"));
		Assert.assertTrue(SearchedProductVisibile.getText().equals(SearchItem),"Search Product item is not visible");
		//Add those products to cart
		driver.findElement(By.xpath("/html/body/section[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/div[1]/a")).click();

		WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//u[text()='View Cart']")));
		viewCart.click();
		// Verify that products are visible in cart
		WebElement ProductAddedInCart=driver.findElement(By.xpath("//*[@id=\"product-1\"]/td[2]"));
		Assert.assertTrue(ProductAddedInCart.getText().contains(SearchItem),"Product is not displayed in cart page with the exact search");

		//Click 'Signup / Login' button and submit login details
		driver.findElement(By.xpath("//a[@href='/login']//parent::li")).click();
		loginPage.enterEmailAndPassWithValidData("SaritaPadhi@gmail.com", "12345678");
		loginPage.clickLoginButton();

		//Again, go to Cart page
		driver.findElement(By.xpath("//a[@href='/view_cart']//parent::li")).click();

		//Verify that those products are visible in cart after login as well
		WebElement ProductAddedInCart1=driver.findElement(By.xpath("//*[@id=\"product-1\"]/td[2]"));

		Assert.assertTrue(ProductAddedInCart1.getText().contains(SearchItem),"Product is not displayed in cart page with the exact search");

	}
	@AfterTest	
	public void tearDown() {
		driver.quit();


	}
}
