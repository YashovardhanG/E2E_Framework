package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import ObjectRepository.Homepage;
import framework.Selenium.Base.BaseClass;

public class HomePage extends BaseClass{
	
	@Test
	
	public void homePageTitle() throws IOException {
		
		test =report.createTest("HomePageTitle");
		driver = InitializeDriver();
		OpenUrl();
		
		Homepage home= new Homepage(driver);
		String title=home.homePageTitle();
		Assert.assertTrue(prop.get("HomePageTitle").equals(title), "Title Mismatch");
		log.info("HomePage title verified successfully");
		
		
		
	}

}
