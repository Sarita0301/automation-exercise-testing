package Project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFiles.LoginPageTc2;
import PageFiles.MainPage;

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
	public void LogoutUserTest(){
		mainPage.navigateToHomePage("https://automationexercise.com/");

		Assert.assertTrue(mainPage.isHomePageVisible());

		mainPage.clickSignupLoginButton();
		Assert.assertTrue(loginPage.loginToYourAccountVisible());


		// Fill out the login form
		loginPage.enterEmailAndPassWithValidData("SaritaPadhi@gmail.com", "12345678");
	    loginPage.clickLoginButton();

	//Verify that 'Logged in as username' is visible
		WebElement LoggedUsernameVisible=driver.findElement(By.xpath("//*[text()=' Logged in as ']"));
		Assert.assertTrue(LoggedUsernameVisible.isDisplayed(),"Logged as username Not visible");
		loginPage.clickLogOutButton();

		
		//		Verify that user is navigated to login page		
	System.out.println("Navigated to:"+loginPage.loginToYourAccountVisible());	

	}
	@AfterTest	
	public void tearDown() {
		driver.quit();

	}
}
