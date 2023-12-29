package Project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Project1.MainPage;
import Project1.SignupPAge;

public class Tc1Registration_page {
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
		signupPage.enterNameAndEmail("Sarita", "SaritaPadhi@gmail.com");
		signupPage.clickSignupButton();

		//Verify that 'ENTER ACCOUNT INFORMATION' is visible
		System.out.println("Verify that 'ENTER ACCOUNT INFORMATION' is visible:"+driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/h2/b")).isDisplayed());

		driver.findElement(By.xpath("//input[@id='id_gender2']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//select[@id='days']")).sendKeys("6");
		driver.findElement(By.xpath("//select[@id='months']")).sendKeys("December");
		driver.findElement(By.xpath("//select[@id='years']")).sendKeys("1994");    
		driver.findElement(By.xpath("//input[@id='newsletter']")).click();
		driver.findElement(By.xpath("//input[@id='optin']")).click();
		driver.findElement(By.xpath("//input[@id=\"first_name\"]")).sendKeys("Sarita");
		driver.findElement(By.xpath("//input[@id=\"last_name\"]")).sendKeys("padhi");
		driver.findElement(By.xpath("//input[@id='company']")).sendKeys("Excelr pvt ltd");
		driver.findElement(By.xpath("//input[@id='address1']")).sendKeys("A-802 regency park");
		driver.findElement(By.xpath("//input[@id='address2']")).sendKeys("Chakki Naka Kalyan");
		driver.findElement(By.xpath("//select[@id='country']")).sendKeys("India");
		driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Maharashtra");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Mumbai");
		driver.findElement(By.xpath("//input[@id='zipcode']")).sendKeys("421306");

		driver.findElement(By.xpath("//input[@id='mobile_number']")).sendKeys("730333379");
		driver.findElement(By.xpath("//button[@type='submit' and text()]")).click();

		Thread.sleep(3000);
		//Verify that 'ACCOUNT CREATED!' is visible

		System.out.println("Account Created is visible:"+driver.findElement(By.xpath("//h2[@class='title text-center']")).isDisplayed());
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		System.out.println("User name is visible:"+driver.findElement(By.xpath("//a[text()=' Logged in as ']")).isDisplayed());
		//driver.findElement(By.xpath("//a[@href='/delete_account']")).click();
		//System.out.println("Account deleted is visible:"+driver.findElement(By.xpath("//*[text()='Account Deleted!']")).isDisplayed()); 
		//	    

	}
	@AfterTest	public void tearDown() {
		driver.quit();

	}

}
