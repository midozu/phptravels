package pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import interfaces.TourPageUI;

public class TourPage extends AbstractPage {
	WebDriver driver;

	public TourPage(WebDriver driver) {
		this.driver = driver;
	}

	public MyAccountPage openMyAccountPage(WebDriver driver) {
		navigateToURL(driver, "http://www.phptravels.net/account/");
		return PageFactory.getMyAccountPage(driver);
	}

	public void chooseTour(String value) {
		clickToElement(driver, TourPageUI.TOUR_NAME, value);
	}

	public void addToWishlist() {
		scrollPageToElement(driver, TourPageUI.DYNAMIC_BUTTON, "Add to wishlist");
		clickToElement(driver, TourPageUI.DYNAMIC_BUTTON, "Add to wishlist");
		acceptAlert(driver);
	}

	public void clickButton(String value) {
		clickToElement(driver, TourPageUI.BOOKNOW_BUTTON, value);
	}

	public void addaGuestWith(String locator, String value) {
		typeToElement(driver, TourPageUI.DYNAMIC_GUEST_FIELD, locator, value);
	}

	public void choosePaymentType(String value) {
		clickToElement(driver, TourPageUI.BOOKNOW_BUTTON, value);
		acceptAlert(driver);
	}

	public String getTotalAmount() {
		return getTextOfElement(driver, TourPageUI.TOTAL_AMOUNT);
	}

	public String getDeposit_Now() {
		return getTextOfElement(driver, TourPageUI.DEPOSIT_AMOUNT);
	}

	public String getStatus(String value) {
		return getTextOfElement(driver, TourPageUI.TOUR_NAME, value);
	}

	public String getMessageBooking() {
		return getTextOfElement(driver, TourPageUI.MESSAGE);
	}

	public String isAdded() {
		String value = "Remove from wishlist";
		return getTextOfElement(driver, TourPageUI.DYNAMIC_BUTTON, value);
	}

}
