package Project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tc7VerifyTestCasePage {
	private WebDriver driver;
	private MainPage mainPage;
	

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
		System.out.println(driver.getTitle());
	}
	@Test
	public void TCpage() throws InterruptedException {
		mainPage.navigateToHomePage("https://automationexercise.com/");

		Assert.assertTrue(mainPage.isHomePageVisible());
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("/html/body/section[1]/div/div/div/div[1]/div/div[1]/div[1]/a[1]/button")).click();
		
		//Verify user is navigated to test cases page successfully
		System.out.println("Verify user is navigated to test cases page successfully:"+driver.findElement(By.xpath("//h2[@class='title text-center']")).isDisplayed());
 
	
	}
	
	@AfterTest	
	public void tearDown() {
		driver.quit();

	}

}
