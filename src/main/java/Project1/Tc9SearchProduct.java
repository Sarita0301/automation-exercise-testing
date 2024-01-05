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

public class Tc9SearchProduct {
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

		//Verify user is navigated to ALL PRODUCTS page successfully
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		String expectedproductTitle= "Automation Exercise - All Products";
		String ActualproductTitle=driver.getTitle();
		Assert.assertEquals(ActualproductTitle, expectedproductTitle, "Failed to Land on product Page");



		WebElement searchP=driver.findElement(By.xpath("//input[@name='search']"));
		searchP.sendKeys("Blue Top");
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
		Assert.assertTrue(SearchedProductVisibile.getText().equals("Blue Top"),"Search Product item is not visible");
	}
	@AfterTest	
	public void tearDown() {
		driver.quit();

	}



}
