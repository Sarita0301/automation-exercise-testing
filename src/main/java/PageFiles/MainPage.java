package PageFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
	

	private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        String ActualHomePageTitle=driver.getTitle();
    }

    public boolean isHomePageVisible() {
        WebElement homePageTitle = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[1]/a"));
        return homePageTitle.isDisplayed();
    }

    public void clickSignupLoginButton() {
        WebElement signupLoginButton = driver.findElement(By.xpath("//a[text()=' Signup / Login']"));
        signupLoginButton.click();
    }
    
    



}
