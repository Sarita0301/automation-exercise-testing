package Project1;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFiles.MainPage;

public class Tc6ContactUsForm {
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
	public void ContactUsPage() {
		driver.findElement(By.xpath("//*[@href='/contact_us']")).click();
		System.out.println("Verify 'GET IN TOUCH' is visible:"+driver.findElement(By.xpath("//h2[text()='Get In Touch']")).isDisplayed());
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Sarita");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("SaritaPadhi@gmail.com");
		driver.findElement(By.xpath("//input[@name='subject']")).sendKeys("Product no delivered");
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("I have order my product on last week, but the same is not yet delivered and the status of order is showing the delivered.");
		System.out.println("000");
		WebElement fileInput = driver.findElement(By.xpath("//input[@name='upload_file']"));
		fileInput.click();
		System.out.println("===");
		File file = new File("C:\\Users\\aryan\\OneDrive\\Desktop\\.png");
		fileInput.sendKeys(file.getAbsolutePath());




	}

	@AfterTest	
	public void tearDown() {
		//driver.quit();

	}





}
