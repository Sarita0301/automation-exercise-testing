package Project1;

import java.time.Duration;

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

public class Tc3LoginUserWithInvalidData {
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
	public void LoginUserTestInvalid() {
		mainPage.navigateToHomePage("https://automationexercise.com/");

		Assert.assertTrue(mainPage.isHomePageVisible());

		mainPage.clickSignupLoginButton();
		Assert.assertTrue(loginPage.loginToYourAccountVisible());


		// Fill out the login form
		loginPage.enterEmailAndPassWithInValidData("saritapadhi@gmail.cm", "   ");
		loginPage.clickLoginButton();

		//Verify error 'Your email or password is incorrect!' is visible
		WebElement ErrorMassage=driver.findElement(By.xpath("//p[text()='Your email or password is incorrect!']"));
		Assert.assertTrue(ErrorMassage.isDisplayed(),"Error Massage is not visible");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
	}
	@AfterTest	public void tearDown() {
		driver.quit();

	}
}
