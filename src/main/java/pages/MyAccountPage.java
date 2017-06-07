package pages;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import interfaces.MyAccountPageUI;

/**
 * 
 *
 */
public class MyAccountPage extends AbstractPage {
	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
	}

	public void chooseTab(String value) {
		clickToElement(driver, MyAccountPageUI.DYNAMIC_FIELD, value);
	}

	public String getMessageWishlistTab() {
		return getTextOfElement(driver, MyAccountPageUI.MSG_WISHLIST);
	}

	public void selectMenu(String value) {
		clickToElement(driver, MyAccountPageUI.DYNAMIC_FIELD, value);
	}

	public TourPage openTourPage(WebDriver driver) {
		return PageFactory.getTourPage(driver);
	}

	public String isTourDisplay() {
		return getTextOfElement(driver, MyAccountPageUI.TOUR_ITEM);
	}

	public OfferPage openOfferPage(WebDriver driver) {
		return PageFactory.getOfferPage(driver);
	}

	public boolean isURLNavigate() {
		try {
			return getURL(driver).contains("www.phptravels.net/account/");
		} catch (Exception e) {
			return false;
		}
	}
}
