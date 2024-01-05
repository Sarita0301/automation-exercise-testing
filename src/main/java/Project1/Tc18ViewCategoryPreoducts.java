package Project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageFiles.MainPage;

public class Tc18ViewCategoryPreoducts {
	private WebDriver driver;
	private MainPage mainPage;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
	}

	@Test
	public void CategoryProduct() {
		String expectedHomePageTitle="Automation Exercise";
		String ActualHomePageTitle= driver.getTitle();
		Assert.assertEquals(ActualHomePageTitle, expectedHomePageTitle, "Failed to Land on Home Page");

		//Click on any category link under 'Women' category, for example: Dress
				driver.findElement(By.xpath("//*[@id=\"accordian\"]/div[1]/div[1]/h4/a/span/i")).click();
				driver.findElement(By.xpath("//a[@href='/category_products/1']")).click();

				//Verify that category page is displayed and confirm text 'WOMEN - TOPS PRODUCTS'
				WebElement ConfirmText=driver.findElement(By.xpath("//h2[@class='title text-center']"));
				if(ConfirmText.getText().contains("WOMEN - DRESS PRODUCTS")) {
					System.out.println("Right product is displayed");
				}
				else {
					System.out.println("Products are not visible");
				}
				// On left side bar, click on any sub-category link of 'Men' category
				driver.findElement(By.xpath("//*[@id=\"accordian\"]/div[2]/div[1]/h4/a/span")).click();
				driver.findElement(By.xpath("//a[@href='/category_products/3']")).click();

				//Verify that user is navigated to that category page
				WebElement ConfirmText1=driver.findElement(By.xpath("/html/body/section/div/div[2]/div[2]/div/h2"));
				if(ConfirmText1.isDisplayed()) {
					System.out.println("Right products related to Men's TShirts is displayed");
				}
				else {
					System.out.println("Products are not related to Men's TShirts displayed");
				}	
	}


	@AfterTest	
	public void tearDown() {
		driver.quit();

	}



}
