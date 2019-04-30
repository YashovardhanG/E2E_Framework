package framework.Selenium.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {

	public WebDriver driver;
	public static Properties prop = new Properties();
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports report = new ExtentReports();
	public static ExtentTest test;
	public static Logger log;
	public Date d;
	public static XSSFWorkbook workbook;
	public String respath = "\\src\\test\\java\\framework\\Selenium\\resources\\";

	@BeforeSuite

	public void Prerequisites() throws IOException {

//**************************Extent Report Config*********************************************************************************************	
		d = new Date();
		String timestamp = d.toString().replaceAll(":", "_").replace(" ", "_");
		htmlreporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\Reports\\Report_" + timestamp + ".html");
		htmlreporter.loadXMLConfig(System.getProperty("user.dir") + respath + "extent-config.xml");

		report.setSystemInfo("Operating System", "Windows10");
		report.setSystemInfo("UserName", "Yashovardhan Goyal");
		report.setSystemInfo("Environment", "QA");
		report.attachReporter(htmlreporter);

//**************************Log4j2 Config****************************************************************************************************

		FileInputStream inputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\framework\\Selenium\\resources\\log4j2.xml");
		ConfigurationSource source = new ConfigurationSource(inputStream);
		Configurator.initialize(null, source);
		log = LogManager.getLogger();
//*****************************************************XL data*********************************************************************************

		FileInputStream xlsinput = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\framework\\Selenium\\resources\\Data.xlsx");
		workbook = new XSSFWorkbook(xlsinput);

	}

	public WebDriver InitializeDriver() throws IOException {

		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")
				+ "\\src\\test\\java\\framework\\Selenium\\resources\\Configuration.properties"));

		prop.load(fis);

		String Browser = prop.getProperty("Browser");

		if (Browser.equals("Chrome")) {

			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Resources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-features=VizDisplayCompositor");
			driver = new ChromeDriver(options);

		}

		else if (Browser.equals("Firefox")) {

			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		return driver;

	}

	public void OpenUrl() {

		driver.get(prop.getProperty("URL"));

	}

	public String takeScreenShot(String testname) throws IOException {

		d = new Date();
		String timestamp = d.toString().replaceAll(":", "_").replace(" ", "_");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destpath = System.getProperty("user.dir") + "\\Screenshots\\Screenshot_"+testname + timestamp + ".png";
		File dest = new File(destpath);
		FileUtils.copyFile(src, dest);
		return destpath;

	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@AfterMethod
	public void getResultTest(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS,
					MarkupHelper.createLabel("Test case PASSED--> " + result.getName(), ExtentColor.GREEN));
			test.log(Status.INFO, "Below is the screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(result.getName())).build());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel("Test case FAILED--> " + result.getName(), ExtentColor.RED));
			test.fail(result.getThrowable());
			test.log(Status.INFO, "Below is the screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(result.getName())).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP,
					MarkupHelper.createLabel("Test case SKIPPED--> " + result.getName(), ExtentColor.AMBER));
			test.fail(result.getThrowable());
			test.log(Status.INFO, "Below is the screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(result.getName())).build());
		}

		driver.quit();

	}

	@AfterSuite
	public void endSuite() {

		report.flush();

	}

}
