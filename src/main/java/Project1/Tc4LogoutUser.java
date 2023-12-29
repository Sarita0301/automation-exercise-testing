package Project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tc4LogoutUser {
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
	public void LogoutUserTest() throws InterruptedException {
		mainPage.navigateToHomePage("https://automationexercise.com/");

		Assert.assertTrue(mainPage.isHomePageVisible());

		mainPage.clickSignupLoginButton();
		Assert.assertTrue(loginPage.loginToYourAccountVisible());


		// Fill out the login form
		loginPage.enterEmailAndPassWithValidData("SaritaPadhi@gmail.com", "12345678");
	    loginPage.clickLoginButton();

	//Verify that 'Logged in as username' is visible
		System.out.println("User name is visible:"+driver.findElement(By.xpath("//*[text()=' Logged in as ']")).isDisplayed());
		loginPage.clickLogOutButton();
//		Verify that user is navigated to login page		
	System.out.println("Navigated to:"+loginPage.loginToYourAccountVisible());	

	}
	@AfterTest	
	public void tearDown() {
		driver.quit();

	}
}
