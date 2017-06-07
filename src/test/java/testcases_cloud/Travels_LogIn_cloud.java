package testcases_cloud;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest_cloud;
import commons.Constants;
import commons.LogEvent;
import pages.HomePage;
import pages.LogInPage;
import pages.PageFactory;

public class Travels_LogIn_cloud extends AbstractTest_cloud {
	WebDriver driver;
	LogEvent LOG;
	HomePage homePage;
	LogInPage loginPage;

	// test data
	private String error_message = "Invalid Email or Password";
	private String valid_email = "thanhtruong@auto.com";
	private String valid_password = "123123";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browser, String url) throws Exception {
		DOMConfigurator.configure("src\\test\\resources\\log4j.xml");
		LOG = new LogEvent();

		driver = openBrowser(browser, url);
		homePage = PageFactory.getHomePage(driver);

		LOG.info("Open the Login page");
		loginPage = homePage.openLogInPage(driver);
	}

	@Test
	public void TC_01_Not_input_email_and_password() {
		LOG.info("Click on \"Login\" button");
		loginPage.submitLogin();

		LOG.info("Verify that \"Invalid Email or Password\" will be displayed");
		assertEquals(loginPage.getErrorMessage(), error_message);
	}

	@Test
	public void TC_02_LogIn_with_a_valid_email_format_without_password() {
		LOG.info("Input an valid email format");
		loginPage.enterValueToField("Email", valid_email);

		LOG.info("Click on \"Login\" button");
		loginPage.submitLogin();

		LOG.info("Verify that \"Invalid Email or Password\" will be displayed");
		assertEquals(loginPage.getErrorMessage(), error_message);
	}

	@Test
	public void TC_03_Login_with_a_password_without_email() {
		LOG.info("Input a valid password");
		loginPage.enterValueToField("Password", valid_password);
	}

	@Test
	public void TC_04_Login_with_an_invalid_account() {
		LOG.info("Login an account which is not existing in DB");
		loginPage.enterValueToField("Email", valid_email);
		loginPage.enterValueToField("Password", valid_password);

		LOG.info("Click on \"Login\" button");
		loginPage.submitLogin();

		LOG.info("Verify that \"Invalid Email or Password\" will be displayed");
		assertEquals(loginPage.getErrorMessage(), error_message);
	}

	@Test
	public void TC_05_Login_successfully() throws InterruptedException {
		LOG.info("Login an valid account");
		loginPage.enterValueToField("Email", Constants.USEREMAIL);
		loginPage.enterValueToField("Password", Constants.PASSWORD);

		LOG.info("Click on \"Login\" button");
		loginPage.submitLogin();
		Thread.sleep(1000);

		LOG.info("The page http://www.phptravels.net/account/ is navigated");
		assertTrue(loginPage.isCurrentURL());
		LOG.info("Hi message is displayed");
		assertEquals(loginPage.getWelcomeMessage(), "Hi, John Smith");
	}

	@AfterClass
	public void afterClass() throws Exception {
		closeBrowser(driver);
	}

}
