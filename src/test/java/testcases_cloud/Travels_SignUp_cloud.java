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
import commons.LogEvent;
import pages.HomePage;
import pages.MyAccountPage;
import pages.PageFactory;
import pages.SignUpPage;

public class Travels_SignUp_cloud extends AbstractTest_cloud {
	WebDriver driver;
	LogEvent LOG;
	HomePage homePage;
	SignUpPage signupPage;
	MyAccountPage myaccountPage;

	// test data
	private String invalid_email = "thanhtruong@@";
	private String valid_email = "thanhtruong_" + randomString(4) + "@auto.com";
	private static String fixed_email = null;
	private String firstname = "Truong";
	private String lastname = "Thanh";
	private String validPassword = "123123";
	private String validPassword2 = "123456";
	private String invalidpassword = "123";
	private String error_msg_Email = "The Email field must contain a valid email address.";
	private String error_msg_password = "The Password field must be at least 6 characters in length.";
	private String error_matching_password = "Password not matching with confirm password.";
	private String error_existing_email = "Email Already Exists.";
	private String welcomeMsg = "Hi, " + firstname + " " + lastname;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browser, String url) throws Exception {
		DOMConfigurator.configure("src\\test\\resources\\log4j.xml");
		LOG = new LogEvent();

		driver = openBrowser(browser, url);
		homePage = PageFactory.getHomePage(driver);

		LOG.info("Open the SignUp page");
		signupPage = homePage.openSignUpPage(driver);
	}

	@Test
	public void TC_01_Register_unsuccessful_with_all_empty_fields() {
		LOG.info("Click on \"Sign Up\" button without inputting value");
		signupPage.submitSignUp();

		LOG.info("Verify that error messages will be displayed for required fields");
		assertTrue(signupPage.verifyErrorMesssageEmtpyFields());
	}

	@Test
	public void TC_02_Register_unsuccessful_with_an_invalid_email() {
		LOG.info("Input an invalid email into \"Email\" field");
		signupPage.enterValueToField("Email", invalid_email);

		LOG.info("Inpute valid value into all required fields");
		signupPage.enterValueToField("First Name", firstname);
		signupPage.enterValueToField("Last Name", lastname);
		signupPage.enterValueToField("Password", validPassword);
		signupPage.enterValueToField("Confirm Password", validPassword);

		LOG.info("Click on \"Sign Up\" button");
		signupPage.submitSignUp();

		LOG.info(
				"Verify that error messages \"The Email field must contain a valid email address.\" will be displayed");
		assertEquals(signupPage.getErrorMessageField(), error_msg_Email);
	}

	@Test
	public void TC_03_Register_unsuccessful_with_an_invalid_password() {
		LOG.info("Input an invalid password");
		signupPage.enterValueToField("Password", invalidpassword);
		signupPage.enterValueToField("Confirm Password", invalidpassword);
		signupPage.enterValueToField("First Name", firstname);
		signupPage.enterValueToField("Last Name", lastname);
		signupPage.enterValueToField("Email", valid_email);

		LOG.info("Click on \"Sign Up\" button");
		signupPage.submitSignUp();

		LOG.info(
				"Verify that error messages \"The Password field must be at least 6 characters in length.\" will be displayed");
		assertEquals(signupPage.getErrorMessageField(), error_msg_password);
	}

	@Test
	public void TC_04_Register_unsuccessful_with_password_confirm_password_not_match() {
		LOG.info("Input an valid password");
		signupPage.enterValueToField("Password", validPassword);

		LOG.info("Input another password for Confirm Password");
		signupPage.enterValueToField("Confirm Password", validPassword2);
		signupPage.enterValueToField("First Name", firstname);
		signupPage.enterValueToField("Last Name", lastname);
		signupPage.enterValueToField("Email", valid_email);

		LOG.info("Click on \"Sign Up\" button");
		signupPage.submitSignUp();
		System.out.println(signupPage.getErrorMessageField());
		LOG.info("Verify that error messages \"Password not matching with confirm password.\" will be displayed");
		assertEquals(signupPage.getErrorMessageField(), error_matching_password);
	}

	@Test
	public void TC_05_Register_successful() throws InterruptedException {
		LOG.info("Input valid value into all required fields");
		signupPage.enterValueToField("First Name", firstname);
		signupPage.enterValueToField("Last Name", lastname);
		signupPage.enterValueToField("Email", valid_email);
		fixed_email = valid_email;
		signupPage.enterValueToField("Password", validPassword);
		signupPage.enterValueToField("Confirm Password", validPassword);

		LOG.info("Click on \"Sign Up\" button without inputting value");
		signupPage.submitSignUp();
		myaccountPage = signupPage.openMyAccountPage(driver);
		Thread.sleep(2000);

		LOG.info("The page http://www.phptravels.net/account/ is navigated");
		assertTrue(myaccountPage.isURLNavigate());

		LOG.info("Hi message is displayed");
		assertEquals(signupPage.getWelcomeMessage(), welcomeMsg);
		signupPage.logout();
	}

	@Test
	public void TC_06_Register_unsuccessful_with_an_existing_email() {
		LOG.info("Input an existing email");
		signupPage.enterValueToField("Email", fixed_email);

		LOG.info("Input valid value to all required fields");
		signupPage.enterValueToField("First Name", firstname);
		signupPage.enterValueToField("Last Name", lastname);
		signupPage.enterValueToField("Password", validPassword);
		signupPage.enterValueToField("Confirm Password", validPassword);

		LOG.info("Click on \"Sign Up\" button");
		signupPage.submitSignUp();

		LOG.info("Verify that error messages \"Email Already Exists.\" will be displayed");
		assertEquals(signupPage.getErrorMessageField(), error_existing_email);
	}

	@AfterClass
	public void afterClass() throws Exception {
		closeBrowser(driver);
	}

}
