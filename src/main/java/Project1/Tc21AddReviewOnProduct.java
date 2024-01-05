package Project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFiles.MainPage;

public class Tc21AddReviewOnProduct {
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


		//Click on 'Products' button
		driver.findElement(By.xpath("//a[@href='/products']")).click();

		//Verify user is navigated to ALL PRODUCTS page successfully

		String expectedproductTitle= "Automation Exercise - All Products";
		String ActualproductTitle=driver.getTitle();

		Assert.assertEquals(ActualproductTitle,expectedproductTitle,"Failed to Navigate to all Products page.");

		//Click on 'View Product' button
		driver.findElement(By.xpath("//a[@href='/product_details/4']")).click();
		//Verify 'Write Your Review' is visible
		WebElement WriteReviewIsvisible=driver.findElement(By.xpath("//a[@href='#reviews']"));
		Assert.assertTrue(WriteReviewIsvisible.isDisplayed(),"'Write Your Review' is not visible");
		
		//Enter name, email and review
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Sarita");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("SaritaPadhi@gmail.com");
		driver.findElement(By.xpath("//textarea[@name='review']")).sendKeys("Hello i want to review the product");
		// Click 'Submit' button
		driver.findElement(By.xpath("//button[@id='button-review']")).click();

		//Verify success message 'Thank you for your review.'
		WebElement ReviewSuccessMessage=driver.findElement(By.xpath("//span[text()='Thank you for your review.']"));
		Assert.assertTrue(ReviewSuccessMessage.getText().contains("Thank you for your review"),"Unable to review");
		
	}


	@AfterTest	
	public void tearDown() {
		driver.quit();


	}
}
