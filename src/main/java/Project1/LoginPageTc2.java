package Project1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageTc2 {
	private WebDriver driver;

    public LoginPageTc2(WebDriver driver) {
        this.driver = driver;
    }

    public boolean loginToYourAccountVisible() {
    	WebElement loginToUrAcText=driver.findElement(By.xpath("//h2[text()='Login to your account']"));
    	return loginToUrAcText.isDisplayed();
    	}

    public void enterEmailAndPassWithValidData(String email, String password) {
        WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
        passwordField.sendKeys(password);
    }

    public void enterEmailAndPassWithInValidData(String Inemail, String Inpassword) {
        WebElement InvalidEmailField = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        InvalidEmailField.sendKeys(Inemail);

        WebElement InvalidPasswordField = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
        InvalidPasswordField.sendKeys(Inpassword);
    }
    
    
    // Implement other methods for signup page interactions
    // ...

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.xpath("//button[@data-qa='login-button']"));
        loginButton.click();
    }
    public void clickLogOutButton() {
        WebElement logOutButton = driver.findElement(By.xpath("//a[@href='/logout']"));
        logOutButton.click();
    }
    

}
