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

public class Tc12AddProductsInCart {
	private WebDriver driver;
	private MainPage mainPage;


	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
	}

	@Test
	public void ProductIn_CartPage() {
		String expectedHomePageTitle="Automation Exercise";
		String ActualHomePageTitle= driver.getTitle();
		Assert.assertEquals(ActualHomePageTitle,expectedHomePageTitle,"Home Page not visible");
		//Click product button
		driver.findElement(By.xpath("//a[@href='/products']")).click();

		//1st product Add to Cart
		driver.findElement(By.xpath("/html/body/section[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/div[1]/a")).click();

		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500))
				.ignoring(NoSuchElementException.class);

		WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/div[1]/div/div[2]/div/div[1]/div/div/div[3]/button")));
		continueShoppingButton.click();

		//2nd Product Add to Cart
		WebElement AddtoCart2ndProcut = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/div[1]/div/div[2]/div/div[4]/div/div[1]/div[1]/a")));
		AddtoCart2ndProcut.click();   

		WebElement continueShoppingButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[1]/div/div/div[3]/button")));
		continueShoppingButton2.click();
		//Click 'View Cart' button
		driver.findElement(By.xpath("//a[@href='/view_cart']//parent::li")).click(); 

		// Verify both products are added to Cart
		WebElement VerifyProduct1=driver.findElement(By.xpath("//*[@id=\"product-1\"]/td[2]"));
		Assert.assertTrue(VerifyProduct1.getText().contains("Blue Top"),"1st Product is not displayed in cart page");
		
		WebElement VerifyProduct2=driver.findElement(By.xpath("//*[@id=\"product-3\"]/td[2]"));
		Assert.assertTrue(VerifyProduct2.getText().contains("Sleeveless Dress"),"2nd Product is not displayed in cart page");
		

		// Verify their prices, quantity and total price
		WebElement productPrice1 = driver.findElement(By.xpath("//*[@id=\"product-1\"]/td[3]"));
		WebElement productQuantity1 = driver.findElement(By.xpath("//*[@id=\"product-1\"]/td[4]"));
		WebElement productTotalPrice1 = driver.findElement(By.xpath("//*[@id=\"product-1\"]/td[5]"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		Assert.assertTrue(productPrice1.isDisplayed() && productQuantity1.isDisplayed() && productTotalPrice1.isDisplayed(), "product1 price, quantity and total price are Not visible");


		// Verify their prices, quantity and total price
		WebElement productPrice2 = driver.findElement(By.xpath("//*[@id=\"product-3\"]/td[3]"));
		WebElement productQuantity2 = driver.findElement(By.xpath("//*[@id=\"product-3\"]/td[4]"));
		WebElement productTotalPrice2 = driver.findElement(By.xpath("//*[@id=\"product-3\"]/td[5]"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		Assert.assertTrue(productPrice2.isDisplayed() && productQuantity2.isDisplayed() && productTotalPrice2.isDisplayed(), "product2 price, quantity and total price are Not visible");


	}

	@AfterTest	public void tearDown() {
		driver.quit();

	}
}
