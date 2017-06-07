package pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {

	public static HomePage getHomePage(WebDriver driver) {
		return new HomePage(driver);
	}

	public static SignUpPage getSignUpPage(WebDriver driver) {
		return new SignUpPage(driver);
	}

	public static LogInPage getLoginPage(WebDriver driver) {
		return new LogInPage(driver);
	}

	public static MyAccountPage getMyAccountPage(WebDriver driver) {
		return new MyAccountPage(driver);
	}

	public static TourPage getTourPage(WebDriver driver) {
		return new TourPage(driver);
	}

	public static OfferPage getOfferPage(WebDriver driver) {
		return new OfferPage(driver);
	}

}
