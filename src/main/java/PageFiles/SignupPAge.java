package PageFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignupPAge {
	private WebDriver driver;

    public SignupPAge(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNewUserSignupVisible() {
    	WebElement newUserSingupText=driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/h2"));
    	return newUserSingupText.isDisplayed();
    	}

    public void enterNameAndEmail(String name, String email) {
        WebElement nameField = driver.findElement(By.xpath("//form[@action='/signup']//child::input[@type='text']"));
        nameField.sendKeys(name);

        WebElement emailField = driver.findElement(By.xpath("//form[@action='/signup']//child::input[@type='email']"));
        emailField.sendKeys(email);
    }

    // Implement other methods for signup page interactions
    // ...

    public void clickSignupButton() {
        WebElement signupButton = driver.findElement(By.xpath("//form[@action='/signup']//child::button[@type='submit']"));
        signupButton.click();
    }
}
