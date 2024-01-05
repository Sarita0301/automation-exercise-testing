package Project1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFiles.MainPage;
import PageFiles.SignupPAge;

public class Tc5RegisterUserWithExistingEmail {
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
	public void registerUserwithEmail() {
		mainPage.navigateToHomePage("https://automationexercise.com/");

		Assert.assertTrue(mainPage.isHomePageVisible());

		mainPage.clickSignupLoginButton();
		Assert.assertTrue(signupPage.isNewUserSignupVisible());


		// Fill out the signup form
		signupPage.enterNameAndEmail("Sarita", "SaritaPadhi@gmail.com");
		signupPage.clickSignupButton();

		//Verify error 'Email Address already exist!' is visible
		System.out.println("Error is visible:"+driver.findElement(By.xpath("//*[text()='Email Address already exist!']")).isDisplayed());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));

	}

	@AfterTest	
	public void tearDown() {
		driver.quit();

	}

}
