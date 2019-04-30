package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
	}

	By pagetitle = By.xpath("//div[@class='page-title']");

	By Email = By.id("Email");
	By Password = By.id("Password");
	By Submit = By.xpath("//div[@class='buttons']/input[@type='submit']");
	
	By UserDisplayed = By.xpath("//div[@class='header-links']/ul/li/a[@class='account']");

	public WebElement pageTitle() {

		return driver.findElement(pagetitle);

	}

	public WebElement email() {

		return driver.findElement(Email);

	}

	public WebElement password() {

		return driver.findElement(Password);

	}
	
	public WebElement submit() {

		return driver.findElement(Submit);

	}	
	
	public WebElement userDisplayed() {

		return driver.findElement(UserDisplayed);

	}
}
