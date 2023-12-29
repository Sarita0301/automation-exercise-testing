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

public class Tc15PlaceOrderRegisterBeforeCheckout {
	private WebDriver driver;
	private MainPage mainPage;
	private SignupPAge signupPage;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		signupPage = new SignupPAge(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
		System.out.println(driver.getTitle());
	}
	@Test
	public void registerUserTest() throws InterruptedException {
		mainPage.navigateToHomePage("https://automationexercise.com/");

		Assert.assertTrue(mainPage.isHomePageVisible());

		mainPage.clickSignupLoginButton();
		Assert.assertTrue(signupPage.isNewUserSignupVisible());


		// Fill out the signup form
		signupPage.enterNameAndEmail("Seema", "Seema13@gmail.com");
		signupPage.clickSignupButton();

		//Verify that 'ENTER ACCOUNT INFORMATION' is visible
		System.out.println("Verify that 'ENTER ACCOUNT INFORMATION' is visible:"+driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/h2/b")).isDisplayed());

		driver.findElement(By.xpath("//input[@id='id_gender2']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//select[@id='days']")).sendKeys("11");
		driver.findElement(By.xpath("//select[@id='months']")).sendKeys("December");
		driver.findElement(By.xpath("//select[@id='years']")).sendKeys("1991");    
		driver.findElement(By.xpath("//input[@id='newsletter']")).click();
		driver.findElement(By.xpath("//input[@id='optin']")).click();
		driver.findElement(By.xpath("//input[@id=\"first_name\"]")).sendKeys("Seema");
		driver.findElement(By.xpath("//input[@id=\"last_name\"]")).sendKeys("Saxsena");
		driver.findElement(By.xpath("//input[@id='company']")).sendKeys("Excelr pvt ltd");
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("A-802 regency park");
		driver.findElement(By.xpath("//input[@id='address2']")).sendKeys("Chakki Naka Kalyan");
		driver.findElement(By.xpath("//select[@id='country']")).sendKeys("India");
		driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Maharashtra");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Mumbai");
		driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys("421306");

		driver.findElement(By.xpath("//input[@id='mobile_number']")).sendKeys("12334432");
		driver.findElement(By.xpath("//button[@type='submit' and text()]")).click();

		Thread.sleep(3000);

		//Verify 'ACCOUNT CREATED!' and click 'Continue' button

		System.out.println("Account Created is visible:"+driver.findElement(By.xpath("//h2[@class='title text-center']")).isDisplayed());
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();

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
		CardName.sendKeys("Aryan P");
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


	@AfterTest	
	public void tearDown() {
     driver.quit();
	}
}

