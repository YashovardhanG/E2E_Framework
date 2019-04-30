package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ObjectRepository.Homepage;
import ObjectRepository.LoginPage;
import framework.Selenium.Base.BaseClass;

public class Login extends BaseClass {
	
	
	@Test(dataProvider ="getData")
	public void DemoPortalLoginLink(String email , String password) throws IOException, InterruptedException {
		
		test =report.createTest("TestLoginLink");
		driver = InitializeDriver();
		OpenUrl();
		
	    
	    Homepage hp = new Homepage(driver);
	    hp.loginlink().click();
	    LoginPage lp = new LoginPage(driver);
	    Assert.assertEquals(lp.pageTitle().getText(),prop.getProperty("LoginPageTitle")); 
	    log.info("Login link present");
	    lp.email().sendKeys(email);
	    lp.password().sendKeys(password);
	    lp.submit().click();
	    Assert.assertEquals(lp.userDisplayed().getText(),email);
		
	}
	
	 @DataProvider
	 public Object[][] getData() {
		 
		 Object[][] data = new Object[2][2];
		 
		 data[0][0] ="aaa123@aaa.com";
		 data[0][1] ="abcd@1234";
		 
		 data[1][0] ="bbb123@bbb.com";
		 data[1][1] ="abcd@1234";
		
		
		return data;
	}
	
	
}
