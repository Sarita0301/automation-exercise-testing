package Project1;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFiles.MainPage;

import org.openqa.selenium.WebElement;

public class Tc8VerifyAllProductsAndProductDetailPage {
	private WebDriver driver;
	private MainPage mainPage;


	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
	}

	@Test
	public void productPage() {
		String expectedHomePageTitle="Automation Exercise";
		String ActualHomePageTitle= driver.getTitle();
		Assert.assertEquals(ActualHomePageTitle, expectedHomePageTitle, "Failed to Land on Home Page");

		//Verify user is navigated to ALL PRODUCTS page successfully
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		String expectedproductTitle= "Automation Exercise - All Products";
		String ActualproductTitle=driver.getTitle();
		Assert.assertEquals(ActualproductTitle, expectedproductTitle, "Failed to Land on product Page");


		//WebElement productl=driver.findElement(By.xpath("//*[@class='features_items']"));

		// The products list is visible
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class);
		WebElement ProductList=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("features_items")));
		Assert.assertTrue(ProductList.isDisplayed(),"Products list is not visible");
		
		//Click on 'View Product' of first product
		WebElement viewProductLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@href='/product_details/1']")));
		viewProductLink.click();

		//        // Verify user is landed to product detail page

		WebElement productdetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='product-information']")));
		Assert.assertTrue(productdetails.isDisplayed(),"Not landed to product detail page");
		
		// Verify product details are visible: product name, category, price, availability, condition, brand
		WebElement productCategory = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[1]"));
		WebElement productPrice = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/span"));
		WebElement productAvailability = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[2]/b"));
		WebElement productCondition = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[3]/b"));
		WebElement productBrand = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/p[4]/b"));
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		
		
		Assert.assertTrue(productdetails.isDisplayed() && productCategory.isDisplayed() && productPrice.isDisplayed() &&
				productAvailability.isDisplayed() && productCondition.isDisplayed() && productBrand.isDisplayed(),"products related to search are Not visible");
		

	}

	@AfterTest	
	public void tearDown() {
		driver.quit();

	}


}
