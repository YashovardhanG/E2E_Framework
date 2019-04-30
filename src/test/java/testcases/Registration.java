package testcases;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ObjectRepository.Homepage;
import ObjectRepository.RegisterPage;
import framework.Selenium.Base.BaseClass;

public class Registration extends BaseClass {

	@Test(dataProvider = "getData")
	public void DemoPortalRegisterLink(String Firstname, String Lastname, String Email, String Password,
			String ConfirmPassword) throws IOException {

		
		test = report.createTest("TestRegisterLink");
		driver = InitializeDriver();
		OpenUrl();

		Homepage hp = new Homepage(driver);
		hp.registerlink().click();

		RegisterPage reg = new RegisterPage(driver);
		reg.gender().click();
		reg.firstName().sendKeys(Firstname);
		reg.lastName().sendKeys(Lastname);
		reg.email().sendKeys(Email);
		reg.password().sendKeys(Password);
		reg.confirmPassword().sendKeys(ConfirmPassword);
		Assert.assertTrue(reg.regButton().isEnabled());
		log.info("Registration was successful");

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		int rowCount = 0;
		int colCount = 0;
	    XSSFSheet sh = workbook.getSheet("RegistrationData");
		System.out.println(workbook.getSheetName(0));
		rowCount = sh.getLastRowNum();
		colCount = sh.getRow(0).getLastCellNum();
		System.out.println(rowCount + " " + colCount);

		Object data[][] = new Object[rowCount][colCount];

		for (int row = 0; row < rowCount; row++) {

			for (int col = 0; col < colCount; col++) {

				data[row][col] = sh.getRow(row + 1).getCell(col).getStringCellValue();

			}
		}

		return data;
	}

}
