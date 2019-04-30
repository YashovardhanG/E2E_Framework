package stepsDefination;

import java.io.IOException;

import org.testng.Assert;

import ObjectRepository.Homepage;
import ObjectRepository.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.Selenium.Base.BaseClass;

public class stepsDefinition_Portal extends BaseClass{
	
	//WebDriver driver;
	//Homepage hp = new Homepage(driver);
	//LoginPage lp = new LoginPage(driver);
	
	@Given("Browser is chrome")
	public void browser_is_chrome() throws IOException {
		
		driver = InitializeDriver();
	}

	@When("Demo portal is launched")
	public void demo_portal_is_launched() {
		
		OpenUrl();
	}

	@When("User click on Login Link")
	public void user_click_on_Login_Link() {
		Homepage hp = new Homepage(driver);
	    hp.loginlink().click();
	}

	@Then("User Login Page is Opened")
	public void user_Login_Page_is_Opened() {
		LoginPage lp = new LoginPage(driver);
	    Assert.assertEquals(lp.pageTitle().getText(),prop.getProperty("LoginPageTitle")); 
	    
	}

	@Then("User provides {string} and {string} and submit")
	public void user_provides_and_and_submit(String email, String password) {
		System.out.println(email);
		System.out.println(password);
		LoginPage lp = new LoginPage(driver);
		lp.email().sendKeys(email);
	    lp.password().sendKeys(password);
	    lp.submit().click();
	}

	@Then("User Login successfully")
	public void user_Login_successfully() {
	    System.out.println("User Logging..");
	}

	@Then("{string} is displayed")
	public void is_displayed(String email) {
		LoginPage lp = new LoginPage(driver);
		Assert.assertEquals(lp.userDisplayed().getText(),email);
		driver.quit();
	}

	@Then("Expected title is displayed")
	public void expected_title_is_displayed() {
		Homepage home= new Homepage(driver);
		String title=home.homePageTitle();
		Assert.assertTrue(prop.get("HomePageTitle").equals(title), "Title Mismatch");
		
		driver.quit();
	}
	
	
	 @And("^User provides (.+) and (.+) and submit$")
	    public void user_provides_and_and_submit1(String username, String password) throws Throwable {
		 System.out.println(username);
			System.out.println(password);
			LoginPage lp = new LoginPage(driver);
			lp.email().sendKeys(username);
		    lp.password().sendKeys(password);
		    lp.submit().click();
	    }

	    @And("^(.+) is displayed$")
	    public void is_displayed1(String username) throws Throwable {
	    	LoginPage lp = new LoginPage(driver);
			Assert.assertEquals(lp.userDisplayed().getText(),username);
			driver.quit();
			
			
	    }
	
}
