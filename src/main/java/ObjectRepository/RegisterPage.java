package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "gender-male")

	WebElement gender;

	@FindBy(name = "FirstName")

	WebElement Firstname;

	@FindBy(name = "LastName")

	WebElement Lastname;

	@FindBy(id = "Email")

	WebElement Email;
	
	@FindBy(id = "Password")

	WebElement Password;
	
	@FindBy(id = "ConfirmPassword")

	WebElement ConfirmPassword;
	
	@FindBy(id = "register-button")

	WebElement RegButton;

	public WebElement gender() {

		return gender;

	}

	public WebElement firstName() {

		return Firstname;

	}

	public WebElement lastName() {

		return Lastname;

	}

	public WebElement email() {

		return Email;

	}
	
	public WebElement password() {

		return Password;

	}
	
	public WebElement confirmPassword() {

		return ConfirmPassword;

	}
	
		

	public WebElement regButton() {
		// TODO Auto-generated method stub
		return RegButton;
	}

}
