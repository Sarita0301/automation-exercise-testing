package Project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tc2LoginUserWithValidData {
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
		mainPage.navigateToHomePage("https://automationexercise.com/");

		Assert.assertTrue(mainPage.isHomePageVisible());

		mainPage.clickSignupLoginButton();
		Assert.assertTrue(loginPage.loginToYourAccountVisible());


		// Fill out the login form
		loginPage.enterEmailAndPassWithValidData("SaritaPadhi@gmail.com", "12345678");
		loginPage.clickLoginButton();

		//Verify that 'Logged in as username' is visible
		System.out.println("User name is visible:"+driver.findElement(By.xpath("//*[text()=' Logged in as ']")).isDisplayed());
		//Verify that 'ACCOUNT DELETED!' is visible
		driver.findElement(By.xpath("//a[@href='/delete_account']")).click();
		System.out.println("Account deleted is visible:"+driver.findElement(By.xpath("//*[text()='Account Deleted!']")).isDisplayed()); 
		Thread.sleep(3000);
	}
	@AfterTest	public void tearDown() {
		driver.quit();

	}

}