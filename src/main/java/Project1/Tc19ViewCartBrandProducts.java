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

public class Tc19ViewCartBrandProducts {
	private WebDriver driver;
	private MainPage mainPage;

	@BeforeTest
	public void setUp() {
		driver = new ChromeDriver();
		mainPage = new MainPage(driver);
		mainPage.navigateToHomePage("https://automationexercise.com/");
	}

	@Test
	public void CartBrand()  {
		//Click on 'Products' button
		driver.findElement(By.xpath("//a[@href='/products']")).click();

		//Verify that Brands are visible on left side bar
		WebElement BrandsVisbile=driver.findElement(By.xpath("//*[@class='brands_products']"));
		Assert.assertTrue(BrandsVisbile.isDisplayed(),"Brands are not visible on left side bar");
		
		//
		driver.findElement(By.xpath("/html/body/section[2]/div[1]/div/div[1]/div/div[3]/div/ul/li[5]/a")).click();

		//Verify that user is navigated to brand page and brand products are displayed
		WebElement ConfirmText=driver.findElement(By.xpath("//h2[@class='title text-center']"));
		if(ConfirmText.getText().contains("BRAND - BABYHUG PRODUCTS")) {
			System.out.println("Right product is displayed");
		}
		else {
			System.out.println("Products are not visible");
		}


		//On left side bar, click on any other brand link
		driver.findElement(By.xpath("//a[@href='/brand_products/H&M']")).click();

		//Verify that user is navigated to that brand page and can see productsWebElement ConfirmText=driver.findElement(By.xpath("//h2[@class='title text-center']"));
		WebElement ConfirmText2=driver.findElement(By.xpath("//h2[@class='title text-center']"));
		if(ConfirmText2.getText().contains("BRAND - H&M PRODUCTS")) {
			System.out.println(" 2nd Right product is displayed");
		}
		else {
			System.out.println("2nd Products are not visible");
		}

	}

	@AfterTest	
	public void tearDown() {
		driver.quit();

	}

}
