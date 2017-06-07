package testcases_cloud;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest_cloud;
import commons.LogEvent;
import pages.HomePage;
import pages.LogInPage;
import pages.MyAccountPage;
import pages.OfferPage;
import pages.PageFactory;
import pages.SignUpPage;
import pages.TourPage;

public class Travels_Bookings_cloud extends AbstractTest_cloud {
	WebDriver driver;
	LogEvent LOG;
	HomePage homePage;
	LogInPage loginPage;
	SignUpPage signupPage;
	MyAccountPage myaccountPage;
	TourPage tourPage;
	OfferPage offerPage;

	// test data
	private String username = "thanhtruong_" + randomString(3) + "@auto.com";
	private String password = "123123";
	private String msg_wishlist = "No Wishlist Items Found";
	private String tour_thailand = "6 Days Around Thailand";
	private String guest_name = "thanhmt";
	private String guest_passport = "456789";
	private String guest_age = "27";
	private String total_amount = "USD $92.40";
	private String deposit_now = "USD $9.24";
	private String status_reserved = "Reserved";
	private String message_booking = "You must confirm your booking otherwise it will be cancelled, please contact us for further information.";
	private String contact_name = "Thanh auto";
	private String contact_phone = "01264206206";
	private String contact_message = "This is a message";
	private String msg_submit = "Thanks For Contacting";
	private String tour_hongkong = "Hong Kong Island Tour";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browser, String url) throws Exception {
		DOMConfigurator.configure("src\\test\\resources\\log4j.xml");
		LOG = new LogEvent();

		driver = openBrowser(browser, url);
		homePage = PageFactory.getHomePage(driver);

		LOG.info("Open the Login page");
		signupPage = homePage.openSignUpPage(driver);
	}

	@Test
	public void TC_01_create_an_account_and_login() {
		LOG.info("create an account");
		signupPage.createAccount(username, password);
		myaccountPage = signupPage.openMyAccountPage(driver);
	}

	@Test
	public void TC_02_the_Wishlist_tab_after_logging() {
		LOG.info("Choose the Wishlist tab");
		myaccountPage.chooseTab("Wishlist");

		LOG.info("Verify that the message \"No Wishlist Items Found\" is displayed");
		assertEquals(myaccountPage.getMessageWishlistTab(), msg_wishlist);
	}

	@Test
	public void TC_03_User_books_Tour() {
		LOG.info("Open the Tour Page");
		myaccountPage.selectMenu("Tour");
		tourPage = myaccountPage.openTourPage(driver);

		LOG.info("Choose \"6 Days Around Thailand\" tour");
		tourPage.chooseTour(tour_thailand);

		LOG.info("Click on \"Book Now\" button");
		tourPage.clickButton("Book Now");

		LOG.info("Add more 1 Guest");
		tourPage.addaGuestWith("Name", guest_name);
		tourPage.addaGuestWith("Passport", guest_passport);
		tourPage.addaGuestWith("Age", guest_age);

		LOG.info("Confirm this booking");
		tourPage.clickButton("CONFIRM THIS BOOKING");

		LOG.info("Choose \"Pay on Arrival\" option");
		tourPage.choosePaymentType("Pay on Arrival");

		LOG.info("Verify info of Booking");
		assertEquals(tourPage.getTotalAmount(), total_amount);
		assertEquals(tourPage.getDeposit_Now(), deposit_now);
		assertEquals(tourPage.getStatus("Reserved"), status_reserved);
		assertEquals(tourPage.getMessageBooking(), message_booking);

	}

	@Test
	public void TC_04_User_contacts_a_Offer() {
		LOG.info("Open the Offer menu");
		myaccountPage.selectMenu("Offer");
		offerPage = myaccountPage.openOfferPage(driver);

		LOG.info("Choose \"Rent Car\" item");
		offerPage.selectOffer("Rent Car");

		LOG.info("Input info into Contact form");
		offerPage.enterValueToField("Name", contact_name);
		offerPage.enterValueToField("Phone", contact_phone);
		offerPage.enterValueToFieldMsg("Message", contact_message);

		LOG.info("Click on Contact button");
		offerPage.submitContact();

		LOG.info("Verify that the message \"Thanks For Contacting\" is displayed");
		assertEquals(offerPage.getMessageSubmit(), msg_submit);
	}

	@Test
	public void TC_05_User_adds_a_tour_to_Wishlist_successful() {
		LOG.info("Open the Tour page");
		myaccountPage.selectMenu("Tour");
		tourPage = myaccountPage.openTourPage(driver);

		LOG.info("Choose \"Hong Kong Island Tour\" tour");
		tourPage.chooseTour(tour_hongkong);
		tourPage.addToWishlist();

		LOG.info("Verify that this tour is added");
		assertEquals(tourPage.isAdded(), "Remove from wishlist");

	}

	@AfterClass
	public void afterClass() throws Exception {
		closeBrowser(driver);
	}

}
