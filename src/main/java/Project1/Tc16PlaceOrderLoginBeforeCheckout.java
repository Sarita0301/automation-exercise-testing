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

public class Tc16PlaceOrderLoginBeforeCheckout {
	private WebDriver driver;
	private MainPage mainPage;
	private LoginPageTc2 loginPage;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		loginPage = new LoginPageTc2(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
		System.out.println(driver.getTitle());
	}
	@Test
	public void LoginUserTest() throws InterruptedException {
		String expectedHomePageTitle="Automation Exercise";
		String ActualHomePageTitle= driver.getTitle();
		if(ActualHomePageTitle.equals(expectedHomePageTitle)) {
			System.out.println("Successfully landed to Home Page");
		}
		else {
			System.out.println("Failed to Land on Home Page");
		}

		mainPage.clickSignupLoginButton();
		Assert.assertTrue(loginPage.loginToYourAccountVisible());


		// Fill out the login form
		loginPage.enterEmailAndPassWithValidData("SaritaPadhi@gmail.com", "12345678");
		loginPage.clickLoginButton();

		//Verify ' Logged in as username' at top
		WebElement loggedInUserName=driver.findElement(By.xpath("/html/body/header/div/div/div/div[2]/div/ul/li[10]/a"));
		if(loggedInUserName.isDisplayed()) {
			System.out.println("Logged In as UserName is visible");
		}
		else {
			System.out.println("Logged In as UserName Not Visible");
		}


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
		WebElement CartPage= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='active']")));
		if (CartPage.isDisplayed()) {
			System.out.println("Cart page is displayed");
		} else {
			System.out.println("Cart page is Not displayed");
		}

		driver.findElement(By.xpath("//a[@class='btn btn-default check_out']")).click();


		// Verify Address Details and Review Your Order
		System.out.println("Verify Address Details is visible:"+driver.findElement(By.xpath("//h2[text()='Address Details']")).isDisplayed());

		System.out.println("Verify Review Your Order is visible:"+driver.findElement(By.xpath("//h2[text()='Review Your Order']")).isDisplayed());



		//Enter description in comment text area and click 'Place Order'

		WebElement commentTextArea=driver.findElement(By.xpath("//textarea[@name='message']"));
		commentTextArea.sendKeys("Request you to please send the parcel during the Non - Office hours.");


		// Click 'Place Order' button
		WebElement placeOrderButton = driver.findElement(By.xpath("//a[@href='/payment']"));
		placeOrderButton.click();
		//Enter payment details: Name on Card, Card Number, CVC, Expiration date
		WebElement CardName=driver.findElement(By.xpath("//input[@name='name_on_card']"));
		CardName.sendKeys("sarita");
		WebElement CardNumber=driver.findElement(By.xpath("//input[@name='card_number']"));
		CardNumber.sendKeys("2345243378738");
		WebElement CvCNumber=driver.findElement(By.xpath("//input[@name='cvc']"));
		CvCNumber.sendKeys("232");
		WebElement ExpiryMonth=driver.findElement(By.xpath("//input[@name='expiry_month']"));
		ExpiryMonth.sendKeys("12");
		WebElement  ExpiryYear=driver.findElement(By.xpath("//input[@name='expiry_year']"));
		ExpiryYear.sendKeys("2025");

		//Click 'Pay and Confirm Order' button
		WebElement PayConfirmorderButton=driver.findElement(By.xpath("//button[@id='submit']"));
		PayConfirmorderButton.click();

		//Verify success message 'Your order has been placed successfully!'
		WebElement OrderSuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Congratulations! Your order has been confirmed!']")));
		if (OrderSuccessMessage.isDisplayed()) {
			System.out.println("Success message 'Your order has been placed successfully!' is visible");
		} else {
			System.out.println("Success message is not visible");
		}	
		driver.findElement(By.xpath("//a[@href='/delete_account']")).click();

		WebElement DeleteMessage=driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div"));
		if(DeleteMessage.isDisplayed()) {
			System.out.println("ACCOUNT DELETED!");
		}
		else {
			System.out.println("ACCOUNT DELETED! not visible");
		}
		//click 'Continue' button
		driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();

	}
	@AfterTest	public void tearDown() {
		driver.quit();

	}
}
