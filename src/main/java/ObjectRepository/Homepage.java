package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
	
	WebDriver driver;
	
	
         By loginlink= By.className("ico-login");
         
         By registerlink =By.className("ico-register");
         
          By pagetitle=By.xpath("//div[@class='header-logo']/a/img[@title='']");
         
         
         public Homepage(WebDriver driver) {
			
        	 this.driver = driver;
		}

        

		public WebElement loginlink () {
        	 
        	 
        	return driver.findElement(loginlink);
        	 
         }
         
         
		public WebElement registerlink () {
       	 
       	 
        	return driver.findElement(registerlink);
        	 
         }
		
         
		


		public String homePageTitle() {
			
			return driver.findElement(pagetitle).getAttribute("alt");
		}
}
